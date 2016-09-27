package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 申请婚宴消息
 */
public class ReqApplyWeddingToGameMessage extends Message{

	//婚宴类型，1普通，2大型，3豪华
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//婚宴类型，1普通，2大型，3豪华
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//婚宴类型，1普通，2大型，3豪华
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 婚宴类型，1普通，2大型，3豪华
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 婚宴类型，1普通，2大型，3豪华
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 163207;
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
		//婚宴类型，1普通，2大型，3豪华
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}