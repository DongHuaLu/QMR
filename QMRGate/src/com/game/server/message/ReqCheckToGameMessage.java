package com.game.server.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 作弊消息
 */
public class ReqCheckToGameMessage extends Message{

	//玩家ID
	private long playerid;
	
	//作弊次数
	private int check;
	
	//作弊参数
	private long para;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//作弊次数
		writeInt(buf, this.check);
		//作弊参数
		writeLong(buf, this.para);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//作弊次数
		this.check = readInt(buf);
		//作弊参数
		this.para = readLong(buf);
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
	 * get 作弊次数
	 * @return 
	 */
	public int getCheck(){
		return check;
	}
	
	/**
	 * set 作弊次数
	 */
	public void setCheck(int check){
		this.check = check;
	}
	
	/**
	 * get 作弊参数
	 * @return 
	 */
	public long getPara(){
		return para;
	}
	
	/**
	 * set 作弊参数
	 */
	public void setPara(long para){
		this.para = para;
	}
	
	
	@Override
	public int getId() {
		return 300313;
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
		//作弊次数
		buf.append("check:" + check +",");
		//作弊参数
		buf.append("para:" + para +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}