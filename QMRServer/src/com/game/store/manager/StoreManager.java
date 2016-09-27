package com.game.store.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.backpack.log.CellOpenLog;
import com.game.backpack.log.ClearUpLog;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemChangeAction;
import com.game.backpack.structs.OtherReason;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_backpack_gridBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.dblog.bean.BaseLogBean;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.npc.manager.NpcManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.store.log.StoreItemChangeLog;
import com.game.store.message.ResStoreCellTimeMessage;
import com.game.store.message.ResStoreItemAddMessage;
import com.game.store.message.ResStoreItemChangeMessage;
import com.game.store.message.ResStoreItemInfosMessage;
import com.game.store.message.ResStoreItemRemoveMessage;
import com.game.store.message.ResStoreOpenCellResultMessage;
import com.game.store.struts.StoreAction;
import com.game.structs.Reasons;
import com.game.utils.CodedUtil;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.ZipUtil;

@SuppressWarnings("deprecation")
public class StoreManager {
	private Logger log = Logger.getLogger(StoreManager.class);
		
	private static Object obj = new Object();

	//玩家管理类实例
	private static StoreManager manager;
	
	private StoreManager(){}
	
	public static StoreManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new StoreManager();
			}
		}
		return manager;
	}
	
	/**
	 * 出库
	 * @param roleId
	 * @param cellId
	 */
	public void storeToBagMsg(Player player, long cellId,int npcid) {
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		if(!checkAble(player, npcid)){
			return;
		}
		Item item = player.getStoreItems().get(String.valueOf(cellId));
		if (item == null) {
			return;
		}
//		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
//		if(model.getQ_save_warehouse()==1){
//			MessageUtil.notify_player(player, Notifys.ERROR,"该物品不允许加入仓库");
//			return;
//		}
		if (!BackpackManager.getInstance().hasAddSpace(player, item)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，包裹空间不足"));
			return;
		}
		if (removeItem(player, item.getId())) {
			long action = Config.getId();
			
			if(BackpackManager.getInstance().isLog(item.getItemModelId())){
				StoreItemChangeLog createLog = StoreItemChangeLog.createLog(player.getId(),item.getId(),item.getItemModelId(),item.getNum(),JSONserializable.toString(item),"",Reasons.STORETOBAG.getValue(),StoreAction.STORETOBAG.toString(),action,ItemChangeAction.REMOVE.toString(),player.getCreateServerId());
				LogService.getInstance().execute(createLog);
			}
			item.setGridId(-1);
			ManagerPool.backpackManager.addItem(player, item,Reasons.STORETOBAG,action);
		}
	}
	
	/**
	 * 入库
	 * @param roleId
	 * @param cellId
	 */
	public void bagToStoreMsg(Player player, long cellId,int npcid){
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		if(!checkAble(player, npcid)){
			return;
		}
		Item item = player.getBackpackItems().get(String.valueOf(cellId));
		if(item==null){
			return;
		}
		int gridId =getStoreFirstEmptGridId(player);
		if (gridId == -1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，仓库空间不足"));
			return;
		}
		Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		if(model.getQ_save_warehouse()==1){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("该物品不允许加入仓库"));
			return;
		}
		if(item.isTrade()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，物品正在交易中"));
			return;
		}
		long action=Config.getId();
		if(ManagerPool.backpackManager.removeItem(player, item.getId(),Reasons.BAGTOSTORE,action)){
			addItem(player, item);
			if(BackpackManager.getInstance().isLog(item.getItemModelId())){
				StoreItemChangeLog createLog = StoreItemChangeLog.createLog(player.getId(),item.getId(),item.getItemModelId(),item.getNum(),"",JSONserializable.toString(item),Reasons.BAGTOSTORE.getValue(),StoreAction.BAGTOSTORE.toString(), action,ItemChangeAction.ADD.toString(),player.getCreateServerId());
				LogService.getInstance().execute(createLog);
			}
		}
	}
	
	private boolean addItem(Player player, Item item) {
		// 数据检查
		if (item.getNum() <= 0)
			return false;
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null)
			return false;
		// 获得玩家
