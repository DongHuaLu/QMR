package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除外交关系消息
 */
public class ReqGuildDeleteDiplomaticToServerMessage extends Message{

	//帮会Id
	private long guildId;
	
	//外交关系类型
	private byte diplomaticType;
	
	//被操作帮会Id
	private long otherGuildId;
	
	//是否同意
	private byte argee;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会Id
		writeLong(buf, this.guildId);
		//外交关系类型
		writeByte(buf, this.diplomaticType);
		//被操作帮会Id
		writeLong(buf, this.otherGuildId);
		//是否同意
		writeByte(buf, this.argee);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会Id
		this.guildId = readLong(buf);
		//外交关系类型
		this.diplomaticType = readByte(buf);
		//被操作帮会Id
		this.otherGuildId = readLong(buf);
		//是否同意
		this.argee = readByte(buf);
		return true;
	}
	
	/**
	 * get 帮会Id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会Id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 外交关系类型
	 * @return 
	 */
	public byte getDiplomaticType(){
		return diplomaticType;
	}
	
	/**
	 * set 外交关系类型
	 */
	public void setDiplomaticType(byte diplomaticType){
		this.diplomaticType = diplomaticType;
	}
	
	/**
	 * get 被操作帮会Id
	 * @return 
	 */
	public long getOtherGuildId(){
		return otherGuildId;
	}
	
	/**
	 * set 被操作帮会Id
	 */
	public void setOtherGuildId(long otherGuildId){
		this.otherGuildId = otherGuildId;
	}
	
	/**
	 * get 是否同意
	 * @return 
	 */
	public byte getArgee(){
		return argee;
	}
	
	/**
	 * set 是否同意
	 */
	public void setArgee(byte argee){
		this.argee = argee;
	}
	
	
	@Override
	public int getId() {
		return 121219;
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
		//帮会Id
		buf.append("guildId:" + guildId +",");
		//外交关系类型
		buf.append("diplomaticType:" + diplomaticType +",");
		//被操作帮会Id
		buf.append("otherGuildId:" + otherGuildId +",");
		//是否同意
		buf.append("argee:" + argee +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}