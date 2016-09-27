package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.player.structs.Player;
import com.game.realm.manager.RealmManager;
import com.game.realm.message.ReqBreakthroughToGameMessage;

/**
 * 激活境界
 * @author Administrator
 *
 */
public class Breakthrough implements IItemScript {

	@Override
	public int getId() {
		return 1018137;
	}

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		ReqBreakthroughToGameMessage msg = new ReqBreakthroughToGameMessage();
		msg.setType(0); // 手动
		RealmManager.getInstance().stReqBreakthroughToGameMessage(player, msg);
		return true;
	}
}