//		Player player = ManagerPool.playerManager.getPlayer(roleId);
		if (player == null)
			return false;
		int num = item.getNum();
		if (num > model.getQ_max()) {
			return false;
		}
		int gridId =getStoreFirstEmptGridId(player);
		if (gridId == -1) {
			log.error("仓库包己满外部调用的检查逻辑有问题");
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，仓库空间不足"));
			return false;
		}
		// 加入包裹中
		item.setGridId(gridId);
		player.getStoreItems().put(String.valueOf(item.getGridId()), item);
		MessageUtil.tell_player_message(player, getItemAddMessage(item));
		ManagerPool.playerManager.savePlayer(player);
		return true;
	}

	/**
	 * 移除物品
	 * @param roleId 玩家id
	 * @param itemId 物品id
	 * @return 
	 */
	private boolean removeItem(Player player,long itemId){
		if(player==null)return false;
		Item item = getItemById(player, itemId);
		if(item==null) return false;
		player.getStoreItems().remove(String.valueOf(item.getGridId()));
		ManagerPool.playerManager.savePlayer(player);
		MessageUtil.tell_player_message(player, getItemRemoveMessage(item));
		return true;
	}
	/**
	 * 获取包裹第一个空格子
	 * @param player
	 * @return
	 */
	private int getStoreFirstEmptGridId(Player player) {
		int storeCellsNum = player.getStoreCellsNum();
		for(int i=1;i<=storeCellsNum;i++){
			Item item = player.getStoreItems().get(String.valueOf(i));
			if (item==null||item.getNum()==0) {
				return i;
			}
		}
		return -1;
	}

	
	/**
	 * 移动物品
	 * @param roleId 角色ID
	 * @param cellId	格子ID
	 * @param num		数量
	 * @param toCellId	目标格子ID
	 */
	private void moveItem(Player player, long cellId, int toCellId) {
//		if (player.getLevel() < Global.STORE_NEED_GRADE) {
//			return;
//		}
//		if(!checkAble(player, npcid)){
//			return;
//		}
		Item item = player.getStoreItems().get(String.valueOf(cellId));
		if (item == null)
			return;
		if (item.getNum() <= 0) {
			return;
		}
		// 数据检查
		if (toCellId > player.getStoreCellsNum() || toCellId <= 0)
			return;
		if(toCellId==item.getGridId()){
			return;
		}
		if (player.getStoreItems().get(String.valueOf(toCellId)) == null) {
			// 完全移动
			player.getStoreItems().remove(String.valueOf(item.getGridId()));
			String before=JSONserializable.toString(item);
			item.setGridId(toCellId);
			player.getStoreItems().put(String.valueOf(item.getGridId()), item);
			MessageUtil.tell_player_message(player, getItemChangeMessage(item));
			try{
				if(BackpackManager.getInstance().isLog(item.getItemModelId())){
					StoreItemChangeLog createLog = StoreItemChangeLog.createLog(player.getId(),item.getId(),item.getItemModelId(),item.getNum(),before,JSONserializable.toString(item),OtherReason.move,StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.MOVE.toString(),player.getCreateServerId());
					LogService.getInstance().execute(createLog);
				}
			}catch (Exception e) {
				log.error(e,e);
			}
		} else {
			Item toGrid = player.getStoreItems().get(String.valueOf(toCellId));
			if(toGrid.equals(item)){
				return;
			}
			// 获取物品模型
			Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
			if (toGrid.isTrade()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品正在交易中"));
				return;
			}
			if (BackpackManager.getInstance().isMergeAble(item, toGrid)) {
				if (item.getNum() + player.getStoreItems().get(String.valueOf(toCellId)).getNum() <= model.getQ_max()) {
					String before=JSONserializable.toString(toGrid);
					// 完全合并
					// 合并
					toGrid.setNum(item.getNum() + toGrid.getNum());
					MessageUtil.tell_player_message(player, getItemChangeMessage(player.getStoreItems().get(String.valueOf(toCellId))));
					// 移除原来物品
					player.getStoreItems().remove(String.valueOf(item.getGridId()));
					MessageUtil.tell_player_message(player, getItemRemoveMessage(item));					
					try{
						if(BackpackManager.getInstance().isLog(item.getItemModelId())){
							StoreItemChangeLog removeLog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), item.getNum(),
									JSONserializable.toString(item),"", OtherReason.move, StoreAction.MOVE.toString(), Config.getId(),
									ItemChangeAction.REMOVE.toString(),player.getCreateServerId());
							StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(),toGrid.getId(),toGrid.getItemModelId(),toGrid.getNum(),before,JSONserializable.toString(toGrid),OtherReason.move,StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
							LogService.getInstance().execute(removeLog);
							LogService.getInstance().execute(changeLog);
						}
					}catch (Exception e) {
						log.error(e,e);
					}
				} else {
					// 部分合并
					// 可以合并 的数量
					int sub = model.getQ_max() - toGrid.getNum();
					String before1=JSONserializable.toString(item);
					String before2=JSONserializable.toString(toGrid);
					toGrid.setNum(model.getQ_max());
					item.setNum(item.getNum() - sub);
					MessageUtil.tell_player_message(player, getItemChangeMessage(item));
					MessageUtil.tell_player_message(player, getItemChangeMessage(toGrid));
					try{
						if(BackpackManager.getInstance().isLog(item.getItemModelId())){
							StoreItemChangeLog removeLog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), item.getNum(),before1,JSONserializable.toString(item), OtherReason.move, StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
							StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(),toGrid.getId(),toGrid.getItemModelId(),toGrid.getNum(),before2,JSONserializable.toString(toGrid),OtherReason.move,StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
							LogService.getInstance().execute(removeLog);
							LogService.getInstance().execute(changeLog);
						}
					}catch (Exception e) {
						log.error(e,e);
					}					
				}
			} else {
				String before1=JSONserializable.toString(item);
				String before2=JSONserializable.toString(toGrid);
				// 交换位置
				int tmpgrid = item.getGridId();
				player.getStoreItems().remove(tmpgrid);
				player.getStoreItems().remove(toGrid.getGridId());
				item.setGridId(toGrid.getGridId());
				toGrid.setGridId(tmpgrid);
				player.getStoreItems().put(String.valueOf(item.getGridId()), item);
				player.getStoreItems().put(String.valueOf(toGrid.getGridId()), toGrid);
				MessageUtil.tell_player_message(player, getItemChangeMessage(item));
				MessageUtil.tell_player_message(player, getItemChangeMessage(toGrid));
				try{
					if(BackpackManager.getInstance().isLog(item.getItemModelId())){
						StoreItemChangeLog removeLog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), item.getNum(),before1,JSONserializable.toString(item), OtherReason.move, StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.MOVE.toString(),player.getCreateServerId());
						StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(),toGrid.getId(),toGrid.getItemModelId(),toGrid.getNum(),before2,JSONserializable.toString(toGrid),OtherReason.move,StoreAction.MOVE.toString(), Config.getId(),ItemChangeAction.MOVE.toString(),player.getCreateServerId());
						LogService.getInstance().execute(removeLog);
						LogService.getInstance().execute(changeLog);
					}
				}catch (Exception e) {
					log.error(e,e);
				}
			}
		}
		ManagerPool.playerManager.savePlayer(player);
	}

	/**
	 * 包裹整理
	 * @param roleid 角色ID
	 */
	public void storeClearUp(Player player,boolean isgm,int npcid){
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		if(!checkAble(player, npcid)){
			return;
		}
		if(!isgm){
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.STORE_CLEAR,null)){
				long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.STORE_CLEAR, null);
				MessageUtil.notify_player(player, Notifys.NORMAL,(cooldownTime/1000l)+"秒之后才可以整理");
				return;
			}
			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.STORE_CLEAR,null,Global.CLEARUP_TIME_INTERVAL*1000);
		}
		String storeBefore=JSONserializable.toString(player.getStoreItems());
		List<Entry<String,Item>> list=new ArrayList<Entry<String,Item>>();
		HashMap<String, Item> container=player.getStoreItems();
		list.addAll(container.entrySet());
		log.debug("整理开始前"+list.size());
		List<BaseLogBean> logs=new ArrayList<BaseLogBean>();
		long actionId = Config.getId();
		for (int i = 0; i < list.size(); i++) {
			Entry<String, Item> entry = list.get(i);
			Item value = entry.getValue();
			Q_itemBean valuemodel = DataManager.getInstance().q_itemContainer
					.getMap().get(value.getItemModelId());
			// 如果第一个物品是满的那么直接略过
			if (valuemodel.getQ_max() > 1
					&& valuemodel.getQ_max() > value.getNum()) {
				for (int j = i + 1; j < list.size(); j++) {
					Entry<String, Item> entry2 = list.get(j);
					Item value2 = entry2.getValue();
					if (BackpackManager.getInstance().isMergeAble(value, value2)) {
						// 合并
						if (value.getNum() + value2.getNum() <= valuemodel
								.getQ_max()) {
							String before1=JSONserializable.toString(value);
							String before2=JSONserializable.toString(value2);							
							value.setNum(value.getNum() + value2.getNum());
							// 移除原来物品
							// 这里直接移除的话会倒致下标溢出
							value2.setNum(0);
							try{
								if(BackpackManager.getInstance().isLog(value.getItemModelId())){
									StoreItemChangeLog removeLog = StoreItemChangeLog.createLog(player.getId(), value.getId(), value.getItemModelId(), value2.getNum(),before1,JSONserializable.toString(value), OtherReason.storeclearup, StoreAction.CLEARUP.toString(), actionId,ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
									StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(),value2.getId(),value2.getItemModelId(),value2.getNum(),before2,JSONserializable.toString(value2),OtherReason.storeclearup,StoreAction.CLEARUP.toString(), actionId,ItemChangeAction.REMOVE.toString(),player.getCreateServerId());
									logs.add(removeLog);
									logs.add(changeLog);
								}
							}catch (Exception e) {
								log.error(e,e);
							}	
						} else {
							String before1=JSONserializable.toString(value);
							String before2=JSONserializable.toString(value2);
							int all = value.getNum() + value2.getNum();
							value.setNum(valuemodel.getQ_max());
							value2.setNum(all - valuemodel.getQ_max());
							try{
								if(BackpackManager.getInstance().isLog(value.getItemModelId())){
									StoreItemChangeLog removeLog = StoreItemChangeLog.createLog(player.getId(), value.getId(), value.getItemModelId(), value2.getNum(),before1,JSONserializable.toString(value), OtherReason.storeclearup, StoreAction.CLEARUP.toString(), actionId,ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
									StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(),value2.getId(),value2.getItemModelId(),value2.getNum(),before2,JSONserializable.toString(value2),OtherReason.storeclearup,StoreAction.CLEARUP.toString(), actionId,ItemChangeAction.CHANGE.toString(),player.getCreateServerId());
									logs.add(removeLog);
									logs.add(changeLog);
								}
							}catch (Exception e) {
								log.error(e,e);
							}	
						}
						if(value.getNum()>=valuemodel.getQ_max()){
							break;
						}
					}
				}
			}
		}
		//清除数量为零的
		for (Entry<String, Item> entry : list) {
			if(entry.getValue().getNum()==0){
				container.remove(entry.getKey());
			}
		}
		//排序
		List<Item> sortlist=new ArrayList<Item>();
		for (Entry<String, Item> entry :container.entrySet()) {
			sortlist.add(entry.getValue());
		}
		
		container.clear();
		Collections.sort(sortlist);
		for(int i=0;i<sortlist.size();i++){
			
			Item item = sortlist.get(i);
			String before1=JSONserializable.toString(item);
			item.setGridId(i+1);
			try{
				if(BackpackManager.getInstance().isLog(item.getItemModelId())){
					StoreItemChangeLog changeLog = StoreItemChangeLog.createLog(player.getId(), item.getId(), item.getItemModelId(), item.getNum(),before1,JSONserializable.toString(item), OtherReason.storeclearup, StoreAction.CLEARUP.toString(), actionId,ItemChangeAction.MOVE.toString(),player.getCreateServerId());
					logs.add(changeLog);
				}
			}catch (Exception e) {
				log.error(e,e);
			}	
			
			container.put(""+(i+1), item);
		}
		
		log.debug("整理后"+container.size());
		//发送通知
		sendItemInfos(player);
		PlayerManager.getInstance().savePlayer(player);
		try{
			for (BaseLogBean log : logs) {
				LogService.getInstance().execute(log);
			}	
			ClearUpLog log=new ClearUpLog();
			log.setBefore(CodedUtil.encodeBase64(ZipUtil.compress(storeBefore)));
			log.setAfter(CodedUtil.encodeBase64(ZipUtil.compress(JSONserializable.toString(player.getStoreItems()))));
			log.setRoleId(player.getId());
			log.setType(Global.STORE_TYPE);
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		}catch(Exception e){
			log.error(e,e);
		}
		
	}
	
	/**
	 * 开格子，不对格子ID和类别做检查请在调用前检查格子
	 * @param roleId	角色ID
	 * @param cellid	格子编号
	 */
	public void openCell(Player player, int cellid) {
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		ResStoreOpenCellResultMessage msg=new ResStoreOpenCellResultMessage();
		int rewardexp=0;
		for (int i = player.getStoreCellsNum()+1; i <=cellid; i++) {
			Q_backpack_gridBean config= DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.STORE_TYPE+"_"+i);
			rewardexp+=config.getQ_exp();
		}
		player.setStoreCellsNum(cellid);
		player.setStoreCellTimeCount(0);		
		msg.setCellId(player.getStoreCellsNum());
		msg.setIsSuccess(1);
		ManagerPool.playerManager.addExp(player, rewardexp, AttributeChangeReason.STOREOPENCELL);
		MessageUtil.tell_player_message(player, msg);
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.STORE);
//		player.setSendBagOpenCellTime(false);
		player.setSendStoreOpenCellTime(false);
	}
	
	private void sendItemInfos(Player player) {

		ResStoreItemInfosMessage msg=new ResStoreItemInfosMessage();
		msg.setCellnum(player.getStoreCellsNum());
		Iterator<Item> iter = player.getStoreItems().values().iterator();
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			msg.getItems().add(item.buildItemInfo());
		}
		MessageUtil.tell_player_message(player, msg);
	}

	
	private Item getItemById(Player player, long itemId){
		Iterator<Item> iter = player.getStoreItems().values().iterator();
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if(item.getId() == itemId) return item;
		}
		return null;
	}
	
	private boolean checkAble(Player player ,int npcid){
		if(player.getLevel()<Global.STORE_NEED_GRADE){
			if(npcid<=0){
				MessageUtil.notify_All_player(Notifys.NORMAL,ResManager.getInstance().getString("等级不足,需要{1}级才可以使用随身仓库"),Global.STORE_NEED_GRADE+"");
				return false;
			}else{
				if(!NpcManager.checkDistance(npcid, player.getPosition())){
					MessageUtil.notify_All_player(Notifys.NORMAL,ResManager.getInstance().getString("距离NPC过远"));
					return false;
				}
				if(!NpcManager.checkFunction(npcid, NpcManager.FUNCTION_STORE)){
					MessageUtil.notify_All_player(Notifys.ERROR,ResManager.getInstance().getString("该NPC没有仓库功能"));
					return false;
				}
			}
		}
		return true;
	}
