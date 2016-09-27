package com.game.fight.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffType;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.fight.bean.AttackResultInfo;
import com.game.fight.message.ResAttackRangeMessage;
import com.game.fight.message.ResAttackResultMessage;
import com.game.fight.message.ResEffectBroadcastMessage;
import com.game.fight.message.ResFightBroadcastMessage;
import com.game.fight.message.ResFightFailedBroadcastMessage;
import com.game.fight.script.IAttackCheckScript;
import com.game.fight.script.IHitDamageScript;
import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterInfo;
import com.game.fight.structs.FighterState;
import com.game.fight.timer.HitTimer;
import com.game.guild.manager.GuildServerManager;
import com.game.hiddenweapon.message.ResHiddenWeaponSkillTriggerMessage;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Area;
import com.game.map.structs.Effect;
import com.game.map.structs.GroundMagic;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.structs.Hatred;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.pet.manager.PetScriptManager;
import com.game.pet.message.script.IPetWasHitScript;
import com.game.pet.struts.Pet;
import com.game.pet.struts.PetJumpState;
import com.game.pet.struts.PetRunState;
import com.game.player.manager.PlayerManager;
import com.game.player.script.IPlayerWasHitScript;
import com.game.player.script.PlayerCheckType;
import com.game.player.structs.GmState;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.skill.structs.ISkillScript;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.util.TimerUtil;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

/**
 * 战斗管理
 *
 * @author heyang szy_heyang@163.com
 *
 */
public class FightManager {

