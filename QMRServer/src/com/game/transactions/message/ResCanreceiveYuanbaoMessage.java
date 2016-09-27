package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送可领取元宝消息
 */
public class ResCanreceiveYuanbaoMessage extends Message{

	//可领取元宝
	private int canryuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//可领取元宝
		writeInt(buf, this.canryuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//可领取元宝
		this.canryuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 可领取元宝
	 * @return 
	 */
	public int getCanryuanbao(){
		return canryuanbao;
	}
	
	/**
	 * set 可领取元宝
	 */
	public void setCanryuanbao(int canryuanbao){
		this.canryuanbao = canryuanbao;
	}
	
	
	@Override
	public int getId() {
		return 122110;
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
		//可领取元宝
		buf.append("canryuanbao:" + canryuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}