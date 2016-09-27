package com.game.player.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 他人信息
 */
public class OtherPlayerInfo extends Bean {

	//角色Id
	private long personId;
	
	//角色名字
	private String name;
	
	//角色性别 1-男 2-女
	private int sex;
	
	//角色等级
	private int level;
	
	//角色经验
	private long exp;
	
	//角色真气
	private int zhenqi;
	
	//角色战场声望
	private int prestige;
	
	//头象ID
	private int avatar;
	
	//当前章节
	private int chapter;
	
	//属性列表
	private List<PlayerAttributeItem> attributes = new ArrayList<PlayerAttributeItem>();
	//装备列表信息
	private List<com.game.equip.bean.EquipInfo> equips = new ArrayList<com.game.equip.bean.EquipInfo>();
	//角色武功境界
	private byte skill;
	
	//角色武功境界层数
	private int skills;
	
	//他人公会信息
	private com.game.guild.bean.OtherGuildInfo otherGuildInfo;
	
	//战斗力
	private int fightpower;
	
	//装备部位全部宝石信息
	private List<com.game.gem.bean.PosGemInfo> posallgeminfo = new ArrayList<com.game.gem.bean.PosGemInfo>();
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//军衔等级
	private byte ranklevel;
	
	//弓箭信息
	private com.game.arrow.bean.ArrowInfo arrowInfo;
	
	//骑战兵器信息
	private com.game.horseweapon.bean.OthersHorseWeaponInfo horseWeaponInfo;
	
	//暗器信息
	private com.game.hiddenweapon.bean.OthersHiddenWeaponInfo hiddenWeaponInfo;
	
	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int realmintensifylevel;
	
	//配偶名字
	private String spousename;
	
	//戒指模版ID
	private int ringmodelid;
	
