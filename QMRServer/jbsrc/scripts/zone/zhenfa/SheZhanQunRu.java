package scripts.zone.zhenfa;

import com.alibaba.fastjson.JSON;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_npcBean;
import com.game.data.bean.Q_qingfengguyunBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.npc.manager.NpcManager;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.struts.Pet;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.structs.ZoneContext;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * 秦风古韵
 *
 * @author 杨鸿岚
 */
public class SheZhanQunRu implements ICreateZoneScript, IEnterMapScript, INpcGatherActionScript, IMonsterDieScript {

	private Logger log = Logger.getLogger(SheZhanQunRu.class);
	public static int zoneId = 3001;		//zoneid
	public static int scriptId = 4009;		//舌战群儒副本scriptid
	public static int messageType = 42101;		//消息类型
	public static int mapModelId = 42101;		//副本地图模板id
	public static String ScriptKey = "舌战群儒副本";	//副本保存KEY
	////////////////////////////////////////////////
	private int[] monidIs = {10001, 10002, 10003, 10004, 10005, 10006, 10007, 10008, 10009, 10010, 10011};
	private int[] npcidIs = {};
	private List<Integer> monidlist = new ArrayList<Integer>();
	private List<Integer> npcidlist = new ArrayList<Integer>();
	private List<Integer> allidlist = new ArrayList<Integer>();
	private int monEliteid = 10012;
	private int monBossid = 10013;
	private int wudibuffid = 22217;
	private int maxRefreshCount = 7;
	private int[] midposxy = {60, 68};
	private Integer[][] posxy = {{29, 66}, {36, 71}, {43, 76}, {49, 81}, {56, 86},
		{35, 63}, {42, 68}, {49, 73}, {55, 78}, {62, 83},
		{41, 60}, {48, 65}, {54, 70}, {60, 75}, {67, 80},
		{47, 57}, {54, 62}, {60, 67}, {66, 72}, {72, 77},
		{53, 54}, {59, 59}, {65, 64}, {71, 69}, {78, 74}};

