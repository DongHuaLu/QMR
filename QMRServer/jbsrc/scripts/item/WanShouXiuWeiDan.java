package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
/**万寿修为丹 
 * 提升1级
 * @author zhangrong
 *
 */
public class WanShouXiuWeiDan implements IItemScript {

	@Override
	public int getId() {
		return 1030107;
	}

	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String name = item.acqItemModel().getQ_name();
		if (item.getItemModelId() != 30107) {
			return false;
		}
		if (player.getLevel() >= 120) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, "{1}暂时不能使用", name);
			return false;
		}
		
		long id = Config.getId();
		if(ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, id)){
			ManagerPool.playerManager.setLevel(player, player.getLevel() + 1, true, Reasons.GOODUSE);
			ManagerPool.playerManager.levelUpSyn(player);
		}
		return true;
	}

}
