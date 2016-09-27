package com.game.shop.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.buff.structs.AttributeBuff;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffType;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_npcBean;
import com.game.data.bean.Q_shopBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.manager.NpcManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.shop.bean.ShopBuyResultItemBean;
import com.game.shop.bean.ShopItemInfo;
import com.game.shop.log.BuyBackLog;
import com.game.shop.log.GoldBuyBackLog;
import com.game.shop.log.ShopBuyLog;
import com.game.shop.log.ShopSellLog;
import com.game.shop.message.ResBuyItemResultMessage;
import com.game.shop.message.ResNotSaleMessage;
import com.game.shop.message.ResRebuyItemInfosMessage;
import com.game.shop.message.ResRebuySuccessMessage;
import com.game.shop.message.ResSaleSuccessMessage;
import com.game.shop.message.ResShopItemListMessage;
import com.game.shop.script.IShopBuyScript;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;

public class ShopManager {

	private Logger log = Logger.getLogger(ShopManager.class);
	/**
	 * 男女共用类型
	 */
//	private static final int COMMONSEXTYPE=0;
	/**
	 * 0随身商店
	 */
	private static final int SHOP_TYPE_PORTABLE = 0;
	/**
	 * 1NPC商店
	 */
	private static final int SHOP_TYPE_NPC = 1;
	/**
	 * 2元宝道具商城
	 */
//	private static final int SHOP_TYPE_OFICIAL = 2;
	/**
	 * 商品上架
	 */
	private static final String SHOP_SHANGJIA = "0";
	/**
	 * 商品下架
	 */
	private static final String SHOP_XIAJIA = "-1";
	private static final String SHOP_DAZHE = "0";
//	private static final String SHOP_UNDAZHE = "-1";
	/**
	 * 可回收
	 */
	private static final int BUYBACK_ABLE = 1;
	/**
	 * 可丢弃
	 */
//	private static final int DROP_ABLE=1;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//购买骑战兵器标识
	private static String BUY_HORSE_WEAPON = "buyhorseweapon";
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static ShopManager manager;

	private ShopManager() {
	}

