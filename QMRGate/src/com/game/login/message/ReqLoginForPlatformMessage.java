package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 登陆消息
 */
public class ReqLoginForPlatformMessage extends Message{

	//服务器Id
	private String serverId;
	
	//玩家用户名
	private String username;
	
	//平台标识
	private String agent;
	
	//服务器Id
	private String ad;
	
	//平台服务器的LINUX时间戳(为长整数, 单位为秒)
	private String time;
	
	//1成年,0未成年,-1未知
	private String isadult;
	
	//全小写MD5验证码
	private String sign;
	
	//
	private String localref;
	
	//
	private String reserva1;
	
	//
	private String reserva2;
	
	//登陆类型
	private String logintype;
	
	//平台数据
	private String agentPlusdata;
	
	//平台数据
	private String agentColdatas;
	
	//平台数据
	private String adregtime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器Id
		writeString(buf, this.serverId);
		//玩家用户名
		writeString(buf, this.username);
		//平台标识
		writeString(buf, this.agent);
		//服务器Id
		writeString(buf, this.ad);
		//平台服务器的LINUX时间戳(为长整数, 单位为秒)
		writeString(buf, this.time);
		//1成年,0未成年,-1未知
		writeString(buf, this.isadult);
		//全小写MD5验证码
		writeString(buf, this.sign);
		//
		writeString(buf, this.localref);
		//
		writeString(buf, this.reserva1);
		//
		writeString(buf, this.reserva2);
		//登陆类型
		writeString(buf, this.logintype);
		//平台数据
		writeString(buf, this.agentPlusdata);
		//平台数据
		writeString(buf, this.agentColdatas);
		//平台数据
		writeString(buf, this.adregtime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器Id
		this.serverId = readString(buf);
		//玩家用户名
		this.username = readString(buf);
		//平台标识
		this.agent = readString(buf);
		//服务器Id
		this.ad = readString(buf);
		//平台服务器的LINUX时间戳(为长整数, 单位为秒)
		this.time = readString(buf);
		//1成年,0未成年,-1未知
		this.isadult = readString(buf);
		//全小写MD5验证码
		this.sign = readString(buf);
		//
		this.localref = readString(buf);
		//
		this.reserva1 = readString(buf);
		//
		this.reserva2 = readString(buf);
		//登陆类型
		this.logintype = readString(buf);
		//平台数据
		this.agentPlusdata = readString(buf);
		//平台数据
		this.agentColdatas = readString(buf);
		//平台数据
		this.adregtime = readString(buf);
		return true;
	}
	
	/**
	 * get 服务器Id
	 * @return 
	 */
	public String getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器Id
	 */
	public void setServerId(String serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 玩家用户名
	 * @return 
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * set 玩家用户名
	 */
	public void setUsername(String username){
		this.username = username;
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
	 * get 平台服务器的LINUX时间戳(为长整数, 单位为秒)
	 * @return 
	 */
	public String getTime(){
		return time;
	}
	
	/**
	 * set 平台服务器的LINUX时间戳(为长整数, 单位为秒)
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * get 1成年,0未成年,-1未知
	 * @return 
	 */
	public String getIsadult(){
		return isadult;
	}
	
	/**
	 * set 1成年,0未成年,-1未知
	 */
	public void setIsadult(String isadult){
		this.isadult = isadult;
	}
	
	/**
	 * get 全小写MD5验证码
	 * @return 
	 */
	public String getSign(){
		return sign;
	}
	
	/**
	 * set 全小写MD5验证码
	 */
	public void setSign(String sign){
		this.sign = sign;
	}
	
	/**
	 * get 
	 * @return 
	 */
	public String getLocalref(){
		return localref;
	}
	
	/**
	 * set 
	 */
	public void setLocalref(String localref){
		this.localref = localref;
	}
	
	/**
	 * get 
	 * @return 
	 */
	public String getReserva1(){
		return reserva1;
	}
	
	/**
	 * set 
	 */
	public void setReserva1(String reserva1){
		this.reserva1 = reserva1;
	}
	
	/**
	 * get 
	 * @return 
	 */
	public String getReserva2(){
		return reserva2;
	}
	
	/**
	 * set 
	 */
	public void setReserva2(String reserva2){
		this.reserva2 = reserva2;
	}
	
	/**
	 * get 登陆类型
	 * @return 
	 */
	public String getLogintype(){
		return logintype;
	}
	
	/**
	 * set 登陆类型
	 */
	public void setLogintype(String logintype){
		this.logintype = logintype;
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
	
	/**
	 * get 平台数据
	 * @return 
	 */
	public String getAdregtime(){
		return adregtime;
	}
	
	/**
	 * set 平台数据
	 */
	public void setAdregtime(String adregtime){
		this.adregtime = adregtime;
	}
	
	
	@Override
	public int getId() {
		return 100208;
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
		//服务器Id
		if(this.serverId!=null) buf.append("serverId:" + serverId.toString() +",");
		//玩家用户名
		if(this.username!=null) buf.append("username:" + username.toString() +",");
		//平台标识
		if(this.agent!=null) buf.append("agent:" + agent.toString() +",");
		//服务器Id
		if(this.ad!=null) buf.append("ad:" + ad.toString() +",");
		//平台服务器的LINUX时间戳(为长整数, 单位为秒)
		if(this.time!=null) buf.append("time:" + time.toString() +",");
		//1成年,0未成年,-1未知
		if(this.isadult!=null) buf.append("isadult:" + isadult.toString() +",");
		//全小写MD5验证码
		if(this.sign!=null) buf.append("sign:" + sign.toString() +",");
		//
		if(this.localref!=null) buf.append("localref:" + localref.toString() +",");
		//
		if(this.reserva1!=null) buf.append("reserva1:" + reserva1.toString() +",");
		//
		if(this.reserva2!=null) buf.append("reserva2:" + reserva2.toString() +",");
		//登陆类型
		if(this.logintype!=null) buf.append("logintype:" + logintype.toString() +",");
		//平台数据
		if(this.agentPlusdata!=null) buf.append("agentPlusdata:" + agentPlusdata.toString() +",");
		//平台数据
		if(this.agentColdatas!=null) buf.append("agentColdatas:" + agentColdatas.toString() +",");
		//平台数据
		if(this.adregtime!=null) buf.append("adregtime:" + adregtime.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}