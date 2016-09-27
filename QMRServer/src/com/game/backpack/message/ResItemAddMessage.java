package com.game.backpack.message;

import com.game.backpack.bean.ItemInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 物品信息增加消息
 */
public class ResItemAddMessage extends Message{

	//物品信息
	private ItemInfo item;
	
	//获取方式
	private byte reason;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品信息
		writeBean(buf, this.item);
		//获取方式
		writeByte(buf, this.reason);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品信息
		this.item = (ItemInfo)readBean(buf, ItemInfo.class);
		//获取方式
		this.reason = readByte(buf);
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
	
	/**
	 * get 获取方式
	 * @return 
	 */
	public byte getReason(){
		return reason;
	}
	
	/**
	 * set 获取方式
	 */
	public void setReason(byte reason){
		this.reason = reason;
	}
	
	
	@Override
	public int getId() {
		return 104102;
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
		//获取方式
		buf.append("reason:" + reason +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}