	public static ShopManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ShopManager();
			}
		}
		return manager;
	}

	/**
	 * 购买物品
	 *
	 * @param roleId 玩家Id
	 * @param npcId NPC Id
	 * @param selllId 贩卖Id 礼金
	 * @param num 购买数量
	 */
	public void buyItem(Player player, int npcId, int sellId, int num, int costType, int coin, int bindgold, int gold) {
		//数据检查
		if (num <= 0) {
			return;
		}
		Q_shopBean shopItem = ManagerPool.dataManager.q_shopContainer.getMap().get(sellId);
		if (shopItem == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您要购买的商品不存在"));
			return;
		}
		if (num <= 0) {
			log.error("非法请求物品数量" + num + " ROLEid=" + player.getId() + " costType=" + costType);
			return;
		}
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(shopItem.getQ_sell());
		if (model == null) {
			return;
		}
		//NPC检查
		if (shopItem.getQ_shop_type() == SHOP_TYPE_NPC) {
			Q_npcBean npc = ManagerPool.dataManager.q_npcContainer.getMap().get(npcId);
			if (npc == null) {
				log.error("找不到NPC模型ID=" + npcId);
				return;
			}
			if (!NpcManager.checkFunction(npcId, NpcManager.FUNCTION_SHOP)) {
				log.error("该NPC没有此功能");
				return;
			}
			//不出售此物品

			if (!NpcManager.checkShop(npcId, shopItem.getQ_model_id())) {
				//不出售此物品
				log.error("该NPC不出售该物品");
				return;
			}
			//npc距离
			if (!NpcManager.checkDistance(npcId, player.getPosition())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您离NPC太远了"));
				return;
			}
		}
		if (shopItem.getQ_shop_type() == SHOP_TYPE_PORTABLE && player.getLevel() < 10) {
			//10级（不包含）以上开启随身商店
			return;
		}

		if (isXiaJia(shopItem)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您要购买的商品已经下架"));
			return;
		}
		
		//骑战兵器是否已经买过
		if(player.getActivitiesReward().containsKey(BUY_HORSE_WEAPON) && shopItem.getQ_sell()==3017){
			return;
		}
		
		//暗器是否已经买过
		if(shopItem.getQ_sell()==18175){
			HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
			if (weapon != null && weapon.getLayer() > 1) {
				return;
			}
		}
		
		//境界激活道具是否已经购买
		if (player.getRealm().getActivation() >= 2 && shopItem.getQ_sell()==18137) {
			return;
		}
		
		if((shopItem.getQ_sell()==3017 || shopItem.getQ_sell()== 18137 || shopItem.getQ_sell() == 18141) && num > 1){
			return;
		}

//		//已经下架
//		if(!(SHOP_SHANGJIA).equals(shopItem.getQ_rack())){
//			if(((SHOP_XIAJIA).equals(shopItem.getQ_rack()) || !TimeUtil.isNowSatisfiedBy(shopItem.getQ_rack()))){
//				if(!TimeUtil.isOpenServerTimer(shopItem.getQ_openserver_rack())){
//					MessageUtil.notify_player(player,Notifys.ERROR,"很抱歉，您要购买的商品已经下架");
//					return;
//				}
//			}
//		}
		boolean isdaze = isDaZheTime(shopItem);
		long action = Config.getId();
		long losttime = 0;
		//是否绑定
		boolean bind = false;
		try {
			//过期时间
			if (shopItem.getQ_losttime() != null && !("").equals(shopItem.getQ_losttime())) {
				Date date = format.parse(shopItem.getQ_losttime());
				losttime = date.getTime();
			} else if (shopItem.getQ_duration() > 0) {
				losttime = System.currentTimeMillis() + shopItem.getQ_duration() * 1000;
			}
			//是否购买时绑定
			if (shopItem.getQ_buy_bind() == 1) {
				bind = true;
			}
			if (costType == 4) {
				bind = true;
			}
		} catch (ParseException e) {
			log.error(e, e);
		}
		if (num > BackpackManager.getInstance().getAbleAddNum(player, model.getQ_id(), bind, losttime)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需包裹位置不足"));
			return;
		}
		List<Item> createItems = Item.createItems(model.getQ_id(), num, bind, losttime, shopItem.getQ_strengthen(), shopItem.getQ_append());;

		//花费
		int resume = 0;

		if (costType == 1) {
			//允许金币购买
			if ((shopItem.getQ_money_type() & 0x00000001) == 0) {
				return;
			}
			//金币购买
			int price = 0;

			if (shopItem.getQ_coin() > 0) {
				//以商店售价为准
				price = shopItem.getQ_coin();
			} else {
				//以物品表售价为准
				price = model.getQ_buy_price();
			}

			if (isdaze) {
				price = getDaZhePrice(shopItem.getQ_show_coin(), shopItem.getQ_sale_rate());
			}
			if (coin != price) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("商品信息发生变更请重新打开商城面板"));
				return;
			}

			int money = price * num;
			resume = money;
			if (player.getMoney() < money) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需游戏铜币不足"));
				return;
			}
			if (!BackpackManager.getInstance().hasAddSpace(player, createItems)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需包裹位置不足"));
				return;
			}
			boolean changeMoney = ManagerPool.backpackManager.changeMoney(player, -money, Reasons.SHOPBUY, action);
			if (!changeMoney) {
				return;
			}


			ManagerPool.backpackManager.addItems(player, createItems, Reasons.SHOPBUY, action);
			if (num > 1) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]({2})成功，使用{3}铜币"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), num + "", String.valueOf(money));
			} else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]成功，使用{2}铜币"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), String.valueOf(money));
			}
		} else if (costType == 2) {
			//允许元宝购买
			if ((shopItem.getQ_money_type() & 0x00000002) == 0) {
				return;
			}
			int price = shopItem.getQ_gold();
			if (isdaze) {
				price = getDaZhePrice(shopItem.getQ_show_gold(), shopItem.getQ_sale_rate());
			}
			// 这里看看玩家有没有折扣buff
			List<Buff> buffs = ManagerPool.buffManager.getBuffByType(player, BuffType.MALLBUYREDUCE);
			Iterator<Buff> it = buffs.iterator();
			while (it.hasNext()) {
				AttributeBuff buff = (AttributeBuff)it.next();
				int reduceGold = buff.getReduceGold(shopItem.getQ_sell());
				price = price > reduceGold ? price - reduceGold : 0;
			}
			
			if (price != gold) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("商品信息发生变更请重新打开商城面板"));
				return;
			}
			//商城限量购买检查
			if (limitbuy(player,shopItem,num) == false) {
				return;
			}

			int money = price * num;
			resume = money;
			if (!BackpackManager.getInstance().checkGold(player, money)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需元宝不足"));
				return;
			}
			if (num > BackpackManager.getInstance().getAbleAddNum(player, model.getQ_id(), bind, losttime)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需包裹位置不足"));
				return;
			}
			
			if (!BackpackManager.getInstance().hasAddSpace(player, createItems)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需包裹位置不足"));
				return;
			}

			int preCostGold = player.getGold() == null ? 0 : player.getGold().getCostGold();
			if (!BackpackManager.getInstance().changeGold(player, -money, Reasons.YBBUY, action)) {
				return;
			}


			ManagerPool.backpackManager.addItems(player, createItems, Reasons.SHOPBUY, action);
			limitbuyaddnum(player,shopItem,num);
			if (num > 1) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]({2})成功，使用{3}元宝"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), num + "", String.valueOf(money));
			} else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]成功，使用{2}元宝"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), String.valueOf(money));
			}
			//购买成功后检测是否需要刷新商城列表
			if (needRefreshList(preCostGold, player.getGold().getCostGold())) {
				shopItemList(player, 2); // 这里的 2 是数据库中的 q_model_id 商店模型id
			}
		} else if (costType == 4) {
			bind = true;
			//礼金
			if ((shopItem.getQ_money_type() & 0x00000004) == 0) {
				return;
			}
			int price = shopItem.getQ_bindgold();
			if (isdaze) {
				price = getDaZhePrice(shopItem.getQ_show_bindgold(), shopItem.getQ_sale_rate());
			}
			if (price != bindgold) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("商品信息发生变更请重新打开商城面板"));
				return;
			}
			int money = price * num;
			resume = money;
			if (player.getBindGold() < money) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需礼金不足"));
				return;
			}
