package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 地图广播炮弹轨迹消息
 */
public class ResCountryArtilleryLocusToClientMessage extends Message{

	//炮弹类型
	private byte type;
	
	//终点坐标X
	private int endx;
	
	//终点坐标Y
	private int endy;
	
	//开炮玩家ID
	private long playerid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//炮弹类型
		writeByte(buf, this.type);
		//终点坐标X
		writeInt(buf, this.endx);
		//终点坐标Y
		writeInt(buf, this.endy);
		//开炮玩家ID
		writeLong(buf, this.playerid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//炮弹类型
		this.type = readByte(buf);
		//终点坐标X
		this.endx = readInt(buf);
		//终点坐标Y
		this.endy = readInt(buf);
		//开炮玩家ID
		this.playerid = readLong(buf);
		return true;
	}
	
	/**
	 * get 炮弹类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 炮弹类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 终点坐标X
	 * @return 
	 */
	public int getEndx(){
		return endx;
	}
	
	/**
	 * set 终点坐标X
	 */
	public void setEndx(int endx){
		this.endx = endx;
	}
	
	/**
	 * get 终点坐标Y
	 * @return 
	 */
	public int getEndy(){
		return endy;
	}
	
	/**
	 * set 终点坐标Y
	 */
	public void setEndy(int endy){
		this.endy = endy;
	}
	
	/**
	 * get 开炮玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 开炮玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	
	@Override
	public int getId() {
		return 146106;
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
		//炮弹类型
		buf.append("type:" + type +",");
		//终点坐标X
		buf.append("endx:" + endx +",");
		//终点坐标Y
		buf.append("endy:" + endy +",");
		//开炮玩家ID
		buf.append("playerid:" + playerid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}