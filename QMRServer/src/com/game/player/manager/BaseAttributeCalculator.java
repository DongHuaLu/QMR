package com.game.player.manager;

import com.game.data.bean.Q_characterBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class BaseAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.BASE;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		
		//基本加成
		Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
		
		att.setAttack(model.getQ_attack());
		att.setDefense(model.getQ_defense());
		att.setCrit(model.getQ_crit());
		att.setDodge(model.getQ_dodge());
		att.setMaxHp(model.getQ_hp());
		att.setMaxMp(model.getQ_mp());
		att.setMaxSp(model.getQ_sp());
		att.setAttackSpeed(model.getQ_attackspeed());
		att.setSpeed(model.getQ_speed());
		
		return att;
	}

}
