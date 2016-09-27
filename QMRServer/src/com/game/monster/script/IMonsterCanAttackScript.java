package com.game.monster.script;

import com.game.fight.structs.Fighter;
import com.game.monster.structs.Monster;
import com.game.script.IScript;

public interface IMonsterCanAttackScript extends IScript {

	public boolean canattack(Fighter fighter, Monster monster);
}
