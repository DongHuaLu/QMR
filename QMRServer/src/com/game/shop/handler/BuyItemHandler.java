package com.game.shop.handler;

import org.apache.log4j.Logger;

import com.game.command.Handler;
import com.game.data.bean.Q_shopBean;
import com.game.data.manager.DataManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.shop.message.BuyItemMessage;
import com.game.utils.MessageUtil;

public class BuyItemHandler extends Handler{

	Logger log = Logger.getLogger(BuyItemHandler.class);

	public void action(){
		try{
			BuyItemMessage msg = (BuyItemMessage)this.getMessage();
			Q_shopBean model = DataManager.getInstance().q_shopContainer.getMap().get(msg.getSellId());
			Player player=(Player) this.getParameter();
			if(model==null){
				MessageUtil.notify_player(player, Notifys.ERROR,"请求的商品不存在");
				return ;
			}
			if(model.getQ_sell()!=msg.getModelId()){
				MessageUtil.notify_player(player, Notifys.ERROR,"商品信息发生变更请重新打开商城面板");
				return;
			}
			if(msg.getOriginalBindGold()!=msg.getOriginalBindGold()){
				MessageUtil.notify_player(player, Notifys.ERROR,"商品信息发生变更请重新打开商城面板");
				return;
			}
			if(msg.getOriginalCoin()!=model.getQ_show_coin()){
				MessageUtil.notify_player(player, Notifys.ERROR,"商品信息发生变更请重新打开商城面板");
				return;
			}
			if(msg.getOriginalGold()!=model.getQ_show_gold()){
				MessageUtil.notify_player(player, Notifys.ERROR,"商品信息发生变更请重新打开商城面板");
				return;
			}
			ManagerPool.shopManager.buyItem((Player)this.getParameter(), msg.getNpcId(), msg.getSellId(), msg.getNum(), msg.getCostType(),msg.getCoin(),msg.getBindgold(),msg.getGold());
		}catch(ClassCastException e){
			log.error(e,e);
		}
	}
}