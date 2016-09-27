package scripts.item;

import java.util.List;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

/**军功翻倍丹
 * 
 * @author zhangrong
 *
 */




public class RankMagnification implements IItemScript {
	@Override
	public int getId() {
		return 1009112;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		int itemmodelid = item.getItemModelId();
		if (itemmodelid < 9112 || itemmodelid > 9120) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("ID错误。"));
			return false;
		}
		
		
		int day = TimeUtil.GetSeriesDay();
		if(player.getActivitiesReward().containsKey("RANK_FANBEI")){
			String olddaystr = player.getActivitiesReward().get("RANK_FANBEI");
			int oldday = Integer.parseInt(olddaystr);
			if (oldday == day) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("今天已经使用军功翻倍道具，明天再用吧。"));
				return false;
			}
		}	
		
		for (int i = 9112; i <= 9120; i++) {
			List<Buff> buff = ManagerPool.buffManager.getBuffByModelId(player, i);
			if (buff.size() > 0) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("拥有军功翻倍BUFF时不能使用该类道具。"));
				return false;
			}
		}
			
		if(BackpackManager.getInstance().removeItem(player, item, 1, Reasons.RANK_FANBEI, Config.getId())){
			player.getActivitiesReward().put("RANK_FANBEI",day+"");
			ManagerPool.buffManager.addBuff(player, player, itemmodelid, 0, 0, 0);
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("成功使用军功翻倍道具。"));
			return true;
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("扣除物品失败"));
			return false;
		}
	}

}
