package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改职权消息
 */
public class ReqGuildChangePowerLevelToServerMessage extends Message{

	//帮会Id
	private long guildId;
	
	//被操作玩家ID
	private long userId;
	
	//新职权
	private byte newPowerLevel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会Id
		writeLong(buf, this.guildId);
		//被操作玩家ID
		writeLong(buf, this.userId);
		//新职权
		writeByte(buf, this.newPowerLevel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会Id
		this.guildId = readLong(buf);
		//被操作玩家ID
		this.userId = readLong(buf);
		//新职权
		this.newPowerLevel = readByte(buf);
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
	 * get 被操作玩家ID
	 * @return 
	 */
	public long getUserId(){
		return userId;
	}
	
	/**
	 * set 被操作玩家ID
	 */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * get 新职权
	 * @return 
	 */
	public byte getNewPowerLevel(){
		return newPowerLevel;
	}
	
	/**
	 * set 新职权
	 */
	public void setNewPowerLevel(byte newPowerLevel){
		this.newPowerLevel = newPowerLevel;
	}
	
	
	@Override
	public int getId() {
		return 121210;
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
		//被操作玩家ID
		buf.append("userId:" + userId +",");
		//新职权
		buf.append("newPowerLevel:" + newPowerLevel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}