package com.game.login.config;

public class HeartConfig {

	private long hearttime; //心跳间隔时间
	private long allowtime; //心跳允许间隔时间
	private int success;    //成功减少次数
	private int error;      //错误增加次数
	private long closetime; //心跳关闭时间
	
	public long getHearttime() {
		return hearttime;
	}
	public void setHearttime(long hearttime) {
		this.hearttime = hearttime;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public long getAllowtime() {
		return allowtime;
	}
	public void setAllowtime(long allowtime) {
		this.allowtime = allowtime;
	}
	public long getClosetime() {
		return closetime;
	}
	public void setClosetime(long closetime) {
		this.closetime = closetime;
	}
	
}
