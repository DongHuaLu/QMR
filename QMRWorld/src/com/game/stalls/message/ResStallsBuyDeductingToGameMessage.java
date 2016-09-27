package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买商品，发送道具给玩家消息
 */
public class ResStallsBuyDeductingToGameMessage extends Message{

	//购买者角色ID
	private long playerid;
	
	//购买者角色名字
	private String playername;
	
	//摆摊的玩家ID
	private long stallsplayerid;
	
	//摆摊的玩家名字
	private String stallsplayername;
	
	//商品金币价格
	private int pricegold;
	
	//商品元宝价格
	private int priceyuanbao;
	
	//商品道具唯一ID，-1金币，-2元宝
	private long goodsid;
	
	//序列化的道具
	private String item;
	
	//交易号
	private long tradersid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//购买者角色ID
		writeLong(buf, this.playerid);
		//购买者角色名字
		writeString(buf, this.playername);
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
		//摆摊的玩家名字
		writeString(buf, this.stallsplayername);
		//商品金币价格
		writeInt(buf, this.pricegold);
		//商品元宝价格
		writeInt(buf, this.priceyuanbao);
		//商品道具唯一ID，-1金币，-2元宝
		writeLong(buf, this.goodsid);
		//序列化的道具
		writeString(buf, this.item);
		//交易号
		writeLong(buf, this.tradersid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//购买者角色ID
		this.playerid = readLong(buf);
		//购买者角色名字
		this.playername = readString(buf);
		//摆摊的玩家ID
		this.stallsplayerid = readLong(buf);
		//摆摊的玩家名字
		this.stallsplayername = readString(buf);
		//商品金币价格
		this.pricegold = readInt(buf);
		//商品元宝价格
		this.priceyuanbao = readInt(buf);
		//商品道具唯一ID，-1金币，-2元宝
		this.goodsid = readLong(buf);
		//序列化的道具
		this.item = readString(buf);
		//交易号
		this.tradersid = readLong(buf);
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
	 * get 购买者角色名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 购买者角色名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
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
	 * get 摆摊的玩家名字
	 * @return 
	 */
	public String getStallsplayername(){
		return stallsplayername;
	}
	
	/**
	 * set 摆摊的玩家名字
	 */
	public void setStallsplayername(String stallsplayername){
		this.stallsplayername = stallsplayername;
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
	
	/**
	 * get 序列化的道具
	 * @return 
	 */
	public String getItem(){
		return item;
	}
	
	/**
	 * set 序列化的道具
	 */
	public void setItem(String item){
		this.item = item;
	}
	
	/**
	 * get 交易号
	 * @return 
	 */
	public long getTradersid(){
		return tradersid;
	}
	
	/**
	 * set 交易号
	 */
	public void setTradersid(long tradersid){
		this.tradersid = tradersid;
	}
	
	
	@Override
	public int getId() {
		return 123307;
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
		//购买者角色名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//摆摊的玩家ID
		buf.append("stallsplayerid:" + stallsplayerid +",");
		//摆摊的玩家名字
		if(this.stallsplayername!=null) buf.append("stallsplayername:" + stallsplayername.toString() +",");
		//商品金币价格
		buf.append("pricegold:" + pricegold +",");
		//商品元宝价格
		buf.append("priceyuanbao:" + priceyuanbao +",");
		//商品道具唯一ID，-1金币，-2元宝
		buf.append("goodsid:" + goodsid +",");
		//序列化的道具
		if(this.item!=null) buf.append("item:" + item.toString() +",");
		//交易号
		buf.append("tradersid:" + tradersid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}