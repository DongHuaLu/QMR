package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求领取信息消息
 */
public class ReqGetRewardMessage extends Message{

	//活动id
	private int activityId;
	
	//活动类型
	private int activityType;
	
	//选择奖励
	private int selected;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//活动id
		writeInt(buf, this.activityId);
		//活动类型
		writeInt(buf, this.activityType);
		//选择奖励
		writeInt(buf, this.selected);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//活动id
		this.activityId = readInt(buf);
		//活动类型
		this.activityType = readInt(buf);
		//选择奖励
		this.selected = readInt(buf);
		return true;
	}
	
	/**
	 * get 活动id
	 * @return 
	 */
	public int getActivityId(){
		return activityId;
	}
	
	/**
	 * set 活动id
	 */
	public void setActivityId(int activityId){
		this.activityId = activityId;
	}
	
	/**
	 * get 活动类型
	 * @return 
	 */
	public int getActivityType(){
		return activityType;
	}
	
	/**
	 * set 活动类型
	 */
	public void setActivityType(int activityType){
		this.activityType = activityType;
	}
	
	/**
	 * get 选择奖励
	 * @return 
	 */
	public int getSelected(){
		return selected;
	}
	
	/**
	 * set 选择奖励
	 */
	public void setSelected(int selected){
		this.selected = selected;
	}
	
	
	@Override
	public int getId() {
		return 138201;
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
		//活动id
		buf.append("activityId:" + activityId +",");
		//活动类型
		buf.append("activityType:" + activityType +",");
		//选择奖励
		buf.append("selected:" + selected +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}