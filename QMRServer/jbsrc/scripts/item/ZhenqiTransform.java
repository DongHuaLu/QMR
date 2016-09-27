package scripts.item;

import java.util.Iterator;
import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;


public class ZhenqiTransform implements IItemScript {
	@Override
	public int getId() {
		return 10091020;	//TODO脚本ID需要修改
	}

	private int ZHENQIDAN = 30201;
	private int SWZHENQIDAN = 30204;
	
	//30201	真气丹
	//30204	神武真气丹

	
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		long action = Config.getId();
		if(ManagerPool.backpackManager.getEmptyGridNum(player) < 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空格数量不足，请先清理一下"));
			return false;
		}
		
		if (ManagerPool.backpackManager.getItemNum(player, ZHENQIDAN) <= 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有符合条件的真气丹。"));
			return false;
		}
		
		if(!BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, action)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("移除物品失败"));
			return false;
		}
		
		boolean is= false;
		boolean bind = true;
		
		Item zhenqiitem = getItem(player ,ZHENQIDAN, false);	//先获取非绑定的
		if (zhenqiitem != null) {
			is = ManagerPool.backpackManager.removeItem(player, zhenqiitem, 1, Reasons.ACTIVITY_RNDYB, action);
			bind = false;
		}else {	
			 zhenqiitem = getItem(player ,ZHENQIDAN, true);//如果没有，再找绑定的
			if (zhenqiitem != null) {
				is = ManagerPool.backpackManager.removeItem(player, zhenqiitem, 1, Reasons.ACTIVITY_RNDYB, action);
			}
		}
		
		if (is) {
			List<Item> createItems = Item.createItems(SWZHENQIDAN, 1, bind,0, 0, null);
			ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_MRBBX,action);
			String name = ManagerPool.dataManager.q_itemContainer.getMap().get(SWZHENQIDAN).getQ_name();
			MessageUtil.notify_player(player, Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您把真气丹转换为：{1}"),name);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜您把真气丹转换为：{1}"),name);
			return true;
		}else {
			return false;
		}
	}

	
	
	@SuppressWarnings("deprecation")
	public Item getItem(Player player, int itemModelId,boolean bind) {
		Iterator<Item> iter = player.getBackpackItems().values().iterator();
		// 遍历物品
		while (iter.hasNext()) {
			Item item = (Item) iter.next();
			if (item.getItemModelId() == itemModelId && !item.isLost()&&!item.isTrade()) {
				if (item.isBind() == bind) {
					return item;
				}
			}
		}
		return null;
	}
	
}
