package com.game.monster.script;

import com.game.fight.structs.Fighter;
import com.game.monster.structs.Monster;
import com.game.script.IScript;

public interface IMonsterCanBeAttackScript extends IScript {

	public boolean canbeattack(Fighter fighter, Monster monster);
}
