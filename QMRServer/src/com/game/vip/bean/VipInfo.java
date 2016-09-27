package com.game.vip.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家VIP信息
 */
public class VipInfo extends Bean {

	//VIPid 0表示不是VIP
	private int vipId;
	
	//VIP剩余持续时间 单位:秒 
	private int remain;
	
	//是否可领取，0-不可领取， 1-可领取
	private int status;
	
	//是否展示 至尊VIP的广告 0-不展示 1-展示
	private byte showad;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//VIPid 0表示不是VIP
		writeInt(buf, this.vipId);
		//VIP剩余持续时间 单位:秒 
		writeInt(buf, this.remain);
		//是否可领取，0-不可领取， 1-可领取
		writeInt(buf, this.status);
		//是否展示 至尊VIP的广告 0-不展示 1-展示
		writeByte(buf, this.showad);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//VIPid 0表示不是VIP
		this.vipId = readInt(buf);
		//VIP剩余持续时间 单位:秒 
		this.remain = readInt(buf);
		//是否可领取，0-不可领取， 1-可领取
		this.status = readInt(buf);
		//是否展示 至尊VIP的广告 0-不展示 1-展示
		this.showad = readByte(buf);
		return true;
	}
	
	/**
	 * get VIPid 0表示不是VIP
	 * @return 
	 */
	public int getVipId(){
		return vipId;
	}
	
	/**
	 * set VIPid 0表示不是VIP
	 */
	public void setVipId(int vipId){
		this.vipId = vipId;
	}
	
	/**
	 * get VIP剩余持续时间 单位:秒 
	 * @return 
	 */
	public int getRemain(){
		return remain;
	}
	
	/**
	 * set VIP剩余持续时间 单位:秒 
	 */
	public void setRemain(int remain){
		this.remain = remain;
	}
	
	/**
	 * get 是否可领取，0-不可领取， 1-可领取
	 * @return 
	 */
	public int getStatus(){
		return status;
	}
	
	/**
	 * set 是否可领取，0-不可领取， 1-可领取
	 */
	public void setStatus(int status){
		this.status = status;
	}
	
	/**
	 * get 是否展示 至尊VIP的广告 0-不展示 1-展示
	 * @return 
	 */
	public byte getShowad(){
		return showad;
	}
	
	/**
	 * set 是否展示 至尊VIP的广告 0-不展示 1-展示
	 */
	public void setShowad(byte showad){
		this.showad = showad;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//VIPid 0表示不是VIP
		buf.append("vipId:" + vipId +",");
		//VIP剩余持续时间 单位:秒 
		buf.append("remain:" + remain +",");
		//是否可领取，0-不可领取， 1-可领取
		buf.append("status:" + status +",");
		//是否展示 至尊VIP的广告 0-不展示 1-展示
		buf.append("showad:" + showad +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}