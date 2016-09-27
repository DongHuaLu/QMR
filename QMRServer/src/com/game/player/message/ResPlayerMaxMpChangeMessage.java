package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 最大内力变化消息
 */
public class ResPlayerMaxMpChangeMessage extends Message{

	//角色Id
	private long personId;
	
	//当前MP
	private int currentMp;
	
	//最大MP
	private int maxMp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//当前MP
		writeInt(buf, this.currentMp);
		//最大MP
		writeInt(buf, this.maxMp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//当前MP
		this.currentMp = readInt(buf);
		//最大MP
		this.maxMp = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 当前MP
	 * @return 
	 */
	public int getCurrentMp(){
		return currentMp;
	}
	
	/**
	 * set 当前MP
	 */
	public void setCurrentMp(int currentMp){
		this.currentMp = currentMp;
	}
	
	/**
	 * get 最大MP
	 * @return 
	 */
	public int getMaxMp(){
		return maxMp;
	}
	
	/**
	 * set 最大MP
	 */
	public void setMaxMp(int maxMp){
		this.maxMp = maxMp;
	}
	
	
	@Override
	public int getId() {
		return 103110;
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
		buf.append("personId:" + personId +",");
		//当前MP
		buf.append("currentMp:" + currentMp +",");
		//最大MP
		buf.append("maxMp:" + maxMp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}