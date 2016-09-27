package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家防沉迷在线时间消息
 */
public class ResPlayerNonageTimeMessage extends Message{

	//玩家防沉迷在线时间
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家防沉迷在线时间
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家防沉迷在线时间
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家防沉迷在线时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 玩家防沉迷在线时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 103122;
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
		//玩家防沉迷在线时间
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}