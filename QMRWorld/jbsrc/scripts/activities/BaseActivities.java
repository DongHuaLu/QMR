package scripts.activities;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.activities.bean.ActivityInfo;
import com.game.activities.log.ActivitiesLog;
import com.game.activities.message.ResActivitiesInfoWorldMessage;
import com.game.activities.script.IWorldBaseActivityScript;
import com.game.data.bean.Q_activitiesBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.goldexpend.manager.GoldExpendManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.recharge.QueryRecharge;
import com.game.registrar.manager.RegistrarManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.TopData;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;
import com.game.utils.ServerParamUtil;
import com.game.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * 基础活动脚本
 *
 * @author 杨鸿岚
 */
public class BaseActivities implements IWorldBaseActivityScript {

	private static final Logger logger = Logger.getLogger(BaseActivities.class);
	private boolean bodelayCallEveryDay0Clock;
	private long delayCallEveryDay0ClockTime;

	@Override
	public int getId() {
		return ScriptEnum.BASEACTIVITIES;
	}

	public BaseActivities() {
//		if (PlayerManager.getPlayers().isEmpty()) {
		bodelayCallEveryDay0Clock = true;
		delayCallEveryDay0ClockTime = 0;
//		} else {
//			bodelayCallEveryDay0Clock = false;
//		}
		logger.info("基础活动脚本初始化--零点调用：" + (bodelayCallEveryDay0Clock ? 1 : 0));
		logger.info("基础活动脚本初始化成功");
	}

	public void clearActivi(List<Object> param) {
		long playerid = Long.valueOf((String) param.get(0));
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(player);
			playerparams.clear();
			QueryRecharge.updatePlayerParams(player, playerparams);
		}
	}

	public void delayCallEveryDay0Clock(List<Object> param) {
		if (System.currentTimeMillis() >= delayCallEveryDay0ClockTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 3600 * 1000);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 5);
