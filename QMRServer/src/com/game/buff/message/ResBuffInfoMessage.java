package com.game.buff.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * Buff信息消息
 */
public class ResBuffInfoMessage extends Message{

	//buff Id
	private long buffId;
	
	//Buff 数值 主要用于血池
	private int value;
	
	//Buff 剩余时间
	private int remain;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//buff Id
		writeLong(buf, this.buffId);
		//Buff 数值 主要用于血池
		writeInt(buf, this.value);
		//Buff 剩余时间
		writeInt(buf, this.remain);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//buff Id
		this.buffId = readLong(buf);
		//Buff 数值 主要用于血池
		this.value = readInt(buf);
		//Buff 剩余时间
		this.remain = readInt(buf);
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
	
	/**
	 * get Buff 数值 主要用于血池
	 * @return 
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * set Buff 数值 主要用于血池
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * get Buff 剩余时间
	 * @return 
	 */
	public int getRemain(){
		return remain;
	}
	
	/**
	 * set Buff 剩余时间
	 */
	public void setRemain(int remain){
		this.remain = remain;
	}
	
	
	@Override
	public int getId() {
		return 113104;
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
		//Buff 数值 主要用于血池
		buf.append("value:" + value +",");
		//Buff 剩余时间
		buf.append("remain:" + remain +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}