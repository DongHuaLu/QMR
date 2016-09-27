package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步单个国家王城帮会到所有服务器消息
 */
public class ResCountrySyncKingCityToGameMessage extends Message{

	//国家ID
	private int countryid;
	
	//王城帮会ID
	private long guildid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//国家ID
		writeInt(buf, this.countryid);
		//王城帮会ID
		writeLong(buf, this.guildid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//国家ID
		this.countryid = readInt(buf);
		//王城帮会ID
		this.guildid = readLong(buf);
		return true;
	}
	
	/**
	 * get 国家ID
	 * @return 
	 */
	public int getCountryid(){
		return countryid;
	}
	
	/**
	 * set 国家ID
	 */
	public void setCountryid(int countryid){
		this.countryid = countryid;
	}
	
	/**
	 * get 王城帮会ID
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 王城帮会ID
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	
	@Override
	public int getId() {
		return 146302;
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
		//国家ID
		buf.append("countryid:" + countryid +",");
		//王城帮会ID
		buf.append("guildid:" + guildid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}