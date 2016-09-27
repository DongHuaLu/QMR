package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城宝箱奖励选择消息
 */
public class ResKingCityChestSelectToClientMessage extends Message{

	//选择的道具奖励信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	//选择次数
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//选择的道具奖励信息
		writeBean(buf, this.iteminfo);
		//选择次数
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//选择的道具奖励信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//选择次数
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 选择的道具奖励信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 选择的道具奖励信息
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	/**
	 * get 选择次数
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 选择次数
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 146109;
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
		//选择的道具奖励信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		//选择次数
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}