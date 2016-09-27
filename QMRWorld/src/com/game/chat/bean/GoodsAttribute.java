package com.game.chat.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 附加项
 */
public class GoodsAttribute extends Bean {

	//属性类型
	private int type;
	
	//属性值
	private long value;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//属性类型
		writeInt(buf, this.type);
		//属性值
		writeLong(buf, this.value);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//属性类型
		this.type = readInt(buf);
		//属性值
		this.value = readLong(buf);
		return true;
	}
	
	/**
	 * get 属性类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 属性类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 属性值
	 * @return 
	 */
	public long getValue(){
		return value;
	}
	
	/**
	 * set 属性值
	 */
	public void setValue(long value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//属性类型
		buf.append("type:" + type +",");
		//属性值
		buf.append("value:" + value +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}