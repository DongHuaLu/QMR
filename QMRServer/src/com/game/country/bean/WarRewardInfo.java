package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 攻城战个人在线奖励信息
 */
public class WarRewardInfo extends Bean {

	//奖励真气
	private int zhenqi;
	
	//奖励经验
	private int exp;
	
	//停留时间（秒）
	private int remaintime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励真气
		writeInt(buf, this.zhenqi);
		//奖励经验
		writeInt(buf, this.exp);
		//停留时间（秒）
		writeInt(buf, this.remaintime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励真气
		this.zhenqi = readInt(buf);
		//奖励经验
		this.exp = readInt(buf);
		//停留时间（秒）
		this.remaintime = readInt(buf);
		return true;
	}
	
	/**
	 * get 奖励真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 奖励真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 奖励经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 奖励经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 停留时间（秒）
	 * @return 
	 */
	public int getRemaintime(){
		return remaintime;
	}
	
	/**
	 * set 停留时间（秒）
	 */
	public void setRemaintime(int remaintime){
		this.remaintime = remaintime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//奖励真气
		buf.append("zhenqi:" + zhenqi +",");
		//奖励经验
		buf.append("exp:" + exp +",");
		//停留时间（秒）
		buf.append("remaintime:" + remaintime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}