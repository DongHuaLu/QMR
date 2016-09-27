package com.game.horseweapon.message;

import com.game.horseweapon.bean.OthersHorseWeaponInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回他人骑乘兵器消息消息
 */
public class ResOthersHorseWeaponInfoMessage extends Message{

	//骑乘兵器使用者ID
	private long playerid;
	
	//骑乘兵器信息
	private OthersHorseWeaponInfo info;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//骑乘兵器使用者ID
		writeLong(buf, this.playerid);
		//骑乘兵器信息
		writeBean(buf, this.info);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//骑乘兵器使用者ID
		this.playerid = readLong(buf);
		//骑乘兵器信息
		this.info = (OthersHorseWeaponInfo)readBean(buf, OthersHorseWeaponInfo.class);
		return true;
	}
	
	/**
	 * get 骑乘兵器使用者ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 骑乘兵器使用者ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 骑乘兵器信息
	 * @return 
	 */
	public OthersHorseWeaponInfo getInfo(){
		return info;
	}
	
	/**
	 * set 骑乘兵器信息
	 */
	public void setInfo(OthersHorseWeaponInfo info){
		this.info = info;
	}
	
	
	@Override
	public int getId() {
		return 155105;
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
		//骑乘兵器使用者ID
		buf.append("playerid:" + playerid +",");
		//骑乘兵器信息
		if(this.info!=null) buf.append("info:" + info.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}