package com.game.login.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 角色信息类
 */
public class CharacterInfo extends Bean {

	//角色Id
	private long playerId;
	
	//角色名字
	private String name;
	
	//角色等级
	private int level;
	
	//角色性别
	private byte sex;
	
	//最后登录时间
	private int longintime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//角色名字
		writeString(buf, this.name);
		//角色等级
		writeInt(buf, this.level);
		//角色性别
		writeByte(buf, this.sex);
		//最后登录时间
		writeInt(buf, this.longintime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//角色名字
		this.name = readString(buf);
		//角色等级
		this.level = readInt(buf);
		//角色性别
		this.sex = readByte(buf);
		//最后登录时间
		this.longintime = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 角色名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 角色名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 角色等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 角色等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 角色性别
	 * @return 
	 */
	public byte getSex(){
		return sex;
	}
	
	/**
	 * set 角色性别
	 */
	public void setSex(byte sex){
		this.sex = sex;
	}
	
	/**
	 * get 最后登录时间
	 * @return 
	 */
	public int getLongintime(){
		return longintime;
	}
	
	/**
	 * set 最后登录时间
	 */
	public void setLongintime(int longintime){
		this.longintime = longintime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("playerId:" + playerId +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色等级
		buf.append("level:" + level +",");
		//角色性别
		buf.append("sex:" + sex +",");
		//最后登录时间
		buf.append("longintime:" + longintime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}