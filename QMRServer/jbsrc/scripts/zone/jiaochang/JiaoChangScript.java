package scripts.zone.jiaochang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_jiaochangBean;
import com.game.data.manager.DataManager;
import com.game.drop.script.IDropClearScript;
import com.game.drop.structs.MapDropInfo;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.manager.MapManager;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IQuitMapScript;
import com.game.map.structs.Map;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.team.bean.TeamInfo;
import com.game.team.manager.TeamManager;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.JiaoChangZoneTop;
import com.game.util.TimerUtil;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.zones.manager.ZonesManager;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneDeleteScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.JiaoChangConfig;
import com.game.zones.structs.JiaoChangInfo;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneEventTimer;
import com.game.zones.timer.ZoneLoopEventTimer;

/**
 * 校场副本
 *
 * @author 杨鸿岚
 */
public class JiaoChangScript implements ICreateZoneScript, IEnterMapScript, IItemScript, IDropClearScript, IZoneEventTimerScript, IQuitMapScript, IZoneDeleteScript {

	public static int zoneId = 3;			//zoneid
	public static int scriptId = 4003;		//校场副本scriptid
	public static int messageType = 4003;		//消息类型
	public static int mapModelId = 30005;		//副本地图模板id
	public static int purpleBallId = 30077;		//紫球id
	public static int greenBallId = 30076;		//绿球id
	public static int redBallId = 30075;		//红球id
	public static String ScriptKey = "校场副本";	//副本保存KEY
	////////////////////////////////////////////////
	private String clonemapName = "校场副本";	//校场副本名字
	private int max_EnterNum = 3;			//最大进入次数
	private int max_EnterLevel = 40;		//进入等级限制
	private short enter_x = 26;			//进入副本gridX
	private short enter_y = 10;			//进入副本gridY
	public static int reliveMapId = 20002;			//复活地图-咸阳王城
	public static short relive_x = 67;			//复活地图-x
	public static short relive_y = 53;			//复活地图-y
	public static String PLAYERS = "players";		//副本类型区分
	private static String CONFIG = "CONFIG";		//校场配置
	private static String REFRESHTIME = "REFRESHTIME";	//刷新复活时间
	private static String ZONEEND = "ZONEEND";		//副本结束
	public static short[][] xyIses = //随机刷新坐标
		{{5, 34}, {7, 40}, {20, 42}, {27, 39}, {36, 33}, {32, 22}, {36, 17}, {49, 10}, {63, 3}, {61, 12}, {65, 18}, {52, 25}, {51, 33}, {83, 19}, {27, 52},
		{37, 50}, {39, 60}, {53, 59}, {60, 62}, {66, 65}, {70, 55}, {69, 46}, {61, 43}, {80, 49}, {86, 41}, {95, 46}, {100, 35}, {115, 27}, {100, 20}, {96, 25}};

