package com.game.server.config;

/**
 * 服务器信息
 * @author heyang E-mail: szy_heyang@163.com
 *
 */
public class ServerInfo {

	private int id;
	
	private String ip;
	
	private int port;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
