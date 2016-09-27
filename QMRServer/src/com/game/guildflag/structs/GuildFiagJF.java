package com.game.guildflag.structs;

import java.util.HashMap;

public class GuildFiagJF {

	//记录所有对帮旗进行攻击的帮会积分
	private HashMap<Long, Integer> jifenmap = new HashMap<Long, Integer>();
	
	//优先权帮会ID
	private long priorityguildid;
	//优先权帮会名字
	private String priorityguildname;
	//优先权时间
	private int prioritytime;
	
	
	
	
	public HashMap<Long, Integer> getJifenmap() {
		return jifenmap;
	}
	public void setJifenmap(HashMap<Long, Integer> jifenmap) {
		this.jifenmap = jifenmap;
	}
	public long getPriorityguildid() {
		return priorityguildid;
	}
	public void setPriorityguildid(long priorityguildid) {
		this.priorityguildid = priorityguildid;
	}
	public String getPriorityguildname() {
		return priorityguildname;
	}
	public void setPriorityguildname(String priorityguildname) {
		this.priorityguildname = priorityguildname;
	}
	public int getPrioritytime() {
		return prioritytime;
	}
	public void setPrioritytime(int prioritytime) {
		this.prioritytime = prioritytime;
	}
	
}
