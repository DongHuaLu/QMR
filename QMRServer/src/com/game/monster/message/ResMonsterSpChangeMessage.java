package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 体力变化消息
 */
public class ResMonsterSpChangeMessage extends Message{

	//角色Id
	private long monsterId;
	
	//当前SP
	private int currentSp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.monsterId);
		//当前SP
		writeInt(buf, this.currentSp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.monsterId = readLong(buf);
		//当前SP
		this.currentSp = readInt(buf);
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
	 * get 当前SP
	 * @return 
	 */
	public int getCurrentSp(){
		return currentSp;
	}
	
	/**
	 * set 当前SP
	 */
	public void setCurrentSp(int currentSp){
		this.currentSp = currentSp;
	}
	
	
	@Override
	public int getId() {
		return 114103;
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
		//当前SP
		buf.append("currentSp:" + currentSp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}