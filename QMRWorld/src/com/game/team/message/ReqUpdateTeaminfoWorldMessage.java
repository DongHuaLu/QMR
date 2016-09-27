package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求更新队伍消息消息
 */
public class ReqUpdateTeaminfoWorldMessage extends Message{

	//队伍Id
	private long teamid;
	
	//玩家ID
	private long playerid;
	
	//0更新外观 ，1 更新到GAME ，2更新到前端
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//玩家ID
		writeLong(buf, this.playerid);
		//0更新外观 ，1 更新到GAME ，2更新到前端
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//玩家ID
		this.playerid = readLong(buf);
		//0更新外观 ，1 更新到GAME ，2更新到前端
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 队伍Id
	 * @return 
	 */
	public long getTeamid(){
		return teamid;
	}
	
	/**
	 * set 队伍Id
	 */
	public void setTeamid(long teamid){
		this.teamid = teamid;
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
	 * get 0更新外观 ，1 更新到GAME ，2更新到前端
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0更新外观 ，1 更新到GAME ，2更新到前端
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 118311;
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
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//0更新外观 ，1 更新到GAME ，2更新到前端
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}