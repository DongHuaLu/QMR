package com.game.marriage.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 红包信息
 */
public class RedEnvelopeInfo extends Bean {

	//玩家id
	private long playerid;
	
	//玩家名字
	private String playername;
	
	//玩家等级
	private int playerlv;
	
	//红包数量
	private int rednum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//玩家名字
		writeString(buf, this.playername);
		//玩家等级
		writeInt(buf, this.playerlv);
		//红包数量
		writeInt(buf, this.rednum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//玩家名字
		this.playername = readString(buf);
		//玩家等级
		this.playerlv = readInt(buf);
		//红包数量
		this.rednum = readInt(buf);
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
	 * get 玩家等级
	 * @return 
	 */
	public int getPlayerlv(){
		return playerlv;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setPlayerlv(int playerlv){
		this.playerlv = playerlv;
	}
	
	/**
	 * get 红包数量
	 * @return 
	 */
	public int getRednum(){
		return rednum;
	}
	
	/**
	 * set 红包数量
	 */
	public void setRednum(int rednum){
		this.rednum = rednum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家id
		buf.append("playerid:" + playerid +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家等级
		buf.append("playerlv:" + playerlv +",");
		//红包数量
		buf.append("rednum:" + rednum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}