package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.player.structs.Player;

public class HiddenWeapon implements IItemScript {

	@Override
	public int getId() {
		return 1018141;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		// TODO 道具不用了
		return false;
//		long id = Config.getId();
//		if(ManagerPool.hiddenWeaponManager.activateHiddenWeapon(player, item, id)){
//			ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.HIDDENWEAPON_ITEM, id);
//			ResUseItemSuccessMessage resmsg = new ResUseItemSuccessMessage();
//			resmsg.setItemId(item.getItemModelId());
//			MessageUtil.tell_player_message(player, resmsg);
//		}
//		return true;
	}

}
