package com.game.fight.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 战斗广播消息
 */
public class ResFightBroadcastMessage extends Message{

	//战斗Id
	private long fightId;
	
	//角色Id
	private long personId;
	
	//攻击朝向
	private int fightDirection;
	
	//攻击类型
	private int fightType;
	
	//攻击目标
	private long fightTarget;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//战斗Id
		writeLong(buf, this.fightId);
		//角色Id
		writeLong(buf, this.personId);
		//攻击朝向
		writeInt(buf, this.fightDirection);
		//攻击类型
		writeInt(buf, this.fightType);
		//攻击目标
		writeLong(buf, this.fightTarget);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//战斗Id
		this.fightId = readLong(buf);
		//角色Id
		this.personId = readLong(buf);
		//攻击朝向
		this.fightDirection = readInt(buf);
		//攻击类型
		this.fightType = readInt(buf);
		//攻击目标
		this.fightTarget = readLong(buf);
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
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 攻击朝向
	 * @return 
	 */
	public int getFightDirection(){
		return fightDirection;
	}
	
	/**
	 * set 攻击朝向
	 */
	public void setFightDirection(int fightDirection){
		this.fightDirection = fightDirection;
	}
	
	/**
	 * get 攻击类型
	 * @return 
	 */
	public int getFightType(){
		return fightType;
	}
	
	/**
	 * set 攻击类型
	 */
	public void setFightType(int fightType){
		this.fightType = fightType;
	}
	
	/**
	 * get 攻击目标
	 * @return 
	 */
	public long getFightTarget(){
		return fightTarget;
	}
	
	/**
	 * set 攻击目标
	 */
	public void setFightTarget(long fightTarget){
		this.fightTarget = fightTarget;
	}
	
	
	@Override
	public int getId() {
		return 102101;
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
		//角色Id
		buf.append("personId:" + personId +",");
		//攻击朝向
		buf.append("fightDirection:" + fightDirection +",");
		//攻击类型
		buf.append("fightType:" + fightType +",");
		//攻击目标
		buf.append("fightTarget:" + fightTarget +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}