package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始比赛消息
 */
public class ResStartMatchMessage extends Message{

	//服务器Id
	private int serverId;
	
	//服务器平台
	private String web;
	
	//账号id
	private String userId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器Id
		writeInt(buf, this.serverId);
		//服务器平台
		writeString(buf, this.web);
		//账号id
		writeString(buf, this.userId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器Id
		this.serverId = readInt(buf);
		//服务器平台
		this.web = readString(buf);
		//账号id
		this.userId = readString(buf);
		return true;
	}
	
	/**
	 * get 服务器Id
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器Id
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
	
	
	@Override
	public int getId() {
		return 203102;
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
		//服务器Id
		buf.append("serverId:" + serverId +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//账号id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}