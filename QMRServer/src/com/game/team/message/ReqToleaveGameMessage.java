package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家离队消息
 */
public class ReqToleaveGameMessage extends Message{

	//要离队的玩家ID
	private long playerid;
	
	//0开除 ，1 自己离开 ，2下线
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//要离队的玩家ID
		writeLong(buf, this.playerid);
		//0开除 ，1 自己离开 ，2下线
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//要离队的玩家ID
		this.playerid = readLong(buf);
		//0开除 ，1 自己离开 ，2下线
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 要离队的玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 要离队的玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 0开除 ，1 自己离开 ，2下线
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0开除 ，1 自己离开 ，2下线
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 118206;
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
		//要离队的玩家ID
		buf.append("playerid:" + playerid +",");
		//0开除 ，1 自己离开 ，2下线
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}