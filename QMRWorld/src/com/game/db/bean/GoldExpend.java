package com.game.db.bean;


public class GoldExpend {
	private long unuseIndex;
	private long optTime;
	private long role;
	private int gold;
	private int type;
	
	public long getOptTime() {
		return optTime;
	}
	public void setOptTime(long optTime) {
		this.optTime = optTime;
	}
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getUnuseIndex() {
		return unuseIndex;
	}
	public void setUnuseIndex(long unuseIndex) {
		this.unuseIndex = unuseIndex;
	}
}
