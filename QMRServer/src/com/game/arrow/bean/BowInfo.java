package com.game.arrow.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 箭支信息
 */
public class BowInfo extends Bean {

	//箭支主等阶
	private int bowmainlv;
	
	//箭支子等阶
	private int bowsublv;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//箭支主等阶
		writeInt(buf, this.bowmainlv);
		//箭支子等阶
		writeInt(buf, this.bowsublv);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//箭支主等阶
		this.bowmainlv = readInt(buf);
		//箭支子等阶
		this.bowsublv = readInt(buf);
		return true;
	}
	
	/**
	 * get 箭支主等阶
	 * @return 
	 */
	public int getBowmainlv(){
		return bowmainlv;
	}
	
	/**
	 * set 箭支主等阶
	 */
	public void setBowmainlv(int bowmainlv){
		this.bowmainlv = bowmainlv;
	}
	
	/**
	 * get 箭支子等阶
	 * @return 
	 */
	public int getBowsublv(){
		return bowsublv;
	}
	
	/**
	 * set 箭支子等阶
	 */
	public void setBowsublv(int bowsublv){
		this.bowsublv = bowsublv;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//箭支主等阶
		buf.append("bowmainlv:" + bowmainlv +",");
		//箭支子等阶
		buf.append("bowsublv:" + bowsublv +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}