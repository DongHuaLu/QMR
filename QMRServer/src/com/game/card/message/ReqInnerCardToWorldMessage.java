package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 内部请求签证CDKEY给世界服务器消息
 */
public class ReqInnerCardToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//CDKEY
	private String card;
	
	//平台账号名字
	private String arguserName;
	
	//平台区服务器标识
	private String argzoneName;
	
	//平台名字标识
	private String argName;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//CDKEY
		writeString(buf, this.card);
		//平台账号名字
		writeString(buf, this.arguserName);
		//平台区服务器标识
		writeString(buf, this.argzoneName);
		//平台名字标识
		writeString(buf, this.argName);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//CDKEY
		this.card = readString(buf);
		//平台账号名字
		this.arguserName = readString(buf);
		//平台区服务器标识
		this.argzoneName = readString(buf);
		//平台名字标识
		this.argName = readString(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get CDKEY
	 * @return 
	 */
	public String getCard(){
		return card;
	}
	
	/**
	 * set CDKEY
	 */
	public void setCard(String card){
		this.card = card;
	}
	
	/**
	 * get 平台账号名字
	 * @return 
	 */
	public String getArguserName(){
		return arguserName;
	}
	
	/**
	 * set 平台账号名字
	 */
	public void setArguserName(String arguserName){
		this.arguserName = arguserName;
	}
	
	/**
	 * get 平台区服务器标识
	 * @return 
	 */
	public String getArgzoneName(){
		return argzoneName;
	}
	
	/**
	 * set 平台区服务器标识
	 */
	public void setArgzoneName(String argzoneName){
		this.argzoneName = argzoneName;
	}
	
	/**
	 * get 平台名字标识
	 * @return 
	 */
	public String getArgName(){
		return argName;
	}
	
	/**
	 * set 平台名字标识
	 */
	public void setArgName(String argName){
		this.argName = argName;
	}
	
	
	@Override
	public int getId() {
		return 137201;
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
		//角色Id
		buf.append("playerId:" + playerId +",");
		//CDKEY
		if(this.card!=null) buf.append("card:" + card.toString() +",");
		//平台账号名字
		if(this.arguserName!=null) buf.append("arguserName:" + arguserName.toString() +",");
		//平台区服务器标识
		if(this.argzoneName!=null) buf.append("argzoneName:" + argzoneName.toString() +",");
		//平台名字标识
		if(this.argName!=null) buf.append("argName:" + argName.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}