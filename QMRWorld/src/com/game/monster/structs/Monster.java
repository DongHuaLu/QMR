package com.game.monster.structs;

import com.game.object.GameObject;

public class Monster extends GameObject {

	private static final long serialVersionUID = -7552839121586654588L;

	private int serverId;
	
	private int lineId;
	
	private int modelId;
	
	private int hp;
	
	private int maxHp;
	//状态 1-复活 2-死亡
	private int state;
	//复活时间
	private long reviveTime;
	//杀死者
	private String killer;
	//地图模型ID
	private int mapModelId;
	
	private short birthX;
	private short birthY;

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getReviveTime() {
		return reviveTime;
	}

	public void setReviveTime(long reviveTime) {
		this.reviveTime = reviveTime;
	}

	public String getKiller() {
		return killer;
	}

	public void setKiller(String killer) {
		this.killer = killer;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public short getBirthX() {
		return birthX;
	}

	public void setBirthX(short birthX) {
		this.birthX = birthX;
	}

	public short getBirthY() {
		return birthY;
	}

	public void setBirthY(short birthY) {
		this.birthY = birthY;
	}

}
