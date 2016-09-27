package com.game.mail.message;

import com.game.mail.bean.MailSummaryInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得邮件列表返回消息
 */
public class ResMailQueryListToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//邮件列表
	private List<MailSummaryInfo> mailSummaryList = new ArrayList<MailSummaryInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//邮件列表
		writeShort(buf, mailSummaryList.size());
		for (int i = 0; i < mailSummaryList.size(); i++) {
			writeBean(buf, mailSummaryList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//邮件列表
		int mailSummaryList_length = readShort(buf);
		for (int i = 0; i < mailSummaryList_length; i++) {
			mailSummaryList.add((MailSummaryInfo)readBean(buf, MailSummaryInfo.class));
		}
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
	 * get 邮件列表
	 * @return 
	 */
	public List<MailSummaryInfo> getMailSummaryList(){
		return mailSummaryList;
	}
	
	/**
	 * set 邮件列表
	 */
	public void setMailSummaryList(List<MailSummaryInfo> mailSummaryList){
		this.mailSummaryList = mailSummaryList;
	}
	
	
	@Override
	public int getId() {
		return 124101;
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
		//邮件列表
		buf.append("mailSummaryList:{");
		for (int i = 0; i < mailSummaryList.size(); i++) {
			buf.append(mailSummaryList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}