package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 降低难度消息
 */
public class ReqTaskDownHardMessage extends Message{

	//任务ID
	private int taskId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务ID
		writeInt(buf, this.taskId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务ID
		this.taskId = readInt(buf);
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
	
	
	@Override
	public int getId() {
		return 120204;
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}