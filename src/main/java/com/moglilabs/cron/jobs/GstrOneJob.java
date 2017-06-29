package com.moglilabs.cron.jobs;

import static java.net.HttpURLConnection.HTTP_OK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class GstrOneJob implements Job{
	
	public void execute(JobExecutionContext context)
            throws JobExecutionException {
		Date date = new Date();
		System.out.println("JOB 1 : " + date.toString());
		
		
		@SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://ip.jsontest.com/");
		HttpResponse response;
		try {
			response = client.execute(request);
		
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
		
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


}
