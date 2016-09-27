package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 职位领奖信息
 */
public class JobAwardInfo extends Bean {

	//职位
	private int level;
	
	//是否可领奖，1可领，0不可
	private int status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//职位
		writeInt(buf, this.level);
		//是否可领奖，1可领，0不可
		writeInt(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//职位
		this.level = readInt(buf);
		//是否可领奖，1可领，0不可
		this.status = readInt(buf);
		return true;
	}
	
	/**
	 * get 职位
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 职位
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 是否可领奖，1可领，0不可
	 * @return 
	 */
	public int getStatus(){
		return status;
	}
	
	/**
	 * set 是否可领奖，1可领，0不可
	 */
	public void setStatus(int status){
		this.status = status;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//职位
		buf.append("level:" + level +",");
		//是否可领奖，1可领，0不可
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}