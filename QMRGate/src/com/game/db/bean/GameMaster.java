package com.game.db.bean;

public class GameMaster {

	private String username;
	private long userid;
	private int serverid;
	private int gmlevel;
	private long addtimes;
	private String date;
	private int isDeleted;
	
	public GameMaster() {
		super();
	}
	public GameMaster(String username, long userid, int serverid, int gmlevel,
			long addtimes, String date, int isDeleted) {
		super();
		this.username = username;
		this.userid = userid;
		this.serverid = serverid;
		this.gmlevel = gmlevel;
		this.addtimes = addtimes;
		this.date = date;
		this.isDeleted = isDeleted;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getServerid() {
		return serverid;
	}
	public void setServerid(int serverid) {
		this.serverid = serverid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getGmlevel() {
		return gmlevel;
	}
	public void setGmlevel(int gmlevel) {
		this.gmlevel = gmlevel;
	}
	public long getAddtimes() {
		return addtimes;
	}
	public void setAddtimes(long addtimes) {
		this.addtimes = addtimes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
