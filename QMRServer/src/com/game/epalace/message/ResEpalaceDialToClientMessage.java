package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回前端转盘消息消息
 */
public class ResEpalaceDialToClientMessage extends Message{

	//当前位置
	private byte currentpos;
	
	//罗盘转动位置，上右下左0246，没有就-1
	private byte forkdirection;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前位置
		writeByte(buf, this.currentpos);
		//罗盘转动位置，上右下左0246，没有就-1
		writeByte(buf, this.forkdirection);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前位置
		this.currentpos = readByte(buf);
		//罗盘转动位置，上右下左0246，没有就-1
		this.forkdirection = readByte(buf);
		return true;
	}
	
	/**
	 * get 当前位置
	 * @return 
	 */
	public byte getCurrentpos(){
		return currentpos;
	}
	
	/**
	 * set 当前位置
	 */
	public void setCurrentpos(byte currentpos){
		this.currentpos = currentpos;
	}
	
	/**
	 * get 罗盘转动位置，上右下左0246，没有就-1
	 * @return 
	 */
	public byte getForkdirection(){
		return forkdirection;
	}
	
	/**
	 * set 罗盘转动位置，上右下左0246，没有就-1
	 */
	public void setForkdirection(byte forkdirection){
		this.forkdirection = forkdirection;
	}
	
	
	@Override
	public int getId() {
		return 143105;
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
		//当前位置
		buf.append("currentpos:" + currentpos +",");
		//罗盘转动位置，上右下左0246，没有就-1
		buf.append("forkdirection:" + forkdirection +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}