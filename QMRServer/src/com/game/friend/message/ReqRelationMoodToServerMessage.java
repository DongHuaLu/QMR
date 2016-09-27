package com.game.friend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求关系心情消息
 */
public class ReqRelationMoodToServerMessage extends Message{

	//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	private byte btListType;
	
	//被操作角色Id
	private long operateplayerId;
	
	//心情
	private String szMood;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		writeByte(buf, this.btListType);
		//被操作角色Id
		writeLong(buf, this.operateplayerId);
		//心情
		writeString(buf, this.szMood);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		this.btListType = readByte(buf);
		//被操作角色Id
		this.operateplayerId = readLong(buf);
		//心情
		this.szMood = readString(buf);
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
	 * get 被操作角色Id
	 * @return 
	 */
	public long getOperateplayerId(){
		return operateplayerId;
	}
	
	/**
	 * set 被操作角色Id
	 */
	public void setOperateplayerId(long operateplayerId){
		this.operateplayerId = operateplayerId;
	}
	
	/**
	 * get 心情
	 * @return 
	 */
	public String getSzMood(){
		return szMood;
	}
	
	/**
	 * set 心情
	 */
	public void setSzMood(String szMood){
		this.szMood = szMood;
	}
	
	
	@Override
	public int getId() {
		return 119205;
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
		//被操作角色Id
		buf.append("operateplayerId:" + operateplayerId +",");
		//心情
		if(this.szMood!=null) buf.append("szMood:" + szMood.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}