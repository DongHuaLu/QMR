package com.game.player.manager;

import com.game.arrow.manager.ArrowAttributeCalculator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackAttributeCalculator;
import com.game.buff.manager.BuffAttributeCalculator;
import com.game.collect.manager.CollectAttributeCalculator;
import com.game.data.bean.Q_hiddenweapon_skillBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.equip.manager.EquipAttributeCalculator;
import com.game.fight.structs.FighterState;
import com.game.fightpower.manager.FightPowerManager;
import com.game.gem.manager.GemAttributeCalculator;
import com.game.guild.manager.GuildBannerAttributeCalculator;
import com.game.hiddenweapon.manager.HiddenWeaponAttributeCalculator;
import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.manager.HorseAttributeCalculator;
import com.game.horse.manager.HorseEquipAttributeCalculator;
import com.game.horseweapon.manager.HorseWeaponAttributeCalculator;
import com.game.longyuan.manager.LongYuanAttributeCalculator;
import com.game.manager.ManagerPool;
import com.game.marriage.manager.MarriageAttributeCalculator;
import com.game.pet.attribute.PetAttributeCaluclator;
import com.game.player.bean.PlayerAttributeItem;
import com.game.player.message.ResPlayerAttributeChangeMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.rank.manager.RankAttributeCalculator;
import com.game.realm.manager.RealmAttributeCalculator;
import com.game.skill.structs.Skill;
import com.game.store.manager.StoreAttributeCalculator;
import com.game.structs.Attributes;
import com.game.task.manager.ChapterAdditionAttributeCalculator;
import com.game.team.manager.TeamAttributeCalculator;
import com.game.toplist.manager.TopTitleAttributeCalculator;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

public class PlayerAttributeManager {

