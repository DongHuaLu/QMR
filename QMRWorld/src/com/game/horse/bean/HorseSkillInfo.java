package com.game.horse.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 坐骑单个技能信息
 */
public class HorseSkillInfo extends Bean {

	//技能等级
	private short skilllevel;
	
	//技能模板Id
	private int skillmodelid;
	
	//技能经验
	private int skillexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//技能等级
		writeShort(buf, this.skilllevel);
		//技能模板Id
		writeInt(buf, this.skillmodelid);
		//技能经验
		writeInt(buf, this.skillexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//技能等级
		this.skilllevel = readShort(buf);
		//技能模板Id
		this.skillmodelid = readInt(buf);
		//技能经验
		this.skillexp = readInt(buf);
		return true;
	}
	
	/**
	 * get 技能等级
	 * @return 
	 */
	public short getSkilllevel(){
		return skilllevel;
	}
	
	/**
	 * set 技能等级
	 */
	public void setSkilllevel(short skilllevel){
		this.skilllevel = skilllevel;
	}
	
	/**
	 * get 技能模板Id
	 * @return 
	 */
	public int getSkillmodelid(){
		return skillmodelid;
	}
	
	/**
	 * set 技能模板Id
	 */
	public void setSkillmodelid(int skillmodelid){
		this.skillmodelid = skillmodelid;
	}
	
	/**
	 * get 技能经验
	 * @return 
	 */
	public int getSkillexp(){
		return skillexp;
	}
	
	/**
	 * set 技能经验
	 */
	public void setSkillexp(int skillexp){
		this.skillexp = skillexp;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//技能等级
		buf.append("skilllevel:" + skilllevel +",");
		//技能模板Id
		buf.append("skillmodelid:" + skillmodelid +",");
		//技能经验
		buf.append("skillexp:" + skillexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}