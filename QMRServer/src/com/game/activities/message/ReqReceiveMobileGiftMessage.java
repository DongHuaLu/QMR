package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 手机版本更新礼包领取消息
 */
public class ReqReceiveMobileGiftMessage extends Message{

	//请求领取指定索引的奖励
	private int index;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//请求领取指定索引的奖励
		writeInt(buf, this.index);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//请求领取指定索引的奖励
		this.index = readInt(buf);
		return true;
	}
	
	/**
	 * get 请求领取指定索引的奖励
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 请求领取指定索引的奖励
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	
	@Override
	public int getId() {
		return 138204;
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
		//请求领取指定索引的奖励
		buf.append("index:" + index +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}