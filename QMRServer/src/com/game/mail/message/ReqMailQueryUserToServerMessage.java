package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 查询玩家消息
 */
public class ReqMailQueryUserToServerMessage extends Message{

	//查询玩家名
	private String szName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//查询玩家名
		writeString(buf, this.szName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//查询玩家名
		this.szName = readString(buf);
		return true;
	}
	
	/**
	 * get 查询玩家名
	 * @return 
	 */
	public String getSzName(){
		return szName;
	}
	
	/**
	 * set 查询玩家名
	 */
	public void setSzName(String szName){
		this.szName = szName;
	}
	
	
	@Override
	public int getId() {
		return 124156;
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
		//查询玩家名
		if(this.szName!=null) buf.append("szName:" + szName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}