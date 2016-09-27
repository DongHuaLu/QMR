package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 外交关系信息
 */
public class DiplomaticInfo extends Bean {

	//帮会id
	private long guildId;
	
	//外交建立时间
	private int diplomaticTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会id
		writeLong(buf, this.guildId);
		//外交建立时间
		writeInt(buf, this.diplomaticTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会id
		this.guildId = readLong(buf);
		//外交建立时间
		this.diplomaticTime = readInt(buf);
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
	 * get 外交建立时间
	 * @return 
	 */
	public int getDiplomaticTime(){
		return diplomaticTime;
	}
	
	/**
	 * set 外交建立时间
	 */
	public void setDiplomaticTime(int diplomaticTime){
		this.diplomaticTime = diplomaticTime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//外交建立时间
		buf.append("diplomaticTime:" + diplomaticTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}