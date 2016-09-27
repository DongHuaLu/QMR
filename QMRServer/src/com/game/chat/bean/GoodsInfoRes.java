package com.game.chat.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 装备信息类
 */
public class GoodsInfoRes extends Bean {

	//请求类型 0物品 1其它
	private int queryType;
	
	//索引位置
	private int index;
	
	//物品内容
	private com.game.backpack.bean.ItemInfo ItemInfo;
	
	//装备部位宝石信息
	private List<com.game.gem.bean.GemInfo> geminfo = new ArrayList<com.game.gem.bean.GemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//请求类型 0物品 1其它
		writeInt(buf, this.queryType);
		//索引位置
		writeInt(buf, this.index);
		//物品内容
		writeBean(buf, this.ItemInfo);
		//装备部位宝石信息
		writeShort(buf, geminfo.size());
		for (int i = 0; i < geminfo.size(); i++) {
			writeBean(buf, geminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//请求类型 0物品 1其它
		this.queryType = readInt(buf);
		//索引位置
		this.index = readInt(buf);
		//物品内容
		this.ItemInfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//装备部位宝石信息
		int geminfo_length = readShort(buf);
		for (int i = 0; i < geminfo_length; i++) {
			geminfo.add((com.game.gem.bean.GemInfo)readBean(buf, com.game.gem.bean.GemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 请求类型 0物品 1其它
	 * @return 
	 */
	public int getQueryType(){
		return queryType;
	}
	
	/**
	 * set 请求类型 0物品 1其它
	 */
	public void setQueryType(int queryType){
		this.queryType = queryType;
	}
	
	/**
	 * get 索引位置
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 索引位置
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	/**
	 * get 物品内容
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getItemInfo(){
		return ItemInfo;
	}
	
	/**
	 * set 物品内容
	 */
	public void setItemInfo(com.game.backpack.bean.ItemInfo ItemInfo){
		this.ItemInfo = ItemInfo;
	}
	
	/**
	 * get 装备部位宝石信息
	 * @return 
	 */
	public List<com.game.gem.bean.GemInfo> getGeminfo(){
		return geminfo;
	}
	
	/**
	 * set 装备部位宝石信息
	 */
	public void setGeminfo(List<com.game.gem.bean.GemInfo> geminfo){
		this.geminfo = geminfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//请求类型 0物品 1其它
		buf.append("queryType:" + queryType +",");
		//索引位置
		buf.append("index:" + index +",");
		//物品内容
		if(this.ItemInfo!=null) buf.append("ItemInfo:" + ItemInfo.toString() +",");
		//装备部位宝石信息
		buf.append("geminfo:{");
		for (int i = 0; i < geminfo.size(); i++) {
			buf.append(geminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}