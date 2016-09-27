package com.game.arrow.message;

import com.game.arrow.bean.StarInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回星斗信息消息
 */
public class ResStarInfoMessage extends Message{

	//通知类型
	private int notifytype;
	
	//星斗信息
	private StarInfo starinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型
		writeInt(buf, this.notifytype);
		//星斗信息
		writeBean(buf, this.starinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型
		this.notifytype = readInt(buf);
		//星斗信息
		this.starinfo = (StarInfo)readBean(buf, StarInfo.class);
		return true;
	}
	
	/**
	 * get 通知类型
	 * @return 
	 */
	public int getNotifytype(){
		return notifytype;
	}
	
	/**
	 * set 通知类型
	 */
	public void setNotifytype(int notifytype){
		this.notifytype = notifytype;
	}
	
	/**
	 * get 星斗信息
	 * @return 
	 */
	public StarInfo getStarinfo(){
		return starinfo;
	}
	
	/**
	 * set 星斗信息
	 */
	public void setStarinfo(StarInfo starinfo){
		this.starinfo = starinfo;
	}
	
	
	@Override
	public int getId() {
		return 151102;
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
		//通知类型
		buf.append("notifytype:" + notifytype +",");
		//星斗信息
		if(this.starinfo!=null) buf.append("starinfo:" + starinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}