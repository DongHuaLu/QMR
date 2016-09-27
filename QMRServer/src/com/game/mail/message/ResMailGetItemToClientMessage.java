package com.game.mail.message;

import com.game.mail.bean.MailDetailInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 收取附件返回消息
 */
public class ResMailGetItemToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	private long itemid;
	
	//读取邮件的内容
	private MailDetailInfo mailDetail;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		writeLong(buf, this.itemid);
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
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		this.itemid = readLong(buf);
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
	 * get 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
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
		return 124103;
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
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		buf.append("itemid:" + itemid +",");
		//读取邮件的内容
		if(this.mailDetail!=null) buf.append("mailDetail:" + mailDetail.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}