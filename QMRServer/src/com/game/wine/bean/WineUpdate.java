package com.game.wine.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 变动信息
 */
public class WineUpdate extends Bean {

	//掷骰子次数
	private byte times;
	
	//铜币转运次数
	private byte moneyTimes;
	
	//本次投掷的点数
	private byte nowWine;
	
	//投掷点数合计
	private byte wine;
	
	//本次投掷的结果(111111:表示投掷的6个都是酒)
	private int detail;
	
	//当前可获得奖励真气
	private int zhenqi;
	
	//当前可获得奖励经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//掷骰子次数
		writeByte(buf, this.times);
		//铜币转运次数
		writeByte(buf, this.moneyTimes);
		//本次投掷的点数
		writeByte(buf, this.nowWine);
		//投掷点数合计
		writeByte(buf, this.wine);
		//本次投掷的结果(111111:表示投掷的6个都是酒)
		writeInt(buf, this.detail);
		//当前可获得奖励真气
		writeInt(buf, this.zhenqi);
		//当前可获得奖励经验
		writeInt(buf, this.exp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//掷骰子次数
		this.times = readByte(buf);
		//铜币转运次数
		this.moneyTimes = readByte(buf);
		//本次投掷的点数
		this.nowWine = readByte(buf);
		//投掷点数合计
		this.wine = readByte(buf);
		//本次投掷的结果(111111:表示投掷的6个都是酒)
		this.detail = readInt(buf);
		//当前可获得奖励真气
		this.zhenqi = readInt(buf);
		//当前可获得奖励经验
		this.exp = readInt(buf);
		return true;
	}
	
	/**
	 * get 掷骰子次数
	 * @return 
	 */
	public byte getTimes(){
		return times;
	}
	
	/**
	 * set 掷骰子次数
	 */
	public void setTimes(byte times){
		this.times = times;
	}
	
	/**
	 * get 铜币转运次数
	 * @return 
	 */
	public byte getMoneyTimes(){
		return moneyTimes;
	}
	
	/**
	 * set 铜币转运次数
	 */
	public void setMoneyTimes(byte moneyTimes){
		this.moneyTimes = moneyTimes;
	}
	
	/**
	 * get 本次投掷的点数
	 * @return 
	 */
	public byte getNowWine(){
		return nowWine;
	}
	
	/**
	 * set 本次投掷的点数
	 */
	public void setNowWine(byte nowWine){
		this.nowWine = nowWine;
	}
	
	/**
	 * get 投掷点数合计
	 * @return 
	 */
	public byte getWine(){
		return wine;
	}
	
	/**
	 * set 投掷点数合计
	 */
	public void setWine(byte wine){
		this.wine = wine;
	}
	
	/**
	 * get 本次投掷的结果(111111:表示投掷的6个都是酒)
	 * @return 
	 */
	public int getDetail(){
		return detail;
	}
	
	/**
	 * set 本次投掷的结果(111111:表示投掷的6个都是酒)
	 */
	public void setDetail(int detail){
		this.detail = detail;
	}
	
	/**
	 * get 当前可获得奖励真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 当前可获得奖励真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 当前可获得奖励经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 当前可获得奖励经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//掷骰子次数
		buf.append("times:" + times +",");
		//铜币转运次数
		buf.append("moneyTimes:" + moneyTimes +",");
		//本次投掷的点数
		buf.append("nowWine:" + nowWine +",");
		//投掷点数合计
		buf.append("wine:" + wine +",");
		//本次投掷的结果(111111:表示投掷的6个都是酒)
		buf.append("detail:" + detail +",");
		//当前可获得奖励真气
		buf.append("zhenqi:" + zhenqi +",");
		//当前可获得奖励经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}