package com.game.skill.message;

import com.game.skill.bean.SkillInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能增加消息
 */
public class SkillAddMessage extends Message{

	//技能信息
	private SkillInfo skill;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品信息
		writeBean(buf, this.skill);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品信息
		this.skill = (SkillInfo)readBean(buf, SkillInfo.class);
		return true;
	}
	
	/**
	 * get 物品信息
	 * @return 
	 */
	public SkillInfo getSkill(){
		return skill;
	}
	
	/**
	 * set 物品信息
	 */
	public void setSkill(SkillInfo skill){
		this.skill = skill;
	}
	
	
	@Override
	public int getId() {
		return 107102;
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
		//物品信息
		if(this.skill!=null) buf.append("skill:" + skill.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}