package com.game.stalls.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位物品信息
 */
public class StallsGoodsInfo extends Bean {

	//金币价格
	private int pricegold;
	
	//元宝价格
	private int priceyuanbao;
	
	//出售的道具信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//金币价格
		writeInt(buf, this.pricegold);
		//元宝价格
		writeInt(buf, this.priceyuanbao);
		//出售的道具信息
		writeBean(buf, this.iteminfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//金币价格
		this.pricegold = readInt(buf);
		//元宝价格
		this.priceyuanbao = readInt(buf);
		//出售的道具信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		return true;
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
	 * get 出售的道具信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 出售的道具信息
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//金币价格
		buf.append("pricegold:" + pricegold +",");
		//元宝价格
		buf.append("priceyuanbao:" + priceyuanbao +",");
		//出售的道具信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}