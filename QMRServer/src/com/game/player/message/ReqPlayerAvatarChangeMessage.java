package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求玩家头像变更消息
 */
public class ReqPlayerAvatarChangeMessage extends Message{

	//新头像模板ID
	private int avatarid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新头像模板ID
		writeInt(buf, this.avatarid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新头像模板ID
		this.avatarid = readInt(buf);
		return true;
	}
	
	/**
	 * get 新头像模板ID
	 * @return 
	 */
	public int getAvatarid(){
		return avatarid;
	}
	
	/**
	 * set 新头像模板ID
	 */
	public void setAvatarid(int avatarid){
		this.avatarid = avatarid;
	}
	
	
	@Override
	public int getId() {
		return 103209;
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
		//新头像模板ID
		buf.append("avatarid:" + avatarid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}