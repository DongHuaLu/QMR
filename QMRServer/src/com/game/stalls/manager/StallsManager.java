package com.game.stalls.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_itemBean;
import com.game.db.bean.Gold;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.stalls.Log.StallsLog;
import com.game.stalls.bean.StallsLogInfo;
import com.game.stalls.bean.StallsSingleLogInfo;
import com.game.stalls.message.ReqChangeStallsNameMessage;
import com.game.stalls.message.ReqChangeStallsNameToWorldMessage;
import com.game.stalls.message.ReqStallsAdjustPricesMessage;
import com.game.stalls.message.ReqStallsAdjustPricesToWorldMessage;
import com.game.stalls.message.ReqStallsBuyMessage;
import com.game.stalls.message.ReqStallsBuyToWorldMessage;
import com.game.stalls.message.ReqStallsBuycheckToWorldMessage;
import com.game.stalls.message.ReqStallsOffShelfMessage;
import com.game.stalls.message.ReqStallsOffShelfToWorldMessage;
import com.game.stalls.message.ReqStallsOpenUpMessage;
import com.game.stalls.message.ReqStallsOpenUpToWorldMessage;
import com.game.stalls.message.ReqStallsPlayerIdLookMessage;
import com.game.stalls.message.ReqStallsPlayerIdLookToWorldMessage;
import com.game.stalls.message.ReqStallsProductWasAddedMessage;
import com.game.stalls.message.ReqStallsProductWasAddedToWorldMessage;
import com.game.stalls.message.ReqStallsSearchMessage;
import com.game.stalls.message.ReqStallsSearchToWorldMessage;
import com.game.stalls.message.ReqStallsSortMessage;
import com.game.stalls.message.ReqStallsSortToWorldMessage;
import com.game.stalls.message.ResStallsBuyAddMoneyToGameMessage;
import com.game.stalls.message.ResStallsBuyDeductingFailToGameMessage;
import com.game.stalls.message.ResStallsBuyDeductingToGameMessage;
import com.game.stalls.message.ResStallsBuycheckToGameMessage;
import com.game.stalls.message.ResStallsExpMessage;
import com.game.stalls.message.ResStallsLooklogMessage;
import com.game.stalls.message.ResStallsOffShelfToGameMessage;
import com.game.stalls.message.ResStallsProductWasAddedFailToGameMessage;
import com.game.stalls.structs.StallsTransactionLog;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.WordFilter;


public class StallsManager {
	private static Object obj = new Object();
	private Logger log = Logger.getLogger(StallsManager.class);
	// 管理类实例
	private static StallsManager manager;

	private StallsManager() {}

