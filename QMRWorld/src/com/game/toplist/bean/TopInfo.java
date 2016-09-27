package com.game.toplist.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 排行信息
 */
public class TopInfo extends Bean {

	//玩家id
	private long playerid;
	
	//平台VIP
	private int webvip;
	
	//玩家名
	private String playername;
	
	//地图模板id
	private int mapModelId;
	
	//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	private byte toptype;
	
	//自己是否膜拜
	private byte worship;
	
	//自己今天膜拜次数
	private byte worshipnum;
	
	//总膜拜次数
	private int allworshipnum;
	
	//名次
	private int topidx;
	
	//等级
	private int level;
	
	//性别
	private byte sex;
	
	//国家
	private int country;
	
	//角色经验
	private long exp;
	
	//角色真气
	private int zhenqi;
	
	//头象ID
	private int avatar;
	
	//当前章节
	private int chapter;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//战场声望
	private int prestige;
	
	//游戏币
	private int money;
	
	//技能总层数
	private int totalSkillLevel;
	
	//墨子主动技能总层数
	private int moziSkillLevel;
	
	//墨子被动技能总层数
	private int mozibackSkillLevel;
	
	//龙元主动技能总层数
	private int longyuanSkillLevel;
	
	//龙元被动技能总层数
	private int longyuanbackSkillLevel;
	
	//龙元阶数
	private int lysection;
	
	//龙元级数
	private int lylevel;
	
	//战斗力
	private int fightPower;
	
	//属性战斗力
	private int attrfightPower;
	
	//装备战斗力
	private int equipfightPower;
	
	//技能战斗力
	private int skillfightPower;
	
	//侍宠模板id
	private int petmodelId;
	
	//侍宠等级
	private int petlevel;
	
	//合体次数
	private int pethtcount;
	
	//合体增加生命
	private int pethtaddhp;
	
	//合体增加内力
	private int pethtaddmp;
	
	//合体增加攻击
	private int pethtaddattack;
	
	//合体增加防御
	private int pethtadddefence;
	
	//合体增加暴击
	private int pethtaddcrit;
	
	//合体增加闪避
	private int pethtadddodge;
	
	//侍宠技能 天赋技能排在第一个 不能动
	private List<com.game.skill.bean.SkillInfo> petskillinfolist = new ArrayList<com.game.skill.bean.SkillInfo>();
	//历史最大连击数
	private int maxEventcut;
	
	//历史最大连击数时间
	private int maxEventcutTime;
	
	//连斩发生地图ID
	private int evencutmapid;
	
	//最后连斩的怪物ID
	private int evencutmonid;
	
	//连斩发生坐标X
	private int evencutmapx;
	
	//连斩发生坐标Y
	private int evencutmapy;
	
	//弓箭信息
	private com.game.arrow.bean.ArrowInfo arrowInfo;
	
	//坐骑最高阶层
	private int horselayer;
	
	//坐骑最高阶的当前等级
	private int horselevel;
	
	//坐骑技能列表
	private List<com.game.horse.bean.HorseSkillInfo> skillinfolist = new ArrayList<com.game.horse.bean.HorseSkillInfo>();
	//坐骑装备列表
	private List<com.game.equip.bean.EquipInfo> horseequipinfo = new ArrayList<com.game.equip.bean.EquipInfo>();
	//属性列表
	private List<com.game.player.bean.PlayerAttributeItem> attributes = new ArrayList<com.game.player.bean.PlayerAttributeItem>();
	//帮会信息
	private com.game.guild.bean.GuildInfo guildinfo;
	
	//身上装备
	private List<com.game.equip.bean.EquipInfo> itemlist = new ArrayList<com.game.equip.bean.EquipInfo>();
	//装备部位全部宝石信息
	private List<com.game.gem.bean.PosGemInfo> posallgeminfo = new ArrayList<com.game.gem.bean.PosGemInfo>();
	//骑战兵器技能信息
	private List<com.game.horseweapon.bean.HorseWeaponSkillInfo> horseWeaponSkillInfo = new ArrayList<com.game.horseweapon.bean.HorseWeaponSkillInfo>();
	//骑战兵器最高阶层
	private int horseweaponlayer;
	
	//骑战兵器最高阶的当前等级
	private int horseweaponlevel;
	
	//暗器技能信息
	private List<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo> hiddenWeaponSkillInfo = new ArrayList<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo>();
	//暗器最高阶层
	private int hiddenweaponlayer;
	
