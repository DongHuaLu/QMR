package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器添加帮会成员消息
 */
public class ReqInnerGuildAddMemberToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//帮会Id
	private long guildId;
	
	//被操作玩家ID
	private long userId;
	
	//是否同意
	private byte argee;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//帮会Id
		writeLong(buf, this.guildId);
		//被操作玩家ID
		writeLong(buf, this.userId);
		//是否同意
		writeByte(buf, this.argee);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//帮会Id
		this.guildId = readLong(buf);
		//被操作玩家ID
		this.userId = readLong(buf);
		//是否同意
		this.argee = readByte(buf);
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
		return 121307;
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
		//帮会Id
		buf.append("guildId:" + guildId +",");
		//被操作玩家ID
		buf.append("userId:" + userId +",");
		//是否同意
		buf.append("argee:" + argee +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}