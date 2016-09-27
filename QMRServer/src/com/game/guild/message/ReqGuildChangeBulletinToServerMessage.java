package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改公告消息
 */
public class ReqGuildChangeBulletinToServerMessage extends Message{

	//帮会Id
	private long guildId;
	
	//帮会公告
	private String guildBulletin;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会Id
		writeLong(buf, this.guildId);
		//帮会公告
		writeString(buf, this.guildBulletin);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会Id
		this.guildId = readLong(buf);
		//帮会公告
		this.guildBulletin = readString(buf);
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
	 * get 帮会公告
	 * @return 
	 */
	public String getGuildBulletin(){
		return guildBulletin;
	}
	
	/**
	 * set 帮会公告
	 */
	public void setGuildBulletin(String guildBulletin){
		this.guildBulletin = guildBulletin;
	}
	
	
	@Override
	public int getId() {
		return 121213;
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
		//帮会公告
		if(this.guildBulletin!=null) buf.append("guildBulletin:" + guildBulletin.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}