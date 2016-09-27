package com.game.arrow.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 战魂信息
 */
public class FightSpiritInfo extends Bean {

	//战魂类型 1日 2月 3金 4木 5水 6火 7土
	private int type;
	
	//战魂数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//战魂类型 1日 2月 3金 4木 5水 6火 7土
		writeInt(buf, this.type);
		//战魂数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//战魂类型 1日 2月 3金 4木 5水 6火 7土
		this.type = readInt(buf);
		//战魂数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 战魂类型 1日 2月 3金 4木 5水 6火 7土
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 战魂类型 1日 2月 3金 4木 5水 6火 7土
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 战魂数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 战魂数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//战魂类型 1日 2月 3金 4木 5水 6火 7土
		buf.append("type:" + type +",");
		//战魂数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}