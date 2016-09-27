package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * B玩家接受交易请求消息
 */
public class ReqTransactionsAcceptMessage extends Message{

	//交易ID
	private long transid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//交易ID
		writeLong(buf, this.transid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//交易ID
		this.transid = readLong(buf);
		return true;
	}
	
	/**
	 * get 交易ID
	 * @return 
	 */
	public long getTransid(){
		return transid;
	}
	
	/**
	 * set 交易ID
	 */
	public void setTransid(long transid){
		this.transid = transid;
	}
	
	
	@Override
	public int getId() {
		return 122202;
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
		//交易ID
		buf.append("transid:" + transid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}