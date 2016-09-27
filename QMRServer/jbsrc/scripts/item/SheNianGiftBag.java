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
 * 蛇年翻翻乐礼包
 * @author Administrator
 *
 */
public class SheNianGiftBag implements IItemScript{


	@Override
	public int getId() {
		return 1009173;	
	}
	
	
	private int[][] GIFT_TAB = {
			//{道具ID,数量,几率,是否全服公告}
			{1011,5,800,1},
			{1007,150,800,1},
			{-5,1000,1000,1},
			{1031,2,800,1},
			{1024,1,1000,1},
			{1019,1,500,1},
			{8440,15,1000,0},
			{30204,5,500,0},
			{30105,5,500,0},
			{-1,2000000,500,0},
			{3019,5,800,0},
			{1100,5,800,0},
			{1020,1,100,1},
			{1021,1,50,1},
			{1022,1,10,1},
			{-7,50,100,0},

	};
	

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String numstr = parameters[0];
		int num = Integer.valueOf(numstr);
		String nameString=item.acqItemModel().getQ_name();
		List<Integer> probs = new ArrayList<Integer>();
		for (int i = 0; i < GIFT_TAB.length; ++i) {
			probs.add(GIFT_TAB[i][2]);
		}
		
		for (int i = 0; i < num; i++) {
			if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.def25, Config.getId())) {
				int idx = RandomUtils.randomIndexByProb(probs);
				String itemStr = GIFT_TAB[idx][0] + "," + GIFT_TAB[idx][1] + ",0,1";
				
				List<Item> items = new ArrayList<Item>();
				ManagerPool.backpackManager.createItems(player, itemStr, items);
				ManagerPool.backpackManager.addItems(player, items, Config.getId());
				if (items.size() > 0) {
					String newname = ManagerPool.backpackManager.getName(GIFT_TAB[idx][0]);
					MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString(("打开{1}，获得:{2}({3})")), nameString, newname,GIFT_TAB[idx][1]+"");
					if (GIFT_TAB[idx][3] > 0) {
						MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("玩家{1}打开{2}获得:{3}({4})"),player.getName(),nameString,newname,GIFT_TAB[idx][1]+"");
					}
				}
			}
		}
		return false;
	}

	
}
