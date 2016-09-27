package com.game.arrow.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 领取七曜战魂消息
 */
public class ReqFightSpiritGetMessage extends Message{

	//领取类型
	private int gettype;
	
	//副本id
	private int zoneid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//领取类型
		writeInt(buf, this.gettype);
		//副本id
		writeInt(buf, this.zoneid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//领取类型
		this.gettype = readInt(buf);
		//副本id
		this.zoneid = readInt(buf);
		return true;
	}
	
	/**
	 * get 领取类型
	 * @return 
	 */
	public int getGettype(){
		return gettype;
	}
	
	/**
	 * set 领取类型
	 */
	public void setGettype(int gettype){
		this.gettype = gettype;
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
	
	
	@Override
	public int getId() {
		return 151205;
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
		//领取类型
		buf.append("gettype:" + gettype +",");
		//副本id
		buf.append("zoneid:" + zoneid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}