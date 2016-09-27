package com.game.zones.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本怪物信息
 */
public class ZoneMonstrInfo extends Bean {

	//怪物id
	private int monstrmodid;
	
	//怪物数量
	private int monstrnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物id
		writeInt(buf, this.monstrmodid);
		//怪物数量
		writeInt(buf, this.monstrnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物id
		this.monstrmodid = readInt(buf);
		//怪物数量
		this.monstrnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 怪物id
	 * @return 
	 */
	public int getMonstrmodid(){
		return monstrmodid;
	}
	
	/**
	 * set 怪物id
	 */
	public void setMonstrmodid(int monstrmodid){
		this.monstrmodid = monstrmodid;
	}
	
	/**
	 * get 怪物数量
	 * @return 
	 */
	public int getMonstrnum(){
		return monstrnum;
	}
	
	/**
	 * set 怪物数量
	 */
	public void setMonstrnum(int monstrnum){
		this.monstrnum = monstrnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//怪物id
		buf.append("monstrmodid:" + monstrmodid +",");
		//怪物数量
		buf.append("monstrnum:" + monstrnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}