//	/**
//	 * 获取物品信息
//	 * @param item 物品
//	 * @return
//	 */
//	private ItemInfo getItemInfo(Item item){
//		ItemInfo info = new ItemInfo();
//		info.setItemId(item.getId());
//		info.setItemModelId(item.getItemModelId());
//		info.setNum(item.getNum());
//		info.setGridId(item.getGridId());
//		info.setIsbind((byte) (item.isBind()?1:0));
//		if(item instanceof Equip){
//			Equip equip=(Equip) item;
//			if(equip.getAttributes()!=null){
//				info.setAttributs((byte) equip.getAttributes().size());	
//			}else{
//				info.setAttributs((byte) 0);
//			}
//			info.setIntensify((byte) equip.getGradeNum());
//		}
//		return info;
//	}

	private Message getItemChangeMessage(Item item) {
		ResStoreItemChangeMessage msg=new ResStoreItemChangeMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}
	private Message getItemAddMessage(Item item) {
		ResStoreItemAddMessage msg=new ResStoreItemAddMessage();
		msg.setItem(item.buildItemInfo());
		return msg;
	}
	private Message getItemRemoveMessage(Item item) {
		
		ResStoreItemRemoveMessage resp=new ResStoreItemRemoveMessage();
		resp.setItemId(item.getId());
		return resp;
	}
	
	public void moveItemMsg(Player player, long itemId, int toGridId,int npcid) {
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		if(!checkAble(player, npcid)){
			return;
		}
		Item itemById = getItemById(player, itemId);
		if(itemById!=null){
			moveItem(player, itemById.getGridId(), toGridId);	
		}
		
	}
	public void openCellByCheck(Player player, int cellId,int npcid) {
//		if(player.getLevel()<Global.STORE_NEED_GRADE){
//			return;
//		}
		if(!checkAble(player, npcid)){
			return;
		}
		if(cellId-player.getStoreCellsNum()>8){
			//最大只能开10个格子 如果有大于此数的请求 视为非法请求
			return ;
		}
		if(cellId<=0||cellId>Global.MAX_STORE_CELLS){
			return;
		}
		if(cellId<=player.getStoreCellsNum()){
			return;
		}
		int rewardsexp=0;
		int needgold=0;;
		for (int i = player.getStoreCellsNum()+1; i <=cellId; i++) {
			Q_backpack_gridBean config= DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.STORE_TYPE+"_"+i);
			rewardsexp+=config.getQ_exp();
			if(i==player.getStoreCellsNum()+1){
				if(player.getStoreCellTimeCount()<config.getQ_time()){
					needgold+=config.getQ_cost();
				}
			}else{
				needgold+=config.getQ_cost();	
			}
			
			
		}
