package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知游戏服务器地图副本最快通关记录,广播消息
 */
public class ResfastestClearanceToGameMessage extends Message{

	//第一名玩家数据
	private String zonetopjsonstr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//第一名玩家数据
		writeString(buf, this.zonetopjsonstr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//第一名玩家数据
		this.zonetopjsonstr = readString(buf);
		return true;
	}
	
	/**
	 * get 第一名玩家数据
	 * @return 
	 */
	public String getZonetopjsonstr(){
		return zonetopjsonstr;
	}
	
	/**
	 * set 第一名玩家数据
	 */
	public void setZonetopjsonstr(String zonetopjsonstr){
		this.zonetopjsonstr = zonetopjsonstr;
	}
	
	
	@Override
	public int getId() {
		return 128301;
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
		//第一名玩家数据
		if(this.zonetopjsonstr!=null) buf.append("zonetopjsonstr:" + zonetopjsonstr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}