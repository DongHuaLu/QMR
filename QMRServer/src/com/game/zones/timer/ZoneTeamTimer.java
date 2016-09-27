package com.game.zones.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_clone_activityBean;
import com.game.fightpower.manager.FightPowerManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.timer.TimerEvent;
import com.game.zones.manager.ZonesTeamManager;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.structs.ZoneApplyData;
import com.game.zones.structs.ZoneContext;
import com.game.zones.structs.ZoneTeamData;

/**多人副本传送计时器
 * 
 * @author zhangrong
 *
 */
public class ZoneTeamTimer extends TimerEvent{
	protected Logger log = Logger.getLogger(ZoneTeamTimer.class);
	
	public ZoneTeamTimer(int serverId) {
		super(-1, 1000);
	}
	

	@Override
	public void action() {
		int ms = (int) (System.currentTimeMillis()/1000);
		ConcurrentHashMap<Long, ZoneTeamData> zoneteammap = ZonesTeamManager.getZoneteammap();
		Iterator<Entry<Long, ZoneTeamData>> it = zoneteammap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Long, ZoneTeamData> entry = it.next();
			long teamid = entry.getKey();
			ZoneTeamData zoneTeamData = entry.getValue();
			Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneTeamData.getZonemodelid());
			if (zonedata != null) {
//				if (zoneTeamData.getWaittime() > 0 && zoneTeamData.getWaittime() == ms ) {	//倒计时已经过，进行检测
//					boolean is =true;
//					for (int select : zoneTeamData.getMemberidmap().values()) {
//						if (select != 2) {
//							is = false;
//							break;
//						}
//					}
//					
//					if (is == false) { 	//条件未达到，停止传送,删除传送信息
//						//TODO 停止传送后，需要给队友提示
//					}else{
//						
//					}
//		
//				}else 
				if (zoneTeamData.getEntertime() > 0 && zoneTeamData.getEntertime() <= ms ) {//这里要进行传送了
					TeamInfo info = ManagerPool.teamManager.getTeam(teamid);
					if (info != null ) {
						Player player = ManagerPool.playerManager.getOnLinePlayer(zoneTeamData.getInitiateplayerid());
						if (player != null) {
							if(ManagerPool.zonesTeamManager.ckTeamRequirement(player, info, zonedata)){
								boolean is = true;
								if (info.getMemberinfo().size() == zoneTeamData.getMemberidmap().size()) {
									for (TeamMemberInfo teammember : info.getMemberinfo()) {
										if (!zoneTeamData.getMemberidmap().containsKey(teammember.getMemberid())) {
											is = false;
											break;
										}
									}
								}

								if (is) {
									ZoneContext zone = null;
									ICreateZoneScript script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(ScriptEnum.ZONE_CREATE);
						 			if (script != null) {
						 				try {
						 					zone = script.onCreate(player, zonedata.getQ_id());
						 				} catch (Exception e) {
						 					log.error(e, e);
						 				}
						 			} else {
						 				log.error("进入副本脚本不存在！");
						 			}
						 			if(zone!=null){
						 				List<Long> teamlist = new ArrayList<Long>();
						 				List<Integer> levellist = new ArrayList<Integer>();
										for (TeamMemberInfo teammember : info.getMemberinfo()) {
											Player member = ManagerPool.playerManager.getOnLinePlayer(teammember.getMemberid());
											if (member != null) {
												member.setZoneteamenterid(zone.getId());	//标记要传送的副本ID
												teamlist.add(member.getId());
												member.setTransType(0);
												levellist.add(member.getLevel());
											}
										}
										zone.getOthers().put("teamlist",teamlist);	//往副本写入当前所属人物id
										zone.getOthers().put("levellist",levellist);	//往副本写入人物等级
						 			}
	
								}else {
									//停止传送后，需要给队友提示
								}
							}
						}else {
							it.remove();
							continue;
						}
					}
					it.remove();
		 			continue;
				}
			}
			
			if(( ms - zoneTeamData.getInitiatetime()) > (ZonesTeamManager.ENTERTIME + ZonesTeamManager.WAITTIME)){
				it.remove();
			}
		}
		
		//-----------------------------报名参加多个阵营的副本--------------------------------
		
		ManagerPool.zonesTeamManager.autogroup();
		ManagerPool.zonesTeamManager.autoteamzoneGroup();
		Vector<List<ZoneApplyData>> zoneReadylist = ZonesTeamManager.getZonereadylist();
		Iterator<List<ZoneApplyData>> readyit = zoneReadylist.iterator();
		
		while (readyit.hasNext()) {
			List<ZoneApplyData> list = (List<ZoneApplyData>) readyit.next();
			if (list.size() == 0) {
				continue;
			}
			
			if(list.get(0).getTime() + ZonesTeamManager.ENTERTIME < ms  ){
				ZoneContext zone = null;
				Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(list.get(0).getZonemodelid());
				if (zonedata != null) {
					ICreateZoneScript script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(ScriptEnum.ZONE_CREATE);
		 			if (script != null) {
		 				try {
		 					zone = script.onCreate(null, zonedata.getQ_id());
		 				} catch (Exception e) {
		 					log.error(e, e);
		 				}
		 			} else {
		 				log.error("进入副本脚本不存在！");
		 			}
		 			if (zone !=null) {
		 				try {
			 				List<Long> teamlist = new ArrayList<Long>();
			 				List<Integer> levellist = new ArrayList<Integer>();
			 				List<Integer> alvlist = new ArrayList<Integer>();
			 				List<Integer> blvlist = new ArrayList<Integer>();
			 				HashMap<Long, Integer> fpmap= new HashMap<Long, Integer>();	
				 			for (ZoneApplyData zoneApplyData : list) {
								Player player = ManagerPool.playerManager.getOnLinePlayer(zoneApplyData.getPlayerid());
								if (player != null) {
									player.setZoneteamenterid(zone.getId());
									teamlist.add(player.getId());
									levellist.add(player.getLevel());
									if (player.getGroupmark() == 1) {
										alvlist.add(player.getLevel());
									}else if (player.getGroupmark() == 2) {
										blvlist.add(player.getLevel());
									}
									int fp=FightPowerManager.getInstance().calAllFightPower(player);
									fpmap.put(player.getId(), fp);
								}
							}
							zone.getOthers().put("teamlist",teamlist);	//往副本写入当前人物id列表
							zone.getOthers().put("levellist",levellist);//往副本写入人物等级列表
							zone.getOthers().put("alevel",alvlist);	//A方等级列表
							zone.getOthers().put("blevel",blvlist);	//B方等级列表
							zone.getOthers().put("fpmap",fpmap);//战斗力列表
		 				}catch (Exception e) {
							log.error("写副本进入前数据出错："+ e,e);
							readyit.remove();
						}
		 			}
				}
				readyit.remove();
			}
		}
	}
}	
	

	
	
	
	
	
	
	
	
	
