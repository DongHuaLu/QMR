package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回个人重新排位消息
 */
public class ResPlayerResetToClientMessage extends Message{

	//匹配剩余秒数
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//匹配剩余秒数
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//匹配剩余秒数
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 匹配剩余秒数
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 匹配剩余秒数
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 203107;
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
		//匹配剩余秒数
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}