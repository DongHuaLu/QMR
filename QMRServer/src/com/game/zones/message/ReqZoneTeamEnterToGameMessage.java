package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 多人副本进入方式消息
 */
public class ReqZoneTeamEnterToGameMessage extends Message{

	//进入方式，0单人，1组队，2报名,3队伍报名
	private byte entertype;
	
	//副本ID
	private int zoneid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//进入方式，0单人，1组队，2报名,3队伍报名
		writeByte(buf, this.entertype);
		//副本ID
		writeInt(buf, this.zoneid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//进入方式，0单人，1组队，2报名,3队伍报名
		this.entertype = readByte(buf);
		//副本ID
		this.zoneid = readInt(buf);
		return true;
	}
	
	/**
	 * get 进入方式，0单人，1组队，2报名,3队伍报名
	 * @return 
	 */
	public byte getEntertype(){
		return entertype;
	}
	
	/**
	 * set 进入方式，0单人，1组队，2报名,3队伍报名
	 */
	public void setEntertype(byte entertype){
		this.entertype = entertype;
	}
	
	/**
	 * get 副本ID
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本ID
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	
	@Override
	public int getId() {
		return 128211;
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
		//进入方式，0单人，1组队，2报名,3队伍报名
		buf.append("entertype:" + entertype +",");
		//副本ID
		buf.append("zoneid:" + zoneid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}