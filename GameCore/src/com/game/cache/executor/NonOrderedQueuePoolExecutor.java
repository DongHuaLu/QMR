package com.game.cache.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.command.Handler;

public class NonOrderedQueuePoolExecutor extends ThreadPoolExecutor {

	//日志
	private Logger log = LogManager.getLogger(NonOrderedQueuePoolExecutor.class);
		
	public NonOrderedQueuePoolExecutor(int corePoolSize) {
		super(corePoolSize, corePoolSize, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}

	public void execute(Handler command){
		Work work = new Work(command);
		execute(work);
	}
	
	
	private class Work implements Runnable{

		private Handler command;
		
		public Work(Handler command){
			this.command = command;
		}
		
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			command.action();
			long end = System.currentTimeMillis();
			if(end-start > 50) log.error("NonOrderedQueuePoolExecutor-->" + command.getClass().getSimpleName() + " run:" + (end-start));
		}
	}
}
