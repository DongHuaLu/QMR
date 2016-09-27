package com.game.longyuan.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 龙元心法星位Tips信息
 */
public class LongYuanPosTipsInfo extends Bean {

	//当前的龙元心法阶段（星图）
	private byte longyuanactlv;
	
	//当前的龙元心法星位
	private byte longyuanactnum;
	
	//成功率
	private int successrate;
	
	//龙元心法分享经验人数
	private short longyuanexpshare;
	
	//龙元心法分享经验总量
	private int longyuanshareexpsum;
	
	//是否已经激活
	private byte isachieve;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前的龙元心法阶段（星图）
		writeByte(buf, this.longyuanactlv);
		//当前的龙元心法星位
		writeByte(buf, this.longyuanactnum);
		//成功率
		writeInt(buf, this.successrate);
		//龙元心法分享经验人数
		writeShort(buf, this.longyuanexpshare);
		//龙元心法分享经验总量
		writeInt(buf, this.longyuanshareexpsum);
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
		//当前的龙元心法星位
		this.longyuanactnum = readByte(buf);
		//成功率
		this.successrate = readInt(buf);
		//龙元心法分享经验人数
		this.longyuanexpshare = readShort(buf);
		//龙元心法分享经验总量
		this.longyuanshareexpsum = readInt(buf);
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
	 * get 当前的龙元心法星位
	 * @return 
	 */
	public byte getLongyuanactnum(){
		return longyuanactnum;
	}
	
	/**
	 * set 当前的龙元心法星位
	 */
	public void setLongyuanactnum(byte longyuanactnum){
		this.longyuanactnum = longyuanactnum;
	}
	
	/**
	 * get 成功率
	 * @return 
	 */
	public int getSuccessrate(){
		return successrate;
	}
	
	/**
	 * set 成功率
	 */
	public void setSuccessrate(int successrate){
		this.successrate = successrate;
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
	 * get 龙元心法分享经验总量
	 * @return 
	 */
	public int getLongyuanshareexpsum(){
		return longyuanshareexpsum;
	}
	
	/**
	 * set 龙元心法分享经验总量
	 */
	public void setLongyuanshareexpsum(int longyuanshareexpsum){
		this.longyuanshareexpsum = longyuanshareexpsum;
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
		//当前的龙元心法星位
		buf.append("longyuanactnum:" + longyuanactnum +",");
		//成功率
		buf.append("successrate:" + successrate +",");
		//龙元心法分享经验人数
		buf.append("longyuanexpshare:" + longyuanexpshare +",");
		//龙元心法分享经验总量
		buf.append("longyuanshareexpsum:" + longyuanshareexpsum +",");
		//是否已经激活
		buf.append("isachieve:" + isachieve +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}