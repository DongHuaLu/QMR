package com.game.equip.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 卸下装备信息消息
 */
public class UnwearEquipItemMessage extends Message{

	//物品Id
	private long itemId;
	
	//格子Id
	private int gridId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeLong(buf, this.itemId);
		//格子Id
		writeInt(buf, this.gridId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		this.itemId = readLong(buf);
		//格子Id
		this.gridId = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品Id
	 * @return 
	 */
	public long getItemId(){
		return itemId;
	}
	
	/**
	 * set 物品Id
	 */
	public void setItemId(long itemId){
		this.itemId = itemId;
	}
	
	/**
	 * get 格子Id
	 * @return 
	 */
	public int getGridId(){
		return gridId;
	}
	
	/**
	 * set 格子Id
	 */
	public void setGridId(int gridId){
		this.gridId = gridId;
	}
	
	
	@Override
	public int getId() {
		return 106102;
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
		//物品Id
		buf.append("itemId:" + itemId +",");
		//格子Id
		buf.append("gridId:" + gridId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}