package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 队伍报名取消（游戏服务器到公共数据服务器）消息
 */
public class ReqCancelTeamEnterToDataServerMessage extends Message{

	//角色id
	private long playerId;
	
	//服务器编号
	private int serverId;
	
	//服务器平台
	private String web;
	
	//队伍id
	private long teamId;
	
	//取消报名的原因，1队员下线，2队员变更
	private int reason;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//服务器编号
		writeInt(buf, this.serverId);
		//服务器平台
		writeString(buf, this.web);
		//队伍id
		writeLong(buf, this.teamId);
		//取消报名的原因，1队员下线，2队员变更
		writeInt(buf, this.reason);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//服务器编号
		this.serverId = readInt(buf);
		//服务器平台
		this.web = readString(buf);
		//队伍id
		this.teamId = readLong(buf);
		//取消报名的原因，1队员下线，2队员变更
		this.reason = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 服务器平台
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 服务器平台
	 */
	public void setWeb(String web){
		this.web = web;
	}
	
	/**
	 * get 队伍id
	 * @return 
	 */
	public long getTeamId(){
		return teamId;
	}
	
	/**
	 * set 队伍id
	 */
	public void setTeamId(long teamId){
		this.teamId = teamId;
	}
	
	/**
	 * get 取消报名的原因，1队员下线，2队员变更
	 * @return 
	 */
	public int getReason(){
		return reason;
	}
	
	/**
	 * set 取消报名的原因，1队员下线，2队员变更
	 */
	public void setReason(int reason){
		this.reason = reason;
	}
	
	
	@Override
	public int getId() {
		return 203324;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//队伍id
		buf.append("teamId:" + teamId +",");
		//取消报名的原因，1队员下线，2队员变更
		buf.append("reason:" + reason +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}