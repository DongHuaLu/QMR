package scripts.item;

import com.game.backpack.message.ResUseItemSuccessMessage;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;

public class Qizhanbingqi implements IItemScript {

	@Override
	public int getId() {
		return 1003017;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		long id = Config.getId();
		if(ManagerPool.horseWeaponManager.activateHorseWeapon(player, item, id)){
			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.HORSEWEAPON_ITEM, id);
			ResUseItemSuccessMessage resmsg = new ResUseItemSuccessMessage();
			resmsg.setItemId(item.getItemModelId());
			MessageUtil.tell_player_message(player, resmsg);
		}
		return true;
	}

}
