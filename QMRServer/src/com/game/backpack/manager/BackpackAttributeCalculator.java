package com.game.backpack.manager;

import com.game.data.bean.Q_backpack_gridBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;
import com.game.utils.Global;
/**
 * 
 * @author 赵聪慧
 *
 */
public class BackpackAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.BACKPACK;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		
		for (int i = Global.DEFAULT_BAG_CELLS; i < player.getBagCellsNum(); i++) {
			//背包格子对血加成
			Q_backpack_gridBean grid = ManagerPool.dataManager.q_backpack_gridContainer.getMap().get(Global.BAG_TYPE + "_" + (i + 1));
			if(grid!=null){
				att.setMaxHp(att.getMaxHp() + grid.getQ_maxhp());
			}
		}
		
		return att;
	}

}
