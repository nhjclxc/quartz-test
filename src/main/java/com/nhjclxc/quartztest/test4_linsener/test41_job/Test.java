package com.nhjclxc.quartztest.test4_linsener.test41_job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author LuoXianchao
 * @since 2024/05/01 17:32
 */
public class Test {

    public static void main(String[] args) throws SchedulerException {
        // 1.创建调度器 Scheduler
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2.创建JobDetail实例，并与HelloJob类绑定(Job执行内容)，注意：HelloJob必须定义为public
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(Job.class) // HelloJob是一个任务实列对象
                .withIdentity("job1", "group1")
                .build();

        // 3.构建Trigger实例,每隔5s执行一次
        // Trigger the job to run now, and then repeat every 5 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()  // 立刻启动触发器
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)  // 5秒
                        .repeatForever())
                .build();

        // 4.告诉调度器schedule，按照给定的触发器trigger来执行指定的任务job
        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        // 创建并注册一个全局job监听器
        // EverythingMatcher.allJobs()表示MyJobLinsener这个监听器再scheduler上全局有效，也就是scheduler上执行的所有任务都会被MyJobLinsener监听
        scheduler.getListenerManager().addJobListener(new MyGlobalJobLinsener(), EverythingMatcher.allJobs());

        // 创建并注册一个局部job监听器
        // 指定job监听
        JobKey key = job.getKey();
        scheduler.getListenerManager().addJobListener(new MyJobLinsener(), KeyMatcher.keyEquals(JobKey.jobKey(key.getName(), key.getGroup())));


        // 5.开启任务
        // and start it off
        scheduler.start();
        // 关闭任务
//        scheduler.shutdown();
    }
}



