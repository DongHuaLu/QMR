package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 武器换装消息
 */
public class ResWeaponChangeMessage extends Message{

	//角色Id
	private long personId;
	
	//武器模板Id
	private int weaponId;
	
	//武器等级
	private byte weaponStreng;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//武器模板Id
		writeInt(buf, this.weaponId);
		//武器等级
		writeByte(buf, this.weaponStreng);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//武器模板Id
		this.weaponId = readInt(buf);
		//武器等级
		this.weaponStreng = readByte(buf);
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
	 * get 武器模板Id
	 * @return 
	 */
	public int getWeaponId(){
		return weaponId;
	}
	
	/**
	 * set 武器模板Id
	 */
	public void setWeaponId(int weaponId){
		this.weaponId = weaponId;
	}
	
	/**
	 * get 武器等级
	 * @return 
	 */
	public byte getWeaponStreng(){
		return weaponStreng;
	}
	
	/**
	 * set 武器等级
	 */
	public void setWeaponStreng(byte weaponStreng){
		this.weaponStreng = weaponStreng;
	}
	
	
	@Override
	public int getId() {
		return 101118;
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
		//武器模板Id
		buf.append("weaponId:" + weaponId +",");
		//武器等级
		buf.append("weaponStreng:" + weaponStreng +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}