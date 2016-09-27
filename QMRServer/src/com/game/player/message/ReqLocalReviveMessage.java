package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 原地复活消息
 */
public class ReqLocalReviveMessage extends Message{

	//使用复活道具modelid
	private int itemmodelid;
	
	//0自动使用玫瑰，1手动使用玫瑰复活
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//使用复活道具modelid
		writeInt(buf, this.itemmodelid);
		//0自动使用玫瑰，1手动使用玫瑰复活
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//使用复活道具modelid
		this.itemmodelid = readInt(buf);
		//0自动使用玫瑰，1手动使用玫瑰复活
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 使用复活道具modelid
	 * @return 
	 */
	public int getItemmodelid(){
		return itemmodelid;
	}
	
	/**
	 * set 使用复活道具modelid
	 */
	public void setItemmodelid(int itemmodelid){
		this.itemmodelid = itemmodelid;
	}
	
	/**
	 * get 0自动使用玫瑰，1手动使用玫瑰复活
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0自动使用玫瑰，1手动使用玫瑰复活
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 103202;
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
		//使用复活道具modelid
		buf.append("itemmodelid:" + itemmodelid +",");
		//0自动使用玫瑰，1手动使用玫瑰复活
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}