package com.game.pet.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 美人目标消息
 */
public class ResPetTargetMessage extends Message{

	//目标id
	private long targetId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//目标id
		writeLong(buf, this.targetId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//目标id
		this.targetId = readLong(buf);
		return true;
	}
	
	/**
	 * get 目标id
	 * @return 
	 */
	public long getTargetId(){
		return targetId;
	}
	
	/**
	 * set 目标id
	 */
	public void setTargetId(long targetId){
		this.targetId = targetId;
	}
	
	
	@Override
	public int getId() {
		return 110114;
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
		//目标id
		buf.append("targetId:" + targetId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}