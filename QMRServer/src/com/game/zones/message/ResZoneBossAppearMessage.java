package com.game.zones.message;

import com.game.zones.bean.ZoneMonstrInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * BOSS出现消息
 */
public class ResZoneBossAppearMessage extends Message{

	//副本怪物信息
	private ZoneMonstrInfo zoenmonstrinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本怪物信息
		writeBean(buf, this.zoenmonstrinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本怪物信息
		this.zoenmonstrinfo = (ZoneMonstrInfo)readBean(buf, ZoneMonstrInfo.class);
		return true;
	}
	
	/**
	 * get 副本怪物信息
	 * @return 
	 */
	public ZoneMonstrInfo getZoenmonstrinfo(){
		return zoenmonstrinfo;
	}
	
	/**
	 * set 副本怪物信息
	 */
	public void setZoenmonstrinfo(ZoneMonstrInfo zoenmonstrinfo){
		this.zoenmonstrinfo = zoenmonstrinfo;
	}
	
	
	@Override
	public int getId() {
		return 128111;
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
		//副本怪物信息
		if(this.zoenmonstrinfo!=null) buf.append("zoenmonstrinfo:" + zoenmonstrinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}