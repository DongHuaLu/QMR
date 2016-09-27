package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器膜拜玩家消息
 */
public class ReqWorShipToWorldMessage extends Message{

	//玩家id
	private long playerid;
	
	//膜拜玩家id
	private long worshipplayerid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//膜拜玩家id
		writeLong(buf, this.worshipplayerid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//膜拜玩家id
		this.worshipplayerid = readLong(buf);
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
	 * get 膜拜玩家id
	 * @return 
	 */
	public long getWorshipplayerid(){
		return worshipplayerid;
	}
	
	/**
	 * set 膜拜玩家id
	 */
	public void setWorshipplayerid(long worshipplayerid){
		this.worshipplayerid = worshipplayerid;
	}
	
	
	@Override
	public int getId() {
		return 142302;
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
		//膜拜玩家id
		buf.append("worshipplayerid:" + worshipplayerid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}