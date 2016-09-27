package com.game.player.message;

import com.game.player.bean.PlayerAttributeItem;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家全部信息变动消息
 */
public class ReqSyncPlayerOrderInfoMessage extends Message{

	//角色id
	private long playerId;
	
	//账号
	private String userId;
	
	//角色名字
	private String name;
	
	//角色性别
	private byte sex;
	
	//角色国家
	private int country;
	
	//角色金币
	private int money;
	
	//角色经验
	private long exp;
	
	//角色真气
	private int zhenqi;
	
	//头象ID
	private int avatar;
	
	//当前章节
	private int chapter;
	
	//战场声望
	private int prestige;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//角色等级
	private int level;
	
	//角色升级时间
	private long levelUpTime;
	
	//角色连斩数量
	private int eventcut;
	
	//角色连斩数量时间
	private long eventcutTime;
	
	//角色技能层数
	private int skillLevel;
	
	//角色技能层数时间
	private long skillTime;
	
	//角色所在地图
	private int mapId;
	
	//角色龙元心法星图
	private int longyuanSection;
	
	//角色龙元心法阶数
	private int longyuanLevel;
	
	//角色龙元心法时间
	private long longyuanTime;
	
	//属性列表
	private List<PlayerAttributeItem> attributes = new ArrayList<PlayerAttributeItem>();
	//战斗力
	private int fightPower;
	
	//装备信息
	private String equip;
	
	//宝石信息
	private String gem;
	
	//技能信息
	private String skills;
	
	//角色坐骑阶数
	private int horseStage;
	
	//角色坐骑等级
	private int horseLevel;
	
	//角色坐骑技能等级
	private int horseSkillLevel;
	
	//角色坐骑阶数时间
	private long horseTime;
	
	//坐骑装备
	private String horseEquip;
	
	//坐骑技能
	private String horseSkill;
	
	//宠物信息
	private String pets;
	
	//军衔等级
	private byte ranklevel;
	
	//军衔点
	private int rankpoint;
	
	//消耗元宝
	private int costgold;
	
	//弓箭信息
	private String arrowinfo;
	
	//骑兵阶数
	private int horseweaponlayer;
	
	//骑兵等级
	private int horseweaponlevel;
	
	//骑兵技能列表
	private String horseWeaponSkill;
	
	//骑兵进阶时间
	private long horseweaponTime;
	
	//暗器阶数
	private int hiddenweaponlayer;
	
	//暗器等级
	private int hiddenweaponlevel;
	
	//暗器技能列表
	private String hiddenWeaponSkill;
	
