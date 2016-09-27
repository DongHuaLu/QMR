package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除玩家后返回给前端消息
 */
public class ResDelPlayerStatusToClientMessage extends Message{

	//角色ID
	private long playerId;
	
	//用户ID
	private String userId;
	
	//服务器ID
	private int createServer;
	
	//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
	private byte type;
	
	
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
		//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		writeByte(buf, this.type);
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
		//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		this.type = readByte(buf);
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
	 * get 类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 100109;
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
		//角色ID
		buf.append("playerId:" + playerId +",");
		//用户ID
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器ID
		buf.append("createServer:" + createServer +",");
		//类型： 0人物不存在（或已删），1删除成功，2删除失败（删除角色达到4人），3需要退出帮会才可以删除
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}