package scripts.player;

import org.apache.log4j.Logger;

import com.game.fight.structs.Fighter;
import com.game.manager.ManagerPool;
import com.game.player.script.IPlayerDieScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class PlayerDieScript implements IPlayerDieScript {

	@Override
	public int getId() {
		return ScriptEnum.PLAYER_DIE;
	}
	private Logger log = Logger.getLogger(IPlayerDieScript.class);
	@Override
	public void onPlayerDie(Player player, Fighter killer) {
		
		//比武岛玩家死亡
		IPlayerDieScript script = (IPlayerDieScript) ManagerPool.scriptManager.getScript(ScriptEnum.BIWUDAO);
		if (script != null) {
			try {
				script.onPlayerDie(player, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		//水淹大梁
		 script = (IPlayerDieScript) ManagerPool.scriptManager.getScript(4012);
		if (script != null) {
			try {
				script.onPlayerDie(player, killer);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}

}
