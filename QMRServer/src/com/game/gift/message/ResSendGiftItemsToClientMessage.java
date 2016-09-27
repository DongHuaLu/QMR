package com.game.gift.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送礼包物品消息
 */
public class ResSendGiftItemsToClientMessage extends Message{

	//礼包物品列表
	private List<com.game.backpack.bean.ItemInfo> items = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//礼包物品列表
		writeShort(buf, items.size());
		for (int i = 0; i < items.size(); i++) {
			writeBean(buf, items.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//礼包物品列表
		int items_length = readShort(buf);
		for (int i = 0; i < items_length; i++) {
			items.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 礼包物品列表
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getItems(){
		return items;
	}
	
	/**
	 * set 礼包物品列表
	 */
	public void setItems(List<com.game.backpack.bean.ItemInfo> items){
		this.items = items;
	}
	
	
	@Override
	public int getId() {
		return 129101;
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
		//礼包物品列表
		buf.append("items:{");
		for (int i = 0; i < items.size(); i++) {
			buf.append(items.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}