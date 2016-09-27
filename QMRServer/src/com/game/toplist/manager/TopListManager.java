package com.game.toplist.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_toplistchestBean;
import com.game.data.manager.DataManager;
import com.game.horse.manager.HorseManager;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.toplist.bean.ChestInfo;
import com.game.toplist.message.ReqChangeTitleToServerMessage;
import com.game.toplist.message.ReqGetTopListToServerMessage;
import com.game.toplist.message.ReqGetTopListToWorldMessage;
import com.game.toplist.message.ReqWorShipToServerMessage;
import com.game.toplist.message.ReqWorShipToWorldMessage;
import com.game.toplist.message.ReqZoneTopToWorldMessage;
import com.game.toplist.message.ResGetTopAwardToServerMessage;
import com.game.toplist.message.ResGetTopTitleToClientMessage;
import com.game.toplist.message.ResGetTopTitleToServerMessage;
import com.game.toplist.message.ResTopListChestInfoToClientMessage;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

/**
 * 排行榜
 *
 * @author 杨鸿岚
 */
public class TopListManager {

	private Logger log = Logger.getLogger(TopListManager.class);
	private static Object obj = new Object();
	//排行榜管理类实例
	private static TopListManager manager;

	private TopListManager() {
	}

	public static TopListManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new TopListManager();
			}
		}
		return manager;
	}
	private byte Error_Success = 0;
//	private byte Error_Fail = -1;
	//
	//排行类型
//	private final static int TOPTYPE_DEFAULT = 0;
	private final static int TOPTYPE_LEVEL = 1;
	private final static int TOPTYPE_HORSE = 2;
	private final static int TOPTYPE_GEST = 3;
	private final static int TOPTYPE_LONGYUAN = 4;
	private final static int TOPTYPE_EVENCUT = 5;
