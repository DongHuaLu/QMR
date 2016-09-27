package com.game.marriage.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 婚宴信息
 */
public class WeddingInfo extends Bean {

	//结婚id
	private long marriageid;
	
	//开始时间（秒）
	private int time;
	
	//婚宴类型，1普通，2大型，3豪华
	private byte type;
	
	//婚姻状态，0未开始，1进行中，2结束
	private byte status;
	
	//新郎名字
	private String bridegroom;
	
	//新娘名字
	private String bride;
	
	//红包数量
	private int rednum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//结婚id
		writeLong(buf, this.marriageid);
		//开始时间（秒）
		writeInt(buf, this.time);
		//婚宴类型，1普通，2大型，3豪华
		writeByte(buf, this.type);
		//婚姻状态，0未开始，1进行中，2结束
		writeByte(buf, this.status);
		//新郎名字
		writeString(buf, this.bridegroom);
		//新娘名字
		writeString(buf, this.bride);
		//红包数量
		writeInt(buf, this.rednum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//结婚id
		this.marriageid = readLong(buf);
		//开始时间（秒）
		this.time = readInt(buf);
		//婚宴类型，1普通，2大型，3豪华
		this.type = readByte(buf);
		//婚姻状态，0未开始，1进行中，2结束
		this.status = readByte(buf);
		//新郎名字
		this.bridegroom = readString(buf);
		//新娘名字
		this.bride = readString(buf);
		//红包数量
		this.rednum = readInt(buf);
		return true;
	}
	
	/**
	 * get 结婚id
	 * @return 
	 */
	public long getMarriageid(){
		return marriageid;
	}
	
	/**
	 * set 结婚id
	 */
	public void setMarriageid(long marriageid){
		this.marriageid = marriageid;
	}
	
	/**
	 * get 开始时间（秒）
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 开始时间（秒）
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 婚宴类型，1普通，2大型，3豪华
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 婚宴类型，1普通，2大型，3豪华
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 婚姻状态，0未开始，1进行中，2结束
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 婚姻状态，0未开始，1进行中，2结束
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	/**
	 * get 新郎名字
	 * @return 
	 */
	public String getBridegroom(){
		return bridegroom;
	}
	
	/**
	 * set 新郎名字
	 */
	public void setBridegroom(String bridegroom){
		this.bridegroom = bridegroom;
	}
	
	/**
	 * get 新娘名字
	 * @return 
	 */
	public String getBride(){
		return bride;
	}
	
	/**
	 * set 新娘名字
	 */
	public void setBride(String bride){
		this.bride = bride;
	}
	
	/**
	 * get 红包数量
	 * @return 
	 */
	public int getRednum(){
		return rednum;
	}
	
	/**
	 * set 红包数量
	 */
	public void setRednum(int rednum){
		this.rednum = rednum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		//开始时间（秒）
		buf.append("time:" + time +",");
		//婚宴类型，1普通，2大型，3豪华
		buf.append("type:" + type +",");
		//婚姻状态，0未开始，1进行中，2结束
		buf.append("status:" + status +",");
		//新郎名字
		if(this.bridegroom!=null) buf.append("bridegroom:" + bridegroom.toString() +",");
		//新娘名字
		if(this.bride!=null) buf.append("bride:" + bride.toString() +",");
		//红包数量
		buf.append("rednum:" + rednum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}