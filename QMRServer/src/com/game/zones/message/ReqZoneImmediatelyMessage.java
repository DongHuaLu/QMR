package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 自动扫荡立即完成（使用元宝）消息
 */
public class ReqZoneImmediatelyMessage extends Message{

	//类型:1战役，4七曜战将
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型:1战役，4七曜战将
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型:1战役，4七曜战将
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 类型:1战役，4七曜战将
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型:1战役，4七曜战将
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 128205;
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
		//类型:1战役，4七曜战将
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}