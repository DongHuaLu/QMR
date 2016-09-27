package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家发起改名消息
 */
public class ReqChangePlayerNameMessage extends Message{

	//新名字
	private String newname;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新名字
		writeString(buf, this.newname);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新名字
		this.newname = readString(buf);
		return true;
	}
	
	/**
	 * get 新名字
	 * @return 
	 */
	public String getNewname(){
		return newname;
	}
	
	/**
	 * set 新名字
	 */
	public void setNewname(String newname){
		this.newname = newname;
	}
	
	
	@Override
	public int getId() {
		return 103212;
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
		//新名字
		if(this.newname!=null) buf.append("newname:" + newname.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}