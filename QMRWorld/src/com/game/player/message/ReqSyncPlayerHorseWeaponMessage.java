package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家骑战兵器消息
 */
public class ReqSyncPlayerHorseWeaponMessage extends Message{

	//角色id
	private long playerId;
	
	//角色骑战兵器阶数
	private int horseWeaponStage;
	
	//角色骑战兵器等级
	private int horseWeaponLevel;
	
	//角色骑战兵器技能等级
	private int horseSkillWeaponLevel;
	
	//角色骑战兵器阶数时间
	private long horseWeaponTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色骑战兵器阶数
		writeInt(buf, this.horseWeaponStage);
		//角色骑战兵器等级
		writeInt(buf, this.horseWeaponLevel);
		//角色骑战兵器技能等级
		writeInt(buf, this.horseSkillWeaponLevel);
		//角色骑战兵器阶数时间
		writeLong(buf, this.horseWeaponTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色骑战兵器阶数
		this.horseWeaponStage = readInt(buf);
		//角色骑战兵器等级
		this.horseWeaponLevel = readInt(buf);
		//角色骑战兵器技能等级
		this.horseSkillWeaponLevel = readInt(buf);
		//角色骑战兵器阶数时间
		this.horseWeaponTime = readLong(buf);
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
	 * get 角色骑战兵器阶数
	 * @return 
	 */
	public int getHorseWeaponStage(){
		return horseWeaponStage;
	}
	
	/**
	 * set 角色骑战兵器阶数
	 */
	public void setHorseWeaponStage(int horseWeaponStage){
		this.horseWeaponStage = horseWeaponStage;
	}
	
	/**
	 * get 角色骑战兵器等级
	 * @return 
	 */
	public int getHorseWeaponLevel(){
		return horseWeaponLevel;
	}
	
	/**
	 * set 角色骑战兵器等级
	 */
	public void setHorseWeaponLevel(int horseWeaponLevel){
		this.horseWeaponLevel = horseWeaponLevel;
	}
	
	/**
	 * get 角色骑战兵器技能等级
	 * @return 
	 */
	public int getHorseSkillWeaponLevel(){
		return horseSkillWeaponLevel;
	}
	
	/**
	 * set 角色骑战兵器技能等级
	 */
	public void setHorseSkillWeaponLevel(int horseSkillWeaponLevel){
		this.horseSkillWeaponLevel = horseSkillWeaponLevel;
	}
	
	/**
	 * get 角色骑战兵器阶数时间
	 * @return 
	 */
	public long getHorseWeaponTime(){
		return horseWeaponTime;
	}
	
	/**
	 * set 角色骑战兵器阶数时间
	 */
	public void setHorseWeaponTime(long horseWeaponTime){
		this.horseWeaponTime = horseWeaponTime;
	}
	
	
	@Override
	public int getId() {
		return 103334;
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
		//角色骑战兵器阶数
		buf.append("horseWeaponStage:" + horseWeaponStage +",");
		//角色骑战兵器等级
		buf.append("horseWeaponLevel:" + horseWeaponLevel +",");
		//角色骑战兵器技能等级
		buf.append("horseSkillWeaponLevel:" + horseSkillWeaponLevel +",");
		//角色骑战兵器阶数时间
		buf.append("horseWeaponTime:" + horseWeaponTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}