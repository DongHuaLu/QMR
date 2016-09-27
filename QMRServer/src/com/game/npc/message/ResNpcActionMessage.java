package com.game.npc.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * npc行为消息
 */
public class ResNpcActionMessage extends Message{

	//npcId
	private long npcId;
	
	//行为目标
	private long tatget;
	
	//行为类型
	private int actionType;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//npcId
		writeLong(buf, this.npcId);
		//行为目标
		writeLong(buf, this.tatget);
		//行为类型
		writeInt(buf, this.actionType);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//npcId
		this.npcId = readLong(buf);
		//行为目标
		this.tatget = readLong(buf);
		//行为类型
		this.actionType = readInt(buf);
		return true;
	}
	
	/**
	 * get npcId
	 * @return 
	 */
	public long getNpcId(){
		return npcId;
	}
	
	/**
	 * set npcId
	 */
	public void setNpcId(long npcId){
		this.npcId = npcId;
	}
	
	/**
	 * get 行为目标
	 * @return 
	 */
	public long getTatget(){
		return tatget;
	}
	
	/**
	 * set 行为目标
	 */
	public void setTatget(long tatget){
		this.tatget = tatget;
	}
	
	/**
	 * get 行为类型
	 * @return 
	 */
	public int getActionType(){
		return actionType;
	}
	
	/**
	 * set 行为类型
	 */
	public void setActionType(int actionType){
		this.actionType = actionType;
	}
	
	
	@Override
	public int getId() {
		return 140104;
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
		//npcId
		buf.append("npcId:" + npcId +",");
		//行为目标
		buf.append("tatget:" + tatget +",");
		//行为类型
		buf.append("actionType:" + actionType +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}