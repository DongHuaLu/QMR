package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器王城事件通知消息
 */
public class ReqInnerKingCityEventToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//事件类型
	private int eventtype;
	
	//帮会事件内容
	private String guildevent;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//事件类型
		writeInt(buf, this.eventtype);
		//帮会事件内容
		writeString(buf, this.guildevent);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//事件类型
		this.eventtype = readInt(buf);
		//帮会事件内容
		this.guildevent = readString(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 事件类型
	 * @return 
	 */
	public int getEventtype(){
		return eventtype;
	}
	
	/**
	 * set 事件类型
	 */
	public void setEventtype(int eventtype){
		this.eventtype = eventtype;
	}
	
	/**
	 * get 帮会事件内容
	 * @return 
	 */
	public String getGuildevent(){
		return guildevent;
	}
	
	/**
	 * set 帮会事件内容
	 */
	public void setGuildevent(String guildevent){
		this.guildevent = guildevent;
	}
	
	
	@Override
	public int getId() {
		return 121323;
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
		//角色Id
		buf.append("playerId:" + playerId +",");
		//事件类型
		buf.append("eventtype:" + eventtype +",");
		//帮会事件内容
		if(this.guildevent!=null) buf.append("guildevent:" + guildevent.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}