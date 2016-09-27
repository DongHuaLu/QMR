package com.game.player.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 个人属性项
 */
public class PlayerAttributeItem extends Bean {

	//属性类型
	private int type;
	
	//属性值
	private int value;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//属性类型
		writeInt(buf, this.type);
		//属性值
		writeInt(buf, this.value);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//属性类型
		this.type = readInt(buf);
		//属性值
		this.value = readInt(buf);
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
	public int getValue(){
		return value;
	}
	
	/**
	 * set 属性值
	 */
	public void setValue(int value){
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