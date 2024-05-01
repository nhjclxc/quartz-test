package com.nhjclxc.quartztest.test1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

    public static void main(String[] args) throws Exception {

        // 1.创建调度器 Scheduler
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

         JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("key1", "value1");
        jobDataMap.put("key2", "value2");
        // 2.创建JobDetail实例，并与HelloJob类绑定(Job执行内容)，注意：HelloJob必须定义为public
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class) // HelloJob是一个任务实列对象
                .withIdentity("job1", "group1")
                .setJobData(jobDataMap)
                .usingJobData("kkk", "vvv")
                .usingJobData("count", 999)
                .usingJobData("count22", 666)
                .build();
        JobDataMap jobDataMap1 = job.getJobDataMap();
        System.out.println(jobDataMap1 == jobDataMap);

        // JobDataMap独属于某个JobDetail或Trigger，每一次启动程序的时候就创建只有销毁程序的时候销毁，也就是上一次的JobDataMap和下一此执行任务的JobDataMap相同
        // 除非使用持久化

        JobDataMap triggerJobDataMap = new JobDataMap();
        triggerJobDataMap.put("triggerJobDataMap", "triggerJobDataMaptriggerJobDataMap");
        // 3.构建Trigger实例,每隔5s执行一次
        // Trigger the job to run now, and then repeat every 5 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()  // 立刻启动触发器
                .usingJobData(triggerJobDataMap)
                .withSchedule(simpleSchedule()
//                        .withIntervalInHours(24) // 24小时
//                        .withIntervalInMinutes(60) // 60分钟
                        .withIntervalInSeconds(5)  // 5秒
//                        .withIntervalInMilliseconds(1000) // 1000毫秒
                        .repeatForever())
                // 开始时间
//                .startAt(startTime)
                // 结束时间
//                .endAt(endTime)
                .build();
        // 可以指定Trigger再指定的时间范围内进行任务调度
//        LocalDateTime localDateTime = LocalDateTime.now();
//        Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());

//        CronTrigger
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .usingJobData(triggerJobDataMap)
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                .build();

        // 注意每一此的任务调度都会创建一个新的job实列对象
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