//		Gold gold = BackpackManager.getInstance().getGold(player);
		if(!BackpackManager.getInstance().checkGold(player,needgold)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，开启格子所需的元宝不足"));
			return;
		}
		long action = Config.getId();
		if(BackpackManager.getInstance().changeGold(player,-needgold, Reasons.YBSTOREKAIGE,action)){
//				.changeGoldFirstBind(player,needgold, Reasons.YBSTOREKAIGE,action)){
			int beforecells=player.getStoreCellsNum();
			openCell(player, cellId);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("成功扩容仓库到{1}个!花费元宝{2},奖励经验{3}"),cellId+"",needgold+"",rewardsexp+"");
			try{
				CellOpenLog log=new CellOpenLog();
				log.setAction((byte)1);
				log.setActionId(action);
				log.setAfterCells(player.getStoreCellsNum());
				log.setBeforeCells(beforecells);
				log.setCellId(cellId);
				log.setResumegold(needgold);
				log.setRoleId(player.getId());
				log.setType(Global.STORE_TYPE);
				log.setSid(player.getCreateServerId());
				LogService.getInstance().execute(log);
			}catch (Exception e) {
				log.error(e,e);
			}
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，开启格子所需的元宝不足!"));
		}
	}
	
	public void sendAllItem(Player player,int npcid) {
		if (player == null)
			return;
		if(!checkAble(player, npcid)){
			return;
		}
		// 获得玩家
		sendItemInfos(player);
	}
	public void cellTimeQuery(Player player, int cellId,int npcid) {
		if(cellId!=player.getStoreCellsNum()+1){
			//请求的格子不对
			return;
		}
		if(player.getLevel()<Global.STORE_NEED_GRADE){
			return;
		}
		
		Q_backpack_gridBean config = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.STORE_TYPE+"_"+cellId);
		int time=config.getQ_time()-player.getStoreCellTimeCount();
		ResStoreCellTimeMessage resp=new ResStoreCellTimeMessage();
		resp.setCellId(cellId);
		resp.setTimeRemaining(time);
