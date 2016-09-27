package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改摊位名字消息
 */
public class ResStallsErrorMessage extends Message{

	//玩家ID，或者摊位号
	private long playerid;
	
	//错误类型
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID，或者摊位号
		writeLong(buf, this.playerid);
		//错误类型
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID，或者摊位号
		this.playerid = readLong(buf);
		//错误类型
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家ID，或者摊位号
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID，或者摊位号
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 错误类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 错误类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 123111;
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
		//玩家ID，或者摊位号
		buf.append("playerid:" + playerid +",");
		//错误类型
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}