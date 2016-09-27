package com.game.marriage.timer;

import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_globalBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.marriage.manager.MarriageManager;
import com.game.marriage.message.ResNoticeReceiveRedToClientMessage;
import com.game.marriage.message.ResWeddingExteriorToClientMessage;
import com.game.marriage.structs.Marriage;
import com.game.marriage.structs.Wedding;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.CommonConfig;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
/**婚礼定时处理
 * 
 * @author zhangrong
 *
 */
public class MarriageTimer extends TimerEvent{
	private Logger log = Logger.getLogger(MarriageTimer.class);
	private int serverId;
	
	private int lineId;
	
	private int mapId;

	private int minute;
	
	private boolean isStart = true;
	
	public MarriageTimer(int serverId, int lineId, int mapId) {
		super(-1,1000*30);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历玩家列表
//		Iterator<Player> iter = map.getPlayers().values().iterator();
//		try {
//			while (iter.hasNext()) {
//				Player player = (Player) iter.next();
//				//不是本线玩家
//				if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
//				if (player.getMarriageid() > 0 && player.getLevel() >= 30) {	//这里只检测结婚状态下，，离婚会在另外地方清理
//					long spouseid = ManagerPool.marriageManager.getSpouseid(player);
//					if(map.getPlayers().containsKey(spouseid)){
//						List<Buff> list = ManagerPool.buffManager.getBuffByModelId(player, 9166);
//						if (list.isEmpty()) {
//							ManagerPool.buffManager.addBuff(player, player, 9166, 0, 0, 0);//结婚技能 风雨同舟
//						}
//					}else {
//						List<Buff> list = ManagerPool.buffManager.getBuffByModelId(player, 9166);
//						if (!list.isEmpty()) {
//							ManagerPool.buffManager.removeByBuffId(player, 9166);//移除 风雨同舟
//						}
//					}
//				}
//			}			
//		} catch (Exception e) {
//			log.error(e,e);
//		}
//		

		try {
			if (mapId == 20002 && lineId == 1){//只有20002 ，1线会执行
				//30秒调用一次，确保每分钟不跳过
				long ms = System.currentTimeMillis();
				int min = TimeUtil.getDayOfMin(ms);
				if (minute != min ) {
					minute = min;
				}else {
					return;
				}
				
				//-----------------------------清理已经离婚的婚姻数据--------------------
				ManagerPool.marriageManager.removeMARKMAP();

				int hour = TimeUtil.getDayOfHour(ms);
				
				if (isStart && hour < 20) {	//小于20点重启
					isStart=false;
					ManagerPool.marriageManager.filterDeleteMarriage();//筛选无用的结婚数据放入删除表
				}else if (isStart && hour >= 21) {	//大于21点启动，把之前进行中的宴会设置为已经结束
					isStart=false;
					List<Wedding>  weddinglist = ManagerPool.marriageManager.selectWedding(false);
					if (weddinglist.size() > 0) {
						for (Wedding wedding : weddinglist) {
							if (wedding.getStatus() == 1) {
								wedding.setStatus((byte) 2);
								ManagerPool.marriageManager.saveWeddinginfo(wedding, false);
							}
						}
					}
				}
				
				
				
				//-----------------------------刷婚宴桌子---------------------------------------

				if ((hour == 20 && min == 0 ) || (isStart && hour == 20) ){//20点整，或者是20点范围内，服务器重启执行操作
					ManagerPool.marriageManager.setWeddingstatus((byte) 1);
					List<Wedding>  weddinglist = ManagerPool.marriageManager.selectWedding(false);
					if (weddinglist.size() == 0) {
						return;
					}
					
					
					List<Long> widxlist = MarriageManager.getWeddingidxlist();
					widxlist.clear();
					int s= 0;
					ResWeddingExteriorToClientMessage extmsg = new ResWeddingExteriorToClientMessage();
					for (Wedding wedding: weddinglist) {
						Marriage marriage = ManagerPool.marriageManager.getMarriage(wedding.getMarriageid());
						//如果是20点范围内重启，会把已经在进行中的宴会继续下去
						if (marriage != null && ( (marriage.getStatus() == 0) || (isStart && hour == 20) && marriage.getStatus() == 1 )) {
							int n = s/6;	//当天5+1个婚宴（但是在申请那里限制为6） 
							s = s + 1;
							wedding.setDay(n);//重设天数
							if (n == 0) {
								wedding.setStatus((byte) 1);
								ManagerPool.marriageManager.saveWeddinginfo(wedding, false);
								widxlist.add(wedding.getMarriageid());//今天要举办的婚礼索引
								extmsg.getRoleslist().add(marriage.getSpouseslist().get(0).getPlayerid());
								extmsg.getRoleslist().add(marriage.getSpouseslist().get(1).getPlayerid());
								ManagerPool.marriageManager.addWeddingLog(wedding.getMarriageid(),1);
							}
						}
					}
					isStart=false;
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("今晚的盛大婚宴欢迎英雄豪杰前往参与，可获大量经验与真气，婚宴5分钟后将在咸阳王城1线开席，欢迎各位光临。可在婚宴列表中查询今晚的婚宴主角，让我们一起恭祝他们恩爱永久，缠绵三生。"));
					//把在王城1线的婚宴主角丢到指定坐标
					for (Long rid : extmsg.getRoleslist()) {
						Player player = ManagerPool.playerManager.getOnLinePlayer(rid);
						if (player!= null ) {
							Map currmap = ManagerPool.mapManager.getMap(player);
							if (currmap != null && currmap.getMapModelid() == mapId  && currmap.getLineId() == lineId) {
								Grid grid = MapUtils.getGrid(120, 90, currmap.getMapModelid());
								ManagerPool.mapManager.changePosition(player, grid.getCenter());
							}
						}
					}
					
					MessageUtil.tell_world_message(extmsg);
				
				}else if (hour == 20 && min%5 == 0) {//每5分钟刷新一次宴会桌子
					List<Long> widxlist = MarriageManager.getWeddingidxlist();
					if (widxlist.size() == 0) {//宴会索引条数为0，停止
						return;
					}
					
					if (min == 5) {
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("婚宴已经在咸阳王城1线开席，欢迎各位光临。可在婚宴列表中查询今晚的婚宴主角，让我们一起恭祝他们恩爱永久，缠绵三生。"));	
					}
					
					
					List<NPC> npclist = ManagerPool.npcManager.findNpc(map, 50001);
					List<NPC> npcs2 = ManagerPool.npcManager.findNpc(map, 50002);
					npclist.addAll(npcs2);
					List<NPC> npcs3 = ManagerPool.npcManager.findNpc(map, 50003);
					npclist.addAll(npcs3);
					for (int i = 0; i < npclist.size(); i++) {	//先清理NPC
						ManagerPool.mapManager.quitMap(npclist.get(i));
					}
					List<Integer[]> coordinatelist = null;
					Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_XY.getValue());
					if (data  != null) {
						coordinatelist = JSON.parseArray(data.getQ_string_value(),Integer[].class);
					}else {
						log.error("Q_globalBean婚宴坐标为空");
					}
					
					
					for (long id : widxlist) {	//每个婚宴循环
						Marriage marriage = ManagerPool.marriageManager.getMarriage(id);
						Wedding wedding = ManagerPool.marriageManager.getWedding(id);
						if (marriage != null && wedding != null) {
							int num = 0;	//可食用次数
							int wednpcid = 0;	//NPCID
							int tabnum =0;	//桌子数量
							if(wedding.getType() == 1){
								wednpcid = 50001;
								num = 5;
								tabnum = 5;
							}else if (wedding.getType() == 2) {
								wednpcid = 50002;
								num = 10;
								tabnum = 6;
							}else if (wedding.getType() == 3) {
								wednpcid = 50003;
								num = 20;
								tabnum=7;
							}
	
							StringBuffer stringBuffer = new StringBuffer();
							stringBuffer.append(marriage.getSpouseslist().get(0).getName());
							stringBuffer.append(ResManager.getInstance().getString("与"));
							stringBuffer.append(marriage.getSpouseslist().get(1).getName());
							stringBuffer.append(ResManager.getInstance().getString("的"));
							stringBuffer.append(ManagerPool.marriageManager.getWeddingname(wedding.getType()));
							stringBuffer.append(ResManager.getInstance().getString("婚宴"));

							for (int i = 0; i < tabnum; i++) {//每次刷新tabnum个桌子
								Position position = null;
								if (coordinatelist != null && coordinatelist.size() > 0) {
									int rnd = RandomUtils.random(coordinatelist.size());
									Integer[] coordinate = coordinatelist.remove(rnd);
									Grid grid = MapUtils.getGrid(coordinate[0], coordinate[1], mapId);
								 	if (grid != null ) {
								 		position = grid.getCenter();
									}
								}
								
								if (position == null) {
									position = MapUtils.getMapRandPoint(mapId);
								}

								NPC npc = ManagerPool.npcManager.createNpc(wednpcid, map, true);
								npc.getParameters().put("marriageid",id );	//婚礼ID
								npc.getParameters().put("num",num);	//可食用次数
								npc.getParameters().put("weddingtype",wedding.getType() );	//婚礼ID
								npc.setPosition(position);
								npc.setName(stringBuffer.toString());
								ManagerPool.mapManager.enterMap(npc);
								log.error(npc.getPosition().getX()/25 +","+ npc.getPosition().getX()/25 +","+ npc.getName());
							}
						}
					}
					
					if (min == 15 || min== 30 || min == 45) {	//刷招财猫
						ManagerPool.marriageManager.marriageMonster(map);
					}
				}else if (hour == 21 && min == 0) {//婚宴结束
					ManagerPool.marriageManager.setWeddingstatus((byte) 2);
					List<Long> widxlist = MarriageManager.getWeddingidxlist();
					if (widxlist.size() > 0) {
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("今晚的盛大婚宴已经结束，再次恭贺各位新人，同时也感谢所有的宾客。祝大家接下来的时间里游戏愉快，事事如意。"));
						
						for (long id : widxlist) {	//找到并结束
							Marriage marriage = ManagerPool.marriageManager.getMarriage(id);
							Wedding wedding = ManagerPool.marriageManager.getWedding(id);
							if (marriage != null && wedding != null) {
								wedding.setStatus((byte) 2);
								ManagerPool.marriageManager.saveWeddinginfo(wedding, false);
								if (wedding.getRedsum() > 0) {//通知举办婚宴在线玩家领取红包
									Player spouse1 = ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseslist().get(0).getPlayerid());
									Player spouse2 = ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseslist().get(1).getPlayerid());
									ResNoticeReceiveRedToClientMessage cmsg = new ResNoticeReceiveRedToClientMessage();
									if (spouse1 != null) {
										MessageUtil.tell_player_message(spouse1, cmsg);
									}
									if (spouse2 != null) {
										MessageUtil.tell_player_message(spouse2, cmsg);
									}
								}
								ManagerPool.marriageManager.addWeddingLog(wedding.getMarriageid(),2);
							}
						}	
						
						List<NPC> npclist = ManagerPool.npcManager.findNpc(map, 50001);
						List<NPC> npcs2 = ManagerPool.npcManager.findNpc(map, 50002);
						npclist.addAll(npcs2);
						List<NPC> npcs3 = ManagerPool.npcManager.findNpc(map, 50003);
						npclist.addAll(npcs3);
						for (int i = 0; i < npclist.size(); i++) {	//先清理NPC
							ManagerPool.mapManager.quitMap(npclist.get(i));
						}
						widxlist.clear();
					}
					MessageUtil.tell_world_message(new ResWeddingExteriorToClientMessage());//去掉改变外观
				}else if (hour == 0 && min == 1) {
					ManagerPool.marriageManager.filterDeleteMarriage();//筛选无用的结婚数据放入删除表
					ManagerPool.marriageManager.setWeddingstatus((byte) 0);
					List<Wedding>  weddinglist = ManagerPool.marriageManager.selectWedding(false);
					if (weddinglist.size() == 0) {
						return;
					}
					int s= 0;
					for (Wedding wedding: weddinglist) {//0点重新排列
						int n = s/6;	//当天5+1个婚宴
						s = s + 1;
						wedding.setDay(n);
					}
				}
			}
		} catch (Exception e) {
			log.error("婚宴执行错误"+e,e);
		}
	}
			
	
	
	
	
}