	protected Logger log = Logger.getLogger(PlayerAttributeManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static PlayerAttributeManager manager;
	//属性计算器
	private static HashMap<Integer, PlayerAttributeCalculator> calculators = new HashMap<Integer, PlayerAttributeCalculator>();

	private PlayerAttributeManager() {
		//注册基本属性计算器
		registerAttributeCalculator(new BaseAttributeCalculator());
		//注册装备属性计算器
		registerAttributeCalculator(new EquipAttributeCalculator());
		//注册buff属性计算器
		registerAttributeCalculator(new BuffAttributeCalculator());
		//注册背包属性计算器
		registerAttributeCalculator(new BackpackAttributeCalculator());
		//注册仓库属性计算器  
		registerAttributeCalculator(new StoreAttributeCalculator());
		//注册天元心法属性计算器   (6)
		registerAttributeCalculator(new LongYuanAttributeCalculator());
		//注册军衔属性计算器   (7)
		registerAttributeCalculator(new RankAttributeCalculator());
		//坐骑属性计算器   （8）
		registerAttributeCalculator(new HorseAttributeCalculator());
		//注册组队属性计算器   (10)
		registerAttributeCalculator(new TeamAttributeCalculator());
		//注册坐骑装备属性计算器   (11)
		registerAttributeCalculator(new HorseEquipAttributeCalculator());
		//注册宝石属性计算器   (13)
		registerAttributeCalculator(new GemAttributeCalculator());
		//注册帮旗属性计算器   (14)
		registerAttributeCalculator(new GuildBannerAttributeCalculator());
		//注册称号属性计算器   (15)
		registerAttributeCalculator(new TopTitleAttributeCalculator());
		//注册弓箭属性计算器   (16)
		registerAttributeCalculator(new ArrowAttributeCalculator());
		//注册骑战兵器属性计算器   (17)
		registerAttributeCalculator(new HorseWeaponAttributeCalculator());
		//注册境界属性计算器   (18)
		registerAttributeCalculator(new RealmAttributeCalculator());
		//大秦典藏加成  (19)
		registerAttributeCalculator(new CollectAttributeCalculator());
		//注册骑战兵器属性计算器   (20)
		registerAttributeCalculator(new HiddenWeaponAttributeCalculator());
		registerAttributeCalculator(new ChapterAdditionAttributeCalculator());
		//结婚属性计算器（20）
		registerAttributeCalculator(new MarriageAttributeCalculator());
		//注册其他属性计算器   (100)
		registerAttributeCalculator(new OtherAttributeCalculator());
		//美人合体属性
		registerAttributeCalculator(new PetAttributeCaluclator());

		//套装计算器
	}

	private void registerAttributeCalculator(PlayerAttributeCalculator calculator) {
		calculators.put(calculator.getType(), calculator);
	}

	public static PlayerAttributeManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new PlayerAttributeManager();
			}
		}
		return manager;
	}

	/**
	 * 初始化玩家属性
	 *
	 * @param player
	 */
	public void initPlayerAttribute(Player player) {
		Iterator<PlayerAttributeCalculator> iter = calculators.values().iterator();
		while (iter.hasNext()) {
			PlayerAttributeCalculator playerAttributeCalculator = (PlayerAttributeCalculator) iter.next();
			player.getAttributes().put(playerAttributeCalculator.getType(), playerAttributeCalculator.getPlayerAttribute(player));
		}
		//showAttributes(player);
		PlayerInfo info = new PlayerInfo();
		info.copyPlayer(player);
		setAttribute(player, true);
		info.comparePlayer(player, 0, 0);
	}

	/**
	 * 计算玩家属性
	 *
	 * @param player
	 * @param type 类型
	 */
	public void countPlayerAttribute(Player player, int type) {
		countPlayerAttribute(player, type, 0);
	}

	/**
	 * 计算玩家属性
	 *
	 * @param player
	 * @param type 类型
	 */
	public void countPlayerAttribute(Player player, int type, int modelId) {
		PlayerAttributeCalculator playerAttributeCalculator = calculators.get(type);
		if (!player.getAttributes().containsKey(playerAttributeCalculator.getType())) {
			return;
		}
		player.getAttributes().put(playerAttributeCalculator.getType(), playerAttributeCalculator.getPlayerAttribute(player));
		//showAttributes(player);
		PlayerInfo info = new PlayerInfo();
		info.copyPlayer(player);
		setAttribute(player, (type != PlayerAttributeType.BUFF) ? true : false);
		info.comparePlayer(player, type, modelId);
	}

	/**
	 * 获取玩家属性
	 *
	 * @param player
	 * @param type 类型
	 */
	private PlayerAttribute getAttribute(Player player, int type) {
		return player.getAttributes().get(type);
	}

	/**
	 * 设置玩家属性
	 *
	 * @param player
	 */
	private void setAttribute(Player player, boolean bosendFightPower) {
		player.setAttack(countAttack(player));
		player.setDefense(countDefense(player));
		player.setCrit(countCrit(player));
		player.setDodge(countDodge(player));
		player.setMaxHp(countMaxHp(player));
		player.setMaxMp(countMaxMp(player));
		player.setMaxSp(countMaxSp(player));
		player.setAttackSpeed(countAttackSpeed(player));
		player.setSpeed(countSpeed(player));
		player.setLuck(countLuck(player));
		player.setExpMultiple(countExpMultiple(player));
		player.setSkillLevelUp(countSkillLevelUp(player));
		player.setZhenQiMultiple(countZhenQiMultiple(player));
		player.setNegDefence(countnegDefence(player));
		player.setArrowProbability(countarrowProbability(player));
		if (bosendFightPower) {
			FightPowerManager.getInstance().Update(player);
		}
	}

	/**
	 * 攻击力计算
	 *
	 * @param player
	 * @param skill 技能
	 * @return
	 */
	public int countAttack(Player player, Skill skill) {
		//攻击清0 Buff
		if (FighterState.GONGJIWEILING.compare(player.getFightState())) {
			return 0;
		}
		
		//暗器基本技能,只计算暗器伤害
		Q_hiddenweapon_skillBean hiddenweaponSkillBean = ManagerPool.dataManager.q_hiddenweapon_skillContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		if (hiddenweaponSkillBean != null) {
			HiddenWeapon hiddenWeapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
			if (hiddenWeapon == null) {
				return 0;
			}
			PlayerAttribute attr = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
			return attr.getAttack();
		}
		
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);

		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);

		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);
		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//技能加成
		int skillValue = 0;
		if (skill != null) {
			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
//			Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(SkillManager.skillKey(player, skill.getSkillModelId()));
			skillValue = skillModel.getQ_attack_addition();
			//武功境界加成
			/*
			 * TODO 武功境界加成效果暂时取消 Q_skill_realmBean realm =
			 * ManagerPool.skillManager.getSkillRealm(player);
			 * if(realm!=null){ skillValue = skillValue *
			 * (Global.MAX_PROBABILITY + realm.getQ_jiache_ratio())
			 * / Global.MAX_PROBABILITY; }
			 */
		}

		double baseValue = ((double)base.getAttack() + skillValue + buff.getAttack() + taskChapter.getAttack() + tianyuan.getAttack() + banner.getAttack())
				* (10000 + buff.getAttackPercent() + taskChapter.getAttackPercent() + banner.getAttackPercent()) / 10000;
		double equipValue = ((double)equip.getAttack() + horseequip.getAttack() + gem.getAttack())
				* (10000 + buff.getEquipAttackPercent() + taskChapter.getEquipAttackPercent()) / 10000;
		double otherValue = rank.getAttack() + other.getAttack() + horse.getAttack() + pet.getAttack() + arrow.getAttack() + horseWeapon.getAttack() + realm.getAttack() + collect.getAttack() + marriageatt.getAttack() + hiddenWeapon.getAttack();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getAttackTotalPercent()) / 10000);
	}

	/**
	 * 攻击力计算
	 *
	 * @param player
	 * @return
	 */
	private int countAttack(Player player) {
		//攻击清0 Buff
		if (FighterState.GONGJIWEILING.compare(player.getFightState())) {
			return 0;
		}

		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalattack(((collect.getAttack() + base.getAttack() + taskChapter.getAttack() + tianyuan.getAttack() + banner.getAttack())
			* (10000 + taskChapter.getAttackPercent() + banner.getAttackPercent()) / 10000 + (equip.getAttack() + horseequip.getAttack() + gem.getAttack()) * (10000 + taskChapter.getEquipAttackPercent()) / 10000
			+ rank.getAttack() + other.getAttack() + horse.getAttack() + pet.getAttack() + arrow.getAttack() + horseWeapon.getAttack() + realm.getAttack() +  + hiddenWeapon.getAttack())  * (10000) / 10000);

//		return ((base.getAttack() + buff.getAttack() + taskChapter.getAttack() + tianyuan.getAttack() + banner.getAttack())
//			* (10000 + buff.getAttackPercent() + taskChapter.getAttackPercent() + banner.getAttackPercent()) / 10000 + (equip.getAttack() + horseequip.getAttack() + gem.getAttack()) * (10000 + buff.getEquipAttackPercent() + taskChapter.getEquipAttackPercent()) / 10000
//			+ rank.getAttack() + other.getAttack() + horse.getAttack() + pet.getAttack() + arrow.getAttack())  * (10000 + buff.getAttackTotalPercent()) / 10000;
		double baseValue = ((double)base.getAttack() + buff.getAttack() + taskChapter.getAttack() + tianyuan.getAttack() + banner.getAttack())
				* (10000 + buff.getAttackPercent() + taskChapter.getAttackPercent() + banner.getAttackPercent()) / 10000;
		double equipValue = ((double)equip.getAttack() + horseequip.getAttack() + gem.getAttack())
				* (10000 + buff.getEquipAttackPercent() + taskChapter.getEquipAttackPercent()) / 10000;
		double otherValue = rank.getAttack() + other.getAttack() + horse.getAttack() + pet.getAttack() + arrow.getAttack() + horseWeapon.getAttack() + realm.getAttack() + collect.getAttack() + marriageatt.getAttack() + hiddenWeapon.getAttack();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getAttackTotalPercent()) / 10000);
	}

	/**
	 * 防御力计算
	 *
	 * @param player
	 * @return
	 */
	private int countDefense(Player player) {
		//防御清0 Buff
		if (FighterState.FANGYUWEILING.compare(player.getFightState())) {
			return 0;
		}

		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCaldefense(((base.getDefense() + collect.getDefense() + taskChapter.getDefense() + tianyuan.getDefense() + banner.getDefense())
			* (10000 + taskChapter.getDefensePercent() + banner.getDefensePercent()) / 10000 + (equip.getDefense() + horseequip.getDefense() + gem.getDefense()) * (10000 + taskChapter.getEquipDefensePercent()) / 10000
			+ rank.getDefense() + other.getDefense() + horse.getDefense() + pet.getDefense() + arrow.getDefense() + horseWeapon.getDefense() + realm.getDefense() + marriageatt.getDefense() + hiddenWeapon.getDefense())  * (10000) / 10000);
		
		double baseValue = ((double)base.getDefense() + buff.getDefense() + taskChapter.getDefense() + tianyuan.getDefense() + banner.getDefense())
				* (10000 + buff.getDefensePercent() + taskChapter.getDefensePercent() + banner.getDefensePercent()) / 10000;
		double equipValue = ((double)equip.getDefense() + horseequip.getDefense() + gem.getDefense())
				* (10000 + buff.getEquipDefensePercent() + taskChapter.getEquipDefensePercent()) / 10000;
		double otherValue = rank.getDefense() + other.getDefense() + horse.getDefense() + pet.getDefense() + arrow.getDefense() + horseWeapon.getDefense() + realm.getDefense() + collect.getDefense() + marriageatt.getDefense() + hiddenWeapon.getDefense();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getDefenseTotalPercent()) / 10000);
		
