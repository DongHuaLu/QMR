package com.game.equip.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 装备附加属性类
 */
public class EquipAttribute extends Bean {

	//附加属性类型
	private byte attributeType;
	
	//附加属性值
	private byte attributeValue;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//附加属性类型
		writeByte(buf, this.attributeType);
		//附加属性值
		writeByte(buf, this.attributeValue);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//附加属性类型
		this.attributeType = readByte(buf);
		//附加属性值
		this.attributeValue = readByte(buf);
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
	public byte getAttributeValue(){
		return attributeValue;
	}
	
	/**
	 * set 附加属性值
	 */
	public void setAttributeValue(byte attributeValue){
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