	//暗器最高阶的当前等级
	private int hiddenweaponlevel;
	
	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int realmintensifylevel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//平台VIP
		writeInt(buf, this.webvip);
		//玩家名
		writeString(buf, this.playername);
		//地图模板id
		writeInt(buf, this.mapModelId);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		writeByte(buf, this.toptype);
		//自己是否膜拜
		writeByte(buf, this.worship);
		//自己今天膜拜次数
		writeByte(buf, this.worshipnum);
		//总膜拜次数
		writeInt(buf, this.allworshipnum);
		//名次
		writeInt(buf, this.topidx);
		//等级
		writeInt(buf, this.level);
		//性别
		writeByte(buf, this.sex);
		//国家
		writeInt(buf, this.country);
		//角色经验
		writeLong(buf, this.exp);
		//角色真气
		writeInt(buf, this.zhenqi);
		//头象ID
		writeInt(buf, this.avatar);
		//当前章节
		writeInt(buf, this.chapter);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//战场声望
		writeInt(buf, this.prestige);
		//游戏币
		writeInt(buf, this.money);
		//技能总层数
		writeInt(buf, this.totalSkillLevel);
		//墨子主动技能总层数
		writeInt(buf, this.moziSkillLevel);
		//墨子被动技能总层数
		writeInt(buf, this.mozibackSkillLevel);
		//龙元主动技能总层数
		writeInt(buf, this.longyuanSkillLevel);
		//龙元被动技能总层数
		writeInt(buf, this.longyuanbackSkillLevel);
		//龙元阶数
		writeInt(buf, this.lysection);
		//龙元级数
		writeInt(buf, this.lylevel);
		//战斗力
		writeInt(buf, this.fightPower);
		//属性战斗力
		writeInt(buf, this.attrfightPower);
		//装备战斗力
		writeInt(buf, this.equipfightPower);
		//技能战斗力
		writeInt(buf, this.skillfightPower);
		//侍宠模板id
		writeInt(buf, this.petmodelId);
		//侍宠等级
		writeInt(buf, this.petlevel);
		//合体次数
		writeInt(buf, this.pethtcount);
		//合体增加生命
		writeInt(buf, this.pethtaddhp);
		//合体增加内力
		writeInt(buf, this.pethtaddmp);
		//合体增加攻击
		writeInt(buf, this.pethtaddattack);
		//合体增加防御
		writeInt(buf, this.pethtadddefence);
		//合体增加暴击
		writeInt(buf, this.pethtaddcrit);
		//合体增加闪避
		writeInt(buf, this.pethtadddodge);
		//侍宠技能 天赋技能排在第一个 不能动
		writeShort(buf, petskillinfolist.size());
		for (int i = 0; i < petskillinfolist.size(); i++) {
			writeBean(buf, petskillinfolist.get(i));
		}
		//历史最大连击数
		writeInt(buf, this.maxEventcut);
		//历史最大连击数时间
		writeInt(buf, this.maxEventcutTime);
		//连斩发生地图ID
		writeInt(buf, this.evencutmapid);
		//最后连斩的怪物ID
		writeInt(buf, this.evencutmonid);
		//连斩发生坐标X
		writeInt(buf, this.evencutmapx);
		//连斩发生坐标Y
		writeInt(buf, this.evencutmapy);
		//弓箭信息
		writeBean(buf, this.arrowInfo);
		//坐骑最高阶层
		writeInt(buf, this.horselayer);
		//坐骑最高阶的当前等级
		writeInt(buf, this.horselevel);
		//坐骑技能列表
		writeShort(buf, skillinfolist.size());
		for (int i = 0; i < skillinfolist.size(); i++) {
			writeBean(buf, skillinfolist.get(i));
		}
		//坐骑装备列表
		writeShort(buf, horseequipinfo.size());
		for (int i = 0; i < horseequipinfo.size(); i++) {
			writeBean(buf, horseequipinfo.get(i));
		}
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//帮会信息
		writeBean(buf, this.guildinfo);
		//身上装备
		writeShort(buf, itemlist.size());
		for (int i = 0; i < itemlist.size(); i++) {
			writeBean(buf, itemlist.get(i));
		}
		//装备部位全部宝石信息
		writeShort(buf, posallgeminfo.size());
		for (int i = 0; i < posallgeminfo.size(); i++) {
			writeBean(buf, posallgeminfo.get(i));
		}
		//骑战兵器技能信息
		writeShort(buf, horseWeaponSkillInfo.size());
		for (int i = 0; i < horseWeaponSkillInfo.size(); i++) {
			writeBean(buf, horseWeaponSkillInfo.get(i));
		}
		//骑战兵器最高阶层
		writeInt(buf, this.horseweaponlayer);
		//骑战兵器最高阶的当前等级
		writeInt(buf, this.horseweaponlevel);
		//暗器技能信息
		writeShort(buf, hiddenWeaponSkillInfo.size());
		for (int i = 0; i < hiddenWeaponSkillInfo.size(); i++) {
			writeBean(buf, hiddenWeaponSkillInfo.get(i));
		}
		//暗器最高阶层
		writeInt(buf, this.hiddenweaponlayer);
		//暗器最高阶的当前等级
		writeInt(buf, this.hiddenweaponlevel);
		//境界等级
		writeInt(buf, this.realmlevel);
		//境界强化等级
		writeInt(buf, this.realmintensifylevel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//平台VIP
		this.webvip = readInt(buf);
		//玩家名
		this.playername = readString(buf);
		//地图模板id
		this.mapModelId = readInt(buf);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		this.toptype = readByte(buf);
		//自己是否膜拜
		this.worship = readByte(buf);
		//自己今天膜拜次数
		this.worshipnum = readByte(buf);
		//总膜拜次数
		this.allworshipnum = readInt(buf);
		//名次
		this.topidx = readInt(buf);
		//等级
		this.level = readInt(buf);
		//性别
		this.sex = readByte(buf);
		//国家
		this.country = readInt(buf);
		//角色经验
		this.exp = readLong(buf);
		//角色真气
		this.zhenqi = readInt(buf);
		//头象ID
		this.avatar = readInt(buf);
		//当前章节
		this.chapter = readInt(buf);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//战场声望
		this.prestige = readInt(buf);
		//游戏币
		this.money = readInt(buf);
		//技能总层数
		this.totalSkillLevel = readInt(buf);
		//墨子主动技能总层数
		this.moziSkillLevel = readInt(buf);
		//墨子被动技能总层数
		this.mozibackSkillLevel = readInt(buf);
		//龙元主动技能总层数
		this.longyuanSkillLevel = readInt(buf);
		//龙元被动技能总层数
		this.longyuanbackSkillLevel = readInt(buf);
		//龙元阶数
		this.lysection = readInt(buf);
		//龙元级数
		this.lylevel = readInt(buf);
		//战斗力
		this.fightPower = readInt(buf);
		//属性战斗力
		this.attrfightPower = readInt(buf);
		//装备战斗力
		this.equipfightPower = readInt(buf);
		//技能战斗力
		this.skillfightPower = readInt(buf);
		//侍宠模板id
		this.petmodelId = readInt(buf);
		//侍宠等级
		this.petlevel = readInt(buf);
		//合体次数
		this.pethtcount = readInt(buf);
		//合体增加生命
		this.pethtaddhp = readInt(buf);
		//合体增加内力
		this.pethtaddmp = readInt(buf);
		//合体增加攻击
		this.pethtaddattack = readInt(buf);
		//合体增加防御
		this.pethtadddefence = readInt(buf);
		//合体增加暴击
		this.pethtaddcrit = readInt(buf);
		//合体增加闪避
		this.pethtadddodge = readInt(buf);
		//侍宠技能 天赋技能排在第一个 不能动
		int petskillinfolist_length = readShort(buf);
		for (int i = 0; i < petskillinfolist_length; i++) {
			petskillinfolist.add((com.game.skill.bean.SkillInfo)readBean(buf, com.game.skill.bean.SkillInfo.class));
		}
		//历史最大连击数
		this.maxEventcut = readInt(buf);
		//历史最大连击数时间
		this.maxEventcutTime = readInt(buf);
		//连斩发生地图ID
		this.evencutmapid = readInt(buf);
		//最后连斩的怪物ID
		this.evencutmonid = readInt(buf);
		//连斩发生坐标X
		this.evencutmapx = readInt(buf);
		//连斩发生坐标Y
		this.evencutmapy = readInt(buf);
		//弓箭信息
		this.arrowInfo = (com.game.arrow.bean.ArrowInfo)readBean(buf, com.game.arrow.bean.ArrowInfo.class);
		//坐骑最高阶层
		this.horselayer = readInt(buf);
		//坐骑最高阶的当前等级
		this.horselevel = readInt(buf);
		//坐骑技能列表
		int skillinfolist_length = readShort(buf);
		for (int i = 0; i < skillinfolist_length; i++) {
			skillinfolist.add((com.game.horse.bean.HorseSkillInfo)readBean(buf, com.game.horse.bean.HorseSkillInfo.class));
		}
		//坐骑装备列表
		int horseequipinfo_length = readShort(buf);
		for (int i = 0; i < horseequipinfo_length; i++) {
			horseequipinfo.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((com.game.player.bean.PlayerAttributeItem)readBean(buf, com.game.player.bean.PlayerAttributeItem.class));
		}
		//帮会信息
		this.guildinfo = (com.game.guild.bean.GuildInfo)readBean(buf, com.game.guild.bean.GuildInfo.class);
		//身上装备
		int itemlist_length = readShort(buf);
		for (int i = 0; i < itemlist_length; i++) {
			itemlist.add((com.game.equip.bean.EquipInfo)readBean(buf, com.game.equip.bean.EquipInfo.class));
		}
		//装备部位全部宝石信息
		int posallgeminfo_length = readShort(buf);
		for (int i = 0; i < posallgeminfo_length; i++) {
			posallgeminfo.add((com.game.gem.bean.PosGemInfo)readBean(buf, com.game.gem.bean.PosGemInfo.class));
		}
		//骑战兵器技能信息
		int horseWeaponSkillInfo_length = readShort(buf);
		for (int i = 0; i < horseWeaponSkillInfo_length; i++) {
			horseWeaponSkillInfo.add((com.game.horseweapon.bean.HorseWeaponSkillInfo)readBean(buf, com.game.horseweapon.bean.HorseWeaponSkillInfo.class));
		}
		//骑战兵器最高阶层
		this.horseweaponlayer = readInt(buf);
		//骑战兵器最高阶的当前等级
		this.horseweaponlevel = readInt(buf);
		//暗器技能信息
		int hiddenWeaponSkillInfo_length = readShort(buf);
		for (int i = 0; i < hiddenWeaponSkillInfo_length; i++) {
			hiddenWeaponSkillInfo.add((com.game.hiddenweapon.bean.HiddenWeaponSkillInfo)readBean(buf, com.game.hiddenweapon.bean.HiddenWeaponSkillInfo.class));
		}
		//暗器最高阶层
		this.hiddenweaponlayer = readInt(buf);
		//暗器最高阶的当前等级
		this.hiddenweaponlevel = readInt(buf);
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.realmintensifylevel = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
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
	 * get 玩家名
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 地图模板id
	 * @return 
	 */
	public int getMapModelId(){
		return mapModelId;
	}
	
	/**
	 * set 地图模板id
	 */
	public void setMapModelId(int mapModelId){
		this.mapModelId = mapModelId;
	}
	
	/**
	 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	 * @return 
	 */
	public byte getToptype(){
		return toptype;
	}
	
	/**
	 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	 */
	public void setToptype(byte toptype){
		this.toptype = toptype;
	}
	
	/**
	 * get 自己是否膜拜
	 * @return 
	 */
	public byte getWorship(){
		return worship;
	}
	
	/**
	 * set 自己是否膜拜
	 */
	public void setWorship(byte worship){
		this.worship = worship;
	}
	
	/**
	 * get 自己今天膜拜次数
	 * @return 
	 */
	public byte getWorshipnum(){
		return worshipnum;
	}
	
	/**
	 * set 自己今天膜拜次数
	 */
	public void setWorshipnum(byte worshipnum){
		this.worshipnum = worshipnum;
	}
	
	/**
	 * get 总膜拜次数
	 * @return 
	 */
	public int getAllworshipnum(){
		return allworshipnum;
	}
	
	/**
	 * set 总膜拜次数
	 */
	public void setAllworshipnum(int allworshipnum){
		this.allworshipnum = allworshipnum;
	}
	
	/**
	 * get 名次
	 * @return 
	 */
	public int getTopidx(){
		return topidx;
	}
	
	/**
	 * set 名次
	 */
	public void setTopidx(int topidx){
		this.topidx = topidx;
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
	
	/**
	 * get 国家
	 * @return 
	 */
	public int getCountry(){
		return country;
	}
	
	/**
	 * set 国家
	 */
	public void setCountry(int country){
		this.country = country;
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
	 * get 战场声望
	 * @return 
	 */
	public int getPrestige(){
		return prestige;
	}
	
	/**
	 * set 战场声望
	 */
	public void setPrestige(int prestige){
		this.prestige = prestige;
	}
	
	/**
	 * get 游戏币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 游戏币
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	/**
	 * get 技能总层数
	 * @return 
	 */
	public int getTotalSkillLevel(){
		return totalSkillLevel;
	}
	
	/**
	 * set 技能总层数
	 */
	public void setTotalSkillLevel(int totalSkillLevel){
		this.totalSkillLevel = totalSkillLevel;
	}
	
	/**
	 * get 墨子主动技能总层数
	 * @return 
	 */
	public int getMoziSkillLevel(){
		return moziSkillLevel;
	}
	
	/**
	 * set 墨子主动技能总层数
	 */
	public void setMoziSkillLevel(int moziSkillLevel){
		this.moziSkillLevel = moziSkillLevel;
	}
	
	/**
	 * get 墨子被动技能总层数
	 * @return 
	 */
	public int getMozibackSkillLevel(){
		return mozibackSkillLevel;
	}
	
	/**
	 * set 墨子被动技能总层数
	 */
	public void setMozibackSkillLevel(int mozibackSkillLevel){
		this.mozibackSkillLevel = mozibackSkillLevel;
	}
	
	/**
	 * get 龙元主动技能总层数
	 * @return 
	 */
	public int getLongyuanSkillLevel(){
		return longyuanSkillLevel;
	}
	
	/**
	 * set 龙元主动技能总层数
	 */
	public void setLongyuanSkillLevel(int longyuanSkillLevel){
		this.longyuanSkillLevel = longyuanSkillLevel;
	}
	
	/**
	 * get 龙元被动技能总层数
	 * @return 
	 */
	public int getLongyuanbackSkillLevel(){
		return longyuanbackSkillLevel;
	}
	
	/**
	 * set 龙元被动技能总层数
	 */
	public void setLongyuanbackSkillLevel(int longyuanbackSkillLevel){
		this.longyuanbackSkillLevel = longyuanbackSkillLevel;
	}
	
	/**
	 * get 龙元阶数
	 * @return 
	 */
	public int getLysection(){
		return lysection;
	}
	
	/**
	 * set 龙元阶数
	 */
	public void setLysection(int lysection){
		this.lysection = lysection;
	}
	
	/**
	 * get 龙元级数
	 * @return 
	 */
	public int getLylevel(){
		return lylevel;
	}
	
	/**
	 * set 龙元级数
	 */
	public void setLylevel(int lylevel){
		this.lylevel = lylevel;
	}
	
	/**
	 * get 战斗力
	 * @return 
	 */
	public int getFightPower(){
		return fightPower;
	}
	
	/**
	 * set 战斗力
	 */
	public void setFightPower(int fightPower){
		this.fightPower = fightPower;
	}
	
	/**
	 * get 属性战斗力
	 * @return 
	 */
	public int getAttrfightPower(){
		return attrfightPower;
	}
	
	/**
	 * set 属性战斗力
	 */
	public void setAttrfightPower(int attrfightPower){
		this.attrfightPower = attrfightPower;
	}
	
	/**
	 * get 装备战斗力
	 * @return 
	 */
	public int getEquipfightPower(){
		return equipfightPower;
	}
	
	/**
	 * set 装备战斗力
	 */
	public void setEquipfightPower(int equipfightPower){
		this.equipfightPower = equipfightPower;
	}
	
	/**
	 * get 技能战斗力
	 * @return 
	 */
	public int getSkillfightPower(){
		return skillfightPower;
	}
	
	/**
	 * set 技能战斗力
	 */
	public void setSkillfightPower(int skillfightPower){
		this.skillfightPower = skillfightPower;
	}
	
	/**
	 * get 侍宠模板id
	 * @return 
	 */
	public int getPetmodelId(){
		return petmodelId;
	}
	
	/**
	 * set 侍宠模板id
	 */
	public void setPetmodelId(int petmodelId){
		this.petmodelId = petmodelId;
	}
	
	/**
	 * get 侍宠等级
	 * @return 
	 */
	public int getPetlevel(){
		return petlevel;
	}
	
	/**
	 * set 侍宠等级
	 */
	public void setPetlevel(int petlevel){
		this.petlevel = petlevel;
	}
	
	/**
	 * get 合体次数
	 * @return 
	 */
	public int getPethtcount(){
		return pethtcount;
	}
	
	/**
	 * set 合体次数
	 */
	public void setPethtcount(int pethtcount){
		this.pethtcount = pethtcount;
	}
	
	/**
	 * get 合体增加生命
	 * @return 
	 */
	public int getPethtaddhp(){
		return pethtaddhp;
	}
	
	/**
	 * set 合体增加生命
	 */
	public void setPethtaddhp(int pethtaddhp){
		this.pethtaddhp = pethtaddhp;
	}
	
	/**
	 * get 合体增加内力
	 * @return 
	 */
	public int getPethtaddmp(){
		return pethtaddmp;
	}
	
	/**
	 * set 合体增加内力
	 */
	public void setPethtaddmp(int pethtaddmp){
		this.pethtaddmp = pethtaddmp;
	}
	
	/**
	 * get 合体增加攻击
	 * @return 
	 */
	public int getPethtaddattack(){
		return pethtaddattack;
	}
	
	/**
	 * set 合体增加攻击
	 */
	public void setPethtaddattack(int pethtaddattack){
		this.pethtaddattack = pethtaddattack;
	}
	
	/**
	 * get 合体增加防御
	 * @return 
	 */
	public int getPethtadddefence(){
		return pethtadddefence;
	}
	
	/**
	 * set 合体增加防御
	 */
	public void setPethtadddefence(int pethtadddefence){
		this.pethtadddefence = pethtadddefence;
	}
	
	/**
	 * get 合体增加暴击
	 * @return 
	 */
	public int getPethtaddcrit(){
		return pethtaddcrit;
	}
	
	/**
	 * set 合体增加暴击
	 */
	public void setPethtaddcrit(int pethtaddcrit){
		this.pethtaddcrit = pethtaddcrit;
	}
	
	/**
	 * get 合体增加闪避
	 * @return 
	 */
	public int getPethtadddodge(){
		return pethtadddodge;
	}
	
	/**
	 * set 合体增加闪避
	 */
	public void setPethtadddodge(int pethtadddodge){
		this.pethtadddodge = pethtadddodge;
	}
	
	/**
	 * get 侍宠技能 天赋技能排在第一个 不能动
	 * @return 
	 */
	public List<com.game.skill.bean.SkillInfo> getPetskillinfolist(){
		return petskillinfolist;
	}
	
	/**
	 * set 侍宠技能 天赋技能排在第一个 不能动
	 */
	public void setPetskillinfolist(List<com.game.skill.bean.SkillInfo> petskillinfolist){
		this.petskillinfolist = petskillinfolist;
	}
	
	/**
	 * get 历史最大连击数
	 * @return 
	 */
	public int getMaxEventcut(){
		return maxEventcut;
	}
	
	/**
	 * set 历史最大连击数
	 */
	public void setMaxEventcut(int maxEventcut){
		this.maxEventcut = maxEventcut;
	}
	
	/**
	 * get 历史最大连击数时间
	 * @return 
	 */
	public int getMaxEventcutTime(){
		return maxEventcutTime;
	}
	
	/**
	 * set 历史最大连击数时间
	 */
	public void setMaxEventcutTime(int maxEventcutTime){
		this.maxEventcutTime = maxEventcutTime;
	}
	
	/**
	 * get 连斩发生地图ID
	 * @return 
	 */
	public int getEvencutmapid(){
		return evencutmapid;
	}
	
	/**
	 * set 连斩发生地图ID
	 */
	public void setEvencutmapid(int evencutmapid){
		this.evencutmapid = evencutmapid;
	}
	
	/**
	 * get 最后连斩的怪物ID
	 * @return 
	 */
	public int getEvencutmonid(){
		return evencutmonid;
	}
	
	/**
	 * set 最后连斩的怪物ID
	 */
	public void setEvencutmonid(int evencutmonid){
		this.evencutmonid = evencutmonid;
	}
	
	/**
	 * get 连斩发生坐标X
	 * @return 
	 */
	public int getEvencutmapx(){
		return evencutmapx;
	}
	
	/**
	 * set 连斩发生坐标X
	 */
	public void setEvencutmapx(int evencutmapx){
		this.evencutmapx = evencutmapx;
	}
	
	/**
	 * get 连斩发生坐标Y
	 * @return 
	 */
	public int getEvencutmapy(){
		return evencutmapy;
	}
	
	/**
	 * set 连斩发生坐标Y
	 */
	public void setEvencutmapy(int evencutmapy){
		this.evencutmapy = evencutmapy;
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
	 * get 坐骑最高阶层
	 * @return 
	 */
	public int getHorselayer(){
		return horselayer;
	}
	
	/**
	 * set 坐骑最高阶层
	 */
	public void setHorselayer(int horselayer){
		this.horselayer = horselayer;
	}
	
	/**
	 * get 坐骑最高阶的当前等级
	 * @return 
	 */
	public int getHorselevel(){
		return horselevel;
	}
	
	/**
	 * set 坐骑最高阶的当前等级
	 */
	public void setHorselevel(int horselevel){
		this.horselevel = horselevel;
	}
	
	/**
	 * get 坐骑技能列表
	 * @return 
	 */
	public List<com.game.horse.bean.HorseSkillInfo> getSkillinfolist(){
		return skillinfolist;
	}
	
	/**
	 * set 坐骑技能列表
	 */
	public void setSkillinfolist(List<com.game.horse.bean.HorseSkillInfo> skillinfolist){
		this.skillinfolist = skillinfolist;
	}
	
	/**
	 * get 坐骑装备列表
	 * @return 
	 */
	public List<com.game.equip.bean.EquipInfo> getHorseequipinfo(){
		return horseequipinfo;
	}
	
	/**
	 * set 坐骑装备列表
	 */
	public void setHorseequipinfo(List<com.game.equip.bean.EquipInfo> horseequipinfo){
		this.horseequipinfo = horseequipinfo;
	}
	
	/**
	 * get 属性列表
	 * @return 
	 */
	public List<com.game.player.bean.PlayerAttributeItem> getAttributes(){
		return attributes;
	}
	
	/**
	 * set 属性列表
	 */
	public void setAttributes(List<com.game.player.bean.PlayerAttributeItem> attributes){
		this.attributes = attributes;
	}
	
	/**
	 * get 帮会信息
	 * @return 
	 */
	public com.game.guild.bean.GuildInfo getGuildinfo(){
		return guildinfo;
	}
	
	/**
	 * set 帮会信息
	 */
	public void setGuildinfo(com.game.guild.bean.GuildInfo guildinfo){
		this.guildinfo = guildinfo;
	}
	
	/**
	 * get 身上装备
	 * @return 
	 */
	public List<com.game.equip.bean.EquipInfo> getItemlist(){
		return itemlist;
	}
	
	/**
	 * set 身上装备
	 */
	public void setItemlist(List<com.game.equip.bean.EquipInfo> itemlist){
		this.itemlist = itemlist;
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
	 * get 骑战兵器技能信息
	 * @return 
	 */
	public List<com.game.horseweapon.bean.HorseWeaponSkillInfo> getHorseWeaponSkillInfo(){
		return horseWeaponSkillInfo;
	}
	
	/**
	 * set 骑战兵器技能信息
	 */
	public void setHorseWeaponSkillInfo(List<com.game.horseweapon.bean.HorseWeaponSkillInfo> horseWeaponSkillInfo){
		this.horseWeaponSkillInfo = horseWeaponSkillInfo;
	}
	
	/**
	 * get 骑战兵器最高阶层
	 * @return 
	 */
	public int getHorseweaponlayer(){
		return horseweaponlayer;
	}
	
	/**
	 * set 骑战兵器最高阶层
	 */
	public void setHorseweaponlayer(int horseweaponlayer){
		this.horseweaponlayer = horseweaponlayer;
	}
	
	/**
	 * get 骑战兵器最高阶的当前等级
	 * @return 
	 */
	public int getHorseweaponlevel(){
		return horseweaponlevel;
	}
	
	/**
	 * set 骑战兵器最高阶的当前等级
	 */
	public void setHorseweaponlevel(int horseweaponlevel){
		this.horseweaponlevel = horseweaponlevel;
	}
	
	/**
	 * get 暗器技能信息
	 * @return 
	 */
	public List<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo> getHiddenWeaponSkillInfo(){
		return hiddenWeaponSkillInfo;
	}
	
	/**
	 * set 暗器技能信息
	 */
	public void setHiddenWeaponSkillInfo(List<com.game.hiddenweapon.bean.HiddenWeaponSkillInfo> hiddenWeaponSkillInfo){
		this.hiddenWeaponSkillInfo = hiddenWeaponSkillInfo;
	}
	
	/**
	 * get 暗器最高阶层
	 * @return 
	 */
	public int getHiddenweaponlayer(){
		return hiddenweaponlayer;
	}
	
	/**
	 * set 暗器最高阶层
	 */
	public void setHiddenweaponlayer(int hiddenweaponlayer){
		this.hiddenweaponlayer = hiddenweaponlayer;
	}
	
	/**
	 * get 暗器最高阶的当前等级
	 * @return 
	 */
	public int getHiddenweaponlevel(){
		return hiddenweaponlevel;
	}
	
	/**
	 * set 暗器最高阶的当前等级
	 */
	public void setHiddenweaponlevel(int hiddenweaponlevel){
		this.hiddenweaponlevel = hiddenweaponlevel;
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
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家id
		buf.append("playerid:" + playerid +",");
		//平台VIP
		buf.append("webvip:" + webvip +",");
		//玩家名
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//地图模板id
		buf.append("mapModelId:" + mapModelId +",");
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		buf.append("toptype:" + toptype +",");
		//自己是否膜拜
		buf.append("worship:" + worship +",");
		//自己今天膜拜次数
		buf.append("worshipnum:" + worshipnum +",");
		//总膜拜次数
		buf.append("allworshipnum:" + allworshipnum +",");
		//名次
		buf.append("topidx:" + topidx +",");
		//等级
		buf.append("level:" + level +",");
		//性别
		buf.append("sex:" + sex +",");
		//国家
		buf.append("country:" + country +",");
		//角色经验
		buf.append("exp:" + exp +",");
		//角色真气
		buf.append("zhenqi:" + zhenqi +",");
		//头象ID
		buf.append("avatar:" + avatar +",");
		//当前章节
		buf.append("chapter:" + chapter +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//战场声望
		buf.append("prestige:" + prestige +",");
		//游戏币
		buf.append("money:" + money +",");
		//技能总层数
		buf.append("totalSkillLevel:" + totalSkillLevel +",");
		//墨子主动技能总层数
		buf.append("moziSkillLevel:" + moziSkillLevel +",");
		//墨子被动技能总层数
		buf.append("mozibackSkillLevel:" + mozibackSkillLevel +",");
		//龙元主动技能总层数
		buf.append("longyuanSkillLevel:" + longyuanSkillLevel +",");
		//龙元被动技能总层数
		buf.append("longyuanbackSkillLevel:" + longyuanbackSkillLevel +",");
		//龙元阶数
		buf.append("lysection:" + lysection +",");
		//龙元级数
		buf.append("lylevel:" + lylevel +",");
		//战斗力
		buf.append("fightPower:" + fightPower +",");
		//属性战斗力
		buf.append("attrfightPower:" + attrfightPower +",");
		//装备战斗力
		buf.append("equipfightPower:" + equipfightPower +",");
		//技能战斗力
		buf.append("skillfightPower:" + skillfightPower +",");
		//侍宠模板id
		buf.append("petmodelId:" + petmodelId +",");
		//侍宠等级
		buf.append("petlevel:" + petlevel +",");
		//合体次数
		buf.append("pethtcount:" + pethtcount +",");
		//合体增加生命
		buf.append("pethtaddhp:" + pethtaddhp +",");
		//合体增加内力
		buf.append("pethtaddmp:" + pethtaddmp +",");
		//合体增加攻击
		buf.append("pethtaddattack:" + pethtaddattack +",");
		//合体增加防御
		buf.append("pethtadddefence:" + pethtadddefence +",");
		//合体增加暴击
		buf.append("pethtaddcrit:" + pethtaddcrit +",");
		//合体增加闪避
		buf.append("pethtadddodge:" + pethtadddodge +",");
		//侍宠技能 天赋技能排在第一个 不能动
		buf.append("petskillinfolist:{");
		for (int i = 0; i < petskillinfolist.size(); i++) {
			buf.append(petskillinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//历史最大连击数
		buf.append("maxEventcut:" + maxEventcut +",");
		//历史最大连击数时间
		buf.append("maxEventcutTime:" + maxEventcutTime +",");
		//连斩发生地图ID
		buf.append("evencutmapid:" + evencutmapid +",");
		//最后连斩的怪物ID
		buf.append("evencutmonid:" + evencutmonid +",");
		//连斩发生坐标X
		buf.append("evencutmapx:" + evencutmapx +",");
		//连斩发生坐标Y
		buf.append("evencutmapy:" + evencutmapy +",");
		//弓箭信息
		if(this.arrowInfo!=null) buf.append("arrowInfo:" + arrowInfo.toString() +",");
		//坐骑最高阶层
		buf.append("horselayer:" + horselayer +",");
		//坐骑最高阶的当前等级
		buf.append("horselevel:" + horselevel +",");
		//坐骑技能列表
		buf.append("skillinfolist:{");
		for (int i = 0; i < skillinfolist.size(); i++) {
			buf.append(skillinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//坐骑装备列表
		buf.append("horseequipinfo:{");
		for (int i = 0; i < horseequipinfo.size(); i++) {
			buf.append(horseequipinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//帮会信息
		if(this.guildinfo!=null) buf.append("guildinfo:" + guildinfo.toString() +",");
		//身上装备
		buf.append("itemlist:{");
		for (int i = 0; i < itemlist.size(); i++) {
			buf.append(itemlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//装备部位全部宝石信息
		buf.append("posallgeminfo:{");
		for (int i = 0; i < posallgeminfo.size(); i++) {
			buf.append(posallgeminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//骑战兵器技能信息
		buf.append("horseWeaponSkillInfo:{");
		for (int i = 0; i < horseWeaponSkillInfo.size(); i++) {
			buf.append(horseWeaponSkillInfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//骑战兵器最高阶层
		buf.append("horseweaponlayer:" + horseweaponlayer +",");
		//骑战兵器最高阶的当前等级
		buf.append("horseweaponlevel:" + horseweaponlevel +",");
		//暗器技能信息
		buf.append("hiddenWeaponSkillInfo:{");
		for (int i = 0; i < hiddenWeaponSkillInfo.size(); i++) {
			buf.append(hiddenWeaponSkillInfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//暗器最高阶层
		buf.append("hiddenweaponlayer:" + hiddenweaponlayer +",");
		//暗器最高阶的当前等级
		buf.append("hiddenweaponlevel:" + hiddenweaponlevel +",");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("realmintensifylevel:" + realmintensifylevel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}