//			Gold gold = BackpackManager.getInstance().getGold(player);
//			int preCostGold = player.getGold()==null?0:player.getGold().getCostGold();
			if (!BackpackManager.getInstance().hasAddSpace(player, createItems)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("所需包裹位置不足"));
				return;
			}

			boolean changeBindGold = ManagerPool.backpackManager.changeBindGold(player, -money, Reasons.SHOPBUY, action);
			if (!changeBindGold) {
				//扣除操作失败
				return;
			}

			ManagerPool.backpackManager.addItems(player, createItems, Reasons.SHOPBUY, action);
			if (num > 1) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]({2})成功，使用{3}礼金"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), num + "", String.valueOf(money));
			} else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("购买[{1}]成功，使用{2}礼金"), BackpackManager.getInstance().getName(shopItem.getQ_sell()), String.valueOf(money));
			}
//			//购买成功后检测是否需要刷新商城列表
//			if(needRefreshList(preCostGold,player.getGold().getCostGold())){
//				shopItemList(player, 2); // 这里的 2 是数据库中的 q_model_id 商店模型id
//			}
		} else {
			return;
		}
		IShopBuyScript script = (IShopBuyScript) ScriptManager.getInstance().getScript(ScriptEnum.SHOPBUY);
		if (script != null) {
			try {
				script.shopbuy(player, shopItem, createItems, num, costType);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("没有商店购买脚本");
		}
		ResBuyItemResultMessage msg = new ResBuyItemResultMessage();
		msg.setCostType(costType);
		msg.setSellId(sellId);
		for (Item item : createItems) {
			ShopBuyResultItemBean buyitem = new ShopBuyResultItemBean();
			buyitem.setGoodsid(item.getId());
			buyitem.setNum(item.getNum());
			msg.getGoodsinfo().add(buyitem);
		}
		msg.getGoodsinfo();
		MessageUtil.tell_player_message(player, msg);
		
		//骑战兵器加标识
		if(shopItem.getQ_sell()==3017) player.getActivitiesReward().put(BUY_HORSE_WEAPON, String.valueOf(System.currentTimeMillis()));
		
		if (shopItem.getQ_sell()==18137 ) {//设置境界激活道具购买标记
			player.getRealm().setActivation(2);
		}
		
		if (BackpackManager.getInstance().isLog(model.getQ_id())) {
			ShopBuyLog shopBuyLog = new ShopBuyLog();
			shopBuyLog.setUserid(Long.valueOf(player.getUserId()));
			shopBuyLog.setActionId(action);
			shopBuyLog.setRoleid(player.getId());
			shopBuyLog.setNpcid(npcId);
			shopBuyLog.setSellid(sellId);
			shopBuyLog.setNum(num);
			shopBuyLog.setResume(resume);
			shopBuyLog.setCosttype(costType);
			shopBuyLog.setRolelevel(player.getLevel());
			shopBuyLog.setItemmodel(shopItem.getQ_sell());
			shopBuyLog.setItems(JSONserializable.toString(createItems));
			shopBuyLog.setSid(player.getCreateServerId());
			LogService.getInstance().execute(shopBuyLog);
		}
	}

	/**限购检查
	 * @return 
	 * 
	 */
	public boolean limitbuy(Player player , Q_shopBean q_shopBean,int num ){
		if(q_shopBean.getQ_shop_limit() > 0 ){
			String key = q_shopBean.getQ_id() +"_"+q_shopBean.getQ_rack();
			Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(q_shopBean.getQ_sell());
			if(player.getShoplimitmap().containsKey(key)){
				Integer alreadynum = player.getShoplimitmap().get(key);
				if (alreadynum + num > q_shopBean.getQ_shop_limit() ) {
					int surplus = q_shopBean.getQ_shop_limit() - alreadynum;
					MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("『{1}』本次限购数量为{2}件,剩余可购买{3}件"), itemBean.getQ_name(),q_shopBean.getQ_shop_limit()+"",surplus+"");
					return false;
				}
			}else {
				if (q_shopBean.getQ_shop_limit() < num) {
					MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("『{1}』本次限购数量为{2}件,剩余可购买{3}件"), itemBean.getQ_name(),q_shopBean.getQ_shop_limit()+"",q_shopBean.getQ_shop_limit()+"");
					return false;
				}
			}
		}
		return true;
	}
	

	/**限购，购买成功后加数量
	 * @return 
	 * 
	 */
	public void limitbuyaddnum(Player player , Q_shopBean q_shopBean,int num ){
		if(q_shopBean.getQ_shop_limit() > 0 ){
			String key = q_shopBean.getQ_id() +"_"+q_shopBean.getQ_rack();
			int surplus = 0;
			int sum = num;
			if(player.getShoplimitmap().containsKey(key)){
				Integer alreadynum = player.getShoplimitmap().get(key);
				sum = alreadynum+num;
				player.getShoplimitmap().put(key,sum);
				surplus = q_shopBean.getQ_shop_limit() - sum;
			}else {
				player.getShoplimitmap().put(key, num);
				surplus = q_shopBean.getQ_shop_limit() - num;
			}
			Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(q_shopBean.getQ_sell());
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("『{1}』本次限购数量为{2}件,您已经购买{3}件,剩余可购买{4}件"),itemBean.getQ_name(), q_shopBean.getQ_shop_limit()+"",sum+"",surplus+"");
		}
	}
	
	
	
	
	
	/**
	 * 出售物品
	 *
	 * @param roleId 玩家Id
	 * @param npcId NPC Id
	 * @param itemId 物品Id
	 */
	public void sellItem(Player player, List<Long> itemIds) {
		if (itemIds == null) {
			return;
		}
		for (Long itemId : itemIds) {
			if (itemId == null) {
				return;
			}
			sellItem(player, itemId);
		}
	}

	/**
	 * 出售物品
	 *
	 * @param roleId 玩家Id
	 * @param npcId NPC Id
	 * @param itemId 物品Id
	 */
	public void sellItem(Player player, long itemId) {
		//获得要出售物品
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if (item == null) {
			return;
		}
		//获得物品模板
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null) {
			return;
		}
		if (model.getQ_sell() != BUYBACK_ABLE) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("出售失败，系统不回收该物品"));
			return;
		}
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("出售失败，物品正在交易中"));
			return;
		}
		long action = Config.getId();
		int q_sell_price = model.getQ_sell_price();
		int price = q_sell_price * item.getNum();
		if (ManagerPool.backpackManager.changeMoney(player, price, Reasons.SHOPSELL, action)) {
			addPortableGoods(player, item);
			ResSaleSuccessMessage msg = new ResSaleSuccessMessage();
			msg.setItemId(itemId);
			MessageUtil.tell_player_message(player, msg);
			ManagerPool.backpackManager.removeItem(player, itemId, Reasons.SHOPSELL, action);
			item.setGridId(-1);
			if (item instanceof Equip ) {
				ManagerPool.meltingManager.sellEquipGetMelting(player, (Equip)item);
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("出售失败，背包中铜币数量已达上限。"));
		}

		if (item.getNum() > 1) {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("出售[{1}]({2})成功，获得{3}铜币"), BackpackManager.getInstance().getName(model.getQ_id()), item.getNum() + "", String.valueOf(price));
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("出售[{1}]成功，获得{2}铜币"), BackpackManager.getInstance().getName(model.getQ_id()), String.valueOf(price));
		}

		if (BackpackManager.getInstance().isLog(model.getQ_id())) {
			ShopSellLog selllog = new ShopSellLog();
			selllog.setUserid(Long.valueOf(player.getUserId()));
			selllog.setActionId(action);
			selllog.setRoleId(player.getId());
			selllog.setItemid(itemId);
			selllog.setModelid(model.getQ_id());
			selllog.setPrice(price);
			selllog.setItems(JSONserializable.toString(item));
			selllog.setSid(player.getCreateServerId());
			LogService.getInstance().execute(selllog);
		}
	}

