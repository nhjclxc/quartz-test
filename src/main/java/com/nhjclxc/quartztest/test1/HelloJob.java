package com.nhjclxc.quartztest.test1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution // 多次调用job实列的时候会对job进行持久化，即会保存数据信息
public class HelloJob implements Job {

    private Integer count;
    private Integer count22;

    public HelloJob(){
        System.out.println("创建新对象了吗？？？");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("key1"));
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("kkk"));
        System.out.println(jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println(jobExecutionContext.getJobDetail().getKey().getGroup());
        System.out.println(jobExecutionContext.getJobDetail().getJobClass().getName());
        System.out.println(jobExecutionContext.getTrigger().getJobDataMap().get("triggerJobDataMap"));

        //this.count一直都是999说明每一次调用任务都会创建一个新的job对象，那么count就被认为是一个无状态的count信息
        // 如果要想使得job有状态，即上一次执行的数据能够保留到下一次任务执行，就必须再Job实现类上加一个注解 @PersistJobDataAfterExecution
        // 加上@PersistJobDataAfterExecution之后quartz就会持久化这个job对应的JobDataMap
        jobExecutionContext.getJobDetail().getJobDataMap().put("count22", ++this.count22);  //持久化count22
        System.out.println(this.count22 + " 我是定时任务的执行体 " + this.count++);




    }

    public void setCount(Integer count){
        this.count = count;
    }

    public void setCount22(Integer count22){
        this.count22 = count22;
    }
}
