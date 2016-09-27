package com.game.transactions.message;

import com.game.transactions.bean.RoleModeInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * B玩家同意，开始交易消息
 */
public class ResTransactionsStartMessage extends Message{

	//A交易玩家造型信息
	private RoleModeInfo arolemodeinfo;
	
	//B交易玩家造型信息
	private RoleModeInfo brolemodeinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//A交易玩家造型信息
		writeBean(buf, this.arolemodeinfo);
		//B交易玩家造型信息
		writeBean(buf, this.brolemodeinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//A交易玩家造型信息
		this.arolemodeinfo = (RoleModeInfo)readBean(buf, RoleModeInfo.class);
		//B交易玩家造型信息
		this.brolemodeinfo = (RoleModeInfo)readBean(buf, RoleModeInfo.class);
		return true;
	}
	
	/**
	 * get A交易玩家造型信息
	 * @return 
	 */
	public RoleModeInfo getArolemodeinfo(){
		return arolemodeinfo;
	}
	
	/**
	 * set A交易玩家造型信息
	 */
	public void setArolemodeinfo(RoleModeInfo arolemodeinfo){
		this.arolemodeinfo = arolemodeinfo;
	}
	
	/**
	 * get B交易玩家造型信息
	 * @return 
	 */
	public RoleModeInfo getBrolemodeinfo(){
		return brolemodeinfo;
	}
	
	/**
	 * set B交易玩家造型信息
	 */
	public void setBrolemodeinfo(RoleModeInfo brolemodeinfo){
		this.brolemodeinfo = brolemodeinfo;
	}
	
	
	@Override
	public int getId() {
		return 122103;
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
		//A交易玩家造型信息
		if(this.arolemodeinfo!=null) buf.append("arolemodeinfo:" + arolemodeinfo.toString() +",");
		//B交易玩家造型信息
		if(this.brolemodeinfo!=null) buf.append("brolemodeinfo:" + brolemodeinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}