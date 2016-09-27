package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家宠物消息
 */
public class ReqSyncPlayerPetMessage extends Message{

	//角色id
	private long playerId;
	
	//宠物信息
	private String pet;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//宠物信息
		writeString(buf, this.pet);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//宠物信息
		this.pet = readString(buf);
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
	 * get 宠物信息
	 * @return 
	 */
	public String getPet(){
		return pet;
	}
	
	/**
	 * set 宠物信息
	 */
	public void setPet(String pet){
		this.pet = pet;
	}
	
	
	@Override
	public int getId() {
		return 103330;
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
		//宠物信息
		if(this.pet!=null) buf.append("pet:" + pet.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}