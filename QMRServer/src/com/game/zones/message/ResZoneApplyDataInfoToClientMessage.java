package com.game.zones.message;

import com.game.zones.bean.ZoneApplyDataInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送多人副本报名名单（单个人报名）消息
 */
public class ResZoneApplyDataInfoToClientMessage extends Message{

	//多人副本报名名单
	private ZoneApplyDataInfo zoneapplydatainfo;
	
	//类型：0第一次报名，1其他人加入，2人数足够
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//多人副本报名名单
		writeBean(buf, this.zoneapplydatainfo);
		//类型：0第一次报名，1其他人加入，2人数足够
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//多人副本报名名单
		this.zoneapplydatainfo = (ZoneApplyDataInfo)readBean(buf, ZoneApplyDataInfo.class);
		//类型：0第一次报名，1其他人加入，2人数足够
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 多人副本报名名单
	 * @return 
	 */
	public ZoneApplyDataInfo getZoneapplydatainfo(){
		return zoneapplydatainfo;
	}
	
	/**
	 * set 多人副本报名名单
	 */
	public void setZoneapplydatainfo(ZoneApplyDataInfo zoneapplydatainfo){
		this.zoneapplydatainfo = zoneapplydatainfo;
	}
	
	/**
	 * get 类型：0第一次报名，1其他人加入，2人数足够
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型：0第一次报名，1其他人加入，2人数足够
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 128120;
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
		//多人副本报名名单
		if(this.zoneapplydatainfo!=null) buf.append("zoneapplydatainfo:" + zoneapplydatainfo.toString() +",");
		//类型：0第一次报名，1其他人加入，2人数足够
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}