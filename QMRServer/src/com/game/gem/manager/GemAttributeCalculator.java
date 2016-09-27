package com.game.gem.manager;

import com.game.data.bean.Q_gem_activationBean;
import com.game.data.bean.Q_gem_upBean;
import com.game.gem.struts.Gem;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class GemAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.GEM;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		int attackEquipValue = 0;
		int defenseEquipValue = 0;
		int critEquipValue = 0;
		int dodgeEquipValue = 0;
		int maxHpEquipValue = 0;
		int maxMpEquipValue = 0;
		int maxSpEquipValue = 0;
		int attackSpeedEquipValue = 0;
		int speedEquipValue = 0;
		int luckEquipValue = 0;
		
		Gem[][] gems = player.getGems();
		if (gems != null) {
			for (int i = 0; i < gems.length; i++) {
				Gem[] gempos = gems[i] ;
				for (int j = 0; j < gempos.length; j++) {
					if (gempos[j]==null) {
						continue;
					}
					if(gempos[j].getIsact() > 0){
						String id = (i+1)+"_"+(gempos[j].getGrid()+1);
						Q_gem_activationBean actdata = ManagerPool.gemManager.getGemActData(id);
						if (actdata != null) {
							Q_gem_upBean updata = ManagerPool.gemManager.getGemUpData(actdata.getQ_gem_type()+"_"+gempos[j].getLevel());
							if (updata != null) {
								attackEquipValue += updata.getQ_attack();
								defenseEquipValue += updata.getQ_defence();
								critEquipValue += updata.getQ_crit();
								dodgeEquipValue += updata.getQ_dodge();
								maxHpEquipValue += updata.getQ_maxhp();
								maxMpEquipValue += updata.getQ_maxmp();
								maxSpEquipValue += updata.getQ_maxsp();
								attackSpeedEquipValue += updata.getQ_attackspeed();
								speedEquipValue += updata.getQ_speed();
								luckEquipValue += updata.getQ_luck();
							}
						}
					}
				}
			}
		}

		
		att.setAttack(attackEquipValue);
		att.setDefense(defenseEquipValue);
		att.setCrit(critEquipValue);
		att.setDodge(dodgeEquipValue);
		att.setMaxHp(maxHpEquipValue);
		att.setMaxMp(maxMpEquipValue);
		att.setMaxSp(maxSpEquipValue);
		att.setAttackSpeed(attackSpeedEquipValue);
		att.setSpeed(speedEquipValue);
		att.setLuck(luckEquipValue);
		
		return att;
	}

}
