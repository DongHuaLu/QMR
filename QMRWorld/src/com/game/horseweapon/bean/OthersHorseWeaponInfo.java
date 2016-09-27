package com.game.horseweapon.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查看他人骑乘兵器详细信息
 */
public class OthersHorseWeaponInfo extends Bean {

	//当前最高骑乘兵器阶层
	private short layer;
	
	//当前使用的骑乘兵器阶层
	private short curlayer;
	
	//技能
	private List<HorseWeaponSkillInfo> skills = new ArrayList<HorseWeaponSkillInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前最高骑乘兵器阶层
		writeShort(buf, this.layer);
		//当前使用的骑乘兵器阶层
		writeShort(buf, this.curlayer);
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
		//当前最高骑乘兵器阶层
		this.layer = readShort(buf);
		//当前使用的骑乘兵器阶层
		this.curlayer = readShort(buf);
		//技能
		int skills_length = readShort(buf);
		for (int i = 0; i < skills_length; i++) {
			skills.add((HorseWeaponSkillInfo)readBean(buf, HorseWeaponSkillInfo.class));
		}
		return true;
	}
	
	/**
	 * get 当前最高骑乘兵器阶层
	 * @return 
	 */
	public short getLayer(){
		return layer;
	}
	
	/**
	 * set 当前最高骑乘兵器阶层
	 */
	public void setLayer(short layer){
		this.layer = layer;
	}
	
	/**
	 * get 当前使用的骑乘兵器阶层
	 * @return 
	 */
	public short getCurlayer(){
		return curlayer;
	}
	
	/**
	 * set 当前使用的骑乘兵器阶层
	 */
	public void setCurlayer(short curlayer){
		this.curlayer = curlayer;
	}
	
	/**
	 * get 技能
	 * @return 
	 */
	public List<HorseWeaponSkillInfo> getSkills(){
		return skills;
	}
	
	/**
	 * set 技能
	 */
	public void setSkills(List<HorseWeaponSkillInfo> skills){
		this.skills = skills;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//当前最高骑乘兵器阶层
		buf.append("layer:" + layer +",");
		//当前使用的骑乘兵器阶层
		buf.append("curlayer:" + curlayer +",");
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