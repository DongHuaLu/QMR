package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查询他人玩家信息到世界服务器消息
 */
public class ReqOtherPlayerInfoToWorldMessage extends Message{

	//角色id
	private long playerId;
	
	//角色id
	private long otherPlayerId;
	
	//类型： 0角色，1坐骑
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色id
		writeLong(buf, this.otherPlayerId);
		//类型： 0角色，1坐骑
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色id
		this.otherPlayerId = readLong(buf);
		//类型： 0角色，1坐骑
		this.type = readByte(buf);
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
	 * get 角色id
	 * @return 
	 */
	public long getOtherPlayerId(){
		return otherPlayerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setOtherPlayerId(long otherPlayerId){
		this.otherPlayerId = otherPlayerId;
	}
	
	/**
	 * get 类型： 0角色，1坐骑
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型： 0角色，1坐骑
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 103307;
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
		//角色id
		buf.append("otherPlayerId:" + otherPlayerId +",");
		//类型： 0角色，1坐骑
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}