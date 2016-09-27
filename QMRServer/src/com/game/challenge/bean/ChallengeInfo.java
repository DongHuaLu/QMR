package com.game.challenge.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 挑战信息
 */
public class ChallengeInfo extends Bean {

	//秦王战役副本剩余次数
	private int zonesnum;
	
	//BOSS刷新时间
	private String bosstime;
	
	//攻城战开始时间
	private String gongchengtime;
	
	//领地战开始时间
	private String lingditime;
	
	//地宫寻宝剩余次数
	private String epalacenum;
	
	//挑战校场剩余次数
	private int jiaochangnum;
	
	//双倍收益时间
	private String doubletime;
	
	//迷宫开始时间
	private String mazetime;
	
	//比武岛时间
	private String biwudaotime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//秦王战役副本剩余次数
		writeInt(buf, this.zonesnum);
		//BOSS刷新时间
		writeString(buf, this.bosstime);
		//攻城战开始时间
		writeString(buf, this.gongchengtime);
		//领地战开始时间
		writeString(buf, this.lingditime);
		//地宫寻宝剩余次数
		writeString(buf, this.epalacenum);
		//挑战校场剩余次数
		writeInt(buf, this.jiaochangnum);
		//双倍收益时间
		writeString(buf, this.doubletime);
		//迷宫开始时间
		writeString(buf, this.mazetime);
		//比武岛时间
		writeString(buf, this.biwudaotime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//秦王战役副本剩余次数
		this.zonesnum = readInt(buf);
		//BOSS刷新时间
		this.bosstime = readString(buf);
		//攻城战开始时间
		this.gongchengtime = readString(buf);
		//领地战开始时间
		this.lingditime = readString(buf);
		//地宫寻宝剩余次数
		this.epalacenum = readString(buf);
		//挑战校场剩余次数
		this.jiaochangnum = readInt(buf);
		//双倍收益时间
		this.doubletime = readString(buf);
		//迷宫开始时间
		this.mazetime = readString(buf);
		//比武岛时间
		this.biwudaotime = readString(buf);
		return true;
	}
	
	/**
	 * get 秦王战役副本剩余次数
	 * @return 
	 */
	public int getZonesnum(){
		return zonesnum;
	}
	
	/**
	 * set 秦王战役副本剩余次数
	 */
	public void setZonesnum(int zonesnum){
		this.zonesnum = zonesnum;
	}
	
	/**
	 * get BOSS刷新时间
	 * @return 
	 */
	public String getBosstime(){
		return bosstime;
	}
	
	/**
	 * set BOSS刷新时间
	 */
	public void setBosstime(String bosstime){
		this.bosstime = bosstime;
	}
	
	/**
	 * get 攻城战开始时间
	 * @return 
	 */
	public String getGongchengtime(){
		return gongchengtime;
	}
	
	/**
	 * set 攻城战开始时间
	 */
	public void setGongchengtime(String gongchengtime){
		this.gongchengtime = gongchengtime;
	}
	
	/**
	 * get 领地战开始时间
	 * @return 
	 */
	public String getLingditime(){
		return lingditime;
	}
	
	/**
	 * set 领地战开始时间
	 */
	public void setLingditime(String lingditime){
		this.lingditime = lingditime;
	}
	
	/**
	 * get 地宫寻宝剩余次数
	 * @return 
	 */
	public String getEpalacenum(){
		return epalacenum;
	}
	
	/**
	 * set 地宫寻宝剩余次数
	 */
	public void setEpalacenum(String epalacenum){
		this.epalacenum = epalacenum;
	}
	
	/**
	 * get 挑战校场剩余次数
	 * @return 
	 */
	public int getJiaochangnum(){
		return jiaochangnum;
	}
	
	/**
	 * set 挑战校场剩余次数
	 */
	public void setJiaochangnum(int jiaochangnum){
		this.jiaochangnum = jiaochangnum;
	}
	
	/**
	 * get 双倍收益时间
	 * @return 
	 */
	public String getDoubletime(){
		return doubletime;
	}
	
	/**
	 * set 双倍收益时间
	 */
	public void setDoubletime(String doubletime){
		this.doubletime = doubletime;
	}
	
	/**
	 * get 迷宫开始时间
	 * @return 
	 */
	public String getMazetime(){
		return mazetime;
	}
	
	/**
	 * set 迷宫开始时间
	 */
	public void setMazetime(String mazetime){
		this.mazetime = mazetime;
	}
	
	/**
	 * get 比武岛时间
	 * @return 
	 */
	public String getBiwudaotime(){
		return biwudaotime;
	}
	
	/**
	 * set 比武岛时间
	 */
	public void setBiwudaotime(String biwudaotime){
		this.biwudaotime = biwudaotime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//秦王战役副本剩余次数
		buf.append("zonesnum:" + zonesnum +",");
		//BOSS刷新时间
		if(this.bosstime!=null) buf.append("bosstime:" + bosstime.toString() +",");
		//攻城战开始时间
		if(this.gongchengtime!=null) buf.append("gongchengtime:" + gongchengtime.toString() +",");
		//领地战开始时间
		if(this.lingditime!=null) buf.append("lingditime:" + lingditime.toString() +",");
		//地宫寻宝剩余次数
		if(this.epalacenum!=null) buf.append("epalacenum:" + epalacenum.toString() +",");
		//挑战校场剩余次数
		buf.append("jiaochangnum:" + jiaochangnum +",");
		//双倍收益时间
		if(this.doubletime!=null) buf.append("doubletime:" + doubletime.toString() +",");
		//迷宫开始时间
		if(this.mazetime!=null) buf.append("mazetime:" + mazetime.toString() +",");
		//比武岛时间
		if(this.biwudaotime!=null) buf.append("biwudaotime:" + biwudaotime.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}