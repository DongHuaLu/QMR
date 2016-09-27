package com.game.team.message;

import com.game.team.bean.MapPlayerInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求搜索本地图玩家信息消息
 */
public class ResMapSearchPlayerInfoClientMessage extends Message{

	//当前地图玩家列表
	private List<MapPlayerInfo> mapplayerinfo = new ArrayList<MapPlayerInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前地图玩家列表
		writeShort(buf, mapplayerinfo.size());
		for (int i = 0; i < mapplayerinfo.size(); i++) {
			writeBean(buf, mapplayerinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前地图玩家列表
		int mapplayerinfo_length = readShort(buf);
		for (int i = 0; i < mapplayerinfo_length; i++) {
			mapplayerinfo.add((MapPlayerInfo)readBean(buf, MapPlayerInfo.class));
		}
		return true;
	}
	
	/**
	 * get 当前地图玩家列表
	 * @return 
	 */
	public List<MapPlayerInfo> getMapplayerinfo(){
		return mapplayerinfo;
	}
	
	/**
	 * set 当前地图玩家列表
	 */
	public void setMapplayerinfo(List<MapPlayerInfo> mapplayerinfo){
		this.mapplayerinfo = mapplayerinfo;
	}
	
	
	@Override
	public int getId() {
		return 118109;
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
		//当前地图玩家列表
		buf.append("mapplayerinfo:{");
		for (int i = 0; i < mapplayerinfo.size(); i++) {
			buf.append(mapplayerinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}