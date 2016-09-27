package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 行会灵树操作消息
 */
public class ReqGuildFruitOperatingToGameMessage extends Message{

	//类型：1浇水，2采摘
	private byte type;
	
	//果实ID
	private long fruitid;
	
	//果实主人ID
	private long hostid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型：1浇水，2采摘
		writeByte(buf, this.type);
		//果实ID
		writeLong(buf, this.fruitid);
		//果实主人ID
		writeLong(buf, this.hostid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型：1浇水，2采摘
		this.type = readByte(buf);
		//果实ID
		this.fruitid = readLong(buf);
		//果实主人ID
		this.hostid = readLong(buf);
		return true;
	}
	
	/**
	 * get 类型：1浇水，2采摘
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：1浇水，2采摘
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	/**
	 * get 果实主人ID
	 * @return 
	 */
	public long getHostid(){
		return hostid;
	}
	
	/**
	 * set 果实主人ID
	 */
	public void setHostid(long hostid){
		this.hostid = hostid;
	}
	
	
	@Override
	public int getId() {
		return 133205;
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
		//类型：1浇水，2采摘
		buf.append("type:" + type +",");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		//果实主人ID
		buf.append("hostid:" + hostid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}