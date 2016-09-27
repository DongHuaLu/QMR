package com.game.buff.structs;

import java.util.HashMap;
import java.util.TreeMap;

import com.game.data.bean.Q_buffBean;
import com.game.fight.structs.Fighter;
import com.game.json.JSONserializable;
import com.game.manager.ManagerPool;
import com.game.utils.CommonString;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;

public class AttributeBuff extends Buff implements IBuff {

	private static final long serialVersionUID = -2679127262641871841L;
	//攻击
	protected transient int attack;
	//防御
	protected transient int defense;
	//暴击
	protected transient int crit;
	//闪避
	protected transient int dodge;
	//最大血量
	protected transient int maxHp;
	//最大魔法
	protected transient int maxMp;
	//最大体力
	protected transient int maxSp;
	//攻击速度
	protected transient int attackSpeed;
	//速度
	protected transient int speed;
	//幸运
	protected transient int luck;
	//攻击加成
	protected transient int attackPercent;
	//防御加成
	protected transient int defensePercent;
	//暴击加成
	protected transient int critPercent;
	//闪避加成
	protected transient int dodgePercent;
	//最大血量加成
	protected transient int maxHpPercent;
	//最大魔法加成
	protected transient int maxMpPercent;
	//最大体力加成
	protected transient int maxSpPercent;
	//攻击速度加成
	protected transient int attackSpeedPercent;
	//速度加成
	protected transient int speedPercent;
	//幸运加成
	protected transient int luckPercent;
	//经验加成
	protected transient int expPercent;
	//真气加成
	protected transient int zhenqiPercent;
	//装备攻击加成
	protected transient int equipAttackPercent;
	//装备防御加成
	protected transient int equipDefensePercent;
	//总攻击加成
	protected transient int totalAttackPercent;
	//总防御加成
	protected transient int totalDefensePercent;
	//总暴击加成
	protected transient int totalCritPercent;
	//总闪避加成
	protected transient int totalDodgePercent;
	//总幸运加成
	protected transient int totalLuckPercent;
	//总攻击速度加成
	protected transient int totalAttackSpeedPercent;
	//总速度加成
	protected transient int totalSpeedPercent;
	//总最大血量加成
	protected transient int totalMaxHpPercent;
	//总最大魔法加成
	protected transient int totalMaxMpPercent;
	//总最大体力加成
	protected transient int totalMaxSpPercent;
	//技能加成
	protected transient HashMap<Integer, Integer> skillLevelUp;
	//加成Map
	protected transient HashMap<String, Integer> powers;
	//商城折扣map
	protected transient HashMap<Integer, Integer> obj2reduce;
		
