package com.game.skill.script;

import com.game.fight.structs.Fighter;
import com.game.script.IScript;
import com.game.skill.structs.Skill;

public interface ITriggerSkillScript extends IScript {

	boolean canTrigger(Fighter source, Fighter target, Skill skill);

}
