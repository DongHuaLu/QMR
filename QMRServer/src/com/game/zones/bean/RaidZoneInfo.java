package com.game.zones.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本扫荡信息
 */
public class RaidZoneInfo extends Bean {

	//副本编号
	private int zoneid;
	
	//最快通关时间（时间秒）
	private int throughtime;
	
	//当前副本排名
	private int ranking;
	
	//手动扫荡次数
	private byte manualmun;
	
	//自动扫荡次数
	private byte automun;
	
	//星星数量，评价（0表示没通关）
	private byte starnum;
	
	//全服最快通关时间（时间秒）
	private int fulltime;
	
	//全服最快通关者名字
	private String fullname;
	
	//今日是否通关，2是通关，1是未通关，0是未进入
	private byte clearance;
	
	//战魂搜索次数
	private int zhanhunnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本编号
		writeInt(buf, this.zoneid);
		//最快通关时间（时间秒）
		writeInt(buf, this.throughtime);
		//当前副本排名
		writeInt(buf, this.ranking);
		//手动扫荡次数
		writeByte(buf, this.manualmun);
		//自动扫荡次数
		writeByte(buf, this.automun);
		//星星数量，评价（0表示没通关）
		writeByte(buf, this.starnum);
		//全服最快通关时间（时间秒）
		writeInt(buf, this.fulltime);
		//全服最快通关者名字
		writeString(buf, this.fullname);
		//今日是否通关，2是通关，1是未通关，0是未进入
		writeByte(buf, this.clearance);
		//战魂搜索次数
		writeInt(buf, this.zhanhunnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本编号
		this.zoneid = readInt(buf);
		//最快通关时间（时间秒）
		this.throughtime = readInt(buf);
		//当前副本排名
		this.ranking = readInt(buf);
		//手动扫荡次数
		this.manualmun = readByte(buf);
		//自动扫荡次数
		this.automun = readByte(buf);
		//星星数量，评价（0表示没通关）
		this.starnum = readByte(buf);
		//全服最快通关时间（时间秒）
		this.fulltime = readInt(buf);
		//全服最快通关者名字
		this.fullname = readString(buf);
		//今日是否通关，2是通关，1是未通关，0是未进入
		this.clearance = readByte(buf);
		//战魂搜索次数
		this.zhanhunnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 副本编号
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本编号
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 最快通关时间（时间秒）
	 * @return 
	 */
	public int getThroughtime(){
		return throughtime;
	}
	
	/**
	 * set 最快通关时间（时间秒）
	 */
	public void setThroughtime(int throughtime){
		this.throughtime = throughtime;
	}
	
	/**
	 * get 当前副本排名
	 * @return 
	 */
	public int getRanking(){
		return ranking;
	}
	
	/**
	 * set 当前副本排名
	 */
	public void setRanking(int ranking){
		this.ranking = ranking;
	}
	
	/**
	 * get 手动扫荡次数
	 * @return 
	 */
	public byte getManualmun(){
		return manualmun;
	}
	
	/**
	 * set 手动扫荡次数
	 */
	public void setManualmun(byte manualmun){
		this.manualmun = manualmun;
	}
	
	/**
	 * get 自动扫荡次数
	 * @return 
	 */
	public byte getAutomun(){
		return automun;
	}
	
	/**
	 * set 自动扫荡次数
	 */
	public void setAutomun(byte automun){
		this.automun = automun;
	}
	
	/**
	 * get 星星数量，评价（0表示没通关）
	 * @return 
	 */
	public byte getStarnum(){
		return starnum;
	}
	
	/**
	 * set 星星数量，评价（0表示没通关）
	 */
	public void setStarnum(byte starnum){
		this.starnum = starnum;
	}
	
	/**
	 * get 全服最快通关时间（时间秒）
	 * @return 
	 */
	public int getFulltime(){
		return fulltime;
	}
	
	/**
	 * set 全服最快通关时间（时间秒）
	 */
	public void setFulltime(int fulltime){
		this.fulltime = fulltime;
	}
	
	/**
	 * get 全服最快通关者名字
	 * @return 
	 */
	public String getFullname(){
		return fullname;
	}
	
	/**
	 * set 全服最快通关者名字
	 */
	public void setFullname(String fullname){
		this.fullname = fullname;
	}
	
	/**
	 * get 今日是否通关，2是通关，1是未通关，0是未进入
	 * @return 
	 */
	public byte getClearance(){
		return clearance;
	}
	
	/**
	 * set 今日是否通关，2是通关，1是未通关，0是未进入
	 */
	public void setClearance(byte clearance){
		this.clearance = clearance;
	}
	
	/**
	 * get 战魂搜索次数
	 * @return 
	 */
	public int getZhanhunnum(){
		return zhanhunnum;
	}
	
	/**
	 * set 战魂搜索次数
	 */
	public void setZhanhunnum(int zhanhunnum){
		this.zhanhunnum = zhanhunnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//副本编号
		buf.append("zoneid:" + zoneid +",");
		//最快通关时间（时间秒）
		buf.append("throughtime:" + throughtime +",");
		//当前副本排名
		buf.append("ranking:" + ranking +",");
		//手动扫荡次数
		buf.append("manualmun:" + manualmun +",");
		//自动扫荡次数
		buf.append("automun:" + automun +",");
		//星星数量，评价（0表示没通关）
		buf.append("starnum:" + starnum +",");
		//全服最快通关时间（时间秒）
		buf.append("fulltime:" + fulltime +",");
		//全服最快通关者名字
		if(this.fullname!=null) buf.append("fullname:" + fullname.toString() +",");
		//今日是否通关，2是通关，1是未通关，0是未进入
		buf.append("clearance:" + clearance +",");
		//战魂搜索次数
		buf.append("zhanhunnum:" + zhanhunnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}