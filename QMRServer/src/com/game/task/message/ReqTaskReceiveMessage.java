package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 领取任务消息
 */
public class ReqTaskReceiveMessage extends Message{

	//任务类型 主线 日常 （讨伐使用卷轴完成领用）
	private byte type;
	
	//任务模型
	private int modelId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型 主线 日常 （讨伐使用卷轴完成领用）
		writeByte(buf, this.type);
		//任务模型
		writeInt(buf, this.modelId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型 主线 日常 （讨伐使用卷轴完成领用）
		this.type = readByte(buf);
		//任务模型
		this.modelId = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务类型 主线 日常 （讨伐使用卷轴完成领用）
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 任务类型 主线 日常 （讨伐使用卷轴完成领用）
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 任务模型
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 任务模型
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	
	@Override
	public int getId() {
		return 120201;
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
		//任务类型 主线 日常 （讨伐使用卷轴完成领用）
		buf.append("type:" + type +",");
		//任务模型
		buf.append("modelId:" + modelId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}