//	/**
//	 * 返回商店禁售列表
//	 * @param player
//	 * @param sellType
//	 */
	public void shopItemList(Player player, int sellType) {
		List<Q_shopBean> list = ManagerPool.dataManager.q_shopContainer.getList();
		List<Integer> noSaleItemIds = new ArrayList<Integer>();
//		Gold gold = BackpackManager.getInstance().getGold(player);
		for (Q_shopBean q_shopBean : list) {
			if (sellType == q_shopBean.getQ_model_id()) {
				String q_rack = q_shopBean.getQ_rack();
				//状态为下架的  设为禁售商品
				if ((SHOP_XIAJIA).equals(q_rack)) {
					noSaleItemIds.add(q_shopBean.getQ_id());
					//状态为上架 或者 上架时间段满足条件的 要做消耗元宝检测
				} else if ((SHOP_SHANGJIA).equals(q_rack) || TimeUtil.isNowSatisfiedBy(q_rack)) {
					//需要累积消耗元宝且玩家未达到要求的 设为禁售商品
					int needcost = q_shopBean.getQ_show_cost();
					if (needcost > 0) {
						if (player.getGold() == null || needcost > player.getGold().getCostGold()) {
							noSaleItemIds.add(q_shopBean.getQ_id());
						}
					}
					//不在上架时间段的 设为禁售商品 不能与第二个条件交换位置  否则会影响对q_rack=SHOP_SHANGJIA商品的判断
				} else if (!TimeUtil.isNowSatisfiedBy(q_rack)) {
					noSaleItemIds.add(q_shopBean.getQ_id());
				}
			}
		}

		ResNotSaleMessage notSaleMsg = new ResNotSaleMessage();
		notSaleMsg.setItemIds(noSaleItemIds);
		notSaleMsg.setSellId(sellType);
		MessageUtil.tell_player_message(player, notSaleMsg);
	}

	private void addPortableGoods(Player player, Item item) {
		List<Item> buybackList = player.getBuybackList();
		buybackList.add(item);
		if (buybackList.size() > Global.BUYBACK_LIST_MAX) {
			@SuppressWarnings("unused")
			Item removeFirst = buybackList.remove(0);
		}
	}

	public void buyBackList(Player player) {
		List<Item> buybackList = player.getBuybackList();
		ResRebuyItemInfosMessage resp = new ResRebuyItemInfosMessage();
		List<ItemInfo> items = new ArrayList<ItemInfo>();
		for (Item item : buybackList) {
			items.add(item.buildItemInfo());
		}
		resp.setItems(items);
		MessageUtil.tell_player_message(player, resp);
	}

	public void buyBack(Player player, long ItemId) {
		long action = Config.getId();
		//在回购列表中找到相应的物品
		List<Item> buybackList = player.getBuybackList();
		int index = -1;
		for (int i = 0; i < buybackList.size(); i++) {
			Item item = buybackList.get(i);
			if (item.getId() == ItemId) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("回购失败，您要回购的物品不存在。"));
			return;
		}
		Item item = buybackList.get(index);
		Q_itemBean q_itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if (q_itemBean.getQ_sell() == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品物品不可回购"));
			return;
		}
		int price = q_itemBean.getQ_sell_price() * item.getNum();
		if (player.getMoney() < price) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，所需游戏铜币不足"));
			return;
		}

		if (!BackpackManager.getInstance().hasAddSpace(player, item)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，所需包裹空格不足"));
			return;
		}
		if (item instanceof Equip && ManagerPool.meltingManager.checkEquipGetMelting((Equip) item)) {
			if (!ManagerPool.backpackManager.checkGold(player, 100)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，回购装备等级60以上的紫装需要100元宝"));
				return;
			}
			if (ManagerPool.backpackManager.changeGold(player, -100, Reasons.MELTING_BUYITEM, action)) {
				//从回购列表中移除
				Item remove = buybackList.remove(index);
				//将物品加入到包裹
				ManagerPool.backpackManager.addItem(player, remove, Reasons.SHOPBUY, action);
				//提示购买成功消息
				ResRebuySuccessMessage msg = new ResRebuySuccessMessage();
				msg.setItemId(remove.getId());

				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("回购【{1}】成功使用{2}元宝"), BackpackManager.getInstance().getName(q_itemBean.getQ_id()), String.valueOf(100));
				MessageUtil.tell_player_message(player, msg);
				if (BackpackManager.getInstance().isLog(remove.getItemModelId())) {
					GoldBuyBackLog log = new GoldBuyBackLog();
					log.setCost(100);
					log.setRoleId(player.getId());
					log.setItem(JSONserializable.toString(remove));
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，回购装备等级60以上的紫装需要100元宝"));
			}
		} else {
			if (ManagerPool.backpackManager.changeMoney(player, -price, Reasons.SHOPBUY, action)) {
				//从回购列表中移除
				Item remove = buybackList.remove(index);
				//将物品加入到包裹
				ManagerPool.backpackManager.addItem(player, remove, Reasons.SHOPBUY, action);
				//提示购买成功消息
				ResRebuySuccessMessage msg = new ResRebuySuccessMessage();
				msg.setItemId(remove.getId());

				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("回购【{1}】成功使用{2}铜币"), BackpackManager.getInstance().getName(q_itemBean.getQ_id()), String.valueOf(price));
				MessageUtil.tell_player_message(player, msg);
				if (BackpackManager.getInstance().isLog(remove.getItemModelId())) {
					BuyBackLog log = new BuyBackLog();
					log.setCost(price);
					log.setRoleId(player.getId());
					log.setItem(JSONserializable.toString(remove));
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}
			}
		}
	}

