package com.game.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.game.server.thread.ServerThread;
import com.game.timer.ITimerEvent;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-5
 * 
 * 计时事件工具类
 */
public class TimerUtil {
	
	private static Logger LOGGER = LogManager.getLogger(TimerUtil.class);
	/**
	 * 添加计时事件
	 * @param event
	 */
	public static void addTimerEvent(ITimerEvent event){
		Thread thread = Thread.currentThread();
		if(thread instanceof ServerThread){
			((ServerThread)thread).addTimerEvent(event);
		}else{
			//日志
			LOGGER.error("Can Not Add Time Event In " + thread.getName());
		}
	}
	
	/**
	 * 移除计时事件
	 * @param event
	 */
	public static void removeTimerEvent(ITimerEvent event){
		Thread thread = Thread.currentThread();
		if(thread instanceof ServerThread){
			((ServerThread)thread).removeTimerEvent(event);
		}else{
			//日志
			LOGGER.error("Can Not Remove Time Event In " + thread.getName());
		}
	}
}
