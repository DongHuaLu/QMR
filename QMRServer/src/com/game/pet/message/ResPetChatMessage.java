package com.game.pet.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 美人物说话消息
 */
public class ResPetChatMessage extends Message{

	//美人id
	private long petId;
	
	//说话内容
	private String saycontent;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//美人id
		writeLong(buf, this.petId);
		//说话内容
		writeString(buf, this.saycontent);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//美人id
		this.petId = readLong(buf);
		//说话内容
		this.saycontent = readString(buf);
		return true;
	}
	
	/**
	 * get 美人id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 美人id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 说话内容
	 * @return 
	 */
	public String getSaycontent(){
		return saycontent;
	}
	
	/**
	 * set 说话内容
	 */
	public void setSaycontent(String saycontent){
		this.saycontent = saycontent;
	}
	
	
	@Override
	public int getId() {
		return 110113;
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
		//美人id
		buf.append("petId:" + petId +",");
		//说话内容
		if(this.saycontent!=null) buf.append("saycontent:" + saycontent.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}