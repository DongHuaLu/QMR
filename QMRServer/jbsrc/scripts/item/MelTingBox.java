package scripts.item;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import java.util.List;

/**
 * 附加属性百宝箱
 *
 * @author 杨鸿岚
 */
public class MelTingBox implements IItemScript {

	@Override
	public int getId() {
		return 1009132;
	}
	private int[] item_60 = {17000, 17020, 17030, 17040, 17060, 17070, 17080};
	private int[] item_80 = {17001, 17021, 17031, 17041, 17061, 17071, 17081};
	private int[] item_100 = {17002, 17022, 17032, 17042, 17062, 17072, 17082};

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String name = item.acqItemModel().getQ_name();
		long actionid = Config.getId();
		if (item.getItemModelId() == 9132) {
			if (BackpackManager.getInstance().getEmptyGridNum(player) < 1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹格子不够，不能打开附加属性百宝箱"));
				return false;
			}
			
			if (!BackpackManager.getInstance().removeItem(player, item, 1, Reasons.def11, actionid)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的附加属性百宝箱移除失败"));
				return false;
			}
			if (player.getLevel() < 80) {
				int itemModelid = item_60[RandomUtils.random(item_60.length)];
				List<Item> createItems = Item.createItems(itemModelid, 1, true, 0);
				if (!createItems.isEmpty()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.def11, actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您打开一个附加属性百宝箱，获得了%s*%d"), BackpackManager.getInstance().getName(itemModelid), 1));
				}
			} else if (player.getLevel() >= 80 && player.getLevel() < 100) {
				int itemModelid = item_80[RandomUtils.random(item_80.length)];
				List<Item> createItems = Item.createItems(itemModelid, 1, true, 0);
				if (!createItems.isEmpty()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.def11, actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您打开一个附加属性百宝箱，获得了%s*%d"), BackpackManager.getInstance().getName(itemModelid), 1));
				}
			} else if (player.getLevel() >= 100) {
				int itemModelid = item_100[RandomUtils.random(item_100.length)];
				List<Item> createItems = Item.createItems(itemModelid, 1, true, 0);
				if (!createItems.isEmpty()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.def11, actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您打开一个附加属性百宝箱，获得了%s*%d"), BackpackManager.getInstance().getName(itemModelid), 1));
				}
			}
		}else{
			//--------------------------新加道具----属性附加盒----------------------------------------
			if (BackpackManager.getInstance().getEmptyGridNum(player) < 1) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您的包裹格子不够，不能打开{1}"),name);
				return false;
			}
			if (player.getLevel() < 60) {
				return false;
			}
			
			int idx = -1;
			if (item.getItemModelId() == 16030) {//攻击属性附加盒
				idx = 0;
			}else if (item.getItemModelId() == 16031) {//防御属性附加盒
				idx = 1;
			}else if (item.getItemModelId() == 16032) {//暴击属性附加盒
				idx = 2;
			}else if (item.getItemModelId() == 16033) {//闪避属性附加盒
				idx = 3;
			}else if (item.getItemModelId() == 16034) {//生命属性附加盒
				idx = 4;
			}else if (item.getItemModelId() == 16035) {//内力属性附加盒
				idx = 5;
			}else if (item.getItemModelId() == 16036) {//体力属性附加盒
				idx = 6;
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("道具使用出错"));
				return false;
			}
			
			int itemmodelid = 0;
			if (player.getLevel() < 80) {
				itemmodelid = item_60[idx];
			} else if (player.getLevel() >= 80 && player.getLevel() < 100) {
				itemmodelid = item_80[idx];
			} else if (player.getLevel() >= 100) {
				itemmodelid = item_100[idx];
			}
			if (BackpackManager.getInstance().removeItem(player, item, 1, Reasons.def15, actionid)) {
				List<Item> createItems = Item.createItems(itemmodelid, 1, true, 0);
				if (!createItems.isEmpty()) {
					BackpackManager.getInstance().addItems(player, createItems, Reasons.def11, actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您打开一个%s，获得了%s*%d"), name,BackpackManager.getInstance().getName(itemmodelid), 1));
				}
			}
		}
		return true;
	}
}
