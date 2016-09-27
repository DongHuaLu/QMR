package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知前端弹出询问面板消息
 */
public class ResAskedPanelToClientMessage extends Message{

	//面板类型：1设置密码前，2输入密码框
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//面板类型：1设置密码前，2输入密码框
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//面板类型：1设置密码前，2输入密码框
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 面板类型：1设置密码前，2输入密码框
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 面板类型：1设置密码前，2输入密码框
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 164101;
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
		//面板类型：1设置密码前，2输入密码框
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}