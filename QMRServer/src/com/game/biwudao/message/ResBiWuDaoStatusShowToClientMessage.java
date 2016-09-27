package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 比武岛活动状态显示消息
 */
public class ResBiWuDaoStatusShowToClientMessage extends Message{

	//比武岛活动状态显示
	private String statusshow;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//比武岛活动状态显示
		writeString(buf, this.statusshow);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//比武岛活动状态显示
		this.statusshow = readString(buf);
		return true;
	}
	
	/**
	 * get 比武岛活动状态显示
	 * @return 
	 */
	public String getStatusshow(){
		return statusshow;
	}
	
	/**
	 * set 比武岛活动状态显示
	 */
	public void setStatusshow(String statusshow){
		this.statusshow = statusshow;
	}
	
	
	@Override
	public int getId() {
		return 157107;
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
		//比武岛活动状态显示
		if(this.statusshow!=null) buf.append("statusshow:" + statusshow.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}