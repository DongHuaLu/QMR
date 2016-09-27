package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器帮主修改自动同意申请加入设置消息
 */
public class ReqInnerGuildAutoGuildArgeeAddToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//帮会Id
	private long guildId;
	
	//自动同意加入帮会的申请
	private byte autoGuildAgreeAdd;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//帮会Id
		writeLong(buf, this.guildId);
		//自动同意加入帮会的申请
		writeByte(buf, this.autoGuildAgreeAdd);
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
		//自动同意加入帮会的申请
		this.autoGuildAgreeAdd = readByte(buf);
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
	 * get 自动同意加入帮会的申请
	 * @return 
	 */
	public byte getAutoGuildAgreeAdd(){
		return autoGuildAgreeAdd;
	}
	
	/**
	 * set 自动同意加入帮会的申请
	 */
	public void setAutoGuildAgreeAdd(byte autoGuildAgreeAdd){
		this.autoGuildAgreeAdd = autoGuildAgreeAdd;
	}
	
	
	@Override
	public int getId() {
		return 121312;
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
		//自动同意加入帮会的申请
		buf.append("autoGuildAgreeAdd:" + autoGuildAgreeAdd +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}