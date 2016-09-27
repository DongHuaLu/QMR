package com.game.log.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 日志信息消息
 */
public class LogInfoMessage extends Message{

	//日志信息
	private String info;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//日志信息
		writeString(buf, this.info);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//日志信息
		this.info = readString(buf);
		return true;
	}
	
	/**
	 * get 日志信息
	 * @return 
	 */
	public String getInfo(){
		return info;
	}
	
	/**
	 * set 日志信息
	 */
	public void setInfo(String info){
		this.info = info;
	}
	
	
	@Override
	public int getId() {
		return 201201;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return "World";
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//日志信息
		if(this.info!=null) buf.append("info:" + info.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}