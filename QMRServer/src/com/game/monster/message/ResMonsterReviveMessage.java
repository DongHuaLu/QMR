package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 怪物复活消息
 */
public class ResMonsterReviveMessage extends Message{

	//怪物信息
	private com.game.map.bean.MonsterInfo monster;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物信息
		writeBean(buf, this.monster);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物信息
		this.monster = (com.game.map.bean.MonsterInfo)readBean(buf, com.game.map.bean.MonsterInfo.class);
		return true;
	}
	
	/**
	 * get 怪物信息
	 * @return 
	 */
	public com.game.map.bean.MonsterInfo getMonster(){
		return monster;
	}
	
	/**
	 * set 怪物信息
	 */
	public void setMonster(com.game.map.bean.MonsterInfo monster){
		this.monster = monster;
	}
	
	
	@Override
	public int getId() {
		return 114109;
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
		if(this.monster!=null) buf.append("monster:" + monster.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}