package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回请求公共服务器玩家全部信息消息
 */
public class ResSyncPlayerInfoFromDataServerMessage extends Message{

	//角色id
	private long dataServerPlayerId;
	
	//账号
	private String userId;
	
	//服务器编号
	private int serverId;
	
	//跨服网关id
	private int gateId;
	
	//服务器平台
	private String web;
	
	//数据
	private String data;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//账号
		writeString(buf, this.userId);
		//服务器编号
		writeInt(buf, this.serverId);
		//跨服网关id
		writeInt(buf, this.gateId);
		//服务器平台
		writeString(buf, this.web);
		//数据
		writeString(buf, this.data);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.dataServerPlayerId = readLong(buf);
		//账号
		this.userId = readString(buf);
		//服务器编号
		this.serverId = readInt(buf);
		//跨服网关id
		this.gateId = readInt(buf);
		//服务器平台
		this.web = readString(buf);
		//数据
		this.data = readString(buf);
		return true;
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
	 * get 数据
	 * @return 
	 */
	public String getData(){
		return data;
	}
	
	/**
	 * set 数据
	 */
	public void setData(String data){
		this.data = data;
	}
	
	
	@Override
	public int getId() {
		return 203304;
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
		//角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//跨服网关id
		buf.append("gateId:" + gateId +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//数据
		if(this.data!=null) buf.append("data:" + data.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}