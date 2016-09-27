package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 攻城战即时信息
 */
public class CountryWarInfo extends Bean {

	//玉玺持有人帮会ID
	private long holderguildid;
	
	//玉玺持有人ID
	private long holderid;
	
	//玉玺持有人名字
	private String holdername;
	
	//玉玺持有人坐标X
	private short mx;
	
	//玉玺持有人坐标Y
	private short my;
	
	//玉玺持有时间（秒）
	private int holdertime;
	
	//攻城战结束剩余时间（秒）
	private int warendtime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玉玺持有人帮会ID
		writeLong(buf, this.holderguildid);
		//玉玺持有人ID
		writeLong(buf, this.holderid);
		//玉玺持有人名字
		writeString(buf, this.holdername);
		//玉玺持有人坐标X
		writeShort(buf, this.mx);
		//玉玺持有人坐标Y
		writeShort(buf, this.my);
		//玉玺持有时间（秒）
		writeInt(buf, this.holdertime);
		//攻城战结束剩余时间（秒）
		writeInt(buf, this.warendtime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玉玺持有人帮会ID
		this.holderguildid = readLong(buf);
		//玉玺持有人ID
		this.holderid = readLong(buf);
		//玉玺持有人名字
		this.holdername = readString(buf);
		//玉玺持有人坐标X
		this.mx = readShort(buf);
		//玉玺持有人坐标Y
		this.my = readShort(buf);
		//玉玺持有时间（秒）
		this.holdertime = readInt(buf);
		//攻城战结束剩余时间（秒）
		this.warendtime = readInt(buf);
		return true;
	}
	
	/**
	 * get 玉玺持有人帮会ID
	 * @return 
	 */
	public long getHolderguildid(){
		return holderguildid;
	}
	
	/**
	 * set 玉玺持有人帮会ID
	 */
	public void setHolderguildid(long holderguildid){
		this.holderguildid = holderguildid;
	}
	
	/**
	 * get 玉玺持有人ID
	 * @return 
	 */
	public long getHolderid(){
		return holderid;
	}
	
	/**
	 * set 玉玺持有人ID
	 */
	public void setHolderid(long holderid){
		this.holderid = holderid;
	}
	
	/**
	 * get 玉玺持有人名字
	 * @return 
	 */
	public String getHoldername(){
		return holdername;
	}
	
	/**
	 * set 玉玺持有人名字
	 */
	public void setHoldername(String holdername){
		this.holdername = holdername;
	}
	
	/**
	 * get 玉玺持有人坐标X
	 * @return 
	 */
	public short getMx(){
		return mx;
	}
	
	/**
	 * set 玉玺持有人坐标X
	 */
	public void setMx(short mx){
		this.mx = mx;
	}
	
	/**
	 * get 玉玺持有人坐标Y
	 * @return 
	 */
	public short getMy(){
		return my;
	}
	
	/**
	 * set 玉玺持有人坐标Y
	 */
	public void setMy(short my){
		this.my = my;
	}
	
	/**
	 * get 玉玺持有时间（秒）
	 * @return 
	 */
	public int getHoldertime(){
		return holdertime;
	}
	
	/**
	 * set 玉玺持有时间（秒）
	 */
	public void setHoldertime(int holdertime){
		this.holdertime = holdertime;
	}
	
	/**
	 * get 攻城战结束剩余时间（秒）
	 * @return 
	 */
	public int getWarendtime(){
		return warendtime;
	}
	
	/**
	 * set 攻城战结束剩余时间（秒）
	 */
	public void setWarendtime(int warendtime){
		this.warendtime = warendtime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玉玺持有人帮会ID
		buf.append("holderguildid:" + holderguildid +",");
		//玉玺持有人ID
		buf.append("holderid:" + holderid +",");
		//玉玺持有人名字
		if(this.holdername!=null) buf.append("holdername:" + holdername.toString() +",");
		//玉玺持有人坐标X
		buf.append("mx:" + mx +",");
		//玉玺持有人坐标Y
		buf.append("my:" + my +",");
		//玉玺持有时间（秒）
		buf.append("holdertime:" + holdertime +",");
		//攻城战结束剩余时间（秒）
		buf.append("warendtime:" + warendtime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}