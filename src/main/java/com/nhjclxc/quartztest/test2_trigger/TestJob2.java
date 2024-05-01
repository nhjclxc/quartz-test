package com.nhjclxc.quartztest.test2_trigger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author LuoXianchao
 * @since 2024/05/01 16:26
 */
public class TestJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("TestJob2");
    }
}
