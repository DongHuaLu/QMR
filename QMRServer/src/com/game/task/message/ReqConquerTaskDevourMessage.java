package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 讨伐任务吞噬请求消息
 */
public class ReqConquerTaskDevourMessage extends Message{

	//选择的任务ID
	private long devourId;
	
	//是否保留100%比例
	private byte isfull;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//选择的任务ID
		writeLong(buf, this.devourId);
		//是否保留100%比例
		writeByte(buf, this.isfull);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//选择的任务ID
		this.devourId = readLong(buf);
		//是否保留100%比例
		this.isfull = readByte(buf);
		return true;
	}
	
	/**
	 * get 选择的任务ID
	 * @return 
	 */
	public long getDevourId(){
		return devourId;
	}
	
	/**
	 * set 选择的任务ID
	 */
	public void setDevourId(long devourId){
		this.devourId = devourId;
	}
	
	/**
	 * get 是否保留100%比例
	 * @return 
	 */
	public byte getIsfull(){
		return isfull;
	}
	
	/**
	 * set 是否保留100%比例
	 */
	public void setIsfull(byte isfull){
		this.isfull = isfull;
	}
	
	
	@Override
	public int getId() {
		return 120208;
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
		//选择的任务ID
		buf.append("devourId:" + devourId +",");
		//是否保留100%比例
		buf.append("isfull:" + isfull +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}