package com.game.friend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求关系设置消息
 */
public class ReqRelationConfigToServerMessage extends Message{

	//是否公开我的地图位置
	private byte openMapLocation;
	
	//是否显示不在线的好友或仇人
	private byte showrelation;
	
	//是否在列表中显示头像
	private byte showicon;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//是否公开我的地图位置
		writeByte(buf, this.openMapLocation);
		//是否显示不在线的好友或仇人
		writeByte(buf, this.showrelation);
		//是否在列表中显示头像
		writeByte(buf, this.showicon);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//是否公开我的地图位置
		this.openMapLocation = readByte(buf);
		//是否显示不在线的好友或仇人
		this.showrelation = readByte(buf);
		//是否在列表中显示头像
		this.showicon = readByte(buf);
		return true;
	}
	
	/**
	 * get 是否公开我的地图位置
	 * @return 
	 */
	public byte getOpenMapLocation(){
		return openMapLocation;
	}
	
	/**
	 * set 是否公开我的地图位置
	 */
	public void setOpenMapLocation(byte openMapLocation){
		this.openMapLocation = openMapLocation;
	}
	
	/**
	 * get 是否显示不在线的好友或仇人
	 * @return 
	 */
	public byte getShowrelation(){
		return showrelation;
	}
	
	/**
	 * set 是否显示不在线的好友或仇人
	 */
	public void setShowrelation(byte showrelation){
		this.showrelation = showrelation;
	}
	
	/**
	 * get 是否在列表中显示头像
	 * @return 
	 */
	public byte getShowicon(){
		return showicon;
	}
	
	/**
	 * set 是否在列表中显示头像
	 */
	public void setShowicon(byte showicon){
		this.showicon = showicon;
	}
	
	
	@Override
	public int getId() {
		return 119206;
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
		//是否公开我的地图位置
		buf.append("openMapLocation:" + openMapLocation +",");
		//是否显示不在线的好友或仇人
		buf.append("showrelation:" + showrelation +",");
		//是否在列表中显示头像
		buf.append("showicon:" + showicon +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}