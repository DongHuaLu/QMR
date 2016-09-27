package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 输入密码解锁消息
 */
public class ReqPasswordUnlockToGameMessage extends Message{

	//密码
	private String password;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//密码
		writeString(buf, this.password);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//密码
		this.password = readString(buf);
		return true;
	}
	
	/**
	 * get 密码
	 * @return 
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * set 密码
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	
	@Override
	public int getId() {
		return 164204;
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
		//密码
		if(this.password!=null) buf.append("password:" + password.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}