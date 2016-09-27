package com.game.arrow.manager;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

import com.game.arrow.log.ArrowLog;
import com.game.arrow.message.ReqArrowInfoOpenMessage;
import com.game.arrow.message.ReqBowActivationMessage;
import com.game.arrow.message.ReqFightSpiritGetMessage;
import com.game.arrow.message.ReqFightSpiritOpenMessage;
import com.game.arrow.message.ReqStarActivationMessage;
import com.game.arrow.message.ResArrowInfoMessage;
import com.game.arrow.message.ResBowInfoMessage;
import com.game.arrow.message.ResFightSpiritGetNumMessage;
import com.game.arrow.message.ResFightSpiritInfoMessage;
import com.game.arrow.message.ResRoundArrowExteriorMessage;
import com.game.arrow.structs.ArrowReasonsType;
import com.game.arrow.structs.FightSpiritData;
import com.game.backpack.manager.BackpackManager;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_arrowBean;
import com.game.data.bean.Q_arrow_bowBean;
import com.game.data.bean.Q_arrow_starBean;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_fightspiritBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.skill.structs.Skill;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;
import com.game.zones.manager.ZonesManager;

/**
 * 弓箭系统
 *
 * @author 杨鸿岚
 */
public class ArrowManager {

	private Logger log = Logger.getLogger(ArrowManager.class);
	private static Object obj = new Object();
	//后台管理类实例
	private static ArrowManager manager;

	private ArrowManager() {
	}

	public static ArrowManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ArrowManager();
			}
		}
		return manager;
	}
	//------------------------------成员变量-------------------------------//
	private static int ARROW_MAXLV = 7;				//弓箭最大等阶
	private static int BOW_MAXLV = 7;				//箭支最大等阶
	private static int BOW_SUBMAXLV = 10;				//箭支最大子等阶
	//1日 2月 3金 4木 5水 6火 7土
	public static int FightType_RI = 1;				//1日(现在战魂类型只有一个了，就用日)
