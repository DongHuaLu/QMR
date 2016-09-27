package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 经验变化消息
 */
public class ResPlayerExpChangeMessage extends Message{

	//当前经验
	private long currentExp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前经验
		writeLong(buf, this.currentExp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前经验
		this.currentExp = readLong(buf);
		return true;
	}
	
	/**
	 * get 当前经验
	 * @return 
	 */
	public long getCurrentExp(){
		return currentExp;
	}
	
	/**
	 * set 当前经验
	 */
	public void setCurrentExp(long currentExp){
		this.currentExp = currentExp;
	}
	
	
	@Override
	public int getId() {
		return 103103;
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
		//当前经验
		buf.append("currentExp:" + currentExp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}