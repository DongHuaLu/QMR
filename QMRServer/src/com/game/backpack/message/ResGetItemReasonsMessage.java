package com.game.backpack.message;

import com.game.backpack.bean.ItemReasonsInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得物品原因消息
 */
public class ResGetItemReasonsMessage extends Message{

	//获得物品原因
	private int itemReasons;
	
	//物品列表
	private List<ItemReasonsInfo> itemReasonsInfoList = new ArrayList<ItemReasonsInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//获得物品原因
		writeInt(buf, this.itemReasons);
		//物品列表
		writeShort(buf, itemReasonsInfoList.size());
		for (int i = 0; i < itemReasonsInfoList.size(); i++) {
			writeBean(buf, itemReasonsInfoList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//获得物品原因
		this.itemReasons = readInt(buf);
		//物品列表
		int itemReasonsInfoList_length = readShort(buf);
		for (int i = 0; i < itemReasonsInfoList_length; i++) {
			itemReasonsInfoList.add((ItemReasonsInfo)readBean(buf, ItemReasonsInfo.class));
		}
		return true;
	}
	
	/**
	 * get 获得物品原因
	 * @return 
	 */
	public int getItemReasons(){
		return itemReasons;
	}
	
	/**
	 * set 获得物品原因
	 */
	public void setItemReasons(int itemReasons){
		this.itemReasons = itemReasons;
	}
	
	/**
	 * get 物品列表
	 * @return 
	 */
	public List<ItemReasonsInfo> getItemReasonsInfoList(){
		return itemReasonsInfoList;
	}
	
	/**
	 * set 物品列表
	 */
	public void setItemReasonsInfoList(List<ItemReasonsInfo> itemReasonsInfoList){
		this.itemReasonsInfoList = itemReasonsInfoList;
	}
	
	
	@Override
	public int getId() {
		return 104214;
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
		//获得物品原因
		buf.append("itemReasons:" + itemReasons +",");
		//物品列表
		buf.append("itemReasonsInfoList:{");
		for (int i = 0; i < itemReasonsInfoList.size(); i++) {
			buf.append(itemReasonsInfoList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}