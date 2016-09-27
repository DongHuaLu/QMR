package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步公共服务器玩家全部信息返回（公共数据服务器到游戏服务器）消息
 */
public class ResSyncPlayerToDataServerMessage extends Message{

	//账号
	private String userId;
	
	//账号名字
	private String userName;
	
	//角色id
	private long playerId;
	
	//公共区角色id
	private long dataServerPlayerId;
	
	//返回结果 0-成功 1-跨服中...
	private int result;
	
	//公共游戏服务器Id
	private int serverId;
	
	//公共游戏服务器Ip
	private String serverIp;
	
	//公共游戏服务器port
	private int serverPort;
	
	//公共游戏服务器sslport
	private int serverSSLPort;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//账号
		writeString(buf, this.userId);
		//账号名字
		writeString(buf, this.userName);
		//角色id
		writeLong(buf, this.playerId);
		//公共区角色id
		writeLong(buf, this.dataServerPlayerId);
		//返回结果 0-成功 1-跨服中...
		writeInt(buf, this.result);
		//公共游戏服务器Id
		writeInt(buf, this.serverId);
		//公共游戏服务器Ip
		writeString(buf, this.serverIp);
		//公共游戏服务器port
		writeInt(buf, this.serverPort);
		//公共游戏服务器sslport
		writeInt(buf, this.serverSSLPort);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//账号
		this.userId = readString(buf);
		//账号名字
		this.userName = readString(buf);
		//角色id
		this.playerId = readLong(buf);
		//公共区角色id
		this.dataServerPlayerId = readLong(buf);
		//返回结果 0-成功 1-跨服中...
		this.result = readInt(buf);
		//公共游戏服务器Id
		this.serverId = readInt(buf);
		//公共游戏服务器Ip
		this.serverIp = readString(buf);
		//公共游戏服务器port
		this.serverPort = readInt(buf);
		//公共游戏服务器sslport
		this.serverSSLPort = readInt(buf);
		return true;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 账号名字
	 * @return 
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * set 账号名字
	 */
	public void setUserName(String userName){
		this.userName = userName;
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
	 * get 公共区角色id
	 * @return 
	 */
	public long getDataServerPlayerId(){
		return dataServerPlayerId;
	}
	
	/**
	 * set 公共区角色id
	 */
	public void setDataServerPlayerId(long dataServerPlayerId){
		this.dataServerPlayerId = dataServerPlayerId;
	}
	
	/**
	 * get 返回结果 0-成功 1-跨服中...
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 返回结果 0-成功 1-跨服中...
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	/**
	 * get 公共游戏服务器Id
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 公共游戏服务器Id
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 公共游戏服务器Ip
	 * @return 
	 */
	public String getServerIp(){
		return serverIp;
	}
	
	/**
	 * set 公共游戏服务器Ip
	 */
	public void setServerIp(String serverIp){
		this.serverIp = serverIp;
	}
	
	/**
	 * get 公共游戏服务器port
	 * @return 
	 */
	public int getServerPort(){
		return serverPort;
	}
	
	/**
	 * set 公共游戏服务器port
	 */
	public void setServerPort(int serverPort){
		this.serverPort = serverPort;
	}
	
	/**
	 * get 公共游戏服务器sslport
	 * @return 
	 */
	public int getServerSSLPort(){
		return serverSSLPort;
	}
	
	/**
	 * set 公共游戏服务器sslport
	 */
	public void setServerSSLPort(int serverSSLPort){
		this.serverSSLPort = serverSSLPort;
	}
	
	
	@Override
	public int getId() {
		return 203302;
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
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//账号名字
		if(this.userName!=null) buf.append("userName:" + userName.toString() +",");
		//角色id
		buf.append("playerId:" + playerId +",");
		//公共区角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//返回结果 0-成功 1-跨服中...
		buf.append("result:" + result +",");
		//公共游戏服务器Id
		buf.append("serverId:" + serverId +",");
		//公共游戏服务器Ip
		if(this.serverIp!=null) buf.append("serverIp:" + serverIp.toString() +",");
		//公共游戏服务器port
		buf.append("serverPort:" + serverPort +",");
		//公共游戏服务器sslport
		buf.append("serverSSLPort:" + serverSSLPort +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}