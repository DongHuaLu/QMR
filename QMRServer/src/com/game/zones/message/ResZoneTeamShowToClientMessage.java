package com.game.zones.message;

import com.game.zones.bean.ZoneTeamInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 展示副本活动信息消息
 */
public class ResZoneTeamShowToClientMessage extends Message{

	//多人副本信息
	private List<ZoneTeamInfo> zoneteaminfo = new ArrayList<ZoneTeamInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//多人副本信息
		writeShort(buf, zoneteaminfo.size());
		for (int i = 0; i < zoneteaminfo.size(); i++) {
			writeBean(buf, zoneteaminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//多人副本信息
		int zoneteaminfo_length = readShort(buf);
		for (int i = 0; i < zoneteaminfo_length; i++) {
			zoneteaminfo.add((ZoneTeamInfo)readBean(buf, ZoneTeamInfo.class));
		}
		return true;
	}
	
	/**
	 * get 多人副本信息
	 * @return 
	 */
	public List<ZoneTeamInfo> getZoneteaminfo(){
		return zoneteaminfo;
	}
	
	/**
	 * set 多人副本信息
	 */
	public void setZoneteaminfo(List<ZoneTeamInfo> zoneteaminfo){
		this.zoneteaminfo = zoneteaminfo;
	}
	
	
	@Override
	public int getId() {
		return 128116;
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
		//多人副本信息
		buf.append("zoneteaminfo:{");
		for (int i = 0; i < zoneteaminfo.size(); i++) {
			buf.append(zoneteaminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}