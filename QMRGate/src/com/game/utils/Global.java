package com.game.utils;

public class Global {

	/**
	 * 服务器心跳参数字符串
	 */
	public static String HEART_PARA = "pt=%s&sid=%d&state=%d&tip=alive&locate=gate";
	
	/**
	 * 服务器心跳地址字符串
	 */
	public static String HEART_WEB = "http://122.226.64.45:9080/QMRBackend/r/callback.do?c=tablehearbeat";
	
}
