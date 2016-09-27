package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发起离婚消息
 */
public class ReqDivorceToGameMessage extends Message{

	//1协议离婚，2强行离婚
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//1协议离婚，2强行离婚
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//1协议离婚，2强行离婚
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 1协议离婚，2强行离婚
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 1协议离婚，2强行离婚
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 163206;
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
		//1协议离婚，2强行离婚
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}