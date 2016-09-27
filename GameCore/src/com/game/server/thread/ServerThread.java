package com.game.server.thread;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.command.ICommand;
import com.game.server.filter.ICommandFilter;
import com.game.timer.ITimerEvent;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-2
 * 
 * 游戏服务器主线程
 */
public class ServerThread extends Thread {

	//日志
	private Logger log = LogManager.getLogger(ServerThread.class);
	//命令执行队列
	private LinkedBlockingQueue<ICommand> command_queue = new LinkedBlockingQueue<ICommand>();
	//计时线程
	private TimerThread timer;
	//线程名称
	protected String threadName;
	//心跳间隔
	protected int heart;
	//过滤器
	private ArrayList<ICommandFilter> filters = new ArrayList<ICommandFilter>();
	//运行标志
	private boolean stop;
	
	private boolean processingCompleted = false;
	
	public ServerThread(ThreadGroup group, String threadName, int heart){
		super(group, threadName);
		this.threadName = threadName;
		this.heart = heart;
		if(this.heart > 0){
			timer = new TimerThread(this);
		}
		this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				log.error(e, e);
				if(timer!=null) timer.stop(true);
				command_queue.clear();
			}
		});
	}
	
	public void run(){
		if(this.heart > 0 && timer!=null){
			//启动计时线程
			timer.start();
		}
		
		stop = false;
		int loop = 0;
		while(!stop){
			ICommand command = command_queue.poll();
			if(command == null){
				try{
					synchronized (this) {
						loop = 0 ;
						processingCompleted = true;
						wait();
					}
				}catch (Exception e) {
					log.error(e, e);
				}
			}else{
				try{
					loop ++;
					processingCompleted = false;
					long start = System.currentTimeMillis();
					boolean result = false;
					for (int i = 0; i < filters.size(); i++) {
						if(!filters.get(i).filter(command)){
							result = true;
							break;
						}
					}
					if(result) continue;
					command.action();
					long end = System.currentTimeMillis();
//					if((command instanceof Handler)) log.error("Main Thread " + threadName + " " + command.getClass().getSimpleName() + " run!");
					//if(command instanceof TimerEvent) 
					if(end-start > 50) log.error(this.getName() + "-->" + command.getClass().getSimpleName() + " run:" + (end-start));
					if(loop > 1000){
						loop = 0;
						try{
							Thread.sleep(1);
						}catch (Exception e) {
							log.error(e, e);
						}
					}
					//Recorder.addRecorder(0, 0, 0, command.getClass().getSimpleName(), end-start, "");
				}catch (Exception e) {
					log.error(e, e);
				}
				
			}
		}
	}
	
	public void stop(boolean flag){
		stop = flag;
		if(timer!=null) this.timer.stop(flag);
		this.command_queue.clear();
		try{
			synchronized (this) {
				if(processingCompleted){
					processingCompleted = false;
					notify();
				}
			}
		}catch (Exception e) {
			log.error("Main Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	/**
	 * 添加命令
	 * @param command 命令
	 */
	public void addCommand(ICommand command){
		try{
			this.command_queue.add(command);
			synchronized (this) {
				notify();
			}
		}catch (Exception e) {
			log.error("Main Thread " + threadName + " Notify Exception:" + e.getMessage());
		}
	}
	
	/**
	 * 添加过滤器
	 * @param filter 过滤器
	 */
	public void addCommandFitler(ICommandFilter filter){
		this.filters.add(filter);
	}
	
	/**
	 * 添加定时事件
	 * @param event 定时事件
	 */
	public void addTimerEvent(ITimerEvent event){
		if(timer!=null) this.timer.addTimerEvent(event);
	}
	
	/**
	 * 移除定时事件
	 * @param event 定时事件
	 */
	public void removeTimerEvent(ITimerEvent event){
		if(timer!=null) this.timer.removeTimerEvent(event);
	}

	public String getThreadName() {
		return threadName;
	}

	public int getHeart() {
		return heart;
	}
}
