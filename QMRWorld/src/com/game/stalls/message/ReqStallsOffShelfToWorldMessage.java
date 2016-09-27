package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品下架消息
 */
public class ReqStallsOffShelfToWorldMessage extends Message{

	//摆摊的玩家ID
	private long stallsplayerid;
	
	//下架道具数量
	private int num;
	
	//道具唯一ID，-1金币，-2元宝
	private long goodsid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
		//下架道具数量
		writeInt(buf, this.num);
		//道具唯一ID，-1金币，-2元宝
		writeLong(buf, this.goodsid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摆摊的玩家ID
		this.stallsplayerid = readLong(buf);
		//下架道具数量
		this.num = readInt(buf);
		//道具唯一ID，-1金币，-2元宝
		this.goodsid = readLong(buf);
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
	 * get 下架道具数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 下架道具数量
	 */
	public void setNum(int num){
		this.num = num;
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
	
	
	@Override
	public int getId() {
		return 123314;
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
		//下架道具数量
		buf.append("num:" + num +",");
		//道具唯一ID，-1金币，-2元宝
		buf.append("goodsid:" + goodsid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}