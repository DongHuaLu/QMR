package com.game.backpack.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.arrow.manager.ArrowManager;
import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.bean.ItemReasonsInfo;
import com.game.backpack.log.AbleReceiveChangeLog;
import com.game.backpack.log.BindGoldChangeLog;
import com.game.backpack.log.CellOpenLog;
import com.game.backpack.log.ClearUpLog;
import com.game.backpack.log.GoldChangeLog;
import com.game.backpack.log.ItemChangeLog;
import com.game.backpack.log.MoneyChangeLog;
import com.game.backpack.message.ResBindGoldChangeMessage;
import com.game.backpack.message.ResCellTimeMessage;
import com.game.backpack.message.ResGetItemReasonsMessage;
import com.game.backpack.message.ResGoldChangeMessage;
import com.game.backpack.message.ResItemAddMessage;
import com.game.backpack.message.ResItemChangeMessage;
import com.game.backpack.message.ResItemInfosMessage;
import com.game.backpack.message.ResItemRemoveMessage;
import com.game.backpack.message.ResMoneyChangeMessage;
import com.game.backpack.message.ResNotEnoughGoldChangeMessage;
import com.game.backpack.message.ResOpenCellResultMessage;
import com.game.backpack.script.IConsumeGoldScript;
import com.game.backpack.structs.EffectType;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemAction;
import com.game.backpack.structs.ItemChangeAction;
import com.game.backpack.structs.OtherReason;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_backpack_gridBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.Gold;
import com.game.db.dao.GoldDao;
import com.game.dblog.LogService;
import com.game.dblog.bean.BaseLogBean;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqSyncPlayerGoldExpendMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.task.manager.TaskManager;
import com.game.task.message.ResRewardsAbleActMessage;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.transactions.message.ResCanreceiveYuanbaoMessage;
import com.game.utils.BeanUtil;
import com.game.utils.CodedUtil;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.ZipUtil;

/**
 * 背包管理类
 *
 * @author heyang szy_heyang@163.com
 *
 */
@SuppressWarnings("deprecation")
public class BackpackManager {

	private Logger log = Logger.getLogger(BackpackManager.class);
//	private static final int NUMBER_IN_PAGE_FOR_BACKPACK = 36;
	private static Object obj = new Object();
	//玩家管理类实例
	private static BackpackManager manager;
	private GoldDao dao;

	private BackpackManager() {
		dao = new GoldDao();
	}

	public static BackpackManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new BackpackManager();
			}
		}
		return manager;
	}

	public static int wsxwdlevel = 120;//万寿修为丹等级限制
	
	/**
	 * 增加物品
	 *
	 * @param item 物品
	 * @return
	 */
	public boolean addItem(Player player, Item item, Reasons resons, long action) {
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null) {
			log.error("玩家("+player.getId()+")模型:" + item.getItemModelId()+"任务物品加入包裹开始检查失败找不到物品的模型物品ID"+ item.getId());
			return false;
		}
		if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
			log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹开始检查"+action);
		}
		// 数据检查
		if (item.getNum() <= 0) {
			log.error("玩家("+player.getId()+")非法参数物品数量 为0");
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查未通过"+action+" 位置1");
			}
			return false;
		}
		int num = item.getNum();
		if (num > model.getQ_max()) {
			log.error("玩家("+player.getId()+")物品数量超过了最大可堆叠数量ID:" + item.getId() + " 模型:" + item.getItemModelId());
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查未通过"+action+" 位置2");
			}
			return false;
		}
		if (!hasAddSpace(player, item)) {
			log.error("玩家("+player.getId()+")不应该走到这里，外部包裹检查出错", new Exception());
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查未通过"+action+" 位置3");
			}
			return false;
		}
		int gridid = getBackpackAbleAddGridId(player, item);
		if (gridid == -1) {
			log.error("玩家("+player.getId()+")这一步就不应该找不到位置了 如果执行到这里说明上方的检查方法出错", new Exception());
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查未通过"+action+" 位置4");
			}
			return false;
		}
		if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
			log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action);
		}
		//用于记录关联操作
		long actionId = action != 0 ? action : Config.getId();
		if (model.getQ_bind() == 1) {
			item.setBind(true);
		}
		if (model.getQ_max() == 1) {
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置11");
			}
			//不存在叠加问题
//			Item item2 = player.getBackpackItems().get(String.valueOf(item.getGridId()));
			if (item.getGridId() <= 0) {
				item.setGridId(gridid);
			}
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置12");
			}
			player.getBackpackItems().put(String.valueOf(item.getGridId()), item);
			MessageUtil.tell_player_message(player, getItemAddMessage(item, resons));
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置13");
			}
			try {
				if (model.getQ_log() == 1) {
					ItemChangeLog log = new ItemChangeLog();
					log.setAction(ItemAction.ADD.toString());
					log.setChangeAction(ItemChangeAction.ADD.toString());
					log.setActionId(actionId);
					log.setItemafterInfo(JSONserializable.toString(item));
					log.setItembeforeInfo("");
					log.setItemid(item.getId());
					log.setModelid(item.getItemModelId());
					log.setNum(item.getNum());
					log.setReason(resons.getValue());
					log.setRoleid(player.getId());
					log.setUserId(player.getUserId());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}
			} catch (Exception e) {
				log.error(e, e);
			}
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹成功"+action);
			}
		} else {
			//可堆叠物品
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置21");
			}
			int count = item.getNum();
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置22");
			}
			for (int i = 1; i <= player.getBagCellsNum() && count > 0; i++) {
				Item target = player.getBackpackItems().get(String.valueOf(i));
				if (target == null) {
					//优先找同类可堆叠的物品
					continue;
				}
				if (!isMerage(item, target)) {
					continue;
				}
				if (target.getNum() >= model.getQ_max()) {
					continue;
				}
				int actionnum = 0;//发生数
				String before = JSONserializable.toString(target);
				int ablenum = model.getQ_max() - target.getNum();
				if (count + target.getNum() <= model.getQ_max()) {
					target.setNum(target.getNum() + count);
					actionnum = count;
					count = 0;
				} else {
					actionnum = ablenum;
					count -= ablenum;
					target.setNum(model.getQ_max());
				}
				String after = JSONserializable.toString(target);
				MessageUtil.tell_player_message(player, getItemChangeMessage(target));
				if (resons.getValue() == Reasons.MELTING_GETITEM.getValue() || resons.getValue() == Reasons.CHESTBOX_ITEM.getValue()) {
					int itemreasons = 3;
					if (resons.getValue() == Reasons.CHESTBOX_ITEM.getValue()) {
						itemreasons = 4;
					}
					ResGetItemReasonsMessage sendMessage = new ResGetItemReasonsMessage();
					ItemReasonsInfo itemReasonsInfo = new ItemReasonsInfo();
					itemReasonsInfo.setItemId(target.getId());
					itemReasonsInfo.setItemModelId(target.getItemModelId());
					itemReasonsInfo.setItemNum(target.getNum());
					itemReasonsInfo.setItemReasons(itemreasons);
					sendMessage.getItemReasonsInfoList().add(itemReasonsInfo);
					MessageUtil.tell_player_message(player, sendMessage);
				}
				try {
					if (model.getQ_log() == 1) {
						ItemChangeLog log = new ItemChangeLog();
						log.setAction(ItemAction.ADD.toString());
						log.setChangeAction(ItemChangeAction.CHANGE.toString());
						log.setActionId(actionId);
						log.setItemafterInfo(after);
						log.setItembeforeInfo(before);
						log.setItemid(target.getId());
						log.setModelid(target.getItemModelId());
						log.setNum(actionnum);
						log.setReason(resons.getValue());
						log.setRoleid(player.getId());
						log.setUserId(player.getUserId());
						log.setSid(player.getCreateServerId());
						LogService.getInstance().execute(log);
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
			if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
				log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置23");
			}
			if (count > 0) {
				int backpackAbleAddGridId = getBackpackAbleAddGridId(player, item);
				if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
					log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置33");
				}
				if (backpackAbleAddGridId == -1) {
					log.error("这一步不应该获取不到位置的", new Exception());
					return false;
				}
				if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
					log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置34");
				}
				item.setGridId(backpackAbleAddGridId);
				item.setNum(count);
				player.getBackpackItems().put(String.valueOf(item.getGridId()), item);
				MessageUtil.tell_player_message(player, getItemAddMessage(item, resons));
				if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
					log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹检查通过"+action+"位置35");
				}
