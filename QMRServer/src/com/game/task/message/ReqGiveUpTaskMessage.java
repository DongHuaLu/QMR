package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 放弃任务消息
 */
public class ReqGiveUpTaskMessage extends Message{

	//任务类型 0主线 1讨伐 2日常 3寻宝
	private int type;
	
	//选择的任务ID
	private long taskid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型 0主线 1讨伐 2日常 3寻宝
		writeInt(buf, this.type);
		//选择的任务ID
		writeLong(buf, this.taskid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型 0主线 1讨伐 2日常 3寻宝
		this.type = readInt(buf);
		//选择的任务ID
		this.taskid = readLong(buf);
		return true;
	}
	
	/**
	 * get 任务类型 0主线 1讨伐 2日常 3寻宝
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 任务类型 0主线 1讨伐 2日常 3寻宝
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 选择的任务ID
	 * @return 
	 */
	public long getTaskid(){
		return taskid;
	}
	
	/**
	 * set 选择的任务ID
	 */
	public void setTaskid(long taskid){
		this.taskid = taskid;
	}
	
	
	@Override
	public int getId() {
		return 120209;
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
		//任务类型 0主线 1讨伐 2日常 3寻宝
		buf.append("type:" + type +",");
		//选择的任务ID
		buf.append("taskid:" + taskid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}