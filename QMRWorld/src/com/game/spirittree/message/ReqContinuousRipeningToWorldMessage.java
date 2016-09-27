package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 连续催熟果到世界服务器消息
 */
public class ReqContinuousRipeningToWorldMessage extends Message{

	//玩家ID
	private long playerid;
	
	//果实类型：0普通果实，1银色奇异果，2金色奇异果
	private byte type;
	
	//连续催熟果实次数
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		writeByte(buf, this.type);
		//连续催熟果实次数
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		this.type = readByte(buf);
		//连续催熟果实次数
		this.num = readInt(buf);
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
	 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 连续催熟果实次数
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 连续催熟果实次数
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 133316;
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
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		buf.append("type:" + type +",");
		//连续催熟果实次数
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}