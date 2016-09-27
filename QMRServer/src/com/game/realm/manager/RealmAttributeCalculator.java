package com.game.realm.manager;

import com.game.data.bean.Q_realm_basicBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.realm.structs.Realm;
/**境界属性计算
 * 
 * @author zhangrong
 *
 */
public class RealmAttributeCalculator implements PlayerAttributeCalculator{

	@Override
	public int getType() {
		return PlayerAttributeType.REALM;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		
		PlayerAttribute att = new PlayerAttribute();
		Realm realm = player.getRealm();
		if (realm.getRealmlevel() > 0) {
			Q_realm_basicBean bean = ManagerPool.realmManager.getRealm_basic().get(realm.getRealmlevel());
			if (bean != null) {
				int infy = realm.getIntensifylevel();
				int[] atts = ManagerPool.realmManager.resolve(bean.getQ_attack_limit());
				if (atts[0] + infy * bean.getQ_attack() < atts[1]){
					att.setAttack(atts[0] + infy * bean.getQ_attack());
				}else {
					att.setAttack(atts[1]);
				}
				
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_defence_limit());
				if (atts[0] + infy * bean.getQ_defence() < atts[1]){
					att.setDefense(atts[0] + infy * bean.getQ_defence());
				}else {
					att.setDefense(atts[1]);
				}
				
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_crit_limit());
				if (atts[0] + infy * bean.getQ_crit() < atts[1]){
					att.setCrit(atts[0] + infy * bean.getQ_crit());
				}else {
					att.setCrit(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_dodge_limit());
				if (atts[0] + infy * bean.getQ_dodge() < atts[1]){
					att.setDodge(atts[0] + infy * bean.getQ_dodge());
				}else {
					att.setDodge(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_maxhp_limit());
				if (atts[0] + infy * bean.getQ_maxhp() < atts[1]){
					att.setMaxHp(atts[0] + infy * bean.getQ_maxhp());
				}else {
					att.setMaxHp(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_maxsp_limit());
				if (atts[0] + infy * bean.getQ_maxsp() < atts[1]){
					att.setMaxSp(atts[0] + infy * bean.getQ_maxsp());
				}else {
					att.setMaxSp(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_maxmp_limit());
				if (atts[0] + infy * bean.getQ_maxmp() < atts[1]){
					att.setMaxMp(atts[0] + infy * bean.getQ_maxmp());
				}else {
					att.setMaxMp(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_attackspeed_limit());
				if (atts[0] + infy * bean.getQ_attackspeed() < atts[1]){
					att.setAttackSpeed(atts[0] + infy * bean.getQ_attackspeed());
				}else {
					att.setAttackSpeed(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_speed_limit());
				if (atts[0] + infy * bean.getQ_speed() < atts[1]){
					att.setSpeed(atts[0] + infy * bean.getQ_speed());
				}else {
					att.setSpeed(atts[1]);
				}
			
				atts = ManagerPool.realmManager.resolve(bean.getQ_neg_defence_limit());
				if (atts[0] + infy * bean.getQ_neg_defence() < atts[1]){
					att.setNegDefence(atts[0] + infy * bean.getQ_neg_defence());
				}else {
					att.setNegDefence(atts[1]);
				}
				
				atts = ManagerPool.realmManager.resolve(bean.getQ_arrow_probability_limit());
				if (atts[0] + infy * bean.getQ_arrow_probability() < atts[1]){
					att.setArrowProbability(atts[0] + infy * bean.getQ_arrow_probability());
				}else {
					att.setArrowProbability(atts[1]);
				}
			}
		}
		return att;
	}
}
