package com.game.rank.message;

import com.game.rank.bean.Rankinfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 军衔改变广播消息
 */
public class ResRankUPToClientMessage extends Message{

	//玩家ID
	private long playerid;
	
	//军衔保存信息
	private Rankinfo rankinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//军衔保存信息
		writeBean(buf, this.rankinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//军衔保存信息
		this.rankinfo = (Rankinfo)readBean(buf, Rankinfo.class);
		return true;
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
	 * get 军衔保存信息
	 * @return 
	 */
	public Rankinfo getRankinfo(){
		return rankinfo;
	}
	
	/**
	 * set 军衔保存信息
	 */
	public void setRankinfo(Rankinfo rankinfo){
		this.rankinfo = rankinfo;
	}
	
	
	@Override
	public int getId() {
		return 117103;
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
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//军衔保存信息
		if(this.rankinfo!=null) buf.append("rankinfo:" + rankinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}