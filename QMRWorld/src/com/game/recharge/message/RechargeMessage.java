package com.game.recharge.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 充值消息消息
 */
public class RechargeMessage extends Message{

	//订单ID
	private String oid;
	
	//充值数
	private int rechargeParam;
	
	//角色ID
	private long playerId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//订单ID
		writeString(buf, this.oid);
		//充值数
		writeInt(buf, this.rechargeParam);
		//角色ID
		writeLong(buf, this.playerId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//订单ID
		this.oid = readString(buf);
		//充值数
		this.rechargeParam = readInt(buf);
		//角色ID
		this.playerId = readLong(buf);
		return true;
	}
	
	/**
	 * get 订单ID
	 * @return 
	 */
	public String getOid(){
		return oid;
	}
	
	/**
	 * set 订单ID
	 */
	public void setOid(String oid){
		this.oid = oid;
	}
	
	/**
	 * get 充值数
	 * @return 
	 */
	public int getRechargeParam(){
		return rechargeParam;
	}
	
	/**
	 * set 充值数
	 */
	public void setRechargeParam(int rechargeParam){
		this.rechargeParam = rechargeParam;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	
	@Override
	public int getId() {
		return 134301;
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
		//订单ID
		if(this.oid!=null) buf.append("oid:" + oid.toString() +",");
		//充值数
		buf.append("rechargeParam:" + rechargeParam +",");
		//角色ID
		buf.append("playerId:" + playerId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}