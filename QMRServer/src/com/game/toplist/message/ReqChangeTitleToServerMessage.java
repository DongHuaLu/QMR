package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 改变称号消息
 */
public class ReqChangeTitleToServerMessage extends Message{

	//称号id
	private int titleid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//称号id
		writeInt(buf, this.titleid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//称号id
		this.titleid = readInt(buf);
		return true;
	}
	
	/**
	 * get 称号id
	 * @return 
	 */
	public int getTitleid(){
		return titleid;
	}
	
	/**
	 * set 称号id
	 */
	public void setTitleid(int titleid){
		this.titleid = titleid;
	}
	
	
	@Override
	public int getId() {
		return 142203;
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
		//称号id
		buf.append("titleid:" + titleid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}