//		return ((base.getDefense() + buff.getDefense() + taskChapter.getDefense() + tianyuan.getDefense() + banner.getDefense())
//			* (10000 + buff.getDefensePercent() + taskChapter.getDefensePercent() + banner.getDefensePercent()) / 10000 + (equip.getDefense() + horseequip.getDefense() + gem.getDefense()) * (10000 + buff.getEquipDefensePercent() + taskChapter.getEquipDefensePercent()) / 10000
//			+ rank.getDefense() + other.getDefense() + horse.getDefense() + pet.getDefense() + arrow.getDefense())  * (10000 + buff.getDefenseTotalPercent()) / 10000;
	}

	/**
	 * 暴击计算
	 *
	 * @param player
	 * @return
	 */
	private int countCrit(Player player) {
		//暴击清0 Buff
		if (FighterState.BAOJIWEILING.compare(player.getFightState())) {
			return 0;
		}

		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalcrit(((base.getCrit() + taskChapter.getCrit() + tianyuan.getCrit() + banner.getCrit())
			* (10000 + taskChapter.getCrit() + banner.getCritPercent()) / 10000 + equip.getCrit() + rank.getCrit() + other.getCrit()
			+ horse.getCrit() + horseequip.getCrit() + collect.getCrit() + gem.getCrit() + pet.getCrit() + arrow.getCrit() + horseWeapon.getCrit() + realm.getCrit() + marriageatt.getCrit() + hiddenWeapon.getCrit()) * (10000) / 10000);

		double baseValue = ((double)base.getCrit() + buff.getCrit() + taskChapter.getCrit() + tianyuan.getCrit() + banner.getCrit())
				* (10000 + buff.getCritPercent() + taskChapter.getCritPercent() + banner.getCritPercent()) / 10000;
		double equipValue = ((double)equip.getCrit() + horseequip.getCrit() + gem.getCrit());
		double otherValue = rank.getCrit() + other.getCrit() + horse.getCrit() + pet.getCrit() + arrow.getCrit() + horseWeapon.getCrit() + realm.getCrit() + collect.getCrit() + marriageatt.getCrit() + hiddenWeapon.getCrit();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getCritTotalPercent()) / 10000);
		
