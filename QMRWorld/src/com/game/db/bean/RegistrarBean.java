package com.game.db.bean;

public class RegistrarBean {

	private long userid;  //用户id
	private long lastregistrartime;  //最后一次领取登录器奖励的时间
	
	private int registrared; //账号是否领取过登录器奖励
	private int firstrechargeed; //账号是否领取过首冲奖励
	
	private String params;
	
	public RegistrarBean() {
		super();
	}
	public RegistrarBean(long userid){
		super();
		this.userid = userid;
	}
	public RegistrarBean(long userid, String params){
		super();
		this.userid = userid;
		this.params = params;
	}
	public RegistrarBean(long userid, long lastregistrartime) {
		super();
		this.userid = userid;
		this.lastregistrartime = lastregistrartime;
	}
	public RegistrarBean(long userid, long lastregistrartime, int registrared, int firstrechargeed) {
		super();
		this.userid = userid;
		this.lastregistrartime = lastregistrartime;
		this.registrared = registrared;
		this.firstrechargeed = firstrechargeed;
	}
	public int getRegistrared() {
		return registrared;
	}
	public void setRegistrared(int registrared) {
		this.registrared = registrared;
	}
	public int getFirstrechargeed() {
		return firstrechargeed;
	}
	public void setFirstrechargeed(int firstrechargeed) {
		this.firstrechargeed = firstrechargeed;
	}
	public long getUserid() {
		return userid;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getLastregistrartime() {
		return lastregistrartime;
	}
	public void setLastregistrartime(long lastregistrartime) {
		this.lastregistrartime = lastregistrartime;
	}
	
}
