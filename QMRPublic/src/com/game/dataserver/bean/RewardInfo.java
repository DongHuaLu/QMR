package com.game.dataserver.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得奖励信息
 */
public class RewardInfo extends Bean {

	//奖励id
	private long rewardId;
	
	//荣誉点
	private int honour;
	
	//真气
	private int zhenqi;
	
	//经验
	private int exp;
	
	//获得时间
	private long time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励id
		writeLong(buf, this.rewardId);
		//荣誉点
		writeInt(buf, this.honour);
		//真气
		writeInt(buf, this.zhenqi);
		//经验
		writeInt(buf, this.exp);
		//获得时间
		writeLong(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励id
		this.rewardId = readLong(buf);
		//荣誉点
		this.honour = readInt(buf);
		//真气
		this.zhenqi = readInt(buf);
		//经验
		this.exp = readInt(buf);
		//获得时间
		this.time = readLong(buf);
		return true;
	}
	
	/**
	 * get 奖励id
	 * @return 
	 */
	public long getRewardId(){
		return rewardId;
	}
	
	/**
	 * set 奖励id
	 */
	public void setRewardId(long rewardId){
		this.rewardId = rewardId;
	}
	
	/**
	 * get 荣誉点
	 * @return 
	 */
	public int getHonour(){
		return honour;
	}
	
	/**
	 * set 荣誉点
	 */
	public void setHonour(int honour){
		this.honour = honour;
	}
	
	/**
	 * get 真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 获得时间
	 * @return 
	 */
	public long getTime(){
		return time;
	}
	
	/**
	 * set 获得时间
	 */
	public void setTime(long time){
		this.time = time;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//奖励id
		buf.append("rewardId:" + rewardId +",");
		//荣誉点
		buf.append("honour:" + honour +",");
		//真气
		buf.append("zhenqi:" + zhenqi +",");
		//经验
		buf.append("exp:" + exp +",");
		//获得时间
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}