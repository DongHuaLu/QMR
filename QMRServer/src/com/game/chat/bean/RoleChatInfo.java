package com.game.chat.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家简要信息
 */
public class RoleChatInfo extends Bean {

	//角色ID
	private long id;
	
	//角色名
	private String name;
	
	//等级
	private int level;
	
	//性别
	private byte sex;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.id);
		//角色名
		writeString(buf, this.name);
		//等级
		writeInt(buf, this.level);
		//性别
		writeByte(buf, this.sex);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.id = readLong(buf);
		//角色名
		this.name = readString(buf);
		//等级
		this.level = readInt(buf);
		//性别
		this.sex = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * set 角色ID
	 */
	public void setId(long id){
		this.id = id;
	}
	
	/**
	 * get 角色名
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 角色名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 性别
	 * @return 
	 */
	public byte getSex(){
		return sex;
	}
	
	/**
	 * set 性别
	 */
	public void setSex(byte sex){
		this.sex = sex;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色ID
		buf.append("id:" + id +",");
		//角色名
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//等级
		buf.append("level:" + level +",");
		//性别
		buf.append("sex:" + sex +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}