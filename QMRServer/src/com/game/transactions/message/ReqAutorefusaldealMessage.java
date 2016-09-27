package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 自动拒绝交易消息
 */
public class ReqAutorefusaldealMessage extends Message{

	//0默认不拒绝，1自动拒绝
	private byte state;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0默认不拒绝，1自动拒绝
		writeByte(buf, this.state);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0默认不拒绝，1自动拒绝
		this.state = readByte(buf);
		return true;
	}
	
	/**
	 * get 0默认不拒绝，1自动拒绝
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 0默认不拒绝，1自动拒绝
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	
	@Override
	public int getId() {
		return 122211;
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
		//0默认不拒绝，1自动拒绝
		buf.append("state:" + state +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}