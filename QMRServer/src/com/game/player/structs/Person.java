package com.game.player.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.game.buff.structs.Buff;
import com.game.cooldown.structs.Cooldown;
import com.game.fight.structs.FighterState;
import com.game.object.GameObject;
import com.game.structs.Position;

public abstract class Person extends GameObject {

	private static final long serialVersionUID = -7778568015291171928L;
	//模板Id
	protected int modelId;
	//创建时间
	protected long createTime;
	//所在线服务器
	protected transient int line;
	//地图
	protected int map;
	//地图模板id
	protected int mapModelId;
	//坐标
	protected Position position;
	//方向
	protected byte direction;
	//移动路径
	protected transient List<Position> roads = new ArrayList<Position>();
	//移动耗时时间
	protected transient int cost = 0;
	//移动上一步时间
	protected transient long prevStep = 0;
	//当前生命
	protected int hp;
	//当前魔法
	protected int mp;
	//当前体力
	protected int sp;
	//名字
	protected String name;
	//资源
	protected String res;
	//头像
	protected String icon;
	//等级
	protected int level;
	//BUFF列表
	protected List<Buff> buffs = new ArrayList<Buff>();
	//冷却列表
	protected HashMap<String, Cooldown> cooldowns= new  HashMap<String, Cooldown>();
	//战斗状态
	protected transient int fightState;
	//战斗伤害减免
	protected transient int reduce;
	//攻击
	protected int attack;
	//防御
	protected int defense;
	//暴击
	protected int crit;
	//闪避
	protected int dodge;
	//最大血量
	protected int maxHp;
	//最大魔法
	protected int maxMp;
	//最大体力
	protected int maxSp;
	//攻击速度
	protected int attackSpeed;
	//速度
	protected int speed;
	//幸运
	protected int luck;
	//无视防御
	private int negDefence;
	//弓箭几率
	private int arrowProbability;
	
	//计算战斗力用攻击
	protected transient int calattack;
	//计算战斗力用防御
	protected transient int caldefense;
	//计算战斗力用暴击
	protected transient int calcrit;
	//计算战斗力用闪避
	protected transient int caldodge;
	//计算战斗力用最大血量
	protected transient int calmaxHp;
	//计算战斗力用最大魔法
	protected transient int calmaxMp;
	//计算战斗力用最大体力
	protected transient int calmaxSp;
	//计算战斗力用攻击速度
	protected transient int calattackSpeed;
	//计算战斗力用速度
	protected transient int calspeed;
	//计算战斗力用幸运
	protected transient int calluck;
	//计算无视防御
	private int calnegDefence;
	//计算弓箭几率
	private int calarrowProbability;
	
	
	//是否死亡
	protected transient boolean die;
	//经验加成
	protected transient int expMultiple;
	//真气加成
	protected transient int zhenQiMultiple;
	
	//技能加成
	/**
	 * key为技能id（key为-1的时候为所有技能加成）
	 */
	protected transient HashMap<Integer, Integer> skillLevelUp = new HashMap<Integer, Integer>();

	abstract public int getServerId();
	
	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public int getMapModelId() {
		return mapModelId;
	}

	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public List<Position> getRoads() {
		return roads;
	}

	public void setRoads(List<Position> roads) {
		this.roads = roads;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public long getPrevStep() {
		return prevStep;
	}

	public void setPrevStep(long prevStep) {
		this.prevStep = prevStep;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getSp() {
		return sp;
	}

	public void setSp(int sp) {
		this.sp = sp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public List<Buff> getBuffs() {
		return buffs;
	}

	public void setBuffs(List<Buff> buffs) {
		this.buffs = buffs;
	}

	public HashMap<String, Cooldown> getCooldowns() {
		return cooldowns;
	}

	public void setCooldowns(HashMap<String, Cooldown> cooldowns) {
		this.cooldowns = cooldowns;
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

	public int getCalattack() {
		return calattack;
	}

	public void setCalattack(int calattack) {
		this.calattack = calattack;
	}

	public int getCalattackSpeed() {
		return calattackSpeed;
	}

	public void setCalattackSpeed(int calattackSpeed) {
		this.calattackSpeed = calattackSpeed;
	}

	public int getCalcrit() {
		return calcrit;
	}

	public void setCalcrit(int calcrit) {
		this.calcrit = calcrit;
	}

	public int getCaldefense() {
		return caldefense;
	}

	public void setCaldefense(int caldefense) {
		this.caldefense = caldefense;
	}

	public int getCaldodge() {
		return caldodge;
	}

	public void setCaldodge(int caldodge) {
		this.caldodge = caldodge;
	}

	public int getCalluck() {
		return calluck;
	}

	public void setCalluck(int calluck) {
		this.calluck = calluck;
	}

	public int getCalmaxHp() {
		return calmaxHp;
	}

	public void setCalmaxHp(int calmaxHp) {
		this.calmaxHp = calmaxHp;
	}

	public int getCalmaxMp() {
		return calmaxMp;
	}

	public void setCalmaxMp(int calmaxMp) {
		this.calmaxMp = calmaxMp;
	}

	public int getCalmaxSp() {
		return calmaxSp;
	}

	public void setCalmaxSp(int calmaxSp) {
		this.calmaxSp = calmaxSp;
	}

	public int getCalspeed() {
		return calspeed;
	}

	public void setCalspeed(int calspeed) {
		this.calspeed = calspeed;
	}

	public int getExpMultiple() {
		return expMultiple;
	}

	public void setExpMultiple(int expMultiple) {
		this.expMultiple = expMultiple;
	}
	
	public int getFightState() {
		return fightState;
	}

	public void setFightState(int fightState) {
		this.fightState = fightState;
	}

	public void addFightState(FighterState state){
		this.fightState = this.fightState | state.getValue();
	}
	
	public void removeFightState(FighterState state){
		this.fightState = this.fightState & (~state.getValue());
	}

	public int getReduce() {
		return reduce;
	}

	public void setReduce(int reduce) {
		this.reduce = reduce;
	}

	public HashMap<Integer, Integer> getSkillLevelUp() {
		return skillLevelUp;
	}

	public void setSkillLevelUp(HashMap<Integer, Integer> skillLevelUp) {
		this.skillLevelUp = skillLevelUp;
	}

	public int getZhenQiMultiple() {
		return zhenQiMultiple;
	}

	public void setZhenQiMultiple(int zhenQiMultiple) {
		this.zhenQiMultiple = zhenQiMultiple;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public int getNegDefence() {
		return negDefence;
	}

	public void setNegDefence(int negDefence) {
		this.negDefence = negDefence;
	}

	public int getArrowProbability() {
		return arrowProbability;
	}

	public void setArrowProbability(int arrowProbability) {
		this.arrowProbability = arrowProbability;
	}

	public int getCalnegDefence() {
		return calnegDefence;
	}

	public void setCalnegDefence(int calnegDefence) {
		this.calnegDefence = calnegDefence;
	}

	public int getCalarrowProbability() {
		return calarrowProbability;
	}

	public void setCalarrowProbability(int calarrowProbability) {
		this.calarrowProbability = calarrowProbability;
	}
	
}
