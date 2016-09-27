package com.game.realm.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知game突破境界消息
 */
public class ReqBreakthroughToGameMessage extends Message{

	//0手动，1自动
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0手动，1自动
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0手动，1自动
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 0手动，1自动
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 0手动，1自动
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 158201;
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
		//0手动，1自动
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}