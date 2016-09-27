package com.game.monster.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.game.batter.manager.BatterManager;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_guildbannerBean;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_scene_monsterBean;
import com.game.data.bean.Q_scene_monster_areaBean;
import com.game.data.manager.DataManager;
import com.game.drop.manager.DropManager;
import com.game.fight.structs.Fighter;
import com.game.guild.manager.GuildServerManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.message.ResRoundMonsterDisappearMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.message.ReqMonsterSyncMessage;
import com.game.monster.message.ResMonsterDieMessage;
import com.game.monster.message.ResMonsterHpChangeMessage;
import com.game.monster.message.ResMonsterMaxHpChangeMessage;
import com.game.monster.message.ResMonsterMaxMpChangeMessage;
import com.game.monster.message.ResMonsterMaxSpChangeMessage;
import com.game.monster.message.ResMonsterMpChangeMessage;
import com.game.monster.message.ResMonsterSpChangeMessage;
import com.game.monster.message.ResMonsterSpeedChangeMessage;
import com.game.monster.message.ResQueryBossListResultMessage;
import com.game.monster.message.ResRefreshBOSSSurplusTimeMessage;
import com.game.monster.structs.Hatred;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.monster.structs.Morph;
import com.game.monster.structs.MorphType;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetScriptManager;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.task.manager.TaskManager;
import com.game.task.message.ReqTargetMonsterMessage;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.team.manager.TeamManager;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import java.util.*;

/**
 * 怪物管理
 *
 * @author heyang
 *
 */
public class MonsterManager {

	/**
	 * 地图场景怪金钱贼
	 */
	public static final int MONSTER_TYPE_MAPMONSTER = 4;
	/**
	 * BOSS
	 */
	public static final int MONSTER_TYPE_BOSS = 3;
	/**
	 * 精英怪
	 */
	public static final int MONSTER_TYPE_ELITE = 2;
	/**
	 * 普通怪
	 */
	public static final int MONSTER_TYPE_COMMON=1;
	
	/**特殊地图怪物寻路到身边
	 * 
	 */
	private int[] mapids = {42124};
	
	
//	private MemoryPool<Monster> monsterPool = new MemoryPool<Monster>(100000);
	private Logger log = Logger.getLogger(MonsterManager.class);
	
	private Logger monsterlog = Logger.getLogger("MONSTER");
	
	private static Object obj = new Object();
	//管理类实例
	private static MonsterManager manager;

	private MonsterManager() {
	}

