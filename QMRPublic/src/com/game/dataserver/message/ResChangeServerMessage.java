package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 切换服务器消息
 */
public class ResChangeServerMessage extends Message{

	//公共游戏服务器Id
	private int serverId;
	
	//服务器平台
	private String web;
	
	//账号id
	private String userId;
	
	//角色id
	private long dataServerPlayerId;
	
	//公共游戏服务器Ip
	private String serverIp;
	
	//公共游戏服务器port
	private int serverPort;
	
	//公共游戏服务器sslport
	private int serverSSLPort;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//公共游戏服务器Id
		writeInt(buf, this.serverId);
		//服务器平台
		writeString(buf, this.web);
		//账号id
		writeString(buf, this.userId);
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//公共游戏服务器Ip
		writeString(buf, this.serverIp);
		//公共游戏服务器port
		writeInt(buf, this.serverPort);
		//公共游戏服务器sslport
		writeInt(buf, this.serverSSLPort);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//公共游戏服务器Id
		this.serverId = readInt(buf);
		//服务器平台
		this.web = readString(buf);
		//账号id
		this.userId = readString(buf);
		//角色id
		this.dataServerPlayerId = readLong(buf);
		//公共游戏服务器Ip
		this.serverIp = readString(buf);
		//公共游戏服务器port
		this.serverPort = readInt(buf);
		//公共游戏服务器sslport
		this.serverSSLPort = readInt(buf);
		return true;
	}
	
	/**
	 * get 公共游戏服务器Id
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 公共游戏服务器Id
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 服务器平台
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 服务器平台
	 */
	public void setWeb(String web){
		this.web = web;
	}
	
	/**
	 * get 账号id
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getDataServerPlayerId(){
		return dataServerPlayerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setDataServerPlayerId(long dataServerPlayerId){
		this.dataServerPlayerId = dataServerPlayerId;
	}
	
	/**
	 * get 公共游戏服务器Ip
	 * @return 
	 */
	public String getServerIp(){
		return serverIp;
	}
	
	/**
	 * set 公共游戏服务器Ip
	 */
	public void setServerIp(String serverIp){
		this.serverIp = serverIp;
	}
	
	/**
	 * get 公共游戏服务器port
	 * @return 
	 */
	public int getServerPort(){
		return serverPort;
	}
	
	/**
	 * set 公共游戏服务器port
	 */
	public void setServerPort(int serverPort){
		this.serverPort = serverPort;
	}
	
	/**
	 * get 公共游戏服务器sslport
	 * @return 
	 */
	public int getServerSSLPort(){
		return serverSSLPort;
	}
	
	/**
	 * set 公共游戏服务器sslport
	 */
	public void setServerSSLPort(int serverSSLPort){
		this.serverSSLPort = serverSSLPort;
	}
	
	
	@Override
	public int getId() {
		return 203101;
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
		//公共游戏服务器Id
		buf.append("serverId:" + serverId +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//账号id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//公共游戏服务器Ip
		if(this.serverIp!=null) buf.append("serverIp:" + serverIp.toString() +",");
		//公共游戏服务器port
		buf.append("serverPort:" + serverPort +",");
		//公共游戏服务器sslport
		buf.append("serverSSLPort:" + serverSSLPort +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}