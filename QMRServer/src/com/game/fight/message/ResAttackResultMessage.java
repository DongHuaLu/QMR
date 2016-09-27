package com.game.fight.message;

import com.game.fight.bean.AttackResultInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 攻击结果消息
 */
public class ResAttackResultMessage extends Message{

	//战斗Id
	private long fightId;
	
	//攻击结果
	private AttackResultInfo state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//战斗Id
		writeLong(buf, this.fightId);
		//攻击结果
		writeBean(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//战斗Id
		this.fightId = readLong(buf);
		//攻击结果
		this.state = (AttackResultInfo)readBean(buf, AttackResultInfo.class);
		return true;
	}
	
	/**
	 * get 战斗Id
	 * @return 
	 */
	public long getFightId(){
		return fightId;
	}
	
	/**
	 * set 战斗Id
	 */
	public void setFightId(long fightId){
		this.fightId = fightId;
	}
	
	/**
	 * get 攻击结果
	 * @return 
	 */
	public AttackResultInfo getState(){
		return state;
	}
	
	/**
	 * set 攻击结果
	 */
	public void setState(AttackResultInfo state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 102102;
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
		//战斗Id
		buf.append("fightId:" + fightId +",");
		//攻击结果
		if(this.state!=null) buf.append("state:" + state.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}