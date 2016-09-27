package com.game.arrow.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回战魂搜索次数消息
 */
public class ResFightSpiritGetNumMessage extends Message{

	//已经搜索次数
	private int num;
	
	//副本id
	private int zoneid;
	
	//是否第一次搜索
	private int first;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//已经搜索次数
		writeInt(buf, this.num);
		//副本id
		writeInt(buf, this.zoneid);
		//是否第一次搜索
		writeInt(buf, this.first);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//已经搜索次数
		this.num = readInt(buf);
		//副本id
		this.zoneid = readInt(buf);
		//是否第一次搜索
		this.first = readInt(buf);
		return true;
	}
	
	/**
	 * get 已经搜索次数
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 已经搜索次数
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 副本id
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本id
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 是否第一次搜索
	 * @return 
	 */
	public int getFirst(){
		return first;
	}
	
	/**
	 * set 是否第一次搜索
	 */
	public void setFirst(int first){
		this.first = first;
	}
	
	
	@Override
	public int getId() {
		return 151105;
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
		//已经搜索次数
		buf.append("num:" + num +",");
		//副本id
		buf.append("zoneid:" + zoneid +",");
		//是否第一次搜索
		buf.append("first:" + first +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}