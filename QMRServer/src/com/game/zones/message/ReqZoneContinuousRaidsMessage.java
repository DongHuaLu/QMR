package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始连续扫荡消息
 */
public class ReqZoneContinuousRaidsMessage extends Message{

	//类型，1剧情副本，4七曜战将
	private int zonetype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型，1剧情副本，4七曜战将
		writeInt(buf, this.zonetype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型，1剧情副本，4七曜战将
		this.zonetype = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型，1剧情副本，4七曜战将
	 * @return 
	 */
	public int getZonetype(){
		return zonetype;
	}
	
	/**
	 * set 类型，1剧情副本，4七曜战将
	 */
	public void setZonetype(int zonetype){
		this.zonetype = zonetype;
	}
	
	
	@Override
	public int getId() {
		return 128208;
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
		//类型，1剧情副本，4七曜战将
		buf.append("zonetype:" + zonetype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}