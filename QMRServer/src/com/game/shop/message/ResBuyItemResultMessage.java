package com.game.shop.message;

import com.game.shop.bean.ShopBuyResultItemBean;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买成功消息消息
 */
public class ResBuyItemResultMessage extends Message{

	//销售Id
	private int sellId;
	
	//花费类型
	private int costType;
	
	//物品列表
	private List<ShopBuyResultItemBean> goodsinfo = new ArrayList<ShopBuyResultItemBean>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//销售Id
		writeInt(buf, this.sellId);
		//花费类型
		writeInt(buf, this.costType);
		//物品列表
		writeShort(buf, goodsinfo.size());
		for (int i = 0; i < goodsinfo.size(); i++) {
			writeBean(buf, goodsinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//销售Id
		this.sellId = readInt(buf);
		//花费类型
		this.costType = readInt(buf);
		//物品列表
		int goodsinfo_length = readShort(buf);
		for (int i = 0; i < goodsinfo_length; i++) {
			goodsinfo.add((ShopBuyResultItemBean)readBean(buf, ShopBuyResultItemBean.class));
		}
		return true;
	}
	
	/**
	 * get 销售Id
	 * @return 
	 */
	public int getSellId(){
		return sellId;
	}
	
	/**
	 * set 销售Id
	 */
	public void setSellId(int sellId){
		this.sellId = sellId;
	}
	
	/**
	 * get 花费类型
	 * @return 
	 */
	public int getCostType(){
		return costType;
	}
	
	/**
	 * set 花费类型
	 */
	public void setCostType(int costType){
		this.costType = costType;
	}
	
	/**
	 * get 物品列表
	 * @return 
	 */
	public List<ShopBuyResultItemBean> getGoodsinfo(){
		return goodsinfo;
	}
	
	/**
	 * set 物品列表
	 */
	public void setGoodsinfo(List<ShopBuyResultItemBean> goodsinfo){
		this.goodsinfo = goodsinfo;
	}
	
	
	@Override
	public int getId() {
		return 105106;
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
		//销售Id
		buf.append("sellId:" + sellId +",");
		//花费类型
		buf.append("costType:" + costType +",");
		//物品列表
		buf.append("goodsinfo:{");
		for (int i = 0; i < goodsinfo.size(); i++) {
			buf.append(goodsinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}