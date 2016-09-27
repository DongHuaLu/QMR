package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围消失物品消息
 */
public class ResRoundGoodsDisappearMessage extends Message{

	//消失物品列表
	private List<Long> goodsIds = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失物品列表
		writeShort(buf, goodsIds.size());
		for (int i = 0; i < goodsIds.size(); i++) {
			writeLong(buf, goodsIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失物品列表
		int goodsIds_length = readShort(buf);
		for (int i = 0; i < goodsIds_length; i++) {
			goodsIds.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失物品列表
	 * @return 
	 */
	public List<Long> getGoodsIds(){
		return goodsIds;
	}
	
	/**
	 * set 消失物品列表
	 */
	public void setGoodsIds(List<Long> goodsIds){
		this.goodsIds = goodsIds;
	}
	
	
	@Override
	public int getId() {
		return 101107;
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
		//消失物品列表
		buf.append("goodsIds:{");
		for (int i = 0; i < goodsIds.size(); i++) {
			buf.append(goodsIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}