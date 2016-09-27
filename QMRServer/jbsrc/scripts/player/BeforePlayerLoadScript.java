package scripts.player;

import com.game.db.bean.Role;
import com.game.player.script.IBeforePlayerLoadScript;
import com.game.script.structs.ScriptEnum;

public class BeforePlayerLoadScript implements IBeforePlayerLoadScript {

	@Override
	public int getId() {
		return ScriptEnum.BEFORE_PLAYER_LOAD;
	}

	@Override
	public Role beforeLoad(Role role) {
		return role;
	}

}
