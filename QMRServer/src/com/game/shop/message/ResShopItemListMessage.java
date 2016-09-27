package com.game.shop.message;

import com.game.shop.bean.ShopItemInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品列表消息
 */
public class ResShopItemListMessage extends Message{

	//模型编号
	private int shopModelId;
	
	//页号
	private byte page;
	
	//指定页的列表
	private List<ShopItemInfo> shopItemList = new ArrayList<ShopItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//模型编号
		writeInt(buf, this.shopModelId);
		//页号
		writeByte(buf, this.page);
		//指定页的列表
		writeShort(buf, shopItemList.size());
		for (int i = 0; i < shopItemList.size(); i++) {
			writeBean(buf, shopItemList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//模型编号
		this.shopModelId = readInt(buf);
		//页号
		this.page = readByte(buf);
		//指定页的列表
		int shopItemList_length = readShort(buf);
		for (int i = 0; i < shopItemList_length; i++) {
			shopItemList.add((ShopItemInfo)readBean(buf, ShopItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 模型编号
	 * @return 
	 */
	public int getShopModelId(){
		return shopModelId;
	}
	
	/**
	 * set 模型编号
	 */
	public void setShopModelId(int shopModelId){
		this.shopModelId = shopModelId;
	}
	
	/**
	 * get 页号
	 * @return 
	 */
	public byte getPage(){
		return page;
	}
	
	/**
	 * set 页号
	 */
	public void setPage(byte page){
		this.page = page;
	}
	
	/**
	 * get 指定页的列表
	 * @return 
	 */
	public List<ShopItemInfo> getShopItemList(){
		return shopItemList;
	}
	
	/**
	 * set 指定页的列表
	 */
	public void setShopItemList(List<ShopItemInfo> shopItemList){
		this.shopItemList = shopItemList;
	}
	
	
	@Override
	public int getId() {
		return 105105;
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
		//模型编号
		buf.append("shopModelId:" + shopModelId +",");
		//页号
		buf.append("page:" + page +",");
		//指定页的列表
		buf.append("shopItemList:{");
		for (int i = 0; i < shopItemList.size(); i++) {
			buf.append(shopItemList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}