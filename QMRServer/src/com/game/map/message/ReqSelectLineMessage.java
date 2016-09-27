package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 选线消息
 */
public class ReqSelectLineMessage extends Message{

	//线编号
	private int line;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//线编号
		writeInt(buf, this.line);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//线编号
		this.line = readInt(buf);
		return true;
	}
	
	/**
	 * get 线编号
	 * @return 
	 */
	public int getLine(){
		return line;
	}
	
	/**
	 * set 线编号
	 */
	public void setLine(int line){
		this.line = line;
	}
	
	
	@Override
	public int getId() {
		return 101212;
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
		//线编号
		buf.append("line:" + line +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}