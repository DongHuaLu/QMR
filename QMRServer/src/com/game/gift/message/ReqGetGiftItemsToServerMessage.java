package com.game.gift.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求获得普通礼包物品消息
 */
public class ReqGetGiftItemsToServerMessage extends Message{

	//要打开的礼包id
	private long giftid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//要打开的礼包id
		writeLong(buf, this.giftid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//要打开的礼包id
		this.giftid = readLong(buf);
		return true;
	}
	
	/**
	 * get 要打开的礼包id
	 * @return 
	 */
	public long getGiftid(){
		return giftid;
	}
	
	/**
	 * set 要打开的礼包id
	 */
	public void setGiftid(long giftid){
		this.giftid = giftid;
	}
	
	
	@Override
	public int getId() {
		return 129201;
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
		//要打开的礼包id
		buf.append("giftid:" + giftid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}