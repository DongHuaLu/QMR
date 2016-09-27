package com.game.timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchedulerTask implements Job {

	//日志
	private Logger log = LogManager.getLogger(SchedulerTask.class);
		
	public SchedulerTask(){}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data = context.getMergedJobDataMap();
		String className = data.getString("className");
		
		try{
			Class<?> c = Class.forName(className);
			SchedulerEvent job = (SchedulerEvent)c.newInstance();
			job.action();
		}catch (ClassNotFoundException e) {
			log.error(e, e);
		}catch (ClassCastException e) {
			log.error(e, e);
		}catch (IllegalAccessException e) {
			log.error(e, e);
		}catch (InstantiationException e) {
			log.error(e, e);
		}
	}
}
