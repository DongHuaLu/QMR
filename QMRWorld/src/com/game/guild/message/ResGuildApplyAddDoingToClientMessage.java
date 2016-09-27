package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 申请加入帮会发给帮会操作人消息
 */
public class ResGuildApplyAddDoingToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//被操作玩家ID（申请人）
	private long userId;
	
	//申请人名字
	private String applyName;
	
	//申请人等级
	private short applyLevel;
	
	//申请人造型信息
	private com.game.friend.bean.FriendModeInfo applyModeInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//被操作玩家ID（申请人）
		writeLong(buf, this.userId);
		//申请人名字
		writeString(buf, this.applyName);
		//申请人等级
		writeShort(buf, this.applyLevel);
		//申请人造型信息
		writeBean(buf, this.applyModeInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//被操作玩家ID（申请人）
		this.userId = readLong(buf);
		//申请人名字
		this.applyName = readString(buf);
		//申请人等级
		this.applyLevel = readShort(buf);
		//申请人造型信息
		this.applyModeInfo = (com.game.friend.bean.FriendModeInfo)readBean(buf, com.game.friend.bean.FriendModeInfo.class);
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
	 * get 被操作玩家ID（申请人）
	 * @return 
	 */
	public long getUserId(){
		return userId;
	}
	
	/**
	 * set 被操作玩家ID（申请人）
	 */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * get 申请人名字
	 * @return 
	 */
	public String getApplyName(){
		return applyName;
	}
	
	/**
	 * set 申请人名字
	 */
	public void setApplyName(String applyName){
		this.applyName = applyName;
	}
	
	/**
	 * get 申请人等级
	 * @return 
	 */
	public short getApplyLevel(){
		return applyLevel;
	}
	
	/**
	 * set 申请人等级
	 */
	public void setApplyLevel(short applyLevel){
		this.applyLevel = applyLevel;
	}
	
	/**
	 * get 申请人造型信息
	 * @return 
	 */
	public com.game.friend.bean.FriendModeInfo getApplyModeInfo(){
		return applyModeInfo;
	}
	
	/**
	 * set 申请人造型信息
	 */
	public void setApplyModeInfo(com.game.friend.bean.FriendModeInfo applyModeInfo){
		this.applyModeInfo = applyModeInfo;
	}
	
	
	@Override
	public int getId() {
		return 121104;
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
		//被操作玩家ID（申请人）
		buf.append("userId:" + userId +",");
		//申请人名字
		if(this.applyName!=null) buf.append("applyName:" + applyName.toString() +",");
		//申请人等级
		buf.append("applyLevel:" + applyLevel +",");
		//申请人造型信息
		if(this.applyModeInfo!=null) buf.append("applyModeInfo:" + applyModeInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}