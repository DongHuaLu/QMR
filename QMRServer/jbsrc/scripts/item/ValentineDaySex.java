package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;

/**
 * 情人节礼盒,男/女
 * @author Administrator
 *
 */
public class ValentineDaySex implements IItemScript {
	@Override
	public int getId() {
		return 1009177;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		byte sex = 0;
		
		if (item.getItemModelId() == 9178) {
			sex = 2;
		}
		else if (item.getItemModelId() == 9177) {
			sex = 1;
		}
		else {
			return false;
		}
		
		if (player.getSex() != sex) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的性别与礼包不符,无法打开该礼包"));
			return false;
		}
		
		if (!timesCheck(player, item)) {
			return false;
		}
		
		String itemstr = "";
		int choice = RandomUtils.random(1, 7);
		if (choice == 1) {
			itemstr = "1031,1,0,1";
		} else if (choice == 2) {
			itemstr = "1011,1,0,1";
		} else if (choice == 3) {
			itemstr = "3019,1,0,1";
		} else if (choice == 4) {
			itemstr = "1007,20,0,1";
		} else if (choice == 5) {
			itemstr = "18138,1,0,1";
		} else if (choice == 6) {
			itemstr = "1001,5,0,1";
		} else if (choice == 7) {
			itemstr = "1009,1,0,1";
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

	/**
	 * 这里判断次数,并且增加次数
	 * @param player
	 * @return
	 */
	private boolean timesCheck(Player player, Item item) {
		// 总次数
		{
			long times = ManagerPool.countManager.getCount(player, CountTypes.VALENTINE_DAY, String.valueOf(item.getItemModelId()) + "_SUM");
			if(times >= 20){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该物品已达到使用上限，不能继续使用。"));
				return false;
			}
		}
		
		//每日次数
		{
			long times = ManagerPool.countManager.getCount(player, CountTypes.VALENTINE_DAY, String.valueOf(item.getItemModelId()));
			if(times < 10){
				ManagerPool.countManager.addCount(player, CountTypes.VALENTINE_DAY, String.valueOf(item.getItemModelId()), CountManager.COUNT_DAY , 1, 0);
				ManagerPool.countManager.addCount(player, CountTypes.VALENTINE_DAY, String.valueOf(item.getItemModelId()) + "_SUM", CountManager.COUNT_PERSISTENT , 1, 0);
				return true;
			}
		}

		MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该物品今日已达到使用上限，不能继续使用。"));
		return false;
	}
}
