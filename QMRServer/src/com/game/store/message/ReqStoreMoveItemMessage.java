package com.game.store.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 移动物品消息
 */
public class ReqStoreMoveItemMessage extends Message{

	//物品Id
	private long itemId;
	
	//移动到格子Id
	private int toGridId;
	
	//移动数量
	private int num;
	
	//关联NPC
	private int npcid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeLong(buf, this.itemId);
		//移动到格子Id
		writeInt(buf, this.toGridId);
		//移动数量
		writeInt(buf, this.num);
		//关联NPC
		writeInt(buf, this.npcid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		this.itemId = readLong(buf);
		//移动到格子Id
		this.toGridId = readInt(buf);
		//移动数量
		this.num = readInt(buf);
		//关联NPC
		this.npcid = readInt(buf);
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
	 * get 移动到格子Id
	 * @return 
	 */
	public int getToGridId(){
		return toGridId;
	}
	
	/**
	 * set 移动到格子Id
	 */
	public void setToGridId(int toGridId){
		this.toGridId = toGridId;
	}
	
	/**
	 * get 移动数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 移动数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 关联NPC
	 * @return 
	 */
	public int getNpcid(){
		return npcid;
	}
	
	/**
	 * set 关联NPC
	 */
	public void setNpcid(int npcid){
		this.npcid = npcid;
	}
	
	
	@Override
	public int getId() {
		return 112201;
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
		//移动到格子Id
		buf.append("toGridId:" + toGridId +",");
		//移动数量
		buf.append("num:" + num +",");
		//关联NPC
		buf.append("npcid:" + npcid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}