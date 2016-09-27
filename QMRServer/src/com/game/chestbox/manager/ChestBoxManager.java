package com.game.chestbox.manager;

import com.alibaba.fastjson.JSON;
import com.game.activities.script.IBaseActivityScript;
import com.game.arrow.manager.ArrowManager;
import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.GoodsAttribute;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.chat.bean.GoodsInfoRes;
import com.game.chestbox.log.ChestBoxLog;
import com.game.chestbox.message.*;
import com.game.chestbox.structs.ChestGridData;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_spirittree_kiwiBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.script.IScript;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.spirittree.manager.FruitAppendManager;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * 宝箱玩法管理类
 *
 * @author 杨鸿岚
 */
public class ChestBoxManager {

	private Logger log = Logger.getLogger(ChestBoxManager.class);
	private Logger faillog = Logger.getLogger("ChestBoxData");
	private static Object obj = new Object();
	//后台管理类实例
	private static ChestBoxManager manager;

	private ChestBoxManager() {
	}

	public static ChestBoxManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ChestBoxManager();
			}
		}
		return manager;
	}
	//------------------------------成员变量-------------------------------//
	private static int ADDDROPINCOF = 1;		//轮盘外圈物品落入倾向概率增幅参数：1
	private static int DEFDROPINCOF = 1;		//默认轮盘外圈落入位置概率参数：1
	private static int DROPGRIDNUM = 3;		//倾向格子数：3（最大为12）
	private static int OPENCHESTBOXNEEDYUANBAO = 40;//开宝箱需要元宝
	private static int OPENCHESTBOXNEEDITEM = 9108;	//开宝箱需要物品

	public static int getADDDROPINCOF() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.CHESTBOX_ADDDROPINCOF.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.CHESTBOX_ADDDROPINCOF.getValue()).getQ_int_value();
		} else {
			return ADDDROPINCOF;
		}
	}

	public static int getDEFDROPINCOF() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.CHESTBOX_DEFDROPINCOF.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.CHESTBOX_DEFDROPINCOF.getValue()).getQ_int_value();
		} else {
			return DEFDROPINCOF;
		}
	}

	public static int getDROPGRIDNUM() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.CHESTBOX_DROPGRIDNUM.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.CHESTBOX_DROPGRIDNUM.getValue()).getQ_int_value();
		} else {
			return DROPGRIDNUM;
		}
	}

	public static int getOPENCHESTBOXNEEDYUANBAO() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.CHESTBOX_OPENCHESTBOXNEEDYUANBAO.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.CHESTBOX_OPENCHESTBOXNEEDYUANBAO.getValue()).getQ_int_value();
		} else {
			return OPENCHESTBOXNEEDYUANBAO;
		}
	}

	public static int getOPENCHESTBOXNEEDITEM() {
		if (DataManager.getInstance().q_globalContainer.getMap().containsKey(CommonConfig.CHESTBOX_OPENCHESTBOXNEEDITEM.getValue())) {
			return DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.CHESTBOX_OPENCHESTBOXNEEDITEM.getValue()).getQ_int_value();
		} else {
			return OPENCHESTBOXNEEDITEM;
		}
	}
	private static int INSIDE = 1;			//内圈
	private static int OUTSIDE = 2;			//外圈
	private static int CHESTBOXNUM = 12;		//随机出的奖励物品数
	private static int INSPECIAL_CHOOSETYPE = 6;	//内圈特殊选择类型6
	private static int INNORMAL_CHOOSETYPE = 7;	//内圈普通选择类型7
	private static int OUTSPECIAL_CHOOSETYPE = 8;	//外圈特殊选择类型8
	private static int OUTNORMAL_CHOOSETYPE = 9;	//外圈普通选择类型9
	//------------------------------成员函数-------------------------------//

	/*
	 * 获得宝箱数据里面的格子物品数据 gridtype为内外圈类型，grididx为格子编号
	 */
	public ChestGridData getChestGridData(Player player, int gridType, int gridIdx) {
		if (player == null) {
			log.error(String.format("玩家为NULL,获得宝箱数据里面的格子物品数据 gridtype为[%d]，grididx为[%d]", gridType, gridIdx));
			return null;
		}
		ChestGridData chestGridData = null;
		if (gridType == INSIDE) {
			chestGridData = player.getChestBoxData().getIngridmap().get(String.valueOf(gridIdx));
		} else if (gridType == OUTSIDE) {
			chestGridData = player.getChestBoxData().getOutgridmap().get(String.valueOf(gridIdx));
		} else {
			log.error(String.format("gridType错误,获得宝箱数据里面的格子物品数据 gridtype为[%d]，grididx为[%d]", gridType, gridIdx));
			return null;
		}
		if (chestGridData == null) {
			chestGridData = new ChestGridData();
			chestGridData.setGridtype(gridType);
			chestGridData.setGrididx(gridIdx);
			setChestGridData(player, gridType, chestGridData);
		}
		return chestGridData;
	}

	/*
	 * 更新或添加宝箱数据里面的格子物品数据 gridtype为内外圈类型，chestGridData为格子物品数据
	 */
	public void setChestGridData(Player player, int gridType, ChestGridData chestGridData) {
		if (chestGridData == null) {
			log.error(String.format("chestGridData为NULL,更新或添加宝箱数据里面的格子物品数据 gridtype为[%d] player为[%s]", gridType, player == null ? "NULL" : String.valueOf(player.getId())));
			return;
		}
		if (player == null) {
			log.error(String.format("玩家为NULL,更新或添加宝箱数据里面的格子物品数据 gridtype为[%d] chestGridData为[%s]", gridType, chestGridData == null ? "NULL" : JSON.toJSONString(chestGridData.toInfo())));
			return;
		}
		if (gridType == INSIDE) {
			player.getChestBoxData().getIngridmap().put(String.valueOf(chestGridData.getGrididx()), chestGridData);
		} else if (gridType == OUTSIDE) {
			player.getChestBoxData().getOutgridmap().put(String.valueOf(chestGridData.getGrididx()), chestGridData);
		} else {
			log.error(String.format("gridType错误,更新或添加宝箱数据里面的格子物品数据 gridtype为[%d] player为[%s][%s] chestGridData为[%s]", gridType, player.getName(), String.valueOf(player.getId()), JSON.toJSONString(chestGridData.toInfo())));
		}
	}

	/*
	 * 掉线或退出时处理宝箱物品，自动领取宝箱奖励物品（现在改为上线调用，顶号才能调用到）
	 */
	public void quitChestGridData(Player player) {
		if (player == null) {
			log.error(String.format("掉线或退出时处理宝箱物品，自动领取宝箱奖励物品,玩家不存在"));
			return;
		}
		if (player.getChestBoxData().getInchooseidx() == -1) {
			//log.error(String.format("您没有选中奖励物品，无法取得奖励！player[%s][%s]", player.getName(), String.valueOf(player.getId())));
			return;
		}
		ChestGridData getGridData = getChestGridData(player, INSIDE, player.getChestBoxData().getInchooseidx());
		if (getGridData == null) {
			log.error(String.format("掉线或退出时处理宝箱物品,您没有选中奖励物品，无法取得奖励！player[%s][%s]", player.getName(), String.valueOf(player.getId())));
			return;
		}
		if (getGridData.getCurFruitReward() == null) {
			log.error(String.format("掉线或退出时处理宝箱物品,您没有选中奖励物品，无法取得奖励！player[%s][%s]", player.getName(), String.valueOf(player.getId())));
			return;
		}
		giveRewarded(player, getGridData.getCurFruitReward(), false, ResManager.getInstance().getString("亲爱的玩家由于上次在轮盘转动过程中您意外掉线或退出，系统已经自动帮您完成轮盘抽取物品，并将奖励以邮件附件形式发给您，请及时领取。"));
		if (!getGridData.getFruitRewardlist().isEmpty()) {
			Iterator<FruitReward> iterator = getGridData.getFruitRewardlist().iterator();
			while (iterator.hasNext()) {
				FruitReward fruitReward = iterator.next();
				if (fruitReward != null) {
					giveRewarded(player, fruitReward, false, ResManager.getInstance().getString("亲爱的玩家由于上次在轮盘转动过程中您意外掉线或退出，系统已经自动帮您完成轮盘抽取物品，并将奖励以邮件附件形式发给您，请及时领取。"));
				}
			}
		}
		setChestBoxLog(player, 5, getGridData);
		//getGridData.setCurFruitReward(null);
		getGridData.getFruitRewardlist().clear();//奖励给出后，清除物品，重新生成新的物品
		player.getChestBoxData().setInchooseidx(-1);
		player.getChestBoxData().setOutchooseidx(-1);
		if (player.getChestBoxData().getOpennum() == 0) {//首次开，用特殊规则
			createFruitRewardList(player, INSIDE, INSPECIAL_CHOOSETYPE);
			createFruitRewardList(player, OUTSIDE, OUTSPECIAL_CHOOSETYPE);
		} else {
			createFruitRewardList(player, INSIDE, INNORMAL_CHOOSETYPE);
			createFruitRewardList(player, OUTSIDE, OUTNORMAL_CHOOSETYPE);
		}
		log.error(String.format("掉线或退出时处理宝箱物品,自动领取宝箱奖励物品,成功！player[%s][%s]", player.getName(), String.valueOf(player.getId())));
		setChestBoxLog(player, 4, null);
	}

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
	 * 筛选并设置奖励列表
	 *
	 * @param player
	 * @param type
	 * @return
	 */
	public void createFruitRewardList(Player player, int gridType, int type) {
		if (gridType == INSIDE) {
			Iterator<ChestGridData> iterator = player.getChestBoxData().getIngridmap().values().iterator();
			while (iterator.hasNext()) {
				ChestGridData chestGridData = iterator.next();
				if (chestGridData != null) {
					chestGridData.setCurFruitReward(null);
				}
			}
		} else if (gridType == OUTSIDE) {
			Iterator<ChestGridData> iterator = player.getChestBoxData().getOutgridmap().values().iterator();
			while (iterator.hasNext()) {
				ChestGridData chestGridData = iterator.next();
				if (chestGridData != null) {
					chestGridData.setCurFruitReward(null);
				}
			}
		} else {
			log.error(String.format("幸运轮盘筛选并设置奖励列表，gridType错误 player[%s][%s] gridType=[%d]", player.getName(), String.valueOf(player.getId()), gridType));
			return;
		}
		List<Integer> grouplist = randomGroup(player, type);//类型为
		log.error(String.format("筛选并设置奖励列表 player=[%s][%s],type=[%d],gridType=[%d],grouplist=[%d]=[%s]", player.getName(), String.valueOf(player.getId()), type, gridType, grouplist.size(), JSON.toJSONString(grouplist)));
		HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
		int grididx = 0;
		List<FruitReward> fruitRewardlist = new ArrayList<FruitReward>();
		for (Integer gid : grouplist) {
			if (grididx >= CHESTBOXNUM) {
				break;
			}
			List<Integer> tmpidxs = new ArrayList<Integer>(); // 储存符合条件的道具奖励索引
			List<Integer> tmprnds = new ArrayList<Integer>(); //选中道具的几率值
			Iterator<Map.Entry<Integer, Q_spirittree_pack_conBean>> it = rewarddata.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<Integer, Q_spirittree_pack_conBean> pack = it.next();
				Q_spirittree_pack_conBean data = pack.getValue();
				if (gid == data.getQ_packet_id()) {
					if (player.getLevel() >= data.getQ_need_level() && player.getLevel() <= data.getQ_exclude_level()) {
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
				fruitRewardlist.add(fruitreward);//加入打乱列表
			} else {
				log.error(String.format("tmprnds.size=%d=groupid=%d", tmprnds.size(), gid));
			}
			grididx++;
		}
		//随机打乱
		for (int i = 0; i < CHESTBOXNUM; i++) {
			ChestGridData chestGridData = getChestGridData(player, gridType, i);
			if (chestGridData != null) {
				if (!fruitRewardlist.isEmpty()) {
					FruitReward remove = fruitRewardlist.remove(RandomUtils.random(fruitRewardlist.size()));
					chestGridData.setCurFruitReward(remove);
				}
				if (chestGridData.getCurFruitReward() == null) {
					log.error(String.format("player[%s][%s] chestGridData=[%s]", player.getName(), String.valueOf(player.getId()), JSON.toJSONString(chestGridData.toInfo())));
				}
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
			if (kiwiBean.getQ_type() == type) {
				if (player.getLevel() >= kiwiBean.getQ_need_level() && player.getLevel() <= kiwiBean.getQ_exclude_level()) {
					int num = 0;
					if (numMap.containsKey(kiwiBean.getQ_packet_id())) {
						num = numMap.get(kiwiBean.getQ_packet_id());
					}

					for (int i = 0; i < kiwiBean.getQ_check_num(); i++) {
						if (RandomUtils.isGenerate2(10000, kiwiBean.getQ_arise_rnd())) {
							if (num < kiwiBean.getQ_check_num()) {	//复选
								if (gidlist.size() >= CHESTBOXNUM) {
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

		if (gidlist.size() < CHESTBOXNUM) {
			//组包不足CHESTBOXNUM个，需要补充
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
				int num = CHESTBOXNUM - gidlist.size();//补充数量
				for (int i = 0; i < num; i++) {
					gidlist.add(tmplist.get(0));
				}
			}
		}
		return gidlist;
	}

	/**
	 * 创建幸运轮盘奖励
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
				log.error("创建幸运轮盘奖励=0");
			}
			return fruitreward;
		}
		return null;
	}

	/**
	 * 得到宝箱奖励
	 */
	public void giveRewarded(Player player, FruitReward fruitReward, boolean bogive, String mailnotice) {
		String rewardedname = ResManager.getInstance().getString("幸运轮盘奖励");
		int id = fruitReward.getItemModelid();
		long action = Config.getId();
		//-1铜币，-2元宝，-3真气，-4经验  -5礼金 -6战魂值 -7军功
		if (fruitReward.getNum() == 0) {
			return;
		}
		boolean issuccess = true;
		List<Item> createItems = new ArrayList<Item>();
		String itemname = "";
		if (id == -1) {
			itemname = ResManager.getInstance().getString("铜币");
			if (bogive && player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.CHESTBOX_MONEY, action)) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}

		} else if (id == -2) {
			issuccess = false;
		} else if (id == -3) {
			itemname = ResManager.getInstance().getString("真气");
			if (bogive && player != null) {
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(), AttributeChangeReason.CHESTBOX);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}
		} else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if (bogive && player != null) {
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(), AttributeChangeReason.CHESTBOX);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}
		} else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if (bogive && player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.CHESTBOX_BIND_YUANBAO, action)) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}
		} else if (id == -6) {
			itemname = ResManager.getInstance().getString("战魂");
			if (bogive && player != null && ManagerPool.arrowManager.addFightSpiritNum(player, ArrowManager.FightType_RI, fruitReward.getNum(), true, ArrowReasonsType.CHESTBOX)) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}
		} else if (id == -7) {
			itemname = ResManager.getInstance().getString("军功");
			if (bogive && player != null) {
				ManagerPool.rankManager.addranknum(player, fruitReward.getNum(), RankType.CHESTBOX);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"), rewardedname, itemname, fruitReward.getNum() + "");
			} else {
				issuccess = false;
			}
		} else if (id > 0) {
			Q_itemBean itemMode = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitReward.getItemModelid());
			if (itemMode != null) {
				itemname = itemMode.getQ_name();
				createItems = Item.createItems(fruitReward.getItemModelid(), fruitReward.getNum(), fruitReward.isBind(), ((fruitReward.getLosttime() == 0) ? fruitReward.getLosttime() : (System.currentTimeMillis() + fruitReward.getLosttime() * 1000)), fruitReward.getStrenglevel(), null);
				List<FruitRewardAttr> attrs = fruitReward.getFruitRewardAttrslist();
				//写入属性
				if (itemMode.getQ_type() == ItemTypeConst.EQUIP || itemMode.getQ_type() == ItemTypeConst.HORSEEQUIP) {
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

				if (bogive && player != null && ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.CHESTBOX_ITEM, action);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})。"), rewardedname, itemMode.getQ_name(), fruitReward.getNum() + "");
					//MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})。"), rewardedname, itemMode.getQ_name(), fruitReward.getNum() + "");
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
			boolean bofailMail = true;
			if (id > 0) {
				bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems);
			} else {
				if (id == -1) {	//铜币
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createMoney(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				} else if (id == -2) {	//元宝 
				} else if (id == -3) {	//真气
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createZhenQi(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				} else if (id == -4) {	//经验
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createExp(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				} else if (id == -5) {	//礼金
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createBindGold(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				} else if (id == -6) {	//战魂
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createFightSpirit(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				} else if (id == -7) {	//军功
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createRank(fruitReward.getNum()));
					bofailMail = ManagerPool.mailServerManager.sendSystemMail(player.getId(), null, ResManager.getInstance().getString("幸运轮盘奖励"), mailnotice, (byte) 1, 0, createItems2);
				}
			}
			if (player != null) {
				if (bofailMail) {
					if (bogive) {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, java.text.MessageFormat.format(ResManager.getInstance().getString("由于您的包裹已满，{0}：{1} 已经通过邮件发送给您。"), new Object[]{rewardedname, itemname}));
					}
				} else {
					faillog.error(String.format("幸运轮盘奖励邮件发送错误=玩家[%s][%s]=[%s]", String.valueOf(player.getId()), player.getName(), JSON.toJSONString(getItemInfo(fruitReward))));
				}
			}
		}
		if (player != null) {
			Q_spirittree_pack_conBean pack_conBean = getpackcondata().get(fruitReward.getIdx());
			if (pack_conBean.getQ_notice_type() >= 3) {
				Q_itemBean itemBean = DataManager.getInstance().q_itemContainer.getMap().get(fruitReward.getItemModelid());
				List<GoodsInfoRes> goodsInfoReses = new ArrayList<GoodsInfoRes>();
				GoodsInfoRes goodsInfoRes = new GoodsInfoRes();
				goodsInfoRes.setItemInfo(getItemInfo(fruitReward));
				goodsInfoReses.add(goodsInfoRes);
				if (itemBean.getQ_type() == 1) {//等于1为装备
					if (fruitReward.getStrenglevel() >= 1) {//强化等级大于等于1 \f %s【%s】
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】玩家在幸运轮盘中获得了强化+%d的\f{@}"), player.getName(), fruitReward.getStrenglevel()/*
							 * ,
							 * getQuality(fruitReward),
							 * itemBean.getQ_name()
							 */), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), GuideType.CHESTBOX_GETITEM.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), goodsInfoReses, GuideType.CHESTBOX_GETITEM.getValue());
					} else {
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】玩家在幸运轮盘中获得了\f{@}"), player.getName()/*
							 * ,
							 * getQuality(fruitReward),
							 * itemBean.getQ_name()
							 */), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), GuideType.CHESTBOX_GETITEM.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), goodsInfoReses, GuideType.CHESTBOX_GETITEM.getValue());
					}
				} else {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】玩家在幸运轮盘中获得了\f{@}"), player.getName()/*
						 * , itemname
						 */), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), GuideType.CHESTBOX_GETITEM.getValue()));
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), goodsInfoReses, GuideType.CHESTBOX_GETITEM.getValue());
				}
			}
		}
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
	 * 获得品质 0-白色 1-黄色 2-绿色 3-蓝色 4-紫色
	 *
	 * @return
	 */
	public String getQuality(FruitReward fruitReward) {
		if (fruitReward != null && fruitReward.getFruitRewardAttrslist() != null && fruitReward.getFruitRewardAttrslist().size() > 0) {
			int num = fruitReward.getFruitRewardAttrslist().size();
			if (num < 2) {
				//白色装备
				return ResManager.getInstance().getString("白色");
			} else if (num < 4) {
				//黄色装备
				return ResManager.getInstance().getString("黄色");
			} else if (num == 4) {
				//绿色装备
				return ResManager.getInstance().getString("绿色");
			} else if (num == 5) {
				//蓝色装备
				return ResManager.getInstance().getString("蓝色");
			} else if (num == 6) {
				//紫色装备
				return ResManager.getInstance().getString("紫色");
			}
		}
		return ResManager.getInstance().getString("白色");
	}

	/**
	 * 选择外圈编号
	 *
	 * @see ADDDROPINCOF = 1;	轮盘外圈物品落入倾向概率增幅参数：1 | DEFDROPINCOF
	 * =1;默认轮盘外圈落入位置概率参数：1 | DROPGRIDNUM = 3;	倾向格子数：3（最大为12）
	 */
	public int chooseOutSideToInSide(Player player) {
		int size = player.getChestBoxData().getOutgridmap().size();//获得最大值
		if (player.getChestBoxData().getAdddropinList().isEmpty() || player.getChestBoxData().getAdddropinList().size() != getDROPGRIDNUM()) {//只要没有倾向格子数或者数目不等，都要重新随机出
			player.getChestBoxData().getAdddropinList().clear();
			while (player.getChestBoxData().getAdddropinList().size() != getDROPGRIDNUM()) {
				int idx = 0;
				while (idx < size) {
					if (RandomUtils.random(1, 2) == 1) {
						if (!player.getChestBoxData().getAdddropinList().contains(idx)) {
							player.getChestBoxData().getAdddropinList().add(idx);
						}
					}
					idx++;
					if (player.getChestBoxData().getAdddropinList().size() == getDROPGRIDNUM()) {
						break;
					}
				}
			}
		}
		int rnd = 0;
		List<Integer> rndlist = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			if (player.getChestBoxData().getAdddropinList().contains(i)) {
				rndlist.add(getADDDROPINCOF() + getDEFDROPINCOF());
			} else {
				rndlist.add(getDEFDROPINCOF());
			}
		}
		rnd = RandomUtils.randomIndexByProb(rndlist);
		if (rnd >= 0 && rnd < size) {
			return rnd;
		} else {//没有选中就随机一个值，这种情况几率很小
			return RandomUtils.random(player.getChestBoxData().getOutgridmap().size());
		}
	}

	public void setChestBoxLog(Player player, int type, ChestGridData chestGridData) {
		if (player != null) {
			try {
				ChestBoxLog chestBoxLog = new ChestBoxLog(player, type, chestGridData);
				LogService.getInstance().execute(chestBoxLog);
			} catch (Exception e) {
				log.error(String.format("玩家[%s][%s]幸运轮盘，日志记录[%d]错误", player.getName(), String.valueOf(player.getId()), type));
				log.error(e, e);
			}
		}
	}

	public void sendChooseInfo(Player player, int outtoin) {
		ResChestBoxChooseInfoToClientMessage sendMessage = new ResChestBoxChooseInfoToClientMessage();
		sendMessage.setOpennum(player.getChestBoxData().getOpennum());
		sendMessage.setInchooseidx(player.getChestBoxData().getInchooseidx());
		sendMessage.setOutchooseidx(player.getChestBoxData().getOutchooseidx());
		sendMessage.setChooseOutSideToInSide(outtoin);
		MessageUtil.tell_player_message(player, sendMessage);
	}

	/*
	 * 检查轮盘活动是否开启
	 */
	public boolean checkActivities(Player player) {
		try {
			IScript script = ScriptManager.getInstance().getScript(ScriptEnum.BASEACTIVITIES);
			if (script != null && script instanceof IBaseActivityScript) {
				IBaseActivityScript baseActivities = (IBaseActivityScript) script;
				return baseActivities.checkActivities(player, 100);
			}
		} catch (Exception e) {
			log.error(e, e);
			return false;
		}
		return true;
	}
	//------------------------------消息处理-------------------------------//

	/*
	 * 设置宝箱是否自动开始
	 */
	public void reqChestBoxAutoSetToServer(Player player, ReqChestBoxAutoSetToServerMessage message) {
		if (player == null) {
			log.error(String.format("玩家不存在，消息id[%d]，Role列表[%s]", message.getId(), JSON.toJSONString(message.getRoleId())));
			return;
		}
		//0 不自动 1 自动
		if (message.getAutoset() == 0 || message.getAutoset() == 1) {
			player.getChestBoxData().setAutoopen(message.getAutoset());
		}
		ResChestBoxAutoSetToClientMessage sendMessage = new ResChestBoxAutoSetToClientMessage();
		sendMessage.setAutoset(player.getChestBoxData().getAutoopen());
		MessageUtil.tell_player_message(player, sendMessage);
	}

	/*
	 * 玩家请求打开宝箱轮盘面板
	 */
	public void reqChestBoxShowPanelToServer(Player player, ReqChestBoxShowPanelToServerMessage message) {
		if (player == null) {
			log.error(String.format("玩家不存在，消息id[%d]，Role列表[%s]", message.getId(), JSON.toJSONString(message.getRoleId())));
			return;
		}
		boolean bolog = false;
		if (player.getChestBoxData().getIngridmap().isEmpty()) {
			if (player.getChestBoxData().getOpennum() == 0) {//首次开，用特殊规则
				createFruitRewardList(player, INSIDE, INSPECIAL_CHOOSETYPE);
			} else {
				createFruitRewardList(player, INSIDE, INNORMAL_CHOOSETYPE);
			}
			bolog = true;
		}
		if (player.getChestBoxData().getOutgridmap().isEmpty()) {
			if (player.getChestBoxData().getOpennum() == 0) {//首次开，用特殊规则
				createFruitRewardList(player, OUTSIDE, OUTSPECIAL_CHOOSETYPE);
			} else {
				createFruitRewardList(player, OUTSIDE, OUTNORMAL_CHOOSETYPE);
			}
			bolog = true;
		}
		ResChestBoxInfoToClientMessage sendMessage = new ResChestBoxInfoToClientMessage();
		sendMessage.setChestboxinfo(player.getChestBoxData().toInfo());
		MessageUtil.tell_player_message(player, sendMessage);
		if (bolog) {
			setChestBoxLog(player, 1, null);
		}
	}

	/*
	 * 玩家请求开始转动和停止轮盘
	 */
	public void reqChestBoxOpenToServer(Player player, ReqChestBoxOpenToServerMessage message) {
		if (player == null) {
			log.error(String.format("玩家不存在，消息id[%d]，Role列表[%s]", message.getId(), JSON.toJSONString(message.getRoleId())));
			return;
		}
		if (player.getChestBoxData().getInchooseidx() != -1 || player.getChestBoxData().getOutchooseidx() != -1) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您还有奖励没有领取，不能开启下一轮幸运轮盘。")));
			//sendChooseInfo(player, -1);
			return;
		}
		if (message.getActiontype() != 0 && message.getActiontype() != 1) {
			log.error(String.format("玩家请求开始转动和停止轮盘，动作类型错误[%d]，player[%s][%s]", message.getActiontype(), player.getName(), String.valueOf(player.getId())));
			sendChooseInfo(player, -1);
			return;
		}
		if (player.getChestBoxData().getIngridmap().isEmpty()) {
			log.error(String.format("内圈物品不存在，player[%s][%s]", player.getName(), String.valueOf(player.getId())));
			sendChooseInfo(player, -1);
			return;
		}
		if (player.getChestBoxData().getOutgridmap().isEmpty()) {
			log.error(String.format("外圈物品不存在，player[%s][%s]", player.getName(), String.valueOf(player.getId())));
			sendChooseInfo(player, -1);
			return;
		}
		if (message.getActiontype() == 0) {//扣除40元宝
			if (!checkActivities(player)) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("幸运轮盘活动没有开启，您不能开启幸运轮盘。")));
				sendChooseInfo(player, -1);
				return;
			}
			if (!BackpackManager.getInstance().checkGold(player, getOPENCHESTBOXNEEDYUANBAO())) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您的元宝不足%s，不能开启幸运轮盘。"), String.valueOf(getOPENCHESTBOXNEEDYUANBAO())));
				sendChooseInfo(player, -1);
				return;
			}
			BackpackManager.getInstance().changeGold(player, -getOPENCHESTBOXNEEDYUANBAO(), Reasons.CHESTBOX_DELGOLD, Config.getId());
		} else if (message.getActiontype() == 1) {//扣除对应物品
			if (!BackpackManager.getInstance().removeItem(player, getOPENCHESTBOXNEEDITEM(), 1, Reasons.CHESTBOX_DELITEM, Config.getId())) {
				MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您的物品%s不足，不能开启幸运轮盘。"), BackpackManager.getInstance().getName(getOPENCHESTBOXNEEDITEM())));
				sendChooseInfo(player, -1);
				return;
			}
		}
		
		List<Integer> rndlist = new ArrayList<Integer>();
		Iterator<ChestGridData> iterator;
		//选择外圈
		iterator = player.getChestBoxData().getOutgridmap().values().iterator();
		int outidx = 0;
		while (iterator.hasNext()) {
			ChestGridData chestGridData = iterator.next();
			if (chestGridData != null && chestGridData.getCurFruitReward() != null) {
				Q_spirittree_pack_conBean fruitdata = getpackcondata().get(chestGridData.getCurFruitReward().getIdx());
				if (fruitdata == null) {
					rndlist.add(100);	//数据库改变后，默认值
				} else {
					rndlist.add(fruitdata.getQ_selected_rnd());
				}
			}
		}
		outidx = RandomUtils.randomIndexByProb(rndlist);
		player.getChestBoxData().setOutchooseidx(outidx);
		int chooseOutSideToInSide = chooseOutSideToInSide(player);
		//选完后直接把外圈的东西放入内圈，表现前端做，后面只需要直接给东西就可以
		ChestGridData outChestGridData = getChestGridData(player, OUTSIDE, outidx);
		ChestGridData inChestGridData = getChestGridData(player, INSIDE, chooseOutSideToInSide);
		if (outChestGridData != null && outChestGridData.getCurFruitReward() != null && inChestGridData != null) {
			inChestGridData.getFruitRewardlist().add(outChestGridData.getCurFruitReward());
		}
		player.getChestBoxData().setOpennum(player.getChestBoxData().getOpennum() + 1);
		
		//选择内圈
		rndlist.clear();
		iterator = player.getChestBoxData().getIngridmap().values().iterator();
		int inidx = 0;
		while (iterator.hasNext()) {
			ChestGridData chestGridData = iterator.next();
			if (chestGridData != null && chestGridData.getCurFruitReward() != null) {
				Q_spirittree_pack_conBean fruitdata = getpackcondata().get(chestGridData.getCurFruitReward().getIdx());
				if (fruitdata == null) {
					rndlist.add(100);	//数据库改变后，默认值
				} else {
					rndlist.add(fruitdata.getQ_selected_rnd());
				}
			}
		}
		inidx = RandomUtils.randomIndexByProb(rndlist);
		player.getChestBoxData().setInchooseidx(inidx);
		
		sendChooseInfo(player, chooseOutSideToInSide);
		setChestBoxLog(player, 2, null);
	}

	/*
	 * 玩家请求取得选中内圈格子的物品
	 */
	public void reqChestBoxGetGridItemToServer(Player player, ReqChestBoxGetGridItemToServerMessage message) {
		if (player == null) {
			log.error(String.format("玩家不存在，消息id[%d]，Role列表[%s]", message.getId(), JSON.toJSONString(message.getRoleId())));
			return;
		}
		if (player.getChestBoxData().getInchooseidx() == -1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有选中奖励物品，无法取得奖励！"));
			return;
		}
		ChestGridData getGridData = getChestGridData(player, INSIDE, player.getChestBoxData().getInchooseidx());
		if (getGridData == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有选中奖励物品，无法取得奖励！"));
			return;
		}
		if (getGridData.getCurFruitReward() == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有选中奖励物品，无法取得奖励！"));
			return;
		}
		giveRewarded(player, getGridData.getCurFruitReward(), true, ResManager.getInstance().getString("亲爱的玩家由于上次在轮盘转动过程中您的包裹没有空余位置，所以系统已经将奖励以邮件附件形式发给您，请及时领取。"));
		if (!getGridData.getFruitRewardlist().isEmpty()) {
			Iterator<FruitReward> iterator = getGridData.getFruitRewardlist().iterator();
			while (iterator.hasNext()) {
				FruitReward fruitReward = iterator.next();
				if (fruitReward != null) {
					giveRewarded(player, fruitReward, true, ResManager.getInstance().getString("亲爱的玩家由于上次在轮盘转动过程中您的包裹没有空余位置，所以系统已经将奖励以邮件附件形式发给您，请及时领取。"));
				}
			}
		}
		setChestBoxLog(player, 3, getGridData);
		//getGridData.setCurFruitReward(null);
		getGridData.getFruitRewardlist().clear();//奖励给出后，清除物品，重新生成新的物品
		player.getChestBoxData().setInchooseidx(-1);
		player.getChestBoxData().setOutchooseidx(-1);
		if (player.getChestBoxData().getOpennum() == 0) {//首次开，用特殊规则
			createFruitRewardList(player, INSIDE, INSPECIAL_CHOOSETYPE);
			createFruitRewardList(player, OUTSIDE, OUTSPECIAL_CHOOSETYPE);
		} else {
			createFruitRewardList(player, INSIDE, INNORMAL_CHOOSETYPE);
			createFruitRewardList(player, OUTSIDE, OUTNORMAL_CHOOSETYPE);
		}
		ResChestBoxNewGridInfoToClientMessage sendMessage = new ResChestBoxNewGridInfoToClientMessage();
		Iterator<ChestGridData> iterator = player.getChestBoxData().getIngridmap().values().iterator();
		while (iterator.hasNext()) {
			ChestGridData chestGridData = iterator.next();
			if (chestGridData != null) {
				sendMessage.getNewingridlist().add(chestGridData.toInfo());
			}
		}
		iterator = player.getChestBoxData().getOutgridmap().values().iterator();
		while (iterator.hasNext()) {
			ChestGridData chestGridData = iterator.next();
			if (chestGridData != null) {
				sendMessage.getNewoutgridlist().add(chestGridData.toInfo());
			}
		}
		MessageUtil.tell_player_message(player, sendMessage);
		setChestBoxLog(player, 1, null);
	}
}
