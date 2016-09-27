package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家HP消息
 */
public class ReqSyncPlayerHpMessage extends Message{

	//角色id
	private long playerId;
	
	//玩家HP
	private int hp;
	
	//玩家MAXHP
	private int maxHp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//玩家HP
		writeInt(buf, this.hp);
		//玩家MAXHP
		writeInt(buf, this.maxHp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//玩家HP
		this.hp = readInt(buf);
		//玩家MAXHP
		this.maxHp = readInt(buf);
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
	 * get 玩家HP
	 * @return 
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * set 玩家HP
	 */
	public void setHp(int hp){
		this.hp = hp;
	}
	
	/**
	 * get 玩家MAXHP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 玩家MAXHP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	
	@Override
	public int getId() {
		return 103302;
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
		//玩家HP
		buf.append("hp:" + hp +",");
		//玩家MAXHP
		buf.append("maxHp:" + maxHp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}