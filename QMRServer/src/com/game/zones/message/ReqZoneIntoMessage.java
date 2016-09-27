package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 进入副本消息
 */
public class ReqZoneIntoMessage extends Message{

	//副本编号
	private int zoneid;
	
	//是否自动：0手动进入，1自动扫荡
	private byte isauto;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本编号
		writeInt(buf, this.zoneid);
		//是否自动：0手动进入，1自动扫荡
		writeByte(buf, this.isauto);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本编号
		this.zoneid = readInt(buf);
		//是否自动：0手动进入，1自动扫荡
		this.isauto = readByte(buf);
		return true;
	}
	
	/**
	 * get 副本编号
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本编号
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 是否自动：0手动进入，1自动扫荡
	 * @return 
	 */
	public byte getIsauto(){
		return isauto;
	}
	
	/**
	 * set 是否自动：0手动进入，1自动扫荡
	 */
	public void setIsauto(byte isauto){
		this.isauto = isauto;
	}
	
	
	@Override
	public int getId() {
		return 128202;
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
		//副本编号
		buf.append("zoneid:" + zoneid +",");
		//是否自动：0手动进入，1自动扫荡
		buf.append("isauto:" + isauto +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}