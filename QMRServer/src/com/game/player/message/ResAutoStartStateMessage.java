package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回挂机状态消息
 */
public class ResAutoStartStateMessage extends Message{

	//挂机状态， 1-挂机 0-未挂机
	private byte result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//挂机状态， 1-挂机 0-未挂机
		writeByte(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//挂机状态， 1-挂机 0-未挂机
		this.result = readByte(buf);
		return true;
	}
	
	/**
	 * get 挂机状态， 1-挂机 0-未挂机
	 * @return 
	 */
	public byte getResult(){
		return result;
	}
	
	/**
	 * set 挂机状态， 1-挂机 0-未挂机
	 */
	public void setResult(byte result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 103133;
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
		//挂机状态， 1-挂机 0-未挂机
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}