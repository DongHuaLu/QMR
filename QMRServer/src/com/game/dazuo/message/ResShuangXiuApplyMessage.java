package com.game.dazuo.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 双修请求消息
 */
public class ResShuangXiuApplyMessage extends Message{

	//玩家ID
	private long role;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.role);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.role = readLong(buf);
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getRole(){
		return role;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setRole(long role){
		this.role = role;
	}
	
	
	@Override
	public int getId() {
		return 136104;
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
		//玩家ID
		buf.append("role:" + role +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}