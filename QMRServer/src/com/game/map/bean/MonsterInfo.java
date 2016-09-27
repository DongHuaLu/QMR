package com.game.map.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 怪物信息类
 */
public class MonsterInfo extends Bean {

	//怪物Id
	private long monsterId;
	
	//怪物模板Id
	private int monsterModelId;
	
	//怪物名字
	private String monsterName;
	
	//怪物资源造型
	private String monsterRes;
	
	//怪物头像造型
	private String monsterIcon;
	
	//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
	private byte friend;
	
	//怪物敌对参数
	private String friendPara;
	
	//怪物等级
	private int level;
	
	//怪物所在地图
	private int mapId;
	
	//怪物所在X
	private short x;
	
	//怪物所在Y
	private short y;
	
	//怪物HP
	private int hp;
	
	//怪物最大HP
	private int maxHp;
	
	//怪物MP
	private int mp;
	
	//怪物最大MP
	private int maxMp;
	
	//怪物SP
	private int sp;
	
	//怪物最大SP
	private int maxSp;
	
	//怪物速度
	private int speed;
	
	//变身类型
	private int morph;
	
	//怪物面对方向
	private byte dir;
	
	//跑步坐标集合
	private List<Byte> positions = new ArrayList<Byte>();
	//buff集合
	private List<com.game.buff.bean.BuffInfo> buffs = new ArrayList<com.game.buff.bean.BuffInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//怪物Id
		writeLong(buf, this.monsterId);
		//怪物模板Id
		writeInt(buf, this.monsterModelId);
		//怪物名字
		writeString(buf, this.monsterName);
		//怪物资源造型
		writeString(buf, this.monsterRes);
		//怪物头像造型
		writeString(buf, this.monsterIcon);
		//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		writeByte(buf, this.friend);
		//怪物敌对参数
		writeString(buf, this.friendPara);
		//怪物等级
		writeInt(buf, this.level);
		//怪物所在地图
		writeInt(buf, this.mapId);
		//怪物所在X
		writeShort(buf, this.x);
		//怪物所在Y
		writeShort(buf, this.y);
		//怪物HP
		writeInt(buf, this.hp);
		//怪物最大HP
		writeInt(buf, this.maxHp);
		//怪物MP
		writeInt(buf, this.mp);
		//怪物最大MP
		writeInt(buf, this.maxMp);
		//怪物SP
		writeInt(buf, this.sp);
		//怪物最大SP
		writeInt(buf, this.maxSp);
		//怪物速度
		writeInt(buf, this.speed);
		//变身类型
		writeInt(buf, this.morph);
		//怪物面对方向
		writeByte(buf, this.dir);
		//跑步坐标集合
		writeShort(buf, positions.size());
		for (int i = 0; i < positions.size(); i++) {
			writeByte(buf, positions.get(i));
		}
		//buff集合
		writeShort(buf, buffs.size());
		for (int i = 0; i < buffs.size(); i++) {
			writeBean(buf, buffs.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//怪物Id
		this.monsterId = readLong(buf);
		//怪物模板Id
		this.monsterModelId = readInt(buf);
		//怪物名字
		this.monsterName = readString(buf);
		//怪物资源造型
		this.monsterRes = readString(buf);
		//怪物头像造型
		this.monsterIcon = readString(buf);
		//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		this.friend = readByte(buf);
		//怪物敌对参数
		this.friendPara = readString(buf);
		//怪物等级
		this.level = readInt(buf);
		//怪物所在地图
		this.mapId = readInt(buf);
		//怪物所在X
		this.x = readShort(buf);
		//怪物所在Y
		this.y = readShort(buf);
		//怪物HP
		this.hp = readInt(buf);
		//怪物最大HP
		this.maxHp = readInt(buf);
		//怪物MP
		this.mp = readInt(buf);
		//怪物最大MP
		this.maxMp = readInt(buf);
		//怪物SP
		this.sp = readInt(buf);
		//怪物最大SP
		this.maxSp = readInt(buf);
		//怪物速度
		this.speed = readInt(buf);
		//变身类型
		this.morph = readInt(buf);
		//怪物面对方向
		this.dir = readByte(buf);
		//跑步坐标集合
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add(readByte(buf));
		}
		//buff集合
		int buffs_length = readShort(buf);
		for (int i = 0; i < buffs_length; i++) {
			buffs.add((com.game.buff.bean.BuffInfo)readBean(buf, com.game.buff.bean.BuffInfo.class));
		}
		return true;
	}
	
