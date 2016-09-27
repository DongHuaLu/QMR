package com.game.buff.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * buff参数
 */
public class BuffPara extends Bean {

	//Buff 参数类型
	private byte type;
	
	//Buff 参数值
	private int value;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//Buff 参数类型
		writeByte(buf, this.type);
		//Buff 参数值
		writeInt(buf, this.value);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//Buff 参数类型
		this.type = readByte(buf);
		//Buff 参数值
		this.value = readInt(buf);
		return true;
	}
	
	/**
	 * get Buff 参数类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set Buff 参数类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get Buff 参数值
	 * @return 
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * set Buff 参数值
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//Buff 参数类型
		buf.append("type:" + type +",");
		//Buff 参数值
		buf.append("value:" + value +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}