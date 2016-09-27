package com.game.marriage.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回配偶武功消息
 */
public class ResGetSpouseSkillToClientMessage extends Message{

	//默认技能模板Id
	private int defaultSkill;
	
	//技能信息列表
	private List<com.game.skill.bean.SkillInfo> skills = new ArrayList<com.game.skill.bean.SkillInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//默认技能模板Id
		writeInt(buf, this.defaultSkill);
		//技能信息列表
		writeShort(buf, skills.size());
		for (int i = 0; i < skills.size(); i++) {
			writeBean(buf, skills.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//默认技能模板Id
		this.defaultSkill = readInt(buf);
		//技能信息列表
		int skills_length = readShort(buf);
		for (int i = 0; i < skills_length; i++) {
			skills.add((com.game.skill.bean.SkillInfo)readBean(buf, com.game.skill.bean.SkillInfo.class));
		}
		return true;
	}
	
	/**
	 * get 默认技能模板Id
	 * @return 
	 */
	public int getDefaultSkill(){
		return defaultSkill;
	}
	
	/**
	 * set 默认技能模板Id
	 */
	public void setDefaultSkill(int defaultSkill){
		this.defaultSkill = defaultSkill;
	}
	
	/**
	 * get 技能信息列表
	 * @return 
	 */
	public List<com.game.skill.bean.SkillInfo> getSkills(){
		return skills;
	}
	
	/**
	 * set 技能信息列表
	 */
	public void setSkills(List<com.game.skill.bean.SkillInfo> skills){
		this.skills = skills;
	}
	
	
	@Override
	public int getId() {
		return 163117;
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
		//默认技能模板Id
		buf.append("defaultSkill:" + defaultSkill +",");
		//技能信息列表
		buf.append("skills:{");
		for (int i = 0; i < skills.size(); i++) {
			buf.append(skills.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}