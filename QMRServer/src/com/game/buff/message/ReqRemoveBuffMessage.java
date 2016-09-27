package com.game.buff.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 移除Buff请求消息
 */
public class ReqRemoveBuffMessage extends Message{

	//buff Id
	private long buffId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//buff Id
		writeLong(buf, this.buffId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//buff Id
		this.buffId = readLong(buf);
		return true;
	}
	
	/**
	 * get buff Id
	 * @return 
	 */
	public long getBuffId(){
		return buffId;
	}
	
	/**
	 * set buff Id
	 */
	public void setBuffId(long buffId){
		this.buffId = buffId;
	}
	
	
	@Override
	public int getId() {
		return 113201;
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
		//buff Id
		buf.append("buffId:" + buffId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}