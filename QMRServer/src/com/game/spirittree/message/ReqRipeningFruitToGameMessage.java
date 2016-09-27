package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 催熟奇异果消息
 */
public class ReqRipeningFruitToGameMessage extends Message{

	//果实ID
	private long fruitid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实ID
		writeLong(buf, this.fruitid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实ID
		this.fruitid = readLong(buf);
		return true;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	
	@Override
	public int getId() {
		return 133203;
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
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}