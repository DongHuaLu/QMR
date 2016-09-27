package com.game.hiddenweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 暗器技能触发消息
 */
public class ResHiddenWeaponSkillTriggerMessage extends Message{

	//暗器技能id
	private int skill;
	
	//暗器技能等级
	private int level;
	
	//玩家id
	private long playerid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//暗器技能id
		writeInt(buf, this.skill);
		//暗器技能等级
		writeInt(buf, this.level);
		//玩家id
		writeLong(buf, this.playerid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//暗器技能id
		this.skill = readInt(buf);
		//暗器技能等级
		this.level = readInt(buf);
		//玩家id
		this.playerid = readLong(buf);
		return true;
	}
	
	/**
	 * get 暗器技能id
	 * @return 
	 */
	public int getSkill(){
		return skill;
	}
	
	/**
	 * set 暗器技能id
	 */
	public void setSkill(int skill){
		this.skill = skill;
	}
	
	/**
	 * get 暗器技能等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 暗器技能等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	
	@Override
	public int getId() {
		return 162109;
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
		//暗器技能id
		buf.append("skill:" + skill +",");
		//暗器技能等级
		buf.append("level:" + level +",");
		//玩家id
		buf.append("playerid:" + playerid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}