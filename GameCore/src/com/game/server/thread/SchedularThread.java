package com.game.server.thread;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.game.timer.SchedulerTask;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-2
 * 
 * 游戏服务器时间表线程
 */
public class SchedularThread extends Thread {

	private static Object obj = new Object();
	//日志
	private Logger log = LogManager.getLogger(SchedularThread.class);
		
	private Scheduler scheduler;
	
	private int count = 0;
	
	private List<SchedulerInfo> infos = new ArrayList<SchedulerInfo>();
	
	public void run(){
		try{
			synchronized (obj) {
				scheduler = StdSchedulerFactory.getDefaultScheduler();
				scheduler.start();
				
				init();
			}
		}catch (SchedulerException e) {
			log.error(e, e);
		}
	}

	private void init(){
		for (int i = 0; i < infos.size(); i++) {
			SchedulerInfo info = infos.get(i);
			try{
				scheduler.scheduleJob(info.getJob(), info.getTrigger());
			}catch (SchedulerException e) {
				log.error(e, e);
			}
		}
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	public void addSchedulerTask(String cron, String className){
		count++;
		JobDetail job = JobBuilder.newJob(SchedulerTask.class)
				.withIdentity("job" + count, "SchedulerTaskGroup")
				.usingJobData("className", className)
				.build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger" + count, "SchedulerTaskGroup")
				.withSchedule(CronScheduleBuilder.cronSchedule(cron))
				.forJob("job" + count, "SchedulerTaskGroup")
				.build();
		synchronized (obj) {
			if(scheduler==null){
				infos.add(new SchedulerInfo(job, trigger));
			}else{
				try{
					scheduler.scheduleJob(job, trigger);
				}catch (SchedulerException e) {
					log.error(e, e);
				}
			}
		}
	}
	
	public void stop(boolean flag){
		try{
			scheduler.shutdown(flag);
		}catch (SchedulerException e) {
			log.error(e, e);
		}
	}
	
	
	private class SchedulerInfo{
		
		private JobDetail job;
		
		private Trigger trigger;
		
		public SchedulerInfo(JobDetail job, Trigger trigger){
			this.job = job;
			this.trigger = trigger;
		}

		public JobDetail getJob() {
			return job;
		}

		public Trigger getTrigger() {
			return trigger;
		}

	}
}
