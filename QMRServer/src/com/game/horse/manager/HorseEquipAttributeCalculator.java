package com.game.horse.manager;

import com.game.backpack.structs.HorseEquip;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class HorseEquipAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.HORSE_EQUIP;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		int MaxHp = 0;
		int MaxMp = 0;
		int MaxSp = 0;
		int Crit = 0;
		int Defense = 0;
		int Attack = 0;
		int Dodge = 0;
		int Luck = 0;
		int AttackSpeed = 0;
		int Speed = 0;

		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null && horse.getLayer() > 0) {
			if (horse.getCurlayer() > 0 && horse.getStatus() == 1) {	//骑马
				//Q_horse_basicBean horsebasic = ManagerPool.dataManager.q_horse_basicContainer.getMap().get((int)horse.getCurlayer());
				HorseEquip[] hrseequips = horse.getHorseequips();
				for (HorseEquip horseEquip : hrseequips) {
					if (horseEquip != null) {
					//	Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(horseEquip.getItemModelId());
					//	if (model != null && player.getLevel() >= model.getQ_level()) {
							 MaxHp += horseEquip.getMaxHp();
							 MaxMp += horseEquip.getMaxMp();
							 MaxSp += horseEquip.getMaxSp();
							 Crit += horseEquip.getCrit();
							 Defense += horseEquip.getDefense();
							 Attack += horseEquip.getAttack();
							 Dodge += horseEquip.getDodge();
							 Luck += horseEquip.getLuck();
							 AttackSpeed += horseEquip.getAttackSpeed();
							 Speed += horseEquip.getSpeed();
						}
					//}
				}
			}
		}
		att.setAttack(Attack);
		att.setCrit(Crit);
		att.setMaxHp(MaxHp);
		att.setMaxSp(MaxSp);
		att.setMaxMp(MaxMp);
		att.setDefense(Defense);
		att.setDodge(Dodge);
		att.setAttackSpeed(AttackSpeed);
		att.setSpeed(Speed);
		att.setLuck(Luck);
		return att;
	}

}
	
	
	
	
