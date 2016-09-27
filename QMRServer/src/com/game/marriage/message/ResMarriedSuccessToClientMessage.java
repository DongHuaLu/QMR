package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 结婚成功消息消息
 */
public class ResMarriedSuccessToClientMessage extends Message{

	//对方名字
	private String playername;
	
	//对方头像
	private int avatarid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//对方名字
		writeString(buf, this.playername);
		//对方头像
		writeInt(buf, this.avatarid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//对方名字
		this.playername = readString(buf);
		//对方头像
		this.avatarid = readInt(buf);
		return true;
	}
	
	/**
	 * get 对方名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 对方名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 对方头像
	 * @return 
	 */
	public int getAvatarid(){
		return avatarid;
	}
	
	/**
	 * set 对方头像
	 */
	public void setAvatarid(int avatarid){
		this.avatarid = avatarid;
	}
	
	
	@Override
	public int getId() {
		return 163109;
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
		//对方名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//对方头像
		buf.append("avatarid:" + avatarid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}