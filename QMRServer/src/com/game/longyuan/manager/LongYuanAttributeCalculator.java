package com.game.longyuan.manager;


import com.game.languageres.manager.ResManager;
import java.util.HashMap;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class LongYuanAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.LONGYUAN;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		HashMap<String, Integer> lyProperty = ManagerPool.longyuanManager.getTianyuanProperty(player);
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
		
		if (lyProperty != null) {
			if (lyProperty.containsKey(ResManager.getInstance().getString("攻击"))) {
				att.setAttack(lyProperty.get(ResManager.getInstance().getString("攻击")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("暴击"))) {
				att.setCrit(lyProperty.get(ResManager.getInstance().getString("暴击")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("血量"))) {
				att.setMaxHp(lyProperty.get(ResManager.getInstance().getString("血量")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("体力"))) {
				att.setMaxSp(lyProperty.get(ResManager.getInstance().getString("体力")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("内力"))) {
				att.setMaxMp(lyProperty.get(ResManager.getInstance().getString("内力")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("防御"))) {
				att.setDefense(lyProperty.get(ResManager.getInstance().getString("防御")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("敏捷"))) {
				att.setDodge(lyProperty.get(ResManager.getInstance().getString("敏捷")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("幸运"))) {
				att.setLuck(lyProperty.get(ResManager.getInstance().getString("幸运")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("攻速"))) {
				att.setAttackSpeed(lyProperty.get(ResManager.getInstance().getString("攻速")));
			}
			if (lyProperty.containsKey(ResManager.getInstance().getString("移速"))) {
				att.setSpeed(lyProperty.get(ResManager.getInstance().getString("移速")));
			}
		}
		HashMap<Integer, Integer> map = ManagerPool.longyuanManager.getLongYuanSkillmap(player);
		if (map != null) {
			att.setSkillLevelUp(map);
		}
		return att;
	}

}