//			ScriptsUtils.delayCall(ScriptEnum.BASEACTIVITIES, "everyDay0Clock", calendar.getTimeInMillis() - System.currentTimeMillis());
			ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "everyDay0Clock");
			delayCallEveryDay0ClockTime = calendar.getTimeInMillis();
		}
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 3600 * 1000);
//		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 5);
//		ScriptsUtils.delayCall(ScriptEnum.BASEACTIVITIES, "everyDay0Clock", calendar.getTimeInMillis() - System.currentTimeMillis());
	}

	/**
	 * 服务器启动的时候和每天凌晨0点0分5秒会调用
	 *
	 * @param param 参数，暂时无用
	 * @return
	 */
	public void everyDay0Clock(List<Object> param) {
		ListIterator<Q_activitiesBean> listIterator = DataManager.getInstance().q_activitiesContainer.getList().listIterator();
		while (listIterator.hasNext()) {
			Q_activitiesBean q_activitiesBean = listIterator.next();
			if (q_activitiesBean != null && !q_activitiesBean.getQ_pay_yuanbao().isEmpty()) {
				if (q_activitiesBean.getQ_listimage().isEmpty()) {
					continue;
				}
				List<Integer> parseList = JSON.parseArray(q_activitiesBean.getQ_pay_yuanbao(), Integer.class);
				if (!parseList.isEmpty()) {
					int type = parseList.get(0);
					switch (type) {
						case 4: { //排行榜
							if (ServerParamUtil.getNormalParamMap().containsKey(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + WorldServer.getInstance().getServerId())) {
								continue;
							}
							int toptype = parseList.get(1);
							int line = parseList.get(2);
							int calday = parseList.get(3);
							int caltype = parseList.get(4);

//							int openday = TimeUtil.getOpenAreaDay();
//							openday = openday < 1 ? 1 : openday;
//							if (calday != openday) {
//								continue;
//							}

							Date opendate = WorldServer.getGameConfig().getServerTimeByServer((Integer) WorldServer.getGameSessions().keySet().toArray()[0]);
							Calendar calendar = Calendar.getInstance();
							//int curday = calendar.get(Calendar.DAY_OF_YEAR);
							long curday = TimeUtil.GetCurTimeInMin(4, calendar.getTimeInMillis());
							calendar.setTime(opendate);
							//int openday = calendar.get(Calendar.DAY_OF_YEAR);
							long openday = TimeUtil.GetCurTimeInMin(4, calendar.getTimeInMillis());
							openday = ((curday - openday) + 1) < 1 ? 1 : ((curday - openday) + 1);
							if (calday != (int)openday) {
								continue;
							}

							TreeMap<TopData, Long> topMap = TopListManager.getInstance().getTreeMap(toptype);
							if (!topMap.isEmpty()) {
								Map<String, String> recordMap = new HashMap<String, String>();
								String recorddate = new SimpleDateFormat().format(System.currentTimeMillis());
								recordMap.put("记录时间", recorddate);
								for (int i = 0; i < topMap.keySet().toArray().length && i < line; i++) {
									if (caltype == 0 && i != (line - 1)) {
										continue;
									}
									TopData topData = (TopData) topMap.keySet().toArray()[i];
									if (topData != null) {
										if (topData.getId() != 0) {
//											Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(topData.getId());
//											if (!playerparams.containsKey(getActivitiesKey(topData.getId(), q_activitiesBean))) {
//												playerparams.put(getActivitiesKey(topData.getId(), q_activitiesBean), "0");
//											}
//											QueryRecharge.updatePlayerParams(topData.getId(), playerparams);
											Player player = PlayerManager.getInstance().getPlayer(topData.getId());
											if (player != null) {
												Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(player);
												if (!playerparams.containsKey(getActivitiesKey(topData.getId(), q_activitiesBean))) {
													playerparams.put(getActivitiesKey(topData.getId(), q_activitiesBean), "0");
												}
												QueryRecharge.updatePlayerParams(player, playerparams);
												recordMap.put("" + (i + 1), Long.toString(player.getId()));
											} else {
												PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(topData.getId());
												if (playerWorldInfo != null) {
													Map<String, String> playerparams = RegistrarManager.getInstance().getPlayerRegistrarParams(Long.valueOf(playerWorldInfo.getAccount()));
													if (!playerparams.containsKey(getActivitiesKey(topData.getId(), q_activitiesBean))) {
														playerparams.put(getActivitiesKey(topData.getId(), q_activitiesBean), "0");
													}
													RegistrarManager.getInstance().savePlayerParams(Long.valueOf(playerWorldInfo.getAccount()), playerparams);
													recordMap.put("" + (i + 1), Long.toString(playerWorldInfo.getId()));
												}
											}
										}
									}
								}
								ServerParamUtil.immediateSaveNormal(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + WorldServer.getInstance().getServerId(), JSON.toJSONString(recordMap, SerializerFeature.WriteClassName));
							}
						}
						break;
					}
				}
			}
		}
		//delayCallEveryDay0Clock();
	}

	public int getPlayerActivitiesState(Player player, Map<String, String> playerparams, Q_activitiesBean q_activitiesBean, ActivityInfo activityInfo) {
		int state = 0;
		List<Integer> parseList = JSON.parseArray(q_activitiesBean.getQ_pay_yuanbao(), Integer.class);
		if (!parseList.isEmpty()) {
			int type = parseList.get(0);
			switch (type) {
				case 0: {//有没有充值
					int result = QueryRecharge.queryRecharge(player, type, 0, 0);
					if (result > 0) {
						if (playerparams.containsKey(getActivitiesKey(player, q_activitiesBean))) {
							if ("1".equals(playerparams.get(getActivitiesKey(player, q_activitiesBean)))) {
								state = 0;
							} else {
								state = 1;
							}
						} else {
							state = 1;
						}
					} else {
						state = 2;
					}
				}
				break;
				case 1: //每日的充值
				case 2: //一段时间的充值
				case 3: {//总的充值
					state = getPlayerRechargeState(player, type, parseList, playerparams, q_activitiesBean, activityInfo);
				}
				break;
				case 4: {//排行榜
					int toptype = parseList.get(1);
					int line = parseList.get(2);
					int calday = parseList.get(3);
					int caltype = parseList.get(4);

//					int openday = TimeUtil.getOpenAreaDay();
//					openday = openday < 1 ? 1 : openday;
//					if (calday != openday) {
//						continue;
//					}

					Date opendate = WorldServer.getGameConfig().getServerTimeByServer((Integer) WorldServer.getGameSessions().keySet().toArray()[0]);
					Calendar calendar = Calendar.getInstance();
					int curday = calendar.get(Calendar.DAY_OF_YEAR);
					calendar.setTime(opendate);
					int openday = calendar.get(Calendar.DAY_OF_YEAR);
					openday = ((curday - openday) + 1) < 1 ? 1 : ((curday - openday) + 1);
//					if (player.getId() == 4592125385488231L) {
//						System.out.println("----------------------------------TOP数据1---------------------------------------");
//						System.out.println("state=" + state + "|curday=" + curday + "|openday=" + openday);
//					}
					if (playerparams.containsKey(getActivitiesKey(player, q_activitiesBean))) {
						if ("1".equals(playerparams.get(getActivitiesKey(player, q_activitiesBean)))) {
							state = 0;
						} else {
							state = 1;
						}
					} else {
						if (calday <= openday) {
							state = -1;
						} else {
							state = 2;
						}
					}
//					if (player.getId() == 4592125385488231L) {
//						System.out.println("----------------------------------TOP数据2---------------------------------------");
//						System.out.println("state=" + state);
//					}
					if (activityInfo != null) {
						if (ServerParamUtil.getNormalParamMap().containsKey(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + WorldServer.getInstance().getServerId())) {
							String jsonString = ServerParamUtil.getNormalParamMap().get(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + WorldServer.getInstance().getServerId());
							if (!jsonString.isEmpty()) {
								Map<String, String> expandMap = new HashMap<String, String>();
								Map<String, String> recordMap = JSON.parseObject(jsonString, Map.class);
								Iterator<String> iterator = recordMap.keySet().iterator();
								while (iterator.hasNext()) {
									String key = iterator.next();
									if (key.equalsIgnoreCase("记录时间")) {
										continue;
									}
									long playerid = Long.valueOf(recordMap.get(key));
									Player recordPlayer = PlayerManager.getInstance().getPlayer(playerid);
									expandMap.clear();
									if (recordPlayer != null) {
										expandMap.put("top", key);
										expandMap.put("name", recordPlayer.getName());
										activityInfo.getInfoExpandList().add(JSON.toJSONString(expandMap));
									} else {
										PlayerWorldInfo recordPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(playerid);
										if (recordPlayerWorldInfo != null) {
											expandMap.put("top", key);
											expandMap.put("name", recordPlayerWorldInfo.getName());
											activityInfo.getInfoExpandList().add(JSON.toJSONString(expandMap));
										}
									}
								}
							}
						}
					}
				}
				break;
				case 7: // 开服开始的元宝消耗记录
				case 5: { // 元宝消耗记录
					if (parseList.size() != 5) {
						logger.error(String.format("活动配置错误(%d),参数不等于5", q_activitiesBean.getQ_id()));
						return state;
					}
					int theType = parseList.get(1); // 0 领取一次 1可重复领取
					int needGold =  parseList.get(2);
					String startYYYYMMDDHHMM = "";
					String endYYYYMMDDHHMM = "";
					if (type == 5) {
						startYYYYMMDDHHMM = "20" + parseList.get(3);
						endYYYYMMDDHHMM = "20" + parseList.get(4);
					} else if (type == 7) {
						if (parseList.size() != 5) {
							logger.error(String.format("活动配置错误(%d),参数不等于5", q_activitiesBean.getQ_id()));
							return state;
						}
						Date opendate = WorldServer.getGameConfig().getServerTimeByServer((Integer) WorldServer.getGameSessions().keySet().toArray()[0]);
						long opentime = opendate.getTime(); //开服时间
						long sTime = opentime + (long)(parseList.get(3) - 1) * 24 * 60 * 60 * 1000;
						sTime = Long.parseLong(new SimpleDateFormat("yyyyMMddhhmmss").format(sTime));
						sTime = sTime / 1000000 * 1000000;
						long eTime = opentime + (long)(parseList.get(4) - 1) * 24 * 60 * 60 * 1000;
						eTime = Long.parseLong(new SimpleDateFormat("yyyyMMddhhmmss").format(eTime));
						eTime = eTime / 1000000 * 1000000 + 235959;
						try {
							sTime = new SimpleDateFormat("yyyyMMddhhmmss").parse(sTime + "").getTime();
							eTime = new SimpleDateFormat("yyyyMMddhhmmss").parse(eTime + "").getTime();
						} catch (Exception e) {
							logger.error(e, e);
							return state;
						}
					    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmm");  
					    startYYYYMMDDHHMM = formatter.format(new Date(sTime));
					    endYYYYMMDDHHMM = formatter.format(new Date(eTime));
//					    MessageUtil.notify_player(player, Notifys.ERROR, String.format("查询时间:%s到%s"
//								, startYYYYMMDDHHMM, endYYYYMMDDHHMM));
					}
					
					int expendGold = GoldExpendManager.getInstance().getExpendGold(player, startYYYYMMDDHHMM, endYYYYMMDDHHMM);
					Map<String, String> expandMap = new HashMap<String, String>();
					expandMap.put("need", String.valueOf(needGold));
					expandMap.put("gold", String.valueOf(expendGold));
					activityInfo.getInfoExpandList().add(JSON.toJSONString(expandMap));
					switch (theType) {
						//不可重复领取
						case 0: {
							if (expendGold >= needGold) {
								if (playerparams.containsKey(getActivitiesKey(player, q_activitiesBean))) {
									if ("1".equals(playerparams.get(getActivitiesKey(player, q_activitiesBean)))) {
										state = 0;
									} else {
										state = 1;
									}
								} else {
									state = 1;
								}
							} else {
								state = 2;
							}
						}
						break;
						//可重复领取
						case 1: {
							int recievenum = expendGold / needGold;
							if (recievenum != 0) {
								int hasreceivenum = playerparams.containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(playerparams.get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
								if (recievenum > hasreceivenum) {
									state = 1;
									player.setRepeatNum(recievenum - hasreceivenum);
								} else if (recievenum == hasreceivenum) {
									state = 2;
								} else {   //已领>可领
									if (activityInfo != null) {
										LogService.getInstance().execute(new ActivitiesLog(player, activityInfo, 2)); //错误日志
									}
									state = 2;
								}
							} else {
								state = 2;
							}
						}
						break;
						default: {
							state = 2;
						}
						break;
					}
				}
				break;
				default: {
					state = 2;
				}
				break;
			}
		}
		return state;
	}

	public int getPlayerRechargeState(Player player, int type, List<Integer> parseList, Map<String, String> playerparams, Q_activitiesBean q_activitiesBean, ActivityInfo activityInfo) {
		int state = 0;
		int result = 0;
		//领取类型
		int resulttype = parseList.get(1);
		//需求元宝数量
		int line = parseList.get(2);
		if (parseList.size() == 5) {
			int starttime = parseList.get(3);
			int endtime = parseList.get(4);
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.set(2000 + starttime / 100000000, (starttime % 100000000 / 1000000) - 1, starttime % 100000000 % 1000000 / 10000, starttime % 100000000 % 1000000 % 10000 / 100, starttime % 100000000 % 1000000 % 10000 % 100, 0);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.set(2000 + endtime / 100000000, (endtime % 100000000 / 1000000) - 1, endtime % 100000000 % 1000000 / 10000, endtime % 100000000 % 1000000 % 10000 / 100, endtime % 100000000 % 1000000 % 10000 % 100, 59);
			result = QueryRecharge.queryRecharge(player, type, startCalendar.getTimeInMillis(), endCalendar.getTimeInMillis());
		} else {
			result = QueryRecharge.queryRecharge(player, type, 0, 0);
		}
		Map<String, String> expandMap = new HashMap<String, String>();
		expandMap.put("need", String.valueOf(line));
		expandMap.put("gold", String.valueOf(result));
		activityInfo.getInfoExpandList().add(JSON.toJSONString(expandMap));
		switch (resulttype) {
			//不可重复领取
			case 0: {
				if (result >= line) {
					if (playerparams.containsKey(getActivitiesKey(player, q_activitiesBean))) {
						if ("1".equals(playerparams.get(getActivitiesKey(player, q_activitiesBean)))) {
							state = 0;
						} else {
							state = 1;
						}
					} else {
						state = 1;
					}
				} else {
					state = 2;
				}
			}
			break;
			//可重复领取
			case 1: {
				int recievenum = result / line;
				if (recievenum != 0) {
					int hasreceivenum = playerparams.containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(playerparams.get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
					if (recievenum > hasreceivenum) {
						state = 1;
						player.setRepeatNum(recievenum - hasreceivenum);
					} else if (recievenum == hasreceivenum) {
						state = 2;
					} else {   //已领>可领
						if (activityInfo != null) {
							LogService.getInstance().execute(new ActivitiesLog(player, activityInfo, 2)); //错误日志
						}
						state = 2;
					}
				} else {
					state = 2;
				}
			}
			break;
			default: {
				state = 2;
			}
			break;
		}
		return state;
	}

	@Override
	public void sendActivitiesInfo(Player player, List<ActivityInfo> activities, boolean force) {
//		if (bodelayCallEveryDay0Clock) {
//			everyDay0Clock(null);
//			bodelayCallEveryDay0Clock = false;
//		}
		logger.error("世界活动脚本版本==2012-11-25 20:00:00");
		ResActivitiesInfoWorldMessage resmsg = new ResActivitiesInfoWorldMessage();
		Iterator<ActivityInfo> it = activities.iterator();
		Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(player);
//		if (player.getId() == 4592125385488231L) {
//			System.out.println("----------------------------------活动数据---------------------------------------");
//			System.out.println(JSON.toJSONString(activities));
//			System.out.println("----------------------------------奖励Key值---------------------------------------");
//			System.out.println(JSON.toJSONString(playerparams));
//			System.out.println("----------------------------------充值历史---------------------------------------");
//			System.out.println(JSON.toJSONString(player.getRechargeHistorys() != null ? player.getRechargeHistorys() : 0));
//			System.out.println("----------------------------------WORLD处理之前---------------------------------------");
//		}
		while (it.hasNext()) {
			ActivityInfo activityInfo = it.next();
			if (activityInfo != null) {
				int state = -1;
				int oldstate = activityInfo.getStatus();
				if (oldstate % 10 == 3) {
					Q_activitiesBean q_activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(activityInfo.getActivityId());
					if (q_activitiesBean != null) {
						player.setRepeatNum(Integer.MAX_VALUE);
						state = getPlayerActivitiesState(player, playerparams, q_activitiesBean, activityInfo);
						if (state <= 0 && q_activitiesBean.getQ_show() != 2 && oldstate / 100 != 1) {
							if (q_activitiesBean.getQ_mainicon().isEmpty()) {
								it.remove();
							} else {
								List<Integer> associateList = JSON.parseArray(q_activitiesBean.getQ_mainicon(), Integer.class);
								if (associateList.isEmpty()) {
									it.remove();
								} else {
									if (!associateList.contains(q_activitiesBean.getQ_id())) {
										it.remove();
									} else {
										int checkstate = 0;
										ListIterator<Integer> listIterator = associateList.listIterator();
										while (listIterator.hasNext()) {
											Integer qactivityid = listIterator.next();
											Q_activitiesBean getQ_activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(qactivityid);
											if (getQ_activitiesBean != null) {
												checkstate = getPlayerActivitiesState(player, playerparams, getQ_activitiesBean, null);
												if (checkstate > 0) {
													break;
												}
											}
										}
										if (checkstate <= 0) {
											it.remove();
										}
									}
								}
							}
						} else {
							if (state == -1) {
								state = 2;
							}
						}
						if (player.getRepeatNum() == Integer.MAX_VALUE) {
							player.setRepeatNum(1);
						}
						activityInfo.setCanreceive(player.getRepeatNum());
					}
				}
				oldstate = oldstate % 100 / 10;
				if (oldstate == 0) {
					oldstate = activityInfo.getStatus() % 100 % 10;
				}
				activityInfo.setStatus(state == -1 ? oldstate : oldstate == 2 ? oldstate : (oldstate == 1 || oldstate == 3) ? state : oldstate);
			}
		}
		resmsg.setActivities(activities);
//		if (player.getId() == 4592125385488231L) {
//			System.out.println("----------------------------------活动数据---------------------------------------");
//			System.out.println(JSON.toJSONString(activities));
//			System.out.println("----------------------------------奖励Key值---------------------------------------");
//			System.out.println(JSON.toJSONString(playerparams));
//			System.out.println("----------------------------------充值历史---------------------------------------");
//			System.out.println(JSON.toJSONString(player.getRechargeHistorys() != null ? player.getRechargeHistorys() : 0));
//			System.out.println("----------------------------------WORLD处理之后---------------------------------------");
//		}
		if (force) {
			MessageUtil.tell_player_message(player, resmsg);
		} else {
			MessageUtil.tell_player_message(player, resmsg);
		}
	}

	@Override
	public void getReward(Player player, ActivityInfo ac, int selected) {
		Map<String, String> playerparams = QueryRecharge.queryPlayerAwardParam(player);
		if (ac != null) {
			int state = -1;
			int oldstate = ac.getStatus();
			Q_activitiesBean q_activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(ac.getActivityId());
			if (q_activitiesBean == null) {
				MessageUtil.notify_player(player, Notifys.ERROR, "没有该活动数据");
				return;
			}
			player.setRepeatNum(Integer.MAX_VALUE);
//			if (player.getId() == 4592125385488231L) {
//				System.out.println("----------------------------------领取活动数据---------------------------------------");
//				System.out.println(JSON.toJSONString(ac));
//				System.out.println("----------------------------------奖励Key值---------------------------------------");
//				System.out.println(JSON.toJSONString(playerparams));
//				System.out.println("----------------------------------充值历史---------------------------------------");
//				System.out.println(JSON.toJSONString(player.getRechargeHistorys() != null ? player.getRechargeHistorys() : 0));
//				System.out.println("----------------------------------领取WORLD处理之前---------------------------------------");
//			}
			if (oldstate % 10 == 3) {
				state = getPlayerActivitiesState(player, playerparams, q_activitiesBean, ac);
				if (state < 0) {
					state = 2;
				}
			}
			oldstate = oldstate % 10;
			ac.setStatus(state == -1 ? oldstate : oldstate == 2 ? oldstate : (oldstate == 1 || oldstate == 3) ? state : oldstate);
//			if (player.getId() == 4592125385488231L) {
//				System.out.println("----------------------------------领取活动数据---------------------------------------");
//				System.out.println(JSON.toJSONString(ac));
//				System.out.println("----------------------------------奖励Key值---------------------------------------");
//				System.out.println(JSON.toJSONString(playerparams));
//				System.out.println("----------------------------------充值历史---------------------------------------");
//				System.out.println(JSON.toJSONString(player.getRechargeHistorys() != null ? player.getRechargeHistorys() : 0));
//				System.out.println("----------------------------------领取WORLD处理之后---------------------------------------");
//			}
			if (ac.getStatus() != 1) {
				if (state == 0) {
					MessageUtil.notify_player(player, Notifys.ERROR, "您已领取");
				} else if (state == 2) {
					MessageUtil.notify_player(player, Notifys.ERROR, "您不能领取");
				}
				ScriptsUtils.callGame(player, ScriptEnum.BASEACTIVITIES, "worldCallSendActivitiesInfo", Long.toString(player.getId()));
				return;
			}
			if (player.getRepeatNum() == Integer.MAX_VALUE) {
				player.setRepeatNum(1);
			}
//			if (ac.getCanreceive() >= 0) {
//				player.setRepeatNum(Math.min(ac.getCanreceive(), player.getRepeatNum()));
//			}
			for (int idx = 0; idx < player.getRepeatNum(); idx++) {
				changeParams(player, playerparams, q_activitiesBean);
			}
			QueryRecharge.updatePlayerParams(player, playerparams);
			ScriptsUtils.callGame(player, ScriptEnum.BASEACTIVITIES, "worldCallGetReward", Long.toString(player.getId()), Integer.toString(ac.getActivityId()), Integer.toString(player.getRepeatNum()), Integer.toString(selected));
//			LogService.getInstance().execute(new ActivitiesLog(player, ac, 3));
			LogService.getInstance().execute(new ActivitiesLog(Long.valueOf(player.getUserId()), player.getId(), q_activitiesBean.getQ_id(), player.getRepeatNum() + "=" + q_activitiesBean.getQ_items(), 3, player.getCountry()));
		}
	}

	public void changeParams(Player player, Map<String, String> playerparams, Q_activitiesBean q_activitiesBean) {
		List<Integer> parseList = JSON.parseArray(q_activitiesBean.getQ_pay_yuanbao(), Integer.class);
		if (!parseList.isEmpty()) {
			int type = parseList.get(0);
			switch (type) {
				case 0: {//有没有充值
					playerparams.put(getActivitiesKey(player, q_activitiesBean), "1");
				}
				break;
				case 1: //每日的充值
				case 2: //一段时间的充值
				case 3: {//总的充值
					int resulttype = parseList.get(1);
					int line = parseList.get(2);
					switch (resulttype) {
						case 0: {
							playerparams.put(getActivitiesKey(player, q_activitiesBean), "1");
						}
						break;
						case 1: {
							int result = 0;
							if (parseList.size() == 5) {
								int starttime = parseList.get(3);
								int endtime = parseList.get(4);
								Calendar startCalendar = Calendar.getInstance();
								startCalendar.set(2000 + starttime / 100000000, (starttime % 100000000 / 1000000) - 1, starttime % 100000000 % 1000000 / 10000, starttime % 100000000 % 1000000 % 10000 / 100, starttime % 100000000 % 1000000 % 10000 % 100, 0);
								Calendar endCalendar = Calendar.getInstance();
								endCalendar.set(2000 + endtime / 100000000, (endtime % 100000000 / 1000000) - 1, endtime % 100000000 % 1000000 / 10000, endtime % 100000000 % 1000000 % 10000 / 100, endtime % 100000000 % 1000000 % 10000 % 100, 59);
								result = QueryRecharge.queryRecharge(player, type, startCalendar.getTimeInMillis(), endCalendar.getTimeInMillis());
							} else {
								result = QueryRecharge.queryRecharge(player, type, 0, 0);
							}
							int recievenum = result / line;
							if (recievenum != 0) {
								int hasreceivenum = playerparams.containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(playerparams.get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
								if (recievenum > hasreceivenum) {
									hasreceivenum++;
									playerparams.put(getActivitiesKey(player, q_activitiesBean), Integer.toString(hasreceivenum));
								}
							}
						}
						break;
					}
				}
				break;
				case 4: {//排行榜
					playerparams.put(getActivitiesKey(player, q_activitiesBean), "1");
				}
				break;
				case 5:
				case 7: {
					if (parseList.size() != 5) {
						logger.error(String.format("活动配置错误(%d),参数不等于5", q_activitiesBean.getQ_id()));
						return ;
					}
					int theType = parseList.get(1); // 0 领取一次 1可重复领取
					int needGold =  parseList.get(2);
					String startYYYYMMDDHHMM = "";
					String endYYYYMMDDHHMM = "";
					if (type == 5) {
						startYYYYMMDDHHMM = "20" + parseList.get(3);
						endYYYYMMDDHHMM = "20" + parseList.get(4);
					} else if (type == 7) {
						Date opendate = WorldServer.getGameConfig().getServerTimeByServer((Integer) WorldServer.getGameSessions().keySet().toArray()[0]);
						long opentime = opendate.getTime(); //开服时间
						long sTime = opentime + (long)(parseList.get(3) - 1) * 24 * 60 * 60 * 1000;
						sTime = Long.parseLong(new SimpleDateFormat("yyyyMMddhhmmss").format(sTime));
						sTime = sTime / 1000000 * 1000000;
						long eTime = opentime + (long)(parseList.get(4) - 1) * 24 * 60 * 60 * 1000;
						eTime = Long.parseLong(new SimpleDateFormat("yyyyMMddhhmmss").format(eTime));
						eTime = eTime / 1000000 * 1000000 + 235959;
						try {
							sTime = new SimpleDateFormat("yyyyMMddhhmmss").parse(sTime + "").getTime();
							eTime = new SimpleDateFormat("yyyyMMddhhmmss").parse(eTime + "").getTime();
						} catch (Exception e) {
							logger.error(e, e);
							return ;
						}
						
					    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmm");  
					    startYYYYMMDDHHMM = formatter.format(new Date(sTime));
					    endYYYYMMDDHHMM = formatter.format(new Date(eTime));
//					    MessageUtil.notify_player(player, Notifys.ERROR, String.format("查询时间:%s到%s"
//								, startYYYYMMDDHHMM, endYYYYMMDDHHMM));
					}
					
					int expendGold = GoldExpendManager.getInstance().getExpendGold(player, startYYYYMMDDHHMM, endYYYYMMDDHHMM);
					
					switch (theType) {
						case 0: {
							playerparams.put(getActivitiesKey(player, q_activitiesBean), "1");
						}
						break;
						case 1: {
							int recievenum = expendGold / needGold;
							if (recievenum != 0) {
								int hasreceivenum = playerparams.containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(playerparams.get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
								if (recievenum > hasreceivenum) {
									hasreceivenum++;
									playerparams.put(getActivitiesKey(player, q_activitiesBean), Integer.toString(hasreceivenum));
								}
							}
						}
						break;
					}
				}
				break;
			}
		}
	}

	public String getActivitiesKey(long playerid, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean != null) {
			if (q_activitiesBean.getQ_titleimage().equalsIgnoreCase("0")) {
				return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id();
			} else {
				return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + playerid;
			}
		}
		return "";
	}

	public String getActivitiesKey(PlayerWorldInfo playerWorldInfo, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean != null) {
			if (q_activitiesBean.getQ_titleimage().equalsIgnoreCase("0")) {
				return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id();
			} else {
				if (playerWorldInfo != null) {
					return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + playerWorldInfo.getId();
				}
			}
		}
		return "";
	}

	public String getActivitiesKey(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean != null) {
			if (q_activitiesBean.getQ_titleimage().equalsIgnoreCase("0")) {
				return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id();
			} else {
				if (player != null) {
					return q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + player.getId();
				}
			}
		}
		return "";
	}
}
