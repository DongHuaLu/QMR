package com.game.backpack.structs;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResUseItemSuccessMessage;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.vip.manager.VipManager;
/**
 * 
 * @author 赵聪慧
 *
 */
public class BuffGoods extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2903314068436106072L;

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
		
		if(model.getQ_id()>=1020 && model.getQ_id()<=1022){ 
			String buff = model.getQ_buff();
			VipManager.getInstance().userVipCard(player, this, buff);
		}else if(model.getQ_id()==1033){ //白金VIP体验卡
			String buff = model.getQ_buff();
			VipManager.getInstance().useVipTmpCard(player, this, buff);
		}else if(model.getQ_id()==1034){ //至尊VIP体验卡
			String buff = model.getQ_buff();
			VipManager.getInstance().useVipTmpZhiZunCard(player, this, buff);	
		}else{
			String buffString = model.getQ_buff();
			if (ManagerPool.backpackManager.setItemBuff(player,buffString,num) == 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("{1}效果叠加已达到上限"), model.getQ_name());
				return ;
			}
			if (BackpackManager.getInstance().removeItem(player, this, num,Reasons.GOODUSE,Config.getId())) {
				ResUseItemSuccessMessage msg = new ResUseItemSuccessMessage();
				msg.setItemId(model.getQ_id());
				MessageUtil.tell_player_message(player, msg);
			}
		}
	}

	@Override
	public void unuse(Player player, String... parameters) {
		// TODO Auto-generated method stub

	}

}
