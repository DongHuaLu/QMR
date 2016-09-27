package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家消耗元宝消息
 */
public class ReqSyncPlayerGoldExpendMessage extends Message{

	//角色id
	private long playerId;
	
	//消耗类型
	private int reason;
	
	//消耗元宝数量
	private int gold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//消耗类型
		writeInt(buf, this.reason);
		//消耗元宝数量
		writeInt(buf, this.gold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//消耗类型
		this.reason = readInt(buf);
		//消耗元宝数量
		this.gold = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 消耗类型
	 * @return 
	 */
	public int getReason(){
		return reason;
	}
	
	/**
	 * set 消耗类型
	 */
	public void setReason(int reason){
		this.reason = reason;
	}
	
	/**
	 * get 消耗元宝数量
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 消耗元宝数量
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	
	@Override
	public int getId() {
		return 103337;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//消耗类型
		buf.append("reason:" + reason +",");
		//消耗元宝数量
		buf.append("gold:" + gold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}