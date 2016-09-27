package com.game.country.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.backpack.bean.GoodsAttribute;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.config.Config;
import com.game.country.message.ReqCountrysalaryToGameMessage;
import com.game.country.message.ReqKingCityChestSelectToGameMessage;
import com.game.country.message.ResKingCityChestPanelShowToClientMessage;
import com.game.country.message.ResKingCityChestSelectToClientMessage;
import com.game.country.structs.KingCity;
import com.game.country.structs.KingData;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_spirittree_kiwiBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.spirittree.manager.FruitAppendManager;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 王城帮派奖励管理类
 *
 * @author 杨鸿岚
 */
public class CountryAwardManager {

	protected Logger log = Logger.getLogger(CountryAwardManager.class);
	//王城帮派奖励管理类实例
	private static CountryAwardManager manager;
	private static Object obj = new Object();

	private CountryAwardManager() {
	}

	public static CountryAwardManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CountryAwardManager();
			}
		}
		return manager;
	}
	//设置王城宝箱奖励数量
	private int REWARDSUM = 10;
	public static int KINGCITYCHEST = 8022;		//王城宝箱物品id
	public static int KINGCITYCHESTTYPE = 4;	//王城宝箱灵树奖励类型

	//-----------------------成员函数-------------------------//
	/**
	 * 判断是否可以领取奖励(攻城战时间)
	 *
	 * @return true 不在攻城战期间，可以领取薪水等 false 在攻城战期间，不可以领取薪水等
	 */
	public boolean isKingCityAwardOpen() {
		return CountryManager.getInstance().getSiegestate() != 1 ? true : false;
	}

	public KingCity getKingCity() {
		return CountryManager.getInstance().getKingcity();
	}

	public int getKingTerm() {
		return getKingCity().gKingTerm();
	}

	public KingData getCurKingData() {
		return getKingCity().gCurKingData();
	}

	public KingData getKingData(int kingterm) {
		return getKingCity().gKingData(kingterm);
	}

	public boolean isKingCity(Player player) {
		return getKingCity().checkKingCity(player);
	}

	public boolean isKingCity(long guildid) {
		return getKingCity().checkKingCity(guildid);
	}

	public int kingCityGetBuffLimitTime() {
		int ret = 0;
		ret = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.KINGCITY_GETBUFFLIMITTIME.getValue()).getQ_int_value();
		if (ret == 0) {
			ret = 1000 * 60 * 60 * 24 * 5;
		}
		return ret;
	}

	/**
	 * 给其他国家的人加王城BUFF(没有判断是否王城帮派，自己判断)
	 *
	 * @param player 玩家
	 * @return
	 */
	public void setOtherKingCityBuff(Player player) {
		if (player != null) {
			int kingBuff = player.getMemberInfo().getGuildPowerLevel();
			if (kingBuff != 0) {
				switch (kingBuff) {
					case 1: {
						List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_KING);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_KING, 0, 0, 0);
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_HEGEMONY);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_HEGEMONY, 0, 0, 0);
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_RENDE);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_RENDE, 0, 0, 0);
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_NORMAL);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_NORMAL, 0, 0, 0);
						}
					}
					break;
					default: {
						if (System.currentTimeMillis() >= (player.getMemberInfo().getAddTime() + kingCityGetBuffLimitTime())) {
							List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_NORMAL);
							if (buffs.isEmpty()) {
								BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_NORMAL, 0, 0, 0);
							}
						}
					}
					break;
				}
			}
		}
	}

	/**
	 * 给本国的玩家加上王城BUFF(有王城帮派判断)
	 *
	 * @param player 玩家
	 * @return
	 */
	public void setKingCityBuff(Player player) {
		if (player != null) {
			int kingBuff = player.gKingBuff();
			log.error(String.format("获得王城成员信息kingBuff=%d", kingBuff));
			if (kingBuff != 0) {
				switch (kingBuff) {
					case 1: {
						List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_KING);
						if (buffs.isEmpty()) {
							int ret = BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_KING, 0, 0, 0);
							log.error(String.format("加王城BUFF=KINGCITY_KING=%d=%d", BuffConst.KINGCITY_KING, ret));
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_HEGEMONY);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_HEGEMONY, 0, 0, 0);
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_RENDE);
						if (buffs.isEmpty()) {
							BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_RENDE, 0, 0, 0);
						}
						buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_NORMAL);
						if (buffs.isEmpty()) {
							int ret = BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_NORMAL, 0, 0, 0);
							log.error(String.format("加王城BUFF=KINGCITY_NORMAL=%d=%d", BuffConst.KINGCITY_NORMAL, ret));
						}
						CountryManager.getInstance().setKingdiaoxoang(player);
						log.error("加王城BUFF完成");
					}
					break;
					default: {
						if (System.currentTimeMillis() >= (player.getMemberInfo().getAddTime() + kingCityGetBuffLimitTime())) {
							List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_NORMAL);
							if (buffs.isEmpty()) {
								int ret = BuffManager.getInstance().addBuff(player, player, BuffConst.KINGCITY_NORMAL, 0, 0, 0);
								log.error(String.format("加王城普通BUFF=KINGCITY_NORMAL=%d=%d", BuffConst.KINGCITY_NORMAL, ret));
							}
							log.error("加王城普通BUFF完成");
						}else{
							log.error("加王城普通BUFF失败，时间不足=" + player.getMemberInfo().getAddTime());
						}
					}
					break;
				}
			}
		}
	}

	/**
	 * 删除王城BUFF(所有国家)
	 *
	 * @param player 玩家
	 * @return
	 */
	public void removeKingCityBuff(Player player) {
		if (player != null) {
			List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_KING);
			if (!buffs.isEmpty()) {
				BuffManager.getInstance().removeByBuffId(player, BuffConst.KINGCITY_KING);
			}
			buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_HEGEMONY);
			if (!buffs.isEmpty()) {
				BuffManager.getInstance().removeByBuffId(player, BuffConst.KINGCITY_HEGEMONY);
			}
			buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_RENDE);
			if (!buffs.isEmpty()) {
				BuffManager.getInstance().removeByBuffId(player, BuffConst.KINGCITY_RENDE);
			}
			buffs = BuffManager.getInstance().getBuffByModelId(player, BuffConst.KINGCITY_NORMAL);
			if (!buffs.isEmpty()) {
				BuffManager.getInstance().removeByBuffId(player, BuffConst.KINGCITY_NORMAL);
			}
		}
	}

	public int getKingCityChestNum(Player player) {
		int ret = 0;
		if (player != null) {
			switch (player.getMemberInfo().getGuildPowerLevel()) {
				case 1: {
					ret = 6;
				}
				break;
				case 2: {
					ret = 2;
				}
				break;
				case 3: {
					ret = 1;
				}
				break;
				case 4: {
					ret = 1;
				}
				break;
				case 5: {
					ret = 1;
				}
				break;
			}
			//TODO 继续判断是否秦后（结婚系统还没做）
		}
		return ret;
	}

	//-----------------------消息处理函数-------------------------//
	public void reqCountrysalaryToGame(Player player, ReqCountrysalaryToGameMessage message) {
		if (isKingCity(player)) {
			if (isKingCityAwardOpen()) {
				if (player.checkCountry()) {
					if (getKingCity().checkSalary(player)) {
						if (BackpackManager.getInstance().getEmptyGridNum(player) >= getKingCityChestNum(player)) {
							List<Item> items = Item.createItems(KINGCITYCHEST, getKingCityChestNum(player), true, 0);
							BackpackManager.getInstance().addItems(player, items, Reasons.KINGCITY_GETCHEST, Config.getId());
							getKingCity().sCurdayToSalary(player.getMemberInfo().getGuildPowerLevel());
							CountryManager.getInstance().savekingcity(getKingCity());
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹已满，请清理后再来领取！"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您无法领取王城宝箱，已经被领取过了！"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您不在本国，无法领取王城宝箱！"));
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("现在王城正在争夺中，无法领取王城宝箱！"));
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您不是王城帮派成员，不能领取王城宝箱！"));
		}
	}
	//-----------------------王城宝箱-----------------------------//

	/**
	 * 奖励组包内容
	 *
	 * @return
	 *
	 */
	public HashMap<Integer, Q_spirittree_pack_conBean> getpackcondata() {
		HashMap<Integer, Q_spirittree_pack_conBean> data = DataManager.getInstance().q_spirittree_pack_conContainer.getMap();
		return data;
	}

	/**
	 * 发送王城宝箱奖励
	 *
	 * @param player
	 * @param msg
	 */
	public void innerKingCityChestPanelShow(Player player) {
		ResKingCityChestPanelShowToClientMessage message = new ResKingCityChestPanelShowToClientMessage();
		List<FruitReward> rewardlist = player.getKingCityChest().getKingcitychestlist();

		for (FruitReward fruitReward : rewardlist) {
			message.getItemlist().add(getItemInfo(fruitReward));
		}

		if (message.getItemlist().size() > 0) {
			for (int i = 0; i < 15; i++) {
				int rnd = RandomUtils.random(1, message.getItemlist().size()) - 1;
				ItemInfo item = message.getItemlist().remove(rnd);
				message.getItemlist().add(item);
			}
		}
		MessageUtil.tell_player_message(player, message);
	}

	/**
	 * 生成ItemInfo
	 *
	 * @param fruitReward
	 * @return
	 */
	public ItemInfo getItemInfo(FruitReward fruitReward) {
		ItemInfo itemInfo = new ItemInfo();
		itemInfo.setIntensify((byte) fruitReward.getStrenglevel());
		if (fruitReward.isBind()) {
			itemInfo.setIsbind((byte) 1);
		}
		itemInfo.setItemModelId(fruitReward.getItemModelid());
		itemInfo.setNum(fruitReward.getNum());
		itemInfo.setLostTime((int) fruitReward.getLosttime());
		itemInfo.setAttributs((byte) fruitReward.getFruitRewardAttrslist().size());
		for (FruitRewardAttr fruitRewardAttr : fruitReward.getFruitRewardAttrslist()) {
			GoodsAttribute goodsatt = new GoodsAttribute();
			goodsatt.setType(fruitRewardAttr.getType());
			goodsatt.setValue(fruitRewardAttr.getValue());
			itemInfo.getGoodAttributes().add(goodsatt);
		}
		itemInfo.setGridId(-1);
		itemInfo.setItemId(fruitReward.getId());
		return itemInfo;
	}

	/**
	 * 筛选并设置奖励列表
	 *
	 * @param player
	 * @param fruit
	 * @param rewarddata
	 */
	public void createFruitRewardList(Player player, int type) {
		player.getKingCityChest().getKingcitychestlist().clear();
		player.getKingCityChest().getKingcitychestidx().clear();

		List<Integer> grouplist = randomGroup(player, KINGCITYCHESTTYPE);//类型为4
		HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
		for (Integer gid : grouplist) {
			List<Integer> tmpidxs = new ArrayList<Integer>(); // 储存符合条件的道具奖励索引
			List<Integer> tmprnds = new ArrayList<Integer>(); //选中道具的几率值
			Iterator<Map.Entry<Integer, Q_spirittree_pack_conBean>> it = rewarddata.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Q_spirittree_pack_conBean> pack = it.next();
				Q_spirittree_pack_conBean data = pack.getValue();
				if (gid == data.getQ_packet_id()) {
					if (player.getLevel() >= data.getQ_need_level() && player.getLevel() < data.getQ_exclude_level()) {
						if (data.getQ_item_id() > 0) {
							Q_itemBean itemdata = ManagerPool.dataManager.q_itemContainer.getMap().get(data.getQ_item_id());
							if (itemdata != null) {
								if (itemdata.getQ_type() == 1) {
									//检测装备性别
									if (itemdata.getQ_sex() == 0 || itemdata.getQ_sex() == player.getSex()) {
										tmpidxs.add(data.getQ_id());
										tmprnds.add(data.getQ_selected_rnd());
									}
								} else {
									tmpidxs.add(data.getQ_id());
									tmprnds.add(data.getQ_selected_rnd());
								}
							}
						} else {
							tmpidxs.add(data.getQ_id());
							tmprnds.add(data.getQ_selected_rnd());
						}
					}
				}
			}


			if (tmprnds.size() > 0) {
				int index = RandomUtils.randomIndexByProb(tmprnds);//在道具合集中随机一个道具
				int idx = tmpidxs.get(index);
				Q_spirittree_pack_conBean rewdata = rewarddata.get(idx);//获取选中道具信息
				FruitReward fruitreward = createFruitreward(rewdata);
				player.getKingCityChest().getKingcitychestlist().add(fruitreward);
			}
		}
	}

	/**
	 * 随机奖励组包 :
	 *
	 * @return type=4 副本奖励
	 */
	public List<Integer> randomGroup(Player player, int type) {
		List<Integer> gidlist = new ArrayList<Integer>();  //选中的组包列表
		List<Integer> tmplist = new ArrayList<Integer>();	//临时补充列表
		HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		List<Q_spirittree_kiwiBean> data = DataManager.getInstance().q_spirittree_kiwiContainer.getList();
		for (Q_spirittree_kiwiBean kiwiBean : data) {
			if (kiwiBean.getQ_type() == KINGCITYCHESTTYPE && type == KINGCITYCHESTTYPE) {
				if (player.getLevel() >= kiwiBean.getQ_need_level()) {
					int num = 0;
					if (numMap.containsKey(kiwiBean.getQ_packet_id())) {
						num = numMap.get(kiwiBean.getQ_packet_id());
					}

					for (int i = 0; i < kiwiBean.getQ_check_num(); i++) {
						if (RandomUtils.isGenerate2(10000, kiwiBean.getQ_arise_rnd())) {
							if (num < kiwiBean.getQ_check_num()) {	//复选
								if (gidlist.size() >= REWARDSUM) {
									break;
								}
								gidlist.add(kiwiBean.getQ_packet_id());
								numMap.put(kiwiBean.getQ_packet_id(), num + 1);
							}
						}
					}
				}
				tmplist.add(kiwiBean.getQ_packet_id());
			}

		}

		if (gidlist.size() < REWARDSUM) {
			//组包不足10个，需要补充
			if (tmplist.size() > 1) {
				Collections.sort(tmplist, new Comparator<Integer>() {

					@Override
					public int compare(Integer o1, Integer o2) {
						if (o1 < o2) {
							return 1;
						}
						return 0;
					}
				});
				int num = REWARDSUM - gidlist.size();//补充数量
				for (int i = 0; i < num; i++) {
					gidlist.add(tmplist.get(0));
				}
			}
		}
		return gidlist;
	}

	/**
	 * 创建王城宝箱奖励
	 *
	 * @param rewdata
	 * @return
	 */
	public FruitReward createFruitreward(Q_spirittree_pack_conBean rewdata) {
		if (rewdata != null) {
			FruitReward fruitreward = new FruitReward();
			fruitreward.setNum(rewdata.getQ_item_num());
			fruitreward.setSum(rewdata.getQ_item_num());
			fruitreward.setItemModelid(rewdata.getQ_item_id());
			fruitreward.setIdx(rewdata.getQ_id());
			fruitreward.setStrenglevel(rewdata.getQ_streng_level());	//产生道具属性
			fruitreward.setLosttime(rewdata.getQ_existtime());
			boolean isbidn = false;
			if (rewdata.getQ_is_binding() == 1) {
				isbidn = true;
			}
			fruitreward.setBind(isbidn);
			if (rewdata.getQ_addProperty_num() > 0 && rewdata.getQ_item_id() > 0) {
				FruitAppendManager.getInstance().buildAppend(fruitreward, rewdata.getQ_addProperty_num());
			}
			if (fruitreward.getItemModelid() == 0) {
				log.error("创建王城宝箱奖励=0");
			}
			return fruitreward;
		}
		return null;
	}

	/**
	 * 选择奖励,0到9自选，不能重复选
	 *
	 * @param player
	 * @param msg
	 */
	public void reqKingCityChestSelectToGame(Player player, ReqKingCityChestSelectToGameMessage message) {
		if (player == null) {
			return;
		}
		List<Integer> idxlist = player.getKingCityChest().getKingcitychestidx();
		List<FruitReward> rewardlist = player.getKingCityChest().getKingcitychestlist();

		if (rewardlist.isEmpty()) {
			return;
		}


		if (idxlist.size() >= 1) {	//现在只开放一次选择
			return;
		}

		int rnd = 0;
		List<Integer> rndlist = new ArrayList<Integer>();
		for (FruitReward fruitReward : rewardlist) {
			Q_spirittree_pack_conBean fruitdata = getpackcondata().get(fruitReward.getIdx());
			if (fruitdata == null) {
				rndlist.add(100);	//数据库改变后，默认值
			} else {
				rndlist.add(fruitdata.getQ_selected_rnd());
			}
		}
		rnd = RandomUtils.randomIndexByProb(rndlist);

		idxlist.add(rnd);
		FruitReward fruitReward = rewardlist.remove(rnd);

		if (idxlist.size() == 1) {	//在这里进行传送，清除奖励数据
			player.getKingCityChest().getKingcitychestlist().clear();
			player.getKingCityChest().getKingcitychestidx().clear();
		}

		ResKingCityChestSelectToClientMessage sendMessage = new ResKingCityChestSelectToClientMessage();
		sendMessage.setNum((byte) idxlist.size());
		sendMessage.setIteminfo(getItemInfo(fruitReward));
		MessageUtil.tell_player_message(player, sendMessage);

		giveRewarded(player, fruitReward, 0);
		ManagerPool.playerManager.savePlayer(player);
	}

	/**
	 * 得到副本奖励 type = 0翻牌奖励，1通关固定奖励
	 *
	 * @param msg
	 */
	public void giveRewarded(Player player, FruitReward fruitReward, int type) {
		String rewardedname = ResManager.getInstance().getString("王城宝箱奖励");
		int id = fruitReward.getItemModelid();
		long action = Config.getId();
		//-1铜币，-2元宝，-3真气，-4经验  -5礼金
		if (fruitReward.getNum() == 0) {
			return;
		}
		boolean issuccess = true;
		List<Item> createItems = new ArrayList<Item>();
		String itemname = "";
		if (id == -1) {
			itemname = ResManager.getInstance().getString("铜币");
			if (player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.KINGCITY_MONEY, action)) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}

		} else if (id == -2) {
//			itemname = ResManager.getInstance().getString("元宝");
//			if (player != null && ManagerPool.backpackManager.addGold(player, fruitReward.getNum(), Reasons.KINGCITY_YUANBAO, action)) {
//				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
//			} else {
//				issuccess = false;
//			}
		} else if (id == -3) {
			itemname = ResManager.getInstance().getString("真气");
			if (player != null) {
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(),AttributeChangeReason.COUNTRY_BOX);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			}
		} else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if (player != null) {
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(),AttributeChangeReason.COUNTRY_BOX);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			}
		} else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if (player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.KINGCITY_BIND_YUANBAO, action)) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			}
		} else if (id > 0) {
			Q_itemBean itemMode = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitReward.getItemModelid());
			if (itemMode != null) {
				itemname = itemMode.getQ_name();
				createItems = Item.createItems(fruitReward.getItemModelid(), fruitReward.getNum(), fruitReward.isBind(), ((fruitReward.getLosttime() == 0) ? fruitReward.getLosttime() : (System.currentTimeMillis() + fruitReward.getLosttime() * 1000)), fruitReward.getStrenglevel(), null);
				List<FruitRewardAttr> attrs = fruitReward.getFruitRewardAttrslist();
				//写入属性
				if (itemMode.getQ_type() == ItemTypeConst.EQUIP) {
					if (attrs.size() > 0) {
						for (Item item : createItems) {
							Equip equip = (Equip) item;
							for (FruitRewardAttr attr : attrs) {
								Attribute attribute = new Attribute();
								attribute.setType(attr.getType());
								attribute.setValue(attr.getValue());
								equip.getAttributes().add(attribute);
							}
						}
					}
				}

				if (player != null && ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.KINGCITY_ITEM, action);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})。"), rewardedname, itemMode.getQ_name(), fruitReward.getNum() + "");
				} else {
					issuccess = false;
				}
			} else {
				log.error(rewardedname + "道具item不存在：[" + id + "]");
			}
		} else {
			log.error(rewardedname + "ID类型未知：[" + id + "]");
		}

		if (issuccess == false) {
			itemname = itemname + "(" + fruitReward.getNum() + ")";
			if (id > 0) {
				ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), rewardedname + ":" + itemname, (byte) 1, 0, createItems);
			} else {
				if (id == -1) {	//铜币
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createMoney(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), rewardedname + ":" + itemname, (byte) 1, 0, createItems2);
//				} else if (id == -2) {	//元宝
//					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, "系统邮件", rewardedname + ":" + itemname, (byte) 2, fruitReward.getNum(), new ArrayList<Item>());
				} else if (id == -3) {	//真气
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createZhenQi(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), rewardedname + ":" + itemname, (byte) 1, 0, createItems2);
				} else if (id == -4) {	//经验
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createExp(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), rewardedname + ":" + itemname, (byte) 1, 0, createItems2);
				} else if (id == -5) {	//礼金
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createBindGold(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("系统邮件"), rewardedname + ":" + itemname, (byte) 1, 0, createItems2);
				}
			}
			if (player != null) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, java.text.MessageFormat.format(ResManager.getInstance().getString("由于您的包裹已满，{0}：{1} 已经通过邮件发送给您。"), new Object[] {rewardedname, itemname}));
			}
		}
	}
}
