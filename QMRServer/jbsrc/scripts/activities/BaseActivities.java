package scripts.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.activities.bean.ActivityInfo;
import com.game.activities.log.ActivitiesLog;
import com.game.activities.message.ReqActivitiesInfoToWorldMessage;
import com.game.activities.message.ReqGetRewardToWorldMessage;
import com.game.activities.message.ResCallEveryDay0ClockMessage;
import com.game.activities.script.IBaseActivityScript;
import com.game.arrow.manager.ArrowManager;
import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.ItemReasonsInfo;
import com.game.backpack.log.ItemChangeLog;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResGetItemReasonsMessage;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemAction;
import com.game.backpack.structs.ItemChangeAction;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.count.structs.CountTypes;
import com.game.country.manager.CountryAwardManager;
import com.game.country.manager.CountryManager;
import com.game.country.structs.KingCity;
import com.game.country.structs.KingData;
import com.game.data.bean.Q_activitiesBean;
import com.game.data.bean.Q_activities_dropBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.drop.structs.MapDropInfo;
import com.game.fight.structs.Fighter;
import com.game.gem.struts.Gem;
import com.game.guild.manager.GuildServerManager;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.monster.structs.Monster;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.realm.structs.Realm;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.ServerParamUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;

/**
 * 基础活动脚本
 *
 * @author 杨鸿岚
 */
public class BaseActivities implements IBaseActivityScript {
	protected Logger log = Logger.getLogger(BaseActivities.class);
	private static final Logger logger = Logger.getLogger(BaseActivities.class);
	private boolean bodelayCallEveryDay0Clock;
	private long delayCallEveryDay0ClockTime;
	
	//通用活动列表
	private List<Q_activitiesBean> commonActivitiesList = new ArrayList<Q_activitiesBean>();
	//新区活动列表
	private List<Q_activitiesBean> newActivitiesList = new ArrayList<Q_activitiesBean>();
	//老区活动列表
	private List<Q_activitiesBean> oldActivitiesList = new ArrayList<Q_activitiesBean>();
	//其他活动列表
	private List<Q_activitiesBean> otherActivitiesList = new ArrayList<Q_activitiesBean>();
	
	
	private static final int ACTIVITIESLISTTYPE_COMMON = 0;
	private static final int ACTIVITIESLISTTYPE_NEW = 1;
	private static final int ACTIVITIESLISTTYPE_OLD = 2;
	private static final int ACTIVITIESLISTTYPE_OTHER = 3;

	
	//TODO 活动掉落，换购时限物品排除列表					//青叶
	private int[] excludeids = {3015,3016,3020,3022, 16027, 16028, 16029,
			18131,18132,18133,18134,//元旦快乐4个字
			18128,18129,18130,      //秦美人3个字
			18135,					//铁精
			18136,	//幸运草
			18139,
			18140,
			18158,	//莲蓉
			18159,	//黑芝麻
			18160,	//白芝麻
			18161,	//花生
			18162,	//鲜肉
			18163,	//水
			18164,	//白糖
			18165,	//糯米
			18172,
			
	};
	private List<Integer> excludeidlist = new ArrayList<Integer>();

	//过期也可以在活动兑换的道具ID   9130=五彩坚硬石
	private int[] expiredallows = {9130,9131};
	private List<Integer> expiredallowlist = new ArrayList<Integer>();
	
	
	@Override
	public int getId() {
		return ScriptEnum.BASEACTIVITIES;
	}

	public BaseActivities() {
//		if (!WServer.STARTFINISH) {
		bodelayCallEveryDay0Clock = true;
		delayCallEveryDay0ClockTime = 0;
//		} else {
//			bodelayCallEveryDay0Clock = false;
//		}
		//活动掉落，换购时限物品排除列表
		if (excludeidlist == null) {
			excludeidlist = new ArrayList<Integer>();
		}
		for (int i = 0; i < excludeids.length; i++) {
			int j = excludeids[i];
			excludeidlist.add(j);
		}
		
		//过期可用道具列表
		if (expiredallowlist == null) {
			expiredallowlist = new ArrayList<Integer>();
		}
		for (int i = 0; i < expiredallows.length; i++) {
			int j = expiredallows[i];
			expiredallowlist.add(j);
		}
		
		
		////////////////////////////////
		commonActivitiesList.clear();
		newActivitiesList.clear();
		oldActivitiesList.clear();
		otherActivitiesList.clear();
		ListIterator<Q_activitiesBean> listIterator = DataManager.getInstance().q_activitiesContainer.getList().listIterator();
		while (listIterator.hasNext()) {
			Q_activitiesBean q_activitiesBean = listIterator.next();
			if (q_activitiesBean != null) {
				if (q_activitiesBean.getQ_listimage().isEmpty()) {
					continue;
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("0")) {
					//通用活动
					commonActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
					//新区活动
					newActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
					//老区活动
					oldActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("3")) {
					//其他活动
					otherActivitiesList.add(q_activitiesBean);
				} else {
					continue;
				}
			}
		}
		logger.info("基础活动脚本初始化--零点调用：" + (bodelayCallEveryDay0Clock ? 1 : 0) + "|活动数目：commonActivitiesList=" + commonActivitiesList.size() + ",newActivitiesList=" + newActivitiesList.size() + ",oldActivitiesList=" + oldActivitiesList.size() + ",otherActivitiesList=" + otherActivitiesList.size());
		logger.info("基础活动脚本初始化成功");
	}

	public List<Q_activitiesBean> getCurrActivitiesList(int listtype) {
		List<Q_activitiesBean> getList = null;
		switch (listtype) {
			case ACTIVITIESLISTTYPE_COMMON: {
				getList = commonActivitiesList;
			}
			break;
			case ACTIVITIESLISTTYPE_NEW: {
				getList = newActivitiesList;
			}
			break;
			case ACTIVITIESLISTTYPE_OLD: {
				getList = oldActivitiesList;
			}
			break;
			case ACTIVITIESLISTTYPE_OTHER: {
				getList = otherActivitiesList;
			}
			break;
		}
		return getList;
	}

