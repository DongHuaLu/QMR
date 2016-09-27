package com.game.shop.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买返回信息
 */
public class ShopBuyResultItemBean extends Bean {

	//物品ID
	private long goodsid;
	
	//物品数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品ID
		writeLong(buf, this.goodsid);
		//物品数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品ID
		this.goodsid = readLong(buf);
		//物品数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品ID
	 * @return 
	 */
	public long getGoodsid(){
		return goodsid;
	}
	
	/**
	 * set 物品ID
	 */
	public void setGoodsid(long goodsid){
		this.goodsid = goodsid;
	}
	
	/**
	 * get 物品数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 物品数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品ID
		buf.append("goodsid:" + goodsid +",");
		//物品数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}