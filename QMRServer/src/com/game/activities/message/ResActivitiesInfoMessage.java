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
 * 返回活动信息消息
 */
public class ResActivitiesInfoMessage extends Message{

	//活动信息
	private List<ActivityInfo> activities = new ArrayList<ActivityInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//活动信息
		writeShort(buf, activities.size());
		for (int i = 0; i < activities.size(); i++) {
			writeBean(buf, activities.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//活动信息
		int activities_length = readShort(buf);
		for (int i = 0; i < activities_length; i++) {
			activities.add((ActivityInfo)readBean(buf, ActivityInfo.class));
		}
		return true;
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
	
	
	@Override
	public int getId() {
		return 138101;
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
		//活动信息
		buf.append("activities:{");
		for (int i = 0; i < activities.size(); i++) {
			buf.append(activities.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}