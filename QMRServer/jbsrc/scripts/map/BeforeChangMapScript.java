package scripts.map;

import com.game.map.script.IBeforeChangeMapScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class BeforeChangMapScript implements IBeforeChangeMapScript {

	@Override
	public int getId() {
		return ScriptEnum.BEFORE_CHANGE_MAP;
	}

	@Override
	public boolean beforeChangeMap(Player player) {
		return true;
	}

}
