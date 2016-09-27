package com.game.horseweapon.manager;

import java.util.List;

import com.game.buff.structs.Buff;
import com.game.data.bean.Q_horseweapon_attrBean;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

/**
 * 骑战兵器属性计算器
 * @author heyang
 *
 */
public class HorseWeaponAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.HORSE_WEAPON;
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

		int attack = 0;
		int defense = 0;
		int hp = 0;
		int crit = 0;
		int dodge = 0;
		
		//骑马中
		if (ManagerPool.horseManager.isRidding(player)) {
			if (ManagerPool.horseWeaponManager.isWearHorseWeapon(player)) {
				HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
				int level = ManagerPool.horseWeaponManager.getHorseWeaponLevel(weapon);
				Q_horseweapon_attrBean bean = ManagerPool.dataManager.q_horseweapon_attrContainer.getMap().get(weapon.getCurLayer() + "_" + level);
				//获取配置数据
				if(bean!=null){
					
					List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, 9158);//版本更新后，骑兵神锻符BUFFID
					if (buffs.size() > 0) {
						attack = 1000;
						defense = 500;
						hp = 4000;
						crit = 150;
						dodge = 150;
					}
					
					

					att.setAttack(bean.getQ_attack() + attack);
					att.setDefense(bean.getQ_defence() + defense);
					att.setCrit(bean.getQ_critical() + crit);
					att.setDodge(bean.getQ_doge() + dodge);
					att.setMaxHp(bean.getQ_hp() + hp);
					att.setMaxMp(bean.getQ_mp());
					att.setMaxSp(bean.getQ_sp());
					att.setAttackSpeed(bean.getQ_attack_speed());
					att.setSpeed(bean.getQ_move_speed());
				}
			}
		}
		return att;
	}

}
	
	
	
	
