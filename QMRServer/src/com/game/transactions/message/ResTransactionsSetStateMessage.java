package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 设置交易状态消息
 */
public class ResTransactionsSetStateMessage extends Message{

	//玩家ID
	private long playerid;
	
	//0交易中，1锁定，2确定交易
	private byte state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//0交易中，1锁定，2确定交易
		writeByte(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//0交易中，1锁定，2确定交易
		this.state = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 0交易中，1锁定，2确定交易
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 0交易中，1锁定，2确定交易
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 122108;
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
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//0交易中，1锁定，2确定交易
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}