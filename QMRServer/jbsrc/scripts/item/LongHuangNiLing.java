package scripts.item;

import java.util.ArrayList;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**龙王逆鳞
 * 只显示，无实际作用
 * 
 * @author zhangrong
 *
 */
public class LongHuangNiLing  implements IItemScript {
	@Override
	public int getId() {
		return 1009123;	
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		if (item.getItemModelId() == 9123) {
			String name = item.acqItemModel().getQ_name();
			if (ManagerPool.backpackManager.removeItem(player, item, 1,Reasons.LONGHUANGNILING, Config.getId())) {
				ManagerPool.buffManager.addBuff(player, player, 9123, 0,0, 0);
				ParseUtil parseUtil = new ParseUtil();
				parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】使用了【%s】获得坐骑升阶成功率提升的效果！{@}"), player.getName(),name), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.LONGHUANG.getValue()));
				MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.LONGHUANG.getValue());
				return true;
			}
		}
		return false;
	}
}


