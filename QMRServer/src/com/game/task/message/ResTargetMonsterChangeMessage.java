package com.game.task.message;

import com.game.task.bean.TargetMonsterInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 讨伐任务怪物状态变化消息
 */
public class ResTargetMonsterChangeMessage extends Message{

	//怪物信息
	private TargetMonsterInfo monsterInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物信息
		writeBean(buf, this.monsterInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物信息
		this.monsterInfo = (TargetMonsterInfo)readBean(buf, TargetMonsterInfo.class);
		return true;
	}
	
	/**
	 * get 怪物信息
	 * @return 
	 */
	public TargetMonsterInfo getMonsterInfo(){
		return monsterInfo;
	}
	
	/**
	 * set 怪物信息
	 */
	public void setMonsterInfo(TargetMonsterInfo monsterInfo){
		this.monsterInfo = monsterInfo;
	}
	
	
	@Override
	public int getId() {
		return 120110;
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
		//怪物信息
		if(this.monsterInfo!=null) buf.append("monsterInfo:" + monsterInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}