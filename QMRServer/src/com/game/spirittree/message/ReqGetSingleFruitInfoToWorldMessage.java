package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 世界服务器收到前端请求单个信息消息
 */
public class ReqGetSingleFruitInfoToWorldMessage extends Message{

	//玩家ID
	private long playerid;
	
	//类型：0查看，1浇水，2，浇仙露，3采摘
	private byte type;
	
	//果实ID
	private long fruitid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//类型：0查看，1浇水，2，浇仙露，3采摘
		writeByte(buf, this.type);
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
		//类型：0查看，1浇水，2，浇仙露，3采摘
		this.type = readByte(buf);
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
	 * get 类型：0查看，1浇水，2，浇仙露，3采摘
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0查看，1浇水，2，浇仙露，3采摘
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
	
	
	@Override
	public int getId() {
		return 133302;
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
		//类型：0查看，1浇水，2，浇仙露，3采摘
		buf.append("type:" + type +",");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}