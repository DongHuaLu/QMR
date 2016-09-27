package com.game.setupmenu.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 系统设置消息
 */
public class ResSetMenuStatusToWorldMessage extends Message{

	//玩家ID
	private long playerid;
	
	//系统设定值
	private int menustatus;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//系统设定值
		writeInt(buf, this.menustatus);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//系统设定值
		this.menustatus = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 系统设定值
	 * @return 
	 */
	public int getMenustatus(){
		return menustatus;
	}
	
	/**
	 * set 系统设定值
	 */
	public void setMenustatus(int menustatus){
		this.menustatus = menustatus;
	}
	
	
	@Override
	public int getId() {
		return 125301;
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
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//系统设定值
		buf.append("menustatus:" + menustatus +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}