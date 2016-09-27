package com.game.zones.structs;

public class ZoneApplyData {

	//玩家ID
	private long playerid ;
	
	//申请状态，0未匹配，1匹配成功
	private int applystatus ;

	//准备进入的副本
	private int zonemodelid;
	
	//如果是组队进，这里是有ID的
	private long teamid;
	
	//匹配完成的时间
	private int  time; 
	
	
	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	
	
	
	public int getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(int applystatus) {
		this.applystatus = applystatus;
	}

	public int getZonemodelid() {
		return zonemodelid;
	}

	public void setZonemodelid(int zonemodelid) {
		this.zonemodelid = zonemodelid;
	}

	public long getTeamid() {
		return teamid;
	}

	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
	
}
