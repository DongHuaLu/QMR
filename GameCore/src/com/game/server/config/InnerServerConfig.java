package com.game.server.config;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器启动配置
 */
public class InnerServerConfig extends ServerConfig {

	//服务器端口
	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
