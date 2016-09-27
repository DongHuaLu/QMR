package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 心跳消息消息
 */
public class ResHeartMessage extends Message{

	//当前时间
	private int time;
	
	//服务器启动时间
	private int time2;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前时间
		writeInt(buf, this.time);
		//服务器启动时间
		writeInt(buf, this.time2);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前时间
		this.time = readInt(buf);
		//服务器启动时间
		this.time2 = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 当前时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 服务器启动时间
	 * @return 
	 */
	public int getTime2(){
		return time2;
	}
	
	/**
	 * set 服务器启动时间
	 */
	public void setTime2(int time2){
		this.time2 = time2;
	}
	
	
	@Override
	public int getId() {
		return 100108;
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
		//当前时间
		buf.append("time:" + time +",");
		//服务器启动时间
		buf.append("time2:" + time2 +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}