package com.game.spirittree.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 奖励道具简要信息（连续催熟时用）
 */
public class Rewardbriefinfo extends Bean {

	//道具ID
	private int itemmodelid;
	
	//道具数量
	private int itemnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具ID
		writeInt(buf, this.itemmodelid);
		//道具数量
		writeInt(buf, this.itemnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具ID
		this.itemmodelid = readInt(buf);
		//道具数量
		this.itemnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 道具ID
	 * @return 
	 */
	public int getItemmodelid(){
		return itemmodelid;
	}
	
	/**
	 * set 道具ID
	 */
	public void setItemmodelid(int itemmodelid){
		this.itemmodelid = itemmodelid;
	}
	
	/**
	 * get 道具数量
	 * @return 
	 */
	public int getItemnum(){
		return itemnum;
	}
	
	/**
	 * set 道具数量
	 */
	public void setItemnum(int itemnum){
		this.itemnum = itemnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//道具ID
		buf.append("itemmodelid:" + itemmodelid +",");
		//道具数量
		buf.append("itemnum:" + itemnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}