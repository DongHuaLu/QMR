package com.game.toplist.manager;

import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_guildbannerBean;
import com.game.data.manager.DataManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

/**
 * 帮旗属性计算器
 *
 * @author 杨洪岚
 */
public class TopTitleAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.TOPTITLE;
	}
	

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		PlayerAttribute patt = new PlayerAttribute();
		if (player.getGuildInfo().getBannerLevel() != 0) {
			Q_guildbannerBean q_guildbannerBean = DataManager.getInstance().q_guildbannerContainer.getMap().get((int) player.getGuildInfo().getBannerLevel());
			if (q_guildbannerBean != null) {
				Q_buffBean q_buffBean = DataManager.getInstance().q_buffContainer.getMap().get(q_guildbannerBean.getBuffid());
				if (q_buffBean != null) {
					patt = PlayerAttribute.getPlayerAttribute(q_buffBean);
				}
			}
		}
		return patt;
	}
}