	//private String[] nameStrings = {"一行白鹭上青天"};
	public SheZhanQunRu() {
		for (int i = 0; i < monidIs.length; i++) {
			int j = monidIs[i];
			monidlist.add(j);
		}
		for (int i = 0; i < npcidIs.length; i++) {
			int j = npcidIs[i];
			npcidlist.add(j);
		}
		allidlist.addAll(monidlist);
		allidlist.addAll(npcidlist);
	}

	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		//副本ID检查
		if (zoneId != SheZhanQunRu.zoneId) {
			return null;
		}
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneId);
		HashMap<String, Object> others = new HashMap<String, Object>();
		others.put("time", (int) (System.currentTimeMillis() / 1000));	//开始时间
		others.put("zonetype", zonedata.getQ_zone_type());			//副本类型 3
		others.put("teamtype", 1);		//队伍类型，0，单人，1组队
		List<Integer> mapidlist = JSON.parseArray(zonedata.getQ_mapid(), Integer.class);
		ZoneContext zone = ManagerPool.zonesManager.setZone("秦风古韵", others, mapidlist, zonedata.getQ_id());	//创建副本，返回副本消息
		Iterator<MapConfig> iterator = zone.getConfigs().iterator();
		while (iterator.hasNext()) {
			MapConfig mapConfig = iterator.next();
			if (mapConfig != null) {
				Map zoneMap = ManagerPool.mapManager.getMap(mapConfig.getServerId(), mapConfig.getLineId(), mapConfig.getMapId());
				if (zoneMap != null) {
					zoneMap.setRound(Math.max(zoneMap.getWidth(), zoneMap.getHeight()) * 2 + 1);
				}
			}
		}
		return zone;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if (map.getZoneModelId() != SheZhanQunRu.zoneId) {
			return;
		}
		ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
		if (zoneContext == null) {
			return;
		}
		if (!zoneContext.getOthers().containsKey("refreshMonster")) {
			ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
			zoneContext.getOthers().put("refreshMonster", "1");
		}

		int refreshCount = 0;
		if (zoneContext.getOthers().containsKey("maxRefreshCount")) {
			refreshCount = (Integer) (zoneContext.getOthers().get("maxRefreshCount"));
		}

		if (refreshCount == 0) {
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("请按正确的诗词顺序击杀怪物，否则将接受宫殿武士考验。"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("击杀全部%d波怪物后再战胜BOSS即为破阵成功。"), maxRefreshCount));
			sendZoneProgress(player, 0, 0, String.format(ResManager.getInstance().getString("等待刷新")));
		} else if (refreshCount == maxRefreshCount) {
			MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("%s非常敬仰您的才华，上殿向您发起挑战。"), MonsterManager.getInstance().getName(monBossid)));
			sendZoneProgress(player, 0, maxRefreshCount, String.format(ResManager.getInstance().getString("击败%s，即可破阵"), MonsterManager.getInstance().getName(monBossid)));
		} else if (refreshCount > maxRefreshCount) {
			sendZoneProgress(player, 0, maxRefreshCount + 1, String.format(ResManager.getInstance().getString("您已破阵，通关成功")));
		} else {
			String nameString = (String) zoneContext.getOthers().get("nameString");
			MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("第【%d】波怪物出现。"), refreshCount));
			MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("正确顺序为：%s。"), nameString));
			sendZoneProgress(player, 1, refreshCount - 1, Integer.toString(maxRefreshCount - refreshCount), nameString);
		}
	}

	public void startZone(List<Object> param) {
		if (param.size() < 2) {
			log.error(String.format("秦风古韵startZone失败:参数小于2"));
			return;
		}
		Player player = (Player) param.get(0);
		Map map = (Map) param.get(1);
		if (player == null) {
			log.error(String.format("秦风古韵startZone失败:player为空"));
			return ;
		}
		if (map == null) {
			log.error(String.format("秦风古韵startZone失败:map为空"));
			return;
		}
		if (player.getMapModelId() != map.getMapModelid()) {
			log.error(String.format("秦风古韵startZone失败:玩家不在该地图(" + player.getId() + ")"));
			return;
		}
		refreshMonster(1, player, map);
	}

	public void refreshMonster(int type, Player player, Map map) {
		log.error(String.format("秦风古韵副本刷新开始"));
		switch (type) {
			case 1: {//小怪
				log.error(String.format("秦风古韵副本刷新小怪开始"));
				ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
				if (zoneContext != null) {
					boolean boRefresh = false;
					int refreshCount = 0;
					if (zoneContext.getOthers().containsKey("maxRefreshCount")) {
						refreshCount = (Integer) (zoneContext.getOthers().get("maxRefreshCount"));
						if (refreshCount < maxRefreshCount) {
							boRefresh = true;
						}
					} else {
						boRefresh = true;
					}
					if (boRefresh) {
						refreshCount++;
						zoneContext.getOthers().put("maxRefreshCount", refreshCount);
						List<String> nameList = new ArrayList<String>();
						ListIterator<Q_qingfengguyunBean> listIterator = DataManager.getInstance().q_qingfengguyunContainer.getList().listIterator();
						while (listIterator.hasNext()) {
							Q_qingfengguyunBean q_qingfengguyunBean = listIterator.next();
							if (q_qingfengguyunBean.getQ_min_refresh() <= refreshCount && q_qingfengguyunBean.getQ_max_refresh() >= refreshCount) {
								nameList.add(q_qingfengguyunBean.getQ_shichi());
							}
						}
						if (nameList.isEmpty()) {
							return;
						}
						String nameString = nameList.get(RandomUtils.random(nameList.size()));
						zoneContext.getOthers().put("nameString", nameString);
						int monid = allidlist.get(RandomUtils.random(allidlist.size()));
						List<Long> teamlist = (List<Long>) zoneContext.getOthers().get("teamlist");
						if (monidlist.contains(monid)) {
							List<Integer[]> oldlist = new ArrayList<Integer[]>();
							for (int i = 0; i < nameString.length(); i++) {
								String monsterName = String.valueOf(nameString.charAt(i));
								if (!monsterName.isEmpty()) {
									Integer[] pos = posxy[RandomUtils.random(posxy.length)];
									int idx = 0;
									while (idx < 30) {
										if (oldlist.contains(pos)) {
											pos = posxy[RandomUtils.random(posxy.length)];
										} else {
											break;
										}
										idx++;
									}
									Grid grid = MapUtils.getGrid(pos[0], pos[1], map.getMapModelid());
									if (grid != null) {
										Monster monster = MonsterManager.getInstance().createMonster(monid, map.getServerId(), map.getLineId(), (int) map.getId(), grid.getCenter());
										if (monster != null) {
											monster.setName(monsterName);
											ManagerPool.mapManager.enterMap(monster);
											oldlist.add(pos);

											log.error(String.format("秦风古韵副本小怪刷新,怪物模板id[%d],怪物唯一id[%d],成员[%s]", monster.getModelId(), monster.getId(), JSON.toJSONString(teamlist)));
										}
									}
								}
							}
						} else if (npcidlist.contains(monid)) {
							List<Integer[]> oldlist = new ArrayList<Integer[]>();
							for (int i = 0; i < nameString.length(); i++) {
								String monsterName = String.valueOf(nameString.charAt(i));
								if (!monsterName.isEmpty()) {
									Integer[] pos = posxy[RandomUtils.random(posxy.length)];
									int idx = 0;
									while (idx < 30) {
										if (oldlist.contains(pos)) {
											pos = posxy[RandomUtils.random(posxy.length)];
										} else {
											break;
										}
										idx++;
									}
									Grid grid = MapUtils.getGrid(pos[0], pos[1], map.getMapModelid());
									if (grid != null) {
										NPC npc = NpcManager.getInstance().createNpc(monid, map, true);
										if (npc != null) {
											npc.setPosition(grid.getCenter());
											npc.setName(monsterName);
											ManagerPool.mapManager.enterMap(npc);
											oldlist.add(pos);

											log.error(String.format("秦风古韵副本小怪刷新,怪物模板id[%d],怪物唯一id[%d],成员[%s]", npc.getModelId(), npc.getId(), JSON.toJSONString(teamlist)));
										}
									}
								}
							}
						}
						MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("第【%d】波怪物出现。"), refreshCount));
						MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("正确顺序为：%s。"), nameString));
						sendAllZoneProgress(zoneContext, 1, refreshCount - 1, Integer.toString(maxRefreshCount - refreshCount), nameString);
					} else {
						if (refreshCount >= maxRefreshCount) {
							refreshMonster(3, player, map);
						}
					}
				}
			}
			break;
			case 2: { //武士
				log.error(String.format("秦风古韵副本刷新武士开始"));
				Iterator<Monster> iterator = map.getMonsters().values().iterator();
				while (iterator.hasNext()) {
					Monster monster = iterator.next();
					if (monster != null && monster.getModelId() != monEliteid) {
						BuffManager.getInstance().addBuff(monster, monster, wudibuffid, 0, 0, 0);
					}
				}
				int errornum = 1;
				ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
				if (zoneContext != null) {
					if (zoneContext.getOthers().containsKey("ErrorNum")) {
						errornum = (Integer) zoneContext.getOthers().get("ErrorNum");
						errornum++;
						zoneContext.getOthers().put("ErrorNum", errornum);
					} else {
						zoneContext.getOthers().put("ErrorNum", errornum);
					}
				}
				List<Long> teamlist = (List<Long>) zoneContext.getOthers().get("teamlist");
				for (int i = 0; i < 5; i++) {
					Position position = MapUtils.getRoundPosByGridRadius(midposxy[0], midposxy[1], 5, 10, false, false, map.getMapModelid());
					if (position != null) {
						Monster monster = MonsterManager.getInstance().createMonster(monEliteid, map.getServerId(), map.getLineId(), (int) map.getId(), position);
						if (monster != null) {
							monster.setLevel(player.getLevel());
							monster.setAttack(player.getAttack());
							monster.setDefense(player.getDefense());
							monster.setMaxHp(player.getMaxHp());
							monster.setHp(monster.getMaxHp());
							monster.setMaxMp(player.getMaxMp());
							monster.setMp(monster.getMaxMp());
							monster.setMaxSp(player.getMaxSp());
							monster.setSp(monster.getMaxSp());
							monster.setDodge(player.getDodge());
							monster.setCrit(player.getCrit());
							monster.setDisappearTime(System.currentTimeMillis() + 30 * 1000);
							ManagerPool.mapManager.enterMap(monster);

							log.error(String.format("秦风古韵副本武士刷新,怪物模板id[%d],怪物唯一id[%d],成员[%s]", monster.getModelId(), monster.getId(), JSON.toJSONString(teamlist)));
						}
					}
				}
				MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("顺序错误，%s上殿向您发起攻击，"), MonsterManager.getInstance().getName(monEliteid)));
				MessageUtil.notify_map(map, Notifys.CUTOUT, ResManager.getInstance().getString("请坚持30秒，之后他们将消失"));
				int refreshCount = 0;
				if (zoneContext.getOthers().containsKey("maxRefreshCount")) {
					refreshCount = (Integer) (zoneContext.getOthers().get("maxRefreshCount"));
				}
				sendAllZoneProgress(zoneContext, 2, refreshCount - 1, "30");
			}
			break;
			case 3: {//BOSS
				log.error(String.format("秦风古韵副本BOSS刷新开始"));
				ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
				if (zoneContext != null) {
					Position position = MapUtils.getRoundPosByGridRadius(midposxy[0], midposxy[1], 2, 4, false, false, map.getMapModelid());
					if (position != null) {
						int addlevel = (player.getLevel() > 100) ? (100 - 30) : (player.getLevel() - 30);
						addlevel = addlevel < 0 ? 0 : addlevel;
						Monster monster = MonsterManager.getInstance().createMonster(monBossid + addlevel, map.getServerId(), map.getLineId(), (int) map.getId(), position);
						if (monster != null) {
							monster.getParameters().put("playerid", player.getId());
							ManagerPool.mapManager.enterMap(monster);
							MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("%s非常敬仰您的才华，上殿向您发起挑战。"), MonsterManager.getInstance().getName(monBossid + addlevel)));
							sendAllZoneProgress(zoneContext, 0, maxRefreshCount, String.format(ResManager.getInstance().getString("击败%s，即可破阵"), MonsterManager.getInstance().getName(monBossid + addlevel)));
							List<Long> teamlist = (List<Long>) zoneContext.getOthers().get("teamlist");
							log.error(String.format("秦风古韵副本BOSS刷新,怪物模板id[%d],怪物唯一id[%d],成员[%s]", monster.getModelId(), monster.getId(), JSON.toJSONString(teamlist)));
						}
					}
				}
			}
			break;
		}
	}

	public void sendZoneProgress(Player player, int type, int refreshCount, String... showString) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("zonemodelid", SheZhanQunRu.zoneId);
		if (refreshCount < maxRefreshCount + 1) {
			paramMap.put("progress", refreshCount * 10);
		} else {
			paramMap.put("progress", 100);
		}
		paramMap.put("type", type);
		if (type == 0) {
			paramMap.put("showstr", showString[0]);
		} else if (type == 1) {
			paramMap.put("boci", showString[0]);
			paramMap.put("shunxu", showString[1]);
		} else {
			paramMap.put("time", showString[0]);
		}
		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
		sendMessage.setScriptid(getId());
		sendMessage.setType(1);
		sendMessage.setMessageData(JSON.toJSONString(paramMap));
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void sendAllZoneProgress(ZoneContext zoneContext, int type, int refreshCount, String... showString) {
		List<Long> teamlist = (List<Long>) zoneContext.getOthers().get("teamlist");
		for (long id : teamlist) {
			Player player = ManagerPool.playerManager.getOnLinePlayer(id);
			if (player != null && SheZhanQunRu.mapModelId == player.getMapModelId()) {
				sendZoneProgress(player, type, refreshCount, showString);
			}
		}
	}

	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void gather(Player player, NPC npc) {
		Map map = MapManager.getInstance().getMap(player);
		if (map == null) {
			return;
		}
		if (map.getZoneModelId() != SheZhanQunRu.zoneId) {
			return;
		}
		Q_npcBean q_npcBean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if (q_npcBean == null) {
			return;
		}
		ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
		if (zoneContext == null) {
			return;
		}
		ManagerPool.npcManager.playerStopGather(player, true);
		MapManager.getInstance().quitMap(npc);
		if (allidlist.contains(npc.getModelId())) {
			String nameString = (String) zoneContext.getOthers().get("nameString");
			if (!nameString.equalsIgnoreCase("")) {
				String monsterName = String.valueOf(nameString.charAt(0));
				if (npc.getName().equalsIgnoreCase(monsterName)) {
					nameString = nameString.length() >= 2 ? nameString.substring(1) : "";
					if (nameString.equalsIgnoreCase("")) {
						List<NPC> findNpc = NpcManager.getInstance().findNpc(map, npc.getModelId());
						if (findNpc.isEmpty()) {
							ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
						}
					}
				} else {
					nameString = nameString.replaceFirst(npc.getName(), "");
					refreshMonster(2, player, map);
				}
				zoneContext.getOthers().put("nameString", nameString);
			} else {
				List<NPC> findNpc = NpcManager.getInstance().findNpc(map, npc.getModelId());
				if (findNpc.isEmpty()) {
					ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
				}
			}
		}
	}

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
//		System.out.println(String.format("%s 怪物死亡 %s 玩家杀死", monster.getModelId(), killer.getName()));
		if (killer instanceof Player || killer instanceof Pet) {
			Map map = MapManager.getInstance().getMap(monster);
			if (map == null) {
				return;
			}
			if (map.getZoneModelId() != SheZhanQunRu.zoneId) {
				return;
			}
			ZoneContext zoneContext = ZonesManager.getInstance().getContexts().get(map.getZoneId());
			if (zoneContext == null) {
				return;
			}
			Player player = null;
			if (killer instanceof Pet) {
				Pet pet = (Pet) killer;
				player = PetInfoManager.getInstance().getPetHost(pet);
			} else {
				player = (Player) killer;
			}
			if (player == null) {
				return;
			}
			int addlevel = (player.getLevel() > 100) ? (100 - 30) : (player.getLevel() - 30);
			addlevel = addlevel < 0 ? 0 : addlevel;
			if (allidlist.contains(monster.getModelId()) && monster.getModelId() != monEliteid && monster.getModelId() != (monBossid + addlevel)) {
				String nameString = (String) zoneContext.getOthers().get("nameString");
				if (!nameString.equalsIgnoreCase("")) {
					String monsterName = String.valueOf(nameString.charAt(0));
					if (monster.getName().equalsIgnoreCase(monsterName)) {
						nameString = nameString.length() >= 2 ? nameString.substring(1) : "";
						if (nameString.equalsIgnoreCase("")) {
							List<Monster> sameMonster = MonsterManager.getInstance().getSameMonster(map, monster.getModelId());
							ListIterator<Monster> listIterator = sameMonster.listIterator();
							while (listIterator.hasNext()) {
								Monster monsterOne = listIterator.next();
								if (monsterOne != null && monsterOne.isDie()) {
									listIterator.remove();
								}
							}
							if (sameMonster.isEmpty()) {
								ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
							}
						}
					} else {
						nameString = nameString.replaceFirst(monster.getName(), "");
						refreshMonster(2, player, map);
					}
					zoneContext.getOthers().put("nameString", nameString);
				} else {
					List<Monster> sameMonster = MonsterManager.getInstance().getSameMonster(map, monster.getModelId());
					ListIterator<Monster> listIterator = sameMonster.listIterator();
					while (listIterator.hasNext()) {
						Monster monsterOne = listIterator.next();
						if (monsterOne != null && monsterOne.isDie()) {
							listIterator.remove();
						}
					}
					if (sameMonster.isEmpty()) {
						ScriptsUtils.delayCall(getId(), "startZone", 3000, player, map);
					}
				}
			} else if (monster.getModelId() == monEliteid) {
				List<Monster> sameMonster = MonsterManager.getInstance().getSameMonster(map, monster.getModelId());
				ListIterator<Monster> listIterator = sameMonster.listIterator();
				while (listIterator.hasNext()) {
					Monster monsterOne = listIterator.next();
					if (monsterOne != null && monsterOne.isDie()) {
						listIterator.remove();
					}
				}
				if (sameMonster.isEmpty()) {
					Iterator<Monster> iterator = map.getMonsters().values().iterator();
					while (iterator.hasNext()) {
						Monster otherMonster = iterator.next();
						if (otherMonster != null && otherMonster.getModelId() != monEliteid) {
							List<Buff> buffList = BuffManager.getInstance().getBuffByModelId(otherMonster, wudibuffid);
							if (!buffList.isEmpty()) {
								ListIterator<Buff> buffListIterator = buffList.listIterator();
								while (buffListIterator.hasNext()) {
									Buff buff = buffListIterator.next();
									BuffManager.getInstance().removeBuff(otherMonster, buff);
								}
							}
						}
					}
				}
			} else {
				List<Long> teamlist = (List<Long>) zoneContext.getOthers().get("teamlist");
				log.error(String.format("秦风古韵副本BOSS击杀,怪物模板id[%d],怪物唯一id[%d],成员[%s]", monster.getModelId(), monster.getId(), JSON.toJSONString(teamlist)));
				if (monster.getParameters().containsKey("playerid")) {
					long saveplayerid = (Long) (monster.getParameters().get("playerid"));
					if (!teamlist.contains(saveplayerid)) {
						return;
					}
				}
				String sendString = "";
				for (long id : teamlist) {
					Player mPlayer = ManagerPool.playerManager.getOnLinePlayer(id);
					if (mPlayer != null) {
						if (map.getMapModelid() == mPlayer.getMapModelId() && !zoneContext.getOthers().containsKey("rew" + id)) {
							zoneContext.getOthers().put("rew" + id, SheZhanQunRu.zoneId);
							mPlayer.getZoneinfo().put(ZonesManager.ManualDeathnum + "_" + SheZhanQunRu.zoneId, 0);
							mPlayer.getZoneinfo().put(ZonesManager.killmonsternum + "_" + SheZhanQunRu.zoneId, 0);
							int time = (Integer) zoneContext.getOthers().get("time");
							int newtime = (int) (System.currentTimeMillis() / 1000 - time);
							mPlayer.getZoneinfo().put(ZonesManager.Manualendtime + "_" + SheZhanQunRu.zoneId, newtime);

							ZoneLog zlog = new ZoneLog();
							zlog.setPlayerid(mPlayer.getId());
							zlog.setType(3);
							zlog.setZonemodelid(SheZhanQunRu.zoneId);
							zlog.setSid(player.getCreateServerId());
							LogService.getInstance().execute(zlog);

							ManagerPool.zonesFlopManager.addZoneReward(mPlayer, SheZhanQunRu.zoneId, false);
							ManagerPool.zonesFlopManager.stZonePassShow(mPlayer, 3, SheZhanQunRu.zoneId);

							sendString = sendString + "/[" + mPlayer.getName() + "|" + mPlayer.getId() + "]";
						}
					}
				}
				zoneContext.getOthers().put("maxRefreshCount", maxRefreshCount + 1);
				sendAllZoneProgress(zoneContext, 0, maxRefreshCount + 1, String.format(ResManager.getInstance().getString("您已破阵，通关成功")));

				log.error(String.format("秦风古韵副本BOSS击杀,并通关发放奖励成功,怪物模板id[%d],怪物唯一id[%d],成员[%s],发送奖励成员[%s]", monster.getModelId(), monster.getId(), JSON.toJSONString(teamlist), sendString));
			}
		}
	}
}
