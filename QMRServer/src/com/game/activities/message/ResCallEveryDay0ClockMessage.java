package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 零点通知事件消息
 */
public class ResCallEveryDay0ClockMessage extends Message{

	//通知事件类型
	private int eventType;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知事件类型
		writeInt(buf, this.eventType);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知事件类型
		this.eventType = readInt(buf);
		return true;
	}
	
	/**
	 * get 通知事件类型
	 * @return 
	 */
	public int getEventType(){
		return eventType;
	}
	
	/**
	 * set 通知事件类型
	 */
	public void setEventType(int eventType){
		this.eventType = eventType;
	}
	
	
	@Override
	public int getId() {
		return 138103;
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
		//通知事件类型
		buf.append("eventType:" + eventType +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}