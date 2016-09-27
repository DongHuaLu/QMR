package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家战斗状态改变消息
 */
public class ResPlayerStateChangeMessage extends Message{

	//玩家战斗状态 0-进入 1-退出
	private byte state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家战斗状态 0-进入 1-退出
		writeByte(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家战斗状态 0-进入 1-退出
		this.state = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家战斗状态 0-进入 1-退出
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 玩家战斗状态 0-进入 1-退出
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 103120;
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
		//玩家战斗状态 0-进入 1-退出
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}