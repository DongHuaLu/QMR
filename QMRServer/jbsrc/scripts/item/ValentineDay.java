package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 情人礼盒
 * @author Administrator
 *
 */
public class ValentineDay implements IItemScript {
	@Override
	public int getId() {
		return 1009176;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String itemstr = "8576,1,0,1;";
		if (RandomUtils.random(100) < 60) {
			itemstr += "9177,1,0,0";
		}
		else {
			itemstr += "9178,1,0,0";
		}
		//创建奖励物品
		List<Item> items = new ArrayList<Item>();
		int num = ManagerPool.backpackManager.createItems(player, itemstr, items);
		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= num){
			long id = Config.getId();
			if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)) {
				ManagerPool.backpackManager.addItems(player, items, id);
				ManagerPool.backpackManager.notifyitemname(player, itemstr);
			}
		}
		else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("背包空间不足"));
		}

		return true;
	}
}
