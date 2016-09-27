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
import com.game.utils.TimeUtil;

/**
 * 元旦的几个礼包
 * @author Administrator
 *
 */
public class YuanxiaoGift implements IItemScript {
	
	private HashMap<String, String> panelmap = new HashMap<String, String>();

	@Override
	public int getId() {
		return 1009184;
	}

	public YuanxiaoGift(){
		//元宝
		panelmap.put("9184_yb", "100");
		panelmap.put("9185_yb", "500");
		panelmap.put("9186_yb", "2000");
		panelmap.put("9187_yb", "10000");
		panelmap.put("9188_yb", "20000");
		panelmap.put("9189_yb", "50000");
		
		//面板ID
		panelmap.put("9184_panel", "24");
		panelmap.put("9185_panel", "25");
		panelmap.put("9186_panel", "26");
		panelmap.put("9187_panel", "27");
		panelmap.put("9188_panel", "28");
		panelmap.put("9189_panel", "29");
		
		//给道具
		panelmap.put("9184_items", "8440,5,0,1;9165,2,0,1;16030,20,0,1;9185,1,0,1");
		panelmap.put("9185_items", "8441,10,0,1;9121,10,0,1;1004,10,0,1;9186,1,0,1");
		
		panelmap.put("9187_items", "9148,1,0,1;3011,5,0,1;3012,5,0,1;3013,5,0,1;3014,5,0,1;9188,1,0,1");
		panelmap.put("9188_items", "9136,50,0,1;9137,50,0,1;1019,5,0,1;9189,1,0,1");
		panelmap.put("9189_items", "9140,100,0,1;9136,100,0,1;9137,50,0,1;1019,15,0,1;30107,1,0,1");
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (!panelmap.containsKey(item.getItemModelId()+"_panel")) {
			return false;
		}
		// 这里做下特殊处理,如果不是元宵节,则不给日历
		long millis = System.currentTimeMillis();
		long day = TimeUtil.getDayOfMonth(millis);	//天
		long month = TimeUtil.getMonth(millis) + 1;	//月
		long year = TimeUtil.getYear(millis);		//年
		if (year == 2013 && month == 2 && (day == 24 || day == 25) && WServer.getInstance().getServerWeb().compareTo("37wan") == 0) {
			panelmap.put("9186_items", "8443,4,0,1;1019,2,0,1;9187,1,0,1;9190,1,0,1");
		} else {
			panelmap.put("9186_items", "8443,4,0,1;1019,2,0,1;9187,1,0,1");
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
					&& ManagerPool.backpackManager.changeGold(player, -gold, Reasons.HOUSHENGDAN, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				if (equipItem != null) {
					String nameString = ManagerPool.backpackManager.getName(equipItem.getItemModelId());
					ManagerPool.backpackManager.addItem(player, equipItem, Reasons.HOUSHENGDAN, id);
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
