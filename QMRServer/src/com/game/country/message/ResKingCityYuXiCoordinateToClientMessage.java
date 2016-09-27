package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玉玺坐标消息
 */
public class ResKingCityYuXiCoordinateToClientMessage extends Message{

	//玉玺持有人坐标X
	private short mx;
	
	//玉玺持有人坐标Y
	private short my;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玉玺持有人坐标X
		writeShort(buf, this.mx);
		//玉玺持有人坐标Y
		writeShort(buf, this.my);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玉玺持有人坐标X
		this.mx = readShort(buf);
		//玉玺持有人坐标Y
		this.my = readShort(buf);
		return true;
	}
	
	/**
	 * get 玉玺持有人坐标X
	 * @return 
	 */
	public short getMx(){
		return mx;
	}
	
	/**
	 * set 玉玺持有人坐标X
	 */
	public void setMx(short mx){
		this.mx = mx;
	}
	
	/**
	 * get 玉玺持有人坐标Y
	 * @return 
	 */
	public short getMy(){
		return my;
	}
	
	/**
	 * set 玉玺持有人坐标Y
	 */
	public void setMy(short my){
		this.my = my;
	}
	
	
	@Override
	public int getId() {
		return 146111;
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
		//玉玺持有人坐标X
		buf.append("mx:" + mx +",");
		//玉玺持有人坐标Y
		buf.append("my:" + my +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}