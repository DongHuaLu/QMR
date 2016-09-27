package com.game.hiddenweapon.manager;

import com.game.data.bean.Q_hiddenweapon_attrBean;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

/**
 * 暗器属性计算器
 * @author heyang
 *
 */
public class HiddenWeaponAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.HIDDEN_WEAPON;
	}
	
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

		if (ManagerPool.hiddenWeaponManager.isWearHiddenWeapon(player)) {
			HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
			Q_hiddenweapon_attrBean bean = ManagerPool.dataManager.q_hiddenweapon_attrContainer.getMap().get(weapon.getLayer());
			//获取配置数据
			if(bean!=null){
				att.setAttack(bean.getQ_attack());
				att.setDefense(bean.getQ_defence());
				att.setCrit(bean.getQ_critical());
				att.setDodge(bean.getQ_doge());
				att.setMaxHp(bean.getQ_hp());
				att.setMaxMp(bean.getQ_mp());
				att.setMaxSp(bean.getQ_sp());
				att.setAttackSpeed(bean.getQ_attack_speed());
			}
		}
		return att;
	}

}
	
	
	
	
