package com.game.player.structs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.game.data.bean.Q_buffBean;
import com.game.json.JSONserializable;
import com.game.object.GameObject;
import com.game.utils.CommonString;
import com.game.utils.StringUtil;

public class PlayerAttribute extends GameObject {

	private static final long serialVersionUID = 8232665477417300232L;
	//攻击
	private int attack = 0;
	//防御
	private int defense = 0;
	//暴击
	private int crit = 0;
	//闪避
	private int dodge = 0;
	//最大血量
	private int maxHp = 0;
	//最大魔法
	private int maxMp = 0;
	//最大体力
	private int maxSp = 0;
	//攻击速度
	private int attackSpeed = 0;
	//速度
	private int speed = 0;
	//幸运
	private int luck = 0;
	//无视防御
	private int negDefence;
	//弓箭几率
	private int arrowProbability;
	//攻击
	private int attackPercent = 0;
	//防御
	private int defensePercent = 0;
	//暴击
	private int critPercent = 0;
	//闪避
	private int dodgePercent = 0;
	//最大血量
	private int maxHpPercent = 0;
	//最大魔法
	private int maxMpPercent = 0;
	//最大体力
	private int maxSpPercent = 0;
	//攻击速度
	private int attackSpeedPercent = 0;
	//速度
	private int speedPercent = 0;
	//幸运
	private int luckPercent = 0;
	//攻击
	private int attackTotalPercent = 0;
	//防御
	private int defenseTotalPercent = 0;
	//暴击
	private int critTotalPercent = 0;
	//闪避
	private int dodgeTotalPercent = 0;
	//最大血量
	private int maxHpTotalPercent = 0;
	//最大魔法
	private int maxMpTotalPercent = 0;
	//最大体力
	private int maxSpTotalPercent = 0;
	//攻击速度
	private int attackSpeedTotalPercent = 0;
	//速度
	private int speedTotalPercent = 0;
	//幸运
	private int luckTotalPercent = 0;
	//装备攻击
	private int equipAttackPercent = 0;
	//装备防御
	private int equipDefensePercent = 0;
	//技能等级加成
	private HashMap<Integer, Integer> skillLevelUp = new HashMap<Integer, Integer>();
	//装备防御
	private int expPercent = 0;
	//真气
	private int zhenQiPercent = 0;
		
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
	public int getAttackPercent() {
		return attackPercent;
	}
	public void setAttackPercent(int attackPercent) {
		this.attackPercent = attackPercent;
	}
	public int getDefensePercent() {
		return defensePercent;
	}
	public void setDefensePercent(int defensePercent) {
		this.defensePercent = defensePercent;
	}
	public int getCritPercent() {
		return critPercent;
	}
	public void setCritPercent(int critPercent) {
		this.critPercent = critPercent;
	}
	public int getDodgePercent() {
		return dodgePercent;
	}
	public void setDodgePercent(int dodgePercent) {
		this.dodgePercent = dodgePercent;
	}
	public int getMaxHpPercent() {
		return maxHpPercent;
	}
	public void setMaxHpPercent(int maxHpPercent) {
		this.maxHpPercent = maxHpPercent;
	}
	public int getMaxMpPercent() {
		return maxMpPercent;
	}
	public void setMaxMpPercent(int maxMpPercent) {
		this.maxMpPercent = maxMpPercent;
	}
	public int getMaxSpPercent() {
		return maxSpPercent;
	}
	public void setMaxSpPercent(int maxSpPercent) {
		this.maxSpPercent = maxSpPercent;
	}
	public int getAttackSpeedPercent() {
		return attackSpeedPercent;
	}
	public void setAttackSpeedPercent(int attackSpeedPercent) {
		this.attackSpeedPercent = attackSpeedPercent;
	}
	public int getSpeedPercent() {
		return speedPercent;
	}
	public void setSpeedPercent(int speedPercent) {
		this.speedPercent = speedPercent;
	}
	public int getLuckPercent() {
		return luckPercent;
	}
	public void setLuckPercent(int luckPercent) {
		this.luckPercent = luckPercent;
	}
	public int getAttackTotalPercent() {
		return attackTotalPercent;
	}
	public void setAttackTotalPercent(int attackTotalPercent) {
		this.attackTotalPercent = attackTotalPercent;
	}
	public int getDefenseTotalPercent() {
		return defenseTotalPercent;
	}
	public void setDefenseTotalPercent(int defenseTotalPercent) {
		this.defenseTotalPercent = defenseTotalPercent;
	}
	public int getCritTotalPercent() {
		return critTotalPercent;
	}
	public void setCritTotalPercent(int critTotalPercent) {
		this.critTotalPercent = critTotalPercent;
	}
	public int getDodgeTotalPercent() {
		return dodgeTotalPercent;
	}
	public void setDodgeTotalPercent(int dodgeTotalPercent) {
		this.dodgeTotalPercent = dodgeTotalPercent;
	}
	public int getMaxHpTotalPercent() {
		return maxHpTotalPercent;
	}
	public void setMaxHpTotalPercent(int maxHpTotalPercent) {
		this.maxHpTotalPercent = maxHpTotalPercent;
	}
	public int getMaxMpTotalPercent() {
		return maxMpTotalPercent;
	}
	public void setMaxMpTotalPercent(int maxMpTotalPercent) {
		this.maxMpTotalPercent = maxMpTotalPercent;
	}
	public int getMaxSpTotalPercent() {
		return maxSpTotalPercent;
	}
	public void setMaxSpTotalPercent(int maxSpTotalPercent) {
		this.maxSpTotalPercent = maxSpTotalPercent;
	}
	public int getAttackSpeedTotalPercent() {
		return attackSpeedTotalPercent;
	}
	public void setAttackSpeedTotalPercent(int attackSpeedTotalPercent) {
		this.attackSpeedTotalPercent = attackSpeedTotalPercent;
	}
	public int getSpeedTotalPercent() {
		return speedTotalPercent;
	}
	public void setSpeedTotalPercent(int speedTotalPercent) {
		this.speedTotalPercent = speedTotalPercent;
	}
	public int getLuckTotalPercent() {
		return luckTotalPercent;
	}
	public void setLuckTotalPercent(int luckTotalPercent) {
		this.luckTotalPercent = luckTotalPercent;
	}
	public int getEquipAttackPercent() {
		return equipAttackPercent;
	}
	public void setEquipAttackPercent(int equipAttackPercent) {
		this.equipAttackPercent = equipAttackPercent;
	}
	public int getEquipDefensePercent() {
		return equipDefensePercent;
	}
	public void setEquipDefensePercent(int equipDefensePercent) {
		this.equipDefensePercent = equipDefensePercent;
	}
	public HashMap<Integer, Integer> getSkillLevelUp() {
		return skillLevelUp;
	}
	public void setSkillLevelUp(HashMap<Integer, Integer> skillLevelUp) {
		this.skillLevelUp = skillLevelUp;
	}
	public int getExpPercent() {
		return expPercent;
	}
	public void setExpPercent(int expPercent) {
		this.expPercent = expPercent;
	}
	public int getZhenQiPercent() {
		return zhenQiPercent;
	}
	public void setZhenQiPercent(int zhenQiPercent) {
		this.zhenQiPercent = zhenQiPercent;
	}

