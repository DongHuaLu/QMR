package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 灵树浇水得到经验ToGame消息
 */
public class ResFruitOperatingToGameMessage extends Message{

	//类型：1浇水，2浇仙露
	private byte type;
	
	//果实ID
	private long fruitid;
	
	//操作的玩家ID
	private long playerid;
	
	//果实主人ID
	private long hostid;
	
	//浇水获得经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型：1浇水，2浇仙露
		writeByte(buf, this.type);
		//果实ID
		writeLong(buf, this.fruitid);
		//操作的玩家ID
		writeLong(buf, this.playerid);
		//果实主人ID
		writeLong(buf, this.hostid);
		//浇水获得经验
		writeInt(buf, this.exp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型：1浇水，2浇仙露
		this.type = readByte(buf);
		//果实ID
		this.fruitid = readLong(buf);
		//操作的玩家ID
		this.playerid = readLong(buf);
		//果实主人ID
		this.hostid = readLong(buf);
		//浇水获得经验
		this.exp = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型：1浇水，2浇仙露
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：1浇水，2浇仙露
	 */
	public void setType(byte type){
		this.type = type;
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
	
	/**
	 * get 操作的玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 操作的玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 果实主人ID
	 * @return 
	 */
	public long getHostid(){
		return hostid;
	}
	
	/**
	 * set 果实主人ID
	 */
	public void setHostid(long hostid){
		this.hostid = hostid;
	}
	
	/**
	 * get 浇水获得经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 浇水获得经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	
	@Override
	public int getId() {
		return 133307;
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
		//类型：1浇水，2浇仙露
		buf.append("type:" + type +",");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		//操作的玩家ID
		buf.append("playerid:" + playerid +",");
		//果实主人ID
		buf.append("hostid:" + hostid +",");
		//浇水获得经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}