package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 被偷果实补偿经验消息
 */
public class ResGuildAccExpToWorldMessage extends Message{

	//操作的玩家ID
	private long playerid;
	
	//经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//操作的玩家ID
		writeLong(buf, this.playerid);
		//经验
		writeInt(buf, this.exp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//操作的玩家ID
		this.playerid = readLong(buf);
		//经验
		this.exp = readInt(buf);
		return true;
	}
	
	/**
	 * get 操作的玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 操作的玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	
	@Override
	public int getId() {
		return 133311;
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
		//操作的玩家ID
		buf.append("playerid:" + playerid +",");
		//经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}