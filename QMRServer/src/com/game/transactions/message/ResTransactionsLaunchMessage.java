package com.game.transactions.message;

import com.game.transactions.bean.RoleModeInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发给B玩家交易请求消息
 */
public class ResTransactionsLaunchMessage extends Message{

	//A交易玩家造型信息
	private RoleModeInfo rolemodeinfo;
	
	//发起交易时间
	private int launchtime;
	
	//交易ID
	private long transid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//A交易玩家造型信息
		writeBean(buf, this.rolemodeinfo);
		//发起交易时间
		writeInt(buf, this.launchtime);
		//交易ID
		writeLong(buf, this.transid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//A交易玩家造型信息
		this.rolemodeinfo = (RoleModeInfo)readBean(buf, RoleModeInfo.class);
		//发起交易时间
		this.launchtime = readInt(buf);
		//交易ID
		this.transid = readLong(buf);
		return true;
	}
	
	/**
	 * get A交易玩家造型信息
	 * @return 
	 */
	public RoleModeInfo getRolemodeinfo(){
		return rolemodeinfo;
	}
	
	/**
	 * set A交易玩家造型信息
	 */
	public void setRolemodeinfo(RoleModeInfo rolemodeinfo){
		this.rolemodeinfo = rolemodeinfo;
	}
	
	/**
	 * get 发起交易时间
	 * @return 
	 */
	public int getLaunchtime(){
		return launchtime;
	}
	
	/**
	 * set 发起交易时间
	 */
	public void setLaunchtime(int launchtime){
		this.launchtime = launchtime;
	}
	
	/**
	 * get 交易ID
	 * @return 
	 */
	public long getTransid(){
		return transid;
	}
	
	/**
	 * set 交易ID
	 */
	public void setTransid(long transid){
		this.transid = transid;
	}
	
	
	@Override
	public int getId() {
		return 122101;
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
		if(this.rolemodeinfo!=null) buf.append("rolemodeinfo:" + rolemodeinfo.toString() +",");
		//发起交易时间
		buf.append("launchtime:" + launchtime +",");
		//交易ID
		buf.append("transid:" + transid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}