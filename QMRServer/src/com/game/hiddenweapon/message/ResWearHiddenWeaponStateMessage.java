package com.game.hiddenweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送暗器装备状态消息
 */
public class ResWearHiddenWeaponStateMessage extends Message{

	//暗器使用者ID
	private long playerid;
	
	//当前使用的暗器阶层
	private short curlayer;
	
	//是否装备，1装备，0卸下
	private byte status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//暗器使用者ID
		writeLong(buf, this.playerid);
		//当前使用的暗器阶层
		writeShort(buf, this.curlayer);
		//是否装备，1装备，0卸下
		writeByte(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//暗器使用者ID
		this.playerid = readLong(buf);
		//当前使用的暗器阶层
		this.curlayer = readShort(buf);
		//是否装备，1装备，0卸下
		this.status = readByte(buf);
		return true;
	}
	
	/**
	 * get 暗器使用者ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 暗器使用者ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 当前使用的暗器阶层
	 * @return 
	 */
	public short getCurlayer(){
		return curlayer;
	}
	
	/**
	 * set 当前使用的暗器阶层
	 */
	public void setCurlayer(short curlayer){
		this.curlayer = curlayer;
	}
	
	/**
	 * get 是否装备，1装备，0卸下
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 是否装备，1装备，0卸下
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 162102;
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
		//暗器使用者ID
		buf.append("playerid:" + playerid +",");
		//当前使用的暗器阶层
		buf.append("curlayer:" + curlayer +",");
		//是否装备，1装备，0卸下
		buf.append("status:" + status +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}