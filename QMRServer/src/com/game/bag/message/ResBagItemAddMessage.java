package com.game.bag.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 跨服包裹物品信息增加消息
 */
public class ResBagItemAddMessage extends Message{

	//物品信息
	private com.game.backpack.bean.ItemInfo item;
	
	
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
		this.item = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		return true;
	}
	
	/**
	 * get 物品信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getItem(){
		return item;
	}
	
	/**
	 * set 物品信息
	 */
	public void setItem(com.game.backpack.bean.ItemInfo item){
		this.item = item;
	}
	
	
	@Override
	public int getId() {
		return 160101;
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