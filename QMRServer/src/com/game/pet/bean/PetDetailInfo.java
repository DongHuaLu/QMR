package com.game.pet.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物祥细信息类
 */
public class PetDetailInfo extends Bean {

	//宠物Id
	private long petId;
	
	//宠物模板Id
	private int petModelId;
	
	//宠物等级
	private int level;
	
	//宠物HP
	private int hp;
	
	//宠物最大HP
	private int maxHp;
	
	//宠物MP
	private int mp;
	
	//宠物最大MP
	private int maxMp;
	
	//宠物SP
	private int sp;
	
	//宠物最大SP
	private int maxSp;
	
	//宠物速度
	private int speed;
	
	//出战状态,1出战 0不出战
	private byte showState;
	
	//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
	private int dieTime;
	
	//合体次数
	private int htCount;
	
	//今日合体次数
	private int dayCount;
	
	//合体冷确时间
	private int htCoolDownTime;
	
	//技能列表
	private List<com.game.skill.bean.SkillInfo> skillInfos = new ArrayList<com.game.skill.bean.SkillInfo>();
	//合体加成
	private List<com.game.player.bean.PlayerAttributeItem> htAddition = new ArrayList<com.game.player.bean.PlayerAttributeItem>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//宠物模板Id
		writeInt(buf, this.petModelId);
		//宠物等级
		writeInt(buf, this.level);
		//宠物HP
		writeInt(buf, this.hp);
		//宠物最大HP
		writeInt(buf, this.maxHp);
		//宠物MP
		writeInt(buf, this.mp);
		//宠物最大MP
		writeInt(buf, this.maxMp);
		//宠物SP
		writeInt(buf, this.sp);
		//宠物最大SP
		writeInt(buf, this.maxSp);
		//宠物速度
		writeInt(buf, this.speed);
		//出战状态,1出战 0不出战
		writeByte(buf, this.showState);
		//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		writeInt(buf, this.dieTime);
		//合体次数
		writeInt(buf, this.htCount);
		//今日合体次数
		writeInt(buf, this.dayCount);
		//合体冷确时间
		writeInt(buf, this.htCoolDownTime);
		//技能列表
		writeShort(buf, skillInfos.size());
		for (int i = 0; i < skillInfos.size(); i++) {
			writeBean(buf, skillInfos.get(i));
		}
		//合体加成
		writeShort(buf, htAddition.size());
		for (int i = 0; i < htAddition.size(); i++) {
			writeBean(buf, htAddition.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//宠物模板Id
		this.petModelId = readInt(buf);
		//宠物等级
		this.level = readInt(buf);
		//宠物HP
		this.hp = readInt(buf);
		//宠物最大HP
		this.maxHp = readInt(buf);
		//宠物MP
		this.mp = readInt(buf);
		//宠物最大MP
		this.maxMp = readInt(buf);
		//宠物SP
		this.sp = readInt(buf);
		//宠物最大SP
		this.maxSp = readInt(buf);
		//宠物速度
		this.speed = readInt(buf);
		//出战状态,1出战 0不出战
		this.showState = readByte(buf);
		//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		this.dieTime = readInt(buf);
		//合体次数
		this.htCount = readInt(buf);
		//今日合体次数
		this.dayCount = readInt(buf);
		//合体冷确时间
		this.htCoolDownTime = readInt(buf);
		//技能列表
		int skillInfos_length = readShort(buf);
		for (int i = 0; i < skillInfos_length; i++) {
			skillInfos.add((com.game.skill.bean.SkillInfo)readBean(buf, com.game.skill.bean.SkillInfo.class));
		}
		//合体加成
		int htAddition_length = readShort(buf);
		for (int i = 0; i < htAddition_length; i++) {
			htAddition.add((com.game.player.bean.PlayerAttributeItem)readBean(buf, com.game.player.bean.PlayerAttributeItem.class));
		}
		return true;
	}
	
	/**
	 * get 宠物Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 宠物模板Id
	 * @return 
	 */
	public int getPetModelId(){
		return petModelId;
	}
	
	/**
	 * set 宠物模板Id
	 */
	public void setPetModelId(int petModelId){
		this.petModelId = petModelId;
	}
	
	/**
	 * get 宠物等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 宠物等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 宠物HP
	 * @return 
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * set 宠物HP
	 */
	public void setHp(int hp){
		this.hp = hp;
	}
	
	/**
	 * get 宠物最大HP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 宠物最大HP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	/**
	 * get 宠物MP
	 * @return 
	 */
	public int getMp(){
		return mp;
	}
	
	/**
	 * set 宠物MP
	 */
	public void setMp(int mp){
		this.mp = mp;
	}
	
	/**
	 * get 宠物最大MP
	 * @return 
	 */
	public int getMaxMp(){
		return maxMp;
	}
	
	/**
	 * set 宠物最大MP
	 */
	public void setMaxMp(int maxMp){
		this.maxMp = maxMp;
	}
	
	/**
	 * get 宠物SP
	 * @return 
	 */
	public int getSp(){
		return sp;
	}
	
	/**
	 * set 宠物SP
	 */
	public void setSp(int sp){
		this.sp = sp;
	}
	
	/**
	 * get 宠物最大SP
	 * @return 
	 */
	public int getMaxSp(){
		return maxSp;
	}
	
	/**
	 * set 宠物最大SP
	 */
	public void setMaxSp(int maxSp){
		this.maxSp = maxSp;
	}
	
	/**
	 * get 宠物速度
	 * @return 
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * set 宠物速度
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * get 出战状态,1出战 0不出战
	 * @return 
	 */
	public byte getShowState(){
		return showState;
	}
	
	/**
	 * set 出战状态,1出战 0不出战
	 */
	public void setShowState(byte showState){
		this.showState = showState;
	}
	
	/**
	 * get 死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
	 * @return 
	 */
	public int getDieTime(){
		return dieTime;
	}
	
	/**
	 * set 死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
	 */
	public void setDieTime(int dieTime){
		this.dieTime = dieTime;
	}
	
	/**
	 * get 合体次数
	 * @return 
	 */
	public int getHtCount(){
		return htCount;
	}
	
	/**
	 * set 合体次数
	 */
	public void setHtCount(int htCount){
		this.htCount = htCount;
	}
	
	/**
	 * get 今日合体次数
	 * @return 
	 */
	public int getDayCount(){
		return dayCount;
	}
	
	/**
	 * set 今日合体次数
	 */
	public void setDayCount(int dayCount){
		this.dayCount = dayCount;
	}
	
	/**
	 * get 合体冷确时间
	 * @return 
	 */
	public int getHtCoolDownTime(){
		return htCoolDownTime;
	}
	
	/**
	 * set 合体冷确时间
	 */
	public void setHtCoolDownTime(int htCoolDownTime){
		this.htCoolDownTime = htCoolDownTime;
	}
	
	/**
	 * get 技能列表
	 * @return 
	 */
	public List<com.game.skill.bean.SkillInfo> getSkillInfos(){
		return skillInfos;
	}
	
	/**
	 * set 技能列表
	 */
	public void setSkillInfos(List<com.game.skill.bean.SkillInfo> skillInfos){
		this.skillInfos = skillInfos;
	}
	
	/**
	 * get 合体加成
	 * @return 
	 */
	public List<com.game.player.bean.PlayerAttributeItem> getHtAddition(){
		return htAddition;
	}
	
	/**
	 * set 合体加成
	 */
	public void setHtAddition(List<com.game.player.bean.PlayerAttributeItem> htAddition){
		this.htAddition = htAddition;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//宠物Id
		buf.append("petId:" + petId +",");
		//宠物模板Id
		buf.append("petModelId:" + petModelId +",");
		//宠物等级
		buf.append("level:" + level +",");
		//宠物HP
		buf.append("hp:" + hp +",");
		//宠物最大HP
		buf.append("maxHp:" + maxHp +",");
		//宠物MP
		buf.append("mp:" + mp +",");
		//宠物最大MP
		buf.append("maxMp:" + maxMp +",");
		//宠物SP
		buf.append("sp:" + sp +",");
		//宠物最大SP
		buf.append("maxSp:" + maxSp +",");
		//宠物速度
		buf.append("speed:" + speed +",");
		//出战状态,1出战 0不出战
		buf.append("showState:" + showState +",");
		//死亡时间 如果出战状态且未死亡则返回0 否则返回秒级时间
		buf.append("dieTime:" + dieTime +",");
		//合体次数
		buf.append("htCount:" + htCount +",");
		//今日合体次数
		buf.append("dayCount:" + dayCount +",");
		//合体冷确时间
		buf.append("htCoolDownTime:" + htCoolDownTime +",");
		//技能列表
		buf.append("skillInfos:{");
		for (int i = 0; i < skillInfos.size(); i++) {
			buf.append(skillInfos.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//合体加成
		buf.append("htAddition:{");
		for (int i = 0; i < htAddition.size(); i++) {
			buf.append(htAddition.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}