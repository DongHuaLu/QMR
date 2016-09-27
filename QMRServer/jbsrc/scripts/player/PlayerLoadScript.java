package scripts.player;

import com.game.player.script.IPlayerLoadScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class PlayerLoadScript implements IPlayerLoadScript {

	@Override
	public int getId() {
		return ScriptEnum.PLAYER_LOAD;
	}

	@Override
	public Player onLoad(Player player, String data) {
		return player;
	}

}
