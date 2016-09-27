package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知网关角色创建回报消息消息
 */
public class ResRolesCreateReportToGateMessage extends Message{

	//服务器编号
	private int serverId;
	
	//服务器编号
	private int createServerId;
	
	//用户id
	private String userId;
	
	//角色id
	private long playerId;
	
	//地图模板id
	private int mapId;
	
	//角色名字
	private String playername;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务器编号
		writeInt(buf, this.serverId);
		//服务器编号
		writeInt(buf, this.createServerId);
		//用户id
		writeString(buf, this.userId);
		//角色id
		writeLong(buf, this.playerId);
		//地图模板id
		writeInt(buf, this.mapId);
		//角色名字
		writeString(buf, this.playername);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务器编号
		this.serverId = readInt(buf);
		//服务器编号
		this.createServerId = readInt(buf);
		//用户id
		this.userId = readString(buf);
		//角色id
		this.playerId = readLong(buf);
		//地图模板id
		this.mapId = readInt(buf);
		//角色名字
		this.playername = readString(buf);
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
	 * get 服务器编号
	 * @return 
	 */
	public int getCreateServerId(){
		return createServerId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setCreateServerId(int createServerId){
		this.createServerId = createServerId;
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
	
	/**
	 * get 角色名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 角色名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	
	@Override
	public int getId() {
		return 100314;
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
		//服务器编号
		buf.append("createServerId:" + createServerId +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//角色id
		buf.append("playerId:" + playerId +",");
		//地图模板id
		buf.append("mapId:" + mapId +",");
		//角色名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}