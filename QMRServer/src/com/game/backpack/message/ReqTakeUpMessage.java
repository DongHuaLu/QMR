package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 拾取消息
 */
public class ReqTakeUpMessage extends Message{

	//掉落ID
	private long goodsId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//掉落ID
		writeLong(buf, this.goodsId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//掉落ID
		this.goodsId = readLong(buf);
		return true;
	}
	
	/**
	 * get 掉落ID
	 * @return 
	 */
	public long getGoodsId(){
		return goodsId;
	}
	
	/**
	 * set 掉落ID
	 */
	public void setGoodsId(long goodsId){
		this.goodsId = goodsId;
	}
	
	
	@Override
	public int getId() {
		return 104211;
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
		//掉落ID
		buf.append("goodsId:" + goodsId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}