//		return ((base.getCrit() + buff.getCrit() + taskChapter.getDefense() + tianyuan.getCrit() + banner.getCrit())
//			* (10000 + buff.getCritPercent() + taskChapter.getDefense() + banner.getCritPercent()) / 10000 + equip.getCrit() + rank.getCrit() + other.getCrit()
//			+ horse.getCrit() + horseequip.getCrit() + gem.getCrit() + pet.getCrit() + arrow.getCrit()) * (10000 + buff.getCritTotalPercent()) / 10000;
	}

	/**
	 * 闪避计算
	 *
	 * @param player
	 * @return
	 */
	private int countDodge(Player player) {
		//闪避清0 Buff
		if (FighterState.SHANBIWEILING.compare(player.getFightState())) {
			return 0;
		}

		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//龙元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		
		//计算战斗力用
		player.setCaldodge(((base.getDodge() + taskChapter.getDodge() + tianyuan.getDodge() + banner.getDodge())
			* (10000 + taskChapter.getDodgePercent() + banner.getDodgePercent()) / 10000 + equip.getDodge() + rank.getDodge()
			+ horse.getDodge() + other.getDodge() + horseequip.getDodge() + gem.getDodge() + pet.getDodge() + arrow.getDodge() + horseWeapon.getDodge() + realm.getDodge() + collect.getDodge() + marriageatt.getDodge() + hiddenWeapon.getDodge()) * (10000) / 10000);

		double baseValue = ((double)base.getDodge() + buff.getDodge() + taskChapter.getDodge() + tianyuan.getDodge() + banner.getDodge())
				* (10000 + buff.getDodgePercent() + taskChapter.getDodgePercent() + banner.getDodgePercent()) / 10000;
		double equipValue = ((double)equip.getDodge() + horseequip.getDodge() + gem.getDodge());
		double otherValue = rank.getDodge() + other.getDodge() + horse.getDodge() + pet.getDodge() + arrow.getDodge() + horseWeapon.getDodge() + realm.getDodge() + collect.getDodge() + marriageatt.getDodge() + hiddenWeapon.getDodge();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getDodgeTotalPercent()) / 10000);
		
//		return ((base.getDodge() + buff.getDodge() + taskChapter.getDodge() + tianyuan.getDodge() + banner.getDodge())
//			* (10000 + buff.getDodgePercent() + taskChapter.getDodgePercent() + banner.getDodgePercent()) / 10000 + equip.getDodge() + rank.getDodge()
//			+ horse.getDodge() + other.getDodge() + horseequip.getDodge() + gem.getDodge() + pet.getDodge() + arrow.getDodge()) * (10000 + buff.getDodgeTotalPercent()) / 10000;
	}

	private int countMaxHp(Player player) {
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//包裹加成
		PlayerAttribute backpack = getAttribute(player, PlayerAttributeType.BACKPACK);
		//仓库加成
		PlayerAttribute store = getAttribute(player, PlayerAttributeType.STORE);
		//龙元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//组队加成
		PlayerAttribute team = getAttribute(player, PlayerAttributeType.TEAM);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		
		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalmaxHp((base.getMaxHp() + taskChapter.getMaxHp() + tianyuan.getMaxHp() + banner.getMaxHp())
			* (10000 + taskChapter.getMaxHpPercent() + banner.getMaxHpPercent()) / 10000 + equip.getMaxHp() + backpack.getMaxHp() + store.getMaxHp()
			+ horse.getMaxHp() + other.getMaxHp() + collect.getMaxHp() + horseequip.getMaxHp() + gem.getMaxHp() + pet.getMaxHp() + arrow.getMaxHp() + rank.getMaxHp() + horseWeapon.getMaxHp() + realm.getMaxHp() + marriageatt.getMaxHp() + hiddenWeapon.getMaxHp());
		
		double baseValue = ((double)base.getMaxHp() + buff.getMaxHp() + taskChapter.getMaxHp() + tianyuan.getMaxHp() + banner.getMaxHp())
				* (10000 + buff.getMaxHpPercent() + taskChapter.getMaxHpPercent() + banner.getMaxHpPercent()) / 10000;
		double equipValue = ((double)equip.getMaxHp() + horseequip.getMaxHp() + gem.getMaxHp());
		double otherValue = rank.getMaxHp() + other.getMaxHp() + horse.getMaxHp() + pet.getMaxHp() + arrow.getMaxHp() + backpack.getMaxHp() + store.getMaxHp() + team.getMaxHp() + horseWeapon.getMaxHp() + realm.getMaxHp() + collect.getMaxHp() + marriageatt.getMaxHp() + hiddenWeapon.getMaxHp();
		int maxhp =  (int)((baseValue + equipValue + otherValue) * (10000 + buff.getMaxHpTotalPercent()) / 10000);
		if(maxhp <= 0) maxhp = 1;
		return maxhp;
//		return (base.getMaxHp() + buff.getMaxHp() + taskChapter.getMaxHp() + tianyuan.getMaxHp() + banner.getMaxHp())
//			* (10000 + buff.getMaxHpPercent() + taskChapter.getMaxHpPercent() + banner.getMaxHpPercent()) / 10000 + equip.getMaxHp() + backpack.getMaxHp() + store.getMaxHp()
//			+ team.getMaxHp() + horse.getMaxHp() + other.getMaxHp() + horseequip.getMaxHp() + gem.getMaxHp() + pet.getMaxHp() + arrow.getMaxHp()+ rank.getMaxHp();
	}

	private int countMaxMp(Player player) {
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//龙元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalmaxMp((base.getMaxMp() + taskChapter.getMaxMp() + tianyuan.getMaxMp() + banner.getMaxMp())
			* (10000 + banner.getMaxMpPercent()) / 10000 + equip.getMaxMp() + other.getMaxMp()
			+ horse.getMaxMp() + horseequip.getMaxMp() + gem.getMaxMp() + collect.getMaxMp() + pet.getMaxMp() + arrow.getMaxMp() + rank.getMaxMp() + horseWeapon.getMaxMp() + realm.getMaxMp() + marriageatt.getMaxMp() + hiddenWeapon.getMaxMp());
		
		double baseValue = ((double)base.getMaxMp() + buff.getMaxMp() + taskChapter.getMaxMp() + tianyuan.getMaxMp() + banner.getMaxMp())
				* (10000 + buff.getMaxMpPercent() + taskChapter.getMaxMpPercent() + banner.getMaxMpPercent()) / 10000;
		double equipValue = ((double)equip.getMaxMp() + horseequip.getMaxMp() + gem.getMaxMp());
		double otherValue = rank.getMaxMp() + other.getMaxMp() + horse.getMaxMp() + pet.getMaxMp() + arrow.getMaxMp() + horseWeapon.getMaxMp() + realm.getMaxMp() + collect.getMaxMp() + marriageatt.getMaxMp() + hiddenWeapon.getMaxMp();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getMaxMpTotalPercent()) / 10000);
		
