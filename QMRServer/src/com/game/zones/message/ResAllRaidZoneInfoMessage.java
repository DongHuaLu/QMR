package com.game.zones.message;

import com.game.zones.bean.RaidZoneInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送所有扫荡副本信息消息
 */
public class ResAllRaidZoneInfoMessage extends Message{

	//所有扫荡副本信息
	private List<RaidZoneInfo> raidzoneinfolist = new ArrayList<RaidZoneInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//所有扫荡副本信息
		writeShort(buf, raidzoneinfolist.size());
		for (int i = 0; i < raidzoneinfolist.size(); i++) {
			writeBean(buf, raidzoneinfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//所有扫荡副本信息
		int raidzoneinfolist_length = readShort(buf);
		for (int i = 0; i < raidzoneinfolist_length; i++) {
			raidzoneinfolist.add((RaidZoneInfo)readBean(buf, RaidZoneInfo.class));
		}
		return true;
	}
	
	/**
	 * get 所有扫荡副本信息
	 * @return 
	 */
	public List<RaidZoneInfo> getRaidzoneinfolist(){
		return raidzoneinfolist;
	}
	
	/**
	 * set 所有扫荡副本信息
	 */
	public void setRaidzoneinfolist(List<RaidZoneInfo> raidzoneinfolist){
		this.raidzoneinfolist = raidzoneinfolist;
	}
	
	
	@Override
	public int getId() {
		return 128106;
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
		//所有扫荡副本信息
		buf.append("raidzoneinfolist:{");
		for (int i = 0; i < raidzoneinfolist.size(); i++) {
			buf.append(raidzoneinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}