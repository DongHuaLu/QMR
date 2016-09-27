package com.game.zones.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本奖励信息
 */
public class ZoneRewardNumInfo extends Bean {

	//副本id
	private int zoneid;
	
	//奖励可领数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本id
		writeInt(buf, this.zoneid);
		//奖励可领数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本id
		this.zoneid = readInt(buf);
		//奖励可领数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 副本id
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本id
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 奖励可领数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 奖励可领数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//副本id
		buf.append("zoneid:" + zoneid +",");
		//奖励可领数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}