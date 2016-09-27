package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 邀请加入帮会发给被操作玩家消息
 */
public class ResGuildInviteAddDoingToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//帮会Id
	private long guildId;
	
	//帮会名字
	private String guildName;
	
	//邀请人名字
	private String inviteName;
	
	//邀请人等级
	private short inviteLevel;
	
	//邀请人造型信息
	private com.game.friend.bean.FriendModeInfo inviteModeInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//帮会Id
		writeLong(buf, this.guildId);
		//帮会名字
		writeString(buf, this.guildName);
		//邀请人名字
		writeString(buf, this.inviteName);
		//邀请人等级
		writeShort(buf, this.inviteLevel);
		//邀请人造型信息
		writeBean(buf, this.inviteModeInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//帮会Id
		this.guildId = readLong(buf);
		//帮会名字
		this.guildName = readString(buf);
		//邀请人名字
		this.inviteName = readString(buf);
		//邀请人等级
		this.inviteLevel = readShort(buf);
		//邀请人造型信息
		this.inviteModeInfo = (com.game.friend.bean.FriendModeInfo)readBean(buf, com.game.friend.bean.FriendModeInfo.class);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getBtErrorCode(){
		return btErrorCode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setBtErrorCode(byte btErrorCode){
		this.btErrorCode = btErrorCode;
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
	 * get 帮会名字
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 帮会名字
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	/**
	 * get 邀请人名字
	 * @return 
	 */
	public String getInviteName(){
		return inviteName;
	}
	
	/**
	 * set 邀请人名字
	 */
	public void setInviteName(String inviteName){
		this.inviteName = inviteName;
	}
	
	/**
	 * get 邀请人等级
	 * @return 
	 */
	public short getInviteLevel(){
		return inviteLevel;
	}
	
	/**
	 * set 邀请人等级
	 */
	public void setInviteLevel(short inviteLevel){
		this.inviteLevel = inviteLevel;
	}
	
	/**
	 * get 邀请人造型信息
	 * @return 
	 */
	public com.game.friend.bean.FriendModeInfo getInviteModeInfo(){
		return inviteModeInfo;
	}
	
	/**
	 * set 邀请人造型信息
	 */
	public void setInviteModeInfo(com.game.friend.bean.FriendModeInfo inviteModeInfo){
		this.inviteModeInfo = inviteModeInfo;
	}
	
	
	@Override
	public int getId() {
		return 121105;
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
		//错误代码
		buf.append("btErrorCode:" + btErrorCode +",");
		//帮会Id
		buf.append("guildId:" + guildId +",");
		//帮会名字
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//邀请人名字
		if(this.inviteName!=null) buf.append("inviteName:" + inviteName.toString() +",");
		//邀请人等级
		buf.append("inviteLevel:" + inviteLevel +",");
		//邀请人造型信息
		if(this.inviteModeInfo!=null) buf.append("inviteModeInfo:" + inviteModeInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}