package scripts.player;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.player.script.IPlayerWasHitScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class PlayerWasHitScript implements IPlayerWasHitScript {

	@Override
	public int getId() {
		return ScriptEnum.PLAYERWASHIT;
	}

	@Override
	public void wasHit(Fighter fighter, Player player) {
		if (ManagerPool.guildFlagManager.getFlagwarstatus() == 1) {
			if (ManagerPool.guildFlagManager.getTerritorymap().containsKey(player.getMapModelId())) {
				ManagerPool.guildFlagManager.addGuildFlagBuff(fighter,player);
			}
		}
	}
	
	
	
	
	
}





