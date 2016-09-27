package com.game.gift.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始翻牌抽奖消息
 */
public class ReqStartShuffleRaffleMessage extends Message{

	//道具唯一ID
	private String itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具唯一ID
		writeString(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具唯一ID
		this.itemid = readString(buf);
		return true;
	}
	
	/**
	 * get 道具唯一ID
	 * @return 
	 */
	public String getItemid(){
		return itemid;
	}
	
	/**
	 * set 道具唯一ID
	 */
	public void setItemid(String itemid){
		this.itemid = itemid;
	}
	
	
	@Override
	public int getId() {
		return 129205;
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
		//道具唯一ID
		if(this.itemid!=null) buf.append("itemid:" + itemid.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}