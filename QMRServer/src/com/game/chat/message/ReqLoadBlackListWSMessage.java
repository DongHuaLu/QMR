package com.game.chat.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知游戏服加载聊天黑名单消息
 */
public class ReqLoadBlackListWSMessage extends Message{

	//聊天黑名单类型 1-账号 2-IP
	private int blacktype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//聊天黑名单类型 1-账号 2-IP
		writeInt(buf, this.blacktype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//聊天黑名单类型 1-账号 2-IP
		this.blacktype = readInt(buf);
		return true;
	}
	
	/**
	 * get 聊天黑名单类型 1-账号 2-IP
	 * @return 
	 */
	public int getBlacktype(){
		return blacktype;
	}
	
	/**
	 * set 聊天黑名单类型 1-账号 2-IP
	 */
	public void setBlacktype(int blacktype){
		this.blacktype = blacktype;
	}
	
	
	@Override
	public int getId() {
		return 111320;
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
		//聊天黑名单类型 1-账号 2-IP
		buf.append("blacktype:" + blacktype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}