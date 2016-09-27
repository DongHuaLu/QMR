package scripts.item;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.player.structs.Player;

public class TestScript implements IItemScript {

	@Override
	public boolean use(Item item, Player player, String... parameters) {
		
		return false;
	}

	@Override
	public int getId() {
		return 1001;
	}
}
