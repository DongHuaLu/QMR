package com.game.skill.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能等级加成信息
 */
public class SkillLevelInfo extends Bean {

	//技能等级加成模块 1-龙元心法， 2-buff
	private int key;
	
	//技能等级
	private int level;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能等级加成模块 1-龙元心法， 2-buff
		writeInt(buf, this.key);
		//技能等级
		writeInt(buf, this.level);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能等级加成模块 1-龙元心法， 2-buff
		this.key = readInt(buf);
		//技能等级
		this.level = readInt(buf);
		return true;
	}
	
	/**
	 * get 技能等级加成模块 1-龙元心法， 2-buff
	 * @return 
	 */
	public int getKey(){
		return key;
	}
	
	/**
	 * set 技能等级加成模块 1-龙元心法， 2-buff
	 */
	public void setKey(int key){
		this.key = key;
	}
	
	/**
	 * get 技能等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 技能等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//技能等级加成模块 1-龙元心法， 2-buff
		buf.append("key:" + key +",");
		//技能等级
		buf.append("level:" + level +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}