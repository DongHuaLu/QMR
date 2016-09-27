package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 更改pk状态消息
 */
public class ResChangePKStateMessage extends Message{

	//角色Id
	private long personId;
	
	//pk状态
	private int pkState;
	
	//是否自动切换
	private int auto;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//pk状态
		writeInt(buf, this.pkState);
		//是否自动切换
		writeInt(buf, this.auto);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//pk状态
		this.pkState = readInt(buf);
		//是否自动切换
		this.auto = readInt(buf);
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
	 * get pk状态
	 * @return 
	 */
	public int getPkState(){
		return pkState;
	}
	
	/**
	 * set pk状态
	 */
	public void setPkState(int pkState){
		this.pkState = pkState;
	}
	
	/**
	 * get 是否自动切换
	 * @return 
	 */
	public int getAuto(){
		return auto;
	}
	
	/**
	 * set 是否自动切换
	 */
	public void setAuto(int auto){
		this.auto = auto;
	}
	
	
	@Override
	public int getId() {
		return 103118;
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
		//pk状态
		buf.append("pkState:" + pkState +",");
		//是否自动切换
		buf.append("auto:" + auto +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}