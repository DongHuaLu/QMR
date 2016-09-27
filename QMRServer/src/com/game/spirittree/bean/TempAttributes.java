package com.game.spirittree.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 奖励道具附加属性
 */
public class TempAttributes extends Bean {

	//附加属性类型
	private byte attributeType;
	
	//附加属性值
	private int attributeValue;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//附加属性类型
		writeByte(buf, this.attributeType);
		//附加属性值
		writeInt(buf, this.attributeValue);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//附加属性类型
		this.attributeType = readByte(buf);
		//附加属性值
		this.attributeValue = readInt(buf);
		return true;
	}
	
	/**
	 * get 附加属性类型
	 * @return 
	 */
	public byte getAttributeType(){
		return attributeType;
	}
	
	/**
	 * set 附加属性类型
	 */
	public void setAttributeType(byte attributeType){
		this.attributeType = attributeType;
	}
	
	/**
	 * get 附加属性值
	 * @return 
	 */
	public int getAttributeValue(){
		return attributeValue;
	}
	
	/**
	 * set 附加属性值
	 */
	public void setAttributeValue(int attributeValue){
		this.attributeValue = attributeValue;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//附加属性类型
		buf.append("attributeType:" + attributeType +",");
		//附加属性值
		buf.append("attributeValue:" + attributeValue +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}