package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 取消报名消息
 */
public class ReqZoneCancelSignupToGameMessage extends Message{

	//类型，1取消单人报名，2取消组队报名
	private int type;
	
	//副本ID
	private int zoneid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型，1取消单人报名，2取消组队报名
		writeInt(buf, this.type);
		//副本ID
		writeInt(buf, this.zoneid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型，1取消单人报名，2取消组队报名
		this.type = readInt(buf);
		//副本ID
		this.zoneid = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型，1取消单人报名，2取消组队报名
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型，1取消单人报名，2取消组队报名
	 */
	public void setType(int type){
		this.type = type;
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
		return 128216;
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
		//类型，1取消单人报名，2取消组队报名
		buf.append("type:" + type +",");
		//副本ID
		buf.append("zoneid:" + zoneid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}