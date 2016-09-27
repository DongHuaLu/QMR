package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 连续扫荡完成消息
 */
public class ResZoneContinuousRaidsEndMessage extends Message{

	//当前完成的副本编号
	private int zoneid;
	
	//1表示全部完成，2表示停止扫荡
	private byte type;
	
	//类型，1剧情副本，4七曜战将
	private int zonetype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前完成的副本编号
		writeInt(buf, this.zoneid);
		//1表示全部完成，2表示停止扫荡
		writeByte(buf, this.type);
		//类型，1剧情副本，4七曜战将
		writeInt(buf, this.zonetype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前完成的副本编号
		this.zoneid = readInt(buf);
		//1表示全部完成，2表示停止扫荡
		this.type = readByte(buf);
		//类型，1剧情副本，4七曜战将
		this.zonetype = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前完成的副本编号
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 当前完成的副本编号
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 1表示全部完成，2表示停止扫荡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 1表示全部完成，2表示停止扫荡
	 */
	public void setType(byte type){
		this.type = type;
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
		return 128115;
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
		//当前完成的副本编号
		buf.append("zoneid:" + zoneid +",");
		//1表示全部完成，2表示停止扫荡
		buf.append("type:" + type +",");
		//类型，1剧情副本，4七曜战将
		buf.append("zonetype:" + zonetype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}