	@Override
	@SuppressWarnings("unchecked")
	public int add(Fighter source, Fighter target) {
		if(powers==null && this.getActionType()==BuffType.POWERUP){
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
			if(buffModel==null) return 0;
			powers = (HashMap<String, Integer>)JSONserializable.toObject(StringUtil.formatToJson(buffModel.getQ_Bonus_skill()), HashMap.class);
		}
		if (this.getActionType() == BuffType.MALLBUYREDUCE) {
			Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
			if(buffModel == null) return 0;
			
			if (obj2reduce == null) {
				obj2reduce = new HashMap<Integer, Integer>();
			} else {
				obj2reduce.clear();
			}
			
			String[] models = buffModel.getQ_Bonus_skill().split(Symbol.SHUXIAN_REG);
			for (int i = 0; i < models.length; i++) {
				String[] tmp = models[i].split(Symbol.MAOHAO_REG);
				if (tmp.length != 2) continue;
				obj2reduce.put(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
			}
			buffModel.getQ_Bonus_skill();
		}
		return 0;
	}

	@Override
	public int action(Fighter source, Fighter target) {
		return 0;
	}

	@Override
	public int remove(Fighter source) {
		return 0;
	}
	
	/**
	 * 获取增加攻击力
	 * @return
	 */
	public int getAttack() {
		switch(this.getActionType()){
			case BuffType.ATTACK:
			case BuffType.ATTACKPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.ATTACK)){
						return (Integer)powers.get(CommonString.ATTACK);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加攻击力比例
	 * @return
	 */
	public int getAttackPercent() {
		switch(this.getActionType()){
			case BuffType.ATTACK:
			case BuffType.ATTACKPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.ATTACKPERCENT)){
						return (Integer)powers.get(CommonString.ATTACKPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	/**
	 * 获取增加防御力
	 * @return
	 */
	public int getDefense() {
		switch(this.getActionType()){
			case BuffType.DEFENSE:
			case BuffType.DEFENSEPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.DEFENSE)){
						return (Integer)powers.get(CommonString.DEFENSE);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加防御力比例
	 * @return
	 */
	public int getDefensePercent() {
		switch(this.getActionType()){
			case BuffType.DEFENSE:
			case BuffType.DEFENSEPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.DEFENSEPERCENT)){
						return (Integer)powers.get(CommonString.DEFENSEPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加暴击
	 * @return
	 */
	public int getCrit() {
		switch(this.getActionType()){
			case BuffType.CRIT:
			case BuffType.CRITPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.CRIT)){
						return (Integer)powers.get(CommonString.CRIT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加暴击比例
	 * @return
	 */
	public int getCritPercent() {
		switch(this.getActionType()){
			case BuffType.CRIT:
			case BuffType.CRITPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.CRITPERCENT)){
						return (Integer)powers.get(CommonString.CRITPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加闪避
	 * @return
	 */
	public int getDodge() {
		switch(this.getActionType()){
			case BuffType.DODGE:
			case BuffType.DODGEPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.DODGE)){
						return (Integer)powers.get(CommonString.DODGE);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加闪避比例
	 * @return
	 */
	public int getDodgePercent() {
		switch(this.getActionType()){
			case BuffType.DODGE:
			case BuffType.DODGEPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.DODGEPERCENT)){
						return (Integer)powers.get(CommonString.DODGEPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大生命
	 * @return
	 */
	public int getMaxHp() {
		switch(this.getActionType()){
			case BuffType.MAXHP:
			case BuffType.REDUCEMAXHP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXHP)){
						return (Integer)powers.get(CommonString.MAXHP);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大生命比例
	 * @return
	 */
	public int getMaxHpPercent() {
		switch(this.getActionType()){
			case BuffType.MAXHP:
			case BuffType.REDUCEMAXHP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXHPPERCENT)){
						return (Integer)powers.get(CommonString.MAXHPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大内力
	 * @return
	 */
	public int getMaxMp() {
		switch(this.getActionType()){
			case BuffType.MAXMP:
			case BuffType.REDUCEMAXMP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXMP)){
						return (Integer)powers.get(CommonString.MAXMP);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大内力比例
	 * @return
	 */
	public int getMaxMpPercent() {
		switch(this.getActionType()){
			case BuffType.MAXMP:
			case BuffType.REDUCEMAXMP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXMPPERCENT)){
						return (Integer)powers.get(CommonString.MAXMPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大体力
	 * @return
	 */
	public int getMaxSp() {
		switch(this.getActionType()){
			case BuffType.MAXSP:
			case BuffType.REDUCEMAXSP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXSP)){
						return (Integer)powers.get(CommonString.MAXSP);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加最大体力比例
	 * @return
	 */
	public int getMaxSpPercent() {
		switch(this.getActionType()){
			case BuffType.MAXSP:
			case BuffType.REDUCEMAXSP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.MAXSPPERCENT)){
						return (Integer)powers.get(CommonString.MAXSPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加攻击速度
	 * @return
	 */
	public int getAttackSpeed() {
		switch(this.getActionType()){
			case BuffType.ATTACKSPEED:
			case BuffType.ATTACKSPEEDPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.ATTACKSPEED)){
						return (Integer)powers.get(CommonString.ATTACKSPEED);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加攻击速度比例
	 * @return
	 */
	public int getAttackSpeedPercent() {
		switch(this.getActionType()){
			case BuffType.ATTACKSPEED:
			case BuffType.ATTACKSPEEDPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.ATTACKSPEEDPERCENT)){
						return (Integer)powers.get(CommonString.ATTACKSPEEDPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加速度
	 * @return
	 */
	public int getSpeed() {
		switch(this.getActionType()){
			case BuffType.SPEED:
			case BuffType.SPEEDPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.SPEED)){
						return (Integer)powers.get(CommonString.SPEED);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加速度比例
	 * @return
	 */
	public int getSpeedPercent() {
		switch(this.getActionType()){
			case BuffType.SPEED:
			case BuffType.SPEEDPERCENT:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.SPEEDPERCENT)){
						return (Integer)powers.get(CommonString.SPEEDPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	/**
	 * 获取增加幸运
	 * @return
	 */
	public int getLuck() {
		switch(this.getActionType()){
			case BuffType.LUCK:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_value() + this.getValue();
		}
		return 0;
	}
	
	/**
	 * 获取增加幸运比例
	 * @return
	 */
	public int getLuckPercent() {
		switch(this.getActionType()){
			case BuffType.LUCK:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
		}
		return 0;
	}
	
	/**
	 * 获取增加经验加成比例
	 * @return
	 */
	public int getExpPercent(){
		switch(this.getActionType()){
			case BuffType.MULEXP:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.EXPPERCENT)){
						return (Integer)powers.get(CommonString.EXPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	/**
	 * 获取增加真气加成比例
	 * @return
	 */
	public int getZhenqiPercent(){
		switch(this.getActionType()){
			case BuffType.MULZHENQI:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.ZHENQIPERCENT)){
						return (Integer)powers.get(CommonString.ZHENQIPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	/**
	 * 获取增加装备攻击加成比例
	 * @return
	 */
	public int getEquipAttackPercent(){
		switch(this.getActionType()){
			case BuffType.EQUIPATTACK:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
		}
		return 0;
	}

	/**
	 * 获取增加装备防御加成比例
	 * @return
	 */
	public int getEquipDefensePercent(){
		switch(this.getActionType()){
			case BuffType.EQUIPDEFENSE:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
		}
		return 0;
	}
	
	/**
	 * 获取总攻击速度加成
	 * @return
	 */
	public int getTotalAttackSpeedPercent(){
		switch(this.getActionType()){
			case BuffType.ATTACKSPEEDRECUDE:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALATTACKSPEEDPERCENT)){
						return (Integer)powers.get(CommonString.TOTALATTACKSPEEDPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	/**
	 * 获取总速度加成
	 * @return
	 */
	public int getTotalSpeedPercent(){
		switch(this.getActionType()){
			case BuffType.SPEEDRECUDE:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff==null) return 0;
				return buff.getQ_effect_ratio() + this.getPercent();
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALSPEEDPERCENT)){
						return (Integer)powers.get(CommonString.TOTALSPEEDPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	
	public int getTotalAttackPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALATTACKPERCENT)){
						return (Integer)powers.get(CommonString.TOTALATTACKPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	public int getTotalDefensePercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALDEFENSEPERCENT)){
						return (Integer)powers.get(CommonString.TOTALDEFENSEPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	public int getTotalCritPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALCRITPERCENT)){
						return (Integer)powers.get(CommonString.TOTALCRITPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	public int getTotalDodgePercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALDODGEPERCENT)){
						return (Integer)powers.get(CommonString.TOTALDODGEPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	public int getTotalLuckPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALLUCKPERCENT)){
						return (Integer)powers.get(CommonString.TOTALLUCKPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	public int getTotalMaxHpPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALMAXHPPERCENT)){
						return (Integer)powers.get(CommonString.TOTALMAXHPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	public int getTotalMaxMpPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALMAXMPPERCENT)){
						return (Integer)powers.get(CommonString.TOTALMAXMPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}
	
	public int getTotalMaxSpPercent() {
		switch(this.getActionType()){
			case BuffType.POWERUP:
				if(powers!=null){
					if(powers.containsKey(CommonString.TOTALMAXSPPERCENT)){
						return (Integer)powers.get(CommonString.TOTALMAXSPPERCENT);
					}
				}
				return 0;
		}
		return 0;
	}

	/**
	 * 获取技能加成
	 * @return
	 */
	public HashMap<Integer, Integer> getSkillLevelUp(){
		HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
		switch(this.getActionType()){
			case BuffType.SKILL:
				Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buff!=null){
					try{
						String s = buff.getQ_Bonus_skill();
						String[] models = s.split(Symbol.DOUHAO_REG);
						for (int i = 0; i < models.length; i++) {
							skills.put(Integer.parseInt(models[i]), buff.getQ_effect_value() + this.getValue());
						}
					}catch (Exception e) {
						log.error(e, e);
					}
				}
		}
		return skills;
	}

	public int getReduceGold(int modelId) {
		if (obj2reduce == null) {
			return 0;
		}
		
		if (obj2reduce.containsKey(modelId)) {
			return obj2reduce.get(modelId) * this.getOverlay();
		}
		return 0;
	}
	
	public HashMap<Integer, Integer> getObj2reduce() {
		return obj2reduce;
	}
}
