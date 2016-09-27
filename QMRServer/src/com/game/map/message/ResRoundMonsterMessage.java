package com.game.map.message;

import com.game.map.bean.MonsterInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围怪物消息
 */
public class ResRoundMonsterMessage extends Message{

	//周围怪物信息
	private MonsterInfo monster;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围怪物信息
		writeBean(buf, this.monster);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围怪物信息
		this.monster = (MonsterInfo)readBean(buf, MonsterInfo.class);
		return true;
	}
	
	/**
	 * get 周围怪物信息
	 * @return 
	 */
	public MonsterInfo getMonster(){
		return monster;
	}
	
	/**
	 * set 周围怪物信息
	 */
	public void setMonster(MonsterInfo monster){
		this.monster = monster;
	}
	
	
	@Override
	public int getId() {
		return 101102;
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
		//周围怪物信息
		if(this.monster!=null) buf.append("monster:" + monster.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}