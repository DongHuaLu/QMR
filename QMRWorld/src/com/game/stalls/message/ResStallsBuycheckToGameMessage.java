package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回购买商品检查结果，如果商品正常，则扣钱消息
 */
public class ResStallsBuycheckToGameMessage extends Message{

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
	
	//成功返回0，失败返回1
	private byte status;
	
	
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
		//成功返回0，失败返回1
		writeByte(buf, this.status);
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
		//成功返回0，失败返回1
		this.status = readByte(buf);
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
	
	/**
	 * get 成功返回0，失败返回1
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 成功返回0，失败返回1
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 123305;
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
		//成功返回0，失败返回1
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}