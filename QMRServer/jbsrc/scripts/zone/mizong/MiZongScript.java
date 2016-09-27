package scripts.zone.mizong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IEnterTeleporterScript;
import com.game.map.structs.Map;
import com.game.monster.manager.MonsterManager;
import com.game.monster.structs.Monster;
import com.game.npc.bean.ServiceInfo;
import com.game.npc.script.INpcApplyServicesScript;
import com.game.npc.script.INpcServiceScript;
import com.game.npc.struts.NPC;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.team.manager.TeamManager;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.zones.manager.ZonesManager;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.structs.ZoneContext;

/**
 * 迷踪幻境副本
 *
 * @author 杨鸿岚
 */
public class MiZongScript implements ICreateZoneScript, IEnterMapScript, IEnterTeleporterScript, INpcServiceScript, INpcApplyServicesScript {

	public static int zoneId = 5;			//zoneid
	public static int scriptId = 4006;		//迷踪幻境副本scriptid
	public static int messageType = 4006;		//消息类型
	public static int mapModelId = 30020;		//副本地图模板id
	public static List<Integer> mapidList;		//副本地图模板id列表
	public static String ScriptKey = "迷踪幻境副本";	//副本保存KEY
	public static String CHECKSCRIPTPARAM = "CHECKSCRIPTPARAM";
	////////////////////////////////////////////////
	private String clonemapName = "迷踪幻境副本";	//迷踪幻境副本名字
	private int max_EnterNum = 1;			//最大进入次数
	private int max_Vip_EnterNum = 2;		//VIP最大进入次数
	private int max_EnterLevel = 40;		//进入等级限制
	private short enter_x = 78;			//进入副本坐标X
	private short enter_y = 133;			//进入副本坐标Y
	private int npcmodelid = 12240;			//副本所有的npc
	private int reliveMapId = 20002;		//复活地图-咸阳王城
	private short relive_x = 67;			//复活地图-x
	private short relive_y = 53;			//复活地图-y
	private String PLAYERS = "players";		//副本类型区分
	private String TEAMIDLIST = "teamidlist";	//组队信息id列表
	private String TARDLIST = "tardlist";		//随机得出的正确出口
	private String FIRST = "first";			//第一次进入
	private String FIRSTFUDI1 = "firstfudi1";	//第一次进入福地1
	private String FIRSTFUDI2 = "firstfudi2";	//第一次进入福地2
	private String FIRSTSIWANG = "firstsiwang";	//第一次进入死亡
	private int[] mapIs = //模板地图id
		{30020, 30021, 30022, 30023, 30024, 30025, 30026, 30027, 30028, 30029, 30030, 30031, 30032};
	private int[][] teleporterXys = //传送下一层传送点
		{{63, 64}, {65, 66}, {67, 68}, {69, 70}, {71, 72}, {73, 74}, {75, 76}, {77, 78}, {79, 80}, {81, 81}, {82, 82}, {83, 83}, {84, 85}};
	private int[][] shuaxinguaiwu = //刷新怪物
		{{120, 10}, {70, 1}, {100, 1}};
	private int[][][] jilv = //随机几率
		{{{10000, 1}, {3100, 2}, {1600, 3}, {1100, 4}, {400, 5}, {100, 8}},
		{{10000, 1}, {5000, 2}, {4500, 3}, {3500, 5}, {3000, 4}, {1300, 6}, {800, 7}, {300, 8}},
		{{10000, 1}, {4600, 2}, {4100, 3}, {3600, 5}, {3100, 4}, {2100, 6}, {1100, 7}, {100, 8}}};

	public MiZongScript() {
		mapidList = new ArrayList<Integer>();
		for (int i = 0; i < mapIs.length; i++) {
			int j = mapIs[i];
			mapidList.add(j);
		}
	}

