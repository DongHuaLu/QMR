package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 绑定元宝变化消息
 */
public class ResBindGoldChangeMessage extends Message{

	//元宝数量
	private int bindgold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//元宝数量
		writeInt(buf, this.bindgold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//元宝数量
		this.bindgold = readInt(buf);
		return true;
	}
	
	/**
	 * get 元宝数量
	 * @return 
	 */
	public int getBindgold(){
		return bindgold;
	}
	
	/**
	 * set 元宝数量
	 */
	public void setBindgold(int bindgold){
		this.bindgold = bindgold;
	}
	
	
	@Override
	public int getId() {
		return 104111;
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
		//元宝数量
		buf.append("bindgold:" + bindgold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}