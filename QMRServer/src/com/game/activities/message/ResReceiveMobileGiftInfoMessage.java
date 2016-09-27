package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 手机版本更新礼包领取信息消息
 */
public class ResReceiveMobileGiftInfoMessage extends Message{

	//已经领取的最大索引
	private int index;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//已经领取的最大索引
		writeInt(buf, this.index);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//已经领取的最大索引
		this.index = readInt(buf);
		return true;
	}
	
	/**
	 * get 已经领取的最大索引
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 已经领取的最大索引
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	
	@Override
	public int getId() {
		return 138104;
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
		//已经领取的最大索引
		buf.append("index:" + index +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}