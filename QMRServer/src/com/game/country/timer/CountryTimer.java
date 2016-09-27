package com.game.country.timer;

import java.util.List;

import com.game.country.bean.WarRewardInfo;
import com.game.country.manager.CountryManager;
import com.game.country.message.ResKingCityTimeRewardToClientMessage;
import com.game.country.message.ResKingCityYuXiCoordinateToClientMessage;
import com.game.country.structs.KingCity;
import com.game.data.bean.Q_characterBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.npc.struts.NPC;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class CountryTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;

	
	
	public CountryTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		long systime = System.currentTimeMillis()/1000;
		KingCity kingCity = ManagerPool.countryManager.getKingcity();
		
		//-----------------------------------控制雕像改名，消失和出现 res=47表示女雕像
		if (map.getMapModelid() == 20002){
			if(map.getParameters().containsKey(CountryManager.WANGCHENGDIAOXIANG)){
				int status  =(Integer)map.getParameters().get(CountryManager.WANGCHENGDIAOXIANG);
				if (status == 1) {//展示
					String kingname= "";
					if (kingCity.getGuildid() > 0 && kingCity.getHoldplayername() != null && !kingCity.getHoldplayername().equals("")) {
						 kingname= ResManager.getInstance().getString("【秦王陛下】")+kingCity.getHoldplayername();
					}
					if (kingname != null && kingname.length() > 0) {
						List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
						if (npcs.size() > 0) {
							if(kingCity.getHoldplayersex() == 2){
								npcs.get(0).setRes("47");
							}else {
								npcs.get(0).setRes("45");
							}
							ManagerPool.npcManager.changeNpcName(npcs.get(0), kingname);
						}
					}else {
						List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
						if (npcs.size() > 0 && npcs.get(0).isShow() ) {
							ManagerPool.npcManager.hideNpc(npcs.get(0));
						}
					}
				}else if (status == 2 ) {//隐藏
					List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
					if (npcs.size() > 0 && npcs.get(0).isShow() ) {
						ManagerPool.npcManager.hideNpc(npcs.get(0));
					}
				}
				map.getParameters().remove(CountryManager.WANGCHENGDIAOXIANG);
			}
		}
		
		
		
		
		
		
		//-----------------------------------控制雕像改名，消失和出现 res=47表示女雕像
