package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除外交关系返回消息
 */
public class ResGuildDeleteDiplomaticToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//外交关系类型
	private byte diplomaticType;
	
	//申请操作帮会Id
	private long applyGuildId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//外交关系类型
		writeByte(buf, this.diplomaticType);
		//申请操作帮会Id
		writeLong(buf, this.applyGuildId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//外交关系类型
		this.diplomaticType = readByte(buf);
		//申请操作帮会Id
		this.applyGuildId = readLong(buf);
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
	 * get 外交关系类型
	 * @return 
	 */
	public byte getDiplomaticType(){
		return diplomaticType;
	}
	
	/**
	 * set 外交关系类型
	 */
	public void setDiplomaticType(byte diplomaticType){
		this.diplomaticType = diplomaticType;
	}
	
	/**
	 * get 申请操作帮会Id
	 * @return 
	 */
	public long getApplyGuildId(){
		return applyGuildId;
	}
	
	/**
	 * set 申请操作帮会Id
	 */
	public void setApplyGuildId(long applyGuildId){
		this.applyGuildId = applyGuildId;
	}
	
	
	@Override
	public int getId() {
		return 121119;
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
		//外交关系类型
		buf.append("diplomaticType:" + diplomaticType +",");
		//申请操作帮会Id
		buf.append("applyGuildId:" + applyGuildId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}