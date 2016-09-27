package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 交付任务消息
 */
public class ReqTaskFinshMessage extends Message{

	//任务类型 主线 日常 讨伐
	private byte type;
	
	//讨伐任务ID
	private long conquererTaskId;
	
	//任务模型
	private int taskId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型 主线 日常 讨伐
		writeByte(buf, this.type);
		//讨伐任务ID
		writeLong(buf, this.conquererTaskId);
		//任务模型
		writeInt(buf, this.taskId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型 主线 日常 讨伐
		this.type = readByte(buf);
		//讨伐任务ID
		this.conquererTaskId = readLong(buf);
		//任务模型
		this.taskId = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务类型 主线 日常 讨伐
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 任务类型 主线 日常 讨伐
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 讨伐任务ID
	 * @return 
	 */
	public long getConquererTaskId(){
		return conquererTaskId;
	}
	
	/**
	 * set 讨伐任务ID
	 */
	public void setConquererTaskId(long conquererTaskId){
		this.conquererTaskId = conquererTaskId;
	}
	
	/**
	 * get 任务模型
	 * @return 
	 */
	public int getTaskId(){
		return taskId;
	}
	
	/**
	 * set 任务模型
	 */
	public void setTaskId(int taskId){
		this.taskId = taskId;
	}
	
	
	@Override
	public int getId() {
		return 120202;
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
		//任务类型 主线 日常 讨伐
		buf.append("type:" + type +",");
		//讨伐任务ID
		buf.append("conquererTaskId:" + conquererTaskId +",");
		//任务模型
		buf.append("taskId:" + taskId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}