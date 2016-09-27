package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查询玩家返回消息
 */
public class ResMailQueryUserToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//查询玩家名字
	private String szName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//查询玩家名字
		writeString(buf, this.szName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//查询玩家名字
		this.szName = readString(buf);
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
	 * get 查询玩家名字
	 * @return 
	 */
	public String getSzName(){
		return szName;
	}
	
	/**
	 * set 查询玩家名字
	 */
	public void setSzName(String szName){
		this.szName = szName;
	}
	
	
	@Override
	public int getId() {
		return 124106;
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
		//查询玩家名字
		if(this.szName!=null) buf.append("szName:" + szName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}