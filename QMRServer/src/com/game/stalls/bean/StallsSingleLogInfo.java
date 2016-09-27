package com.game.stalls.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位单个交易日志
 */
public class StallsSingleLogInfo extends Bean {

	//交易时间（秒）
	private int transactiontime;
	
	//交易类型，0出售，1购买
	private byte transactiontype;
	
	//交易者名字
	private String tradersname;
	
	//交易者ID
	private long tradersid;
	
	//金币价格
	private int pricegold;
	
	//元宝价格
	private int priceyuanbao;
	
	//道具信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//交易时间（秒）
		writeInt(buf, this.transactiontime);
		//交易类型，0出售，1购买
		writeByte(buf, this.transactiontype);
		//交易者名字
		writeString(buf, this.tradersname);
		//交易者ID
		writeLong(buf, this.tradersid);
		//金币价格
		writeInt(buf, this.pricegold);
		//元宝价格
		writeInt(buf, this.priceyuanbao);
		//道具信息
		writeBean(buf, this.iteminfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//交易时间（秒）
		this.transactiontime = readInt(buf);
		//交易类型，0出售，1购买
		this.transactiontype = readByte(buf);
		//交易者名字
		this.tradersname = readString(buf);
		//交易者ID
		this.tradersid = readLong(buf);
		//金币价格
		this.pricegold = readInt(buf);
		//元宝价格
		this.priceyuanbao = readInt(buf);
		//道具信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		return true;
	}
	
	/**
	 * get 交易时间（秒）
	 * @return 
	 */
	public int getTransactiontime(){
		return transactiontime;
	}
	
	/**
	 * set 交易时间（秒）
	 */
	public void setTransactiontime(int transactiontime){
		this.transactiontime = transactiontime;
	}
	
	/**
	 * get 交易类型，0出售，1购买
	 * @return 
	 */
	public byte getTransactiontype(){
		return transactiontype;
	}
	
	/**
	 * set 交易类型，0出售，1购买
	 */
	public void setTransactiontype(byte transactiontype){
		this.transactiontype = transactiontype;
	}
	
	/**
	 * get 交易者名字
	 * @return 
	 */
	public String getTradersname(){
		return tradersname;
	}
	
	/**
	 * set 交易者名字
	 */
	public void setTradersname(String tradersname){
		this.tradersname = tradersname;
	}
	
	/**
	 * get 交易者ID
	 * @return 
	 */
	public long getTradersid(){
		return tradersid;
	}
	
	/**
	 * set 交易者ID
	 */
	public void setTradersid(long tradersid){
		this.tradersid = tradersid;
	}
	
	/**
	 * get 金币价格
	 * @return 
	 */
	public int getPricegold(){
		return pricegold;
	}
	
	/**
	 * set 金币价格
	 */
	public void setPricegold(int pricegold){
		this.pricegold = pricegold;
	}
	
	/**
	 * get 元宝价格
	 * @return 
	 */
	public int getPriceyuanbao(){
		return priceyuanbao;
	}
	
	/**
	 * set 元宝价格
	 */
	public void setPriceyuanbao(int priceyuanbao){
		this.priceyuanbao = priceyuanbao;
	}
	
	/**
	 * get 道具信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 道具信息
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//交易时间（秒）
		buf.append("transactiontime:" + transactiontime +",");
		//交易类型，0出售，1购买
		buf.append("transactiontype:" + transactiontype +",");
		//交易者名字
		if(this.tradersname!=null) buf.append("tradersname:" + tradersname.toString() +",");
		//交易者ID
		buf.append("tradersid:" + tradersid +",");
		//金币价格
		buf.append("pricegold:" + pricegold +",");
		//元宝价格
		buf.append("priceyuanbao:" + priceyuanbao +",");
		//道具信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}