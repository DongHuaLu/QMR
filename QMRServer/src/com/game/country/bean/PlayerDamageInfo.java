package com.game.country.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 角色信息类
 */
public class PlayerDamageInfo extends Bean {

	//角色Id
	private long personId;
	
	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private byte sex;
	
	//角色所在X
	private short x;
	
	//角色所在Y
	private short y;
	
	//角色HP
	private int hp;
	
	//角色最大HP
	private int maxHp;
	
	//角色状态
	private int state;
	
	//武器模板id
	private int weapon;
	
	//衣服模板id
	private int armor;
	
	//头像模板id
	private int avatar;
	
	//人物面对方向
	private byte dir;
	
	//帮派ID
	private long guild;
	
	//当前坐骑阶数
	private short horseid;
	
	//伤害数值
	private int damage;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeByte(buf, this.sex);
		//角色所在X
		writeShort(buf, this.x);
		//角色所在Y
		writeShort(buf, this.y);
		//角色HP
		writeInt(buf, this.hp);
		//角色最大HP
		writeInt(buf, this.maxHp);
		//角色状态
		writeInt(buf, this.state);
		//武器模板id
		writeInt(buf, this.weapon);
		//衣服模板id
		writeInt(buf, this.armor);
		//头像模板id
		writeInt(buf, this.avatar);
		//人物面对方向
		writeByte(buf, this.dir);
		//帮派ID
		writeLong(buf, this.guild);
		//当前坐骑阶数
		writeShort(buf, this.horseid);
		//伤害数值
		writeInt(buf, this.damage);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//角色名字
		this.name = readString(buf);
		//角色性别 1-男 2-女
		this.sex = readByte(buf);
		//角色所在X
		this.x = readShort(buf);
		//角色所在Y
		this.y = readShort(buf);
		//角色HP
		this.hp = readInt(buf);
		//角色最大HP
		this.maxHp = readInt(buf);
		//角色状态
		this.state = readInt(buf);
		//武器模板id
		this.weapon = readInt(buf);
		//衣服模板id
		this.armor = readInt(buf);
		//头像模板id
		this.avatar = readInt(buf);
		//人物面对方向
		this.dir = readByte(buf);
		//帮派ID
		this.guild = readLong(buf);
		//当前坐骑阶数
		this.horseid = readShort(buf);
		//伤害数值
		this.damage = readInt(buf);
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
	 * get 角色所在X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 角色所在X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 角色所在Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 角色所在Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	/**
	 * get 角色HP
	 * @return 
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * set 角色HP
	 */
	public void setHp(int hp){
		this.hp = hp;
	}
	
	/**
	 * get 角色最大HP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 角色最大HP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	/**
	 * get 角色状态
	 * @return 
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * set 角色状态
	 */
	public void setState(int state){
		this.state = state;
	}
	
	/**
	 * get 武器模板id
	 * @return 
	 */
	public int getWeapon(){
		return weapon;
	}
	
	/**
	 * set 武器模板id
	 */
	public void setWeapon(int weapon){
		this.weapon = weapon;
	}
	
	/**
	 * get 衣服模板id
	 * @return 
	 */
	public int getArmor(){
		return armor;
	}
	
	/**
	 * set 衣服模板id
	 */
	public void setArmor(int armor){
		this.armor = armor;
	}
	
	/**
	 * get 头像模板id
	 * @return 
	 */
	public int getAvatar(){
		return avatar;
	}
	
	/**
	 * set 头像模板id
	 */
	public void setAvatar(int avatar){
		this.avatar = avatar;
	}
	
	/**
	 * get 人物面对方向
	 * @return 
	 */
	public byte getDir(){
		return dir;
	}
	
	/**
	 * set 人物面对方向
	 */
	public void setDir(byte dir){
		this.dir = dir;
	}
	
	/**
	 * get 帮派ID
	 * @return 
	 */
	public long getGuild(){
		return guild;
	}
	
	/**
	 * set 帮派ID
	 */
	public void setGuild(long guild){
		this.guild = guild;
	}
	
	/**
	 * get 当前坐骑阶数
	 * @return 
	 */
	public short getHorseid(){
		return horseid;
	}
	
	/**
	 * set 当前坐骑阶数
	 */
	public void setHorseid(short horseid){
		this.horseid = horseid;
	}
	
	/**
	 * get 伤害数值
	 * @return 
	 */
	public int getDamage(){
		return damage;
	}
	
	/**
	 * set 伤害数值
	 */
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("personId:" + personId +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别 1-男 2-女
		buf.append("sex:" + sex +",");
		//角色所在X
		buf.append("x:" + x +",");
		//角色所在Y
		buf.append("y:" + y +",");
		//角色HP
		buf.append("hp:" + hp +",");
		//角色最大HP
		buf.append("maxHp:" + maxHp +",");
		//角色状态
		buf.append("state:" + state +",");
		//武器模板id
		buf.append("weapon:" + weapon +",");
		//衣服模板id
		buf.append("armor:" + armor +",");
		//头像模板id
		buf.append("avatar:" + avatar +",");
		//人物面对方向
		buf.append("dir:" + dir +",");
		//帮派ID
		buf.append("guild:" + guild +",");
		//当前坐骑阶数
		buf.append("horseid:" + horseid +",");
		//伤害数值
		buf.append("damage:" + damage +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}