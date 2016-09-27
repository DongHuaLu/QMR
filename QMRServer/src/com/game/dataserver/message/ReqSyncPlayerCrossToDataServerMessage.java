package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步公共服务器玩家跨服部分信息消息
 */
public class ReqSyncPlayerCrossToDataServerMessage extends Message{

	//跨服服务器id
	private int serverId;
	
	//角色id
	private long dataServerPlayerId;
	
	//跨服背包数据
	private String bag;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跨服服务器id
		writeInt(buf, this.serverId);
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//跨服背包数据
		writeString(buf, this.bag);
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
		//跨服背包数据
		this.bag = readString(buf);
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
	 * get 跨服背包数据
	 * @return 
	 */
	public String getBag(){
		return bag;
	}
	
	/**
	 * set 跨服背包数据
	 */
	public void setBag(String bag){
		this.bag = bag;
	}
	
	
	@Override
	public int getId() {
		return 203310;
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
		//跨服背包数据
		if(this.bag!=null) buf.append("bag:" + bag.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}