package com.moglilabs.cron.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.moglilabs.cron.jobs.GstrOneJob;
import com.moglilabs.cron.jobs.GstrTwoJob;

public class Gstr implements ServletContextListener{

	Scheduler scheduler = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context Initialized");
		
		try {
            // Setup the Job class and the Job group
            JobDetail jobOne =  JobBuilder.newJob(GstrOneJob.class).withIdentity(
            		"GstrOneJob", "Group").build();
            
            JobDetail jobTwo = JobBuilder.newJob(GstrTwoJob.class).withIdentity(
            		"GstrTwoJob", "Group").build();
            		
            // Create a Trigger that fires every 5 minutes.
            Trigger triggerOne = TriggerBuilder.newTrigger()
            		.withIdentity("FistTriggerName", "Group")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
            		.build();
            
            Trigger triggerTwo = TriggerBuilder.newTrigger()
            		.withIdentity("SecondTriggerName", "Group")
            		.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
            		.build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobOne, triggerOne);
            scheduler.scheduleJob(jobTwo, triggerTwo);
            
            
    }
    catch (SchedulerException e) {
            e.printStackTrace();
    }


		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Context Destroyed");
        try 
        {
                scheduler.shutdown();
        } 
        catch (SchedulerException e) 
        {
                e.printStackTrace();
        }

	}

}
