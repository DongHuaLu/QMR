package com.game.friend.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 内部通知游戏服务器返回关系列表消息
 */
public class ResInnerRelationSendListToWorldMessage extends Message{

	//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
	private byte btListType;
	
	//关系列表
	private List<Long> relationList = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		writeByte(buf, this.btListType);
		//关系列表
		writeShort(buf, relationList.size());
		for (int i = 0; i < relationList.size(); i++) {
			writeLong(buf, relationList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//关系类型 1 好友列表 2 仇人列表 3 最近联系人 4 黑名单 0 所有列表
		this.btListType = readByte(buf);
		//关系列表
		int relationList_length = readShort(buf);
		for (int i = 0; i < relationList_length; i++) {
			relationList.add(readLong(buf));
		}
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
	 * get 关系列表
	 * @return 
	 */
	public List<Long> getRelationList(){
		return relationList;
	}
	
	/**
	 * set 关系列表
	 */
	public void setRelationList(List<Long> relationList){
		this.relationList = relationList;
	}
	
	
	@Override
	public int getId() {
		return 119320;
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
		//关系列表
		buf.append("relationList:{");
		for (int i = 0; i < relationList.size(); i++) {
			buf.append(relationList.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}