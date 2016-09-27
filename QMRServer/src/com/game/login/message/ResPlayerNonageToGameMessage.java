package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步防沉迷状态到游戏服务器消息
 */
public class ResPlayerNonageToGameMessage extends Message{

	//角色id
	private long playerId;
	
	//防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
	private int nonage;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
		writeInt(buf, this.nonage);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
		this.nonage = readInt(buf);
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
	 * get 防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
	 * @return 
	 */
	public int getNonage(){
		return nonage;
	}
	
	/**
	 * set 防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
	 */
	public void setNonage(int nonage){
		this.nonage = nonage;
	}
	
	
	@Override
	public int getId() {
		return 100310;
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
		//防沉迷状态 0：0-3小时 1：3-5小时 2：大于5小时
		buf.append("nonage:" + nonage +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}