//		return (base.getMaxMp() + buff.getMaxMp() + taskChapter.getMaxMp() + tianyuan.getMaxMp() + banner.getMaxMp())
//			* (10000 + buff.getMaxMpPercent() + banner.getMaxMpPercent()) / 10000 + equip.getMaxMp() + other.getMaxMp()
//			+ horse.getMaxMp() + horseequip.getMaxMp() + gem.getMaxMp() + pet.getMaxMp() + arrow.getMaxMp() + rank.getMaxMp();
	}

	private int countMaxSp(Player player) {
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalmaxSp((base.getMaxSp() + taskChapter.getMaxSp() + tianyuan.getMaxSp() + banner.getMaxSp())
			* (10000 + taskChapter.getMaxSpPercent() + banner.getMaxSpPercent()) / 10000 + equip.getMaxSp() + other.getMaxSp()
			+ horse.getMaxSp() + horseequip.getMaxSp() + collect.getMaxSp() + gem.getMaxSp() + pet.getMaxSp() + arrow.getMaxSp() + rank.getMaxSp() + horseWeapon.getMaxSp() + realm.getMaxSp() + marriageatt.getMaxSp() + hiddenWeapon.getMaxSp());

		double baseValue = ((double)base.getMaxSp() + buff.getMaxSp() + taskChapter.getMaxSp() + tianyuan.getMaxSp() + banner.getMaxSp())
				* (10000 + buff.getMaxSpPercent() + taskChapter.getMaxSpPercent() + banner.getMaxSpPercent()) / 10000;
		double equipValue = ((double)equip.getMaxSp() + horseequip.getMaxSp() + gem.getMaxSp());
		double otherValue = rank.getMaxSp() + other.getMaxSp() + horse.getMaxSp() + pet.getMaxSp() + arrow.getMaxSp() + horseWeapon.getMaxSp() + realm.getMaxSp() + collect.getMaxSp() + marriageatt.getMaxSp() + hiddenWeapon.getMaxSp();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getMaxSpTotalPercent()) / 10000);
		
//		return (base.getMaxSp() + buff.getMaxSp() + taskChapter.getMaxSp() + tianyuan.getMaxSp() + banner.getMaxSp())
//			* (10000 + buff.getMaxSpPercent() + taskChapter.getMaxSpPercent() + banner.getMaxSpPercent()) / 10000 + equip.getMaxSp() + other.getMaxSp()
//			+ horse.getMaxSp() + horseequip.getMaxSp() + gem.getMaxSp() + pet.getMaxSp() + arrow.getMaxSp() + rank.getMaxSp();
	}

	private int countAttackSpeed(Player player) {
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect= getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		//计算战斗力用
		player.setCalattackSpeed(((base.getAttackSpeed() + taskChapter.getAttackSpeed() + tianyuan.getAttackSpeed() + banner.getAttackSpeed())
			* (10000 + taskChapter.getAttackSpeedPercent() + banner.getAttackSpeedPercent()) / 10000 + (equip.getAttackSpeed() + horseequip.getAttackSpeed() + gem.getAttackSpeed())) * (10000) / 10000
			+ rank.getAttackSpeed() + other.getAttackSpeed() + collect.getAttackSpeed() + horse.getAttackSpeed() + pet.getAttackSpeed() + arrow.getAttackSpeed() + horseWeapon.getAttackSpeed()+ realm.getAttackSpeed() + marriageatt.getAttackSpeed() + hiddenWeapon.getAttackSpeed());

		double baseValue = ((double)base.getAttackSpeed() + buff.getAttackSpeed() + taskChapter.getAttackSpeed() + tianyuan.getAttackSpeed() + banner.getAttackSpeed())
				* (10000 + buff.getAttackSpeedPercent() + taskChapter.getAttackSpeedPercent() + banner.getAttackSpeedPercent()) / 10000;
		double equipValue = ((double)equip.getAttackSpeed() + horseequip.getAttackSpeed() + gem.getAttackSpeed());
		double otherValue = rank.getAttackSpeed() + other.getAttackSpeed() + horse.getAttackSpeed() + pet.getAttackSpeed() + arrow.getAttackSpeed() + horseWeapon.getAttackSpeed() + realm.getAttackSpeed() + collect.getAttackSpeed() + marriageatt.getAttackSpeed() + hiddenWeapon.getAttackSpeed();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getAttackSpeedTotalPercent()) / 10000);
		
