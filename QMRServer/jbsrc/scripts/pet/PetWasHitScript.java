package scripts.pet;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.pet.message.script.IPetWasHitScript;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class PetWasHitScript implements IPetWasHitScript {

	@Override
	public int getId() {
		return ScriptEnum.PETWASHIT;
	}

	@Override
	public void wasHit(Fighter fighter, Pet pet) {
		Player player = ManagerPool.petInfoManager.getPetHost(pet);
		if (player != null) {
			if (ManagerPool.guildFlagManager.getFlagwarstatus() == 1) {
				if (ManagerPool.guildFlagManager.getTerritorymap().containsKey(player.getMapModelId())) {
					ManagerPool.guildFlagManager.addGuildFlagBuff(fighter,player);
				}
			}

		}
	}

}
