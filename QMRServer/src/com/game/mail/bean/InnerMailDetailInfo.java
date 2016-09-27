package com.game.mail.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 内部邮件具体信息
 */
public class InnerMailDetailInfo extends Bean {

	//邮件Id
	private long mailid;
	
	//发送者Id
	private long senderid;
	
	//接受者Id
	private long receiverid;
	
	//发件人名字
	private String szSenderName;
	
	//接收人名字
	private String szReceiverName;
	
	//邮件标题
	private String szTitle;
	
	//邮件内容
	private String szNotice;
	
	//是否已读取
	private byte btRead;
	
	//金币类型
	private byte btGoldType;
	
	//金币数量
	private int nGold;
	
	//是否有附件
	private byte btAccessory;
	
	//发送时间
	private int nSendTime;
	
	//是否系统邮件
	private byte btSystem;
	
	//是否是回信
	private byte btReturn;
	
	//邮件物品列表(json字符串)
	private String itemdata;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//邮件Id
		writeLong(buf, this.mailid);
		//发送者Id
		writeLong(buf, this.senderid);
		//接受者Id
		writeLong(buf, this.receiverid);
		//发件人名字
		writeString(buf, this.szSenderName);
		//接收人名字
		writeString(buf, this.szReceiverName);
		//邮件标题
		writeString(buf, this.szTitle);
		//邮件内容
		writeString(buf, this.szNotice);
		//是否已读取
		writeByte(buf, this.btRead);
		//金币类型
		writeByte(buf, this.btGoldType);
		//金币数量
		writeInt(buf, this.nGold);
		//是否有附件
		writeByte(buf, this.btAccessory);
		//发送时间
		writeInt(buf, this.nSendTime);
		//是否系统邮件
		writeByte(buf, this.btSystem);
		//是否是回信
		writeByte(buf, this.btReturn);
		//邮件物品列表(json字符串)
		writeString(buf, this.itemdata);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//邮件Id
		this.mailid = readLong(buf);
		//发送者Id
		this.senderid = readLong(buf);
		//接受者Id
		this.receiverid = readLong(buf);
		//发件人名字
		this.szSenderName = readString(buf);
		//接收人名字
		this.szReceiverName = readString(buf);
		//邮件标题
		this.szTitle = readString(buf);
		//邮件内容
		this.szNotice = readString(buf);
		//是否已读取
		this.btRead = readByte(buf);
		//金币类型
		this.btGoldType = readByte(buf);
		//金币数量
		this.nGold = readInt(buf);
		//是否有附件
		this.btAccessory = readByte(buf);
		//发送时间
		this.nSendTime = readInt(buf);
		//是否系统邮件
		this.btSystem = readByte(buf);
		//是否是回信
		this.btReturn = readByte(buf);
		//邮件物品列表(json字符串)
		this.itemdata = readString(buf);
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
	 * get 发送者Id
	 * @return 
	 */
	public long getSenderid(){
		return senderid;
	}
	
	/**
	 * set 发送者Id
	 */
	public void setSenderid(long senderid){
		this.senderid = senderid;
	}
	
	/**
	 * get 接受者Id
	 * @return 
	 */
	public long getReceiverid(){
		return receiverid;
	}
	
	/**
	 * set 接受者Id
	 */
	public void setReceiverid(long receiverid){
		this.receiverid = receiverid;
	}
	
	/**
	 * get 发件人名字
	 * @return 
	 */
	public String getSzSenderName(){
		return szSenderName;
	}
	
	/**
	 * set 发件人名字
	 */
	public void setSzSenderName(String szSenderName){
		this.szSenderName = szSenderName;
	}
	
	/**
	 * get 接收人名字
	 * @return 
	 */
	public String getSzReceiverName(){
		return szReceiverName;
	}
	
	/**
	 * set 接收人名字
	 */
	public void setSzReceiverName(String szReceiverName){
		this.szReceiverName = szReceiverName;
	}
	
