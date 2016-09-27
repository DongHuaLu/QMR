package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 讨伐任务吞噬消息
 */
public class ResConquerTaskAnnexMessage extends Message{

	//被吞噬的任务(要消失)
	private long vanishTaskId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//被吞噬的任务(要消失)
		writeLong(buf, this.vanishTaskId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//被吞噬的任务(要消失)
		this.vanishTaskId = readLong(buf);
		return true;
	}
	
	/**
	 * get 被吞噬的任务(要消失)
	 * @return 
	 */
	public long getVanishTaskId(){
		return vanishTaskId;
	}
	
	/**
	 * set 被吞噬的任务(要消失)
	 */
	public void setVanishTaskId(long vanishTaskId){
		this.vanishTaskId = vanishTaskId;
	}
	
	
	@Override
	public int getId() {
		return 120107;
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
		//被吞噬的任务(要消失)
		buf.append("vanishTaskId:" + vanishTaskId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}