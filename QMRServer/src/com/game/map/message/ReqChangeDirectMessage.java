package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 变换方面消息
 */
public class ReqChangeDirectMessage extends Message{

	//人物面对方向
	private byte dir;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//人物面对方向
		writeByte(buf, this.dir);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//人物面对方向
		this.dir = readByte(buf);
		return true;
	}
	
	/**
	 * get 人物面对方向
	 * @return 
	 */
	public byte getDir(){
		return dir;
	}
	
	/**
	 * set 人物面对方向
	 */
	public void setDir(byte dir){
		this.dir = dir;
	}
	
	
	@Override
	public int getId() {
		return 101213;
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
		//人物面对方向
		buf.append("dir:" + dir +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}