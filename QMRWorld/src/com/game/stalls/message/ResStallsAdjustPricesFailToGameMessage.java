package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 商品调整，失败后返回消息
 */
public class ResStallsAdjustPricesFailToGameMessage extends Message{

	//摆摊的玩家ID
	private long stallsplayerid;
	
	//序列化的道具
	private String item;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摆摊的玩家ID
		writeLong(buf, this.stallsplayerid);
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
		return 123170;
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
		//序列化的道具
		if(this.item!=null) buf.append("item:" + item.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}