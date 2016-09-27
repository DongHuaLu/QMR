package com.game.arrow.manager;

import com.game.data.bean.Q_arrowBean;
import com.game.data.bean.Q_arrow_bowBean;
import com.game.data.bean.Q_arrow_starBean;
import com.game.data.manager.DataManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import java.util.Iterator;

/**
 * 弓箭属性计算
 *
 * @author 杨鸿岚
 */
public class ArrowAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.ARROW;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute patt = new PlayerAttribute();
		Iterator<Q_arrowBean> iterator = DataManager.getInstance().q_arrowContainer.getMap().values().iterator();
		while (iterator.hasNext()) {
			Q_arrowBean q_arrowBean = iterator.next();
			if (q_arrowBean != null && q_arrowBean.getQ_arrow_id() <= player.getArrowData().getArrowlv()) {
				patt.setAttack(patt.getAttack()+q_arrowBean.getQ_attack());
				patt.setDefense(patt.getDefense()+q_arrowBean.getQ_defence());
				patt.setCrit(patt.getCrit()+q_arrowBean.getQ_crit());
				patt.setDodge(patt.getDodge()+q_arrowBean.getQ_dodge());
				patt.setMaxHp(patt.getMaxHp()+q_arrowBean.getQ_maxhp());
				patt.setMaxMp(patt.getMaxMp()+q_arrowBean.getQ_maxmp());
				patt.setMaxSp(patt.getMaxSp()+q_arrowBean.getQ_maxsp());
				patt.setAttackSpeed(patt.getAttackSpeed()+q_arrowBean.getQ_attackspeed());
				patt.setSpeed(patt.getSpeed()+q_arrowBean.getQ_speed());
			}
		}
		Iterator<Q_arrow_starBean> iterator1 = DataManager.getInstance().q_arrow_starContainer.getMap().values().iterator();
		while(iterator1.hasNext()) {
			Q_arrow_starBean q_arrow_starBean =  iterator1.next();
			if (q_arrow_starBean != null && ((q_arrow_starBean.getQ_mainlv() < player.getArrowData().getStarData().getStarmainlv()) || (q_arrow_starBean.getQ_mainlv() == player.getArrowData().getStarData().getStarmainlv() && q_arrow_starBean.getQ_sublv() <= player.getArrowData().getStarData().getStarsublv()))) {
				patt.setAttack(patt.getAttack()+q_arrow_starBean.getQ_attack());
				patt.setDefense(patt.getDefense()+q_arrow_starBean.getQ_defence());
				patt.setCrit(patt.getCrit()+q_arrow_starBean.getQ_crit());
				patt.setDodge(patt.getDodge()+q_arrow_starBean.getQ_dodge());
				patt.setMaxHp(patt.getMaxHp()+q_arrow_starBean.getQ_maxhp());
				patt.setMaxMp(patt.getMaxMp()+q_arrow_starBean.getQ_maxmp());
				patt.setMaxSp(patt.getMaxSp()+q_arrow_starBean.getQ_maxsp());
				patt.setAttackSpeed(patt.getAttackSpeed()+q_arrow_starBean.getQ_attackspeed());
				patt.setSpeed(patt.getSpeed()+q_arrow_starBean.getQ_speed());
			}
		}
		Iterator<Q_arrow_bowBean> iterator2 = DataManager.getInstance().q_arrow_bowContainer.getMap().values().iterator();
		while(iterator2.hasNext()) {
			Q_arrow_bowBean q_arrow_bowBean =  iterator2.next();
			if (q_arrow_bowBean != null && ((q_arrow_bowBean.getQ_mainlv() < player.getArrowData().getBowData().getBowmainlv()) || (q_arrow_bowBean.getQ_mainlv() == player.getArrowData().getBowData().getBowmainlv() && q_arrow_bowBean.getQ_sublv() <= player.getArrowData().getBowData().getBowsublv()))) {
				patt.setAttack(patt.getAttack()+q_arrow_bowBean.getQ_attack());
				patt.setDefense(patt.getDefense()+q_arrow_bowBean.getQ_defence());
				patt.setCrit(patt.getCrit()+q_arrow_bowBean.getQ_crit());
				patt.setDodge(patt.getDodge()+q_arrow_bowBean.getQ_dodge());
				patt.setMaxHp(patt.getMaxHp()+q_arrow_bowBean.getQ_maxhp());
				patt.setMaxMp(patt.getMaxMp()+q_arrow_bowBean.getQ_maxmp());
				patt.setMaxSp(patt.getMaxSp()+q_arrow_bowBean.getQ_maxsp());
				patt.setAttackSpeed(patt.getAttackSpeed()+q_arrow_bowBean.getQ_attackspeed());
				patt.setSpeed(patt.getSpeed()+q_arrow_bowBean.getQ_speed());
			}
		}
		return patt;
	}
}
