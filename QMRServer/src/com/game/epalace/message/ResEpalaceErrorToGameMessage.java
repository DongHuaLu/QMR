package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 错误提示消息消息
 */
public class ResEpalaceErrorToGameMessage extends Message{

	//错误消息文字提示
	private String str;
	
	//错误消息数字提示
	private int num;
	
	//错误消息类型
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误消息文字提示
		writeString(buf, this.str);
		//错误消息数字提示
		writeInt(buf, this.num);
		//错误消息类型
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误消息文字提示
		this.str = readString(buf);
		//错误消息数字提示
		this.num = readInt(buf);
		//错误消息类型
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 错误消息文字提示
	 * @return 
	 */
	public String getStr(){
		return str;
	}
	
	/**
	 * set 错误消息文字提示
	 */
	public void setStr(String str){
		this.str = str;
	}
	
	/**
	 * get 错误消息数字提示
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 错误消息数字提示
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 错误消息类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 错误消息类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 143104;
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
		//错误消息文字提示
		if(this.str!=null) buf.append("str:" + str.toString() +",");
		//错误消息数字提示
		buf.append("num:" + num +",");
		//错误消息类型
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}