package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器帮会通知消息
 */
public class ReqInnerGuildNotifyToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//通知类型
	private int notifytype;
	
	//帮会通知内容
	private String guildNotify;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//通知类型
		writeInt(buf, this.notifytype);
		//帮会通知内容
		writeString(buf, this.guildNotify);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//通知类型
		this.notifytype = readInt(buf);
		//帮会通知内容
		this.guildNotify = readString(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 通知类型
	 * @return 
	 */
	public int getNotifytype(){
		return notifytype;
	}
	
	/**
	 * set 通知类型
	 */
	public void setNotifytype(int notifytype){
		this.notifytype = notifytype;
	}
	
	/**
	 * get 帮会通知内容
	 * @return 
	 */
	public String getGuildNotify(){
		return guildNotify;
	}
	
	/**
	 * set 帮会通知内容
	 */
	public void setGuildNotify(String guildNotify){
		this.guildNotify = guildNotify;
	}
	
	
	@Override
	public int getId() {
		return 121322;
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
		//角色Id
		buf.append("playerId:" + playerId +",");
		//通知类型
		buf.append("notifytype:" + notifytype +",");
		//帮会通知内容
		if(this.guildNotify!=null) buf.append("guildNotify:" + guildNotify.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}