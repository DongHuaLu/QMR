package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 战场经验变化消息
 */
public class ResPlayerBattleExpChangeMessage extends Message{

	//当前战场经验
	private int currentBattleExp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前战场经验
		writeInt(buf, this.currentBattleExp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前战场经验
		this.currentBattleExp = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前战场经验
	 * @return 
	 */
	public int getCurrentBattleExp(){
		return currentBattleExp;
	}
	
	/**
	 * set 当前战场经验
	 */
	public void setCurrentBattleExp(int currentBattleExp){
		this.currentBattleExp = currentBattleExp;
	}
	
	
	@Override
	public int getId() {
		return 103114;
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
		//当前战场经验
		buf.append("currentBattleExp:" + currentBattleExp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}