package com.game.horse.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查看他人坐骑详细信息
 */
public class OthersHorseInfo extends Bean {

	//当前最高坐骑阶层
	private short layer;
	
	//当前使用的坐骑阶层
	private short curlayer;
	
	//是否骑乘，1骑乘，0未骑乘
	private byte status;
	
	//当前坐骑等级
	private short level;
	
	//坐骑技能列表
	private List<HorseSkillInfo> skillinfolist = new ArrayList<HorseSkillInfo>();
	//坐骑装备列表
	private List<com.game.equip.bean.EquipInfo> horseequipinfo = new ArrayList<com.game.equip.bean.EquipInfo>();
	//潜能点
	private int potential;
	
	//已加炼骨丹数量
	private int mixingbone;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前最高坐骑阶层
		writeShort(buf, this.layer);
		//当前使用的坐骑阶层
		writeShort(buf, this.curlayer);
		//是否骑乘，1骑乘，0未骑乘
		writeByte(buf, this.status);
		//当前坐骑等级
		writeShort(buf, this.level);
		//坐骑技能列表
		writeShort(buf, skillinfolist.size());
		for (int i = 0; i < skillinfolist.size(); i++) {
			writeBean(buf, skillinfolist.get(i));
		}
		//坐骑装备列表
		writeShort(buf, horseequipinfo.size());
		for (int i = 0; i < horseequipinfo.size(); i++) {
			writeBean(buf, horseequipinfo.get(i));
		}
		//潜能点
		writeInt(buf, this.potential);
		//已加炼骨丹数量
		writeInt(buf, this.mixingbone);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前最高坐骑阶层
		this.layer = readShort(buf);
		//当前使用的坐骑阶层
		this.curlayer = readShort(buf);
		//是否骑乘，1骑乘，0未骑乘
		this.status = readByte(buf);
		//当前坐骑等级
		this.level = readShort(buf);
		//坐骑技能列表
		int skillinfolist_length = readShort(buf);
		for (int i = 0; i < skillinfolist_length; i++) {
			skillinfolist.add((HorseSkillInfo)readBean(buf, HorseSkillInfo.class));
		}
		//坐骑装备列表
		int horseequipinfo_length = readShort(buf);
		for (int i = 0; i < horseequipinfo_length; i++) {
			horseequipinfo.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//潜能点
		this.potential = readInt(buf);
		//已加炼骨丹数量
		this.mixingbone = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前最高坐骑阶层
	 * @return 
	 */
	public short getLayer(){
		return layer;
	}
	
	/**
	 * set 当前最高坐骑阶层
	 */
	public void setLayer(short layer){
		this.layer = layer;
	}
	
	/**
	 * get 当前使用的坐骑阶层
	 * @return 
	 */
	public short getCurlayer(){
		return curlayer;
	}
	
	/**
	 * set 当前使用的坐骑阶层
	 */
	public void setCurlayer(short curlayer){
		this.curlayer = curlayer;
	}
	
	/**
	 * get 是否骑乘，1骑乘，0未骑乘
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 是否骑乘，1骑乘，0未骑乘
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	/**
	 * get 当前坐骑等级
	 * @return 
	 */
	public short getLevel(){
		return level;
	}
	
	/**
	 * set 当前坐骑等级
	 */
	public void setLevel(short level){
		this.level = level;
	}
	
	/**
	 * get 坐骑技能列表
	 * @return 
	 */
	public List<HorseSkillInfo> getSkillinfolist(){
		return skillinfolist;
	}
	
	/**
	 * set 坐骑技能列表
	 */
	public void setSkillinfolist(List<HorseSkillInfo> skillinfolist){
		this.skillinfolist = skillinfolist;
	}
	
	/**
	 * get 坐骑装备列表
	 * @return 
	 */
	public List<com.game.equip.bean.EquipInfo> getHorseequipinfo(){
		return horseequipinfo;
	}
	
	/**
	 * set 坐骑装备列表
	 */
	public void setHorseequipinfo(List<com.game.equip.bean.EquipInfo> horseequipinfo){
		this.horseequipinfo = horseequipinfo;
	}
	
	/**
	 * get 潜能点
	 * @return 
	 */
	public int getPotential(){
		return potential;
	}
	
	/**
	 * set 潜能点
	 */
	public void setPotential(int potential){
		this.potential = potential;
	}
	
	/**
	 * get 已加炼骨丹数量
	 * @return 
	 */
	public int getMixingbone(){
		return mixingbone;
	}
	
	/**
	 * set 已加炼骨丹数量
	 */
	public void setMixingbone(int mixingbone){
		this.mixingbone = mixingbone;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//当前最高坐骑阶层
		buf.append("layer:" + layer +",");
		//当前使用的坐骑阶层
		buf.append("curlayer:" + curlayer +",");
		//是否骑乘，1骑乘，0未骑乘
		buf.append("status:" + status +",");
		//当前坐骑等级
		buf.append("level:" + level +",");
		//坐骑技能列表
		buf.append("skillinfolist:{");
		for (int i = 0; i < skillinfolist.size(); i++) {
			buf.append(skillinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//坐骑装备列表
		buf.append("horseequipinfo:{");
		for (int i = 0; i < horseequipinfo.size(); i++) {
			buf.append(horseequipinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//潜能点
		buf.append("potential:" + potential +",");
		//已加炼骨丹数量
		buf.append("mixingbone:" + mixingbone +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}