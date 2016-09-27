package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 坐骑领取计时器消息
 */
public class ReshorseReceiveTimerMessage extends Message{

	//倒计时（秒）
	private int second;
	
	//总时间（秒）
	private int secondsum;
	
	//0不能领取，1可领取，2已经领取（关闭）
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//倒计时（秒）
		writeInt(buf, this.second);
		//总时间（秒）
		writeInt(buf, this.secondsum);
		//0不能领取，1可领取，2已经领取（关闭）
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//倒计时（秒）
		this.second = readInt(buf);
		//总时间（秒）
		this.secondsum = readInt(buf);
		//0不能领取，1可领取，2已经领取（关闭）
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 倒计时（秒）
	 * @return 
	 */
	public int getSecond(){
		return second;
	}
	
	/**
	 * set 倒计时（秒）
	 */
	public void setSecond(int second){
		this.second = second;
	}
	
	/**
	 * get 总时间（秒）
	 * @return 
	 */
	public int getSecondsum(){
		return secondsum;
	}
	
	/**
	 * set 总时间（秒）
	 */
	public void setSecondsum(int secondsum){
		this.secondsum = secondsum;
	}
	
	/**
	 * get 0不能领取，1可领取，2已经领取（关闭）
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0不能领取，1可领取，2已经领取（关闭）
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 126109;
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
		//倒计时（秒）
		buf.append("second:" + second +",");
		//总时间（秒）
		buf.append("secondsum:" + secondsum +",");
		//0不能领取，1可领取，2已经领取（关闭）
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}