	private Logger log = Logger.getLogger(FightManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static FightManager manager;
	//玩家同步坐标
	private static HashSet<Long> syncArea = new HashSet<Long>();

	private FightManager() {
	}

	public static FightManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new FightManager();
			}
		}
		return manager;
	}

	public static HashSet<Long> getSyncArea() {
		return syncArea;
	}

	/**
	 * 玩家攻击怪物
	 *
	 * @param roleId 玩家Id
	 * @param monsterId 怪物Id
	 * @param skillId 攻击技能
	 * @param direction 攻击方向
	 */
	public void playerAttackMonster(Player player, long monsterId, int skillId, int direction) {

		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		//冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.PLAYER_ATTACK, null)) {
			log.debug("攻击者（玩家）攻击冷却");
			return;
		}
		
		//冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL, String.valueOf(skillId))) {
			long remain = (long) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL, String.valueOf(skillId));
			
			ManagerPool.playerManager.playercheck(player, PlayerCheckType.ATTACK_SPEED, remain);
			//技能冷却中
			if (remain > 10 * 1000) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf((int)(remain/1000)));
			}

			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//玩家已经死亡
		if (player.isDie()) {
			log.debug("攻击者（玩家）死亡");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(player.getFightState()) || FighterState.SHUIMIAN.compare(player.getFightState())) {
			log.debug("攻击者（玩家）定身或睡眠");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//停止格挡
		if (PlayerState.BLOCKPREPARE.compare(player.getState()) || PlayerState.BLOCK.compare(player.getState())) {
			ManagerPool.mapManager.playerStopBlock(player);
		}

		// 游泳中
		if (PlayerState.SWIM.compare(player.getState())) {
			log.debug("攻击者（玩家）游泳中了");
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法使用技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//技能判断
		Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillId);
		//技能是否创建时学会
		boolean defaultStudy = false;
		if (skill == null) {
			skill = new Skill();
			skill.setSkillModelId(skillId);
			skill.setSkillLevel(1);
			defaultStudy = true;
		}

		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
		if (model == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		if (defaultStudy) {
			//不是默认学会技能
			if (model.getQ_default_study() != 1) {
				log.debug("攻击者（玩家）技能不对");
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
				return;
			}
		}

		//是否主动技能
		if (model.getQ_trigger_type() != 1) {
			log.debug("攻击者（玩家）非主动技能");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//是否人物技能
		if (model.getQ_skill_user() != 1) {
			log.debug("攻击者（玩家）非人物技能");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//获得怪物
		Monster monster = null;
		if (monsterId > 0) {
			monster = ManagerPool.monsterManager.getMonster(player.getServerId(), player.getLine(), player.getMap(), monsterId);
			if (monster != null) {
				if(monster.isDie()){
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
					return;
				}
				
				IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
				if (script != null) {
					try {
						if(!script.check(player, monster)){
							MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
							return;
						}
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("攻击检查脚本不存在！");
				}
			}
		}
		
		//技能目标选择
		ISkillScript script = null;
		if(model.getQ_skill_script() > 0){
			script = (ISkillScript) ManagerPool.scriptManager.getScript(model.getQ_skill_script());
		}
		if(script!=null){
			try{
				if(!script.canUse(player, monster, direction)){
					return;
				}
			}catch(Exception e){
				log.error(e, e);
			}
		}

		//log.error("Player " + player.getId() + " attack monster " + monsterId + ", is null:" + (monster!=null) + (monster==null?"":(" is die:" + monster.isDie())));

		//目标检查 （单体目标且对象不为自己时）
		if (model.getQ_area_shape() == 1 && model.getQ_target() != 1 && (monster == null || monster.isDie())) {
			//无目标错误
			//MessageUtil.notify_player(player, Notifys.ERROR, "请先选定一个释放目标");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//目标不符，无法释放
		if (model.getQ_target() == 1 || model.getQ_target() == 2) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("目标不符，无法释放"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		if (PlayerState.CHANGEMAP.compare(player.getState())) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		Map map = ManagerPool.mapManager.getMap(player);
		if (map == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}
		
		//梅花副本不能使用群攻技能
		if(map.getMapModelid()==42121 && model.getQ_area_shape()!=1){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("本地图不能使用此技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//距离检查
		if (monster != null) {
			Grid playerGrid = MapUtils.getGrid(player.getPosition(), ManagerPool.mapManager.getMapBlocks(map.getMapModelid()));
			Grid monsterGrid = MapUtils.getGrid(monster.getPosition(), ManagerPool.mapManager.getMapBlocks(map.getMapModelid()));
			//计算格子之间距离，放宽一格
			if (model.getQ_range_limit() >= 0 && MapUtils.countDistance(playerGrid, monsterGrid) > model.getQ_range_limit() + 1) {
				//超出攻击距离
				log.debug("超出攻击距离");
				ManagerPool.mapManager.broadcastPlayerForceStop(player);
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
				return;
			}
		}

		//消耗检查
		if (player.getMp() < model.getQ_need_mp()) {
			//内力不足
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("内力不足，无法释放"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//公共冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))) {
			int remain = (int) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level())) / 1000;
			//技能公共冷却中
			if (remain > 10) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf(remain));
			}
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}

		//跳跃状态不能攻击
		if (model.getQ_is_Jump_skill() == 0 && (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState()))) {
			log.debug("跳跃状态不能攻击");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, monsterId, skillId, direction));
			return;
		}
		
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		//攻击怪物不进入或延迟战斗状态
		//玩家进入攻击状态
		//player.setState(PlayerState.FIGHT);
		//玩家转向
		player.setDirection((byte) direction);
		//内力消耗
		player.setMp(player.getMp() - model.getQ_need_mp());
		ManagerPool.playerManager.onMpChange(player);

		//开始冷却
		double speed = 1 + ((double) player.getAttackSpeed()) / 1000;
//		System.out.println("攻击时间：" + System.currentTimeMillis());
//		System.out.println("冷却：" + (long)(model.getQ_cd() / speed));
//		System.out.println("公共冷却：" + (long)(model.getQ_cd() / speed));
		//添加技能冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long) (model.getQ_cd() / speed));
		//添加技能公共冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long) (model.getQ_public_cd() / speed));

		long fightId = Config.getId();

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, player, monster, skillId, direction));

		boolean action = false;
		if(script!=null){
			try{
				//技能脚本行为
				action = script.defaultAction(player, monster);
			}catch(Exception e){
				log.error(e, e);
			}
		}
		if(!action){
			//技能飞行时间计算
			long fly = model.getQ_delay_time();
			//单体攻击
			if (model.getQ_area_shape() == 1 && monster != null) {
				fly += MapUtils.countDistance(player.getPosition(), monster.getPosition()) * 1000 / model.getQ_trajectory_speed();
			}
	
			//log.debug("技能" + model.getQ_skillID_q_grade() + "飞行" + fly + "毫秒,速度" + model.getQ_trajectory_speed() + ",距离" +  MapUtils.countDistance(player.getPosition(), monster.getPosition()));
			//通知宠物
			if (fly > 0) {
				//延时伤害
				HitTimer timer = new HitTimer(fightId, player, monster, skill, direction, fly, true);
				TimerUtil.addTimerEvent(timer);
			} else {
				//即时伤害
				attack(fightId, player, monster, skill, direction, true);
			}
		}
	}

	/**
	 * 玩家攻击玩家
	 *
	 * @param roleId 玩家Id
	 * @param targetId 怪物Id
	 * @param skillId 攻击技能
	 * @param direction 攻击方向
	 */
	public void playerAttackPlayer(Player player, long targetId, int skillId, int direction) {
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		//冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.PLAYER_ATTACK, null)) {
			log.debug("攻击者（玩家）攻击冷却");
			return;
		}
				
		//冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL, String.valueOf(skillId))) {
			long remain = (long) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL, String.valueOf(skillId));
			
			ManagerPool.playerManager.playercheck(player, PlayerCheckType.ATTACK_SPEED, remain);
			//技能冷却中
			if (remain > 10 * 1000) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf((int)(remain/1000)));
			}

			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//玩家已经死亡
		if (player.isDie()) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(player.getFightState()) || FighterState.SHUIMIAN.compare(player.getFightState())) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//停止格挡
		if (PlayerState.BLOCKPREPARE.compare(player.getState()) || PlayerState.BLOCK.compare(player.getState())) {
			ManagerPool.mapManager.playerStopBlock(player);
		}

		// 游泳中
		if (PlayerState.SWIM.compare(player.getState())) {
			log.debug("攻击者（玩家）游泳中了");
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法使用技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//技能判断
		Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillId);
		//技能是否创建时学会
		boolean defaultStudy = false;
		if (skill == null) {
			skill = new Skill();
			skill.setSkillModelId(skillId);
			skill.setSkillLevel(1);
			defaultStudy = true;
		}

		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
		if (model == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		if (defaultStudy) {
			//不是默认学会技能
			if (model.getQ_default_study() != 1) {
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}
		}

		//是否主动技能 
		if (model.getQ_trigger_type() != 1) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//是否人物技能
		if (model.getQ_skill_user() != 1) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//获得怪物
		Player target = null;
		if (targetId > 0) {
			target = ManagerPool.playerManager.getPlayer(targetId);
		}
		
		//技能目标选择
		ISkillScript script = null;
		if(model.getQ_skill_script() > 0){
			script = (ISkillScript) ManagerPool.scriptManager.getScript(model.getQ_skill_script());
		}
		if(script!=null){
			try{
				if(!script.canUse(player, target, direction)){
					return;
				}
			}catch(Exception e){
				log.error(e, e);
			}
		}
		
		if(target==null){
			log.error("玩家(" + player.getId() + ")对玩家(" + targetId + ")攻击没有目标");
			return;
		}

		if (PlayerState.CHANGEMAP.compare(player.getState())) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		Map map = ManagerPool.mapManager.getMap(player);
		if (map == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}
		
		//梅花副本不能使用群攻
		if(map.getMapModelid()==42121 && model.getQ_area_shape()!=1){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("本地图不能使用此技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//目标检查 （单体目标且对象不为自己时）
		if (model.getQ_area_shape() == 1 && model.getQ_target() != 1 && (target == null || target.isDie())) {
			//无目标错误
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请先选定一个释放目标"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//消耗检查
		if (player.getMp() < model.getQ_need_mp()) {
			//内力不足
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("内力不足，无法释放"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//公共冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))) {
			int remain = (int) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level())) / 1000;
			log.debug("公共冷却中");
			//技能公共冷却中
			if (remain > 10) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf(remain));
			}
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//跳跃状态不能攻击
		if (model.getQ_is_Jump_skill() == 0 && (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState()))) {
			log.debug("跳跃状态不能攻击");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		if (target != null) {
			if ( model.getQ_target() != 2) {
				//不在pk列表中
				if (!player.getEnemys().containsKey(target.getId())) {
					//PK状态检查
					if (player.getPkState() == 0) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}else if (player.getPkState() == 1) {
						//同队伍检查
						if (player.getTeamid() == target.getTeamid() && player.getTeamid() != 0) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
							MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
							return;
						}
					} else if (player.getPkState() == 2) {
						//同帮会检查
						if ((player.getGuildId() == target.getGuildId()  || GuildServerManager.getInstance().isFriendGuild(player.getGuildId(), target.getGuildId())) && player.getGuildId() != 0) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
							MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
							return;
						}
					}
				}else{
					log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")对玩家(" + target.getId() + ")PK状态为(" + target.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(target.getState()) + ")攻击因为在仇恨列表中");	
				}

				// 游泳中
				if (PlayerState.SWIM.compare(target.getState())) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法被攻击"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}

				//自己等级检查
				if (player.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您处于30级以下新手保护期内，无法对其他玩家造成伤害"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}

				//敌人等级检查
				if (target.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1 ) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您无法对低于30级以下处于新手保护期内的玩家造成伤害"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}

				if(!FighterState.FORCEPK.compare(target.getFightState())){
					//Pk保护Buff
					if (FighterState.PKBAOHU.compare(target.getFightState())) {
						Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PKPROTECT).get(0);
						long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
						if (remain > 0) {
							int sec = (int) (remain / 1000);
							if (remain > 5000) {
								if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.NOTIFY_KILL, null)) {
									MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
									return;
								} else {
									ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.NOTIFY_KILL, null, 10 * 1000);
								}
							}
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家处于复活后的PK保护时间内，您目前无法对其造成伤害，剩余时间：{1}分{2}秒"), String.valueOf(sec / 60), String.valueOf(sec % 60));
							MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
							return;
						}
					}
		
					//夜晚挂机和平buff
					if (FighterState.PKBAOHUFORNIGHT.compare(target.getFightState())) {
						Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PROTECTFORNIGHT).get(0);
						long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
						if (remain > 0) {
							int sec = (int) (remain / 1000);
							if (remain > 5000) {
								if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.NOTIFY_KILL, null)) {
									MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
									return;
								} else {
									ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.NOTIFY_KILL, null, 10 * 1000);
								}
							}
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("玩家处于夜晚挂机PK保护时间内，您目前无法对其造成伤害，剩余时间：{1}分{2}秒"), String.valueOf(sec / 60), String.valueOf(sec % 60));
							MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
							return;
						}
					}
		
					//等级差检查(地图开启等级差保护)
					if (Math.abs(player.getLevel() - target.getLevel()) > 20 && mapBean.getQ_map_pk() == 1) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("等级差>20级，互相之间无法造成伤害"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
		
					//自己安全区检查
					if (ManagerPool.mapManager.isSafe(player.getPosition(), player.getMapModelId())) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，安全区内禁止PK"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
		
					//目标安全区检查
		
					if (ManagerPool.mapManager.isSafe(target.getPosition(), player.getMapModelId())) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法PK处于安全区内的玩家"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				}
			
			}
			
			//距离检查
			//计算格子之间距离，放宽一格
			Grid playerGrid = MapUtils.getGrid(player.getPosition(), blocks);
			Grid monsterGrid = MapUtils.getGrid(target.getPosition(), blocks);
			if (model.getQ_range_limit() >= 0 && MapUtils.countDistance(playerGrid, monsterGrid) > model.getQ_range_limit() + 1) {
				//超出攻击距离
				log.debug("超出攻击距离");
				ManagerPool.mapManager.broadcastPlayerForceStop(player);
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}
			
			IAttackCheckScript checkscript = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (checkscript != null) {
				try {
					if(!checkscript.check(player, target)){
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
		}
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		if (model.getQ_target() != 2 ) {
			//玩家进入攻击状态
			player.setState(PlayerState.FIGHT);

			//主动Pk移除被杀保护Buff
			if (FighterState.PKBAOHU.compare(player.getFightState()) ) {
				ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
				log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")敌人列表为(" + MessageUtil.castListToString(player.getEnemys().values()) + ")对玩家(" + target.getId() + ")PK状态为(" + target.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(target.getState()) + ")攻击导致和平保护buff消失");	
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您主动发起了对其他玩家的PK，和平保护BUFF消失了"));
			}

			//主动Pk移除夜晚保护Buff
			if (FighterState.PKBAOHUFORNIGHT.compare(player.getFightState()) ) {
				ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
				log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")敌人列表为(" + MessageUtil.castListToString(player.getEnemys().values()) + ")对玩家(" + target.getId() + ")PK状态为(" + target.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(target.getState()) + ")攻击导致夜晚和平保护buff消失");	
			}
		}
		

		log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")敌人列表为(" + MessageUtil.castListToString(player.getEnemys().values()) + ")对玩家(" + target.getId() + ")PK状态为(" + target.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(target.getState()) + ")攻击");	
		
		//玩家转向
		player.setDirection((byte) direction);

		//内力消耗
		player.setMp(player.getMp() - model.getQ_need_mp());
		ManagerPool.playerManager.onMpChange(player);

		//开始冷却
		double speed = (double) 1;
		//结婚几个技能不受攻击速度影响
		if ( !(model.getQ_skillID()==25005 || model.getQ_skillID()==25006 || model.getQ_skillID()==25007) ) {
			speed = 1 + ((double) player.getAttackSpeed()) / 1000;
		}
		//添加技能冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long) (model.getQ_cd() / speed));
		//添加技能公共冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long) (model.getQ_public_cd() / speed));

		long fightId = Config.getId();

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, player, target, skillId, direction));

		boolean action = false;
		if(script!=null){
			try{
				//技能脚本行为
				action = script.defaultAction(player, target);
			}catch(Exception e){
				log.error(e, e);
			}
		}
		if(!action){
			//技能飞行时间计算
			long fly = model.getQ_delay_time();
			//单体攻击
			if (model.getQ_area_shape() == 1 && target != null) {
				fly += MapUtils.countDistance(player.getPosition(), target.getPosition()) * 1000 / model.getQ_trajectory_speed();
			}

			if (fly > 0) {
				//延时伤害
				HitTimer timer = new HitTimer(fightId, player, target, skill, direction, fly, true);
				TimerUtil.addTimerEvent(timer);
			} else {
				//即时伤害
				attack(fightId, player, target, skill, direction, true);
			}
		}
		
	}

	/**
	 * 玩家攻击宠物
	 *
	 * @param roleId 玩家Id
	 * @param ownerId 主人Id
	 * @param targetId 宠物Id
	 * @param skillId 攻击技能
	 * @param direction 攻击方向
	 */
	public void playerAttackPet(Player player, long ownerId, long targetId, int skillId, int direction) {
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		//玩家已经死亡
		if (player.isDie()) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(player.getFightState()) || FighterState.SHUIMIAN.compare(player.getFightState())) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//停止格挡
		if (PlayerState.BLOCKPREPARE.compare(player.getState()) || PlayerState.BLOCK.compare(player.getState())) {
			ManagerPool.mapManager.playerStopBlock(player);
		}

		// 游泳中
		if (PlayerState.SWIM.compare(player.getState())) {
			log.debug("攻击者（玩家）游泳中了");
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法使用技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//技能判断
		Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillId);
		//技能是否创建时学会
		boolean defaultStudy = false;
		if (skill == null) {
			skill = new Skill();
			skill.setSkillModelId(skillId);
			skill.setSkillLevel(1);
			defaultStudy = true;
		}

		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
		if (model == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		if (defaultStudy) {
			//不是默认学会技能
			if (model.getQ_default_study() != 1) {
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}
		}

		//是否主动技能 
		if (model.getQ_trigger_type() != 1) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//是否人物技能
		if (model.getQ_skill_user() != 1) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		if (PlayerState.CHANGEMAP.compare(player.getState())) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		Map map = ManagerPool.mapManager.getMap(player);
		if (map == null) {
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}
		
		//梅花副本不能使用群攻
		if(map.getMapModelid()==42121 && model.getQ_area_shape()!=1){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("本地图不能使用此技能"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//获得怪物
		Player owner = null;
		Pet target = null;
		if (ownerId == player.getId()) {
			//宠物主人是自己！
			return;
		}

		if (ownerId > 0) {
			owner = ManagerPool.playerManager.getPlayer(ownerId);
			if (owner != null && targetId > 0) {
				target = ManagerPool.petInfoManager.getPet(owner, targetId);
			}
		}
		
		//技能目标选择
		ISkillScript script = null;
		if(model.getQ_skill_script() > 0){
			script = (ISkillScript) ManagerPool.scriptManager.getScript(model.getQ_skill_script());
		}
		if(script!=null){
			try{
				if(!script.canUse(player, target, direction)){
					return;
				}
			}catch(Exception e){
				log.error(e, e);
			}
		}
		
		if(target==null){
			log.error("玩家(" + player.getId() + ")对宠物(" + targetId + ")主人为(" + ownerId + ")攻击没有目标");
			return;
		}

		//目标检查 （单体目标且对象不为自己时）
		if (model.getQ_area_shape() == 1 && model.getQ_target() != 1 && (target == null || target.isDie())) {
			//无目标错误
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请先选定一个释放目标"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//消耗检查
		if (player.getMp() < model.getQ_need_mp()) {
			//内力不足
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("内力不足，无法释放"));
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))) {
			int remain = (int) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID())) / 1000;
			log.debug("技能冷却中");
			//技能冷却中
			if (remain > 10) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf(remain));
			}
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//公共冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))) {
			int remain = (int) ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level())) / 1000;
			log.debug("公共冷却中");
			//技能公共冷却中
			if (remain > 10) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("技能冷却中，请稍后再试，剩余{1}秒"), String.valueOf(remain));
			}
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		//跳跃状态不能攻击
		if (model.getQ_is_Jump_skill() == 0 && (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState()))) {
			log.debug("跳跃状态不能攻击");
			MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
			return;
		}

		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		if (target != null && owner != null) {
			if (PetRunState.SWIM == target.getRunState()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法被攻击"));
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}

			//不在pk列表中
			if (!player.getEnemys().containsKey(owner.getId())) {
				//PK状态检查
				if (player.getPkState() == 0) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				} else if (player.getPkState() == 1) {
					//同队伍检查
					if (player.getTeamid() == owner.getTeamid() && player.getTeamid() != 0) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				} else if (player.getPkState() == 2) {
					//同帮会检查
					if ((player.getGuildId() == owner.getGuildId()  || GuildServerManager.getInstance().isFriendGuild(owner.getGuildId(), player.getGuildId())) && player.getGuildId()!=0) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前PK模式，无法对其造成伤害"));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				}
			}

			//自己等级检查
			if (player.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您处于30级以下新手保护期内，无法对其他玩家造成伤害"));
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}

			//敌人等级检查
			if (owner.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您无法对低于30级以下处于新手保护期内的玩家造成伤害"));
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}

			if(!FighterState.FORCEPK.compare(owner.getFightState())){
				//Pk保护Buff
				if (FighterState.PKBAOHU.compare(owner.getFightState())) {
					Buff buff = ManagerPool.buffManager.getBuffByType(owner, BuffType.PKPROTECT).get(0);
					long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
					if (remain > 0) {
						int sec = (int) (remain / 1000);
						if (remain > 5000) {
							if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.NOTIFY_KILL, null)) {
								MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
								return;
							} else {
								ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.NOTIFY_KILL, null, 10 * 1000);
							}
						}
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家处于复活后的PK保护时间内，您目前无法对其造成伤害，剩余时间：{1}分{2}秒"), String.valueOf(sec / 60), String.valueOf(sec % 60));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				}
	
				//夜晚挂机和平buff
				if (FighterState.PKBAOHUFORNIGHT.compare(owner.getFightState())) {
					Buff buff = ManagerPool.buffManager.getBuffByType(owner, BuffType.PROTECTFORNIGHT).get(0);
					long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
					if (remain > 0) {
						int sec = (int) (remain / 1000);
						if (remain > 5000) {
							if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.NOTIFY_KILL, null)) {
								MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
								return;
							} else {
								ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.NOTIFY_KILL, null, 10 * 1000);
							}
						}
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("玩家处于夜晚挂机PK保护时间内，您目前无法对其造成伤害，剩余时间：{1}分{2}秒"), String.valueOf(sec / 60), String.valueOf(sec % 60));
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				}
	
				//等级差检查(地图开启等级差保护)
				if (Math.abs(player.getLevel() - owner.getLevel()) > 20 && mapBean.getQ_map_pk() == 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("等级差>20级，互相之间无法造成伤害"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}
	
				//自己安全区检查
				if (ManagerPool.mapManager.isSafe(player.getPosition(), player.getMapModelId())) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，安全区内禁止PK"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}
	
				//目标安全区检查
				if (ManagerPool.mapManager.isSafe(target.getPosition(), player.getMapModelId())) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法PK处于安全区内的美人"));
					MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
					return;
				}
			}
			
			//距离检查
			//计算格子之间距离，放宽一格
			Grid playerGrid = MapUtils.getGrid(player.getPosition(), blocks);
			Grid monsterGrid = MapUtils.getGrid(target.getPosition(), blocks);
			if (model.getQ_range_limit() >= 0 && MapUtils.countDistance(playerGrid, monsterGrid) > model.getQ_range_limit() + 1) {
				//超出攻击距离
				log.debug("超出攻击距离");
				ManagerPool.mapManager.broadcastPlayerForceStop(player);
				MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
				return;
			}
			
			IAttackCheckScript checkscript = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (checkscript != null) {
				try {
					if(!checkscript.check(player, target)){
						MessageUtil.tell_player_message(player, getFightFailedBroadcastMessage(0, player, targetId, skillId, direction));
						return;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
		}
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		//玩家进入攻击状态
		player.setState(PlayerState.FIGHT);

		//主动Pk移除被杀保护Buff
		if (FighterState.PKBAOHU.compare(player.getFightState())) {
			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
			log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")敌人列表为(" + MessageUtil.castListToString(player.getEnemys().values()) + ")对宠物(" + target.getId() + ")攻击导致和平保护buff消失");
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您主动发起了对其他玩家的PK，和平保护BUFF消失了"));
		}

		//主动Pk移除夜晚保护Buff
		if (FighterState.PKBAOHUFORNIGHT.compare(player.getFightState())) {
			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
			log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")敌人列表为(" + MessageUtil.castListToString(player.getEnemys().values()) + ")对宠物(" + target.getId() + ")攻击导致夜晚和平保护buff消失");
		}

		//玩家转向
		player.setDirection((byte) direction);

		//内力消耗
		player.setMp(player.getMp() - model.getQ_need_mp());
		ManagerPool.playerManager.onMpChange(player);

		//开始冷却
		double speed = 1 + ((double) player.getAttackSpeed()) / 1000;
		//添加技能冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long) (model.getQ_cd() / speed));
		//添加技能公共冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long) (model.getQ_public_cd() / speed));

		long fightId = Config.getId();

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, player, target, skillId, direction));
		
		boolean action = false;
		if(script!=null){
			try{
				//技能脚本行为
				action = script.defaultAction(player, target);
			}catch(Exception e){
				log.error(e, e);
			}
		}
		if(!action){
			//技能飞行时间计算
			long fly = model.getQ_delay_time();
			//单体攻击
			if (model.getQ_area_shape() == 1 && target != null) {
				fly += MapUtils.countDistance(player.getPosition(), target.getPosition()) * 1000 / model.getQ_trajectory_speed();
			}
	
			if (fly > 0) {
				//延时伤害
				HitTimer timer = new HitTimer(fightId, player, target, skill, direction, fly, true);
				TimerUtil.addTimerEvent(timer);
			} else {
				//即时伤害
				attack(fightId, player, target, skill, direction, true);
			}
		}
	}

	/**
	 * 怪物攻击玩家
	 *
	 * @param monster 怪物
	 * @param roleId 玩家Id
	 * @param attackType 攻击类型
	 */
	public void monsterAttackPlayer(Monster monster, Player player, Skill skill) {
		//攻击间隔按默认技能冷却算
		Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
		Skill defaultSkill = monster.getDefaultSkill(monsterModel);
		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(defaultSkill.getSkillModelId() + "_" + defaultSkill.getSkillLevel());
		if (model == null) {
			return;
		}
		
		if(player!=null){
			if(!monster.canAttack(player)){
				return;
			}
			
			IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (script != null) {
				try {
					if(!script.check(monster, player)){
						return;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
		}

		//开始冷却
		double speed = ((double) monster.getAttackSpeed()) / 1000;
		//添加怪物攻击冷却
		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ATTACK, null, (long) (model.getQ_public_cd() / speed));

		//添加怪物攻击动画冷却
		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ACTION, null, (long) (model.getQ_delay_time()));

		long fightId = Config.getId();

		log.debug("monster " + (monster == null ? "null" : monster.getId()) + " use skill " + (skill == null ? "null" : skill.getSkillModelId()) + " attack player " + (player == null ? "null" : player.getId()));

		int dir = MapUtils.countDirection(monster.getPosition(), player.getPosition());
		monster.setDirection((byte) dir);

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, monster, player, skill.getSkillModelId(), dir));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();

		if (model.getQ_area_shape() == 1 && player != null) {
			fly += MapUtils.countDistance(monster.getPosition(), player.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, monster, player, skill, dir, fly, true);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, monster, player, skill, dir, true);
		}
	}
	
	/**
	 * 怪物攻击怪物
	 *
	 * @param monster 怪物
	 * @param target 怪物
	 * @param attackType 攻击类型
	 */
	public void monsterAttackPlayer(Monster monster, Monster target, Skill skill) {
		//攻击间隔按默认技能冷却算
		Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
		Skill defaultSkill = monster.getDefaultSkill(monsterModel);
		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(defaultSkill.getSkillModelId() + "_" + defaultSkill.getSkillLevel());
		if (model == null) {
			return;
		}
		
		if(target!=null){
			if(!monster.canAttack(target)){
				return;
			}
			
			IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (script != null) {
				try {
					if(!script.check(monster, target)){
						return;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
		}

		//开始冷却
		double speed = ((double) monster.getAttackSpeed()) / 1000;
		//添加怪物攻击冷却
		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ATTACK, null, (long) (model.getQ_public_cd() / speed));

		//添加怪物攻击动画冷却
		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ACTION, null, (long) (model.getQ_delay_time()));

		long fightId = Config.getId();

		log.debug("monster " + (monster == null ? "null" : monster.getId()) + " use skill " + (skill == null ? "null" : skill.getSkillModelId()) + " attack monster " + (target == null ? "null" : target.getId()));

		int dir = MapUtils.countDirection(monster.getPosition(), target.getPosition());
		monster.setDirection((byte) dir);

		MessageUtil.tell_round_message(monster, getFightBroadcastMessage(fightId, monster, target, skill.getSkillModelId(), dir));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();

		if (model.getQ_area_shape() == 1 && target != null) {
			fly += MapUtils.countDistance(monster.getPosition(), target.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, monster, target, skill, dir, fly, true);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, monster, target, skill, dir, true);
		}
	}

	/**
	 * 怪物不可以攻击宠物 怪物攻击宠物
	 * @param monster 怪物
	 * @param pet 宠物
	 * @param attackType 攻击类型
	 */
