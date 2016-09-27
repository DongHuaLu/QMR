package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 组队副本开放时，国家公告消息
 */
public class ResZoneTeamOpenBullToClientMessage extends Message{

	//副本模版编号
	private int zonemodelid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本模版编号
		writeInt(buf, this.zonemodelid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本模版编号
		this.zonemodelid = readInt(buf);
		return true;
	}
	
	/**
	 * get 副本模版编号
	 * @return 
	 */
	public int getZonemodelid(){
		return zonemodelid;
	}
	
	/**
	 * set 副本模版编号
	 */
	public void setZonemodelid(int zonemodelid){
		this.zonemodelid = zonemodelid;
	}
	
	
	@Override
	public int getId() {
		return 128119;
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
		//副本模版编号
		buf.append("zonemodelid:" + zonemodelid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}