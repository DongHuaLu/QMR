package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家防沉迷注册身份证消息
 */
public class ReqPlayerNonageRegisterMessage extends Message{

	//名字
	private String name;
	
	//证件号码
	private String idCard;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//名字
		writeString(buf, this.name);
		//证件号码
		writeString(buf, this.idCard);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//名字
		this.name = readString(buf);
		//证件号码
		this.idCard = readString(buf);
		return true;
	}
	
	/**
	 * get 名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 证件号码
	 * @return 
	 */
	public String getIdCard(){
		return idCard;
	}
	
	/**
	 * set 证件号码
	 */
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	
	
	@Override
	public int getId() {
		return 103208;
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
		//名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//证件号码
		if(this.idCard!=null) buf.append("idCard:" + idCard.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}