package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 最大生命变化消息
 */
public class ResMonsterMaxHpChangeMessage extends Message{

	//角色Id
	private long monsterId;
	
	//当前HP
	private int currentHp;
	
	//最大HP
	private int maxHp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.monsterId);
		//当前HP
		writeInt(buf, this.currentHp);
		//最大HP
		writeInt(buf, this.maxHp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.monsterId = readLong(buf);
		//当前HP
		this.currentHp = readInt(buf);
		//最大HP
		this.maxHp = readInt(buf);
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
	 * get 当前HP
	 * @return 
	 */
	public int getCurrentHp(){
		return currentHp;
	}
	
	/**
	 * set 当前HP
	 */
	public void setCurrentHp(int currentHp){
		this.currentHp = currentHp;
	}
	
	/**
	 * get 最大HP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 最大HP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	
	@Override
	public int getId() {
		return 114104;
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
		//当前HP
		buf.append("currentHp:" + currentHp +",");
		//最大HP
		buf.append("maxHp:" + maxHp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}