package com.game.player.script;

import com.game.db.bean.Role;
import com.game.script.IScript;

public interface IBeforePlayerLoadScript extends IScript {

	public Role beforeLoad(Role role);
}
