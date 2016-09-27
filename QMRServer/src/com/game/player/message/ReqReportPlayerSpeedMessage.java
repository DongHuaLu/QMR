package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 举报玩家加速消息
 */
public class ReqReportPlayerSpeedMessage extends Message{

	//被举报玩家ID
	private long targetid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//被举报玩家ID
		writeLong(buf, this.targetid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//被举报玩家ID
		this.targetid = readLong(buf);
		return true;
	}
	
	/**
	 * get 被举报玩家ID
	 * @return 
	 */
	public long getTargetid(){
		return targetid;
	}
	
	/**
	 * set 被举报玩家ID
	 */
	public void setTargetid(long targetid){
		this.targetid = targetid;
	}
	
	
	@Override
	public int getId() {
		return 103215;
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
		//被举报玩家ID
		buf.append("targetid:" + targetid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}