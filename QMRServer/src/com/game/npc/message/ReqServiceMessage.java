package com.game.npc.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求npc脚本服务消息
 */
public class ReqServiceMessage extends Message{

	//npcId
	private long npcId;
	
	//服务参数
	private String serviceParameter;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//npcId
		writeLong(buf, this.npcId);
		//服务参数
		writeString(buf, this.serviceParameter);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//npcId
		this.npcId = readLong(buf);
		//服务参数
		this.serviceParameter = readString(buf);
		return true;
	}
	
	/**
	 * get npcId
	 * @return 
	 */
	public long getNpcId(){
		return npcId;
	}
	
	/**
	 * set npcId
	 */
	public void setNpcId(long npcId){
		this.npcId = npcId;
	}
	
	/**
	 * get 服务参数
	 * @return 
	 */
	public String getServiceParameter(){
		return serviceParameter;
	}
	
	/**
	 * set 服务参数
	 */
	public void setServiceParameter(String serviceParameter){
		this.serviceParameter = serviceParameter;
	}
	
	
	@Override
	public int getId() {
		return 140202;
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
		//npcId
		buf.append("npcId:" + npcId +",");
		//服务参数
		if(this.serviceParameter!=null) buf.append("serviceParameter:" + serviceParameter.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}