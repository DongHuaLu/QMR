package com.game.horseweapon.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 骑乘兵器技能信息
 */
public class HorseWeaponSkillInfo extends Bean {

	//技能
	private int skill;
	
	//等级
	private int level;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能
		writeInt(buf, this.skill);
		//等级
		writeInt(buf, this.level);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能
		this.skill = readInt(buf);
		//等级
		this.level = readInt(buf);
		return true;
	}
	
	/**
	 * get 技能
	 * @return 
	 */
	public int getSkill(){
		return skill;
	}
	
	/**
	 * set 技能
	 */
	public void setSkill(int skill){
		this.skill = skill;
	}
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//技能
		buf.append("skill:" + skill +",");
		//等级
		buf.append("level:" + level +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}