	/**
	 * get 邮件标题
	 * @return 
	 */
	public String getSzTitle(){
		return szTitle;
	}
	
	/**
	 * set 邮件标题
	 */
	public void setSzTitle(String szTitle){
		this.szTitle = szTitle;
	}
	
	/**
	 * get 邮件内容
	 * @return 
	 */
	public String getSzNotice(){
		return szNotice;
	}
	
	/**
	 * set 邮件内容
	 */
	public void setSzNotice(String szNotice){
		this.szNotice = szNotice;
	}
	
	/**
	 * get 是否已读取
	 * @return 
	 */
	public byte getBtRead(){
		return btRead;
	}
	
	/**
	 * set 是否已读取
	 */
	public void setBtRead(byte btRead){
		this.btRead = btRead;
	}
	
	/**
	 * get 金币类型
	 * @return 
	 */
	public byte getBtGoldType(){
		return btGoldType;
	}
	
	/**
	 * set 金币类型
	 */
	public void setBtGoldType(byte btGoldType){
		this.btGoldType = btGoldType;
	}
	
	/**
	 * get 金币数量
	 * @return 
	 */
	public int getNGold(){
		return nGold;
	}
	
	/**
	 * set 金币数量
	 */
	public void setNGold(int nGold){
		this.nGold = nGold;
	}
	
	/**
	 * get 是否有附件
	 * @return 
	 */
	public byte getBtAccessory(){
		return btAccessory;
	}
	
	/**
	 * set 是否有附件
	 */
	public void setBtAccessory(byte btAccessory){
		this.btAccessory = btAccessory;
	}
	
	/**
	 * get 发送时间
	 * @return 
	 */
	public int getNSendTime(){
		return nSendTime;
	}
	
	/**
	 * set 发送时间
	 */
	public void setNSendTime(int nSendTime){
		this.nSendTime = nSendTime;
	}
	
	/**
	 * get 是否系统邮件
	 * @return 
	 */
	public byte getBtSystem(){
		return btSystem;
	}
	
	/**
	 * set 是否系统邮件
	 */
	public void setBtSystem(byte btSystem){
		this.btSystem = btSystem;
	}
	
	/**
	 * get 是否是回信
	 * @return 
	 */
	public byte getBtReturn(){
		return btReturn;
	}
	
	/**
	 * set 是否是回信
	 */
	public void setBtReturn(byte btReturn){
		this.btReturn = btReturn;
	}
	
	/**
	 * get 邮件物品列表(json字符串)
	 * @return 
	 */
	public String getItemdata(){
		return itemdata;
	}
	
	/**
	 * set 邮件物品列表(json字符串)
	 */
	public void setItemdata(String itemdata){
		this.itemdata = itemdata;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//邮件Id
		buf.append("mailid:" + mailid +",");
		//发送者Id
		buf.append("senderid:" + senderid +",");
		//接受者Id
		buf.append("receiverid:" + receiverid +",");
		//发件人名字
		if(this.szSenderName!=null) buf.append("szSenderName:" + szSenderName.toString() +",");
		//接收人名字
		if(this.szReceiverName!=null) buf.append("szReceiverName:" + szReceiverName.toString() +",");
		//邮件标题
		if(this.szTitle!=null) buf.append("szTitle:" + szTitle.toString() +",");
		//邮件内容
		if(this.szNotice!=null) buf.append("szNotice:" + szNotice.toString() +",");
		//是否已读取
		buf.append("btRead:" + btRead +",");
		//金币类型
		buf.append("btGoldType:" + btGoldType +",");
		//金币数量
		buf.append("nGold:" + nGold +",");
		//是否有附件
		buf.append("btAccessory:" + btAccessory +",");
		//发送时间
		buf.append("nSendTime:" + nSendTime +",");
		//是否系统邮件
		buf.append("btSystem:" + btSystem +",");
		//是否是回信
		buf.append("btReturn:" + btReturn +",");
		//邮件物品列表(json字符串)
		if(this.itemdata!=null) buf.append("itemdata:" + itemdata.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}