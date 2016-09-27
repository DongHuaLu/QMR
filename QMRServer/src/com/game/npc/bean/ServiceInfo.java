package com.game.npc.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * NPC服务信息类
 */
public class ServiceInfo extends Bean {

	//服务Id
	private int serviceId;
	
	//服务名称
	private String serviceName;
	
	//服务参数
	private String serviceParameter;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//服务Id
		writeInt(buf, this.serviceId);
		//服务名称
		writeString(buf, this.serviceName);
		//服务参数
		writeString(buf, this.serviceParameter);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//服务Id
		this.serviceId = readInt(buf);
		//服务名称
		this.serviceName = readString(buf);
		//服务参数
		this.serviceParameter = readString(buf);
		return true;
	}
	
	/**
	 * get 服务Id
	 * @return 
	 */
	public int getServiceId(){
		return serviceId;
	}
	
	/**
	 * set 服务Id
	 */
	public void setServiceId(int serviceId){
		this.serviceId = serviceId;
	}
	
	/**
	 * get 服务名称
	 * @return 
	 */
	public String getServiceName(){
		return serviceName;
	}
	
	/**
	 * set 服务名称
	 */
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
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
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//服务Id
		buf.append("serviceId:" + serviceId +",");
		//服务名称
		if(this.serviceName!=null) buf.append("serviceName:" + serviceName.toString() +",");
		//服务参数
		if(this.serviceParameter!=null) buf.append("serviceParameter:" + serviceParameter.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}