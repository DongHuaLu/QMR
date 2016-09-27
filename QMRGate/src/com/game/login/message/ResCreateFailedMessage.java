package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 创建角色失败消息
 */
public class ResCreateFailedMessage extends Message{

	//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
	private byte reason;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		writeByte(buf, this.reason);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		this.reason = readByte(buf);
		return true;
	}
	
	/**
	 * get 失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
	 * @return 
	 */
	public byte getReason(){
		return reason;
	}
	
	/**
	 * set 失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
	 */
	public void setReason(byte reason){
		this.reason = reason;
	}
	
	
	@Override
	public int getId() {
		return 100104;
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
		//失败原因 1-名字长度不对 2-名字含有非法字符 3-名字含有禁止词汇 4-重名
		buf.append("reason:" + reason +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}