//	public static int FightType_YUE = 2;				//2月
//	public static int FightType_JIN = 3;				//3金
//	public static int FightType_MU = 4;				//4木
//	public static int FightType_SHUI = 5;				//5水
//	public static int FightType_HUO = 6;				//6火
//	public static int FightType_TU = 7;				//7土
	//通知类型
	private static int Notify_ArrowOpen = 1;			//弓箭打开
	private static int Notify_ArrowLvUp = 2;			//弓箭升级
	private static int Notify_ArrowMaxLvUp = 3;			//弓箭升级到最大
	private static int Notify_StarLvUp = 4;				//星斗升级
	private static int Notify_BowLvUp = 5;				//箭支升级
	private static int Notify_BowSubMaxLvUp = 6;			//箭支当阶满级
	private static int Notify_BowMainLvUp = 7;			//箭支升阶
	private static int Notify_BowMainMaxLvUp = 8;			//箭支全满级
	private static int Notify_AddFightSpirit = 9;			//战魂增加

	//------------------------------成员函数-------------------------------//
	/**
	 * 得到战魂值
	 *
	 * @return
	 */
	public int getFightSpiritNum(Player player, int type) {
		if (type != FightType_RI) {
			return 0;
		}
		FightSpiritData fightSpiritData = player.getArrowData().getFightSpiritDataMap().get("" + type);
		if (fightSpiritData != null) {
			return fightSpiritData.getNum();
		}
		return 0;
	}

	/**
	 * 加战魂值
	 *
	 * @return
	 */
	public boolean addFightSpiritNum(Player player, int type, int num, boolean bosend, int reason) {
		log.error("进入加战魂值=" + player.getId() + "=type=" + type + "=num=" + num + "=bosend=" + (bosend?"1":"0") + "=reason=" + reason);
		if (type != FightType_RI) {
			log.error("加战魂值类型不对=" + player.getId());
			return false;
		}
		FightSpiritData fightSpiritData = player.getArrowData().getFightSpiritDataMap().get("" + type);
		if (fightSpiritData != null) {
			fightSpiritData.setNum(fightSpiritData.getNum() + num);
		} else {
			fightSpiritData = new FightSpiritData();
			fightSpiritData.setType(type);
			fightSpiritData.setNum(num);
		}
		if (fightSpiritData.getNum() < 0) {
			fightSpiritData.setNum(0);
		}
		log.error("加战魂值成功=" + player.getId() + "=战魂值数据=" + JSON.toJSONString(fightSpiritData));
		player.getArrowData().getFightSpiritDataMap().put("" + type, fightSpiritData);
		if (bosend) {
			ResFightSpiritInfoMessage sendMessage = new ResFightSpiritInfoMessage();
			sendMessage.setNotifytype(Notify_AddFightSpirit);
			Iterator<FightSpiritData> iterator = player.getArrowData().getFightSpiritDataMap().values().iterator();
			while (iterator.hasNext()) {
				FightSpiritData sendfightSpiritData = iterator.next();
				sendMessage.getFightspiritList().add(sendfightSpiritData.toInfo());
			}
			MessageUtil.tell_player_message(player, sendMessage);
		}
		log.error("加战魂值写日志=" + player.getId());
		setArrowLog(player, 4, type, num, reason);
		log.error("加战魂值写日志成功=" + player.getId());
		return true;
	}

	public void addArrowSkill(Player player, Q_arrowBean q_arrowBean) {
		String[] skillStrs = q_arrowBean.getQ_ownskill().split("_");
		if (skillStrs.length < 2) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该弓箭等阶数据错误"));
			return;
		}
		int skillid = Integer.valueOf(skillStrs[0]);
		int skilllv = Integer.valueOf(skillStrs[1]);
		Skill skill = new Skill();
		skill.setSkillModelId(skillid);
		skill.setSkillLevel(skilllv);
		player.getArrowData().getSkilllist().add(skill);
	}

	public void addAllArrowSkill(Player player) {
		if (player == null) {
			return;
		}
		player.getArrowData().getSkilllist().clear();
		for (int i = 0; i <= player.getArrowData().getArrowlv(); i++) {
			Q_arrowBean q_arrowBean = DataManager.getInstance().q_arrowContainer.getMap().get(i);
			if (q_arrowBean != null && !q_arrowBean.getQ_ownskill().isEmpty()) {
				addArrowSkill(player, q_arrowBean);
			}
		}
	}

	/**
	 * 可触发技能列表
	 *
	 * @return
	 */
	public List<Skill> tiggerSkillList(Player player) {
		return player.getArrowData().getSkilllist();
	}

	/**
	 * 可触发技能数
	 *
	 * @return
	 */
	public int tiggerSkillNum(Player player) {
		return player.getArrowData().getBowData().getBowmainlv();
	}

	//------------------------------消息处理-------------------------------//
	public void reqArrowInfoOpenToServer(Player player, ReqArrowInfoOpenMessage message) {
		ResArrowInfoMessage sendMessage = new ResArrowInfoMessage();
		sendMessage.setNotifytype(Notify_ArrowOpen);
		sendMessage.setArrowinfo(player.getArrowData().toInfo());
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqStarActivationToServer(Player player, ReqStarActivationMessage message) {
		if (player.getArrowData().getArrowlv() == ARROW_MAXLV) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已完全激活七曜神弓"));
			return;
		}
		Q_arrow_starBean oldBean = DataManager.getInstance().q_arrow_starContainer.getMap().get(player.getArrowData().getStarData().getStarmainlv() + "_" + player.getArrowData().getStarData().getStarsublv());
		if (oldBean == null) {
			log.error("没有该星斗数据");
			return;
		}
		int newmainlv = oldBean.getQ_mainlv();
		int newsublv = oldBean.getQ_sublv() + 1;
		if (newsublv > oldBean.getQ_maxlv()) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("超过了【%s】星斗最大等阶"), oldBean.getQ_star_name()));
			return;
		}
		Q_arrow_starBean newBean = DataManager.getInstance().q_arrow_starContainer.getMap().get(newmainlv + "_" + newsublv);
		if (newBean == null) {
			log.error("没有要升阶的星斗数据");
			return;
		}
		if (player.getLevel() < newBean.getQ_need_lv()) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("【%s】升阶星斗需要人物达到%d级"), newBean.getQ_star_name(), newBean.getQ_need_lv()));
			return;
		}
		int ownFightSpiritnum = getFightSpiritNum(player, newBean.getQ_needtype());
		if (ownFightSpiritnum < newBean.getQ_neednum()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的战魂值不足，不能激发这股强大的力量"));
			return;
		}
		ResArrowInfoMessage sendMessage = new ResArrowInfoMessage();
		if (newBean.getQ_active_arrowid() != 0) {
			Q_arrowBean q_arrowBean = DataManager.getInstance().q_arrowContainer.getMap().get(newBean.getQ_active_arrowid());
			if (q_arrowBean == null) {
				log.error("没有该弓箭等阶数据");
				return;
			}
			if (player.getLevel() < q_arrowBean.getQ_need_lv()) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("【%s】升阶弓箭需要人物等级达到%d"), q_arrowBean.getQ_arrow_name(), newBean.getQ_need_lv()));
				return;
			}
			addFightSpiritNum(player, newBean.getQ_needtype(), -newBean.getQ_neednum(), false, ArrowReasonsType.ARROWLVUP);
			int oldarrowlv = player.getArrowData().getArrowlv();
			player.getArrowData().setArrowlv(newBean.getQ_active_arrowid());
			addArrowSkill(player, q_arrowBean);
			String[] starStrs = q_arrowBean.getQ_active_starid().split("_");
			if (starStrs.length < 2) {
				log.error("该弓箭等阶数据错误");
				return;
			}
			int nowstarmainlv = Integer.valueOf(starStrs[0]);
			int nowstarsublv = Integer.valueOf(starStrs[1]);
			player.getArrowData().getStarData().setStarmainlv(nowstarmainlv);
			player.getArrowData().getStarData().setStarsublv(nowstarsublv);
			sendMessage.setNotifytype(Notify_ArrowLvUp);
			if (player.getArrowData().getArrowlv() == ARROW_MAXLV) {
				sendMessage.setNotifytype(Notify_ArrowMaxLvUp);
			}
			ResRoundArrowExteriorMessage roundMessage = new ResRoundArrowExteriorMessage();
			roundMessage.setPlayerid(player.getId());
			roundMessage.setArrowid(player.getArrowData().getArrowlv());
			MessageUtil.tell_round_message(player, roundMessage);
			if (player.getArrowData().getArrowlv() == 1) {
				//MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功激活第一把弓箭【{2}】，获得超高属性加成及弓箭专属技能"), player.getName(), q_arrowBean.getQ_arrow_name());
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format("恭喜玩家【%s】成功激活第一把弓箭【%s】，获得超高属性加成及弓箭专属技能!{@}", player.getName(), q_arrowBean.getQ_arrow_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.ARROW_A.getValue()));
				MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.ARROW_A.getValue());
			} else if (player.getArrowData().getArrowlv() == ARROW_MAXLV) {
				//MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功将弓箭提升至顶级【{2}】，真乃人中之龙必成就一代霸业！"), player.getName(), q_arrowBean.getQ_arrow_name());
			
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format("恭喜玩家【%s】成功将弓箭提升至顶级【%s】，真乃人中之龙必成就一代霸业!{@}", player.getName(), q_arrowBean.getQ_arrow_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.ARROW_A.getValue()));
				MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.ARROW_A.getValue());
			} else {
				ParseUtil parseUtil = new ParseUtil();
				Q_arrowBean oldarrowBean = DataManager.getInstance().q_arrowContainer.getMap().get(oldarrowlv);
				if (oldarrowBean != null) {
					parseUtil.setValue(String.format("恭喜玩家【%s】成功将弓箭【%s】提升为【%s】，获得超高属性加成!{@}", player.getName(), oldarrowBean.getQ_arrow_name(), q_arrowBean.getQ_arrow_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.ARROW_A.getValue()));
					//MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功将弓箭【{2}】提升为【{3}】，获得超高属性加成！"), player.getName(), oldarrowBean.getQ_arrow_name(), q_arrowBean.getQ_arrow_name());
				} else {
					parseUtil.setValue(String.format("恭喜玩家【%s】成功将弓箭提升至顶级【%s】，获得超高属性加成！{@}", player.getName(), q_arrowBean.getQ_arrow_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.ARROW_A.getValue()));
					//MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功将弓箭提升为【{2}】，获得超高属性加成！"), player.getName(), q_arrowBean.getQ_arrow_name());
				}
				MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.ARROW_A.getValue());
			}
			

			
		} else {
			addFightSpiritNum(player, newBean.getQ_needtype(), -newBean.getQ_neednum(), false, ArrowReasonsType.ARROWLVUP);
			player.getArrowData().getStarData().setStarmainlv(newBean.getQ_mainlv());
			player.getArrowData().getStarData().setStarsublv(newBean.getQ_sublv());
			sendMessage.setNotifytype(Notify_StarLvUp);
		}
		PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.ARROW);
		sendMessage.setArrowinfo(player.getArrowData().toInfo());
		MessageUtil.tell_player_message(player, sendMessage);
		setArrowLog(player, 1, 0, 0, ArrowReasonsType.ARROWLVUP);
	}

	public void reqBowActivationToServer(Player player, ReqBowActivationMessage message) {
		if (player.getArrowData().getBowData().getBowmainlv() >= BOW_MAXLV && player.getArrowData().getBowData().getBowsublv() >= BOW_SUBMAXLV) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的箭支已达满级"));
			return;
		}
		Q_arrow_bowBean oldBean = DataManager.getInstance().q_arrow_bowContainer.getMap().get(player.getArrowData().getBowData().getBowmainlv() + "_" + player.getArrowData().getBowData().getBowsublv());
		if (oldBean == null) {
			log.error("没有原箭支数据");
			return;
		}
		int newmainlv = oldBean.getQ_mainlv();
		int newsublv = oldBean.getQ_sublv() + 1;
		if (newsublv > BOW_SUBMAXLV) {
			newmainlv = newmainlv + 1;
			newsublv = 0;
		}
		Q_arrow_bowBean newBean = DataManager.getInstance().q_arrow_bowContainer.getMap().get(newmainlv + "_" + newsublv);
		if (newBean == null) {
			log.error("没有要升星的箭支数据");
			return;
		}
		if (player.getLevel() < newBean.getQ_need_lv()) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("【%s】升星需要人物达到等级%d级"), newBean.getQ_bow_name(), newBean.getQ_need_lv()));
			return;
		}
		if (player.getArrowData().getArrowlv() < newBean.getQ_need_arrowlv()) {
			Q_arrowBean arrowBean = DataManager.getInstance().q_arrowContainer.getMap().get(newBean.getQ_need_arrowlv());
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("【%s】升星需要弓箭达到【%s】"), newBean.getQ_bow_name(), arrowBean == null ? newBean.getQ_need_arrowlv() + "级" : arrowBean.getQ_arrow_name()));
			return;
		}
		if (newBean.getQ_need_num() != 0) {
			int ownitemnum = BackpackManager.getInstance().getItemNum(player, newBean.getQ_need_item());
			if (ownitemnum < newBean.getQ_need_num()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的道具不足，不能进行升星"));
				return;
			}
			if (!BackpackManager.getInstance().removeItem(player, newBean.getQ_need_item(), newBean.getQ_need_num(), Reasons.ARROW_BOWLVUPDELITEM, Config.getId())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的道具移除失败，不能进行升星"));
				return;
			}
		}
		ResBowInfoMessage sendMessage = new ResBowInfoMessage();
		if (newsublv == BOW_SUBMAXLV) {
			player.getArrowData().getBowData().setBowmainlv(newmainlv);
			player.getArrowData().getBowData().setBowsublv(newsublv);
			sendMessage.setNotifytype(Notify_BowSubMaxLvUp);
			if (player.getArrowData().getBowData().getBowmainlv() == BOW_MAXLV && player.getArrowData().getBowData().getBowsublv() == BOW_SUBMAXLV) {
				sendMessage.setNotifytype(Notify_BowMainMaxLvUp);
			} else {
				newmainlv = newmainlv + 1;
				newsublv = 0;
				Q_arrow_bowBean nowBean = DataManager.getInstance().q_arrow_bowContainer.getMap().get(newmainlv + "_" + newsublv);
				if (player.getArrowData().getArrowlv() < nowBean.getQ_need_arrowlv()) {
					Q_arrowBean q_arrowBean = DataManager.getInstance().q_arrowContainer.getMap().get(nowBean.getQ_need_arrowlv());
					if (q_arrowBean != null) {
						MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("对不起，您需激活弓箭【%s】后才能升阶。"), q_arrowBean.getQ_arrow_name()));
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("对不起，您需激活下一级弓箭后才能升阶。")));
					}
				} else {
					if (nowBean.getQ_need_num() != 0) {
						boolean boMainlvup = true;
						int ownitemnum = BackpackManager.getInstance().getItemNum(player, nowBean.getQ_need_item());
						if (ownitemnum < nowBean.getQ_need_num()) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的道具不足，不能进行升阶"));
							boMainlvup = false;
						}
						if (!BackpackManager.getInstance().removeItem(player, nowBean.getQ_need_item(), nowBean.getQ_need_num(), Reasons.ARROW_BOWLVUPDELITEM, Config.getId())) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的道具移除失败，不能进行升阶"));
							boMainlvup = false;
						}
						if (boMainlvup) {
							player.getArrowData().getBowData().setBowmainlv(newmainlv);
							player.getArrowData().getBowData().setBowsublv(newsublv);
							sendMessage.setNotifytype(Notify_BowMainLvUp);
						}
					} else {
						player.getArrowData().getBowData().setBowmainlv(newmainlv);
						player.getArrowData().getBowData().setBowsublv(newsublv);
						sendMessage.setNotifytype(Notify_BowMainLvUp);
					}
				}
			}
		} else {
			player.getArrowData().getBowData().setBowmainlv(newmainlv);
			player.getArrowData().getBowData().setBowsublv(newsublv);
			sendMessage.setNotifytype(Notify_BowLvUp);
		}
		PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.ARROW);
		sendMessage.setBowinfo(player.getArrowData().getBowData().toInfo());
		MessageUtil.tell_player_message(player, sendMessage);
		setArrowLog(player, 2, 0, 0, ArrowReasonsType.BOWLVUP);
	}

	public void reqFightSpiritOpenToServer(Player player, ReqFightSpiritOpenMessage message) {
		Q_clone_activityBean q_clone_activityBean = DataManager.getInstance().q_clone_activityContainer.getMap().get(message.getZoneid());
		if (q_clone_activityBean == null) {
			log.error(message.getZoneid() + "==没有该副本数据");
			return;
		}
		if (q_clone_activityBean.getQ_zone_type() != 4) {
			log.error(message.getZoneid() + "==副本类型错误");
			return;
		}
		if (ZonesManager.getInstance().getZoneTime(player, message.getZoneid()) <= 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您没有通关副本【%s】"), q_clone_activityBean.getQ_duplicate_name()));
			return;
		}
		//判断次数
		long count = CountManager.getInstance().getCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId());
		ResFightSpiritGetNumMessage sendMessage = new ResFightSpiritGetNumMessage();
		sendMessage.setNum((int) count);
		sendMessage.setZoneid(message.getZoneid());
		if (!player.getActivitiesReward().containsKey("FIRSTGETFIGHTSPIRIT")) {
			sendMessage.setFirst(1);
		}
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void reqFightSpiritGetToServer(Player player, ReqFightSpiritGetMessage message) {
		Q_clone_activityBean q_clone_activityBean = DataManager.getInstance().q_clone_activityContainer.getMap().get(message.getZoneid());
		if (q_clone_activityBean == null) {
			log.error(message.getZoneid() + "==没有该副本数据");
			return;
		}
		if (q_clone_activityBean.getQ_zone_type() != 4) {
			log.error(message.getZoneid() + "==副本类型错误");
			return;
		}
		if (ZonesManager.getInstance().getZoneTime(player, message.getZoneid()) <= 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您没有通关副本【%s】"), q_clone_activityBean.getQ_duplicate_name()));
			return;
		}
		List<Q_fightspiritBean> fsList = new ArrayList<Q_fightspiritBean>();
		ListIterator<Q_fightspiritBean> listIter = DataManager.getInstance().q_fightspiritContainer.getList().listIterator();
		while (listIter.hasNext()) {
			Q_fightspiritBean q_fightspiritBean = listIter.next();
			if (q_fightspiritBean != null && q_fightspiritBean.getQ_fightspirit_type() == message.getZoneid()) {
				fsList.add(q_fightspiritBean);
			}
		}
		if (message.getGettype() == 0) {
			//判断次数
			long count = CountManager.getInstance().getCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId());
			Q_fightspiritBean nowBean = DataManager.getInstance().q_fightspiritContainer.getMap().get(message.getZoneid() + "_" + ((int) count + 1));
			if (nowBean == null) {
				log.error("七曜战魂数据出错！count==" + count);
				return;
			}
			int needyuanbao = nowBean.getQ_yuanbao();
			if (!BackpackManager.getInstance().checkGold(player, needyuanbao)) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您的元宝不足%s，不能搜索七曜战魂【%s】。"), String.valueOf(needyuanbao), q_clone_activityBean.getQ_duplicate_name()));
				return;
			}
			if (count == 0) {
				CountManager.getInstance().addCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId(), 1, 1, 1);
			} else {
				if (count < fsList.size()) {
					CountManager.getInstance().addCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId(), 1);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("今天您搜索七曜战魂【%s】次数已经达到上限。"), q_clone_activityBean.getQ_duplicate_name()));
					return;
				}
			}
			BackpackManager.getInstance().changeGold(player, -needyuanbao, Reasons.ARROW_DELGOLD, Config.getId());
			int addfightspirit = nowBean.getQ_getfightspirit();
			if (addfightspirit > 0) {
				List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, 9125);
				if (!buffs.isEmpty()) {
					addfightspirit = addfightspirit * 2;
				}
			}
			addFightSpiritNum(player, FightType_RI, addfightspirit, true, ArrowReasonsType.FSSOUSUO);
			ResFightSpiritGetNumMessage sendMessage = new ResFightSpiritGetNumMessage();
			sendMessage.setNum((int) count + 1);
			sendMessage.setZoneid(message.getZoneid());
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			long count = CountManager.getInstance().getCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId());
			int needyuanbao = 0;
			int addcount = 0;
			int addfightspirit = 0;
			ListIterator<Q_fightspiritBean> listIterator = fsList.listIterator();
			while (listIterator.hasNext()) {
				Q_fightspiritBean q_fightspiritBean = listIterator.next();
				if (q_fightspiritBean != null && q_fightspiritBean.getQ_fightspirit_num() > count && q_fightspiritBean.getQ_fightspirit_type() == message.getZoneid()) {
					needyuanbao = needyuanbao + q_fightspiritBean.getQ_yuanbao();
					addfightspirit = addfightspirit + q_fightspiritBean.getQ_getfightspirit();
					addcount++;
				}
			}
			if (!BackpackManager.getInstance().checkGold(player, needyuanbao)) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您的元宝不足%s，不能连续搜索七曜战魂【%s】。"), String.valueOf(needyuanbao), q_clone_activityBean.getQ_duplicate_name()));
				return;
			}
			if (count == 0) {
				CountManager.getInstance().addCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId(), 1, addcount, 1);
			} else {
				if (count < fsList.size()) {
					CountManager.getInstance().addCount(player, CountTypes.FIGHTSPIRIT_NUM, message.getZoneid() + "_" + player.getId(), addcount);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("今天您搜索七曜战魂【%s】次数已经达到上限。"), q_clone_activityBean.getQ_duplicate_name()));
					return;
				}
			}
			BackpackManager.getInstance().changeGold(player, -needyuanbao, Reasons.ARROW_DELGOLD, Config.getId());
			if (addfightspirit > 0) {
				List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, 9125);
				if (!buffs.isEmpty()) {
					addfightspirit = addfightspirit * 2;
				}
			}
			addFightSpiritNum(player, FightType_RI, addfightspirit, true, ArrowReasonsType.FSSOUSUO);
			ResFightSpiritGetNumMessage sendMessage = new ResFightSpiritGetNumMessage();
			sendMessage.setNum((int) count + addcount);
			sendMessage.setZoneid(message.getZoneid());
			MessageUtil.tell_player_message(player, sendMessage);
		}
		if (!player.getActivitiesReward().containsKey("FIRSTGETFIGHTSPIRIT")) {
			player.getActivitiesReward().put("FIRSTGETFIGHTSPIRIT", "1");
		}
		setArrowLog(player, 3, 0, 0, ArrowReasonsType.FSSOUSUO);
	}

	public void setArrowLog(Player player, int type, int fstype, int fsnum, int reason) {
		try {
			if (player != null) {
				ArrowLog arrowLog = new ArrowLog(player, type, fstype, fsnum, reason);
				LogService.getInstance().execute(arrowLog);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}
