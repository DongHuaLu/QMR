package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 婚宴时间消息
 */
public class ResWeddingTimeToClientMessage extends Message{

	//婚宴类型，1普通，2大型，3豪华
	private byte type;
	
	//月
	private byte month;
	
	//日
	private byte day;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//婚宴类型，1普通，2大型，3豪华
		writeByte(buf, this.type);
		//月
		writeByte(buf, this.month);
		//日
		writeByte(buf, this.day);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//婚宴类型，1普通，2大型，3豪华
		this.type = readByte(buf);
		//月
		this.month = readByte(buf);
		//日
		this.day = readByte(buf);
		return true;
	}
	
	/**
	 * get 婚宴类型，1普通，2大型，3豪华
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 婚宴类型，1普通，2大型，3豪华
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 月
	 * @return 
	 */
	public byte getMonth(){
		return month;
	}
	
	/**
	 * set 月
	 */
	public void setMonth(byte month){
		this.month = month;
	}
	
	/**
	 * get 日
	 * @return 
	 */
	public byte getDay(){
		return day;
	}
	
	/**
	 * set 日
	 */
	public void setDay(byte day){
		this.day = day;
	}
	
	
	@Override
	public int getId() {
		return 163122;
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
		//婚宴类型，1普通，2大型，3豪华
		buf.append("type:" + type +",");
		//月
		buf.append("month:" + month +",");
		//日
		buf.append("day:" + day +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}