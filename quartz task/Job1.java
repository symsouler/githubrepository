package com.bjpowernode.oa.util;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
public class Job1 extends QuartzJobBean{

	private int timeout;
	
	public void setTimeout(int timeout){
		this.timeout=timeout;
	}
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("this time is "+timeout);
		
	}
	
}
