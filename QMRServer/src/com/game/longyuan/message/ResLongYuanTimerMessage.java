package com.game.longyuan.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 计时器开始消息
 */
public class ResLongYuanTimerMessage extends Message{

	//是否播放倒计时特效,1开始播放，0不处理
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//是否播放倒计时特效,1开始播放，0不处理
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//是否播放倒计时特效,1开始播放，0不处理
		this.status = readByte(buf);
		return true;
	}
	
	/**
	 * get 是否播放倒计时特效,1开始播放，0不处理
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 是否播放倒计时特效,1开始播放，0不处理
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 115105;
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
		//是否播放倒计时特效,1开始播放，0不处理
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}