package com.game.hiddenweapon.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 暗器技能ICO
 */
public class HiddenWeaponSkillIco extends Bean {

	//技能
	private int skill;
	
	//当前投掷数
	private int times;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能
		writeInt(buf, this.skill);
		//当前投掷数
		writeInt(buf, this.times);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能
		this.skill = readInt(buf);
		//当前投掷数
		this.times = readInt(buf);
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
	 * get 当前投掷数
	 * @return 
	 */
	public int getTimes(){
		return times;
	}
	
	/**
	 * set 当前投掷数
	 */
	public void setTimes(int times){
		this.times = times;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//技能
		buf.append("skill:" + skill +",");
		//当前投掷数
		buf.append("times:" + times +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}