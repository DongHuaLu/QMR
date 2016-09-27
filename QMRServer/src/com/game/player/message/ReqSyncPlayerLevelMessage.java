package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家等级消息
 */
public class ReqSyncPlayerLevelMessage extends Message{

	//角色id
	private long playerId;
	
	//角色等级
	private int level;
	
	//角色升级时间
	private long levelUpTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色等级
		writeInt(buf, this.level);
		//角色升级时间
		writeLong(buf, this.levelUpTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色等级
		this.level = readInt(buf);
		//角色升级时间
		this.levelUpTime = readLong(buf);
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
	 * get 角色等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 角色等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 角色升级时间
	 * @return 
	 */
	public long getLevelUpTime(){
		return levelUpTime;
	}
	
	/**
	 * set 角色升级时间
	 */
	public void setLevelUpTime(long levelUpTime){
		this.levelUpTime = levelUpTime;
	}
	
	
	@Override
	public int getId() {
		return 103305;
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
		//角色等级
		buf.append("level:" + level +",");
		//角色升级时间
		buf.append("levelUpTime:" + levelUpTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}