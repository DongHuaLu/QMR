package com.game.version.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器广播给所有GAME重读data消息
 */
public class ReqVersionUpdateToWorldMessage extends Message{

	//GM角色ID
	private long playerId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//GM角色ID
		writeLong(buf, this.playerId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//GM角色ID
		this.playerId = readLong(buf);
		return true;
	}
	
	/**
	 * get GM角色ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set GM角色ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	
	@Override
	public int getId() {
		return 202301;
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
		//GM角色ID
		buf.append("playerId:" + playerId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}