package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步怪物请求消息
 */
public class ReqMonsterSyncMessage extends Message{

	//服务器编号
	private int serverId;
	
	//线ID
	private int lineId;
	
	//地图模型ID
	private int mapmodelid;
	
	//出生点X
	private short birthX;
	
	//出生点Y
	private short birthY;
	
	//角色Id
	private long monsterId;
	
	//模板Id
	private int modelId;
	
	//当前HP
	private int currentHp;
	
	//最大HP
	private int maxHp;
	
	//杀死者名字
	private String killer;
	
	//复活时间
	private long revive;
	
	//状态 1-复活 2-死亡
	private int state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器编号
		writeInt(buf, this.serverId);
		//线ID
		writeInt(buf, this.lineId);
		//地图模型ID
		writeInt(buf, this.mapmodelid);
		//出生点X
		writeShort(buf, this.birthX);
		//出生点Y
		writeShort(buf, this.birthY);
		//角色Id
		writeLong(buf, this.monsterId);
		//模板Id
		writeInt(buf, this.modelId);
		//当前HP
		writeInt(buf, this.currentHp);
		//最大HP
		writeInt(buf, this.maxHp);
		//杀死者名字
		writeString(buf, this.killer);
		//复活时间
		writeLong(buf, this.revive);
		//状态 1-复活 2-死亡
		writeInt(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器编号
		this.serverId = readInt(buf);
		//线ID
		this.lineId = readInt(buf);
		//地图模型ID
		this.mapmodelid = readInt(buf);
		//出生点X
		this.birthX = readShort(buf);
		//出生点Y
		this.birthY = readShort(buf);
		//角色Id
		this.monsterId = readLong(buf);
		//模板Id
		this.modelId = readInt(buf);
		//当前HP
		this.currentHp = readInt(buf);
		//最大HP
		this.maxHp = readInt(buf);
		//杀死者名字
		this.killer = readString(buf);
		//复活时间
		this.revive = readLong(buf);
		//状态 1-复活 2-死亡
		this.state = readInt(buf);
		return true;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器编号
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
	 * get 地图模型ID
	 * @return 
	 */
	public int getMapmodelid(){
		return mapmodelid;
	}
	
	/**
	 * set 地图模型ID
	 */
	public void setMapmodelid(int mapmodelid){
		this.mapmodelid = mapmodelid;
	}
	
	/**
	 * get 出生点X
	 * @return 
	 */
	public short getBirthX(){
		return birthX;
	}
	
	/**
	 * set 出生点X
	 */
	public void setBirthX(short birthX){
		this.birthX = birthX;
	}
	
	/**
	 * get 出生点Y
	 * @return 
	 */
	public short getBirthY(){
		return birthY;
	}
	
	/**
	 * set 出生点Y
	 */
	public void setBirthY(short birthY){
		this.birthY = birthY;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
	}
	
	/**
	 * get 模板Id
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 模板Id
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	/**
	 * get 当前HP
	 * @return 
	 */
	public int getCurrentHp(){
		return currentHp;
	}
	
	/**
	 * set 当前HP
	 */
	public void setCurrentHp(int currentHp){
		this.currentHp = currentHp;
	}
	
	/**
	 * get 最大HP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 最大HP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	/**
	 * get 杀死者名字
	 * @return 
	 */
	public String getKiller(){
		return killer;
	}
	
	/**
	 * set 杀死者名字
	 */
	public void setKiller(String killer){
		this.killer = killer;
	}
	
	/**
	 * get 复活时间
	 * @return 
	 */
	public long getRevive(){
		return revive;
	}
	
	/**
	 * set 复活时间
	 */
	public void setRevive(long revive){
		this.revive = revive;
	}
	
	/**
	 * get 状态 1-复活 2-死亡
	 * @return 
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * set 状态 1-复活 2-死亡
	 */
	public void setState(int state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 114301;
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
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//线ID
		buf.append("lineId:" + lineId +",");
		//地图模型ID
		buf.append("mapmodelid:" + mapmodelid +",");
		//出生点X
		buf.append("birthX:" + birthX +",");
		//出生点Y
		buf.append("birthY:" + birthY +",");
		//角色Id
		buf.append("monsterId:" + monsterId +",");
		//模板Id
		buf.append("modelId:" + modelId +",");
		//当前HP
		buf.append("currentHp:" + currentHp +",");
		//最大HP
		buf.append("maxHp:" + maxHp +",");
		//杀死者名字
		if(this.killer!=null) buf.append("killer:" + killer.toString() +",");
		//复活时间
		buf.append("revive:" + revive +",");
		//状态 1-复活 2-死亡
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}