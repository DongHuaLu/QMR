package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 连续扫荡信息消息
 */
public class ResZoneContinuousRaidsMessage extends Message{

	//总时间（秒）
	private int sumtime;
	
	//已经过去的时间（秒）
	private int passedime;
	
	//可领取的奖励次数
	private int rewardnum;
	
	//当前扫荡的副本数量
	private int zonenum;
	
	//类型，1剧情副本，4七曜战将
	private int zonetype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//总时间（秒）
		writeInt(buf, this.sumtime);
		//已经过去的时间（秒）
		writeInt(buf, this.passedime);
		//可领取的奖励次数
		writeInt(buf, this.rewardnum);
		//当前扫荡的副本数量
		writeInt(buf, this.zonenum);
		//类型，1剧情副本，4七曜战将
		writeInt(buf, this.zonetype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//总时间（秒）
		this.sumtime = readInt(buf);
		//已经过去的时间（秒）
		this.passedime = readInt(buf);
		//可领取的奖励次数
		this.rewardnum = readInt(buf);
		//当前扫荡的副本数量
		this.zonenum = readInt(buf);
		//类型，1剧情副本，4七曜战将
		this.zonetype = readInt(buf);
		return true;
	}
	
	/**
	 * get 总时间（秒）
	 * @return 
	 */
	public int getSumtime(){
		return sumtime;
	}
	
	/**
	 * set 总时间（秒）
	 */
	public void setSumtime(int sumtime){
		this.sumtime = sumtime;
	}
	
	/**
	 * get 已经过去的时间（秒）
	 * @return 
	 */
	public int getPassedime(){
		return passedime;
	}
	
	/**
	 * set 已经过去的时间（秒）
	 */
	public void setPassedime(int passedime){
		this.passedime = passedime;
	}
	
	/**
	 * get 可领取的奖励次数
	 * @return 
	 */
	public int getRewardnum(){
		return rewardnum;
	}
	
	/**
	 * set 可领取的奖励次数
	 */
	public void setRewardnum(int rewardnum){
		this.rewardnum = rewardnum;
	}
	
	/**
	 * get 当前扫荡的副本数量
	 * @return 
	 */
	public int getZonenum(){
		return zonenum;
	}
	
	/**
	 * set 当前扫荡的副本数量
	 */
	public void setZonenum(int zonenum){
		this.zonenum = zonenum;
	}
	
	/**
	 * get 类型，1剧情副本，4七曜战将
	 * @return 
	 */
	public int getZonetype(){
		return zonetype;
	}
	
	/**
	 * set 类型，1剧情副本，4七曜战将
	 */
	public void setZonetype(int zonetype){
		this.zonetype = zonetype;
	}
	
	
	@Override
	public int getId() {
		return 128114;
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
		//总时间（秒）
		buf.append("sumtime:" + sumtime +",");
		//已经过去的时间（秒）
		buf.append("passedime:" + passedime +",");
		//可领取的奖励次数
		buf.append("rewardnum:" + rewardnum +",");
		//当前扫荡的副本数量
		buf.append("zonenum:" + zonenum +",");
		//类型，1剧情副本，4七曜战将
		buf.append("zonetype:" + zonetype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}