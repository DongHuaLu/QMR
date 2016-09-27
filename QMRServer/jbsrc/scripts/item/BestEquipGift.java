package scripts.item;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_equip_appendBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Attributes;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 极品装备礼盒
 * @author Administrator
 *
 */
public class BestEquipGift implements IItemScript {

	@Override
	public int getId() {
		return 1009197;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		Item equipItem = getRandEquip(player, 10);
		if (equipItem == null) {
			return false;
		}
		if(ManagerPool.backpackManager.hasAddSpace(player, equipItem)){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)) {
				ManagerPool.backpackManager.addItem(player, equipItem, Reasons.def27, id);
			}
		}
		else {
			MessageUtil.notify_player(player, Notifys.ERROR, "背包空间不足");
		}
		return false;
	}

	/*
	 * 随机一个当前等级的强化等级为strengthenLevel的紫色装备,全部加攻击
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
		Equip equip = null;
		if (itemBeans.size() > 0) {
			count = (int) (Math.random() * count);
			Q_itemBean itemBean = itemBeans.get(count);
			List<Item> items = Item.createItems(itemBean.getQ_id(), 1, true, 0, strengthenLevel, 0);
			if (items.size() > 0) {
				Iterator<Item> it = items.iterator();
				if (it.hasNext()) {
					equip = (Equip)it.next();
				}
			}
		}
		
		if (equip == null) {
			return null;
		}
		
		Q_equip_appendBean appendModel = DataManager.getInstance().q_equip_appendContainer.getMap().get(equip.getItemModelId());
		for (int ii = 0; ii < 6; ++ii) {
			Attribute attribute=new Attribute();
			attribute.setType(Attributes.ATTACK.getValue());
			int value = generateAttribute(attribute.getType(), appendModel);
			attribute.setValue(value);
			equip.getAttributes().add(attribute);
		}
		return equip;
	}
	
	public int generateAttribute(int type, Q_equip_appendBean appendModel) {
		if (type == Attributes.ATTACK.getValue())
			return RandomUtils.random(appendModel.getQ_attack_min(),appendModel.getQ_attack_max());
		if (type == Attributes.ATTACKSPEED.getValue())
			return RandomUtils.random(appendModel.getQ_attackspeed_min(),appendModel.getQ_attackspeed_max());
		if (type == Attributes.DEFENSE.getValue())
			return RandomUtils.random(appendModel.getQ_defence_min(),appendModel.getQ_defence_max());
		if (type == Attributes.CRIT.getValue())
			return RandomUtils.random(appendModel.getQ_crit_min(),appendModel.getQ_crit_max());
		if (type == Attributes.DODGE.getValue())
			return RandomUtils.random(appendModel.getQ_dodge_min(),appendModel.getQ_dodge_max());
		if (type == Attributes.SPEED.getValue())
			return RandomUtils.random(appendModel.getQ_speed_min(),appendModel.getQ_speed_max());
		if (type == Attributes.MAXHP.getValue())
			return RandomUtils.random(appendModel.getQ_hp_min(),appendModel.getQ_hp_max());
		if (type == Attributes.MAXMP.getValue())
			return RandomUtils.random(appendModel.getQ_mp_min(),appendModel.getQ_mp_max());
		if (type == Attributes.MAXSP.getValue())
			return RandomUtils.random(appendModel.getQ_sp_min(),appendModel.getQ_sp_max());
		if (type == Attributes.LUCK.getValue())
			return RandomUtils.random(appendModel.getQ_luck_min(),appendModel.getQ_luck_max());
		return 0;
	}
}
