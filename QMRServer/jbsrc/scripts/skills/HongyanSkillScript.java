package scripts.skills;

import com.game.fight.structs.FightResult;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.skill.structs.ISkillScript;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

/**守护
 * 
 * @author zhangrong
 *
 */
public class HongyanSkillScript implements ISkillScript{

	private int skillId = 25007;
	
	@Override
	public int getId() {
		return 2025007;
	}

	
	@Override
	public boolean canUse(Fighter attacker, Fighter defender, int direction) {
		if(!(attacker instanceof Player)){
			return false;
		}
		
		Player player = (Player)attacker;
		//玩家配偶
		Player marryed = ManagerPool.marriageManager.getSpousePlayer((Player)attacker);
		if(marryed==null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能只能对正在攻击您配偶的玩家使用！"));
			return false;
		}
		
		if(defender==null){
			
			double min = Double.MAX_VALUE;
			//选择离自己最新攻击配偶的目标
			for (Long id : marryed.getHits()) {
				Player target = ManagerPool.playerManager.getPlayer(id);
				if(target==null){
					continue;
				}
				if(target.getMap()!=attacker.getMap() || target.getLine()!=attacker.getLine()){
					continue;
				}
				double dis = MapUtils.countDistance(target.getPosition(), attacker.getPosition());
				if(dis < min){
					min = dis;
					defender = target;
				}
			}
			
			if(defender!=null){
				ManagerPool.fightManager.playerAttackPlayer((Player)attacker, defender.getId(), skillId, direction);
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能只能对正在攻击您配偶的玩家使用！"));
			}
			
			return false;
		}else if(defender instanceof Monster){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能只能对正在攻击您配偶的玩家使用！"));
			return false;
		}else if(defender instanceof Pet){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能只能对正在攻击您配偶的玩家使用！"));
			return false;
		}else if(defender instanceof Player){
			//是否攻击过自己配偶
			if(!marryed.getHits().contains(defender.getId())){
				//提示不能攻击因为未攻击过其配偶
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该技能只能对正在攻击您配偶的玩家使用！"));
				return false;
			}
		}
		return true;
	}

	@Override
	public void damage(Fighter attacker, Fighter defender, FightResult result) {
		if(!(attacker instanceof Player) || !(defender instanceof Player)){
			return;
		}
		//清空攻击者内力，造成双倍当前内力伤害
		int damage = attacker.getMp() * 2;
		attacker.setMp(0);
		ManagerPool.playerManager.onMpChange((Player)attacker);
		result.damage = damage;
	}

	@Override
	public boolean defaultAction(Fighter attacker, Fighter defender) {
		return false;
	}

	@Override
	public boolean canJumpSidestep(Fighter attacker, Fighter defender) {
		return false;
	}

}
