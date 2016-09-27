package com.game.memorycache.structs;

public class UserInfo {
	//玩家账号id
	private String userId;
	//玩家账号名字
	private String name;
	//玩家账号所在服务器
	private int serverId;
	//玩家账号平台
	private String web;
	//1-请求跨服中，2-跨服中，3-退出跨服
	private int state;
	//时间点
	private long time;
	//玩家角色id
	private long playerId;
	//数据服务器player id
	private long dataServerPlayerId;
	//所在服务器
	private int crossServer;
	//所在服务器平台
	private String crossServerWeb;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getDataServerPlayerId() {
		return dataServerPlayerId;
	}

	public void setDataServerPlayerId(long dataServerPlayerId) {
		this.dataServerPlayerId = dataServerPlayerId;
	}

	public int getCrossServer() {
		return crossServer;
	}

	public void setCrossServer(int crossServer) {
		this.crossServer = crossServer;
	}

	public String getCrossServerWeb() {
		return crossServerWeb;
	}

	public void setCrossServerWeb(String crossServerWeb) {
		this.crossServerWeb = crossServerWeb;
	}
	
}
