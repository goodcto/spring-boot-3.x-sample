package com.ufsite.quartz;

import com.ufsite.quartz.job.PrintPropsJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class ch1 {

    @Test
    public void test1() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();

        scheduler.standby();
        // Scheduler will not execute jobs until it has been started (though they can be scheduled before start())
        scheduler.start();
    }


    @Test
    public void test2() {
        // Define job instance
        JobDetail job1 = newJob(PrintPropsJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("someProp", "someValue")
                .build();

    }

    @Test
    public void test3() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();


        // Define job instance
        JobDetail job = newJob(PrintPropsJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("someProp", "someValue")
                .build();

        // Define a Trigger that will fire "now", and not repeat
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(3).withRepeatCount(5))
                .build();
        // Schedule the job with the trigger
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        while (true) {
        }
    }

    @Test
    public void test4() throws SchedulerException {
        try {
            //1.从StdSchedulerFactory工厂中获取一个任务调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //2. 启动调度器
            scheduler.start();
            System.out.println("scheduler is start...");
            //3. 添加定时任务
            //  3.1 定义job
            JobDetail job = newJob(PrintPropsJob.class).withIdentity("job1", "group1")
                    .usingJobData("someProp", "someValue").build();

            //  3.2 定义Trigger，使得job现在就运行，并每隔3s中运行一次，重复运行5次, withRepeatCount(number)设定job运行次数为number+1
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                    .startNow().withSchedule(simpleSchedule().withIntervalInSeconds(3).withRepeatCount(4)).build();

            //  3.3 交给scheduler去调度
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(100);
            //4. 关闭调度器
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
