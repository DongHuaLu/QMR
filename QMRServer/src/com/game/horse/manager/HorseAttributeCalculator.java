package com.game.horse.manager;

import com.game.data.bean.Q_horse_additionBean;
import com.game.data.bean.Q_horse_basicBean;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class HorseAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.HORSE;
	}
	
	//潜能点额外加成
//		100攻击
//		50防御
//		320血量
//		20暴击
//		20闪避

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		att.setMaxHp(0);
		att.setMaxMp(0);
		att.setMaxSp(0);
		att.setCrit(0);
		att.setDefense(0);
		att.setAttack(0);
		att.setDodge(0);
		att.setLuck(0);
		att.setAttackSpeed(0);
		att.setSpeed(0);

		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null && horse.getLayer() > 0) {
			if (horse.getCurlayer() > 0 && horse.getStatus() == 1) {	//骑马
				Q_horse_basicBean horsebasic = ManagerPool.dataManager.q_horse_basicContainer.getMap().get((int)horse.getCurlayer());
				int lv = player.getLevel();
				if (lv > horsebasic.getQ_level_max()) {
					lv = horsebasic.getQ_level_max();
				}
				Q_horse_additionBean horsedata = ManagerPool.dataManager.q_horse_additionContainer.getMap().get(horse.getCurlayer()+"_"+lv);
				if (horsedata != null) {
					int attack = 100;
					int defense = 50;
					int hp = 320;
					int crit = 20;
					int dodge = 20;
					
					//潜能点额外加成
						
					attack = attack * horse.getPotential();
					defense = defense * horse.getPotential();
					hp = hp *  horse.getPotential();
					crit = crit * horse.getPotential();
					dodge = dodge * horse.getPotential();
					
					//炼骨丹

					double mixing= (double)(horse.getMixingbone()*0.02);
					int mix_attack = (int) (horsedata.getQ_attack() * mixing);
					int mix_defense = (int) (horsedata.getQ_defense() *  mixing);
					int mix_hp = (int) (horsedata.getQ_maxHp() *   mixing);
					int mix_crit = (int) (horsedata.getQ_crit() *  mixing);
					int mix_dodge = (int) (horsedata.getQ_dodge() *  mixing);
					int mix_sp =(int) (horsedata.getQ_maxSp()*  mixing);
					int mix_mp = (int) (horsedata.getQ_maxMp()*  mixing);
					
					att.setAttack(horsedata.getQ_attack() + attack + mix_attack);
					att.setCrit(horsedata.getQ_crit() + crit + mix_crit );
					att.setMaxHp(horsedata.getQ_maxHp() + hp + mix_hp);
					att.setMaxSp(horsedata.getQ_maxSp() + mix_sp);
					att.setMaxMp(horsedata.getQ_maxMp() + mix_mp);
					att.setDefense(horsedata.getQ_defense() + defense + mix_defense);
					att.setDodge(horsedata.getQ_dodge() + dodge + mix_dodge);
					att.setAttackSpeed(horsedata.getQ_attackSpeed());
					att.setSpeed(horsedata.getQ_speed());
				}
			}
		}
		return att;
	}

}
	
	
	
	
