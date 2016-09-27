package com.game.scripts.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 面板文字信息
 */
public class PanelTxtInfo extends Bean {

	//控件ID名称
	private String name;
	
	//0整段文字替换，1只替换对应变量
	private byte type;
	
	//文字内容替换
	private String content;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//控件ID名称
		writeString(buf, this.name);
		//0整段文字替换，1只替换对应变量
		writeByte(buf, this.type);
		//文字内容替换
		writeString(buf, this.content);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//控件ID名称
		this.name = readString(buf);
		//0整段文字替换，1只替换对应变量
		this.type = readByte(buf);
		//文字内容替换
		this.content = readString(buf);
		return true;
	}
	
	/**
	 * get 控件ID名称
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 控件ID名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 0整段文字替换，1只替换对应变量
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0整段文字替换，1只替换对应变量
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 文字内容替换
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 文字内容替换
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//控件ID名称
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//0整段文字替换，1只替换对应变量
		buf.append("type:" + type +",");
		//文字内容替换
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}