	/**
	 * get 怪物Id
	 * @return 
	 */
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 怪物Id
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
	}
	
	/**
	 * get 怪物模板Id
	 * @return 
	 */
	public int getMonsterModelId(){
		return monsterModelId;
	}
	
	/**
	 * set 怪物模板Id
	 */
	public void setMonsterModelId(int monsterModelId){
		this.monsterModelId = monsterModelId;
	}
	
	/**
	 * get 怪物名字
	 * @return 
	 */
	public String getMonsterName(){
		return monsterName;
	}
	
	/**
	 * set 怪物名字
	 */
	public void setMonsterName(String monsterName){
		this.monsterName = monsterName;
	}
	
	/**
	 * get 怪物资源造型
	 * @return 
	 */
	public String getMonsterRes(){
		return monsterRes;
	}
	
	/**
	 * set 怪物资源造型
	 */
	public void setMonsterRes(String monsterRes){
		this.monsterRes = monsterRes;
	}
	
	/**
	 * get 怪物头像造型
	 * @return 
	 */
	public String getMonsterIcon(){
		return monsterIcon;
	}
	
	/**
	 * set 怪物头像造型
	 */
	public void setMonsterIcon(String monsterIcon){
		this.monsterIcon = monsterIcon;
	}
	
	/**
	 * get 怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
	 * @return 
	 */
	public byte getFriend(){
		return friend;
	}
	
	/**
	 * set 怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
	 */
	public void setFriend(byte friend){
		this.friend = friend;
	}
	
	/**
	 * get 怪物敌对参数
	 * @return 
	 */
	public String getFriendPara(){
		return friendPara;
	}
	
	/**
	 * set 怪物敌对参数
	 */
	public void setFriendPara(String friendPara){
		this.friendPara = friendPara;
	}
	
	/**
	 * get 怪物等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 怪物等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 怪物所在地图
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 怪物所在地图
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 怪物所在X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 怪物所在X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 怪物所在Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 怪物所在Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	/**
	 * get 怪物HP
	 * @return 
	 */
	public int getHp(){
		return hp;
	}
	
	/**
	 * set 怪物HP
	 */
	public void setHp(int hp){
		this.hp = hp;
	}
	
	/**
	 * get 怪物最大HP
	 * @return 
	 */
	public int getMaxHp(){
		return maxHp;
	}
	
	/**
	 * set 怪物最大HP
	 */
	public void setMaxHp(int maxHp){
		this.maxHp = maxHp;
	}
	
	/**
	 * get 怪物MP
	 * @return 
	 */
	public int getMp(){
		return mp;
	}
	
	/**
	 * set 怪物MP
	 */
	public void setMp(int mp){
		this.mp = mp;
	}
	
	/**
	 * get 怪物最大MP
	 * @return 
	 */
	public int getMaxMp(){
		return maxMp;
	}
	
	/**
	 * set 怪物最大MP
	 */
	public void setMaxMp(int maxMp){
		this.maxMp = maxMp;
	}
	
	/**
	 * get 怪物SP
	 * @return 
	 */
	public int getSp(){
		return sp;
	}
	
	/**
	 * set 怪物SP
	 */
	public void setSp(int sp){
		this.sp = sp;
	}
	
	/**
	 * get 怪物最大SP
	 * @return 
	 */
	public int getMaxSp(){
		return maxSp;
	}
	
	/**
	 * set 怪物最大SP
	 */
	public void setMaxSp(int maxSp){
		this.maxSp = maxSp;
	}
	
	/**
	 * get 怪物速度
	 * @return 
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * set 怪物速度
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * get 变身类型
	 * @return 
	 */
	public int getMorph(){
		return morph;
	}
	
	/**
	 * set 变身类型
	 */
	public void setMorph(int morph){
		this.morph = morph;
	}
	
	/**
	 * get 怪物面对方向
	 * @return 
	 */
	public byte getDir(){
		return dir;
	}
	
	/**
	 * set 怪物面对方向
	 */
	public void setDir(byte dir){
		this.dir = dir;
	}
	
	/**
	 * get 跑步坐标集合
	 * @return 
	 */
	public List<Byte> getPositions(){
		return positions;
	}
	
	/**
	 * set 跑步坐标集合
	 */
	public void setPositions(List<Byte> positions){
		this.positions = positions;
	}
	
	/**
	 * get buff集合
	 * @return 
	 */
	public List<com.game.buff.bean.BuffInfo> getBuffs(){
		return buffs;
	}
	
	/**
	 * set buff集合
	 */
	public void setBuffs(List<com.game.buff.bean.BuffInfo> buffs){
		this.buffs = buffs;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//怪物Id
		buf.append("monsterId:" + monsterId +",");
		//怪物模板Id
		buf.append("monsterModelId:" + monsterModelId +",");
		//怪物名字
		if(this.monsterName!=null) buf.append("monsterName:" + monsterName.toString() +",");
		//怪物资源造型
		if(this.monsterRes!=null) buf.append("monsterRes:" + monsterRes.toString() +",");
		//怪物头像造型
		if(this.monsterIcon!=null) buf.append("monsterIcon:" + monsterIcon.toString() +",");
		//怪物敌对类型 0-全体玩家敌对， 1-全体玩家友好， 2-帮派敌对
		buf.append("friend:" + friend +",");
		//怪物敌对参数
		if(this.friendPara!=null) buf.append("friendPara:" + friendPara.toString() +",");
		//怪物等级
		buf.append("level:" + level +",");
		//怪物所在地图
		buf.append("mapId:" + mapId +",");
		//怪物所在X
		buf.append("x:" + x +",");
		//怪物所在Y
		buf.append("y:" + y +",");
		//怪物HP
		buf.append("hp:" + hp +",");
		//怪物最大HP
		buf.append("maxHp:" + maxHp +",");
		//怪物MP
		buf.append("mp:" + mp +",");
		//怪物最大MP
		buf.append("maxMp:" + maxMp +",");
		//怪物SP
		buf.append("sp:" + sp +",");
		//怪物最大SP
		buf.append("maxSp:" + maxSp +",");
		//怪物速度
		buf.append("speed:" + speed +",");
		//变身类型
		buf.append("morph:" + morph +",");
		//怪物面对方向
		buf.append("dir:" + dir +",");
		//跑步坐标集合
		buf.append("positions:{");
		for (int i = 0; i < positions.size(); i++) {
			buf.append(positions.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//buff集合
		buf.append("buffs:{");
		for (int i = 0; i < buffs.size(); i++) {
			buf.append(buffs.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}