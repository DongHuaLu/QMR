package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回请求公共服务器玩家错误信息（公共数据服务器到游戏服务器）消息
 */
public class ResSyncPlayerInfoFromDataServerErrorMessage extends Message{

	//角色id
	private long dataServerPlayerId;
	
	//账号
	private String userId;
	
	//服务器平台
	private String web;
	
	//跨服服务器id
	private int serverId;
	
	//跨服网关id
	private int gateId;
	
	//错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
	private int error;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//账号
		writeString(buf, this.userId);
		//服务器平台
		writeString(buf, this.web);
		//跨服服务器id
		writeInt(buf, this.serverId);
		//跨服网关id
		writeInt(buf, this.gateId);
		//错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
		writeInt(buf, this.error);
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
		//服务器平台
		this.web = readString(buf);
		//跨服服务器id
		this.serverId = readInt(buf);
		//跨服网关id
		this.gateId = readInt(buf);
		//错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
		this.error = readInt(buf);
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
	 * get 错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
	 * @return 
	 */
	public int getError(){
		return error;
	}
	
	/**
	 * set 错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
	 */
	public void setError(int error){
		this.error = error;
	}
	
	
	@Override
	public int getId() {
		return 203305;
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
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//跨服服务器id
		buf.append("serverId:" + serverId +",");
		//跨服网关id
		buf.append("gateId:" + gateId +",");
		//错误类型 1-角色不存在 2-账号不存在 3-已经在跨服中
		buf.append("error:" + error +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}