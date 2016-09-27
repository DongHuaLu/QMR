package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改昵称消息
 */
public class ReqGuildChangeNickNameToServerMessage extends Message{

	//帮会Id
	private long guildId;
	
	//被操作玩家ID
	private long userId;
	
	//昵称
	private String nickName;
	
	//分组名
	private String groupName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会Id
		writeLong(buf, this.guildId);
		//被操作玩家ID
		writeLong(buf, this.userId);
		//昵称
		writeString(buf, this.nickName);
		//分组名
		writeString(buf, this.groupName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会Id
		this.guildId = readLong(buf);
		//被操作玩家ID
		this.userId = readLong(buf);
		//昵称
		this.nickName = readString(buf);
		//分组名
		this.groupName = readString(buf);
		return true;
	}
	
	/**
	 * get 帮会Id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会Id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 被操作玩家ID
	 * @return 
	 */
	public long getUserId(){
		return userId;
	}
	
	/**
	 * set 被操作玩家ID
	 */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * get 昵称
	 * @return 
	 */
	public String getNickName(){
		return nickName;
	}
	
	/**
	 * set 昵称
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	/**
	 * get 分组名
	 * @return 
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * set 分组名
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	
	@Override
	public int getId() {
		return 121209;
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
		//帮会Id
		buf.append("guildId:" + guildId +",");
		//被操作玩家ID
		buf.append("userId:" + userId +",");
		//昵称
		if(this.nickName!=null) buf.append("nickName:" + nickName.toString() +",");
		//分组名
		if(this.groupName!=null) buf.append("groupName:" + groupName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}