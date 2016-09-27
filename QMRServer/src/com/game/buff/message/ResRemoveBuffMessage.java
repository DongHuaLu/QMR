package com.game.buff.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 移除Buff消息
 */
public class ResRemoveBuffMessage extends Message{

	//角色Id
	private long personId;
	
	//战斗状态
	private int fightState;
	
	//buff Id
	private long buffId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//战斗状态
		writeInt(buf, this.fightState);
		//buff Id
		writeLong(buf, this.buffId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//战斗状态
		this.fightState = readInt(buf);
		//buff Id
		this.buffId = readLong(buf);
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
	 * get 战斗状态
	 * @return 
	 */
	public int getFightState(){
		return fightState;
	}
	
	/**
	 * set 战斗状态
	 */
	public void setFightState(int fightState){
		this.fightState = fightState;
	}
	
	/**
	 * get buff Id
	 * @return 
	 */
	public long getBuffId(){
		return buffId;
	}
	
	/**
	 * set buff Id
	 */
	public void setBuffId(long buffId){
		this.buffId = buffId;
	}
	
	
	@Override
	public int getId() {
		return 113103;
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
		//战斗状态
		buf.append("fightState:" + fightState +",");
		//buff Id
		buf.append("buffId:" + buffId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}