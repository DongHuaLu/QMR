package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 世界服务器通知游戏服务器玩家改名信息消息
 */
public class ResPlayerNameInfoToGameMessage extends Message{

	//角色id
	private long playerId;
	
	//是否可以改名
	private byte changeName;
	
	//是否可以改账号
	private byte changeUser;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//是否可以改名
		writeByte(buf, this.changeName);
		//是否可以改账号
		writeByte(buf, this.changeUser);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//是否可以改名
		this.changeName = readByte(buf);
		//是否可以改账号
		this.changeUser = readByte(buf);
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
	 * get 是否可以改名
	 * @return 
	 */
	public byte getChangeName(){
		return changeName;
	}
	
	/**
	 * set 是否可以改名
	 */
	public void setChangeName(byte changeName){
		this.changeName = changeName;
	}
	
	/**
	 * get 是否可以改账号
	 * @return 
	 */
	public byte getChangeUser(){
		return changeUser;
	}
	
	/**
	 * set 是否可以改账号
	 */
	public void setChangeUser(byte changeUser){
		this.changeUser = changeUser;
	}
	
	
	@Override
	public int getId() {
		return 103316;
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
		//是否可以改名
		buf.append("changeName:" + changeName +",");
		//是否可以改账号
		buf.append("changeUser:" + changeUser +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}