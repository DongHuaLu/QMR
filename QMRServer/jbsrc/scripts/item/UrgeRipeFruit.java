package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.cooldown.structs.CooldownTypes;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.spirittree.message.ReqActivityCheckFruitToWorldMessage;
import com.game.utils.MessageUtil;


public class UrgeRipeFruit implements IItemScript {

	@Override
	public int getId() {
		return 1009121;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		//2秒冷却
		if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.USE_FRUIR,null) == false) {
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.USE_FRUIR,null,2000);
		}else {
			return false;
		}
		ReqActivityCheckFruitToWorldMessage wmsg=new ReqActivityCheckFruitToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setType(2);
		MessageUtil.send_to_world(wmsg);
		return true;
	}

}
