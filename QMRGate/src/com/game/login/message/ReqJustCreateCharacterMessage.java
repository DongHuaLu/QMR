package com.game.login.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 创建角色,不进入场景消息
 */
public class ReqJustCreateCharacterMessage extends Message{

	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private byte sex;
	
	//是否自动生成
	private byte auto;
	
	//角色头像
	private String icon;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeByte(buf, this.sex);
		//是否自动生成
		writeByte(buf, this.auto);
		//角色头像
		writeString(buf, this.icon);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色名字
		this.name = readString(buf);
		//角色性别 1-男 2-女
		this.sex = readByte(buf);
		//是否自动生成
		this.auto = readByte(buf);
		//角色头像
		this.icon = readString(buf);
		return true;
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
	 * get 角色性别 1-男 2-女
	 * @return 
	 */
	public byte getSex(){
		return sex;
	}
	
	/**
	 * set 角色性别 1-男 2-女
	 */
	public void setSex(byte sex){
		this.sex = sex;
	}
	
	/**
	 * get 是否自动生成
	 * @return 
	 */
	public byte getAuto(){
		return auto;
	}
	
	/**
	 * set 是否自动生成
	 */
	public void setAuto(byte auto){
		this.auto = auto;
	}
	
	/**
	 * get 角色头像
	 * @return 
	 */
	public String getIcon(){
		return icon;
	}
	
	/**
	 * set 角色头像
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	
	@Override
	public int getId() {
		return 100211;
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
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别 1-男 2-女
		buf.append("sex:" + sex +",");
		//是否自动生成
		buf.append("auto:" + auto +",");
		//角色头像
		if(this.icon!=null) buf.append("icon:" + icon.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}