//	/**
//	 * 自动购买物品是否足够
//	 * @param player
//	 * @param itemModelId
//	 * @param num
//	 * @return
//	 */
//	public boolean isEnough(Player player, int itemModelId, int num){
//		//获取包裹内该物品数量
//		int num1 = ManagerPool.backpackManager.getItemNum(player, itemModelId);
//		if(num1 >= num){
//			return true;
//		}
//		
//		num = num - num1;
//		List<Q_shopBean> beans = ManagerPool.dataManager.q_shopContainer.getList();
//		
//		int bindgold = player.getBindGold();
//		int gold = 0;
//		if(player.getGold()!=null){
//			gold = player.getGold().getGold();
//		}
//		
//		for (int i = 0; i < beans.size(); i++) {
//			Q_shopBean shopItem = beans.get(i);
//			
//			if(shopItem.getQ_shop_type()!=SHOP_TYPE_OFICIAL){
//				//非商城物品
//				continue;
//			}
//			
//			if(shopItem.getQ_sell()!=itemModelId){
//				//非需要物品
//				continue;
//			}
//			
//			//已经下架
//			if(!(SHOP_SHANGJIA).equals(shopItem.getQ_rack())){
//				if(((SHOP_XIAJIA).equals(shopItem.getQ_rack()) || !TimeUtil.isNowSatisfiedBy(shopItem.getQ_rack()))){
//					continue;
//				}
//			}
//			
//			if(shopItem.getQ_bindgold() > 0){
//				if(bindgold >= shopItem.getQ_bindgold() * num){
//					return true;
//				}else{
//					int num2 = (int)(bindgold/shopItem.getQ_bindgold());
//					num = num - num2;
//					bindgold = bindgold - shopItem.getQ_bindgold()*num2;
//				}
//			}
//			
//			if(shopItem.getQ_gold() > 0){
//				if(gold >= shopItem.getQ_gold() * num){
//					return true;
//				}else{
//					int num3 = (int)(gold/shopItem.getQ_gold());
//					num = num - num3;
//					gold = gold - shopItem.getQ_bindgold()*num3;
//				}
//			}
//		}
//		
//		return false;
//	}
	/**
	 * 扣除自动购买物品是否足够
	 *
	 * @param player
	 * @param itemModelId
	 * @param num
	 * @return
	 */
	public boolean autoRemove(Player player, int itemModelId, int num, Reasons reasons, long action) {
		//获取包裹内该物品数量
		int num1 = ManagerPool.backpackManager.getItemNum(player, itemModelId);
		if (num1 >= num) {
			ManagerPool.backpackManager.removeItem(player, itemModelId, num, reasons, action);
			return true;
		}

		num = num - num1;

		int bindgold = player.getBindGold();
		int gold = 0;
		if (player.getGold() != null) {
			gold = player.getGold().getGold();
		}
		//礼金
		int costBindGold = 0;
		//消耗元宝
		int costGold = 0;
		int[] price = getItemYuanbao(itemModelId);
		int pricegold = price[0];
		// 这里看看玩家有没有折扣buff
		List<Buff> buffs = ManagerPool.buffManager.getBuffByType(player, BuffType.MALLBUYREDUCE);
		Iterator<Buff> it = buffs.iterator();
		while (it.hasNext()) {
			AttributeBuff buff = (AttributeBuff)it.next();
			int reduceGold = buff.getReduceGold(itemModelId);
			pricegold = pricegold > reduceGold ? pricegold - reduceGold : 0;
		}
		
		int pricebindgold = price[1];

		if (pricegold > 0) {
			int num3 = (int) (gold / pricegold);
			if (num3 >= num) {
				costGold += pricegold * num;
				num = 0;
			} else {
				num = num - num3;
				costGold += pricegold * num3;
				gold = gold - pricegold * num3;
			}
		}

		if (pricebindgold > 0) {
			int num2 = (int) (bindgold / pricebindgold);
			if (num2 >= num) {
				costBindGold += pricebindgold * num;
				num = 0;
			} else {
				num = num - num2;
				costBindGold += pricebindgold * num2;
				bindgold = bindgold - pricebindgold * num2;
			}
		}

		if (num == 0) {
			if (num1 > 0) {
				if (ManagerPool.backpackManager.removeItem(player, itemModelId, num1, reasons, action) == false) {
					return false;
				};
			}
			if (costBindGold > 0) {
				ManagerPool.backpackManager.changeBindGold(player, -costBindGold, reasons, action);
			}
			if (costGold > 0) {
				ManagerPool.backpackManager.changeGold(player, -costGold, reasons, action);
			}
			return true;
		}else{
			ManagerPool.backpackManager.checkGold(player, pricegold * num);
		}
		return false;
	}

	/**
	 * 获取该物品元宝价格
	 *
	 * @param id
	 * @return 0元宝，1绑定元宝
	 */
	public int[] getItemYuanbao(int id) {
		int[] price = {0, 0};
		HashMap<Integer, Q_shopBean> shopBean = ManagerPool.dataManager.q_shopContainer.getMap();
		Iterator<Entry<Integer, Q_shopBean>> shop = shopBean.entrySet().iterator();
		while (shop.hasNext()) {
			Entry<Integer, Q_shopBean> entry = shop.next();
			if (isShanJia(entry.getValue())) {
				if (entry.getValue().getQ_sell() == id) {
					//打折价格
					boolean isdazhe = isDaZheTime(entry.getValue());
					if (entry.getValue().getQ_gold() > 0) {
						if (isdazhe) {
							price[0] = getDaZhePrice(entry.getValue().getQ_show_gold(), entry.getValue().getQ_sale_rate());
						} else {
							price[0] = entry.getValue().getQ_gold();
						}
					} else if (entry.getValue().getQ_bindgold() > 0) {
						if (isdazhe) {
							price[1] = getDaZhePrice(entry.getValue().getQ_show_bindgold(), entry.getValue().getQ_sale_rate());
						} else {
							price[1] = entry.getValue().getQ_bindgold();
						}
					}
				}
			}
		}
		return price;
	}

	/**
	 * 商城购买成功后检测是否需要刷新商城列表
	 *
	 * @author tangchao
	 * @return
	 */
	public boolean needRefreshList(int preCostGold, int nowCostGold) {
		boolean need = false;
		List<Q_shopBean> list = ManagerPool.dataManager.q_shopContainer.getList();
		for (Q_shopBean q_shopBean : list) {
			if (2 == q_shopBean.getQ_model_id()) {  //这里的2是商店模型id  数据库中的q_model_id
				String q_rack = q_shopBean.getQ_rack();
				if ((SHOP_SHANGJIA).equals(q_rack) || TimeUtil.isNowSatisfiedBy(q_rack)) {
					//上架商品 或 满足配置时间段的商品 检查是否满足消耗元宝的要求
					int needcost = q_shopBean.getQ_show_cost();
					if (needcost > 0) {
						if (preCostGold < needcost && needcost <= nowCostGold) {
							need = true;
							break;
						}
					}
				}
			}
		}
		return need;
	}

	/**
	 * 商城显示的商品列表
	 *
	 * @param player
	 * @param shopModelId	商店类型
	 * @param page	页号
	 * @param gridelimit 是否等级限制
	 */
	public void shopList(Player player, int shopModelId, byte page, boolean gridelimit) {
		List<Q_shopBean> list = ManagerPool.dataManager.q_shopContainer.getList();

		// 这里看看玩家有没有折扣buff,放在外面,避免循环嵌套
		List<Buff> buffs = ManagerPool.buffManager.getBuffByType(player, BuffType.MALLBUYREDUCE);
		
		List<ShopItemInfo> itemInfos = new ArrayList<ShopItemInfo>();
		for (Q_shopBean shopModel : list) {
			try {
				if (shopModel.getQ_model_id() != shopModelId) {
					continue;
				}
				if (shopModel.getQ_page() != page) {
					continue;
				}
				// if (shopModel)
				if (shopModel.getQ_page_sex() != 0 && shopModel.getQ_page_sex() != player.getSex()) {
					continue;
				}
				if (gridelimit && shopModel.getQ_show_level() > player.getLevel()) {
					continue;
				}
				//影响区服
				if(!StringUtil.isBlank(shopModel.getQ_service_area())&&!istrue(shopModel.getQ_service_area(), WServer.getInstance().getServerId())){
					continue;
				}
				//上架时间逻辑
				if (isXiaJia(shopModel)) {
					continue;
				}
				//骑战兵器
				if(shopModel.getQ_sell()==3017 && player.getActivitiesReward().containsKey(BUY_HORSE_WEAPON)){
					continue;
				}
				//暗器
				if(shopModel.getQ_sell()==18175){
					HiddenWeapon weapon = HiddenWeaponManager.getInstance().getHiddenWeapon(player);
					if (weapon != null && weapon.getLayer() > 1) {
						continue;
					}
				}
				//已经购买境界激活道具
				if (shopModel.getQ_sell()==18137 && player.getRealm().getActivation() == 2) {
					continue;
				}
				
				
				HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
				//骑战兵器
				if(shopModel.getQ_sell()==3018 && weapon!=null && weapon.getLayer()>1){
					continue;
				}
				if (shopModel.getQ_show_cost() != 0 && (player.getGold() == null || player.getGold().getCostGold() < shopModel.getQ_show_cost())) {
					continue;
				}
	
				ShopItemInfo buildShopItemInfo = buildShopItemInfo(shopModel);
				
				// 这里看看玩家有没有折扣buff
				Iterator<Buff> it = buffs.iterator();
				while (it.hasNext()) {
					AttributeBuff buff = (AttributeBuff)it.next();
					int reduceGold = buff.getReduceGold(buildShopItemInfo.getModelId());
					buildShopItemInfo.setGold(buildShopItemInfo.getGold() > reduceGold ? buildShopItemInfo.getGold() - reduceGold : 0);
				}
				
				itemInfos.add(buildShopItemInfo);
			} catch (Exception e) {
				log.error(e, e);
			}
		}

		ResShopItemListMessage msg = new ResShopItemListMessage();
		msg.setPage(page);
		msg.setShopModelId(shopModelId);
		msg.setShopItemList(itemInfos);
		MessageUtil.tell_player_message(player, msg);
	}
	
	
	private static boolean istrue(String parm,int serverId){
		if (!StringUtil.isBlank(parm)) {
			String areatag = parm;
			String[] split2 = areatag.split(Symbol.FENHAO_REG);
			for (String string : split2) {
				if (string.contains("-")) {
					String[] split = string.split("-");
					if (split[1].equals("&")) {
						int min = Integer.parseInt(split[0]);
						if (serverId >= min) {
							return true;
						}
					} else {
						int min = Integer.parseInt(split[0]);
						int max = Integer.parseInt(split[1]);
						if(serverId>=min&&serverId<=max){
							return true;
						}
					}
				} else {
					int parseInt = Integer.parseInt(string);
					if (serverId == parseInt) {
						return true;
					}
				}
			}					
		}
		return false;
	}
	
	public static void main(String args[]){
		System.out.println(istrue("5;1-2",1));
		System.out.println(istrue("2;3",1));
		System.out.println(istrue("2-&",1));
	}
	

	private ShopItemInfo buildShopItemInfo(Q_shopBean model) {
		ShopItemInfo info = new ShopItemInfo();
		info.setAppend(model.getQ_append());
		info.setBindgold(model.getQ_bindgold());
		info.setCoin(model.getQ_coin());
		info.setGold(model.getQ_gold());
		info.setDuration(model.getQ_duration());
		info.setHot((byte) model.getQ_hot());
//		boolean dazhe1=!StringUtil.isBlank(model.getQ_discount_time())&&TimeUtil.isNowSatisfiedBy(model.getQ_discount_time());
//		boolean dazhe2=!StringUtil.isBlank(model.getQ_openserver_discount())&&TimeUtil.isOpenServerTimer(model.getQ_openserver_discount());
		Q_itemBean itemModel = DataManager.getInstance().q_itemContainer.getMap().get(model.getQ_sell());
//		int coinprice=model.getQ_coin()==0?itemModel.getQ_buy_price():model.getQ_coin();
		if (model.getQ_coin() == 0) {
			info.setCoin(itemModel.getQ_buy_price());
		}
//		if(model.getQ_hot()==0&&isdaze){
//			info.setHot((byte)2);
//			if(model.getQ_show_coin()==0){
//				info.setCoin(getDaZhePrice(itemModel.getQ_buy_price(),model.getQ_sale_rate()));
//			}else{
//				info.setCoin(getDaZhePrice(model.getQ_show_coin(),model.getQ_sale_rate()));	
//			}
//			info.setGold(getDaZhePrice(model.getQ_show_gold(),model.getQ_sale_rate()));
//			info.setBindgold(getDaZhePrice(model.getQ_show_bindgold(), model.getQ_sale_rate()));			
//		}
		if (isDaZheTime(model)) {
//			info.setHot((byte)3);
			if (model.getQ_show_coin() == 0) {
				info.setCoin(getDaZhePrice(itemModel.getQ_buy_price(), model.getQ_sale_rate()));
			} else {
				info.setCoin(getDaZhePrice(model.getQ_show_coin(), model.getQ_sale_rate()));
			}
//			info.setCoin(getDaZhePrice(model.getQ_show_coin(),model.getQ_sale_rate()));
			info.setGold(getDaZhePrice(model.getQ_show_gold(), model.getQ_sale_rate()));
			info.setBindgold(getDaZhePrice(model.getQ_show_bindgold(), model.getQ_sale_rate()));
		}
		info.setIndex((short) model.getQ_index());
		info.setLostTime(model.getQ_losttime());
		info.setModelId(model.getQ_sell());
		info.setMoneyType((byte) model.getQ_money_type());
		info.setOriginalBindGold(model.getQ_show_bindgold());
		info.setOriginalCoin(model.getQ_show_coin());
		info.setOriginalGold(model.getQ_show_gold());
		info.setSellId(model.getQ_id());
		info.setStrengthen(model.getQ_strengthen());
		return info;
	}

	/**
	 * 是否处于定时打折时间
	 *
	 * @param model
	 * @return
	 */
	public boolean isDaZheTime(Q_shopBean model) {
		String shopDazhe = SHOP_DAZHE;
		boolean dazhe = !StringUtil.isBlank(model.getQ_discount_time()) && model.getQ_discount_time().equals(shopDazhe);
		boolean dazhe1 = !StringUtil.isBlank(model.getQ_discount_time()) && TimeUtil.isNowSatisfiedBy(model.getQ_discount_time());
		boolean dazhe2 = !StringUtil.isBlank(model.getQ_openserver_discount()) && TimeUtil.isOpenServerTimer(model.getQ_openserver_discount());
		return dazhe || dazhe1 || dazhe2;
	}

	/**
	 * 是否上架
	 *
	 * @param model
	 * @return
	 */
	public boolean isShanJia(Q_shopBean model) {
		if(!StringUtil.isBlank(model.getQ_openserver_rack())&&!TimeUtil.isOpenServerTimer(model.getQ_openserver_rack())){
			return false;
		}
		if(!StringUtil.isBlank(model.getQ_rack())&&!SHOP_SHANGJIA.equals(model.getQ_rack())&&!TimeUtil.isNowSatisfiedBy(model.getQ_rack())){
			return false;
		}	
		return true;
	}

	/**
	 * 是否下架
	 *
	 * @param model
	 * @return
	 */
	public boolean isXiaJia(Q_shopBean model) {
		return !isShanJia(model);
	}

	/**
	 * 打折价格 小于等于零为非法请求
	 *
	 * @param costtype 1 铜币 2元宝 3绑定元宝 其它的为非法请求 返回负数
	 * @param modelId
	 * @return
	 */
	public int getDaZhePrice(int price, int rate) {
		return (int) Math.ceil((double) price * rate / 100);
	}
}
