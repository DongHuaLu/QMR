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
public class MinaServerConfig extends ServerConfig {
	
	//服务器mina端口
	private int mina_port;
	//服务器mina安全端口
	private int mina_ssl_port;

	public int getMina_port() {
		return mina_port;
	}

	public void setMina_port(int mina_port) {
		this.mina_port = mina_port;
	}

	public int getMina_ssl_port() {
		return mina_ssl_port;
	}

	public void setMina_ssl_port(int mina_ssl_port) {
		this.mina_ssl_port = mina_ssl_port;
	}

}
