package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向游戏服务器请求创建角色消息
 */
public class ReqCreateCharacterToGameMessage extends Message{

	//网关编号
	private int gateId;
	
	//创建服务器id
	private int createServer;
	
	//用户id
	private String userId;
	
	//用户名字
	private String userName;
	
	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private byte sex;
	
	//是否自动生成
	private byte auto;
	
	//角色头像
	private String icon;
	
	//1成年,0未成年,-1未知
	private byte isAdult;
	
	//1是,0否
	private byte isGuest;
	
	//操作IP
	private String optIp;
	
	//登陆类型
	private int logintype;
	
	//平台标识
	private String agent;
	
	//服务器Id
	private String ad;
	
	//平台数据
	private String agentPlusdata;
	
	//平台数据
	private String agentColdatas;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//网关编号
		writeInt(buf, this.gateId);
		//创建服务器id
		writeInt(buf, this.createServer);
		//用户id
		writeString(buf, this.userId);
		//用户名字
		writeString(buf, this.userName);
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeByte(buf, this.sex);
		//是否自动生成
		writeByte(buf, this.auto);
		//角色头像
		writeString(buf, this.icon);
		//1成年,0未成年,-1未知
		writeByte(buf, this.isAdult);
		//1是,0否
		writeByte(buf, this.isGuest);
		//操作IP
		writeString(buf, this.optIp);
		//登陆类型
		writeInt(buf, this.logintype);
		//平台标识
		writeString(buf, this.agent);
		//服务器Id
		writeString(buf, this.ad);
		//平台数据
		writeString(buf, this.agentPlusdata);
		//平台数据
		writeString(buf, this.agentColdatas);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//网关编号
		this.gateId = readInt(buf);
		//创建服务器id
		this.createServer = readInt(buf);
		//用户id
		this.userId = readString(buf);
		//用户名字
		this.userName = readString(buf);
		//角色名字
		this.name = readString(buf);
		//角色性别 1-男 2-女
		this.sex = readByte(buf);
		//是否自动生成
		this.auto = readByte(buf);
		//角色头像
		this.icon = readString(buf);
		//1成年,0未成年,-1未知
		this.isAdult = readByte(buf);
		//1是,0否
		this.isGuest = readByte(buf);
		//操作IP
		this.optIp = readString(buf);
		//登陆类型
		this.logintype = readInt(buf);
		//平台标识
		this.agent = readString(buf);
		//服务器Id
		this.ad = readString(buf);
		//平台数据
		this.agentPlusdata = readString(buf);
		//平台数据
		this.agentColdatas = readString(buf);
		return true;
	}
	
	/**
	 * get 网关编号
	 * @return 
	 */
	public int getGateId(){
		return gateId;
	}
	
	/**
	 * set 网关编号
	 */
	public void setGateId(int gateId){
		this.gateId = gateId;
	}
	
	/**
	 * get 创建服务器id
	 * @return 
	 */
	public int getCreateServer(){
		return createServer;
	}
	
	/**
	 * set 创建服务器id
	 */
	public void setCreateServer(int createServer){
		this.createServer = createServer;
	}
	
	/**
	 * get 用户id
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 用户名字
	 * @return 
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * set 用户名字
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * get 角色名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 角色名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 角色性别 1-男 2-女
	 * @return 
	 */
	public byte getSex(){
		return sex;
	}
	
	/**
	 * set 角色性别 1-男 2-女
	 */
	public void setSex(byte sex){
		this.sex = sex;
	}
	
	/**
	 * get 是否自动生成
	 * @return 
	 */
	public byte getAuto(){
		return auto;
	}
	
	/**
	 * set 是否自动生成
	 */
	public void setAuto(byte auto){
		this.auto = auto;
	}
	
	/**
	 * get 角色头像
	 * @return 
	 */
	public String getIcon(){
		return icon;
	}
	
	/**
	 * set 角色头像
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	/**
	 * get 1成年,0未成年,-1未知
	 * @return 
	 */
	public byte getIsAdult(){
		return isAdult;
	}
	
	/**
	 * set 1成年,0未成年,-1未知
	 */
	public void setIsAdult(byte isAdult){
		this.isAdult = isAdult;
	}
	
	/**
	 * get 1是,0否
	 * @return 
	 */
	public byte getIsGuest(){
		return isGuest;
	}
	
	/**
	 * set 1是,0否
	 */
	public void setIsGuest(byte isGuest){
		this.isGuest = isGuest;
	}
	
	/**
	 * get 操作IP
	 * @return 
	 */
	public String getOptIp(){
		return optIp;
	}
	
	/**
	 * set 操作IP
	 */
	public void setOptIp(String optIp){
		this.optIp = optIp;
	}
	
	/**
	 * get 登陆类型
	 * @return 
	 */
	public int getLogintype(){
		return logintype;
	}
	
	/**
	 * set 登陆类型
	 */
	public void setLogintype(int logintype){
		this.logintype = logintype;
	}
	
	/**
	 * get 平台标识
	 * @return 
	 */
	public String getAgent(){
		return agent;
	}
	
	/**
	 * set 平台标识
	 */
	public void setAgent(String agent){
		this.agent = agent;
	}
	
	/**
	 * get 服务器Id
	 * @return 
	 */
	public String getAd(){
		return ad;
	}
	
	/**
	 * set 服务器Id
	 */
	public void setAd(String ad){
		this.ad = ad;
	}
	
	/**
	 * get 平台数据
	 * @return 
	 */
	public String getAgentPlusdata(){
		return agentPlusdata;
	}
	
	/**
	 * set 平台数据
	 */
	public void setAgentPlusdata(String agentPlusdata){
		this.agentPlusdata = agentPlusdata;
	}
	
	/**
	 * get 平台数据
	 * @return 
	 */
	public String getAgentColdatas(){
		return agentColdatas;
	}
	
	/**
	 * set 平台数据
	 */
	public void setAgentColdatas(String agentColdatas){
		this.agentColdatas = agentColdatas;
	}
	
	
	@Override
	public int getId() {
		return 100304;
	}

	@Override
	public String getQueue() {
		return "Local";
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//网关编号
		buf.append("gateId:" + gateId +",");
		//创建服务器id
		buf.append("createServer:" + createServer +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//用户名字
		if(this.userName!=null) buf.append("userName:" + userName.toString() +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别 1-男 2-女
		buf.append("sex:" + sex +",");
		//是否自动生成
		buf.append("auto:" + auto +",");
		//角色头像
		if(this.icon!=null) buf.append("icon:" + icon.toString() +",");
		//1成年,0未成年,-1未知
		buf.append("isAdult:" + isAdult +",");
		//1是,0否
		buf.append("isGuest:" + isGuest +",");
		//操作IP
		if(this.optIp!=null) buf.append("optIp:" + optIp.toString() +",");
		//登陆类型
		buf.append("logintype:" + logintype +",");
		//平台标识
		if(this.agent!=null) buf.append("agent:" + agent.toString() +",");
		//服务器Id
		if(this.ad!=null) buf.append("ad:" + ad.toString() +",");
		//平台数据
		if(this.agentPlusdata!=null) buf.append("agentPlusdata:" + agentPlusdata.toString() +",");
		//平台数据
		if(this.agentColdatas!=null) buf.append("agentColdatas:" + agentColdatas.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}