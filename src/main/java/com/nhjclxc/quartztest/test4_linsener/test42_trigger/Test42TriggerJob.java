package com.nhjclxc.quartztest.test4_linsener.test42_trigger;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author LuoXianchao
 * @since 2024/05/01 17:32
 */
public class Test42TriggerJob implements org.quartz.Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test42_trigger Test42TriggerJob 被执行");
    }
}



