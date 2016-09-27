package com.game.horse.message;

import com.game.horse.bean.OthersHorseInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回他人坐骑消息消息
 */
public class ResOthersHorseInfoMessage extends Message{

	//坐骑使用者ID
	private long playerid;
	
	//坐骑信息
	private OthersHorseInfo info;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//坐骑使用者ID
		writeLong(buf, this.playerid);
		//坐骑信息
		writeBean(buf, this.info);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//坐骑使用者ID
		this.playerid = readLong(buf);
		//坐骑信息
		this.info = (OthersHorseInfo)readBean(buf, OthersHorseInfo.class);
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
	public OthersHorseInfo getInfo(){
		return info;
	}
	
	/**
	 * set 坐骑信息
	 */
	public void setInfo(OthersHorseInfo info){
		this.info = info;
	}
	
	
	@Override
	public int getId() {
		return 126112;
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
		if(this.info!=null) buf.append("info:" + info.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}