	public static StallsManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new StallsManager();
			}
		}
		return manager;
	}
	
	/**得到开启摆摊功能等级需求
	 * 
	 * @return
	 */
	public int getlookStallslv(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(20);
		return global.getQ_int_value();
	}
	
	
	
	//-----------------------------------------------------------------
	/**
	 * 打开摆摊面板  分段发送数据 
	 * @param player
	 */
	 
	public void stReqStallsOpenUpMessage(Player player,ReqStallsOpenUpMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqStallsOpenUpToWorldMessage wmsg = new ReqStallsOpenUpToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setIndexlittle(msg.getIndexlittle());
		wmsg.setIndexLarge(msg.getIndexLarge());
		MessageUtil.send_to_world(wmsg);
	}
	

	
	/**
	 * 点击某个摊位，进行看摊消息
	 */
	
	public void stReqStallsPlayerIdLookMessage(Player player,ReqStallsPlayerIdLookMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		
		if (ManagerPool.teamManager.playerIsDie(player.getId()) ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，死亡状态不能进行看摊"));
			return;
		}
		
		if(player.getLevel() < getlookStallslv() ){
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("摊位功能开放等级为{1}级，您的等级不够"),String.valueOf(getlookStallslv()));
			return;
		}
		
		ReqStallsPlayerIdLookToWorldMessage wmsg = new ReqStallsPlayerIdLookToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setStallsplayerid(msg.getPlayerid());
		MessageUtil.send_to_world(wmsg);
	}

	
	
	/**
	 * 按照条件排序（如果是按照名字搜到到的数据，前端自行排序）
	 * @param player
	 * @param little
	 * @param large
	 * @param type 0，摊位星级，1，玩家等级，2，物品数量，3在售货币
	 */

	public void  stReqStallsSortMessage(Player player, ReqStallsSortMessage msg){
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqStallsSortToWorldMessage wmsg = new ReqStallsSortToWorldMessage();
		wmsg.setIndexLarge(msg.getIndexLarge());
		wmsg.setIndexlittle(msg.getIndexlittle());
		wmsg.setType(msg.getType());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);
	}
	
	
	//--------------------------------------------------------------------------------
	
	/**
	 * 商品上架
	 * @param player
	 * @param itemid	-1元宝，-2元宝
	 * @param goldnum	金币售价
	 * @param yuanbaonum   	元宝售价
	 * @param itemnum    要出售的货物数量
	 */
	public void  stReqStallsProductWasAddedMessage(Player player,ReqStallsProductWasAddedMessage msg ) {
		if (player.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用摆摊功能。"));
			return;
		}
		if (ManagerPool.teamManager.playerIsDie(player.getId())) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，死亡状态不能进行摊位操作。"));
			return;
		}
		int goldnum = msg.getPricegold();
		int yuanbaonum = msg.getPriceyuanbao();
		long itemid = msg.getGoodsid();
		int itemnum = msg.getNum();
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		
		if (goldnum == 0 && yuanbaonum == 0) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("请先输入对该物品的铜币或元宝定价。"));
			return ;
		}

		if (goldnum < 0 || yuanbaonum < 0) {
			return;
		}
		
		if (goldnum > 2100000000 || yuanbaonum > 2100000000) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("铜币或元宝定价不能超过21亿。"));
			return;
		}
		
		
		if (itemid > 0 && msg.getGoodsnum() >=10) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("在售物品已经达到上限10件，请先下架部分货物，或者刷新再试。"));
			return ;
		}
		
		if (itemnum <= 0) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，上架道具的数量不能为0。"));
			return ;
		}
		
		
		String itemstr = "";
		boolean issuccess = false;
		long action=Config.getId();
		if (itemid == -1 ) {	//上架金币
			if (ManagerPool.backpackManager.changeMoney(player,-itemnum,Reasons.QUCHUTMPGOLD,action)) {
				Item goodsitem = Item.createMoney(itemnum);	//产生一个金币道具
				issuccess = true;
				itemstr = JSONserializable.toString(goodsitem);
				stStallsLog(player.getId(),-1,goodsitem,goldnum,yuanbaonum);	//数据库日志记录
			}else{
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您没{1}铜币，无法上架。"),String.valueOf(itemnum));
				return;
			 }
		}else if (itemid == -2) {//上架元宝
//			Gold gold = ManagerPool.backpackManager.getGold(player);
			if(ManagerPool.backpackManager.checkGold(player, itemnum)) {
				ManagerPool.backpackManager.changeGold(player,-itemnum,Reasons.STYBSHANGJIA,action);
				Item goodsitem = Item.createGold(itemnum,false); //产生一个元宝道具
				itemstr = JSONserializable.toString(goodsitem);
				issuccess = true;
				stStallsLog(player.getId(),-1,goodsitem,goldnum,yuanbaonum);	//数据库日志记录
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您没{1}元宝，无法上架。"),String.valueOf(itemnum));
				return;
			}

		}else{		//上架道具
			Item goodsitem  = ManagerPool.backpackManager.getItemById(player,itemid);	//得到包裹道具
			if (goodsitem == null) {//道具异常
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，您选择的道具状态异常，无法上架。"));
				return ;
			}
			if (goodsitem.isBind() || goodsitem.getLosttime() > 0 ) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，绑定道具或者限时道具不能上架。"));
				return ;
			}
			if(goodsitem.isTrade()){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易中的物品不能上架。"));
				return;
			}
			if (itemnum > goodsitem.getNum() ) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该物品出售数量不符。"));
				return ;
			}else if (itemnum == goodsitem.getNum()) {
				if(ManagerPool.backpackManager.removeItem(player,itemid,Reasons.SHANGJIAITEM,action)) {
					goodsitem.setGridId(0);
					itemstr = JSONserializable.toString(goodsitem);
					issuccess = true;
					stStallsLog(player.getId(),-1,goodsitem,goldnum,yuanbaonum);	//数据库日志记录
				}
			}else {		//拆分道具
				goodsitem.setNum(goodsitem.getNum()-itemnum);	
				Item xitem = null;
				try {
					xitem = goodsitem.clone();
					xitem.setId(Config.getId());
					xitem.setNum(itemnum);
					xitem.setGridId(0);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				
				itemstr = JSONserializable.toString(xitem);
				issuccess = true;
				stStallsLog(player.getId(),-1,xitem,goldnum,yuanbaonum);	//数据库日志记录
				MessageUtil.tell_player_message(player,ManagerPool.backpackManager.getItemChangeMessage(goodsitem));
			}
		}

		if (issuccess) {
			ReqStallsProductWasAddedToWorldMessage wmsg = new ReqStallsProductWasAddedToWorldMessage();
			wmsg.setGoodsid(msg.getGoodsid());
			wmsg.setPricegold(msg.getPricegold());
			wmsg.setPriceyuanbao(msg.getPriceyuanbao());
			wmsg.setStallsplayerid(player.getId());
			wmsg.setItem(itemstr);
			ManagerPool.playerManager.savePlayer(player);
			MessageUtil.send_to_world(wmsg);	//转发到世界服务器
		}
	}
	
	
	/**给玩家包裹塞东西
	 * 
	 * @param player
	 * @param item
	 * @return
	 */
	public boolean setStallsGoods(Player player , Item item){
		long action=Config.getId();
		if (item.getId() > 0 && item.getItemModelId() > 0) {
			if(ManagerPool.backpackManager.getEmptyGridNum(player) > 0) {
				if(ManagerPool.backpackManager.addItem(player,item,Reasons.BUYITEM,action)){
					return true ;
				}else {
					return false;
				}		//返还道具
			}
			
		}else if (item.getId() == -1 || item.getItemModelId() == -1) {	//金币
			if (player.getMoney() +item.getNum() > 0 && player.getMoney() +item.getNum() < Global.BAG_MAX_COPPER ) {
				ManagerPool.backpackManager.changeMoney(player,item.getNum() ,Reasons.BUYGOLD,action);		//得到金币
				return true ;
			}

		}else if (item.getId() == -2 || item.getItemModelId() == -2) {	//元宝
			Gold golddata = player.getGold();
			if (golddata !=null && golddata.getGold() > 0 && golddata.getTotalGold() > 0) {
				if (golddata.getGold() +item.getNum() > 0 && golddata.getGold() +item.getNum() < Global.BAG_MAX_GOLD ) {
					return ManagerPool.backpackManager.addGold(player,item.getNum(),Reasons.STYBGOUMAI,action);
				}
			}else {
				ManagerPool.backpackManager.changeTmpGold(player,item.getNum());
			}
			return true ;
		}
		return false;
	}
		
		
		

	
	
	
	
	//--------------------------------------------------------------------------------
	
	/**
	 * 商品上架失败返还
	 * 
	 * @param msg
	 */
	public void  stResStallsProductWasAddedFailToGameMessage(ResStallsProductWasAddedFailToGameMessage msg ) {
		Player player  = ManagerPool.playerManager.getOnLinePlayer(msg.getStallsplayerid());
		Item item = (Item) JSONserializable.toObject(msg.getItem(),Item.class);
		if (item != null) {
			long itemid = item.getId();
			boolean issuccess = false; 
			if (player != null ) {
				issuccess = setStallsGoods(player,item);
			}
			
			if (issuccess == false) {
				List<Item> items = new ArrayList<Item>();
				items.add(item);
				if (itemid > 0 && item.getItemModelId() > 0) {
					ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊商品上架失败返还道具"),(byte) 1,0,items);
				}else if (item.getItemModelId() == -1) {
					ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊铜币上架失败返还"),(byte) 1,0,items);
				}else if (item.getItemModelId() == -2) {	//元宝
					ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊元宝上架失败返还"),(byte) 1,0,items);
				}
				
				if (player != null) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("上架失败了，由于您的包裹已满，商品已经从邮件返还给您。"));
				}
				
			}
		}
	}
	
	
	//--------------------------------------------------------------------------------
	
	
	
	
	/**
	 * 购买商品
	 * @param player  购买者
	 * @param itemid  元宝或者元宝  传入 -1 -2
	 * @param goldnum
	 * @param yuanbaonum
	 */

	public void  stReqStallsBuyMessage(Player player,ReqStallsBuyMessage msg) {
		if (ManagerPool.teamManager.playerIsDie(player.getId()) ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，死亡状态不能进行摊位操作。"));
			return;
		}
		if (player.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用摆摊功能。"));
			return;
		}
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		int goldnum = msg.getPricegold();
		int yuanbaonum = msg.getPriceyuanbao();

		if (msg.getGoodsid() > 0) {
			if(ManagerPool.backpackManager.getEmptyGridNum(player) <= 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的包裹已满，购买前请先清理包裹。"));
				return  ;
			}
		}

		
		if (goldnum > 0 &&  goldnum > player.getMoney() ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的金币数量不足，无法购买。"));
			return ;
		}

		if(yuanbaonum > 0 && !ManagerPool.backpackManager.checkGold(player, yuanbaonum)){
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的元宝数量不足，无法购买。"));
			return ;
		}
		
		
		ReqStallsBuycheckToWorldMessage wmsg = new ReqStallsBuycheckToWorldMessage();
		
		wmsg.setGoodsid(msg.getGoodsid());
		wmsg.setPricegold(msg.getPricegold());
		wmsg.setPriceyuanbao(msg.getPriceyuanbao());
		wmsg.setStallsplayerid(msg.getPlayerid());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);	//转发到世界服务器
	}

	
	/**返回购买商品检查结果，商品正常，则扣钱
	 * 
	 * @param player
	 * @param msg
	 */
	public void stResStallsBuycheckToGameMessage(ResStallsBuycheckToGameMessage msg){
		Player player  = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player == null) {
			return;
		}
		long action=Config.getId();
		if (msg.getStatus() == 0) {
			boolean is = false;
			if (msg.getPricegold() > 0 || msg.getPriceyuanbao() > 0) {
				if (msg.getPricegold() > 0 &&  msg.getPricegold() > player.getMoney() ) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的金币数量不足，无法购买。"));
					return ;
				}

				if (msg.getPriceyuanbao() > 0 && !ManagerPool.backpackManager.checkGold(player, msg.getPriceyuanbao())) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的元宝数量不足，无法购买。"));
					return ;
				}
				
				
				if (msg.getPricegold() > 0) {
					if( ManagerPool.backpackManager.changeMoney(player,-msg.getPricegold(),Reasons.SUBGOLD,action)) {
						is =true;
					}
				}
				
				if (msg.getPriceyuanbao() > 0) {
					if(ManagerPool.backpackManager.checkGold(player, msg.getPriceyuanbao())) {
						ManagerPool.backpackManager.changeGold(player,-msg.getPriceyuanbao(),Reasons.STYBGOUMAIDAOJU,action);
						is =true;
					}
				}
				
				if (is) {
					ReqStallsBuyToWorldMessage wmsg = new ReqStallsBuyToWorldMessage();
					wmsg.setPlayerid(player.getId());
					wmsg.setGoodsid(msg.getGoodsid());
					wmsg.setStallsplayerid(msg.getStallsplayerid());
					wmsg.setPricegold(msg.getPricegold());
					wmsg.setPriceyuanbao(msg.getPriceyuanbao());
					MessageUtil.send_to_world(wmsg);	//转发到世界服务器
					return;
				}
			}	
		}else if (msg.getStatus() == 2) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该商品无法购买，摊主已经修改了价格，请刷新摊位后再购买。"));
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该商品无法购买，可能已经下架或被其他玩家抢购，请刷新摊位。"));
		}
		
	}
	
	
	
	
	/**
	 * 接收购买的道具
	 * 
	 */
	public void stResStallsBuyDeductingToGameMessage( ResStallsBuyDeductingToGameMessage msg){
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		Item item = (Item) JSONserializable.toObject(msg.getItem(),Item.class);
		int goldnum = msg.getPricegold();
		int yuanbaonum = msg.getPriceyuanbao();
		if (item != null) {
			int  num = item.getNum();
			ManagerPool.equipstrengManager.clearStrengthenLog(item);//清除强化记录
			long itemid = item.getId();
			boolean issuccess = false; 
			if (player != null ) {
				issuccess = setStallsGoods(player,item);
			}
			Q_itemBean itemdata = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
			stStallsLog(msg.getStallsplayerid(),msg.getPlayerid(),item,goldnum,yuanbaonum);	//数据库日志记录
			if (issuccess == false) {
				List<Item> items = new ArrayList<Item>();
				items.add(item);
				if (itemid > 0 && item.getItemModelId() > 0) {
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("您从摊位购买的道具"),(byte)1,0,items);
				}else if (itemid == -1 || item.getItemModelId() == -1) {//金币
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("您从摊位购买的铜币"),(byte)1,0,items);
				}else if (itemid == -2 || item.getItemModelId() == -2) {	//元宝
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("您从摊位购买的元宝"),(byte)1,0,items);
				}
				
				if (player != null) {
					MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("由于您的包裹已满，或铜币元宝达到上限，您从摊位购买的{1}*{2}已经从邮件发送给您。"),itemdata.getQ_name(),num+"");
				}
				
			} else  {
				addStallsTransactionLogBuy(player,msg.getStallsplayerid(),msg.getStallsplayername(),msg.getItem(),goldnum,yuanbaonum,msg.getTradersid());
				String txt = "";
				if (goldnum > 0) {
					txt = ResManager.getInstance().getString("铜币(")+goldnum+") ";
				}
				if (yuanbaonum > 0) {
					txt = txt + ResManager.getInstance().getString("元宝(")+yuanbaonum+") ";
				}
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("您花费{1}成功购买{2}({3})。"),txt,itemdata.getQ_name(),num+"");
				
				if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.STALL_EXP_CD,null) == false && player.getNonage() !=3) {//加摆摊经验
					ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.STALL_EXP_CD,null,Global.STALL_EXP_TIME);
					int exp = player.getLevel()*player.getLevel();
					ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.STALLS);
					ResStallsExpMessage expmsg = new ResStallsExpMessage();
					if (player.getNonage()==2 ) {
						exp = exp/2;
					}
					expmsg.setExp(exp);
					MessageUtil.tell_player_message(player,expmsg);
					MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜摊位交易成功，增加人物经验：{1}"),String.valueOf(exp));
				}
