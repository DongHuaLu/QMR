package com.game.backpack.message;

import com.game.backpack.bean.ItemInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 物品信息修正消息
 */
public class ResItemChangeMessage extends Message{

	//物品信息
	private ItemInfo item;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品信息
		writeBean(buf, this.item);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品信息
		this.item = (ItemInfo)readBean(buf, ItemInfo.class);
		return true;
	}
	
	/**
	 * get 物品信息
	 * @return 
	 */
	public ItemInfo getItem(){
		return item;
	}
	
	/**
	 * set 物品信息
	 */
	public void setItem(ItemInfo item){
		this.item = item;
	}
	
	
	@Override
	public int getId() {
		return 104103;
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
		//物品信息
		if(this.item!=null) buf.append("item:" + item.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}