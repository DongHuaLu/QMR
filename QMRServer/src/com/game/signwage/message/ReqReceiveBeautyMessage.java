package com.game.signwage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求领取美人消息
 */
public class ReqReceiveBeautyMessage extends Message{

	//领取的美人类型，1到4
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//领取的美人类型，1到4
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//领取的美人类型，1到4
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 领取的美人类型，1到4
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 领取的美人类型，1到4
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 152207;
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
		//领取的美人类型，1到4
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}