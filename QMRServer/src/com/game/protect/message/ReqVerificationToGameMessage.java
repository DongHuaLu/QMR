package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求服务器，获得验证码消息
 */
public class ReqVerificationToGameMessage extends Message{

	//邮箱
	private String mail;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//邮箱
		writeString(buf, this.mail);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//邮箱
		this.mail = readString(buf);
		return true;
	}
	
	/**
	 * get 邮箱
	 * @return 
	 */
	public String getMail(){
		return mail;
	}
	
	/**
	 * set 邮箱
	 */
	public void setMail(String mail){
		this.mail = mail;
	}
	
	
	@Override
	public int getId() {
		return 164203;
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
		//邮箱
		if(this.mail!=null) buf.append("mail:" + mail.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}