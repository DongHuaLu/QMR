package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求更改pk状态消息
 */
public class ReqChangePKStateMessage extends Message{

	//pk状态
	private int pkState;
	
	//是否自动切换
	private int auto;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//pk状态
		writeInt(buf, this.pkState);
		//是否自动切换
		writeInt(buf, this.auto);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//pk状态
		this.pkState = readInt(buf);
		//是否自动切换
		this.auto = readInt(buf);
		return true;
	}
	
	/**
	 * get pk状态
	 * @return 
	 */
	public int getPkState(){
		return pkState;
	}
	
	/**
	 * set pk状态
	 */
	public void setPkState(int pkState){
		this.pkState = pkState;
	}
	
	/**
	 * get 是否自动切换
	 * @return 
	 */
	public int getAuto(){
		return auto;
	}
	
	/**
	 * set 是否自动切换
	 */
	public void setAuto(int auto){
		this.auto = auto;
	}
	
	
	@Override
	public int getId() {
		return 103204;
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
		//pk状态
		buf.append("pkState:" + pkState +",");
		//是否自动切换
		buf.append("auto:" + auto +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}