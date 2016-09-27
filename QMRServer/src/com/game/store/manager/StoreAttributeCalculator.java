package com.game.store.manager;

import com.game.data.bean.Q_backpack_gridBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.utils.Global;

public class StoreAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.STORE;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		
		//仓库格子对血加成	
		for (int i = Global.DEFAULT_STORE_CELLS; i < player.getStoreCellsNum(); i++) {
			//背包格子对血加成
			Q_backpack_gridBean grid = ManagerPool.dataManager.q_backpack_gridContainer.getMap().get(Global.STORE_TYPE + "_" + (i + 1));
			if(grid!=null){
				att.setMaxHp(att.getMaxHp() + grid.getQ_maxhp());
			}
		}
		
		return att;
	}

}
