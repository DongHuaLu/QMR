package com.game.gm.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知网关服务器GM指令消息
 */
public class GmCommandToGateMessage extends Message{

	//角色Id
	private long playerId;
	
	//Gm指令
	private String command;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//Gm指令
		writeString(buf, this.command);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//Gm指令
		this.command = readString(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get Gm指令
	 * @return 
	 */
	public String getCommand(){
		return command;
	}
	
	/**
	 * set Gm指令
	 */
	public void setCommand(String command){
		this.command = command;
	}
	
	
	@Override
	public int getId() {
		return 200302;
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
		//角色Id
		buf.append("playerId:" + playerId +",");
		//Gm指令
		if(this.command!=null) buf.append("command:" + command.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}