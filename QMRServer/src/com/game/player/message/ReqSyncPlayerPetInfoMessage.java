package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家宠物简要信息消息
 */
public class ReqSyncPlayerPetInfoMessage extends Message{

	//角色id
	private long playerId;
	
	//宠物id
	private long petId;
	
	//宠物合体
	private int petHeti;
	
	//宠物等级
	private int petLevel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//宠物id
		writeLong(buf, this.petId);
		//宠物合体
		writeInt(buf, this.petHeti);
		//宠物等级
		writeInt(buf, this.petLevel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//宠物id
		this.petId = readLong(buf);
		//宠物合体
		this.petHeti = readInt(buf);
		//宠物等级
		this.petLevel = readInt(buf);
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
	 * get 宠物id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 宠物合体
	 * @return 
	 */
	public int getPetHeti(){
		return petHeti;
	}
	
	/**
	 * set 宠物合体
	 */
	public void setPetHeti(int petHeti){
		this.petHeti = petHeti;
	}
	
	/**
	 * get 宠物等级
	 * @return 
	 */
	public int getPetLevel(){
		return petLevel;
	}
	
	/**
	 * set 宠物等级
	 */
	public void setPetLevel(int petLevel){
		this.petLevel = petLevel;
	}
	
	
	@Override
	public int getId() {
		return 103331;
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
		//宠物id
		buf.append("petId:" + petId +",");
		//宠物合体
		buf.append("petHeti:" + petHeti +",");
		//宠物等级
		buf.append("petLevel:" + petLevel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}