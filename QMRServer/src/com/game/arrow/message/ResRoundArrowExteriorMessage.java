package com.game.arrow.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步弓箭外观消息
 */
public class ResRoundArrowExteriorMessage extends Message{

	//玩家id
	private long playerid;
	
	//弓箭等级
	private int arrowid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//弓箭等级
		writeInt(buf, this.arrowid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//弓箭等级
		this.arrowid = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 弓箭等级
	 * @return 
	 */
	public int getArrowid(){
		return arrowid;
	}
	
	/**
	 * set 弓箭等级
	 */
	public void setArrowid(int arrowid){
		this.arrowid = arrowid;
	}
	
	
	@Override
	public int getId() {
		return 151106;
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
		//玩家id
		buf.append("playerid:" + playerid +",");
		//弓箭等级
		buf.append("arrowid:" + arrowid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}