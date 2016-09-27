package scripts.map;

import com.game.map.script.IEnterGridScript;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;

public class EnterGridScript implements IEnterGridScript {

	@Override
	public int getId() {
		return ScriptEnum.ENTER_GRID;
	}

	@Override
	public void onEnterGrid(Player player, Map map, Grid grid) {
		// TODO Auto-generated method stub

	}

}
