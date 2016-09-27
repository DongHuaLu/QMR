package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家弓箭消息
 */
public class ReqSyncPlayerArrowMessage extends Message{

	//角色id
	private long playerId;
	
	//弓箭信息
	private String arrowinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//弓箭信息
		writeString(buf, this.arrowinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//弓箭信息
		this.arrowinfo = readString(buf);
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
	 * get 弓箭信息
	 * @return 
	 */
	public String getArrowinfo(){
		return arrowinfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowinfo(String arrowinfo){
		this.arrowinfo = arrowinfo;
	}
	
	
	@Override
	public int getId() {
		return 103333;
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
		//弓箭信息
		if(this.arrowinfo!=null) buf.append("arrowinfo:" + arrowinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}