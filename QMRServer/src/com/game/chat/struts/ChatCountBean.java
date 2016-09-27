package com.game.chat.struts;
/**
 * 
 * @author 赵聪慧
 * @2012-11-21 下午8:57:13
 */
public class ChatCountBean {
	private long firstTime;
	private int count;
	
	public ChatCountBean(long firstTime, int count) {
		super();
		this.firstTime = firstTime;
		this.count = count;
	}
	public long getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(long firstTime) {
		this.firstTime = firstTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
