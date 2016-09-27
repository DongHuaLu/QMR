package com.game.fight.script;

import com.game.fight.structs.Fighter;
import com.game.script.IScript;

public interface IAttackCheckScript extends IScript {

	public boolean check(Fighter attacker, Fighter defenser);
}
