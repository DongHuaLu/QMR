package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 攻城战状态消息
 */
public class ResCountrySiegeWarStateToClientMessage extends Message{

	//0没有攻城战，1攻城战进行中
	private byte state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0没有攻城战，1攻城战进行中
		writeByte(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0没有攻城战，1攻城战进行中
		this.state = readByte(buf);
		return true;
	}
	
	/**
	 * get 0没有攻城战，1攻城战进行中
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 0没有攻城战，1攻城战进行中
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 146107;
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
		//0没有攻城战，1攻城战进行中
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}