package com.game.bag.manager;

import java.util.Iterator;
import org.apache.log4j.Logger;
import com.game.backpack.log.ItemChangeLog;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemAction;
import com.game.backpack.structs.ItemChangeAction;
import com.game.backpack.structs.OtherReason;
import com.game.bag.message.ResBagItemAddMessage;
import com.game.bag.message.ResBagItemRemoveMessage;
import com.game.bag.message.ResBagItemUpdateMessage;
import com.game.bag.message.ResBagSynMessage;
import com.game.bag.structs.Bag;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

public class BagManager {
	private Logger log = Logger.getLogger(BagManager.class);
	private static Object obj = new Object();
	private static BagManager me;
	private BagManager() {}
	public static BagManager getInstance() {
		synchronized (obj) {
			if (me == null) {
				me = new BagManager();
			}
		}
		return me;
	}
	
	private Bag getByType(Player player, int type) {
		return player.getGlobalBag();
	}
	
	private Item getItemById(Bag bag, long itemId) {
		Iterator<Item> it = bag.getItems().values().iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getId() == itemId) return item;
		}
		return null;
	}
	
	private Item getItemByCell(Bag bag, int grid) {
		return bag.getItems().get(String.valueOf(grid));
	}
	
	public void move(Player player, long itemId, int num, int toGridId, int type) {
		if (num < 0 || toGridId < 1) return ;
		
		Bag bag = getByType(player, type);
		if (bag == null) return ;
		
		if (bag.isLocked()) {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包被锁定");
			return ;
		}
		
		Item item = getItemById(bag, itemId);
		if (item == null) return ;
		
		if (num > item.getNum()) {
			return;
		}
		
		if (item.isTrade()) return ;
		
		if (item.getGridId() == toGridId) return;
		
		Item destItem = getItemByCell(bag, toGridId);
		if (destItem == null) {
			if (item.getNum() > num) { // 拆分
				try {
					Item clone;
					clone = item.clone();
					clone.setId(Config.getId());
					clone.setNum(num);
					clone.setGridId(toGridId);
					item.setNum(item.getNum() - num);
					bag.getItems().put(String.valueOf(clone.getGridId()), clone);
					MessageUtil.tell_player_message(player, getItemAddMsg(type, clone));
					MessageUtil.tell_player_message(player, getItemUpdateMsg(type, item));
					logItemSplit(player, item, clone, type);
				} catch (CloneNotSupportedException e) {
					log.error(e, e);
				}
			}
			else { // 直接移动
				logItemMove(player, item, toGridId, type);
				bag.getItems().remove(String.valueOf(item.getGridId()));
				item.setGridId(toGridId);
				bag.getItems().put(String.valueOf(item.getGridId()), item);
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, item));
			}
		}
		else {
			byte flag = 0; // 0:do nothing 1:交换  2:合并  3:部分合并
			Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
			if (itemBean == null) return ;
			if (item.getItemModelId() == destItem.getItemModelId()) {
				if (num + destItem.getNum() > itemBean.getQ_max()
						|| item.getLosttime() != destItem.getLosttime()) {
					flag = 1;
				}
				else {
					if (num < item.getNum()) {
						flag = 3;
					}
					else {
						flag = 2;
					}
				}
			}
			else {
				flag = 1;
			}
			
			if (flag == 1) {
				logItemInterchange(player, item, destItem, type);
				bag.getItems().remove(String.valueOf(item.getGridId()));
				bag.getItems().remove(String.valueOf(destItem.getGridId()));
				destItem.setGridId(item.getGridId());
				item.setGridId(toGridId);
				bag.getItems().put(String.valueOf(item.getGridId()), item);
				bag.getItems().put(String.valueOf(destItem.getGridId()), destItem);
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, item));
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, destItem));
			}
			else if (flag == 2) {
				logItemCombine(player, item, destItem, type);
				bag.getItems().remove(String.valueOf(item.getGridId()));
				destItem.setNum(destItem.getNum() + item.getNum());
				MessageUtil.tell_player_message(player, getItemRemoveMsg(type, item));
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, destItem));
			}
			else if (flag == 3) {
				int oldNum = item.getNum();
				item.setNum(num);
				logItemCombine(player, item, destItem, type);
				item.setNum(oldNum - num);
				destItem.setNum(destItem.getNum() + num);
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, item));
				MessageUtil.tell_player_message(player, getItemUpdateMsg(type, destItem));
			}
			else {
				return ;
			}
		}
	}

	// 这里直接将背包里面的物品移动到跨服背包里面
	public void addItem(Player player, long cellId, int type) {
		@SuppressWarnings("deprecation")
		Item item = player.getBackpackItems().get(String.valueOf(cellId));
		if(item == null) return;
		
		Bag bag = getByType(player, type);
		if (bag == null) return ;
		
		if (bag.isLocked()) {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包被锁定");
			return ;
		}
		
		int gridId = getFirstEmptyGrid(bag);
		if (gridId < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，空间不足"));
			return;
		}
		
		if(item.isTrade()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，物品正在交易中"));
			return;
		}
		
		if(ManagerPool.backpackManager.removeItem(player, item.getId(), Reasons.BAGTOGLOBAL, Config.getId())){
			item.setGridId(gridId);
			bag.getItems().put(String.valueOf(item.getGridId()), item);
			MessageUtil.tell_player_message(player, this.getItemAddMsg(0, item));
			logItemMoveIn(player, item, type);
		}
	}
	
	private void logItemMoveIn(Player player, Item item, int type) {
		ItemChangeLog log = new ItemChangeLog();
		log.setActionId(Config.getId());
		log.setAction(ItemAction.MOVE.toString());
		log.setChangeAction(ItemChangeAction.GLOBAL_MOVE.toString());
		item.setGridId(item.getGridId());
		log.setItemafterInfo(JSONserializable.toString(item));
		log.setItembeforeInfo("");
		log.setItemid(item.getId());
		log.setModelid(item.getItemModelId());
		log.setNum(item.getNum());
		log.setReason(OtherReason.move);
		log.setRoleid(player.getId());
		log.setUserId(player.getUserId());
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
	}
	
	private int getFirstEmptyGrid(Bag bag) {
		for (int i = 1; i <= bag.getCellNum(); ++i) {
			if (!bag.getItems().containsKey(String.valueOf(i))) return i;
		}
		return 0;
	}
	
	public void syn(Player player, int type) {
		Bag bag = getByType(player, type);
		if (bag == null) return ;
		
		if (bag.isLocked()) {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包被锁定");
			return ;
		}
		
		MessageUtil.tell_player_message(player, getSynMsg(type, bag));
	}
	
	private Message getSynMsg(int type, Bag bag) {
		ResBagSynMessage msg = new ResBagSynMessage();
		msg.setCellnum(bag.getCellNum());
		Iterator<Item> it = bag.getItems().values().iterator();
		while (it.hasNext()) {
			Item item = it.next();
			msg.getItems().add(item.buildItemInfo());
		}
		return msg;
	}
	
	// 这里直接将物品移除到背包里面
	public void removeItem(Player player, int cellId, int type) {
		Bag bag = getByType(player, type);
		if (bag == null) return ;
		
		if (bag.isLocked()) {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包被锁定");
			return ;
		}
		
		Item item = this.getItemByCell(bag, cellId);
		if (item == null) return ;
		
		if(ManagerPool.backpackManager.getEmptyGridNum(player) > 0){
			bag.getItems().remove(String.valueOf(item.getGridId()));
			item.setGridId(-1);
			MessageUtil.tell_player_message(player, this.getItemRemoveMsg(0, item));
			BackpackManager.getInstance().addItem(player, item, Reasons.GLOBALTOBAG, Config.getId());
		}
	}
	
	private Message getItemRemoveMsg(int type, Item item) {
		ResBagItemRemoveMessage msg = new ResBagItemRemoveMessage();
		msg.setItemId(item.getId());
		return msg;
	}
	
	private Message getItemUpdateMsg(int type, Item item) {
		ResBagItemUpdateMessage msg = new ResBagItemUpdateMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}
	
	private Message getItemAddMsg(int type, Item item) {
		ResBagItemAddMessage msg = new ResBagItemAddMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}

	private void logItemCombine(Player player, Item item, Item destItem, int type) {
		{
			ItemChangeLog log = new ItemChangeLog();
			log.setActionId(Config.getId());
			log.setAction(ItemAction.MOVE.toString());
			log.setChangeAction(ItemChangeAction.GLOBAL_CHANGE.toString());
			log.setItemafterInfo(JSONserializable.toString(destItem.getGridId()));
			log.setItembeforeInfo(JSONserializable.toString(destItem));
			log.setItemid(destItem.getId());
			log.setModelid(destItem.getItemModelId());
			log.setNum(item.getNum());
			log.setReason(OtherReason.combin);
			log.setRoleid(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}

		{
			ItemChangeLog log = new ItemChangeLog();
			log.setActionId(Config.getId());
			log.setAction(ItemAction.MOVE.toString());
			log.setChangeAction(ItemChangeAction.GLOBAL_REMOVE.toString());
			log.setItemafterInfo("");
			log.setItembeforeInfo(JSONserializable.toString(item));
			log.setItemid(item.getId());
			log.setModelid(item.getItemModelId());
			log.setNum(item.getNum());
			log.setReason(OtherReason.combin);
			log.setRoleid(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}
	}
	private void logItemInterchange(Player player, Item item, Item destItem, int type) {
		{
			int tmpGrid = destItem.getGridId();
			ItemChangeLog log = new ItemChangeLog();
			log.setActionId(Config.getId());
			log.setAction(ItemAction.MOVE.toString());
			log.setChangeAction(ItemChangeAction.GLOBAL_MOVE.toString());
			destItem.setGridId(item.getGridId());
			log.setItemafterInfo(JSONserializable.toString(destItem));
			destItem.setGridId(tmpGrid);
			log.setItembeforeInfo(JSONserializable.toString(destItem));
			log.setItemid(destItem.getId());
			log.setModelid(destItem.getItemModelId());
			log.setNum(destItem.getNum());
			log.setReason(OtherReason.move);
			log.setRoleid(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}

		{
			int tmpGrid = item.getGridId();
			ItemChangeLog removelog = new ItemChangeLog();
			removelog.setActionId(Config.getId());
			removelog.setAction(ItemAction.MOVE.toString());
			removelog.setChangeAction(ItemChangeAction.GLOBAL_MOVE.toString());
			item.setGridId(destItem.getGridId());
			removelog.setItemafterInfo(JSONserializable.toString(item));
			item.setGridId(tmpGrid);
			removelog.setItembeforeInfo(JSONserializable.toString(item));
			removelog.setItemid(item.getId());
			removelog.setModelid(item.getItemModelId());
			removelog.setNum(item.getNum());
			removelog.setReason(OtherReason.move);
			removelog.setRoleid(player.getId());
			removelog.setUserId(player.getUserId());
			removelog.setSid(player.getCreateServerId());
			LogService.getInstance().execute(removelog);
		}
	}
	private void logItemMove(Player player, Item item, int toGridId, int type) {
		int oldGrid = item.getGridId();
		ItemChangeLog log = new ItemChangeLog();
		log.setActionId(Config.getId());
		log.setAction(ItemAction.MOVE.toString());
		log.setChangeAction(ItemChangeAction.GLOBAL_MOVE.toString());
		item.setGridId(toGridId);
		log.setItemafterInfo(JSONserializable.toString(item));
		item.setGridId(oldGrid);
		log.setItembeforeInfo(JSONserializable.toString(item));
		log.setItemid(item.getId());
		log.setModelid(item.getItemModelId());
		log.setNum(item.getNum());
		log.setReason(OtherReason.move);
		log.setRoleid(player.getId());
		log.setUserId(player.getUserId());
		log.setSid(player.getCreateServerId());
		LogService.getInstance().execute(log);
	}
	
	private void logItemSplit(Player player, Item item, Item splitItem, int type) {
		{
			ItemChangeLog log = new ItemChangeLog();
			log.setActionId(Config.getId());
			log.setAction(ItemAction.MOVE.toString());
			log.setChangeAction(ItemChangeAction.GLOBAL_ADD.toString());
			log.setItemafterInfo(JSONserializable.toString(splitItem));
			log.setItembeforeInfo("");
			log.setItemid(splitItem.getId());
			log.setModelid(splitItem.getItemModelId());
			log.setNum(splitItem.getNum());
			log.setReason(OtherReason.slip);
			log.setRoleid(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}
		
		{
			ItemChangeLog log = new ItemChangeLog();
			log.setActionId(Config.getId());
			log.setAction(ItemAction.MOVE.toString());
			log.setChangeAction(ItemChangeAction.GLOBAL_CHANGE.toString());
			log.setItemafterInfo(JSONserializable.toString(item));
			log.setItembeforeInfo("");
			log.setItemid(item.getId());
			log.setModelid(item.getItemModelId());
			log.setNum(splitItem.getNum());
			log.setReason(OtherReason.slip);
			log.setRoleid(player.getId());
			log.setUserId(player.getUserId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}
	}
	
	public void requestSynGlobalBag(Player player) {
		// TODO 发送请求同步消息到跨服服务器
		
	}
	public void saveGlobalBag(Player player) {
		// TODO 将跨服包裹信息发送到跨服服务器保存
	}
}
