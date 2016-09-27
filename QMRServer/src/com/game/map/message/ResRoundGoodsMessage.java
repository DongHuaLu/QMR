package com.game.map.message;

import com.game.map.bean.DropGoodsInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围掉落物品消息
 */
public class ResRoundGoodsMessage extends Message{

	//周围掉落物品信息
	private DropGoodsInfo goods;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围掉落物品信息
		writeBean(buf, this.goods);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围掉落物品信息
		this.goods = (DropGoodsInfo)readBean(buf, DropGoodsInfo.class);
		return true;
	}
	
	/**
	 * get 周围掉落物品信息
	 * @return 
	 */
	public DropGoodsInfo getGoods(){
		return goods;
	}
	
	/**
	 * set 周围掉落物品信息
	 */
	public void setGoods(DropGoodsInfo goods){
		this.goods = goods;
	}
	
	
	@Override
	public int getId() {
		return 101103;
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
		//周围掉落物品信息
		if(this.goods!=null) buf.append("goods:" + goods.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}