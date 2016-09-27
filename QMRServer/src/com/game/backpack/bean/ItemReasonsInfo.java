package com.game.backpack.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得物品原因信息
 */
public class ItemReasonsInfo extends Bean {

	//物品唯一id
	private long itemId;
	
	//物品模板Id
	private int itemModelId;
	
	//物品数量
	private int itemNum;
	
	//获得物品原因
	private int itemReasons;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品唯一id
		writeLong(buf, this.itemId);
		//物品模板Id
		writeInt(buf, this.itemModelId);
		//物品数量
		writeInt(buf, this.itemNum);
		//获得物品原因
		writeInt(buf, this.itemReasons);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品唯一id
		this.itemId = readLong(buf);
		//物品模板Id
		this.itemModelId = readInt(buf);
		//物品数量
		this.itemNum = readInt(buf);
		//获得物品原因
		this.itemReasons = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品唯一id
	 * @return 
	 */
	public long getItemId(){
		return itemId;
	}
	
	/**
	 * set 物品唯一id
	 */
	public void setItemId(long itemId){
		this.itemId = itemId;
	}
	
	/**
	 * get 物品模板Id
	 * @return 
	 */
	public int getItemModelId(){
		return itemModelId;
	}
	
	/**
	 * set 物品模板Id
	 */
	public void setItemModelId(int itemModelId){
		this.itemModelId = itemModelId;
	}
	
	/**
	 * get 物品数量
	 * @return 
	 */
	public int getItemNum(){
		return itemNum;
	}
	
	/**
	 * set 物品数量
	 */
	public void setItemNum(int itemNum){
		this.itemNum = itemNum;
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
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品唯一id
		buf.append("itemId:" + itemId +",");
		//物品模板Id
		buf.append("itemModelId:" + itemModelId +",");
		//物品数量
		buf.append("itemNum:" + itemNum +",");
		//获得物品原因
		buf.append("itemReasons:" + itemReasons +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}