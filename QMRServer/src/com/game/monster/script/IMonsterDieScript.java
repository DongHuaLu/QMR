package com.game.monster.script;

import com.game.fight.structs.Fighter;
import com.game.monster.structs.Monster;
import com.game.script.IScript;

public interface IMonsterDieScript extends IScript {

	public void onMonsterDie(Monster monster, Fighter killer);
}
