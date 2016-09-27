package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买商品失败，返还钱消息
 */
public class ResStallsBuyDeductingFailToGameMessage extends Message{

	//购买者角色ID
	private long playerid;
	
	//摆摊的玩家ID
	private long stallsplayerid;
	
	//商品金币价格
	private int pricegold;
	
	//商品元宝价格
	private int priceyuanbao;
	
	//商品道具唯一ID，-1金币，-2元宝
	private long goodsid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//购买者角色ID
		writeLong(buf, this.playerid);
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
		//商品金币价格
		writeInt(buf, this.pricegold);
		//商品元宝价格
		writeInt(buf, this.priceyuanbao);
		//商品道具唯一ID，-1金币，-2元宝
		writeLong(buf, this.goodsid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//购买者角色ID
		this.playerid = readLong(buf);
		//摆摊的玩家ID
		this.stallsplayerid = readLong(buf);
		//商品金币价格
		this.pricegold = readInt(buf);
		//商品元宝价格
		this.priceyuanbao = readInt(buf);
		//商品道具唯一ID，-1金币，-2元宝
		this.goodsid = readLong(buf);
		return true;
	}
	
	/**
	 * get 购买者角色ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 购买者角色ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 摆摊的玩家ID
	 * @return 
	 */
	public long getStallsplayerid(){
		return stallsplayerid;
	}
	
	/**
	 * set 摆摊的玩家ID
	 */
	public void setStallsplayerid(long stallsplayerid){
		this.stallsplayerid = stallsplayerid;
	}
	
	/**
	 * get 商品金币价格
	 * @return 
	 */
	public int getPricegold(){
		return pricegold;
	}
	
	/**
	 * set 商品金币价格
	 */
	public void setPricegold(int pricegold){
		this.pricegold = pricegold;
	}
	
	/**
	 * get 商品元宝价格
	 * @return 
	 */
	public int getPriceyuanbao(){
		return priceyuanbao;
	}
	
	/**
	 * set 商品元宝价格
	 */
	public void setPriceyuanbao(int priceyuanbao){
		this.priceyuanbao = priceyuanbao;
	}
	
	/**
	 * get 商品道具唯一ID，-1金币，-2元宝
	 * @return 
	 */
	public long getGoodsid(){
		return goodsid;
	}
	
	/**
	 * set 商品道具唯一ID，-1金币，-2元宝
	 */
	public void setGoodsid(long goodsid){
		this.goodsid = goodsid;
	}
	
	
	@Override
	public int getId() {
		return 123309;
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
		//购买者角色ID
		buf.append("playerid:" + playerid +",");
		//摆摊的玩家ID
		buf.append("stallsplayerid:" + stallsplayerid +",");
		//商品金币价格
		buf.append("pricegold:" + pricegold +",");
		//商品元宝价格
		buf.append("priceyuanbao:" + priceyuanbao +",");
		//商品道具唯一ID，-1金币，-2元宝
		buf.append("goodsid:" + goodsid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}