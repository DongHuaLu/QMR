package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求手机绑定消息
 */
public class ReqCardPhoneToServerMessage extends Message{

	//账号
	private String account;
	
	//角色id
	private long playerid;
	
	//电话
	private String phone;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//账号
		writeString(buf, this.account);
		//角色id
		writeLong(buf, this.playerid);
		//电话
		writeString(buf, this.phone);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//账号
		this.account = readString(buf);
		//角色id
		this.playerid = readLong(buf);
		//电话
		this.phone = readString(buf);
		return true;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 * set 账号
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
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
	
	
	@Override
	public int getId() {
		return 137102;
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
		//账号
		if(this.account!=null) buf.append("account:" + account.toString() +",");
		//角色id
		buf.append("playerid:" + playerid +",");
		//电话
		if(this.phone!=null) buf.append("phone:" + phone.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}