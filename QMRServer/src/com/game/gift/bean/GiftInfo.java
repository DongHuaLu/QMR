package com.game.gift.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 礼包信息
 */
public class GiftInfo extends Bean {

	//礼包id
	private int giftid;
	
	//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
	private byte gifttype;
	
	//礼包相关数据
	private int value;
	
	//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
	private byte canreceive;
	
	//奖励道具信息
	private List<com.game.backpack.bean.ItemInfo> iteminfos = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//礼包id
		writeInt(buf, this.giftid);
		//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		writeByte(buf, this.gifttype);
		//礼包相关数据
		writeInt(buf, this.value);
		//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		writeByte(buf, this.canreceive);
		//奖励道具信息
		writeShort(buf, iteminfos.size());
		for (int i = 0; i < iteminfos.size(); i++) {
			writeBean(buf, iteminfos.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//礼包id
		this.giftid = readInt(buf);
		//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		this.gifttype = readByte(buf);
		//礼包相关数据
		this.value = readInt(buf);
		//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		this.canreceive = readByte(buf);
		//奖励道具信息
		int iteminfos_length = readShort(buf);
		for (int i = 0; i < iteminfos_length; i++) {
			iteminfos.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 礼包id
	 * @return 
	 */
	public int getGiftid(){
		return giftid;
	}
	
	/**
	 * set 礼包id
	 */
	public void setGiftid(int giftid){
		this.giftid = giftid;
	}
	
	/**
	 * get 礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
	 * @return 
	 */
	public byte getGifttype(){
		return gifttype;
	}
	
	/**
	 * set 礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
	 */
	public void setGifttype(byte gifttype){
		this.gifttype = gifttype;
	}
	
	/**
	 * get 礼包相关数据
	 * @return 
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * set 礼包相关数据
	 */
	public void setValue(int value){
		this.value = value;
	}
	
	/**
	 * get 是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
	 * @return 
	 */
	public byte getCanreceive(){
		return canreceive;
	}
	
	/**
	 * set 是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
	 */
	public void setCanreceive(byte canreceive){
		this.canreceive = canreceive;
	}
	
	/**
	 * get 奖励道具信息
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getIteminfos(){
		return iteminfos;
	}
	
	/**
	 * set 奖励道具信息
	 */
	public void setIteminfos(List<com.game.backpack.bean.ItemInfo> iteminfos){
		this.iteminfos = iteminfos;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//礼包id
		buf.append("giftid:" + giftid +",");
		//礼包类型(1-蓝钻每日 2-年费蓝钻每日 3-蓝钻新手 4-蓝钻成长 5-3366每日)
		buf.append("gifttype:" + gifttype +",");
		//礼包相关数据
		buf.append("value:" + value +",");
		//是否可领取 0-可领取 1-未达成不可领 2-已领取不可领
		buf.append("canreceive:" + canreceive +",");
		//奖励道具信息
		buf.append("iteminfos:{");
		for (int i = 0; i < iteminfos.size(); i++) {
			buf.append(iteminfos.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}