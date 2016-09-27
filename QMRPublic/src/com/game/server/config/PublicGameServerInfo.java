package com.game.server.config;

public class PublicGameServerInfo {

	private int serverId;
	
	private String web;
	
	private String ip;
	
	private int port;
	
	private int sslport;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSslport() {
		return sslport;
	}

	public void setSslport(int sslport) {
		this.sslport = sslport;
	}
	
}
