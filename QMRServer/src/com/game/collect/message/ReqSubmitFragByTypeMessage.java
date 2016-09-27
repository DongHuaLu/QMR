package com.game.collect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 提交碎片根据系列消息
 */
public class ReqSubmitFragByTypeMessage extends Message{

	//系列
	private int collecttype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//系列
		writeInt(buf, this.collecttype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//系列
		this.collecttype = readInt(buf);
		return true;
	}
	
	/**
	 * get 系列
	 * @return 
	 */
	public int getCollecttype(){
		return collecttype;
	}
	
	/**
	 * set 系列
	 */
	public void setCollecttype(int collecttype){
		this.collecttype = collecttype;
	}
	
	
	@Override
	public int getId() {
		return 153201;
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
		//系列
		buf.append("collecttype:" + collecttype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}