//	public void monsterAttackPet(Monster monster, Pet pet, Skill skill){
//		//攻击间隔按默认技能冷却算
//		Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
//		Skill defaultSkill = monster.getDefaultSkill(monsterModel);
//		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(defaultSkill.getSkillModelId() + "_" + defaultSkill.getSkillLevel());
//		if(model==null) return;
//		
//		//开始冷却
//		double speed = ((double)monster.getAttackSpeed()) / 1000;
//		//添加怪物攻击冷却
//		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ATTACK, null, (long)(model.getQ_public_cd() / speed));
//		
//		//添加怪物攻击动画冷却
//		ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.MONSTER_ACTION, null, (long)(model.getQ_delay_time()));
//		
//		long fightId = Config.getId();
//		
////		log.debug("monster " + (monster==null?"null":monster.getId()) + " use skill " + (skill==null?"null":skill.getSkillModelId()) + " attack player " + (pet==null?"null":pet.getId()));
//		
//		int dir = MapUtils.countDirection(monster.getPosition(), pet.getPosition());
//		monster.setDirection((byte)dir);
//		
//		MessageUtil.tell_round_message(pet, getFightBroadcastMessage(fightId, monster, pet, skill.getSkillModelId(), dir));
//		
//		//技能飞行时间计算
//		long fly = model.getQ_delay_time();
//
//		if(model.getQ_area_shape()==1 && pet!=null){
//			fly += MapUtils.countDistance(monster.getPosition(), pet.getPosition()) * 1000 / model.getQ_trajectory_speed();
//		}
//		
//		if(fly > 0){
//			//延时伤害
//			HitTimer timer = new HitTimer(fightId, monster, pet, skill, dir, fly);
//			TimerUtil.addTimerEvent(timer);
//		}else{
//			//即时伤害
//			attack(fightId, monster, pet, skill, dir);
//		}
//	}
	/**
	 * 宠物攻击怪物
	 *
	 * @param roleId 玩家Id
	 * @param monsterId 怪物Id
	 * @param skillId 攻击技能
	 * @param direction 攻击方向
	 */
	public void petAttackMonster(Pet pet, Monster monster, Skill skill) {
		//宠物主人死亡
		Player owner = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
		if (owner == null || owner.isDie()) {
			return;
		}
		//宠物死亡
		if (pet.isDie()) {
			return;
		}

		//攻击间隔按默认技能冷却算
		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		if (model == null) {
			return;
		}

		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) {
			return;
		}

		//目标检查 （单体目标且对象不为自己时）
		if (model.getQ_area_shape() == 1 && model.getQ_target() != 1 && (monster == null || monster.isDie())) {
			//无目标错误
			return;
		}

//		//消耗检查
//		if (pet.getMp() < model.getQ_need_mp()) {
//			//内力不足
//			return;
//		}

		//攻击冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_ATTACK, null)) {
			return;
		}

//		//冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))){
//			return;
//		}
//		
//		//公共冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))){
//			return;
//		}

//		//内力消耗
//		pet.setMp(pet.getMp() - model.getQ_need_mp());
//		ManagerPool.petInfoManager.onMpChange(pet);

		IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
		if (script != null) {
			try {
				if(!script.check(pet, monster)){
					return;
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("攻击检查脚本不存在！");
		}
		//开始冷却
		double speed = 1 + ((double) pet.getAttackSpeed()) / 1000;

		//System.out.println("宠物攻击速度：" + speed);
		//添加怪物攻击冷却
		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.PET_ATTACK, null, (long) (model.getQ_public_cd() / speed));

		//添加怪物攻击动画冷却
		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.PET_ACTION, null, (long) (model.getQ_delay_time()));

		long fightId = Config.getId();

//		log.debug("pet " + (pet==null?"null":pet.getId()) + " use skill " + (skill==null?"null":skill.getSkillModelId()) + " attack player " + (player==null?"null":player.getId()));

		int dir = MapUtils.countDirection(pet.getPosition(), monster.getPosition());
		pet.setDirection((byte) dir);

		MessageUtil.tell_round_message(pet, getFightBroadcastMessage(fightId, pet, monster, skill.getSkillModelId(), dir));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();

		if (model.getQ_area_shape() == 1 && monster != null) {
			fly += MapUtils.countDistance(pet.getPosition(), monster.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, pet, monster, skill, dir, fly, true);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, pet, monster, skill, dir, true);
		}
		PetScriptManager.getInstance().useSkill(pet, skill);
	}

	/**
	 * 宠物攻击玩家
	 *
	 * @param roleId 玩家Id
	 * @param targetId 怪物Id
	 * @param skillId 攻击技能
	 * @param direction 攻击方向
	 */
	public void petAttackPlayer(Pet pet, Player target, Skill skill) {
		//宠物主人死亡
		Player owner = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
		if (owner == null || owner.isDie()) {
			return;
		}
		//宠物死亡
		if (pet.isDie()) {
			return;
		}

		//玩家已经死亡
		if (target.isDie()) {
			return;
		}

		//定身或睡眠中
		if (FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) {
			return;
		}


		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		if (model == null) {
			return;
		}

		//目标检查 （单体目标且对象不为自己时）
		if (model.getQ_area_shape() == 1 && model.getQ_target() != 1 && (target == null || target.isDie())) {
			//无目标错误
			return;
		}

//		//消耗检查
//		if (pet.getMp() < model.getQ_need_mp()) {
//			//内力不足
//			return;
//		}

		//攻击冷却检查
		if (ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.PET_ATTACK, null)) {
			return;
		}

//		//冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))){
//			return;
//		}
//		
//		//公共冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))){
//			return;
//		}

		Map map = ManagerPool.mapManager.getMap(owner.getServerId(), owner.getLine(), owner.getMap());
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		if (target != null) {
			//不在pk列表中
			if (!owner.getEnemys().containsKey(target.getId())) {
				//PK状态检查
				if (owner.getPkState() == 0) {
					return;
				} else if (owner.getPkState() == 1) {
					//同队伍检查
					if (owner.getTeamid() == target.getTeamid() && owner.getTeamid() != 0) {
						return;
					}
				} else if (owner.getPkState() == 2) {
					//同帮会检查
					if ((owner.getGuildId() == target.getGuildId() || GuildServerManager.getInstance().isFriendGuild(owner.getGuildId(), target.getGuildId())) && owner.getGuildId() != 0) {
						return;
					}
				}
			}

			//自己等级检查
			if (owner.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				return;
			}

			//敌人等级检查
			if (target.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				return;
			}

			if(!FighterState.FORCEPK.compare(target.getFightState())){
				//Pk保护Buff
				if (FighterState.PKBAOHU.compare(target.getFightState())) {
					Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PKPROTECT).get(0);
					long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
					if (remain > 0) {
						return;
					}
				}
	
				//夜晚挂机和平buff
				if (FighterState.PKBAOHUFORNIGHT.compare(target.getFightState())) {
					Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PROTECTFORNIGHT).get(0);
					long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
					if (remain > 0) {
						return;
					}
				}
	
				//等级差检查(地图开启等级差保护)
				if (Math.abs(owner.getLevel() - target.getLevel()) > 20 && mapBean.getQ_map_pk() == 1) {
					return;
				}
	
				//自己安全区检查
	
				if (ManagerPool.mapManager.isSafe(pet.getPosition(), owner.getMapModelId())) {
					return;
				}
	
				//目标安全区检查
	
				if (ManagerPool.mapManager.isSafe(target.getPosition(), owner.getMapModelId())) {
					return;
				}
			}
			//距离检查
			//计算格子之间距离，放宽一格
			Grid playerGrid = MapUtils.getGrid(pet.getPosition(), blocks);
			Grid monsterGrid = MapUtils.getGrid(target.getPosition(), blocks);
			if (model.getQ_range_limit() >= 0 && MapUtils.countDistance(playerGrid, monsterGrid) > model.getQ_range_limit() + 1) {
				//超出攻击距离
				log.debug("超出攻击距离");
				return;
			}
			
			IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (script != null) {
				try {
					if(!script.check(pet, target)){
						return;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
		}

		//玩家进入攻击状态
		owner.setState(PlayerState.FIGHT);

//		//主动Pk移除被杀保护Buff
//		if(FighterState.PKBAOHU.compare(player.getFightState())){
//			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
//			MessageUtil.notify_player(player, Notifys.NORMAL, "您主动发起了对其他玩家的PK，和平保护BUFF消失了");
//		}
//		
//		//主动Pk移除夜晚保护Buff
//		if(FighterState.PKBAOHUFORNIGHT.compare(player.getFightState())){
//			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
//		}

		int dir = MapUtils.countDirection(pet.getPosition(), target.getPosition());
		//玩家转向
		pet.setDirection((byte) dir);
		pet.setLastFightTime(System.currentTimeMillis());

//		//内力消耗
//		pet.setMp(pet.getMp() - model.getQ_need_mp());
//		ManagerPool.petInfoManager.onMpChange(pet);

		//开始冷却
		double speed = 1 + ((double) pet.getAttackSpeed()) / 1000;
//		//添加技能冷却
//		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long)(model.getQ_cd() / speed));
//		//添加技能公共冷却
//		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long)(model.getQ_public_cd() / speed));

		//添加怪物攻击冷却
		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.PET_ATTACK, null, (long) (model.getQ_public_cd() / speed));

		//添加怪物攻击动画冷却
		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.PET_ACTION, null, (long) (model.getQ_delay_time()));

		long fightId = Config.getId();

		MessageUtil.tell_round_message(pet, getFightBroadcastMessage(fightId, pet, target, skill.getSkillModelId(), dir));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();
		//单体攻击
		if (model.getQ_area_shape() == 1 && target != null) {
			fly += MapUtils.countDistance(pet.getPosition(), target.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, pet, target, skill, dir, fly, true);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, pet, target, skill, dir, true);
		}
		PetScriptManager.getInstance().useSkill(pet, skill);
	}

//	/**
//	 * @deprecated 宠物不可以攻击宠物
//	 * 宠物攻击宠物
//	 * @param roleId 玩家Id
//	 * @param targetId 怪物Id
//	 * @param skillId 攻击技能
//	 * @param direction 攻击方向
//	 */
//	public void petAttackPet(Pet pet, Pet targetPet, Skill skill){
//		//宠物主人死亡
//		Player owner = ManagerPool.playerManager.getPlayer(pet.getOwnerId());
//		if(owner==null || owner.isDie()) return;
//		//宠物死亡
//		if(pet.isDie()) return;
//		
//		//玩家已经死亡
//		if(targetPet==null || targetPet.isDie()){
//			return;
//		}
//		
//		//定身或睡眠中
//		if(FighterState.DINGSHEN.compare(pet.getFightState()) || FighterState.SHUIMIAN.compare(pet.getFightState())) return;
//		
//		
//		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
//		if(model==null) return;
//		
//		//目标检查 （单体目标且对象不为自己时）
//		if(model.getQ_area_shape()==1 && model.getQ_target()!=1 && (targetPet==null || targetPet.isDie())){
//			//无目标错误
//			return;
//		}
//		
//		//消耗检查
//		if(pet.getMp() < model.getQ_need_mp()){
//			//内力不足
//			return;
//		}
//		
//		//冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))){
//			return;
//		}
//		
//		//公共冷却检查
//		if(ManagerPool.cooldownManager.isCooldowning(pet, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))){
//			return;
//		}
//		
//		Map map = ManagerPool.mapManager.getMap(owner.getServerId(), owner.getLine(), owner.getMap());
//		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
//		Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
//		Player target = ManagerPool.playerManager.getPlayer(targetPet.getOwnerId());
//		if(target!=null){
//			//不在pk列表中
//			if(!owner.getEnemys().containsKey(target.getId())){
//				//PK状态检查
//				if(owner.getPkState()==0){
//					return;
//				}
//			}
//			
//			//自己等级检查
//			if(owner.getLevel() < 30 && mapBean.getQ_map_pkprotection()==1){
//				return;
//			}
//			
//			//敌人等级检查
//			if(target.getLevel() < 30 && mapBean.getQ_map_pkprotection()==1){
//				return;
//			}
//			
//			//Pk保护Buff
//			if(FighterState.PKBAOHU.compare(target.getFightState())){
//				Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PKPROTECT).get(0);
//				long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
//				if(remain > 0){
//					return;
//				}
//			}
//			
//			//夜晚挂机和平buff
//			if(FighterState.PKBAOHUFORNIGHT.compare(target.getFightState())){
//				Buff buff = ManagerPool.buffManager.getBuffByType(target, BuffType.PROTECTFORNIGHT).get(0);
//				long remain = buff.getStart() + buff.getTotalTime() - System.currentTimeMillis();
//				if(remain > 0){
//					return;
//				}
//			}
//			
//			//等级差检查(地图开启等级差保护)
//			if(Math.abs(owner.getLevel() - target.getLevel()) > 20 && mapBean.getQ_map_pk()==1){
//				return;
//			}
//			
//			//自己安全区检查
//			Grid playerGrid = MapUtils.getGrid(pet.getPosition(), blocks);
//			if(playerGrid.getSafe()==1){
//				return;
//			}
//			
//			//目标安全区检查
//			Grid monsterGrid = MapUtils.getGrid(targetPet.getPosition(), blocks);
//			if(monsterGrid.getSafe()==1){
//				return;
//			}
//
//			//距离检查
//			//计算格子之间距离，放宽一格
//			if(MapUtils.countDistance(playerGrid, monsterGrid) > model.getQ_range_limit() + 1){
//				//超出攻击距离
//				log.debug("超出攻击距离");
//				return;
//			}
//		}
//
//		//玩家进入攻击状态
//		owner.setState(PlayerState.FIGHT);
//		
////		//主动Pk移除被杀保护Buff
////		if(FighterState.PKBAOHU.compare(player.getFightState())){
////			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
////			MessageUtil.notify_player(player, Notifys.NORMAL, "您主动发起了对其他玩家的PK，和平保护BUFF消失了");
////		}
////		
////		//主动Pk移除夜晚保护Buff
////		if(FighterState.PKBAOHUFORNIGHT.compare(player.getFightState())){
////			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
////		}
//		
//		int dir = MapUtils.countDirection(pet.getPosition(), targetPet.getPosition());
//		//玩家转向
//		pet.setDirection((byte)dir);
//		
//		//内力消耗
//		pet.setMp(pet.getMp() - model.getQ_need_mp());
//		ManagerPool.petManager.onMpChange(pet);
//		
//		//开始冷却
//		double speed = 1 + ((double)pet.getAttackSpeed()) / 1000;
//		//添加技能冷却
//		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long)(model.getQ_cd() / speed));
//		//添加技能公共冷却
//		ManagerPool.cooldownManager.addCooldown(pet, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long)(model.getQ_public_cd() / speed));
//		
//		long fightId = Config.getId();
//		
//		MessageUtil.tell_round_message(pet, getFightBroadcastMessage(fightId, pet, targetPet, skill.getSkillModelId(), dir));
//		
//		//技能飞行时间计算
//		long fly = model.getQ_delay_time();
//		//单体攻击
//		if(model.getQ_area_shape()==1 && target!=null){
//			fly += MapUtils.countDistance(pet.getPosition(), targetPet.getPosition()) * 1000 / model.getQ_trajectory_speed();
//		}
//		
//		if(fly > 0){
//			//延时伤害
//			HitTimer timer = new HitTimer(fightId, pet, targetPet, skill, dir, fly);
//			TimerUtil.addTimerEvent(timer);
//		}else{
//			//即时伤害
//			attack(fightId, pet, targetPet, skill, dir);
//		}
//	}
	/**
	 * 攻击
	 *
	 * @param attacker 攻击者
	 * @param defender 防御者
	 * @param skill 使用技能
	 * @param direction 方向
	 */
	public void attack(long fightId, Fighter attacker, Fighter defender, Skill skill, int direction, boolean trigger) {
		//死亡检查
		if (attacker.isDie()) {
			return;
		}
		if (defender != null && defender.isDie()) {
			return;
		}
		if (defender != null) {
			if (attacker.getLine() != defender.getLine() || attacker.getMap() != defender.getMap()) {
				return;
			}
		}

		//获得技能模板
		Q_skill_modelBean model = null;
		if (attacker instanceof Player) {
			model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel((Player) attacker));
		} else {
			model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		}
		
		// 火墙技能 走特殊流程
		if (model != null && model.getQ_skill_type() == 1 && attacker instanceof Player && defender != null) {
			this.useGroundMagic((Player) attacker, skill, defender.getMap(), defender.getLine(), defender.getPosition());
			return ;
		}
		
		//技能目标选择
		ISkillScript skillscript = null;
		if(model.getQ_skill_script() > 0){
			skillscript = (ISkillScript) ManagerPool.scriptManager.getScript(model.getQ_skill_script());
		}

		Map map = ManagerPool.mapManager.getMap(attacker.getServerId(), attacker.getLine(), attacker.getMap());
		if (map == null) {
			return;
		}
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		//中心点
		Position center = null;
		if (model.getQ_area_target() == 1) {
			//自身
			Grid grid = MapUtils.getGrid(attacker.getPosition(), grids);
			center = grid.getCenter();
		} else {
			//敌人
			Grid grid = MapUtils.getGrid(defender.getPosition(), grids);
			center = grid.getCenter();
		}

		List<Fighter> fighters = null;

		//设置敌人类型 0-全部, 1-玩家和宠物, 2-怪物
		int type = 0;
		//玩家类型选择 0-和平 1-组队 2-帮会 3-全体
		int playerAttackType = 0;

		if (attacker instanceof Player || attacker instanceof Pet) {
			Player role = null;
			if (attacker instanceof Player) {
				role = (Player) attacker;
			}
			if (attacker instanceof Pet) {
				role = PlayerManager.getInstance().getPlayer(((Pet) attacker).getOwnerId());
			}
			//人物pk模式
			playerAttackType = role.getPkState();
			//人物是否站在安全区内
			if (ManagerPool.mapManager.isSafe(role.getPosition(), map.getMapModelid())) {
				playerAttackType = 0;
			}
			//自己等级检查
			if (role.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				playerAttackType = 0;
			}
		} else if (attacker instanceof Monster) {
			type = 0;
			playerAttackType = 3;
		}

		if (attacker instanceof Player && defender != null) {
			ManagerPool.petOptManager.onwerAttack((Player) attacker, defender, 0);
		}
		
		boolean issingle = false;
		//点
		if (model.getQ_area_shape() == 1) {
			issingle = true;
			fighters = new ArrayList<Fighter>();
			if(checkCanAttack(attacker, defender)){
				fighters.add(defender);
			}
		} //线形
		else if (model.getQ_area_shape() == 2) {
			//计算角度
			direction = countDirection(direction);
			//测试用 显示范围
			if (attacker instanceof Player && syncArea.contains(attacker.getId())) {
				showGridInLine((Player) attacker, center, direction, model.getQ_area_width(), model.getQ_area_length(), grids);
			}
			//获取范围内战斗者
			fighters = getFighterInLine(attacker, center, getFighters(center, map, model.getQ_area_length(), type), direction, model.getQ_area_width(), model.getQ_area_length(), playerAttackType, grids);
		} //锥形
		else if (model.getQ_area_shape() == 3) {
			//计算角度
			direction = countDirection(direction);
			//测试用 显示范围
			if (attacker instanceof Player && syncArea.contains(attacker.getId())) {
				showGridInSector((Player) attacker, center, direction, model.getQ_sector_start(), model.getQ_sector_radius(), grids);
			}
			//获取范围内战斗者
			fighters = getFighterInSector(attacker, center, getFighters(center, map, model.getQ_sector_radius(), type), direction, model.getQ_sector_start(), model.getQ_sector_radius(), playerAttackType, grids);
		} //圆形
		else if (model.getQ_area_shape() == 4) {
			//测试用 显示范围
			if (attacker instanceof Player && syncArea.contains(attacker.getId())) {
				showGridInCircle((Player) attacker, center, model.getQ_circular_radius(), grids);
			}
			//获取范围内战斗者
			fighters = getFighterInCircle(attacker, center, getFighters(center, map, model.getQ_circular_radius(), type), model.getQ_circular_radius(), playerAttackType, grids);
		}
		
		if(fighters==null || fighters.size()==0) return;
		
		//随机选择战斗者
		fighters = randomSelectFighters(fighters, model.getQ_target_max());

