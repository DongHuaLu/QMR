package com.game.pet.attribute;

import java.util.List;

import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class PetAttributeCaluclator implements PlayerAttributeCalculator {
	@Override
	public int getType() {
		return PlayerAttributeType.PET;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		
		PlayerAttribute att = new PlayerAttribute();
		int maxhp=0;
		int maxmp=0;
		int attack=0;
		int defence=0;
		int crit=0;
		int dodge=0;
		List<Pet> petList = player.getPetList();
		for (Pet pet : petList) {
			//--------------合体加成
			maxhp+=pet.getHtaddhp();
			maxmp+=pet.getHtaddmp();
			attack+=pet.getHtaddattack();
			defence+=pet.getHtadddefence();
			crit+=pet.getHtaddcrit();
			dodge+=pet.getHtadddodge();
			//-------------合体加成结束 
		}
		att.setMaxHp(maxhp);
		att.setMaxMp(maxmp);
		att.setAttack(attack);
		att.setDefense(defence);
		att.setCrit(crit);
		att.setDodge(dodge);
		return att;
	}

}
