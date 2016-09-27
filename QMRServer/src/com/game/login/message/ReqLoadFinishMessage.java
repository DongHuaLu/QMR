package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 加载完成消息
 */
public class ReqLoadFinishMessage extends Message{

	//类别
	private byte type;
	
	//屏幕宽度
	private int width;
	
	//屏幕高度
	private int height;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类别
		writeByte(buf, this.type);
		//屏幕宽度
		writeInt(buf, this.width);
		//屏幕高度
		writeInt(buf, this.height);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类别
		this.type = readByte(buf);
		//屏幕宽度
		this.width = readInt(buf);
		//屏幕高度
		this.height = readInt(buf);
		return true;
	}
	
	/**
	 * get 类别
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类别
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 屏幕宽度
	 * @return 
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * set 屏幕宽度
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * get 屏幕高度
	 * @return 
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * set 屏幕高度
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	
	@Override
	public int getId() {
		return 100204;
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
		//类别
		buf.append("type:" + type +",");
		//屏幕宽度
		buf.append("width:" + width +",");
		//屏幕高度
		buf.append("height:" + height +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}