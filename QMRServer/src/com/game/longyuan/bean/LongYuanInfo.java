package com.game.longyuan.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 龙元心法信息
 */
public class LongYuanInfo extends Bean {

	//龙元心法分享经验人数
	private short longyuanexpshare;
	
	//龙元心法阶段（星图）
	private byte longyuanlv;
	
	//龙元心法星位
	private byte longyuannum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//龙元心法分享经验人数
		writeShort(buf, this.longyuanexpshare);
		//龙元心法阶段（星图）
		writeByte(buf, this.longyuanlv);
		//龙元心法星位
		writeByte(buf, this.longyuannum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//龙元心法分享经验人数
		this.longyuanexpshare = readShort(buf);
		//龙元心法阶段（星图）
		this.longyuanlv = readByte(buf);
		//龙元心法星位
		this.longyuannum = readByte(buf);
		return true;
	}
	
	/**
	 * get 龙元心法分享经验人数
	 * @return 
	 */
	public short getLongyuanexpshare(){
		return longyuanexpshare;
	}
	
	/**
	 * set 龙元心法分享经验人数
	 */
	public void setLongyuanexpshare(short longyuanexpshare){
		this.longyuanexpshare = longyuanexpshare;
	}
	
	/**
	 * get 龙元心法阶段（星图）
	 * @return 
	 */
	public byte getLongyuanlv(){
		return longyuanlv;
	}
	
	/**
	 * set 龙元心法阶段（星图）
	 */
	public void setLongyuanlv(byte longyuanlv){
		this.longyuanlv = longyuanlv;
	}
	
	/**
	 * get 龙元心法星位
	 * @return 
	 */
	public byte getLongyuannum(){
		return longyuannum;
	}
	
	/**
	 * set 龙元心法星位
	 */
	public void setLongyuannum(byte longyuannum){
		this.longyuannum = longyuannum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//龙元心法分享经验人数
		buf.append("longyuanexpshare:" + longyuanexpshare +",");
		//龙元心法阶段（星图）
		buf.append("longyuanlv:" + longyuanlv +",");
		//龙元心法星位
		buf.append("longyuannum:" + longyuannum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}