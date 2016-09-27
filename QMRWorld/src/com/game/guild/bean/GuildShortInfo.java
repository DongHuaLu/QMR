package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮会简略信息
 */
public class GuildShortInfo extends Bean {

	//帮会id
	private long guildId;
	
	//帮会名字
	private String guildName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会id
		writeLong(buf, this.guildId);
		//帮会名字
		writeString(buf, this.guildName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会id
		this.guildId = readLong(buf);
		//帮会名字
		this.guildName = readString(buf);
		return true;
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
	 * get 帮会名字
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 帮会名字
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//帮会名字
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}