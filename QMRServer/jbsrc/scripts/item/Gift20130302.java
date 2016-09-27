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
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

/**
 * 2013年3月2日放出的礼包 (美人礼包 江山礼包 王城礼包 帝王礼包)
 * @author Administrator
 *
 */
public class Gift20130302 implements IItemScript {
	
	private HashMap<String, String> panelmap = new HashMap<String, String>();

	@Override
	public int getId() {
		return 1009193;
	}

	public Gift20130302(){
		//元宝
		panelmap.put("9193_yb", "100");
		panelmap.put("9194_yb", "500");
		panelmap.put("9195_yb", "2000");
		panelmap.put("9196_yb", "10000");
		
		//面板ID
		panelmap.put("9193_panel", "30");
		panelmap.put("9194_panel", "31");
		panelmap.put("9195_panel", "32");
		panelmap.put("9196_panel", "33");
		
		//给道具
		panelmap.put("9193_items", "8440,5,0,1;3019,2,0,1;16030,20,0,1;9194,1,0,1");
		panelmap.put("9194_items", "8441,10,0,1;1001,40,0,1;1004,10,0,1;9195,1,0,1");
		panelmap.put("9195_items", "1019,2,0,1;8443,2,0,1;30204,20,0,1;9196,1,0,1");
		panelmap.put("9196_items", "8617,1,0,1;9148,1,0,1");
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (!panelmap.containsKey(item.getItemModelId()+"_panel")) {
			return false;
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
