package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家防沉迷状态改变消息
 */
public class ResPlayerNonageStateMessage extends Message{

	//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	private byte nonage;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		writeByte(buf, this.nonage);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		this.nonage = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	 * @return 
	 */
	public byte getNonage(){
		return nonage;
	}
	
	/**
	 * set 玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
	 */
	public void setNonage(byte nonage){
		this.nonage = nonage;
	}
	
	
	@Override
	public int getId() {
		return 103121;
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
		//玩家防沉迷状态 0-非防沉迷 1-正常 2-疲劳 3-不健康
		buf.append("nonage:" + nonage +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}