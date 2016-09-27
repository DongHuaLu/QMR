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
 * 发送坐骑技能列表消息
 */
public class ResSkillInfoMessage extends Message{

	//坐骑技能列表
	private List<HorseSkillInfo> skillinfolist = new ArrayList<HorseSkillInfo>();
	//拉杆需要的金币
	private int money;
	
	//连珠需要的元宝
	private int yuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//坐骑技能列表
		writeShort(buf, skillinfolist.size());
		for (int i = 0; i < skillinfolist.size(); i++) {
			writeBean(buf, skillinfolist.get(i));
		}
		//拉杆需要的金币
		writeInt(buf, this.money);
		//连珠需要的元宝
		writeInt(buf, this.yuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//坐骑技能列表
		int skillinfolist_length = readShort(buf);
		for (int i = 0; i < skillinfolist_length; i++) {
			skillinfolist.add((HorseSkillInfo)readBean(buf, HorseSkillInfo.class));
		}
		//拉杆需要的金币
		this.money = readInt(buf);
		//连珠需要的元宝
		this.yuanbao = readInt(buf);
		return true;
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
	 * get 拉杆需要的金币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 拉杆需要的金币
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	/**
	 * get 连珠需要的元宝
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 连珠需要的元宝
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	
	@Override
	public int getId() {
		return 126114;
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
		//坐骑技能列表
		buf.append("skillinfolist:{");
		for (int i = 0; i < skillinfolist.size(); i++) {
			buf.append(skillinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//拉杆需要的金币
		buf.append("money:" + money +",");
		//连珠需要的元宝
		buf.append("yuanbao:" + yuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}