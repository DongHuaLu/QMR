package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 活动检查果实是否成熟消息
 */
public class ReqActivityCheckFruitToWorldMessage extends Message{

	//玩家ID
	private long playerid;
	
	//果实类型，1银色奇异果，2金色奇异果
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//果实类型，1银色奇异果，2金色奇异果
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//果实类型，1银色奇异果，2金色奇异果
		this.type = readInt(buf);
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
	 * get 果实类型，1银色奇异果，2金色奇异果
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 果实类型，1银色奇异果，2金色奇异果
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 133313;
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
		//果实类型，1银色奇异果，2金色奇异果
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}