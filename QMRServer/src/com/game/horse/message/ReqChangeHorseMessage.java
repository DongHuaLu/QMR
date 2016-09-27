package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 骑乘状态更换坐骑消息
 */
public class ReqChangeHorseMessage extends Message{

	//当前使用的坐骑阶层
	private short curlayer;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前使用的坐骑阶层
		writeShort(buf, this.curlayer);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前使用的坐骑阶层
		this.curlayer = readShort(buf);
		return true;
	}
	
	/**
	 * get 当前使用的坐骑阶层
	 * @return 
	 */
	public short getCurlayer(){
		return curlayer;
	}
	
	/**
	 * set 当前使用的坐骑阶层
	 */
	public void setCurlayer(short curlayer){
		this.curlayer = curlayer;
	}
	
	
	@Override
	public int getId() {
		return 126211;
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
		//当前使用的坐骑阶层
		buf.append("curlayer:" + curlayer +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}