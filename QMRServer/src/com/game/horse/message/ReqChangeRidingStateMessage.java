package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 改变骑乘状态消息
 */
public class ReqChangeRidingStateMessage extends Message{

	//当前使用的坐骑阶层
	private short curlayer;
	
	//是否骑乘，1骑乘，0未骑乘
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前使用的坐骑阶层
		writeShort(buf, this.curlayer);
		//是否骑乘，1骑乘，0未骑乘
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前使用的坐骑阶层
		this.curlayer = readShort(buf);
		//是否骑乘，1骑乘，0未骑乘
		this.status = readByte(buf);
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
	
	/**
	 * get 是否骑乘，1骑乘，0未骑乘
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 是否骑乘，1骑乘，0未骑乘
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 126202;
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
		//是否骑乘，1骑乘，0未骑乘
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}