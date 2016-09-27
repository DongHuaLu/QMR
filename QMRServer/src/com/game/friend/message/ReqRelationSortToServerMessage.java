package com.game.friend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求关系排序消息
 */
public class ReqRelationSortToServerMessage extends Message{

	//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	private byte btListType;
	
	//被操作角色Id
	private long operateplayerId;
	
	//排序次序
	private int sortNum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		writeByte(buf, this.btListType);
		//被操作角色Id
		writeLong(buf, this.operateplayerId);
		//排序次序
		writeInt(buf, this.sortNum);
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
		//排序次序
		this.sortNum = readInt(buf);
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
	 * get 排序次序
	 * @return 
	 */
	public int getSortNum(){
		return sortNum;
	}
	
	/**
	 * set 排序次序
	 */
	public void setSortNum(int sortNum){
		this.sortNum = sortNum;
	}
	
	
	@Override
	public int getId() {
		return 119204;
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
		//排序次序
		buf.append("sortNum:" + sortNum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}