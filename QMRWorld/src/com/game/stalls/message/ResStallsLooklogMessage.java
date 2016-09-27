package com.game.stalls.message;

import com.game.stalls.bean.StallsLogInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 个人摊位交易日志消息
 */
public class ResStallsLooklogMessage extends Message{

	//摊位交易日志列表
	private StallsLogInfo stallslogInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摊位交易日志列表
		writeBean(buf, this.stallslogInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摊位交易日志列表
		this.stallslogInfo = (StallsLogInfo)readBean(buf, StallsLogInfo.class);
		return true;
	}
	
	/**
	 * get 摊位交易日志列表
	 * @return 
	 */
	public StallsLogInfo getStallslogInfo(){
		return stallslogInfo;
	}
	
	/**
	 * set 摊位交易日志列表
	 */
	public void setStallslogInfo(StallsLogInfo stallslogInfo){
		this.stallslogInfo = stallslogInfo;
	}
	
	
	@Override
	public int getId() {
		return 123109;
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
		//摊位交易日志列表
		if(this.stallslogInfo!=null) buf.append("stallslogInfo:" + stallslogInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}