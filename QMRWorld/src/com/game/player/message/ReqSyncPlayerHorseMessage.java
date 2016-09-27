package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家坐骑阶数消息
 */
public class ReqSyncPlayerHorseMessage extends Message{

	//角色id
	private long playerId;
	
	//角色坐骑阶数
	private int horseStage;
	
	//角色坐骑等级
	private int horseLevel;
	
	//角色坐骑技能等级
	private int horseSkillLevel;
	
	//角色坐骑阶数时间
	private long horseTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色坐骑阶数
		writeInt(buf, this.horseStage);
		//角色坐骑等级
		writeInt(buf, this.horseLevel);
		//角色坐骑技能等级
		writeInt(buf, this.horseSkillLevel);
		//角色坐骑阶数时间
		writeLong(buf, this.horseTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色坐骑阶数
		this.horseStage = readInt(buf);
		//角色坐骑等级
		this.horseLevel = readInt(buf);
		//角色坐骑技能等级
		this.horseSkillLevel = readInt(buf);
		//角色坐骑阶数时间
		this.horseTime = readLong(buf);
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
	 * get 角色坐骑阶数
	 * @return 
	 */
	public int getHorseStage(){
		return horseStage;
	}
	
	/**
	 * set 角色坐骑阶数
	 */
	public void setHorseStage(int horseStage){
		this.horseStage = horseStage;
	}
	
	/**
	 * get 角色坐骑等级
	 * @return 
	 */
	public int getHorseLevel(){
		return horseLevel;
	}
	
	/**
	 * set 角色坐骑等级
	 */
	public void setHorseLevel(int horseLevel){
		this.horseLevel = horseLevel;
	}
	
	/**
	 * get 角色坐骑技能等级
	 * @return 
	 */
	public int getHorseSkillLevel(){
		return horseSkillLevel;
	}
	
	/**
	 * set 角色坐骑技能等级
	 */
	public void setHorseSkillLevel(int horseSkillLevel){
		this.horseSkillLevel = horseSkillLevel;
	}
	
	/**
	 * get 角色坐骑阶数时间
	 * @return 
	 */
	public long getHorseTime(){
		return horseTime;
	}
	
	/**
	 * set 角色坐骑阶数时间
	 */
	public void setHorseTime(long horseTime){
		this.horseTime = horseTime;
	}
	
	
	@Override
	public int getId() {
		return 103322;
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
		//角色坐骑阶数
		buf.append("horseStage:" + horseStage +",");
		//角色坐骑等级
		buf.append("horseLevel:" + horseLevel +",");
		//角色坐骑技能等级
		buf.append("horseSkillLevel:" + horseSkillLevel +",");
		//角色坐骑阶数时间
		buf.append("horseTime:" + horseTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}