package com.game.ybcard.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开公测元宝卡到世界服务器消息
 */
public class ReqYBCardToWorldMessage extends Message{

	//玩家Id
	private long playerid;
	
	//类型，0打开面板，1使用元宝卡，2领取元宝
	private byte type;
	
	//使用数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家Id
		writeLong(buf, this.playerid);
		//类型，0打开面板，1使用元宝卡，2领取元宝
		writeByte(buf, this.type);
		//使用数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家Id
		this.playerid = readLong(buf);
		//类型，0打开面板，1使用元宝卡，2领取元宝
		this.type = readByte(buf);
		//使用数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家Id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家Id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 类型，0打开面板，1使用元宝卡，2领取元宝
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型，0打开面板，1使用元宝卡，2领取元宝
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 使用数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 使用数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	
	@Override
	public int getId() {
		return 139301;
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
		//玩家Id
		buf.append("playerid:" + playerid +",");
		//类型，0打开面板，1使用元宝卡，2领取元宝
		buf.append("type:" + type +",");
		//使用数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}