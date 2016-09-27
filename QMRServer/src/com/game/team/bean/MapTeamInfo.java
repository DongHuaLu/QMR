package com.game.team.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 当前地图队伍信息类
 */
public class MapTeamInfo extends Bean {

	//队伍Id
	private long teamid;
	
	//队长Id
	private long captainid;
	
	//队长名字
	private String captainname;
	
	//队伍人数
	private byte teamnum;
	
	//队长等级
	private short captainlv;
	
	//最高等级
	private short highestlv;
	
	//平均等级
	private short averagelv;
	
	//所在线路
	private byte line;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//队长Id
		writeLong(buf, this.captainid);
		//队长名字
		writeString(buf, this.captainname);
		//队伍人数
		writeByte(buf, this.teamnum);
		//队长等级
		writeShort(buf, this.captainlv);
		//最高等级
		writeShort(buf, this.highestlv);
		//平均等级
		writeShort(buf, this.averagelv);
		//所在线路
		writeByte(buf, this.line);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//队长Id
		this.captainid = readLong(buf);
		//队长名字
		this.captainname = readString(buf);
		//队伍人数
		this.teamnum = readByte(buf);
		//队长等级
		this.captainlv = readShort(buf);
		//最高等级
		this.highestlv = readShort(buf);
		//平均等级
		this.averagelv = readShort(buf);
		//所在线路
		this.line = readByte(buf);
		return true;
	}
	
	/**
	 * get 队伍Id
	 * @return 
	 */
	public long getTeamid(){
		return teamid;
	}
	
	/**
	 * set 队伍Id
	 */
	public void setTeamid(long teamid){
		this.teamid = teamid;
	}
	
	/**
	 * get 队长Id
	 * @return 
	 */
	public long getCaptainid(){
		return captainid;
	}
	
	/**
	 * set 队长Id
	 */
	public void setCaptainid(long captainid){
		this.captainid = captainid;
	}
	
	/**
	 * get 队长名字
	 * @return 
	 */
	public String getCaptainname(){
		return captainname;
	}
	
	/**
	 * set 队长名字
	 */
	public void setCaptainname(String captainname){
		this.captainname = captainname;
	}
	
	/**
	 * get 队伍人数
	 * @return 
	 */
	public byte getTeamnum(){
		return teamnum;
	}
	
	/**
	 * set 队伍人数
	 */
	public void setTeamnum(byte teamnum){
		this.teamnum = teamnum;
	}
	
	/**
	 * get 队长等级
	 * @return 
	 */
	public short getCaptainlv(){
		return captainlv;
	}
	
	/**
	 * set 队长等级
	 */
	public void setCaptainlv(short captainlv){
		this.captainlv = captainlv;
	}
	
	/**
	 * get 最高等级
	 * @return 
	 */
	public short getHighestlv(){
		return highestlv;
	}
	
	/**
	 * set 最高等级
	 */
	public void setHighestlv(short highestlv){
		this.highestlv = highestlv;
	}
	
	/**
	 * get 平均等级
	 * @return 
	 */
	public short getAveragelv(){
		return averagelv;
	}
	
	/**
	 * set 平均等级
	 */
	public void setAveragelv(short averagelv){
		this.averagelv = averagelv;
	}
	
	/**
	 * get 所在线路
	 * @return 
	 */
	public byte getLine(){
		return line;
	}
	
	/**
	 * set 所在线路
	 */
	public void setLine(byte line){
		this.line = line;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//队长Id
		buf.append("captainid:" + captainid +",");
		//队长名字
		if(this.captainname!=null) buf.append("captainname:" + captainname.toString() +",");
		//队伍人数
		buf.append("teamnum:" + teamnum +",");
		//队长等级
		buf.append("captainlv:" + captainlv +",");
		//最高等级
		buf.append("highestlv:" + highestlv +",");
		//平均等级
		buf.append("averagelv:" + averagelv +",");
		//所在线路
		buf.append("line:" + line +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}