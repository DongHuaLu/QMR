package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 真气变化消息
 */
public class ResPlayerZhenqiChangeMessage extends Message{

	//当前真气
	private int currentZhenqi;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前真气
		writeInt(buf, this.currentZhenqi);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前真气
		this.currentZhenqi = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前真气
	 * @return 
	 */
	public int getCurrentZhenqi(){
		return currentZhenqi;
	}
	
	/**
	 * set 当前真气
	 */
	public void setCurrentZhenqi(int currentZhenqi){
		this.currentZhenqi = currentZhenqi;
	}
	
	
	@Override
	public int getId() {
		return 103113;
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
		//当前真气
		buf.append("currentZhenqi:" + currentZhenqi +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}