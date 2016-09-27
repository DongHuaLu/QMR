package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家军衔消息
 */
public class ReqSyncPlayerRankMessage extends Message{

	//角色id
	private long playerId;
	
	//军衔等级
	private byte ranklevel;
	
	//军衔点
	private int rankpoint;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//军衔点
		writeInt(buf, this.rankpoint);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//军衔点
		this.rankpoint = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public byte getRanklevel(){
		return ranklevel;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setRanklevel(byte ranklevel){
		this.ranklevel = ranklevel;
	}
	
	/**
	 * get 军衔点
	 * @return 
	 */
	public int getRankpoint(){
		return rankpoint;
	}
	
	/**
	 * set 军衔点
	 */
	public void setRankpoint(int rankpoint){
		this.rankpoint = rankpoint;
	}
	
	
	@Override
	public int getId() {
		return 103332;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//军衔点
		buf.append("rankpoint:" + rankpoint +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}