package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 事件信息
 */
public class EventInfo extends Bean {

	//事件id
	private long eventId;
	
	//帮会id
	private long guildId;
	
	//帮会名
	private String guildName;
	
	//消息时间
	private int messageTime;
	
	//消息类型
	private String messageType;
	
	//消息内容
	private String message;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//事件id
		writeLong(buf, this.eventId);
		//帮会id
		writeLong(buf, this.guildId);
		//帮会名
		writeString(buf, this.guildName);
		//消息时间
		writeInt(buf, this.messageTime);
		//消息类型
		writeString(buf, this.messageType);
		//消息内容
		writeString(buf, this.message);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//事件id
		this.eventId = readLong(buf);
		//帮会id
		this.guildId = readLong(buf);
		//帮会名
		this.guildName = readString(buf);
		//消息时间
		this.messageTime = readInt(buf);
		//消息类型
		this.messageType = readString(buf);
		//消息内容
		this.message = readString(buf);
		return true;
	}
	
	/**
	 * get 事件id
	 * @return 
	 */
	public long getEventId(){
		return eventId;
	}
	
	/**
	 * set 事件id
	 */
	public void setEventId(long eventId){
		this.eventId = eventId;
	}
	
	/**
	 * get 帮会id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 帮会名
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 帮会名
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	/**
	 * get 消息时间
	 * @return 
	 */
	public int getMessageTime(){
		return messageTime;
	}
	
	/**
	 * set 消息时间
	 */
	public void setMessageTime(int messageTime){
		this.messageTime = messageTime;
	}
	
	/**
	 * get 消息类型
	 * @return 
	 */
	public String getMessageType(){
		return messageType;
	}
	
	/**
	 * set 消息类型
	 */
	public void setMessageType(String messageType){
		this.messageType = messageType;
	}
	
	/**
	 * get 消息内容
	 * @return 
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * set 消息内容
	 */
	public void setMessage(String message){
		this.message = message;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//事件id
		buf.append("eventId:" + eventId +",");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//帮会名
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//消息时间
		buf.append("messageTime:" + messageTime +",");
		//消息类型
		if(this.messageType!=null) buf.append("messageType:" + messageType.toString() +",");
		//消息内容
		if(this.message!=null) buf.append("message:" + message.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}