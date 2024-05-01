package com.nhjclxc.quartztest.test2_trigger;

import com.nhjclxc.quartztest.test1.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author LuoXianchao
 * @since 2024/05/01 16:26
 */
public class QuartzTriggerTest2 {
    public static void main(String[] args) throws Exception {

        // 1.创建调度器 Scheduler
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2.创建JobDetail实例，并与HelloJob类绑定(Job执行内容)，注意：HelloJob必须定义为public
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class) // HelloJob是一个任务实列对象
                .withIdentity("job1", "group1")
                .build();

        JobDataMap triggerJobDataMap = new JobDataMap();
        triggerJobDataMap.put("triggerJobDataMap", "triggerJobDataMaptriggerJobDataMap");
        // 3.构建Trigger实例,每隔5s执行一次
        // Trigger the job to run now, and then repeat every 5 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .usingJobData(triggerJobDataMap)
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        // 4.告诉调度器schedule，按照给定的触发器trigger来执行指定的任务job
        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);

        // 5.开启任务
        // and start it off
        scheduler.start();
        // 关闭任务
//        scheduler.shutdown();
    }
}
