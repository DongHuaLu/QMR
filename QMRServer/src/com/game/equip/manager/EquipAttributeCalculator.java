package com.game.equip.manager;

import com.game.backpack.structs.Equip;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class EquipAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.EQUIP;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		
		//装备加成
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
		for (int i = 0; i < player.getEquips().length; i++) {
			Equip equip = player.getEquips()[i];
			if(equip==null) continue;
			attackEquipValue += equip.getAttack();
			defenseEquipValue += equip.getDefense();
			critEquipValue += equip.getCrit();
			dodgeEquipValue += equip.getDodge();
			maxHpEquipValue += equip.getMaxHp();
			maxMpEquipValue += equip.getMaxMp();
			maxSpEquipValue += equip.getMaxSp();
			attackSpeedEquipValue += equip.getAttackSpeed();
			speedEquipValue += equip.getSpeed();
			luckEquipValue += equip.getLuck();
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
