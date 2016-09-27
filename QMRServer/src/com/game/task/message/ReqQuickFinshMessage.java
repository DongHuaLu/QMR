package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 快速完成消息
 */
public class ReqQuickFinshMessage extends Message{

	//任务ID
	private int taskId;
	
	//0快速完成  1最优快速完成
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务ID
		writeInt(buf, this.taskId);
		//0快速完成  1最优快速完成
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务ID
		this.taskId = readInt(buf);
		//0快速完成  1最优快速完成
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 任务ID
	 * @return 
	 */
	public int getTaskId(){
		return taskId;
	}
	
	/**
	 * set 任务ID
	 */
	public void setTaskId(int taskId){
		this.taskId = taskId;
	}
	
	/**
	 * get 0快速完成  1最优快速完成
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0快速完成  1最优快速完成
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 120206;
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
		//任务ID
		buf.append("taskId:" + taskId +",");
		//0快速完成  1最优快速完成
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}