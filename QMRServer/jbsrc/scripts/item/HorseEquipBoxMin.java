package scripts.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 坐骑小宝箱
 * @author Administrator
 *
 */
public class HorseEquipBoxMin implements IItemScript {
	
	@Override
	public int getId() {
		return 1009166;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if(ManagerPool.backpackManager.getEmptyGridNum(player) < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包空间不足");
			return false;
		}
		
		if (!ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())) {
			return false;
		}
		
		Item horseEquip = getHorseEquip(player);
		if (horseEquip == null) {
			return false;
		}
		
		ManagerPool.backpackManager.addItem(player, horseEquip, Reasons.SHENGDAN, Config.getId());
		return true;
	}

	private Item getHorseEquip(Player player) {
		List<Q_itemBean> itemBeans = new ArrayList<Q_itemBean>();
		
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
			if (item.getQ_type() != ItemTypeConst.HORSEEQUIP) continue;
			if (item.getQ_kind() < 20 || item.getQ_kind() > 23) continue;
			if (item.getQ_level() != equipLevel) continue;
			itemBeans.add(item);
		}
		
		if (itemBeans.size() > 0) {
			int index = RandomUtils.random(itemBeans.size());
			Q_itemBean itemBean = itemBeans.get(index);
			List<Item> items = Item.createItems(itemBean.getQ_id(), 1, true, 0, (RandomUtils.random(100) < 80 ? 4 : 5), 6);
			if (items.size() > 0) {
				return items.get(0);
			}
		}
		return null;
	}
}
