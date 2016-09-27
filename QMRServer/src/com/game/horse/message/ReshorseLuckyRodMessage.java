package com.game.horse.message;

import com.game.horse.bean.HorseSkillInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 幸运拉杆（使用金币）消息
 */
public class ReshorseLuckyRodMessage extends Message{

	//已使用连珠和拉杆总次数
	private byte num;
	
	//坐骑技能列表
	private List<HorseSkillInfo> skillinfolist = new ArrayList<HorseSkillInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//已使用连珠和拉杆总次数
		writeByte(buf, this.num);
		//坐骑技能列表
		writeShort(buf, skillinfolist.size());
		for (int i = 0; i < skillinfolist.size(); i++) {
			writeBean(buf, skillinfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//已使用连珠和拉杆总次数
		this.num = readByte(buf);
		//坐骑技能列表
		int skillinfolist_length = readShort(buf);
		for (int i = 0; i < skillinfolist_length; i++) {
			skillinfolist.add((HorseSkillInfo)readBean(buf, HorseSkillInfo.class));
		}
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
	
	
	@Override
	public int getId() {
		return 126106;
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
		//坐骑技能列表
		buf.append("skillinfolist:{");
		for (int i = 0; i < skillinfolist.size(); i++) {
			buf.append(skillinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}