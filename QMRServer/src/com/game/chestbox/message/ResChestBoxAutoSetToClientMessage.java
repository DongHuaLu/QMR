package com.game.chestbox.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱盒子自动开始设置发送到客户端消息
 */
public class ResChestBoxAutoSetToClientMessage extends Message{

	//自动开始设置
	private int autoset;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//自动开始设置
		writeInt(buf, this.autoset);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//自动开始设置
		this.autoset = readInt(buf);
		return true;
	}
	
	/**
	 * get 自动开始设置
	 * @return 
	 */
	public int getAutoset(){
		return autoset;
	}
	
	/**
	 * set 自动开始设置
	 */
	public void setAutoset(int autoset){
		this.autoset = autoset;
	}
	
	
	@Override
	public int getId() {
		return 156104;
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
		//自动开始设置
		buf.append("autoset:" + autoset +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}