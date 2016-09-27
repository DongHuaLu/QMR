package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 放弃任务消息
 */
public class ReqEpalaceAbandonTaskToGameMessage extends Message{

	//任务ID
	private int taskid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务ID
		writeInt(buf, this.taskid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务ID
		this.taskid = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务ID
	 * @return 
	 */
	public int getTaskid(){
		return taskid;
	}
	
	/**
	 * set 任务ID
	 */
	public void setTaskid(int taskid){
		this.taskid = taskid;
	}
	
	
	@Override
	public int getId() {
		return 143204;
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
		buf.append("taskid:" + taskid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}