package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家造型信息
 */
public class PlayerModeInfo extends Bean {

	//玩家id
	private long userId;
	
	//玩家头像icon
	private int icon;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.userId);
		//玩家头像icon
		writeInt(buf, this.icon);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.userId = readLong(buf);
		//玩家头像icon
		this.icon = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getUserId(){
		return userId;
	}
	
	/**
	 * set 玩家id
	 */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * get 玩家头像icon
	 * @return 
	 */
	public int getIcon(){
		return icon;
	}
	
	/**
	 * set 玩家头像icon
	 */
	public void setIcon(int icon){
		this.icon = icon;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家id
		buf.append("userId:" + userId +",");
		//玩家头像icon
		buf.append("icon:" + icon +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}