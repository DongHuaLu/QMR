package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 收取附件消息
 */
public class ReqMailGetItemToServerMessage extends Message{

	//邮件Id
	private long mailid;
	
	//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
	private long itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//邮件Id
		writeLong(buf, this.mailid);
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		writeLong(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//邮件Id
		this.mailid = readLong(buf);
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		this.itemid = readLong(buf);
		return true;
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
	
	
	@Override
	public int getId() {
		return 124153;
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
		//邮件Id
		buf.append("mailid:" + mailid +",");
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		buf.append("itemid:" + itemid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}