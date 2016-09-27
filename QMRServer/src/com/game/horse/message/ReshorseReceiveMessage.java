package com.game.horse.message;

import com.game.horse.bean.HorseInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 响应领取坐骑消息
 */
public class ReshorseReceiveMessage extends Message{

	//坐骑信息
	private HorseInfo horseinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//坐骑信息
		writeBean(buf, this.horseinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//坐骑信息
		this.horseinfo = (HorseInfo)readBean(buf, HorseInfo.class);
		return true;
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
		return 126108;
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
		//坐骑信息
		if(this.horseinfo!=null) buf.append("horseinfo:" + horseinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}