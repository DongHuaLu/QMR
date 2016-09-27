package com.game.epalace.message;

import com.game.epalace.bean.EpalaceInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 地宫行走消息消息
 */
public class ResEpalaceWalkToClientMessage extends Message{

	//目标格子信息
	private EpalaceInfo epalaceInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//目标格子信息
		writeBean(buf, this.epalaceInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//目标格子信息
		this.epalaceInfo = (EpalaceInfo)readBean(buf, EpalaceInfo.class);
		return true;
	}
	
	/**
	 * get 目标格子信息
	 * @return 
	 */
	public EpalaceInfo getEpalaceInfo(){
		return epalaceInfo;
	}
	
	/**
	 * set 目标格子信息
	 */
	public void setEpalaceInfo(EpalaceInfo epalaceInfo){
		this.epalaceInfo = epalaceInfo;
	}
	
	
	@Override
	public int getId() {
		return 143101;
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
		//目标格子信息
		if(this.epalaceInfo!=null) buf.append("epalaceInfo:" + epalaceInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}