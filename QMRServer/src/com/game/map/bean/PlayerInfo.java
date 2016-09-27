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
 * 角色信息类
 */
public class PlayerInfo extends Bean {

	//角色Id
	private long personId;
	
	//平台VIP
	private int webvip;
	
	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private byte sex;
	
	//角色等级
	private int level;
	
	//角色所在地图
	private int mapId;
	
	//角色所在X
	private short x;
	
	//角色所在Y
	private short y;
	
	//角色HP
	private int hp;
	
	//角色最大HP
	private int maxHp;
	
	//角色MP
	private int mp;
	
	//角色最大MP
	private int maxMp;
	
	//角色SP
	private int sp;
	
	//角色最大SP
	private int maxSp;
	
	//角色速度
	private int speed;
	
	//角色状态
	private int state;
	
	//武器模板id
	private int weapon;
	
	//武器模板强化等级
	private byte weaponStreng;
	
	//衣服模板id
	private int armor;
	
	//头像模板id
	private int avatar;
	
	//人物面对方向
	private byte dir;
	
	//队伍ID
	private long team;
	
	//帮派ID
	private long guild;
	
	//跑步坐标集合
	private List<Byte> positions = new ArrayList<Byte>();
	//buff集合
	private List<com.game.buff.bean.BuffInfo> buffs = new ArrayList<com.game.buff.bean.BuffInfo>();
	//当前坐骑阶数
	private short horseid;
	
	//当前骑战兵器阶数
	private short horseweaponid;
	
	//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
	private byte dazuoState;
	
	//参与双修的宠物唯一ID
	private List<Long> sxpets = new ArrayList<Long>();
	//双修的玩家ID
	private long sxroleid;
	
	//双修玩家对方的坐标X
	private short sxtargetx;
	
	//双修玩家对方的坐标Y
	private short sxtargety;
	
	//军衔等级
	private byte ranklevel;
	
	//弓箭ID
	private int arrowid;
	
	//境界等级
	private int realmlevel;
	
