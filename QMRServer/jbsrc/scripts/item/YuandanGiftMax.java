package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

public class YuandanGiftMax implements IItemScript {
	
	private int panelId = 8;

	@Override
	public int getId() {
		return 1009151;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		List<String> list = new ArrayList<String>();
		list.add("btn3#" + this.getId() + "#open#"+item.getId());
		panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
		NpcParamUtil.showPanel(player, panel);
		return false;
	}
	
	//1000
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
		
		int gold = 1000;
		if(!ManagerPool.backpackManager.checkGold(player, gold)){
			return;
		}
		
		//物品模板ID，物品数量，性别，是否绑定（0或1)，过期时间，升级级数，附加条数
		//花费1000元宝开启可获得：10倍*1   战魂5000  当前等级紫装+10随机一件  坐骑进阶丹20
		String itemstr = "1019,1,0,1;-6,5000;1011,20,0,1";
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		Item equipItem = getRandEquip(player, 10);
		if (equipItem != null) ++num;
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)
					&& ManagerPool.backpackManager.changeGold(player, -gold, Reasons.SHENGDAN, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				if (equipItem != null) ManagerPool.backpackManager.addItem(player, equipItem, Reasons.SHENGDAN, id);
			}
		}
		else {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包空间不足");
		}
		//关闭面板
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
