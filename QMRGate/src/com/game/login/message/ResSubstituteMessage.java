package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 被顶替下线信息消息
 */
public class ResSubstituteMessage extends Message{

	//ip地址
	private String ip;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//ip地址
		writeString(buf, this.ip);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//ip地址
		this.ip = readString(buf);
		return true;
	}
	
	/**
	 * get ip地址
	 * @return 
	 */
	public String getIp(){
		return ip;
	}
	
	/**
	 * set ip地址
	 */
	public void setIp(String ip){
		this.ip = ip;
	}
	
	
	@Override
	public int getId() {
		return 100103;
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
		//ip地址
		if(this.ip!=null) buf.append("ip:" + ip.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}