package com.game.horse.message;

import com.game.horse.bean.HorseInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回客户端坐骑信息消息
 */
public class ResHorseInfoMessage extends Message{

	//坐骑使用者ID
	private long playerid;
	
	//坐骑信息
	private HorseInfo horseinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//坐骑使用者ID
		writeLong(buf, this.playerid);
		//坐骑信息
		writeBean(buf, this.horseinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//坐骑使用者ID
		this.playerid = readLong(buf);
		//坐骑信息
		this.horseinfo = (HorseInfo)readBean(buf, HorseInfo.class);
		return true;
	}
	
	/**
	 * get 坐骑使用者ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 坐骑使用者ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 坐骑信息
	 * @return 
	 */
	public HorseInfo getHorseinfo(){
		return horseinfo;
	}
	
	/**
	 * set 坐骑信息
	 */
	public void setHorseinfo(HorseInfo horseinfo){
		this.horseinfo = horseinfo;
	}
	
	
	@Override
	public int getId() {
		return 126101;
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
		//坐骑使用者ID
		buf.append("playerid:" + playerid +",");
		//坐骑信息
		if(this.horseinfo!=null) buf.append("horseinfo:" + horseinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}