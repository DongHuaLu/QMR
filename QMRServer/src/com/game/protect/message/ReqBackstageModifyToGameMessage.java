package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 后台修改2级密码或邮件消息
 */
public class ReqBackstageModifyToGameMessage extends Message{

	//用户id
	private String userid;
	
	//密码
	private String password;
	
	//邮箱
	private String mail;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//用户id
		writeString(buf, this.userid);
		//密码
		writeString(buf, this.password);
		//邮箱
		writeString(buf, this.mail);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//用户id
		this.userid = readString(buf);
		//密码
		this.password = readString(buf);
		//邮箱
		this.mail = readString(buf);
		return true;
	}
	
	/**
	 * get 用户id
	 * @return 
	 */
	public String getUserid(){
		return userid;
	}
	
	/**
	 * set 用户id
	 */
	public void setUserid(String userid){
		this.userid = userid;
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
	
	
	@Override
	public int getId() {
		return 164301;
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
		//用户id
		if(this.userid!=null) buf.append("userid:" + userid.toString() +",");
		//密码
		if(this.password!=null) buf.append("password:" + password.toString() +",");
		//邮箱
		if(this.mail!=null) buf.append("mail:" + mail.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}