package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 向游戏服务器切换地图加载完成消息
 */
public class ReqLoadFinishForChangeMapToGameMessage extends Message{

	//网关编号
	private int gateId;
	
	//1成年,0未成年,-1未知
	private byte isadult;
	
	//屏幕宽度
	private int width;
	
	//屏幕高度
	private int height;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//网关编号
		writeInt(buf, this.gateId);
		//1成年,0未成年,-1未知
		writeByte(buf, this.isadult);
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
		//网关编号
		this.gateId = readInt(buf);
		//1成年,0未成年,-1未知
		this.isadult = readByte(buf);
		//屏幕宽度
		this.width = readInt(buf);
		//屏幕高度
		this.height = readInt(buf);
		return true;
	}
	
	/**
	 * get 网关编号
	 * @return 
	 */
	public int getGateId(){
		return gateId;
	}
	
	/**
	 * set 网关编号
	 */
	public void setGateId(int gateId){
		this.gateId = gateId;
	}
	
	/**
	 * get 1成年,0未成年,-1未知
	 * @return 
	 */
	public byte getIsadult(){
		return isadult;
	}
	
	/**
	 * set 1成年,0未成年,-1未知
	 */
	public void setIsadult(byte isadult){
		this.isadult = isadult;
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
		return 101301;
	}

	@Override
	public String getQueue() {
		return "Local";
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//网关编号
		buf.append("gateId:" + gateId +",");
		//1成年,0未成年,-1未知
		buf.append("isadult:" + isadult +",");
		//屏幕宽度
		buf.append("width:" + width +",");
		//屏幕高度
		buf.append("height:" + height +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}