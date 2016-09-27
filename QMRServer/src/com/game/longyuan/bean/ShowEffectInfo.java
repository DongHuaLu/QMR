package com.game.longyuan.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 自定义特效展示（通用）
 */
public class ShowEffectInfo extends Bean {

	//类型，1地面效果，2人物效果，3怪物效果
	private byte type;
	
	//怪物ID或者玩家ID
	private long id;
	
	//特效ID，和前端定
	private int effectid;
	
	//坐标X，像素
	private short x;
	
	//坐标Y，像素
	private short y;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型，1地面效果，2人物效果，3怪物效果
		writeByte(buf, this.type);
		//怪物ID或者玩家ID
		writeLong(buf, this.id);
		//特效ID，和前端定
		writeInt(buf, this.effectid);
		//坐标X，像素
		writeShort(buf, this.x);
		//坐标Y，像素
		writeShort(buf, this.y);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型，1地面效果，2人物效果，3怪物效果
		this.type = readByte(buf);
		//怪物ID或者玩家ID
		this.id = readLong(buf);
		//特效ID，和前端定
		this.effectid = readInt(buf);
		//坐标X，像素
		this.x = readShort(buf);
		//坐标Y，像素
		this.y = readShort(buf);
		return true;
	}
	
	/**
	 * get 类型，1地面效果，2人物效果，3怪物效果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型，1地面效果，2人物效果，3怪物效果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 怪物ID或者玩家ID
	 * @return 
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * set 怪物ID或者玩家ID
	 */
	public void setId(long id){
		this.id = id;
	}
	
	/**
	 * get 特效ID，和前端定
	 * @return 
	 */
	public int getEffectid(){
		return effectid;
	}
	
	/**
	 * set 特效ID，和前端定
	 */
	public void setEffectid(int effectid){
		this.effectid = effectid;
	}
	
	/**
	 * get 坐标X，像素
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 坐标X，像素
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 坐标Y，像素
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 坐标Y，像素
	 */
	public void setY(short y){
		this.y = y;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//类型，1地面效果，2人物效果，3怪物效果
		buf.append("type:" + type +",");
		//怪物ID或者玩家ID
		buf.append("id:" + id +",");
		//特效ID，和前端定
		buf.append("effectid:" + effectid +",");
		//坐标X，像素
		buf.append("x:" + x +",");
		//坐标Y，像素
		buf.append("y:" + y +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}