package com.game.player.structs;

public class Player {
	//玩家id
	private long id;
	//玩家所在服务器
	private int server;
	//玩家创建服务器
	private int createServer;
	//玩家用户id
	private String userId;
	//玩家平台
	private String web;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getServer() {
		return server;
	}
	public void setServer(int server) {
		this.server = server;
	}
	public int getCreateServer() {
		return createServer;
	}
	public void setCreateServer(int createServer) {
		this.createServer = createServer;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	
}
