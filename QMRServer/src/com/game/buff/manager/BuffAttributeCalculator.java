package com.game.buff.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.game.buff.structs.AttributeBuff;
import com.game.buff.structs.Buff;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class BuffAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.BUFF;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		
		//Buff加成
		int attackBufValue = 0;
		int attackBufPercent = 0;
		int equipAttackPercent = 0;
		int defenseBufValue = 0;
		int defenseBufPercent = 0;
		int equipDefensePercent = 0;
		int critBufValue = 0;
		int critBufPercent = 0;
		int dodgeBufValue = 0;
		int dodgeBufPercent = 0;
		int maxHpBufValue = 0;
		int maxHpBufPercent = 0;
		int maxMpBufValue = 0;
		int maxMpBufPercent = 0;
		int maxSpBufValue = 0;
		int maxSpBufPercent = 0;
		int attackSpeedBufValue = 0;
		int attackSpeedBufPercent = 0;
		int totalAttackPercent = 0;
		int totalDefensePercent = 0;
		int totalCritPercent = 0;
		int totalDodgePercent = 0;
		int totalLuckPercent = 0;
		int totalSpeedPercent = 0;
		int totalAttackSpeedPercent = 0;
		int totolMaxHpPercent = 0;
		int totolMaxMpPercent = 0;
		int totolMaxSpPercent = 0;
		int speedBufValue = 0;
		int speedBufPercent = 0;
		int luckBufValue = 0;
		int luckBufPercent = 0;
		int expPercent = 0;
		int zhenQiPercent=0;
		HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
		for (int i = 0; i < player.getBuffs().size(); i++) {
			Buff buff = player.getBuffs().get(i);
			if(buff instanceof AttributeBuff){
				AttributeBuff aBuff = (AttributeBuff)buff;
				attackBufValue += aBuff.getAttack() * aBuff.getOverlay();
				attackBufPercent += aBuff.getAttackPercent() * aBuff.getOverlay();
				equipAttackPercent += aBuff.getEquipAttackPercent() * aBuff.getOverlay();
				defenseBufValue += aBuff.getDefense() * aBuff.getOverlay();
				defenseBufPercent += aBuff.getDefensePercent() * aBuff.getOverlay();
				equipDefensePercent += aBuff.getEquipDefensePercent() * aBuff.getOverlay();
				critBufValue += aBuff.getCrit() * aBuff.getOverlay();
				critBufPercent += aBuff.getCritPercent() * aBuff.getOverlay();
				dodgeBufValue += aBuff.getDodge() * aBuff.getOverlay();
				dodgeBufPercent += aBuff.getDodgePercent() * aBuff.getOverlay();
				maxHpBufValue += aBuff.getMaxHp() * aBuff.getOverlay();
				maxHpBufPercent += aBuff.getMaxHpPercent() * aBuff.getOverlay();
				maxMpBufValue += aBuff.getMaxMp() * aBuff.getOverlay();
				maxMpBufPercent += aBuff.getMaxMpPercent() * aBuff.getOverlay();
				maxSpBufValue += aBuff.getMaxSp() * aBuff.getOverlay();
				maxSpBufPercent += aBuff.getMaxSpPercent() * aBuff.getOverlay();
				attackSpeedBufValue += aBuff.getAttackSpeed() * aBuff.getOverlay();
				attackSpeedBufPercent += aBuff.getAttackSpeedPercent() * aBuff.getOverlay();
				totalAttackPercent += aBuff.getTotalAttackPercent() * aBuff.getOverlay();;
				totalDefensePercent += aBuff.getTotalDefensePercent() * aBuff.getOverlay();;
				totalCritPercent += aBuff.getTotalCritPercent() * aBuff.getOverlay();;
				totalDodgePercent += aBuff.getTotalDodgePercent() * aBuff.getOverlay();;
				totalLuckPercent += aBuff.getTotalLuckPercent() * aBuff.getOverlay();
				totalSpeedPercent += aBuff.getTotalSpeedPercent() * aBuff.getOverlay();
				totalAttackSpeedPercent += aBuff.getTotalAttackSpeedPercent() * aBuff.getOverlay();
				totolMaxHpPercent += aBuff.getTotalMaxHpPercent() * aBuff.getOverlay();
				totolMaxMpPercent += aBuff.getTotalMaxMpPercent() * aBuff.getOverlay();
				totolMaxSpPercent += aBuff.getTotalMaxSpPercent() * aBuff.getOverlay();
				speedBufValue += aBuff.getSpeed() * aBuff.getOverlay();
				speedBufPercent += aBuff.getSpeedPercent() * aBuff.getOverlay();
				luckBufValue += aBuff.getLuck() * aBuff.getOverlay();
				luckBufPercent += aBuff.getLuckPercent() * aBuff.getOverlay();
				expPercent += aBuff.getExpPercent() * aBuff.getOverlay();
				zhenQiPercent+=aBuff.getZhenqiPercent() * aBuff.getOverlay();
				Iterator<Entry<Integer, Integer>> iter = aBuff.getSkillLevelUp().entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<java.lang.Integer, java.lang.Integer> entry = (Map.Entry<java.lang.Integer, java.lang.Integer>) iter
							.next();
					if(skills.containsKey(entry.getKey())){
						skills.put(entry.getKey(), skills.get(entry.getKey()) + entry.getValue() * aBuff.getOverlay());
					}else{
						skills.put(entry.getKey(), entry.getValue() * aBuff.getOverlay());
					}
				}
			}
		}
		
		att.setAttack(attackBufValue);
		att.setAttackPercent(attackBufPercent);
		att.setEquipAttackPercent(equipAttackPercent);
		att.setDefense(defenseBufValue);
		att.setDefensePercent(defenseBufPercent);
		att.setEquipDefensePercent(equipDefensePercent);
		att.setCrit(critBufValue);
		att.setCritPercent(critBufPercent);
		att.setDodge(dodgeBufValue);
		att.setDodgePercent(dodgeBufPercent);
		att.setMaxHp(maxHpBufValue);
		att.setMaxHpPercent(maxHpBufPercent);
		att.setMaxMp(maxMpBufValue);
		att.setMaxMpPercent(maxMpBufPercent);
		att.setMaxSp(maxSpBufValue);
		att.setMaxSpPercent(maxSpBufPercent);
		att.setAttackSpeed(attackSpeedBufValue);
		att.setAttackSpeedPercent(attackSpeedBufPercent);
		att.setAttackTotalPercent(totalAttackPercent);
		att.setDefenseTotalPercent(totalDefensePercent);
		att.setCritTotalPercent(totalCritPercent);
		att.setDodgeTotalPercent(totalDodgePercent);
		att.setLuckTotalPercent(totalLuckPercent);
		att.setSpeedTotalPercent(totalSpeedPercent);
		att.setAttackSpeedTotalPercent(totalAttackSpeedPercent);
		att.setMaxHpTotalPercent(totolMaxHpPercent);
		att.setMaxMpTotalPercent(totolMaxMpPercent);
		att.setMaxSpTotalPercent(totolMaxSpPercent);
		att.setSpeed(speedBufValue);
		att.setSpeedPercent(speedBufPercent);
		att.setLuck(luckBufValue);
		att.setLuckPercent(luckBufPercent);
		att.setSkillLevelUp(skills);
		att.setExpPercent(expPercent);
		att.setZhenQiPercent(zhenQiPercent);
		return att;
	}

}
