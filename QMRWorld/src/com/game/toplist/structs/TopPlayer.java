package com.game.toplist.structs;

import com.game.arrow.structs.ArrowData;
import com.game.backpack.structs.Equip;
import com.game.data.bean.Q_gem_activationBean;
import com.game.data.bean.Q_gem_upBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.data.manager.DataManager;
import com.game.gem.struts.Gem;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.longyuan.structs.LongYuanData;
import com.game.manager.ManagerPool;
import com.game.pet.struts.Pet;
import com.game.skill.structs.Skill;
import com.game.toplist.manager.TopListManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 排行玩家信息
 *
 * @author 杨鸿岚
 */
public class TopPlayer {
	//唯一ID

	private long id;
	//名字
	private String name;
	//等级
	private int level;
	//升级时间
	private long levelUpTime;
	//账号id
	private String userId;
	//地图模板id
	private int mapModelId;
	//性别
	private byte sex;
	//国家
	private int country;
	//头象ID
	private int avatar;
	//当前章节
	private int chapter;
	//王城BUFFid
	private int kingcitybuffid;
	//VIPID
	private int vipid;
	//身上装备
	private Equip[] equips = new Equip[12];
	// 游戏币
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
	//技能升级时间
	private long skillUpTime;
	//-------------------宠物部分--------------------------//
	//个人拥有的宠物列表
	private List<Pet> petList = new ArrayList<Pet>();
	//侍宠等阶(排行用)
	private int petorder;
	//侍宠合体次数(排行用)
	private int makelovenum;
	//侍宠等级(排行用)
	private int petlv;
	//-------------------宠物部分end--------------------------//
	//经验值
	private long exp;
	//真气
	private int zhenqi;
	//战场声望
	private int prestige;
	//---------------龙元心法-------------------
	//龙元心法阶段
	private LongYuanData longyuan = new LongYuanData();
	//龙元心法升级时间
	private long longyuanUpTime;
	//弓箭阶数
	private byte arrowLevel;
	//星斗等级
	private int starlv;
	//箭支等级
	private int bowlv;
	//弓箭信息
	private ArrowData arrowData = new ArrowData();
	//消耗元宝数
	private int costGold;
	//声望点
	private int prestigePoint;
	//成就点
	private int achievementPoint;
	//战斗力
	private int fightPower;
	//属性战斗力
	private int attrfightPower;
	//装备战斗力
	private int equipfightPower;
	//技能战斗力
	private int skillfightPower;
	//身上宝石10个部位，每个部位5颗宝石
	private Gem[][] gems = new Gem[10][5];
	//历史最大连击数
	private int maxEventcut;
	//历史最大连击数时间
	private long maxEventcutTime;
	//连斩发生地图ID
	private int evencutmapid;
	//最后连斩的怪物ID
	private int evencutmonid;
	//连斩发生坐标X
	private short evencutmapx;
	//连斩发生坐标Y
	private short evencutmapy;
	//技能
	private List<Skill> skills = new ArrayList<Skill>();
	//坐骑
	private List<Horse> horselist = new ArrayList<Horse>();
	//攻击
	private int attack;
	//防御
	private int defense;
	//暴击
	private int crit;
	//闪避
	private int dodge;
	//最大血量
	private int maxHp;
	//最大魔法
	private int maxMp;
	//最大体力
	private int maxSp;
	//攻击速度
	private int attackSpeed;
	//速度
	private int speed;
	//幸运
	private int luck;
	//骑兵
	private List<HorseWeapon> horseWeaponlist = new ArrayList<HorseWeapon>();
	//暗器
	private List<HiddenWeapon> hiddenWeaponlist = new ArrayList<HiddenWeapon>();
	//境界等级
	private int realmlevel;
		
	//境界强化等级
	private int realmintensifylevel;

	public List<HiddenWeapon> getHiddenWeaponlist() {
		return hiddenWeaponlist;
	}

