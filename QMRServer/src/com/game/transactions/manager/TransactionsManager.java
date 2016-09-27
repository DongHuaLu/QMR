package com.game.transactions.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.db.bean.Gold;
import com.game.dblog.LogService;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.bean.PlayerAppearanceInfo;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.setupmenu.struts.SetupMenuType;
import com.game.structs.Reasons;
import com.game.transactions.Log.TransactionsLog;
import com.game.transactions.bean.RoleModeInfo;
import com.game.transactions.bean.TmpYuanbaoLogInfo;
import com.game.transactions.message.ResCanreceiveYuanbaoMessage;
import com.game.transactions.message.ResTmpYuanbaoLogMessage;
import com.game.transactions.message.ResTransactionsCanceledMessage;
import com.game.transactions.message.ResTransactionsChangeGoldMessage;
import com.game.transactions.message.ResTransactionsChangeYuanbaoMessage;
import com.game.transactions.message.ResTransactionsErrorMessage;
import com.game.transactions.message.ResTransactionsIntoItemMessage;
import com.game.transactions.message.ResTransactionsLaunchMessage;
import com.game.transactions.message.ResTransactionsRemoveItemMessage;
import com.game.transactions.message.ResTransactionsSetStateMessage;
import com.game.transactions.message.ResTransactionsStartMessage;
import com.game.transactions.message.ResTransactionsSuccessMessage;
import com.game.transactions.structs.ItemLogData;
import com.game.transactions.structs.TempYuanbaoLogData;
import com.game.transactions.structs.Transactions;
import com.game.transactions.structs.TsInfo;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;


public class TransactionsManager {

	//交易范围  25像素=1格子
	private int TRANSACTION_AREA = MapUtils.GRID_BORDER*30 ;
	//交易请求超时 （秒）
	private int TRANSACTION_REQ = 60*10 ;
	
	
	//验证交易用
	private static  ConcurrentHashMap<Long,long[]> tmptransactionslist = new ConcurrentHashMap<Long, long[]>();

	private static Object obj = new Object();
	// 管理类实例
	private static TransactionsManager manager;

	private TransactionsManager() {
	}

