package com.game.gem.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 单个宝石信息
 */
public class GemInfo extends Bean {

	//宝石唯一ID
	private long id;
	
	//宝石等级
	private byte level;
	
	//宝石类型
	private byte type;
	
	//当前经验值
	private int exp;
	
	//宝石位置（0-4）
	private byte grid;
	
	//当前宝石是否激活
	private byte isact;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宝石唯一ID
		writeLong(buf, this.id);
		//宝石等级
		writeByte(buf, this.level);
		//宝石类型
		writeByte(buf, this.type);
		//当前经验值
		writeInt(buf, this.exp);
		//宝石位置（0-4）
		writeByte(buf, this.grid);
		//当前宝石是否激活
		writeByte(buf, this.isact);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宝石唯一ID
		this.id = readLong(buf);
		//宝石等级
		this.level = readByte(buf);
		//宝石类型
		this.type = readByte(buf);
		//当前经验值
		this.exp = readInt(buf);
		//宝石位置（0-4）
		this.grid = readByte(buf);
		//当前宝石是否激活
		this.isact = readByte(buf);
		return true;
	}
	
	/**
	 * get 宝石唯一ID
	 * @return 
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * set 宝石唯一ID
	 */
	public void setId(long id){
		this.id = id;
	}
	
	/**
	 * get 宝石等级
	 * @return 
	 */
	public byte getLevel(){
		return level;
	}
	
	/**
	 * set 宝石等级
	 */
	public void setLevel(byte level){
		this.level = level;
	}
	
	/**
	 * get 宝石类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 宝石类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 当前经验值
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 当前经验值
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 宝石位置（0-4）
	 * @return 
	 */
	public byte getGrid(){
		return grid;
	}
	
	/**
	 * set 宝石位置（0-4）
	 */
	public void setGrid(byte grid){
		this.grid = grid;
	}
	
	/**
	 * get 当前宝石是否激活
	 * @return 
	 */
	public byte getIsact(){
		return isact;
	}
	
	/**
	 * set 当前宝石是否激活
	 */
	public void setIsact(byte isact){
		this.isact = isact;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//宝石唯一ID
		buf.append("id:" + id +",");
		//宝石等级
		buf.append("level:" + level +",");
		//宝石类型
		buf.append("type:" + type +",");
		//当前经验值
		buf.append("exp:" + exp +",");
		//宝石位置（0-4）
		buf.append("grid:" + grid +",");
		//当前宝石是否激活
		buf.append("isact:" + isact +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}