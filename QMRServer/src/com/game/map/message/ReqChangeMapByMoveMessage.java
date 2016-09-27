package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 切换地图消息
 */
public class ReqChangeMapByMoveMessage extends Message{

	//切换线路
	private int line;
	
	//传送点Id
	private int tranId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//切换线路
		writeInt(buf, this.line);
		//传送点Id
		writeInt(buf, this.tranId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//切换线路
		this.line = readInt(buf);
		//传送点Id
		this.tranId = readInt(buf);
		return true;
	}
	
	/**
	 * get 切换线路
	 * @return 
	 */
	public int getLine(){
		return line;
	}
	
	/**
	 * set 切换线路
	 */
	public void setLine(int line){
		this.line = line;
	}
	
	/**
	 * get 传送点Id
	 * @return 
	 */
	public int getTranId(){
		return tranId;
	}
	
	/**
	 * set 传送点Id
	 */
	public void setTranId(int tranId){
		this.tranId = tranId;
	}
	
	
	@Override
	public int getId() {
		return 101206;
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
		//切换线路
		buf.append("line:" + line +",");
		//传送点Id
		buf.append("tranId:" + tranId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}