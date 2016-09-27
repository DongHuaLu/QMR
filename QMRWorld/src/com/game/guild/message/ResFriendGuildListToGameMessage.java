package com.game.guild.message;

import com.game.guild.bean.FriendGuild;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮派友好列表消息
 */
public class ResFriendGuildListToGameMessage extends Message{

	//友好帮派
	private List<FriendGuild> friend = new ArrayList<FriendGuild>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//友好帮派
		writeShort(buf, friend.size());
		for (int i = 0; i < friend.size(); i++) {
			writeBean(buf, friend.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//友好帮派
		int friend_length = readShort(buf);
		for (int i = 0; i < friend_length; i++) {
			friend.add((FriendGuild)readBean(buf, FriendGuild.class));
		}
		return true;
	}
	
	/**
	 * get 友好帮派
	 * @return 
	 */
	public List<FriendGuild> getFriend(){
		return friend;
	}
	
	/**
	 * set 友好帮派
	 */
	public void setFriend(List<FriendGuild> friend){
		this.friend = friend;
	}
	
	
	@Override
	public int getId() {
		return 121325;
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
		buf.append("friend:{");
		for (int i = 0; i < friend.size(); i++) {
			buf.append(friend.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}