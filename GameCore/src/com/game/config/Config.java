package com.game.config;

import com.game.server.Server;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器配置
 */
public class Config {
	//停止服务器命令
	public static final String CLOSE_COMMAND = "stop server";
	//计数
	private static int id = 0;
	
	private static Object obj = new Object();
	/**
	 * 获得唯一id
	 * @return id
	 */
	public static long getId(){
		synchronized (obj) {
			id = id + 1;
			return (((long)(Server.server_id & 0xFFFF)) << 48) | (((System.currentTimeMillis() / 1000) & 0x00000000FFFFFFFFl) << 16) | (id & 0x0000FFFF);
		}
	}
}
