package com.game.publogin.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知网关服务器登录成功消息
 */
public class ResLoginSuccessToPublicGateMessage extends Message{

	//服务器编号
	private int serverId;
	
	//来源平台
	private String web;
	
	//用户id
	private String userId;
	
	//角色id
	private long playerId;
	
	//地图模板id
	private int mapId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器编号
		writeInt(buf, this.serverId);
		//来源平台
		writeString(buf, this.web);
		//用户id
		writeString(buf, this.userId);
		//角色id
		writeLong(buf, this.playerId);
		//地图模板id
		writeInt(buf, this.mapId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器编号
		this.serverId = readInt(buf);
		//来源平台
		this.web = readString(buf);
		//用户id
		this.userId = readString(buf);
		//角色id
		this.playerId = readLong(buf);
		//地图模板id
		this.mapId = readInt(buf);
		return true;
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
	 * get 来源平台
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 来源平台
	 */
	public void setWeb(String web){
		this.web = web;
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
	 * get 地图模板id
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 地图模板id
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	
	@Override
	public int getId() {
		return 204302;
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
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//来源平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//角色id
		buf.append("playerId:" + playerId +",");
		//地图模板id
		buf.append("mapId:" + mapId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}