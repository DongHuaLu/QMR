package com.game.hiddenweapon.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IHiddenWeaponSkillScript extends IScript {

	public int onHiddenWeaponSkillStudy(Player player , int skill, int skillLevel);
}
