package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送系统新邮件消息
 */
public class ResMailSendSystemMailToServerMessage extends Message{

	//邮件json
	private String mailJsonstr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//邮件json
		writeString(buf, this.mailJsonstr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//邮件json
		this.mailJsonstr = readString(buf);
		return true;
	}
	
	/**
	 * get 邮件json
	 * @return 
	 */
	public String getMailJsonstr(){
		return mailJsonstr;
	}
	
	/**
	 * set 邮件json
	 */
	public void setMailJsonstr(String mailJsonstr){
		this.mailJsonstr = mailJsonstr;
	}
	
	
	@Override
	public int getId() {
		return 124201;
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
		//邮件json
		if(this.mailJsonstr!=null) buf.append("mailJsonstr:" + mailJsonstr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}