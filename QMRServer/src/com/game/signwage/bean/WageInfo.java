package com.game.signwage.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 工资消息
 */
public class WageInfo extends Bean {

	//开服时间
	private String svrstarttime;
	
	//当前月
	private int curmonth;
	
	//今日累计在线时间（秒）
	private int daytime;
	
	//本月累计在线时间（秒）
	private int monthtime;
	
	//本月领取状态(1领取，0未领取)
	private byte curstatus;
	
	//上月领取状态（1领取，0未领取）
	private byte oldstatus;
	
	//本月累计工资
	private int curwage;
	
	//上月累计工资
	private int oldwage;
	
	//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
	private List<Integer> ernie = new ArrayList<Integer>();
	//已经领取的道具奖励
	private List<com.game.spirittree.bean.FruitRewardinfo> fruitRewardinfo = new ArrayList<com.game.spirittree.bean.FruitRewardinfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//开服时间
		writeString(buf, this.svrstarttime);
		//当前月
		writeInt(buf, this.curmonth);
		//今日累计在线时间（秒）
		writeInt(buf, this.daytime);
		//本月累计在线时间（秒）
		writeInt(buf, this.monthtime);
		//本月领取状态(1领取，0未领取)
		writeByte(buf, this.curstatus);
		//上月领取状态（1领取，0未领取）
		writeByte(buf, this.oldstatus);
		//本月累计工资
		writeInt(buf, this.curwage);
		//上月累计工资
		writeInt(buf, this.oldwage);
		//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		writeShort(buf, ernie.size());
		for (int i = 0; i < ernie.size(); i++) {
			writeInt(buf, ernie.get(i));
		}
		//已经领取的道具奖励
		writeShort(buf, fruitRewardinfo.size());
		for (int i = 0; i < fruitRewardinfo.size(); i++) {
			writeBean(buf, fruitRewardinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//开服时间
		this.svrstarttime = readString(buf);
		//当前月
		this.curmonth = readInt(buf);
		//今日累计在线时间（秒）
		this.daytime = readInt(buf);
		//本月累计在线时间（秒）
		this.monthtime = readInt(buf);
		//本月领取状态(1领取，0未领取)
		this.curstatus = readByte(buf);
		//上月领取状态（1领取，0未领取）
		this.oldstatus = readByte(buf);
		//本月累计工资
		this.curwage = readInt(buf);
		//上月累计工资
		this.oldwage = readInt(buf);
		//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		int ernie_length = readShort(buf);
		for (int i = 0; i < ernie_length; i++) {
			ernie.add(readInt(buf));
		}
		//已经领取的道具奖励
		int fruitRewardinfo_length = readShort(buf);
		for (int i = 0; i < fruitRewardinfo_length; i++) {
			fruitRewardinfo.add((com.game.spirittree.bean.FruitRewardinfo)readBean(buf, com.game.spirittree.bean.FruitRewardinfo.class));
		}
		return true;
	}
	
	/**
	 * get 开服时间
	 * @return 
	 */
	public String getSvrstarttime(){
		return svrstarttime;
	}
	
	/**
	 * set 开服时间
	 */
	public void setSvrstarttime(String svrstarttime){
		this.svrstarttime = svrstarttime;
	}
	
	/**
	 * get 当前月
	 * @return 
	 */
	public int getCurmonth(){
		return curmonth;
	}
	
	/**
	 * set 当前月
	 */
	public void setCurmonth(int curmonth){
		this.curmonth = curmonth;
	}
	
	/**
	 * get 今日累计在线时间（秒）
	 * @return 
	 */
	public int getDaytime(){
		return daytime;
	}
	
	/**
	 * set 今日累计在线时间（秒）
	 */
	public void setDaytime(int daytime){
		this.daytime = daytime;
	}
	
	/**
	 * get 本月累计在线时间（秒）
	 * @return 
	 */
	public int getMonthtime(){
		return monthtime;
	}
	
	/**
	 * set 本月累计在线时间（秒）
	 */
	public void setMonthtime(int monthtime){
		this.monthtime = monthtime;
	}
	
	/**
	 * get 本月领取状态(1领取，0未领取)
	 * @return 
	 */
	public byte getCurstatus(){
		return curstatus;
	}
	
	/**
	 * set 本月领取状态(1领取，0未领取)
	 */
	public void setCurstatus(byte curstatus){
		this.curstatus = curstatus;
	}
	
	/**
	 * get 上月领取状态（1领取，0未领取）
	 * @return 
	 */
	public byte getOldstatus(){
		return oldstatus;
	}
	
	/**
	 * set 上月领取状态（1领取，0未领取）
	 */
	public void setOldstatus(byte oldstatus){
		this.oldstatus = oldstatus;
	}
	
	/**
	 * get 本月累计工资
	 * @return 
	 */
	public int getCurwage(){
		return curwage;
	}
	
	/**
	 * set 本月累计工资
	 */
	public void setCurwage(int curwage){
		this.curwage = curwage;
	}
	
	/**
	 * get 上月累计工资
	 * @return 
	 */
	public int getOldwage(){
		return oldwage;
	}
	
	/**
	 * set 上月累计工资
	 */
	public void setOldwage(int oldwage){
		this.oldwage = oldwage;
	}
	
	/**
	 * get 摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
	 * @return 
	 */
	public List<Integer> getErnie(){
		return ernie;
	}
	
	/**
	 * set 摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
	 */
	public void setErnie(List<Integer> ernie){
		this.ernie = ernie;
	}
	
	/**
	 * get 已经领取的道具奖励
	 * @return 
	 */
	public List<com.game.spirittree.bean.FruitRewardinfo> getFruitRewardinfo(){
		return fruitRewardinfo;
	}
	
	/**
	 * set 已经领取的道具奖励
	 */
	public void setFruitRewardinfo(List<com.game.spirittree.bean.FruitRewardinfo> fruitRewardinfo){
		this.fruitRewardinfo = fruitRewardinfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//开服时间
		if(this.svrstarttime!=null) buf.append("svrstarttime:" + svrstarttime.toString() +",");
		//当前月
		buf.append("curmonth:" + curmonth +",");
		//今日累计在线时间（秒）
		buf.append("daytime:" + daytime +",");
		//本月累计在线时间（秒）
		buf.append("monthtime:" + monthtime +",");
		//本月领取状态(1领取，0未领取)
		buf.append("curstatus:" + curstatus +",");
		//上月领取状态（1领取，0未领取）
		buf.append("oldstatus:" + oldstatus +",");
		//本月累计工资
		buf.append("curwage:" + curwage +",");
		//上月累计工资
		buf.append("oldwage:" + oldwage +",");
		//摇奖列表（0可摇奖，1已经摇奖，2条件未达到）
		buf.append("ernie:{");
		for (int i = 0; i < ernie.size(); i++) {
			buf.append(ernie.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//已经领取的道具奖励
		buf.append("fruitRewardinfo:{");
		for (int i = 0; i < fruitRewardinfo.size(); i++) {
			buf.append(fruitRewardinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}