//		if (map.getMapModelid() == 20002){
//			if (ManagerPool.countryManager.getSiegestate() == 1) {//攻城开始
//				List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
//				if (npcs.size() > 0) {
//					if (npcs.get(0).isShow()) {
//						ManagerPool.npcManager.hideNpc(npcs.get(0));
//					}
//				}
//			}else if (ManagerPool.countryManager.getSiegestate() == 2) {//攻城结束
//				List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
//				if (npcs.size() > 0) {
//					if (kingCity.getHoldplayername() != null && !kingCity.getHoldplayername().equals("")) {
//						if (!npcs.get(0).isShow()) {
//							if(kingCity.getHoldplayersex() == 2){
//								npcs.get(0).setRes("47");
//							}
//							ManagerPool.npcManager.changeNpcName(npcs.get(0), "【秦王陛下】\n"+kingCity.getHoldplayername());
//						}
//					}
//				}
//			}else {
//				if (systime%30 == 0) {//没有攻城，30秒执行一次
//					List<NPC> npcs = ManagerPool.npcManager.findNpc(map, 12450);
//					if (npcs.size() > 0) {
//						if (kingCity.getGuildid() > 0 && kingCity.getHoldplayername() != null && !kingCity.getHoldplayername().equals("")) {
//							String kingname= "【秦王陛下】\n"+kingCity.getHoldplayername();
//							if (npcs.get(0).getName() == null || !npcs.get(0).getName().equals(kingname)) {
//								if(kingCity.getHoldplayersex() == 2){
//									npcs.get(0).setRes("47");
//								}
//								ManagerPool.npcManager.changeNpcName(npcs.get(0), kingname);
//							}
//						}else {
//							if (npcs.get(0).isShow()) {
//								ManagerPool.npcManager.hideNpc(npcs.get(0));
//							}
//						}
//					}
//				}
//			}
//		}
		//-----------------------------------------------------
		
		if (map.getMapModelid() != ManagerPool.countryManager.SIEGE_MAPID) {
			return;
		}
		
		
		
		if (ManagerPool.countryManager.getSiegestate() == 1) {
			if (ManagerPool.countryManager.getSiegecountdown()+30*60 == systime) {
				if (ManagerPool.countryManager.getMonstatus() == 0) {
					ManagerPool.countryManager.appearMonster();	//刷怪
				}
			
			}else if( systime %10 == 0){	
				ManagerPool.countryManager.mapbroadcastTop();	
			}else if(ManagerPool.countryManager.getSiegecountdown()+60*60+5 == systime){	//如果是手动开启，这里特意延长5秒
				ManagerPool.countryManager.SiegeEnd(0);	//到时间结束
			}

			
			//遍历玩家列表
			
			Player[] players = map.getPlayers().values().toArray(new Player[0]);
			for (Player player : players) {
				//不是本线玩家
				if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			
				if (map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID) {
					player.setKingcityrewtime(player.getKingcityrewtime() +1);
					if (player.getKingcityrewtime() == 20*60) {
						player.getActivitiesReward().put("PARTISIEGE", ""+TimeUtil.GetCurTimeInMin(4));
					}
					
					if ( systime % 20 == 0) {		//20秒得到1次打坐奖励
						long day=TimeUtil.GetCurTimeInMin(4);	//每天清理奖励数据
						if(player.getKingcityrewday() != day ){
							player.setKingcityrewtime(0);
							player.setKingcityrewday((int) day);
							player.setKingcityexp(0);
							player.setKingcityzq(0);
						}
						Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
						int multiple = ManagerPool.countryManager.getposexpmultiple(player);
						int zq= multiple*model.getQ_dazuozq();
						int exp = multiple*model.getQ_dazuoexp();
						player.setKingcityexp( exp + player.getKingcityexp());
						player.setKingcityzq( zq + player.getKingcityzq());
						ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.COUNTRY);
						ManagerPool.playerManager.addZhenqi(player, zq,AttributeChangeReason.COUNTRY);
						ResKingCityTimeRewardToClientMessage expmag = new ResKingCityTimeRewardToClientMessage();
						WarRewardInfo warRewardInfo = new WarRewardInfo();
						warRewardInfo.setExp(player.getKingcityexp());
						warRewardInfo.setZhenqi(player.getKingcityzq());
						warRewardInfo.setRemaintime(player.getKingcityrewtime());
						expmag.setWarrewardinfo(warRewardInfo);
						MessageUtil.tell_player_message(player, expmag);
						
					}

					//玉玺持有者
					if (kingCity.getHoldplayerid() > 0 && kingCity.getHoldplayerid() == player.getId()) {
						if (kingCity.getHoldtime() > 0 ) {
							long endtime = systime - kingCity.getHoldtime();
							if (endtime >= 20*60) {		//20 分钟结束攻城战
								ManagerPool.countryManager.SiegeEnd(1);
							}else{
								if ( endtime %5 == 0) {	//5秒公告一次信息
									ManagerPool.countryManager.stcountryWarInfo(player,true);
								}else{
									//每秒更新玉玺坐标
									ResKingCityYuXiCoordinateToClientMessage xymsg=new ResKingCityYuXiCoordinateToClientMessage();
									xymsg.setMx(player.getPosition().getX());
									xymsg.setMy(player.getPosition().getY());
									MessageUtil.tell_map_message(map, xymsg);
								}
							}
						}
					}   
				}	
			}
		}else if (ManagerPool.countryManager.getSiegestate() == 2) {
			Player[] players = map.getPlayers().values().toArray(new Player[0]);
			for (Player player : players) {
				if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
				//8秒内得到参与时间奖励
				if (map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID) {
					if ( systime % 5 == 0) {
						ManagerPool.countryManager.giveSiegeTimeReward(player);
					}
				}
			}
//				if ( CountryManager.getInstance().getMovetime() > 0 && CountryManager.getInstance().getMovetime() < systime ) {
//					ManagerPool.countryManager.setMovetime(0);
//					ManagerPool.countryManager.backtocitymove();//全部传送
//				}
		}
	}
	
	
	
	
}
