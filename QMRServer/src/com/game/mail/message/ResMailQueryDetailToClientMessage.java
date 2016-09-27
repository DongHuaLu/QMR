package com.game.mail.message;

import com.game.mail.bean.MailDetailInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回读取邮件的结果消息
 */
public class ResMailQueryDetailToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//读取邮件的内容
	private MailDetailInfo mailDetail;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//读取邮件的内容
		writeBean(buf, this.mailDetail);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//读取邮件的内容
		this.mailDetail = (MailDetailInfo)readBean(buf, MailDetailInfo.class);
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
	 * get 读取邮件的内容
	 * @return 
	 */
	public MailDetailInfo getMailDetail(){
		return mailDetail;
	}
	
	/**
	 * set 读取邮件的内容
	 */
	public void setMailDetail(MailDetailInfo mailDetail){
		this.mailDetail = mailDetail;
	}
	
	
	@Override
	public int getId() {
		return 124102;
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
		//读取邮件的内容
		if(this.mailDetail!=null) buf.append("mailDetail:" + mailDetail.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}