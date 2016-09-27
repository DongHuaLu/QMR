package com.game.db.bean;

public class BlackListBean {

	private int id;
	private String username;
	private long endtime;
	private int endcount;
	private int nowcount;
	private int type;   // 1-账号禁言  2-ip禁言
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public int getEndcount() {
		return endcount;
	}
	public void setEndcount(int endcount) {
		this.endcount = endcount;
	}
	public int getNowcount() {
		return nowcount;
	}
	public void setNowcount(int nowcount) {
		this.nowcount = nowcount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
