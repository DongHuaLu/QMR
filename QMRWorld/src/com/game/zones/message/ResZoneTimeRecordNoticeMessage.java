package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 时间破记录公告消息
 */
public class ResZoneTimeRecordNoticeMessage extends Message{

	//全服最快通关时间（时间秒）
	private int fulltime;
	
	//全服最快通关者名字
	private String fullname;
	
	//副本编号
	private int zoneid;
	
	//全服最快通关者id
	private long playerid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//全服最快通关时间（时间秒）
		writeInt(buf, this.fulltime);
		//全服最快通关者名字
		writeString(buf, this.fullname);
		//副本编号
		writeInt(buf, this.zoneid);
		//全服最快通关者id
		writeLong(buf, this.playerid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//全服最快通关时间（时间秒）
		this.fulltime = readInt(buf);
		//全服最快通关者名字
		this.fullname = readString(buf);
		//副本编号
		this.zoneid = readInt(buf);
		//全服最快通关者id
		this.playerid = readLong(buf);
		return true;
	}
	
	/**
	 * get 全服最快通关时间（时间秒）
	 * @return 
	 */
	public int getFulltime(){
		return fulltime;
	}
	
	/**
	 * set 全服最快通关时间（时间秒）
	 */
	public void setFulltime(int fulltime){
		this.fulltime = fulltime;
	}
	
	/**
	 * get 全服最快通关者名字
	 * @return 
	 */
	public String getFullname(){
		return fullname;
	}
	
	/**
	 * set 全服最快通关者名字
	 */
	public void setFullname(String fullname){
		this.fullname = fullname;
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
	 * get 全服最快通关者id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 全服最快通关者id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	
	@Override
	public int getId() {
		return 128112;
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
		//全服最快通关时间（时间秒）
		buf.append("fulltime:" + fulltime +",");
		//全服最快通关者名字
		if(this.fullname!=null) buf.append("fullname:" + fullname.toString() +",");
		//副本编号
		buf.append("zoneid:" + zoneid +",");
		//全服最快通关者id
		buf.append("playerid:" + playerid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}