package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 完成任务消息
 */
public class ReqEpalaceTaskEndToGameMessage extends Message{

	//完成任务，0手动，1用元宝完成
	private byte type;
	
	//任务ID
	private int taskid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//完成任务，0手动，1用元宝完成
		writeByte(buf, this.type);
		//任务ID
		writeInt(buf, this.taskid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//完成任务，0手动，1用元宝完成
		this.type = readByte(buf);
		//任务ID
		this.taskid = readInt(buf);
		return true;
	}
	
	/**
	 * get 完成任务，0手动，1用元宝完成
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 完成任务，0手动，1用元宝完成
	 */
	public void setType(byte type){
		this.type = type;
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
		return 143203;
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
		//完成任务，0手动，1用元宝完成
		buf.append("type:" + type +",");
		//任务ID
		buf.append("taskid:" + taskid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}