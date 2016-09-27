package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回请求公共服务器玩家结束跨服信息（公共数据服务器到公共游戏服务器）消息
 */
public class ResPlayerQuitToDataServerMessage extends Message{

	//跨服服务器id
	private int serverId;
	
	//角色id
	private long dataServerPlayerId;
	
	//状态类型
	private int state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跨服服务器id
		writeInt(buf, this.serverId);
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//状态类型
		writeInt(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//跨服服务器id
		this.serverId = readInt(buf);
		//角色id
		this.dataServerPlayerId = readLong(buf);
		//状态类型
		this.state = readInt(buf);
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
	 * get 状态类型
	 * @return 
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * set 状态类型
	 */
	public void setState(int state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 203309;
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
		//角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//状态类型
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}