	private static Position getPosition(int x, int y, int mapModelId) {
		Grid grid = MapUtils.getGrid(x, y, mapModelId);
		if (grid != null) {
			return grid.getCenter();
		}
		return new Position((short) 500, (short) 500);
	}

	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		//副本ID检查
		if (zoneId != JiaoChangScript.zoneId) {
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
		//读取数据库配置
		Q_clone_activityBean zoneBean = ManagerPool.zonesManager.getContainer(zoneId);
		if (zoneBean == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有该副本的相关配置。"));
			return null;
		}
		//判断组队信息
		if (player.getTeamid() != 0) {
			TeamInfo teamInfo = TeamManager.getInstance().getTeam(player.getTeamid());
			if (teamInfo.getMemberinfo().size() > zoneBean.getQ_max_num()) {
				if (zoneBean.getQ_max_num() != 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("校场副本只能%d人进入。"), zoneBean.getQ_max_num()));
					return null;
				} else if (zoneBean.getQ_max_num() == 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("校场副本只能单人进入。"));
					return null;
				}
			}
		}
		//等级限制
		if (player.getLevel() < zoneBean.getQ_min_lv()) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("校场副本只能等级%d级以上才能进入。"), zoneBean.getQ_min_lv()));
			return null;
		}
		//判断次数
		long count = CountManager.getInstance().getCount(player, CountTypes.JIAOCHANG_NUM, "" + getId());
		if (count == 0) {
			CountManager.getInstance().addCount(player, CountTypes.JIAOCHANG_NUM, "" + getId(), 1, 1, 1);
		} else {
			if (count < zoneBean.getQ_manual_num()) {
				CountManager.getInstance().addCount(player, CountTypes.JIAOCHANG_NUM, "" + getId(), 1);
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("今天您进入校场副本的次数已经达到上限。"));
				return null;
			}
		}
		//地图信息
		List<MapConfig> configs = new ArrayList<MapConfig>();
		MapConfig config = new MapConfig();
		config.setLineId(1);
		config.setServerId(WServer.getInstance().getServerId());
		config.setMapModelId(mapModelId);
		configs.add(config);
		//创建副本，返回副本消息
		ZoneContext zone = ManagerPool.zonesManager.createZone(clonemapName, zoneId, configs);
		//拉人进副本
		ManagerPool.mapManager.changeMap(player, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, getPosition(enter_x, enter_y, zone.getConfigs().get(0).getMapModelId()), this.getClass().getName() + ".onCreate");
		
		return zone;
	}

	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if (player.getMapModelId() != mapModelId) {
			return;
		}
		if (map.getPlayerNumber() == 1) {
			List<Object> createBallparamList = new ArrayList<Object>();
			createBallparamList.add(1);
			createBallparamList.add(WServer.getInstance().getServerId());
			createBallparamList.add(1);
			createBallparamList.add(map.getId());
			ZoneEventTimer createBallTimer = new ZoneEventTimer(1, scriptId, map.getZoneId(), map.getZoneModelId(), createBallparamList, 10 * 1000);
			TimerUtil.addTimerEvent(createBallTimer);

			List<Object> loopRefreshparamList = new ArrayList<Object>();
			loopRefreshparamList.add(2);
			loopRefreshparamList.add(WServer.getInstance().getServerId());
			loopRefreshparamList.add(1);
			loopRefreshparamList.add(map.getId());
			ZoneLoopEventTimer loopRefreshTimer = new ZoneLoopEventTimer(scriptId, map.getZoneId(), map.getZoneModelId(), loopRefreshparamList, 500);
			TimerUtil.addTimerEvent(loopRefreshTimer);

			List<Object> limitparamList = new ArrayList<Object>();
			limitparamList.add(3);
			limitparamList.add(WServer.getInstance().getServerId());
			limitparamList.add(1);
			limitparamList.add(map.getId());
			int limitTime = 210 * 1000;
			Q_clone_activityBean q_clone_activityBean = DataManager.getInstance().q_clone_activityContainer.getMap().get(zoneId);
			if (q_clone_activityBean != null) {
				limitTime = q_clone_activityBean.getQ_exist_time() - 50 * 1000;
			}
			ZoneEventTimer limitTimer = new ZoneEventTimer(1, scriptId, map.getZoneId(), map.getZoneModelId(), limitparamList, limitTime);
			TimerUtil.addTimerEvent(limitTimer);

			JiaoChangInfo jiaoChangInfo = getJiaoChangInfo(player, ScriptKey);
			if (jiaoChangInfo != null) {
				ReqScriptCommonServerToWorldMessage scriptCommonServerToWorldMessage = new ReqScriptCommonServerToWorldMessage();
				scriptCommonServerToWorldMessage.setScriptid(scriptId);
				scriptCommonServerToWorldMessage.setType(2);
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("id", player.getId());
				scriptCommonServerToWorldMessage.setMessageData(JSON.toJSONString(paramMap));
				MessageUtil.send_to_world(scriptCommonServerToWorldMessage);

				jiaoChangInfo.setCurjifen(0);
				jiaoChangInfo.setMaxcombom(0);
				jiaoChangInfo.setCurcombom(0);
				jiaoChangInfo.setActiontype(0);
				jiaoChangInfo.setDataid(0);
				String infoString = setJiaoChangInfo(player, ScriptKey, jiaoChangInfo);
				ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
				sendMessage.setScriptid(scriptId);
				sendMessage.setType(1);
				sendMessage.setMessageData(infoString);
				MessageUtil.tell_player_message(player, sendMessage);

				//开始创建球
				ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (context != null) {
					JiaoChangConfig jiaoChangConfig = new JiaoChangConfig();
					jiaoChangConfig.setMapDropInfoArray(new MapDropInfo[DataManager.getInstance().q_jiaochangContainer.getList().size()]);
					ListIterator< Q_jiaochangBean> iter = DataManager.getInstance().q_jiaochangContainer.getList().listIterator();
					while (iter.hasNext()) {
						Q_jiaochangBean q_jiaochangBean = iter.next();
						MapDropInfo mapDropInfo = getBallMapDropInfo(q_jiaochangBean, map, true);
						if (mapDropInfo != null) {
							jiaoChangConfig.getMapDropInfoArray()[q_jiaochangBean.getQ_id() - 1] = mapDropInfo;
						}
					}
					context.getOthers().put(CONFIG, jiaoChangConfig);
				}

				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("欢迎进入校场副本，10秒后副本将正式开放。"));
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您需要在石柱上用轻功追逐绿色的光球，活动时间内碰到绿球越多，奖励越高。"));
			}
		}
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.getItemModelId() != greenBallId && item.getItemModelId() != redBallId && item.getItemModelId() != purpleBallId) {
			return false;
		}
		if (player.getMapModelId() != mapModelId) {
			return false;
		}
		int jifen = 0;
		JiaoChangInfo jiaoChangInfo = getJiaoChangInfo(player, ScriptKey);
		if (item.getItemModelId() == greenBallId) {
			jifen = 1;
		} else if (item.getItemModelId() == redBallId) {
			jifen = -1;
		} else if (item.getItemModelId() == purpleBallId) {
			jifen = getPurpleBallRandom(item.getGridId(),jiaoChangInfo.getCurjifen());
		}
		
		if (jiaoChangInfo != null) {
			if (jifen == 1) {
				jiaoChangInfo.setCurjifen(jiaoChangInfo.getCurjifen() + jifen);
				jiaoChangInfo.setCurcombom(jiaoChangInfo.getCurcombom() + 1);
				if (jiaoChangInfo.getCurcombom() > jiaoChangInfo.getMaxcombom()) {
					jiaoChangInfo.setMaxcombom(jiaoChangInfo.getCurcombom());
				}
			} else if (jifen == -1) {
				jiaoChangInfo.setCurjifen(jiaoChangInfo.getCurjifen() + jifen);
				jiaoChangInfo.setCurcombom(0);
			} else if (jifen == 2) {
				if (jiaoChangInfo.getCurjifen() >= 0) {
					jiaoChangInfo.setCurjifen(jiaoChangInfo.getCurjifen() * 2);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("人品帝！积分居然翻倍了！"));
				} else {
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("人品帝！积分居然翻倍了！但是您的积分是负数，没有生效！"));
				}
			} else if (jifen == 3) {
				if (jiaoChangInfo.getCurjifen() >= 0) {
					jiaoChangInfo.setCurjifen(jiaoChangInfo.getCurjifen() / 2);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("该是多倒霉才能遇到这事！积分只有一半了！"));
				} else {
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("该是多倒霉才能遇到这事！积分只有一半了！但是您的积分是负数，没有生效！"));
				}
			} else if (jifen == 4) {
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
						if (jiaoChangConfig != null) {
							for (int i = 0; i < jiaoChangConfig.getMapDropInfoArray().length; i++) {
								MapDropInfo mapDropInfo = jiaoChangConfig.getMapDropInfoArray()[i];
								if (mapDropInfo != null) {
									if (mapDropInfo.getItem().getParameters().containsKey(REFRESHTIME)) {
										long refreshTime = Long.valueOf(mapDropInfo.getItem().getParameters().get(REFRESHTIME));
										if (refreshTime != 0) {
											Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(mapDropInfo.getItem().getGridId());
											if (q_jiaochangBean != null) {
												refreshTime = refreshTime - q_jiaochangBean.getQ_refresh_del_time() * 1000;
												mapDropInfo.getItem().getParameters().put(REFRESHTIME, Long.toString(refreshTime));
											}
										}
									}
								}
							}
							MessageUtil.notify_map(map, Notifys.SUCCESS, ResManager.getInstance().getString("紫色光球激发出强大的力量，时间被加速了。所有等待刷新的光球等待时间减少了！"));
						}
					}
				}
			} else if (jifen == 5) {
				Map map = MapManager.getInstance().getMap(player);
				if (map != null) {
					ZoneContext context = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
					if (context != null) {
						JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
						if (jiaoChangConfig != null) {
							for (int i = 0; i < jiaoChangConfig.getMapDropInfoArray().length; i++) {
								MapDropInfo mapDropInfo = jiaoChangConfig.getMapDropInfoArray()[i];
								if (mapDropInfo != null) {
									if (!mapDropInfo.getItem().getParameters().containsKey(REFRESHTIME)) {
										Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(mapDropInfo.getItem().getGridId());
										if (q_jiaochangBean != null) {
											mapDropInfo.setCleartimepoint(mapDropInfo.getCleartimepoint() + q_jiaochangBean.getQ_extend_time() * 1000);
										}
									}
								}
							}
							MessageUtil.notify_map(map, Notifys.SUCCESS, ResManager.getInstance().getString("紫色光球散发出强大的力量，时间被禁锢了。所有可见光球变化时间增加了！"));
						}
					}
				}
			}
		}
		if (jiaoChangInfo != null) {
			jiaoChangInfo.setActiontype(jifen);
			jiaoChangInfo.setDataid(item.getGridId());
			String infoString = setJiaoChangInfo(player, ScriptKey, jiaoChangInfo);
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(scriptId);
			sendMessage.setType(1);
			sendMessage.setMessageData(infoString);
			MessageUtil.tell_player_message(player, sendMessage);

			Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(item.getGridId());
			if (q_jiaochangBean != null) {
				long refreshTime = System.currentTimeMillis() + q_jiaochangBean.getQ_refresh_time() * 1000;
				item.getParameters().put(REFRESHTIME, Long.toString(refreshTime));
			}
			//MessageUtil.notify_map(map, Notifys.SUCCESS, String.format("当前积分为[%d],最高积分为[%d],当前COMBOM为[%d],最高COMBOM为[%d]", jiaoChangInfo.getCurjifen(), jiaoChangInfo.getMaxjifen(), jiaoChangInfo.getCurcombom(), jiaoChangInfo.getMaxcombom()));
		}
		return true;
	}

	public JiaoChangInfo getJiaoChangInfo(Player player, String key) {
		JiaoChangInfo jiaoChangInfo = null;
		String infoString = player.getZoneSaveInfoMap().get(key);
		if (infoString != null && !infoString.isEmpty()) {
			jiaoChangInfo = JSON.parseObject(infoString, JiaoChangInfo.class);
			if (jiaoChangInfo == null) {
				jiaoChangInfo = new JiaoChangInfo();
			}
		} else {
			jiaoChangInfo = new JiaoChangInfo();
		}
		return jiaoChangInfo;
	}

	public String setJiaoChangInfo(Player player, String key, JiaoChangInfo jiaoChangInfo) {
		String infoString = JSON.toJSONString(jiaoChangInfo);
		if (infoString == null) {
			infoString = "";
		}
		player.getZoneSaveInfoMap().put(key, infoString);
		return infoString;
	}

	@Override
	public boolean dropClear(MapDropInfo mapDropInfo) {
		if (mapDropInfo.getMapIns().getZoneModelId() != zoneId) {
			return false;
		}
		if (mapDropInfo.getMapModelid() != mapModelId) {
			return false;
		}
		if (mapDropInfo.getItem().getItemModelId() != greenBallId && mapDropInfo.getItem().getItemModelId() != redBallId && mapDropInfo.getItem().getItemModelId() != purpleBallId) {
			return false;
		}
		if (mapDropInfo.getMapIns().getPlayerNumber() == 0) {
			return false;
		}
		Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(mapDropInfo.getItem().getGridId());
		if (q_jiaochangBean != null) {
			MapDropInfo otherMapDropInfo = getBallMapDropInfo(q_jiaochangBean, mapDropInfo.getMapIns(), false);
			if (otherMapDropInfo != null) {
				ZoneContext context = ZonesManager.getInstance().getContexts().get(mapDropInfo.getMapIns().getZoneId());
				if (context != null) {
					JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
					if (jiaoChangConfig != null) {
						jiaoChangConfig.getMapDropInfoArray()[q_jiaochangBean.getQ_id() - 1] = otherMapDropInfo;
						MapManager.getInstance().enterMap(otherMapDropInfo);
					}
				}
			}
		}
		return true;
	}

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		if (zoneModelId != JiaoChangScript.zoneId) {
			return;
		}
		if (parameters.size() > 0) {
			int type = (Integer) parameters.get(0);
			switch (type) {
				case 1: {
					if (parameters.size() < 4) {
						return;
					}
					int serverId = (Integer) parameters.get(1);
					int lineId = (Integer) parameters.get(2);
					long mapId = (Long) parameters.get(3);
					Map map = ManagerPool.mapManager.getMap(serverId, lineId, (int) mapId);
					if (map != null && map.getMapModelid() == mapModelId) {
						ZoneContext context = ZonesManager.getInstance().getContexts().get(map.getZoneId());
						if (context != null) {
							JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
							if (jiaoChangConfig != null) {
								int limitTime = 210 * 1000;
								Q_clone_activityBean q_clone_activityBean = DataManager.getInstance().q_clone_activityContainer.getMap().get(zoneId);
								if (q_clone_activityBean != null) {
									limitTime = q_clone_activityBean.getQ_exist_time() - 60 * 1000;
								}
								HashMap<String, Object> timeMap = new HashMap<String, Object>();
								timeMap.put("time", limitTime);
								for (Entry<Long, Player> entry : map.getPlayers().entrySet()) {
									Player player = entry.getValue();
									if (player != null) {
										MapManager.getInstance().changePosition(player, getPosition(enter_x, enter_y, map.getMapModelid()));
										ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
										sendMessage.setScriptid(scriptId);
										sendMessage.setType(4);
										sendMessage.setMessageData(JSON.toJSONString(timeMap));
										MessageUtil.tell_player_message(player, sendMessage);
									}
								}
								for (int i = 0; i < jiaoChangConfig.getMapDropInfoArray().length; i++) {
									MapDropInfo mapDropInfo = jiaoChangConfig.getMapDropInfoArray()[i];
									if (mapDropInfo != null) {
										MapManager.getInstance().enterMap(mapDropInfo);
									}
								}
								MessageUtil.notify_map(map, Notifys.SUCCESS, ResManager.getInstance().getString("校场试炼开始！"));
							}
						}
					}
				}
				break;
				case 2: {
					if (parameters.size() < 4) {
						return;
					}
					int serverId = (Integer) parameters.get(1);
					int lineId = (Integer) parameters.get(2);
					long mapId = (Long) parameters.get(3);
					Map map = ManagerPool.mapManager.getMap(serverId, lineId, (int) mapId);
					if (map != null && map.getMapModelid() == mapModelId) {
						ZoneContext context = ZonesManager.getInstance().getContexts().get(map.getZoneId());
						if (context != null) {
							if (context.getOthers().containsKey(ZONEEND)) {
								return;
							}
							JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
							if (jiaoChangConfig != null) {
								long curTime = System.currentTimeMillis();
								for (int i = 0; i < jiaoChangConfig.getMapDropInfoArray().length; i++) {
									MapDropInfo mapDropInfo = jiaoChangConfig.getMapDropInfoArray()[i];
									if (mapDropInfo != null) {
										if (mapDropInfo.getItem().getParameters().containsKey(REFRESHTIME)) {
											long refreshTime = Long.valueOf(mapDropInfo.getItem().getParameters().get(REFRESHTIME));
											if (refreshTime != 0 && curTime >= refreshTime) {
												Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(mapDropInfo.getItem().getGridId());
												if (q_jiaochangBean != null) {
													MapDropInfo otherMapDropInfo = getBallMapDropInfo(q_jiaochangBean, mapDropInfo.getMapIns(), false);
													if (otherMapDropInfo != null) {
														jiaoChangConfig.getMapDropInfoArray()[i] = otherMapDropInfo;
														MapManager.getInstance().enterMap(otherMapDropInfo);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				break;
				case 3: {//TODO 校场正式时间完了，结束刷新，和给奖励。
					if (parameters.size() < 4) {
						return;
					}
					int serverId = (Integer) parameters.get(1);
					int lineId = (Integer) parameters.get(2);
					long mapId = (Long) parameters.get(3);
					Map map = ManagerPool.mapManager.getMap(serverId, lineId, (int) mapId);
					if (map != null && map.getMapModelid() == mapModelId) {
						ZoneContext context = ZonesManager.getInstance().getContexts().get(map.getZoneId());
						if (context != null) {
							JiaoChangConfig jiaoChangConfig = (JiaoChangConfig) context.getOthers().get(CONFIG);
							if (jiaoChangConfig != null) {
								for (int i = 0; i < jiaoChangConfig.getMapDropInfoArray().length; i++) {
									MapDropInfo mapDropInfo = jiaoChangConfig.getMapDropInfoArray()[i];
									if (mapDropInfo != null) {
										MapManager.getInstance().quitMap(mapDropInfo);
									}
								}
								context.getOthers().put(ZONEEND, ZONEEND);
								MessageUtil.notify_map(map, Notifys.SUCCESS, ResManager.getInstance().getString("校场试炼结束！"));
								MessageUtil.notify_map(map, Notifys.SUCCESS, ResManager.getInstance().getString("您可以从传送点回到王城！"));
							}
						}
						for (Entry<Long, Player> entry : map.getPlayers().entrySet()) {
							Player player = entry.getValue();
							if (player != null) {
								JiaoChangInfo jiaoChangInfo = getJiaoChangInfo(player, ScriptKey);
								if (jiaoChangInfo != null) {
									int jifen = jiaoChangInfo.getCurjifen() + jiaoChangInfo.getMaxcombom() * 2;
									if (jifen > jiaoChangInfo.getMaxjifen()) {
										jiaoChangInfo.setMaxjifen(jifen);
										JiaoChangZoneTop jiaoChangZoneTop = new JiaoChangZoneTop(player.getId(), player.getName(), JiaoChangScript.zoneId, jifen, System.currentTimeMillis());
										TopListManager.getInstance().completeZone(2, player, JSON.toJSONString(jiaoChangZoneTop, SerializerFeature.WriteClassName));
									}
									jiaoChangInfo.setCurjifen(jifen);
									jiaoChangInfo.setActiontype(0);
									jiaoChangInfo.setDataid(0);
									String infoString = setJiaoChangInfo(player, ScriptKey, jiaoChangInfo);
									ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
									sendMessage.setScriptid(scriptId);
									sendMessage.setType(5);
									sendMessage.setMessageData(infoString);
									MessageUtil.tell_player_message(player, sendMessage);
								}
								//ManagerPool.zonesFlopManager.giveZoneFixedReward(player, map.getZoneModelId(), 0);
							}
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public void onQuitMap(Player player, Map map) {
		if (map.getMapModelid() != mapModelId) {
			return;
		}
		if (map.getZoneModelId() != zoneId) {
			return;
		}
		JiaoChangInfo jiaoChangInfo = getJiaoChangInfo(player, ScriptKey);
		if (jiaoChangInfo != null) {
			if (jiaoChangInfo.getCurjifen() > 0) {
				Q_characterBean q_characterBean = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
				if (q_characterBean != null) {
					int exp = jiaoChangInfo.getCurjifen() * q_characterBean.getQ_dazuoexp() + 1000;
					PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.JIAOCHANG);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("校场奖励：获得%d经验。"), exp));
					int lijin = (int) (Math.floor(jiaoChangInfo.getCurjifen() / 2 + 100));
					if (lijin + player.getBindGold() > Global.BAG_MAX_GOLD) {
						Item item = Item.createBindGold(lijin);
						List<Item> items = new ArrayList<Item>();
						items.add(item);
						MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("系统邮件"), String.format(ResManager.getInstance().getString("校场奖励：获得%d礼金。"), lijin), (byte) 0, 0, items);
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("校场奖励礼金由于您的礼金已达到上限，转发为系统邮件领取。"), lijin));
					} else {
						BackpackManager.getInstance().changeBindGold(player, lijin, Reasons.JIAOCHANGE_GETBINDGOLD, Config.getId());
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("校场奖励：获得%d礼金。"), lijin));
					}
				}
				jiaoChangInfo.setCurjifen(0);
				setJiaoChangInfo(player, ScriptKey, jiaoChangInfo);
			} else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您的总积分无法获得奖励，请不要灰心，再接再励！"));
			}
		}
		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
		sendMessage.setScriptid(scriptId);
		sendMessage.setType(3);
		MessageUtil.tell_player_message(player, sendMessage);
		MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您退出了校场副本！"));
//		if (map.getPlayerNumber() == 0) {//单地图没人了可以直接移除副本，其他不行
//			ZonesManager.getInstance().removeZone(map.getZoneId());
//		}
	}

	@Override
	public void onDelete(MServer mServer, ZoneContext context) {
	}

	public int getPurpleBallRandom(int key , int jifen) {
		int ret = 0;
		Q_jiaochangBean q_jiaochangBean = DataManager.getInstance().q_jiaochangContainer.getMap().get(key);
		if (q_jiaochangBean != null) {
			int purpleballranidx = RandomUtils.random(10000);
			if (purpleballranidx < q_jiaochangBean.getQ_double()) {
				ret = 2;
			} else if (purpleballranidx >= q_jiaochangBean.getQ_double() && purpleballranidx < q_jiaochangBean.getQ_half()) {
				ret = 3;
			} else if (purpleballranidx >= q_jiaochangBean.getQ_half() && purpleballranidx < q_jiaochangBean.getQ_refresh_del()) {
				ret = 4;
			} else {
				ret = 5;
			}
			//几率调整 20130314 zhangrong
			if (jifen > 5000 && ret == 2) {//不出乘法
				ret = RandomUtils.random(3,5);	
			}else if (jifen > 8000 ) {//只出除法
				ret = 3;
			}
			
		}
		return ret;
	}

	public MapDropInfo getBallMapDropInfo(Q_jiaochangBean bean, Map map, boolean boDefault) {
		int ballranidx = RandomUtils.random(10000);
		int itemid = 0;
		if (ballranidx < bean.getQ_redball()) {
			itemid = redBallId;
		} else if (ballranidx >= bean.getQ_redball() && ballranidx < bean.getQ_greenball()) {
			itemid = greenBallId;
		} else {
			itemid = purpleBallId;
		}
		List<Item> items = Item.createItems(itemid, 1, true, 0);
		if (!items.isEmpty()) {
			Item item = items.get(0);
			DropGoodsInfo info = new DropGoodsInfo();
			info.setDropGoodsId(item.getId());
			info.setItemModelId(item.getItemModelId());
			info.setNum(item.getNum());
			Position position = getPosition(bean.getQ_x(), bean.getQ_y(), map.getMapModelid());
			info.setX(position.getX());
			info.setY(position.getY());
			//info.setIntensify((byte) bean.getQ_id());//TODO 临时用
			info.setDropTime(System.currentTimeMillis());
			item.setGridId(bean.getQ_id());
			int idx = RandomUtils.random(3);
			switch (idx) {
				case 0:
					idx = bean.getQ_change_time1();
					break;
				case 1:
					idx = bean.getQ_change_time2();
					break;
				case 2:
					idx = bean.getQ_change_time3();
					break;
			}
			if (boDefault) {
				idx = idx + 30;
			}
			MapDropInfo mapDropInfo = new MapDropInfo(info, item, map, System.currentTimeMillis() + idx * 1000);
			return mapDropInfo;
		}
		return null;
	}

//	public static class JiaoChangInfo {
//
//		private int maxjifen;
//		private int curjifen;
//		private int maxcombom;
//		private int curcombom;
//		private int actiontype;
//		private int dataid;
//
//		public int getDataid() {
//			return dataid;
//		}
//
//		public void setDataid(int dataid) {
//			this.dataid = dataid;
//		}
//
//		public int getCurcombom() {
//			return curcombom;
//		}
//
//		public void setCurcombom(int curcombom) {
//			this.curcombom = curcombom;
//		}
//
//		public int getCurjifen() {
//			return curjifen;
//		}
//
//		public void setCurjifen(int curjifen) {
//			this.curjifen = curjifen;
//		}
//
//		public int getMaxcombom() {
//			return maxcombom;
//		}
//
//		public void setMaxcombom(int maxcombom) {
//			this.maxcombom = maxcombom;
//		}
//
//		public int getMaxjifen() {
//			return maxjifen;
//		}
//
//		public void setMaxjifen(int maxjifen) {
//			this.maxjifen = maxjifen;
//		}
//
//		public int getActiontype() {
//			return actiontype;
//		}
//
//		public void setActiontype(int actiontype) {
//			this.actiontype = actiontype;
//		}
//	}
//
//	public static class JiaoChangConfig {
//
//		private MapDropInfo[] mapDropInfoArray;
//
//		public MapDropInfo[] getMapDropInfoArray() {
//			return mapDropInfoArray;
//		}
//
//		public void setMapDropInfoArray(MapDropInfo[] mapDropInfoArray) {
//			this.mapDropInfoArray = mapDropInfoArray;
//		}
//	}
}
