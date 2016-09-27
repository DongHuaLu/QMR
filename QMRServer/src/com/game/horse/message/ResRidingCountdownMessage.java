package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送骑乘倒计时开始消息
 */
public class ResRidingCountdownMessage extends Message{

	//时间（秒）
	private byte time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//时间（秒）
		writeByte(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//时间（秒）
		this.time = readByte(buf);
		return true;
	}
	
	/**
	 * get 时间（秒）
	 * @return 
	 */
	public byte getTime(){
		return time;
	}
	
	/**
	 * set 时间（秒）
	 */
	public void setTime(byte time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 126102;
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
		//时间（秒）
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}