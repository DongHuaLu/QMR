package com.game.equipstreng.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送错误消息消息
 */
public class ResErrorInfoToClientMessage extends Message{

	//错误类型
	private byte type;
	
	//错误int内容
	private int errint;
	
	//错误字符内容
	private String errstr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误类型
		writeByte(buf, this.type);
		//错误int内容
		writeInt(buf, this.errint);
		//错误字符内容
		writeString(buf, this.errstr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误类型
		this.type = readByte(buf);
		//错误int内容
		this.errint = readInt(buf);
		//错误字符内容
		this.errstr = readString(buf);
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
	 * get 错误int内容
	 * @return 
	 */
	public int getErrint(){
		return errint;
	}
	
	/**
	 * set 错误int内容
	 */
	public void setErrint(int errint){
		this.errint = errint;
	}
	
	/**
	 * get 错误字符内容
	 * @return 
	 */
	public String getErrstr(){
		return errstr;
	}
	
	/**
	 * set 错误字符内容
	 */
	public void setErrstr(String errstr){
		this.errstr = errstr;
	}
	
	
	@Override
	public int getId() {
		return 130103;
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
		//错误int内容
		buf.append("errint:" + errint +",");
		//错误字符内容
		if(this.errstr!=null) buf.append("errstr:" + errstr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}