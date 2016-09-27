package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家死亡消息
 */
public class ResPlayerDieMessage extends Message{

	//死亡的角色Id
	private long personId;
	
	//自动复活
	private byte autoRevive;
	
	//怪物模型ID
	private int monstermodelid;
	
	//攻击者玩家ID
	private long attackerid;
	
	//攻击者玩家名字
	private String attackername;
	
	//死亡位置
	private com.game.structs.Position position;
	
	//死亡时间
	private int time;
	
	//0正常死亡，1攻城战期间在攻城战地图死亡
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//死亡的角色Id
		writeLong(buf, this.personId);
		//自动复活
		writeByte(buf, this.autoRevive);
		//怪物模型ID
		writeInt(buf, this.monstermodelid);
		//攻击者玩家ID
		writeLong(buf, this.attackerid);
		//攻击者玩家名字
		writeString(buf, this.attackername);
		//死亡位置
		writeBean(buf, this.position);
		//死亡时间
		writeInt(buf, this.time);
		//0正常死亡，1攻城战期间在攻城战地图死亡
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//死亡的角色Id
		this.personId = readLong(buf);
		//自动复活
		this.autoRevive = readByte(buf);
		//怪物模型ID
		this.monstermodelid = readInt(buf);
		//攻击者玩家ID
		this.attackerid = readLong(buf);
		//攻击者玩家名字
		this.attackername = readString(buf);
		//死亡位置
		this.position = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
		//死亡时间
		this.time = readInt(buf);
		//0正常死亡，1攻城战期间在攻城战地图死亡
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 死亡的角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 死亡的角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 自动复活
	 * @return 
	 */
	public byte getAutoRevive(){
		return autoRevive;
	}
	
	/**
	 * set 自动复活
	 */
	public void setAutoRevive(byte autoRevive){
		this.autoRevive = autoRevive;
	}
	
	/**
	 * get 怪物模型ID
	 * @return 
	 */
	public int getMonstermodelid(){
		return monstermodelid;
	}
	
	/**
	 * set 怪物模型ID
	 */
	public void setMonstermodelid(int monstermodelid){
		this.monstermodelid = monstermodelid;
	}
	
	/**
	 * get 攻击者玩家ID
	 * @return 
	 */
	public long getAttackerid(){
		return attackerid;
	}
	
	/**
	 * set 攻击者玩家ID
	 */
	public void setAttackerid(long attackerid){
		this.attackerid = attackerid;
	}
	
	/**
	 * get 攻击者玩家名字
	 * @return 
	 */
	public String getAttackername(){
		return attackername;
	}
	
	/**
	 * set 攻击者玩家名字
	 */
	public void setAttackername(String attackername){
		this.attackername = attackername;
	}
	
	/**
	 * get 死亡位置
	 * @return 
	 */
	public com.game.structs.Position getPosition(){
		return position;
	}
	
	/**
	 * set 死亡位置
	 */
	public void setPosition(com.game.structs.Position position){
		this.position = position;
	}
	
	/**
	 * get 死亡时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 死亡时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 0正常死亡，1攻城战期间在攻城战地图死亡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0正常死亡，1攻城战期间在攻城战地图死亡
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 103115;
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
		//死亡的角色Id
		buf.append("personId:" + personId +",");
		//自动复活
		buf.append("autoRevive:" + autoRevive +",");
		//怪物模型ID
		buf.append("monstermodelid:" + monstermodelid +",");
		//攻击者玩家ID
		buf.append("attackerid:" + attackerid +",");
		//攻击者玩家名字
		if(this.attackername!=null) buf.append("attackername:" + attackername.toString() +",");
		//死亡位置
		if(this.position!=null) buf.append("position:" + position.toString() +",");
		//死亡时间
		buf.append("time:" + time +",");
		//0正常死亡，1攻城战期间在攻城战地图死亡
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}