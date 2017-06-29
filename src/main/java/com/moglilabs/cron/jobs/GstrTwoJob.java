package com.moglilabs.cron.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class GstrTwoJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Date date = new Date();
		System.out.println("JOB 2 : " + date.toString());
	}

}
