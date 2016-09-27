package com.game.scripts.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 面板物品框信息
 */
public class PanelItemInfo extends Bean {

	//控件ID名称
	private String name;
	
	//道具信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//控件ID名称
		writeString(buf, this.name);
		//道具信息
		writeBean(buf, this.iteminfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//控件ID名称
		this.name = readString(buf);
		//道具信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		return true;
	}
	
	/**
	 * get 控件ID名称
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 控件ID名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 道具信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 道具信息
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//控件ID名称
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//道具信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}