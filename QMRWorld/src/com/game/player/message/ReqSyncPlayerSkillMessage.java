package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能升级消息
 */
public class ReqSyncPlayerSkillMessage extends Message{

	//角色id
	private long playerId;
	
	//角色技能
	private int skill;
	
	//技能提升的层数
	private int upLevel;
	
	//角色升级时间
	private long skillTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色技能
		writeInt(buf, this.skill);
		//技能提升的层数
		writeInt(buf, this.upLevel);
		//角色升级时间
		writeLong(buf, this.skillTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色技能
		this.skill = readInt(buf);
		//技能提升的层数
		this.upLevel = readInt(buf);
		//角色升级时间
		this.skillTime = readLong(buf);
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
	 * get 角色技能
	 * @return 
	 */
	public int getSkill(){
		return skill;
	}
	
	/**
	 * set 角色技能
	 */
	public void setSkill(int skill){
		this.skill = skill;
	}
	
	/**
	 * get 技能提升的层数
	 * @return 
	 */
	public int getUpLevel(){
		return upLevel;
	}
	
	/**
	 * set 技能提升的层数
	 */
	public void setUpLevel(int upLevel){
		this.upLevel = upLevel;
	}
	
	/**
	 * get 角色升级时间
	 * @return 
	 */
	public long getSkillTime(){
		return skillTime;
	}
	
	/**
	 * set 角色升级时间
	 */
	public void setSkillTime(long skillTime){
		this.skillTime = skillTime;
	}
	
	
	@Override
	public int getId() {
		return 103321;
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
		//角色技能
		buf.append("skill:" + skill +",");
		//技能提升的层数
		buf.append("upLevel:" + upLevel +",");
		//角色升级时间
		buf.append("skillTime:" + skillTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}