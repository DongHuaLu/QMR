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
public class ReqStallsOffShelfMessage extends Message{

	//道具唯一ID，-1金币，-2元宝
	private long goodsid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具唯一ID，-1金币，-2元宝
		writeLong(buf, this.goodsid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具唯一ID，-1金币，-2元宝
		this.goodsid = readLong(buf);
		return true;
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
		return 123207;
	}

	@Override
	public String getQueue() {
		return "Server";
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//道具唯一ID，-1金币，-2元宝
		buf.append("goodsid:" + goodsid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}