	public static MonsterManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MonsterManager();
			}
		}
		return manager;
	}
	
	//双倍经验状态
	private byte DaguaiDoubleStatus;

	//GM命令添加双倍时间（打坐和打怪）
	public static String DaguaiDoubleTime;
	
	
	/**检测是否特殊地图怪物寻路到身边
	 * 
	 * @param mapid
	 * @return
	 */
	public boolean ckWayfindingmapid(int mapid){
		for (int i = 0; i < mapids.length; i++) {
			if (mapids[i] == mapid) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 是否开启打怪双倍经验 true 开启
	 *
	 * @return
	 */
	public String isDaguaiDouble() {
		if (DaguaiDoubleTime != null && !DaguaiDoubleTime.equals("")) {
			try {
				if (TimeUtil.checkRangeTime(DaguaiDoubleTime)) {
					return DaguaiDoubleTime;
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.DOUBLEXP_MON.getValue());
		if (global != null) {
			String timeString = global.getQ_string_value();
			if (!timeString.equals("")) {
				String[] times = timeString.split(Symbol.FENHAO);
				for (String string : times) {
					if (TimeUtil.checkRangeTime(string)) {
						return string;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 设定双倍到经验算法里
	 *
	 * @param modelId
	 * @return
	 */
	public int setDaguaiDouble(int modelId) {
		if (getDaguaiDoubleStatus() == 1 && !isBoss(modelId)) {
			return 19;
		}
		return 0;
	}

	/**
	 * 初始化怪物分布
	 *
	 * @param lineId 线Id
	 */
	public void initMonster(int serverId, int lineId, int mapId, int mapModelId) {
		//获得怪物所在地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map == null) {
			return;
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		//遍历怪物，分布到地图中
		List<Q_scene_monsterBean> monsterBeans = ManagerPool.dataManager.q_scene_monsterContainer.getList();
		Iterator<Q_scene_monsterBean> iter = monsterBeans.iterator();
		while (iter.hasNext()) {
			Q_scene_monsterBean q_scene_monsterBean = (Q_scene_monsterBean) iter.next();
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(q_scene_monsterBean.getQ_monster_model());
			if (model == null) {
				continue;
			}

			//非本地图怪物
			if (q_scene_monsterBean.getQ_mapid() != mapModelId) {
				continue;
			}

			//初始化怪物
			Monster monster = createMonster();
			monster.setId(Config.getId());
			monster.setModelId(model.getQ_id());
			monster.setDistributeId(q_scene_monsterBean.getQ_id());
			monster.setDistributeType(1);
			monster.setServerId(serverId);
			monster.setLine(lineId);
			monster.setMap(mapId);
			if(q_scene_monsterBean.getQ_whether_display()==1){
				monster.setShow(false);
			}else{
				monster.setShow(true);
			}

			Grid grid = MapUtils.getGrid(q_scene_monsterBean.getQ_x(), q_scene_monsterBean.getQ_y(), grids);
			if (grid.getBlock() == 0) {
				continue;
			}

			monster.setBirthPos(grid.getCenter());
			monster.setPosition(grid.getCenter());
			monster.setDirection((byte) RandomUtils.random(8));

			try {
				//获得怪物模型
				if (model.getQ_passive_skill() != null && !("").equals(model.getQ_passive_skill())) {
					//技能模板_技能等级
					String[] morphs = model.getQ_passive_skill().split(Symbol.FENHAO_REG);
					for (int i = 0; i < morphs.length; i++) {
						String[] paras = morphs[i].split(Symbol.SHUXIAN_REG);
						Skill skill = new Skill();
						skill.setId(Config.getId());
						skill.setSkillModelId(Integer.parseInt(paras[0]));
						skill.setSkillLevel(Integer.parseInt(paras[1]));
						monster.getSkills().add(skill);
					}
				}
			} catch (NumberFormatException e) {
				log.error(e, e);
			}

			monster.reset();



			//是否在复活时间段内
//			if (model.getQ_refreshtime() != null && !model.getQ_refreshtime().equals("")) {
//				if (!TimeUtil.isNowSatisfiedBy(model.getQ_refreshtime())) {
//					monster.setState(MonsterState.DIE);
//					//添加到复活队列
//					map.getRevives().put(monster.getId(), monster);
//					//添加到地图中
//					map.getMonsters().put(monster.getId(), monster);
//					continue;
//				}
//			}

			//获得怪物所在区域
			Area area = map.getAreas().get(ManagerPool.mapManager.getAreaId(monster.getPosition()));

			area.getMonsters().put(monster.getId(), monster);
			//添加到地图中
			map.getMonsters().put(monster.getId(), monster);
			
			monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] enter map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");

//			if(model.getQ_info_sync()>0){
//				ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
//				syncmsg.setModelId(monster.getModelId());
//				syncmsg.setServerId(monster.getServerId());
//				syncmsg.setMonsterId(monster.getId());
//				syncmsg.setCurrentHp(monster.getHp());
//				syncmsg.setMaxHp(monster.getMaxHp());
//				syncmsg.setState(1);
//				MessageUtil.tell_world_message(syncmsg);
//			}
		}

		//遍历怪物，分布到地图中
		List<Q_scene_monster_areaBean> monsterAreaBeans = ManagerPool.dataManager.q_scene_monster_areaContainer.getList();
		Iterator<Q_scene_monster_areaBean> iter2 = monsterAreaBeans.iterator();
		while (iter2.hasNext()) {
			Q_scene_monster_areaBean q_scene_monster_areaBean = (Q_scene_monster_areaBean) iter2.next();
			//获得怪物模型
			Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(q_scene_monster_areaBean.getQ_monster_model());
			if (model == null) {
				continue;
			}

			//非本地图怪物
			if (q_scene_monster_areaBean.getQ_mapid() != mapModelId) {
				continue;
			}

			Grid center = MapUtils.getGrid(q_scene_monster_areaBean.getQ_center_x(), q_scene_monster_areaBean.getQ_center_y(), grids);

			List<Grid> areaGrids = MapUtils.getRoundNoBlockGrid(center, q_scene_monster_areaBean.getQ_radius() * MapUtils.GRID_BORDER, mapModelId);

			List<Grid> noblockGrids = new ArrayList<Grid>();

			if (areaGrids == null || areaGrids.size() == 0) {
				continue;
			}

			for (int i = 0; i < q_scene_monster_areaBean.getQ_monster_num(); i++) {
				//初始化怪物
				Monster monster = createMonster();
				monster.setId(Config.getId());
				monster.setModelId(model.getQ_id());
				monster.setDistributeId(q_scene_monster_areaBean.getQ_id());
				monster.setDistributeType(2);
				monster.setServerId(serverId);
				monster.setLine(lineId);
				monster.setMap(mapId);
				if(q_scene_monster_areaBean.getQ_whether_display()==1){
					monster.setShow(false);
				}else{
					monster.setShow(true);
				}

				if (noblockGrids.size() == 0) {
					noblockGrids.addAll(areaGrids);
				}
				Grid grid = noblockGrids.remove(RandomUtils.random(noblockGrids.size()));
				noblockGrids.removeAll(MapUtils.getRoundGrid(grid, grids));

				monster.setBirthPos(grid.getCenter());
				monster.setPosition(grid.getCenter());
				monster.setDirection((byte) RandomUtils.random(8));

				try {
					//获得怪物模型
					if (model.getQ_passive_skill() != null && !("").equals(model.getQ_passive_skill())) {
						//技能模板_技能等级
						String[] morphs = model.getQ_passive_skill().split(Symbol.FENHAO_REG);
						for (int j = 0; j < morphs.length; j++) {
							String[] paras = morphs[j].split(Symbol.SHUXIAN_REG);
							Skill skill = new Skill();
							skill.setId(Config.getId());
							skill.setSkillModelId(Integer.parseInt(paras[0]));
							skill.setSkillLevel(Integer.parseInt(paras[1]));
							monster.getSkills().add(skill);
						}
					}
				} catch (NumberFormatException e) {
					log.error(e, e);
				}

				monster.reset();

				//是否在复活时间段内
//				if (model.getQ_refreshtime() != null && !model.getQ_refreshtime().equals("")) {
//					if (!TimeUtil.isNowSatisfiedBy(model.getQ_refreshtime())) {
//						monster.setState(MonsterState.DIE);
//						//添加到复活队列
//						map.getRevives().put(monster.getId(), monster);
//						//添加到地图中
//						map.getMonsters().put(monster.getId(), monster);
//						continue;
//					}
//				}

				//获得怪物所在区域
				Area area = map.getAreas().get(ManagerPool.mapManager.getAreaId(monster.getPosition()));

				area.getMonsters().put(monster.getId(), monster);
				//添加到地图中
				map.getMonsters().put(monster.getId(), monster);
				
				monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] enter map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");

//				if(model.getQ_info_sync()>0){
//					ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
//					syncmsg.setModelId(monster.getModelId());
//					syncmsg.setServerId(monster.getServerId());
//					syncmsg.setMonsterId(monster.getId());
//					syncmsg.setCurrentHp(monster.getHp());
//					syncmsg.setMaxHp(monster.getMaxHp());
//					syncmsg.setState(1);
//					MessageUtil.tell_world_message(syncmsg);
//				}
			}
		}
	}

	private Monster createMonster() {
		try {
			return new Monster();//monsterPool.get(Monster.class);
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	/**
	 * 同步怪物到世界服务器
	 *
	 * @param serverId
	 * @param lineId
	 * @param mapId
	 */
	public void syncMonster(int serverId, int lineId, int mapId) {
		//按地图，区域遍历怪物列表
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);

		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			//是否在复活时间段内
			Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());

			if (q_monsterBean.getQ_info_sync() > 0) {
				ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
				syncmsg.setModelId(monster.getModelId());
				syncmsg.setServerId(monster.getServerId());
				syncmsg.setLineId(monster.getLine());
				syncmsg.setMapmodelid(map.getMapModelid());
				syncmsg.setMonsterId(monster.getId());
				syncmsg.setCurrentHp(monster.getHp());
				syncmsg.setMaxHp(monster.getMaxHp());
				syncmsg.setState(monster.isDie() ? 0 : 1);
				syncmsg.setBirthX(monster.getBirthPos().getX());
				syncmsg.setBirthY(monster.getBirthPos().getY());
				if (monster.isDie()) {
					long reviveTime = System.currentTimeMillis() + q_monsterBean.getQ_revive_time() * 1000;
					if (q_monsterBean.getQ_refreshtime() != null && !q_monsterBean.getQ_refreshtime().equals("")) {
						if (!TimeUtil.isSatisfiedBy(q_monsterBean.getQ_refreshtime(), new Date(reviveTime))) {
							try {
								syncmsg.setRevive(TimeUtil.getNextValidTime(q_monsterBean.getQ_refreshtime()).getTime());
							} catch (Exception ex) {
								syncmsg.setRevive(reviveTime);
							}

						} else {
							syncmsg.setRevive(reviveTime);
						}
					} else {
						syncmsg.setRevive(reviveTime);
					}
				}
				MessageUtil.send_to_world(syncmsg);
			}
		}
	}

	/**
	 * 移除怪物
	 *
	 * @param
	 */
	public void removeMonster(Monster monster) {
		//获得怪物所在地图
		Map map = ManagerPool.mapManager.getMap(monster);
		if (map == null) {
			return;
		}
		
		ManagerPool.mapManager.monsterStopRun(monster);

		//获得怪物所在区域
		Area area = map.getAreas().get(ManagerPool.mapManager.getAreaId(monster.getPosition()));

		if (!area.getMonsters().containsKey(monster.getId())) {
			log.error("Monster " + monster.getId() + " not in area " + area.getId());
			Iterator<Area> areas = map.getAreas().values().iterator();
			while (areas.hasNext()) {
				Area area2 = (Area) areas.next();
				if (area2.getMonsters().containsKey(monster.getId())) {
					area2.getMonsters().remove(monster.getId());
				}
			}
		}
		//地图上移除怪物
		area.getMonsters().remove(monster.getId());
		map.getMonsters().remove(monster.getId());
		
		monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] remove from map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");
		
		ResRoundMonsterDisappearMessage msg = new ResRoundMonsterDisappearMessage();
		msg.getMonstersIds().add(monster.getId());
		MessageUtil.tell_round_message(monster, msg);

//		monsterPool.put(monster);
	}

	/**
	 * 初始化怪物分布
	 *
	 * @param lineId 线Id
	 */
//	public void removeMonster(int serverId, int lineId, int mapId) {
//		//获得怪物所在地图
//		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
//		if (map == null) {
//			return;
//		}
//
//		Iterator<Monster> iter = map.getMonsters().values().iterator();
//		while (iter.hasNext()) {
//			Monster monster = (Monster) iter.next();
//			monsterPool.put(monster);
//		}
//	}

	/**
	 * 创建怪物对象
	 *
	 * @param model
	 * @param sceneMonster
	 * @param server
	 * @param line
	 * @return
	 */
	public Monster createMonster(Q_monsterBean model, Q_scene_monsterBean sceneMonster, int server, int line) {
		Position pos = new Position((short) sceneMonster.getQ_x(), (short) sceneMonster.getQ_y());
		Monster createMonster = createMonsterAndEnterMap(model.getQ_id(), server, line, sceneMonster.getQ_mapid(), pos);
		createMonster.setDistributeId(sceneMonster.getQ_id());
		return createMonster;
	}

	/**
	 * 创建怪物对象
	 *
	 * @param model
	 * @param server
	 * @param line
	 * @param map
	 * @param pos
	 * @return
	 */
	public Monster createMonster(int model, int serverId, int lineId, int mapId, Position pos) {
		Monster monster = createMonster();
		monster.setId(Config.getId());
		monster.setModelId(model);
		monster.setServerId(serverId);
		monster.setLine(lineId);
		monster.setMap(mapId);
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if(map==null){
			log.error("创建怪物(" + model + ")时地图(server:" + serverId + ",line:" + lineId + ",map:" + mapId +")不存在！");
			return null;
		}
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		Grid grid = MapUtils.getGrid(pos, grids);
		if(grid==null){
			log.error("创建怪物(" + model + ")时地图(server:" + serverId + ",line:" + lineId + ",map:" + mapId +")中格子(" + pos + ")不存在！");
			return null;
		}
		monster.setMapModelId(map.getMapModelid());
		monster.setBirthPos(grid.getCenter());
		monster.setPosition(grid.getCenter());
		
		monster.setDirection((byte) RandomUtils.random(8));

		try {
			//是否在复活时间段内
			Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(model);
			//获得怪物模型
			if (q_monsterBean.getQ_passive_skill() != null && !("").equals(q_monsterBean.getQ_passive_skill())) {
				//技能模板_技能等级
				String[] morphs = q_monsterBean.getQ_passive_skill().split(Symbol.FENHAO_REG);
				for (int i = 0; i < morphs.length; i++) {
					String[] paras = morphs[i].split(Symbol.SHUXIAN_REG);
					Skill skill = new Skill();
					skill.setId(Config.getId());
					skill.setSkillModelId(Integer.parseInt(paras[0]));
					skill.setSkillLevel(Integer.parseInt(paras[1]));
					monster.getSkills().add(skill);
				}
			}
		} catch (NumberFormatException e) {
			log.error(e, e);
		}

		monster.reset();
		return monster;
	}

	/**
	 * 创建怪物对象并进入地图
	 *
	 * @param model
	 * @param server
	 * @param line
	 * @param map
	 * @param pos
	 * @return
	 */
	public Monster createMonsterAndEnterMap(int model, int server, int line, int map, Position pos) {
		Monster createMonster = createMonster(model, server, line, map, pos);
		ManagerPool.mapManager.enterMap(createMonster);
		return createMonster;
	}

	/**
	 * 创建剧情怪物对象并进入地图
	 *
	 * @param model
	 * @param server
	 * @param line
	 * @param map
	 * @param pos
	 * @return
	 */
	public Monster createStoryMonsterAndEnterMap(Player player, int model, int server, int line, int map, Position pos) {
		Monster createMonster = createMonster(model, server, line, map, pos);
		createMonster.setShow(false);
		createMonster.getShowSet().add(player.getId());
		ManagerPool.mapManager.enterMap(createMonster);
		return createMonster;
	}

	public void die(Monster monster, Fighter attacter) {
		//获取怪物
		if (monster == null) {
			return;
		}
		//停止走路
		ManagerPool.mapManager.monsterStopRun(monster);
		//已经死亡
		if (MonsterState.DIE.compare(monster.getState()) || MonsterState.DIEWAIT.compare(monster.getState())) {
			return;
		}
		
		if (attacter instanceof Pet) {
			Pet attackPet = (Pet) attacter;
			PetScriptManager.getInstance().petKillTarget(attackPet, monster);			
			long attackplayerid = attackPet.getOwnerId();
			attacter= ManagerPool.playerManager.getPlayer(attackplayerid);
		}
		
		if(attacter instanceof Player){
			Player player=(Player)attacter;
			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			if(showPet!=null){
				PetScriptManager.getInstance().playerKillTarget(player, monster);
			}
		}
		
		
		//设置死亡
		if (ManagerPool.monsterManager.isBoss(monster.getModelId())) {
			if (!MonsterState.DIEING.compare(monster.getState())) {
				//设置boss死亡中
				monster.setState(MonsterState.DIEING);
				//死亡时间
				monster.setDieTime(System.currentTimeMillis());

				monster.setKiller(attacter);

				log.debug("Monster " + monster.getId() + " dieing!");

				//发送怪物死亡消息
				ResMonsterDieMessage msg = new ResMonsterDieMessage();
				msg.setMonsterId(monster.getId());
				msg.setDie((byte) monster.getState());
				msg.setKiller(attacter.getId());
				MessageUtil.tell_round_message(monster, msg);

				if (attacter instanceof Player) {
					Player attackplayer = (Player) attacter;
					GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(attackplayer, 2, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员击败BOSS"), monster.getName()), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName()), new ParseUtil.MapParm(monster.getMapModelId(), monster.getLine(), monster.getPosition().getX(), monster.getPosition().getY())));
					try{
						monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] KILLed BY "+ attackplayer.getName() +"(" + attackplayer.getId() + ") IN ["+monster.getMapModelId()+":"+monster.getLine()+"] !");
					}catch (Exception e) {
						log.error(e, e);
					}
				}
//				else if (attacter instanceof Pet) {
//					Pet attackPet = (Pet) attacter;
//					long attackplayerid = attackPet.getOwnerId();
//					Player attackplayer = ManagerPool.playerManager.getPlayer(attackplayerid);
//					if (attackplayer != null) {
//						GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(attackplayer, 2, GuildServerManager.getParserString(GuildServerManager.getEventString("成员击败BOSS", monster.getName()), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName()), new ParseUtil.MapParm(monster.getMapModelId(), monster.getLine(), monster.getPosition().getX(), monster.getPosition().getY())));
//					}
//				}
			} else {
				//在被攻击冷却中
				if (ManagerPool.cooldownManager.isCooldowning(monster, CooldownTypes.BE_ATTACK, null)) {
					return;
				}
				try {
					DropManager.bossCorpseDrop(monster);
				} catch (Exception e) {
				}

				//添加被攻击冷却
				ManagerPool.cooldownManager.addCooldown(monster, CooldownTypes.BE_ATTACK, null, Global.BOSS_ATTACK_TIME);
				return;
			}
		} else {

			monster.setState(MonsterState.DIEWAIT);
			monster.setKiller(attacter);

			log.debug("Monster " + monster.getId() + " die!");
		}


		try {
			Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());
			if (q_monsterBean.getQ_info_sync() > 0) {
				ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
				syncmsg.setModelId(monster.getModelId());
				syncmsg.setLineId(monster.getLine());
				syncmsg.setServerId(monster.getServerId());
				syncmsg.setMonsterId(monster.getId());
				syncmsg.setCurrentHp(monster.getHp());
				syncmsg.setMaxHp(monster.getMaxHp());
				syncmsg.setState(0);
				syncmsg.setBirthX(monster.getBirthPos().getX());
				syncmsg.setBirthY(monster.getBirthPos().getY());
				if (attacter instanceof Player) {
					syncmsg.setKiller(((Player) attacter).getName());
				}
				long reviveTime = System.currentTimeMillis() + q_monsterBean.getQ_revive_time() * 1000;
				if (q_monsterBean.getQ_refreshtime() != null && !q_monsterBean.getQ_refreshtime().equals("")) {
					if (!TimeUtil.isSatisfiedBy(q_monsterBean.getQ_refreshtime(), new Date(reviveTime))) {
						try {
							syncmsg.setRevive(TimeUtil.getNextValidTime(q_monsterBean.getQ_refreshtime()).getTime());
						} catch (Exception ex) {
							syncmsg.setRevive(reviveTime);
						}

					} else {
						syncmsg.setRevive(reviveTime);
					}
				} else {
					syncmsg.setRevive(reviveTime);
				}
				MessageUtil.send_to_world(syncmsg);
			}
			int total = q_monsterBean.getQ_carry_exp();

			Morph morph = monster.getMorph().get(MorphType.EXP.getValue());
			if (morph != null) {
				total = total * morph.getValue() / 10000;
			}
//			在怪物死亡时检测怪物的仇恨列表，怪物的全部经验归属于最仇恨的对象；
			Hatred maxHatred = monster.getMaxHatred();
			if (maxHatred != null) {
				Fighter attacker = maxHatred.getTarget();
				Player role = null;
				if (attacker instanceof Player) {
					role = (Player) attacker;
				}
				if (role != null) {
					int team = 0;// 同地图组队人数
					List<Player> mapSameTeam = TeamManager.getInstance().getMapSameTeam(role);
					if (mapSameTeam != null) {
						team = mapSameTeam.size();
					}

					if (team <= 1) {
						//最仇恨的对象如果是组队的玩家，但是该玩家同线同地图内没有其他队员，则适用单人经验结算方式。
						//最仇恨的对象如果是未组队的玩家，则对玩家使用未组队经验获得公式结算经验。

						long exp = 0;
						//王帮经验系数
						int kingcityCOF = 0;
//						if (role.checkKingCityBuffOfKing() || role.checkKingCityBuffOfNormal()) {
//							kingcityCOF = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_EXPCOF.getValue()).getQ_int_value();
//						}
						//帮旗系数
						int bangqixishu = 0;
						Q_guildbannerBean q_guildbannerBean = DataManager.getInstance().q_guildbannerContainer.getMap().get((int) role.getGuildInfo().getBannerLevel());
						if (q_guildbannerBean != null) {
							bangqixishu = q_guildbannerBean.getExpcof();
						}
						//未组队状态下的经验获得公式：
						//玩家获得经验 = 怪物经验 * （人物多倍经验系数 + 帮旗经验加成系数+(多倍经验活动)） * 人物等级衰减系数
						exp = (long) Math.ceil((total * ((double) role.getExpMultiple() / 10000 + (double)bangqixishu / 10000 + (double)kingcityCOF / 10000 + setDaguaiDouble(monster.getMapModelId())) * getAttenuation(role.getLevel(),
							monster.getLevel(), monster)));
						PlayerManager.getInstance().addExp(role, exp, AttributeChangeReason.KILLMONSTER);
					} else {
						//王帮经验系数
						int kingcityCOF = 0;
//						if (role.checkKingCityBuffOfKing() || role.checkKingCityBuffOfNormal()) {
//							kingcityCOF = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_EXPCOF.getValue()).getQ_int_value();
//						}
						
//						玩家获得经验 = 怪物经验 * （X%*（同线同地图内的队伍人数-1）+ 人物多倍经验系数 + 帮旗经验加成系数） * 人物等级衰减系数
//								备注说明：
//								A：其中同线同地图内的队伍人数是包含自己的，比如：A与B两人的组队，队伍人数是计算为2的。
//								B：全部怪物经验归属于怪物死亡时最仇恨的那个玩家。不与其队友进行分享。
//								C：取消杀怪血量百分比的修订规则。
//								D:X%是组队经验加成百分比
						int bangqixishu = 0;
						Q_guildbannerBean q_guildbannerBean = DataManager.getInstance().q_guildbannerContainer.getMap().get((int) role.getGuildInfo().getBannerLevel());
						if (q_guildbannerBean != null) {
							bangqixishu = q_guildbannerBean.getExpcof();
						}
						long exp=(long)Math.ceil(total*((team-1)*(double)10/100+(double) role.getExpMultiple() / 10000 + (double)bangqixishu / 10000 + (double)kingcityCOF / 10000 + setDaguaiDouble(monster.getMapModelId()))* getAttenuation(role.getLevel(), monster.getLevel(), monster));
						PlayerManager.getInstance().addExp(role, exp, AttributeChangeReason.KILLMONSTER);
						/*
						//最仇恨的对象如果是组最的玩家，且该玩家同线同地图内存在1个以上其他队员，则使用组队经验获得公式，对该玩家以及与该玩家同线同地图的队友进行经验结算。
						
						for (Player player : mapSameTeam) {
							if (player.isDie()) {
								continue;
							}
							Integer integer = monster.getDamages().get(player.getId());
							int hurt = integer == null ? 0 : integer;
							long exp = 0;
							//帮旗系数
							int bangqixishu = 0;
							Q_guildbannerBean q_guildbannerBean = DataManager.getInstance().q_guildbannerContainer.getMap().get((int) player.getGuildInfo().getBannerLevel());
							if (q_guildbannerBean != null) {
								bangqixishu = q_guildbannerBean.getExpcof();
							}
							//组队状态下的经验获得公式：
							//玩家获得经验 = ( 怪物经验 * （1 + 5% *（同线同地图内的队伍人数-1）） / 同线同地图内的队伍内成员数 + 杀怪伤害血量百分比修正系数 * 怪物经验）
							//* （人物多倍经验系数 + 帮旗经验加成系数） 
							//* 人物等级衰减系数
							exp = (long) Math.ceil(((total * (1 + (team - 1) * (double) 5 / 100) / team + getTeamExpAmend(hurt, monster.getMaxHp(), total))
								* ((double) player.getExpMultiple() / 10000 + bangqixishu / 10000 + kingcityCOF / 10000 + setDaguaiDouble(monster.getMapModelId()))
								* getAttenuation(player.getLevel(), monster.getLevel(), monster)));
							PlayerManager.getInstance().addExp(player, exp);
							// 任务事件
						}
						 */
					}
				}
			}



			//经验分配结束
			//有效击杀		
			List<Hatred> hatreds = monster.getHatreds();
			Set<Player> effectivePlayer = new HashSet<Player>();
			if (attacter instanceof Player) {
				effectivePlayer.add((Player)attacter);	
			}
			if(attacter instanceof Pet){
				Pet pet=(Pet) attacter;
				Player player = PlayerManager.getInstance().getPlayer(pet.getOwnerId());
				effectivePlayer.add(player);
			}			
			//过滤重复值 防止 重复有效击杀
			for (Hatred hatred : hatreds) {
				Fighter attacker = hatred.getTarget();
				if (attacker != null) {
					Player role = null;
					if (attacker instanceof Player) {
						role = (Player) attacker;
						if (role != null) {
							Integer damage = monster.getDamages().get(role.getId());
							if(isJingYing(monster.getModelId())){
								if (damage != null && damage > (long) monster.getMaxHp() * Global.TASK_EFFECTIVE_JINYIN_DAMAGE_RATIO) {
									int team = 0;// 同地图组队人数
									List<Player> mapSameTeam = TeamManager.getInstance().getMapSameTeam(role);
									if (mapSameTeam != null) {
										team = mapSameTeam.size();
									}
									if (team <= 1) { // 不是组队
										if ((monster.canSee(role))) {
											effectivePlayer.add(role);
										}
									} else {
										for (Player player : mapSameTeam) {
											if (player.isDie()) {
												continue;
											}
											if ((monster.canSee(player))) {
												effectivePlayer.add(player);
											}
										}
									}
								}
							}else{
								if (damage != null && damage > (long) monster.getMaxHp() * Global.TASK_EFFECTIVE_DAMAGE_RATIO) {
									int team = 0;// 同地图组队人数
									List<Player> mapSameTeam = TeamManager.getInstance().getMapSameTeam(role);
									if (mapSameTeam != null) {
										team = mapSameTeam.size();
									}
									if (team <= 1) { // 不是组队
										if ((monster.canSee(role))) {
											effectivePlayer.add(role);
										}
									} else {
										for (Player player : mapSameTeam) {
											if (player.isDie()) {
												continue;
											}
											if ((monster.canSee(player))) {
												effectivePlayer.add(player);
											}
										}
									}
								}	
							}
							
							
						}
					}
				}
			}

			for (Player player : effectivePlayer) {
				TaskManager.getInstance().action(player, Task.ACTION_TYPE_KILLMONSTER, monster.getModelId(), 1);
			}

			//物品掉落
			DropManager.monsterDieDrop(monster);

			//是否进入连斩，，经验不衰减才进入
			BatterManager.getInstance().setBatter(hatreds, monster, attacter);

			//军功任务
			tiggerRankTask(monster, attacter);
			// 军功分配
			//ManagerPool.rankManager.MilitaryExpAllocation(hatreds,monster,attacter);
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	/**
	 * 触发军功任务
	 * 
	 * @return 
	 */
	public void tiggerRankTask(Monster monster, Fighter killer){
		try{
			Map map = MapManager.getInstance().getMap(monster);
			//军衔任务
			if (killer instanceof Player || killer instanceof Pet) {
				Player player = null;
				if (killer instanceof Pet) {
					Pet pet = (Pet) killer;
					player = PetInfoManager.getInstance().getPetHost(pet);
				} else {
					player = (Player) killer;
				}
				if (player != null) {
					Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());
					if (q_monsterBean != null) {
						if ((q_monsterBean.getQ_monster_type() == 0 || q_monsterBean.getQ_monster_type() == MonsterManager.MONSTER_TYPE_COMMON) && (monster.getLevel() <= player.getLevel() + 5 && monster.getLevel() >= player.getLevel() - 5)) {
							TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLNORMAL, 1);
						} else if (q_monsterBean.getQ_monster_type() == MonsterManager.MONSTER_TYPE_ELITE && !map.isCopy() /*&& (monster.getLevel() <= player.getLevel() + 10 && monster.getLevel() >= player.getLevel() - 10)*/) {
							if (player.getTeamid()!=0) {
								TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
								if (teamInfo != null) {
									ListIterator<TeamMemberInfo> listIterator = teamInfo.getMemberinfo().listIterator();
									while(listIterator.hasNext()) {
										TeamMemberInfo teamMemberInfo =  listIterator.next();
										if (teamMemberInfo != null) {
											Player otherPlayer = PlayerManager.getInstance().getOnLinePlayer(teamMemberInfo.getMemberid());
											if (otherPlayer != null /*&& otherPlayer.getMapModelId() == map.getMapModelid() && otherPlayer.getLine() == map.getLineId()*/) {
												TaskManager.getInstance().action(otherPlayer, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLELITE, 1);
											}
										}
									}
								}
							}else{
								TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLELITE, 1);
							}
						} else if (q_monsterBean.getQ_monster_type() == MonsterManager.MONSTER_TYPE_BOSS && !map.isCopy() /*&& (monster.getLevel() <= player.getLevel() + 10 && monster.getLevel() >= player.getLevel() - 10)*/) {
							if (player.getTeamid()!=0) {
								TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
								if (teamInfo != null) {
									ListIterator<TeamMemberInfo> listIterator = teamInfo.getMemberinfo().listIterator();
									while(listIterator.hasNext()) {
										TeamMemberInfo teamMemberInfo =  listIterator.next();
										if (teamMemberInfo != null) {
											Player otherPlayer = PlayerManager.getInstance().getOnLinePlayer(teamMemberInfo.getMemberid());
											if (otherPlayer != null /*&& otherPlayer.getMapModelId() == map.getMapModelid() && otherPlayer.getLine() == map.getLineId()*/) {
												TaskManager.getInstance().action(otherPlayer, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLBOSS, 1);
											}
										}
									}
								}
							}else{
								TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLBOSS, 1);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}
	}

	/**
	 * 人物等级衰减系数
	 *
	 * @param playerGrade
	 * @param monsterGrade
	 * @param monster
	 * @return
	 */
	public double getAttenuation(int playerGrade, int monsterGrade, Monster monster) {
		//TODO 衰减配置化
		Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());

		if (q_monsterBean.getQ_isexclude() == 0) {
			//排除在衰减规则之外
			return 1;
		}
		int grade = monsterGrade - playerGrade;
		grade = Math.abs(grade);
		if (grade > 10) {
			return 0.01;
		}
		if (grade > 5 && grade <= 10) {
			return 0.8;
		}
		if (grade <= 5) {
			return 1;
		}
		return 1;
//		  怪物等级 - 人物等级 >10     	0.01
//		  5<怪物等级 - 人物等级 <=10     	0.8
//		    怪物等级 - 人物等级 <=5	       1
	}

//	/**
//	 * 组队经验修正
//	 *
//	 * @param hurt
//	 * @param total
//	 * @param isteam
//	 * @return
//	 */
//	private double getTeamExpAmend(int hurt, int maxhp, int total) {
//		//TODO 修正配置化
////		杀怪血量百分比<20%，修正系数为：0
////		20%<=杀怪血量<40%，修正系数为：10%
////		40%<=杀怪血量<70%，修正系数为：15%
////		70%<=杀怪血量，修正系数为：20%
//		double ratio = (double) hurt / maxhp * 100;
//		if (ratio < 20) {
//			return 0 * total;
//		}
//		if (ratio <= 40) {
//			return 0.1 * total;
//		}
//		if (ratio <= 70) {
//			return 0.15 * total;
//		}
//		return 0.2 * total;
//	}

	/**
	 * 是否是boss
	 *
	 * @param MonsterModelId
	 * @return
	 */
	public boolean isBoss(int MonsterModelId) {
		Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(MonsterModelId);
		if (q_monsterBean == null) {
			return false;
		}
		return q_monsterBean.getQ_monster_type() == MONSTER_TYPE_BOSS;
	}

	public boolean isJingYing(int MonsterModelId) {
		Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(MonsterModelId);
		if (q_monsterBean == null) {
			return false;
		}
		return q_monsterBean.getQ_monster_type() == MONSTER_TYPE_ELITE;
	}
	
	public boolean isMapMonster(int MonsterModelId){
		Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(MonsterModelId);
		if (q_monsterBean == null) {
			return false;
		}
		return q_monsterBean.getQ_monster_type() == MONSTER_TYPE_MAPMONSTER;
	}

	/**
	 * 清楚地图上怪物对玩家的仇恨
	 *
	 * @param map
	 * @param player
	 */
	public void cleanHatred(Map map, Player player) {
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			//清除攻击目标
			if (monster.getTarget() != null && monster.getTarget().getId() == player.getId()) {
				monster.setTarget(null);
			}
			//查找仇恨列表中是否已有该角色
			List<Hatred> hatreds = monster.getHatreds();
			for (int i = 0; i < hatreds.size(); i++) {
				if (hatreds.get(i).getTarget().getId() == player.getId()) {
					log.debug("怪物" + monster.getId() + "移除对" + player.getId() + "的仇恨!");
					Hatred hatred = hatreds.remove(i);
					ManagerPool.hatredManager.removeHatred(hatred);
					break;
				}
			}
			//移除伤害统计
			monster.getDamages().remove(player.getId());
		}
	}

	/**
	 * 获取怪物
	 *
	 * @param serverId 区编号
	 * @param lineId 线编号
	 * @param mapId 地图编号
	 * @param monsterId 怪物编号
	 * @return
	 */
	public Monster getMonster(int serverId, int lineId, int mapId, long monsterId) {
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map == null) {
			return null;
		}
		return getMonster(map, monsterId);
	}

	/**
	 * 获取怪物
	 *
	 * @param lineId 线编号
	 * @param monsterId 怪物编号
	 * @return
	 */
	public Monster getMonster(Map map, long monsterId) {
		return map.getMonsters().get(monsterId);
	}
	
	/**
	 * 获取怪物
	 * @param map
	 * @param modelId 怪物模板
	 * @return
	 */
	public List<Monster> getMonsterByModel(Map map, int modelId){
		List<Monster> monsters = new ArrayList<Monster>();
		Iterator<Monster> iter = map.getMonsters().values().iterator();
		while (iter.hasNext()) {
			Monster monster = (Monster) iter.next();
			if(monster.getModelId() == modelId){
				monsters.add(monster);
			}
		}
		
		return monsters;
	}

	/**
	 * 怪物HP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onHpChange(Monster monster) {

		//log.info("怪物" + monster.getId() + "剩余HP(" + monster.getHp() + ")!");
		//hp广播
		ResMonsterHpChangeMessage msg = new ResMonsterHpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentHp(monster.getHp());
		MessageUtil.tell_round_message(monster, msg);

//		Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());
//		if(q_monsterBean.getQ_info_sync()>0){
//			ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
//			syncmsg.setModelId(monster.getModelId());
//			syncmsg.setServerId(monster.getServerId());
//			syncmsg.setMonsterId(monster.getId());
//			syncmsg.setCurrentHp(monster.getHp());
//			syncmsg.setMaxHp(monster.getMaxHp());
//			syncmsg.setState(1);
//			MessageUtil.send_to_world(syncmsg);
//		}
		monster.getHpChangeSkill();
	}

	/**
	 * 怪物最大HP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onMaxHpChange(Monster monster, int maxhp) {
		if (monster.getHp() > maxhp) {
			monster.setHp(maxhp);
		}
		//最大hp广播
		ResMonsterMaxHpChangeMessage msg = new ResMonsterMaxHpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentHp(monster.getHp());
		msg.setMaxHp(maxhp);
		MessageUtil.tell_round_message(monster, msg);

//		Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(monster.getModelId());
//		if(q_monsterBean.getQ_info_sync()>0){
//			ReqMonsterSyncMessage syncmsg = new ReqMonsterSyncMessage();
//			syncmsg.setModelId(monster.getModelId());
//			syncmsg.setServerId(monster.getServerId());
//			syncmsg.setMonsterId(monster.getId());
//			syncmsg.setCurrentHp(monster.getHp());
//			syncmsg.setMaxHp(monster.getMaxHp());
//			syncmsg.setState(1);
//			MessageUtil.tell_world_message(syncmsg);
//		}
	}

	/**
	 * 怪物MP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onMpChange(Monster monster) {
		//mp广播
		ResMonsterMpChangeMessage msg = new ResMonsterMpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentMp(monster.getMp());
		MessageUtil.tell_round_message(monster, msg);
	}

	/**
	 * 怪物最大MP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onMaxMpChange(Monster monster, int maxmp) {
		if (monster.getMp() > maxmp) {
			monster.setMp(maxmp);
		}
		//最大mp广播
		ResMonsterMaxMpChangeMessage msg = new ResMonsterMaxMpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentMp(monster.getMp());
		msg.setMaxMp(maxmp);
		MessageUtil.tell_round_message(monster, msg);
	}

	/**
	 * 怪物SP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onSpChange(Monster monster) {
		//sp广播
		ResMonsterSpChangeMessage msg = new ResMonsterSpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentSp(monster.getSp());
		MessageUtil.tell_round_message(monster, msg);
	}

	/**
	 * 怪物最大SP变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onMaxSpChange(Monster monster, int maxsp) {
		if (monster.getSp() > maxsp) {
			monster.setSp(maxsp);
		}
		//最大sp广播
		ResMonsterMaxSpChangeMessage msg = new ResMonsterMaxSpChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setCurrentSp(monster.getSp());
		msg.setMaxSp(maxsp);
		MessageUtil.tell_round_message(monster, msg);
	}

	/**
	 * 怪物速度变化
	 *
	 * @param monster 怪物
	 * @return
	 */
	public void onSpeedChange(Monster monster, int speed) {
		//速度广播
		ResMonsterSpeedChangeMessage msg = new ResMonsterSpeedChangeMessage();
		msg.setMonsterId(monster.getId());
		msg.setSpeed(monster.getSpeed());
		MessageUtil.tell_round_message(monster, msg);
	}

	public byte getDaguaiDoubleStatus() {
		return DaguaiDoubleStatus;
	}

	public void setDaguaiDoubleStatus(byte daguaiDoubleStatus) {
		DaguaiDoubleStatus = daguaiDoubleStatus;
	}

	public void queryBossList(Player player) {
		List<Q_monsterBean> list = DataManager.getInstance().q_monsterContainer.getList();
		ResQueryBossListResultMessage msg = new ResQueryBossListResultMessage();
		for (Q_monsterBean q_monsterBean : list) {
			if (isBoss(q_monsterBean.getQ_id())) {
				msg.getMonsterModelId().add(q_monsterBean.getQ_id());
			}
		}
		MessageUtil.tell_player_message(player, msg);

	}

	public void queryBossStateList(Player player, List<Integer> monsterModelId) {
		ReqTargetMonsterMessage msg = new ReqTargetMonsterMessage();
		int serverByCountry = WServer.getGameConfig().getServerByCountry(player.getLocate());
		msg.setModelIds(monsterModelId);
		msg.setReqRoleId(player.getId());
		msg.setServerId(serverByCountry);
		MessageUtil.send_to_world(msg);
	}
	
	/**删除地图上所有怪物
	 * 
	 * @param map
	 */
	public void removeMonster(Map map){
		if (map.getMonsters().size() > 0) {
			HashMap<Long, Monster> mons = map.getMonsters();
			List<Monster> monsters = new ArrayList<Monster>();
			monsters.addAll(mons.values());
			for (Monster monster : monsters) {
				removeMonster(monster);
			}
		}
	}
		
	/**获得地图相同模组怪物
	 * 
	 * @param map
	 * @return 
	 */
	public List<Monster> getSameMonster(Map map,int modelid){
		List<Monster> monsters = new ArrayList<Monster>();
		if (map.getMonsters().size() > 0) {
			HashMap<Long, Monster> mons = map.getMonsters();
			for (Monster monster : mons.values()) {
				if (monster.getModelId() == modelid) {
					monsters.add(monster);
				}
			}
		}
		return monsters;
	}
	
	
	/**登录调用活动追踪展示 BOSS刷新剩余时间
	 * 10：30，14：30，18：30，22：30
	 */
	public void loginTrackShow(Player player){
		int hour = TimeUtil.getDayOfHour(System.currentTimeMillis());
		int min = TimeUtil.getDayOfMin(System.currentTimeMillis());
		long time = 0;
		if (hour < 10 || ( hour == 10 && min < 30)) {
			time=TimeUtil.getTheDayUnixTime(10,30,0,0);
		}else if (hour < 14 || ( hour == 14 && min < 30)) {
			time=TimeUtil.getTheDayUnixTime(14,30,0,0);
		}else if (hour < 18 || (hour == 18 && min <= 30)) {
			time=TimeUtil.getTheDayUnixTime(18,30,0,0);
		}else if (hour < 22 || (hour == 22 && min <= 30)) {
			time=TimeUtil.getTheDayUnixTime(22,30,0,0);
		}
		ResRefreshBOSSSurplusTimeMessage cmsg = new ResRefreshBOSSSurplusTimeMessage();
		long shengyu = time - System.currentTimeMillis() ;
		int ms = (int)(shengyu/1000);
		cmsg.setTime(ms);
		if (player == null) {
			if (ms < 70*60) {	//进入剩余70分钟后，才可能广播
				MessageUtil.tell_world_message(cmsg);
			}
		}else {
			MessageUtil.tell_player_message(player, cmsg);
		}
	}
	
	/**得到怪物名字，下划线前面部分
	 * 
	 * @param monstermodelid
	 * @return
	 */
	public String getName(int monstermodelid){
		Q_monsterBean model = ManagerPool.dataManager.q_monsterContainer.getMap().get(monstermodelid);
		if(model==null){
			return ResManager.getInstance().getString("未知怪物");
		}
		String q_name = model.getQ_name();
		if(!StringUtil.isBlank(q_name)&&q_name.contains(Symbol.XIAHUAXIAN_REG)){
			String[] split = q_name.split(Symbol.XIAHUAXIAN_REG);
			return split[0];
		}
		return q_name;
	}
	
	/**
	 * 移除地图上所有怪物
	 * @param map
	 */
	public void removeAllMonster(Map map){
		Monster[] monsters = map.getMonsters().values().toArray(new Monster[0]);
		for (Monster monster : monsters) {
			ManagerPool.mapManager.monsterStopRun(monster);

			int areaId = ManagerPool.mapManager.getAreaId(monster.getPosition());
			//获得怪物所在区域
			Area area = map.getAreas().get(areaId);
			
			if(!area.getMonsters().containsKey(monster.getId())){
				monsterlog.error("Monster " + monster.getId() + " not in area " + area.getId());
				Iterator<Area> areas = map.getAreas().values().iterator();
				while (areas.hasNext()) {
					Area area2 = (Area) areas.next();
					if(area2.getMonsters().containsKey(monster.getId())){
						monsterlog.error("Monster " + monster.getId() + " in area " + area2.getId());
						area2.getMonsters().remove(monster.getId());
					}
				}
			}
			//地图上移除怪物
			area.getMonsters().remove(monster.getId());
			
			//周围区域宠物攻击列表中移除
			List<Area> areas = ManagerPool.mapManager.getRoundAreas(map, areaId);
			for (Area area2 : areas) {
				Iterator<Pet> iter = area2.getPets().iterator();
				while (iter.hasNext()) {
					Pet pet = (Pet) iter.next();
					pet.getAttackTargets().remove(monster);
				}
			}
			
			log.debug("Monster " + monster.getId() + " remove from map!");
			
			map.getMonsters().remove(monster.getId());
				
			monsterlog.error("Monster " + monster.getId() + "[" + monster.getModelId() + "] remove from map " + map.getId() + " [" + map.getMapModelid() + "] " + " area " + area.getId() + "!");

			ResRoundMonsterDisappearMessage msg = new ResRoundMonsterDisappearMessage();
			msg.getMonstersIds().add(monster.getId());
			MessageUtil.tell_round_message(monster, msg);
		}
	}
}