//				Gold gold = ManagerPool.backpackManager.getGold(player);
				int playeryuanbao = 0;
				if (player.getGold() != null) {
					playeryuanbao = player.getGold().getGold();
				}
				if (playeryuanbao == 0 && item.getItemModelId() == -2) {
					//临时元宝来源展示日志
					ManagerPool.transactionsManager.saveTmpyuanbaolog(player,msg.getStallsplayerid(),msg.getStallsplayername(),(byte)3,num);
				}
			}	
		}else {
			log.error("玩家["+msg.getPlayerid()+"]摊位购买道具["+msg.getItem()+"]添加失败");
		}
	}
	
	
	
	
	/**购买道具成功，给摊主加钱
	 * 
	 * @param msg
	 */
	public void stResStallsBuyAddMoneyToGameMessage(ResStallsBuyAddMoneyToGameMessage msg){
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getStallsplayerid());
		
		long action =Config.getId();
		if (player != null) {
			byte type = 0;
			String str = "";
			if (msg.getPricegold() > 0 ) {
				str = ResManager.getInstance().getString("铜币")+ msg.getPricegold();
				if (ManagerPool.backpackManager.changeMoney(player,msg.getPricegold(),Reasons.GETGOLD,action)==false) {//摊主得到金币
					List<Item> items = new ArrayList<Item>();
					items.add(Item.createMoney(msg.getPricegold()));
					ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊出售道具获得货款"),(byte)1,0,items);
					type = 1;
				}	
			}
			
			if (msg.getPriceyuanbao() > 0) {
				str = str+ ResManager.getInstance().getString("元宝")+ msg.getPriceyuanbao();
//				Gold gold = ManagerPool.backpackManager.getGold(player);
				int playeryuanbao = 0;
				int recharge = 0;
				Gold goldinfo =player.getGold();
				if ( goldinfo != null) {
					playeryuanbao = goldinfo.getGold();
					recharge = goldinfo.getTotalGold();
				}
				if (playeryuanbao > 0 && recharge > 0) {
					if (ManagerPool.backpackManager.addGold(player,msg.getPriceyuanbao(),Reasons.STYBHUOKUAN,action)==false) {//摊主得到元宝
						Item ybitem=Item.createGold(msg.getPriceyuanbao(), false);
						List<Item> ybitems = new ArrayList<Item>();
						ybitems.add(ybitem);
						ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊出售道具获得货款"),(byte)1,0,ybitems);
						type = 1;
					}
				}else {
					if (ManagerPool.backpackManager.changeTmpGold(player,msg.getPriceyuanbao())==false) {//摊主得到元宝
						Item ybitem=Item.createGold(msg.getPriceyuanbao(), false);
						List<Item> ybitems = new ArrayList<Item>();
						ybitems.add(ybitem);
						ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊出售道具获得货款"),(byte)1,0,ybitems);
						type = 1;
					}
					//可领取元宝日志记录
					ManagerPool.transactionsManager.saveTmpyuanbaolog(player,msg.getPlayerid(),msg.getPlayername(),(byte)2,msg.getItem(),0,msg.getPricegold(),msg.getPriceyuanbao());
				}
			}
			addStallsTransactionLogSell(player,msg.getPlayerid(),msg.getPlayername(),msg.getItem(),msg.getPricegold(),msg.getPriceyuanbao(),msg.getTradersid());
			if (type == 0) {
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("您的摊位出售了一件商品，您获得{1}。"),str);
			}else{
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("您的摊位出售了一件商品,请从邮件领取货款。"));
			}
			//加摆摊经验
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.STALL_EXP_CD,null) == false && player.getNonage() != 3) {
				ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.STALL_EXP_CD,null,Global.STALL_EXP_TIME);
				int exp = player.getLevel()*player.getLevel();
				ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.STALLS);
				ResStallsExpMessage expmsg = new ResStallsExpMessage();
				if (player.getNonage()==2 ) {
					exp = exp/2;
				}
				expmsg.setExp(exp);
				MessageUtil.tell_player_message(player,expmsg);
				MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜摊位交易成功，增加人物经验：{1}"),String.valueOf(exp));
			}
		}else{
			if (msg.getPricegold() > 0 ) {
				List<Item> items = new ArrayList<Item>();
				items.add(Item.createMoney(msg.getPricegold()));
				ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊出售道具获得货款"),(byte)1,0,items);
			}
			
			if (msg.getPriceyuanbao() > 0 ) {
				List<Item> items = new ArrayList<Item>();
				items.add(Item.createGold(msg.getPriceyuanbao(),false));
				ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摆摊出售道具获得货款"),(byte)1,0,items);
			}
			
		}
	}
	

	
	
	
	/**购买商品失败，返还钱
	 * 
	 * @param msg
	 */
	public void stResStallsBuyDeductingFailToGameMessage(ResStallsBuyDeductingFailToGameMessage msg){
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		long action=Config.getId();
		if (player != null) {
			byte type = 0;
			if (msg.getPricegold() > 0 ) {
				if (ManagerPool.backpackManager.changeMoney(player,msg.getPricegold(),Reasons.GOUMAISHIBAIGOLD,action) == false) {//买家得到金币
					type = 1;
					Item item=Item.createMoney(msg.getPricegold());
					List<Item> items = new ArrayList<Item>();
					items.add(item);
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摊位商品购买失败，返还给您的铜币"),(byte)1,0,items);
				}	
			}
			
			if (msg.getPriceyuanbao() > 0) {
//				Gold gold = ManagerPool.backpackManager.getGold(player);
				int playeryuanbao = 0;
				int recharge = 0;
				Gold goldinfo= player.getGold();
				if (goldinfo != null) {
					playeryuanbao = goldinfo.getGold();
					recharge = goldinfo.getTotalGold();
				}
				if (playeryuanbao > 0 && recharge > 0) {
					if (ManagerPool.backpackManager.addGold(player,msg.getPriceyuanbao(),Reasons.STGOUMAISHIBAI,action) ==false) {//买家得到元宝
						type = 1;
					}
				}else {
					if (ManagerPool.backpackManager.changeTmpGold(player,msg.getPriceyuanbao())==false) {//买家得到元宝（放入临时元宝）
						type = 1;
					}
				}
				if (type == 1) {
					Item item=Item.createGold(msg.getPriceyuanbao(), false);
					List<Item> items = new ArrayList<Item>();
					items.add(item);
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摊位商品购买失败，返还给您的元宝"),(byte)1,0,items);
				}
				
			}
			
			if (type == 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您所希望购买的物品已不存在，可能被其他玩家抢购或已被下架，货款已返还给您。"));
			}else{
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您所希望购买的物品已不存在，可能被其他玩家抢购或已被下架，货款已从邮件返还给您。"));
			}
			
		}else {
			if (msg.getPricegold() > 0 ) {
				Item item=Item.createMoney(msg.getPricegold());
				List<Item> items = new ArrayList<Item>();
				items.add(item);
				ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摊位商品购买失败，返还给您的铜币"),(byte)1,0,items);
			}
			
			if (msg.getPriceyuanbao() > 0) {
				Item item=Item.createGold(msg.getPriceyuanbao(), false);
				List<Item> items = new ArrayList<Item>();
				items.add(item);
				ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("摊位商品购买失败，返还给您的元宝"),(byte)1,0,items);
			}
			
		}
	}
	
	
	
	//------------------------------------------------------------------------------
	
	

	/**商品下架,检查并发送到世界服务器
	 * 
	 * @param player
	 * @param itemid  铜币-1，元宝-2
	 */
	public void stReqStallsOffShelfMessage(Player player,ReqStallsOffShelfMessage msg) {
		if (ManagerPool.teamManager.playerIsDie(player.getId()) ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，死亡状态不能进行摊位操作。"));
			return;
		}
		if (player.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用摆摊功能。"));
			return;
		}
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		
		if (msg.getGoodsid() > 0) {
			if (ManagerPool.backpackManager.getEmptyGridNum(player) < 1) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包已满，无法下架商品，请先清理包裹。"));
				return;
			}
		}else if (msg.getGoodsid() == -1) {
			int num = msg.getNum() + player.getMoney();
			if (num > Global.BAG_MAX_COPPER || num < 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，下架的铜币加您身上携带铜币已经达到上限，无法下架商品。"));
				return ;
			}
			
		}else if (msg.getGoodsid() == -2) {
			int playeryuanbao = 0;
			if (player.getGold() != null) {
				playeryuanbao = player.getGold().getGold();
			}
			int num = msg.getNum() +playeryuanbao;
			if (num > Global.BAG_MAX_GOLD || num < 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，下架的元宝加您身上携带元宝已经达到上限，无法下架商品。"));
				return ;
			}
		}
		
		 if (msg.getGoodsid() == -2 || msg.getGoodsid() == -1 ||msg.getGoodsid() > 0 ) {
			ReqStallsOffShelfToWorldMessage wmsg = new ReqStallsOffShelfToWorldMessage();
			wmsg.setGoodsid(msg.getGoodsid());
			wmsg.setStallsplayerid(player.getId());
			MessageUtil.send_to_world(wmsg);	//转发到世界服务器
		 }
	}
	

	
	/**商品下架，世界服务器发送给game消息
	 * 
	 * @param msg
	 */
	public void stResStallsOffShelfToGameMessage(ResStallsOffShelfToGameMessage msg ){
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getStallsplayerid());
		Item item = null;
		List<Item> items = new ArrayList<Item>();
		boolean issuccess = false;
		if (msg.getItem().length() > 1) {
			item = (Item) JSONserializable.toObject(msg.getItem(),Item.class);
			if (item != null) {
				if (player != null) {
					 issuccess = setStallsGoods(player,item);
				}
			}
		}else{
			log.error("玩家ID "+msg.getStallsplayerid()+",商品下架,道具不存在"+msg.getItem());
			if (player !=null) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("道具下架失败。"));
			}
		}
			
		
		if (issuccess) {
			String name = "";
			if (item.getItemModelId() == -1 || item.getItemModelId() == -2) {
				name = ResManager.getInstance().getString("铜币");
				if (item.getItemModelId() == -2) {
					name = ResManager.getInstance().getString("元宝");
				}
			}else{
				name = item.acqItemModel().getQ_name();
			}
			MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("商品『")+name+ResManager.getInstance().getString("』下架成功。"));
			stStallsLog(msg.getStallsplayerid(),-3,item,0,0);	//数据库日志记录
		}else {
			if (item == null) {
				log.error("玩家ID "+msg.getStallsplayerid()+",商品下架,道具不存在"+msg.getItem());
				return;
			}
			items.add(item);
			if (item.getId() > 0 && item.getItemModelId() > 0) {
				ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("从摊位下架的商品，由于下架后您不在线或者包裹已满，所以邮寄给您。"),(byte)1,0,items);
			}else if (item.getId() == -1 || item.getItemModelId() == -1) {
				ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("从摊位下架的铜币，由于下架后您不在线或者包裹已满，所以邮寄给您。"),(byte)1,0,items);
			}else if (item.getId() == -2 || item.getItemModelId() == -2) {	//元宝
				ManagerPool.mailServerManager.sendSystemMail(msg.getStallsplayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("从摊位下架的元宝，由于下架后您不在线或者包裹已满，所以邮寄给您。"),(byte)2,0,items);
			}
			if (player !=null) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("由于您的包裹满了，下架的商品通过邮件发送给您了。"));
			}
			stStallsLog(msg.getStallsplayerid(),-3,item,0,0);	//数据库日志记录
		}

	}
	
		

	
	
	
	
	
	
	//--------------------------------------------------------------------------------
	
	/**
	 * 调整商品
	 * @param player
	 * @param msg
	 */
	public void  stReqStallsAdjustPricesMessage(Player player,ReqStallsAdjustPricesMessage msg) {
		if (player.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用摆摊功能。"));
			return;
		}
		if(msg.getPricegold() <= 0 && msg.getPriceyuanbao() <= 0 ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，修改的价格不能小于0。"));
			return;
		}
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		
//		if(msg.getNum() <= 0 ) {
//			MessageUtil.notify_player(player,Notifys.ERROR,"很抱歉，修改的数量不能小于0。");
//			return;
//		}
		
		ReqStallsAdjustPricesToWorldMessage wmsg = new ReqStallsAdjustPricesToWorldMessage();
		//wmsg.setCurrentnum(msg.getCurrentnum());
		//wmsg.setNum(msg.getNum());
		wmsg.setGoodsid(msg.getGoodsid());
		wmsg.setGoodsmodelid(msg.getGoodsmodelid());
		wmsg.setPos(msg.getPos());
		wmsg.setPricegold(msg.getPricegold());
		wmsg.setPriceyuanbao(msg.getPriceyuanbao());
		wmsg.setStallsplayerid(player.getId());
		stStallsLog(player.getId(),-2,wmsg);	//数据库日志记录
		MessageUtil.send_to_world(wmsg);	//转发到世界服务器
	}
	
	

	
	


	
	
	/**增加展示用的交易日志   ，购买日志
	 * 
	 * @param playerid
	 * @param name
	 * @param item
	 * @param pricegold
	 * @param priceyuanbao
	 */

	public void addStallsTransactionLogBuy(Player player ,long xplayerid,String xname,String itemstr,int pricegold , int priceyuanbao,long tradersid) {
		if (player != null) {
			StallsTransactionLog alog = new StallsTransactionLog();//买方
			long time = System.currentTimeMillis()/1000;
			Item item = (Item) JSONserializable.toObject(itemstr,Item.class);
			alog.setItem(item);
			alog.setPricegold(pricegold);
			alog.setPriceyuanbao(priceyuanbao);
			alog.setTransactiontype((byte) 1); //购买
			alog.setTradersid(xplayerid);
			alog.setTradersname(xname);
			alog.setTransactiontime(time);
			List<StallsTransactionLog> alogs = player.getStallsloglist();
			if (alogs.size() >= 20) {
				alogs.remove(19);
			}
			alogs.add(0, alog);
		}
	}
	
	
	
	
	/**增加展示用的交易日志   ，出售日志
	 * 
	 * @param playerid
	 * @param name
	 * @param item
	 * @param pricegold
	 * @param priceyuanbao
	 */
	public void addStallsTransactionLogSell(Player player ,long xplayerid,String xname,String itemstr,int pricegold , int priceyuanbao,long tradersid) {
		if (player != null) {
			StallsTransactionLog xlog = new StallsTransactionLog();//卖方
			long time = System.currentTimeMillis()/1000;
			Item item = (Item) JSONserializable.toObject(itemstr,Item.class);
			xlog.setItem(item);
			xlog.setPricegold(pricegold);
			xlog.setPriceyuanbao(priceyuanbao);
			xlog.setTransactiontype((byte) 0); //出售
			xlog.setTradersid(xplayerid);
			xlog.setTradersname(xname);
			xlog.setTransactiontime(time);
			
			List<StallsTransactionLog> xlogs = player.getStallsloglist();
			if (xlogs.size() >= 20) {
				xlogs.remove(19);
			}
			xlogs.add(0, xlog);
		}
	}
		
	
	/**发送给前端，摆摊交易日志
	 * 
	 * @param player
	 */
	public void stReqStallsLooklogMessage(Player player) {
		List<StallsTransactionLog> logs = player.getStallsloglist();
		ResStallsLooklogMessage msg = new ResStallsLooklogMessage();
		StallsLogInfo log = new StallsLogInfo();
		for (int i = 0; i < logs.size(); i++) {
			StallsSingleLogInfo loginfo = new StallsSingleLogInfo();
			loginfo.setIteminfo(logs.get(i).getItem().buildItemInfo());
			loginfo.setPricegold(logs.get(i).getPricegold());
			loginfo.setPriceyuanbao(logs.get(i).getPriceyuanbao());
			loginfo.setTradersid(logs.get(i).getTradersid());
			loginfo.setTradersname(logs.get(i).getTradersname());
			loginfo.setTransactiontime((int)logs.get(i).getTransactiontime());
			loginfo.setTransactiontype(logs.get(i).getTransactiontype());
			log.getStallsloglist().add(loginfo);
		}
		msg.setStallslogInfo(log);
		MessageUtil.tell_player_message(player, msg);
	}
	
	
	
	
	/**检测评价条件
	 * 
	 * @param player
	 * @param xplayer
	 * @return
	 */
