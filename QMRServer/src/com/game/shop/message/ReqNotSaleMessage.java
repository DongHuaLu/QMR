package com.game.shop.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求下架商品消息
 */
public class ReqNotSaleMessage extends Message{

	//销售Id
	private int sellId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//销售Id
		writeInt(buf, this.sellId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//销售Id
		this.sellId = readInt(buf);
		return true;
	}
	
	/**
	 * get 销售Id
	 * @return 
	 */
	public int getSellId(){
		return sellId;
	}
	
	/**
	 * set 销售Id
	 */
	public void setSellId(int sellId){
		this.sellId = sellId;
	}
	
	
	@Override
	public int getId() {
		return 105203;
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
		//销售Id
		buf.append("sellId:" + sellId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}