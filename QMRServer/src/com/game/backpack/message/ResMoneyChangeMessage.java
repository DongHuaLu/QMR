package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 游戏币变化消息
 */
public class ResMoneyChangeMessage extends Message{

	//游戏币数量
	private int money;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//游戏币数量
		writeInt(buf, this.money);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//游戏币数量
		this.money = readInt(buf);
		return true;
	}
	
	/**
	 * get 游戏币数量
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 游戏币数量
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	
	@Override
	public int getId() {
		return 104105;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//游戏币数量
		buf.append("money:" + money +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}