package com.game.player.structs;

public class VerifyConfig {

	private String md5key;  //平台登陆key
	private long losttime;  //登陆key失效时间  单位:毫秒
	private String token;   //后门登录令牌
	public String getMd5key() {
		return md5key;
	}
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
	public long getLosttime() {
		return losttime;
	}
	public void setLosttime(long losttime) {
		this.losttime = losttime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
