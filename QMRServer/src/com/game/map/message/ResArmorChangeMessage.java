package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 装备换装消息
 */
public class ResArmorChangeMessage extends Message{

	//角色Id
	private long personId;
	
	//衣服模板Id
	private int armorId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//衣服模板Id
		writeInt(buf, this.armorId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//衣服模板Id
		this.armorId = readInt(buf);
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
	 * get 衣服模板Id
	 * @return 
	 */
	public int getArmorId(){
		return armorId;
	}
	
	/**
	 * set 衣服模板Id
	 */
	public void setArmorId(int armorId){
		this.armorId = armorId;
	}
	
	
	@Override
	public int getId() {
		return 101119;
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
		//衣服模板Id
		buf.append("armorId:" + armorId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}