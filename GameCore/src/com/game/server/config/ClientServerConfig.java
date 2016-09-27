package com.game.server.config;

import java.util.ArrayList;
import java.util.List;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器启动配置
 */
public class ClientServerConfig extends ServerConfig {

	private List<ServerInfo> gateServers = new ArrayList<ServerInfo>();
	
	private ServerInfo worldServer;
	
	private ServerInfo publicServer;

	public List<ServerInfo> getGateServers() {
		return gateServers;
	}

	public void setGateServers(List<ServerInfo> gateServers) {
		this.gateServers = gateServers;
	}

	public ServerInfo getWorldServer() {
		return worldServer;
	}

	public void setWorldServer(ServerInfo worldServer) {
		this.worldServer = worldServer;
	}

	public ServerInfo getPublicServer() {
		return publicServer;
	}

	public void setPublicServers(ServerInfo publicServer) {
		this.publicServer = publicServer;
	}
	
}
