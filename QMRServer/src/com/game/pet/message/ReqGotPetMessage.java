package com.game.pet.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物钦点消息
 */
public class ReqGotPetMessage extends Message{

	//宠物ID
	private int modelId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物ID
		writeInt(buf, this.modelId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物ID
		this.modelId = readInt(buf);
		return true;
	}
	
	/**
	 * get 宠物ID
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 宠物ID
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	
	@Override
	public int getId() {
		return 110207;
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
		//宠物ID
		buf.append("modelId:" + modelId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}