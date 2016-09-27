package com.game.arrow.message;

import com.game.arrow.bean.ArrowInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回弓箭信息消息
 */
public class ResArrowInfoMessage extends Message{

	//通知类型
	private int notifytype;
	
	//弓箭信息
	private ArrowInfo arrowinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型
		writeInt(buf, this.notifytype);
		//弓箭信息
		writeBean(buf, this.arrowinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型
		this.notifytype = readInt(buf);
		//弓箭信息
		this.arrowinfo = (ArrowInfo)readBean(buf, ArrowInfo.class);
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
	 * get 弓箭信息
	 * @return 
	 */
	public ArrowInfo getArrowinfo(){
		return arrowinfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowinfo(ArrowInfo arrowinfo){
		this.arrowinfo = arrowinfo;
	}
	
	
	@Override
	public int getId() {
		return 151101;
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
		//弓箭信息
		if(this.arrowinfo!=null) buf.append("arrowinfo:" + arrowinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}