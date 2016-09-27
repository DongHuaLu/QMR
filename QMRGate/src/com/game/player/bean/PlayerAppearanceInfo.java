package com.game.player.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家外观展示信息
 */
public class PlayerAppearanceInfo extends Bean {

	//玩家性别
	private byte sex;
	
	//衣服模版ID
	private int clothingmodid;
	
	//武器模版ID
	private int weaponmodid;
	
	//坐骑模版ID
	private int horsemodid;
	
	//头像模板ID
	private int avatarid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家性别
		writeByte(buf, this.sex);
		//衣服模版ID
		writeInt(buf, this.clothingmodid);
		//武器模版ID
		writeInt(buf, this.weaponmodid);
		//坐骑模版ID
		writeInt(buf, this.horsemodid);
		//头像模板ID
		writeInt(buf, this.avatarid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家性别
		this.sex = readByte(buf);
		//衣服模版ID
		this.clothingmodid = readInt(buf);
		//武器模版ID
		this.weaponmodid = readInt(buf);
		//坐骑模版ID
		this.horsemodid = readInt(buf);
		//头像模板ID
		this.avatarid = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家性别
	 * @return 
	 */
	public byte getSex(){
		return sex;
	}
	
	/**
	 * set 玩家性别
	 */
	public void setSex(byte sex){
		this.sex = sex;
	}
	
	/**
	 * get 衣服模版ID
	 * @return 
	 */
	public int getClothingmodid(){
		return clothingmodid;
	}
	
	/**
	 * set 衣服模版ID
	 */
	public void setClothingmodid(int clothingmodid){
		this.clothingmodid = clothingmodid;
	}
	
	/**
	 * get 武器模版ID
	 * @return 
	 */
	public int getWeaponmodid(){
		return weaponmodid;
	}
	
	/**
	 * set 武器模版ID
	 */
	public void setWeaponmodid(int weaponmodid){
		this.weaponmodid = weaponmodid;
	}
	
	/**
	 * get 坐骑模版ID
	 * @return 
	 */
	public int getHorsemodid(){
		return horsemodid;
	}
	
	/**
	 * set 坐骑模版ID
	 */
	public void setHorsemodid(int horsemodid){
		this.horsemodid = horsemodid;
	}
	
	/**
	 * get 头像模板ID
	 * @return 
	 */
	public int getAvatarid(){
		return avatarid;
	}
	
	/**
	 * set 头像模板ID
	 */
	public void setAvatarid(int avatarid){
		this.avatarid = avatarid;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家性别
		buf.append("sex:" + sex +",");
		//衣服模版ID
		buf.append("clothingmodid:" + clothingmodid +",");
		//武器模版ID
		buf.append("weaponmodid:" + weaponmodid +",");
		//坐骑模版ID
		buf.append("horsemodid:" + horsemodid +",");
		//头像模板ID
		buf.append("avatarid:" + avatarid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}