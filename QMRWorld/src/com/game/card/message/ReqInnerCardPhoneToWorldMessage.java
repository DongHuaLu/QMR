package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 内部请求手机绑定给世界服务器消息
 */
public class ReqInnerCardPhoneToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
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
		//角色Id
		writeLong(buf, this.playerId);
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
		//角色Id
		this.playerId = readLong(buf);
		//账号
		this.account = readString(buf);
		//角色id
		this.playerid = readLong(buf);
		//电话
		this.phone = readString(buf);
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
		return 137202;
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