//				String after=JSONserializable.toString(target);
//				MessageUtil.tell_player_message(player, getItemChangeMessage(item));
//				if (resons.getValue() == Reasons.MELTING_GETITEM.getValue()) {
//					ResGetItemReasonsMessage sendMessage = new ResGetItemReasonsMessage();
//					ItemReasonsInfo itemReasonsInfo = new ItemReasonsInfo();
//					itemReasonsInfo.setItemId(item.getId());
//					itemReasonsInfo.setItemModelId(item.getItemModelId());
//					itemReasonsInfo.setItemNum(item.getNum());
//					itemReasonsInfo.setItemReasons(3);
//					sendMessage.getItemReasonsInfoList().add(itemReasonsInfo);
//					MessageUtil.tell_player_message(player, sendMessage);
//				}
				try {
					if (model.getQ_log() == 1) {
						ItemChangeLog log = new ItemChangeLog();
						log.setActionId(actionId);
						log.setAction(ItemAction.ADD.toString());
						log.setChangeAction(ItemChangeAction.ADD.toString());
						log.setItemafterInfo(JSONserializable.toString(item));
						log.setItembeforeInfo("");
						log.setItemid(item.getId());
						log.setModelid(item.getItemModelId());
						log.setNum(count);
						log.setReason(resons.getValue());
						log.setRoleid(player.getId());
						log.setUserId(player.getUserId());
						log.setSid(player.getCreateServerId());
						LogService.getInstance().execute(log);
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
		if(model.getQ_type()==11&&resons.equals(Reasons.TAKEUP)){
			log.error("玩家("+player.getId()+")"+model.getQ_name()+"任务物品加入包裹加入包裹完成"+action+"位置24");
		}
//		if(item.getNum()==1){
//			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"得到物品"+model.getQ_name());	
//		}else{
//			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"得到物品{1}({2})",model.getQ_name(),num+"");
//		}
		//任务追踪
		TaskManager.getInstance().action(player, Task.ACTION_TYPE_GOODS, item.getItemModelId(), num);
		ManagerPool.playerManager.savePlayer(player);
		return true;
	}

	/**
	 * 批量添加
	 *
	 * @param roleId
	 * @param items
	 * @return
	 */
	public boolean addItems(Player player, Collection<Item> items, Reasons reasons, long action) {
		if (!hasAddSpace(player, items)) {
			log.error("不应该走到这里，外部包裹检查出错", new Exception());
			return false;
		}
		for (Item item : items) {
			item.setGridId(-1);
			addItem(player, item, reasons, action);
		}
		return true;
	}

	/**
	 * 移动物品
	 *
	 * @param roleId 玩家id
	 * @param itemId 物品id
	 * @param num 物品数量
	 * @param toGridId 移动目的地格子id
	 */
	public void moveItem(Player player, long itemId, int num, int toGridId) {
		// 数据检查
		if (num < 0) {
			return;
		}
//		if(player.getTransactionsinfo()!=null){
//			MessageUtil.notify_player(player, Notifys.ERROR,"正在交易中不允许该操作");
//			return;
//		}
		if (toGridId > player.getBagCellsNum() || toGridId <= 0) {
			return;
		}
		// 获得要移动物品
		Item item = getItemById(player, itemId);
		if (item == null) {
			return;
		}
		if (num > item.getNum()) {
			return;
		}
		if(item.getGridId()==toGridId&&num==item.getNum()){
			return;
		}
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中"));
			return;
		}
		long actionId = Config.getId();//日志事件
		String before = JSONserializable.toString(item);
		if (player.getBackpackItems().get(String.valueOf(toGridId)) == null) {
			// 完全移动
			if (num == 0 || num >= item.getNum()) {
				int emptyGridNum = getEmptyGridNum(player);
				if (emptyGridNum < 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空间不足,移动失败"));
					return;
				}
				if (toGridId == -1) {
					toGridId = getBackpackFirstEmptGridId(player);
				}
				player.getBackpackItems().remove(String.valueOf(item.getGridId()));
				item.setGridId(toGridId);
				player.getBackpackItems().put(String.valueOf(item.getGridId()), item);
				MessageUtil.tell_player_message(player,
					getItemChangeMessage(item));
				try {
					Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
					if (model.getQ_log() == 1) {

						// 移动日志
						ItemChangeLog log = new ItemChangeLog();
						log.setActionId(actionId);
						log.setAction(ItemAction.MOVE.toString());
						log.setChangeAction(ItemChangeAction.MOVE.toString());
						log.setItemafterInfo(JSONserializable.toString(item));
						log.setItembeforeInfo(before);
						log.setItemid(item.getId());
						log.setModelid(item.getItemModelId());
						log.setNum(num);
						log.setReason(OtherReason.move);
						log.setRoleid(player.getId());
						log.setUserId(player.getUserId());
						log.setSid(player.getCreateServerId());
						LogService.getInstance().execute(log);
					}
				} catch (Exception e) {
					log.error(e, e);
				}

			} else {
				if (player.getBackpackItems().get(String.valueOf(toGridId)) != null && player.getBackpackItems().get(String.valueOf(toGridId)).isTrade()) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中,不可以执行该操作"));
					return;
				}
				try {
					if (toGridId == -1) {
						toGridId = getBackpackClosestEmptGridId(player, item.getGridId());
					}
					if (toGridId == -1) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空间不足,拆分失败"));
						return;
					}
					Item togrid= BackpackManager.getInstance().getItemByCellId(player, toGridId);
					if(togrid!=null){
//						目标格子不为空 不允许 拆分
						return;
					}
					
					// 新增拆分物品
					Item clone = item.clone();
					clone.setId(Config.getId());
					clone.setNum(num);
					clone.setGridId(toGridId);
					player.getBackpackItems().put(String.valueOf(clone.getGridId()), clone);
					MessageUtil.tell_player_message(player, getItemAddMessage(clone, null));
					// 拆分
					item.setNum(item.getNum() - num);
					MessageUtil.tell_player_message(player, getItemChangeMessage(item));
					try {
						Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
						if (model.getQ_log() == 1) {


							// 拆分增加日志
							ItemChangeLog addlog = new ItemChangeLog();
							addlog.setActionId(actionId);
							addlog.setAction(ItemAction.MOVE.toString());
							addlog.setChangeAction(ItemChangeAction.ADD.toString());
							addlog.setItemafterInfo(JSONserializable.toString(clone));
							addlog.setItembeforeInfo("");
							addlog.setItemid(clone.getId());
							addlog.setModelid(clone.getItemModelId());
							addlog.setNum(num);
							addlog.setReason(OtherReason.slip);
							addlog.setRoleid(player.getId());
							addlog.setUserId(player.getUserId());
							addlog.setSid(player.getCreateServerId());
							LogService.getInstance().execute(addlog);

							// 拆分日志
							ItemChangeLog log = new ItemChangeLog();
							log.setActionId(actionId);
							log.setAction(ItemAction.MOVE.toString());
							log.setChangeAction(ItemChangeAction.CHANGE.toString());
							log.setItemafterInfo(JSONserializable.toString(item));
							log.setItembeforeInfo("");
							log.setItemid(item.getId());
							log.setModelid(item.getItemModelId());
							log.setNum(num);
							log.setReason(OtherReason.slip);
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);
						}
					} catch (Exception e) {
						log.error(e, e);
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		} else {
			// 获取物品模型
			Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
			Item toGrid = player.getBackpackItems().get(String.valueOf(toGridId));
			if (toGrid.isTrade()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中"));
				return;
			}
			if(item.getGridId()==toGridId||item.getId()==toGrid.getId()){
				//自己不可以跟自己合并 
				return;
			}
			
			if (isMergeAble(item, toGrid)) {
				if (item.getNum() + player.getBackpackItems().get(String.valueOf(toGridId)).getNum() <= model.getQ_max()) {
					String toGridbefore = JSONserializable.toString(toGrid);
					//完全合并
					//合并
					toGrid.setNum(item.getNum() + toGrid.getNum());
					MessageUtil.tell_player_message(player, getItemChangeMessage(player.getBackpackItems().get(String.valueOf(toGridId))));
					// 移除原来物品
					player.getBackpackItems().remove(String.valueOf(item.getGridId()));
					MessageUtil.tell_player_message(player, getItemRemoveMessage(item));

					try {
						if (model.getQ_log() == 1) {
							// 合并日志
							ItemChangeLog log = new ItemChangeLog();
							log.setActionId(actionId);
							log.setAction(ItemAction.MOVE.toString());
							log.setChangeAction(ItemChangeAction.CHANGE.toString());
							log.setItemafterInfo(JSONserializable.toString(toGrid));
							log.setItembeforeInfo(toGridbefore);
							log.setItemid(toGrid.getId());
							log.setModelid(toGrid.getItemModelId());
							log.setNum(item.getNum());
							log.setReason(OtherReason.combin);
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);

							ItemChangeLog removelog = new ItemChangeLog();
							removelog.setActionId(actionId);
							removelog.setAction(ItemAction.MOVE.toString());
							removelog.setChangeAction(ItemChangeAction.REMOVE.toString());
							removelog.setItemafterInfo("");
							removelog.setItembeforeInfo(before);
							removelog.setItemid(item.getId());
							removelog.setModelid(item.getItemModelId());
							removelog.setNum(item.getNum());
							removelog.setReason(OtherReason.combin);
							removelog.setRoleid(player.getId());
							removelog.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(removelog);
						}
					} catch (Exception e) {
						log.error(e, e);
					}
				} else {
					//部分合并
					//可以合并 的数量
					int sub = model.getQ_max() - toGrid.getNum();
					String toGridbefore = JSONserializable.toString(toGrid);
					String itembefore = JSONserializable.toString(item);
					toGrid.setNum(model.getQ_max());
					item.setNum(item.getNum() - sub);
					MessageUtil.tell_player_message(player, getItemChangeMessage(item));
					MessageUtil.tell_player_message(player, getItemChangeMessage(toGrid));

					try {
						if (model.getQ_log() == 1) {
							// 合并日志
							ItemChangeLog log = new ItemChangeLog();
							log.setActionId(actionId);
							log.setAction(ItemAction.MOVE.toString());
							log.setChangeAction(ItemChangeAction.CHANGE.toString());
							log.setItemafterInfo(JSONserializable.toString(toGrid));
							log.setItembeforeInfo(toGridbefore);
							log.setItemid(toGrid.getId());
							log.setModelid(toGrid.getItemModelId());
							log.setNum(item.getNum());
							log.setReason(OtherReason.combin);
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);

							ItemChangeLog removelog = new ItemChangeLog();
							removelog.setActionId(actionId);
							removelog.setAction(ItemAction.MOVE.toString());
							removelog.setChangeAction(ItemChangeAction.CHANGE.toString());
							removelog.setItemafterInfo(JSONserializable.toString(item));
							removelog.setItembeforeInfo(itembefore);
							removelog.setItemid(item.getId());
							removelog.setModelid(item.getItemModelId());
							removelog.setNum(item.getNum());
							removelog.setReason(OtherReason.combin);
							removelog.setRoleid(player.getId());
							removelog.setUserId(player.getUserId());
							removelog.setSid(player.getCreateServerId());
							LogService.getInstance().execute(removelog);
						}
					} catch (Exception e) {
						log.error(e, e);
					}
				}
			} else {
				// 交换位置
				int tmpgrid = item.getGridId();
				String toGridbefore = JSONserializable.toString(toGrid);
				String itembefore = JSONserializable.toString(item);
				player.getBackpackItems().remove(String.valueOf(item.getGridId()));
				player.getBackpackItems().remove(String.valueOf(toGrid.getGridId()));
				item.setGridId(toGrid.getGridId());
				toGrid.setGridId(tmpgrid);
				player.getBackpackItems().put(String.valueOf(item.getGridId()), item);
				player.getBackpackItems().put(String.valueOf(toGrid.getGridId()), toGrid);
				MessageUtil.tell_player_message(player, getItemChangeMessage(item));
				MessageUtil.tell_player_message(player, getItemChangeMessage(toGrid));
				try {
					if (model.getQ_log() == 1) {
						// 合并日志
						ItemChangeLog log = new ItemChangeLog();
						log.setActionId(actionId);
						log.setAction(ItemAction.MOVE.toString());
						log.setChangeAction(ItemChangeAction.MOVE.toString());
						log.setItemafterInfo(JSONserializable.toString(toGrid));
						log.setItembeforeInfo(toGridbefore);
						log.setItemid(toGrid.getId());
						log.setModelid(toGrid.getItemModelId());
						log.setNum(item.getNum());
						log.setReason(OtherReason.move);
						log.setRoleid(player.getId());
						log.setUserId(player.getUserId());
						log.setSid(player.getCreateServerId());
						LogService.getInstance().execute(log);

						ItemChangeLog removelog = new ItemChangeLog();
						removelog.setActionId(actionId);
						removelog.setAction(ItemAction.MOVE.toString());
						removelog.setChangeAction(ItemChangeAction.MOVE.toString());
						removelog.setItemafterInfo(JSONserializable.toString(item));
						removelog.setItembeforeInfo(itembefore);
						removelog.setItemid(item.getId());
						removelog.setModelid(item.getItemModelId());
						removelog.setNum(item.getNum());
						removelog.setReason(OtherReason.move);
						removelog.setRoleid(player.getId());
						removelog.setUserId(player.getUserId());
						removelog.setSid(player.getCreateServerId());
						LogService.getInstance().execute(removelog);
					}
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
		ManagerPool.playerManager.savePlayer(player);
	}

	/**
	 * 得到包裹中第一个为空的格子id， 没有则返回-1
	 *
	 * @author zhaoconghui
	 * @param player
	 * @return
	 */
	private int getBackpackFirstEmptGridId(Player player) {
		int bagCellsNum = player.getBagCellsNum();
		for (int i = 1; i <= bagCellsNum; i++) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null || item.getNum() == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 是否有足够的空间加入物品
	 *
	 * @param player
	 * @param item
	 * @return
	 */
	public boolean hasAddSpace(Player player, Item item) {
		int emptyGridNum = getEmptyGridNum(player);
		if (emptyGridNum > 0) {
			return true;
		} else {
			int count = 0;//able add count
			Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
			if (q_itemBean == null) {
				log.error("找不到的物品模型ID" + item.getItemModelId());
				return false;
			}
			for (int i = 1; i <= player.getBagCellsNum(); i++) {
				Item target = player.getBackpackItems().get(String.valueOf(i));
				if (!isMerage(item, target)) {
					continue;
				}
				count += q_itemBean.getQ_max() - target.getNum();
				if (count >= item.getNum()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasAddSpace(Player player, Collection<Item> items) {
		if (items.size() <= 0) {
			return true;
		}
		int emptyGridNum = getEmptyGridNum(player);
		if (emptyGridNum >= items.size()) {
			// 光空格子就够了
			return true;
		}
		HashMap<String, Integer> addcount = new HashMap<String, Integer>();
		HashMap<String, Integer> combAbleCount = new HashMap<String, Integer>();
		HashMap<String, Integer> modellist = new HashMap<String, Integer>();
		for (Item item : items) {
			if(item.isTrade()){
				continue;
			}
			String key = item.getItemModelId() + "_" + item.getLosttime() + "_" + item.isBind();
			if (addcount.containsKey(key)) {
				addcount.put(key, addcount.get(key) + item.getNum());
			} else {
				addcount.put(key, item.getNum());
			}
			if (!combAbleCount.containsKey(key)) {
				combAbleCount.put(key, getCombinNum(player, item));
				modellist.put(key, item.getItemModelId());
			}
		}

		//过滤掉可合并项
		Set<String> keySet = combAbleCount.keySet();
		for (String key : keySet) {
			Integer able = combAbleCount.get(key);
			Integer need = addcount.get(key);
			if (able >= need) {
				addcount.remove(key);
			} else {
				addcount.put(key, need - able);
			}
		}
		int needGridNum = 0;
		Set<String> addKeySet = addcount.keySet();
		for (String key : addKeySet) {
			Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(modellist.get(key));
			int num = addcount.get(key);
			int q_max = q_itemBean.getQ_max();
			if (num <= q_max) {
				needGridNum += 1;
			} else {
				if (num % q_max == 0) {
					needGridNum += num / q_max;
				} else {
					needGridNum += num / q_max + 1;
				}
			}
		}
		if (needGridNum > emptyGridNum) {
			return false;
		}
		return true;
	}

	/**
	 * 可叠加数量
	 *
	 * @param item
	 * @return
	 */
	private int getCombinNum(Player player, Item item) {
		int count = 0;
		Collection<Item> allItem = BackpackManager.getInstance().getAllItem(player);
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		for (Item item2 : allItem) {
			if (isMerage(item, item2)) {
				count += model.getQ_max() - item2.getNum();
			}
		}
		return count;
	}

	/**
	 * 获取包裹中可添加的指定物品的数量
	 *
	 * @param player
	 * @param itemModelId
	 * @param num
	 * @param isbind
	 * @param lostTime
	 * @return
	 */
	public int getAbleAddNum(Player player, int itemModelId, boolean isbind, long lostTime) {
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);
		if (model == null) {
			log.error("找不到的模型ID" + itemModelId, new Exception());
			return 0;
		}
		int emptyGridNum = getEmptyGridNum(player);
		if (model.getQ_max() == 0 || model.getQ_max() == 1) {
			return emptyGridNum;
		}
		int count = 0;
		Collection<Item> allItem = getAllItem(player);
		for (Item item : allItem) {
			if (item.getItemModelId() != itemModelId) {
				continue;
			}
			if (item.isBind() != isbind) {
				continue;
			}
			if (item.getLosttime() != lostTime) {
				continue;
			}
			if (item.getNum() >= model.getQ_max()) {
				continue;
			}
			if(item.isTrade()){
				continue;
			}
			count += model.getQ_max() - item.getNum();
		}
		return emptyGridNum * model.getQ_max() + count;
	}

	/**
	 * 获取离当前格子最近的一个空格子
	 *
	 * @param roleId
	 * @param gridId
	 * @return
	 */
	private int getBackpackClosestEmptGridId(Player player, int gridId) {
		int result = -1;
		if (gridId <= 0) {
			//非法的格子
			return result;
		}

		int bagCellsNum = player.getBagCellsNum();
		for (int i = gridId + 1; i <= bagCellsNum; i++) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null || item.getNum() == 0) {
				result = i;
				break;
			}
		}
		if (gridId <= 1) {
			return result;
		}
		int result2 = -1;
		for (int i = gridId - 1; i >= 1; i--) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null || item.getNum() == 0) {
				result2 = i;
				break;
			}
		}
		if (result == -1 && result2 == -1) {
			return -1;
		}
		if (result != -1 && result2 == -1) {
			return result;
		}
		if (result == -1 && result2 != -1) {
			return result2;
		}
		int distance = result - gridId;
		int distance2 = gridId - result2;
		if (distance <= distance2) {
			return result;
		} else {
			return result2;
		}
	}

	/**
	 * 使用物品
	 *
	 * @param roleId 玩家id
	 * @param itemId 物品id
	 * @param num	使用数量
	 */
	public void useItem(Player player, long itemId, int num) {
		//获得要移动物品
		Item item = getItemById(player, itemId);
		if (item == null) {
			return;
		}
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null) {
			return;
		}
		if (num < 1) {
			num = 1;
		}
		if (num > 1 && model.getQ_max() <= 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该位置物品数量不足，该物品不支持批量使用"));
			return;
		}
		if (num > item.getNum()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该位置物品数量不足，请求数量{1}当前数量{2}"), num + "", item.getNum() + "");
			//请求非法
			return;
		}

		if (num > 1 && model.getQ_whether_batch() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品不支持批量使用"));
			return;
		}
		
		if (item.getItemModelId() == 30107 && player.getLevel() >= wsxwdlevel) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "万寿修为丹暂时不能使用");
			return;
		}
		
		if (player.isDie()) {
			log.error("玩家己死亡不允许使用物品" + player.getId());
			return;
		}
		//未在包裹中
		if (item.getGridId() == -1) {
			return;
		}

		// 装备条件检查
		if (model.getQ_sex() != 0 && model.getQ_sex() != player.getSex()) {
			// 装备性别不符合
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品限{1}性使用"), model.getQ_sex() == 1 ? ResManager.getInstance().getString("男") : ResManager.getInstance().getString("女"));
			return;
		}

		if (player.getLevel() < model.getQ_level()) {
			// 装备等级不符合
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品需要人物等级达到{1}后才可以使用"), String.valueOf(model.getQ_level()));
			return;
		}
		if (item.isLost()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该物品已过期"), String.valueOf(model.getQ_level()));
			return;
		}
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中"));
			return;
		}
		if (checkHaveEffict(EffectType.ADDEXP, model) && PlayerManager.getInstance().isTopLevel(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的等级已达到最高无法使用该用品"));
			return;
		}
		//检查真气上限
		if (checkHaveEffict(EffectType.ADDZHENQI, model) && PlayerManager.getInstance().isFullZq(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的真气已满无法使用该用品"));
			return;
		}
		if (checkHaveEffict(EffectType.ADDHP, model) && PlayerManager.getInstance().isFullHp(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的血量已满无法使用该用品"));
			return;
		}
		if (checkHaveEffict(EffectType.ADDMP, model) && PlayerManager.getInstance().isFullMp(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的内力已满无法使用该用品"));
			return;
		}
		if (checkHaveEffict(EffectType.ADDSP, model) && PlayerManager.getInstance().isFullSp(player)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的体力已满无法使用该用品"));
			return;
		}
		//是否达到使用物品数量上限
		int canuse = model.getQ_item_limit();
		if(canuse > 0){
			long alreadynum = ManagerPool.countManager.getCount(player, CountTypes.ITEM_USE, String.valueOf(item.getItemModelId()));
			if(alreadynum < canuse){
				if(num > canuse - (int)alreadynum) num = canuse - (int)alreadynum;
				ManagerPool.countManager.addCount(player, CountTypes.ITEM_USE, String.valueOf(item.getItemModelId()), CountManager.COUNT_PERSISTENT , num, 0);
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该物品已达到使用上限，不能继续使用。"));
				return;
			}
		}
		
		if (model.getQ_bind() == 2) {
			item.setBind(true);
		}
		item.use(player, num + "");
	}

	/**
	 * 检查是否有某种药效
	 *
	 * @param type	药效类型
	 * @param model	物品模型
	 * @return
	 */
	private boolean checkHaveEffict(int type, Q_itemBean model) {
		if (model.getQ_effict_type() == null || model.getQ_effict_type().equals("")) {
			return false;
		}
		String effict = model.getQ_effict_type();
		effict = effict.replace("[", "");
		effict = effict.replace("]", "");
		String[] efficts = effict.split(Symbol.DOUHAO_REG);
		for (String string : efficts) {
			int parseInt = Integer.parseInt(string);
			if (parseInt == type) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 移除物品
	 *
	 * @param roleId 玩家id
	 * @param itemId 物品id
	 */
	public boolean removeItem(Player player, long itemId, Reasons reasons, long action) {
		//获得要移动物品
		Item item = getItemById(player, itemId);
		if (item == null) {
			return false;
		}
		return removeItemByCellId(player, item.getGridId(), reasons, action);
	}

	/**
	 * //TODO 绑定优先 有过期时间优先 移除物品
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @param num 物品数量
	 */
	public boolean removeItem(Player player, int itemModelId, int num, Reasons reasons, long action) {
		if (num <= 0) {
			return false;
		}

		if (getItemNum(player, itemModelId) < num) {
			return false;
		}
		int tmpnum = num;
		int binditemNum = getItemNum(player, itemModelId, true);
		int itemNum = getItemNum(player, itemModelId, false);
		if (binditemNum + itemNum < num) {
			log.error("正常不会执行到这部 检查代码", new Exception());
			return false;
		}
		long actionId = action != 0 ? 0 : Config.getId();
//		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemModelId);



		if (binditemNum > 0) {
			if (binditemNum >= tmpnum) {
				removeItem(player, itemModelId, tmpnum, true, reasons, actionId);
				tmpnum = 0;
//				if (tmpnum == 1) {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//				} else {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), tmpnum + "");
//				}
			} else {
				removeItem(player, itemModelId, binditemNum, true, reasons, actionId);
				tmpnum -= binditemNum;

//				if (binditemNum == 1) {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//				} else {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), binditemNum + "");
//				}
			}
		}
		if (itemNum > 0) {
			if (itemNum >= tmpnum) {
				removeItem(player, itemModelId, tmpnum, false, reasons, actionId);
				tmpnum = 0;
//				if (tmpnum == 1) {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//				} else {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), tmpnum + "");
//				}
			} else {
				removeItem(player, itemModelId, binditemNum, true, reasons, actionId);
				tmpnum -= binditemNum;
//				if (binditemNum == 1) {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}", q_itemBean.getQ_name());
//				} else {
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "失去物品{1}({2})", q_itemBean.getQ_name(), binditemNum + "");
//				}
			}
		}
		TaskManager.getInstance().action(player, Task.ACTION_TYPE_GOODS, itemModelId, -num);
		ManagerPool.playerManager.savePlayer(player);
		return true;
	}

	/**
	 * 移除指定绑定类型 指定数量的物品
	 *
	 * @param player
	 * @param itemModelId
	 * @param num
	 * @param bind
	 */
	private boolean removeItem(Player player, int itemModelId, int num, boolean bind, Reasons reasons, long action) {
		if (getItemNum(player, itemModelId, bind) < num) {
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
			if (item.isBind() == bind && item.getItemModelId() == itemModelId && !item.isLost() && !item.isTrade()) {
				if (item.getNum() <= num) {
					removeItemByCellId(player, i, reasons, actionId);
					num = num - item.getNum();
				} else {
					String before = JSONserializable.toString(item);
					item.setNum(item.getNum() - num);
					num = 0;
					MessageUtil.tell_player_message(player, getItemChangeMessage(item));
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
							log.setNum(num);
							log.setReason(reasons.getValue());
							log.setRoleid(player.getId());
							log.setUserId(player.getUserId());
							log.setSid(player.getCreateServerId());
							LogService.getInstance().execute(log);
						}
					} catch (Exception e) {
						log.error(e, e);
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
	 * 移除指定道具指定数量
	 *
	 * @param player
	 * @param item
	 * @param num
	 */
	public boolean removeItem(Player player, Item item, int num, Reasons reasons, long action) {
		if (item.getNum() < num) {
			//数量不足
			log.error("物品数量不足外部检查出错", new Exception());
			return false;
		}
		if (item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，物品正在交易中"));
			return false;
		}
		if (item.isLost()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，物品己过期"));
			return false;
		}
		long actionId = action != 0 ? action : Config.getId();
		if (item.getNum() == num) {
			removeItemByCellId(player, item.getGridId(), reasons, actionId);
		} else {
			String before = JSONserializable.toString(item);

			item.setNum(item.getNum() - num);
//			Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
//			if(num==1){
//				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"失去物品{1}",q_itemBean.getQ_name());	
//			}else{
//				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"失去物品{1}({2})",q_itemBean.getQ_name(),num+"");
//			}
			MessageUtil.tell_player_message(player, getItemChangeMessage(item));
			Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());

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
					log.setNum(num);
					log.setReason(reasons.getValue());
					log.setRoleid(player.getId());
					log.setUserId(player.getUserId());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		try{
			TaskManager.getInstance().action(player, Task.ACTION_TYPE_GOODS, item.getItemModelId(), -num);	
		}catch (Exception e) {
			log.error(BeanUtil.getStack(),e);
		}
		
		return true;
	}

	/**
	 * 移除指定格子中的物品
	 *
	 * @param roleId
	 * @param cellId
	 */
	public boolean removeItemByCellId(Player player, int cellId, Reasons reasons, long action) {
		Item item = getItemByCellId(player, cellId);
		if (item == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("指定位置的物品不存在"));
			return false;
		}
		if (reasons != Reasons.JIAOYIITEM && item.isTrade()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在被交易"));
			return false;
		}
		Item remove = player.getBackpackItems().remove(String.valueOf(cellId));
		
//		if(item.getNum()==1){
//			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"失去物品"+model.getQ_name());	
//		}else{
//			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,"失去物品{1}({2})",model.getQ_name(),item.getNum()+"");
//		}
		
		try{
			
			MessageUtil.tell_player_message(player, getItemRemoveMessage(remove));
			TaskManager.getInstance().action(player, Task.ACTION_TYPE_GOODS, item.getItemModelId(), -item.getNum());	
		}catch (Exception e) {
			log.error(e,e);
		}
		
		long actionId = action != 0 ? action : Config.getId();
		try {
			Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
			if (model.getQ_log() == 1) {
				ItemChangeLog log = new ItemChangeLog();
				log.setActionId(actionId);
				log.setAction(ItemAction.REMOVE.toString());
				log.setChangeAction(ItemChangeAction.REMOVE.toString());
				log.setItemafterInfo("");
				log.setItembeforeInfo(JSONserializable.toString(item));
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
			log.error(e, e);
		}
		return true;
	}

	/**
	 * 获得剩余格子数量
	 *
	 * @param roleId 玩家id
	 * @return 剩余格子数量
	 */
	public int getEmptyGridNum(Player player) {
		return player.getBagCellsNum() - player.getBackpackItems().size();
	}

	/**
	 * 获得物品数量
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	public int getItemNum(Player player, int itemModelId) {
		int num = 0;
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isLost() && !item.isTrade()) {
				num += item.getNum();
			}
		}
		return num;
	}

	/**
	 * 获取指定绑定属性的数量
	 *
	 * @param player
	 * @param itemModelId
	 * @param bind
	 * @return
	 */
	private int getItemNum(Player player, int itemModelId, boolean bind) {
		int num = 0;
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isLost() && !item.isTrade() && item.isBind() == bind) {
				num += item.getNum();
			}
		}
		return num;
	}

	/**
	 * 按模板Id获得第一个此模板物品 绑定优先
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品Id
	 */
	public Item getFirstItemByModelId(Player player, int itemModelId) {
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		//遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isTrade() && !item.isLost() && item.isBind()) {
				return item;
			}
		}
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isTrade() && !item.isLost()) {
				return item;
			}
		}
		return null;
	}

	/**
	 * 发送玩家全部物品信息
	 *
	 * @param roleId 玩家id
	 */
	public void sendItemInfos(Player player) {
		ResItemInfosMessage msg = new ResItemInfosMessage();
		msg.setCellnum(player.getBagCellsNum());
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			msg.getItems().add(item.buildItemInfo());
		}
		MessageUtil.tell_player_message(player, msg);
		sendMoneyInfo(player);
		sendGoldInfo(player);
		sendBindGoldInfo(player);
		sendCanreceiveYuanbaoInfo(player);
	}

	/**
	 * 发送玩家金钱信息
	 *
	 * @param roleId 玩家id
	 */
	private void sendMoneyInfo(Player player) {
		ResMoneyChangeMessage msg = new ResMoneyChangeMessage();
		msg.setMoney(player.getMoney());

		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 发送玩家元宝信息
	 *
	 * @param roleId 玩家id
	 */
	private void sendGoldInfo(Player player) {
		ResGoldChangeMessage msg = new ResGoldChangeMessage();
		Gold gold = player.getGold();
		if (gold != null) {
			msg.setGold(gold.getGold());
		} else {
			msg.setGold(0);
		}
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 发送玩家元宝信息
	 *
	 * @param roleId 玩家id
	 */
	private void sendBindGoldInfo(Player player) {
		ResBindGoldChangeMessage msg = new ResBindGoldChangeMessage();
		msg.setBindgold(player.getBindGold());
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 发送可领取元宝变化
	 *
	 * @param player
	 */
	public void sendCanreceiveYuanbaoInfo(Player player) {
		ResCanreceiveYuanbaoMessage ybmsg = new ResCanreceiveYuanbaoMessage();
		ybmsg.setCanryuanbao(player.getCanreceiveyuanbao());
		MessageUtil.tell_player_message(player, ybmsg);//发送可领取元宝
	}

	/**
	 * 获得背包中物品
	 *
	 * @param roleId 玩家id
	 * @param itemId 物品id
	 * @return
	 */
	public Item getItemById(Player player, long itemId) {
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getId() == itemId) {
				return item;
			}
		}
		return null;
	}

	public Item getItemByCellId(Player player, long cellId) {
		return player.getBackpackItems().get(String.valueOf(cellId));
	}

	public Collection<Item> getAllItem(Player player) {
		return player.getBackpackItems().values();
	}

	/**
	 * 获取包裹空余位置或第一个同模板物品且物品数量满足可放置的位置
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id(为0时获取第一空余位置)
	 * @return
	 */
	public int getBackpackAbleAddGridId(Player player, int itemModelId, int num, boolean isbind, long lostTime) {
		//获取物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(itemModelId);
		if (model.getQ_max() > 1) {
			for (int i = 1; i <= player.getBagCellsNum(); i++) {
				Item item = player.getBackpackItems().get(String.valueOf(i));
				if (item == null) {
					continue;
				}
				if (item.getItemModelId() != itemModelId) {
					//模型不一致 下一个
					continue;
				}
				if (item.getLosttime() != lostTime) {
					//失效时间不一至
					continue;
				}
				if (item.isBind() != isbind) {
					//绑定属性不一致
					continue;
				}
				if (model.getQ_max() == 1) {
					continue;
				}
				if (item.getNum() + num > model.getQ_max()) {
					continue;
				}
				if (item.isTrade()) {
					continue;
				}
				return i;

			}
		}
		for (int i = 1; i <= player.getBagCellsNum(); i++) {
			Item item = player.getBackpackItems().get(String.valueOf(i));
			if (item == null) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取包裹空余位置或第一个同模板物品且物品数量满足可放置的位置
	 *
	 * @param player	角色
	 * @param item	物品
	 * @return
	 */
	public int getBackpackAbleAddGridId(Player player, Item item) {
		return getBackpackAbleAddGridId(player, item.getItemModelId(), item.getNum(), item.isBind(), item.getLosttime());
	}

	/**
	 * 获取物品增加消息
	 *
	 * @param item 物品
	 * @return
	 */
	public ResItemAddMessage getItemAddMessage(Item item, Reasons resons) {
		ResItemAddMessage msg = new ResItemAddMessage();
		msg.setItem(item.buildItemInfo());
		if (resons != null) {
			if (resons.compare(Reasons.MELTING_GETITEM.getValue())) {
				msg.setReason((byte) 3);
			} else if (resons.compare(Reasons.ACTIVITY_GIFT.getValue())) {
				msg.setReason((byte) 2);
			} else if (resons.compare(Reasons.CHESTBOX_ITEM.getValue())) {
				msg.setReason((byte) 4);
			} else {
				if (!resons.compare(Reasons.WEAREDORUNWEARED.getValue())) {
					msg.setReason((byte) 1);
				}
			}
		}
		return msg;
	}

	/**
	 * 获取物品信息变更消息
	 *
	 * @param item 物品
	 * @return
	 */
	public ResItemChangeMessage getItemChangeMessage(Item item) {
		ResItemChangeMessage msg = new ResItemChangeMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}

	/**
	 * 获取物品移除消息
	 *
	 * @param item 物品
	 * @return
	 */
	public ResItemRemoveMessage getItemRemoveMessage(Item item) {
		ResItemRemoveMessage msg = new ResItemRemoveMessage();
		msg.setItemId(item.getId());
		return msg;
	}

	/**
	 * 包裹整理
	 *
	 * @param roleid 角色ID
	 */
	public void bagClearUp(Player player, boolean isgm) {
		if (!isgm) {
			if (player.getTransactionsinfo() != null) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("交易中不可以整理包裹"));
				return;
			}
			if (ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.BAG_CLEAR, null)) {
				long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.BAG_CLEAR, null);
				MessageUtil.notify_player(player, Notifys.NORMAL, (cooldownTime / 1000l) + "秒之后才可以整理");
				return;
			}

			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.BAG_CLEAR, null, Global.CLEARUP_TIME_INTERVAL * 1000);
		}
		String bagbefore = JSONserializable.toString(player.getBackpackItems());
		long actionId = Config.getId();
		List<Entry<String, Item>> list = new ArrayList<Entry<String, Item>>();
		ConcurrentHashMap<String, Item> container = player.getBackpackItems();
		list.addAll(container.entrySet());
		List<BaseLogBean> logs = new ArrayList<BaseLogBean>();
		for (int i = 0; i < list.size(); i++) {
			Entry<String, Item> entry = list.get(i);
			Item value = entry.getValue();
			Q_itemBean valuemodel = DataManager.getInstance().q_itemContainer.getMap().get(value.getItemModelId());
			// 如果第一个物品是满的那么直接略过
			if (valuemodel.getQ_max() > 1
				&& valuemodel.getQ_max() > value.getNum()) {
				for (int j = i + 1; j < list.size(); j++) {
					Entry<String, Item> entry2 = list.get(j);
					Item value2 = entry2.getValue();
					if (isMergeAble(value, value2)) {
						// 合并
						if (value.getNum() + value2.getNum() <= valuemodel.getQ_max()) {
							String logBefore = JSONserializable.toString(value);
							String logBefore2 = JSONserializable.toString(value2);
							int logchangenum = value2.getNum();
							value.setNum(value.getNum() + value2.getNum());
							// 移除原来物品
							// 这里直接移除的话会倒致下标溢出
							value2.setNum(0);
							if (valuemodel.getQ_log() == 1) {
								logs.add(ItemChangeLog.createLog(player.getUserId(), player.getId(), value.getId(), value.getItemModelId(), logchangenum, logBefore,
									JSONserializable.toString(value), OtherReason.bagclearup, ItemAction.CLEARUP.toString(), actionId,
									ItemChangeAction.CHANGE.toString(),player.getCreateServerId()));
								logs.add(ItemChangeLog.createLog(player.getUserId(), player.getId(), value2.getId(), value2.getItemModelId(), logchangenum, logBefore2,
									JSONserializable.toString(value2), OtherReason.bagclearup, ItemAction.CLEARUP.toString(), actionId,
									ItemChangeAction.REMOVE.toString(),player.getCreateServerId()));
							}
						} else {
							int all = value.getNum() + value2.getNum();
							int logchangenum = valuemodel.getQ_max() - value.getNum();
							String before1 = JSONserializable.toString(value);
							String before2 = JSONserializable.toString(value2);
							value.setNum(valuemodel.getQ_max());
							value2.setNum(all - valuemodel.getQ_max());
							if (valuemodel.getQ_log() == 1) {
								logs.add(ItemChangeLog.createLog(player.getUserId(), player.getId(), value.getId(), value.getItemModelId(), logchangenum, before1,
									JSONserializable.toString(value), OtherReason.bagclearup, ItemAction.CLEARUP.toString(), actionId,
									ItemChangeAction.CHANGE.toString(),player.getCreateServerId()));
								logs.add(ItemChangeLog.createLog(player.getUserId(), player.getId(), value2.getId(), value2.getItemModelId(), logchangenum, before2,
									JSONserializable.toString(value2), OtherReason.bagclearup, ItemAction.CLEARUP.toString(), actionId,
									ItemChangeAction.CHANGE.toString(),player.getCreateServerId()));
							}
						}
						if (value.getNum() >= valuemodel.getQ_max()) {
							break;
						}
					}
				}
			}
		}
		//清除数量为零的
		for (Entry<String, Item> entry : list) {
			if (entry.getValue().getNum() == 0) {
				container.remove(entry.getKey());
			}
		}
		//排序
		List<Item> sortlist = new ArrayList<Item>();
		for (Entry<String, Item> entry : container.entrySet()) {
			sortlist.add(entry.getValue());
		}
		container.clear();
		Collections.sort(sortlist);
		for (int i = 0; i < sortlist.size(); i++) {
			Item item = sortlist.get(i);
			String before = JSONserializable.toString(item);
			item.setGridId(i + 1);
			Q_itemBean valuemodel = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
			container.put("" + (i + 1), item);
			if (valuemodel.getQ_log() == 1) {
				try {
					logs.add(ItemChangeLog.createLog(player.getUserId(), player.getId(), item.getId(), item.getItemModelId(), 0, before, JSONserializable.toString(item),
						OtherReason.bagclearup, ItemAction.CLEARUP.toString(), actionId, ItemChangeAction.MOVE.toString(),player.getCreateServerId()));
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
		//发送通知
		sendItemInfos(player);
		PlayerManager.getInstance().savePlayer(player);
		try {
			for (BaseLogBean item : logs) {
				LogService.getInstance().execute(item);
			}
			ClearUpLog log = new ClearUpLog();
			log.setBefore(CodedUtil.encodeBase64(ZipUtil.compress(bagbefore)));
			log.setAfter(CodedUtil.encodeBase64(ZipUtil.compress(JSONserializable.toString(player.getBackpackItems()))));
			log.setRoleId(player.getId());
			log.setType(Global.BAG_TYPE);
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	/**
	 * 验证两个物品是否可以合并 包括最大数量
	 *
	 * @param source
	 * @param target
	 * @return
	 */
	public boolean isMergeAble(Item item1, Item item2) {
		HashMap<Integer, Q_itemBean> goodmodels = DataManager.getInstance().q_itemContainer.getMap();
		Q_itemBean sourcemodel = goodmodels.get(item1.getItemModelId());
//		Q_itemBean targetmodel = goodmodels.get(item2.getItemModelId());
		if (!isMerage(item1, item2)) {
			return false;
		}

		if (item1.getNum() >= sourcemodel.getQ_max() || item2.getNum() >= sourcemodel.getQ_max()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断两个物品属性是否一至 可合并 忽略 最大数量
	 *
	 * @param item1
	 * @param item2
	 * @return
	 */
	private boolean isMerage(Item item1, Item item2) {
		HashMap<Integer, Q_itemBean> goodmodels = DataManager.getInstance().q_itemContainer.getMap();
		if (!goodmodels.containsKey(item1.getItemModelId()) && !goodmodels.containsKey(item2.getItemModelId())) {
			log.error(item1 + " " + item2 + "物品模型找不到", new Exception());
			return false;
		}
		Q_itemBean sourcemodel = goodmodels.get(item1.getItemModelId());
		Q_itemBean targetmodel = goodmodels.get(item2.getItemModelId());
		if (sourcemodel != targetmodel) {
			//模型不一致不能合并
			return false;
		}
		if (sourcemodel.getQ_max() <= 1) {
			//不可叠加的物品
			return false;
		}
		if (item1.isBind() != item2.isBind()) {
			//绑定属性不一至 不能合并
			return false;
		}
		if (item1.getLosttime() != item2.getLosttime()) {
			//过期时间不一至不能合并
			return false;
		}
		if (item1.isTrade() || item2.isTrade()) {
			return false;
		}
//		if(item1.getNum()>=sourcemodel.getQ_max()||item2.getNum()>=sourcemodel.getQ_max()){
//			return false;
//		}
		return true;
	}

	public void dealCellTimeQueryMsg(Player player, int cellId) {
		if (cellId <= 0 || cellId > Global.MAX_BAG_CELLS) {
			return;
		}
		if (cellId != player.getBagCellsNum() + 1) {
			//请求的格子不是可开的格子
			return;
		}
		Q_backpack_gridBean config = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.BAG_TYPE + "_" + cellId);
		int time = config.getQ_time() - player.getBagCellTimeCount();
		ResCellTimeMessage resp = new ResCellTimeMessage();
		resp.setCellId(cellId);
		resp.setTimeRemaining(time);
//		if(log.isDebugEnabled()){
//			log.debug("包裹还剩下"+time);
//		}
		MessageUtil.tell_player_message(player, resp);
	}

	/**
	 * 开格子，不对格子ID和类别做检查请在调用前检查格子
	 *
	 * @param roleId	角色ID
	 * @param cellid	格子编号
	 * @param type	1包裹 2仓库
	 */
	public void openCell(Player player, int cellId) {
		ResOpenCellResultMessage msg = new ResOpenCellResultMessage();
		int rewardexp = 0;
		for (int i = player.getBagCellsNum() + 1; i <= cellId; i++) {
			Q_backpack_gridBean config = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.BAG_TYPE + "_" + i);
			rewardexp += config.getQ_exp();
		}
		player.setBagCellTimeCount(0);
		player.setBagCellsNum(cellId);
		msg.setCellId(player.getBagCellsNum());
		msg.setIsSuccess(1);
		ManagerPool.playerManager.addExp(player, rewardexp, AttributeChangeReason.BAGOPENCELL);
		MessageUtil.tell_player_message(player, msg);
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BACKPACK);
		player.setSendBagOpenCellTime(false);

	}

	public void openCellMsg(Player player, int cellId) {
		if (cellId <= 0 || cellId > Global.MAX_BAG_CELLS) {
			log.error("非法请求开格号" + cellId + "roleid" + player.getId() + "当前格子ID" + player.getBagCellsNum());
			return;
		}

		if (cellId - player.getBagCellsNum() > 7) {
			//最大只能开七个格子 如果有大于此数的请求 视为非法请求
			return;
		}
		if (cellId <= player.getBagCellsNum()) {
			//非法请求开格号
			log.error("非法请求开格号" + cellId + "roleid" + player.getId() + "当前格子ID" + player.getBagCellsNum());
			return;
		}
		int needgold = 0;;
		int rewardexp = 0;
		for (int i = player.getBagCellsNum() + 1; i <= cellId; i++) {
			Q_backpack_gridBean config = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.BAG_TYPE + "_" + i);
			rewardexp += config.getQ_exp();
			if (i == player.getBagCellsNum() + 1) {
				if (player.getBagCellTimeCount() < config.getQ_time()) {
					needgold += config.getQ_cost();
				}
			} else {
				needgold += config.getQ_cost();
			}
		}
		if (!checkGold(player, needgold)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，开启格子所需的元宝不足!"));
			return;
		}
		if (changeGold(player, -needgold, Reasons.YBBAGKAIGE, Config.getId())) {
			int bagCellsNum = player.getBagCellsNum();
			openCell(player, cellId);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("成功扩容背包到{1}个!花费元宝{2}，奖励经验{3}"), cellId + "", needgold + "", rewardexp + "");
			try {
				CellOpenLog log = new CellOpenLog();
				log.setAction((byte) 1);
				log.setAfterCells(player.getBagCellsNum());
				log.setBeforeCells(bagCellsNum);
				log.setCellId(cellId);
				log.setResumegold(needgold);
				log.setRoleId(player.getId());
				log.setType(Global.BAG_TYPE);
				log.setActionId(Config.getId());
				log.setSid(player.getCreateServerId());
				LogService.getInstance().execute(log);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，开启格子所需的元宝不足!"));
		}
	}

	public void openCellByTime(Player player) {
		int bagCellsNum = player.getBagCellsNum();
		if (bagCellsNum < Global.MAX_BAG_CELLS && bagCellsNum >= Global.BAG_AUTOOPEN_CELL_ID) {
			int bagCellTimeCount = player.getBagCellTimeCount() + 1;
			Q_backpack_gridBean bagConfig = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.BAG_TYPE + "_" + (bagCellsNum + 1));
			if (bagCellTimeCount >= bagConfig.getQ_time()) {
				BackpackManager.getInstance().openCell(player, bagCellsNum + 1);
//				BackpackManager.getInstance().openCell(player, bagCellsNum+1);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("成功扩容背包到{1}个!奖励经验{2}"), (bagCellsNum + 1) + "", bagConfig.getQ_exp() + "");
				try {
					CellOpenLog log = new CellOpenLog();
					log.setAction((byte) 0);
					log.setAfterCells(player.getBagCellsNum());
					log.setBeforeCells(bagCellsNum);
					log.setCellId(bagCellsNum + 1);
					log.setResumegold(0);
					log.setRoleId(player.getId());
					log.setType(Global.BAG_TYPE);
					log.setActionId(Config.getId());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				dealCellTimeQueryMsg(player, bagCellsNum + 1);
			}
		}
	}

//	/**
//	 * 礼金
//	 *
//	 * @param player
//	 * @param gold
//	 * @param needGold
//	 * @return
//	 */
//	public boolean checkBindGoldAndGold(Player player, int needgold) {
//		int goldnum = 0;
//		if (needgold < 0) {
//			return false;
//		}
//		if (needgold == 0) {
//			return true;
//		}
//		if (player.getGold() != null) {
//			goldnum = player.getGold().getGold();
//		}
//		if ((long) goldnum + player.getBindGold() < needgold) {
//			return false;
//		}
//		return true;
//	}

	/**
	 * 检查元宝数量
	 *
	 * @param player
	 * @param gold
	 * @param needGold
	 * @return
	 */
	public boolean checkGold(Player player, int needGold) {
		if (needGold == 0) {
			return true;
		}
		if (player.getGold() == null) {
			return false;
		}
		int goldnum = 0;
		if (player.getGold() != null) {
			goldnum = player.getGold().getGold();
		}
		if ((long) goldnum < needGold) {
			ResNotEnoughGoldChangeMessage msg = new ResNotEnoughGoldChangeMessage();
			msg.setGold(needGold - goldnum);
			MessageUtil.tell_player_message(player, msg);
			return false;
		}
		return true;
	}

//	public Gold getGold(Player player){
//		try{
//			return dao.select(player.getUserId(), player.getCreateServerId());
//		}catch (SQLException e) {
//			log.error(e, e);
//		}
//		return null;
//	}
//	public Gold getGold(String userid, int serverId) {
//		return dao.select(userid, serverId);
//	}

	/**
	 * 铜币变化
	 *
	 * @param roleId
	 * @param num
	 * @return
	 */
	public boolean changeMoney(Player player, int num, Reasons reasons, long action) {
		if (num > 0
			&& PlayerManager.CHECK_NONAGE
			&& Reasons.GETGOLD != reasons
			&& Reasons.BUYGOLD != reasons
			&& Reasons.JIAOYIGOLD != reasons) {

			if (player.getNonage() == 2) {
				num = num / 2;
			} else if (player.getNonage() == 3) {
				sendMoneyInfo(player);
				return true;
			}

		}
		if (num == 0) {
			return true;
		}
		int money = player.getMoney() + num;
		if (money < 0 || money > Global.BAG_MAX_COPPER) {
			if (num < 0) {
				//MessageUtil.notify_player(player, Notifys.ERROR, "操作失败，铜币数量不足"); 
				return false;
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包铜币数己达上限"));
				return false;
			}
		}
		int before = player.getMoney();
		player.setMoney(money);
		sendMoneyInfo(player);
		if (num > 0) {  // 验证通过 记录累积得到铜币 和 累积消耗铜币  
			//MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, "获得{1}铜币",String.valueOf(Math.abs(num)));	
			player.setTotalgetmoney(player.getTotalgetmoney() + num);  //玩家累积获得铜币
			WServer.sgeneratemoney.addAndGet(num);  //服务器累积产出铜币
		} else {
			//MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, "失去{1}铜币",String.valueOf(Math.abs(num)));
			player.setTotalconsumemoney(player.getTotalconsumemoney() + Math.abs(num)); //玩家累积消耗铜币
			WServer.sconsumemoney.addAndGet(Math.abs(num)); //服务器累积产出铜币
			try {
				if (player.getTransactionsinfo() != null && reasons != Reasons.JIAOYIGOLD) {
					ManagerPool.transactionsManager.stChangeState(player);	//解锁交易
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
		if (Reasons.TAKEUP != reasons) {
			MoneyChangeLog log = new MoneyChangeLog();
			log.setActionId(action);
			log.setRoleId(player.getId());
			log.setBeforenum(before);
			log.setAfternum(money);
			log.setNum(num);
			log.setReason(reasons.getValue());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}
		return true;
	}

	/**
	 * 增加元宝变化
	 *
	 * @param roleId
	 * @param num
	 * @return
	 */
	public boolean addGold(Player player, int num, Reasons reason, long action) {
		if (num == 0) {
			return true;
		}
		boolean isinsert = false;
		int goldnum = num;
		Gold gold = player.getGold();
		if (gold != null) {
			isinsert = false;
			goldnum += gold.getGold();
		} else {
			isinsert = true;
			gold = new Gold();
			gold.setBuyitemresume(0);
			gold.setCostGold(0);
			gold.setFaildrollBackadd(0);
			gold.setGettempybadd(0);
			gold.setGold(0);
			gold.setHuokuanAdd(0);
			gold.setJiaoyiresume(0);
			gold.setJiaoyiybadd(0);
			gold.setServerId(player.getCreateServerId());
			gold.setShangjiaresume(0);
			gold.setTotalGold(0);
			gold.setTwgmadd(0);
			gold.setUserId(player.getUserId());
			gold.setYbxiajiaadd(0);
//			gold.setIsinner(0);
		}
		if (goldnum < 0 || goldnum > Global.BAG_MAX_GOLD) {
			if (num >= 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包元宝数己达上限"));
			}
			return false;
		}
		player.setGold(gold);
		int before = gold.getGold();
		Gold saveGold = new Gold();
		saveGold.setBuyitemresume(0);
		saveGold.setCostGold(0);
		saveGold.setFaildrollBackadd(0);
		saveGold.setGettempybadd(0);
		saveGold.setHuokuanAdd(0);
		saveGold.setJiaoyiresume(0);
		saveGold.setJiaoyiybadd(0);
		saveGold.setShangjiaresume(0);
		saveGold.setTotalGold(0);
		saveGold.setTwgmadd(0);
		saveGold.setYbxiajiaadd(0);

		saveGold.setUserId(player.getUserId());
		saveGold.setServerId(player.getCreateServerId());
		saveGold.setGold(num);
//		gold.setIsinner(0);
		gold.setGold(gold.getGold() + num);

		if (num > 0) {
			switch (reason) {
				case STYBGOUMAI:
					saveGold.setTwgmadd(num);
					gold.setTwgmadd(gold.getTwgmadd() + num);
					break;
				case STYBXIAJIA:
					saveGold.setYbxiajiaadd(num);
					gold.setYbxiajiaadd(gold.getYbxiajiaadd() + num);
					break;
				case STYBHUOKUAN:
					saveGold.setHuokuanAdd(num);
					gold.setHuokuanAdd(gold.getHuokuanAdd() + num);
					break;
				case JIAOYIYB:
					saveGold.setJiaoyiybadd(num);
					gold.setJiaoyiybadd(gold.getJiaoyiybadd() + num);
					break;
				case STGOUMAISHIBAI:
					saveGold.setFaildrollBackadd(num);
					gold.setFaildrollBackadd(gold.getFaildrollBackadd() + num);
					break;
				case QUCHUTMPYB:
					saveGold.setGettempybadd(num);
					gold.setGettempybadd(gold.getGettempybadd() + num);
					break;
				case GOLD_RECHARGE:
					saveGold.setTotalGold(num);
					gold.setTotalGold(gold.getTotalGold() + num);
					break;
				default:
					// 非交易类增加
					break;
			}
		} else {
			//元宝上架	元宝交易	元宝购买道具不算消耗 需 另计
			switch (reason) {
				case JIAOYIYB:
					saveGold.setJiaoyiresume(num);
					gold.setJiaoyiresume(gold.getJiaoyiresume() + num);
					break;
				case STYBSHANGJIA:
					saveGold.setShangjiaresume(num);
					gold.setShangjiaresume(gold.getShangjiaresume() + num);
					break;
				case STYBGOUMAIDAOJU:
					saveGold.setBuyitemresume(num);
					gold.setBuyitemresume(gold.getBuyitemresume() + num);
					break;
				default:
					//非交易类消耗
					saveGold.setCostGold(num);
					gold.setCostGold(gold.getCostGold() + num);
					break;
			}
		}
		//充值 忽略 
		if (reason != Reasons.GOLD_RECHARGE) {
			saveGold(saveGold, isinsert);
		}
		sendGoldInfo(player);
		if (num > 0) {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("获得{1}元宝"), String.valueOf(Math.abs(num)));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得{1}元宝"), String.valueOf(Math.abs(num)));
		} else {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("失去{1}元宝"), String.valueOf(Math.abs(num)));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("失去{1}元宝"), String.valueOf(Math.abs(num)));
			try {
				if (player.getTransactionsinfo() != null && reason != Reasons.JIAOYIYB) {
					ManagerPool.transactionsManager.stChangeState(player);	//解锁交易
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		GoldChangeLog log = new GoldChangeLog();
		log.setActionId(action);
		log.setBeforenum(before);
		log.setAfternum(goldnum);
		log.setNum(num);
		log.setReason(reason.getValue());
		log.setRoleId(player.getId());
		log.setUserId(player.getUserId());
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
		return true;
	}
	
	/**
	 * 减少元宝变化
	 *
	 * @param roleId
	 * @param num
	 * @return
	 */
	public boolean changeGold(Player player, int num, Reasons reason, long action) {
		if (num >= 0) {
			return false;
		}
		boolean isscript = false;
		boolean isinsert = false;
		int goldnum = num;
		Gold gold = player.getGold();
		if (gold != null) {
			isinsert = false;
			goldnum += gold.getGold();
		} else {
			isinsert = true;
			gold = new Gold();
			gold.setBuyitemresume(0);
			gold.setCostGold(0);
			gold.setFaildrollBackadd(0);
			gold.setGettempybadd(0);
			gold.setGold(0);
			gold.setHuokuanAdd(0);
			gold.setJiaoyiresume(0);
			gold.setJiaoyiybadd(0);
			gold.setServerId(player.getCreateServerId());
			gold.setShangjiaresume(0);
			gold.setTotalGold(0);
			gold.setTwgmadd(0);
			gold.setUserId(player.getUserId());
			gold.setYbxiajiaadd(0);
//			gold.setIsinner(0);
		}
		
		if (goldnum < 0 || goldnum > Global.BAG_MAX_GOLD) {
			if (num >= 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包元宝数己达上限"));
			}
			return false;
		}
		player.setGold(gold);
		int before = gold.getGold();
		Gold saveGold = new Gold();
		saveGold.setBuyitemresume(0);
		saveGold.setCostGold(0);
		saveGold.setFaildrollBackadd(0);
		saveGold.setGettempybadd(0);
		saveGold.setHuokuanAdd(0);
		saveGold.setJiaoyiresume(0);
		saveGold.setJiaoyiybadd(0);
		saveGold.setShangjiaresume(0);
		saveGold.setTotalGold(0);
		saveGold.setTwgmadd(0);
		saveGold.setYbxiajiaadd(0);

		saveGold.setUserId(player.getUserId());
		saveGold.setServerId(player.getCreateServerId());
		saveGold.setGold(num);

		gold.setGold(gold.getGold() + num);

		if (num > 0) {
			switch (reason) {
				case STYBGOUMAI:
					saveGold.setTwgmadd(num);
					gold.setTwgmadd(gold.getTwgmadd() + num);
					break;
				case STYBXIAJIA:
					saveGold.setYbxiajiaadd(num);
					gold.setYbxiajiaadd(gold.getYbxiajiaadd() + num);
					break;
				case STYBHUOKUAN:
					saveGold.setHuokuanAdd(num);
					gold.setHuokuanAdd(gold.getHuokuanAdd() + num);
					break;
				case JIAOYIYB:
					saveGold.setJiaoyiybadd(num);
					gold.setJiaoyiybadd(gold.getJiaoyiybadd() + num);
					break;
				case STGOUMAISHIBAI:
					saveGold.setFaildrollBackadd(num);
					gold.setFaildrollBackadd(gold.getFaildrollBackadd() + num);
					break;
				case QUCHUTMPYB:
					saveGold.setGettempybadd(num);
					gold.setGettempybadd(gold.getGettempybadd() + num);
					break;
				case GOLD_RECHARGE:
					saveGold.setTotalGold(num);
					gold.setTotalGold(gold.getTotalGold() + num);
					break;
				default:
					// 非交易类增加
					break;
			}
		} else {
			//元宝上架	元宝交易	元宝购买道具不算消耗 需 另计
			switch (reason) {
				case JIAOYIYB:
					saveGold.setJiaoyiresume(num);
					gold.setJiaoyiresume(gold.getJiaoyiresume() + num);
					break;
				case STYBSHANGJIA:
					saveGold.setShangjiaresume(num);
					gold.setShangjiaresume(gold.getShangjiaresume() + num);
					break;
				case STYBGOUMAIDAOJU:
					saveGold.setBuyitemresume(num);
					gold.setBuyitemresume(gold.getBuyitemresume() + num);
					break;
				default:
					//非交易类消耗
					saveGold.setCostGold(num);
					gold.setCostGold(gold.getCostGold() + num);
					isscript = true;
					saveGoldExpend(player, num, reason);
					break;
			}
		}
		//充值 忽略 
		if (reason != Reasons.GOLD_RECHARGE) {
			saveGold(saveGold, isinsert);
		}
		sendGoldInfo(player);
		if (num > 0) {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("获得{1}元宝"), String.valueOf(Math.abs(num)));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得{1}元宝"), String.valueOf(Math.abs(num)));
		} else {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("失去{1}元宝"), String.valueOf(Math.abs(num)));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("失去{1}元宝"), String.valueOf(Math.abs(num)));
			try {
				if (player.getTransactionsinfo() != null && reason != Reasons.JIAOYIYB) {
					ManagerPool.transactionsManager.stChangeState(player);	//解锁交易
				}
			} catch (Exception e) {
				log.error(e, e);
			}
			TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COSTGOLD, Math.abs(num));
		}
		GoldChangeLog log = new GoldChangeLog();
		log.setActionId(action);
		log.setBeforenum(before);
		log.setAfternum(goldnum);
		log.setNum(num);
		log.setReason(reason.getValue());
		log.setRoleId(player.getId());
		log.setUserId(player.getUserId());
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
		
		if (isscript) {//非交易类消耗元宝，触发脚本
			IConsumeGoldScript script = (IConsumeGoldScript)ManagerPool.scriptManager.getScript(ScriptEnum.RETBINDGOLD);
			if(script!=null){
				try{
					script.consumegold(player, num, reason, action);
				}catch (Exception e) {
					this.log.error(e,e);
				}
			}
		}
		
		return true;
	}
	
	private void saveGoldExpend(Player player, int goldnum, Reasons reason) {
		if (goldnum >= 0) {
			return ;
		}
		
		ReqSyncPlayerGoldExpendMessage msg = new ReqSyncPlayerGoldExpendMessage();
		msg.setPlayerId(player.getId());
		msg.setGold(-goldnum);
		msg.setReason(reason.ordinal());
		MessageUtil.send_to_world(msg);
	}

	public void loadGold(Player player) {
		Gold gold = dao.select(player.getUserId(), player.getCreateServerId());
		player.setGold(gold);
	}

	public int saveGold(Gold gold, boolean insert) {
		try {
//			if(insert)
//				return dao.insert(gold);
//			else
//				return dao.update(gold);
			WServer.getInstance().getSaveGoldThread().addGold(gold, insert);
		} catch (Exception e) {
			log.error(e, e);
		}
		return 0;
	}

//	/**
//	 * 礼金
//	 *
//	 * @param player
//	 * @param num
//	 * @return
//	 */
//	public boolean changeGoldFirstBind(Player player, int num, Reasons reason, long action) {
//		if (num == 0) {
//			return true;
//		}
//		if (num < 0) {
//			return false;
//		}
//		int goldnum = 0;
//		if (player.getGold() != null) {
//			goldnum = player.getGold().getGold();
//		}
//		if ((long) player.getBindGold() + goldnum < num) {
//			return false;
//		}
//		num = Math.abs(num);//无论如何都是扣除
//		if (player.getBindGold() >= num) {
//			changeBindGold(player, -num, reason, action);
//		} else {
//			int cost = num;
//			if (player.getBindGold() > 0) {
//				cost -= player.getBindGold();
//				changeBindGold(player, -player.getBindGold(), reason, action);
//			}
//			changeGold(player, -cost, reason, action);
//		}
//		return true;
//	}

	/**
	 * （可领取元宝）改变
	 *
	 * @param player
	 * @param num
	 * @return
	 */
	public boolean changeTmpGold(Player player, int num) {
		if (num == 0) {
			return true;
		}
		int gold = player.getCanreceiveyuanbao() + num;
		if (gold < 0 || gold > Global.BAG_MAX_GOLD) {
			if (num < 0) {
//				MessageUtil.notify_player(player, Notifys.ERROR, "操作失败，元宝数量不足");
				return false;
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包元宝数己达上限"));
				return false;
			}
		}
		player.setCanreceiveyuanbao(gold);
		sendCanreceiveYuanbaoInfo(player);
		return true;
	}

	/**
	 * 礼金
	 *
	 * @param roleId
	 * @param num
	 * @param action
	 * @return
	 */
	public boolean changeBindGold(Player player, int num, Reasons reasons, long action) {
		if (num > 0 && PlayerManager.CHECK_NONAGE) {
			if (player.getNonage() == 2) {
				num = num / 2;
			} else if (player.getNonage() == 3) {
				sendBindGoldInfo(player);
				return true;
			}
		}
		if (num == 0) {
			return true;
		}
		int bindgold = player.getBindGold() + num;
		if (bindgold < 0 || bindgold > Global.BAG_MAX_GOLD) {
			if (num >= 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，礼金数己达上限"));
			}
			return false;
		}

		int before = player.getBindGold();
		player.setBindGold(bindgold);
		sendBindGoldInfo(player);
		if (num > 0) {   //验证通过 记录 累积获得礼金 与 累积消耗礼金 排除社交原因
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得{1}礼金"), String.valueOf(Math.abs(num)));
			player.setTotalgetbindgold(player.getTotalgetbindgold() + num);  //玩家获得绑定元宝
			WServer.sgeneratebindgold.addAndGet(num); //服务器产出绑定元宝
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("失去{1}礼金"), String.valueOf(Math.abs(num)));
			player.setTotalconsumebindgold(player.getTotalconsumebindgold() + Math.abs(num)); //玩家消耗绑定元宝
			WServer.sconsumebindgold.addAndGet(Math.abs(num));  //服务器消耗绑定元宝
			TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COSTBINDGOLD, Math.abs(num));
		}
		BindGoldChangeLog log = new BindGoldChangeLog();
		log.setActionId(action);
		log.setBeforenum(before);
		log.setRoleId(player.getId());
		log.setAfternum(bindgold);
		log.setNum(num);
		log.setReason(reasons.getValue());
		log.setUserId(player.getUserId());
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
		return true;
	}

	/**
	 * 清空包裹
	 *
	 * @param player
	 */
	public void clear(Player player) {
		ConcurrentHashMap<String, Item> backpackItems = player.getBackpackItems();
		long actionId = Config.getId();
		for (Entry<String, Item> entry : backpackItems.entrySet()) {
			try {
				Item item = entry.getValue();
				Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
				if (model.getQ_log() == 1) {
					ItemChangeLog log = new ItemChangeLog();
					log.setActionId(actionId);
					log.setAction(ItemAction.REMOVE.toString());
					log.setChangeAction(ItemChangeAction.REMOVE.toString());
					log.setItemafterInfo("");
					log.setItembeforeInfo(JSONserializable.toString(item));
					log.setItemid(item.getId());
					log.setModelid(item.getItemModelId());
					log.setNum(item.getNum());
					log.setReason(Reasons.GMCOMMAND.getValue());
					log.setRoleid(player.getId());
					log.setUserId(player.getUserId());
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		player.getBackpackItems().clear();
		BackpackManager.getInstance().sendItemInfos(player);
	}

	/**
	 * 可领取任务物品增加
	 *
	 * @param player
	 * @param spilthGoods
	 */
	public void addAbleReceieve(Player player, List<Item> spilthGoods) {
		if (spilthGoods == null || spilthGoods.size() <= 0) {
			return;
		}
		long actionId = Config.getId();
		String before = JSONserializable.toString(player.getTaskRewardsReceiveAble());
		List<Item> removeList = new ArrayList<Item>();
		for (Item item : spilthGoods) {
			player.getTaskRewardsReceiveAble().add(0, item);
		}
		while (player.getTaskRewardsReceiveAble().size() > 14) {
			Item remove = player.getTaskRewardsReceiveAble().get(player.getTaskRewardsReceiveAble().size() - 1);
			player.getTaskRewardsReceiveAble().remove(remove);
			removeList.add(remove);
		}

		sendAbleReceiveInfo(player);
		AbleReceiveChangeLog log = new AbleReceiveChangeLog();
		log.setAction("remove");
		log.setActionId(actionId);
		log.setRoleId(player.getId());
		log.setAfterList(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
		log.setBeforeList(before);
		log.setItem(JSONserializable.toString(removeList));
		log.setReason("add");
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
		AbleReceiveChangeLog addlog = new AbleReceiveChangeLog();
		addlog.setAction("add");
		addlog.setActionId(actionId);
		addlog.setRoleId(player.getId());
		addlog.setAfterList(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
		addlog.setBeforeList(before);
		addlog.setItem(JSONserializable.toString(spilthGoods));
		addlog.setReason("add");
		addlog.setSid(player.getCreateServerId());
		LogService.getInstance().execute(addlog);
	}

	/**
	 * 可领取任务物品删除
	 *
	 * @param player
	 */
	public void removeAbleReceieveAll(Player player) {
		long actionId = Config.getId();
		String before = JSONserializable.toString(player.getTaskRewardsReceiveAble());
		player.getTaskRewardsReceiveAble().clear();
		sendAbleReceiveInfo(player);
		try {
			AbleReceiveChangeLog log = new AbleReceiveChangeLog();
			log.setAction("remove");
			log.setActionId(actionId);
			log.setAfterList("");
			log.setRoleId(player.getId());
			log.setBeforeList(before);
			log.setItem(before);
			log.setReason("receieve");
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			log.error(e, e);
		}


	}

	private void sendAbleReceiveInfo(Player player) {
		ResRewardsAbleActMessage msg = new ResRewardsAbleActMessage();
		List<ItemInfo> result = new ArrayList<ItemInfo>();
		List<Item> taskRewardsReceiveAble = player.getTaskRewardsReceiveAble();
		for (Item item : taskRewardsReceiveAble) {
			result.add(item.buildItemInfo());
		}
		msg.setAbleAct(result);
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 计算批量增加物品至包裹需要的最少空格数
	 *
	 * @author tangchao
	 * @param items
	 * @return
	 */
	public int needEmptyCellNum(Player player, Collection<Item> items) {
		int emptynum = getEmptyGridNum(player);
		// 新增列表是有物品的
		if (items == null || items.size() == 0 || emptynum >= items.size()) {
			return 0;
		}
		int need = -1 * emptynum;
		// 得到物品模型
		HashMap<Integer, Q_itemBean> goodmodels = DataManager.getInstance().q_itemContainer.getMap(); // 取得物品模型
		// KEY+要加入的数量  合并处理新增物品中可堆叠的 不计上限先堆叠在一起
		HashMap<String, Integer> mergedmap = new HashMap<String, Integer>();
		// KEY+Model
		HashMap<String, Q_itemBean> mergedModel = new HashMap<String, Q_itemBean>();

		for (Item item : items) {
			// key= 模型id+失效时间+绑定属性+最大堆叠数 这些属性都相等的物品才是同类的可堆叠物品
			String key = getKey(item);
			Integer num = mergedmap.get(key);
			num = num == null ? 0 : num;
			mergedmap.put(key, num + item.getNum());
			Q_itemBean bean = goodmodels.get(item.getItemModelId());
			mergedModel.put(key, bean);
		}

		// KEY+包裹 中可合并的数量
		for (String key : mergedmap.keySet()) {
			int ableAddcount = 0;
			for (String cellid : player.getBackpackItems().keySet()) {
				Item item = player.getBackpackItems().get(cellid);
				Q_itemBean bean = goodmodels.get(item.getItemModelId());
				if (getKey(item).equals(key)) {
					ableAddcount += bean.getQ_max() - item.getNum();
				}
			}
			mergedmap.put(key, mergedmap.get(key) - ableAddcount);
		}
		for (String key : mergedmap.keySet()) {
			Q_itemBean model = mergedModel.get(key);
			Integer sum = mergedmap.get(key);
			need += Math.ceil((double) sum / model.getQ_max());
		}
		// 返回加入这些物品还需要的格子数
		return need <= 0 ? 0 : need;
	}

	private String getKey(Item item) {
		StringBuilder bagkeys = new StringBuilder();
		bagkeys.append(item.getItemModelId()).append("_").append(item.getLosttime()).append("_").append(item.isBind());
		return bagkeys.toString();
	}

	/**
	 * 是否记录日志
	 *
	 * @param itemmodel	物品模型ID
	 * @return
	 */
	public boolean isLog(int itemmodel) {
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(itemmodel);
		if (q_itemBean != null && q_itemBean.getQ_log() == 1) {
			return true;
		}
		return false;
	}

	public String getName(int itemmodel) {
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(itemmodel);
		if (model == null) {
			return ResManager.getInstance().getString("未知类型的物品");
		}
		String q_name = model.getQ_name();
		if (!StringUtil.isBlank(q_name) && q_name.contains(Symbol.XIAHUAXIAN_REG)) {
			String[] split = q_name.split(Symbol.XIAHUAXIAN_REG);
			return split[0];
		}
		return q_name;
	}

	/**
	 * 解析item buff并且使用
	 *
	 * @param player
	 * @param buffString
	 * @return
	 */
	public int setItemBuff(Player player, String buffString, int num) {
		String[] buffs = buffString.split(Symbol.FENHAO);
		int result = 0;
		for (String string : buffs) {
			int buffid = Integer.parseInt(string);
			result = ManagerPool.buffManager.addBuff(player, player, buffid, num, 0, 0, 0, 0);
			if (result == 0) {
				//MessageUtil.notify_player(player, Notifys.ERROR, "使用出错。");
				return 0;
			}
		}
		return result;
	}

	/**
	 * 获得仓库物品数量
	 *
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	public int getWarehouseItemNum(Player player, int itemModelId) {
		int num = 0;
		Iterator<Item> iter = player.getStoreItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isLost()) {
				num += item.getNum();
			}
		}
		return num;
	}
	
	/**
	 * 创建多件物品
	 * @param player 玩家
	 * @param itemstr 物品字符串，格式(物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数；...)
	 * @param itemlist 物品集合，一般为new ArrayList()
	 * @return 创建的物品占背包格子数
	 */
	public int createItems(Player player, String itemstr, List<Item> itemlist){
		String[] itemstrs = itemstr.split(Symbol.FENHAO_REG);
		int allitemnum = 0;
		for (int i = 0; i < itemstrs.length; i++) {
			String[] itemdataStrings = itemstrs[i].split(Symbol.DOUHAO_REG);
			//物品id
			int itemid = 0;
			//物品数量
			int itemnum = 0;
			//性别
			int sex = 0;//0都可1 男 2 女
			//是否绑定
			boolean bind = true;
			//过期时间
			long losttime = 0;
			//升级
			int gradenum = 0;
			//附加条数
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
			if (itemdataStrings.length >= 2) {
				if (itemid != 0 && itemnum != 0) {
					if (sex == 0 || sex == player.getSex()) {
						switch (itemid) {
							//铜币
							case -1: {
								Item moneyItem = Item.createMoney(itemnum);
								if (moneyItem != null) {
									itemlist.add(moneyItem);
								}
							}
							break;
//							//元宝
//							case -2: {
//								Item goldItem = Item.createGold(itemnum, true);
//								if (goldItem != null) {
//									itemlist.add(goldItem);
//								}
//							}
//							break;
							//真气
							case -3: {
								Item zhenqiItem = Item.createZhenQi(itemnum);
								if (zhenqiItem != null) {
									itemlist.add(zhenqiItem);
								}
							}
							break;
							//经验
							case -4: {
								Item expItem = Item.createExp(itemnum);
								if (expItem != null) {
									itemlist.add(expItem);
								}
							}
							break;
							//礼金
							case -5: {
								Item bindgoldItem = Item.createBindGold(itemnum);
								if (bindgoldItem != null) {
									itemlist.add(bindgoldItem);
								}
							}
							break;
							//战魂
							case -6: {
								Item fightspiritItem = Item.createFightSpirit(itemnum);
								if (fightspiritItem != null) {
									itemlist.add(fightspiritItem);
								}
							}
							break;
							//军功
							case -7: {
								Item rankItem = Item.createRank(itemnum);
								if (rankItem != null) {
									itemlist.add(rankItem);
								}
							}
							break;
							default: {
								List<Item> items = Item.createItems(itemid, itemnum, bind, losttime == 0 ? losttime : System.currentTimeMillis() + losttime * 1000, gradenum, append);
								if (!items.isEmpty()) {
									itemlist.addAll(items);
									allitemnum += items.size();
								}
							}
							break;
						}
					}
				}
			}
		}
		
		return allitemnum;
	}
	
	/**
	 * 增加多件物品
	 * @param player 玩家
	 * @param itemlist 物品列表
	 */
	public void addItems(Player player, List<Item> itemlist, long id){
		if (!itemlist.isEmpty()) {
			for (int i = 0; i < itemlist.size(); i++) {
				Item item = itemlist.get(i);
				if (item != null) {
					if (item.getItemModelId() == -1) {//Money
						if (!BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.ACTIVITY_GIFT, id)) {
							MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击附件领取。"), (byte) 1, item.getNum(), null);
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击邮件领取。"));
						}
//					} else if (item.getItemModelId() == -2) {//Gold
//						if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, id)) {
//							List<Item> items = new ArrayList<Item>();
//							items.add(Item.createBindGold(item.getNum()));
//							MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
//							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中礼金未领取成功，请点击邮件领取。"));
//						}
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
						if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, id)) {
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
						if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, id)) {
							MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，该物品未领取成功，请点击附件领取。"), (byte) 1, 0, items);
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您的活动中有物品未领取成功，请点击邮件领取。"));
						}
//						else {
//							ItemReasonsInfo itemReasonsInfo = new ItemReasonsInfo();
//							if (!items.isEmpty()) {
//								itemReasonsInfo.setItemId(items.get(0).getId());
//							}
//							itemReasonsInfo.setItemModelId(item.getItemModelId());
//							itemReasonsInfo.setItemNum(item.getNum());
//							itemReasonsInfo.setItemReasons(0);
//							sendMessage.getItemReasonsInfoList().add(itemReasonsInfo);
//						}
					}
				}
			}
		}
	}
	
	
	
	/**玩家得到道具提示
	 * 
	 * @param player
	 * @param itemstr
	 */
	public void notifyitemname(Player player ,String itemstr){
		String[] itemstrs = itemstr.split(Symbol.FENHAO_REG);
		for (int i = 0; i < itemstrs.length; i++) {
			String[] itemdata = itemstrs[i].split(Symbol.DOUHAO_REG);
			if (itemdata.length >= 2) {
				Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(Integer.valueOf(itemdata[0]));
				if (model != null) {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得{1}（{2}）"), model.getQ_name(),itemdata[1]);
				}
			}
		}
	}
	
	
}
