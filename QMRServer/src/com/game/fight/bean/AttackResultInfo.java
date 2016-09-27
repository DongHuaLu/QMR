package com.game.fight.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 游戏实体战斗结果
 */
public class AttackResultInfo extends Bean {

	//实体Id
	private long entityId;
	
	//攻击来源Id
	private long sourceId;
	
	//攻击技能模板Id
	private int skillId;
	
	//攻击技能等级
	private int skillLevel;
	
	//攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
	private int fightResult;
	
	//伤害
	private int damage;
	
	//连击伤害
	private int hit;
	
	//反伤
	private int back;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//实体Id
		writeLong(buf, this.entityId);
		//攻击来源Id
		writeLong(buf, this.sourceId);
		//攻击技能模板Id
		writeInt(buf, this.skillId);
		//攻击技能等级
		writeInt(buf, this.skillLevel);
		//攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
		writeInt(buf, this.fightResult);
		//伤害
		writeInt(buf, this.damage);
		//连击伤害
		writeInt(buf, this.hit);
		//反伤
		writeInt(buf, this.back);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//实体Id
		this.entityId = readLong(buf);
		//攻击来源Id
		this.sourceId = readLong(buf);
		//攻击技能模板Id
		this.skillId = readInt(buf);
		//攻击技能等级
		this.skillLevel = readInt(buf);
		//攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
		this.fightResult = readInt(buf);
		//伤害
		this.damage = readInt(buf);
		//连击伤害
		this.hit = readInt(buf);
		//反伤
		this.back = readInt(buf);
		return true;
	}
	
	/**
	 * get 实体Id
	 * @return 
	 */
	public long getEntityId(){
		return entityId;
	}
	
	/**
	 * set 实体Id
	 */
	public void setEntityId(long entityId){
		this.entityId = entityId;
	}
	
	/**
	 * get 攻击来源Id
	 * @return 
	 */
	public long getSourceId(){
		return sourceId;
	}
	
	/**
	 * set 攻击来源Id
	 */
	public void setSourceId(long sourceId){
		this.sourceId = sourceId;
	}
	
	/**
	 * get 攻击技能模板Id
	 * @return 
	 */
	public int getSkillId(){
		return skillId;
	}
	
	/**
	 * set 攻击技能模板Id
	 */
	public void setSkillId(int skillId){
		this.skillId = skillId;
	}
	
	/**
	 * get 攻击技能等级
	 * @return 
	 */
	public int getSkillLevel(){
		return skillLevel;
	}
	
	/**
	 * set 攻击技能等级
	 */
	public void setSkillLevel(int skillLevel){
		this.skillLevel = skillLevel;
	}
	
	/**
	 * get 攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
	 * @return 
	 */
	public int getFightResult(){
		return fightResult;
	}
	
	/**
	 * set 攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
	 */
	public void setFightResult(int fightResult){
		this.fightResult = fightResult;
	}
	
	/**
	 * get 伤害
	 * @return 
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * set 伤害
	 */
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	/**
	 * get 连击伤害
	 * @return 
	 */
	public int getHit(){
		return hit;
	}
	
	/**
	 * set 连击伤害
	 */
	public void setHit(int hit){
		this.hit = hit;
	}
	
	/**
	 * get 反伤
	 * @return 
	 */
	public int getBack(){
		return back;
	}
	
	/**
	 * set 反伤
	 */
	public void setBack(int back){
		this.back = back;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//实体Id
		buf.append("entityId:" + entityId +",");
		//攻击来源Id
		buf.append("sourceId:" + sourceId +",");
		//攻击技能模板Id
		buf.append("skillId:" + skillId +",");
		//攻击技能等级
		buf.append("skillLevel:" + skillLevel +",");
		//攻击结果0-成功 1-MISS 2-跳闪 4-暴击 8-格挡    6-跳闪+暴击 12-格挡+暴击
		buf.append("fightResult:" + fightResult +",");
		//伤害
		buf.append("damage:" + damage +",");
		//连击伤害
		buf.append("hit:" + hit +",");
		//反伤
		buf.append("back:" + back +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}