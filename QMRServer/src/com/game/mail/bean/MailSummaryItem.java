package com.game.mail.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 邮件摘要物品
 */
public class MailSummaryItem extends Bean {

	//物品Id
	private int itemid;
	
	//物品数目
	private int itemnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeInt(buf, this.itemid);
		//物品数目
		writeInt(buf, this.itemnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		this.itemid = readInt(buf);
		//物品数目
		this.itemnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品Id
	 * @return 
	 */
	public int getItemid(){
		return itemid;
	}
	
	/**
	 * set 物品Id
	 */
	public void setItemid(int itemid){
		this.itemid = itemid;
	}
	
	/**
	 * get 物品数目
	 * @return 
	 */
	public int getItemnum(){
		return itemnum;
	}
	
	/**
	 * set 物品数目
	 */
	public void setItemnum(int itemnum){
		this.itemnum = itemnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品Id
		buf.append("itemid:" + itemid +",");
		//物品数目
		buf.append("itemnum:" + itemnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}