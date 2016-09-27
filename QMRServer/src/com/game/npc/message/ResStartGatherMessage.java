package com.game.npc.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家开始采集消息
 */
public class ResStartGatherMessage extends Message{

	//角色Id
	private long personId;
	
	//行为目标
	private long tatget;
	
	//采集时间
	private int costtime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//行为目标
		writeLong(buf, this.tatget);
		//采集时间
		writeInt(buf, this.costtime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//行为目标
		this.tatget = readLong(buf);
		//采集时间
		this.costtime = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 行为目标
	 * @return 
	 */
	public long getTatget(){
		return tatget;
	}
	
	/**
	 * set 行为目标
	 */
	public void setTatget(long tatget){
		this.tatget = tatget;
	}
	
	/**
	 * get 采集时间
	 * @return 
	 */
	public int getCosttime(){
		return costtime;
	}
	
	/**
	 * set 采集时间
	 */
	public void setCosttime(int costtime){
		this.costtime = costtime;
	}
	
	
	@Override
	public int getId() {
		return 140102;
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
		buf.append("personId:" + personId +",");
		//行为目标
		buf.append("tatget:" + tatget +",");
		//采集时间
		buf.append("costtime:" + costtime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}