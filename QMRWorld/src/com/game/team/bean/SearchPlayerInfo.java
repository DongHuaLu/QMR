package com.game.team.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通用搜索玩家信息
 */
public class SearchPlayerInfo extends Bean {

	//玩家Id
	private long playerid;
	
	//玩家等级
	private short playerlv;
	
	//玩家名字
	private String playername;
	
	//线路
	private byte line;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家Id
		writeLong(buf, this.playerid);
		//玩家等级
		writeShort(buf, this.playerlv);
		//玩家名字
		writeString(buf, this.playername);
		//线路
		writeByte(buf, this.line);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家Id
		this.playerid = readLong(buf);
		//玩家等级
		this.playerlv = readShort(buf);
		//玩家名字
		this.playername = readString(buf);
		//线路
		this.line = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家Id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家Id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 玩家等级
	 * @return 
	 */
	public short getPlayerlv(){
		return playerlv;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setPlayerlv(short playerlv){
		this.playerlv = playerlv;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 线路
	 * @return 
	 */
	public byte getLine(){
		return line;
	}
	
	/**
	 * set 线路
	 */
	public void setLine(byte line){
		this.line = line;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家Id
		buf.append("playerid:" + playerid +",");
		//玩家等级
		buf.append("playerlv:" + playerlv +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//线路
		buf.append("line:" + line +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}