package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 远程入队，在不知道队伍ID的情况下，服务器做判断是邀请还是加入消息
 */
public class ReqIntoTeamToWorldMessage extends Message{

	//玩家自己ID
	private long playerid;
	
	//他人ID
	private long othersid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家自己ID
		writeLong(buf, this.playerid);
		//他人ID
		writeLong(buf, this.othersid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家自己ID
		this.playerid = readLong(buf);
		//他人ID
		this.othersid = readLong(buf);
		return true;
	}
	
	/**
	 * get 玩家自己ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家自己ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 他人ID
	 * @return 
	 */
	public long getOthersid(){
		return othersid;
	}
	
	/**
	 * set 他人ID
	 */
	public void setOthersid(long othersid){
		this.othersid = othersid;
	}
	
	
	@Override
	public int getId() {
		return 118320;
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
		//玩家自己ID
		buf.append("playerid:" + playerid +",");
		//他人ID
		buf.append("othersid:" + othersid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}