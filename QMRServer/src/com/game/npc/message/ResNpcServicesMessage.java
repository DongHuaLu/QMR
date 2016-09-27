package com.game.npc.message;

import com.game.npc.bean.ServiceInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回服务器npc服务列表消息
 */
public class ResNpcServicesMessage extends Message{

	//npcId
	private long npcId;
	
	//npc服务列表集合
	private List<ServiceInfo> services = new ArrayList<ServiceInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//npcId
		writeLong(buf, this.npcId);
		//npc服务列表集合
		writeShort(buf, services.size());
		for (int i = 0; i < services.size(); i++) {
			writeBean(buf, services.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//npcId
		this.npcId = readLong(buf);
		//npc服务列表集合
		int services_length = readShort(buf);
		for (int i = 0; i < services_length; i++) {
			services.add((ServiceInfo)readBean(buf, ServiceInfo.class));
		}
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
	 * get npc服务列表集合
	 * @return 
	 */
	public List<ServiceInfo> getServices(){
		return services;
	}
	
	/**
	 * set npc服务列表集合
	 */
	public void setServices(List<ServiceInfo> services){
		this.services = services;
	}
	
	
	@Override
	public int getId() {
		return 140101;
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
		//npc服务列表集合
		buf.append("services:{");
		for (int i = 0; i < services.size(); i++) {
			buf.append(services.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}