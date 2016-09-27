package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 元宝改变消息
 */
public class ReqTransactionsChangeYuanbaoMessage extends Message{

	//元宝数量
	private int yuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//元宝数量
		writeInt(buf, this.yuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//元宝数量
		this.yuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 元宝数量
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 元宝数量
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	
	@Override
	public int getId() {
		return 122208;
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
		buf.append("yuanbao:" + yuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}