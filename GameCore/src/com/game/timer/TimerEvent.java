package com.game.timer;


/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-4
 * 
 * 定时计时抽象类 
 */
public abstract class TimerEvent implements ITimerEvent {
	//定时结束时间
	private long end;
	//定时剩余时间
	private long remain;
	//执行次数
	private int loop;
	//间隔时间
	private long delay;
	
	/**
	 * 计时事件
	 * @param end 执行事件
	 */
	protected TimerEvent(long end){
		this.end = end;
		this.loop = 1;
	}
	
	/**
	 * 循环事件
	 * @param loop 循环次数
	 * @param delay 间隔时间
	 */
	protected TimerEvent(int loop, long delay){
		this.loop = loop;
		this.delay = delay;
		this.end = System.currentTimeMillis() + delay;
	}
	
	public long remain(){
		return this.end - System.currentTimeMillis();
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}


	public long getEnd() {
		return end;
	}


	public void setEnd(long end) {
		this.end = end;
	}


	public long getRemain() {
		return remain;
	}


	public void setRemain(long remain) {
		this.remain = remain;
	}


	public int getLoop() {
		return loop;
	}


	public void setLoop(int loop) {
		this.loop = loop;
		this.end = System.currentTimeMillis() + delay;
	}


	public long getDelay() {
		return delay;
	}


	public void setDelay(long delay) {
		this.delay = delay;
	}
	
}
