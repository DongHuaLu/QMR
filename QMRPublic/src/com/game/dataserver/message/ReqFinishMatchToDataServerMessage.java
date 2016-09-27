package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求跨服比赛结束（公共游戏服务器到公共数据服务器）消息
 */
public class ReqFinishMatchToDataServerMessage extends Message{

	//角色id
	private long matchId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.matchId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.matchId = readLong(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getMatchId(){
		return matchId;
	}
	
	/**
	 * set 角色id
	 */
	public void setMatchId(long matchId){
		this.matchId = matchId;
	}
	
	
	@Override
	public int getId() {
		return 203314;
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
		buf.append("matchId:" + matchId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}