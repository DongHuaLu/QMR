package com.game.wine.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 配置信息
 */
public class WineConfig extends Bean {

	//掷骰子的次数上限
	private byte maxTimes;
	
	//铜币改运的次数上限
	private byte maxMoneyTimes;
	
	//转运需要的铜币数量
	private int money;
	
	//转运需要的元宝数量
	private int gold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//掷骰子的次数上限
		writeByte(buf, this.maxTimes);
		//铜币改运的次数上限
		writeByte(buf, this.maxMoneyTimes);
		//转运需要的铜币数量
		writeInt(buf, this.money);
		//转运需要的元宝数量
		writeInt(buf, this.gold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//掷骰子的次数上限
		this.maxTimes = readByte(buf);
		//铜币改运的次数上限
		this.maxMoneyTimes = readByte(buf);
		//转运需要的铜币数量
		this.money = readInt(buf);
		//转运需要的元宝数量
		this.gold = readInt(buf);
		return true;
	}
	
	/**
	 * get 掷骰子的次数上限
	 * @return 
	 */
	public byte getMaxTimes(){
		return maxTimes;
	}
	
	/**
	 * set 掷骰子的次数上限
	 */
	public void setMaxTimes(byte maxTimes){
		this.maxTimes = maxTimes;
	}
	
	/**
	 * get 铜币改运的次数上限
	 * @return 
	 */
	public byte getMaxMoneyTimes(){
		return maxMoneyTimes;
	}
	
	/**
	 * set 铜币改运的次数上限
	 */
	public void setMaxMoneyTimes(byte maxMoneyTimes){
		this.maxMoneyTimes = maxMoneyTimes;
	}
	
	/**
	 * get 转运需要的铜币数量
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 转运需要的铜币数量
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	/**
	 * get 转运需要的元宝数量
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 转运需要的元宝数量
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//掷骰子的次数上限
		buf.append("maxTimes:" + maxTimes +",");
		//铜币改运的次数上限
		buf.append("maxMoneyTimes:" + maxMoneyTimes +",");
		//转运需要的铜币数量
		buf.append("money:" + money +",");
		//转运需要的元宝数量
		buf.append("gold:" + gold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}