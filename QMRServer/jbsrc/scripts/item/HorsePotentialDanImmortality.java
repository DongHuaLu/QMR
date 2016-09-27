//package com.game.zones.script;


package scripts.item;

import java.util.ArrayList;
import java.util.HashMap;

import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_horse_basicBean;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**坐骑潜能丹
 */
public class HorsePotentialDanImmortality implements IItemScript {
	@Override
	public int getId() {
		return 1009122;	
	}

	public HorsePotentialDanImmortality(){
		potentialmap.put(8, 2);
		potentialmap.put(9, 5);
		potentialmap.put(10, 10);
	}
	
	private HashMap<Integer, Integer> potentialmap = new HashMap<Integer, Integer>();
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		String name = item.acqItemModel().getQ_name();
		Horse horse = ManagerPool.horseManager.getHorse(player);
		int lv = horse.getLayer();
		Q_horse_basicBean horse_data = ManagerPool.dataManager.q_horse_basicContainer.getMap().get(8);
		if (!potentialmap.containsKey(lv) ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，{1}以上坐骑，才能使用{2}"), horse_data.getQ_name(),name);
			return false;
		}
		
		int pote = potentialmap.get(lv);
		Q_horse_basicBean horse_basicBean = ManagerPool.dataManager.q_horse_basicContainer.getMap().get(lv);
		if (horse.getPotential() >= pote) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对不起，{1}最多只能使用{2}颗{3}"), horse_basicBean.getQ_name(),pote+"",name);
			return false;
		}
			
		if( ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GEMQIANGHUA,Config.getId()) ){
			horse.setPotential(horse.getPotential() + 1);
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE);
			MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜您成功使用{1}，获得属性加成。"),name);
			ManagerPool.horseManager.stResHorseInfo(player);
			
			
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】使用【%s】，获得永久属性加成，战斗力获得提升!{@}"), player.getName(),name), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.ZUOQIDAN.getValue()));
			MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.ZUOQIDAN.getValue());
			return true;	
		}

		return false;
	}

}
