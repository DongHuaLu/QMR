package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 队伍广播消息
 */
public class TeambroadcastMessage extends Message{

	//发送者ID
	private long playerid;
	
	//队伍消息
	private Message teammessage;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//发送者ID
		writeLong(buf, this.playerid);
		//队伍消息
		writeBean(buf, this.teammessage);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//发送者ID
		this.playerid = readLong(buf);
		//队伍消息
		this.teammessage = (Message)readBean(buf, Message.class);
		return true;
	}
	
	/**
	 * get 发送者ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 发送者ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 队伍消息
	 * @return 
	 */
	public Message getTeammessage(){
		return teammessage;
	}
	
	/**
	 * set 队伍消息
	 */
	public void setTeammessage(Message teammessage){
		this.teammessage = teammessage;
	}
	
	
	@Override
	public int getId() {
		return 118314;
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
		//发送者ID
		buf.append("playerid:" + playerid +",");
		//队伍消息
		if(this.teammessage!=null) buf.append("teammessage:" + teammessage.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}