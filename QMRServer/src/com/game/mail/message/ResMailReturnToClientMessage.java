package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 退信返回消息
 */
public class ResMailReturnToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//邮件Id
	private long mailid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//邮件Id
		writeLong(buf, this.mailid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//邮件Id
		this.mailid = readLong(buf);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getBtErrorCode(){
		return btErrorCode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setBtErrorCode(byte btErrorCode){
		this.btErrorCode = btErrorCode;
	}
	
	/**
	 * get 邮件Id
	 * @return 
	 */
	public long getMailid(){
		return mailid;
	}
	
	/**
	 * set 邮件Id
	 */
	public void setMailid(long mailid){
		this.mailid = mailid;
	}
	
	
	@Override
	public int getId() {
		return 124107;
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
		//错误代码
		buf.append("btErrorCode:" + btErrorCode +",");
		//邮件Id
		buf.append("mailid:" + mailid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}