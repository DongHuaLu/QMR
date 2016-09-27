package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求公共服务器玩家全部信息（公共服务器到公共数据服务器）消息
 */
public class ReqSyncPlayerInfoFromDataServerMessage extends Message{

	//跨服服务器id
	private int serverId;
	
	//跨服服务器平台
	private String serverWeb;
	
	//跨服网关id
	private int gateId;
	
	//账号
	private String userId;
	
	//服务器平台
	private String web;
	
	//角色id
	private long dataServerPlayerId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跨服服务器id
		writeInt(buf, this.serverId);
		//跨服服务器平台
		writeString(buf, this.serverWeb);
		//跨服网关id
		writeInt(buf, this.gateId);
		//账号
		writeString(buf, this.userId);
		//服务器平台
		writeString(buf, this.web);
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//跨服服务器id
		this.serverId = readInt(buf);
		//跨服服务器平台
		this.serverWeb = readString(buf);
		//跨服网关id
		this.gateId = readInt(buf);
		//账号
		this.userId = readString(buf);
		//服务器平台
		this.web = readString(buf);
		//角色id
		this.dataServerPlayerId = readLong(buf);
		return true;
	}
	
	/**
	 * get 跨服服务器id
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 跨服服务器id
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 跨服服务器平台
	 * @return 
	 */
	public String getServerWeb(){
		return serverWeb;
	}
	
	/**
	 * set 跨服服务器平台
	 */
	public void setServerWeb(String serverWeb){
		this.serverWeb = serverWeb;
	}
	
	/**
	 * get 跨服网关id
	 * @return 
	 */
	public int getGateId(){
		return gateId;
	}
	
	/**
	 * set 跨服网关id
	 */
	public void setGateId(int gateId){
		this.gateId = gateId;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
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
	
	
	@Override
	public int getId() {
		return 203303;
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
		//跨服服务器id
		buf.append("serverId:" + serverId +",");
		//跨服服务器平台
		if(this.serverWeb!=null) buf.append("serverWeb:" + serverWeb.toString() +",");
		//跨服网关id
		buf.append("gateId:" + gateId +",");
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}