package com.game.activities.message;

import com.game.activities.bean.ActivityInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向世界服请求活动信息消息
 */
public class ReqActivitiesInfoToWorldMessage extends Message{

	//角色ID
	private long playerid;
	
	//活动信息
	private List<ActivityInfo> activities = new ArrayList<ActivityInfo>();
	//是否强制发送，1强制，0不强制
	private byte force;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerid);
		//活动信息
		writeShort(buf, activities.size());
		for (int i = 0; i < activities.size(); i++) {
			writeBean(buf, activities.get(i));
		}
		//是否强制发送，1强制，0不强制
		writeByte(buf, this.force);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerid = readLong(buf);
		//活动信息
		int activities_length = readShort(buf);
		for (int i = 0; i < activities_length; i++) {
			activities.add((ActivityInfo)readBean(buf, ActivityInfo.class));
		}
		//是否强制发送，1强制，0不强制
		this.force = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 活动信息
	 * @return 
	 */
	public List<ActivityInfo> getActivities(){
		return activities;
	}
	
	/**
	 * set 活动信息
	 */
	public void setActivities(List<ActivityInfo> activities){
		this.activities = activities;
	}
	
	/**
	 * get 是否强制发送，1强制，0不强制
	 * @return 
	 */
	public byte getForce(){
		return force;
	}
	
	/**
	 * set 是否强制发送，1强制，0不强制
	 */
	public void setForce(byte force){
		this.force = force;
	}
	
	
	@Override
	public int getId() {
		return 138301;
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
		//角色ID
		buf.append("playerid:" + playerid +",");
		//活动信息
		buf.append("activities:{");
		for (int i = 0; i < activities.size(); i++) {
			buf.append(activities.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//是否强制发送，1强制，0不强制
		buf.append("force:" + force +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}