package com.game.friend.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 关系信息
 */
public class RelationInfo extends Bean {

	//角色Id
	private long lgUserId;
	
	//平台VIP
	private int webvip;
	
	//角色名
	private String szName;
	
	//人气
	private int nPopularity;
	
	//等级
	private int nLevel;
	
	//人物头像
	private int nIcon;
	
	//性别
	private byte btSex;
	
	//职业
	private byte btJob;
	
	//地图Id
	private int nMapId;
	
	//心情
	private String szMood;
	
	//历史战绩
	private int nRelationDegree;
	
	//添加时间
	private int nAddTime;
	
	//状态 2 摆摊 1 在线 0 离线
	private byte btState;
	
	//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	private byte btListType;
	
	//关系排序位置
	private byte btSortIdx;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.lgUserId);
		//平台VIP
		writeInt(buf, this.webvip);
		//角色名
		writeString(buf, this.szName);
		//人气
		writeInt(buf, this.nPopularity);
		//等级
		writeInt(buf, this.nLevel);
		//人物头像
		writeInt(buf, this.nIcon);
		//性别
		writeByte(buf, this.btSex);
		//职业
		writeByte(buf, this.btJob);
		//地图Id
		writeInt(buf, this.nMapId);
		//心情
		writeString(buf, this.szMood);
		//历史战绩
		writeInt(buf, this.nRelationDegree);
		//添加时间
		writeInt(buf, this.nAddTime);
		//状态 2 摆摊 1 在线 0 离线
		writeByte(buf, this.btState);
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		writeByte(buf, this.btListType);
		//关系排序位置
		writeByte(buf, this.btSortIdx);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.lgUserId = readLong(buf);
		//平台VIP
		this.webvip = readInt(buf);
		//角色名
		this.szName = readString(buf);
		//人气
		this.nPopularity = readInt(buf);
		//等级
		this.nLevel = readInt(buf);
		//人物头像
		this.nIcon = readInt(buf);
		//性别
		this.btSex = readByte(buf);
		//职业
		this.btJob = readByte(buf);
		//地图Id
		this.nMapId = readInt(buf);
		//心情
		this.szMood = readString(buf);
		//历史战绩
		this.nRelationDegree = readInt(buf);
		//添加时间
		this.nAddTime = readInt(buf);
		//状态 2 摆摊 1 在线 0 离线
		this.btState = readByte(buf);
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		this.btListType = readByte(buf);
		//关系排序位置
		this.btSortIdx = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getLgUserId(){
		return lgUserId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setLgUserId(long lgUserId){
		this.lgUserId = lgUserId;
	}
	
	/**
	 * get 平台VIP
	 * @return 
	 */
	public int getWebvip(){
		return webvip;
	}
	
	/**
	 * set 平台VIP
	 */
	public void setWebvip(int webvip){
		this.webvip = webvip;
	}
	
	/**
	 * get 角色名
	 * @return 
	 */
	public String getSzName(){
		return szName;
	}
	
	/**
	 * set 角色名
	 */
	public void setSzName(String szName){
		this.szName = szName;
	}
	
	/**
	 * get 人气
	 * @return 
	 */
	public int getNPopularity(){
		return nPopularity;
	}
	
	/**
	 * set 人气
	 */
	public void setNPopularity(int nPopularity){
		this.nPopularity = nPopularity;
	}
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getNLevel(){
		return nLevel;
	}
	
	/**
	 * set 等级
	 */
	public void setNLevel(int nLevel){
		this.nLevel = nLevel;
	}
	
	/**
	 * get 人物头像
	 * @return 
	 */
	public int getNIcon(){
		return nIcon;
	}
	
	/**
	 * set 人物头像
	 */
	public void setNIcon(int nIcon){
		this.nIcon = nIcon;
	}
	
	/**
	 * get 性别
	 * @return 
	 */
	public byte getBtSex(){
		return btSex;
	}
	
	/**
	 * set 性别
	 */
	public void setBtSex(byte btSex){
		this.btSex = btSex;
	}
	
	/**
	 * get 职业
	 * @return 
	 */
	public byte getBtJob(){
		return btJob;
	}
	
	/**
	 * set 职业
	 */
	public void setBtJob(byte btJob){
		this.btJob = btJob;
	}
	
	/**
	 * get 地图Id
	 * @return 
	 */
	public int getNMapId(){
		return nMapId;
	}
	
	/**
	 * set 地图Id
	 */
	public void setNMapId(int nMapId){
		this.nMapId = nMapId;
	}
	
	/**
	 * get 心情
	 * @return 
	 */
	public String getSzMood(){
		return szMood;
	}
	
	/**
	 * set 心情
	 */
	public void setSzMood(String szMood){
		this.szMood = szMood;
	}
	
	/**
	 * get 历史战绩
	 * @return 
	 */
	public int getNRelationDegree(){
		return nRelationDegree;
	}
	
	/**
	 * set 历史战绩
	 */
	public void setNRelationDegree(int nRelationDegree){
		this.nRelationDegree = nRelationDegree;
	}
	
	/**
	 * get 添加时间
	 * @return 
	 */
	public int getNAddTime(){
		return nAddTime;
	}
	
	/**
	 * set 添加时间
	 */
	public void setNAddTime(int nAddTime){
		this.nAddTime = nAddTime;
	}
	
	/**
	 * get 状态 2 摆摊 1 在线 0 离线
	 * @return 
	 */
	public byte getBtState(){
		return btState;
	}
	
	/**
	 * set 状态 2 摆摊 1 在线 0 离线
	 */
	public void setBtState(byte btState){
		this.btState = btState;
	}
	
	/**
	 * get 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	 * @return 
	 */
	public byte getBtListType(){
		return btListType;
	}
	
	/**
	 * set 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	 */
	public void setBtListType(byte btListType){
		this.btListType = btListType;
	}
	
	/**
	 * get 关系排序位置
	 * @return 
	 */
	public byte getBtSortIdx(){
		return btSortIdx;
	}
	
	/**
	 * set 关系排序位置
	 */
	public void setBtSortIdx(byte btSortIdx){
		this.btSortIdx = btSortIdx;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("lgUserId:" + lgUserId +",");
		//平台VIP
		buf.append("webvip:" + webvip +",");
		//角色名
		if(this.szName!=null) buf.append("szName:" + szName.toString() +",");
		//人气
		buf.append("nPopularity:" + nPopularity +",");
		//等级
		buf.append("nLevel:" + nLevel +",");
		//人物头像
		buf.append("nIcon:" + nIcon +",");
		//性别
		buf.append("btSex:" + btSex +",");
		//职业
		buf.append("btJob:" + btJob +",");
		//地图Id
		buf.append("nMapId:" + nMapId +",");
		//心情
		if(this.szMood!=null) buf.append("szMood:" + szMood.toString() +",");
		//历史战绩
		buf.append("nRelationDegree:" + nRelationDegree +",");
		//添加时间
		buf.append("nAddTime:" + nAddTime +",");
		//状态 2 摆摊 1 在线 0 离线
		buf.append("btState:" + btState +",");
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		buf.append("btListType:" + btListType +",");
		//关系排序位置
		buf.append("btSortIdx:" + btSortIdx +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}