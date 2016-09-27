package com.game.publogin.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器登录成功消息
 */
public class ResLoginSuccessToPublicWorldMessage extends Message{

	//服务器编号
	private int serverId;
	
	//网关编号
	private int gateId;
	
	//来源平台
	private String web;
	
	//用户id
	private String userId;
	
	//角色id
	private long playerId;
	
	//1成年,0未成年,-1未知
	private byte isAdult;
	
	//登录 IP
	private String loginIp;
	
	//登陆类型
	private int logintype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器编号
		writeInt(buf, this.serverId);
		//网关编号
		writeInt(buf, this.gateId);
		//来源平台
		writeString(buf, this.web);
		//用户id
		writeString(buf, this.userId);
		//角色id
		writeLong(buf, this.playerId);
		//1成年,0未成年,-1未知
		writeByte(buf, this.isAdult);
		//登录 IP
		writeString(buf, this.loginIp);
		//登陆类型
		writeInt(buf, this.logintype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器编号
		this.serverId = readInt(buf);
		//网关编号
		this.gateId = readInt(buf);
		//来源平台
		this.web = readString(buf);
		//用户id
		this.userId = readString(buf);
		//角色id
		this.playerId = readLong(buf);
		//1成年,0未成年,-1未知
		this.isAdult = readByte(buf);
		//登录 IP
		this.loginIp = readString(buf);
		//登陆类型
		this.logintype = readInt(buf);
		return true;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 网关编号
	 * @return 
	 */
	public int getGateId(){
		return gateId;
	}
	
	/**
	 * set 网关编号
	 */
	public void setGateId(int gateId){
		this.gateId = gateId;
	}
	
	/**
	 * get 来源平台
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 来源平台
	 */
	public void setWeb(String web){
		this.web = web;
	}
	
	/**
	 * get 用户id
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 1成年,0未成年,-1未知
	 * @return 
	 */
	public byte getIsAdult(){
		return isAdult;
	}
	
	/**
	 * set 1成年,0未成年,-1未知
	 */
	public void setIsAdult(byte isAdult){
		this.isAdult = isAdult;
	}
	
	/**
	 * get 登录 IP
	 * @return 
	 */
	public String getLoginIp(){
		return loginIp;
	}
	
	/**
	 * set 登录 IP
	 */
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	
	/**
	 * get 登陆类型
	 * @return 
	 */
	public int getLogintype(){
		return logintype;
	}
	
	/**
	 * set 登陆类型
	 */
	public void setLogintype(int logintype){
		this.logintype = logintype;
	}
	
	
	@Override
	public int getId() {
		return 204303;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//网关编号
		buf.append("gateId:" + gateId +",");
		//来源平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//角色id
		buf.append("playerId:" + playerId +",");
		//1成年,0未成年,-1未知
		buf.append("isAdult:" + isAdult +",");
		//登录 IP
		if(this.loginIp!=null) buf.append("loginIp:" + loginIp.toString() +",");
		//登陆类型
		buf.append("logintype:" + logintype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}