//		//攻击前可触发Skill
//		List<Skill> beforeAttackSkills = null;
		//攻击可触发Skill
		List<Skill> attackSkills = null;
		
		List<Skill> bowSkills = null;
		boolean triggerBow = false;
		int triggerNum = 0;
		int maxTriggerNum = 0;
		boolean triggerHorseWeapon = false;
		//攻击者可触发Buff
		List<Buff> attackBuffs = null;
		if(trigger){
			if (attacker instanceof Player) {
	//			beforeAttackSkills = ManagerPool.skillManager.getSkillTriggerBeforeAttack((Player)attacker);
				attackSkills = ManagerPool.skillManager.getSkillTriggerByAttack((Player) attacker);

				// 暗器触发
				if (fighters.get(0) != null) {
					triggerHiddenWeaponSkill(fightId, (Player) attacker, fighters.get(0), direction);
				}

			} else if (attacker instanceof Pet) {
				attackSkills = ManagerPool.skillManager.getPetSkillTriggerByAttack((Pet) attacker);
			}
			
			
			if (attacker instanceof Player) {
				bowSkills = ManagerPool.arrowManager.tiggerSkillList((Player) attacker);
				maxTriggerNum = ManagerPool.arrowManager.tiggerSkillNum((Player) attacker);
			}
			
			attackBuffs = ManagerPool.buffManager.getBuffTriggerByAttack(attacker);
		}

		FighterInfo attackerinfo = new FighterInfo();
		attackerinfo.copyFighter(attacker);
		
		boolean topHorseFlag = true;
		for (int i = 0; i < fighters.size(); i++) {
			Fighter fighter = fighters.get(i);
			
			if (fighter instanceof Pet) {
				Player owner = ManagerPool.playerManager.getPlayer(((Pet) fighter).getOwnerId());
				if(owner.isDie()) continue;
			}
			
			//攻击结果0-成功 1-MISS 2-跳闪 4-暴击  8-格挡 6-跳闪+暴击 12-格挡+暴击 16-无敌 32-死亡中被打 64-被秒杀
			FightResult fightResult = new FightResult();
			//无敌
			if (FighterState.WUDI.compare(fighter.getFightState())) {
				fightResult.fightResult = 16;
				MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, 0, 0, 0));
				continue;
			}
			//造成伤害
			if (model.getQ_trigger_figth_hurt() == 1) {

				boolean sidestep = true;
				if(skillscript!=null){
					try{
						//技能是否能被跳闪
						sidestep = skillscript.canJumpSidestep(attacker, fighter);
					}catch(Exception e){
						log.error(e, e);
					}
				}
				if(sidestep){
					if (!ManagerPool.skillManager.isIgnoreJumpMiss(model) && fighter instanceof Player) {
						//TODO 跳跃中闪避
						//if (PlayerState.JUMP.compare(((Player) fighter).getState()) || PlayerState.DOUBLEJUMP.compare(((Player) fighter).getState())) {
						if(((Player) fighter).isJumpProtect()){
							fightResult.fightResult = 2;
						}
						
					} else if (fighter instanceof Pet) {
						//跳跃中闪避
						if (((Pet) fighter).getJumpState() != PetJumpState.NOMAL) {
							fightResult.fightResult = 2;
						}
					}
				}

				//对象死亡
				if (fighter.isDie()) {
//					fighter.setReduce(0);
					continue;
				}
				//死亡中被打特殊
				if (fighter instanceof Monster && MonsterState.DIEING.compare(((Monster) fighter).getState())) {
					fightResult.fightResult = 32;
//					fighter.setReduce(0);
					MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, 0, 0, 0));
					ManagerPool.monsterManager.die((Monster) fighter, attacker);
					continue;
				}

				//连击秒怪
				if (fighter instanceof Monster && attacker instanceof Player) {
					Monster monster = (Monster) fighter;
					Player player = (Player) attacker;
					if (!monster.isDie() && ManagerPool.batterManager.checkEvencut(player, monster)) {
						fightResult.fightResult = 64;
//						fighter.setReduce(0);
						addHatred(monster, player, 100);
						int hp = monster.getHp();
						monster.getDamages().put(player.getId(), hp);
						monster.setHp(0);
						MonsterManager.getInstance().die(monster, player);
						MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, hp, 0, 0));
						continue;
					}
				}

				if(!(attacker instanceof Pet || fighter instanceof Pet)){
					//未跳闪
					if (fightResult.fightResult == 0) {
						
						if(!ManagerPool.skillManager.isIgnoreMiss(model) && FighterState.FORCEDODGE.compare(fighter.getFightState())){
							fightResult.fightResult = 1;
							ManagerPool.buffManager.removeByType(fighter, BuffType.ZIMANGBUFF);
//							fighter.setReduce(0);
							MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, 0, 0, 0));
							continue;
						}
	//					//怪物死亡中状态不会闪避
	//					if(!(fighter instanceof Monster && MonsterState.DIEING.compare(((Monster)fighter).getState()))){
						//命中计算
						int dodge = 0;//(int)(Global.MAX_PROBABILITY * ((double)fighter.getDodge() / (fighter.getDodge() + attacker.getDodge())  * (double)fighter.getLevel() / (fighter.getLevel() + attacker.getLevel())));
						if (fighter.getDodge() == 0) {
							dodge = 0;
						} else {
	//						 (int) (10000 * ((double) 被攻击者闪避 / (被攻击者闪避 + 攻击者闪避) * 被攻击者等级/ (被攻击者等级 + 攻击者等级)));
							dodge = (int) (Global.MAX_PROBABILITY * ((double) fighter.getDodge() / (fighter.getDodge() + attacker.getDodge()) * (double) fighter.getLevel() / (fighter.getLevel() + attacker.getLevel())));
						}
						if (fighter instanceof Monster) {
							dodge = (int) ((double) dodge * 0.2);
						}
						log.debug("闪避值" + dodge + "-->攻击者名字" + attacker.getName() + "攻击者等级" + attacker.getLevel() + "攻击者闪避" + attacker.getDodge() + "防御者名字" + fighter.getName() + "防御者等级" + fighter.getLevel() + "防御者闪避" + fighter.getDodge());
						if (dodge < 500) {
							dodge = 500;
						} else if (dodge > 9500) {
							dodge = 9500;
						}
						if (!ManagerPool.skillManager.isIgnoreMiss(model) && RandomUtils.random(Global.MAX_PROBABILITY) < dodge) {
							fightResult.fightResult = 1;
//							fighter.setReduce(0);
							MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, 0, 0, 0));
							continue;
						}
	//					}
	
						if (fighter instanceof Player) {
							if (isWudi((Player) fighter)) {
								fightResult.fightResult = 1;
//								fighter.setReduce(0);
								MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, 0, 0, 0));
								continue;
							}
						}
					}
				}

				int critMul = Global.MAX_PROBABILITY;
				//暴击buff
				List<Buff> critBuffs = ManagerPool.buffManager.getBuffByType(attacker, BuffType.MULCRIT);
				if (critBuffs.size() > 0) {
					Buff critBuff = critBuffs.get(0);
					int result = critBuff.action(attacker, attacker);
					if (result == 2) {
						//移除buff
						ManagerPool.buffManager.removeBuff(attacker, critBuff);
					}
					Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(critBuff.getModelId());
					critMul = buffModel.getQ_effect_ratio();
					fightResult.fightResult = fightResult.fightResult | 4;
				}

				//皇权buff
				Buff powerBuff = null;
				if ((fightResult.fightResult & 4) == 0) {
					List<Buff> powerBuffs = ManagerPool.buffManager.getBuffByType(attacker, BuffType.BAQUANBUFF);
					if (powerBuffs.size() > 0) {
						powerBuff = powerBuffs.get(0);
						Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(powerBuff.getModelId());
						if (RandomUtils.random(Global.MAX_PROBABILITY) < buffModel.getQ_trigger_probability()) {
							//暴击倍率
							critMul = Global.MAX_PROBABILITY * Global.CRIT_MULTIPLE;
							fightResult.fightResult = fightResult.fightResult | 4;
						} else {
							powerBuff = null;
						}
					}
				}

				//未暴击过
				if ((fightResult.fightResult & 4) == 0) {
					//命中计算
					int crit = 0;//(int)(Global.MAX_PROBABILITY * ((double)attacker.getCrit() / (fighter.getCrit() + attacker.getCrit())  * (double)attacker.getLevel() / (fighter.getLevel() + attacker.getLevel())));
					if (fighter.getCrit() == 0) {
						crit = 0;
					} else {
						crit = (int) (Global.MAX_PROBABILITY * ((double) attacker.getCrit() / (fighter.getCrit() + attacker.getCrit()) * (double) attacker.getLevel() / (fighter.getLevel() + attacker.getLevel())));
					}
					if (attacker instanceof Monster) {
						crit = (int) ((double) crit * 0.2);
					}else if(attacker instanceof Player){
						crit = (int) ((double) crit * 0.4);
					}
					log.debug("暴击值" + crit + "-->攻击者名字" + attacker.getName() + "攻击者等级" + attacker.getLevel() + "攻击者暴击" + attacker.getCrit() + "防御者名字" + fighter.getName() + "防御者等级" + fighter.getLevel() + "防御者暴击" + fighter.getCrit());
					if (crit < 500) {
						crit = 500;
					} else if (crit > 5000) {
						crit = 5000;
					}
					if (RandomUtils.random(Global.MAX_PROBABILITY) < crit) {
						//暴击倍率
						critMul = Global.MAX_PROBABILITY * Global.CRIT_MULTIPLE;
						fightResult.fightResult = fightResult.fightResult | 4;
					}
				}

				if(attacker instanceof Pet){
					fightResult.damage = attacker.getAttack(skill);
				}else{
					//计算伤害
					fightResult.damage = attacker.getAttack(skill);
				}
				if (fightResult.damage < 0) {
					fightResult.damage = 0;
				}
				fightResult.backDamage += model.getQ_ignore_defence();
				if (attacker instanceof Monster) {
					//怪物无视防御
					fightResult.backDamage += ((Monster) attacker).getIgnoreDamage();
				} else if (attacker instanceof Player) {
					fightResult.backDamage += ((Player) attacker).getNegDefence();//境界属性 无视防御
					if (fighter instanceof Monster) {
						Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(((Monster) fighter).getModelId());
						if (q_monsterBean != null) {
							if (q_monsterBean.getQ_monster_type()==3) {
								fightResult.hitDamage = ManagerPool.batterManager.getbossBatter(((Player) attacker));
								fightResult.backDamage += fightResult.hitDamage;
							}else if (q_monsterBean.getQ_monster_type()==1){
								fightResult.backDamage += ((Player) attacker).getEvencutatk();
							}
						}
						
					}
				}

				//随即攻击比例（最大攻击比例和最小攻击比例之间）
				int max_damage = 12000;
				int min_damage = 8000 + attacker.getLuck() * 35;
				int percent = RandomUtils.random(min_damage, max_damage);

				int defense = fighter.getDefense();
				if (ManagerPool.skillManager.isIgnoreDefense(model)) {
					defense = 0;
				}
