package com.nhjclxc.quartztest.test4_linsener.test41_job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author LuoXianchao
 * @since 2024/05/01 17:32
 */
public class MyGlobalJobLinsener implements JobListener {

    /**
     * 监听器的名称
     */
    @Override
    public String getName() {
        return "MyGlobalJobLinsener";
    }

    /**
     * 本监听器监听的任务被执行前调用的方法
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("MyGlobalJobLinsener.jobToBeExecuted");
    }

    /**
     * 任务被触发器被监听器TriggerLinsener否决时执行的方法
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("MyGlobalJobLinsener.jobExecutionVetoed");
    }

    /**
     * 任务被执行之后执行的方法
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("MyGlobalJobLinsener.jobWasExecuted");
    }
}



