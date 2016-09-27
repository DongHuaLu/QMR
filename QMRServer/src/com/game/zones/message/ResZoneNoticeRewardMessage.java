package com.game.zones.message;

import com.game.zones.bean.ZoneRewardNumInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知前端-可领取奖励消息
 */
public class ResZoneNoticeRewardMessage extends Message{

	//通知可领取奖励数量
	private List<ZoneRewardNumInfo> zoneRewardnuminfo = new ArrayList<ZoneRewardNumInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知可领取奖励数量
		writeShort(buf, zoneRewardnuminfo.size());
		for (int i = 0; i < zoneRewardnuminfo.size(); i++) {
			writeBean(buf, zoneRewardnuminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知可领取奖励数量
		int zoneRewardnuminfo_length = readShort(buf);
		for (int i = 0; i < zoneRewardnuminfo_length; i++) {
			zoneRewardnuminfo.add((ZoneRewardNumInfo)readBean(buf, ZoneRewardNumInfo.class));
		}
		return true;
	}
	
	/**
	 * get 通知可领取奖励数量
	 * @return 
	 */
	public List<ZoneRewardNumInfo> getZoneRewardnuminfo(){
		return zoneRewardnuminfo;
	}
	
	/**
	 * set 通知可领取奖励数量
	 */
	public void setZoneRewardnuminfo(List<ZoneRewardNumInfo> zoneRewardnuminfo){
		this.zoneRewardnuminfo = zoneRewardnuminfo;
	}
	
	
	@Override
	public int getId() {
		return 128108;
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
		//通知可领取奖励数量
		buf.append("zoneRewardnuminfo:{");
		for (int i = 0; i < zoneRewardnuminfo.size(); i++) {
			buf.append(zoneRewardnuminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}