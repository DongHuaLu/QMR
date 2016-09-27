package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回果实是否成熟，收取道具消息
 */
public class ResActivityReturnFruitToGameMessage extends Message{

	//玩家ID
	private long playerid;
	
	//果实类型，1银色奇异果，2金色奇异果
	private int type;
	
	//0未成熟，1成熟
	private byte status;
	
	//果实ID
	private long fruitid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//果实类型，1银色奇异果，2金色奇异果
		writeInt(buf, this.type);
		//0未成熟，1成熟
		writeByte(buf, this.status);
		//果实ID
		writeLong(buf, this.fruitid);
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
		//0未成熟，1成熟
		this.status = readByte(buf);
		//果实ID
		this.fruitid = readLong(buf);
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
	
	/**
	 * get 0未成熟，1成熟
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 0未成熟，1成熟
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	
	@Override
	public int getId() {
		return 133314;
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
		//0未成熟，1成熟
		buf.append("status:" + status +",");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}