//		if(log.isDebugEnabled()){
//			log.debug("仓库还剩下"+time);	
//		}
		MessageUtil.tell_player_message(player, resp);		
	}

	public void openCellByTime(Player player) {
		int storeCellsNum = player.getStoreCellsNum();
		if(player.getLevel()>=Global.STORE_NEED_GRADE&&storeCellsNum<Global.MAX_STORE_CELLS&&storeCellsNum>=Global.STORE_AUTOOPEN_CELL_ID){
			Q_backpack_gridBean StoreConfig = DataManager.getInstance().q_backpack_gridContainer.getMap().get(Global.STORE_TYPE+"_"+(storeCellsNum+1));
			int storeCellTimeCount = player.getStoreCellTimeCount()+1;
			if(storeCellTimeCount>=StoreConfig.getQ_time()){
				StoreManager.getInstance().openCell(player, storeCellsNum+1);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("成功扩容仓库到{1}个!奖励经验{2}"),(storeCellsNum+1)+"",StoreConfig.getQ_exp()+"");
				try{
					CellOpenLog log=new CellOpenLog();
					log.setAction((byte)0);
					log.setAfterCells(player.getStoreCellsNum());
					log.setBeforeCells(storeCellsNum);
					log.setCellId(storeCellsNum+1);
					log.setResumegold(0);
					log.setRoleId(player.getId());
					log.setType(Global.STORE_TYPE);
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				}catch (Exception e) {
					log.error(e,e);
				}
			}else{
				//未达条件  时间加一秒
				cellTimeQuery(player, storeCellsNum+1, 0);
			}
		}
		
	}
}