	public void setHiddenWeaponlist(List<HiddenWeapon> hiddenWeaponlist) {
		this.hiddenWeaponlist = hiddenWeaponlist;
	}

	public int getMakelovenum() {
		return makelovenum;
	}

	public void setMakelovenum(int makelovenum) {
		this.makelovenum = makelovenum;
	}

	public int getPetlv() {
		return petlv;
	}

	public void setPetlv(int petlv) {
		this.petlv = petlv;
	}

	public int getPetorder() {
		return petorder;
	}

	public void setPetorder(int petorder) {
		this.petorder = petorder;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public int getKingcitybuffid() {
		return kingcitybuffid;
	}

	public void setKingcitybuffid(int kingcitybuffid) {
		this.kingcitybuffid = kingcitybuffid;
	}

	public int getVipid() {
		return vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public int getAchievementPoint() {
		return achievementPoint;
	}

	public void setAchievementPoint(int achievementPoint) {
		this.achievementPoint = achievementPoint;
	}

	public byte getArrowLevel() {
		return arrowLevel;
	}

	public void setArrowLevel(byte arrowLevel) {
		this.arrowLevel = arrowLevel;
	}

	public int getBowlv() {
		return bowlv;
	}

	public void setBowlv(int bowlv) {
		this.bowlv = bowlv;
	}

	public int getStarlv() {
		return starlv;
	}

	public void setStarlv(int starlv) {
		this.starlv = starlv;
	}

	public ArrowData getArrowData() {
		return arrowData;
	}

	public void setArrowData(ArrowData arrowData) {
		this.arrowData = arrowData;
	}

	public int getCostGold() {
		return costGold;
	}

	public void setCostGold(int costGold) {
		this.costGold = costGold;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public Equip[] getEquips() {
		return equips;
	}

	public void setEquips(Equip[] equips) {
		this.equips = equips;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public int getFightPower() {
		return fightPower;
	}

	public void setFightPower(int fightPower) {
		this.fightPower = fightPower;
	}

	public Gem[][] getGems() {
		return gems;
	}

	public void setGems(Gem[][] gems) {
		this.gems = gems;
	}

	public List<Horse> getHorselist() {
		return horselist;
	}

	public void setHorselist(List<Horse> horselist) {
		this.horselist = horselist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LongYuanData getLongyuan() {
		return longyuan;
	}

	public void setLongyuan(LongYuanData longyuan) {
		this.longyuan = longyuan;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public int getPrestigePoint() {
		return prestigePoint;
	}

	public void setPrestigePoint(int prestigePoint) {
		this.prestigePoint = prestigePoint;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}

	public long getLevelUpTime() {
		return levelUpTime;
	}

	public void setLevelUpTime(long levelUpTime) {
		this.levelUpTime = levelUpTime;
	}

	public int getTotalSkillLevel() {
		return totalSkillLevel;
	}

	public void setTotalSkillLevel(int totalSkillLevel) {
		this.totalSkillLevel = totalSkillLevel;
	}

	public long getSkillUpTime() {
		return skillUpTime;
	}

	public void setSkillUpTime(long skillUpTime) {
		this.skillUpTime = skillUpTime;
	}

	public long getLongyuanUpTime() {
		return longyuanUpTime;
	}

	public void setLongyuanUpTime(long longyuanUpTime) {
		this.longyuanUpTime = longyuanUpTime;
	}

	public int getMaxEventcut() {
		return maxEventcut;
	}

	public void setMaxEventcut(int maxEventcut) {
		this.maxEventcut = maxEventcut;
	}

	public long getMaxEventcutTime() {
		return maxEventcutTime;
	}

	public void setMaxEventcutTime(long maxEventcutTime) {
		this.maxEventcutTime = maxEventcutTime;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	public int getMaxSp() {
		return maxSp;
	}

	public void setMaxSp(int maxSp) {
		this.maxSp = maxSp;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getEvencutmapid() {
		return evencutmapid;
	}

	public void setEvencutmapid(int evencutmapid) {
		this.evencutmapid = evencutmapid;
	}

	public int getEvencutmonid() {
		return evencutmonid;
	}

	public void setEvencutmonid(int evencutmonid) {
		this.evencutmonid = evencutmonid;
	}

	public short getEvencutmapx() {
		return evencutmapx;
	}

	public void setEvencutmapx(short evencutmapx) {
		this.evencutmapx = evencutmapx;
	}

	public short getEvencutmapy() {
		return evencutmapy;
	}

	public void setEvencutmapy(short evencutmapy) {
		this.evencutmapy = evencutmapy;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public int getAttrfightPower() {
		return attrfightPower;
	}

	public void setAttrfightPower(int attrfightPower) {
		this.attrfightPower = attrfightPower;
	}

	public int getEquipfightPower() {
		return equipfightPower;
	}

	public void setEquipfightPower(int equipfightPower) {
		this.equipfightPower = equipfightPower;
	}

	public int getLongyuanSkillLevel() {
		return longyuanSkillLevel;
	}

	public void setLongyuanSkillLevel(int longyuanSkillLevel) {
		this.longyuanSkillLevel = longyuanSkillLevel;
	}

	public int getLongyuanbackSkillLevel() {
		return longyuanbackSkillLevel;
	}

	public void setLongyuanbackSkillLevel(int longyuanbackSkillLevel) {
		this.longyuanbackSkillLevel = longyuanbackSkillLevel;
	}

	public int getMoziSkillLevel() {
		return moziSkillLevel;
	}

	public void setMoziSkillLevel(int moziSkillLevel) {
		this.moziSkillLevel = moziSkillLevel;
	}

	public int getMozibackSkillLevel() {
		return mozibackSkillLevel;
	}

	public void setMozibackSkillLevel(int mozibackSkillLevel) {
		this.mozibackSkillLevel = mozibackSkillLevel;
	}

	public int getSkillfightPower() {
		return skillfightPower;
	}

	public void setSkillfightPower(int skillfightPower) {
		this.skillfightPower = skillfightPower;
	}

	public Pet checkAndRetPet(int petModelId) {
		for (int i = 0; i < petList.size(); i++) {
			Pet pet = petList.get(i);
			if (pet.getModelId() == petModelId) {
				return pet;
			}
		}
		return null;
	}

	public Pet getMaxPet() {
		Pet maxPet = null;
		ListIterator<Pet> listIterator = petList.listIterator();
		while (listIterator.hasNext()) {
			Pet pet = listIterator.next();
			if (maxPet == null) {
				maxPet = pet;
			} else {
				if (maxPet.getModelId() < pet.getModelId()) {
					maxPet = pet;
				}
			}
		}
		return maxPet;
	}

	public void calSkillLinked() {
		setSkillfightPower(0);
		setMoziSkillLevel(0);
		setMozibackSkillLevel(0);
		setLongyuanSkillLevel(0);
		setLongyuanbackSkillLevel(0);
		moziSkillLevel++;
		for (int i = 0; i < getSkills().size(); i++) {
			Skill skill = getSkills().get(i);
			if (skill != null) {
				Q_skill_modelBean q_skill_modelBean = DataManager.getInstance().q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
				if (q_skill_modelBean != null) {
					skillfightPower = skillfightPower + q_skill_modelBean.getQ_fight_bonus();
					if (q_skill_modelBean.getQ_panel_type() == 1 && q_skill_modelBean.getQ_trigger_type() == 1) {
						moziSkillLevel = moziSkillLevel + skill.getSkillLevel();
					}
					if (q_skill_modelBean.getQ_panel_type() == 1 && q_skill_modelBean.getQ_trigger_type() == 2) {
						mozibackSkillLevel = mozibackSkillLevel + skill.getSkillLevel();
					}
					if (q_skill_modelBean.getQ_panel_type() == 2 && q_skill_modelBean.getQ_trigger_type() == 1) {
						longyuanSkillLevel = longyuanSkillLevel + skill.getSkillLevel();
					}
					if (q_skill_modelBean.getQ_panel_type() == 2 && q_skill_modelBean.getQ_trigger_type() == 2) {
						longyuanbackSkillLevel = longyuanbackSkillLevel + skill.getSkillLevel();
					}
				}
			}
		}
	}
	//分母为10
	private final int MaxMaxHpCof = 2;			//最大生命	2
	private final int MaxMaxMpCof = 1;			//最大内力	1
	private final int MaxDefCof = 10;			//防御		10
	private final int MaxDodgeCof = 40;			//闪避		40
	private final int MaxCritCof = 40;			//暴击		40
	private final int MaxAttackSpeedCOF = 25;		//攻击速度	25
	private final int MaxMoveSpeedCof = 15;		//移动速度	15
	private final int MaxLuckyCof = 8;			//幸运		8
	private final int MaxMaxSpCof = 1;			//体力		1
	private final int MaxAttackCof = 14;			//攻击		14

	public void calEquipLinked() {
		setEquipfightPower(0);
		int attackEquipValue = 0;
		int defenseEquipValue = 0;
		int critEquipValue = 0;
		int dodgeEquipValue = 0;
		int maxHpEquipValue = 0;
		int maxMpEquipValue = 0;
		int maxSpEquipValue = 0;
		int attackSpeedEquipValue = 0;
		int speedEquipValue = 0;
		int luckEquipValue = 0;
		for (int i = 0; i < getEquips().length; i++) {
			Equip equip = getEquips()[i];
			if (equip == null) {
				continue;
			}
			attackEquipValue += equip.getAttack();
			defenseEquipValue += equip.getDefense();
			critEquipValue += equip.getCrit();
			dodgeEquipValue += equip.getDodge();
			maxHpEquipValue += equip.getMaxHp();
			maxMpEquipValue += equip.getMaxMp();
			maxSpEquipValue += equip.getMaxSp();
			attackSpeedEquipValue += equip.getAttackSpeed();
			speedEquipValue += equip.getSpeed();
			luckEquipValue += equip.getLuck();
		}
		Gem[][] gemarr = getGems();
		if (gemarr != null) {
			for (int i = 0; i < gemarr.length; i++) {
				Gem[] gempos = gemarr[i];
				for (int j = 0; j < gempos.length; j++) {
					if (gempos[j] == null) {
						continue;
					}
					if (gempos[j].getIsact() > 0) {
						String id = (i + 1) + "_" + (gempos[j].getGrid() + 1);
						Q_gem_activationBean actdata = ManagerPool.dataManager.q_gem_activationContainer.getMap().get(id);
						if (actdata != null) {
							Q_gem_upBean updata = ManagerPool.dataManager.q_gem_upContainer.getMap().get(actdata.getQ_gem_type() + "_" + gempos[j].getLevel());
							if (updata != null) {
								attackEquipValue += updata.getQ_attack();
								defenseEquipValue += updata.getQ_defence();
								critEquipValue += updata.getQ_crit();
								dodgeEquipValue += updata.getQ_dodge();
								maxHpEquipValue += updata.getQ_maxhp();
								maxMpEquipValue += updata.getQ_maxmp();
								maxSpEquipValue += updata.getQ_maxsp();
								attackSpeedEquipValue += updata.getQ_attackspeed();
								speedEquipValue += updata.getQ_speed();
								luckEquipValue += updata.getQ_luck();
							}
						}
					}
				}
			}
		}
		setEquipfightPower((attackEquipValue * MaxAttackCof
			+ defenseEquipValue * MaxDefCof
			+ critEquipValue * MaxCritCof
			+ dodgeEquipValue * MaxDodgeCof
			+ maxHpEquipValue * MaxMaxHpCof
			+ maxMpEquipValue * MaxMaxMpCof
			+ maxSpEquipValue * MaxMaxSpCof
			+ attackSpeedEquipValue * MaxAttackSpeedCOF
			+ speedEquipValue * MaxMoveSpeedCof
			+ luckEquipValue * MaxLuckyCof) / 10);
	}

	public void calAttrLinked() {
		setAttrfightPower(0);
		setAttrfightPower(getFightPower() - getEquipfightPower() - getSkillfightPower());
	}
	
	public Horse getCurHorse(){
		if (getHorselist().isEmpty()) {
			return new Horse();
		}else{
			return getHorselist().get(0);
		}
	}
	
	public HorseWeapon getCurHorseWeapon(){
		if (getHorseWeaponlist().isEmpty()) {
			return new HorseWeapon();
		}else{
			return getHorseWeaponlist().get(0);
		}
	}
	
	public void updateTopData(TopData topData) {
		if (topData == null) {
			return;
		}
		if (topData instanceof LevelTop) {
			LevelTop levelTop = (LevelTop) topData;
			this.setLevel(levelTop.getLv());
			this.setLevelUpTime(levelTop.getLvtime());
		} else if (topData instanceof HorseTop) {
			HorseTop horseTop = (HorseTop) topData;
			this.getCurHorse().setLayer((short) horseTop.getHorsejieshu());
			this.getCurHorse().setHorselevel(horseTop.getHorselv());
			this.getCurHorse().setSkilllevelsum(horseTop.getHorseskilllv());
			this.getCurHorse().setHorseUpTime(horseTop.getHorsetime());
		} else if (topData instanceof GestTop) {
			GestTop gestTop = (GestTop) topData;
			this.setTotalSkillLevel(gestTop.getGestlv());
			this.setSkillUpTime(gestTop.getGesttime());
		} else if (topData instanceof LongYuanTop) {
			LongYuanTop longYuanTop = (LongYuanTop) topData;
			this.getLongyuan().setLysection((byte) longYuanTop.getLysection());
			this.getLongyuan().setLylevel((byte) longYuanTop.getLylevel());
			this.setLongyuanUpTime(longYuanTop.getLongyuantime());
		} else if (topData instanceof EvenCutTop) {
			EvenCutTop evenCutTop = (EvenCutTop) topData;
			this.setMaxEventcut(evenCutTop.getEvencutNum());
			this.setMaxEventcutTime(evenCutTop.getEvencuttime());
			this.setEvencutmapid(evenCutTop.getMapModelId());
			this.setEvencutmonid(evenCutTop.getMonsterModelId());
			this.setEvencutmapx(evenCutTop.getMapX());
			this.setEvencutmapy(evenCutTop.getMapY());
		} else if (topData instanceof PetTop) {
			PetTop petTop = (PetTop) topData;
			this.setPetorder(petTop.getPetorder());
			this.setMakelovenum(petTop.getMakelovenum());
			this.setPetlv(petTop.getPetlv());
		} else if (topData instanceof FightPowerTop) {
			FightPowerTop fightPowerTop = (FightPowerTop) topData;
			this.setFightPower(fightPowerTop.getFightpower());
		} else if (topData instanceof ArrowTop) {
			ArrowTop arrowTop = (ArrowTop) topData;
			this.setArrowLevel((byte) arrowTop.getArrowlv());
			this.setStarlv(arrowTop.getStarlv());
			this.setBowlv(arrowTop.getBowlv());
			this.setCostGold(arrowTop.getCostgold());
		}
	}

	public TopData getTopData(TopData topData) {
		if (topData == null) {
			return null;
		}
		TopData retTopData = null;
		if (topData instanceof LevelTop) {
			retTopData = new LevelTop(getId(), getLevel(), getLevelUpTime());
		} else if (topData instanceof HorseTop) {
			retTopData = new HorseTop(getId(), getCurHorse().getLayer(), getCurHorse().getHorselevel(), getCurHorse().getSkilllevelsum(), getCurHorse().getHorseUpTime());
		} else if (topData instanceof GestTop) {
			retTopData = new GestTop(getId(), getTotalSkillLevel(), getSkillUpTime());
		} else if (topData instanceof LongYuanTop) {
			retTopData = new LongYuanTop(getId(), getLongyuan().getLysection(), getLongyuan().getLylevel(), getLongyuanUpTime());
		} else if (topData instanceof EvenCutTop) {
			retTopData = new EvenCutTop(getId(), getMaxEventcut(), getMaxEventcutTime(), getEvencutmapid(), getEvencutmonid(), getEvencutmapx(), getEvencutmapy());
		} else if (topData instanceof PetTop) {
			retTopData = new PetTop(getId(), getPetorder(), getMakelovenum(), getPetlv());
		} else if (topData instanceof FightPowerTop) {
			retTopData = new FightPowerTop(getId(), getFightPower(), getLevel());
		} else if (topData instanceof ArrowTop) {
			retTopData = new ArrowTop(getId(), getArrowLevel(), getStarlv(), getBowlv(), getLevel(), getCostGold());
		}
		return retTopData;
	}

	public TopData getTopData(int topType) {
		TopData retTopData = null;
		switch (topType) {
			case TopListManager.TOPTYPE_LEVEL: {
				retTopData = new LevelTop(getId(), getLevel(), getLevelUpTime());
			}
			break;
			case TopListManager.TOPTYPE_HORSE: {
				retTopData = new HorseTop(getId(), getCurHorse().getLayer(), getCurHorse().getHorselevel(), getCurHorse().getSkilllevelsum(), getCurHorse().getHorseUpTime());
			}
			break;
			case TopListManager.TOPTYPE_GEST: {
				retTopData = new GestTop(getId(), getTotalSkillLevel(), getSkillUpTime());
			}
			break;
			case TopListManager.TOPTYPE_LONGYUAN: {
				retTopData = new LongYuanTop(getId(), getLongyuan().getLysection(), getLongyuan().getLylevel(), getLongyuanUpTime());
			}
			break;
			case TopListManager.TOPTYPE_EVENCUT: {
				retTopData = new EvenCutTop(getId(), getMaxEventcut(), getMaxEventcutTime(), getEvencutmapid(), getEvencutmonid(), getEvencutmapx(), getEvencutmapy());
			}
			break;
			case TopListManager.TOPTYPE_PET: {
				retTopData = new PetTop(getId(), getPetorder(), getMakelovenum(), getPetlv());
			}
			break;
			case TopListManager.TOPTYPE_FIGHTPOWER: {
				retTopData = new FightPowerTop(getId(), getFightPower(), getLevel());
			}
			break;
			case TopListManager.TOPTYPE_ARROW: {
				retTopData = new ArrowTop(getId(), getArrowLevel(), getStarlv(), getBowlv(), getLevel(), getCostGold());
			}
			break;
		}
		return retTopData;
	}



	public List<HorseWeapon> getHorseWeaponlist() {
		return horseWeaponlist;
	}

	public void setHorseWeaponlist(List<HorseWeapon> horseWeaponlist) {
		this.horseWeaponlist = horseWeaponlist;
	}

	public HiddenWeapon getCurHiddenWeapon(){
		if (getHiddenWeaponlist().isEmpty()) {
			return new HiddenWeapon();
		}else{
			return getHiddenWeaponlist().get(0);
		}
	}

	public int getRealmlevel() {
		return realmlevel;
	}

	public void setRealmlevel(int realmlevel) {
		this.realmlevel = realmlevel;
	}

	public int getRealmintensifylevel() {
		return realmintensifylevel;
	}

	public void setRealmintensifylevel(int realmintensifylevel) {
		this.realmintensifylevel = realmintensifylevel;
	}
}
