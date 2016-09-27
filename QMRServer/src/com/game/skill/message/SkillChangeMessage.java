package com.game.skill.message;

import com.game.skill.bean.SkillInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能变更消息消息
 */
public class SkillChangeMessage extends Message{

	//技能信息列表
	private SkillInfo skills;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能信息列表
		writeBean(buf, this.skills);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能信息列表
		this.skills = (SkillInfo)readBean(buf, SkillInfo.class);
		return true;
	}
	
	/**
	 * get 技能信息列表
	 * @return 
	 */
	public SkillInfo getSkills(){
		return skills;
	}
	
	/**
	 * set 技能信息列表
	 */
	public void setSkills(SkillInfo skills){
		this.skills = skills;
	}
	
	
	@Override
	public int getId() {
		return 107107;
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
		//技能信息列表
		if(this.skills!=null) buf.append("skills:" + skills.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}