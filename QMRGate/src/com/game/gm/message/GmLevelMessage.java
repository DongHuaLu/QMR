package com.game.gm.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * GM等级消息
 */
public class GmLevelMessage extends Message{

	//Gm等级
	private int level;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//Gm等级
		writeInt(buf, this.level);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//Gm等级
		this.level = readInt(buf);
		return true;
	}
	
	/**
	 * get Gm等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set Gm等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	
	@Override
	public int getId() {
		return 200101;
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
		//Gm等级
		buf.append("level:" + level +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}