	/**
	 * 0点活动统计
	 * @param param
	 */
	public void delayCallEveryDay0Clock(List<Object> param) {
		if (System.currentTimeMillis() >= delayCallEveryDay0ClockTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 3600 * 1000);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			//ScriptsUtils.delayCall(ScriptEnum.BASEACTIVITIES, "everyDay0Clock", calendar.getTimeInMillis() - System.currentTimeMillis());
			ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "everyDay0Clock");
			MessageUtil.tell_world_message(new ResCallEveryDay0ClockMessage());
			delayCallEveryDay0ClockTime = calendar.getTimeInMillis();
		}
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(calendar.getTimeInMillis() + 24 * 3600 * 1000);
//		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 5);
//		ScriptsUtils.delayCall(ScriptEnum.BASEACTIVITIES, "everyDay0Clock", calendar.getTimeInMillis() - System.currentTimeMillis());
//		MessageUtil.tell_world_message(new ResCallEveryDay0ClockMessage());
	}

	/**
	 * 服务器启动的时候和每天凌晨0点0分5秒会调用
	 *
	 * @param param 参数，暂时无用
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void everyDay0Clock(List<Object> param) {
		List<Q_activitiesBean> reloadcommonActivitiesList = new ArrayList<Q_activitiesBean>();
		List<Q_activitiesBean> reloadnewActivitiesList = new ArrayList<Q_activitiesBean>();
		List<Q_activitiesBean> reloadoldActivitiesList = new ArrayList<Q_activitiesBean>();
		List<Q_activitiesBean> reloadotherActivitiesList = new ArrayList<Q_activitiesBean>();
		ListIterator<Q_activitiesBean> dataIterator = DataManager.getInstance().q_activitiesContainer.getList().listIterator();
		while (dataIterator.hasNext()) {
			Q_activitiesBean q_activitiesBean = dataIterator.next();
			if (q_activitiesBean != null) {
				if (q_activitiesBean.getQ_listimage().isEmpty()) {
					continue;
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("0")) {
					reloadcommonActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
					reloadnewActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
					reloadoldActivitiesList.add(q_activitiesBean);
				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("3")) {
					reloadotherActivitiesList.add(q_activitiesBean);
				} else {
					continue;
				}
			}
		}
		commonActivitiesList = reloadcommonActivitiesList;
		newActivitiesList = reloadnewActivitiesList;
		oldActivitiesList = reloadoldActivitiesList;
		otherActivitiesList = reloadotherActivitiesList;

		boolean boNewOrOld = false;//为TRUE不在检查OLD类型
		for (int i = ACTIVITIESLISTTYPE_COMMON; i <= ACTIVITIESLISTTYPE_OTHER; i++) {
			List<Q_activitiesBean> currActivitiesList = getCurrActivitiesList(i);
			if (currActivitiesList == null || currActivitiesList.isEmpty()) {
				continue;
			}
			if (i == ACTIVITIESLISTTYPE_OLD && boNewOrOld) {
				continue;
			}
			ListIterator<Q_activitiesBean> listIterator = currActivitiesList.listIterator();
			while (listIterator.hasNext()) {
				//获取活动
				Q_activitiesBean q_activitiesBean = listIterator.next();
				if (q_activitiesBean != null && !q_activitiesBean.getQ_other().isEmpty()) {
					/**
					 * 活动需求类型，第一位数字代表类型，后面为参数
					 * [1,3] 玩家vip等级 [VIP,VIP等级]
					 * [2,1,X] 王城任期 [王城,任期,天数]
					 * [3,0,id,num] 商城购买 [商城,是否可以多次领取(0不可以,1可以),物品ID,需购买数量]
					 * [4,50] 当天获得军功 [军功,军功数量]
					 * [5,2,1,1007,500,0] 换购活动 [换购,换购类型(1无限换购,2每天换购,3活动期间换购),可换购数量,换购物品ID,换购物品数量,是否弹出换购面板(0不弹出,1弹出)]
					 * [6,3] 军功翻倍 [军功翻倍,倍数]
					 * [7,0,300] 坐骑活动 [坐骑活动,坐骑等级,坐骑祝福值]
					 * [8,4] 弓箭活动 [弓箭活动,弓箭等级]
					 * [9,2,1,1007,500,1008,200,0] 任一物品换购 [任一物品换购,换购类型(1无限换购,2每天换购,3活动期间换购),可换购数量,换购物品1ID,换购物品1数量,换购物品2ID,换购物品2数量,...是否弹出换购面板(0不弹出,1弹出)]
					 * [10,20] 宝石活动 [宝石活动,全部宝石之和等级]
					 * [11,5] 骑兵活动 [骑兵活动,骑兵等级]
					 * [12,6] 境界活动 [境界活动,境界等级]
					 */
					List<Integer[]> otherList = null;
					try {
						otherList = JSON.parseArray(q_activitiesBean.getQ_other(), Integer[].class);
					} catch (Exception e) {
						logger.error(q_activitiesBean.getQ_other()+"解析出错"+e,e);
					}
					
					if (!otherList.isEmpty()) {
						Iterator<Integer[]> iterator = otherList.iterator();
						while (iterator.hasNext()) {
							Integer[] datas = iterator.next();
							if (datas.length < 2) {
								continue;
							}
							int key = datas[0];
							switch (key) {
								case 3: {//元宝商城购买
									if (!checkZoneServer(null, q_activitiesBean)) {
										continue;
									}
									if (!checkTime(null, q_activitiesBean)) {
										continue;
									}
									HashMap<String, List<Integer>> shopbuyMap = null;
									if (ServerParamUtil.getNormalParamMap().containsKey(ServerParamUtil.SHOPBUY + "_" + WServer.getInstance().getServerId())) {
										String shopbuyString = ServerParamUtil.getNormalParamMap().get(ServerParamUtil.SHOPBUY + "_" + WServer.getInstance().getServerId());
										shopbuyMap = JSON.parseObject(shopbuyString, HashMap.class);
									}
									if (shopbuyMap == null) {
										shopbuyMap = new HashMap<String, List<Integer>>();
									}
									Calendar calendar = Calendar.getInstance();
									String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
									Iterator<String> shopbuyIterator = shopbuyMap.keySet().iterator();
									while (shopbuyIterator.hasNext()) {
										String dateString = shopbuyIterator.next();
										if (!date.equalsIgnoreCase(dateString)) {
											shopbuyIterator.remove();
										}
									}
									List<Integer> shopbuyList = null;
									if (shopbuyMap.containsKey(date)) {
										shopbuyList = shopbuyMap.get(date);
									}
									if (shopbuyList == null) {
										shopbuyList = new ArrayList<Integer>();
									}
									int itemid = 0;
									if (datas.length >= 4) {
										itemid = datas[2];
									}
									if (!shopbuyList.contains(itemid)) {
										shopbuyList.add(itemid);
									}
									shopbuyMap.put(date, shopbuyList);
									ServerParamUtil.threadSaveNormal(ServerParamUtil.SHOPBUY + "_" + WServer.getInstance().getServerId(), JSON.toJSONString(shopbuyMap, SerializerFeature.WriteClassName));
									if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
										boNewOrOld = true;
									}
								}
								break;
								case 6: {//军功任务奖励倍数
									if (!checkZoneServer(null, q_activitiesBean)) {
										continue;
									}
									if (!checkTime(null, q_activitiesBean)) {
										continue;
									}
									HashMap<String, List<Integer>> ranktaskMap = null;
									if (ServerParamUtil.getNormalParamMap().containsKey("RANKTASK" + "_" + WServer.getInstance().getServerId())) {
										String ranktaskString = ServerParamUtil.getNormalParamMap().get("RANKTASK" + "_" + WServer.getInstance().getServerId());
										ranktaskMap = JSON.parseObject(ranktaskString, HashMap.class);
									}
									if (ranktaskMap == null) {
										ranktaskMap = new HashMap<String, List<Integer>>();
									}
									Calendar calendar = Calendar.getInstance();
									String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
									Iterator<String> ranktaskIterator = ranktaskMap.keySet().iterator();
									while (ranktaskIterator.hasNext()) {
										String dateString = ranktaskIterator.next();
										if (!date.equalsIgnoreCase(dateString)) {
											ranktaskIterator.remove();
										}
									}
									List<Integer> ranktaskList = null;
									if (ranktaskMap.containsKey(date)) {
										ranktaskList = ranktaskMap.get(date);
									}
									if (ranktaskList == null) {
										ranktaskList = new ArrayList<Integer>();
									}
									int value = datas[1];
									ranktaskList.clear();
									ranktaskList.add(value);
									ranktaskMap.put(date, ranktaskList);
									ServerParamUtil.threadSaveNormal("RANKTASK" + "_" + WServer.getInstance().getServerId(), JSON.toJSONString(ranktaskMap, SerializerFeature.WriteClassName));
									if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
										boNewOrOld = true;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		//delayCallEveryDay0Clock();
	}

	/**
	 * 世界服返回活动状态信息
	 * @param param
	 */
	public void worldCallSendActivitiesInfo(List<Object> param) {
		long playerid = Long.valueOf((String) param.get(0));
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		if (player != null) {
			sendActivitiesInfo(player, true);
		}
	}

	/**
	 * 获取活动状态信息列表
	 */
	@Override
	public void sendActivitiesInfo(Player player, boolean force) {
//		if (bodelayCallEveryDay0Clock) {
//			everyDay0Clock(null);
//			bodelayCallEveryDay0Clock = false;
//		}
		logger.error("游戏活动脚本版本==2012-11-25 20:00:00");
		ReqActivitiesInfoToWorldMessage reqmsg = new ReqActivitiesInfoToWorldMessage();
		Set<Integer> types = new HashSet<Integer>();
		boolean boNewOrOld = false;//为TRUE不在检查OLD类型
		for (int i = ACTIVITIESLISTTYPE_COMMON; i <= ACTIVITIESLISTTYPE_OTHER; i++) {
			List<Q_activitiesBean> currActivitiesList = getCurrActivitiesList(i);
			if (currActivitiesList == null || currActivitiesList.isEmpty()) {
				continue;
			}
			if (i == ACTIVITIESLISTTYPE_OLD && boNewOrOld) {
				continue;
			}
			ListIterator<Q_activitiesBean> listIterator = currActivitiesList.listIterator();
			while (listIterator.hasNext()) {
				Q_activitiesBean q_activitiesBean = listIterator.next();
				if (q_activitiesBean != null) {
					player.setRepeatNum(Integer.MAX_VALUE);
//				IActivityScript script = (IActivityScript) ManagerPool.scriptManager.getScript(q_activitiesBean.getQ_type());
//				if (script == null) {
//					continue;
//				}

					if (types.contains(q_activitiesBean.getQ_type())) {
						continue;
					}
					if (!checkZoneServer(player, q_activitiesBean)) {
						types.add(q_activitiesBean.getQ_type());
						continue;
					}
					if (!checkTime(player, q_activitiesBean)) {
						if (!checkdelTime(player, q_activitiesBean)) {
							types.add(q_activitiesBean.getQ_type());
							continue;
						}
					}
					int status = checkstate(player, q_activitiesBean);
					if (checkdelTime(player, q_activitiesBean)) {
						status = (status == -1) ? 100 : status + 100;
					}
					if (status == 0 && q_activitiesBean.getQ_show() == 1) {
						if (!q_activitiesBean.getQ_mainicon().isEmpty()) {
							ListIterator<ActivityInfo> listIterator1 = reqmsg.getActivities().listIterator();
							while (listIterator1.hasNext()) {
								ActivityInfo activityInfo = listIterator1.next();
								if (activityInfo != null) {
									Q_activitiesBean otherBean = DataManager.getInstance().q_activitiesContainer.getMap().get(activityInfo.getActivityId());
									if (otherBean != null && otherBean.getQ_type() == q_activitiesBean.getQ_type()) {
										listIterator1.remove();
									}
								}
							}
							types.add(q_activitiesBean.getQ_type());
						}
						continue;
					}
					if (status == -1) {
						status = 0;
					}
					if (status % 10 != 3) {
						status = status % 10;
					}
					if (player.getRepeatNum() == Integer.MAX_VALUE) {
						player.setRepeatNum(1);
					}
					ActivityInfo info = new ActivityInfo();
					info.setActivityType(q_activitiesBean.getQ_maintype());
					info.setGrouptype(q_activitiesBean.getQ_type());
					info.setActivityId(q_activitiesBean.getQ_id());
					info.setActivityDescribe(q_activitiesBean.getQ_info());
					info.setActivityReward(q_activitiesBean.getQ_items());
					info.setRecommend(q_activitiesBean.getQ_tuijian());
					info.setCanrepeated(q_activitiesBean.getQ_canrepeated());
					info.setCanreceive(player.getRepeatNum());
					info.setStatus(status);
					long remainTime = getRemainTime(player, q_activitiesBean);
					long startTime = getStartTime(player, q_activitiesBean);
					long endTime = getEndTime(player, q_activitiesBean);
					startTime = startTime == -1 ? 0 : startTime;
					endTime = endTime == -1 ? 0 : endTime;
					if (remainTime / 1000 <= 0 && remainTime != -1) {
						if (status / 100 == 1) {
							remainTime = 0;
						} else {
							continue;
						}
					}
					if (remainTime == -1) {
						remainTime = 0;
					}
					info.setDuration((int) (remainTime / 1000));
					info.setStarttime((int) (startTime / 1000));
					info.setEndtime((int) (endTime / 1000));
					info.setInfoExpandList(stinfoExpand(player, q_activitiesBean));
					try {
						info.setSign(q_activitiesBean.getQ_flag()+"###"+q_activitiesBean.getQ_resflag());
					} catch (Exception e) {
						logger.error("q_activitiesBean.getQ_flag()null");
					}

					reqmsg.getActivities().add(info);
					if (q_activitiesBean.getQ_show() == 1) {
						if (q_activitiesBean.getQ_mainicon().isEmpty()) {
							types.add(q_activitiesBean.getQ_type());
						}
					}
					if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
						boNewOrOld = true;
					}
				}
			}
		}
		reqmsg.setForce(force ? (byte) 1 : (byte) 0);
		reqmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(reqmsg);
	}

	public void worldCallGetReward(List<Object> param) {
		long playerid = Long.valueOf((String) param.get(0));
		int qid = Integer.valueOf((String) param.get(1));
		int repeat = Integer.valueOf((String) param.get(2));
		int selected = Integer.valueOf((String) param.get(3));
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		Q_activitiesBean q_activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(qid);
		if (player != null && q_activitiesBean != null) {
			getReward(player, q_activitiesBean, repeat, selected);
			sendActivitiesInfo(player, true);
		}
	}

	@Override
	public void getReward(Player player, int id, int type, int selected) {
		LogService.getInstance().execute(new ActivitiesLog(Long.valueOf(player.getUserId()), player.getId(), id, "", 4, player.getCreateServerId())); //申请领取日志
		if (player != null) {
			Q_activitiesBean q_activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(id);
			if (q_activitiesBean != null) {
				//检测时间
				if (!checkTime(player, q_activitiesBean)) {	
					if (!checkdelTime(player, q_activitiesBean)) {
						log.error(player.getId()+","+player.getName() + "活动未开放：ID"+id);
						return;
					}
				}
				
				if(q_activitiesBean.getQ_items()!=null){
					String[] activities_datas = q_activitiesBean.getQ_items().split(Symbol.JINGHAO_REG);
					if(selected >= activities_datas.length){
						log.error(player.getId()+","+player.getName() + "活动："+id + "选择物品" + selected +"不存在");
						return;
					}
				}
				
				player.setRepeatNum(Integer.MAX_VALUE);
				int status = checkstate(player, q_activitiesBean);
				if (player.getRepeatNum() == Integer.MAX_VALUE) {
					player.setRepeatNum(1);
				}
				if (type > 0) {
					type = Math.min(type, player.getRepeatNum());
					player.setRepeatNum(type);
				}
				if (status % 10 == 3) { //转到世界服
					ReqGetRewardToWorldMessage reqmsg = new ReqGetRewardToWorldMessage();
					ActivityInfo info = new ActivityInfo();
					info.setActivityType(q_activitiesBean.getQ_maintype());
					info.setGrouptype(q_activitiesBean.getQ_type());
					info.setActivityId(q_activitiesBean.getQ_id());
					info.setActivityDescribe(q_activitiesBean.getQ_info());
					info.setActivityReward(q_activitiesBean.getQ_items());
					info.setRecommend(q_activitiesBean.getQ_tuijian());
					info.setCanrepeated(q_activitiesBean.getQ_canrepeated());
					info.setCanreceive(player.getRepeatNum());
					info.setStatus(status);
					long remainTime = getRemainTime(player, q_activitiesBean);
					long startTime = getStartTime(player, q_activitiesBean);
					long endTime = getEndTime(player, q_activitiesBean);
					startTime = startTime == -1 ? 0 : startTime;
					endTime = endTime == -1 ? 0 : endTime;
					if (remainTime / 1000 <= 0 && remainTime != -1) {
						if (checkdelTime(player, q_activitiesBean)) {
							status = (status == -1) ? 100 : status + 100;
						}
						if (status / 100 == 1) {
							remainTime = 0;
						} else {
							return;
						}
					}
					if (remainTime == -1) {
						remainTime = 0;
					}
					info.setDuration((int) (remainTime / 1000));
					info.setStarttime((int) (startTime / 1000));
					info.setEndtime((int) (endTime / 1000));
					try {
						info.setSign(q_activitiesBean.getQ_flag()+"###"+q_activitiesBean.getQ_resflag());
					} catch (Exception e) {
						logger.error("q_activitiesBean.getQ_flag()null");
					}
					reqmsg.setActivityinfo(info);
					reqmsg.setPlayerid(player.getId());
					reqmsg.setSelected(selected);
					MessageUtil.send_to_world(reqmsg);
				} else {
					if (status == 1) {
						getReward(player, q_activitiesBean, player.getRepeatNum(), selected);
						sendActivitiesInfo(player, true);//刷新
					}
				}
			}
		}
	}

	public void getReward(Player player, Q_activitiesBean q_activitiesBean, int repeat, int selected) {
		if (player == null) {
			return;
		}
		if (q_activitiesBean == null) {
			return;
		}
		String activities_data = null;
		String[] activities_datas = q_activitiesBean.getQ_items().split(Symbol.JINGHAO_REG);
		if(selected < activities_datas.length){
			activities_data = activities_datas[selected];
		}else{
			activities_data = activities_datas[0];
		}
		List<Item> itemlist = new ArrayList<Item>();
		int itemNum = 0;
		//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
		String[] activities_dataStrings = activities_data.split(Symbol.FENHAO_REG);
		for (int i = 0; i < activities_dataStrings.length; i++) {
			String activities_item = activities_dataStrings[i];
			if (activities_item != null && !activities_item.isEmpty()) {
				String[] itemdataStrings = activities_item.split(Symbol.DOUHAO_REG);
				int itemid = 0;
				int itemnum = 0;
				int sex = 0;//1 男 2 女
				boolean bind = true;
				long losttime = 0;
				int gradenum = 0;
				int append = 0;
				for (int j = 0; j < itemdataStrings.length; j++) {
					String item_data = itemdataStrings[j];
					if (item_data != null && !item_data.isEmpty()) {
						switch (j) {
							case 0: {
								itemid = Integer.valueOf(item_data);
							}
							break;
							case 1: {
								itemnum = Integer.valueOf(item_data);
							}
							break;
							case 2: {
								sex = Integer.valueOf(item_data);
							}
							break;
							case 3: {
								int bindidx = Integer.valueOf(item_data);
								bind = (bindidx == 1) ? true : false;
							}
							break;
							case 4: {
								//2种到期时间：到达指定期限， 从使用后+XX小时
								if (!item_data.equals("") &&  item_data.contains(":")) {
									Date ydate = TimeUtil.getDateByString(item_data);
									if (ydate != null) {
										losttime = ydate.getTime();
									}else {
										losttime = 24*60*60*1000;	//如果格式错误，默认24小时后过期
									}
								}else {
									losttime = Long.valueOf(item_data);
								}
							}
							break;
							case 5: {
								gradenum = Integer.valueOf(item_data);
							}
							break;
							case 6: {
								append = Integer.valueOf(item_data);
							}
							break;
						}
					}
				}
				if (itemdataStrings.length >= 2) {
					if (itemid != 0 && itemnum != 0) {
						if (sex == 0 || sex == player.getSex()) {
							switch (itemid) {
								case -1: {
									Item moneyItem = Item.createMoney(itemnum);
									if (moneyItem != null) {
										itemlist.add(moneyItem);
									}
								}
								break;
								case -2: {
									Item goldItem = Item.createGold(itemnum, true);
									if (goldItem != null) {
										itemlist.add(goldItem);
									}
								}
								break;
								case -3: {
									Item zhenqiItem = Item.createZhenQi(itemnum);
									if (zhenqiItem != null) {
										itemlist.add(zhenqiItem);
									}
								}
								break;
								case -4: {
									Item expItem = Item.createExp(itemnum);
									if (expItem != null) {
										itemlist.add(expItem);
									}
								}
								break;
								case -5: {
									Item bindgoldItem = Item.createBindGold(itemnum);
									if (bindgoldItem != null) {
										itemlist.add(bindgoldItem);
									}
								}
								break;
								case -6: {
									Item fightspiritItem = Item.createFightSpirit(itemnum);
									if (fightspiritItem != null) {
										itemlist.add(fightspiritItem);
									}
								}
								break;
								case -7: {
									Item rankItem = Item.createRank(itemnum);
									if (rankItem != null) {
										itemlist.add(rankItem);
									}
								}
								break;
								default: {
									if(losttime > 0 && losttime < 2100000000){
										losttime =  System.currentTimeMillis() + losttime * 1000;
									}
									List<Item> items = Item.createItems(itemid, itemnum, bind, losttime, gradenum, append);
									if (!items.isEmpty()) {
										itemlist.addAll(items);
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		if (!itemlist.isEmpty()) {
			repeat = Math.min(repeat, 999);
			removeNumByActivitiesBean(player, q_activitiesBean, repeat);
			List<Item> addItems = new ArrayList<Item>();
			ListIterator<Item> listIterator = itemlist.listIterator();
			while (listIterator.hasNext()) {
				Item item = listIterator.next();
				if (item != null) {
					if (item.getItemModelId() < 0) {//编号负数的物品，铜币，元宝，礼金等
						int num = item.getNum() * repeat;
						if (num >= 0 && num <= Integer.MAX_VALUE) {
							item.setNum(item.getNum() * repeat);
							addItems.add(item);
						}
					} else {//普通物品
						int num = item.getNum() * repeat;
						if (num >= 0 && num <= Integer.MAX_VALUE) {
							List<Item> items = new ArrayList<Item>();
							if (item instanceof Equip) {
								Equip equip = (Equip) item;
								items = Item.createItems(item.getItemModelId(), num, item.isBind(), (long) equip.getLosttime() * 1000, equip.getGradeNum(), equip.getAttributes().size());
							} else {
								items = Item.createItems(item.getItemModelId(), num, item.isBind(), (long) item.getLosttime() * 1000, 0, 0);
							}
							addItems.addAll(items);
							itemNum += items.size();
						}
					}
				}
			}
			ResGetItemReasonsMessage sendMessage = new ResGetItemReasonsMessage();
			if (BackpackManager.getInstance().getEmptyGridNum(player) >= itemNum) {
				for (int i = 0; i < addItems.size(); i++) {
					Item item = addItems.get(i);
					if (item != null) {
						if (item.getItemModelId() == -1) {//Money
							if (!BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击附件领取。"), (byte) 1, item.getNum(), null);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -2) {//Gold
							if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createBindGold(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -3) {//zhenqi
							if (PlayerManager.getInstance().addZhenqi(player, item.getNum(), AttributeChangeReason.ACTIVITIESADD) == 0 && item.getNum() != 0) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createZhenQi(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中真气未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中真气未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -4) {//exp
							PlayerManager.getInstance().addExp(player, item.getNum(), AttributeChangeReason.ACTIVITIESADD);
						} else if (item.getItemModelId() == -5) {//bindgold
							if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createBindGold(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -6) {//fightspirit
							if (!ArrowManager.getInstance().addFightSpiritNum(player, ArrowManager.FightType_RI, item.getNum(), true, ArrowReasonsType.ACTIVITIES)) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createFightSpirit(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中七曜战魂未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中七曜战魂未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -7) {//rank
							RankManager.getInstance().addranknum(player, item.getNum(), RankType.Activities);
//							if (!RankManager.getInstance().addranknum(player, item.getNum(), RankType.Activities)) {
//								List<Item> items = new ArrayList<Item>();
//								items.add(Item.createRank(item.getNum()));
//								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "活动系统邮件", "由于未知原因，您的活动中军功未领取成功，请点击附件领取。", (byte) 1, 0, items);
//								MessageUtil.notify_player(player, Notifys.ERROR, "由于未知原因，您的活动中军功未领取成功，请点击邮件领取。");
//							}
						} else {//普通物品
							List<Item> items = new ArrayList<Item>();
							if (item instanceof Equip) {
								Equip equip = (Equip) item;
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long) equip.getLosttime() * 1000, equip.getGradeNum(), equip.getAttributes().size());
							} else {
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long) item.getLosttime() * 1000, 0, 0);
							}
							if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, Config.getId())) {
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，该物品未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中有物品未领取成功，请点击邮件领取。"));
							} else {
								ItemReasonsInfo itemReasonsInfo = new ItemReasonsInfo();
								if (!items.isEmpty()) {
									itemReasonsInfo.setItemId(items.get(0).getId());
								}
								itemReasonsInfo.setItemModelId(item.getItemModelId());
								itemReasonsInfo.setItemNum(item.getNum());
								itemReasonsInfo.setItemReasons(0);
								sendMessage.getItemReasonsInfoList().add(itemReasonsInfo);
							}
						}
					}
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹格子不足，不能获得活动物品，已经转发邮件。"));
				MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于您的包裹格子不足，活动物品未领取成功，请点击附件领取。"), (byte) 1, 0, addItems);
			}
			for (int idx = 0; idx < repeat; idx++) {
				if (q_activitiesBean.getQ_login_limit() != 0) {
					int loginlimit = q_activitiesBean.getQ_login_limit();
					loginlimit = loginlimit % 10000;
					int btw = player.getLoginTimes() - loginlimit;
					if (btw >= 0) {
						Calendar cal = Calendar.getInstance();
						if (q_activitiesBean.getQ_login_limit() < 10000) {
							cal.add(Calendar.DATE, -btw);
						}
						String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
						player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), date);
					}
				} else {
					if (!q_activitiesBean.getQ_other().isEmpty()) {
						List<Integer[]> otherList = null;
						try {
							otherList = JSON.parseArray(q_activitiesBean.getQ_other(), Integer[].class);
						} catch (Exception e) {
							logger.error(q_activitiesBean.getQ_other()+"解析出错"+e,e);
						}
						if (!otherList.isEmpty()) {
							ListIterator<Integer[]> iterator = otherList.listIterator();
							while (iterator.hasNext()) {
								Integer[] datas = iterator.next();
								if (datas.length < 2) {
									continue;
								}
								int key = datas[0];
								switch (key) {
									case 2: {//王城
										player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
										KingCity kingCity = CountryManager.getInstance().getKingcity();
										if (kingCity != null) {
											int limitCount = 0;
											if (kingCity.getSalarymap().containsKey(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id())) {
												limitCount = kingCity.getSalarymap().get(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id());
												if (limitCount < GuildServerManager.Guild_MaxNum) {
													limitCount++;
													kingCity.getSalarymap().put(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id(), limitCount);
												}
											} else {
												limitCount++;
												kingCity.getSalarymap().put(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id(), limitCount);
											}
										}
									}
									break;
									case 3: {//元宝商城购买
										int value = datas[1];
										int itemid = 0;
										int linenum = 0;
										if (datas.length >= 4) {
											itemid = datas[2];
											linenum = datas[3];
										}
										int buynum = 0;
										long startTime = getStartTime(player, q_activitiesBean);
										long endTime = getEndTime(player, q_activitiesBean);
										while (startTime <= endTime) {
											String date = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
											if (player.getVariables().containsKey(itemid + "_" + date)) {
												buynum += Integer.valueOf(player.getVariables().get(itemid + "_" + date));
											}
											startTime += 24 * 3600 * 1000;
										}
//										Calendar calendar = Calendar.getInstance();
//										String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
//										if (player.getVariables().containsKey(itemid + "_" + date)) {
//											buynum = Integer.valueOf(player.getVariables().get(itemid + "_" + date));
//										}
										switch (value) {
											case 0: {//达到num个itemid，可以领取，单次
												if (buynum >= linenum) {
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
												}
											}
											break;
											case 1: {//每num个itemid，可以领取,多次
												int recievenum = buynum / linenum;
												if (recievenum != 0) {
													int hasreceivenum = player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
													if (recievenum > hasreceivenum) {
														hasreceivenum++;
														player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), Integer.toString(hasreceivenum));
													}
												}
											}
											break;
										}
									}
									break;
									case 4: {//军功奖励
										int day = TimeUtil.GetSeriesDay();
										player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "");
									}
									break;
									case 5: {//换购活动(蛋疼)
										//换购活动：2250宝石精华+2250元宝，换购指定宝石类型的极品宝石激活符，使用后既可激活一颗未激活的极品宝石（每人每天限换购一朵）
										//换购活动：1朵红玫瑰+50元宝，换购20朵蓝玫瑰（无限换购）
										//限量换购活动：双倍收益丹*1+10元宝，可换取1颗3倍收益丹（每日每人限换购10颗）
										//限量换购活动：生命池*1+15元宝，可换购5倍容量的大型生命池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
										//限量换购活动：内力池*1+15元宝，可换购5倍容量的大型内力池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
										int redemptiontype = datas[1];
										switch (redemptiontype) {
											case 1: {//无限换购
												//removeNumByItemId(player, itemid, num);
											}
											break;
											case 2: {//每人每天换购
												//removeNumByItemId(player, itemid, num);
												if (iterator.hasNext()) {
													continue;
												}
												int day = TimeUtil.GetSeriesDay();
												if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
													String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
													String[] dayStrings = daystr.split("_");
													if (dayStrings.length >= 2) {
														int oldvalue = Integer.valueOf(dayStrings[1]);
														oldvalue++;
														player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + oldvalue);
													}
												} else {
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + 1);
												}
											}
											break;
											case 3: {//活动时间每人换购
												//removeNumByItemId(player, itemid, num);
												if (iterator.hasNext()) {
													continue;
												}
												if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
													String oldvaluestr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
													int oldvalue = Integer.valueOf(oldvaluestr);
													oldvalue++;
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + oldvalue);
												} else {
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
												}
											}
											break;
											default: {
												logger.error("错误的换购活动数据==" + q_activitiesBean.getQ_id());
											}
											break;
										}
									}
									break;
									case 7: {//坐骑福利：[[7,0,300]]
										//当玩家任意一只坐骑祝福值达到300、600时，均可领取三颗完美坐骑进阶丹与六颗完美坐骑进阶丹（时限，绑定）。
										//例：我在夜豹的时候领取过祝福值达到300时可获得的奖励。当我升阶到白泽后，祝福值又达到300则还可以领取。
										//领取奖励后需记录当前领取时的坐骑ID和祝福值区间，作为标识，不能重复领取。
										int horselv = 0;
										int horsebless = 0;
										if (datas.length > 2) {
											horselv = datas[1];
											horsebless = datas[2];
										}
										Horse curhorse = HorseManager.getInstance().getHorse(player);
										if (curhorse != null) {
											if (horselv == 0) {
												player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), curhorse.getLayer() + "_" + horsebless);
											} else {
												player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), horselv + "_" + horsebless);
											}
										} else {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), horselv + "_" + horsebless);
										}
									}
									break;
									case 8: {//弓箭福利：[[8,4]]
										//活动期间，将弓箭提升到5阶的玩家（已激活第四阶弓箭）均可在本处免费领取以下道具：
										//双倍战魂收益丹*1
										int arrowlv = 0;
										if (datas.length > 1) {
											arrowlv = datas[1];
										}
										if (arrowlv == 0) {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + player.getArrowData().getArrowlv());
										} else {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + arrowlv);
										}
									}
									break;
									case 9: {//任一物品换购活动(杨洪岚蛋疼)
										int redemptiontype = datas[1];
										switch (redemptiontype) {
											case 1: {//无限换购
											}
											break;
											case 2: {//每人每天换购
												if (iterator.hasNext()) {
													continue;
												}
												int day = TimeUtil.GetSeriesDay();
												if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
													String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
													String[] dayStrings = daystr.split("_");
													if (dayStrings.length >= 2) {
														int oldvalue = Integer.valueOf(dayStrings[1]);
														oldvalue++;
														player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + oldvalue);
													}
												} else {
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + 1);
												}
											}
											break;
											case 3: {//活动时间每人换购
												if (iterator.hasNext()) {
													continue;
												}
												if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
													String oldvaluestr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
													int oldvalue = Integer.valueOf(oldvaluestr);
													oldvalue++;
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + oldvalue);
												} else {
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
												}
											}
											break;
											default: {
												logger.error("错误的换购活动数据==" + q_activitiesBean.getQ_id());
											}
											break;
										}
									}
									break;
									case 10: {//宝石福利：[[10,20]]
										//活动期间，将全身宝石到最高级
										int gemlv = 0;
										if (datas.length > 1) {
											gemlv = datas[1];
										}
										if (gemlv == 0) {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + getGemLevel(player));
										} else {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + gemlv);
										}
									}
									break;
									case 11: {//骑战兵器：[[11,5]]
										//活动期间，将全身宝石到最高级
										int horseweaponlv = 0;
										if (datas.length > 1) {
											horseweaponlv = datas[1];
										}
										if (horseweaponlv == 0) {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + getHorseWeaponLayer(player));
										} else {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + horseweaponlv);
										}
									}
									break;
									case 12: {//境界：[[12,6]]
										int skilllv = 0;
										if (datas.length > 1) {
											skilllv = datas[1];
										}
										if (skilllv == 0) {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + getSkillLayer(player));
										} else {
											player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "" + skilllv);
										}
									}
									break;
									default: {
										player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
									}
									break;
								}
							}
						}
					} else {
						player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), "1");
					}
				}
			}
			sendMessage.setItemReasons(Reasons.ACTIVITY_GIFT.getValue());
			MessageUtil.tell_player_message(player, sendMessage);
			LogService.getInstance().execute(new ActivitiesLog(Long.valueOf(player.getUserId()), player.getId(), q_activitiesBean.getQ_id(), repeat + "=" + q_activitiesBean.getQ_items(), 1, player.getCreateServerId())); //活动日志
		}
	}

	public int getstate(int oldstate, int newstate) {
		return oldstate == -2 ? newstate : oldstate == 2 ? oldstate : oldstate == 1 ? newstate : oldstate;
	}

	//state  0-已领取 1-可领取 2-不可领取
	public int checkstate(Player player, Q_activitiesBean q_activitiesBean) {
		int status = -2;
		if (q_activitiesBean.getQ_login_limit() != 0) {
			int thisstatus = 0;
			if (TimeUtil.isSameDay(player.getLastAddLoginTime(), System.currentTimeMillis())) {
				int loginlimit = q_activitiesBean.getQ_login_limit();
				loginlimit = loginlimit % 10000;
				int btw = player.getLoginTimes() - loginlimit;
				if (btw >= 0) {
					Calendar cal = Calendar.getInstance();
					if (q_activitiesBean.getQ_login_limit() < 10000) {
						cal.add(Calendar.DATE, -btw);
					}
					String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
					if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
						if (date.equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
							thisstatus = 0;
						} else {
							thisstatus = 1;
						}
					} else {
						thisstatus = 1;
					}
				} else {
					thisstatus = 2;
				}
			} else {
				thisstatus = 2;
			}
			if (q_activitiesBean.getQ_login_limit() > 10000 && thisstatus == 1) {
				int loginlimit = q_activitiesBean.getQ_login_limit();
				loginlimit = loginlimit / 10000;
				Q_activitiesBean q_otherBean = DataManager.getInstance().q_activitiesContainer.getMap().get(loginlimit);
				if (q_otherBean != null) {
					loginlimit = q_otherBean.getQ_login_limit() % 10000;
					int btw = player.getLoginTimes() - loginlimit;
					if (btw >= 0) {
						Calendar cal = Calendar.getInstance();
						if (q_otherBean.getQ_login_limit() < 10000) {
							cal.add(Calendar.DATE, -btw);
						}
						String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
						if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_otherBean))) {
							if (!date.equals(player.getActivitiesReward().get(getActivitiesKey(player, q_otherBean)))) {
								player.getActivitiesReward().put(getActivitiesKey(player, q_otherBean), date);
							}
						} else {
							player.getActivitiesReward().put(getActivitiesKey(player, q_otherBean), date);
						}
					}
				}
			}
			status = getstate(status, thisstatus);
		}
		if (q_activitiesBean.getQ_need_level() != 0) {
			int thisstatus = 0;
			if (player.getLevel() >= q_activitiesBean.getQ_need_level()) {
				if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
					if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
						thisstatus = 0;
					} else {
						thisstatus = 1;
					}
				} else {
					thisstatus = 1;
				}
			} else {
				thisstatus = 2;
			}
			status = getstate(status, thisstatus);
		}
		if (q_activitiesBean.getQ_need_horse() != 0) {
			int thisstatus = 0;
			int horselevel = HorseManager.getInstance().getHorse(player).getLayer(); //玩家坐骑最高阶数 
			if (horselevel >= q_activitiesBean.getQ_need_horse()) {
				if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
					if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
						thisstatus = 0;
					} else {
						thisstatus = 1;
					}
				} else {
					thisstatus = 1;
				}
			} else {
				thisstatus = 2;
			}
			status = getstate(status, thisstatus);
		}
		if (q_activitiesBean.getQ_need_skill() != 0) {
			int thisstatus = 0;
			if (player.getTotalSkillLevel() >= q_activitiesBean.getQ_need_skill()) {
				if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
					if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
						thisstatus = 0;
					} else {
						thisstatus = 1;
					}
				} else {
					thisstatus = 1;
				}
			} else {
				thisstatus = 2;
			}
			status = getstate(status, thisstatus);
		}
		if (q_activitiesBean.getQ_onlinetime() != 0) {
			int thisstatus = 0;
			if (player.getDayonlinetime() >= q_activitiesBean.getQ_onlinetime()) {
				if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
					if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
						thisstatus = 0;
					} else {
						thisstatus = 1;
					}
				} else {
					thisstatus = 1;
				}
			} else {
				thisstatus = 2;
			}
			status = getstate(status, thisstatus);
		}
		if (!q_activitiesBean.getQ_other().isEmpty()) {
			List<Integer[]> otherList = null;
			try {
				otherList = JSON.parseArray(q_activitiesBean.getQ_other(), Integer[].class);
			} catch (Exception e) {
				logger.error(q_activitiesBean.getQ_other()+"解析出错"+e, e);
			}
			
			if (!otherList.isEmpty()) {
				ListIterator<Integer[]> iterator = otherList.listIterator();
				while (iterator.hasNext()) {
					Integer[] datas = iterator.next();
					if (datas.length < 2) {
						continue;
					}
					int key = datas[0];
					switch (key) {
						case 1: {//VIP
							int thisstatus = 0;
							int value = datas[1];
							if (VipManager.getInstance().getPlayerVipId(player) >= value) {
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
									if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
										thisstatus = 0;
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								thisstatus = 2;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 2: {//王城
							int thisstatus = 0;
							int value = datas[1];
							if (value != 0) {
								//state  0-已领取 1-可领取 2-不可领取 //不显示的条件是 serverparamutil 没有kingcity并且有 combineserver
								//如果已经合服，则不显示首任王帮活动
								if (ServerParamUtil.getImportantParamMap().containsKey("COMBINESERVER")) {
									thisstatus = 0;
								} else if (CountryManager.getInstance().getKingcity().checkKingCity(player) && CountryAwardManager.getInstance().isKingCityAwardOpen()) {
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
										if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
											thisstatus = 0;
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 1;
									}
									KingCity kingCity = CountryManager.getInstance().getKingcity();
									if (kingCity != null) {
										int limitCount = 0;
										if (kingCity.getSalarymap().containsKey(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id())) {
											limitCount = kingCity.getSalarymap().get(q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id());
											if (limitCount >= GuildServerManager.Guild_MaxNum) {
												thisstatus = 0;
											}
										}
									}
								} else {
									thisstatus = 2;
								}
								int delayday = 0;
								if (datas.length >= 3) {
									delayday = datas[2];
								}
								if (delayday != 0) {
									KingData gKingData = CountryManager.getInstance().getKingcity().gKingData(value);
									if (gKingData != null) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTimeInMillis(gKingData.getReigntime() * 1000 + delayday * 24 * 3600 * 1000);
										calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
										if (System.currentTimeMillis() <= calendar.getTimeInMillis()) {
											if (thisstatus == 0) {
												thisstatus = 4;
											}
										} else {
											thisstatus = 0;
										}
									}
								}
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 3: {//元宝商城购买
							int thisstatus = 0;
							int value = datas[1];
							int itemid = 0;
							int linenum = 0;
							if (datas.length >= 4) {
								itemid = datas[2];
								linenum = datas[3];
							}
							int buynum = 0;
							long startTime = getStartTime(player, q_activitiesBean);
							long endTime = getEndTime(player, q_activitiesBean);
							while (startTime <= endTime) {
								String date = new SimpleDateFormat("yyyy-MM-dd").format(startTime);
								if (player.getVariables().containsKey(itemid + "_" + date)) {
									buynum += Integer.valueOf(player.getVariables().get(itemid + "_" + date));
								}
								startTime += 24 * 3600 * 1000;
							}
//							Calendar calendar = Calendar.getInstance();
//							String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
//							if (player.getVariables().containsKey(itemid + "_" + date)) {
//								buynum = Integer.valueOf(player.getVariables().get(itemid + "_" + date));
//							}
							switch (value) {
								case 0: {//达到num个itemid，可以领取，单次
									if (buynum >= linenum) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) { //已经领过
											if ("1".equals(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean)))) {
												thisstatus = 0;
											} else {
												thisstatus = 1;
											}
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 2;
									}
								}
								break;
								case 1: {//每num个itemid，可以领取,多次
									int recievenum = buynum / linenum;
									if (recievenum != 0) {
										int hasreceivenum = player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean)) ? Integer.parseInt(player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean))) : 0; //已经领取次数
										if (recievenum > hasreceivenum) {
											thisstatus = 1;
											player.setRepeatNum(recievenum - hasreceivenum);
										} else if (recievenum == hasreceivenum) {
											thisstatus = 2;
										} else {   //已领>可领
											thisstatus = 2;
										}
									} else {
										thisstatus = 2;
									}
								}
								break;
								default: {
									thisstatus = 2;
								}
								break;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 4: {//军功奖励 1可领取，0已经领取，2不可领取
							int thisstatus = 0;
							long num = ManagerPool.countManager.getCount(player, CountTypes.DAY_RANK, null);
							if (datas[1] <= num) {
								int day = TimeUtil.GetSeriesDay();
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
									String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
									int tday = Integer.parseInt(daystr);
									if (tday == day) {
										thisstatus = 0;
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								thisstatus = 2;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 5: {//换购活动(蛋疼)
							//换购活动：2250宝石精华+2250元宝，换购指定宝石类型的极品宝石激活符，使用后既可激活一颗未激活的极品宝石（每人每天限换购一朵）
							//换购活动：1朵红玫瑰+50元宝，换购20朵蓝玫瑰（无限换购）
							//限量换购活动：双倍收益丹*1+10元宝，可换取1颗3倍收益丹（每日每人限换购10颗）
							//限量换购活动：生命池*1+15元宝，可换购5倍容量的大型生命池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
							//限量换购活动：内力池*1+15元宝，可换购5倍容量的大型内力池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
							int thisstatus = 0;
							int redemptiontype = datas[1];
							int value = 0;
							int itemid = 0;
							int num = 0;
							int showpanel = 0;
							if (datas.length > 5) {
								value = datas[2];
								itemid = datas[3];
								num = datas[4];
								showpanel = datas[5];
							}
							switch (redemptiontype) {
								case 1: {//无限换购
									int getnum = getNumByItemId(player, itemid, num);
									getnum = Math.min(getnum, player.getRepeatNum());
									if (getnum > 0) {
										thisstatus = 1;
										if (showpanel == 1) {
											player.setRepeatNum(getnum);
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								case 2: {//每人每天换购
									int getnum = getNumByItemId(player, itemid, num);
									getnum = Math.min(getnum, player.getRepeatNum());
									getnum = Math.min(getnum, value);
									if (getnum >= 0) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
											String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
											String[] dayStrings = daystr.split("_");
											if (dayStrings.length >= 2) {
												int oldday = Integer.valueOf(dayStrings[0]);
												int oldvalue = Integer.valueOf(dayStrings[1]);
												int day = TimeUtil.GetSeriesDay();
												if (oldday != day) {
													thisstatus = 1;
													if (showpanel == 1) {
														player.setRepeatNum(getnum);
													}
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + 0);
													if (getnum == 0) {
														thisstatus = 2;
													}
												} else {
													if (oldvalue >= value) {
														thisstatus = 0;
														if (showpanel == 1) {
															player.setRepeatNum(0);
														}
													} else {
														thisstatus = 1;
														getnum = Math.min(getnum, value - oldvalue);
														if (showpanel == 1) {
															player.setRepeatNum(getnum);
														}
														if (getnum == 0) {
															thisstatus = 2;
														}
													}
												}
											} else {
												thisstatus = 2;
												if (showpanel == 1) {
													player.setRepeatNum(0);
												}
											}
										} else {
											thisstatus = 1;
											if (showpanel == 1) {
												player.setRepeatNum(getnum);
											}
											if (getnum == 0) {
												thisstatus = 2;
											}
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								case 3: {//活动时间每人换购
									int getnum = getNumByItemId(player, itemid, num);
									getnum = Math.min(getnum, player.getRepeatNum());
									getnum = Math.min(getnum, value);
									if (getnum >= 0) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
											String oldvaluestr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
											int oldvalue = Integer.valueOf(oldvaluestr);
											if (oldvalue >= value) {
												thisstatus = 0;
												if (showpanel == 1) {
													player.setRepeatNum(0);
												}
											} else {
												thisstatus = 1;
												getnum = Math.min(getnum, value - oldvalue);
												if (showpanel == 1) {
													player.setRepeatNum(getnum);
												}
												if (getnum == 0) {
													thisstatus = 2;
												}
											}
										} else {
											thisstatus = 1;
											if (showpanel == 1) {
												player.setRepeatNum(getnum);
											}
											if (getnum == 0) {
												thisstatus = 2;
											}
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								default: {
									thisstatus = 2;
									if (showpanel == 1) {
										player.setRepeatNum(0);
									}
								}
								break;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 6: {//军功翻倍活动不用管
						}
						break;
						case 7: {//坐骑福利：[[7,0,300]]
							//当玩家任意一只坐骑祝福值达到300、600时，均可领取三颗完美坐骑进阶丹与六颗完美坐骑进阶丹（时限，绑定）。
							//例：我在夜豹的时候领取过祝福值达到300时可获得的奖励。当我升阶到白泽后，祝福值又达到300则还可以领取。
							//领取奖励后需记录当前领取时的坐骑ID和祝福值区间，作为标识，不能重复领取。
							int thisstatus = 0;
							int horselv = 0;
							int horsebless = 0;
							if (datas.length > 2) {
								horselv = datas[1];
								horsebless = datas[2];
							}
							Horse curhorse = HorseManager.getInstance().getHorse(player);
							if (curhorse != null) {
								if (horselv == 0) {
									int curhorsebless = curhorse.getDayblessvalue();
									if (curhorsebless >= horsebless) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
											String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
											if (oldvalue.equalsIgnoreCase(curhorse.getLayer() + "_" + horsebless)) {
												thisstatus = 2;
											} else {
												thisstatus = 1;
											}
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 2;
									}
								} else {
									if (curhorse.getLayer() >= horselv) {
										int curhorsebless = curhorse.getDayblessvalue();
										if (curhorsebless >= horsebless) {
											if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
												String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
												if (oldvalue.equalsIgnoreCase(horselv + "_" + horsebless)) {
													thisstatus = 0;
												} else {
													thisstatus = 1;
												}
											} else {
												thisstatus = 1;
											}
										} else {
											thisstatus = 2;
										}
									} else {
										thisstatus = 2;
									}
								}
							} else {
								thisstatus = 2;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 8: {//弓箭福利：[[8,4]]
							//活动期间，将弓箭提升到5阶的玩家（已激活第四阶弓箭）均可在本处免费领取以下道具：
							//双倍战魂收益丹*1
							int thisstatus = 0;
							int arrowlv = 0;
							if (datas.length > 1) {
								arrowlv = datas[1];
							}
							if (arrowlv == 0) {
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
									String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
									int value = Integer.valueOf(oldvalue);
									if (player.getArrowData().getArrowlv() == value) {
										thisstatus = 2;
									} else if (player.getArrowData().getArrowlv() > value) {
										thisstatus = 1;
									} else {
										thisstatus = 2;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								if (player.getArrowData().getArrowlv() >= arrowlv) {
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										int value = Integer.valueOf(oldvalue);
										if (arrowlv == value) {
											thisstatus = 0;
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 2;
								}
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 9: {//任一物品换购活动(蛋疼)
							int thisstatus = 0;
							int redemptiontype = datas[1];
							int value = 0;
							int[] itemids = null;
							int[] nums = null;
							int showpanel = 0;
							if (datas.length > 5) {
								value = datas[2];
								int length = (datas.length - 4) / 2;
								itemids = new int[length];
								nums = new int[length];
								for (int i = 0; i < length; i++) {
									itemids[i] = datas[ 3 + i * 2];
									nums[i] = datas[3 + i * 2 + 1];
								}
								showpanel = datas[datas.length - 1];
							}
							switch (redemptiontype) {
								case 1: {//无限换购
									int getnum = 0;
									for (int i = 0; i < itemids.length; i++) {
										getnum += getNumByItemId(player, itemids[i], nums[i]);
									}
									getnum = Math.min(getnum, player.getRepeatNum());
									if (getnum > 0) {
										thisstatus = 1;
										if (showpanel == 1) {
											player.setRepeatNum(getnum);
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								case 2: {//每人每天换购
									int getnum = 0;
									for (int i = 0; i < itemids.length; i++) {
										getnum += getNumByItemId(player, itemids[i], nums[i]);
									}
									getnum = Math.min(getnum, player.getRepeatNum());
									getnum = Math.min(getnum, value);
									if (getnum >= 0) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
											String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
											String[] dayStrings = daystr.split("_");
											if (dayStrings.length >= 2) {
												int oldday = Integer.valueOf(dayStrings[0]);
												int oldvalue = Integer.valueOf(dayStrings[1]);
												int day = TimeUtil.GetSeriesDay();
												if (oldday != day) {
													thisstatus = 1;
													if (showpanel == 1) {
														player.setRepeatNum(getnum);
													}
													player.getActivitiesReward().put(getActivitiesKey(player, q_activitiesBean), day + "_" + 0);
													if (getnum == 0) {
														thisstatus = 2;
													}
												} else {
													if (oldvalue >= value) {
														thisstatus = 0;
														if (showpanel == 1) {
															player.setRepeatNum(0);
														}
													} else {
														thisstatus = 1;
														getnum = Math.min(getnum, value - oldvalue);
														if (showpanel == 1) {
															player.setRepeatNum(getnum);
														}
														if (getnum == 0) {
															thisstatus = 2;
														}
													}
												}
											} else {
												thisstatus = 2;
												if (showpanel == 1) {
													player.setRepeatNum(0);
												}
											}
										} else {
											thisstatus = 1;
											if (showpanel == 1) {
												player.setRepeatNum(getnum);
											}
											if (getnum == 0) {
												thisstatus = 2;
											}
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								case 3: {//活动时间每人换购
									int getnum = 0;
									for (int i = 0; i < itemids.length; i++) {
										getnum += getNumByItemId(player, itemids[i], nums[i]);
									}
									getnum = Math.min(getnum, player.getRepeatNum());
									getnum = Math.min(getnum, value);
									if (getnum >= 0) {
										if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
											String oldvaluestr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
											int oldvalue = Integer.valueOf(oldvaluestr);
											if (oldvalue >= value) {
												thisstatus = 0;
												if (showpanel == 1) {
													player.setRepeatNum(0);
												}
											} else {
												thisstatus = 1;
												getnum = Math.min(getnum, value - oldvalue);
												if (showpanel == 1) {
													player.setRepeatNum(getnum);
												}
												if (getnum == 0) {
													thisstatus = 2;
												}
											}
										} else {
											thisstatus = 1;
											if (showpanel == 1) {
												player.setRepeatNum(getnum);
											}
											if (getnum == 0) {
												thisstatus = 2;
											}
										}
									} else {
										thisstatus = 2;
										if (showpanel == 1) {
											player.setRepeatNum(0);
										}
									}
								}
								break;
								default: {
									thisstatus = 2;
									if (showpanel == 1) {
										player.setRepeatNum(0);
									}
								}
								break;
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 10: {//宝石福利：[[10,20]]
							//活动期间，将全身宝石到最高级
							int thisstatus = 0;
							int gemlv = 0;
							if (datas.length > 1) {
								gemlv = datas[1];
							}
							int pgemlv = getGemLevel(player);
//							String akey = getActivitiesKey(player, q_activitiesBean);
//							logger.error(player.getName() + " 宝石key：" + akey + "是否已有" + player.getActivitiesReward().containsKey(akey));
							if (gemlv == 0) {
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
									String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
									int value = Integer.valueOf(oldvalue);
									if (pgemlv == value) {
										thisstatus = 2;
									} else if (pgemlv > value) {
										thisstatus = 1;
									} else {
										thisstatus = 2;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								if (pgemlv >= gemlv) {
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										int value = Integer.valueOf(oldvalue);
										if (gemlv == value) {
											thisstatus = 0;
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 2;
								}
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 11: {//骑战兵器：[[11,5]]
							//活动期间，将全身宝石到最高级
							int thisstatus = 0;
							int horseweaponlv = 0;
							if (datas.length > 1) {
								horseweaponlv = datas[1];
							}
							int phorseweaponlv = getHorseWeaponLayer(player);
//							String akey = getActivitiesKey(player, q_activitiesBean);
//							logger.error(player.getName() + " 骑兵key：" + akey + "是否已有" + player.getActivitiesReward().containsKey(akey));
							if (horseweaponlv == 0) {
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
									String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
									int value = Integer.valueOf(oldvalue);
									if (phorseweaponlv == value) {
										thisstatus = 2;
									} else if (phorseweaponlv > value) {
										thisstatus = 1;
									} else {
										thisstatus = 2;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								if (phorseweaponlv >= horseweaponlv) {
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										int value = Integer.valueOf(oldvalue);
										if (horseweaponlv == value) {
											thisstatus = 0;
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 2;
								}
							}
							status = getstate(status, thisstatus);
						}
						break;
						case 12: {//境界：[[12,6]]
							int thisstatus = 0;
							int skilllv = 0;
							if (datas.length > 1) {
								skilllv = datas[1];
							}
							int pskilllv = getSkillLayer(player);
//							String akey = getActivitiesKey(player, q_activitiesBean);
//							logger.error(player.getName() + " 骑兵key：" + akey + "是否已有" + player.getActivitiesReward().containsKey(akey));
							if (skilllv == 0) {
								if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
									String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
									int value = Integer.valueOf(oldvalue);
									if (pskilllv == value) {
										thisstatus = 2;
									} else if (pskilllv > value) {
										thisstatus = 1;
									} else {
										thisstatus = 2;
									}
								} else {
									thisstatus = 1;
								}
							} else {
								if (pskilllv >= skilllv) {
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String oldvalue = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										int value = Integer.valueOf(oldvalue);
										if (skilllv == value) {
											thisstatus = 0;
										} else {
											thisstatus = 1;
										}
									} else {
										thisstatus = 1;
									}
								} else {
									thisstatus = 2;
								}
							}
							status = getstate(status, thisstatus);
						}
						break;
					}
					
				}
			}
		}
		if (!q_activitiesBean.getQ_pay_yuanbao().isEmpty() && (status == 1 || status == 2 || status == -2)) {//元宝去世界服务器判断
			switch (status) {
				case 1: {
					status = status * 10 + 3;
				}
				break;
				case 2: {
					status = status * 10 + 3;
				}
				break;
				case -2: {
					status = 3;
				}
				break;
			}

		}
		if (status == -2) {
			status = 0;
		}
		return status;
	}

	/**
	 * 根据需要的物品id和数量计算出可以获得的奖励物品数
	 *
	 * @return int
	 */
	public int getNumByItemId(Player player, int itemid, int num) {
		int result = 0;
		try {
			switch (itemid) {
				case -1: {//Money
					result = player.getMoney() / num;
				}
				break;
				case -2: {//Gold
					if (player.getGold() == null) {
						return 0;
					}
					int goldnum = 0;
					if (player.getGold() != null) {
						goldnum = player.getGold().getGold();
					}
					result = goldnum / num;
				}
				break;
				case -3: {//zhenqi
					result = player.getZhenqi() / num;
				}
				break;
				case -4: {//exp
					result = (int) (player.getExp() / num);
				}
				break;
				case -5: {//bindgold
					result = player.getBindGold() / num;
				}
				break;
				case -6: {//fightspirit
					int fsnum = ArrowManager.getInstance().getFightSpiritNum(player, ArrowManager.FightType_RI);
					result = fsnum / num;
				}
				break;
				case -7: {//rank
					result = player.getRankexp() / num;
				}
				break;
				default: {//普通物品
					int itemnum = getItemNum(player, itemid);
					result = itemnum / num;
				}
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 

		if (result < 0) {
			result = 0;
		}
		return result;
	}

	/**
	 * 根据需要的物品id和数量进行移除
	 *
	 * @return
	 */
	public void removeNumByItemId(Player player, int itemid, int num) {
		try {
			switch (itemid) {
				case -1: {//Money
					BackpackManager.getInstance().changeMoney(player, -Math.abs(num), Reasons.def6, Config.getId());
				}
				break;
				case -2: {//Gold
					BackpackManager.getInstance().changeGold(player, -Math.abs(num), Reasons.def6, Config.getId());
				}
				break;
				case -3: {//zhenqi
					PlayerManager.getInstance().addZhenqi(player, -Math.abs(num), AttributeChangeReason.ACTIVITIESDEL);
				}
				break;
				case -4: {//exp
					PlayerManager.getInstance().addExp(player, -Math.abs(num), AttributeChangeReason.ACTIVITIESDEL);
				}
				break;
				case -5: {//bindgold
					BackpackManager.getInstance().changeBindGold(player, -Math.abs(num), Reasons.def6, Config.getId());
				}
				break;
				case -6: {//fightspirit
					ArrowManager.getInstance().addFightSpiritNum(player, ArrowManager.FightType_RI, -Math.abs(num), true, ArrowReasonsType.ACTIVITIES);
				}
				break;
				case -7: {//rank
					RankManager.getInstance().addranknum(player, -Math.abs(num), RankType.Activities);
				}
				break;
				default: {//普通物品
					int itemnum = getItemNum(player, itemid);
					if (itemnum >= num) {
						removeItem(player, itemid, Math.abs(num), Reasons.def6, Config.getId());
					}
				}
				break;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 根据活动数据进行移除
	 *
	 * @return
	 */
	public void removeNumByActivitiesBean(Player player, Q_activitiesBean q_activitiesBean, int repeat) {
		if (q_activitiesBean != null && player != null && repeat > 0) {
			if (!q_activitiesBean.getQ_other().isEmpty()) {
				try {
					List<Integer[]> otherList = null;
					try {
						otherList = JSON.parseArray(q_activitiesBean.getQ_other(), Integer[].class);
					} catch (Exception e) {
						logger.error(q_activitiesBean.getQ_other()+"解析出错"+e,e);
					}
					if (!otherList.isEmpty()) {
						Iterator<Integer[]> iterator = otherList.iterator();
						while (iterator.hasNext()) {
							Integer[] datas = iterator.next();
							if (datas.length < 2) {
								continue;
							}
							int key = datas[0];
							switch (key) {
								case 5: {//换购活动(蛋疼)
									//换购活动：2250宝石精华+2250元宝，换购指定宝石类型的极品宝石激活符，使用后既可激活一颗未激活的极品宝石（每人每天限换购一朵）
									//换购活动：1朵红玫瑰+50元宝，换购20朵蓝玫瑰（无限换购）
									//限量换购活动：双倍收益丹*1+10元宝，可换取1颗3倍收益丹（每日每人限换购10颗）
									//限量换购活动：生命池*1+15元宝，可换购5倍容量的大型生命池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
									//限量换购活动：内力池*1+15元宝，可换购5倍容量的大型内力池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
									int redemptiontype = datas[1];
									int value = 0;
									int itemid = 0;
									int num = 0;
									if (datas.length > 5) {
										value = datas[2];
										itemid = datas[3];
										num = datas[4];
									}
									repeat = Math.min((value == 0 ? repeat : value), repeat);
									switch (redemptiontype) {
										case 1: {//无限换购
											removeNumByItemId(player, itemid, num * repeat);
										}
										break;
										case 2: {//每人每天换购
											removeNumByItemId(player, itemid, num * repeat);
										}
										break;
										case 3: {//活动时间每人换购
											removeNumByItemId(player, itemid, num * repeat);
										}
										break;
										default: {
											removeNumByItemId(player, itemid, num * repeat);
										}
										break;
									}
								}
								break;
								case 9: {//任一物品换购活动(蛋疼)
									int value = 0;
									int[] itemids = null;
									int[] nums = null;
									if (datas.length > 5) {
										value = datas[2];
										int length = (datas.length - 4) / 2;
										itemids = new int[length];
										nums = new int[length];
										for (int i = 0; i < length; i++) {
											itemids[i] = datas[ 3 + i * 2];
											nums[i] = datas[3 + i * 2 + 1];
										}
									}
									repeat = Math.min((value == 0 ? repeat : value), repeat);
									for (int j = 0; j < itemids.length; j++) {
										int getnum = getNumByItemId(player, itemids[j], nums[j]);
										if(getnum > 0){
											int removenum = Math.min(getnum, repeat);
											removeNumByItemId(player, itemids[j], nums[j] * removenum);
											repeat = repeat - removenum;
											if(repeat==0) break;
										}
									}
								}
								break;
							}
						}
					}
				} catch (Exception e) {
					logger.error(e, e);
				}
			}
		}
	}

	//检查区服
	public boolean checkZoneServer(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_listimage().isEmpty()) {
			return false;
		} else {
			if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("0")) {
				return true;
			} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("3")) {
				if (!WServer.getInstance().getServerWeb().equalsIgnoreCase("37wan")) {
					return false;
				}
				if (q_activitiesBean.getQ_id() == 101) {
					if (WServer.getInstance().getServerId() <= 197) {
						return true;
					} else {
						return false;
					}
				} else {
					if (WServer.getInstance().getServerId() == 300) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return true;
			}
//			if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
//				return true;
//			} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
//				Date time = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
//				Calendar calendar = Calendar.getInstance();
//				calendar.set(2012, 10, 5, 0, 0, 0);
//				if (time.getTime() < calendar.getTimeInMillis()) {
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				return false;
//			}

//			Date time = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(2012, 9, 29, 0, 0, 0);
//			if (time.getTime() <= calendar.getTimeInMillis()) {
//				if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
//					return false;
//				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
//					return true;
//				} else {
//					return false;
//				}
//			} else {
//				if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
//					return true;
//				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
//					return false;
//				} else {
//					return false;
//				}
//			}

//			if (WServer.getInstance().getServerWeb().equalsIgnoreCase("37wan")) {
//				if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
//					if (WServer.getInstance().getServerId() >= 13) {
//						return true;
//					} else {
//						return false;
//					}
//				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
//					if (WServer.getInstance().getServerId() < 13) {
//						return true;
//					} else {
//						return false;
//					}
//				} else {
//					return false;
//				}
//			} else {
//				if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("1")) {
//					return true;
//				} else if (q_activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
//					return false;
//				} else {
//					return false;
//				}
//			}
		}
	}

	public boolean checkdelTime(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_duration().isEmpty()) {
			return false;
		} else {
			return TimeUtil.checkRangeTime(q_activitiesBean.getQ_duration());
		}
	}

	public boolean checkTime(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_timingstart().isEmpty()) {
			return false;
		} else {
			return TimeUtil.checkRangeTime(q_activitiesBean.getQ_timingstart());
		}
	}

	public long getRemainTime(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_timingstart().isEmpty()) {
			return 0;
		} else {
			return TimeUtil.getRangeTimeBeforeOrAfter(q_activitiesBean.getQ_timingstart(), false, false);
		}
	}

	public long getStartTime(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_timingstart().isEmpty()) {
			return 0;
		} else {
			return TimeUtil.getRangeTimeBeforeOrAfter(q_activitiesBean.getQ_timingstart(), true, true);
		}
	}

	public long getEndTime(Player player, Q_activitiesBean q_activitiesBean) {
		if (q_activitiesBean.getQ_timingstart().isEmpty()) {
			return 0;
		} else {
			return TimeUtil.getRangeTimeBeforeOrAfter(q_activitiesBean.getQ_timingstart(), false, true);
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

	/**
	 * 解析附加自定义参数
	 *
	 */
	public List<String> stinfoExpand(Player player, Q_activitiesBean q_activitiesBean) {
		List<String> list = new ArrayList<String>();
		if (q_activitiesBean.getQ_other().equals("")) {
			return list;
		}
		
		try {
			List<Integer[]> otherList = null;
			try {
				otherList = JSON.parseArray(q_activitiesBean.getQ_other(), Integer[].class);
			} catch (Exception e) {
				logger.error(q_activitiesBean.getQ_other()+"解析出错"+e,e);
			}
			if (!otherList.isEmpty()) {
				HashMap<String, Object> expandMap = new HashMap<String, Object>();
				Iterator<Integer[]> iterator = otherList.iterator();
				while (iterator.hasNext()) {
					Integer[] datas = iterator.next();
					if (datas.length < 2) {
						continue;
					}
					int key = datas[0];
					switch (key) {
						case 4: {//军功奖励类型
							list.add(datas[1] + "");
						}
						break;
						case 5: {//换购活动(蛋疼)
							//换购活动：2250宝石精华+2250元宝，换购指定宝石类型的极品宝石激活符，使用后既可激活一颗未激活的极品宝石（每人每天限换购一朵）
							//换购活动：1朵红玫瑰+50元宝，换购20朵蓝玫瑰（无限换购）
							//限量换购活动：双倍收益丹*1+10元宝，可换取1颗3倍收益丹（每日每人限换购10颗）
							//限量换购活动：生命池*1+15元宝，可换购5倍容量的大型生命池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
							//限量换购活动：内力池*1+15元宝，可换购5倍容量的大型内力池，并且大型生命池受放大卡影响，效果可再次翻倍到10倍（活动7天内限换购70个）
							int redemptiontype = datas[1];
							int value = 0;
							int showpanel = 0;
							if (datas.length > 5) {
								value = datas[2];
								showpanel = datas[5];
							}
							expandMap.put("showpanel", showpanel);
							switch (redemptiontype) {
								case 1: {//无限换购
								}
								break;
								case 2: {//每人每天换购
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String daystr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										String[] dayStrings = daystr.split("_");
										if (dayStrings.length >= 2) {
											int oldday = Integer.valueOf(dayStrings[0]);
											int oldvalue = Integer.valueOf(dayStrings[1]);
											int day = TimeUtil.GetSeriesDay();
											if (oldday != day) {
												expandMap.put("remain", value);
											} else {
												if (oldvalue >= value) {
													expandMap.put("remain", 0);
												} else {
													int remain = value - oldvalue;
													expandMap.put("remain", remain);
												}
											}
										} else {
											expandMap.put("remain", 0);
										}
									} else {
										expandMap.put("remain", value);
									}
								}
								break;
								case 3: {//活动时间每人换购
									if (player.getActivitiesReward().containsKey(getActivitiesKey(player, q_activitiesBean))) {
										String oldvaluestr = player.getActivitiesReward().get(getActivitiesKey(player, q_activitiesBean));
										int oldvalue = Integer.valueOf(oldvaluestr);
										if (oldvalue >= value) {
											expandMap.put("remain", 0);
										} else {
											int remain = value - oldvalue;
											expandMap.put("remain", remain);
										}
									} else {
										expandMap.put("remain", value);
									}
								}
								break;
								default: {
								}
								break;
							}
						}
						break;
					}
				}
				if (!expandMap.isEmpty()) {
					list.add(JSON.toJSONString(expandMap));
				}
			}
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 获得物品数量
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	@SuppressWarnings("deprecation")
	public int getItemNum(Player player, int itemModelId) {
		int num = 0;
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && (item.getLosttime() == 0 || (excludeidlist.contains(itemModelId) && !item.isLost()) || (expiredallowlist.contains(itemModelId)) ) && !item.isTrade()) {
				num += item.getNum();
			}
		}
		return num;
	}

	/**
	 * 移除指定绑定类型 指定数量的物品(有过期时限的不移除)
	 *
	 * @param player
	 * @param itemModelId
	 * @param num
	 * @param reasons
	 * @param action
	 */
	@SuppressWarnings("deprecation")
	private boolean removeItem(Player player, int itemModelId, int num, Reasons reasons, long action) {
		if (getItemNum(player, itemModelId) < num) {
			// 数量不足
			return false;
		}
		long actionId = action != 0 ? action : Config.getId();
		for (int i = player.getBagCellsNum(); i >= 1 && num > 0; i--) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null) {
				continue;
			}
			Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
			if (item.getItemModelId() == itemModelId && (item.getLosttime() == 0 || (excludeidlist.contains(itemModelId) && !item.isLost()) || (expiredallowlist.contains(itemModelId)) ) && !item.isTrade()) {
				if (item.getNum() <= num) {
					BackpackManager.getInstance().removeItemByCellId(player, i, reasons, actionId);
					num = num - item.getNum();
				} else {
					String before = JSONserializable.toString(item);
					item.setNum(item.getNum() - num);
					num = 0;
					MessageUtil.tell_player_message(player, BackpackManager.getInstance().getItemChangeMessage(item));
					try {
						if (model.getQ_log() == 1) {
							ItemChangeLog log = new ItemChangeLog();
							log.setActionId(actionId);
							log.setAction(ItemAction.REMOVE.toString());
							log.setChangeAction(ItemChangeAction.CHANGE.toString());
							log.setItemafterInfo(JSONserializable.toString(item));
							log.setItembeforeInfo(before);
							log.setItemid(item.getId());
							log.setModelid(item.getItemModelId());
							log.setNum(item.getNum());
							log.setReason(reasons.getValue());
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);
						}
					} catch (Exception e) {
						logger.error(e, e);
					}
				}

			}
		}
//		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
//		if (num == 1) {
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//		} else {
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), num + "");
//		}
		return true;
	}

	/**
	 * TODO 检查活动是否存在
	 *
	 * @param player 玩家
	 * @param activitiesid 活动id
	 * @return boolean
	 */
	@Override
	public boolean checkActivities(Player player, int activitiesid) {
		try {
			Q_activitiesBean activitiesBean = DataManager.getInstance().q_activitiesContainer.getMap().get(activitiesid);
			if (activitiesBean != null) {
				if (activitiesBean.getQ_listimage().equalsIgnoreCase("2")) {
					Set<Integer> types = new HashSet<Integer>();
					List<Q_activitiesBean> currActivitiesList = getCurrActivitiesList(1);
					ListIterator<Q_activitiesBean> listIterator = currActivitiesList.listIterator();
					while (listIterator.hasNext()) {
						Q_activitiesBean q_activitiesBean = listIterator.next();
						if (q_activitiesBean != null) {
							if (types.contains(q_activitiesBean.getQ_type())) {
								continue;
							}
							if (!checkZoneServer(player, q_activitiesBean)) {
								types.add(q_activitiesBean.getQ_type());
								continue;
							}
							if (!checkTime(player, q_activitiesBean)) {
								if (!checkdelTime(player, q_activitiesBean)) {
									types.add(q_activitiesBean.getQ_type());
									continue;
								}
							}
							return false;
						}
					}
				}
				if (!checkZoneServer(player, activitiesBean)) {
					return false;
				}
				if (!checkTime(player, activitiesBean)) {
					if (!checkdelTime(player, activitiesBean)) {
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 活动随机掉落
	 *
	 * @param monster
	 * @param killer
	 */
	public void activityRandomDrop(List<Object> paramList) {
		if (paramList.size() < 2) {
			return;
		}
		Monster monster = (Monster) paramList.get(0);
		Fighter killer = (Fighter) paramList.get(1);
		int day = TimeUtil.GetSeriesDay();
		Player player = (Player) killer;
		com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
		if (map == null) {
			return;
		}
		
		//-------------------------数据库自定义掉落-------------------------------
		try {
			List<Q_activities_dropBean> list = ManagerPool.dataManager.q_activities_dropContainer.getList();
			if (!list.isEmpty()) {
				String platform = WServer.getInstance().getServerWeb();//平台ID
				for (Q_activities_dropBean dropBean : list) {
					if (RandomUtils.random(1, 10000) > dropBean.getQ_probability()){
						continue;	//几率没有随到
					}
					
					if (!dropBean.getQ_platform().equals("") && !dropBean.getQ_platform().contains(platform)) {
						continue;	//平台不符合
					}
					
					if (dropBean.getQ_activityid() > 0 && !checkActivities(player, dropBean.getQ_activityid())){ 
						continue;	//活动ID不符合
					}
	
					if (TimeUtil.getOpenAreaDay() < dropBean.getQ_openday()  ) {
						continue;	//开区天数不符合
					}
					
					if (dropBean.getQ_monlevel_Interval() >  0 && Math.abs(player.getLevel() - monster.getLevel()) > dropBean.getQ_monlevel_Interval() ) {
						continue;	//怪物等级区间不符合
					}
					
					if (!TimeUtil.checkRangeTime(dropBean.getQ_time_interval())) {
						continue;	//掉落时间范围不符合
					}
					
					Integer[] itemids = JSON.parseObject(dropBean.getQ_drop_items(),Integer[].class);
					int idx = RandomUtils.random(itemids.length);
					int itemid = itemids[idx];
					
					long ms =0 ;
					if (!dropBean.getQ_expired().equals("")) {
						Date date = TimeUtil.getDateByString(dropBean.getQ_expired());
						if (date != null) {
							ms = date.getTime();
							if (!excludeidlist.contains(itemid)) {//加入可兑换列表
								excludeidlist.add(itemid);
							}
						}
					}
					

					List<Item> items = Item.createItems(itemid, 1, false,ms);
					if (!items.isEmpty()) {
						Item item = items.get(0);
						item.setGridId(0);
						DropGoodsInfo info = new DropGoodsInfo();
						info.setDropGoodsId(item.getId());
						info.setItemModelId(item.getItemModelId());
						info.setNum(item.getNum());
						info.setX(monster.getPosition().getX());
						info.setY(monster.getPosition().getY());
						info.setDropTime(System.currentTimeMillis());
						MapDropInfo mapDropInfo = new MapDropInfo(info, item, map, System.currentTimeMillis() + 60 * 5 * 1000);
						ManagerPool.mapManager.enterMap(mapDropInfo);
					}
				}
			}
		} catch (Exception e) {
			log.error("q_activities_drop出错"+e,e);
		}
		//-------------------------数据库自定义掉落----结束-------------------------------
		if (Math.abs(player.getLevel() - monster.getLevel()) <= 10 ) {
			if (RandomUtils.random(1, 5000) == 1) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, 9154);//是否有藏宝符BUFF
				if (buffs.size() > 0) {
					int rewday = 0;
					if (player.getActivitiesReward().containsKey("JSBX_DROP_DAY")) {
						 rewday = Integer.valueOf(player.getActivitiesReward().get("JSBX_DROP_DAY"));
					}
					if (rewday != day) {
						player.getActivitiesReward().put("JSBX_DROP_DAY", day+"");
						player.getActivitiesReward().put("JSBX_DROP_NUM", "0");
					}
					if (!player.getActivitiesReward().containsKey("JSBX_DROP_NUM")) {
						player.getActivitiesReward().put("JSBX_DROP_NUM", "0");
					}
					int num = Integer.valueOf(player.getActivitiesReward().get("JSBX_DROP_NUM"));
					if (num < 20) {
						player.getActivitiesReward().put("JSBX_DROP_NUM", (num+1)+"");
						List<Item> items = Item.createItems(9162, 1, true, 0);
						ManagerPool.backpackManager.addItems(player, items, Config.getId());
						String itemname = ManagerPool.backpackManager.getName(9162);
						MessageUtil.notify_player(player, Notifys.CHAT_ROLE, "恭喜打怪获得：{1}",itemname);	
					}
				}
			}
		}	
	
	
		if (day >= 20130129 && day <= 20130131) {//国内
			if(TimeUtil.getOpenAreaDay() > 7){
				int activityId = 2601000; // 活动ID
				int itemId = 18139; // 掉落道具ID
				if (checkActivities(player, activityId)){ ;
					if (Math.abs(player.getLevel() - monster.getLevel()) <= 100 ) {
						if (RandomUtils.random(1, 100) == 1) {
							Date date = TimeUtil.getDateByString("2013-1-31 23:59:59");
							List<Item> items = Item.createItems(itemId, 1, false, date.getTime());
							if (!items.isEmpty()) {
								Item item = items.get(0);
								item.setGridId(0);
								DropGoodsInfo info = new DropGoodsInfo();
								info.setDropGoodsId(item.getId());
								info.setItemModelId(item.getItemModelId());
								info.setNum(item.getNum());
								info.setX(monster.getPosition().getX());
								info.setY(monster.getPosition().getY());
								info.setDropTime(System.currentTimeMillis());
								MapDropInfo mapDropInfo = new MapDropInfo(info, item, map, System.currentTimeMillis() + 60 * 5 * 1000);
								ManagerPool.mapManager.enterMap(mapDropInfo);
							}
						}
					}
				}
			}
		}
	}
	
	
	
	
	
	
	/**
	 * 获取骑战兵器等级
	 * @param weapon
	 * @return
	 */
	public int getHorseWeaponLayer(Player player){
		HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		if(weapon!=null){
			return weapon.getLayer();
		}
		return 0;
	}
	
	/**获取身上宝石等级之和
	 * 
	 * @param player
	 * @return
	 */
	public int getGemLevel(Player player){
		int lv= 0;
		Gem[][] gems = player.getGems();
		for (int i = 0; i < gems.length; i++) {
			Gem[] gempos = ManagerPool.gemManager.getPosGems(player,i);	//得到位置上可显示的宝石
			if (gempos != null) {
				for (int j = 0; j < gempos.length; j++) {
					if (gempos[j].getLevel() >= 6) {
						lv = lv + 6;
					}else {
						lv = lv + gempos[j].getLevel();
					}
				}
			}
		}
		return lv;
	}
	
	
	/**得到玩家境界等级
	 * 
	 * @param player
	 * @return
	 */
	public int getSkillLayer(Player player){
		Realm realm = player.getRealm();
		return realm.getRealmlevel();
	}
	
	
}
