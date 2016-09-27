package com.game.task.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步怪物的信息
 */
public class TargetMonsterInfo extends Bean {

	//怪物状态 0未刷新 1未死亡
	private byte state;
	
	//怪物模型ID
	private int modelId;
	
	//服务器ID
	private int serverId;
	
	//线ID
	private int lineId;
	
	//怪物当前血量
	private int hp;
	
	//地图模型ID
	private int mapId;
	
	//上次击杀者名字
	private String killer;
	
	//重生时间
	private int reliveTime;
	
	//座标X
	private short birthX;
	
	//座标Y
	private short birthY;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物状态 0未刷新 1未死亡
		writeByte(buf, this.state);
		//怪物模型ID
		writeInt(buf, this.modelId);
		//服务器ID
		writeInt(buf, this.serverId);
		//线ID
		writeInt(buf, this.lineId);
		//怪物当前血量
		writeInt(buf, this.hp);
		//地图模型ID
		writeInt(buf, this.mapId);
		//上次击杀者名字
		writeString(buf, this.killer);
		//重生时间
		writeInt(buf, this.reliveTime);
		//座标X
		writeShort(buf, this.birthX);
		//座标Y
		writeShort(buf, this.birthY);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物状态 0未刷新 1未死亡
		this.state = readByte(buf);
		//怪物模型ID
		this.modelId = readInt(buf);
		//服务器ID
		this.serverId = readInt(buf);
		//线ID
		this.lineId = readInt(buf);
		//怪物当前血量
		this.hp = readInt(buf);
		//地图模型ID
		this.mapId = readInt(buf);
		//上次击杀者名字
		this.killer = readString(buf);
		//重生时间
		this.reliveTime = readInt(buf);
		//座标X
		this.birthX = readShort(buf);
		//座标Y
		this.birthY = readShort(buf);
		return true;
	}
	
	/**
	 * get 怪物状态 0未刷新 1未死亡
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 怪物状态 0未刷新 1未死亡
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	/**
	 * get 怪物模型ID
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 怪物模型ID
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	/**
	 * get 服务器ID
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器ID
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 线ID
	 * @return 
	 */
	public int getLineId(){
		return lineId;
	}
	
	/**
	 * set 线ID
	 */
	public void setLineId(int lineId){
		this.lineId = lineId;
	}
	
	/**
	 * get 怪物当前血量
	 * @return 
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * set 怪物当前血量
	 */
	public void setHp(int hp){
		this.hp = hp;
	}
	
	/**
	 * get 地图模型ID
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 地图模型ID
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 上次击杀者名字
	 * @return 
	 */
	public String getKiller(){
		return killer;
	}
	
	/**
	 * set 上次击杀者名字
	 */
	public void setKiller(String killer){
		this.killer = killer;
	}
	
	/**
	 * get 重生时间
	 * @return 
	 */
	public int getReliveTime(){
		return reliveTime;
	}
	
	/**
	 * set 重生时间
	 */
	public void setReliveTime(int reliveTime){
		this.reliveTime = reliveTime;
	}
	
	/**
	 * get 座标X
	 * @return 
	 */
	public short getBirthX(){
		return birthX;
	}
	
	/**
	 * set 座标X
	 */
	public void setBirthX(short birthX){
		this.birthX = birthX;
	}
	
	/**
	 * get 座标Y
	 * @return 
	 */
	public short getBirthY(){
		return birthY;
	}
	
	/**
	 * set 座标Y
	 */
	public void setBirthY(short birthY){
		this.birthY = birthY;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//怪物状态 0未刷新 1未死亡
		buf.append("state:" + state +",");
		//怪物模型ID
		buf.append("modelId:" + modelId +",");
		//服务器ID
		buf.append("serverId:" + serverId +",");
		//线ID
		buf.append("lineId:" + lineId +",");
		//怪物当前血量
		buf.append("hp:" + hp +",");
		//地图模型ID
		buf.append("mapId:" + mapId +",");
		//上次击杀者名字
		if(this.killer!=null) buf.append("killer:" + killer.toString() +",");
		//重生时间
		buf.append("reliveTime:" + reliveTime +",");
		//座标X
		buf.append("birthX:" + birthX +",");
		//座标Y
		buf.append("birthY:" + birthY +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}