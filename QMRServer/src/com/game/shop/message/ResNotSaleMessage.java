package com.game.shop.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 下架商品列表消息
 */
public class ResNotSaleMessage extends Message{

	//销售Id
	private int sellId;
	
	//下架商品列表
	private List<Integer> itemIds = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//销售Id
		writeInt(buf, this.sellId);
		//下架商品列表
		writeShort(buf, itemIds.size());
		for (int i = 0; i < itemIds.size(); i++) {
			writeInt(buf, itemIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//销售Id
		this.sellId = readInt(buf);
		//下架商品列表
		int itemIds_length = readShort(buf);
		for (int i = 0; i < itemIds_length; i++) {
			itemIds.add(readInt(buf));
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
	 * get 下架商品列表
	 * @return 
	 */
	public List<Integer> getItemIds(){
		return itemIds;
	}
	
	/**
	 * set 下架商品列表
	 */
	public void setItemIds(List<Integer> itemIds){
		this.itemIds = itemIds;
	}
	
	
	@Override
	public int getId() {
		return 105101;
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
		//下架商品列表
		buf.append("itemIds:{");
		for (int i = 0; i < itemIds.size(); i++) {
			buf.append(itemIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}