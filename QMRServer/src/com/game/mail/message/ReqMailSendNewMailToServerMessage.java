package com.game.mail.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送新邮件消息
 */
public class ReqMailSendNewMailToServerMessage extends Message{

	//接收人名字
	private String szReceiverName;
	
	//邮件标题
	private String szTitle;
	
	//邮件内容
	private String szNotice;
	
	//金币类型
	private byte btGoldType;
	
	//金币数量
	private int nGold;
	
	//邮件附件物品id列表
	private List<Long> itemidlist = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//接收人名字
		writeString(buf, this.szReceiverName);
		//邮件标题
		writeString(buf, this.szTitle);
		//邮件内容
		writeString(buf, this.szNotice);
		//金币类型
		writeByte(buf, this.btGoldType);
		//金币数量
		writeInt(buf, this.nGold);
		//邮件附件物品id列表
		writeShort(buf, itemidlist.size());
		for (int i = 0; i < itemidlist.size(); i++) {
			writeLong(buf, itemidlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//接收人名字
		this.szReceiverName = readString(buf);
		//邮件标题
		this.szTitle = readString(buf);
		//邮件内容
		this.szNotice = readString(buf);
		//金币类型
		this.btGoldType = readByte(buf);
		//金币数量
		this.nGold = readInt(buf);
		//邮件附件物品id列表
		int itemidlist_length = readShort(buf);
		for (int i = 0; i < itemidlist_length; i++) {
			itemidlist.add(readLong(buf));
		}
		return true;
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
	 * get 邮件附件物品id列表
	 * @return 
	 */
	public List<Long> getItemidlist(){
		return itemidlist;
	}
	
	/**
	 * set 邮件附件物品id列表
	 */
	public void setItemidlist(List<Long> itemidlist){
		this.itemidlist = itemidlist;
	}
	
	
	@Override
	public int getId() {
		return 124154;
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
		//接收人名字
		if(this.szReceiverName!=null) buf.append("szReceiverName:" + szReceiverName.toString() +",");
		//邮件标题
		if(this.szTitle!=null) buf.append("szTitle:" + szTitle.toString() +",");
		//邮件内容
		if(this.szNotice!=null) buf.append("szNotice:" + szNotice.toString() +",");
		//金币类型
		buf.append("btGoldType:" + btGoldType +",");
		//金币数量
		buf.append("nGold:" + nGold +",");
		//邮件附件物品id列表
		buf.append("itemidlist:{");
		for (int i = 0; i < itemidlist.size(); i++) {
			buf.append(itemidlist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}