	private Position getPosition(int x, int y, int mapModelId) {
		Grid grid = MapUtils.getGrid(x, y, mapModelId);
		if (grid != null) {
			return grid.getCenter();
		}
		return new Position((short) 500, (short) 500);
	}

	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		//副本ID检查
		if (zoneId != MiZongScript.zoneId) {
			return null;
		}
		//状态检测
		if (!MapManager.getInstance().ischangMap(player)) {
			return null;
		}
		//副本中
		Map ckmap = ManagerPool.mapManager.getMap(player);
		if (ckmap.getZoneId() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经在副本中。"));
			return null;
		}
		//等级限制
		if (player.getLevel() < max_EnterLevel) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("迷踪幻境副本只能等级40级以上才能进入。"));
			return null;
		}
		//判断次数
		long count = CountManager.getInstance().getCount(player, CountTypes.MIZONG_NUM, "" + getId());
		if (count == 0) {
			CountManager.getInstance().addCount(player, CountTypes.MIZONG_NUM, "" + getId(), 1, 1, 1);
		} else {
			if (count < max_EnterNum) {
				CountManager.getInstance().addCount(player, CountTypes.MIZONG_NUM, "" + getId(), count + 1);
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("今天您进入迷踪幻境副本的次数已经达到上限。"));
				return null;
			}
		}
		//判断组队信息
		if (player.getTeamid() != 0) {
			TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
			if (teamInfo != null) {
				TeamMemberInfo teamMemberInfo = teamInfo.getMemberinfo().get(0);
				if (teamMemberInfo != null) {
					if (teamMemberInfo.getMemberid() != player.getId()) {
						Player headerPlayer = PlayerManager.getInstance().getOnLinePlayer(teamMemberInfo.getMemberid());
						if (headerPlayer != null) {
							Map map = MapManager.getInstance().getMap(headerPlayer);
							if (map != null) {
								ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
								if (context != null) {
									List<Long> idList = (List<Long>) context.getOthers().get(TEAMIDLIST);
									if (idList != null && !idList.isEmpty() && idList.contains(player.getId())) {
										MapManager.getInstance().changeMap(player, headerPlayer.getMap(), map.getMapModelid(), map.getLineId(), getPosition(enter_x, enter_y, map.getMapModelid()), this.getClass().getName() + ".onCreate 1");
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您不是副本开始时的成员，不能进入副本地图。"));
										return null;
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有获得副本地图信息，不能进入副本地图。"));
									return null;
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有获得副本地图信息，不能进入副本地图。"));
								return null;
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有和您的队长在同一服务器，不能进入副本地图。"));
							return null;
						}
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您获得队伍信息错误，不能进入副本地图。"));
					return null;
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您获得队伍信息错误，不能进入副本地图。"));
				return null;
			}
		}
		//地图信息(创建多张地图)
		List<MapConfig> configs = new ArrayList<MapConfig>();
		for (int i = 0; i < mapidList.size(); i++) {
			Integer mapid = mapidList.get(i);
			if (mapid != 0) {
				MapConfig config = new MapConfig();
				config.setLineId(1);
				config.setServerId(WServer.getInstance().getServerId());
				config.setMapModelId(mapid);
				configs.add(config);
			}
		}
		//创建副本，返回副本消息
		ZoneContext zone = ManagerPool.zonesManager.createZone(clonemapName, zoneId, configs);
		if (player.getTeamid() != 0) {
			List<Long> idList = new ArrayList<Long>();
			TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
			if (teamInfo != null) {
				for (int i = 0; i < teamInfo.getMemberinfo().size(); i++) {
					TeamMemberInfo teamMemberInfo = teamInfo.getMemberinfo().get(i);
					if (teamMemberInfo != null) {
						idList.add(teamMemberInfo.getMemberid());
					}
				}
			}
			zone.getOthers().put(TEAMIDLIST, idList);
		}
		List<Integer> telList = new ArrayList<Integer>();
		for (int i = 0; i < teleporterXys.length; i++) {
			int[] intes = teleporterXys[i];
			if (intes != null && intes.length == 2) {
				telList.add(intes[RandomUtils.random(intes.length)]);
			}
		}
		zone.getOthers().put(TARDLIST, telList);
		//拉人进副本
		ManagerPool.mapManager.changeMap(player, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, getPosition(enter_x, enter_y, zone.getConfigs().get(0).getMapModelId()), this.getClass().getName() + ".onCreate 2");
		
		return zone;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if (!mapidList.contains(player.getMapModelId())) {
			return;
		}
		//“走到正确出口传至下一层，走到错误出口将倒退层数”
		//“第十层设有经验大奖，组队交流可加快通关速度”
		switch (player.getMapModelId()) {
			case 30020: {
				ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (context != null) {
					if (!context.getOthers().containsKey(FIRST)) {
						MessageUtil.notify_player(player, Notifys.SROLL, ResManager.getInstance().getString("走到正确出口传至下一层，走到错误出口将倒退层数，第十层设有经验大奖。"));
						context.getOthers().put(FIRST, FIRST);
					}
				}
			}
			break;
			case 30030: {
				ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (context != null) {
					if (!context.getOthers().containsKey(FIRSTFUDI1)) {
						//RefreshMonster(map, shuaxinguaiwu[0][0], shuaxinguaiwu[0][1], player.getLevel());
						MessageUtil.notify_player(player, Notifys.SROLL, ResManager.getInstance().getString("恭喜您进入了神秘层1，击杀里面的怪物可获得大量的经验奖励哦！"));
						context.getOthers().put(FIRSTFUDI1, FIRSTFUDI1);
					}
				}
			}
			break;
			case 30031: {
				ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (context != null) {
					if (!context.getOthers().containsKey(FIRSTFUDI2)) {
						//RefreshMonster(map, shuaxinguaiwu[1][0], shuaxinguaiwu[1][1], player.getLevel());
						MessageUtil.notify_player(player, Notifys.SROLL, ResManager.getInstance().getString("恭喜您进入了神秘层2，击杀里面的宝物偷盗者可获得意想不到的奖励哦！"));
						context.getOthers().put(FIRSTFUDI2, FIRSTFUDI2);
					}
				}
			}
			break;
			case 30032: {
				ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (context != null) {
					if (!context.getOthers().containsKey(FIRSTSIWANG)) {
						//RefreshMonster(map, shuaxinguaiwu[2][0], shuaxinguaiwu[2][1], player.getLevel());
						MessageUtil.notify_player(player, Notifys.SROLL, ResManager.getInstance().getString("您不慎进入了死亡层，赶紧找到出口逃出去啊，这里面的怪物太恐怖了！"));
						context.getOthers().put(FIRSTSIWANG, FIRSTSIWANG);
					}
				}
			}
			break;
		}
	}

	@Override
	public void onTeleporter(Player player, int line, int tranId, int scriptid) {
		if (!mapidList.contains(player.getMapModelId())) {
			return;
		}
		switch (player.getMapModelId()) {
			case 30020:
			case 30021:
			case 30022:
			case 30023:
			case 30024:
			case 30025:
			case 30026:
			case 30027:
			case 30028: {//普通层
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						List<Integer> telList = (List<Integer>) context.getOthers().get(TARDLIST);
						if (telList != null && !telList.isEmpty()) {
							if (tranId == telList.get(map.getMapModelid() - 30020)) {
								MapConfig mapConfig = getMapConfig(context.getConfigs(), map.getMapModelid() + 1);
								if (mapConfig != null) {
									MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 1");
								}
							} else {
								if (context.getOthers().containsKey(player.getId() + "_" + map.getMapModelid())) {
									MapConfig mapConfig = getMapConfigByActionType(player, context, getActionType(player, context, 0), map.getMapModelid());
									if (mapConfig != null) {
										MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 2");
									}
								} else {
									Horse horse = HorseManager.getInstance().getHorse(player);
									if (horse != null && horse.getLayer() >= 6) {
										MapConfig mapConfig = getMapConfigByActionType(player, context, getActionType(player, context, 1), map.getMapModelid());
										if (mapConfig != null) {
											MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 3");
										}
									} else {
										MapConfig mapConfig = getMapConfigByActionType(player, context, getActionType(player, context, 2), map.getMapModelid());
										if (mapConfig != null) {
											MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 4");
										}
									}
									context.getOthers().put(player.getId() + "_" + map.getMapModelid(), player.getId());
								}
							}
						}
					}
				}
			}
			break;
			case 30029: {//最后一层
				if (player.getZoneSaveInfoMap().containsKey(ScriptKey + player.getId())) {
					player.getZoneSaveInfoMap().remove(ScriptKey + player.getId());
					ZonesManager.getInstance().outZone(player);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您还没有领取副本奖励，请领取后再传送回城。"));
				}
			}
			break;
			case 30030: {//福地1
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						int changemapmodelid = (Integer) context.getOthers().get(FIRSTFUDI1 + player.getId());
						if (changemapmodelid != 0) {
							MapConfig mapConfig = getMapConfig(context.getConfigs(), changemapmodelid);
							if (mapConfig != null) {
								MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 5");
							}
						}
					}
				}
			}
			break;
			case 30031: {//福地2
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						int changemapmodelid = (Integer) context.getOthers().get(FIRSTFUDI2 + player.getId());
						if (changemapmodelid != 0) {
							MapConfig mapConfig = getMapConfig(context.getConfigs(), changemapmodelid);
							if (mapConfig != null) {
								MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 6");
							}
						}
					}
				}
			}
			break;
			case 30032: {//死亡
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						List<Integer> telList = (List<Integer>) context.getOthers().get(TARDLIST);
						if (telList != null && !telList.isEmpty()) {
							if (tranId == telList.get(telList.size() - 1)) {
								int changemapmodelid = (Integer) context.getOthers().get(FIRSTFUDI2 + player.getId());
								if (changemapmodelid != 0) {
									MapConfig mapConfig = getMapConfig(context.getConfigs(), changemapmodelid);
									if (mapConfig != null) {
										MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 7");
									}
								}
							} else {
								MapConfig mapConfig = getMapConfig(context.getConfigs(), map.getMapModelid());
								if (mapConfig != null) {
									MapManager.getInstance().changeMap(player, mapConfig.getMapId(), mapConfig.getMapModelId(), mapConfig.getLineId(), getPosition(enter_x, enter_y, mapConfig.getMapModelId()), this.getClass().getName() + ".onTeleporter 8");
								}
							}
						}
					}
				}
			}
			break;
		}
	}

	@Override
	public void reqService(Player player, NPC npc, String parameters) {
		if (!mapidList.contains(player.getMapModelId())) {
			return;
		}
		if (npc.getModelId() != npcmodelid) {
			return;
		}
		String jsonString = player.getZoneSaveInfoMap().get(CHECKSCRIPTPARAM);
		if (jsonString != null && jsonString.equalsIgnoreCase(parameters)) {
			HashMap paramMap = JSON.parseObject(parameters, HashMap.class);
			int type = (Integer) paramMap.get("type");
			if (type == 1) {
				int zoneid = (Integer) paramMap.get("zoneid");
				if (zoneid == MiZongScript.zoneId) {
					if (player.getZoneSaveInfoMap().containsKey(ScriptKey + player.getId())) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经领取了奖励。"));
						return;
					}
					if (BackpackManager.getInstance().getAbleAddNum(player, 1100, true, 0) < 2) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹不够放入奖励物品，请清理包裹后再来领取。"));
						return;
					}
					int exp = player.getLevel() * player.getLevel() * player.getLevel() * 10;
					PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.FUBENG);
					List<Item> items = Item.createItems(1100, 2, true, 0);
					if (items != null && !items.isEmpty()) {
						BackpackManager.getInstance().addItems(player, items, Reasons.ZONE_MIZONG_GETITEM, Config.getId());
					}
					player.getZoneSaveInfoMap().put(ScriptKey + player.getId(), "1");
				}
			}
		}
	}

	@Override
	public void applyServices(Player player, NPC npc, List<ServiceInfo> infos) {
		if (!mapidList.contains(player.getMapModelId())) {
			return;
		}
		if (npc.getModelId() != npcmodelid) {
			return;
		}
		ServiceInfo info = new ServiceInfo();
		info.setServiceId(20);
		info.setServiceName(ResManager.getInstance().getString("领取迷踪阵通关经验奖励"));
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", 1);
		paramMap.put("zoneid", zoneId);
		paramMap.put("pid", player.getId());
		String jsonString = JSON.toJSONString(paramMap);
		info.setServiceParameter(jsonString);
		infos.add(info);
		player.getZoneSaveInfoMap().put(CHECKSCRIPTPARAM, jsonString);
	}

	public MapConfig getMapConfig(List<MapConfig> mapConfigs, int ckmapmodelid) {
		for (int i = 0; i < mapConfigs.size(); i++) {
			MapConfig mapConfig = mapConfigs.get(i);
			if (mapConfig.getMapModelId() == ckmapmodelid) {
				return mapConfig;
			}
			if (i == 0) {
				if (ckmapmodelid < mapConfig.getMapModelId()) {
					return mapConfig;
				}
			}
			if (i == mapConfigs.size() - 1) {
				if (ckmapmodelid > mapConfig.getMapModelId()) {
					return mapConfig;
				}
			}
		}
		return null;
	}

	public int getActionType(Player player, ZoneContext context, int type) {
		int ret = 1;
		int rand = RandomUtils.random(10000);
		int[][] randver = jilv[type];
		if (randver != null && randver.length != 0) {
			for (int i = 0; i < randver.length; i++) {
				int[] is = randver[i];
				if (is != null && is.length == 2) {
					if (rand < is[0]) {
						ret = is[1];
					} else {
						break;
					}
				}
			}
		}

		switch (ret) {
			case 6: {
				if (context.getOthers().containsKey(FIRSTFUDI1 + player.getId())) {
					ret = getActionType(player, context, type);
				}
			}
			break;
			case 7: {
				if (context.getOthers().containsKey(FIRSTFUDI2 + player.getId())) {
					ret = getActionType(player, context, type);
				}
			}
			break;
			case 8: {
				if (context.getOthers().containsKey(FIRSTSIWANG + player.getId())) {
					ret = getActionType(player, context, type);
				}
			}
			break;
		}
		return ret;
	}

	public MapConfig getMapConfigByActionType(Player player, ZoneContext context, int actionType, int ckmapModelid) {
		int curmapModelid = 0;
		switch (actionType) {
			case 1: {
				curmapModelid = ckmapModelid - 1;
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您遇到陷阱了，只倒退了一层，还好没有大坑。"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您遇到陷阱了，只倒退了一层，还好没有大坑。"));
			}
			break;
			case 2: {
				curmapModelid = ckmapModelid - 3;
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您遇到了大坑，倒退了三层。大坑！！！"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您遇到了大坑，倒退了三层。大坑！！！"));
			}
			break;
			case 3: {
				curmapModelid = mapidList.get(0);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您被霉运附身了吗？居然回到了第一层。"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您被霉运附身了吗？居然回到了第一层。"));
			}
			break;
			case 4: {
				curmapModelid = mapidList.get(4);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("运气真好，您直接达到了第五层，可以去中彩票了。"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("运气真好，您直接达到了第五层，可以中彩票了。"));
			}
			break;
			case 5: {
				curmapModelid = mapidList.get(7);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("运气真好，您直接达到了第八层。真的中了，500W！"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("运气真好，您直接达到了第八层。真的中了，500W！"));
			}
			break;
			case 6: {
				curmapModelid = mapidList.get(mapidList.size() - 3);
				context.getOthers().put(FIRSTFUDI1 + player.getId(), ckmapModelid);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("运气真好，您居然到达了幻境福地一。别说500W了，20倍投都能中。"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("运气真好，您居然到达了幻境福地一。别说500W了，20倍投都能中。"));
			}
			break;
			case 7: {
				curmapModelid = mapidList.get(mapidList.size() - 2);
				context.getOthers().put(FIRSTFUDI2 + player.getId(), ckmapModelid);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("运气真好，您居然到达了幻境福地二。逆天了，中了20倍投啊！"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("运气真好，您居然到达了幻境福地二。逆天了，中了20倍投啊！"));
			}
			break;
			case 8: {
				curmapModelid = mapidList.get(mapidList.size() - 1);
				context.getOthers().put(FIRSTSIWANG + player.getId(), ckmapModelid);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("坑爹啊，您居然来到了死亡境地，运气不是一般的“好”。"));
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("坑爹啊，您居然来到了死亡境地，运气不是一般的“好”。"));
			}
			break;
			default: {
				curmapModelid = ckmapModelid;
			}
			break;
		}
		return getMapConfig(context.getConfigs(), curmapModelid);
	}

	public void RefreshMonster(Map map, int monsterid, int num, int lv) {
		for (int i = 0; i < num; i++) {
			Monster monster = MonsterManager.getInstance().createMonster(monsterid, map.getServerId(), map.getLineId(), (int) map.getId(), new Position((short) 500, (short) 500));
			if (monster != null) {
				monster.setLevel(lv);
				ManagerPool.mapManager.enterMap(monster);
			}
		}
	}
}
