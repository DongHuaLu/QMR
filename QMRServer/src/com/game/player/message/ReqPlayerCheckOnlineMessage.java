package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 检查指定玩家是否在线消息
 */
public class ReqPlayerCheckOnlineMessage extends Message{

	//玩家ID
	private long othersid;
	
	//屏幕坐标x
	private short x;
	
	//屏幕坐标y
	private short y;
	
	//面板类型
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.othersid);
		//屏幕坐标x
		writeShort(buf, this.x);
		//屏幕坐标y
		writeShort(buf, this.y);
		//面板类型
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.othersid = readLong(buf);
		//屏幕坐标x
		this.x = readShort(buf);
		//屏幕坐标y
		this.y = readShort(buf);
		//面板类型
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getOthersid(){
		return othersid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setOthersid(long othersid){
		this.othersid = othersid;
	}
	
	/**
	 * get 屏幕坐标x
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 屏幕坐标x
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 屏幕坐标y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 屏幕坐标y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	/**
	 * get 面板类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 面板类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 103210;
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
		//玩家ID
		buf.append("othersid:" + othersid +",");
		//屏幕坐标x
		buf.append("x:" + x +",");
		//屏幕坐标y
		buf.append("y:" + y +",");
		//面板类型
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}