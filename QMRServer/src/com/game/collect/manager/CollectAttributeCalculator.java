package com.game.collect.manager;

import java.util.ArrayList;
import java.util.List;

import com.game.collect.struts.CollectItem;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_collectBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class CollectAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.COLLECT;
	}
	
	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute att = new PlayerAttribute();
		List<String> removeList = new ArrayList<String>();

		
		for (String collectId:player.getCollect().getInfos().keySet()) {
			try {
				CollectItem item = player.getCollect().getInfos().get(collectId);
				Q_collectBean collectBean = DataManager.getInstance().q_collectContainer.getMap().get(Integer.valueOf(collectId));
				
				if (CollectManager.getInstance().isActivity(item, collectBean)) {
					Q_buffBean buffBean = ManagerPool.dataManager.q_buffContainer.getMap().get(collectBean.getQ_buff_id());
					if (buffBean != null) att.add(PlayerAttribute.getPlayerAttribute(buffBean));
				}
			}
			catch (Exception e) {
				removeList.add(collectId);
			}
		}
		
		for (String key:removeList) {
			player.getCollect().getInfos().remove(key);
		}
		

		return att;
	}

}
