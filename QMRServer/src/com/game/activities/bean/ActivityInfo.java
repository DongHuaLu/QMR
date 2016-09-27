package com.game.activities.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 活动信息
 */
public class ActivityInfo extends Bean {

	//活动id
	private int activityId;
	
	//活动类型
	private int activityType;
	
	//活动描述
	private String activityDescribe;
	
	//活动奖励
	private String activityReward;
	
	//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
	private int status;
	
	//活动持续时间 单位:秒  0表示没有结束时间
	private int duration;
	
	//是否推荐
	private int recommend;
	
	//开始时间
	private int starttime;
	
	//结束时间
	private int endtime;
	
	//是否可重复领取
	private int canrepeated;
	
	//可领取次数
	private int canreceive;
	
	//分组类型
	private int grouptype;
	
	//标识
	private String sign;
	
	//扩展属性
	private List<String> infoExpandList = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//活动id
		writeInt(buf, this.activityId);
		//活动类型
		writeInt(buf, this.activityType);
		//活动描述
		writeString(buf, this.activityDescribe);
		//活动奖励
		writeString(buf, this.activityReward);
		//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		writeInt(buf, this.status);
		//活动持续时间 单位:秒  0表示没有结束时间
		writeInt(buf, this.duration);
		//是否推荐
		writeInt(buf, this.recommend);
		//开始时间
		writeInt(buf, this.starttime);
		//结束时间
		writeInt(buf, this.endtime);
		//是否可重复领取
		writeInt(buf, this.canrepeated);
		//可领取次数
		writeInt(buf, this.canreceive);
		//分组类型
		writeInt(buf, this.grouptype);
		//标识
		writeString(buf, this.sign);
		//扩展属性
		writeShort(buf, infoExpandList.size());
		for (int i = 0; i < infoExpandList.size(); i++) {
			writeString(buf, infoExpandList.get(i));
		}
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
		//活动描述
		this.activityDescribe = readString(buf);
		//活动奖励
		this.activityReward = readString(buf);
		//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		this.status = readInt(buf);
		//活动持续时间 单位:秒  0表示没有结束时间
		this.duration = readInt(buf);
		//是否推荐
		this.recommend = readInt(buf);
		//开始时间
		this.starttime = readInt(buf);
		//结束时间
		this.endtime = readInt(buf);
		//是否可重复领取
		this.canrepeated = readInt(buf);
		//可领取次数
		this.canreceive = readInt(buf);
		//分组类型
		this.grouptype = readInt(buf);
		//标识
		this.sign = readString(buf);
		//扩展属性
		int infoExpandList_length = readShort(buf);
		for (int i = 0; i < infoExpandList_length; i++) {
			infoExpandList.add(readString(buf));
		}
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
	 * get 活动描述
	 * @return 
	 */
	public String getActivityDescribe(){
		return activityDescribe;
	}
	
	/**
	 * set 活动描述
	 */
	public void setActivityDescribe(String activityDescribe){
		this.activityDescribe = activityDescribe;
	}
	
	/**
	 * get 活动奖励
	 * @return 
	 */
	public String getActivityReward(){
		return activityReward;
	}
	
	/**
	 * set 活动奖励
	 */
	public void setActivityReward(String activityReward){
		this.activityReward = activityReward;
	}
	
	/**
	 * get 是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
	 * @return 
	 */
	public int getStatus(){
		return status;
	}
	
	/**
	 * set 是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
	 */
	public void setStatus(int status){
		this.status = status;
	}
	
	/**
	 * get 活动持续时间 单位:秒  0表示没有结束时间
	 * @return 
	 */
	public int getDuration(){
		return duration;
	}
	
	/**
	 * set 活动持续时间 单位:秒  0表示没有结束时间
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/**
	 * get 是否推荐
	 * @return 
	 */
	public int getRecommend(){
		return recommend;
	}
	
	/**
	 * set 是否推荐
	 */
	public void setRecommend(int recommend){
		this.recommend = recommend;
	}
	
	/**
	 * get 开始时间
	 * @return 
	 */
	public int getStarttime(){
		return starttime;
	}
	
	/**
	 * set 开始时间
	 */
	public void setStarttime(int starttime){
		this.starttime = starttime;
	}
	
	/**
	 * get 结束时间
	 * @return 
	 */
	public int getEndtime(){
		return endtime;
	}
	
	/**
	 * set 结束时间
	 */
	public void setEndtime(int endtime){
		this.endtime = endtime;
	}
	
	/**
	 * get 是否可重复领取
	 * @return 
	 */
	public int getCanrepeated(){
		return canrepeated;
	}
	
	/**
	 * set 是否可重复领取
	 */
	public void setCanrepeated(int canrepeated){
		this.canrepeated = canrepeated;
	}
	
	/**
	 * get 可领取次数
	 * @return 
	 */
	public int getCanreceive(){
		return canreceive;
	}
	
	/**
	 * set 可领取次数
	 */
	public void setCanreceive(int canreceive){
		this.canreceive = canreceive;
	}
	
	/**
	 * get 分组类型
	 * @return 
	 */
	public int getGrouptype(){
		return grouptype;
	}
	
	/**
	 * set 分组类型
	 */
	public void setGrouptype(int grouptype){
		this.grouptype = grouptype;
	}
	
	/**
	 * get 标识
	 * @return 
	 */
	public String getSign(){
		return sign;
	}
	
	/**
	 * set 标识
	 */
	public void setSign(String sign){
		this.sign = sign;
	}
	
	/**
	 * get 扩展属性
	 * @return 
	 */
	public List<String> getInfoExpandList(){
		return infoExpandList;
	}
	
	/**
	 * set 扩展属性
	 */
	public void setInfoExpandList(List<String> infoExpandList){
		this.infoExpandList = infoExpandList;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//活动id
		buf.append("activityId:" + activityId +",");
		//活动类型
		buf.append("activityType:" + activityType +",");
		//活动描述
		if(this.activityDescribe!=null) buf.append("activityDescribe:" + activityDescribe.toString() +",");
		//活动奖励
		if(this.activityReward!=null) buf.append("activityReward:" + activityReward.toString() +",");
		//是否领取，0已经领取，1可领取， 2不可领取  3到世界服判断
		buf.append("status:" + status +",");
		//活动持续时间 单位:秒  0表示没有结束时间
		buf.append("duration:" + duration +",");
		//是否推荐
		buf.append("recommend:" + recommend +",");
		//开始时间
		buf.append("starttime:" + starttime +",");
		//结束时间
		buf.append("endtime:" + endtime +",");
		//是否可重复领取
		buf.append("canrepeated:" + canrepeated +",");
		//可领取次数
		buf.append("canreceive:" + canreceive +",");
		//分组类型
		buf.append("grouptype:" + grouptype +",");
		//标识
		if(this.sign!=null) buf.append("sign:" + sign.toString() +",");
		//扩展属性
		buf.append("infoExpandList:{");
		for (int i = 0; i < infoExpandList.size(); i++) {
			buf.append(infoExpandList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}