package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 转发元旦台历领取信息到世界服消息
 */
public class ReqGetTailiToWorldMessage extends Message{

	//角色ID
	private long playerid;
	
	//姓名
	private String name;
	
	//电话
	private String phone;
	
	//地址
	private String addr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerid);
		//姓名
		writeString(buf, this.name);
		//电话
		writeString(buf, this.phone);
		//地址
		writeString(buf, this.addr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerid = readLong(buf);
		//姓名
		this.name = readString(buf);
		//电话
		this.phone = readString(buf);
		//地址
		this.addr = readString(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 姓名
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 姓名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 电话
	 * @return 
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * set 电话
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * get 地址
	 * @return 
	 */
	public String getAddr(){
		return addr;
	}
	
	/**
	 * set 地址
	 */
	public void setAddr(String addr){
		this.addr = addr;
	}
	
	
	@Override
	public int getId() {
		return 138303;
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
		//角色ID
		buf.append("playerid:" + playerid +",");
		//姓名
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//电话
		if(this.phone!=null) buf.append("phone:" + phone.toString() +",");
		//地址
		if(this.addr!=null) buf.append("addr:" + addr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}