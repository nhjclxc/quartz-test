package com.nhjclxc.quartztest.test4_linsener.test42_trigger;


import org.quartz.*;

/**
 * @author LuoXianchao
 * @since 2024/05/01 17:32
 */
public class MyTriggerLinsener implements TriggerListener {

    /**
     * 监听器的名称
     */
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    /**
     * 当被监听的监听器被触发时，对应的job的execute方法被执行前，本方法被执行
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("MyTriggerLinsener.triggerFired");
    }

    /**
     * 这个方法是用来判断是否拒绝job执行的
     *
     * 触发器关联的job被执行前执行这个方法，如果本方法返回false，则job对应的execute方法会被执行，反之则不会执行
     * 可以用这个方法来控制job是否执行
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("MyTriggerLinsener.vetoJobExecution");
//        return false;
        return true;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("MyTriggerLinsener.triggerMisfired");
    }

    /**
     * 触发器关联的job对应的execute方法被执行之后，执行这个方法
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("MyTriggerLinsener.triggerComplete");
    }

}



