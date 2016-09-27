package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物停止移动消息
 */
public class ResPetStopMessage extends Message{

	//宠物Id
	private long petId;
	
	//修正坐标
	private com.game.structs.Position position;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//修正坐标
		writeBean(buf, this.position);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//修正坐标
		this.position = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
		return true;
	}
	
	/**
	 * get 宠物Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 修正坐标
	 * @return 
	 */
	public com.game.structs.Position getPosition(){
		return position;
	}
	
	/**
	 * set 修正坐标
	 */
	public void setPosition(com.game.structs.Position position){
		this.position = position;
	}
	
	
	@Override
	public int getId() {
		return 101121;
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
		//宠物Id
		buf.append("petId:" + petId +",");
		//修正坐标
		if(this.position!=null) buf.append("position:" + position.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}