//		return ((base.getAttackSpeed() + buff.getAttackSpeed() + taskChapter.getAttackSpeed() + tianyuan.getAttackSpeed() + banner.getAttackSpeed())
//			* (10000 + buff.getAttackSpeedPercent() + taskChapter.getAttackSpeedPercent() + banner.getAttackSpeedPercent()) / 10000 + (equip.getAttackSpeed() + horseequip.getAttackSpeed() + gem.getAttackSpeed())) * (10000 + buff.getAttackSpeedTotalPercent()) / 10000
//			+ rank.getAttackSpeed() + other.getAttackSpeed() + horse.getAttackSpeed() + pet.getAttackSpeed() + arrow.getAttackSpeed();
	}

	private int countSpeed(Player player) {
		//获取基本加成
		PlayerAttribute base = getAttribute(player, PlayerAttributeType.BASE);
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		
		
		//计算战斗力用
		player.setCalspeed(((base.getSpeed() + taskChapter.getSpeed() + tianyuan.getSpeed() + banner.getSpeed())
			* (10000 + taskChapter.getSpeedPercent() + banner.getSpeedPercent()) / 10000 + (equip.getSpeed() + horseequip.getSpeed() + gem.getSpeed())) * (10000 + taskChapter.getSpeedPercent()) / 10000 + other.getSpeed()
			+ rank.getSpeed() + horse.getSpeed() + collect.getSpeed() + pet.getSpeed() + arrow.getSpeed() + horseWeapon.getSpeed() + realm.getSpeed() + marriageatt.getSpeed());
		
		double baseValue = ((double)base.getSpeed() + buff.getSpeed() + taskChapter.getSpeed() + tianyuan.getSpeed() + banner.getSpeed())
				* (10000 + buff.getSpeedPercent() + taskChapter.getSpeedPercent() + banner.getSpeedPercent()) / 10000;
		double equipValue = ((double)equip.getSpeed() + horseequip.getSpeed() + gem.getSpeed());
		double otherValue = rank.getSpeed() + other.getSpeed() + horse.getSpeed() + pet.getSpeed() + arrow.getSpeed() + horseWeapon.getSpeed() + realm.getSpeed() + collect.getSpeed() + marriageatt.getSpeed();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getSpeedTotalPercent()) / 10000);
		
//		return ((base.getSpeed() + buff.getSpeed() + taskChapter.getSpeed() + tianyuan.getSpeed() + banner.getSpeed())
//			* (10000 + buff.getSpeedPercent() + taskChapter.getSpeedPercent() + banner.getSpeedPercent()) / 10000 + (equip.getSpeed() + horseequip.getSpeed() + gem.getSpeed())) * (10000 + buff.getSpeedTotalPercent() + taskChapter.getSpeedPercent()) / 10000 + other.getSpeed()
//			+ rank.getSpeed() + horse.getSpeed() + pet.getSpeed() + arrow.getSpeed();
	}

	private int countLuck(Player player) {
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//大秦典藏加成
		PlayerAttribute collect = getAttribute(player, PlayerAttributeType.COLLECT);
		//装备加成
		PlayerAttribute equip = getAttribute(player, PlayerAttributeType.EQUIP);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);
		//宝石属性加成
		PlayerAttribute gem = getAttribute(player, PlayerAttributeType.GEM);
		//帮旗属性加成
		PlayerAttribute banner = getAttribute(player, PlayerAttributeType.GUILDBANNER);
		//弓箭属性加成
		PlayerAttribute arrow = getAttribute(player, PlayerAttributeType.ARROW);
		//军衔属性加成
		PlayerAttribute rank = getAttribute(player, PlayerAttributeType.RANK);
		//坐骑加成
		PlayerAttribute horse = getAttribute(player, PlayerAttributeType.HORSE);
		//坐骑装备加成
		PlayerAttribute horseequip = getAttribute(player, PlayerAttributeType.HORSE_EQUIP);
		//任务章节加成
		PlayerAttribute taskChapter = getAttribute(player, PlayerAttributeType.TASK_CHATPER);
		//其他加成
		PlayerAttribute other = getAttribute(player, PlayerAttributeType.OTHER);

		PlayerAttribute pet = getAttribute(player, PlayerAttributeType.PET);
		//境界加成
		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);
		PlayerAttribute horseWeapon = getAttribute(player, PlayerAttributeType.HORSE_WEAPON);
		PlayerAttribute hiddenWeapon = getAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
		//结婚属性加成
		PlayerAttribute marriageatt = getAttribute(player, PlayerAttributeType.MARRIAGE);
		//计算战斗力用
		player.setCalluck(((taskChapter.getLuck() + tianyuan.getLuck() + banner.getLuck())
			* (10000 + taskChapter.getLuck() + collect.getLuck() + banner.getLuckPercent()) / 10000 + equip.getLuck() + other.getLuck() + gem.getLuck() + pet.getLuck() + arrow.getLuck() + rank.getLuck() + horseWeapon.getLuck() + realm.getLuck() + marriageatt.getLuck() + hiddenWeapon.getLuck())  * (10000) / 10000);

		double baseValue = ((double)buff.getLuck() + taskChapter.getLuck() + tianyuan.getLuck() + banner.getLuck())
				* (10000 + buff.getLuckPercent() + taskChapter.getLuckPercent() + banner.getLuckPercent()) / 10000;
		double equipValue = ((double)equip.getLuck() + horseequip.getLuck() + gem.getLuck());
		double otherValue = rank.getLuck() + other.getLuck() + horse.getLuck() + pet.getLuck() + arrow.getLuck() + horseWeapon.getLuck() + realm.getLuck() + collect.getLuck() + marriageatt.getLuck() + hiddenWeapon.getLuck();
		return (int)((baseValue + equipValue + otherValue) * (10000 + buff.getLuckTotalPercent()) / 10000);
		
