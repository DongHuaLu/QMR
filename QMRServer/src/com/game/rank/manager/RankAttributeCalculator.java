
package com.game.rank.manager;

import com.game.data.bean.Q_rankBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class RankAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.RANK;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		Q_rankBean db = ManagerPool.dataManager.q_rankContainer.getMap().get(player.getRanklevel());
		if(db != null) {
			att.setCrit(db.getQ_crit());
			att.setAttackSpeed(db.getQ_attackspeed());
			att.setDodge(db.getQ_dodge());
			att.setDefense(db.getQ_defence());
			att.setSpeed(db.getQ_speed());
			att.setAttack(db.getQ_attack());
			att.setMaxHp(db.getQ_maxhp());
			att.setMaxMp(db.getQ_maxmp());
			att.setMaxSp(db.getQ_maxsp());
			att.setLuck(db.getQ_luck());
		}
		return att;
	}

}
