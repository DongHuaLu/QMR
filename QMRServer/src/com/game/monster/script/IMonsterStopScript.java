package com.game.monster.script;

import com.game.monster.structs.Monster;
import com.game.script.IScript;

public interface IMonsterStopScript extends IScript {

	public void stop(Monster monster);
}
