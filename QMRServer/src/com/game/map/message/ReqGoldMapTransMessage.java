package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 世界地图元宝传送消息
 */
public class ReqGoldMapTransMessage extends Message{

	//地图序号
	private int mapId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//地图序号
		writeInt(buf, this.mapId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//地图序号
		this.mapId = readInt(buf);
		return true;
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
	
	
	@Override
	public int getId() {
		return 101210;
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
		//地图序号
		buf.append("mapId:" + mapId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}