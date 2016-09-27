package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 切换地图返回消息
 */
public class ResChangeMapMessage extends Message{

	//切换地图类型
	private byte type;
	
	//地图Id
	private int mapId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//切换地图类型
		writeByte(buf, this.type);
		//地图Id
		writeInt(buf, this.mapId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//切换地图类型
		this.type = readByte(buf);
		//地图Id
		this.mapId = readInt(buf);
		return true;
	}
	
	/**
	 * get 切换地图类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 切换地图类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 地图Id
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 地图Id
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	
	@Override
	public int getId() {
		return 101117;
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
		//切换地图类型
		buf.append("type:" + type +",");
		//地图Id
		buf.append("mapId:" + mapId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}