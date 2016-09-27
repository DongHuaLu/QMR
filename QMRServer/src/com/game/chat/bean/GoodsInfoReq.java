package com.game.chat.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 装备信息类
 */
public class GoodsInfoReq extends Bean {

	//物品Id
	private long goodId;
	
	//索引位置
	private int index;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeLong(buf, this.goodId);
		//索引位置
		writeInt(buf, this.index);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		this.goodId = readLong(buf);
		//索引位置
		this.index = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品Id
	 * @return 
	 */
	public long getGoodId(){
		return goodId;
	}
	
	/**
	 * set 物品Id
	 */
	public void setGoodId(long goodId){
		this.goodId = goodId;
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
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品Id
		buf.append("goodId:" + goodId +",");
		//索引位置
		buf.append("index:" + index +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}