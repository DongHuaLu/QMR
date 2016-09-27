package com.game.zones.structs;

import com.game.object.GameObject;

public class ContinuousRaidsInfo extends GameObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5885552173666559942L;

	
	private int time;//扫荡时间
	
	private int zonemodelid;//扫荡副本模组
	
	private int status;//领奖状态

	
	
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getZonemodelid() {
		return zonemodelid;
	}

	public void setZonemodelid(int zonemodelid) {
		this.zonemodelid = zonemodelid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
