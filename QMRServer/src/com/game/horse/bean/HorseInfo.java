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
 * 坐骑信息
 */
public class HorseInfo extends Bean {

	//当前最高坐骑阶层
	private short layer;
	
	//当前使用的坐骑阶层
	private short curlayer;
	
	//是否骑乘，1骑乘，0未骑乘
	private byte status;
	
	//坐骑技能列表
	private List<HorseSkillInfo> skillinfolist = new ArrayList<HorseSkillInfo>();
	//坐骑装备列表
	private List<com.game.equip.bean.EquipInfo> horseequipinfo = new ArrayList<com.game.equip.bean.EquipInfo>();
	//当前祝福值
	private int dayblessvalue;
	
	//历史祝福值
	private int hisblessvalue;
	
	//当前进阶次数
	private short dayupnum;
	
	//历史进阶次数
	private short hisupnum;
	
	//已使用连珠和拉杆总次数
	private byte boxnum;
	
	//拉杆CD剩余时间（秒）
	private int boxcdtime;
	
	//清除CD需要的元宝数量
	private int cdtimeyuanbao;
	
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
		//当前祝福值
		writeInt(buf, this.dayblessvalue);
		//历史祝福值
		writeInt(buf, this.hisblessvalue);
		//当前进阶次数
		writeShort(buf, this.dayupnum);
		//历史进阶次数
		writeShort(buf, this.hisupnum);
		//已使用连珠和拉杆总次数
		writeByte(buf, this.boxnum);
		//拉杆CD剩余时间（秒）
		writeInt(buf, this.boxcdtime);
		//清除CD需要的元宝数量
		writeInt(buf, this.cdtimeyuanbao);
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
		//当前祝福值
		this.dayblessvalue = readInt(buf);
		//历史祝福值
		this.hisblessvalue = readInt(buf);
		//当前进阶次数
		this.dayupnum = readShort(buf);
		//历史进阶次数
		this.hisupnum = readShort(buf);
		//已使用连珠和拉杆总次数
		this.boxnum = readByte(buf);
		//拉杆CD剩余时间（秒）
		this.boxcdtime = readInt(buf);
		//清除CD需要的元宝数量
		this.cdtimeyuanbao = readInt(buf);
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
	 * get 当前祝福值
	 * @return 
	 */
	public int getDayblessvalue(){
		return dayblessvalue;
	}
	
	/**
	 * set 当前祝福值
	 */
	public void setDayblessvalue(int dayblessvalue){
		this.dayblessvalue = dayblessvalue;
	}
	
	/**
	 * get 历史祝福值
	 * @return 
	 */
	public int getHisblessvalue(){
		return hisblessvalue;
	}
	
	/**
	 * set 历史祝福值
	 */
	public void setHisblessvalue(int hisblessvalue){
		this.hisblessvalue = hisblessvalue;
	}
	
	/**
	 * get 当前进阶次数
	 * @return 
	 */
	public short getDayupnum(){
		return dayupnum;
	}
	
	/**
	 * set 当前进阶次数
	 */
	public void setDayupnum(short dayupnum){
		this.dayupnum = dayupnum;
	}
	
	/**
	 * get 历史进阶次数
	 * @return 
	 */
	public short getHisupnum(){
		return hisupnum;
	}
	
	/**
	 * set 历史进阶次数
	 */
	public void setHisupnum(short hisupnum){
		this.hisupnum = hisupnum;
	}
	
	/**
	 * get 已使用连珠和拉杆总次数
	 * @return 
	 */
	public byte getBoxnum(){
		return boxnum;
	}
	
	/**
	 * set 已使用连珠和拉杆总次数
	 */
	public void setBoxnum(byte boxnum){
		this.boxnum = boxnum;
	}
	
	/**
	 * get 拉杆CD剩余时间（秒）
	 * @return 
	 */
	public int getBoxcdtime(){
		return boxcdtime;
	}
	
	/**
	 * set 拉杆CD剩余时间（秒）
	 */
	public void setBoxcdtime(int boxcdtime){
		this.boxcdtime = boxcdtime;
	}
	
	/**
	 * get 清除CD需要的元宝数量
	 * @return 
	 */
	public int getCdtimeyuanbao(){
		return cdtimeyuanbao;
	}
	
	/**
	 * set 清除CD需要的元宝数量
	 */
	public void setCdtimeyuanbao(int cdtimeyuanbao){
		this.cdtimeyuanbao = cdtimeyuanbao;
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
		//当前祝福值
		buf.append("dayblessvalue:" + dayblessvalue +",");
		//历史祝福值
		buf.append("hisblessvalue:" + hisblessvalue +",");
		//当前进阶次数
		buf.append("dayupnum:" + dayupnum +",");
		//历史进阶次数
		buf.append("hisupnum:" + hisupnum +",");
		//已使用连珠和拉杆总次数
		buf.append("boxnum:" + boxnum +",");
		//拉杆CD剩余时间（秒）
		buf.append("boxcdtime:" + boxcdtime +",");
		//清除CD需要的元宝数量
		buf.append("cdtimeyuanbao:" + cdtimeyuanbao +",");
		//潜能点
		buf.append("potential:" + potential +",");
		//已加炼骨丹数量
		buf.append("mixingbone:" + mixingbone +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}