package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家选择是否参与攻城消息
 */
public class ResCountrySiegeSelectToGameMessage extends Message{

	//是否参与，1参与攻城
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//是否参与，1参与攻城
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//是否参与，1参与攻城
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 是否参与，1参与攻城
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 是否参与，1参与攻城
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 146201;
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
		//是否参与，1参与攻城
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}