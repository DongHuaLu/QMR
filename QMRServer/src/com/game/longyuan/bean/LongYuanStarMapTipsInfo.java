package com.game.longyuan.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 龙元心法星图Tips信息
 */
public class LongYuanStarMapTipsInfo extends Bean {

	//当前的龙元心法阶段（星图）
	private byte longyuanactlv;
	
	//是否已经激活
	private byte isachieve;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前的龙元心法阶段（星图）
		writeByte(buf, this.longyuanactlv);
		//是否已经激活
		writeByte(buf, this.isachieve);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前的龙元心法阶段（星图）
		this.longyuanactlv = readByte(buf);
		//是否已经激活
		this.isachieve = readByte(buf);
		return true;
	}
	
	/**
	 * get 当前的龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanactlv(){
		return longyuanactlv;
	}
	
	/**
	 * set 当前的龙元心法阶段（星图）
	 */
	public void setLongyuanactlv(byte longyuanactlv){
		this.longyuanactlv = longyuanactlv;
	}
	
	/**
	 * get 是否已经激活
	 * @return 
	 */
	public byte getIsachieve(){
		return isachieve;
	}
	
	/**
	 * set 是否已经激活
	 */
	public void setIsachieve(byte isachieve){
		this.isachieve = isachieve;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//当前的龙元心法阶段（星图）
		buf.append("longyuanactlv:" + longyuanactlv +",");
		//是否已经激活
		buf.append("isachieve:" + isachieve +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}