package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 金币改变消息
 */
public class ReqTransactionsChangeGoldMessage extends Message{

	//金币数量
	private int gold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//金币数量
		writeInt(buf, this.gold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//金币数量
		this.gold = readInt(buf);
		return true;
	}
	
	/**
	 * get 金币数量
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 金币数量
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	
	@Override
	public int getId() {
		return 122207;
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
		//金币数量
		buf.append("gold:" + gold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}