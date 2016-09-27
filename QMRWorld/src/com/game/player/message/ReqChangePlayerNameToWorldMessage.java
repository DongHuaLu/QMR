package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家改名，通知世界服务器消息
 */
public class ReqChangePlayerNameToWorldMessage extends Message{

	//角色ID
	private long playerId;
	
	//新名字
	private String newname;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerId);
		//新名字
		writeString(buf, this.newname);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerId = readLong(buf);
		//新名字
		this.newname = readString(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
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
		return 103318;
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
		//角色ID
		buf.append("playerId:" + playerId +",");
		//新名字
		if(this.newname!=null) buf.append("newname:" + newname.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}