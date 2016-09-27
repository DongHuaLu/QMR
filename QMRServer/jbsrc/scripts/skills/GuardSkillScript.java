package scripts.skills;

import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.skill.structs.ISkillScript;
import com.game.utils.MessageUtil;

/**守护
 * 
 * @author zhangrong
 *
 */
public class GuardSkillScript implements ISkillScript{

	@Override
	public int getId() {
		return 2025006;
	}

	
	
	
	@Override
	public boolean canUse(Fighter attacker, Fighter defender, int direction) {
		Player spousePlayer = ManagerPool.marriageManager.getSpousePlayer((Player)attacker);
		if (spousePlayer != null && spousePlayer.getId() == defender.getId()) {
			if (spousePlayer.getMapModelId() == ManagerPool.countryManager.SIEGE_MAPID) {
				MessageUtil.notify_player((Player)attacker, Notifys.NORMAL, ResManager.getInstance().getString("攻城战地图不能使用守护技能"));
				return false;
			}else {
				return true;
			}
		}else {
			MessageUtil.notify_player((Player)attacker, Notifys.NORMAL, ResManager.getInstance().getString("守护技能只能对您的爱人使用"));
		}
		return false;
	}

	@Override
	public void damage(Fighter attacker, Fighter defender, FightResult result) {

	}

	@Override
	public boolean defaultAction(Fighter attacker, Fighter defender) {
		Player spousePlayer = ManagerPool.marriageManager.getSpousePlayer((Player)attacker);
		if (spousePlayer != null && spousePlayer.getId() == defender.getId()) {
			if (spousePlayer.getMapModelId() == ManagerPool.countryManager.SIEGE_MAPID) {
				return true;
			}
			ManagerPool.buffManager.addBuff(defender, defender, 9168,0, 0, 0);	//守护BUFF
			MessageUtil.notify_player(spousePlayer, Notifys.NORMAL, ResManager.getInstance().getString("您的爱人对您使用守护技能"));
			MessageUtil.notify_player((Player)attacker, Notifys.NORMAL, ResManager.getInstance().getString("您对爱人使用了守护技能"));
		}else {
			MessageUtil.notify_player((Player)attacker, Notifys.NORMAL, ResManager.getInstance().getString("守护技能只能对您的爱人使用"));
		}
		return true;
	}



	@Override
	public boolean canJumpSidestep(Fighter attacker, Fighter defender) {
		return false;
	}

}