//				//计算被攻击者防御减少
//				int defenseMul = Global.MAX_PROBABILITY;
//				//计算被攻击者防御减少buff
//				List<Buff> redefenseBuffs = ManagerPool.buffManager.getBuffByType(fighter, BuffType.MEIHUABUFF);
//				if ((fightResult & 2) == 0 && redefenseBuffs.size() > 0) {
//					for (int j = 0; j < redefenseBuffs.size(); j++) {
//						Buff redefenseBuff = redefenseBuffs.get(j);
//						redefenseBuff.action(fighter, fighter);
//						defenseMul += redefenseBuff.getParameter();
//					}
//				}
//				if(defenseMul < 0){
//					defenseMul = 0;
//				}
//				defense = (int)((double)defense * defenseMul / Global.MAX_PROBABILITY);
				
				if(!(attacker instanceof Pet)){
					fightResult.damage = fightResult.damage - defense;
				}
				//计算伤害加成
				fightResult.damage = (int) (((double) fightResult.damage) * model.getQ_hurt_correct_factor() / Global.MAX_PROBABILITY);
				if (fightResult.damage < 0) {
					fightResult.damage = 0;
				}
				fightResult.damage = (int) ((double) fightResult.damage * percent / Global.MAX_PROBABILITY * critMul / Global.MAX_PROBABILITY);
				//backDamage = backDamage * percent / Global.MAX_PROBABILITY * model.getQ_hurt_correct_factor() / Global.MAX_PROBABILITY * critMul / Global.MAX_PROBABILITY;

				//计算被攻击者伤害加深
				int damageMul = Global.MAX_PROBABILITY;
				//计算被攻击者伤害加深buff
				List<Buff> damageBuffs = ManagerPool.buffManager.getBuffByType(fighter, BuffType.DEEPENDAMAGE);
				if ((fightResult.fightResult & 2) == 0 && damageBuffs.size() > 0) {
					for (int j = 0; j < damageBuffs.size(); j++) {
						Buff damageBuff = damageBuffs.get(j);
						damageBuff.action(fighter, fighter);
						damageMul += damageBuff.getParameter();
					}
				}
				//伤害加深
				fightResult.damage = (int) ((double) fightResult.damage * damageMul / Global.MAX_PROBABILITY);
				//backDamage = backDamage * damageMul / Global.MAX_PROBABILITY ;

				log.debug("attacker " + attacker.getName() + " damage " + fightResult.damage + " backdamage " + fightResult.backDamage);

				if (fightResult.damage < 0) {
					fightResult.damage = 0;
				}

				//伤害减少
				if (fighter instanceof Player) {
					//格挡中 墨子剑法Buff
					if (PlayerState.BLOCK.compare(((Player) fighter).getState())) {
						fightResult.fightResult = fightResult.fightResult | 8;
						//减少伤害
						int reduceDamage = (int) ((double) fightResult.damage * ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_REDUCTION.getValue()).getQ_int_value() / Global.MAX_PROBABILITY);
						fightResult.damage -= reduceDamage;
						//只反弹前3式的
						if (!FighterState.FANTAN.compare(fighter.getFightState())) {
							fightResult.damage += fightResult.backDamage;
							fightResult.backDamage = 0;
						} else {
							boolean rebound = false;
							List<Buff> buffs = ManagerPool.buffManager.getBuffByType(fighter, BuffType.REBOUND);
							for (int j = 0; j < buffs.size(); j++) {
								Buff buff = buffs.get(j);
								Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
								if (buffModel == null) {
									continue;
								}
								String[] rebounds = buffModel.getQ_Bonus_skill().split(Symbol.DOUHAO_REG);
								for (int k = 0; k < rebounds.length; k++) {
									if (skill.getSkillModelId() == Integer.parseInt(rebounds[k])) {
										rebound = true;
										break;
									}
								}
								if (rebound) {
									break;
								}
							}
							if (!rebound) {
								fightResult.damage += fightResult.backDamage;
								fightResult.backDamage = 0;
							}
						}
					} else {
						fightResult.damage += fightResult.backDamage;
						fightResult.backDamage = 0;
					}
				} else {
					fightResult.damage += fightResult.backDamage;
					fightResult.backDamage = 0;
				}

				if(skillscript!=null){
					try{
						//技能附加伤害
						skillscript.damage(attacker, fighter, fightResult);
					}catch(Exception e){
						log.error(e, e);
					}
				}
				
				//固定伤害型怪物
				if (fighter instanceof Monster) {
					//获得怪物模型
					Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(((Monster) fighter).getModelId());
					//收到固定伤害
					if (monsterModel.getQ_fixed_hurt() == 1) {
						fightResult.damage = monsterModel.getQ_fiexd_value();
					}
				}

				//侍宠固定掉血
				else if(fighter instanceof Pet){
					//获得侍宠模型
					Q_petinfoBean petModel = ManagerPool.dataManager.q_petinfoContainer.getMap().get(((Pet) fighter).getModelId());
					//收到固定伤害
					fightResult.damage = petModel.getQ_fiexd_value();
				}
				
