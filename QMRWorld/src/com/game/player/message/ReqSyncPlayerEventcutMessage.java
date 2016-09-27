package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家连斩数量消息
 */
public class ReqSyncPlayerEventcutMessage extends Message{

	//角色id
	private long playerId;
	
	//角色连斩数量
	private int eventcut;
	
	//角色连斩数量时间
	private long eventcutTime;
	
	//角色连斩地图
	private int mapModelId;
	
	//角色连斩怪物
	private int monsterModelId;
	
	//角色连斩地图X
	private short mapX;
	
	//角色连斩地图Y
	private short mapY;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色连斩数量
		writeInt(buf, this.eventcut);
		//角色连斩数量时间
		writeLong(buf, this.eventcutTime);
		//角色连斩地图
		writeInt(buf, this.mapModelId);
		//角色连斩怪物
		writeInt(buf, this.monsterModelId);
		//角色连斩地图X
		writeShort(buf, this.mapX);
		//角色连斩地图Y
		writeShort(buf, this.mapY);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色连斩数量
		this.eventcut = readInt(buf);
		//角色连斩数量时间
		this.eventcutTime = readLong(buf);
		//角色连斩地图
		this.mapModelId = readInt(buf);
		//角色连斩怪物
		this.monsterModelId = readInt(buf);
		//角色连斩地图X
		this.mapX = readShort(buf);
		//角色连斩地图Y
		this.mapY = readShort(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 角色连斩数量
	 * @return 
	 */
	public int getEventcut(){
		return eventcut;
	}
	
	/**
	 * set 角色连斩数量
	 */
	public void setEventcut(int eventcut){
		this.eventcut = eventcut;
	}
	
	/**
	 * get 角色连斩数量时间
	 * @return 
	 */
	public long getEventcutTime(){
		return eventcutTime;
	}
	
	/**
	 * set 角色连斩数量时间
	 */
	public void setEventcutTime(long eventcutTime){
		this.eventcutTime = eventcutTime;
	}
	
	/**
	 * get 角色连斩地图
	 * @return 
	 */
	public int getMapModelId(){
		return mapModelId;
	}
	
	/**
	 * set 角色连斩地图
	 */
	public void setMapModelId(int mapModelId){
		this.mapModelId = mapModelId;
	}
	
	/**
	 * get 角色连斩怪物
	 * @return 
	 */
	public int getMonsterModelId(){
		return monsterModelId;
	}
	
	/**
	 * set 角色连斩怪物
	 */
	public void setMonsterModelId(int monsterModelId){
		this.monsterModelId = monsterModelId;
	}
	
	/**
	 * get 角色连斩地图X
	 * @return 
	 */
	public short getMapX(){
		return mapX;
	}
	
	/**
	 * set 角色连斩地图X
	 */
	public void setMapX(short mapX){
		this.mapX = mapX;
	}
	
	/**
	 * get 角色连斩地图Y
	 * @return 
	 */
	public short getMapY(){
		return mapY;
	}
	
	/**
	 * set 角色连斩地图Y
	 */
	public void setMapY(short mapY){
		this.mapY = mapY;
	}
	
	
	@Override
	public int getId() {
		return 103320;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//角色连斩数量
		buf.append("eventcut:" + eventcut +",");
		//角色连斩数量时间
		buf.append("eventcutTime:" + eventcutTime +",");
		//角色连斩地图
		buf.append("mapModelId:" + mapModelId +",");
		//角色连斩怪物
		buf.append("monsterModelId:" + monsterModelId +",");
		//角色连斩地图X
		buf.append("mapX:" + mapX +",");
		//角色连斩地图Y
		buf.append("mapY:" + mapY +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}