//		return ((buff.getLuck() + taskChapter.getLuck() + tianyuan.getLuck() + banner.getLuck())
//			* (10000 + buff.getLuckPercent() + taskChapter.getLuck() + banner.getLuckPercent()) / 10000 + equip.getLuck() + other.getLuck() + gem.getLuck() + pet.getLuck() + arrow.getLuck() + rank.getLuck())  * (10000 + buff.getLuckTotalPercent()) / 10000;
	}

	
	
	/**计算无视防御
	 * 
	 * @param player
	 * @return
	 */

	private int countnegDefence(Player player) {

		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);

		return realm.getNegDefence();
	}
	
	
	/**计算弓箭几率
	 * 
	 * @param player
	 * @return
	 */

	private int countarrowProbability(Player player) {

		PlayerAttribute realm = getAttribute(player, PlayerAttributeType.REALM);

		return realm.getArrowProbability();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 技能加成计算
	 *
	 * @param player
	 * @return
	 */
	public HashMap<Integer, Integer> countSkillLevelUp(Player player) {
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		//天元心法加成
		PlayerAttribute tianyuan = getAttribute(player, PlayerAttributeType.LONGYUAN);

		HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
		skills.putAll(buff.getSkillLevelUp());

		Iterator<Entry<Integer, Integer>> iter = tianyuan.getSkillLevelUp().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<java.lang.Integer, java.lang.Integer> entry = (Map.Entry<java.lang.Integer, java.lang.Integer>) iter.next();
			if (skills.containsKey(entry.getKey())) {
				skills.put(entry.getKey(), skills.get(entry.getKey()) + entry.getValue());
			} else {
				skills.put(entry.getKey(), entry.getValue());
			}
		}

		return skills;
	}

	/**
	 * 经验加成计算
	 *
	 * @param player
	 * @return
	 */
	private int countExpMultiple(Player player) {
		int value = Global.MAX_PROBABILITY;
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);

		return value + buff.getExpPercent();
	}

	private int countZhenQiMultiple(Player player) {
		int value = Global.MAX_PROBABILITY;
		//Buff加成
		PlayerAttribute buff = getAttribute(player, PlayerAttributeType.BUFF);
		return value + buff.getZhenQiPercent();
	}

