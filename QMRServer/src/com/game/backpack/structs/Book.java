package com.game.backpack.structs;

import com.game.data.bean.Q_itemBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;

/**
 * 秘籍类书籍
 * @author Administrator
 *
 */
public class Book extends Item {

	private static final long serialVersionUID = 7814781978321265596L;

	/**
	 * 使用技能书
	 */
	@Override
	public void use(Player player, String... parameters) {
		//获得物品模型
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
		
		Item itemById = ManagerPool.backpackManager.getItemById(player, getId());
		if(itemById==null||this.getGridId()==-1){
			//未在包裹中
			return;
		}
		//XXX 技能书学习技能去掉了
//		SkillManager.getInstance().study(player, model.getQ_skill(), getId());
	}

	@Override
	public void unuse(Player player, String... parameters) {
		
	}
}
