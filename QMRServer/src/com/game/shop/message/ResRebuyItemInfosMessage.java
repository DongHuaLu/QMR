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
 * 回购物品列表消息
 */
public class ResRebuyItemInfosMessage extends Message{

	//物品信息列表
	private List<com.game.backpack.bean.ItemInfo> items = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品信息列表
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
		//物品信息列表
		int items_length = readShort(buf);
		for (int i = 0; i < items_length; i++) {
			items.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 物品信息列表
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getItems(){
		return items;
	}
	
	/**
	 * set 物品信息列表
	 */
	public void setItems(List<com.game.backpack.bean.ItemInfo> items){
		this.items = items;
	}
	
	
	@Override
	public int getId() {
		return 105104;
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
		//物品信息列表
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