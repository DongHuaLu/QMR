package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 翻牌-副本通关奖励信息消息
 */
public class ResZoneSelectAwardMessage extends Message{

	//单个道具奖励列表
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	//选择次数
	private byte num;
	
	//类型:0手动，1自动扫荡
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//单个道具奖励列表
		writeBean(buf, this.iteminfo);
		//选择次数
		writeByte(buf, this.num);
		//类型:0手动，1自动扫荡
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//单个道具奖励列表
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//选择次数
		this.num = readByte(buf);
		//类型:0手动，1自动扫荡
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 单个道具奖励列表
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 单个道具奖励列表
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	/**
	 * get 选择次数
	 * @return 
	 */
	public byte getNum(){
		return num;
	}
	
	/**
	 * set 选择次数
	 */
	public void setNum(byte num){
		this.num = num;
	}
	
	/**
	 * get 类型:0手动，1自动扫荡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型:0手动，1自动扫荡
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 128104;
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
		//单个道具奖励列表
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		//选择次数
		buf.append("num:" + num +",");
		//类型:0手动，1自动扫荡
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}