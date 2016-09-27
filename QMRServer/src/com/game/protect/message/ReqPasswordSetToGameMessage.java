package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 新设置密码消息
 */
public class ReqPasswordSetToGameMessage extends Message{

	//密码
	private String password;
	
	//邮箱
	private String mail;
	
	//验证码
	private String verification;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//密码
		writeString(buf, this.password);
		//邮箱
		writeString(buf, this.mail);
		//验证码
		writeString(buf, this.verification);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//密码
		this.password = readString(buf);
		//邮箱
		this.mail = readString(buf);
		//验证码
		this.verification = readString(buf);
		return true;
	}
	
	/**
	 * get 密码
	 * @return 
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * set 密码
	 */
	public void setPassword(String password){
		this.password = password;
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
	
	/**
	 * get 验证码
	 * @return 
	 */
	public String getVerification(){
		return verification;
	}
	
	/**
	 * set 验证码
	 */
	public void setVerification(String verification){
		this.verification = verification;
	}
	
	
	@Override
	public int getId() {
		return 164202;
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
		//密码
		if(this.password!=null) buf.append("password:" + password.toString() +",");
		//邮箱
		if(this.mail!=null) buf.append("mail:" + mail.toString() +",");
		//验证码
		if(this.verification!=null) buf.append("verification:" + verification.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}