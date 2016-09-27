package com.game.pet.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物死亡广播消息
 */
public class ResPetDieBroadcastMessage extends Message{

	//死亡宠物ID
	private long petId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//死亡宠物ID
		writeLong(buf, this.petId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//死亡宠物ID
		this.petId = readLong(buf);
		return true;
	}
	
	/**
	 * get 死亡宠物ID
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 死亡宠物ID
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	
	@Override
	public int getId() {
		return 110111;
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
		//死亡宠物ID
		buf.append("petId:" + petId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}