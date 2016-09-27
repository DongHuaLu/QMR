package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求搜索本地图队伍信息消息
 */
public class ReqMapSearchTeamInfoWorldMessage extends Message{

	//玩家ID
	private long playerid;
	
	//搜索内容
	private String searchcontent;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//搜索内容
		writeString(buf, this.searchcontent);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//搜索内容
		this.searchcontent = readString(buf);
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
	 * get 搜索内容
	 * @return 
	 */
	public String getSearchcontent(){
		return searchcontent;
	}
	
	/**
	 * set 搜索内容
	 */
	public void setSearchcontent(String searchcontent){
		this.searchcontent = searchcontent;
	}
	
	
	@Override
	public int getId() {
		return 118315;
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
		//搜索内容
		if(this.searchcontent!=null) buf.append("searchcontent:" + searchcontent.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}