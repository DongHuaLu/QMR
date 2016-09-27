package com.game.horseweapon.message;

import com.game.horseweapon.bean.HorseWeaponInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回客户端坐骑信息消息
 */
public class ResHorseWeaponInfoMessage extends Message{

	//骑乘兵器使用者ID
	private long playerid;
	
	//坐骑信息
	private HorseWeaponInfo info;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//骑乘兵器使用者ID
		writeLong(buf, this.playerid);
		//坐骑信息
		writeBean(buf, this.info);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//骑乘兵器使用者ID
		this.playerid = readLong(buf);
		//坐骑信息
		this.info = (HorseWeaponInfo)readBean(buf, HorseWeaponInfo.class);
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
	 * get 坐骑信息
	 * @return 
	 */
	public HorseWeaponInfo getInfo(){
		return info;
	}
	
	/**
	 * set 坐骑信息
	 */
	public void setInfo(HorseWeaponInfo info){
		this.info = info;
	}
	
	
	@Override
	public int getId() {
		return 155101;
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
		//坐骑信息
		if(this.info!=null) buf.append("info:" + info.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}