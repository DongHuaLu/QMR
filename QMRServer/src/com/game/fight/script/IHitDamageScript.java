package com.game.fight.script;

import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.script.IScript;

public interface IHitDamageScript extends IScript {
	
	public void onDamage(Fighter attacker, Fighter defender, FightResult fightResult);
	
}
