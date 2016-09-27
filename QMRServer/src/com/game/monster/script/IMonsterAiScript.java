package com.game.monster.script;

import com.game.fight.structs.Fighter;
import com.game.monster.structs.Monster;
import com.game.script.IScript;
import com.game.skill.structs.Skill;

public interface IMonsterAiScript extends IScript {
	
	public boolean wasHit(Monster monster,Fighter attacker);

	public Fighter getAttackTarget(Monster monster );
	
	public Skill getSkill(Monster monster);
}