//				if (fighter.getReduce() > 0) {
//					fightResult.damage = fightResult.damage - fighter.getReduce();
//					fighter.setReduce(0);
//				}

				if (fightResult.damage < 0) {
					fightResult.damage = 0;
				}

				//受到伤害脚本触发
				IHitDamageScript script = (IHitDamageScript) ManagerPool.scriptManager.getScript(ScriptEnum.HIT_DAMAGE);
				if (script != null) {
					try {
						script.onDamage(attacker, fighter, fightResult);
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					log.error("攻击伤害脚本不存在！");
				}

				//未跳跃闪避
				if ((fightResult.fightResult & 2) == 0) {

					FighterInfo fighterinfo = new FighterInfo();
					fighterinfo.copyFighter(fighter);

					fighter.setHp(fighter.getHp() - fightResult.damage);
					if (fighter.getHp() < 0) {
						fighter.setHp(0);
					}

					//技能可造成仇恨
					if (model.getQ_trigger_figth_hurt() > 0 && fightResult.damage > 0) {
						if (attacker instanceof Player) {
							ManagerPool.petOptManager.onwerDamage((Player) attacker, fighter, fightResult.damage);
						}

						if (attacker instanceof Player && (fighter instanceof Player || fighter instanceof Pet)) {
							//主动Pk移除被杀保护Buff
							if (FighterState.PKBAOHU.compare(attacker.getFightState())) {
								ManagerPool.buffManager.removeByBuffId((Player) attacker, Global.PROTECT_FOR_KILLED);
								if(fighter instanceof Player){
									log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对玩家(" + fighter.getId() + ")PK状态为(" + ((Player)fighter).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)fighter).getState()) + ")群体攻击导致和平保护buff消失");
								}else{
									log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对宠物(" + fighter.getId() + ")群体攻击导致和平保护buff消失");
								}
								MessageUtil.notify_player((Player) attacker, Notifys.NORMAL, "您主动发起了对其他玩家的PK，和平保护BUFF消失了");
							}

							//主动Pk移除夜晚保护Buff
							if (FighterState.PKBAOHUFORNIGHT.compare(attacker.getFightState())) {
								ManagerPool.buffManager.removeByBuffId((Player) attacker, Global.PROTECT_IN_NIGHT);
								if(fighter instanceof Player){
									log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对玩家(" + fighter.getId() + ")PK状态为(" + ((Player)fighter).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)fighter).getState()) + ")群体攻击导致夜晚和平保护buff消失");
								}else{
									log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对宠物(" + fighter.getId() + ")群体攻击导致夜晚和平保护buff消失");
								}
							}
						}

						if (fighter instanceof Monster) {
							Monster monster = (Monster) fighter;

							Player owner = null;
							//增加敌对对象
							if (attacker instanceof Player) {
								owner = (Player) attacker;
								//增加连击
								if (ManagerPool.monsterManager.isBoss(((Monster) fighter).getModelId())) {
									ManagerPool.batterManager.bossBatter(owner);
								}
							} else if (attacker instanceof Pet) {
								owner = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
							}
							
							if(owner!=null){
								//增加仇恨
								addHatred(monster, owner, model.getQ_enmity());
								//增加伤害统计
								if (monster.getDamages().containsKey(owner.getId())) {
									monster.getDamages().put(owner.getId(), monster.getDamages().get(owner.getId()) + fightResult.damage);
								} else {
									monster.getDamages().put(owner.getId(), fightResult.damage);
								}
	
								//怪物受到伤害脚本
								Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
								if (q_monsterBean != null) {
									if (q_monsterBean.getQ_script_id() > 0) {
										IMonsterAiScript aiScript = (IMonsterAiScript) ManagerPool.scriptManager.getScript(q_monsterBean.getQ_script_id());
										if (aiScript != null) {
											aiScript.wasHit(monster,attacker);
										}
									}
								}
							}
						} else if (fighter instanceof Player) {
							//增加敌对对象
							if (attacker instanceof Player) {
								if(!((Player) attacker).getEnemys().containsKey(fighter.getId())){
									((Player) fighter).addEnemy((Player) attacker);
								}
								((Player) fighter).getHits().add(attacker.getId());
							} else if (attacker instanceof Pet) {
								Player aOwner = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
								if(!aOwner.getEnemys().containsKey(fighter.getId())){
									((Player) fighter).addEnemy(aOwner);
								}
								((Player) fighter).getHits().add(aOwner.getId());
							}
							ManagerPool.petOptManager.ownerDefence(attacker, (Player) fighter, fightResult.damage);
							//玩家进入战斗状态
							if (!fighter.isDie() && !(attacker instanceof Monster)) {
								((Player) fighter).setState(PlayerState.FIGHT);
							}
							
							//人物受到伤害脚本
							IPlayerWasHitScript hscript = (IPlayerWasHitScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYERWASHIT);
							if (hscript != null) {
								try{
									hscript.wasHit(attacker, (Player) fighter);
								}catch (Exception e) {
									log.error(e, e);
								}
							}
						} else if (fighter instanceof Pet) {
							Player owner = ManagerPool.playerManager.getPlayer(((Pet) fighter).getOwnerId());
							//增加敌对对象
							if (attacker instanceof Player) {
								if(!((Player) attacker).getEnemys().containsKey(owner.getId())){
									if(!owner.isDie()) owner.addEnemy((Player) attacker);
								}
								((Player) owner).getHits().add(attacker.getId());
							} 
//							else if (attacker instanceof Pet) {
//								Player aOwner = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
//								owner.addEnemy(aOwner);
//							}
							ManagerPool.petOptManager.petDefence(attacker, (Pet) fighter, fightResult.damage);
							//玩家进入战斗状态
							if (!owner.isDie()) {
								owner.setState(PlayerState.FIGHT);
							}
							
							//宠物受到伤害脚本
							IPetWasHitScript hscript = (IPetWasHitScript) ManagerPool.scriptManager.getScript(ScriptEnum.PETWASHIT);
							if (hscript != null) {
								try{
									hscript.wasHit(attacker, (Pet) fighter);
								}catch (Exception e) {
									log.error(e, e);
								}
							}
						}
					}

					//怪物或玩家死亡
					if (fighter.getHp() == 0) {
						if (fighter instanceof Monster) {
							ManagerPool.monsterManager.die((Monster) fighter, attacker);
						} else if (fighter instanceof Player) {
							ManagerPool.playerManager.die((Player) fighter, attacker);
						} else if (fighter instanceof Pet) {
							ManagerPool.petOptManager.die((Pet) fighter, attacker);
						}
					}

					//反弹伤害
					if (fightResult.backDamage > 0 && !attacker.isDie()) {
						attacker.setHp(attacker.getHp() - fightResult.backDamage);
						if (attacker.getHp() < 0) {
							attacker.setHp(0);
						}

						if (attacker instanceof Monster) {
							Monster monster = (Monster) attacker;
							//增加伤害统计
							if (monster.getDamages().containsKey(fighter.getId())) {
								monster.getDamages().put(fighter.getId(), monster.getDamages().get(fighter.getId()) + fightResult.backDamage);
							} else {
								monster.getDamages().put(fighter.getId(), fightResult.backDamage);
							}
						}

						//怪物或玩家死亡
						if (attacker.getHp() == 0) {
							if (attacker instanceof Monster) {
								ManagerPool.monsterManager.die((Monster) attacker, fighter);
							} else if (attacker instanceof Player) {
								ManagerPool.playerManager.die((Player) attacker, fighter);
							} else if (attacker instanceof Pet) {
								ManagerPool.petOptManager.die((Pet) attacker, fighter);
							}
						}
					}

					//攻击者未死亡
					if (!attacker.isDie()) {
						//log.error("fightid:" + fightId + ",monster:" + fighter.getId() + "addbuff:" + skill.getSkillModelId());
						//触发进攻技能
						if (skill.getSkillModelId() == 25027) { // 断骨草的群攻触发技能,仅仅生效一次
							if (topHorseFlag) {
								ManagerPool.skillManager.triggerSkill(attacker, fighter, skill, true);
								topHorseFlag = false;
							}
						} else {
							ManagerPool.skillManager.triggerSkill(attacker, fighter, skill, true);
						}
						
						if(trigger){
							//弓箭被动
							if(attacker instanceof Player){
	 							if(!ManagerPool.buffManager.isHaveLongyuanBuff(fighter)){
									List<Skill> horseWeaponSkills = ManagerPool.horseWeaponManager.getHorseWeaponSkillTriggerByAttack((Player)attacker);
									for (int j = 0; j < horseWeaponSkills.size() - 1; j++) {
										if(ManagerPool.skillManager.triggerSkill(attacker, fighter, horseWeaponSkills.get(j), true)>0){
											break;
										}
									}
								}
							}
						
							if (powerBuff != null) {
								powerBuff.action(attacker, fighter);
							}
	
							if (attackSkills != null) {
								//计算攻击触发Skill
								for (int j = 0; j < attackSkills.size(); j++) {
									ManagerPool.skillManager.triggerSkill(attacker, fighter, attackSkills.get(j), false);
								}
							}
							
							if (attackBuffs != null) {
								//计算攻击触发Buff
								for (int j = 0; j < attackBuffs.size(); j++) {
									ManagerPool.buffManager.triggerBuff(attacker, fighter, attackBuffs.get(j));
								}
							}
						}
					}

					if (!fighter.isDie() && trigger) {
						//睡眠buff
						List<Buff> sleepBuffs = ManagerPool.buffManager.getBuffByType(fighter, BuffType.SLEEP);
						if (sleepBuffs.size() > 0) {
							//移除睡眠buff
							for (int j = 0; j < sleepBuffs.size(); j++) {
								Buff sleepBuff = sleepBuffs.get(j);
								sleepBuff.remove(fighter);
							}
						}

						//防御者可触发Skill
						List<Skill> defenseSkills = null;
						if (fighter instanceof Player) {
							defenseSkills = ManagerPool.skillManager.getSkillTriggerByDefense((Player) fighter);
						} else if (fighter instanceof Pet) {
							defenseSkills = ManagerPool.skillManager.getPetSkillTriggerByDefense((Pet) fighter);
						}

						//防御者可触发Buff
						List<Buff> defenseBuffs = ManagerPool.buffManager.getBuffTriggerByDefense(fighter);

						if (defenseSkills != null) {
							//计算攻击触发Skill
							for (int j = 0; j < defenseSkills.size(); j++) {
								Skill defenseSkill = defenseSkills.get(j);
								//紫芒技能
								if(defenseSkill.getSkillModelId()==Global.ZIMANG_SKILL){
									//暴击
									if(!(attacker instanceof Monster) && (fightResult.fightResult&4)>0 && fightResult.damage>(fighter.getMaxHp()/2)){
										ManagerPool.skillManager.triggerSkill(fighter, attacker, defenseSkills.get(j), false);
									}
								}else{
									ManagerPool.skillManager.triggerSkill(fighter, attacker, defenseSkills.get(j), false);
								}
							}
						}

						if (defenseBuffs != null) {
							//计算攻击触发Buff
							for (int j = 0; j < defenseBuffs.size(); j++) {
								ManagerPool.buffManager.triggerBuff(fighter, attacker, defenseBuffs.get(j));
							}
						}

						if (fighter instanceof Pet) {
							//美人被攻击 进入战斗状态
							Pet pet = (Pet) fighter;
							pet.setLastFightTime(System.currentTimeMillis());
						}
					}
					try{
						//攻击者未死亡
						if (!attacker.isDie() && (attacker instanceof Player) && trigger) {
							if (bowSkills != null && !triggerBow) {// && (fighter instanceof Player)
								//计算攻击触发Skill
								for (int j = 0; j < bowSkills.size(); j++) {
									boolean triggerResult = triggerBowSkill((Player)attacker, fighter, bowSkills.get(j),issingle);//ManagerPool.skillManager.triggerSkill(attacker, fighter, bowSkills.get(j), false);
									if(triggerResult){
										triggerBow = true;
										triggerNum++;
										if(triggerNum >= maxTriggerNum) break;
									}
								}
							}
							
							if(!triggerHorseWeapon){
								//长虹贯日
								List<Skill> horseWeaponSkills = ManagerPool.horseWeaponManager.getHorseWeaponSkillTriggerByAttack((Player)attacker);
								if(horseWeaponSkills.size() > 0){
									triggerBowSkill((Player)attacker, fighter, horseWeaponSkills.get(horseWeaponSkills.size() - 1),issingle);
									triggerHorseWeapon = true;
								}
							}
						}
					}catch (Exception e) {
						log.error(e, e);
					}
//					if(fighter instanceof Player||attacker instanceof Player){
//						//玩家攻击  或者被攻击时 通知到宠物
//						PetManager.getInstance().playerAttack(attacker, fighter);
//					}
					//比较变化 发送消息
					fighterinfo.compareFighter(fighter);
				}

				MessageUtil.tell_round_message(fighter, getAttackResultMessage(fightId, attacker, fighter, skill, fightResult.fightResult, fightResult.damage, fightResult.hitDamage, fightResult.backDamage));
			}
		}
		//比较变化 发送消息
		attackerinfo.compareFighter(attacker);
	}

	private boolean triggerHiddenWeaponSkill(long oldFightId, Player player, Fighter defender, int direction) {
		Skill skill = ManagerPool.hiddenWeaponManager.getHiddenWeaponSkillTriggerByAttack(player);
		if (skill == null) {
			return false;
		}

		//获得技能模板
		Q_skill_modelBean model= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
		if (model == null) return false;
		
		//技能冷却
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))){
			return false;
		}
		//技能公共冷却
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))){
			return false;
		}

		//开始冷却
		double speed = 1 + ((double) player.getAttackSpeed()) / 1000;
		//添加技能冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long) (model.getQ_cd() / speed));
		//添加技能公共冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long) (model.getQ_public_cd() / speed));

		long fightId = Config.getId();
		
		// 通知前端显示特效
		ResHiddenWeaponSkillTriggerMessage msg = new ResHiddenWeaponSkillTriggerMessage();
		msg.setPlayerid(player.getId());
		msg.setSkill(skill.getSkillModelId());
		msg.setLevel(skill.getSkillLevel());
		MessageUtil.tell_round_message(player, msg);
		

		// 如果是对自己施放buff,那就不走攻击流程
		if (model.getQ_target() == 1) {
			if (ManagerPool.skillManager.triggerSkill(player, defender, skill, true) == 1) {
				ResAttackResultMessage retMsg = ManagerPool.fightManager.getAttackResultMessage(oldFightId, player, player, skill, 0, 0, 0, 0);
				MessageUtil.tell_round_message(player, retMsg);
			}
			ManagerPool.hiddenWeaponManager.countNextIco(player);
			return true;
		}
		
		if (!ManagerPool.skillManager.canTrigger(player, defender, skill)) {
			ManagerPool.hiddenWeaponManager.countNextIco(player);
			return false;
		}

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, player, defender, skill.getSkillModelId(), -1));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();
		//单体攻击
		if (model.getQ_area_shape() == 1 && defender != null) {
			fly += MapUtils.countDistance(player.getPosition(), defender.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, player, defender, skill, direction, fly, false);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, player, defender, skill, direction, false);
		}
		
		ManagerPool.hiddenWeaponManager.countNextIco(player);
		return true;
	}

	/**
	 * 伤害(不会触发任何buff)
	 *
	 * @param attacker 攻击者
	 * @param defender 防御者
	 */
	public void damage(Fighter attacker, Fighter defender, int damage, int hate) {
		//死亡检查
		if (attacker.isDie()) {
			return;
		}
		if (defender != null && defender.isDie()) {
			return;
		}
		if (defender != null) {
			if (attacker.getLine() != defender.getLine() || attacker.getMap() != defender.getMap()) {
				return;
			}
			
			if(!checkCanAttack(attacker, defender)){
				return;
			}
		}
		
		Map map = ManagerPool.mapManager.getMap(attacker.getServerId(), attacker.getLine(), attacker.getMap());
		if (map == null) {
			return;
		}

		//攻击结果0-成功 1-MISS 2-跳闪 4-暴击  8-格挡 6-跳闪+暴击 12-格挡+暴击 16-无敌
		FightResult fightResult = new FightResult();
		fightResult.damage = damage;
		//无敌
		if (FighterState.WUDI.compare(defender.getFightState())) {
			fightResult.fightResult = 16;
			MessageUtil.tell_round_message(defender, getAttackResultMessage(0, attacker, defender, null, fightResult.fightResult, 0, 0, 0));
			return;
		}

		//减少伤害
		int reduceDamage = 0;

		if (defender instanceof Player) {
			//跳跃中闪避
			if(((Player) defender).isJumpProtect()){
				fightResult.fightResult = 2;
			}
			
		} else if (defender instanceof Pet) {
			//跳跃中闪避
			if (((Pet) defender).getJumpState() != PetJumpState.NOMAL) {
				fightResult.fightResult = 2;
			}
		}

		//无敌
		if (FighterState.WUDI.compare(defender.getFightState())) {
			fightResult.fightResult = 16;
			MessageUtil.tell_round_message(defender, getAttackResultMessage(0, attacker, defender, null, fightResult.fightResult, 0, 0, 0));
			return;
		}

		//死亡中被打特殊
		if (defender instanceof Monster && MonsterState.DIEING.compare(((Monster) defender).getState())) {
			fightResult.fightResult = 32;
			MessageUtil.tell_round_message(defender, getAttackResultMessage(0, attacker, defender, null, fightResult.fightResult, 0, 0, 0));
			ManagerPool.monsterManager.die((Monster) defender, attacker);
			return;
		}


		//伤害减少
		if (defender instanceof Player) {
			//格挡中 墨子剑法Buff
			if (PlayerState.BLOCK.compare(((Player) defender).getState())) {
				fightResult.fightResult = fightResult.fightResult | 8;
				//减少伤害
				reduceDamage = (int) ((double) fightResult.damage * ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_REDUCTION.getValue()).getQ_int_value() / Global.MAX_PROBABILITY);
				fightResult.damage -= reduceDamage;
			}
		}

		//固定伤害型怪物
		if (defender instanceof Monster) {
			//获得怪物模型
			Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(((Monster) defender).getModelId());
			//收到固定伤害
			if (monsterModel.getQ_fixed_hurt() == 1) {
				fightResult.damage = monsterModel.getQ_fiexd_value();
			}
		}
		
		//侍宠固定掉血
		else if(defender instanceof Pet){
			//获得侍宠模型
			Q_petinfoBean petModel = ManagerPool.dataManager.q_petinfoContainer.getMap().get(((Pet) defender).getModelId());
			//收到固定伤害
			fightResult.damage = petModel.getQ_fiexd_value();
		}
		
		if (fightResult.damage < 0) {
			fightResult.damage = 0;
		}
		
		//受到伤害脚本触发
		IHitDamageScript script = (IHitDamageScript) ManagerPool.scriptManager.getScript(ScriptEnum.HIT_DAMAGE);
		if (script != null) {
			try {
				script.onDamage(attacker, defender, fightResult);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("攻击伤害脚本不存在！");
		}

		//未跳跃闪避
		if ((fightResult.fightResult & 2) == 0) {

			FighterInfo defenderinfo = new FighterInfo();
			defenderinfo.copyFighter(defender);

			defender.setHp(defender.getHp() - fightResult.damage);
			if (defender.getHp() < 0) {
				defender.setHp(0);
			}

			//技能可造成仇恨
			if (fightResult.damage > 0) {
				if (attacker instanceof Player) {
					ManagerPool.petOptManager.onwerAttack((Player) attacker, defender, fightResult.damage);
				}
				
				if (attacker instanceof Player && (defender instanceof Player || defender instanceof Pet)) {
					//主动Pk移除被杀保护Buff
					if (FighterState.PKBAOHU.compare(attacker.getFightState())) {
						ManagerPool.buffManager.removeByBuffId((Player) attacker, Global.PROTECT_FOR_KILLED);
						if(defender instanceof Player){
							log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对玩家(" + defender.getId() + ")PK状态为(" + ((Player)defender).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)defender).getState()) + ")群体攻击导致和平保护buff消失");
						}else{
							log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对宠物(" + defender.getId() + ")群体攻击导致和平保护buff消失");
						}
						MessageUtil.notify_player((Player) attacker, Notifys.NORMAL, "您主动发起了对其他玩家的PK，和平保护BUFF消失了");
					}

					//主动Pk移除夜晚保护Buff
					if (FighterState.PKBAOHUFORNIGHT.compare(attacker.getFightState())) {
						ManagerPool.buffManager.removeByBuffId((Player) attacker, Global.PROTECT_IN_NIGHT);
						if(defender instanceof Player){
							log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对玩家(" + defender.getId() + ")PK状态为(" + ((Player)defender).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)defender).getState()) + ")群体攻击导致夜晚和平保护buff消失");
						}else{
							log.error("玩家(" + attacker.getId() + ")PK状态为(" + ((Player)attacker).getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(((Player)attacker).getState()) + ")敌人列表为(" + MessageUtil.castListToString(((Player)attacker).getEnemys().values()) + ")对宠物(" + defender.getId() + ")群体攻击导致夜晚和平保护buff消失");
						}
					}
				}

				if (defender instanceof Monster) {
					Monster monster = (Monster) defender;
					Player owner = null;
					//增加敌对对象
					if (attacker instanceof Player) {
						owner = (Player) attacker;
					} else if (attacker instanceof Pet) {
						owner = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
					}
					
					if(owner!=null){
						//增加仇恨
						if (hate != 0) {
							ManagerPool.fightManager.addHatred(monster, owner, hate);
						}
						//增加伤害统计
						if (monster.getDamages().containsKey(owner.getId())) {
							monster.getDamages().put(owner.getId(), monster.getDamages().get(owner.getId()) + fightResult.damage);
						} else {
							monster.getDamages().put(owner.getId(), fightResult.damage);
						}
	
						//怪物受到伤害脚本
						Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
						if (q_monsterBean != null) {
							if (q_monsterBean.getQ_script_id() > 0) {
								IMonsterAiScript aiScript = (IMonsterAiScript) ManagerPool.scriptManager.getScript(q_monsterBean.getQ_script_id());
								if (aiScript != null) {
									aiScript.wasHit(monster,attacker);
								}
							}
						}
					}
				} else if (defender instanceof Player) {
					//增加敌对对象
					if (attacker instanceof Player) {
						if(!((Player) attacker).getEnemys().containsKey(defender.getId())){
							((Player) defender).addEnemy((Player) attacker);
						}
						((Player) defender).getHits().add(attacker.getId());
					} else if (attacker instanceof Pet) {
						Player aOwner = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
						if(!aOwner.getEnemys().containsKey(defender.getId())){
							((Player) defender).addEnemy(aOwner);
						}
						((Player) defender).getHits().add(aOwner.getId());
					}
					ManagerPool.petOptManager.ownerDefence(attacker, (Player) defender, fightResult.damage);
					//玩家进入战斗状态
					if (!defender.isDie() && !(attacker instanceof Monster)) {
						((Player) defender).setState(PlayerState.FIGHT);
						if (attacker instanceof Player) {
							((Player) attacker).setState(PlayerState.FIGHT);
						}
					}
					
					//人物受到伤害脚本
					IPlayerWasHitScript hscript = (IPlayerWasHitScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYERWASHIT);
					if (hscript != null) {
						try{
							hscript.wasHit(attacker, (Player)defender);
						}catch (Exception e) {
							log.error(e, e);
						}
					}
				} else if (defender instanceof Pet) {
					Player owner = ManagerPool.playerManager.getPlayer(((Pet) defender).getOwnerId());
					//增加敌对对象
					if (attacker instanceof Player) {
						if(!((Player) attacker).getEnemys().containsKey(owner.getId())){
							if(!owner.isDie()) owner.addEnemy((Player) attacker);
						}
						((Player) owner).getHits().add(attacker.getId());
					} 
					ManagerPool.petOptManager.petDefence(attacker, (Pet) defender, fightResult.damage);
					//玩家进入战斗状态
					if (!owner.isDie()) {
						owner.setState(PlayerState.FIGHT);
						if (attacker instanceof Player) {
							((Player) attacker).setState(PlayerState.FIGHT);
						}
					}
					
					//人物受到伤害脚本
					IPetWasHitScript hscript = (IPetWasHitScript) ManagerPool.scriptManager.getScript(ScriptEnum.PETWASHIT);
					if (hscript != null) {
						try{
							hscript.wasHit(attacker, (Pet)defender);
						}catch (Exception e) {
							log.error(e, e);
						}
					}
				}
				

				if (!defender.isDie()) {
					//睡眠buff
					List<Buff> sleepBuffs = ManagerPool.buffManager.getBuffByType(defender, BuffType.SLEEP);
					if (sleepBuffs.size() > 0) {
						//移除睡眠buff
						for (int j = 0; j < sleepBuffs.size(); j++) {
							Buff sleepBuff = sleepBuffs.get(j);
							sleepBuff.remove(defender);
						}
					}
				}
			}

			//怪物或玩家死亡
			if (defender.getHp() == 0) {
				if (defender instanceof Monster) {
					ManagerPool.monsterManager.die((Monster) defender, attacker);
				} else if (defender instanceof Player) {
					ManagerPool.playerManager.die((Player) defender, attacker);
				} else if (defender instanceof Pet) {
					ManagerPool.petOptManager.die((Pet) defender, attacker);
				}
			}

			//比较变化 发送消息
			defenderinfo.compareFighter(defender);
		}
		MessageUtil.tell_round_message(defender, getAttackResultMessage(0, attacker, defender, null, fightResult.fightResult, fightResult.damage, 0, 0));

	}
	
	/**
	 * 使用地面魔法
	 */
	public void useGroundMagic(Player player, Skill skill, int mapModelId, int line, Position pos){
		//获得地图
		Map map = ManagerPool.mapManager.getMap(player);
		//地图不存在
		if(map==null){
			return;
		}
		//地图与要释放的地图不一致
		if(map.getMapModelid()!=mapModelId || map.getLineId()!=line){
			return;
		}
		
		Grid[][] mapBlocks = ManagerPool.mapManager.getMapBlocks(mapModelId);
		Grid center = MapUtils.getGrid(pos, mapBlocks);
		//坐标是否超出范围
		if(center==null){
			return;
		}
		
		Q_skill_modelBean model = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
		//技能模版不存在
		if(model==null){
			return;
		}
		
		List<Grid> roundGrid = MapUtils.getRoundGrid(center, model.getQ_circular_radius(), mapBlocks);
		HashSet<Grid> newgrids = new HashSet<Grid>();
		for (Grid grid : roundGrid) {
			newgrids.add(grid);
		}
		
		Iterator<GroundMagic> iterator = map.getMagics().values().iterator();
		while (iterator.hasNext()) {
			GroundMagic groundMagic = (GroundMagic) iterator.next();
			for (Grid grid : groundMagic.getGrids()) {
				if(newgrids.contains(grid)){
					log.error("与其他地面魔法重合");
					return;
				}
			}
		}

		//地面魔法信息
		GroundMagic magic = new GroundMagic();
		magic.setId(Config.getId());
		magic.setSkill(skill);
		magic.setPosition(pos);
		magic.setMap((int)map.getId());
		magic.setMapmodelid(map.getMapModelid());
		magic.setLine(map.getLineId());
		//释放者id
		magic.setSourceId(player.getId());
		//分布格子
		magic.setGrids(roundGrid);
		//开始时间
		magic.setStartTime(System.currentTimeMillis());
		//持续时间
		magic.setLastTime(model.getQ_skill_time());
		
		map.getMagics().put(magic.getId(), magic);
		
		//地图上效果
		Effect effect = new Effect();
		effect.setId(magic.getId());
		effect.setEffectModelId(skill.getSkillModelId());
		effect.setPosition(pos);
		effect.setMap((int)map.getId());
		effect.setMapmodelid(map.getMapModelid());
		effect.setLine(map.getLineId());
		effect.setSourceId(magic.getId());
		effect.setServerId(player.getServerId());
		effect.setType((byte) 1);
		
		ManagerPool.mapManager.enterMap(effect);
		
		magic.setEffect(effect);
	}
	
	/**
	 * 移除地面魔法
	 * @param magic
	 */
	public void removeGroundMagic(GroundMagic magic){
		if(magic.getEffect()!=null){
			ManagerPool.mapManager.quitMap(magic.getEffect());
		}
	}

	/**
	 * 触发弓箭技能
	 * @param player
	 * @param skill
	 * @return
	 */
	private boolean triggerBowSkill(Player player, Fighter target, Skill skill,boolean issingle){
		if (!ManagerPool.skillManager.canTrigger(player, target, skill)) {
			return false;
		}
		
		//获得技能模板
		Q_skill_modelBean model= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
		if(model == null) return false;
		
		//单机技能额外+弓箭几率
		int arrowpro = model.getQ_passive_prob();
		if (issingle) {
			arrowpro += player.getArrowProbability();
		}
		//未成功触发技能
		int prop = RandomUtils.random(Global.MAX_PROBABILITY);
		if(prop >= arrowpro){
			return false;
		}
		
		//技能冷却
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()))){
			return false;
		}
		//技能公共冷却
		if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()))){
			return false;
		}
		
		//开始冷却
		double speed = 1 + ((double) player.getAttackSpeed()) / 1000;
		//添加技能冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL, String.valueOf(model.getQ_skillID()), (long) (model.getQ_cd() / speed));
		//添加技能公共冷却
		ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.SKILL_PUBLIC, String.valueOf(model.getQ_public_cd_level()), (long) (model.getQ_public_cd() / speed));

		long fightId = Config.getId();

		MessageUtil.tell_round_message(player, getFightBroadcastMessage(fightId, player, target, skill.getSkillModelId(), -1));

		//技能飞行时间计算
		long fly = model.getQ_delay_time();
		//单体攻击
		if (model.getQ_area_shape() == 1 && target != null) {
			fly += MapUtils.countDistance(player.getPosition(), target.getPosition()) * 1000 / model.getQ_trajectory_speed();
		}

		if (fly > 0) {
			//延时伤害
			HitTimer timer = new HitTimer(fightId, player, target, skill, -1, fly, false);
			TimerUtil.addTimerEvent(timer);
		} else {
			//即时伤害
			attack(fightId, player, target, skill, -1, false);
		}
		
		return true;
	}
	
	
	/**
	 * 获取中心点周围一定范围内指定个数敌对目标
	 *
	 * @param map 所在地图
	 * @param attacker 攻击者
	 * @param center 中心点
	 * @param radius 半径
	 * @param type 类型 0-怪物 1-玩家 2-玩家和怪物
	 * @param playerAttackType 玩家类型选择 0-全部 1-非同队 2-非本帮会
	 * @param max 最大数量
	 * @return
	 */
	public List<Fighter> selectFighters(Map map, Fighter attacker, Position center, int radius, int type, int playerAttackType, int max, Grid[][] grids) {
		return randomSelectFighters(getFighterInCircle(attacker, center, getFighters(center, map, radius, type), radius, playerAttackType, grids), max);
	}

	/**
	 * 随机选择战斗者
	 *
	 * @param fighters 总战斗者
	 * @param max 最大树立那个
	 * @return
	 */
	public List<Fighter> randomSelectFighters(List<Fighter> fighters, int max) {
		if(fighters==null){
			try{
				throw new Exception();
			}catch (Exception e) {
				log.error(e, e);
			}
			log.error("选择目标为null");
			return new ArrayList<Fighter>();
		}
		//总数量
		int num = fighters.size();
		if (num <= max) {
			return fighters;
		} else {
			//目标集合
			List<Fighter> targets = new ArrayList<Fighter>();

			for (int i = 0; i < max; i++) {
				targets.add(fighters.remove(RandomUtils.random(fighters.size())));
			}

			return targets;
		}
	}

	/**
	 * 获取范围内战斗者集合
	 *
	 * @param center 中心点
	 * @param map 地图
	 * @param radius 半径
	 * @param type 类型 0-玩家和怪物 1-玩家 2-怪物
	 * @return
	 */
	private List<Fighter> getFighters(Position center, Map map, int radius, int type) {
		//战斗人员集合
		List<Fighter> fighters = new ArrayList<Fighter>();
		//获得技能半径以内区域
		int[] rounds = ManagerPool.mapManager.getRoundAreas(center, map, radius);
		for (int i = 0; i < rounds.length; i++) {
			Area area = map.getAreas().get(rounds[i]);
			if (area == null) {
				continue;
			}
			//获取战斗者
			if (type == 0) {
				fighters.addAll(area.getPlayers());
				fighters.addAll(area.getPets());
				fighters.addAll(area.getMonsters().values());
			} else if (type == 1) {
				fighters.addAll(area.getPlayers());
			} else if (type == 2) {
				fighters.addAll(area.getMonsters().values());
			}
		}

		return fighters;
	}

	/**
	 * 计算角度
	 *
	 * @param dir 前台方向1
	 * @return 坐标2相对坐标1的角度 0：→， 1：↗， 2：↑， 3：↖， 4：←， 5：↙， 6：↓， 7：↘
	 */
	private int countDirection(int dir) {
		switch (dir) {
			case 0:
				return 2;
			case 1:
				return 1;
			case 2:
				return 0;
			case 3:
				return 7;
			case 4:
				return 6;
			case 5:
				return 5;
			case 6:
				return 4;
			case 7:
				return 3;
			default:
				log.error("skill directiont error:" + dir);
				return -1;
		}
	}

	/**
	 * 增加仇恨值
	 *
	 * @param monster 怪物
	 * @param fighter 攻击者
	 * @param damage 伤害
	 */
	public void addHatred(Monster monster, Fighter fighter, int hate) {
		Hatred hatred = null;

		//log.debug("怪物" + monster.getId() + "增加对" + fighter.getId() + "的仇恨" + hate);
		//查找仇恨列表中是否已有该角色
		List<Hatred> hatreds = monster.getHatreds();
		for (int i = 0; i < hatreds.size(); i++) {
			if (hatreds.get(i).getTarget().getId() == fighter.getId()) {
				hatred = hatreds.remove(i);
				break;
			}
		}

		if (hatred == null) {
			hatred = ManagerPool.hatredManager.getHatred();
			hatred.setTarget(fighter);
			hatred.setFirstAttack(System.currentTimeMillis());
		}
		//增加仇恨值
		hatred.setHatred(hatred.getHatred() + hate);
		hatred.setLastAttack(System.currentTimeMillis());
		//插入仇恨列表（按仇恨值大小排列）
		for (int i = 0; i < hatreds.size(); i++) {
			if (hatreds.get(i).getHatred() < hatred.getHatred()) {
				hatreds.add(i, hatred);
				return;
			}
		}
		hatreds.add(hatred);
	}

