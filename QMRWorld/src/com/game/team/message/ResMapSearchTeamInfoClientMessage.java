package com.game.team.message;

import com.game.team.bean.MapTeamInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求搜索本地图队伍信息消息
 */
public class ResMapSearchTeamInfoClientMessage extends Message{

	//当前地图队伍列表
	private List<MapTeamInfo> mapteaminfo = new ArrayList<MapTeamInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前地图队伍列表
		writeShort(buf, mapteaminfo.size());
		for (int i = 0; i < mapteaminfo.size(); i++) {
			writeBean(buf, mapteaminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前地图队伍列表
		int mapteaminfo_length = readShort(buf);
		for (int i = 0; i < mapteaminfo_length; i++) {
			mapteaminfo.add((MapTeamInfo)readBean(buf, MapTeamInfo.class));
		}
		return true;
	}
	
	/**
	 * get 当前地图队伍列表
	 * @return 
	 */
	public List<MapTeamInfo> getMapteaminfo(){
		return mapteaminfo;
	}
	
	/**
	 * set 当前地图队伍列表
	 */
	public void setMapteaminfo(List<MapTeamInfo> mapteaminfo){
		this.mapteaminfo = mapteaminfo;
	}
	
	
	@Override
	public int getId() {
		return 118108;
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
		//当前地图队伍列表
		buf.append("mapteaminfo:{");
		for (int i = 0; i < mapteaminfo.size(); i++) {
			buf.append(mapteaminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}