	//当前暗器阶数
	private short hiddenweaponid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//平台VIP
		writeInt(buf, this.webvip);
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeByte(buf, this.sex);
		//角色等级
		writeInt(buf, this.level);
		//角色所在地图
		writeInt(buf, this.mapId);
		//角色所在X
		writeShort(buf, this.x);
		//角色所在Y
		writeShort(buf, this.y);
		//角色HP
		writeInt(buf, this.hp);
		//角色最大HP
		writeInt(buf, this.maxHp);
		//角色MP
		writeInt(buf, this.mp);
		//角色最大MP
		writeInt(buf, this.maxMp);
		//角色SP
		writeInt(buf, this.sp);
		//角色最大SP
		writeInt(buf, this.maxSp);
		//角色速度
		writeInt(buf, this.speed);
		//角色状态
		writeInt(buf, this.state);
		//武器模板id
		writeInt(buf, this.weapon);
		//武器模板强化等级
		writeByte(buf, this.weaponStreng);
		//衣服模板id
		writeInt(buf, this.armor);
		//头像模板id
		writeInt(buf, this.avatar);
		//人物面对方向
		writeByte(buf, this.dir);
		//队伍ID
		writeLong(buf, this.team);
		//帮派ID
		writeLong(buf, this.guild);
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
		//当前坐骑阶数
		writeShort(buf, this.horseid);
		//当前骑战兵器阶数
		writeShort(buf, this.horseweaponid);
		//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		writeByte(buf, this.dazuoState);
		//参与双修的宠物唯一ID
		writeShort(buf, sxpets.size());
		for (int i = 0; i < sxpets.size(); i++) {
			writeLong(buf, sxpets.get(i));
		}
		//双修的玩家ID
		writeLong(buf, this.sxroleid);
		//双修玩家对方的坐标X
		writeShort(buf, this.sxtargetx);
		//双修玩家对方的坐标Y
		writeShort(buf, this.sxtargety);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//弓箭ID
		writeInt(buf, this.arrowid);
		//境界等级
		writeInt(buf, this.realmlevel);
		//当前暗器阶数
		writeShort(buf, this.hiddenweaponid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//平台VIP
		this.webvip = readInt(buf);
		//角色名字
		this.name = readString(buf);
		//角色性别 1-男 2-女
		this.sex = readByte(buf);
		//角色等级
		this.level = readInt(buf);
		//角色所在地图
		this.mapId = readInt(buf);
		//角色所在X
		this.x = readShort(buf);
		//角色所在Y
		this.y = readShort(buf);
		//角色HP
		this.hp = readInt(buf);
		//角色最大HP
		this.maxHp = readInt(buf);
		//角色MP
		this.mp = readInt(buf);
		//角色最大MP
		this.maxMp = readInt(buf);
		//角色SP
		this.sp = readInt(buf);
		//角色最大SP
		this.maxSp = readInt(buf);
		//角色速度
		this.speed = readInt(buf);
		//角色状态
		this.state = readInt(buf);
		//武器模板id
		this.weapon = readInt(buf);
		//武器模板强化等级
		this.weaponStreng = readByte(buf);
		//衣服模板id
		this.armor = readInt(buf);
		//头像模板id
		this.avatar = readInt(buf);
		//人物面对方向
		this.dir = readByte(buf);
		//队伍ID
		this.team = readLong(buf);
		//帮派ID
		this.guild = readLong(buf);
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
		//当前坐骑阶数
		this.horseid = readShort(buf);
		//当前骑战兵器阶数
		this.horseweaponid = readShort(buf);
		//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		this.dazuoState = readByte(buf);
		//参与双修的宠物唯一ID
		int sxpets_length = readShort(buf);
		for (int i = 0; i < sxpets_length; i++) {
			sxpets.add(readLong(buf));
		}
		//双修的玩家ID
		this.sxroleid = readLong(buf);
		//双修玩家对方的坐标X
		this.sxtargetx = readShort(buf);
		//双修玩家对方的坐标Y
		this.sxtargety = readShort(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//弓箭ID
		this.arrowid = readInt(buf);
		//境界等级
		this.realmlevel = readInt(buf);
		//当前暗器阶数
		this.hiddenweaponid = readShort(buf);
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
	 * get 平台VIP
	 * @return 
	 */
	public int getWebvip(){
		return webvip;
	}
	
	/**
	 * set 平台VIP
	 */
	public void setWebvip(int webvip){
		this.webvip = webvip;
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
	 * get 角色所在地图
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 角色所在地图
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
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
	 * get 角色MP
	 * @return 
	 */
	public int getMp(){
		return mp;
	}
	
	/**
	 * set 角色MP
	 */
	public void setMp(int mp){
		this.mp = mp;
	}
	
	/**
	 * get 角色最大MP
	 * @return 
	 */
	public int getMaxMp(){
		return maxMp;
	}
	
	/**
	 * set 角色最大MP
	 */
	public void setMaxMp(int maxMp){
		this.maxMp = maxMp;
	}
	
	/**
	 * get 角色SP
	 * @return 
	 */
	public int getSp(){
		return sp;
	}
	
	/**
	 * set 角色SP
	 */
	public void setSp(int sp){
		this.sp = sp;
	}
	
	/**
	 * get 角色最大SP
	 * @return 
	 */
	public int getMaxSp(){
		return maxSp;
	}
	
	/**
	 * set 角色最大SP
	 */
	public void setMaxSp(int maxSp){
		this.maxSp = maxSp;
	}
	
	/**
	 * get 角色速度
	 * @return 
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * set 角色速度
	 */
	public void setSpeed(int speed){
		this.speed = speed;
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
	 * get 武器模板强化等级
	 * @return 
	 */
	public byte getWeaponStreng(){
		return weaponStreng;
	}
	
	/**
	 * set 武器模板强化等级
	 */
	public void setWeaponStreng(byte weaponStreng){
		this.weaponStreng = weaponStreng;
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
	 * get 队伍ID
	 * @return 
	 */
	public long getTeam(){
		return team;
	}
	
	/**
	 * set 队伍ID
	 */
	public void setTeam(long team){
		this.team = team;
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
	 * get 当前骑战兵器阶数
	 * @return 
	 */
	public short getHorseweaponid(){
		return horseweaponid;
	}
	
	/**
	 * set 当前骑战兵器阶数
	 */
	public void setHorseweaponid(short horseweaponid){
		this.horseweaponid = horseweaponid;
	}
	
	/**
	 * get 打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
	 * @return 
	 */
	public byte getDazuoState(){
		return dazuoState;
	}
	
	/**
	 * set 打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
	 */
	public void setDazuoState(byte dazuoState){
		this.dazuoState = dazuoState;
	}
	
	/**
	 * get 参与双修的宠物唯一ID
	 * @return 
	 */
	public List<Long> getSxpets(){
		return sxpets;
	}
	
	/**
	 * set 参与双修的宠物唯一ID
	 */
	public void setSxpets(List<Long> sxpets){
		this.sxpets = sxpets;
	}
	
	/**
	 * get 双修的玩家ID
	 * @return 
	 */
	public long getSxroleid(){
		return sxroleid;
	}
	
	/**
	 * set 双修的玩家ID
	 */
	public void setSxroleid(long sxroleid){
		this.sxroleid = sxroleid;
	}
	
	/**
	 * get 双修玩家对方的坐标X
	 * @return 
	 */
	public short getSxtargetx(){
		return sxtargetx;
	}
	
	/**
	 * set 双修玩家对方的坐标X
	 */
	public void setSxtargetx(short sxtargetx){
		this.sxtargetx = sxtargetx;
	}
	
	/**
	 * get 双修玩家对方的坐标Y
	 * @return 
	 */
	public short getSxtargety(){
		return sxtargety;
	}
	
	/**
	 * set 双修玩家对方的坐标Y
	 */
	public void setSxtargety(short sxtargety){
		this.sxtargety = sxtargety;
	}
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public byte getRanklevel(){
		return ranklevel;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setRanklevel(byte ranklevel){
		this.ranklevel = ranklevel;
	}
	
	/**
	 * get 弓箭ID
	 * @return 
	 */
	public int getArrowid(){
		return arrowid;
	}
	
	/**
	 * set 弓箭ID
	 */
	public void setArrowid(int arrowid){
		this.arrowid = arrowid;
	}
	
	/**
	 * get 境界等级
	 * @return 
	 */
	public int getRealmlevel(){
		return realmlevel;
	}
	
	/**
	 * set 境界等级
	 */
	public void setRealmlevel(int realmlevel){
		this.realmlevel = realmlevel;
	}
	
	/**
	 * get 当前暗器阶数
	 * @return 
	 */
	public short getHiddenweaponid(){
		return hiddenweaponid;
	}
	
	/**
	 * set 当前暗器阶数
	 */
	public void setHiddenweaponid(short hiddenweaponid){
		this.hiddenweaponid = hiddenweaponid;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("personId:" + personId +",");
		//平台VIP
		buf.append("webvip:" + webvip +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别 1-男 2-女
		buf.append("sex:" + sex +",");
		//角色等级
		buf.append("level:" + level +",");
		//角色所在地图
		buf.append("mapId:" + mapId +",");
		//角色所在X
		buf.append("x:" + x +",");
		//角色所在Y
		buf.append("y:" + y +",");
		//角色HP
		buf.append("hp:" + hp +",");
		//角色最大HP
		buf.append("maxHp:" + maxHp +",");
		//角色MP
		buf.append("mp:" + mp +",");
		//角色最大MP
		buf.append("maxMp:" + maxMp +",");
		//角色SP
		buf.append("sp:" + sp +",");
		//角色最大SP
		buf.append("maxSp:" + maxSp +",");
		//角色速度
		buf.append("speed:" + speed +",");
		//角色状态
		buf.append("state:" + state +",");
		//武器模板id
		buf.append("weapon:" + weapon +",");
		//武器模板强化等级
		buf.append("weaponStreng:" + weaponStreng +",");
		//衣服模板id
		buf.append("armor:" + armor +",");
		//头像模板id
		buf.append("avatar:" + avatar +",");
		//人物面对方向
		buf.append("dir:" + dir +",");
		//队伍ID
		buf.append("team:" + team +",");
		//帮派ID
		buf.append("guild:" + guild +",");
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
		//当前坐骑阶数
		buf.append("horseid:" + horseid +",");
		//当前骑战兵器阶数
		buf.append("horseweaponid:" + horseweaponid +",");
		//打坐状态 0 未打座 1单人打座 2与待宠双修 3与玩家双修
		buf.append("dazuoState:" + dazuoState +",");
		//参与双修的宠物唯一ID
		buf.append("sxpets:{");
		for (int i = 0; i < sxpets.size(); i++) {
			buf.append(sxpets.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//双修的玩家ID
		buf.append("sxroleid:" + sxroleid +",");
		//双修玩家对方的坐标X
		buf.append("sxtargetx:" + sxtargetx +",");
		//双修玩家对方的坐标Y
		buf.append("sxtargety:" + sxtargety +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//弓箭ID
		buf.append("arrowid:" + arrowid +",");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//当前暗器阶数
		buf.append("hiddenweaponid:" + hiddenweaponid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}