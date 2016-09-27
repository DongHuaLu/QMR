package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 时间到打开格子申请消息
 */
public class ReqOpenTimeCellMessage extends Message{

	//0 包裹  2仓库
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0 包裹  2仓库
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0 包裹  2仓库
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 0 包裹  2仓库
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0 包裹  2仓库
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 104213;
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
		//0 包裹  2仓库
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}