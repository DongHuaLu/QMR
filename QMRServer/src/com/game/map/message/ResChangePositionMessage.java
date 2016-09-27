package com.game.map.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 角色改变坐标消息
 */
public class ResChangePositionMessage extends Message{

	//角色Id
	private long personId;
	
	//改变坐标
	private com.game.structs.Position position;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//改变坐标
		writeBean(buf, this.position);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//改变坐标
		this.position = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
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
	 * get 改变坐标
	 * @return 
	 */
	public com.game.structs.Position getPosition(){
		return position;
	}
	
	/**
	 * set 改变坐标
	 */
	public void setPosition(com.game.structs.Position position){
		this.position = position;
	}
	
	
	@Override
	public int getId() {
		return 101128;
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
		//改变坐标
		if(this.position!=null) buf.append("position:" + position.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}