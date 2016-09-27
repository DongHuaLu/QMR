package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品上架消息
 */
public class ReqStallsProductWasAddedToWorldMessage extends Message{

	//摆摊的玩家ID
	private long stallsplayerid;
	
	//道具唯一ID，-1金币，-2元宝
	private long goodsid;
	
	//金币价格
	private int pricegold;
	
	//元宝价格
	private int priceyuanbao;
	
	//序列化的道具
	private String item;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
		//道具唯一ID，-1金币，-2元宝
		writeLong(buf, this.goodsid);
		//金币价格
		writeInt(buf, this.pricegold);
		//元宝价格
		writeInt(buf, this.priceyuanbao);
		//序列化的道具
		writeString(buf, this.item);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摆摊的玩家ID
		this.stallsplayerid = readLong(buf);
		//道具唯一ID，-1金币，-2元宝
		this.goodsid = readLong(buf);
		//金币价格
		this.pricegold = readInt(buf);
		//元宝价格
		this.priceyuanbao = readInt(buf);
		//序列化的道具
		this.item = readString(buf);
		return true;
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
	 * get 道具唯一ID，-1金币，-2元宝
	 * @return 
	 */
	public long getGoodsid(){
		return goodsid;
	}
	
	/**
	 * set 道具唯一ID，-1金币，-2元宝
	 */
	public void setGoodsid(long goodsid){
		this.goodsid = goodsid;
	}
	
	/**
	 * get 金币价格
	 * @return 
	 */
	public int getPricegold(){
		return pricegold;
	}
	
	/**
	 * set 金币价格
	 */
	public void setPricegold(int pricegold){
		this.pricegold = pricegold;
	}
	
	/**
	 * get 元宝价格
	 * @return 
	 */
	public int getPriceyuanbao(){
		return priceyuanbao;
	}
	
	/**
	 * set 元宝价格
	 */
	public void setPriceyuanbao(int priceyuanbao){
		this.priceyuanbao = priceyuanbao;
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
	
	
	@Override
	public int getId() {
		return 123310;
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
		//摆摊的玩家ID
		buf.append("stallsplayerid:" + stallsplayerid +",");
		//道具唯一ID，-1金币，-2元宝
		buf.append("goodsid:" + goodsid +",");
		//金币价格
		buf.append("pricegold:" + pricegold +",");
		//元宝价格
		buf.append("priceyuanbao:" + priceyuanbao +",");
		//序列化的道具
		if(this.item!=null) buf.append("item:" + item.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}