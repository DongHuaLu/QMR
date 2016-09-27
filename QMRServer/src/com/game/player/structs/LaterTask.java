package com.game.player.structs;
/**
 * 延时执行任务
 * @author 赵聪慧
 * @2012-8-24 下午12:01:23
 */
public class LaterTask {
	private Runnable run;
	private int interval;
	private int count;
	private int nowcount;
	private long startTime ;
	/**
	 * 
	 * @param run 执行事件
	 * @param interval 间隔 毫秒
	 * @param count	次数
	 */
	public LaterTask(Runnable run,int interval,int count){
		this.run=run;
		this.interval=interval;
		this.count=count;
		this.nowcount=0;
		this.startTime=System.currentTimeMillis();
	}
	public Runnable getRun() {
		return run;
	}
	public void setRun(Runnable run) {
		this.run = run;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNowcount() {
		return nowcount;
	}
	public void setNowcount(int nowcount) {
		this.nowcount = nowcount;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
