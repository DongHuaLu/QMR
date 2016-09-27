package scripts.skills;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_mapBean;
import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.skill.structs.ISkillScript;
import com.game.utils.MessageUtil;
/**夫妻传送
 * 
 * @author zhangrong
 *
 */
public class SpouseMoveSkillScript implements ISkillScript{

	@Override
	public int getId() {
		return 2025005;
	}

	private Logger log = Logger.getLogger(SpouseMoveSkillScript.class);
	
	
	@Override
	public boolean canUse(Fighter attacker, Fighter defender, int direction) {
		Player spouse = ManagerPool.marriageManager.getSpousePlayer((Player)attacker);
		long id = 0;
		if (defender != null) {
			id = defender.getId();
		}
		
		Player player = (Player)attacker;
		if (spouse == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前不在线，无法传送"));
			return false;
		}
		
		Map map = ManagerPool.mapManager.getMap(spouse);
		if (map == null) {
			return false;
		}
		
		Q_mapBean q_mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		if (q_mapBean != null) {
			if(q_mapBean.getQ_map_min_level() > attacker.getLevel() ){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您当前的等级不能进入此地图，无法传送"));
				return false;
			}
		}else {
			return false;
		}
		
		if (map != null  && map.isCopy() ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前正在副本中，无法传送"));
			return false;
		}
		
		if (map != null  && map.getMapModelid()==ManagerPool.countryManager.SIEGE_MAPID) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前正在攻城战地图内，无法传送"));
			return false;
		}
		if (PlayerState.FIGHT.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法进行传送"));
			return false;
		}
		
		if ( id != spouse.getId()) {//先设置目标为配偶，再进这里不会再设置了
			ManagerPool.fightManager.playerAttackPlayer(player, spouse.getId(), 25005, direction);
			return false;
		}
		log.error("夫妻传送,mapid="+map.getMapModelid() + ",maplevel="+ q_mapBean.getQ_map_min_level()+"传送者level="+ attacker.getLevel());
		return true;
	}

	@Override
	public void damage(Fighter attacker, Fighter defender, FightResult result) {

	}

	@Override
	public boolean defaultAction(Fighter attacker, Fighter defender) {
		ManagerPool.marriageManager.spouseMove((Player)attacker);
		return true;
	}


	@Override
	public boolean canJumpSidestep(Fighter attacker, Fighter defender) {
		// TODO Auto-generated method stub
		return true;
	}

}