	//暗器进阶时间
	private long hiddenweaponTime;
	
	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int realmintensifylevel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//账号
		writeString(buf, this.userId);
		//角色名字
		writeString(buf, this.name);
		//角色性别
		writeByte(buf, this.sex);
		//角色国家
		writeInt(buf, this.country);
		//角色金币
		writeInt(buf, this.money);
		//角色经验
		writeLong(buf, this.exp);
		//角色真气
		writeInt(buf, this.zhenqi);
		//头象ID
		writeInt(buf, this.avatar);
		//当前章节
		writeInt(buf, this.chapter);
		//战场声望
		writeInt(buf, this.prestige);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//角色等级
		writeInt(buf, this.level);
		//角色升级时间
		writeLong(buf, this.levelUpTime);
		//角色连斩数量
		writeInt(buf, this.eventcut);
		//角色连斩数量时间
		writeLong(buf, this.eventcutTime);
		//角色技能层数
		writeInt(buf, this.skillLevel);
		//角色技能层数时间
		writeLong(buf, this.skillTime);
		//角色所在地图
		writeInt(buf, this.mapId);
		//角色龙元心法星图
		writeInt(buf, this.longyuanSection);
		//角色龙元心法阶数
		writeInt(buf, this.longyuanLevel);
		//角色龙元心法时间
		writeLong(buf, this.longyuanTime);
		//属性列表
		writeShort(buf, attributes.size());
		for (int i = 0; i < attributes.size(); i++) {
			writeBean(buf, attributes.get(i));
		}
		//战斗力
		writeInt(buf, this.fightPower);
		//装备信息
		writeString(buf, this.equip);
		//宝石信息
		writeString(buf, this.gem);
		//技能信息
		writeString(buf, this.skills);
		//角色坐骑阶数
		writeInt(buf, this.horseStage);
		//角色坐骑等级
		writeInt(buf, this.horseLevel);
		//角色坐骑技能等级
		writeInt(buf, this.horseSkillLevel);
		//角色坐骑阶数时间
		writeLong(buf, this.horseTime);
		//坐骑装备
		writeString(buf, this.horseEquip);
		//坐骑技能
		writeString(buf, this.horseSkill);
		//宠物信息
		writeString(buf, this.pets);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//军衔点
		writeInt(buf, this.rankpoint);
		//消耗元宝
		writeInt(buf, this.costgold);
		//弓箭信息
		writeString(buf, this.arrowinfo);
		//骑兵阶数
		writeInt(buf, this.horseweaponlayer);
		//骑兵等级
		writeInt(buf, this.horseweaponlevel);
		//骑兵技能列表
		writeString(buf, this.horseWeaponSkill);
		//骑兵进阶时间
		writeLong(buf, this.horseweaponTime);
		//暗器阶数
		writeInt(buf, this.hiddenweaponlayer);
		//暗器等级
		writeInt(buf, this.hiddenweaponlevel);
		//暗器技能列表
		writeString(buf, this.hiddenWeaponSkill);
		//暗器进阶时间
		writeLong(buf, this.hiddenweaponTime);
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
		//角色id
		this.playerId = readLong(buf);
		//账号
		this.userId = readString(buf);
		//角色名字
		this.name = readString(buf);
		//角色性别
		this.sex = readByte(buf);
		//角色国家
		this.country = readInt(buf);
		//角色金币
		this.money = readInt(buf);
		//角色经验
		this.exp = readLong(buf);
		//角色真气
		this.zhenqi = readInt(buf);
		//头象ID
		this.avatar = readInt(buf);
		//当前章节
		this.chapter = readInt(buf);
		//战场声望
		this.prestige = readInt(buf);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//角色等级
		this.level = readInt(buf);
		//角色升级时间
		this.levelUpTime = readLong(buf);
		//角色连斩数量
		this.eventcut = readInt(buf);
		//角色连斩数量时间
		this.eventcutTime = readLong(buf);
		//角色技能层数
		this.skillLevel = readInt(buf);
		//角色技能层数时间
		this.skillTime = readLong(buf);
		//角色所在地图
		this.mapId = readInt(buf);
		//角色龙元心法星图
		this.longyuanSection = readInt(buf);
		//角色龙元心法阶数
		this.longyuanLevel = readInt(buf);
		//角色龙元心法时间
		this.longyuanTime = readLong(buf);
		//属性列表
		int attributes_length = readShort(buf);
		for (int i = 0; i < attributes_length; i++) {
			attributes.add((PlayerAttributeItem)readBean(buf, PlayerAttributeItem.class));
		}
		//战斗力
		this.fightPower = readInt(buf);
		//装备信息
		this.equip = readString(buf);
		//宝石信息
		this.gem = readString(buf);
		//技能信息
		this.skills = readString(buf);
		//角色坐骑阶数
		this.horseStage = readInt(buf);
		//角色坐骑等级
		this.horseLevel = readInt(buf);
		//角色坐骑技能等级
		this.horseSkillLevel = readInt(buf);
		//角色坐骑阶数时间
		this.horseTime = readLong(buf);
		//坐骑装备
		this.horseEquip = readString(buf);
		//坐骑技能
		this.horseSkill = readString(buf);
		//宠物信息
		this.pets = readString(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//军衔点
		this.rankpoint = readInt(buf);
		//消耗元宝
		this.costgold = readInt(buf);
		//弓箭信息
		this.arrowinfo = readString(buf);
		//骑兵阶数
		this.horseweaponlayer = readInt(buf);
		//骑兵等级
		this.horseweaponlevel = readInt(buf);
		//骑兵技能列表
		this.horseWeaponSkill = readString(buf);
		//骑兵进阶时间
		this.horseweaponTime = readLong(buf);
		//暗器阶数
		this.hiddenweaponlayer = readInt(buf);
		//暗器等级
		this.hiddenweaponlevel = readInt(buf);
		//暗器技能列表
		this.hiddenWeaponSkill = readString(buf);
		//暗器进阶时间
		this.hiddenweaponTime = readLong(buf);
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.realmintensifylevel = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
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
	 * get 角色国家
	 * @return 
	 */
	public int getCountry(){
		return country;
	}
	
	/**
	 * set 角色国家
	 */
	public void setCountry(int country){
		this.country = country;
	}
	
	/**
	 * get 角色金币
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 角色金币
	 */
	public void setMoney(int money){
		this.money = money;
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
	 * get 角色升级时间
	 * @return 
	 */
	public long getLevelUpTime(){
		return levelUpTime;
	}
	
	/**
	 * set 角色升级时间
	 */
	public void setLevelUpTime(long levelUpTime){
		this.levelUpTime = levelUpTime;
	}
	
	/**
	 * get 角色连斩数量
	 * @return 
	 */
	public int getEventcut(){
		return eventcut;
	}
	
	/**
	 * set 角色连斩数量
	 */
	public void setEventcut(int eventcut){
		this.eventcut = eventcut;
	}
	
	/**
	 * get 角色连斩数量时间
	 * @return 
	 */
	public long getEventcutTime(){
		return eventcutTime;
	}
	
	/**
	 * set 角色连斩数量时间
	 */
	public void setEventcutTime(long eventcutTime){
		this.eventcutTime = eventcutTime;
	}
	
	/**
	 * get 角色技能层数
	 * @return 
	 */
	public int getSkillLevel(){
		return skillLevel;
	}
	
	/**
	 * set 角色技能层数
	 */
	public void setSkillLevel(int skillLevel){
		this.skillLevel = skillLevel;
	}
	
	/**
	 * get 角色技能层数时间
	 * @return 
	 */
	public long getSkillTime(){
		return skillTime;
	}
	
	/**
	 * set 角色技能层数时间
	 */
	public void setSkillTime(long skillTime){
		this.skillTime = skillTime;
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
	 * get 角色龙元心法星图
	 * @return 
	 */
	public int getLongyuanSection(){
		return longyuanSection;
	}
	
	/**
	 * set 角色龙元心法星图
	 */
	public void setLongyuanSection(int longyuanSection){
		this.longyuanSection = longyuanSection;
	}
	
	/**
	 * get 角色龙元心法阶数
	 * @return 
	 */
	public int getLongyuanLevel(){
		return longyuanLevel;
	}
	
	/**
	 * set 角色龙元心法阶数
	 */
	public void setLongyuanLevel(int longyuanLevel){
		this.longyuanLevel = longyuanLevel;
	}
	
	/**
	 * get 角色龙元心法时间
	 * @return 
	 */
	public long getLongyuanTime(){
		return longyuanTime;
	}
	
	/**
	 * set 角色龙元心法时间
	 */
	public void setLongyuanTime(long longyuanTime){
		this.longyuanTime = longyuanTime;
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
	 * get 装备信息
	 * @return 
	 */
	public String getEquip(){
		return equip;
	}
	
	/**
	 * set 装备信息
	 */
	public void setEquip(String equip){
		this.equip = equip;
	}
	
	/**
	 * get 宝石信息
	 * @return 
	 */
	public String getGem(){
		return gem;
	}
	
	/**
	 * set 宝石信息
	 */
	public void setGem(String gem){
		this.gem = gem;
	}
	
	/**
	 * get 技能信息
	 * @return 
	 */
	public String getSkills(){
		return skills;
	}
	
	/**
	 * set 技能信息
	 */
	public void setSkills(String skills){
		this.skills = skills;
	}
	
	/**
	 * get 角色坐骑阶数
	 * @return 
	 */
	public int getHorseStage(){
		return horseStage;
	}
	
	/**
	 * set 角色坐骑阶数
	 */
	public void setHorseStage(int horseStage){
		this.horseStage = horseStage;
	}
	
	/**
	 * get 角色坐骑等级
	 * @return 
	 */
	public int getHorseLevel(){
		return horseLevel;
	}
	
	/**
	 * set 角色坐骑等级
	 */
	public void setHorseLevel(int horseLevel){
		this.horseLevel = horseLevel;
	}
	
	/**
	 * get 角色坐骑技能等级
	 * @return 
	 */
	public int getHorseSkillLevel(){
		return horseSkillLevel;
	}
	
	/**
	 * set 角色坐骑技能等级
	 */
	public void setHorseSkillLevel(int horseSkillLevel){
		this.horseSkillLevel = horseSkillLevel;
	}
	
	/**
	 * get 角色坐骑阶数时间
	 * @return 
	 */
	public long getHorseTime(){
		return horseTime;
	}
	
	/**
	 * set 角色坐骑阶数时间
	 */
	public void setHorseTime(long horseTime){
		this.horseTime = horseTime;
	}
	
	/**
	 * get 坐骑装备
	 * @return 
	 */
	public String getHorseEquip(){
		return horseEquip;
	}
	
	/**
	 * set 坐骑装备
	 */
	public void setHorseEquip(String horseEquip){
		this.horseEquip = horseEquip;
	}
	
	/**
	 * get 坐骑技能
	 * @return 
	 */
	public String getHorseSkill(){
		return horseSkill;
	}
	
	/**
	 * set 坐骑技能
	 */
	public void setHorseSkill(String horseSkill){
		this.horseSkill = horseSkill;
	}
	
	/**
	 * get 宠物信息
	 * @return 
	 */
	public String getPets(){
		return pets;
	}
	
	/**
	 * set 宠物信息
	 */
	public void setPets(String pets){
		this.pets = pets;
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
	 * get 军衔点
	 * @return 
	 */
	public int getRankpoint(){
		return rankpoint;
	}
	
	/**
	 * set 军衔点
	 */
	public void setRankpoint(int rankpoint){
		this.rankpoint = rankpoint;
	}
	
	/**
	 * get 消耗元宝
	 * @return 
	 */
	public int getCostgold(){
		return costgold;
	}
	
	/**
	 * set 消耗元宝
	 */
	public void setCostgold(int costgold){
		this.costgold = costgold;
	}
	
	/**
	 * get 弓箭信息
	 * @return 
	 */
	public String getArrowinfo(){
		return arrowinfo;
	}
	
	/**
	 * set 弓箭信息
	 */
	public void setArrowinfo(String arrowinfo){
		this.arrowinfo = arrowinfo;
	}
	
	/**
	 * get 骑兵阶数
	 * @return 
	 */
	public int getHorseweaponlayer(){
		return horseweaponlayer;
	}
	
	/**
	 * set 骑兵阶数
	 */
	public void setHorseweaponlayer(int horseweaponlayer){
		this.horseweaponlayer = horseweaponlayer;
	}
	
	/**
	 * get 骑兵等级
	 * @return 
	 */
	public int getHorseweaponlevel(){
		return horseweaponlevel;
	}
	
	/**
	 * set 骑兵等级
	 */
	public void setHorseweaponlevel(int horseweaponlevel){
		this.horseweaponlevel = horseweaponlevel;
	}
	
	/**
	 * get 骑兵技能列表
	 * @return 
	 */
	public String getHorseWeaponSkill(){
		return horseWeaponSkill;
	}
	
	/**
	 * set 骑兵技能列表
	 */
	public void setHorseWeaponSkill(String horseWeaponSkill){
		this.horseWeaponSkill = horseWeaponSkill;
	}
	
	/**
	 * get 骑兵进阶时间
	 * @return 
	 */
	public long getHorseweaponTime(){
		return horseweaponTime;
	}
	
	/**
	 * set 骑兵进阶时间
	 */
	public void setHorseweaponTime(long horseweaponTime){
		this.horseweaponTime = horseweaponTime;
	}
	
	/**
	 * get 暗器阶数
	 * @return 
	 */
	public int getHiddenweaponlayer(){
		return hiddenweaponlayer;
	}
	
	/**
	 * set 暗器阶数
	 */
	public void setHiddenweaponlayer(int hiddenweaponlayer){
		this.hiddenweaponlayer = hiddenweaponlayer;
	}
	
	/**
	 * get 暗器等级
	 * @return 
	 */
	public int getHiddenweaponlevel(){
		return hiddenweaponlevel;
	}
	
	/**
	 * set 暗器等级
	 */
	public void setHiddenweaponlevel(int hiddenweaponlevel){
		this.hiddenweaponlevel = hiddenweaponlevel;
	}
	
	/**
	 * get 暗器技能列表
	 * @return 
	 */
	public String getHiddenWeaponSkill(){
		return hiddenWeaponSkill;
	}
	
	/**
	 * set 暗器技能列表
	 */
	public void setHiddenWeaponSkill(String hiddenWeaponSkill){
		this.hiddenWeaponSkill = hiddenWeaponSkill;
	}
	
	/**
	 * get 暗器进阶时间
	 * @return 
	 */
	public long getHiddenweaponTime(){
		return hiddenweaponTime;
	}
	
	/**
	 * set 暗器进阶时间
	 */
	public void setHiddenweaponTime(long hiddenweaponTime){
		this.hiddenweaponTime = hiddenweaponTime;
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
	public int getId() {
		return 103324;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色性别
		buf.append("sex:" + sex +",");
		//角色国家
		buf.append("country:" + country +",");
		//角色金币
		buf.append("money:" + money +",");
		//角色经验
		buf.append("exp:" + exp +",");
		//角色真气
		buf.append("zhenqi:" + zhenqi +",");
		//头象ID
		buf.append("avatar:" + avatar +",");
		//当前章节
		buf.append("chapter:" + chapter +",");
		//战场声望
		buf.append("prestige:" + prestige +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//角色等级
		buf.append("level:" + level +",");
		//角色升级时间
		buf.append("levelUpTime:" + levelUpTime +",");
		//角色连斩数量
		buf.append("eventcut:" + eventcut +",");
		//角色连斩数量时间
		buf.append("eventcutTime:" + eventcutTime +",");
		//角色技能层数
		buf.append("skillLevel:" + skillLevel +",");
		//角色技能层数时间
		buf.append("skillTime:" + skillTime +",");
		//角色所在地图
		buf.append("mapId:" + mapId +",");
		//角色龙元心法星图
		buf.append("longyuanSection:" + longyuanSection +",");
		//角色龙元心法阶数
		buf.append("longyuanLevel:" + longyuanLevel +",");
		//角色龙元心法时间
		buf.append("longyuanTime:" + longyuanTime +",");
		//属性列表
		buf.append("attributes:{");
		for (int i = 0; i < attributes.size(); i++) {
			buf.append(attributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//战斗力
		buf.append("fightPower:" + fightPower +",");
		//装备信息
		if(this.equip!=null) buf.append("equip:" + equip.toString() +",");
		//宝石信息
		if(this.gem!=null) buf.append("gem:" + gem.toString() +",");
		//技能信息
		if(this.skills!=null) buf.append("skills:" + skills.toString() +",");
		//角色坐骑阶数
		buf.append("horseStage:" + horseStage +",");
		//角色坐骑等级
		buf.append("horseLevel:" + horseLevel +",");
		//角色坐骑技能等级
		buf.append("horseSkillLevel:" + horseSkillLevel +",");
		//角色坐骑阶数时间
		buf.append("horseTime:" + horseTime +",");
		//坐骑装备
		if(this.horseEquip!=null) buf.append("horseEquip:" + horseEquip.toString() +",");
		//坐骑技能
		if(this.horseSkill!=null) buf.append("horseSkill:" + horseSkill.toString() +",");
		//宠物信息
		if(this.pets!=null) buf.append("pets:" + pets.toString() +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//军衔点
		buf.append("rankpoint:" + rankpoint +",");
		//消耗元宝
		buf.append("costgold:" + costgold +",");
		//弓箭信息
		if(this.arrowinfo!=null) buf.append("arrowinfo:" + arrowinfo.toString() +",");
		//骑兵阶数
		buf.append("horseweaponlayer:" + horseweaponlayer +",");
		//骑兵等级
		buf.append("horseweaponlevel:" + horseweaponlevel +",");
		//骑兵技能列表
		if(this.horseWeaponSkill!=null) buf.append("horseWeaponSkill:" + horseWeaponSkill.toString() +",");
		//骑兵进阶时间
		buf.append("horseweaponTime:" + horseweaponTime +",");
		//暗器阶数
		buf.append("hiddenweaponlayer:" + hiddenweaponlayer +",");
		//暗器等级
		buf.append("hiddenweaponlevel:" + hiddenweaponlevel +",");
		//暗器技能列表
		if(this.hiddenWeaponSkill!=null) buf.append("hiddenWeaponSkill:" + hiddenWeaponSkill.toString() +",");
		//暗器进阶时间
		buf.append("hiddenweaponTime:" + hiddenweaponTime +",");
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("realmintensifylevel:" + realmintensifylevel +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}