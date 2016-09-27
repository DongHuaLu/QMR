package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * A玩家发起交易消息
 */
public class ReqTransactionsLaunchMessage extends Message{

	//B交易玩家ID
	private long playerid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//B交易玩家ID
		writeLong(buf, this.playerid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//B交易玩家ID
		this.playerid = readLong(buf);
		return true;
	}
	
	/**
	 * get B交易玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set B交易玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	
	@Override
	public int getId() {
		return 122201;
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
		//B交易玩家ID
		buf.append("playerid:" + playerid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}