package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 最大内力变化消息
 */
public class ResMonsterMaxMpChangeMessage extends Message{

	//角色Id
	private long monsterId;
	
	//当前MP
	private int currentMp;
	
	//最大MP
	private int maxMp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.monsterId);
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
		this.monsterId = readLong(buf);
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
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
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
		return 114105;
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
		buf.append("monsterId:" + monsterId +",");
		//当前MP
		buf.append("currentMp:" + currentMp +",");
		//最大MP
		buf.append("maxMp:" + maxMp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}