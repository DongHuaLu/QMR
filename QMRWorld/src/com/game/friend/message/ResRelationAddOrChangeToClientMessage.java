package com.game.friend.message;

import com.game.friend.bean.RelationInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 添加或者更新一个新关系消息
 */
public class ResRelationAddOrChangeToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//添加或更新的关系信息
	private RelationInfo relationAddInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//添加或更新的关系信息
		writeBean(buf, this.relationAddInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//添加或更新的关系信息
		this.relationAddInfo = (RelationInfo)readBean(buf, RelationInfo.class);
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
	 * get 添加或更新的关系信息
	 * @return 
	 */
	public RelationInfo getRelationAddInfo(){
		return relationAddInfo;
	}
	
	/**
	 * set 添加或更新的关系信息
	 */
	public void setRelationAddInfo(RelationInfo relationAddInfo){
		this.relationAddInfo = relationAddInfo;
	}
	
	
	@Override
	public int getId() {
		return 119102;
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
		//添加或更新的关系信息
		if(this.relationAddInfo!=null) buf.append("relationAddInfo:" + relationAddInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}