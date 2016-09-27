package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 登陆玩家失败消息
 */
public class ResReloginCharacterMessage extends Message{

	//角色id
	private long playerId;
	
	//服务器编号
	private int createServerId;
	
	//用户id
	private String userId;
	
	//1成年,0未成年,-1未知
	private byte isAdult;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//服务器编号
		writeInt(buf, this.createServerId);
		//用户id
		writeString(buf, this.userId);
		//1成年,0未成年,-1未知
		writeByte(buf, this.isAdult);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//服务器编号
		this.createServerId = readInt(buf);
		//用户id
		this.userId = readString(buf);
		//1成年,0未成年,-1未知
		this.isAdult = readByte(buf);
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
	
	
	@Override
	public int getId() {
		return 100312;
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
		buf.append("createServerId:" + createServerId +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//1成年,0未成年,-1未知
		buf.append("isAdult:" + isAdult +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}