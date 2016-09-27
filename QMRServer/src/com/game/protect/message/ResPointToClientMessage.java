package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 错误通知消息
 */
public class ResPointToClientMessage extends Message{

	//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 164102;
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
		//1注册时验证码错误，2解锁时密码错误，3注册成功，4解锁成功，5修改密码成功，6找回密码发送邮件成功
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}