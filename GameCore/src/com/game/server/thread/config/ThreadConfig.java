package com.game.server.thread.config;
/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-6
 * 
 * 服务器线程配置类信息
 */
public class ThreadConfig {
	//线程名称
	private String threadName;
	//心跳间隔
	private int heart;

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}
	
}
