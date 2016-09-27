package com.game.backpack.structs;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.structs.Reasons;

public class AttributeItem extends Item {

	private static final long serialVersionUID = -3295718130510094878L;

	/**
	 * 使用属性物品
	 */
	@Override
	public void use(Player player, String... parameters) {
		//数量判断
		if (this.getNum() == 0)
			return;
		// 获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if (model == null)
			return;
		int num=1;
		
		if(parameters!=null&&parameters.length>=1){
			num = Integer.parseInt(parameters[0]);
			if(num>getNum()){
				return;
			}
		}
		//q_attack	q_defence	q_crit	q_dodge	q_max_hp	q_max_mp	q_max_sp	q_attackspeed	q_speed	q_luck

		// 使用成功
		if (BackpackManager.getInstance().removeItem(player, this, num,Reasons.GOODUSE,Config.getId())){
			if(model.getQ_attack()>0) player.getOtherAttribute().setAttack(player.getOtherAttribute().getAttack() + model.getQ_attack() * num);
			if(model.getQ_defence()>0) player.getOtherAttribute().setDefense(player.getOtherAttribute().getDefense() + model.getQ_defence() * num);
			if(model.getQ_crit()>0) player.getOtherAttribute().setCrit(player.getOtherAttribute().getCrit() + model.getQ_crit() * num);
			if(model.getQ_dodge()>0) player.getOtherAttribute().setDodge(player.getOtherAttribute().getDodge() + model.getQ_dodge() * num);
			if(model.getQ_max_hp()>0) player.getOtherAttribute().setMaxHp(player.getOtherAttribute().getMaxHp() + model.getQ_max_hp() * num);
			if(model.getQ_max_mp()>0) player.getOtherAttribute().setMaxMp(player.getOtherAttribute().getMaxMp() + model.getQ_max_mp() * num);
			if(model.getQ_max_sp()>0) player.getOtherAttribute().setMaxSp(player.getOtherAttribute().getMaxSp() + model.getQ_max_sp() * num);
			if(model.getQ_attackspeed()>0) player.getOtherAttribute().setAttackSpeed(player.getOtherAttribute().getAttackSpeed() + model.getQ_attackspeed() * num);
			if(model.getQ_speed()>0) player.getOtherAttribute().setSpeed(player.getOtherAttribute().getSpeed() + model.getQ_speed() * num);
			if(model.getQ_luck()>0) player.getOtherAttribute().setLuck(player.getOtherAttribute().getLuck() + model.getQ_luck() * num);
			
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.OTHER);
		}
	}


	@Override
	public void unuse(Player player, String... parameters) {
		
	}

}