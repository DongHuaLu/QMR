package com.game.pet.message.script;

import com.game.fight.structs.Fighter;
import com.game.pet.struts.Pet;
import com.game.script.IScript;

public interface IPetWasHitScript extends IScript {

	public void wasHit(Fighter fighter, Pet pet);
}
