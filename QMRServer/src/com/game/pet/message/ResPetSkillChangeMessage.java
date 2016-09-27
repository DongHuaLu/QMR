package com.game.pet.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宠物技能变更消息
 */
public class ResPetSkillChangeMessage extends Message{

	//宠物Id
	private long petId;
	
	//技能列表
	private List<com.game.skill.bean.SkillInfo> skillInfos = new ArrayList<com.game.skill.bean.SkillInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//技能列表
		writeShort(buf, skillInfos.size());
		for (int i = 0; i < skillInfos.size(); i++) {
			writeBean(buf, skillInfos.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//技能列表
		int skillInfos_length = readShort(buf);
		for (int i = 0; i < skillInfos_length; i++) {
			skillInfos.add((com.game.skill.bean.SkillInfo)readBean(buf, com.game.skill.bean.SkillInfo.class));
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
	
	
	@Override
	public int getId() {
		return 110110;
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
		//宠物Id
		buf.append("petId:" + petId +",");
		//技能列表
		buf.append("skillInfos:{");
		for (int i = 0; i < skillInfos.size(); i++) {
			buf.append(skillInfos.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}