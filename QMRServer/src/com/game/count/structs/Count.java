package com.game.count.structs;

import com.game.object.GameObject;
import com.game.pool.MemoryObject;

public class Count extends GameObject implements MemoryObject{

	private static final long serialVersionUID = 9115169575362913470L;
	//计时类型(1-天， 2-周，3-月，4-年， -1-永久)
	private int type;
	//关键字
	private String key;
	//计数数量
	private long count;
	//开始时间
	private long start;
	//最后一次计数时间
	private long lastTime;
	//刷新时间
	/**
	 * 天-距离每天0时秒数
	 * 周-距离周一0时秒数
	 * 月-距离每月1日0时秒数
	 * 年-距离每年1月1日0时秒数	
	 */
	private long refreshTime;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getLastTime() {
		return lastTime;
	}
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	public long getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}
	@Override
	public void release() {
		this.setKey(null);
		this.setType(0);
		this.setStart(0);
		this.setCount(0);
		this.setLastTime(0);
		this.setRefreshTime(0);
	}
}
