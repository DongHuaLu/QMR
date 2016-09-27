package com.game.skill.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 技能信息类
 */
public class SkillInfo extends Bean {

	//技能Id
	private long skillId;
	
	//技能模板Id
	private int skillModelId;
	
	//技能等级
	private int skillLevel;
	
	//加成等级
	private List<SkillLevelInfo> skillAddLevels = new ArrayList<SkillLevelInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能Id
		writeLong(buf, this.skillId);
		//技能模板Id
		writeInt(buf, this.skillModelId);
		//技能等级
		writeInt(buf, this.skillLevel);
		//加成等级
		writeShort(buf, skillAddLevels.size());
		for (int i = 0; i < skillAddLevels.size(); i++) {
			writeBean(buf, skillAddLevels.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能Id
		this.skillId = readLong(buf);
		//技能模板Id
		this.skillModelId = readInt(buf);
		//技能等级
		this.skillLevel = readInt(buf);
		//加成等级
		int skillAddLevels_length = readShort(buf);
		for (int i = 0; i < skillAddLevels_length; i++) {
			skillAddLevels.add((SkillLevelInfo)readBean(buf, SkillLevelInfo.class));
		}
		return true;
	}
	
	/**
	 * get 技能Id
	 * @return 
	 */
	public long getSkillId(){
		return skillId;
	}
	
	/**
	 * set 技能Id
	 */
	public void setSkillId(long skillId){
		this.skillId = skillId;
	}
	
	/**
	 * get 技能模板Id
	 * @return 
	 */
	public int getSkillModelId(){
		return skillModelId;
	}
	
	/**
	 * set 技能模板Id
	 */
	public void setSkillModelId(int skillModelId){
		this.skillModelId = skillModelId;
	}
	
	/**
	 * get 技能等级
	 * @return 
	 */
	public int getSkillLevel(){
		return skillLevel;
	}
	
	/**
	 * set 技能等级
	 */
	public void setSkillLevel(int skillLevel){
		this.skillLevel = skillLevel;
	}
	
	/**
	 * get 加成等级
	 * @return 
	 */
	public List<SkillLevelInfo> getSkillAddLevels(){
		return skillAddLevels;
	}
	
	/**
	 * set 加成等级
	 */
	public void setSkillAddLevels(List<SkillLevelInfo> skillAddLevels){
		this.skillAddLevels = skillAddLevels;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//技能Id
		buf.append("skillId:" + skillId +",");
		//技能模板Id
		buf.append("skillModelId:" + skillModelId +",");
		//技能等级
		buf.append("skillLevel:" + skillLevel +",");
		//加成等级
		buf.append("skillAddLevels:{");
		for (int i = 0; i < skillAddLevels.size(); i++) {
			buf.append(skillAddLevels.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}