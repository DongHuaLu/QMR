package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步删除帮会信息给客户端消息
 */
public class ResGuildDeleteInfoRoundToClientMessage extends Message{

	//被通知的玩家
	private long playerid;
	
	//被通知的帮会id
	private long guildid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//被通知的玩家
		writeLong(buf, this.playerid);
		//被通知的帮会id
		writeLong(buf, this.guildid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//被通知的玩家
		this.playerid = readLong(buf);
		//被通知的帮会id
		this.guildid = readLong(buf);
		return true;
	}
	
	/**
	 * get 被通知的玩家
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 被通知的玩家
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 被通知的帮会id
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 被通知的帮会id
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	
	@Override
	public int getId() {
		return 121392;
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
		//被通知的玩家
		buf.append("playerid:" + playerid +",");
		//被通知的帮会id
		buf.append("guildid:" + guildid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}