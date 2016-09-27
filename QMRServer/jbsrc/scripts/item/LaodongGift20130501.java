package scripts.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

/**
 * 2013年5月1日放出的劳动礼包
 * @author Administrator
 *
 */
public class LaodongGift20130501 implements IItemScript {
	
	private HashMap<String, String> panelmap = new HashMap<String, String>();

	@Override
	public int getId() {
		return 1009486;
	}

	public LaodongGift20130501(){
		//元宝
//		panelmap.put("9486_yb", "100");
		panelmap.put("9487_yb", "50");
		panelmap.put("9488_yb", "200");
		panelmap.put("9489_yb", "500");
		panelmap.put("9490_yb", "1000");
		panelmap.put("9491_yb", "2000");
		
		//面板ID
//		panelmap.put("9486_panel", "51");
		panelmap.put("9487_panel", "52");
		panelmap.put("9488_panel", "53");
		panelmap.put("9489_panel", "54");
		panelmap.put("9490_panel", "55");
		panelmap.put("9491_panel", "56");
		

		//给道具
		panelmap.put("9487_items", "1011,10,0,1;30301,10,0,1;9488,1,0,1");
		panelmap.put("9488_items", "17000,30,0,1;30302,10,0,1;1024,3,0,1;9489,1,0,1");
		panelmap.put("9489_items", "30701,3,0,1;30702,3,0,1;1024,6,0,1;1019,3,0,1;9490,1,0,1");
		panelmap.put("9490_items", "1012,100,0,1;16010,50,0,1;1023,1,0,1;9491,1,0,1");
		panelmap.put("9491_items", "30107,1,0,1;1007,999,0,1");
//		panelmap.put("9486_items", "30107,1,0,1;9168,1,0,1;9140,150,0,1;9136,150,0,1;9137,75,0,1;9141,50,0,1");
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (!panelmap.containsKey(item.getItemModelId()+"_panel")) {
			return false;
		}
		
		if(WServer.getInstance().getServerWeb().equals("twgmei") || WServer.getInstance().getServerWeb().equals("twrunup") || WServer.getInstance().getServerWeb().equals("yxjiuba")) {
			if (!panelmap.get("9491_panel").equals("61")) {
				panelmap.put("9486_panel", "56");
				panelmap.put("9487_panel", "57");
				panelmap.put("9488_panel", "58");
				panelmap.put("9489_panel", "59");
				panelmap.put("9490_panel", "60");
				panelmap.put("9491_panel", "61");
			}
		}
		
		int panelId = Integer.valueOf(panelmap.get(item.getItemModelId()+"_panel"));
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<String> list = new ArrayList<String>();
		list.add("btn3#" + this.getId() + "#open#"+item.getId());
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
		NpcParamUtil.showPanel(player, panel);
		return false;
	}
	

	public void open(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null){
			return;
		}
		
		if (!panelmap.containsKey(item.getItemModelId()+"_yb")) {
			return ;
		}
		
		int gold =  Integer.valueOf(panelmap.get(item.getItemModelId()+"_yb"));
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			return;
		}

		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		String itemstr = panelmap.get(item.getItemModelId()+"_items");
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		Item equipItem = null;
		if (panelmap.containsKey(item.getItemModelId()+"_equip")) {
			String[] strings = panelmap.get(item.getItemModelId()+"_equip").split(Symbol.DOUHAO);
			equipItem = getRandEquip(player, RandomUtils.random(Integer.valueOf(strings[0]), Integer.valueOf(strings[1])));
		}

		if (equipItem != null) ++num;
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)
					&& ManagerPool.backpackManager.changeGold(player, -gold, Reasons.def27, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				if (equipItem != null) {
					String nameString = ManagerPool.backpackManager.getName(equipItem.getItemModelId());
					ManagerPool.backpackManager.addItem(player, equipItem, Reasons.def27, id);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得:{1}", nameString);
				}
				ManagerPool.backpackManager.notifyitemname(player,itemstr);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("背包空間不足,需要{1}個空位"),num+"");
		}
		//关闭面板
		int panelId = Integer.valueOf(panelmap.get(item.getItemModelId()+"_panel"));
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
	}

	/*
	 * 随机一个当前等级的强化等级为strengthenLevel的紫色装备
	 */
	public Item getRandEquip(Player player, int strengthenLevel) {
		TreeMap<Integer, Q_itemBean> itemBeans = new TreeMap<Integer, Q_itemBean>();
		Integer count = new Integer(0);
		
		int equipLevel = player.getLevel() / 20;
		if (equipLevel < 1) {
			equipLevel = 1;
		}
		else {
			equipLevel = equipLevel * 20;
		}
		Iterator<Q_itemBean> i = DataManager.getInstance().q_itemContainer.getList().iterator();
		while (i.hasNext()) {
			Q_itemBean item = i.next();
			if (item.getQ_kind() > 10 || item.getQ_kind() < 2) continue;
			if (item.getQ_level() != equipLevel) continue;
			if (item.getQ_sex() != 0 && item.getQ_sex() != player.getSex()) continue;
			if (item.getQ_max_strengthen() < strengthenLevel) continue;
			itemBeans.put(count++, item);
		}
		
		if (itemBeans.size() > 0) {
			count = (int) (Math.random() * count);
			Q_itemBean itemBean = itemBeans.get(count);
			List<Item> items = Item.createItems(itemBean.getQ_id(), 1, true, 0, strengthenLevel, 6);
			if (items.size() > 0) {
				Iterator<Item> it = items.iterator();
				if (it.hasNext()) {
					return it.next();
				}
			}
		}
		return null;
	}
}
