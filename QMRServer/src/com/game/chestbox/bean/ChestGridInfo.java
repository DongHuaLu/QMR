package com.game.chestbox.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱格子信息
 */
public class ChestGridInfo extends Bean {

	//格子编号
	private int grididx;
	
	//格子类型
	private int gridtype;
	
	//当前格子中的物品信息
	private com.game.backpack.bean.ItemInfo curiteminfo;
	
	//当前格子中的多倍奖励物品信息
	private List<com.game.backpack.bean.ItemInfo> itemlist = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.grididx);
		//格子类型
		writeInt(buf, this.gridtype);
		//当前格子中的物品信息
		writeBean(buf, this.curiteminfo);
		//当前格子中的多倍奖励物品信息
		writeShort(buf, itemlist.size());
		for (int i = 0; i < itemlist.size(); i++) {
			writeBean(buf, itemlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.grididx = readInt(buf);
		//格子类型
		this.gridtype = readInt(buf);
		//当前格子中的物品信息
		this.curiteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//当前格子中的多倍奖励物品信息
		int itemlist_length = readShort(buf);
		for (int i = 0; i < itemlist_length; i++) {
			itemlist.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 格子编号
	 * @return 
	 */
	public int getGrididx(){
		return grididx;
	}
	
	/**
	 * set 格子编号
	 */
	public void setGrididx(int grididx){
		this.grididx = grididx;
	}
	
	/**
	 * get 格子类型
	 * @return 
	 */
	public int getGridtype(){
		return gridtype;
	}
	
	/**
	 * set 格子类型
	 */
	public void setGridtype(int gridtype){
		this.gridtype = gridtype;
	}
	
	/**
	 * get 当前格子中的物品信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getCuriteminfo(){
		return curiteminfo;
	}
	
	/**
	 * set 当前格子中的物品信息
	 */
	public void setCuriteminfo(com.game.backpack.bean.ItemInfo curiteminfo){
		this.curiteminfo = curiteminfo;
	}
	
	/**
	 * get 当前格子中的多倍奖励物品信息
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getItemlist(){
		return itemlist;
	}
	
	/**
	 * set 当前格子中的多倍奖励物品信息
	 */
	public void setItemlist(List<com.game.backpack.bean.ItemInfo> itemlist){
		this.itemlist = itemlist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//格子编号
		buf.append("grididx:" + grididx +",");
		//格子类型
		buf.append("gridtype:" + gridtype +",");
		//当前格子中的物品信息
		if(this.curiteminfo!=null) buf.append("curiteminfo:" + curiteminfo.toString() +",");
		//当前格子中的多倍奖励物品信息
		buf.append("itemlist:{");
		for (int i = 0; i < itemlist.size(); i++) {
			buf.append(itemlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}