//	private final static int TOPTYPE_PET = 6;
//	private final static int TOPTYPE_FIGHTPOWER = 7;
//	private final static int TOPTYPE_ARROW = 8;
//	private final static int TOPTYPE_MAX = 9;
	//
	public void reqChangeTitleToServer(Player player, ReqChangeTitleToServerMessage message) {
		if (message.getTitleid() != player.getToptitleid()) {
			if (player.getTitleidlist().contains(message.getTitleid())) {
				player.setToptitleid(message.getTitleid());
				PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.TOPTITLE);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您改变称号成功！"));
			} else {
				if (message.getTitleid() == 0) {
					player.setToptitleid(0);
					PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.TOPTITLE);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您取消称号成功！"));
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有该称号！"));
				}
			}
		}
	}

	public void reqGetTopListToServer(Player player, ReqGetTopListToServerMessage message) {
		ReqGetTopListToWorldMessage sendMessage = new ReqGetTopListToWorldMessage();
		sendMessage.setPlayerid(player.getId());
		sendMessage.setToptype(message.getToptype());
		MessageUtil.send_to_world(sendMessage);
		//发送宝箱信息
		ResTopListChestInfoToClientMessage remsg = new ResTopListChestInfoToClientMessage();
		List<ChestInfo> chestlist = new ArrayList<ChestInfo>();
		chestlist = TopListManager.getInstance().getChestInfoList(player);
		remsg.setChestinfolist(chestlist);
		MessageUtil.tell_player_message(player, remsg);
	}

	public void reqWorShipToServer(Player player, ReqWorShipToServerMessage message) {
		ReqWorShipToWorldMessage sendMessage = new ReqWorShipToWorldMessage();
		sendMessage.setPlayerid(player.getId());
		sendMessage.setWorshipplayerid(message.getWorshipplayerid());
		MessageUtil.send_to_world(sendMessage);
	}

	public void resGetTopAwardToServer(Player player, ResGetTopAwardToServerMessage message) {
		if (player != null) {
			if (message.getZonetype() == 0) {
				Q_characterBean q_characterBean = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
				int exp = 0;
				if (q_characterBean != null) {
					exp = q_characterBean.getQ_basis_exp() * 3;
				}
				if (exp == 0) {
					exp = message.getExp();
				}
				PlayerManager.getInstance().addExp(player, exp, AttributeChangeReason.TOPLISTAWARD);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("崇拜奖励：增加 %d 经验"), exp));
				//MessageUtil.notify_player(player, Notifys.MOUSEPOS, String.format("崇拜奖励：增加 %d 经验", exp));
				BackpackManager.getInstance().changeMoney(player, message.getMoney(), Reasons.TOPLIST_AWARD, Config.getId());
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, String.format(ResManager.getInstance().getString("崇拜奖励：增加 %d 铜币"), message.getMoney()));
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, String.format(ResManager.getInstance().getString("崇拜奖励：增加 %d 经验, 增加 %d 铜币"), exp, message.getMoney()));
			} else {
				//TODO 根据副本类型获得奖励
				if (message.getZonetype() == 2) {
					if (message.getZoneid() == 3) {//校场最快记录通关
						player.getActivitiesReward().put("REWARDJCFB", Long.toString(TimeUtil.GetCurTimeInMin(4)));
					}					
				}else if (message.getZonetype() == 1) {
					if (!player.getActivitiesReward().containsKey("REWARDMAPBT"+message.getZoneid())) {
						player.getActivitiesReward().put("REWARDMAPBT"+message.getZoneid(), "1");
					}
				}
			}
		}
	}

	public void resGetTopTitleToServer(Player player, ResGetTopTitleToServerMessage message) {
		if (player != null) {
			player.getTitleidlist().clear();
			player.getTitleidlist().addAll(message.getTitleidlist());
			if (!player.getTitleidlist().contains(player.getToptitleid())) {
				player.setToptitleid(0);
				PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.TOPTITLE);
			}
			ResGetTopTitleToClientMessage sendMessage = new ResGetTopTitleToClientMessage();
			sendMessage.setErrorcode(Error_Success);
			sendMessage.setTitleidlist(message.getTitleidlist());
			MessageUtil.tell_player_message(player, sendMessage);
		}
	}

	public void completeZone(int scriptid, Player player, String jsonstr) {
		if (player != null) {
			ReqZoneTopToWorldMessage sendMessage = new ReqZoneTopToWorldMessage();
			sendMessage.setPlayerid(player.getId());
			sendMessage.setZonetype(scriptid);
			sendMessage.setZonetopjsonstr(jsonstr);
			MessageUtil.send_to_world(sendMessage);
		}
	}
	
	//领取宝箱
	public boolean recieveChest(Player player, int chestid){
		HashMap<Integer,Q_toplistchestBean> chestmap = DataManager.getInstance().q_toplistchestContainer.getMap();
		Q_toplistchestBean chestBean = chestmap.get(chestid);
		if(canRecieve(player, chestBean)){ //如果可领取
			if(BackpackManager.getInstance().getAbleAddNum(player, chestBean.getQ_gift(), true, 0L)>0){
				List<Item> createItems = Item.createItems(chestBean.getQ_gift(), 1, true, 0L);
				if(createItems.size()>0){
					if(BackpackManager.getInstance().addItems(player, createItems, Reasons.TOPLIST_CHEST, Config.getId())){
						player.getChestRecievedMap().put(""+chestBean.getQ_chest_type(), chestid);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//发送宝箱信息
	public List<ChestInfo> getChestInfoList(Player player){
		List<ChestInfo> chestinfolist = new ArrayList<ChestInfo>();
		//每个类型分别处理
		ResTopListChestInfoToClientMessage msg = new ResTopListChestInfoToClientMessage();
		chestinfolist.add(getChestInfoByType(player, TOPTYPE_LEVEL));
		chestinfolist.add(getChestInfoByType(player, TOPTYPE_HORSE));
		chestinfolist.add(getChestInfoByType(player, TOPTYPE_GEST));
		chestinfolist.add(getChestInfoByType(player, TOPTYPE_LONGYUAN));
		chestinfolist.add(getChestInfoByType(player, TOPTYPE_EVENCUT));
		
		msg.setChestinfolist(chestinfolist);
		return chestinfolist;
	}
	public ChestInfo getChestInfoByType(Player player, int type){
		ChestInfo cinfo = new ChestInfo();
		HashMap<Integer,Q_toplistchestBean> chestmap = DataManager.getInstance().q_toplistchestContainer.getMap();
		List<Q_toplistchestBean> chestlist = DataManager.getInstance().q_toplistchestContainer.getList();
		if(player.getChestRecievedMap().containsKey(""+type)){  //玩家领过这个类型
			int lastid = player.getChestRecievedMap().get(""+type);
			int nextid = lastid+1;
			Q_toplistchestBean b = chestmap.get(nextid);
			cinfo = new ChestInfo();
			cinfo.setChesttype(type);
			if(b==null){ log.error("下个宝箱不存在"+nextid); return cinfo; }
			if(b.getQ_chest_type()==type){ 
				cinfo.setChestid(nextid);
				if(canRecieve(player, chestmap.get(nextid))){
					cinfo.setCanreceive((byte)1);
				}else{
					cinfo.setCanreceive((byte)0);
				}
			}else{  //本类型领完了
				cinfo.setChestid(0);
				cinfo.setCanreceive((byte)0);
			}
		}else{  //玩家没有领过这个类型
			//找出类型中的第一个
			for(int index=0; index<chestlist.size(); index++){
				Q_toplistchestBean b = chestlist.get(index);
				if(b.getQ_chest_type()==type){
					cinfo = new ChestInfo();
					cinfo.setChestid(b.getQ_chest_id());
					cinfo.setChesttype(b.getQ_chest_type());
					if(canRecieve(player, b)){
						cinfo.setCanreceive((byte)1);
					}else{
						cinfo.setCanreceive((byte)0);
					}
					break;
				}
			}
		}
		return cinfo;
	}
	
	//判断玩家是否满足宝箱条件
	public boolean canRecieve(Player player, Q_toplistchestBean chestBean){
		boolean canrecieve = false;
		//没领过此类型 或者 已领取的id小于当前要领取的id
		if(!player.getChestRecievedMap().containsKey(""+chestBean.getQ_chest_type()) || 
				player.getChestRecievedMap().get(""+chestBean.getQ_chest_type())<chestBean.getQ_chest_id()){
		
			int toptype = chestBean.getQ_chest_type();
			switch(toptype){
			case 1: //等级排行  条件 等级>=cont
				canrecieve = checkPlayerLevelCont(player, chestBean.getQ_recieve_cont());
				break;
			case 2: //坐骑
				canrecieve = checkHorseCont(player, chestBean.getQ_recieve_cont());
				break;
			case 3: //武功
				canrecieve = checkGESTCont(player, chestBean.getQ_recieve_cont());
				break;
			case 4: //龙元
				canrecieve = checkLongYuanCont(player, chestBean.getQ_recieve_cont());
				break;
			case 5: //连斩
				canrecieve = checkEventCutCont(player, chestBean.getQ_recieve_cont());
				break;
			default: canrecieve = false;
			}
		}
		return canrecieve;
	}
	
	//等级排行榜条件判断
	public boolean checkPlayerLevelCont(Player player, int contlevel){
		
		return player.getLevel()>=contlevel;
	}
	
	//坐骑排行榜条件判断
	public boolean checkHorseCont(Player player, int contlevel){
		
		return HorseManager.getInstance().getHorse(player).getLayer()>=contlevel;
	}
	
	//武功排行榜条件判断
	public boolean checkGESTCont(Player player, int contlevel){
		
		return player.getTotalSkillLevel()>=contlevel;
	}
	
	//龙元排行榜条件判断  contlevel = section*100+level
	public boolean checkLongYuanCont(Player player, int contlevel){
		int section = player.getLongyuan().getLysection(); //星图
		int level = player.getLongyuan().getLylevel(); //星位
		int cur = section*100+level;
		return cur>=contlevel;
	}
	
	//连斩排行榜条件判断
	public boolean checkEventCutCont(Player player, int contlevel){
		
		return player.getMaxEventcut()>=contlevel;
	}
	
}
