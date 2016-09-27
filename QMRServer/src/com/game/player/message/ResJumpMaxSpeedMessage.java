package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 跳跃最大速度消息
 */
public class ResJumpMaxSpeedMessage extends Message{

	//跳跃最大速度
	private int jumpmaxspeed;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跳跃最大速度
		writeInt(buf, this.jumpmaxspeed);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//跳跃最大速度
		this.jumpmaxspeed = readInt(buf);
		return true;
	}
	
	/**
	 * get 跳跃最大速度
	 * @return 
	 */
	public int getJumpmaxspeed(){
		return jumpmaxspeed;
	}
	
	/**
	 * set 跳跃最大速度
	 */
	public void setJumpmaxspeed(int jumpmaxspeed){
		this.jumpmaxspeed = jumpmaxspeed;
	}
	
	
	@Override
	public int getId() {
		return 103135;
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
		//跳跃最大速度
		buf.append("jumpmaxspeed:" + jumpmaxspeed +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}