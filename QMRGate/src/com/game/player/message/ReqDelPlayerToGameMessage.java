package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除角色ToGame消息
 */
public class ReqDelPlayerToGameMessage extends Message{

	//角色ID
	private long playerId;
	
	//用户ID
	private String userId;
	
	//服务器ID
	private int createServer;
	
	//网关ID
	private int gateId;
	
	//操作IP
	private String optIp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerId);
		//用户ID
		writeString(buf, this.userId);
		//服务器ID
		writeInt(buf, this.createServer);
		//网关ID
		writeInt(buf, this.gateId);
		//操作IP
		writeString(buf, this.optIp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerId = readLong(buf);
		//用户ID
		this.userId = readString(buf);
		//服务器ID
		this.createServer = readInt(buf);
		//网关ID
		this.gateId = readInt(buf);
		//操作IP
		this.optIp = readString(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 用户ID
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 用户ID
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 服务器ID
	 * @return 
	 */
	public int getCreateServer(){
		return createServer;
	}
	
	/**
	 * set 服务器ID
	 */
	public void setCreateServer(int createServer){
		this.createServer = createServer;
	}
	
	/**
	 * get 网关ID
	 * @return 
	 */
	public int getGateId(){
		return gateId;
	}
	
	/**
	 * set 网关ID
	 */
	public void setGateId(int gateId){
		this.gateId = gateId;
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
	
	
	@Override
	public int getId() {
		return 103314;
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
		//角色ID
		buf.append("playerId:" + playerId +",");
		//用户ID
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器ID
		buf.append("createServer:" + createServer +",");
		//网关ID
		buf.append("gateId:" + gateId +",");
		//操作IP
		if(this.optIp!=null) buf.append("optIp:" + optIp.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}