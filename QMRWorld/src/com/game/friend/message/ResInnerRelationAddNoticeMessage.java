package com.game.friend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 被别人好友操作的通知消息
 */
public class ResInnerRelationAddNoticeMessage extends Message{

	//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	private byte btListType;
	
	//被操作人ID
	private long playerId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		writeByte(buf, this.btListType);
		//被操作人ID
		writeLong(buf, this.playerId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		this.btListType = readByte(buf);
		//被操作人ID
		this.playerId = readLong(buf);
		return true;
	}
	
	/**
	 * get 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	 * @return 
	 */
	public byte getBtListType(){
		return btListType;
	}
	
	/**
	 * set 关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	 */
	public void setBtListType(byte btListType){
		this.btListType = btListType;
	}
	
	/**
	 * get 被操作人ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 被操作人ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	
	@Override
	public int getId() {
		return 119321;
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
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		buf.append("btListType:" + btListType +",");
		//被操作人ID
		buf.append("playerId:" + playerId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}