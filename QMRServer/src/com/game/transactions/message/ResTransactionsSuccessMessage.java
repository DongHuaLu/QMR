package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 交易成功消息
 */
public class ResTransactionsSuccessMessage extends Message{

	//交易获得经验
	private long tsexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//交易获得经验
		writeLong(buf, this.tsexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//交易获得经验
		this.tsexp = readLong(buf);
		return true;
	}
	
	/**
	 * get 交易获得经验
	 * @return 
	 */
	public long getTsexp(){
		return tsexp;
	}
	
	/**
	 * set 交易获得经验
	 */
	public void setTsexp(long tsexp){
		this.tsexp = tsexp;
	}
	
	
	@Override
	public int getId() {
		return 122109;
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
		//交易获得经验
		buf.append("tsexp:" + tsexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}