package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 魔法变化消息
 */
public class ResMonsterMpChangeMessage extends Message{

	//角色Id
	private long monsterId;
	
	//当前MP
	private int currentMp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.monsterId);
		//当前MP
		writeInt(buf, this.currentMp);
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
	
	
	@Override
	public int getId() {
		return 114102;
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}