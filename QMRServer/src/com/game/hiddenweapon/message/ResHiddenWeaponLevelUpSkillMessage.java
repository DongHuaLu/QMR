package com.game.hiddenweapon.message;

import com.game.hiddenweapon.bean.HiddenWeaponSkillInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 学习暗器技能之后的技能列表消息
 */
public class ResHiddenWeaponLevelUpSkillMessage extends Message{

	//新学的技能所在的格子编号,从1开始
	private byte index;
	
	//技能
	private List<HiddenWeaponSkillInfo> skills = new ArrayList<HiddenWeaponSkillInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新学的技能所在的格子编号,从1开始
		writeByte(buf, this.index);
		//技能
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
		//新学的技能所在的格子编号,从1开始
		this.index = readByte(buf);
		//技能
		int skills_length = readShort(buf);
		for (int i = 0; i < skills_length; i++) {
			skills.add((HiddenWeaponSkillInfo)readBean(buf, HiddenWeaponSkillInfo.class));
		}
		return true;
	}
	
	/**
	 * get 新学的技能所在的格子编号,从1开始
	 * @return 
	 */
	public byte getIndex(){
		return index;
	}
	
	/**
	 * set 新学的技能所在的格子编号,从1开始
	 */
	public void setIndex(byte index){
		this.index = index;
	}
	
	/**
	 * get 技能
	 * @return 
	 */
	public List<HiddenWeaponSkillInfo> getSkills(){
		return skills;
	}
	
	/**
	 * set 技能
	 */
	public void setSkills(List<HiddenWeaponSkillInfo> skills){
		this.skills = skills;
	}
	
	
	@Override
	public int getId() {
		return 162107;
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
		//新学的技能所在的格子编号,从1开始
		buf.append("index:" + index +",");
		//技能
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