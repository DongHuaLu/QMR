package com.game.gift.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 翻牌抽奖，发送道具列表消息
 */
public class ResShuffleRaffleMessage extends Message{

	//奖励道具信息
	private List<com.game.backpack.bean.ItemInfo> iteminfos = new ArrayList<com.game.backpack.bean.ItemInfo>();
	//0发送翻牌前的列表，1发送翻牌后的道具
	private int type;
	
	//道具唯一ID
	private String itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励道具信息
		writeShort(buf, iteminfos.size());
		for (int i = 0; i < iteminfos.size(); i++) {
			writeBean(buf, iteminfos.get(i));
		}
		//0发送翻牌前的列表，1发送翻牌后的道具
		writeInt(buf, this.type);
		//道具唯一ID
		writeString(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励道具信息
		int iteminfos_length = readShort(buf);
		for (int i = 0; i < iteminfos_length; i++) {
			iteminfos.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		//0发送翻牌前的列表，1发送翻牌后的道具
		this.type = readInt(buf);
		//道具唯一ID
		this.itemid = readString(buf);
		return true;
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
	
	/**
	 * get 0发送翻牌前的列表，1发送翻牌后的道具
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 0发送翻牌前的列表，1发送翻牌后的道具
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 道具唯一ID
	 * @return 
	 */
	public String getItemid(){
		return itemid;
	}
	
	/**
	 * set 道具唯一ID
	 */
	public void setItemid(String itemid){
		this.itemid = itemid;
	}
	
	
	@Override
	public int getId() {
		return 129105;
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
		//奖励道具信息
		buf.append("iteminfos:{");
		for (int i = 0; i < iteminfos.size(); i++) {
			buf.append(iteminfos.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//0发送翻牌前的列表，1发送翻牌后的道具
		buf.append("type:" + type +",");
		//道具唯一ID
		if(this.itemid!=null) buf.append("itemid:" + itemid.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}