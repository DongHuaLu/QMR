//package com.game.zones.script;


package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;


public class BestqualityBox implements IItemScript {
	@Override
	public int getId() {
		return 1009101;
	}

	private int[][]  items= {
			{-1,100000,50}, //铜币
			{-1,50000,450},
			{-1,30000,1500},
			{-1,10000,3000},
			{30204,1,5}, //真气丹
			{30203,1,45},
			{30205,1,150},
			{30202,1,300},
			{1007,10,45},//宝石精华
			{1007,5,405},
			{1007,3,1350},
			{1007,1,2700},
	};
	
	
	
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		Horse horse=HorseManager.getInstance().getHorse(player);
//		HashMap<String, String> activmap = player.getActivitiesReward();
		long action = Config.getId();
		int sday=TimeUtil.getOpenAreaDay(player);
		if(ManagerPool.backpackManager.getEmptyGridNum(player) < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空格数量不足，请先清理一下"));
			return false;
		}
		
		
		if(!BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, action)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("移除物品失败"));
			return false;
		}
		
		if (sday >= 4) {
			if(horse.getLayer() >= 6){
//				boolean is = false;
//				if (getBuybackListItemNum(player, 1023) == 0 && horse.getDayblessvalue() == 0 && ManagerPool.backpackManager.getItemNum(player, 1023) == 0 &&  getWarehouseItemNum(player, 1023)==0) {
//					is =true;
//				}
//				if(activmap.containsKey("JPbox1023") ==false || is ){
//					List<Item> createItems = Item.createItems(1023, 1, true,System.currentTimeMillis()+24*60*60*1000 , 0, null);
//					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.def26,action);
//					activmap.put("JPbox1023", "1023");
//					String name = ManagerPool.dataManager.q_itemContainer.getMap().get(1023).getQ_name();
//					MessageUtil.notify_player(player, Notifys.SUCCESS,"恭喜您打开宝盒获得了：{1}",name);
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,"恭喜您打开宝盒获得了：{1}",name);
//					return true;
//				}
//				else if (activmap.containsKey("JPbox1025") ==false) {
//					List<Item> createItems = Item.createItems(1025, 1, true,System.currentTimeMillis()+24*60*60*1000 , 0, null);
//					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.def26,action);
//					activmap.put("JPbox1025", "1025");
//					String name = ManagerPool.dataManager.q_itemContainer.getMap().get(1025).getQ_name();
//					MessageUtil.notify_player(player, Notifys.SUCCESS,"恭喜您打开宝盒获得了：{1}",name);
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,"恭喜您打开宝盒获得了：{1}",name);
//					return true;
//				}
			}
		}
		
		
		//---------------------多玩1区特殊奖励--------------------------
		int sid = WServer.getInstance().getServerId();
		int day = TimeUtil.GetSeriesDay();
		if (day <= 20121026 && WServer.getInstance().getServerWeb().equals("duowan") && sid == 1) {	//检测时间和平台 
			String duowankey="DUOWAN1011_20121026";
			if (!player.getActivitiesReward().containsKey(duowankey)) {
				
				int num = 0;
				if(horse.getLayer() == 5){
					num = 20;
				}else if (horse.getLayer() == 6) {
					num = 40;
				}else if (horse.getLayer() == 7) {
					num = 80;
				}
				if (num > 0) {
					player.getActivitiesReward().put(duowankey, "20121026");
					List<Item> createItems = Item.createItems(1011,num, true,System.currentTimeMillis()+24*60*60*1000 , 0, null);
					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.def26,action);
					MessageUtil.notify_player(player, Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您打开宝盒获得了：坐骑进阶丹（{1}）"),num+"");
					return true;
				}
			}
		}
		

		
		//-----------------默认奖励-----------------------
		List<Integer> rndList = new ArrayList<Integer>();
		for (int i = 0; i < items.length; i++) {
			rndList.add(items[i][2]);
		}
		int idx = RandomUtils.randomIndexByProb(rndList);
		int id = items[idx][0];
		int num = items[idx][1];
		if (id == -1) {
			ManagerPool.backpackManager.changeMoney(player, num, Reasons.def26, action);
			MessageUtil.notify_player(player, Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您打开宝盒获得了：铜币{1}"),num+"");
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜您打开宝盒获得了：铜币{1}"),num+"");
			return true;
		}else if ( id == -3) {
			ManagerPool.playerManager.addZhenqi(player, num,AttributeChangeReason.JIPINGBAOHE);
			MessageUtil.notify_player(player, Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您打开宝盒获得了：真气{1}"),num+"");
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜您打开宝盒获得了：真气{1}"),num+"");
			return true;
		}else {
			List<Item> createItems = Item.createItems(id, num, false,0 , 0, null);
			ManagerPool.backpackManager.addItems(player, createItems, Reasons.def26,action);
			String name = ManagerPool.dataManager.q_itemContainer.getMap().get(id).getQ_name();
			MessageUtil.notify_player(player, Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您打开宝盒获得了：{1}（{2}）"),name,num+"");
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜您打开宝盒获得了：{1}（{2}）"),name,num+"");
			return true;
		}
	}
	
	
	/**
	 * 获得仓库物品数量
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	public int getWarehouseItemNum(Player player, int itemModelId) {
		int num = 0;
		@SuppressWarnings("deprecation")
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
	 * 获得售出物品数量
	 * @param roleId 玩家id
	 * @param itemModelId 物品模板id
	 * @return 物品数量
	 */
	public int getBuybackListItemNum(Player player, int itemModelId) {
		int num = 0;
		List<Item> list = player.getBuybackList();
		// 遍历物品
		for (Item item : list) {
			if (item.getItemModelId() == itemModelId && !item.isLost()) {
				num += item.getNum();
			}
		}
		return num;
	}
	
	
	public void setmap(List<Object> list){
		Player player = (Player) list.get(0);
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("按钮点击触发"));
		
		List<String> lista = new ArrayList<String>();
		lista.add("btnyanzheng#0#0#0");
		
		NpcParamUtil.setPanel(player,1,lista,1);
		
	}

}