	public static TransactionsManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new TransactionsManager();
			}
		}
		return manager;
	}

	/**
	 * 计时器调用，自动删除超时交易请求
	 */
	public void stCleantransactionslist(){
		long time = System.currentTimeMillis()/1000;
		Iterator<Entry<Long, long[]>> tab = tmptransactionslist.entrySet().iterator();
		while (tab.hasNext()) { 
		    Entry<Long, long[]> entry = tab.next(); 
		    long[] list =  entry.getValue();
		    if (time - list[2] > TRANSACTION_REQ ) {	//超时
		    	tab.remove();
			}
		}
	}
	
	
	
	/**
	 * 从临时交易人员信息列表获取A玩家ID
	 * @param tsid
	 * @param bid
	 * @return
	 */
	public long gettradersAid(long tsid,long bid){
		stCleantransactionslist();// 获取交易请求前，先清理过期的交易请求
		long[] tslist = tmptransactionslist.get(tsid);
		if (tslist != null) {
			if(tslist[1] == bid ) {
				return tslist[0];
			}
		}
		return 0;
	}

	/**
	 * 根据交易ID移除临时交易人员信息列表
	 * 
	 * @param tsid
	 */
	public void removetraders(long tsid) {
		if (tmptransactionslist.get(tsid) != null) {
			tmptransactionslist.remove(tsid);
		}
	}
	
	
	/**增加交易人员信息
	 * 
	 * @param tsid
	 * @param longtab
	 */
	public void addtraders(long tsid ,long[] longtab) {
		tmptransactionslist.put(tsid, longtab);
	}
	
	
	
	/**
	 * 获得当前双方交易数据，交易检测
	 * @param player
	 * @return
	 */
	public Transactions gettransactions(Player player){
		Transactions transactions= player.getTransactionsinfo();
		if (transactions != null) {
			if(transactions.getAid() > 0 && transactions.getBid() > 0) {
				Player aplayer = ManagerPool.playerManager.getOnLinePlayer(transactions.getAid());
				Player bplayer = ManagerPool.playerManager.getOnLinePlayer(transactions.getBid());
				if(aplayer != null && bplayer != null ) {	
					return transactions;
				}else {
					removeTransactions(player);//这里把自己的所有道具锁定去掉
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方已经离线，交易取消。"));
					ResTransactionsCanceledMessage canmsg = new ResTransactionsCanceledMessage();
					MessageUtil.tell_player_message(player, canmsg);
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 前端主动打断交易 (包括下线)
	 * @param player
	 */
	public void stTransactionsCanceled(Player player) {
		Player xplayer = getOtherplayer(player);
		ResTransactionsCanceledMessage canmsg = new ResTransactionsCanceledMessage();
		Transactions transactions= player.getTransactionsinfo();
		if (transactions != null) {
			MessageUtil.tell_player_message(player, canmsg);
			removeTransactions(player);
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("交易被取消。"));
		}
		player.setState(PlayerState.NOTRANSACTION);//设置为非交易状态
		if (xplayer !=null) {
			Transactions xtransactions= xplayer.getTransactionsinfo();
			if (xtransactions != null) {
				removeTransactions(xplayer);
				MessageUtil.tell_player_message(xplayer, canmsg);
				MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("交易被取消。"));
				xplayer.setState(PlayerState.NOTRANSACTION);	//设置为非交易状态
			}
		}
	}
	
	
	
	
	/**
	 * 交易打断后清除交易数据
	 * @param player
	 */
	public void removeTransactions(Player player) {
		Transactions transactions= player.getTransactionsinfo();
		if (transactions != null) {
			TsInfo tsinfo = transactions.getTsInfo(player.getId());
			if (tsinfo != null) {
				HashMap<Long, Integer> items = tsinfo.getItems();
				Iterator<Entry<Long, Integer>> it = items.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, Integer> mEntry = it.next();
					Item item  = ManagerPool.backpackManager.getItemById(player,mEntry.getKey());
					if (item != null) {
						item.setStatus((byte) 0);	//去掉道具锁定
					}
				}
				tsinfo.setGold(0);
				tsinfo.setYuanbao(0);
				tsinfo.setStatus((byte) 0);
				tsinfo.setItems(null);
			}
			player.setTransactionsinfo(null);
		}
	}
	
	
//	武器	1
//	衣服	2
//	头盔	3
//	项链	4
//	护腕	5
//	护腿	6
//	戒指	7
//	绶佩	8
//	腰带	9
//	鞋子	10
//	护符1	11
//	护符2	12
//	护符3	13
	/**更新外观组件
	 * 
	 */
	
	
	public PlayerAppearanceInfo setPlayerAppearanceInfo(Player player){
		PlayerAppearanceInfo appearanceInfo = new PlayerAppearanceInfo();
		Equip[] equips = player.getEquips();
		appearanceInfo.setSex(player.getSex());
		if (equips != null) {
			if (equips[0] != null) {
				appearanceInfo.setWeaponmodid(equips[0].getItemModelId());		//武器	
				appearanceInfo.setWeaponStreng((byte)equips[0].getGradeNum());
			}
			if (equips[1] != null) {
				appearanceInfo.setClothingmodid(equips[1].getItemModelId());	//衣服
			}
		}
		//坐骑阶数
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse !=null && horse.getStatus() == 1) {
			appearanceInfo.setHorsemodid(horse.getCurlayer());	//坐骑
		}
		
		//骑战兵器
		if (ManagerPool.horseWeaponManager.isWearHorseWeapon(player)){
			HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
			appearanceInfo.setHorseweaponmodid(weapon.getCurLayer());
		}
		
		//暗器
		if (ManagerPool.hiddenWeaponManager.isWearHiddenWeapon(player)) {
			HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
			appearanceInfo.setHiddenweaponmodid(weapon.getLayer());
		}
		
		//头像
		appearanceInfo.setAvatarid(player.getAvatarid());
		appearanceInfo.setArrowid(player.getArrowData().getArrowlv());
		return appearanceInfo ;
	}
	
	
	
	/**
	 * 新发起交易
	 * @param aplayer
	 * @param bplayerid
	 */

	public void stTransactionsLaunch(Player aplayer ,long bplayerid) {
		if (aplayer.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用交易功能。"));
			return;
		}

		if (aplayer.getId() == bplayerid) {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您不能和自己交易。"));
			return;
		}
		
		Player bplayer = ManagerPool.playerManager.getOnLinePlayer(bplayerid);
		if(bplayer == null) {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方和您距离太远，或者已离线，交易无法建立。"));
			return;
		}
		if(PlayerState.DIE.compare(aplayer.getState())) {
			MessageUtil.notify_player(aplayer, Notifys.ERROR, ResManager.getInstance().getString("处于死亡状态，不能进行交易。"));
			return;
		}

		
		if (bplayer.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方尚未完成注册，暂时不能使用交易功能"));
			return;
		}

		if (ManagerPool.setupMenuManager.isSetMenu(bplayer ,SetupMenuType.TRANSACTION)) {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，『{1}』打开了自动拒绝交易请求设置，交易建立失败"),bplayer.getName());
			return;
		}
		if(checkmaparea(aplayer,bplayer) == false){
			return;
		}
		Transactions ats = aplayer.getTransactionsinfo();
		if (ats != null) {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，发起新交易请求前，请先结束当前已建立的交易。"));
			return;
		}
		Transactions ts = bplayer.getTransactionsinfo();	
		if (ts == null || (ts != null && ts.getAid() == 0 && ts.getBid() == 0  )) {
			long tsid = Config.getId();		//新产生一个交易ID
			long time = System.currentTimeMillis()/1000;
			long[] tab = {aplayer.getId(),bplayerid,time};//Aid,Bid,时间秒
			addtraders(tsid,tab);
			ResTransactionsLaunchMessage launchmsg = new ResTransactionsLaunchMessage();
			RoleModeInfo rolemode = new RoleModeInfo();	//角色造型
			rolemode.setPlayerid(aplayer.getId());
			rolemode.setAppearanceInfo(setPlayerAppearanceInfo(aplayer));
			rolemode.setPlayerlv(aplayer.getLevel());
			launchmsg.setRolemodeinfo(rolemode);
			launchmsg.setTransid(tsid);		
			launchmsg.setLaunchtime((int)time);
			MessageUtil.tell_player_message(bplayer, launchmsg);
			MessageUtil.notify_player(aplayer,Notifys.NORMAL,ResManager.getInstance().getString("您的交易请求已发送，请等待对方回应。"));
		}else {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方正处于另一庄交易中，请稍后再试。"));
		}

	}
	
	
	
	
	/**
	 * B玩家同意交易
	 * 
	 */
	public void stTransactionsAccept(Player bplayer,long tsid) {
		long aid = gettradersAid(tsid,bplayer.getId());
		removetraders(tsid);
		if (aid == 0) {
			MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易超时了，请重新发起交易。"));
			return;
		}
		if (bplayer.checkTempPlayer()) {	//未注册玩家不能使用
			MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您尚未完成注册，暂时不能使用交易功能。"));
			return;
		}
		Player aplayer = ManagerPool.playerManager.getOnLinePlayer(aid);
		if (aplayer != null) {
			Transactions ats = aplayer.getTransactionsinfo();
			if ((ats != null ) && (ats.getAid() > 0 || ats.getBid() > 0 )) {
				MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方正处于另一庄交易中，请稍后再试。"));
				return;
			}
			Transactions bts = bplayer.getTransactionsinfo();
			if ((bts != null ) && (bts.getAid() > 0 || bts.getBid() > 0 )) {
				MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，在响应新交易请求前，请先结束当前已建立的交易。"));
				return;
			}
			
			if(checkmaparea(aplayer,bplayer) == false){
				MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，『{1}』和您距离太远，不能进行交易。"),aplayer.getName());
				return;
			}
			
			long time = System.currentTimeMillis()/1000;
			Transactions transactions = new Transactions();   //交易具体信息
			transactions.setAid(aid);
			transactions.setBid(bplayer.getId());
			transactions.setStarttime(time);
			//TsInfo aTsInfo = transactions.getTsInfo(aid);
			//TsInfo bTsInfo = transactions.getTsInfo(bplayer.getId());
			aplayer.setTransactionsinfo(transactions);
			bplayer.setTransactionsinfo(transactions);
			
			ResTransactionsStartMessage msg = new ResTransactionsStartMessage();
			RoleModeInfo arolemode = new RoleModeInfo();	//A角色造型
			RoleModeInfo brolemode = new RoleModeInfo();	//B角色造型
			
			arolemode.setPlayername(aplayer.getName());
			arolemode.setPlayerid(aplayer.getId());
			arolemode.setPlayerlv(aplayer.getLevel());
			arolemode.setAppearanceInfo(setPlayerAppearanceInfo(aplayer));
			
			brolemode.setPlayername(bplayer.getName());
			brolemode.setPlayerid(bplayer.getId());
			brolemode.setPlayerlv(bplayer.getLevel());
			brolemode.setAppearanceInfo(setPlayerAppearanceInfo(bplayer));
			
			msg.setArolemodeinfo(arolemode);
			msg.setBrolemodeinfo(brolemode);
			MessageUtil.tell_player_message(aplayer, msg);
			MessageUtil.tell_player_message(bplayer, msg);
			
		}else {
			MessageUtil.notify_player(bplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方已经离线，交易无法建立。"));
		}

	}
	
	
	
	/**
	 * B玩家拒绝交易
	 * 
	 * @param bplayer
	 * @param tsid
	 */
	public void stTransactionsRefuse(Player bplayer ,long tsid) {
		long aid = gettradersAid(tsid,bplayer.getId());
		removetraders(tsid);
		if (aid == 0) {
			return;
		}
		
		Player aplayer = ManagerPool.playerManager.getOnLinePlayer(aid);
		if (aplayer != null) {
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，{1}拒绝了您的交易请求。"),bplayer.getName());
		}
	}
	
	
	
	
	/**
	 * 玩家在交易框放入物品
	 * @param player
	 * @param itemposition
	 * @param itemid
	 */
	public void stTransactionsIntoItem(Player player,short itemposition ,long itemid){
		Transactions transactions= gettransactions(player);
		if (transactions != null) {
			Player xplayer = getOtherplayer(player);
			TsInfo tsinfo = transactions.getTsInfo(player.getId());
			if (tsinfo.getStatus() > 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，您已经锁定交易，不能更改交易内容。"));
				return;
			}
			
			
			HashMap<Long, Integer> items = tsinfo.getItems();
			if (items.size() >= 6) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易栏已经放满道具，请先完成交易。"));
				return;
			}
			
			Item item  = ManagerPool.backpackManager.getItemById(player,itemid);
			if (item == null) {
				//道具异常
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
				ResTransactionsErrorMessage emsg = new ResTransactionsErrorMessage();
				emsg.setErrorid(1);
				MessageUtil.tell_player_message(player, emsg);
				return ;
			}
			
			
			if(item.isBind() || item.isLost()) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，绑定道具或过期道具不能用于交易。"));
				ResTransactionsErrorMessage emsg = new ResTransactionsErrorMessage();
				emsg.setErrorid(1);
				MessageUtil.tell_player_message(player, emsg);
				return;
			}
			
			boolean isinto = true;
			Iterator<Entry<Long, Integer>> it = items.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Integer> mEntry = it.next();
				if(itemid ==mEntry.getKey()){
					isinto = false;
					break;
				}
			}

			if(isinto) {
				items.put(itemid, item.getNum());
				item.setStatus((byte) 1);	//设置锁定
				ResTransactionsIntoItemMessage msg = new ResTransactionsIntoItemMessage();
				msg.setItemposition(itemposition);
				msg.setPlayerid(player.getId());
				msg.setIteminfo(item.buildItemInfo());
				MessageUtil.tell_player_message(xplayer, msg);
				MessageUtil.tell_player_message(player, msg);
				stChangeState(player);//判断取消对方锁定状态
			}
	
		}

	}

	
	
	
	
	
	/**
	 * 从交易框移除道具
	 * @param player
	 * @param itemid
	 */
	public void stTransactionsRemoveItem(Player player,long itemid){
		Transactions transactions= gettransactions(player);
		if (transactions != null) {
			Player xplayer = getOtherplayer(player);
			
			TsInfo tsinfo = transactions.getTsInfo(player.getId());
			if (tsinfo.getStatus() > 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，您已经锁定交易，不能更改交易内容。"));
				return;
			}
			HashMap<Long, Integer> items = tsinfo.getItems();
			
			Item item  = ManagerPool.backpackManager.getItemById(player,itemid);
			if (item == null) {
				//道具异常
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
				ResTransactionsErrorMessage emsg = new ResTransactionsErrorMessage();
				emsg.setErrorid(1);
				MessageUtil.tell_player_message(player, emsg);
				return ;
			}
			boolean isremove = false;
			Iterator<Entry<Long, Integer>> it = items.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Integer> mEntry = it.next();
				if (itemid == mEntry.getKey()) {
					isremove =true;
				}
			}
			
			
			if(isremove) {
				if (items.remove(itemid)!= null) {
					item.setStatus((byte) 0);	//去掉锁定
					ResTransactionsRemoveItemMessage msg = new ResTransactionsRemoveItemMessage();
					msg.setPlayerid(player.getId());
					msg.setItemid(itemid);
					MessageUtil.tell_player_message(xplayer, msg);
					MessageUtil.tell_player_message(player, msg);
					stChangeState(player);//判断取消对方锁定状态
				}
			}
		}
	}
	
	
	
	
	/**
	 * 改变交易的金币数量
	 */
	public void  stTransactionsChangeGold(Player player ,int gold){
		Transactions transactions= gettransactions(player);
		if (transactions != null) {
			TsInfo tsinfo = transactions.getTsInfo(player.getId());

			
			if (player.getMoney() < gold || gold < 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您输入的铜币数量少于携带数量。"));
				ResTransactionsErrorMessage emsg = new ResTransactionsErrorMessage();
				emsg.setErrorid(2);
				MessageUtil.tell_player_message(player, emsg);
				return;
			}
			
			if (tsinfo.getGold() != gold ) {
				if (tsinfo.getStatus() > 0) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，您已经锁定交易，不能更改交易内容。"));
					return;
				}
				Player xplayer = getOtherplayer(player);
				tsinfo.setGold(gold);
				ResTransactionsChangeGoldMessage msg = new ResTransactionsChangeGoldMessage();
				msg.setPlayerid(player.getId());
				msg.setGold(gold);
				MessageUtil.tell_player_message(player, msg);
				MessageUtil.tell_player_message(xplayer, msg);
				stChangeState(player);//判断取消对方锁定状态
			}
		}
	}
	
	
	
	/**
	 * 改变交易的元宝数量
	 */
	public void  stTransactionsChangeYuanbao(Player player ,int yuanbao){
		Transactions transactions= gettransactions(player);
		if (transactions != null) {
			TsInfo tsinfo = transactions.getTsInfo(player.getId());
			Gold gold = player.getGold();
			int playeryuanbao = 0;
			if (gold != null) {
				playeryuanbao = gold.getGold();
			}
			
			if (playeryuanbao < yuanbao || yuanbao < 0) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您输入的元宝数量少于携带数量。"));
				ResTransactionsErrorMessage emsg = new ResTransactionsErrorMessage();
				emsg.setErrorid(3);
				MessageUtil.tell_player_message(player, emsg);
				return;
			}
			
			if (tsinfo.getYuanbao() != yuanbao ) {
				if (tsinfo.getStatus() > 0) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对不起，您已经锁定交易，不能更改交易内容。"));
					return;
				}
				Player xplayer = getOtherplayer(player);
				tsinfo.setYuanbao(yuanbao);
				ResTransactionsChangeYuanbaoMessage msg = new ResTransactionsChangeYuanbaoMessage();
				msg.setPlayerid(player.getId());
				msg.setYuanbao(yuanbao);
				MessageUtil.tell_player_message(xplayer, msg);
				MessageUtil.tell_player_message(player, msg);
				stChangeState(player);//判断取消对方锁定状态
			}
		}
	}
	
	
	
	/**
	 * 判断取消对方锁定状态
	 * @param player
	 * @param aplayer
	 * @param bplayer
	 * @param transactions
	 */
	public void stChangeState(Player player){
		Transactions transactions= player.getTransactionsinfo();
		if (transactions != null) {
			if (transactions.getaInfo().getStatus() >= 1) {
				stTransactionsSetState(player,(byte) 0);
			}
			TsInfo xtsinfo = transactions.getOtherTsInfo(player.getId());	//对方的交易数据
			Player xplayer = getOtherplayer(player);
			if (xtsinfo != null && xtsinfo.getStatus() >= 1) {
				stTransactionsSetState(xplayer,(byte) 0);
			}
		}
	}
	
	
	
	
	/**
	 * 玩家改变交易状态
	 * @param player
	 * @param state 0正常交易中，1锁定交易，2确定交易
	 */
	public void stTransactionsSetState(Player aplayer,byte state) {
		Transactions transactions= gettransactions(aplayer);
		if (transactions != null) {
			TsInfo atsinfo = transactions.getTsInfo(aplayer.getId());
			if (atsinfo.getGold() < 0) {
				MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("铜币不能小于0。"));
				return;
			}
			if (atsinfo.getYuanbao() < 0) {
				MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("元宝数量不能小于0。"));
				return;
			}
			
			atsinfo.setStatus(state);
			
			Player xplayer  = getOtherplayer(aplayer);	//获得交易另一方人物数据	
			if (xplayer == null) {
				stTransactionsCanceled(aplayer);
				return;
			}
			
			if(checkmaparea(aplayer,xplayer) == false){
				MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("对方不在交易范围内。"));
				stTransactionsCanceled(aplayer);
				return;
			}

			TsInfo xtsinfo = transactions.getTsInfo(xplayer.getId());	//根据玩家ID得到对方交易信息 
			if (atsinfo.getStatus() == 2 && xtsinfo.getStatus() == 2) {
				Gold aybdata = aplayer.getGold();
				int aplayeryuanbao = 0;
				int arecharge = 0;	//充值记录
				if (aybdata != null) {
					aplayeryuanbao = aybdata.getGold();
					arecharge = aybdata.getTotalGold();
				}
				
				Gold xybdata = xplayer.getGold();
				int xplayeryuanbao = 0;
				int xrecharge = 0;
				if (xybdata != null) {
					xplayeryuanbao = xybdata.getGold();
					xrecharge = xybdata.getTotalGold();
				}

				boolean ists = false;
				//--------双方确定-交换物品开始-----------
				long logid = Config.getId();		//数据记录用唯一ID
				xplayer.setState(PlayerState.TRANSACTION);	//设置为交易状态
				aplayer.setState(PlayerState.TRANSACTION);
				
				int	agold = (int) atsinfo.getGold();
				int ayuanbao = (int) atsinfo.getYuanbao();
				HashMap<Long, Integer> aitems = atsinfo.getItems();
				
				int	xgold =  xtsinfo.getGold();
				int xyuanbao =  xtsinfo.getYuanbao();
				HashMap<Long, Integer> xitems = xtsinfo.getItems();
				StringBuffer astr = new StringBuffer("");
				StringBuffer xstr = new StringBuffer("");
				
				if (agold > 0 && aplayer.getMoney() < agold) {
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的携带的铜币数少于交易的数量，交易失败。"));
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的铜币数少于交易的数量，交易失败。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				if (ayuanbao > 0 && aplayeryuanbao < ayuanbao) {
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的携带的元宝数少于交易的数量，交易失败。"));
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的元宝数少于交易的数量，交易失败。"));
					stTransactionsCanceled(aplayer);
					return;
				}

				if (xgold > 0 && xplayer.getMoney() < xgold) {
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的携带的铜币数少于交易的数量，交易失败。"));
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的铜币数少于交易的数量，交易失败。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				if (xyuanbao > 0 && xplayeryuanbao < xyuanbao) {
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的携带的元宝数少于交易的数量，交易失败。"));
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的元宝数少于交易的数量，交易失败。"));
					stTransactionsCanceled(aplayer);
					return;
				}

				if ((agold > 0 && xplayer.getMoney() + agold > Global.BAG_MAX_COPPER ) || (xplayer.getMoney() + agold < 0)) {	
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易的铜币数超过了对方背包的负荷，本次交易失败了。"));
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的铜币数过多，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				
				if ((ayuanbao > 0 && xplayeryuanbao + ayuanbao > Global.BAG_MAX_GOLD) || (xplayeryuanbao + ayuanbao < 0)){
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易的元宝数超过了对方背包的负荷，本次交易失败了。"));
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的元宝数过多，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				
				if ((xgold > 0 && aplayer.getMoney() + xgold > Global.BAG_MAX_COPPER )  || (aplayer.getMoney() + xgold < 0)) {
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易的铜币数超过了对方背包的负荷，本次交易失败了。"));
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的铜币数过多，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				
				if ((xyuanbao > 0 && aplayeryuanbao + xyuanbao > Global.BAG_MAX_GOLD) || ( aplayeryuanbao + xyuanbao < 0)) {
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，交易的元宝数超过了对方背包的负荷，本次交易失败了。"));
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包携带的元宝数过多，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				
				 int agridnum  = ManagerPool.backpackManager.getEmptyGridNum(aplayer);
				 if (agridnum < xitems.size()) {
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包过满，放不下过多物品，本次交易失败了。"));
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的背包过满，放不下过多物品，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				 
				 int xgridnum  = ManagerPool.backpackManager.getEmptyGridNum(xplayer);
				 if (xgridnum < aitems.size()) {
					MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的背包过满，放不下过多物品，本次交易失败了。"));
					MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，对方的背包过满，放不下过多物品，本次交易失败了。"));
					stTransactionsCanceled(aplayer);
					return;
				}
				 
				 //检测道具是否正常
				 if (aitems.size() > 0) {
					 Iterator<Entry<Long, Integer>> ait = aitems.entrySet().iterator();
					while (ait.hasNext()) {
						Entry<Long, Integer> amEntry = ait.next();
						Item item  = ManagerPool.backpackManager.getItemById(aplayer,amEntry.getKey());
						if (item == null || (item != null && item.getNum() != amEntry.getValue())) {
							MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
							MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
							stTransactionsCanceled(aplayer);
							return;
						}
					}	
				 }
				 
				 
				 if (xitems.size() > 0) {
					 Iterator<Entry<Long, Integer>> xit = xitems.entrySet().iterator();
					 while (xit.hasNext()) {
						 Entry<Long, Integer> xmEntry = xit.next();
						 Item item  = ManagerPool.backpackManager.getItemById(xplayer,xmEntry.getKey());
						if (item == null || (item != null && item.getNum() != xmEntry.getValue())) {
							MessageUtil.notify_player(xplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
							MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("对不起，交易的货品状态异常，交易中断，请重新尝试交易。"));
							stTransactionsCanceled(aplayer);
							return;
						}
					 }
				 } 
				 
				 
				//先减后加(金币和元宝)
				 
				 
				 if (agold > 0) {
					 if (ManagerPool.backpackManager.changeMoney(aplayer,-agold,Reasons.JIAOYIGOLD,logid)) {
						 ManagerPool.backpackManager.changeMoney(xplayer,agold,Reasons.JIAOYIGOLD,logid); 
						 sttransactionslog(aplayer,xplayer,-1,agold,logid);
						 ists = true;
						 xstr.append( ResManager.getInstance().getString(" 铜币")+agold);
					}
				 }
				 
				 if (xgold > 0) {
					 if (ManagerPool.backpackManager.changeMoney(xplayer,-xgold,Reasons.JIAOYIGOLD,logid)) {
						 ManagerPool.backpackManager.changeMoney(aplayer,xgold,Reasons.JIAOYIGOLD,logid); 
						 sttransactionslog(xplayer,aplayer,-1,xgold,logid);
						 ists = true;
						 astr.append( ResManager.getInstance().getString(" 铜币")+xgold);
					}	
				 }

				 if (ayuanbao > 0) {
					 if (ManagerPool.backpackManager.changeGold(aplayer,-ayuanbao,Reasons.JIAOYIYB,logid)) {
						 if (xplayeryuanbao > 0 && xrecharge > 0 ) {
							 ManagerPool.backpackManager.addGold(xplayer,ayuanbao,Reasons.JIAOYIYB,logid); 
						 }else {
							ManagerPool.backpackManager.changeTmpGold(xplayer,ayuanbao);
							saveTmpyuanbaolog(xplayer,aplayer.getId(),aplayer.getName(),(byte)1,ayuanbao);//可领取元宝日志记录
							
						 }
						 sttransactionslog(aplayer,xplayer,-2,ayuanbao,logid);	 
						 ists = true;
						 xstr.append( ResManager.getInstance().getString(" 元宝")+ayuanbao);
					}
				 }
				 
				 if (xyuanbao > 0) {
					 if (ManagerPool.backpackManager.changeGold(xplayer,-xyuanbao,Reasons.JIAOYIYB,logid)) {
						 if (aplayeryuanbao > 0 && arecharge > 0) {
							 ManagerPool.backpackManager.addGold(aplayer,xyuanbao,Reasons.JIAOYIYB,logid); 
						}else {
							ManagerPool.backpackManager.changeTmpGold(aplayer,xyuanbao);
							saveTmpyuanbaolog(aplayer,xplayer.getId(),xplayer.getName(),(byte)1,xyuanbao);//可领取元宝日志记录
							
						}	
						 sttransactionslog(xplayer,aplayer,-2,xyuanbao,logid);	
						 ists = true;
						 astr.append( ResManager.getInstance().getString(" 元宝")+xyuanbao);
					}
				 }
				 
				//先减后加(道具)
				List<Item> atmpitems = new ArrayList<Item>();
				List<Item> xtmpitems = new ArrayList<Item>();
				
				 if (aitems.size() > 0) {
					 ists = true;
					 Iterator<Entry<Long, Integer>> ait = aitems.entrySet().iterator();
					 while (ait.hasNext()) {
						Entry<Long, Integer> amEntry = ait.next();
						Item item  = ManagerPool.backpackManager.getItemById(aplayer,amEntry.getKey());
						if(ManagerPool.backpackManager.removeItem(aplayer,amEntry.getKey(),Reasons.JIAOYIITEM,logid)) {
							atmpitems.add(item);
						}else {
							for (Item tmpitem : atmpitems) {		//删道具失败，返还
								tmpitem.setStatus((byte) 0);	 //解锁装备
								ManagerPool.backpackManager.addItem(aplayer,tmpitem,Reasons.JIAOYISHIBAI,logid);
							}
							atmpitems = null;
							xtmpitems = null;
							stTransactionsCanceled(aplayer);  //打断交易
							return;
						}
					 }
				 }

				 if (xitems.size() > 0) {
					 ists = true;
					 Iterator<Entry<Long, Integer>> xit = xitems.entrySet().iterator();
					 while (xit.hasNext()) {
						Entry<Long, Integer> xmEntry = xit.next();
						Item item  = ManagerPool.backpackManager.getItemById(xplayer,xmEntry.getKey());
						if(ManagerPool.backpackManager.removeItem(xplayer,xmEntry.getKey(),Reasons.JIAOYIITEM,logid)) {
							xtmpitems.add(item);
						}else {
							for (Item tmpitem : atmpitems) {		//删道具失败，返还
								tmpitem.setStatus((byte) 0);	 //解锁装备
								ManagerPool.backpackManager.addItem(aplayer,tmpitem,Reasons.JIAOYISHIBAI,logid);
							}
							for (Item xtmpitem : xtmpitems) {		//删道具失败，返还
								xtmpitem.setStatus((byte) 0);	 //解锁装备
								ManagerPool.backpackManager.addItem(xplayer,xtmpitem,Reasons.JIAOYISHIBAI,logid);
							}
							atmpitems = null;
							xtmpitems = null;
							stTransactionsCanceled(aplayer); //打断交易
							return;
						}
					 }
				 }
				 
				 
				//--------------------加道具--------------------------
				for (Item tmpitem : atmpitems) {
					tmpitem.setStatus((byte) 0);	 //解锁装备
					tmpitem.setGridId(0);
					xstr.append(" "+tmpitem.acqItemModel().getQ_name());
					xstr.append(tmpitem.getNum()>1? "("+tmpitem.getNum()+")" : "");
					sttransactionslog(aplayer,xplayer,tmpitem,logid);	
					ManagerPool.backpackManager.addItem(xplayer,tmpitem,Reasons.JIAOYIITEMADD,logid);
					ManagerPool.equipstrengManager.clearStrengthenLog(tmpitem);
				}
				
				for (Item xtmpitem : xtmpitems) {
					xtmpitem.setStatus((byte) 0);	 //解锁装备
					xtmpitem.setGridId(0);
					astr.append(" "+xtmpitem.acqItemModel().getQ_name());
					astr.append(xtmpitem.getNum()>1?"("+xtmpitem.getNum()+")":"");
					sttransactionslog(xplayer,aplayer,xtmpitem,logid);	
					ManagerPool.backpackManager.addItem(aplayer,xtmpitem,Reasons.JIAOYIITEMADD,logid);
					ManagerPool.equipstrengManager.clearStrengthenLog(xtmpitem);
				}

				xplayer.setState(PlayerState.NOTRANSACTION);
				aplayer.setState(PlayerState.NOTRANSACTION);
				xplayer.setTransactionsinfo(null);
				aplayer.setTransactionsinfo(null);
				atmpitems = null;
				xtmpitems = null;
				
				if (astr.toString().length() > 0) {
					MessageUtil.notify_player(aplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您与『{1}』本次交易成功。您获得了:{2}"),xplayer.getName(),astr.toString());
				}else {
					MessageUtil.notify_player(aplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您与『{1}』本次交易成功。"),xplayer.getName());
				}
				
				if (xstr.toString().length() > 0) {
					MessageUtil.notify_player(xplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您与『{1}』本次交易成功。您获得了:{2}"),aplayer.getName(),xstr.toString());
				}else {
					MessageUtil.notify_player(xplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您与『{1}』本次交易成功。"),aplayer.getName());
				}
							
				ResTransactionsSuccessMessage asuccessmsg = new ResTransactionsSuccessMessage();
				ResTransactionsSuccessMessage xsuccessmsg = new ResTransactionsSuccessMessage();
				//加交易经验
				if (ists) {
					int aexp= aplayer.getLevel()*aplayer.getLevel();
					int xexp= xplayer.getLevel()*xplayer.getLevel();
					if (ManagerPool.cooldownManager.isCooldowning(aplayer,CooldownTypes.TS_EXP_CD,null) == false && aplayer.getNonage() != 3) {
						ManagerPool.cooldownManager.addCooldown(aplayer,CooldownTypes.TS_EXP_CD,null,Global.TS_EXP_TIME);
						ManagerPool.playerManager.addExp(aplayer, aexp,AttributeChangeReason.TRABS);
						if (aplayer.getNonage()==2 ) {
							aexp = aexp/2;
						}
						asuccessmsg.setTsexp(aexp);
						MessageUtil.notify_player(aplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您成功交易，增加人物经验：{1}"),String.valueOf(aexp));
					}
					
					if (ManagerPool.cooldownManager.isCooldowning(xplayer,CooldownTypes.TS_EXP_CD,null) == false && xplayer.getNonage() != 3) {
						ManagerPool.cooldownManager.addCooldown(xplayer,CooldownTypes.TS_EXP_CD,null,Global.TS_EXP_TIME);
						ManagerPool.playerManager.addExp(xplayer,xexp,AttributeChangeReason.TRABS);
						if (xplayer.getNonage()==2 ) {
							xexp = xexp/2;
						}
						xsuccessmsg.setTsexp(xexp);
						MessageUtil.notify_player(xplayer,Notifys.SUCCESS,ResManager.getInstance().getString("恭喜您成功交易，增加人物经验：{1}"),String.valueOf(xexp));
					}
				}

				MessageUtil.tell_player_message(aplayer, asuccessmsg);
				MessageUtil.tell_player_message(xplayer, xsuccessmsg);
				//添加最近联系人
				ManagerPool.friendManager.RelationInnerAdd(aplayer,(byte)3,xplayer.getId(),null);
				ManagerPool.friendManager.RelationInnerAdd(xplayer,(byte)3,aplayer.getId(),null);

			}else{
				ResTransactionsSetStateMessage msg = new ResTransactionsSetStateMessage();
				msg.setState(state);
				msg.setPlayerid(aplayer.getId());
				MessageUtil.tell_player_message(aplayer, msg);
				MessageUtil.tell_player_message(xplayer, msg);
			}
		}
	}
	
	
	/**
	 * 获得交易另一方人物数据
	 * @param player
	 * @param transactions
	 * @return
	 */
	public Player getOtherplayer(Player player ){
		Transactions transactions= player.getTransactionsinfo();
		Player xplayer = null;
		if (transactions != null) {
			if (player.getId() == transactions.getAid()) {
				xplayer = ManagerPool.playerManager.getOnLinePlayer(transactions.getBid());
			}else if(player.getId() == transactions.getBid()) {
				xplayer = ManagerPool.playerManager.getOnLinePlayer(transactions.getAid());
			}
		}
		return xplayer;
	}
	
	

	
	/**
	 * 设置自动拒绝交易状态
	 */
	public void stAutorefusaldeal(Player player,byte state ){
		if (player != null) {
			player.setAutorefusaldeal(state);
		}
	}
	



	

	
	//----------------数据库记录--------------------
	/**
	 * 交易日志 (道具)
	 */
	public void sttransactionslog(Player outplayer,Player intoplayer ,Item item,long logid) {
		 TransactionsLog log = new TransactionsLog();
		 log.setIntoId(intoplayer.getId());
		 log.setOutId(outplayer.getId());
		 log.setTradingId(logid);
		 
		 log.setGoodsName(item.acqItemModel().getQ_name());
		 log.setGoodsNum(item.getNum());
		 log.setGoodsModid(item.getItemModelId());
		 log.setGoodsOnlyid(item.getId());
		 log.setGoodsInfo(JSONserializable.toString(item));
		 
		 LogService.getInstance().execute(log);
	}
	
	/**
	 * 交易日志（铜币 和 元宝）
	 */
	public void sttransactionslog(Player outplayer,Player intoplayer ,int type , int num, long logid) {
		 TransactionsLog log = new TransactionsLog();
		 log.setIntoId(intoplayer.getId());
		 log.setOutId(outplayer.getId());
		 log.setTradingId(logid);

		 String typename = ResManager.getInstance().getString("未知");
		 if (type == -1) {
			 typename = ResManager.getInstance().getString("铜币");
		}else if (type == -2) {
			typename = ResManager.getInstance().getString("元宝");
		}
		 log.setGoodsModid(type);
		 log.setGoodsName(typename);
		 log.setGoodsNum(num);
		 log.setGoodsOnlyid(0);
		 log.setGoodsInfo(typename);
		 LogService.getInstance().execute(log);
	}
	
	
	
	/**判断是否在交易范围
	 * 
	 * @param aplayer
	 * @param bplayer
	 * @return
	 */
	public boolean checkmaparea(Player aplayer,Player bplayer) {
		if (aplayer != null && bplayer != null) {
			if (aplayer.getCountry() != bplayer.getCountry()) {
				MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，不同国家的玩家无法进行交易。"));
				return false;
			}
				
			if (aplayer.getLine() == bplayer.getLine()) {
				if (aplayer.getMap() == bplayer.getMap()) {
					double dis = MapUtils.countDistance(aplayer.getPosition(), bplayer.getPosition());
					if (dis < TRANSACTION_AREA) { 
						return true;
					}
				}
			}
			MessageUtil.notify_player(aplayer,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，『{1}』和您距离太远，不能进行交易。"),bplayer.getName());
		}
		return false;
	}

	
	
	/**
	 * 设置临时元宝记录并发送消息(最多50条)
	 * @param player
	 * @param rid
	 * @param name
	 * @param type    1交易，2摆摊货物卖出，3摆摊购买元宝
	 * @param yuanbao
	 */

	
	public void saveTmpyuanbaolog(Player player ,long rid, String name, byte type ,int yuanbao ){
//		ResCanreceiveYuanbaoMessage tymsg = new ResCanreceiveYuanbaoMessage();
//		tymsg.setCanryuanbao(player.getCanreceiveyuanbao());
//		MessageUtil.tell_player_message(player, tymsg);
		TempYuanbaoLogData tybdata = new TempYuanbaoLogData();
		tybdata.stTmpYuanbaoLog(name,rid, type, new ItemLogData(), 0, 0, yuanbao);
		List<TempYuanbaoLogData> pyblog = player.getTempyuanbaoLogdata();
		if (pyblog.size() >= 50) {
			pyblog.remove(49);
		}
		pyblog.add(0,tybdata);	//加入临时元宝日志
	}
	
	/**设置临时元宝记录并发送消息(最多50条)
	 * 
	 * @param player
	 * @param rid
	 * @param name
	 * @param type  1交易，2摆摊货物卖出，3摆摊购买元宝
	 * @param itemstr
	 * @param num
	 * @param gold
	 * @param yuanbao
	 */
	public void saveTmpyuanbaolog(Player player ,long rid, String name, byte type , String itemstr, int num ,int gold ,int yuanbao ){
//		ResCanreceiveYuanbaoMessage tymsg = new ResCanreceiveYuanbaoMessage();
//		tymsg.setCanryuanbao(player.getCanreceiveyuanbao());
//		MessageUtil.tell_player_message(player, tymsg);

		TempYuanbaoLogData tybdata = new TempYuanbaoLogData();
		Item item = (Item) JSONserializable.toObject(itemstr,Item.class);
		tybdata.stTmpYuanbaoLog(name,rid, type, item.buildItemLogData(), item.getNum(), gold, yuanbao);
		List<TempYuanbaoLogData> pyblog = player.getTempyuanbaoLogdata();
		if (pyblog.size() >= 50) {
			pyblog.remove(49);
		}
		pyblog.add(0,tybdata);	//加入临时元宝日志
	}
	
	
	
	/**
	 * 打开临时元宝面板（发送日志）
	 * @param player
	 */
	public void stReqOpenTmpYuanbaoPanelMessage(Player player) {
		String exchange = ManagerPool.dataManager.q_globalContainer.getMap().get(26).getQ_string_value();
		String web = ManagerPool.dataManager.q_globalContainer.getMap().get(27).getQ_string_value();

		ResTmpYuanbaoLogMessage msg = new ResTmpYuanbaoLogMessage();
		msg.setCanryuanbao(player.getCanreceiveyuanbao());
		msg.setExchange(exchange);
		msg.setWeb(web);
		List<TmpYuanbaoLogInfo> tmptab = msg.getTpmyuanbaolonginfo();
		List<TempYuanbaoLogData> tab = player.getTempyuanbaoLogdata();
		for (TempYuanbaoLogData tmpdata : tab) {
			TmpYuanbaoLogInfo log = new TmpYuanbaoLogInfo();
			log.setGoldnum(tmpdata.getGoldnum());
			log.setIteminfo(tmpdata.getItemlogdata().toiteminof());
			log.setNum(tmpdata.getNum());
			log.setPlayerid(tmpdata.getPlayerid());
			log.setType(tmpdata.getType());
			log.setTime((int)tmpdata.getTime());
			log.setPlayername(tmpdata.getPlayername());
			log.setYuanbaonum(tmpdata.getYuanbaonum());
			tmptab.add(log);
		}

		MessageUtil.tell_player_message(player, msg);
	}
	
	
	
	/**取出临时元宝
	 * 
	 * @param player
	 */
	public void stReqGetTmpYuanbaoMessage(Player player) {
		int tyb = player.getCanreceiveyuanbao();
		if (tyb > 0) {
			Gold ybdata = player.getGold();
			int recharge = 0;
			int yuanbao = 0;
			if (ybdata != null) {
				yuanbao = ybdata.getGold();
				recharge = ybdata.getTotalGold();
						
			}

			if (yuanbao > 0 &&  recharge > 0) {
				if (yuanbao + tyb >= Global.BAG_MAX_GOLD) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的可携带元宝数量已经达到上限，无法领取。"));
					return;
				}
				if (ManagerPool.backpackManager.changeTmpGold(player,-tyb)){
					 ManagerPool.backpackManager.addGold(player,tyb,Reasons.QUCHUTMPYB,Config.getId()); 
					 ResCanreceiveYuanbaoMessage tymsg = new ResCanreceiveYuanbaoMessage();
					 tymsg.setCanryuanbao(0);
					 player.getTempyuanbaoLogdata().clear();		//清除记录
					 MessageUtil.tell_player_message(player, tymsg);
					 MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("恭喜您成功领取{1}元宝。"),String.valueOf(tyb));
				}	
			 }else {
				 MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您的元宝账户未激活，请充值至少1元宝激活后再领取。"));
			}
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您没有可领取的元宝。"));
		}
	}
	
	
	

	
	
}





