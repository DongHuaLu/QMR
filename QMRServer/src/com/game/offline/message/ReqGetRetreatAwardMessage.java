package com.game.offline.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求闭关奖励消息
 */
public class ReqGetRetreatAwardMessage extends Message{

	//领取类型，0单倍 1双倍 2自动双倍
	private int getType;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//领取类型，0单倍 1双倍 2自动双倍
		writeInt(buf, this.getType);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//领取类型，0单倍 1双倍 2自动双倍
		this.getType = readInt(buf);
		return true;
	}
	
	/**
	 * get 领取类型，0单倍 1双倍 2自动双倍
	 * @return 
	 */
	public int getGetType(){
		return getType;
	}
	
	/**
	 * set 领取类型，0单倍 1双倍 2自动双倍
	 */
	public void setGetType(int getType){
		this.getType = getType;
	}
	
	
	@Override
	public int getId() {
		return 150202;
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
		//领取类型，0单倍 1双倍 2自动双倍
		buf.append("getType:" + getType +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}