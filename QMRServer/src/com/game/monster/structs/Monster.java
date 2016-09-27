package com.game.monster.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.structs.AttributeBuff;
import com.game.buff.structs.Buff;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_scene_monsterBean;
import com.game.data.bean.Q_scene_monster_areaBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.IMapObject;
import com.game.map.structs.Map;
import com.game.monster.message.ResMonsterSayMessage;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.script.IMonsterCanAttackScript;
import com.game.monster.script.IMonsterCanBeAttackScript;
import com.game.monster.script.IMonsterCanSeeScript;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.pool.MemoryObject;
import com.game.script.structs.ScriptEnum;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class Monster extends Person implements IMapObject, Fighter, MemoryObject {

	private static final long serialVersionUID = -2328772357502414458L;
	
	private static Logger log = Logger.getLogger(Monster.class);
	//分布Id
	private int distributeId;
	//分布方式 1-定点分布 2-区域分布
	private int distributeType;
	//出生点
	private Position birthPos;
	//复活剩余时间
	private int revive;
	//仇恨列表
	private List<Hatred> hatreds = new ArrayList<Hatred>();
	//伤害列表
	private HashMap<Long, Integer> damages = new HashMap<Long, Integer>();
	//怪物状态
	private int state;
	//怪物使用技能
	protected Skill defaultSkill = new Skill();
	//怪物使用技能
	protected Skill useSkill = new Skill();
	//怪物类型
	private int monsterType;
	//怪物变身状态
	private HashMap<Integer, Morph> morph = new HashMap<Integer, Morph>();
	//攻击目标
	private Fighter target;
	//最后一次被攻击时间（boss用）
	private long lastAttackTime;
	//死亡时间
	private long dieTime;
	//所在服务器
	private int serverId;
	//血量比率
	private int hppercentage;
	//下一个技能
	private String nextskillid;

	private Fighter killer;
	
	protected Fighter attackTarget;
	
	protected Fighter defaultAttackTarget;
	//临时参数列表
	private HashMap<String, Object> parameters = new HashMap<String, Object>();
	//是否显示
	private boolean show = true;
	//显示set
	private HashSet<Long> showSet = new HashSet<Long>();
	//隐藏set
	private HashSet<Long> hideSet = new HashSet<Long>();
	//技能信息
	private List<Skill> skills = new ArrayList<Skill>();
	
	//字符串value
	private String strvalue;
	//友好状态
	private int friend;
	//友好参数
	private String friendPara;
	//巡逻路径
	private List<Position> xunluo = new ArrayList<Position>();
	//消失时间
	private long disappearTime;
	
	//怪物阵营
	private int groupmark;
	
	public int getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(int distributeId) {
		this.distributeId = distributeId;
	}
	
	public int getDistributeType() {
		return distributeType;
	}

	public void setDistributeType(int distributeType) {
		this.distributeType = distributeType;
	}

	public Position getBirthPos() {
		return birthPos;
	}

	public void setBirthPos(Position birthPos) {
		this.birthPos = birthPos;
	}

	public List<Hatred> getHatreds() {
		return hatreds;
	}

	public void setHatreds(List<Hatred> hatreds) {
		this.hatreds = hatreds;
	}

	public int getRevive() {
		return revive;
	}

	public void setRevive(int revive) {
		this.revive = revive;
	}

	public int getState() {
		return state;
	}

	public void setState(MonsterState state) {
		this.state = state.getValue();
	}

	public HashMap<Long, Integer> getDamages() {
		return damages;
	}

	public void setDamages(HashMap<Long, Integer> damages) {
		this.damages = damages;
	}

	public int getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(int monsterType) {
		this.monsterType = monsterType;
	}

	public HashMap<Integer, Morph> getMorph() {
		return morph;
	}

	public void setMorph(HashMap<Integer, Morph> morph) {
		this.morph = morph;
	}

	public long getLastAttackTime() {
		return lastAttackTime;
	}

	public void setLastAttackTime(long lastAttackTime) {
		this.lastAttackTime = lastAttackTime;
	}

	public long getDieTime() {
		return dieTime;
	}

	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	public Fighter getTarget() {
		return target;
	}

	public void setTarget(Fighter target) {
		this.target = target;
	}
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public Fighter getKiller() {
		return killer;
	}

	public void setKiller(Fighter killer) {
		this.killer = killer;
	}

	
	public int getHppercentage() {
		return hppercentage;
	}

	public void setHppercentage(int hppercentage) {
		this.hppercentage = hppercentage;
	}
	
	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	/**
	 * 返回攻击类型
	 * @return 1-主动攻击 2-被动攻击 3-木桩
	 */
	public int getAttackType(){
		if(this.getDistributeType()==1){
			Q_scene_monsterBean q_scene_monsterBean = ManagerPool.dataManager.q_scene_monsterContainer.getMap().get(this.getDistributeId());
			if(q_scene_monsterBean!=null && q_scene_monsterBean.getQ_attack() != 0){
				return q_scene_monsterBean.getQ_attack();
			}
		}else if(this.getDistributeType()==2){
			Q_scene_monster_areaBean q_scene_monster_areaBean = ManagerPool.dataManager.q_scene_monster_areaContainer.getMap().get(this.getDistributeId());
			if(q_scene_monster_areaBean!=null && q_scene_monster_areaBean.getQ_attack() != 0){
				return q_scene_monster_areaBean.getQ_attack();
			}
		}
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		return model.getQ_evasive_style();
	}
	
	/**
	 * 返回复活时间
	 * @return 
	 */
	public int getReviveTime(){
		if(this.getDistributeType()==1){
			Q_scene_monsterBean q_scene_monsterBean = ManagerPool.dataManager.q_scene_monsterContainer.getMap().get(this.getDistributeId());
			if(q_scene_monsterBean!=null && q_scene_monsterBean.getQ_relive() != 0){
				return q_scene_monsterBean.getQ_relive();
			}
		}else if(this.getDistributeType()==2){
			Q_scene_monster_areaBean q_scene_monster_areaBean = ManagerPool.dataManager.q_scene_monster_areaContainer.getMap().get(this.getDistributeId());
			if(q_scene_monster_areaBean!=null && q_scene_monster_areaBean.getQ_relive() != 0){
				return q_scene_monster_areaBean.getQ_relive();
			}
		}
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		return model.getQ_revive_time();
	}
	
	@Override
	public int getMaxHp() {
		double value = 0;
		if(this.maxHp==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_maxhp();
		}else{
			value = this.maxHp;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getMaxHp() * aBuff.getOverlay());
				bufPercent += (aBuff.getMaxHpPercent() * aBuff.getOverlay());
			}
		}
		value = ((value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY);

		//变身加成
		if(morph.containsKey(MorphType.HP.getValue())){
			value = value * morph.get(MorphType.HP.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		return (int)value;
	}

	@Override
	public int getMaxMp() {
		double value = 0;
		if(this.maxMp==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_maxmp();
		}else{
			value = this.maxMp;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getMaxMp() * aBuff.getOverlay());
				bufPercent += (aBuff.getMaxMpPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		return (int)value;
	}
	
	@Override
	public int getMaxSp() {
		double value = 0;
		if(this.maxSp==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_maxsp();
		}else{
			value = this.maxSp;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getMaxSp() * aBuff.getOverlay());
				bufPercent += (aBuff.getMaxSp() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		return (int)value;
	}

	public int getSpeed() {
		double value = 0;
		if(this.speed==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_speed();
		}else{
			value = this.speed;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		int totalPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getSpeed() * aBuff.getOverlay());
				bufPercent += (aBuff.getSpeedPercent() * aBuff.getOverlay());
				totalPercent += (aBuff.getTotalSpeedPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		//变身加成
		if(morph.containsKey(MorphType.SPEED.getValue())){
			value = value * morph.get(MorphType.SPEED.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		
		//总体buff加成
		value = value * (Global.MAX_PROBABILITY + totalPercent) / Global.MAX_PROBABILITY;
		
		return (int)value;
	}

	@Override
	public int getAttack() {
		return getAttack(null);
	}
	
	@Override
	public int getAttack(Skill skill){
		//攻击清0 Buff
		if(FighterState.GONGJIWEILING.compare(this.getFightState())) return 0;
		
		double value = 0;
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		if(this.attack==0){
			//获得怪物模型
			value = model.getQ_attack();
		}else{
			value = this.attack;
		}
		
		//技能加成
		if(skill!=null){
			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			value += skillModel.getQ_attack_addition();
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		int equipPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getAttack() * aBuff.getOverlay());
				bufPercent += (aBuff.getAttackPercent() * aBuff.getOverlay());
				equipPercent += (aBuff.getEquipAttackPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;
		
		//装备加成
		int equipValue = model.getQ_equip_attack();
		value = value + (double)equipValue * (Global.MAX_PROBABILITY + equipPercent) / Global.MAX_PROBABILITY;
		
		//变身加成
		if(morph.containsKey(MorphType.ATTACK.getValue())){
			value = value * morph.get(MorphType.ATTACK.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		return (int)value;
	}

	@Override
	public int getDefense() {
		//防御清0 Buff
		if(FighterState.FANGYUWEILING.compare(this.getFightState())) return 0;
				
		double value = 0;
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		if(this.defense==0){
			//获得怪物模型
			value = model.getQ_defense();
		}else{
			value = this.defense;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		int equipPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getDefense() * aBuff.getOverlay());
				bufPercent += (aBuff.getDefensePercent() * aBuff.getOverlay());
				equipPercent += (aBuff.getEquipDefensePercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;
		
		//装备加成
		int equipValue = model.getQ_equip_defense();
		value = value + (double)equipValue * (Global.MAX_PROBABILITY + equipPercent) / Global.MAX_PROBABILITY;
		
		//变身加成
		if(morph.containsKey(MorphType.DEFENSE.getValue())){
			value = value * morph.get(MorphType.DEFENSE.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		return (int)value;
	}

	@Override
	public int getAttackSpeed() {
		double value = 0;
		if(this.attackSpeed==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = 1000 + model.getQ_attack_speed();
		}else{
			value = this.attackSpeed;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		int totalPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getAttackSpeed() * aBuff.getOverlay());
				bufPercent += (aBuff.getAttackSpeedPercent() * aBuff.getOverlay());
				totalPercent += (aBuff.getTotalAttackSpeedPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		//变身加成
		if(morph.containsKey(MorphType.ATTACKSPEED.getValue())){
			value = value * morph.get(MorphType.ATTACKSPEED.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		
		//总体buff加成
		value = value * (Global.MAX_PROBABILITY + totalPercent) / Global.MAX_PROBABILITY;
		
		return (int)value;
	}

	@Override
	public int getLuck() {
		double value = 0;
		if(this.luck==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_luck();
		}else{
			value = this.luck;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getLuck() * aBuff.getOverlay());
				bufPercent += (aBuff.getLuckPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		return (int)value;
	}

	@Override
	public int getCrit() {
		//暴击清0 Buff
		if(FighterState.BAOJIWEILING.compare(this.getFightState())) return 0;
				
		double value = 0;
		if(this.crit==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_crt();
		}else{
			value = this.crit;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getCrit() * aBuff.getOverlay());
				bufPercent += (aBuff.getCritPercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		//变身加成
		if(morph.containsKey(MorphType.CRIT.getValue())){
			value = value * morph.get(MorphType.CRIT.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		return (int)value;
	}

	@Override
	public int getDodge() {
		//闪避清0 Buff
		if(FighterState.SHANBIWEILING.compare(this.getFightState())) return 0;
				
		double value = 0;
		if(this.dodge==0){
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			value = model.getQ_dodge();
		}else{
			value = this.dodge;
		}
		
		//Buff加成
		int bufValue = 0;
		int bufPercent = 0;
		for (int i = 0; i < this.getBuffs().size(); i++) {
			Buff buff = this.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				bufValue += (aBuff.getDodge() * aBuff.getOverlay());
				bufPercent += (aBuff.getDodgePercent() * aBuff.getOverlay());
			}
		}
		value = (value + bufValue) * (Global.MAX_PROBABILITY + bufPercent) / Global.MAX_PROBABILITY;

		//变身加成
		if(morph.containsKey(MorphType.DODGE.getValue())){
			value = value * morph.get(MorphType.DODGE.getValue()).getValue() / Global.MAX_PROBABILITY;
		}
		return (int)value;
	}
	
	public int getLevel(){
		if(this.level > 0){
			return this.level;
		}else{
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			return model.getQ_grade();
		}
	}
	
	/**
	 * 获取无视伤害防御值
	 * @return
	 */
	public int getIgnoreDamage(){
		//获得怪物模型
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		return model.getQ_ignore_damage();
	}
	
	/**
	 * 获取巡逻间隔
	 * @return
	 */
	public int getPatrolTime(){
		if(this.getDistributeType()==1){
			Q_scene_monsterBean q_scene_monsterBean = ManagerPool.dataManager.q_scene_monsterContainer.getMap().get(this.getDistributeId());
			if(q_scene_monsterBean!=null && q_scene_monsterBean.getQ_patrol_time() != 0){
				return q_scene_monsterBean.getQ_patrol_time();
			}
		}else if(this.getDistributeType()==2){
			Q_scene_monster_areaBean q_scene_monster_areaBean = ManagerPool.dataManager.q_scene_monster_areaContainer.getMap().get(this.getDistributeId());
			if(q_scene_monster_areaBean!=null && q_scene_monster_areaBean.getQ_patrol_time() != 0){
				return q_scene_monster_areaBean.getQ_patrol_time();
			}
		}
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		return model.getQ_patrol_time();
	}
	
	/**
	 * 获取巡逻几率
	 * @return
	 */
	public int getPatrolPro(){
		if(this.getDistributeType()==1){
			Q_scene_monsterBean q_scene_monsterBean = ManagerPool.dataManager.q_scene_monsterContainer.getMap().get(this.getDistributeId());
			if(q_scene_monsterBean!=null && q_scene_monsterBean.getQ_patrol_pro() != 0){
				return q_scene_monsterBean.getQ_patrol_pro();
			}
		}else if(this.getDistributeType()==2){
			Q_scene_monster_areaBean q_scene_monster_areaBean = ManagerPool.dataManager.q_scene_monster_areaContainer.getMap().get(this.getDistributeId());
			if(q_scene_monster_areaBean!=null && q_scene_monster_areaBean.getQ_patrol_pro() != 0){
				return q_scene_monster_areaBean.getQ_patrol_pro();
			}
		}
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		return model.getQ_patrol_pro();
	}
	
	/**
	 * 怪物死亡
	 */
	public boolean isDie() {
		return MonsterState.DIE.compare(this.getState()) || MonsterState.DIEWAIT.compare(this.getState());
	}
	
//	/**
//	 * 计算怪物仇恨，过期仇恨清除
//	 */
//	public void countHatreds(){
//		//仇恨过时，但在警戒范围内仇恨度最高的目标
//		Hatred lastOverHatred = null;
//		
//		if(this.getHatreds().size() > 0){
//			//最后攻击有效时间
//			long valid = System.currentTimeMillis() - Global.OVERDUE;
//			//查找怪物所在地图
//			Map map = ManagerPool.mapManager.getMap(this.getServerId(), this.getLine(), this.getMap());
//			if(map==null){
//				return;
//			}
//			//查找怪物模板
//			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
//			if(model==null){
//				return;
//			}
//			//地图阻挡信息
//			Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
//			//怪物所在阻挡格
//			Grid monsterGrid = MapUtils.getGrid(this.getPosition(), blocks);
//			
//			//遍历仇恨列表
//			Iterator<Hatred> iter = this.getHatreds().iterator();
//			while (iter.hasNext()) {
//				Hatred hatred = (Hatred) iter.next();
//				
//				Grid grid = MapUtils.getGrid(hatred.getTarget().getPosition(), blocks);
//				//是否在警戒范围内
//				int distance = MapUtils.countDistance(monsterGrid, grid);
//				
//				//6秒内无再次攻击，移出仇恨列表
//				if(hatred.getLastAttack() < valid){
//					//仇恨过时，但在警戒范围内仇恨度最高的目标，暂时保留在仇恨列表中
//					if(lastOverHatred==null && distance <= model.getQ_eyeshot()) lastOverHatred = hatred;
//					else{
//						//伤害移出
//						this.getDamages().remove(hatred.getTarget().getId());
//						//移出仇恨列表
//						iter.remove();
//					}
//				}
//			}
//		}
//		
//		if(this.getHatreds().size()>1 && lastOverHatred!=null){
//			//伤害移出
//			this.getDamages().remove(lastOverHatred.getTarget().getId());
//			//移出仇恨列表
//			this.getHatreds().remove(lastOverHatred);
//		}
//				
//	}
	
	/**
	 * 最大仇恨
	 * @return
	 */
	public Hatred getMaxHatred() {
		if (getHatreds() != null && getHatreds().size() > 0) {
			Hatred max = getHatreds().get(0);
//			for (Hatred hatred : getHatreds()) {
//				if (hatred.getHatred() > max.getHatred() || ((max.getHatred() == hatred.getHatred()) && (hatred.getFirstAttack() < max.getFirstAttack()))) {
//					max = hatred;
//				}
//			}
			return max;
		}
		return null;
	}
	
	public Fighter getAttackTarget() {
		//查找怪物模板
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		if(model==null){
			return null;
		}
		
		if(model.getQ_script_id() > 0){
			IMonsterAiScript script = (IMonsterAiScript)ManagerPool.scriptManager.getScript(model.getQ_script_id());
			if(script!=null){
				return script.getAttackTarget(this);
			}
		}
		
		return getDefaultAttackTarget();
	}
	
	public Fighter getDefaultAttackTarget() {
		if(this.getHatreds().size() > 0){
			//查找怪物所在地图
			Map map = ManagerPool.mapManager.getMap(this.getServerId(), this.getLine(), this.getMap());
			if(map==null){
				return null;
			}
			//查找怪物模板
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			if(model==null){
				return null;
			}
//			//地图阻挡信息
//			Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
//			//怪物所在阻挡格
//			Grid monsterGrid = MapUtils.getGrid(this.getPosition(), blocks);
			
			//log.error("怪物" + this.getId() + "仇恨对象数量" + this.getHatreds().size());
			//遍历仇恨列表
			Iterator<Hatred> iter = this.getHatreds().iterator();
			while (iter.hasNext()) {
				Hatred hatred = (Hatred) iter.next();
				//不在同一条线
				if(hatred.getTarget().getLine()!=this.getLine()
				//不在同一地图
				|| hatred.getTarget().getMap()!=this.getMap()	
						){
					continue;
				}
				//仇恨对象是玩家
				if(hatred.getTarget() instanceof Player){
					Player player = (Player)hatred.getTarget();
					//游泳状态
					if(PlayerState.SWIM.compare(player.getState())){
						continue;
					}
					//玩家死亡或退出游戏
					if(PlayerState.DIE.compare(player.getState()) || PlayerState.QUIT.compare(player.getState())){
						//log.info("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + hatred.getTarget().getId() + "(" + hatred.getTarget().getPosition() + ")" + "的仇恨，因为死亡或退出");
						continue;
					}
				}
				
//				Grid grid = MapUtils.getGrid(hatred.getTarget().getPosition(), blocks);
//				//是否在警戒范围内
//				int distance = MapUtils.countDistance(monsterGrid, grid);
//				
//				//在警戒范围内设为目标
//				if(distance <= model.getQ_eyeshot()){
				return hatred.getTarget();
//				}
			}
		}
		
		return null;
	}
	
	
	public void countHatreds(){
		//仇恨过时，但在警戒范围内仇恨度最高的目标
		Hatred lastOverHatred = null;
		if(this.getHatreds().size() > 0){
			//最后攻击有效时间
			long valid = System.currentTimeMillis() - Global.OVERDUE;
			//查找怪物所在地图
			Map map = ManagerPool.mapManager.getMap(this.getServerId(), this.getLine(), this.getMap());
			if(map==null){
				return;
			}
			//查找怪物模板
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			if(model==null){
				return;
			}
//			//地图阻挡信息
//			Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
//			//怪物所在阻挡格
//			Grid monsterGrid = MapUtils.getGrid(this.getPosition(), blocks);
			
			//log.error("怪物" + this.getId() + "仇恨对象数量" + this.getHatreds().size());
			//遍历仇恨列表
			Iterator<Hatred> iter = this.getHatreds().iterator();
			while (iter.hasNext()) {
				Hatred hatred = (Hatred) iter.next();
				//不在同一条线
				if(hatred.getTarget().getLine()!=this.getLine()
				//不在同一地图
				|| hatred.getTarget().getMap()!=this.getMap()	
						){
					log.debug("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + hatred.getTarget().getId() + "(" + hatred.getTarget().getPosition() + ")" + "的仇恨，因为换线");
					//伤害移出
					this.getDamages().remove(hatred.getTarget().getId());
					//移出仇恨列表
					ManagerPool.hatredManager.removeHatred(hatred);
					iter.remove();
					continue;
				}
				//仇恨对象是玩家
				if(hatred.getTarget() instanceof Player){
					Player player = (Player)hatred.getTarget();
					
					//游泳状态
					if(PlayerState.SWIM.compare(player.getState())){
						log.debug("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + hatred.getTarget().getId() + "(" + hatred.getTarget().getPosition() + ")" + "的仇恨，因为游泳");
						//伤害移出
						this.getDamages().remove(hatred.getTarget().getId());
						//移出仇恨列表
						ManagerPool.hatredManager.removeHatred(hatred);
						iter.remove();
						continue;
					}
					
					//玩家死亡或退出游戏
					if(PlayerState.DIE.compare(player.getState()) || PlayerState.QUIT.compare(player.getState())){
						log.debug("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + hatred.getTarget().getId() + "(" + hatred.getTarget().getPosition() + ")" + "的仇恨，因为死亡或退出");
						//伤害移出
						this.getDamages().remove(hatred.getTarget().getId());
						//移出仇恨列表
						ManagerPool.hatredManager.removeHatred(hatred);
						iter.remove();
						continue;
					}
				}
				
//				Grid grid = MapUtils.getGrid(hatred.getTarget().getPosition(), blocks);
//				//是否在警戒范围内
//				int distance = MapUtils.countDistance(monsterGrid, grid);
//				
				//6秒内无再次攻击，移出仇恨列表
				if(hatred.getLastAttack() < valid){
					//仇恨过时，但在警戒范围内仇恨度最高的目标，暂时保留在仇恨列表中
					if(lastOverHatred==null /**&& distance <= model.getQ_eyeshot()**/) lastOverHatred = hatred;
					else{
						log.debug("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + hatred.getTarget().getId() + "(" + hatred.getTarget().getPosition() + ")" + "的仇恨");
						//伤害移出
						this.getDamages().remove(hatred.getTarget().getId());
						//移出仇恨列表
						ManagerPool.hatredManager.removeHatred(hatred);
						iter.remove();
					}
					continue;
				}
			}
		}

		if(lastOverHatred!=null && this.getHatreds().size()>1){
			
			//log.info("怪物" + this.getId() + "(" + this.getPosition() + ")移除对" + lastOverHatred.getTarget().getId() + "(" + lastOverHatred.getTarget().getPosition() + ")" + "的仇恨");
			//伤害移出
			this.getDamages().remove(lastOverHatred.getTarget().getId());
			//移出仇恨列表
			ManagerPool.hatredManager.removeHatred(lastOverHatred);
			this.getHatreds().remove(lastOverHatred);
		}
	}
	
	public Skill getSkill() {
		//查找怪物模板
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		if(model==null){
			return null;
		}
		
		if(model.getQ_script_id() > 0){
			IMonsterAiScript script = (IMonsterAiScript)ManagerPool.scriptManager.getScript(model.getQ_script_id());
			if(script!=null){
				return script.getSkill(this);
			}
		}
		
		
		//BOSS技能随机
		if (model.getQ_monster_ai() != null &&model.getQ_monster_ai().length() > 0 ) {
			return getBossskill();
		}
		
		
		return getUseSkill();
	}
	/**
	 * 怪物AI使用技能
	 * @return
	 */
	public Skill getUseSkill() {
		//确定攻击技能
		try{
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			//计算特殊技能
			if(model.getQ_special_skill()!=null && !("").equals(model.getQ_special_skill())){
				//技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;
				//怪物技能
				String[] skills = model.getQ_special_skill().split(Symbol.FENHAO_REG);
				for (int i = 0; i < skills.length; i++) {
					String[] paras = skills[i].split(Symbol.SHUXIAN_REG);
					String[] probabilitys = paras[1].split(Symbol.XIEGANG_REG);
			    	//随机变身概率
			    	int pro = RandomUtils.random(Integer.parseInt(probabilitys[0]));
			    	if(pro < Integer.parseInt(probabilitys[1])){
			    		String[] skillParas = paras[0].split(Symbol.XIAHUAXIAN_REG);
			    		useSkill.setSkillModelId(Integer.parseInt(skillParas[0]));
			    		useSkill.setSkillLevel(Integer.parseInt(skillParas[1]));
			    		return useSkill;
			    	}
				}
			}
			//计算默认技能
			return getDefaultSkill(model);
		}catch (NumberFormatException e) {
			log.error(e, e);
		}
		
		return null;
	}
	
	/**
	 * 获取默认技能
	 * @return
	 */
	public Skill getDefaultSkill(Q_monsterBean model){
		try{
			//计算默认技能
			if(model.getQ_default_skill()!=null && !("").equals(model.getQ_default_skill())){
				String[] skills = model.getQ_default_skill().split(Symbol.XIAHUAXIAN_REG);
				defaultSkill.setSkillModelId(Integer.parseInt(skills[0]));
				defaultSkill.setSkillLevel(Integer.parseInt(skills[1]));
	    		return defaultSkill;
			}
		}catch (NumberFormatException e) {
			log.error(e,e);
		}
		
		return null;
	}
	
	public void cleanHatreds(){
		Iterator<Hatred> iter = this.getHatreds().iterator();
		while (iter.hasNext()) {
			Hatred hatred = (Hatred) iter.next();
			ManagerPool.hatredManager.removeHatred(hatred);
			iter.remove();
		}
	}
	
	/**
	 * 怪物重置
	 */
	public void reset(){
		//清除显示隐藏列表
		this.getShowSet().clear();
		this.getHideSet().clear();
		//BUFF清除
		this.getBuffs().clear();
		//仇恨列表清除
		cleanHatreds();
		//伤害列表清除
		this.getDamages().clear();
		//清除目标
		this.target = null;
		//变身清除
		this.getMorph().clear();
		//参数清除
		this.getParameters().clear();
		//消失时间清0
		this.setDisappearTime(0);
		try{
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
			if(model.getQ_variation()!=null && !("").equals(model.getQ_variation())){
				//变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;
				//怪物变身
				String[] morphs = model.getQ_variation().split(Symbol.FENHAO_REG);
				for (int i = 0; i < morphs.length; i++) {
					String[] paras = morphs[i].split(Symbol.SHUXIAN_REG);
					String[] probabilitys = paras[1].split(Symbol.XIEGANG_REG);
			    	//随机变身概率
			    	int pro = RandomUtils.random(Integer.parseInt(probabilitys[1]));
			    	if(pro < Integer.parseInt(probabilitys[0])){
			    		String[] morphParas = paras[0].split(Symbol.XIAHUAXIAN_REG);
			    		Morph _morph = new Morph();
			    		_morph.setType(1 << (Integer.parseInt(morphParas[0]) - 1));
			    		_morph.setValue(Integer.parseInt(morphParas[1]));
			    		morph.put(_morph.getType(), _morph);
			    	}
				}
			}
		}catch (NumberFormatException e) {
			log.error(e,e);
		}
		
		//设置血量
		this.setHp(this.getMaxHp());
		//设置内力
		this.setMp(this.getMaxMp());
		//设置体力
		this.setSp(this.getMaxSp());
		//设置正常
		this.setState(MonsterState.NORMAL);
		//设置战斗状态正常
		this.setFightState(0);
	}
	
	
	
	
	/**
	 * BOSS技能
	 * 
	 */
	//TODO 调用数据库指定BOSS技能
	public Skill getBossskill(){
		//获得怪物模型
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		try {
			if (model.getQ_monster_ai() != null  && model.getQ_monster_ai().length() > 0 ) {
				String objString = model.getQ_monster_ai();
				net.sf.json.JSONArray skilllarray = net.sf.json.JSONArray.fromObject(objString); 
				for (int i = 0; i < skilllarray.size(); i++) {
					net.sf.json.JSONObject skillHashMap = skilllarray.getJSONObject(i);
					
					Integer type = 0;
					if (skillHashMap.containsKey(ResManager.getInstance().getString("类型"))) {
						type = (Integer) skillHashMap.get(ResManager.getInstance().getString("类型"));
					}
					
					Integer rnd = 0;
					if (skillHashMap.containsKey(ResManager.getInstance().getString("几率"))) {
						 rnd = (Integer) skillHashMap.get(ResManager.getInstance().getString("几率"));
					}
					String say = "";
					if (skillHashMap.containsKey(ResManager.getInstance().getString("说话"))) {
						say = (String) skillHashMap.get(ResManager.getInstance().getString("说话"));
					}
					String warnid ="";
					if (skillHashMap.containsKey(ResManager.getInstance().getString("预警"))) {
						warnid = ""+skillHashMap.get(ResManager.getInstance().getString("预警"));
					}
					String skillid = "";
					if (skillHashMap.containsKey(ResManager.getInstance().getString("触发"))) {
						skillid = (String) skillHashMap.get(ResManager.getInstance().getString("触发"));
					}
					
					boolean attack = false;
					String timername = skillid;   //设置冷却计时器标识用
					//{类型:1,间隔:11,几率:400,说话:"怪物头顶冒字内容",预警:10000,触发:1001,对象:1},
					if (RandomUtils.random(10000) <= rnd  || (this.getNextskillid()!= null && this.getNextskillid().length() > 0)) {
						//获取目标
						Fighter target = getDefaultAttackTarget();

						if (this.getNextskillid() != null && this.getNextskillid().length() > 0) {	//有预设技能，直接释放
							skillid = this.getNextskillid();
							this.setNextskillid("");
							attack = true;
						}else {
							//如果预警技能不为空，而且怪物身上未设定下个技能，先放预警技能
							if(warnid != null && warnid.length() > 2  && this.getNextskillid().length()== 0){
								this.setNextskillid(skillid);
								skillid = warnid;
								attack = true;
							}
							
							if (type == 1) {	//冷却计时触发
								Integer  jiange = (Integer) skillHashMap.get(ResManager.getInstance().getString("间隔"));
								if(!ManagerPool.cooldownManager.isCooldowning(this, CooldownTypes.SKILL, timername)){
									//添加间隔
									ManagerPool.cooldownManager.addCooldown(this, CooldownTypes.SKILL, timername, (long)jiange);
									attack = true;
								}
							}
							else if(type == 2) {//攻击触发 //{类型:2,几率:400,说话:"怪物头顶冒字内容",预警:10000,触发:1001,对象:1}
									attack = true;
							} 
						}

						if (attack) {
							if(isAattack(target,skillid)){
								if (say != null && !say.equals("")) {
									ResMonsterSayMessage msg = new ResMonsterSayMessage();
									msg.setSaycontent(say);
									msg.setMonsterId(this.getId());
									MessageUtil.tell_round_message(this, msg);
								}

								//怪物使用技能
								Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillid);
//								//添加怪物攻击冷却
//								ManagerPool.cooldownManager.addCooldown(this, CooldownTypes.MONSTER_ATTACK, null, (long)2000);
//								//添加怪物攻击动画冷却
//								ManagerPool.cooldownManager.addCooldown(this, CooldownTypes.MONSTER_ACTION, null, (long)500);
								if (skillModel != null) {
									useSkill.setSkillLevel(skillModel.getQ_grade());
									useSkill.setSkillModelId(skillModel.getQ_skillID());
									return useSkill;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}

		return getUseSkill();
	}
	
	
	
	
	/**
	 * 怪物掉血触发技能
	 * 
	 */
	public void getHpChangeSkill(){
		//获得怪物模型
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		try {
			if (model.getQ_monster_ai() != null  && model.getQ_monster_ai().length() > 0 ) {
				if (this.checkHpChange() ==false ) return ;
				String objString = model.getQ_monster_ai();
				net.sf.json.JSONArray skilllarray = net.sf.json.JSONArray.fromObject(objString); 
				
				for (int i = 0; i < skilllarray.size(); i++) {
					net.sf.json.JSONObject skillHashMap = skilllarray.getJSONObject(i);
					Integer  type = (Integer) skillHashMap.get(ResManager.getInstance().getString("类型"));
					//{类型:3,血量:50,几率:333,说话:"内容",预警:10000,触发:1222,对象:1}
					if (type == 3) {//掉血触发
						int  nowhp =acqHppercentage();
						
						Integer sethp = 0;
						if (skillHashMap.containsKey(ResManager.getInstance().getString("血量"))) {
							 sethp = (Integer) skillHashMap.get(ResManager.getInstance().getString("血量"));
						}
						Integer rnd = 0;
						if (skillHashMap.containsKey(ResManager.getInstance().getString("几率"))) {
							 rnd = (Integer) skillHashMap.get(ResManager.getInstance().getString("几率"));
						}
						String say = "";
						if (skillHashMap.containsKey(ResManager.getInstance().getString("说话"))) {
							say = (String) skillHashMap.get(ResManager.getInstance().getString("说话"));
						}
//						String warnid ="";
//						if (skillHashMap.containsKey("预警")) {
//							warnid = ""+skillHashMap.get("预警");
//						}
						String skillid = "";
						if (skillHashMap.containsKey(ResManager.getInstance().getString("触发"))) {
							skillid = (String) skillHashMap.get(ResManager.getInstance().getString("触发"));
						}
						
						if(nowhp == sethp){
							if (RandomUtils.random(10000) <= rnd) {
								//获取目标
								Fighter target = getDefaultAttackTarget();
								if(isAattack(target,skillid)){
									if (say != null && !say.equals("")) {
										ResMonsterSayMessage msg = new ResMonsterSayMessage();
										msg.setSaycontent(say);
										msg.setMonsterId(this.getId());
										MessageUtil.tell_round_message(this, msg);
									}
									//添加怪物攻击冷却
									ManagerPool.cooldownManager.addCooldown(this, CooldownTypes.MONSTER_ATTACK, null, (long)2000);
									//添加怪物攻击动画冷却
									ManagerPool.cooldownManager.addCooldown(this, CooldownTypes.MONSTER_ACTION, null, (long)500);
									//怪物使用技能
									Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillid);

									useSkill.setSkillLevel(skillModel.getQ_grade());
									useSkill.setSkillModelId(skillModel.getQ_skillID());
									ManagerPool.fightManager.monsterAttackPlayer(this, (Player)target, useSkill);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}
	
	}
	
	/**是否可进行攻击
	 * 
	 * @param target
	 * @return
	 */
	public boolean isAattack(Fighter target,String skillid){
		//得到地图
		Map map = ManagerPool.mapManager.getMap(serverId, this.getLine(), this.getMap());
		//地图阻挡信息
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		//无攻击目标
		if(target==null) return false;
		//目标死亡
		if(target.isDie()) return false;
		if (this.isDie()) return false;
		if(target.getMap()!=this.getMap() || target.getLine()!=this.getLine()) return false;
		//定身或睡眠中
		if(FighterState.DINGSHEN.compare(this.getFightState()) || FighterState.SHUIMIAN.compare(this.getFightState())) return false;
		//怪物所在格子
		Grid monsterGrid = MapUtils.getGrid(this.getPosition(), blocks);
		//获取目标所在格子
		Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
		//查找怪物模板
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(this.getModelId());
		if(model==null){
			log.error("Monster Model " + this.getModelId() + " 没有找到！");
			 return false;
		}
		//获取技能范围
		Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillid);
		//是否在攻击范围内
		int distance = MapUtils.countDistance(monsterGrid, grid);
		//在攻击范围内
		if(skillModel != null && distance <= skillModel.getQ_range_limit()){
			return true;
		}
		return false;
	}
	
	
	
	/**检测血量是否变化（十分比）
	 * 
	 * @return
	 */
	public boolean checkHpChange(){
		int monhp = this.getHppercentage();
		int nowhp =  acqHppercentage();
		if (monhp != nowhp ) {
			this.setHppercentage(nowhp);
			return true ;
		}
		return false ;
	}
	
	
	
	/**得到当前怪物血量十分比
	 * 
	 * @return
	 */
	public int acqHppercentage() {
		double nowhp = (double)this.getHp() * 10 / this.getMaxHp();	//十分比
		return (int)nowhp;
	}

	public String getNextskillid() {
		return nextskillid;
	}

	public void setNextskillid(String nextskillid) {
		this.nextskillid = nextskillid;
	}

	@Override
	public void release() {
		//清除显示隐藏列表
		this.getShowSet().clear();
		this.getHideSet().clear();
		//BUFF清除
		this.getBuffs().clear();
		//仇恨列表清除
		cleanHatreds();
		//伤害列表清除
		this.getDamages().clear();
		//清除目标
		this.target = null;
		//变身清除
		this.getMorph().clear();
		//参数清除
		this.getParameters().clear();
		//技能清除
		this.getSkills().clear();
		//设置分布
		this.setDistributeId(0);
		this.setDistributeType(0);
		//消失时间清0
		this.setDisappearTime(0);
	}

	@Override
	public boolean canSee(Player player) {
		IMonsterCanSeeScript script = (IMonsterCanSeeScript)ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_SEE);
		if(script!=null){
			try{
				return script.cansee(player, this);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("monster是否可见脚本不存在！");
		}
		return true;
	}
	
	/**
	 * 是否可以攻击对手
	 * @param fighter
	 * @return
	 */
	public boolean canAttack(Fighter fighter) {
		IMonsterCanAttackScript script = (IMonsterCanAttackScript)ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_ATTACK);
		if(script!=null){
			try{
				return script.canattack(fighter, this);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("monster是否可攻击脚本不存在！");
		}
		return true;
	}
	
	/**
	 * 是否可以被对手攻击
	 * @param fighter
	 * @return
	 */
	public boolean canBeAttack(Fighter fighter) {
		IMonsterCanBeAttackScript script = (IMonsterCanBeAttackScript)ManagerPool.scriptManager.getScript(ScriptEnum.MONSTER_BEATTACK);
		if(script!=null){
			try{
				return script.canbeattack(fighter, this);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("monster是否可被攻击脚本不存在！");
		}
		return true;
	}

	public HashSet<Long> getShowSet() {
		return showSet;
	}

	public void setShowSet(HashSet<Long> showSet) {
		this.showSet = showSet;
	}

	public HashSet<Long> getHideSet() {
		return hideSet;
	}

	public void setHideSet(HashSet<Long> hideSet) {
		this.hideSet = hideSet;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public String getStrvalue() {
		return strvalue;
	}

	public void setStrvalue(String strvalue) {
		this.strvalue = strvalue;
	}

	public int getFriend() {
		return friend;
	}

	public void setFriend(int friend) {
		this.friend = friend;
	}

	public String getFriendPara() {
		return friendPara;
	}

	public void setFriendPara(String friendPara) {
		this.friendPara = friendPara;
	}

	public List<Position> getXunluo() {
		return xunluo;
	}

	public void setXunluo(List<Position> xunluo) {
		this.xunluo = xunluo;
	}

	public long getDisappearTime() {
		return disappearTime;
	}

	public void setDisappearTime(long disappearTime) {
		this.disappearTime = disappearTime;
	}

	public int getGroupmark() {
		return groupmark;
	}

	public void setGroupmark(int groupmark) {
		this.groupmark = groupmark;
	}

}



