package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家防沉迷注册返回消息
 */
public class ResPlayerNonageRegisterMessage extends Message{

	//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
	private byte result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		writeByte(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		this.result = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
	 * @return 
	 */
	public byte getResult(){
		return result;
	}
	
	/**
	 * set 玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
	 */
	public void setResult(byte result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 103123;
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
		//玩家防沉迷注册结果返回， 1-成功 2-未成年 3-身份证错误
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}