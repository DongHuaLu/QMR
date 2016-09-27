package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 创建账号后返回给前端消息
 */
public class ResCreateUserMessage extends Message{

	//用户ID
	private String userId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//用户ID
		writeString(buf, this.userId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//用户ID
		this.userId = readString(buf);
		return true;
	}
	
	/**
	 * get 用户ID
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 用户ID
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	
	@Override
	public int getId() {
		return 100110;
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
		//用户ID
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}