package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任务完成消息
 */
public class ResTaskFinshMessage extends Message{

	//任务类型 主线 日常 讨伐
	private byte type;
	
	//任务模型
	private int modelId;
	
	//吞噬任务ID
	private long conquerTadkId;
	
	//日常任务完成类型   0普通完成 1一键完成 
	private int finshType;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型 主线 日常 讨伐
		writeByte(buf, this.type);
		//任务模型
		writeInt(buf, this.modelId);
		//吞噬任务ID
		writeLong(buf, this.conquerTadkId);
		//日常任务完成类型   0普通完成 1一键完成 
		writeInt(buf, this.finshType);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型 主线 日常 讨伐
		this.type = readByte(buf);
		//任务模型
		this.modelId = readInt(buf);
		//吞噬任务ID
		this.conquerTadkId = readLong(buf);
		//日常任务完成类型   0普通完成 1一键完成 
		this.finshType = readInt(buf);
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
	
	/**
	 * get 吞噬任务ID
	 * @return 
	 */
	public long getConquerTadkId(){
		return conquerTadkId;
	}
	
	/**
	 * set 吞噬任务ID
	 */
	public void setConquerTadkId(long conquerTadkId){
		this.conquerTadkId = conquerTadkId;
	}
	
	/**
	 * get 日常任务完成类型   0普通完成 1一键完成 
	 * @return 
	 */
	public int getFinshType(){
		return finshType;
	}
	
	/**
	 * set 日常任务完成类型   0普通完成 1一键完成 
	 */
	public void setFinshType(int finshType){
		this.finshType = finshType;
	}
	
	
	@Override
	public int getId() {
		return 120102;
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
		//任务模型
		buf.append("modelId:" + modelId +",");
		//吞噬任务ID
		buf.append("conquerTadkId:" + conquerTadkId +",");
		//日常任务完成类型   0普通完成 1一键完成 
		buf.append("finshType:" + finshType +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}