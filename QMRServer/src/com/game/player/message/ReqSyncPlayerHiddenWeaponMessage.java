package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家暗器消息
 */
public class ReqSyncPlayerHiddenWeaponMessage extends Message{

	//角色id
	private long playerId;
	
	//角色暗器阶数
	private int hiddenWeaponStage;
	
	//角色暗器等级
	private int hiddenWeaponLevel;
	
	//角色暗器技能等级
	private int hiddenSkillWeaponLevel;
	
	//角色暗器阶数时间
	private long hiddenWeaponTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色暗器阶数
		writeInt(buf, this.hiddenWeaponStage);
		//角色暗器等级
		writeInt(buf, this.hiddenWeaponLevel);
		//角色暗器技能等级
		writeInt(buf, this.hiddenSkillWeaponLevel);
		//角色暗器阶数时间
		writeLong(buf, this.hiddenWeaponTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色暗器阶数
		this.hiddenWeaponStage = readInt(buf);
		//角色暗器等级
		this.hiddenWeaponLevel = readInt(buf);
		//角色暗器技能等级
		this.hiddenSkillWeaponLevel = readInt(buf);
		//角色暗器阶数时间
		this.hiddenWeaponTime = readLong(buf);
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
	 * get 角色暗器阶数
	 * @return 
	 */
	public int getHiddenWeaponStage(){
		return hiddenWeaponStage;
	}
	
	/**
	 * set 角色暗器阶数
	 */
	public void setHiddenWeaponStage(int hiddenWeaponStage){
		this.hiddenWeaponStage = hiddenWeaponStage;
	}
	
	/**
	 * get 角色暗器等级
	 * @return 
	 */
	public int getHiddenWeaponLevel(){
		return hiddenWeaponLevel;
	}
	
	/**
	 * set 角色暗器等级
	 */
	public void setHiddenWeaponLevel(int hiddenWeaponLevel){
		this.hiddenWeaponLevel = hiddenWeaponLevel;
	}
	
	/**
	 * get 角色暗器技能等级
	 * @return 
	 */
	public int getHiddenSkillWeaponLevel(){
		return hiddenSkillWeaponLevel;
	}
	
	/**
	 * set 角色暗器技能等级
	 */
	public void setHiddenSkillWeaponLevel(int hiddenSkillWeaponLevel){
		this.hiddenSkillWeaponLevel = hiddenSkillWeaponLevel;
	}
	
	/**
	 * get 角色暗器阶数时间
	 * @return 
	 */
	public long getHiddenWeaponTime(){
		return hiddenWeaponTime;
	}
	
	/**
	 * set 角色暗器阶数时间
	 */
	public void setHiddenWeaponTime(long hiddenWeaponTime){
		this.hiddenWeaponTime = hiddenWeaponTime;
	}
	
	
	@Override
	public int getId() {
		return 103335;
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
		//角色暗器阶数
		buf.append("hiddenWeaponStage:" + hiddenWeaponStage +",");
		//角色暗器等级
		buf.append("hiddenWeaponLevel:" + hiddenWeaponLevel +",");
		//角色暗器技能等级
		buf.append("hiddenSkillWeaponLevel:" + hiddenSkillWeaponLevel +",");
		//角色暗器阶数时间
		buf.append("hiddenWeaponTime:" + hiddenWeaponTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}