//	/**
//	 * 增加仇恨值
//	 * @param monster 怪物
//	 * @param fighter 攻击者
//	 * @param damage 伤害
//	 */
//	public void addHatred(Pet pet, Fighter fighter, int hate){
//		Hatred hatred = null;
//		
//		//log.debug("怪物" + monster.getId() + "增加对" + fighter.getId() + "的仇恨" + hate);
//		//查找仇恨列表中是否已有该角色
//		List<Hatred> hatreds = pet.getHatreds();
//		for (int i = 0; i < hatreds.size(); i++) {
//			if(hatreds.get(i).getTarget().getId() == fighter.getId()){
//				hatred = hatreds.remove(i);
//				break;
//			}
//		}
//		
//		if(hatred == null){
//			hatred = ManagerPool.hatredManager.getHatred();
//			hatred.setTarget(fighter);
//			hatred.setFirstAttack(System.currentTimeMillis());
//		}
//		//增加仇恨值
//		hatred.setHatred(hatred.getHatred() + hate);
//		hatred.setLastAttack(System.currentTimeMillis());
//		//插入仇恨列表（按仇恨值大小排列）
//		for (int i = 0; i < hatreds.size(); i++) {
//			if(hatreds.get(i).getHatred() < hatred.getHatred()){
//				hatreds.add(i, hatred);
//				return;
//			}
//		}
//		
//		hatreds.add(hatred);
//	}
	/**
	 * 广播攻击动作消息
	 *
	 * @param attacker
	 * @param defender
	 * @param attackType
	 * @return
	 */
	public ResFightBroadcastMessage getFightBroadcastMessage(long fightId, Fighter attacker, Fighter defender, int attackType, int direction) {
		ResFightBroadcastMessage msg = new ResFightBroadcastMessage();
		msg.setFightId(fightId);
		msg.setFightDirection(direction);
		msg.setPersonId(attacker.getId());
		msg.setFightType(attackType);
		if (defender != null) {
			msg.setFightTarget(defender.getId());
		}

		return msg;
	}

	/**
	 * 广播攻击动作失败消息
	 *
	 * @param attacker
	 * @param defender
	 * @param attackType
	 * @return
	 */
	public ResFightFailedBroadcastMessage getFightFailedBroadcastMessage(long fightId, Fighter attacker, long targetId, int attackType, int direction) {
		ResFightFailedBroadcastMessage msg = new ResFightFailedBroadcastMessage();
		msg.setFightId(fightId);
		msg.setFightDirection(direction);
		msg.setPersonId(attacker.getId());
		msg.setFightType(attackType);
		msg.setFightTarget(targetId);

		return msg;
	}

	/**
	 * 广播特效消息
	 *
	 * @param attacker
	 * @param defender
	 * @param attackType
	 * @return
	 */
	public ResEffectBroadcastMessage getEffectBroadcastMessage(Fighter attacker, Fighter defender, int attackType, int direction) {
		ResEffectBroadcastMessage msg = new ResEffectBroadcastMessage();
		msg.setFightDirection(direction);
		msg.setPersonId(attacker.getId());
		msg.setFightType(attackType);
		if (defender != null) {
			msg.setFightTarget(defender.getId());
		}

		return msg;
	}

	/**
	 * 攻击结果
	 *
	 * @param fighter 战斗者
	 * @param damage 伤害
	 * @return
	 */
	public ResAttackResultMessage getAttackResultMessage(long fightId, Fighter source, Fighter target, Skill skill, int resultType, int damage, int hitDamage, int backDamage) {
		AttackResultInfo result = new AttackResultInfo();
		result.setEntityId(target.getId());
		result.setSourceId(source.getId());
		if (skill != null) {
			result.setSkillId(skill.getSkillModelId());
			if (source instanceof Player) {
				result.setSkillLevel(skill.getRealLevel((Player) source));
			} else {
				result.setSkillLevel(skill.getSkillLevel());
			}
		}
		result.setFightResult(resultType);
		result.setDamage(damage);
		result.setHit(hitDamage);
		result.setBack(backDamage);

		ResAttackResultMessage msg = new ResAttackResultMessage();
		msg.setFightId(fightId);
		msg.setState(result);
		
		return msg;
	}

	/**
	 * 线攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param fighters 战斗者集合
	 * @param direction 方向
	 * @param width 宽度
	 * @param radius 半径
	 * @param type 玩家类型选择 0-全部 1-非同队 2-非本帮会
	 * @return
	 */
	private List<Fighter> getFighterInLine(Fighter attacker, Position center, List<Fighter> fighters, int direction, int width, int radius, int playerAttackType, Grid[][] grids) {
		//目标集合
		List<Fighter> targets = new ArrayList<Fighter>();

		int half = width / 2;
		double sin45 = Math.sin(Math.PI / 4);
		//选择目标
		Iterator<Fighter> iter = fighters.iterator();
		while (iter.hasNext()) {
			Fighter fighter = (Fighter) iter.next();
			if (fighter.isDie()) {
				continue;
			}
			if (fighter.getId() == attacker.getId()) {
				continue;
			}

			Grid grid = MapUtils.getGrid(fighter.getPosition(), grids);
			if (grid == null) {
				Map map = ManagerPool.mapManager.getMap((Person)fighter);
				if(map!=null){
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}
				}else{
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}
				}
				continue;
			}

			if (!checkCanAttack(attacker, fighter)) {
				continue;
			}

			int x = grid.getCenter().getX() - center.getX();
			int y = -(grid.getCenter().getY() - center.getY());
			switch (direction) {
				case 0:
					if (x >= 0 && x <= radius && Math.abs(y) <= half) {
						targets.add(fighter);
					}
					break;
				case 1:
					if (y + x >= 0 && y + x <= radius / sin45 && Math.abs(y - x) <= half / sin45) {
						targets.add(fighter);
					}
					break;
				case 2:
					if (y >= 0 && y <= radius && Math.abs(x) <= half) {
						targets.add(fighter);
					}
					break;
				case 3:
					if (y - x >= 0 && y - x <= radius / sin45 && Math.abs(y + x) <= half / sin45) {
						targets.add(fighter);
					}
					break;
				case 4:
					if (x <= 0 && x >= -radius && Math.abs(y) <= half) {
						targets.add(fighter);
					}
					break;
				case 5:
					if (y + x <= 0 && y + x >= -radius / sin45 && Math.abs(y - x) <= half / sin45) {
						targets.add(fighter);
					}
					break;
				case 6:
					if (y <= 0 && y >= -radius && Math.abs(x) <= half) {
						targets.add(fighter);
					}
					break;
				case 7:
					if (y - x <= 0 && y - x >= -radius / sin45 && Math.abs(y + x) <= half / sin45) {
						targets.add(fighter);
					}
					break;
				default:
					break;
			}
		}

		return targets;
	}

	/**
	 * 圆攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param fighters 战斗者集合
	 * @param radius 半径
	 * @param type 玩家类型选择 0-全部 1-非同队 2-非本帮会
	 * @return
	 */
	private List<Fighter> getFighterInCircle(Fighter attacker, Position center, List<Fighter> fighters, int radius, int playerAttackType, Grid[][] grids) {
		//目标集合
		List<Fighter> targets = new ArrayList<Fighter>();

		//选择目标
		Iterator<Fighter> iter = fighters.iterator();
		while (iter.hasNext()) {
			Fighter fighter = (Fighter) iter.next();
			if (fighter.isDie()) {
				continue;
			}
			if (fighter.getId() == attacker.getId()) {
				continue;
			}

			Grid grid = MapUtils.getGrid(fighter.getPosition(), grids);
			if (grid == null) {
				Map map = ManagerPool.mapManager.getMap((Person)fighter);
				if(map!=null){
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}
				}else{
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}
				}
				continue;
			}

			if (!checkCanAttack(attacker, fighter)) {
				continue;
			}

			double distance = MapUtils.countDistance(center, grid.getCenter());

