package scripts.monster;

import com.game.fight.structs.Fighter;
import com.game.monster.script.IMonsterCanAttackScript;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
/**怪物是否可攻击目标
 * 
 * @author zhangrong
 *
 */
public class MonsterCanAttackScript implements IMonsterCanAttackScript {

	@Override
	public int getId() {
		return ScriptEnum.MONSTER_ATTACK;
	}

	@Override
	public boolean canattack(Fighter fighter, Monster monster) {
		
		if(fighter instanceof Pet){
			return false;
		}
		
		if (fighter instanceof Player) {	//相同阵营不能攻击
			Player player = (Player)fighter;
			if (player.getGroupmark() > 0  && player.getGroupmark() == monster.getGroupmark() ) {
				return false;
			}
		}
		
		
		return true;
	}

}
