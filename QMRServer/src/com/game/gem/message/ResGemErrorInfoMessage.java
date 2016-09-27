package com.game.gem.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 错误消息消息
 */
public class ResGemErrorInfoMessage extends Message{

	//错误类型
	private byte type;
	
	//数值
	private int intnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误类型
		writeByte(buf, this.type);
		//数值
		writeInt(buf, this.intnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误类型
		this.type = readByte(buf);
		//数值
		this.intnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 错误类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 错误类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 数值
	 * @return 
	 */
	public int getIntnum(){
		return intnum;
	}
	
	/**
	 * set 数值
	 */
	public void setIntnum(int intnum){
		this.intnum = intnum;
	}
	
	
	@Override
	public int getId() {
		return 132105;
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
		//错误类型
		buf.append("type:" + type +",");
		//数值
		buf.append("intnum:" + intnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}