//	public boolean stCkStallsRating(Player player,Player xplayer) {
//		if (player.getIp().equals(xplayer.getIp()))	{		//IP相同
//			return false;
//		}
//		
//		StallsInfoSave xpinof = xplayer.getStallsinfo();	//从人物身上得到摊位数据
//		RatingData ratingData = xpinof.getRatingdata();		//得到评价记录
//		ratingData.removeRatingData();
//		if (ratingData.getEvaluatelist().size() > 0 ) {		//大于0就表示没删除
//			if (ratingData.checkRating(player.getId())) {
//				return false;
//			}
//		}
//		if (xplayer.getLevel() > 30 && player.getLevel() > 30 ) {
//			return true;
//		}
//		return false;
//	}
	

	/**玩家评分
	 * 
	 */
//	public void stStallsRating(Player player,long pid) {
//		Player xplayer = ManagerPool.playerManager.getOnLinePlayer(pid);
//		if (xplayer == null) {
//			return ;
//		}
//	
//		if(stCkStallsRating( player, xplayer)) {
//			StallsInfoSave xpinof = xplayer.getStallsinfo();	//从人物身上得到摊位数据
//			RatingData ratingData = xpinof.getRatingdata();
//			ratingData.getEvaluatelist().add(player.getId());
//			ratingData.setEvaluatetime(System.currentTimeMillis());	//最后评分时间记录
//			int num = xpinof.getStallsscore();
//			xpinof.setStallsscore(num + 1);
//			MessageUtil.notify_player(player,Notifys.NORMAL,"您摆摊售出商品获得好评。");
//		}
//	}
	
	
	
	
	/**摆摊搜索目标
	 * 
	 * @param player
	 * @param pname
	 * @param goodsname
	 * @param isyuanbaogold
	 */
	public void stReqStallsSearchMessage(Player player,ReqStallsSearchMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		CooldownTypes type = CooldownTypes.STALL_SEARCH;
		if (msg.getGoldyuanbao() == 1) {
			 type = CooldownTypes.STALL_MONEYSEARCH;
		}else if (msg.getGoldyuanbao() == 2) {
			 type = CooldownTypes.STALL_YBSEARCH;
		}
		
		if (ManagerPool.cooldownManager.isCooldowning(player,type,null) == false) {
			ManagerPool.cooldownManager.addCooldown(player,type,null,Global.STALL_SEARCH_TIME);
		}else {
			if (msg.getGoldyuanbao() == 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("搜索过于频繁请稍后再试。"));
				return;
			}
		}
		
		ReqStallsSearchToWorldMessage wmsg = new ReqStallsSearchToWorldMessage();
		wmsg.setGoldyuanbao(msg.getGoldyuanbao());
		wmsg.setGoodsname(msg.getGoodsname());
		wmsg.setPlayerid(player.getId());
		wmsg.setPlayername(msg.getPlayername());
		MessageUtil.send_to_world(wmsg);	//转发到世界服务器

	}
	
	
	
	
	//----------------数据库记录--------------------
	/**
	 * 摆摊日志
	 */
	public void stStallsLog(long stallsId,long buyId ,Item item,int gold,int yuanbao) {
		if(item.getItemModelId() > 0 && !BackpackManager.getInstance().isLog(item.getItemModelId())){
			return;
		}
		StallsLog log = new StallsLog();
	
		log.setStallsId(stallsId);

		log.setGoodsNum(item.getNum());
		log.setGoodsModid(item.getItemModelId());
		if(item.getItemModelId() == -1) {
			log.setGoodsName("铜币");
			log.setGoodsInfo("铜币");
		}else if (item.getItemModelId() == -2) {
			log.setGoodsName("元宝");
			log.setGoodsInfo("元宝");
		}else {
			log.setGoodsName(item.acqItemModel().getQ_name());
			log.setGoodsInfo(JSONserializable.toString(item));
		}
		log.setGoodsOnlyid(item.getId());
		log.setPricegold(gold);
		log.setPriceyuanbao(yuanbao);
		
		log.setBuyId(buyId);	//-1商品上架，-2商品调整，-3商品下架
		
		LogService.getInstance().execute(log);
	}
	
	
	
	public void stStallsLog(long stallsId,long buyId ,ReqStallsAdjustPricesToWorldMessage msg) {
//		if(!BackpackManager.getInstance().isLog(msg.getGoodsmodelid())){
//			return;
//		}
		StallsLog log = new StallsLog();
	
		log.setStallsId(stallsId);

		log.setGoodsNum(msg.getNum());
		log.setGoodsModid(msg.getGoodsmodelid());
		if(msg.getGoodsmodelid() == -1) {
			log.setGoodsName("铜币");
			log.setGoodsInfo("铜币");
		}else if (msg.getGoodsmodelid() == -2) {
			log.setGoodsName("元宝");
			log.setGoodsInfo("元宝");
		}else {
			Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(msg.getGoodsmodelid());
			if (model != null) {
				log.setGoodsName(model.getQ_name());
				log.setGoodsInfo("调整");
			}
		}
		log.setGoodsOnlyid(msg.getGoodsid());
		log.setPricegold(msg.getPricegold());
		log.setPriceyuanbao(msg.getPriceyuanbao());
		
		log.setBuyId(buyId);	//-1商品上架，-2商品调整，-3商品下架

		LogService.getInstance().execute(log);
	}
	
	
	
	
	/**修改摊位名称
	 * 
	 * @param player
	 * @param stname
	 */

	public void stReqChangeStallsNameMessage(Player player ,ReqChangeStallsNameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，摆摊功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		String stname = msg.getName();
		if (stname == null) {
			stname = "";
		}
		if (stname.equals("") || WordFilter.getInstance().hashBadWords(stname) == false) {
			ReqChangeStallsNameToWorldMessage wmsg = new ReqChangeStallsNameToWorldMessage();
			wmsg.setStallsplayerid(player.getId());
			wmsg.setName(msg.getName());
			MessageUtil.send_to_world(wmsg);	//转发到世界服务器
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您输入的摊位名字中存在非法或敏感字符。"));
		}
	}

	
}
	
	
	
	
















