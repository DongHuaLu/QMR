package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器副本排行消息
 */
public class ReqZoneTopToWorldMessage extends Message{

	//玩家id
	private long playerid;
	
	//副本类型
	private int zonetype;
	
	//副本排行jsonstr
	private String zonetopjsonstr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//副本类型
		writeInt(buf, this.zonetype);
		//副本排行jsonstr
		writeString(buf, this.zonetopjsonstr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//副本类型
		this.zonetype = readInt(buf);
		//副本排行jsonstr
		this.zonetopjsonstr = readString(buf);
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
	 * get 副本类型
	 * @return 
	 */
	public int getZonetype(){
		return zonetype;
	}
	
	/**
	 * set 副本类型
	 */
	public void setZonetype(int zonetype){
		this.zonetype = zonetype;
	}
	
	/**
	 * get 副本排行jsonstr
	 * @return 
	 */
	public String getZonetopjsonstr(){
		return zonetopjsonstr;
	}
	
	/**
	 * set 副本排行jsonstr
	 */
	public void setZonetopjsonstr(String zonetopjsonstr){
		this.zonetopjsonstr = zonetopjsonstr;
	}
	
	
	@Override
	public int getId() {
		return 142303;
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
		//副本类型
		buf.append("zonetype:" + zonetype +",");
		//副本排行jsonstr
		if(this.zonetopjsonstr!=null) buf.append("zonetopjsonstr:" + zonetopjsonstr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}