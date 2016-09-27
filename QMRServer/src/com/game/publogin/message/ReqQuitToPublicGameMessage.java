package com.game.publogin.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 退出游戏消息
 */
public class ReqQuitToPublicGameMessage extends Message{

	//是否强制退出
	private byte force;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//是否强制退出
		writeByte(buf, this.force);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//是否强制退出
		this.force = readByte(buf);
		return true;
	}
	
	/**
	 * get 是否强制退出
	 * @return 
	 */
	public byte getForce(){
		return force;
	}
	
	/**
	 * set 是否强制退出
	 */
	public void setForce(byte force){
		this.force = force;
	}
	
	
	@Override
	public int getId() {
		return 204304;
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
		//是否强制退出
		buf.append("force:" + force +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}