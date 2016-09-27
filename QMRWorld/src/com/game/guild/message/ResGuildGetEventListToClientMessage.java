package com.game.guild.message;

import com.game.guild.bean.EventInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取帮会事件列表返回消息
 */
public class ResGuildGetEventListToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//帮会事件列表
	private List<EventInfo> eventList = new ArrayList<EventInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//帮会事件列表
		writeShort(buf, eventList.size());
		for (int i = 0; i < eventList.size(); i++) {
			writeBean(buf, eventList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//帮会事件列表
		int eventList_length = readShort(buf);
		for (int i = 0; i < eventList_length; i++) {
			eventList.add((EventInfo)readBean(buf, EventInfo.class));
		}
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getBtErrorCode(){
		return btErrorCode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setBtErrorCode(byte btErrorCode){
		this.btErrorCode = btErrorCode;
	}
	
	/**
	 * get 帮会事件列表
	 * @return 
	 */
	public List<EventInfo> getEventList(){
		return eventList;
	}
	
	/**
	 * set 帮会事件列表
	 */
	public void setEventList(List<EventInfo> eventList){
		this.eventList = eventList;
	}
	
	
	@Override
	public int getId() {
		return 121121;
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
		//错误代码
		buf.append("btErrorCode:" + btErrorCode +",");
		//帮会事件列表
		buf.append("eventList:{");
		for (int i = 0; i < eventList.size(); i++) {
			buf.append(eventList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}