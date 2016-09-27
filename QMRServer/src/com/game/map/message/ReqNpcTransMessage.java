package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * NPC传送消息
 */
public class ReqNpcTransMessage extends Message{

	//NPC序号
	private int npcId;
	
	//地图序号
	private int mapId;
	
	//坐标
	private com.game.structs.Position pos;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//NPC序号
		writeInt(buf, this.npcId);
		//地图序号
		writeInt(buf, this.mapId);
		//坐标
		writeBean(buf, this.pos);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//NPC序号
		this.npcId = readInt(buf);
		//地图序号
		this.mapId = readInt(buf);
		//坐标
		this.pos = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
		return true;
	}
	
	/**
	 * get NPC序号
	 * @return 
	 */
	public int getNpcId(){
		return npcId;
	}
	
	/**
	 * set NPC序号
	 */
	public void setNpcId(int npcId){
		this.npcId = npcId;
	}
	
	/**
	 * get 地图序号
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 地图序号
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 坐标
	 * @return 
	 */
	public com.game.structs.Position getPos(){
		return pos;
	}
	
	/**
	 * set 坐标
	 */
	public void setPos(com.game.structs.Position pos){
		this.pos = pos;
	}
	
	
	@Override
	public int getId() {
		return 101209;
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
		//NPC序号
		buf.append("npcId:" + npcId +",");
		//地图序号
		buf.append("mapId:" + mapId +",");
		//坐标
		if(this.pos!=null) buf.append("pos:" + pos.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}