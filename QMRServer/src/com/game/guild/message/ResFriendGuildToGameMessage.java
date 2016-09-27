package com.game.guild.message;

import com.game.guild.bean.FriendGuild;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮派友好消息
 */
public class ResFriendGuildToGameMessage extends Message{

	//友好帮派
	private FriendGuild friend;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//友好帮派
		writeBean(buf, this.friend);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//友好帮派
		this.friend = (FriendGuild)readBean(buf, FriendGuild.class);
		return true;
	}
	
	/**
	 * get 友好帮派
	 * @return 
	 */
	public FriendGuild getFriend(){
		return friend;
	}
	
	/**
	 * set 友好帮派
	 */
	public void setFriend(FriendGuild friend){
		this.friend = friend;
	}
	
	
	@Override
	public int getId() {
		return 121326;
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
		//友好帮派
		if(this.friend!=null) buf.append("friend:" + friend.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}