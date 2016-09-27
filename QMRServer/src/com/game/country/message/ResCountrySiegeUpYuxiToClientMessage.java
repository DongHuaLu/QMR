package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 全服广播拔起玉玺消息
 */
public class ResCountrySiegeUpYuxiToClientMessage extends Message{

	//拔起玉玺的人ID
	private long playerid;
	
	//玉拔起玉玺的人名字
	private String playername;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//拔起玉玺的人ID
		writeLong(buf, this.playerid);
		//玉拔起玉玺的人名字
		writeString(buf, this.playername);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//拔起玉玺的人ID
		this.playerid = readLong(buf);
		//玉拔起玉玺的人名字
		this.playername = readString(buf);
		return true;
	}
	
	/**
	 * get 拔起玉玺的人ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 拔起玉玺的人ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 玉拔起玉玺的人名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玉拔起玉玺的人名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	
	@Override
	public int getId() {
		return 146102;
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
		//拔起玉玺的人ID
		buf.append("playerid:" + playerid +",");
		//玉拔起玉玺的人名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}