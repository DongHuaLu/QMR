package scripts.item;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.batter.manager.BatterManager;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.backpack.script.IItemScript;
import com.game.languageres.manager.ResManager;

/**
 * 连斩延时丹
 * @author 赵聪慧
 * @2012-9-24 下午6:10:01
 */
public class LianZhanYanShi implements IItemScript {

	@Override
	public int getId() {
		return 1001018;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		Buff batterBuff = BatterManager.getInstance().getBatterBuff(player);
		if(batterBuff==null){
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("需要具有连斩状态才可使用物品"));
			return false;
		}
		if(BackpackManager.getInstance().removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())){
			batterBuff.setTotalTime(batterBuff.getTotalTime()+30*60*1000);
			BuffManager.getInstance().sendChangeBuffMessage(player, player, batterBuff);
//			BuffManager.getInstance().sendBuffInfoMessage(player, batterBuff.getId());	;
		}else{
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("扣除物品失败"));
			return false;
		}
		return true;
	}

}