//			log.debug("圆形选择" + fighter.getId() + "距离：" + distance);
			//是否在半径内
			if (distance <= radius) {
				targets.add(fighter);
			}
		}

		return targets;
	}

	/**
	 * 扇形攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param fighters 战斗者集合
	 * @param direction 方向
	 * @param width 宽度
	 * @param radius 半径
	 * @param type 玩家类型选择 0-全部 1-非同队 2-非本帮会
	 * @return
	 */
	private List<Fighter> getFighterInSector(Fighter attacker, Position center, List<Fighter> fighters, int direction, int angle, int radius, int playerAttackType, Grid[][] grids) {
		//目标集合
		List<Fighter> targets = new ArrayList<Fighter>();

//		log.debug("攻击者" + attacker.getId() + "坐标：" + attacker.getPosition().toString());
		//最小角度
		double minAngle = Math.PI / 4 * direction - ((double) angle) / 180 * Math.PI / 2;
		//最大角度
		double maxAngle = Math.PI / 4 * direction + ((double) angle) / 180 * Math.PI / 2;
		if (maxAngle > Math.PI * 2) {
			maxAngle = maxAngle - Math.PI * 2;
			minAngle = minAngle - Math.PI * 2;
		}
//		log.debug("扇形角度：" + Math.PI / 4 * direction);
//		log.debug("扇形角度最小：" + minAngle);
//		log.debug("扇形角度最大：" + maxAngle);

		//选择目标
		Iterator<Fighter> iter = fighters.iterator();
		while (iter.hasNext()) {
			Fighter fighter = (Fighter) iter.next();
			if (fighter.isDie()) {
				continue;
			}
			if (fighter.getId() == attacker.getId()) {
				continue;
			}

			Grid grid = MapUtils.getGrid(fighter.getPosition(), grids);
			if (grid == null) {
				Map map = ManagerPool.mapManager.getMap((Person)fighter);
				if(map!=null){
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map " + map.getMapModelid() + "!");
					}
				}else{
					if(fighter instanceof Player){
						log.error("player" + " " + fighter.getId() + "(" + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Monster){
						log.error("monster" + " " + fighter.getId() + "(" + ((Monster)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}else if(fighter instanceof Pet){
						log.error("pet" + " " + fighter.getId() + "(" + ((Pet)fighter).getModelId() + ") position " + fighter.getPosition() + " out map !");
					}
				}
				continue;
			}

			if (!checkCanAttack(attacker, fighter)) {
				continue;
			}

			double distance = MapUtils.countDistance(center, grid.getCenter());

//			log.debug("扇形选择" + fighter.getId() + "距离：" + distance);
//			log.debug("扇形选择" + fighter.getId() + "坐标：" + grid.getCenter().toString());
			if (distance <= radius) {
				if (distance == 0) {
					targets.add(fighter);
					continue;
				}
				int x = grid.getCenter().getX() - center.getX();
				int y = grid.getCenter().getY() - center.getY();

				//战斗者角度
				double fighterAngle = 0;
				if (x >= 0) {
					fighterAngle = Math.asin(-y / distance);
					if (fighterAngle < 0) {
						fighterAngle = fighterAngle + Math.PI * 2;
					}
				} else if (x < 0) {
					fighterAngle = Math.PI - Math.asin(-y / distance);
				}
//				log.debug("扇形选择" + fighter.getId() + "扇形角度：" + fighterAngle);
				if (fighterAngle >= minAngle && fighterAngle <= maxAngle) {
					targets.add(fighter);
				} else if (fighterAngle - Math.PI * 2 >= minAngle && fighterAngle - Math.PI * 2 <= maxAngle) {
					targets.add(fighter);
				}
			}
		}

		return targets;
	}

	/**
	 * 检查是否可以攻击
	 * @param attacker
	 * @param target
	 * @return
	 */
	public boolean checkCanAttack(Fighter attacker, Fighter target) {
		try{
			if(attacker==null || target==null){
				try{
					throw new Exception();
				}catch (Exception e) {
					log.error(e, e);
				}
				log.error("攻击者" + attacker + "或被攻击者" + target + "为空!");
				return false;
			}
			
			if (attacker instanceof Player) {
				if (!target.canSee((Player) attacker)) {
					return false;
				}
			} else if (attacker instanceof Pet) {
				Player att = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
				if (att != null && !target.canSee(att)) {
					return false;
				}
			}
			
			//攻击者或者被攻击者为怪物时
			if(target instanceof Monster){
				return ((Monster) target).canBeAttack(attacker);
	//			return true;
			}
			
			if (attacker instanceof Monster) {
				return ((Monster) attacker).canAttack(target);
	//			return true;
			}
			
			IAttackCheckScript script = (IAttackCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.CHECKATTACK);
			if (script != null) {
				try {
					if(!script.check(attacker, target)){
						return false;
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("攻击检查脚本不存在！");
			}
			
			
	
			Player att = null;
			Player tar = null;
	
			if (attacker instanceof Player) {
				att = (Player) attacker;
			} else if (attacker instanceof Pet) {
				att = ManagerPool.playerManager.getPlayer(((Pet) attacker).getOwnerId());
			}
			
			if(target instanceof Player){
				tar = (Player)target;
			}else if(target instanceof Pet){
				if(PetRunState.SWIM == ((Pet)target).getRunState()){
					return false;
				}
				tar = ManagerPool.playerManager.getPlayer(((Pet)target).getOwnerId());
			}
	
			if(att==null || tar==null){
				try{
					throw new Exception();
				}catch (Exception e) {
					log.error(e, e);
				}
				log.error("攻击者" + att + "(" + attacker +  ")或被攻击者" + tar + "(" + target + ")为空!");
				return false;
			}
			
			if (att.getId() == tar.getId()) {
				return false;
			}
	
			Map map = ManagerPool.mapManager.getMap(attacker.getServerId(), attacker.getLine(), attacker.getMap());
			if (map == null) {
				return false;
			}
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
	
			if(!FighterState.FORCEPK.compare(tar.getFightState())){
				//Grid grid= MapUtils.getGrid(tar.getPosition(), grids);
				//安全区检查
				if (ManagerPool.mapManager.isSafe(tar.getPosition(), map.getMapModelid())) {
					return false;
				}
		
				//20级差检查
				if (Math.abs(att.getLevel() - tar.getLevel()) > 20 && mapBean.getQ_map_pk() == 1) {
					return false;
				}
		
				//pk保护检查
				if (FighterState.PKBAOHU.compare(tar.getFightState())) {
					log.error("玩家(" + tar.getId() + ")处于和平保护！");
					return false;
				}
		
				//挂机pk保护检查
				if (FighterState.PKBAOHUFORNIGHT.compare(tar.getFightState())) {
					return false;
				}
			}else{
				log.error("玩家(" + att.getId() + ")PK状态为(" + att.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(att.getState()) + ")敌人列表为(" + MessageUtil.castListToString(att.getEnemys().values()) + ")对玩家(" + tar.getId() + ")PK状态为(" + tar.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(tar.getState()) + ")强行攻击因为强行攻击状态");	
			}
			
			// 游泳中
			if (PlayerState.SWIM.compare(tar.getState())) {
				return false;
			}
	
			//30级以下新手保护
			if (tar.getLevel() < 30 && mapBean.getQ_map_pkprotection() == 1) {
				return false;
			}
	
			//TODO 非本国家人
	
			if (!att.getEnemys().containsKey(tar.getId())) {
				//pk模式检查
				if (att.getPkState() == 0) {
					return false;
				} else if (att.getPkState() == 1) {
					//同队伍检查
					if (att.getTeamid() == tar.getTeamid() && att.getTeamid() != 0) {
						return false;
					}
				} else if (att.getPkState() == 2) {
					//同帮会检查
					if ((att.getGuildId() == tar.getGuildId() || GuildServerManager.getInstance().isFriendGuild(att.getGuildId(), tar.getGuildId())) && att.getGuildId() != 0) {
						return false;
					}
				}
			}
			return true;
		}catch (Exception e) {
			log.error(e, e);
		}
		return false;
	}

	/**
	 * 线攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param direction 方向
	 * @param width 宽度
	 * @param radius 半径
	 * @return
	 */
	protected void showGridInLine(Player attacker, Position center, int direction, int width, int radius, Grid[][] grids) {
		//目标集合
		List<Integer> targets = new ArrayList<Integer>();

		int half = width / 2;
		double sin45 = Math.sin(Math.PI / 4);

		List<Grid> gridlist = MapUtils.getRoundGrid(MapUtils.getGrid(center, grids), radius, grids);
		//选择目标
		Iterator<Grid> iter = gridlist.iterator();
		while (iter.hasNext()) {
			Grid grid = (Grid) iter.next();
			int x = grid.getCenter().getX() - center.getX();
			int y = -(grid.getCenter().getY() - center.getY());
			switch (direction) {
				case 0:
					if (x >= 0 && x <= radius && Math.abs(y) <= half) {
						targets.add(grid.getKey());
					}
					break;
				case 1:
					if (y + x >= 0 && y + x <= radius / sin45 && Math.abs(y - x) <= half / sin45) {
						targets.add(grid.getKey());
					}
					break;
				case 2:
					if (y >= 0 && y <= radius && Math.abs(x) <= half) {
						targets.add(grid.getKey());
					}
					break;
				case 3:
					if (y - x >= 0 && y - x <= radius / sin45 && Math.abs(y + x) <= half / sin45) {
						targets.add(grid.getKey());
					}
					break;
				case 4:
					if (x <= 0 && x >= -radius && Math.abs(y) <= half) {
						targets.add(grid.getKey());
					}
					break;
				case 5:
					if (y + x <= 0 && y + x >= -radius / sin45 && Math.abs(y - x) <= half / sin45) {
						targets.add(grid.getKey());
					}
					break;
				case 6:
					if (y <= 0 && y >= -radius && Math.abs(x) <= half) {
						targets.add(grid.getKey());
					}
					break;
				case 7:
					if (y - x <= 0 && y - x >= -radius / sin45 && Math.abs(y + x) <= half / sin45) {
						targets.add(grid.getKey());
					}
					break;
				default:
					break;
			}
		}

		ResAttackRangeMessage msg = new ResAttackRangeMessage();
		msg.setGrids(targets);
		MessageUtil.tell_player_message(attacker, msg);
	}

	/**
	 * 圆攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param radius 半径
	 * @return
	 */
	protected void showGridInCircle(Player attacker, Position center, int radius, Grid[][] grids) {
		//目标集合
		List<Integer> targets = new ArrayList<Integer>();

		List<Grid> gridlist = MapUtils.getRoundGrid(MapUtils.getGrid(center, grids), radius, grids);
		//选择目标
		Iterator<Grid> iter = gridlist.iterator();
		while (iter.hasNext()) {
			Grid grid = (Grid) iter.next();
			double distance = MapUtils.countDistance(center, grid.getCenter());

			//是否在半径内
			if (distance <= radius) {
				targets.add(grid.getKey());
			}
		}

		ResAttackRangeMessage msg = new ResAttackRangeMessage();
		msg.setGrids(targets);
		MessageUtil.tell_player_message(attacker, msg);
	}

	/**
	 * 扇形攻击技能选择
	 *
	 * @param attacker 攻击者
	 * @param center 技能中心
	 * @param direction 方向
	 * @param width 宽度
	 * @param radius 半径
	 * @return
	 */
	protected void showGridInSector(Player attacker, Position center, int direction, int angle, int radius, Grid[][] grids) {
		//目标集合
		List<Integer> targets = new ArrayList<Integer>();

		//最小角度
		double minAngle = Math.PI / 4 * direction - ((double) angle) / 180 * Math.PI / 2;
		//最大角度
		double maxAngle = Math.PI / 4 * direction + ((double) angle) / 180 * Math.PI / 2;
		if (maxAngle > Math.PI * 2) {
			maxAngle = maxAngle - Math.PI * 2;
			minAngle = minAngle - Math.PI * 2;
		}

		List<Grid> gridlist = MapUtils.getRoundGrid(MapUtils.getGrid(center, grids), radius, grids);
		//选择目标
		Iterator<Grid> iter = gridlist.iterator();
		while (iter.hasNext()) {
			Grid grid = (Grid) iter.next();
			double distance = MapUtils.countDistance(center, grid.getCenter());

			if (distance <= radius) {
				if (distance == 0) {
					targets.add(grid.getKey());
					continue;
				}

				int x = grid.getCenter().getX() - center.getX();
				int y = grid.getCenter().getY() - center.getY();

				//战斗者角度
				double fighterAngle = 0;
				if (x >= 0) {
					fighterAngle = Math.asin(-y / distance);
					if (fighterAngle < 0) {
						fighterAngle = fighterAngle + Math.PI * 2;
					}
				} else if (x < 0) {
					fighterAngle = Math.PI - Math.asin(-y / distance);
				}
				if (fighterAngle >= minAngle && fighterAngle <= maxAngle) {
					targets.add(grid.getKey());
				} else if (fighterAngle - Math.PI * 2 >= minAngle && fighterAngle - Math.PI * 2 <= maxAngle) {
					targets.add(grid.getKey());
				}
			}
		}

		ResAttackRangeMessage msg = new ResAttackRangeMessage();
		msg.setGrids(targets);
		MessageUtil.tell_player_message(attacker, msg);
	}

	private boolean isWudi(Player player) {
		if (GmState.WUDI.compare(player.getGmState())) {
			return true;
		}
		return false;
	}

	/**
	 * 切换攻击锁定目标
	 *
	 * @param player 操作玩家
	 * @param targetID	目标ID
	 * @param targetType 目标类型 1玩家 2怪物 3美人 0 取消锁定
	 */
	public void chanteAttackTarget(Player player, long targetID, int targetType) {
		if (targetType == 0) {
//			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
//			if (showPet != null) {
//				PetOptManager.getInstance().changeAttackTarget(showPet,null, PetOptManager.FIGHTTYPE_PET_IDEL);
//				showPet.changeStateTo(PetState.IDEL);
//				showPet.setFightState(0);
//			}
		}
//		Fighter fighter=null;
//		Map map = MapManager.getInstance().getMap(player);
//		if(map==null){
//			return;
//		}
//		
//		switch (targetType) {
//		case 1:
//			fighter=map.getPlayers().get(targetID);
//			break;
//		case 2:
//			fighter=map.getMonsters().get(targetID);
//			break;
//		case 3:
//			//美人设为主人
//			Pet pet=MapManager.getInstance().getMapPet(map, targetID);
//			if(pet!=null&&!pet.isDie()){
//				fighter= PlayerManager.getInstance().getPlayer(pet.getOwnerId());
//			}
//			break;
//		case 0:
//			break;
//		default:
//			break;
//		}
//		Pet showPet = PetInfoManager.getInstance().getShowPet(player);
//		if (showPet != null) {
//			if (fighter != null && !fighter.isDie()) {
//				// 设置锁定目标
//				showPet.setAttackTarget(fighter);
//			} else {
//				// 清除战斗状态
//				showPet.setAttackTarget(null);
//				showPet.changeStateTo(PetState.IDEL);
//				showPet.setFightState(0);
//			}
//		}
	}
}
