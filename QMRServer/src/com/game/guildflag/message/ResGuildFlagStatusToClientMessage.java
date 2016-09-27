package com.game.guildflag.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送领地争夺战是否开启消息
 */
public class ResGuildFlagStatusToClientMessage extends Message{

	//时间（秒）大于0表示进行中
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//时间（秒）大于0表示进行中
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//时间（秒）大于0表示进行中
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 时间（秒）大于0表示进行中
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 时间（秒）大于0表示进行中
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 149102;
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
		//时间（秒）大于0表示进行中
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}