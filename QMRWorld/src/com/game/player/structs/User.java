package com.game.player.structs;

public class User {

	private String userId;
	
	private long online;
	
	private long lasttime;
	//0-正常，1-待插入，2-待删除
	private int state;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getOnline() {
		return online;
	}

	public void setOnline(long online) {
		this.online = online;
	}

	public long getLasttime() {
		return lasttime;
	}

	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
