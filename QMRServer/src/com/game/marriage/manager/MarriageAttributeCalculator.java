package com.game.marriage.manager;


import com.game.data.bean.Q_itemBean;
import com.game.manager.ManagerPool;
import com.game.marriage.structs.Marriage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class MarriageAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.MARRIAGE;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		Marriage marriage = ManagerPool.marriageManager.getMarriage(player.getMarriageid());
		if (marriage != null) {
			Q_itemBean data = ManagerPool.dataManager.q_itemContainer.getMap().get(player.getWeddingringid());
			if (data != null) {
				att.setMaxHp(data.getQ_max_hp());
				att.setMaxMp(data.getQ_max_mp());
				att.setMaxSp(data.getQ_max_sp());
				att.setCrit(data.getQ_crit());
				att.setDefense(data.getQ_defence());
				att.setAttack(data.getQ_attack());
				att.setDodge(data.getQ_dodge());
				att.setLuck(data.getQ_luck());
				att.setAttackSpeed(data.getQ_attackspeed());
				att.setSpeed(data.getQ_speed());
			}
		}
		return att;
	}

}