	public int getArrowProbability() {
		return arrowProbability;
	}
	public void setArrowProbability(int arrowProbability) {
		this.arrowProbability = arrowProbability;
	}
	public int getNegDefence() {
		return negDefence;
	}
	public void setNegDefence(int negDefence) {
		this.negDefence = negDefence;
	}
	
	public void add(PlayerAttribute other) {
		this.setAttack(this.getAttack() + other.getAttack());
		this.setDefense(this.getDefense() + other.getDefense());
		this.setCrit(this.getCrit() + other.getCrit());
		this.setDodge(this.getDodge() + other.getDodge());
		this.setMaxHp(this.getMaxHp() + other.getMaxHp());
		this.setMaxMp(this.getMaxMp() + other.getMaxMp());
		this.setMaxSp(this.getMaxSp() + other.getMaxSp());
		this.setAttackSpeed(this.getAttackSpeed() + other.getAttackSpeed());
		this.setSpeed(this.getSpeed() + other.getSpeed());
		this.setLuck(this.getLuck() + other.getLuck());
		this.setAttackPercent(this.getAttackPercent() + other.getAttackPercent());
		this.setDefensePercent(this.getDefensePercent() + other.getDefensePercent());
		this.setCritPercent(this.getCritPercent() + other.getCritPercent());
		this.setDodgePercent(this.getDodgePercent() + other.getDodgePercent());
		this.setMaxHpPercent(this.getMaxHpPercent() + other.getMaxHpPercent());
		this.setMaxMpPercent(this.getMaxMpPercent() + other.getMaxMpPercent());
		this.setMaxSpPercent(this.getMaxSpPercent() + other.getMaxSpPercent());
		this.setAttackSpeedPercent(this.getAttackSpeedPercent() + other.getAttackSpeedPercent());
		this.setSpeedPercent(this.getSpeedPercent() + other.getSpeedPercent());
		this.setLuckPercent(this.getLuckPercent() + other.getLuckPercent());
		this.setAttackTotalPercent(this.getAttackSpeedTotalPercent() + other.getAttackSpeedTotalPercent());
		this.setDefenseTotalPercent(this.getDefenseTotalPercent() + other.getDefenseTotalPercent());
		this.setCritTotalPercent(this.getCritTotalPercent() + other.getCritTotalPercent());
		this.setDodgeTotalPercent(this.getDodgeTotalPercent() + other.getDodgeTotalPercent());
		this.setMaxHpTotalPercent(this.getMaxHpTotalPercent() + other.getMaxHpTotalPercent());
		this.setMaxMpTotalPercent(this.getMaxMpTotalPercent() + other.getMaxMpTotalPercent());
		this.setMaxSpTotalPercent(this.getMaxSpTotalPercent() + other.getMaxSpTotalPercent());
		this.setAttackSpeedTotalPercent(this.getAttackSpeedTotalPercent() + other.getAttackSpeedTotalPercent());
		this.setSpeedTotalPercent(this.getSpeedTotalPercent() + other.getSpeedTotalPercent());
		this.setLuckTotalPercent(this.getLuckTotalPercent() + other.getLuckTotalPercent());
		this.setEquipAttackPercent(this.getEquipAttackPercent() + other.getEquipAttackPercent());
		this.setEquipDefensePercent(this.getEquipDefensePercent() + other.getEquipDefensePercent());
		this.setExpPercent(this.getExpPercent() + other.getExpPercent());
		this.setZhenQiPercent(this.getZhenQiPercent() + other.getZhenQiPercent());
		this.setArrowProbability(this.getArrowProbability() + other.getArrowProbability());
		this.setNegDefence(this.getNegDefence() + other.getNegDefence());
		
		Iterator<Entry<Integer, Integer>> iter = other.getSkillLevelUp().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<java.lang.Integer, java.lang.Integer> entry = (Map.Entry<java.lang.Integer, java.lang.Integer>) iter.next();
			Integer level = getSkillLevelUp().get(entry.getKey());
			if (level == null) {
				getSkillLevelUp().put(entry.getKey(), entry.getValue());
			} else {
				getSkillLevelUp().put(entry.getKey(), entry.getValue() + level);
			}
		}
	}
	
	public static PlayerAttribute getPlayerAttribute(Q_buffBean buffBean) {
		PlayerAttribute patt = new PlayerAttribute();
		@SuppressWarnings("unchecked")
		HashMap<String, Integer> powers = (HashMap<String, Integer>) JSONserializable.toObject(StringUtil.formatToJson(buffBean.getQ_Bonus_skill()), HashMap.class);
		if (powers != null) {
			for (Map.Entry<String, Integer> entry : powers.entrySet()) {
				String attString = entry.getKey();
				Integer attInteger = entry.getValue();
				if (attString.equalsIgnoreCase(CommonString.ATTACK)) {
					patt.setAttack(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.ATTACKPERCENT)) {
					patt.setAttackPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.DEFENSE)) {
					patt.setDefense(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.DEFENSEPERCENT)) {
					patt.setDefensePercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.CRIT)) {
					patt.setCrit(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.CRITPERCENT)) {
					patt.setCritPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.DODGE)) {
					patt.setDodge(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.DODGEPERCENT)) {
					patt.setDodgePercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXHP)) {
					patt.setMaxHp(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXHPPERCENT)) {
					patt.setMaxHpPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXMP)) {
					patt.setMaxMp(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXMPPERCENT)) {
					patt.setMaxMpPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXSP)) {
					patt.setMaxSp(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.MAXSPPERCENT)) {
					patt.setMaxSpPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.ATTACKSPEED)) {
					patt.setAttackSpeed(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.ATTACKSPEEDPERCENT)) {
					patt.setAttackSpeedPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.SPEED)) {
					patt.setSpeed(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.SPEEDPERCENT)) {
					patt.setSpeedPercent(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.LUCK)) {
					patt.setLuck(attInteger);
				} else if (attString.equalsIgnoreCase(CommonString.LUCKPERCENT)) {
					patt.setLuckPercent(attInteger);
				}
			}
		}
		return patt;
	}
}
