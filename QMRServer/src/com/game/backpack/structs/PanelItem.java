package com.game.backpack.structs;

import java.util.List;

import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_itemBean;
import com.game.db.bean.Gold;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.ybcard.message.ReqYBCardToWorldMessage;

public class PanelItem extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void use(Player player, String... parameters) {
		//获得物品模型
		//long action = Config.getId();
		
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(this.getItemModelId());
		if(model==null) return;
		
		if (this.getItemModelId() == 8060) {	// 封测元宝卡
			
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.YB_CARD,null) == false) {
				ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.YB_CARD,null,1000);
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("点击过于频繁请稍后再试。"));
				return;
			}
			
			int num = 1;
			if(parameters.length > 0){
				num = Integer.parseInt(parameters[0]);
				if(num <=0 || num>model.getQ_max()) return;
			}
			if(num > 1){
				ManagerPool.ybcardManager.stReqYBCardToGameMessage(player, (byte)1, num);
			}else{
				ReqYBCardToWorldMessage wmsg = new ReqYBCardToWorldMessage();
				wmsg.setPlayerid(player.getId());
				wmsg.setType((byte)0);//打开面板，查询
				wmsg.setNum(1);
				MessageUtil.send_to_world(wmsg);
			}
			
			
			
			
		}else if (this.getItemModelId() == 8070) {//红色缘分袋
			if (ManagerPool.backpackManager.getEmptyGridNum(player) <= 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("包裹已满，使用前请先清理一下。"));	
				return;
			}
			Gold ybdata = player.getGold();
			int playeryuanbao = 0;
			if (ybdata != null) {
				playeryuanbao = ybdata.getGold();
	
			}
			if (playeryuanbao + 100 > Global.BAG_MAX_GOLD) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("携带的元宝数量快要达到上限了，请先清理一下。"));	
				return;
			}
			
			long gid = Config.getId();
			String itemname="";
			int num = 0;
			if (ManagerPool.backpackManager.removeItem(player, this, 1, Reasons.ACTIVITY_YBCARD_USE,gid) ) {
				//可随机获得以下道具之一：\n非绑定元宝（100~1000）\n坐骑进阶丹（10~100）\n圣兽令牌(青龙令、白虎令随机一种5个）
				int rnd = RandomUtils.random(1,3);
				if (rnd == 1) {//元宝
					int rndnum = RandomUtils.random(10,100)*10;
					ManagerPool.backpackManager.addGold(player, rndnum, Reasons.ACTIVITY_RNDYB, gid);
					itemname = ResManager.getInstance().getString("元宝");
					num = 	rndnum;	
				}else if (rnd == 2) {//坐骑进阶丹
					int rndnum = RandomUtils.random(10,100);
					List<Item> createItems = Item.createItems(1011, rndnum ,false,0 ,0, null);
					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_RNDITEM,gid);
					itemname = ResManager.getInstance().getString("坐骑进阶丹");
					num = rndnum;	
				}else {
					int rndid = RandomUtils.random(3011,3012);
					List<Item> createItems = Item.createItems(rndid, 5 ,false,0 ,0, null);
					itemname = createItems.get(0).acqItemModel().getQ_name();	
					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_RNDITEM,gid);
					num = 5;
				}
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜！打开『{1}』获得了:{2}（{3}）。"),this.acqItemModel().getQ_name(),itemname,num+"");
			}
			
			
			
		}else if (this.getItemModelId() == 8071) {//绿色缘分袋
			//	铜币（10万~100万）    十倍经验丹（1－10）  \n圣兽令牌(朱雀令、玄武令随机一种5个）
			if (ManagerPool.backpackManager.getEmptyGridNum(player) <= 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("包裹已满，使用前请先清理一下。"));	
				return;
			}
			if ( player.getMoney() + 100000 > Global.BAG_MAX_COPPER) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("携带的铜币数量快要达到上限了，请先清理一下。"));	
				return;
			}
			
			
			long gid = Config.getId();
			String itemname="";
			int num = 0;
			if (ManagerPool.backpackManager.removeItem(player, this, 1, Reasons.ACTIVITY_YBCARD_USE, gid) ) {
				int rnd = RandomUtils.random(1,3);
				if (rnd == 1) {
					int rndnum = RandomUtils.random(10000,100000)*10;
					ManagerPool.backpackManager.changeMoney(player, rndnum, Reasons.ACTIVITY_MONEY, gid);
					itemname = ResManager.getInstance().getString("铜币");
					num = 	rndnum;	
				}else if (rnd == 2) {
					int rndnum = RandomUtils.random(1,10);
					List<Item> createItems = Item.createItems(1019, rndnum ,false,0 ,0, null);
					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_RNDITEM,gid);
					itemname = ResManager.getInstance().getString("十倍经验丹");
					num = rndnum;	
				}else {
					int rndid = RandomUtils.random(3013,3014);
					List<Item> createItems = Item.createItems(rndid, 5 ,false,0 ,0, null);
					itemname = createItems.get(0).acqItemModel().getQ_name();	
					ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_RNDITEM,gid);
					num = 5;
				}
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜！打开『{1}』获得了:{2}（{3}）。"),this.acqItemModel().getQ_name(),itemname,num+"");	
			}	
			
			
		}
		
		
		
		
		
	}

	
	@Override
	public void unuse(Player player, String... parameters) {

	}

}
