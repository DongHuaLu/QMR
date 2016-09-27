package com.game.server.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 游戏服务器请求注册公共服务器消息
 */
public class ReqRegisterGameForPublicMessage extends Message{

	//服务器编号
	private int serverId;
	
	//服务器名字
	private String serverName;
	
	//平台名字
	private String webName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器编号
		writeInt(buf, this.serverId);
		//服务器名字
		writeString(buf, this.serverName);
		//平台名字
		writeString(buf, this.webName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器编号
		this.serverId = readInt(buf);
		//服务器名字
		this.serverName = readString(buf);
		//平台名字
		this.webName = readString(buf);
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
	 * get 服务器名字
	 * @return 
	 */
	public String getServerName(){
		return serverName;
	}
	
	/**
	 * set 服务器名字
	 */
	public void setServerName(String serverName){
		this.serverName = serverName;
	}
	
	/**
	 * get 平台名字
	 * @return 
	 */
	public String getWebName(){
		return webName;
	}
	
	/**
	 * set 平台名字
	 */
	public void setWebName(String webName){
		this.webName = webName;
	}
	
	
	@Override
	public int getId() {
		return 300311;
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
		//服务器名字
		if(this.serverName!=null) buf.append("serverName:" + serverName.toString() +",");
		//平台名字
		if(this.webName!=null) buf.append("webName:" + webName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}