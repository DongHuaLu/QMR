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
public class ResPlayerCheckOnlineMessage extends Message{

	//他人ID
	private long othersid;
	
	//他人名字
	private String othersname;
	
	//屏幕坐标x
	private short x;
	
	//屏幕坐标y
	private short y;
	
	//面板类型
	private byte type;
	
	//0不在线，1在线
	private byte isonline;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//他人ID
		writeLong(buf, this.othersid);
		//他人名字
		writeString(buf, this.othersname);
		//屏幕坐标x
		writeShort(buf, this.x);
		//屏幕坐标y
		writeShort(buf, this.y);
		//面板类型
		writeByte(buf, this.type);
		//0不在线，1在线
		writeByte(buf, this.isonline);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//他人ID
		this.othersid = readLong(buf);
		//他人名字
		this.othersname = readString(buf);
		//屏幕坐标x
		this.x = readShort(buf);
		//屏幕坐标y
		this.y = readShort(buf);
		//面板类型
		this.type = readByte(buf);
		//0不在线，1在线
		this.isonline = readByte(buf);
		return true;
	}
	
	/**
	 * get 他人ID
	 * @return 
	 */
	public long getOthersid(){
		return othersid;
	}
	
	/**
	 * set 他人ID
	 */
	public void setOthersid(long othersid){
		this.othersid = othersid;
	}
	
	/**
	 * get 他人名字
	 * @return 
	 */
	public String getOthersname(){
		return othersname;
	}
	
	/**
	 * set 他人名字
	 */
	public void setOthersname(String othersname){
		this.othersname = othersname;
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
	
	/**
	 * get 0不在线，1在线
	 * @return 
	 */
	public byte getIsonline(){
		return isonline;
	}
	
	/**
	 * set 0不在线，1在线
	 */
	public void setIsonline(byte isonline){
		this.isonline = isonline;
	}
	
	
	@Override
	public int getId() {
		return 103125;
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
		//他人ID
		buf.append("othersid:" + othersid +",");
		//他人名字
		if(this.othersname!=null) buf.append("othersname:" + othersname.toString() +",");
		//屏幕坐标x
		buf.append("x:" + x +",");
		//屏幕坐标y
		buf.append("y:" + y +",");
		//面板类型
		buf.append("type:" + type +",");
		//0不在线，1在线
		buf.append("isonline:" + isonline +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}