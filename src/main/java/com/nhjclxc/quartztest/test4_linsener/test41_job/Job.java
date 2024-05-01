package com.nhjclxc.quartztest.test4_linsener.test41_job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author LuoXianchao
 * @since 2024/05/01 17:32
 */
public class Job implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("JobLinsenerJob 被执行");
    }
}