//	private void showAttributes(Player player){
//		Iterator<Integer> atts = player.getAttributes().keySet().iterator();
//		while (atts.hasNext()) {
//			Integer playerAttribute = (Integer) atts.next();
//			log.info(player.getId() + ":" + playerAttribute);
//		}
//	}
	private class PlayerInfo {
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
		HashMap<Integer, Integer> skillLevelUp = new HashMap<Integer, Integer>();

		public void copyPlayer(Player player) {
			this.setAttack(player.getAttack());
			this.setDefense(player.getDefense());
			this.setCrit(player.getCrit());
			this.setDodge(player.getDodge());
			this.setMaxHp(player.getMaxHp());
			this.setMaxMp(player.getMaxMp());
			this.setMaxSp(player.getMaxSp());
			this.setAttackSpeed(player.getAttackSpeed());
			this.setSpeed(player.getSpeed());
			this.setLuck(player.getLuck());
			this.getSkillLevelUp().putAll(player.getSkillLevelUp());
		}

		public void comparePlayer(Player player, int type, int modelId) {
			List<PlayerAttributeItem> list = new ArrayList<PlayerAttributeItem>();
			if (this.getAttack() != player.getAttack()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.ATTACK.getValue());
				attribute.setValue(player.getAttack());
				list.add(attribute);
				log.debug("Player " + player.getId() + " attack " + this.getAttack() + "-->" + player.getAttack());
			}
			if (this.getDefense() != player.getDefense()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.DEFENSE.getValue());
				attribute.setValue(player.getDefense());
				list.add(attribute);
				log.debug("Player " + player.getId() + " defense " + this.getDefense() + "-->" + player.getDefense());
			}
			if (this.getCrit() != player.getCrit()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.CRIT.getValue());
				attribute.setValue(player.getCrit());
				list.add(attribute);
				log.debug("Player " + player.getId() + " crit " + this.getCrit() + "-->" + player.getCrit());
			}
			if (this.getDodge() != player.getDodge()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.DODGE.getValue());
				attribute.setValue(player.getDodge());
				list.add(attribute);
				log.debug("Player " + player.getId() + " dodge " + this.getDodge() + "-->" + player.getDodge());
			}
			if (this.getMaxHp() != player.getMaxHp()) {
				ManagerPool.playerManager.onMaxHpChange(player);
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.MAXHP.getValue());
				attribute.setValue(player.getMaxHp());
				list.add(attribute);
				log.debug("Player " + player.getId() + " maxHp " + this.getMaxHp() + "-->" + player.getMaxHp());
			}
			if (this.getMaxMp() != player.getMaxMp()) {
				ManagerPool.playerManager.onMaxMpChange(player);
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.MAXMP.getValue());
				attribute.setValue(player.getMaxMp());
				list.add(attribute);
				log.debug("Player " + player.getId() + " maxMp " + this.getMaxMp() + "-->" + player.getMaxMp());
			}
			if (this.getMaxSp() != player.getMaxSp()) {
				ManagerPool.playerManager.onMaxSpChange(player);
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.MAXSP.getValue());
				attribute.setValue(player.getMaxSp());
				list.add(attribute);
				log.debug("Player " + player.getId() + " maxSp " + this.getMaxSp() + "-->" + player.getMaxSp());
			}
			if (this.getAttackSpeed() != player.getAttackSpeed()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.ATTACKSPEED.getValue());
				attribute.setValue(player.getAttackSpeed());
				list.add(attribute);
				log.debug("Player " + player.getId() + " attackSpeed " + this.getAttackSpeed() + "-->" + player.getAttackSpeed());
			}
			if (this.getSpeed() != player.getSpeed()) {
				ManagerPool.playerManager.onSpeedChange(player);
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.SPEED.getValue());
				attribute.setValue(player.getSpeed());
				list.add(attribute);
				log.debug("Player " + player.getId() + " speed " + this.getSpeed() + "-->" + player.getSpeed());
			}
			if (this.getLuck() != player.getLuck()) {
				PlayerAttributeItem attribute = new PlayerAttributeItem();
				attribute.setType(Attributes.LUCK.getValue());
				attribute.setValue(player.getLuck());
				list.add(attribute);
				log.debug("Player " + player.getId() + " luck " + this.getLuck() + "-->" + player.getLuck());
			}
			ResPlayerAttributeChangeMessage msg = new ResPlayerAttributeChangeMessage();
			msg.setAttributeChangeReason(type);
			msg.setModelId(modelId);
			msg.setAttributeChangeList(list);
			MessageUtil.tell_player_message(player, msg);

			HashSet<Integer> keys = new HashSet<Integer>();
			keys.addAll(this.getSkillLevelUp().keySet());
			keys.addAll(player.getSkillLevelUp().keySet());
			Iterator<Integer> iter = keys.iterator();
			while (iter.hasNext()) {
				Integer key = (Integer) iter.next();
				if (key == -1) {
					int level1 = 0;
					if (this.getSkillLevelUp().containsKey(-1)) {
						level1 = this.getSkillLevelUp().get(-1);
					}
					int level2 = 0;
					if (player.getSkillLevelUp().containsKey(-1)) {
						level2 = player.getSkillLevelUp().get(-1);
					}
					if (level1 != level2) {
						ManagerPool.skillManager.sendSkillInfos(player);
						break;
					}
				}
//				else if(key > 100 && key < 1000){
//					continue;
//				}
//				
//				// 初学 默认取第一级
//				Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(key + "_" + 1);
//				
				int level1 = 0;
				if (this.getSkillLevelUp().containsKey(-1)) {
					level1 = this.getSkillLevelUp().get(-1);
				}
//				if(this.getSkillLevelUp().containsKey(skillModel.getq_)){
//					level1 += this.getSkillLevelUp().get(key);
//				}
				if (this.getSkillLevelUp().containsKey(key)) {
					level1 += this.getSkillLevelUp().get(key);
				}

				int level2 = 0;
				if (player.getSkillLevelUp().containsKey(-1)) {
					level2 = player.getSkillLevelUp().get(-1);
				}
				if (player.getSkillLevelUp().containsKey(key)) {
					level2 += player.getSkillLevelUp().get(key);
				}

				if (level1 != level2) {
					//ManagerPool.skillManager.sendSkillInfos(player);
					ManagerPool.skillManager.sendSkillInfo(player, key);
				}
			}

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

		public HashMap<Integer, Integer> getSkillLevelUp() {
			return skillLevelUp;
		}
	}
}