	//结婚时间
	private int marriedtime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//角色名字
		writeString(buf, this.name);
		//角色性别 1-男 2-女
		writeInt(buf, this.sex);
		//角色等级
		writeInt(buf, this.level);
		//角色经验
		writeLong(buf, this.exp);
		//角色真气
		writeInt(buf, this.zhenqi);
		//角色战场声望
		writeInt(buf, this.prestige);
		//头象ID
		writeInt(buf, this.avatar);
		//当前章节
		writeInt(buf, this.chapter);
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//装备列表信息
		writeShort(buf, equips.size());
		for (int i = 0; i < equips.size(); i++) {
			writeBean(buf, equips.get(i));
		}
		//角色武功境界
		writeByte(buf, this.skill);
		//角色武功境界层数
		writeInt(buf, this.skills);
		//他人公会信息
		writeBean(buf, this.otherGuildInfo);
		//战斗力
		writeInt(buf, this.fightpower);
		//装备部位全部宝石信息
		writeShort(buf, posallgeminfo.size());
		for (int i = 0; i < posallgeminfo.size(); i++) {
			writeBean(buf, posallgeminfo.get(i));
		}
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//弓箭信息
		writeBean(buf, this.arrowInfo);
		//骑战兵器信息
		writeBean(buf, this.horseWeaponInfo);
		//暗器信息
		writeBean(buf, this.hiddenWeaponInfo);
		//境界等级
		writeInt(buf, this.realmlevel);
		//境界强化等级
		writeInt(buf, this.realmintensifylevel);
		//配偶名字
		writeString(buf, this.spousename);
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		//结婚时间
		writeInt(buf, this.marriedtime);
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
		this.sex = readInt(buf);
		//角色等级
		this.level = readInt(buf);
		//角色经验
		this.exp = readLong(buf);
		//角色真气
		this.zhenqi = readInt(buf);
		//角色战场声望
		this.prestige = readInt(buf);
		//头象ID
		this.avatar = readInt(buf);
		//当前章节
		this.chapter = readInt(buf);
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((PlayerAttributeItem)readBean(buf, PlayerAttributeItem.class));
		}
		//装备列表信息
		int equips_length = readShort(buf);
		for (int i = 0; i < equips_length; i++) {
			equips.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//角色武功境界
		this.skill = readByte(buf);
		//角色武功境界层数
		this.skills = readInt(buf);
		//他人公会信息
		this.otherGuildInfo = (com.game.guild.bean.OtherGuildInfo)readBean(buf, com.game.guild.bean.OtherGuildInfo.class);
		//战斗力
		this.fightpower = readInt(buf);
		//装备部位全部宝石信息
		int posallgeminfo_length = readShort(buf);
		for (int i = 0; i < posallgeminfo_length; i++) {
			posallgeminfo.add((com.game.gem.bean.PosGemInfo)readBean(buf, com.game.gem.bean.PosGemInfo.class));
		}
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//弓箭信息
		this.arrowInfo = (com.game.arrow.bean.ArrowInfo)readBean(buf, com.game.arrow.bean.ArrowInfo.class);
		//骑战兵器信息
		this.horseWeaponInfo = (com.game.horseweapon.bean.OthersHorseWeaponInfo)readBean(buf, com.game.horseweapon.bean.OthersHorseWeaponInfo.class);
		//暗器信息
		this.hiddenWeaponInfo = (com.game.hiddenweapon.bean.OthersHiddenWeaponInfo)readBean(buf, com.game.hiddenweapon.bean.OthersHiddenWeaponInfo.class);
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.realmintensifylevel = readInt(buf);
		//配偶名字
		this.spousename = readString(buf);
		//戒指模版ID
		this.ringmodelid = readInt(buf);
		//结婚时间
		this.marriedtime = readInt(buf);
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
	public int getSex(){
		return sex;
	}
	
	/**
	 * set 角色性别 1-男 2-女
	 */
	public void setSex(int sex){
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
	 * get 角色经验
	 * @return 
	 */
	public long getExp(){
		return exp;
	}
	
	/**
	 * set 角色经验
	 */
	public void setExp(long exp){
		this.exp = exp;
	}
	
	/**
	 * get 角色真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 角色真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * get 角色战场声望
	 * @return 
	 */
	public int getPrestige(){
		return prestige;
	}
	
	/**
	 * set 角色战场声望
	 */
	public void setPrestige(int prestige){
		this.prestige = prestige;
	}
	
	/**
	 * get 头象ID
	 * @return 
	 */
	public int getAvatar(){
		return avatar;
	}
	
	/**
	 * set 头象ID
	 */
	public void setAvatar(int avatar){
		this.avatar = avatar;
	}
	
	/**
	 * get 当前章节
	 * @return 
	 */
	public int getChapter(){
		return chapter;
	}
	
	/**
	 * set 当前章节
	 */
	public void setChapter(int chapter){
		this.chapter = chapter;
	}
	
	/**
	 * get 属性列表
	 * @return 
	 */
	public List<PlayerAttributeItem> getAttributes(){
		return attributes;
	}
	
	/**
	 * set 属性列表
	 */
	public void setAttributes(List<PlayerAttributeItem> attributes){
		this.attributes = attributes;
	}
	
	/**
	 * get 装备列表信息
	 * @return 
	 */
	public List<com.game.equip.bean.EquipInfo> getEquips(){
		return equips;
	}
	
	/**
	 * set 装备列表信息
	 */
	public void setEquips(List<com.game.equip.bean.EquipInfo> equips){
		this.equips = equips;
	}
	
	/**
	 * get 角色武功境界
	 * @return 
	 */
	public byte getSkill(){
		return skill;
	}
	
	/**
	 * set 角色武功境界
	 */
	public void setSkill(byte skill){
		this.skill = skill;
	}
	
	/**
	 * get 角色武功境界层数
	 * @return 
	 */
	public int getSkills(){
		return skills;
	}
	
	/**
	 * set 角色武功境界层数
	 */
	public void setSkills(int skills){
		this.skills = skills;
	}
	
	/**
	 * get 他人公会信息
	 * @return 
	 */
	public com.game.guild.bean.OtherGuildInfo getOtherGuildInfo(){
		return otherGuildInfo;
	}
	
	/**
	 * set 他人公会信息
	 */
	public void setOtherGuildInfo(com.game.guild.bean.OtherGuildInfo otherGuildInfo){
		this.otherGuildInfo = otherGuildInfo;
	}
	
	/**
	 * get 战斗力
	 * @return 
	 */
	public int getFightpower(){
		return fightpower;
	}
	
	/**
	 * set 战斗力
	 */
	public void setFightpower(int fightpower){
		this.fightpower = fightpower;
	}
	
	/**
	 * get 装备部位全部宝石信息
	 * @return 
	 */
	public List<com.game.gem.bean.PosGemInfo> getPosallgeminfo(){
		return posallgeminfo;
	}
	
	/**
	 * set 装备部位全部宝石信息
	 */
	public void setPosallgeminfo(List<com.game.gem.bean.PosGemInfo> posallgeminfo){
		this.posallgeminfo = posallgeminfo;
	}
	
	/**
	 * get 王城BUFFid
	 * @return 
	 */
	public int getKingcitybuffid(){
		return kingcitybuffid;
	}
	
	/**
	 * set 王城BUFFid
	 */
	public void setKingcitybuffid(int kingcitybuffid){
		this.kingcitybuffid = kingcitybuffid;
	}
	
	/**
	 * get VIPid
	 * @return 
	 */
	public int getVipid(){
		return vipid;
	}
	
	/**
	 * set VIPid
	 */
	public void setVipid(int vipid){
		this.vipid = vipid;
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
	 * get 弓箭信息
	 * @return 
	 */
	public com.game.arrow.bean.ArrowInfo getArrowInfo(){
		return arrowInfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowInfo(com.game.arrow.bean.ArrowInfo arrowInfo){
		this.arrowInfo = arrowInfo;
	}
	
	/**
	 * get 骑战兵器信息
	 * @return 
	 */
	public com.game.horseweapon.bean.OthersHorseWeaponInfo getHorseWeaponInfo(){
		return horseWeaponInfo;
	}
	
	/**
	 * set 骑战兵器信息
	 */
	public void setHorseWeaponInfo(com.game.horseweapon.bean.OthersHorseWeaponInfo horseWeaponInfo){
		this.horseWeaponInfo = horseWeaponInfo;
	}
	
	/**
	 * get 暗器信息
	 * @return 
	 */
	public com.game.hiddenweapon.bean.OthersHiddenWeaponInfo getHiddenWeaponInfo(){
		return hiddenWeaponInfo;
	}
	
	/**
	 * set 暗器信息
	 */
	public void setHiddenWeaponInfo(com.game.hiddenweapon.bean.OthersHiddenWeaponInfo hiddenWeaponInfo){
		this.hiddenWeaponInfo = hiddenWeaponInfo;
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
	 * get 境界强化等级
	 * @return 
	 */
	public int getRealmintensifylevel(){
		return realmintensifylevel;
	}
	
	/**
	 * set 境界强化等级
	 */
	public void setRealmintensifylevel(int realmintensifylevel){
		this.realmintensifylevel = realmintensifylevel;
	}
	
	/**
	 * get 配偶名字
	 * @return 
	 */
	public String getSpousename(){
		return spousename;
	}
	
	/**
	 * set 配偶名字
	 */
	public void setSpousename(String spousename){
		this.spousename = spousename;
	}
	
	/**
	 * get 戒指模版ID
	 * @return 
	 */
	public int getRingmodelid(){
		return ringmodelid;
	}
	
	/**
	 * set 戒指模版ID
	 */
	public void setRingmodelid(int ringmodelid){
		this.ringmodelid = ringmodelid;
	}
	
	/**
	 * get 结婚时间
	 * @return 
	 */
	public int getMarriedtime(){
		return marriedtime;
	}
	
	/**
	 * set 结婚时间
	 */
	public void setMarriedtime(int marriedtime){
		this.marriedtime = marriedtime;
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
		//角色等级
		buf.append("level:" + level +",");
		//角色经验
		buf.append("exp:" + exp +",");
		//角色真气
		buf.append("zhenqi:" + zhenqi +",");
		//角色战场声望
		buf.append("prestige:" + prestige +",");
		//头象ID
		buf.append("avatar:" + avatar +",");
		//当前章节
		buf.append("chapter:" + chapter +",");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//装备列表信息
		buf.append("equips:{");
		for (int i = 0; i < equips.size(); i++) {
			buf.append(equips.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//角色武功境界
		buf.append("skill:" + skill +",");
		//角色武功境界层数
		buf.append("skills:" + skills +",");
		//他人公会信息
		if(this.otherGuildInfo!=null) buf.append("otherGuildInfo:" + otherGuildInfo.toString() +",");
		//战斗力
		buf.append("fightpower:" + fightpower +",");
		//装备部位全部宝石信息
		buf.append("posallgeminfo:{");
		for (int i = 0; i < posallgeminfo.size(); i++) {
			buf.append(posallgeminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//弓箭信息
		if(this.arrowInfo!=null) buf.append("arrowInfo:" + arrowInfo.toString() +",");
		//骑战兵器信息
		if(this.horseWeaponInfo!=null) buf.append("horseWeaponInfo:" + horseWeaponInfo.toString() +",");
		//暗器信息
		if(this.hiddenWeaponInfo!=null) buf.append("hiddenWeaponInfo:" + hiddenWeaponInfo.toString() +",");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("realmintensifylevel:" + realmintensifylevel +",");
		//配偶名字
		if(this.spousename!=null) buf.append("spousename:" + spousename.toString() +",");
		//戒指模版ID
		buf.append("ringmodelid:" + ringmodelid +",");
		//结婚时间
		buf.append("marriedtime:" + marriedtime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}