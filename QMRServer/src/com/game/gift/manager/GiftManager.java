package com.game.gift.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.game.arrow.manager.ArrowManager;
import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Gift;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_giftBean;
import com.game.data.bean.Q_platformgiftBean;
import com.game.data.manager.DataManager;
import com.game.gift.bean.GiftInfo;
import com.game.gift.message.ReqGetGiftItemsToServerMessage;
import com.game.gift.message.ReqGetOtherGiftItemsToServerMessage;
import com.game.gift.message.ResSendGiftInfoToClientMessage;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.TimeUtil;

/**
 * 礼包管理类
 *
 * @author 杨洪岚
 */
public class GiftManager {

//	private Logger log = Logger.getLogger(GiftManager.class);
	private static Object obj = new Object();
	//礼包管理类实例
	private static GiftManager manager;

	private GiftManager() {
	}

	public static GiftManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new GiftManager();
			}
		}
		return manager;
	}
	private final int GiftType_Normal = 1;		//1.常规礼包，升级礼包；
	private final int GiftType_Invite = 2;		//2.推广玩家礼品；
	private final int GiftType_Login = 3;		//3.连续登陆；
	private final int GiftType_OnlineTime = 4;	//4.周在线时间；
	private final int GiftType_Time = 5;		//5.延时礼品；
	private final int GiftType_QianDao = 6;		//6.签到奖励；
	private final int GiftType_QianDaoCount = 7;	//7.累计签到次数领取奖励；
	////////////////////////////////////////////////
	private final int TimeType_Day = 1;		//时间天
	private final int TimeType_Week = 2;		//时间周
	private final int TimeType_Month = 3;		//时间月
	private final int TimeType_Forever = 4;		//时间永久
	////////////////////////////////////////////////
	private final int GetType_Fail = -1;		//不可用(也不可领取)
	private final int GetType_GetItem = -2;		//可以领取

	//------------------------成员函数-------------------------//
	/**
	 * 获取礼包里的物品
	 *
	 * @param player 玩家
	 * @param message 消息
	 * @return boolean
	 */
	public boolean reqGetGiftItemsToServer(Player player, ReqGetGiftItemsToServerMessage message, int num) {
		Gift gift = (Gift) BackpackManager.getInstance().getItemById(player, message.getGiftid());
		if (gift != null && gift instanceof Gift) {
			if (gift.isTrade()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包在交易中，不能领取。"));
				return false;
			}
			if (gift.isLost()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包已过期，不能领取。"));
				return false;
			}
			int needGridNum = gift.getOwnItems().size();
			if (gift.getMoney() != 0) {
				needGridNum--;
			}
			if (gift.getGold() != 0) {
				needGridNum--;
			}
			if (BackpackManager.getInstance().getEmptyGridNum(player) >= needGridNum) {
				for (int i = 0; i < gift.getOwnItems().size(); i++) {
					Item item = gift.getOwnItems().get(i);
					if (item != null) {
						if (item.getItemModelId() == -1) {//Money
							if (!BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.CARD_GIFT_MONEY, Config.getId())) {
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("礼包系统邮件"), ResManager.getInstance().getString("由于未知原因，您的礼包中铜币未领取成功，请点击附件领取。"), (byte) 1, item.getNum(), null);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的礼包中铜币未领取成功，请点击邮件领取。"));
							}
						} else if (item.getItemModelId() == -2) {//Gold
							if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.CARD_GIFT_GOLD, Config.getId())) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createBindGold(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("礼包系统邮件"), ResManager.getInstance().getString("由于未知原因，您的礼包中礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的礼包中礼金未领取成功，请点击邮件领取。"));
							}
						} else {//普通物品
							List<Item> items = new ArrayList<Item>();
							if (item instanceof Equip) {
								Equip equip = (Equip) item;
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long)equip.getLosttime()*1000, equip.getGradeNum(), equip.getAttributes().size());
							} else {
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long)item.getLosttime()*1000, 0, 0);
							}
							if (!BackpackManager.getInstance().addItems(player, items, Reasons.CARD_GIFT_ITEM, Config.getId())) {
//								List<Item> items = new ArrayList<Item>();
//								items.add(item);
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("礼包系统邮件"), ResManager.getInstance().getString("由于未知原因，您的礼包中该物品未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的礼包中有物品未领取成功，请点击邮件领取。"));
							}
						}
					}
				}
				if (gift.getZhenqi() != 0) {
					PlayerManager.getInstance().addZhenqi(player, gift.getZhenqi(),AttributeChangeReason.GIFT);
				}
				if (gift.getExp() != 0) {
					PlayerManager.getInstance().addExp(player, gift.getExp(), AttributeChangeReason.GIFT);
				}
				if (gift.getBindgold() != 0) {
					if (!BackpackManager.getInstance().changeBindGold(player, gift.getBindgold(), Reasons.CARD_GIFT_BINDGOLD, Config.getId())) {
						List<Item> items = new ArrayList<Item>();
						items.add(Item.createBindGold(gift.getBindgold()));
						MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("礼包系统邮件"), ResManager.getInstance().getString("由于未知原因，您的礼包中礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的礼包中礼金未领取成功，请点击邮件领取。"));
					}
				}
				if (gift.getFightspirit() != 0) {
					ArrowManager.getInstance().addFightSpiritNum(player, ArrowManager.FightType_RI, gift.getFightspirit(), true, ArrowReasonsType.GIFT);
				}
				if (gift.getRank() != 0) {
					RankManager.getInstance().addranknum(player, gift.getRank(), RankType.GIFT);
				}
				BackpackManager.getInstance().removeItem(player, gift, num, Reasons.CARD_GIFT_REMOVE, Config.getId());
				//MessageUtil.notify_player(player, Notifys.SUCCESS, "您的礼包打开成功，获得了物品");
				gift.getOwnItems().clear();
				return true;
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹格子不足，不能获得礼包物品"));
				return false;
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您打开的不是礼包"));
			return false;
		}
	}

	public void reqGetOtherGiftItemsToServer(Player player, ReqGetOtherGiftItemsToServerMessage message) {
		Q_giftBean q_giftBean = DataManager.getInstance().q_giftContainer.getMap().get((int) message.getGiftid());
		if (q_giftBean != null) {
			if (checkGiftGet(player, q_giftBean)) {
				if (BackpackManager.getInstance().getEmptyGridNum(player) >= 1) {
					List<Item> items = Item.createItems((int) message.getGiftid(), 1, true, 0);
					if (!items.isEmpty()) {
						long action = Config.getId();
						BackpackManager.getInstance().addItems(player, items, Reasons.CARD_GIFT, action);
						updateOldGiftInfo(player, q_giftBean.getQ_gift_type());
						player.getGetGiftMap().put(q_giftBean.getQ_gift_id() + "_" + q_giftBean.getQ_gift_type(), q_giftBean.getQ_gift_type());
						switch (q_giftBean.getQ_gift_type()) {
							case GiftType_Time: {
								setNewGiftInfo(player, GiftType_Time, 0);
								player.getOldGiftSaveInfo().setTime(q_giftBean.getQ_time_limit());
							}
							break;
						}
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于您的包裹已满，所以您的礼包未能正常发放，请清理包裹。"));
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("由于您的包裹已满，所以您的礼包未能正常发放，请清理包裹。"));
				}
			}
		}
	}

	/**
	 * 登陆发送礼包信息
	 *
	 * @param player 玩家
	 * @return
	 */
	public void loginSendGiftInfos(Player player) {
		if (!compareTime(player, TimeType_Day)) {
			if (TimeUtil.getBetweenDays(player.getNewGiftSaveInfo().getRecordtime(), System.currentTimeMillis()) > 1) {
				setNewGiftInfo(player, GiftType_Login, 1);
			} else {
				setNewGiftInfo(player, GiftType_Login, player.getNewGiftSaveInfo().getLogin() + 1);
			}
			player.getNewGiftSaveInfo().setRecordtime(System.currentTimeMillis());
		}
		ResSendGiftInfoToClientMessage sendMessage = new ResSendGiftInfoToClientMessage();
		for (Map.Entry<Integer, Q_giftBean> entry : DataManager.getInstance().q_giftContainer.getMap().entrySet()) {
			Q_giftBean q_giftBean = entry.getValue();
			if (q_giftBean != null) {
				GiftInfo giftInfo = new GiftInfo();
				giftInfo.setGiftid(q_giftBean.getQ_gift_id());
				giftInfo.setGifttype((byte) q_giftBean.getQ_gift_type());
				if (checkGiftShow(player, q_giftBean)) {
					if (checkGiftGet(player, q_giftBean)) {
						giftInfo.setValue(GetType_GetItem);
					} else {
						giftInfo.setValue(getGiftInfoValue(player, q_giftBean.getQ_gift_type()));
					}
				} else {
					giftInfo.setValue(GetType_Fail);
				}
				sendMessage.getGifts().add(giftInfo);
			}
		}
		MessageUtil.tell_player_message(player, sendMessage);
	}

	/**
	 * 退出保存礼包信息
	 *
	 * @param player 玩家
	 * @return
	 */
	public void quitSaveGiftInfos(Player player) {
	}

	public int getGiftInfoValue(Player player, int giftType) {
		switch (giftType) {
			case GiftType_Normal: {
				return -1;
			}
			case GiftType_Invite: {
				return player.getNewGiftSaveInfo().getInvite();
			}
			case GiftType_Login: {
				return player.getNewGiftSaveInfo().getLogin();
			}
			case GiftType_OnlineTime: {
				return player.getNewGiftSaveInfo().getOnline_time();
			}
			case GiftType_Time: {
				return player.getNewGiftSaveInfo().getTime();
			}
			case GiftType_QianDao: {
				return player.getNewGiftSaveInfo().getQiandao();
			}
			case GiftType_QianDaoCount: {
				return player.getNewGiftSaveInfo().getQiandao_count();
			}
		}
		return -1;
	}

	public boolean checkGiftShow(Player player, Q_giftBean q_giftBean) {
		switch (q_giftBean.getQ_gift_type()) {
			case GiftType_Time: {
				if (player.getGetGiftMap().containsKey(q_giftBean.getQ_gift_id() + "_" + q_giftBean.getQ_gift_type())) {
					return false;
				}
				if (!player.getGetGiftMap().containsKey((q_giftBean.getQ_gift_id() - 1) + "_" + q_giftBean.getQ_gift_type())) {
					return false;
				}
			}
			break;
			default: {
				if (player.getGetGiftMap().containsKey(q_giftBean.getQ_gift_id() + "_" + q_giftBean.getQ_gift_type())) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkGiftGet(Player player, Q_giftBean q_giftBean) {
		switch (q_giftBean.getQ_gift_type()) {
			case GiftType_Normal: {
				return false;
			}
			case GiftType_Invite: {
				if (player.getNewGiftSaveInfo().getInvite() >= q_giftBean.getQ_invite_limit()) {
					if (player.getNewGiftSaveInfo().getInvite() >= player.getOldGiftSaveInfo().getInvite() && checkGiftShow(player, q_giftBean)) {
						return true;
					}
				}
			}
			break;
			case GiftType_Login: {
				if (player.getNewGiftSaveInfo().getLogin() >= q_giftBean.getQ_login_limit()) {
					if (player.getNewGiftSaveInfo().getLogin() >= player.getOldGiftSaveInfo().getLogin() && checkGiftShow(player, q_giftBean)) {
						return true;
					}
				}
			}
			break;
			case GiftType_OnlineTime: {
				if (player.getNewGiftSaveInfo().getOnline_time() >= q_giftBean.getQ_online_time_limit()) {
					if (player.getNewGiftSaveInfo().getOnline_time() >= player.getOldGiftSaveInfo().getOnline_time() && checkGiftShow(player, q_giftBean)) {
						return true;
					}
				}
			}
			break;
			case GiftType_Time: {
				if (player.getNewGiftSaveInfo().getTime() >= q_giftBean.getQ_time_limit()) {
					if (player.getNewGiftSaveInfo().getTime() >= player.getOldGiftSaveInfo().getTime() && checkGiftShow(player, q_giftBean)) {
						return true;
					}
				}
			}
			break;
			case GiftType_QianDao: {
				if (checkGiftShow(player, q_giftBean)) {
					return true;
				}
			}
			break;
			case GiftType_QianDaoCount: {
				if (player.getNewGiftSaveInfo().getQiandao_count() >= q_giftBean.getQ_qiandao_count_limit()) {
					if (player.getNewGiftSaveInfo().getQiandao_count() >= player.getOldGiftSaveInfo().getQiandao_count() && checkGiftShow(player, q_giftBean)) {
						return true;
					}
				}
			}
			break;
		}
		return false;
	}

	public void setNewGiftInfoByTime(Player player, int timeNum) {
		setNewGiftInfo(player, GiftType_OnlineTime, player.getNewGiftSaveInfo().getOnline_time() + timeNum);
		setNewGiftInfo(player, GiftType_Time, player.getNewGiftSaveInfo().getTime() + timeNum);
	}

	public void setNewGiftInfoByDay(Player player) {
		if (!TimeUtil.isToday(player.getOldGiftSaveInfo().getRecordtime())) {
			setNewGiftInfo(player, GiftType_Login, player.getNewGiftSaveInfo().getLogin() + 1);
		}
	}

	public void setNewGiftInfo(Player player, int giftType, int setvalue) {
		switch (giftType) {
			case GiftType_Normal: {
				return;
			}
			case GiftType_Invite: {
				player.getNewGiftSaveInfo().setInvite(setvalue);
			}
			break;
			case GiftType_Login: {
				player.getNewGiftSaveInfo().setLogin(setvalue);
			}
			break;
			case GiftType_OnlineTime: {
				player.getNewGiftSaveInfo().setOnline_time(setvalue);
			}
			break;
			case GiftType_Time: {
				player.getNewGiftSaveInfo().setTime(setvalue);
			}
			break;
			case GiftType_QianDao: {
				player.getNewGiftSaveInfo().setQiandao(setvalue);
			}
			break;
			case GiftType_QianDaoCount: {
				player.getNewGiftSaveInfo().setQiandao_count(setvalue);
			}
			break;
		}
	}

	public void updateOldGiftInfo(Player player, int giftType) {
		switch (giftType) {
			case GiftType_Normal: {
				return;
			}
			case GiftType_Invite: {
				player.getOldGiftSaveInfo().setInvite(player.getNewGiftSaveInfo().getInvite());
			}
			break;
			case GiftType_Login: {
				player.getOldGiftSaveInfo().setLogin(player.getNewGiftSaveInfo().getLogin());
			}
			break;
			case GiftType_OnlineTime: {
				player.getOldGiftSaveInfo().setOnline_time(player.getNewGiftSaveInfo().getOnline_time());
			}
			break;
			case GiftType_Time: {
				player.getOldGiftSaveInfo().setTime(player.getNewGiftSaveInfo().getTime());
			}
			break;
			case GiftType_QianDao: {
				player.getOldGiftSaveInfo().setQiandao(player.getNewGiftSaveInfo().getQiandao());
			}
			break;
			case GiftType_QianDaoCount: {
				player.getOldGiftSaveInfo().setQiandao_count(player.getNewGiftSaveInfo().getQiandao_count());
			}
			break;
		}
	}

	public boolean compareTime(Player player, int timeType) {
		switch (timeType) {
			case TimeType_Day: {
				if (TimeUtil.isToday(player.getNewGiftSaveInfo().getRecordtime())) {
					return true;
				}
			}
			break;
			case TimeType_Week: {
				if (TimeUtil.isCurrentWeek(player.getNewGiftSaveInfo().getRecordtime())) {
					return true;
				}
			}
			break;
			case TimeType_Month: {
				return true;
			}
			//break;
			case TimeType_Forever: {
				return true;
			}
			//break;
		}
		return false;
	}

	public boolean clearGiftInfo(Player player) {
		List<Integer> timeTypeList = new ArrayList<Integer>();
		for (Map.Entry<String, Integer> entry : player.getGetGiftMap().entrySet()) {
			String keyStr = entry.getKey();
			Integer valueType = entry.getValue();
			switch (valueType) {
				case GiftType_Normal: {
				}
				break;
				case GiftType_Invite: {
				}
				break;
				case GiftType_Login: {
					if (!compareTime(player, TimeType_Week)) {
						if (!timeTypeList.contains(TimeType_Week)) {
							timeTypeList.add(TimeType_Week);
						}
						player.getGetGiftMap().remove(keyStr);
					}
				}
				break;
				case GiftType_OnlineTime: {
					if (!compareTime(player, TimeType_Week)) {
						if (!timeTypeList.contains(TimeType_Week)) {
							timeTypeList.add(TimeType_Week);
						}
						player.getGetGiftMap().remove(keyStr);
					}
				}
				break;
				case GiftType_Time: {
				}
				break;
				case GiftType_QianDao: {
					if (!compareTime(player, TimeType_Day)) {
						if (!timeTypeList.contains(TimeType_Day)) {
							timeTypeList.add(TimeType_Day);
						}
						player.getGetGiftMap().remove(keyStr);
					}
				}
				break;
				case GiftType_QianDaoCount: {
				}
				break;
			}
		}
		if (!timeTypeList.isEmpty()) {
			for (int i = 0; i < timeTypeList.size(); i++) {
				Integer timeType = timeTypeList.get(i);
				switch (timeType) {
					case TimeType_Day: {
						player.getNewGiftSaveInfo().setQiandao(0);
						player.getOldGiftSaveInfo().setQiandao(0);
					}
					break;
					case TimeType_Week: {
						player.getNewGiftSaveInfo().setOnline_time(0);
						player.getOldGiftSaveInfo().setOnline_time(0);
						player.getNewGiftSaveInfo().setLogin(0);
						player.getOldGiftSaveInfo().setLogin(0);
					}
					break;
					case TimeType_Month: {
					}
					break;
					case TimeType_Forever: {
					}
					break;
				}
			}
			player.getOldGiftSaveInfo().setRecordtime(player.getNewGiftSaveInfo().getRecordtime());
			player.getNewGiftSaveInfo().setRecordtime(System.currentTimeMillis());
			return true;
		}
		return false;
	}

	public void sendMailByGift(long receiverid, String receivername, int itemid) {
		if (itemid != 0) {
			List<Item> items = Item.createItems(itemid, 1, true, 0);
			if (!items.isEmpty()) {
				MailServerManager.getInstance().sendSystemMail(receiverid, receivername, ResManager.getInstance().getString("系统邮件"), ResManager.getInstance().getString("由于包裹满，或者其他原因，您的礼包未能正常发放，转发为系统邮件，请点击附件领取。"), (byte) 1, 0, items);
			}
		}
	}

	//请求领取平台礼包  1-成功 0-失败
	public int getPlatformGift(Player player, String platform, int giftid){
		String playerplatform = player.getWebName();
		if(StringUtils.isBlank(platform) || !platform.equals(playerplatform)){
			return -1; //平台标识不正确
		}
		//取得礼包数据
		Q_platformgiftBean bean = getPlatformGiftMap().get(giftid);
		if(bean==null){
			return -2; //要领取的礼包不存在
		}
		//判断是否可领取
		//时间限制
		String key = bean.getQ_agent()+bean.getQ_id();
		int canreceive = canReceive(player, key, bean.getQ_limit(), true); 
		if(canreceive < 0 || canreceive==2){
			return canreceive; //不符合限制条件 或 已领取
		}
		//平台礼包的特殊规则
		int platformcanreceive = canReceivePlatform(player, platform, bean.getQ_type(), bean.getQ_min_lv(), bean.getQ_max_lv(), true);
		if(platformcanreceive < 0){
			return platformcanreceive; //不符合平台礼包特殊规则
		}
		//可领取则  记标志位 发放 提示
		//标识
		long now = System.currentTimeMillis();
		player.getActivitiesReward().put(key, String.valueOf(now));
		//发放
		int giftitem = bean.getQ_giftid();
		List<Item> createItems = Item.createItems(giftitem, 1, true, 0L);
		long actionid = Config.getId();
		if(BackpackManager.getInstance().getAbleAddNum(player, giftitem, true, 0L)>0){ //TODO 默认绑定 无过期
			if(BackpackManager.getInstance().addItems(player, createItems, Reasons.PLATFORM_GIFT, actionid)){
				MessageUtil.notify_player(player, Notifys.SUCCESS, "领取{1}成功", bean.getQ_name());
			}else{
				MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "您的礼包", "请查收", (byte)1, 0, createItems);
			}
		}else{
			MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "您的礼包", "请查收", (byte)1, 0, createItems);
		}
		return 1;
	}
	
	//请求平台礼包列表 
	public List<GiftInfo> getPlatformGiftList(Player player, String platform){
		List<GiftInfo> giftinfolist = new ArrayList<GiftInfo>();
		String playerplatform = player.getWebName();
		if(StringUtils.isBlank(platform)||!platform.equals(playerplatform)){
			return giftinfolist; //平台标识不正确 直接返回
		}
		//根据平台得到对应礼包列表
		List<Q_platformgiftBean> giftlist = getPlatformGiftList();
		for(Q_platformgiftBean bean: giftlist){
			if(!bean.getQ_agent().equals(platform)){ continue; } //不是此平台的礼包 略过
			GiftInfo info = new GiftInfo();
			info.setGiftid(bean.getQ_id());
			info.setGifttype((byte) bean.getQ_type());
			info.setIteminfos(buildIteminfos(bean.getQ_giftid()));
			String key = bean.getQ_agent()+bean.getQ_id();
			// 0-可领取 1-未达成不可领 2-已领取不可领
			int canrecieve = canReceive(player, key, bean.getQ_limit(), false); //判断礼包是否能领取
			if(canrecieve>=0 && canrecieve!=2){
				int canReceivePlatform = canReceivePlatform(player, platform, bean.getQ_type(), bean.getQ_min_lv(), bean.getQ_max_lv(), false);
				if(canReceivePlatform>=0){
					info.setCanreceive((byte)0);
				}else{
					info.setCanreceive((byte)1);
				}
			}else{
				info.setCanreceive((byte)1);
				if(canrecieve==2) info.setCanreceive((byte)2);//已经领取
			}
			//
			if(platform.equals("qq3366") && bean.getQ_type()==4){
				info.setValue(bean.getQ_min_lv());
			}
			//
			giftinfolist.add(info);
		}
		
		//返回列表
		return giftinfolist;
	}

	//判断玩家是否能领取此礼包 并提示
	//limittype  (0-无时间限制 1-每日一次 2-一生一次)
	//返回 0 可领取  <0 不可领取 以及原因
	public int canReceive(Player player, String key, int limittype, boolean isnotify){
		int result = 0;
		Map<String, String> keysmap = player.getActivitiesReward();
		switch(limittype){
			case 0: //无限制
			{
				break;
			}
			case 1: //每日一次
			{
				String lasttime = ParseUtil.getMapString(keysmap, key);
				long now = System.currentTimeMillis();
				//不为空且同日不可领取
				if(!StringUtils.isBlank(lasttime) && TimeUtil.isSameDay(now, Long.valueOf(lasttime))){
					if(isnotify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您今天已领取过此礼包");
					result = 2; //每日一次不满足 今日已领取
				}
				break;
			}
			case 2: //一生一次
			{
				String value = ParseUtil.getMapString(keysmap, key);
				if(!StringUtils.isBlank(value)){
					if(isnotify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您已领取过此礼包");
					result = 2; //一生一次不满足 此角色已经领取 
				}
				break;
			}
		}
		return result;
	}
	
	//平台礼包特殊规则
	public int canReceivePlatform(Player player, String platform, int gifttype, int minlv, int maxlv, boolean notify){
		int result = 0;
		if(platform.equals("qq3366")){
			int bluelevel = player.getVipright().getWebVipLevel(); //蓝钻等级 最后8位是等级
			if(gifttype==1){ //蓝钻等级
				bluelevel = bluelevel & 255 ; //与 出 最后8位  2^8-1
				if(bluelevel<minlv || bluelevel>maxlv){
					if(notify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您的蓝钻等级不符合");
					result = -6;
				}
			}else if(gifttype==2){ //年费蓝钻
				int isyearblue = (bluelevel & 256)>>8 ;
				if(isyearblue!=1){
					if(notify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您还不是年费蓝钻贵族");
					result = -7;
				}
			}else if(gifttype==3){ //蓝钻贵族新手礼包
				if(bluelevel<=0){
					if(notify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您还不是蓝钻贵族");
					result = -8;
				}
			}else if(gifttype==4){ //蓝钻成长礼包
				int level = player.getLevel();
				if(level<minlv){
					if(notify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您的等级不能领取此礼包");
					result = -9;
				}
			}else if(gifttype==7){ //qq3366用户每日礼包
				int lv3366 = player.getVipright().getWebVipLevel2();
				if(lv3366<minlv || lv3366>maxlv){
					if(notify) MessageUtil.notify_player(player, Notifys.ERROR, "对不起,您的3366成长等级不能领取此礼包");
					result = -10;
				}
			}
		}
		return result;
	}
	
	//构造礼包内容iteminfo列表
	public List<ItemInfo> buildIteminfos(int giftid){
		List<ItemInfo> iteminfos = new ArrayList<ItemInfo>();
		Q_giftBean giftbean = DataManager.getInstance().q_giftContainer.getMap().get(giftid);
		//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
		String[] gift_dataStrings = giftbean.getQ_gift_data().split(";");
		for (int i = 0; i < gift_dataStrings.length; i++) {
			String gift_item = gift_dataStrings[i];
			if (gift_item != null && !gift_item.isEmpty()) {
				String[] itemdataStrings = gift_item.split(",");
				int itemid = 0;
				int itemnum = 0;
				int sex = 0;//1 男 2 女
				boolean bind = true;
				long losttime = 0;
				int gradenum = 0;
				int append = 0;
				for (int j = 0; j < itemdataStrings.length; j++) {
					String item_data = itemdataStrings[j];
					if (!StringUtils.isBlank(item_data)) {
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
								losttime = Long.valueOf(item_data);
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
				List<Item> createItems = Item.createItems(itemid, itemnum, bind, losttime, gradenum, append);
				if(createItems!=null && createItems.size()>0){
					for(Item item: createItems){
						iteminfos.add(item.buildItemInfo());
					}
				}
			}
		}
		return iteminfos;
	}
	
	//取得平台礼包map  
	private Map<Integer, Q_platformgiftBean> getPlatformGiftMap(){
		return DataManager.getInstance().q_platformgiftContainer.getMap();
	}
	//取得平台礼包list
	private List<Q_platformgiftBean> getPlatformGiftList(){
		return DataManager.getInstance().q_platformgiftContainer.getList();
	}
	
	public static void main(String[] args){
	}
	
}