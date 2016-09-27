package com.game.horse.message;

import com.game.horse.bean.HorseSkillInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 幸运连珠（使用元宝）消息
 */
public class ReshorseLuckyPenteMessage extends Message{

	//已使用连珠和拉杆总次数
	private byte num;
	
	//单个坐骑技能
	private HorseSkillInfo skillinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//已使用连珠和拉杆总次数
		writeByte(buf, this.num);
		//单个坐骑技能
		writeBean(buf, this.skillinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//已使用连珠和拉杆总次数
		this.num = readByte(buf);
		//单个坐骑技能
		this.skillinfo = (HorseSkillInfo)readBean(buf, HorseSkillInfo.class);
		return true;
	}
	
	/**
	 * get 已使用连珠和拉杆总次数
	 * @return 
	 */
	public byte getNum(){
		return num;
	}
	
	/**
	 * set 已使用连珠和拉杆总次数
	 */
	public void setNum(byte num){
		this.num = num;
	}
	
	/**
	 * get 单个坐骑技能
	 * @return 
	 */
	public HorseSkillInfo getSkillinfo(){
		return skillinfo;
	}
	
	/**
	 * set 单个坐骑技能
	 */
	public void setSkillinfo(HorseSkillInfo skillinfo){
		this.skillinfo = skillinfo;
	}
	
	
	@Override
	public int getId() {
		return 126107;
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
		//已使用连珠和拉杆总次数
		buf.append("num:" + num +",");
		//单个坐骑技能
		if(this.skillinfo!=null) buf.append("skillinfo:" + skillinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}