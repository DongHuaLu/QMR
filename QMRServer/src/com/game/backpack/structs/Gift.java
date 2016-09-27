package com.game.backpack.structs;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.country.manager.CountryAwardManager;
import com.game.data.bean.Q_giftBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.gift.manager.GiftManager;
import com.game.gift.message.ReqGetGiftItemsToServerMessage;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.util.TimerUtil;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Gift extends Item {

	/**
	 *
	 */
	private static final long serialVersionUID = -8680091763726841301L;
	private List<Item> ownItems = new ArrayList<Item>();	//礼包所有的物品列表
	private int money;					//铜币 -1
	private int gold;					//元宝 -2
	private int zhenqi;					//真气 -3
	private int exp;					//经验 -4
	private int bindgold;					//礼金 -5
	private int fightspirit;				//战魂 -6
	private int rank;					//军功 -7

	public List<Item> getOwnItems() {
		return ownItems;
	}

	public void setOwnItems(List<Item> ownItems) {
		this.ownItems = ownItems;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}

	public int getBindgold() {
		return bindgold;
	}

	public void setBindgold(int bindgold) {
		this.bindgold = bindgold;
	}

	public int getFightspirit() {
		return fightspirit;
	}

	public void setFightspirit(int fightspirit) {
		this.fightspirit = fightspirit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public void use(Player player, String... parameters) {//TODO 一些礼包字段还没解析
		int num = 1;
		if (parameters != null && parameters.length >= 1) {
			num = Integer.parseInt(parameters[0]);
		}
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(getItemModelId());
		if (q_itemBean != null) {
			if (player.getLevel() >= q_itemBean.getQ_level()) {
				if (player.getSex() == q_itemBean.getQ_sex() || q_itemBean.getQ_sex() == 0) {
					if (q_itemBean.getQ_gift() != 0) {
						if (q_itemBean.getQ_gift() == CountryAwardManager.KINGCITYCHEST) {
							if (num > 1) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("王城宝箱不能批量使用！"));
								num = 1;
							}
							CountryAwardManager.getInstance().createFruitRewardList(player, CountryAwardManager.KINGCITYCHESTTYPE);
							//CountryAwardManager.getInstance().innerKingCityChestPanelShow(player);//这个是显示面板给东西
							CountryAwardManager.getInstance().reqKingCityChestSelectToGame(player, null);//这个是直接给东西
							BackpackManager.getInstance().removeItem(player, this, num, Reasons.CARD_GIFT_REMOVE, Config.getId());
							MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您的王城宝箱打开成功，获得了物品"));
						} else {
							boolean boMoreUse = (q_itemBean.getQ_whether_batch() == 1) ? true : false;
							boolean bohcran = false;
							boolean boran = false;
							HashMap<Integer, Integer> itemstrMap = new HashMap<Integer, Integer>();
							for (int k = 0; k < num; k++) {
								if (getOwnItems().isEmpty()) {
									Q_giftBean q_giftBean = DataManager.getInstance().q_giftContainer.getMap().get(q_itemBean.getQ_gift());
									if (q_giftBean != null) {
										//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
										String[] gift_dataStrings = q_giftBean.getQ_gift_data().split(";");
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
															boMoreUse = true;
															pushItemStringMap(itemstrMap, itemid, itemnum);
															switch (itemid) {
																case -1: {
																	Item moneyItem = Item.createMoney(itemnum);
																	if (moneyItem != null) {
																		getOwnItems().add(moneyItem);
																		setMoney(itemnum);
																	}
																}
																break;
																case -2: {
																	Item goldItem = Item.createGold(itemnum, true);
																	if (goldItem != null) {
																		getOwnItems().add(goldItem);
																		setGold(itemnum);
																	}
																}
																break;
																case -3: {
																	setZhenqi(itemnum);
																}
																break;
																case -4: {
																	setExp(itemnum);
																}
																break;
																case -5: {
																	setBindgold(itemnum);
																}
																break;
																case -6: {
																	setFightspirit(itemnum);
																}
																break;
																case -7: {
																	setRank(itemnum);
																}
																break;
																default: {
																	
																	if(losttime > 0 && losttime < 2100000000){
																		losttime =  System.currentTimeMillis() + losttime * 1000;
																	}
																	
																	List<Item> items = Item.createItems(itemid, itemnum, bind,losttime , gradenum, append);
																	if (!items.isEmpty()) {
																		getOwnItems().addAll(items);
																	}
																}
																break;
															}
														}
													}
												}
											}
										}
										//互斥随机道具（几率,道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
										String[] hc_ran_gift_dataStrings = q_giftBean.getQ_huchi_random_gift_data().split(";");
										if (hc_ran_gift_dataStrings.length != 0) {
											List<Integer> randomIntegers = new ArrayList<Integer>();
											for (int i = 0; i < hc_ran_gift_dataStrings.length; i++) {
												String hc_ran_gift_item = hc_ran_gift_dataStrings[i];
												if (hc_ran_gift_item != null && !hc_ran_gift_item.isEmpty()) {
													String[] itemdataStrings = hc_ran_gift_item.split(",");
													if (itemdataStrings.length >= 3) {
														int randomcof = Integer.valueOf(itemdataStrings[0]);
														randomIntegers.add(randomcof);
													}
												}
											}
											int ranidx = RandomUtils.randomIndexByProb(randomIntegers);
											if (ranidx != -1) {
												String hc_ran_gift_item = hc_ran_gift_dataStrings[ranidx];
												if (hc_ran_gift_item != null && !hc_ran_gift_item.isEmpty()) {
													String[] itemdataStrings = hc_ran_gift_item.split(",");
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
																case 1: {
																	itemid = Integer.valueOf(item_data);
																}
																break;
																case 2: {
																	itemnum = Integer.valueOf(item_data);
																}
																break;
																case 3: {
																	sex = Integer.valueOf(item_data);
																}
																break;
																case 4: {
																	int bindidx = Integer.valueOf(item_data);
																	bind = (bindidx == 1) ? true : false;
																}
																break;
																case 5: {
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
																case 6: {
																	gradenum = Integer.valueOf(item_data);
																}
																break;
																case 7: {
																	append = Integer.valueOf(item_data);
																}
																break;
															}
														}
													}
													if (itemdataStrings.length >= 3) {
														if (itemid != 0 && itemnum != 0) {
															if (sex == 0 || sex == player.getSex()) {
																boMoreUse = false;
																bohcran = true;
																pushItemStringMap(itemstrMap, itemid, itemnum);
																switch (itemid) {
																	case -1: {
																		Item moneyItem = Item.createMoney(itemnum);
																		if (moneyItem != null) {
																			getOwnItems().add(moneyItem);
																			setMoney(itemnum);
																		}
																	}
																	break;
																	case -2: {
																		Item goldItem = Item.createGold(itemnum, true);
																		if (goldItem != null) {
																			getOwnItems().add(goldItem);
																			setGold(itemnum);
																		}
																	}
																	break;
																	case -3: {
																		setZhenqi(itemnum);
																	}
																	break;
																	case -4: {
																		setExp(itemnum);
																	}
																	break;
																	case -5: {
																		setBindgold(itemnum);
																	}
																	break;
																	case -6: {
																		setFightspirit(itemnum);
																	}
																	break;
																	case -7: {
																		setRank(itemnum);
																	}
																	break;
																	default: {
																		if(losttime > 0 && losttime < 2100000000){
																			losttime =  System.currentTimeMillis() + losttime * 1000;
																		}
																		List<Item> items = Item.createItems(itemid, itemnum, bind, losttime, gradenum, append);
																		if (!items.isEmpty()) {
																			getOwnItems().addAll(items);
																		}
																	}
																	break;
																}
															}
														}
													}
												}
											}
										}
										//随机道具（几率,道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
										String[] ran_gift_dataStrings = q_giftBean.getQ_random_gift_data().split(";");
										if (ran_gift_dataStrings.length != 0) {
											for (int i = 0; i < ran_gift_dataStrings.length; i++) {
												String ran_gift_item = ran_gift_dataStrings[i];
												if (ran_gift_item != null && !ran_gift_item.isEmpty()) {
													String[] itemdataStrings = ran_gift_item.split(",");
													if (itemdataStrings.length >= 3) {
														int randomcof = Integer.valueOf(itemdataStrings[0]);
														if (randomcof >= RandomUtils.random(10000)) {
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
																		case 1: {
																			itemid = Integer.valueOf(item_data);
																		}
																		break;
																		case 2: {
																			itemnum = Integer.valueOf(item_data);
																		}
																		break;
																		case 3: {
																			sex = Integer.valueOf(item_data);
																		}
																		break;
																		case 4: {
																			int bindidx = Integer.valueOf(item_data);
																			bind = (bindidx == 1) ? true : false;
																		}
																		break;
																		case 5: {
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
																		case 6: {
																			gradenum = Integer.valueOf(item_data);
																		}
																		break;
																		case 7: {
																			append = Integer.valueOf(item_data);
																		}
																		break;
																	}
																}
															}
															if (itemdataStrings.length >= 3) {
																if (itemid != 0 && itemnum != 0) {
																	if (sex == 0 || sex == player.getSex()) {
																		boMoreUse = false;
																		boran = true;
																		pushItemStringMap(itemstrMap, itemid, itemnum);
																		switch (itemid) {
																			case -1: {
																				Item moneyItem = Item.createMoney(itemnum);
																				if (moneyItem != null) {
																					getOwnItems().add(moneyItem);
																					setMoney(itemnum);
																				}
																			}
																			break;
																			case -2: {
																				Item goldItem = Item.createGold(itemnum, true);
																				if (goldItem != null) {
																					getOwnItems().add(goldItem);
																					setGold(itemnum);
																				}
																			}
																			break;
																			case -3: {
																				setZhenqi(itemnum);
																			}
																			break;
																			case -4: {
																				setExp(itemnum);
																			}
																			break;
																			case -5: {
																				setBindgold(itemnum);
																			}
																			break;
																			case -6: {
																				setFightspirit(itemnum);
																			}
																			break;
																			case -7: {
																				setRank(itemnum);
																			}
																			break;
																			default: {
																				if(losttime > 0 && losttime < 2100000000){
																					losttime =  System.currentTimeMillis() + losttime * 1000;
																				}
																				List<Item> items = Item.createItems(itemid, itemnum, bind, losttime, gradenum, append);
																				if (!items.isEmpty()) {
																					getOwnItems().addAll(items);
																				}
																			}
																			break;
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
										if (q_giftBean.getQ_gift_money() != 0) {
											Item moneyItem = Item.createMoney(q_giftBean.getQ_gift_money());
											if (moneyItem != null) {
												getOwnItems().add(moneyItem);
												setMoney(q_giftBean.getQ_gift_money());
											}
											boMoreUse = true;
											pushItemStringMap(itemstrMap, -1, q_giftBean.getQ_gift_money());
										}
										if (q_giftBean.getQ_gift_gold() != 0) {
											Item goldItem = Item.createGold(q_giftBean.getQ_gift_gold(), true);
											if (goldItem != null) {
												getOwnItems().add(goldItem);
												setGold(q_giftBean.getQ_gift_gold());
											}
											boMoreUse = true;
											pushItemStringMap(itemstrMap, -5, q_giftBean.getQ_gift_gold());
										}
										if (isEmpty()) {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包没有数据"));
											return;
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包没有数据"));
										return;
									}
								}
								ReqGetGiftItemsToServerMessage sendMessage = new ReqGetGiftItemsToServerMessage();
								sendMessage.setGiftid(this.getId());
								if (!GiftManager.getInstance().reqGetGiftItemsToServer(player, sendMessage, 1)) {
									return;
								}
								if (bohcran || boran) {
									clear();
									bohcran = false;
									boran = false;
								}
								if ((q_itemBean.getQ_whether_batch() == 0 || !boMoreUse) && num != 1) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包不能批量使用！"));
									MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您的礼包打开成功，获得了物品 %s"), getItemString(itemstrMap)));
									return;
								}
							}
							MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您的礼包打开成功，获得了物品 %s"), getItemString(itemstrMap)));
//						ResSendGiftItemsToClientMessage sendMessage = new ResSendGiftItemsToClientMessage();
//						for (int i = 0; i < ownItems.size(); i++) {
//							Item item = ownItems.get(i);
//							if (item != null) {
//								sendMessage.getItems().add(item.buildItemInfo());
//							}
//						}
//						MessageUtil.tell_player_message(player, sendMessage);
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该礼包没有数据"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("性别不符，不能打开该礼包"));
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("等级不够，不能打开该礼包"));
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有该物品模板"));
		}
	}

	@Override
	public void unuse(Player player, String... parameters) {
	}

	public void pushItemStringMap(HashMap<Integer, Integer> itemstrMap, int itemid, int itemnum) {
		if (itemstrMap != null) {
			if (itemstrMap.containsKey(itemid)) {
				int oldvalue = itemstrMap.get(itemid);
				oldvalue = oldvalue + itemnum;
				itemstrMap.put(itemid, oldvalue);
			} else {
				itemstrMap.put(itemid, itemnum);
			}
		}
	}

	public String getItemString(HashMap<Integer, Integer> itemstrMap) {
		String itemString = "";
		if (itemstrMap != null) {
			Iterator<Entry<Integer, Integer>> iterator = itemstrMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Integer, Integer> entry = iterator.next();
				int itemid = entry.getKey();
				int itemnum = entry.getValue();
				Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemid);
				if (q_itemBean != null) {
					if (itemString.equalsIgnoreCase("")) {
						itemString = String.format("%s*%d", q_itemBean.getQ_name(), itemnum);
					} else {
						if (itemString.length() < 64) {
							itemString = itemString + String.format(",%s*%d", q_itemBean.getQ_name(), itemnum);
							if (itemString.length() >= 64) {
								itemString = itemString + ResManager.getInstance().getString("等物品");
							}
						}
					}
				}
			}
		}
		return itemString;
	}

	public boolean isEmpty() {
		if (getOwnItems().isEmpty() && getGold() == 0 && getMoney() == 0 && getExp() == 0 && getZhenqi() == 0 && getBindgold() == 0 && getFightspirit() == 0 && getRank() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		getOwnItems().clear();
		setGold(0);
		setMoney(0);
		setExp(0);
		setZhenqi(0);
		setBindgold(0);
		setFightspirit(0);
		setRank(0);
	}
}
