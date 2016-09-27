package com.game.zones.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_clone_activityBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.message.Message;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Position;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.zones.bean.ZoneApplyDataInfo;
import com.game.zones.bean.ZoneTeamInfo;
import com.game.zones.message.ReqZoneCancelSignupToGameMessage;
import com.game.zones.message.ReqZoneTeamEnterToGameMessage;
import com.game.zones.message.ReqZoneTeamSelectToGameMessage;
import com.game.zones.message.ResZoneApplyDataInfoToClientMessage;
import com.game.zones.message.ResZoneTeamNoticeSelectToClientMessage;
import com.game.zones.message.ResZoneTeamNoticeToClientMessage;
import com.game.zones.message.ResZoneTeamShowToClientMessage;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.structs.ZoneApplyData;
import com.game.zones.structs.ZoneContext;
import com.game.zones.structs.ZoneTeamData;

public class ZonesTeamManager {
	
	private static Object obj = new Object();
	protected Logger log = Logger.getLogger(ZonesTeamManager.class);
	//玩家管理类实例
	private static ZonesTeamManager manager;
	
	private ZonesTeamManager(){}
	
	public static ZonesTeamManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new ZonesTeamManager();
			}
		}
		return manager;
	}
	
	/**八卦阵入口，每天更新，全国共享
	 * 
	 */
	public static HashMap<Integer ,Integer> entrance = new HashMap<Integer ,Integer>() ;

	

	/**等待选择时间
	 * 
	 */
	public static int WAITTIME = 30;
	
	/**最后进入时间
	 * 
	 */
	public static int ENTERTIME = 5;
	
	/**
	 * 报名参加多人副本
	 * kye =副本模版ID
	 */
	private static ConcurrentHashMap<Integer,Vector<ZoneApplyData>> zoneapplymap = new ConcurrentHashMap<Integer, Vector<ZoneApplyData>>();
	
	/**
	 * 组队多人副本(队伍ID，队伍信息)
	 */
	private static ConcurrentHashMap<Long, ZoneTeamData> zoneteammap = new ConcurrentHashMap<Long, ZoneTeamData>();
	
	/**
	 * 报名后准备进入，暂时存放
	 */
	private static Vector< List<ZoneApplyData>> zonereadylist = new Vector< List<ZoneApplyData>>();
	
	/**
	 * 组队多人副本队伍匹配进入  (队伍ID，队伍信息)
	 */
	private static ConcurrentHashMap<Integer,Vector<ZoneTeamData>> zoneteammatch = new ConcurrentHashMap<Integer, Vector<ZoneTeamData>>();
	
	
	
	/**增加报名数据
	 * 
	 * @param zonemodelid
	 * @param ZoneApplyData
	 */
	public void addzoneapplymap(int zonemodelid,ZoneApplyData zoneApplyData){
		synchronized (zoneapplymap) {
			if (zoneapplymap.containsKey(zonemodelid)) {
				zoneapplymap.get(zonemodelid).add(zoneApplyData);
			}else {
				Vector<ZoneApplyData> vector = new Vector<ZoneApplyData>();
				vector.add(zoneApplyData);
				zoneapplymap.put(zonemodelid, vector);
			}
		}
	}
	
	/**增加队伍匹配报名数据
	 * 
	 * @param teamid
	 * @param zoneTeamData
	 */
	public void addzoneteammatch(int zonemodelid ,ZoneTeamData zoneTeamData){
		synchronized (zoneteammatch) {
			if (zoneteammatch.containsKey(zonemodelid)) {
				zoneteammatch.get(zonemodelid).add(zoneTeamData);
			}else {
				Vector<ZoneTeamData> vector = new Vector<ZoneTeamData>();
				vector.add(zoneTeamData);
				zoneteammatch.put(zonemodelid, vector);
			}
		}
	}
	
	
	public ZoneTeamData getmatchZoneTeamData(int zonemodelid,long teamid){
		if (zoneteammatch.containsKey(zonemodelid)) {
			Vector<ZoneTeamData> list = zoneteammatch.get(zonemodelid);
			for (ZoneTeamData zoneTeamData : list) {
				if (zoneTeamData.getTeamid() == teamid) {
					return zoneTeamData;
				}
			}
		}
		return null;
	}
	
	
	/**增加队伍匹配报名数据
	 * 
	 * @param teamid
	 * @param zoneTeamData
	 */
	public void addzonereadylist(List<ZoneApplyData> list){
		synchronized (zonereadylist) {
			zonereadylist.add(list);
		}
	}
	
	
	/**检测自己队伍是否已经在报名中，并且删除其他无用的队伍
	 * 
	 * @param zonemodelid
	 * @param teamid
	 * @return true 已经报过名
	 */
	public boolean checkzoneteammatch(int zonemodelid,long teamid){
		boolean is = false;
		synchronized (zoneteammatch) {
			if (zoneteammatch.containsKey(zonemodelid)) {
				Iterator<ZoneTeamData> it = zoneteammatch.get(zonemodelid).iterator();
				while (it.hasNext()) {
					ZoneTeamData zoneTeamData = (ZoneTeamData) it.next();
					//队伍ID为0或者发起时间超过1小时
					boolean isselect =true;
					for (int select : zoneTeamData.getMemberidmap().values()) {
						if (select != 2) {
							isselect = false;
							break;
						}
					}
					TeamInfo tame = ManagerPool.teamManager.getTeam(teamid);
					if (tame !=null) {
						if (zoneTeamData.getTeamid() == teamid ) {
							//如果择的人数和记录队伍总人数不一致，或者选择人数和队伍当前人数不一致
							if (zoneTeamData.getSelectnum() != zoneTeamData.getTeamsum() || zoneTeamData.getSelectnum() != tame.getMemberinfo().size() || (System.currentTimeMillis() /1000) - zoneTeamData.getInitiatetime() > 60*30) {
								it.remove();
							}else if (zoneTeamData.getSelectnum() == tame.getMemberinfo().size() && isselect   && (System.currentTimeMillis() /1000) - zoneTeamData.getInitiatetime() < 60*30 ) {
								is = true;
							}
						}else {
							//其他队伍-如果有人拒绝或者队伍ID为0
							if ( isselect == false || (zoneTeamData.getTeamid() == 0 ||  (System.currentTimeMillis() /1000) - zoneTeamData.getInitiatetime() > 60*30 ) ) {
								it.remove();
							}
						}
					}else {
						it.remove();
					}
				}
			}
		}
		return is;
	}
	

	
	/**检测自己是否已经在当前副本报名，并删除已经进入的人
	 * 
	 * @param zonemodelid
	 * @param playerid
	 * @return true 已经报过名
	 */
	public boolean checkzoneapplymap(int zonemodelid,long playerid){
		boolean is = false;
		synchronized (zoneapplymap) {
			if (zoneapplymap.containsKey(zonemodelid)) {
				Iterator<ZoneApplyData> it = zoneapplymap.get(zonemodelid).iterator();
				while (it.hasNext()) {
					ZoneApplyData zoneApplyData = (ZoneApplyData) it.next();
					
					if (zoneApplyData.getApplystatus() == 0 && zoneApplyData.getPlayerid() == playerid ) {
						is = true;
					}else {
						//如果已经进行传送 或者 报名 时间超过1小时，则删除
						if (zoneApplyData.getApplystatus() == 1 || (System.currentTimeMillis() /1000) - zoneApplyData.getTime() > 60*60) {
							it.remove();
						}else {
							Player mPlayer = ManagerPool.playerManager.getOnLinePlayer(zoneApplyData.getPlayerid());
							if (mPlayer == null) {//不在线
								it.remove();
							}else {
								Map map=ManagerPool.mapManager.getMap(mPlayer);
								if (map != null && map.isCopy()) {	//在副本中
									ResZoneApplyDataInfoToClientMessage pmsg = new ResZoneApplyDataInfoToClientMessage();
									ZoneApplyDataInfo zoneApplyDataInfo = new ZoneApplyDataInfo();
									zoneApplyDataInfo.getPlayerlvlist().add(mPlayer.getLevel());
									zoneApplyDataInfo.getPlayernamelist().add(mPlayer.getName());
									pmsg.setZoneapplydatainfo(zoneApplyDataInfo);
									MessageUtil.tell_player_message(mPlayer, pmsg);	//发送只有他个人的列表
								}
							}
						}
					}
				}
			}
		}
		return is;
	}
	
	
	/**检测玩家是否在准备传送的列表里
	 * 
	 * @param pid
	 * @param zonemodelid
	 * @return
	 */
	public boolean ckzonereadylist(long pid  ,int zonemodelid){
		for (List<ZoneApplyData> list : zonereadylist) {
			for (ZoneApplyData zoneApplyData : list) {
				if (zoneApplyData.getPlayerid() == pid && zoneApplyData.getZonemodelid() == zonemodelid) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	/**增加多人传送数据
	 * 
	 * @param teamid
	 * @param zoneTeamData
	 */
	public void addzoneteammap(long teamid,ZoneTeamData zoneTeamData){
		synchronized (zoneteammap) {
			zoneteammap.put(teamid, zoneTeamData);
		}
	}
	
	
	

	
	/**打开多人副本主面板
	 * 
	 * @param parameter
	 */
	public void stReqZoneTeamOpenToGameMessage(Player player) {
		ResZoneTeamShowToClientMessage cmsg = new ResZoneTeamShowToClientMessage();
		List<Q_clone_activityBean> actlist = ManagerPool.dataManager.q_clone_activityContainer.getList();
		for (Q_clone_activityBean q_clone_activityBean : actlist) {
			if (q_clone_activityBean.getQ_zone_type()==3) {
				ZoneTeamInfo info=new ZoneTeamInfo();
				int zoneModelid = q_clone_activityBean.getQ_id();
				long manual = ManagerPool.countManager.getCount(player, CountTypes.ZONE_MANUAL, ""+zoneModelid);
				long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelid);
				if (status == 0) {
					ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelid,1, 0, 0);
				}
				if (TimeUtil.isNowSatisfiedBy(q_clone_activityBean.getQ_open_time()))  {
					info.setIsopen((byte) 1);
				}
				info.setEnternum((byte) manual);
				info.setZoneid(zoneModelid);
				if (status > 2) {
					status = 2;
				}
				info.setClearancestatus((byte) status);
				cmsg.getZoneteaminfo().add(info);
			}
		}
		MessageUtil.tell_player_message(player,cmsg );
	}
	
	
	
	/**进入多人活动副本
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqZoneTeamEnterToGameMessage(Player player,ReqZoneTeamEnterToGameMessage msg) {
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(msg.getZoneid());
		Map map=ManagerPool.mapManager.getMap(player);
		if (zonedata == null) {
			return;
		}
		
		if (ManagerPool.zonesManager.getmServers().size() >= ManagerPool.zonesManager.getZONEMAX()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("副本系统繁忙中，请稍后再试"));
			return;
		}
		
		if (!TimeUtil.isNowSatisfiedBy(zonedata.getQ_open_time())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该副本只在指定时间开放，请届时参与。"));	
			return;
		}
		
		if (zonedata.getQ_min_lv() > player.getLevel()) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的等级低于{1}级，不能进入此副本"),zonedata.getQ_min_lv()+"");
			return ;
		}
	
		if (zonedata.getQ_max_lv() < player.getLevel()){
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的等级高于{1}级，不能进入此副本"),zonedata.getQ_max_lv()+"");
			return ;
		}
		
		
		if (map.isCopy() ==true) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您正处于副本中，请先退出本副本中再进行本操作"));
			return;
		}
		long manual = ManagerPool.countManager.getCount(player, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id());
		if (manual == 0) {	//初始化计数器
			ManagerPool.countManager.addCount(player, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id(),1, 0, 0);
		}
		int num = 0;
//		int vipid = VipManager.getInstance().getPlayerVipId(player);
//		if(vipid>0){
//			Q_vipBean vipdb = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
//			if (vipdb != null) {
//				num = vipdb.getQ_zone_time();
//			}
//		}
		if (manual >= zonedata.getQ_manual_num() + num) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您今日的参与次数已用完"));
			return;
		}
		
		if (!ManagerPool.mapManager.ischangMap(player)) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有脱离战斗状态或者其他原因导致不能进入副本。"));
			return ;
		}
		
		if (msg.getEntertype() == 0 && (zonedata.getQ_type() == 1 || zonedata.getQ_type() == 3)) {//单人进入
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
 			if (zone != null) {
 				List<Long> teamlist = new ArrayList<Long>();
 				List<Integer> levellist = new ArrayList<Integer>();	
 				teamlist.add(player.getId());
 				levellist.add(player.getLevel());
 				zone.getOthers().put("teamlist",teamlist);	//往副本写入当前所属人物id
 				zone.getOthers().put("levellist",levellist);	//往副本写入人物等级
				long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zonedata.getQ_id());
				if (status == 0) {//初始化计数器（记录是否参与）（通关后设置为2）
					ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zonedata.getQ_id(),1, 1, 0);
				}
				player.getZoneinfo().put(ZonesManager.ManualDeathnum+"_"+zonedata.getQ_id(), 0);
				player.getZoneinfo().put(ZonesManager.killmonsternum+"_"+zonedata.getQ_id(), 0);
				player.getZoneinfo().put(ZonesManager.Manualendtime+"_"+zonedata.getQ_id(), 0);
				
 				ManagerPool.countManager.addCount(player, CountTypes.ZONE_MANUAL, zonedata.getQ_id()+"", 1);	//现在改成手动和自动共用一个变量
				Position position = new Position();
				position.setX((short) (zonedata.getQ_x()*MapUtils.GRID_BORDER));
				position.setY((short) (zonedata.getQ_y()*MapUtils.GRID_BORDER));
				ManagerPool.mapManager.changeMap(player, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, position, this.getClass().getName() + ".stReqZoneTeamEnterToGameMessage");	
 			}


		}else if (msg.getEntertype() == 1 && zonedata.getQ_type() > 1) {//组队进入
			
			if (player.getTeamid() == 0) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("您没有组队，不能使用组队进入功能"));
				return;
			}
			TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
			if (team==null) {
				return;
			}

			if (team.getMemberinfo().size() < zonedata.getQ_min_num()) {
				if (zonedata.getQ_min_num() == zonedata.getQ_max_num()) {
					MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("需要{1}人组队才能进入本活动，队伍人数不足"),zonedata.getQ_min_num()+"");
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("需要{1}到{2}人组队才能进入本活动，队伍人数不足"),zonedata.getQ_min_num()+"",zonedata.getQ_max_num()+"");
				}
				return;
			}
			
			if (team.getMemberinfo().size() > zonedata.getQ_max_num()) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("队伍人数超过副本进入最大人数限制{1}"),zonedata.getQ_max_num()+"");
				return;
			}
			
			//先期设定为队长可发起，，以后要去掉这个检测
			if (team.getMemberinfo().get(0).getMemberid()!= player.getId()) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("只有队长才能发起组队进入"));
				return;
			}
			
			if (!ckTeamRequirement(player ,team,zonedata)) {
				return;
			}
			
//			ClearupData();
			
			if(zoneteammap.containsKey(player.getTeamid())){
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("本队伍已经有人发起组队传送,请稍后再试"));
				return;
			}
			int ms = (int) (System.currentTimeMillis()/1000);
			ZoneTeamData zoneTeamData = new ZoneTeamData();
			zoneTeamData.setInitiateplayerid(player.getId());
			zoneTeamData.setInitiatetime(ms);
			zoneTeamData.setZonemodelid(zonedata.getQ_id());
			
			for (TeamMemberInfo info : team.getMemberinfo()) {
				Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
				if (member != null ) {
					ResZoneTeamNoticeToClientMessage cmsg= new ResZoneTeamNoticeToClientMessage();
					cmsg.setLeaderid(player.getId());
					cmsg.setLeadername(player.getName());
					cmsg.setZoneid(msg.getZoneid());
					cmsg.setEntertime(ENTERTIME);
					cmsg.setWaittime(WAITTIME);
					MessageUtil.tell_player_message(member, cmsg);		//通知队员
					zoneTeamData.getMemberidmap().put(info.getMemberid(), 0);
				}
			}
			zoneTeamData.setSelectnum(1);
			zoneTeamData.setInitiatetime(ms+ENTERTIME);
			zoneTeamData.getMemberidmap().put(player.getId(), 2);
			addzoneteammap(player.getTeamid(), zoneTeamData);
			
			if (team.getMemberinfo().size() == 1) {
				zoneTeamData.setEntertime(ms+ENTERTIME);
				zoneTeamData.setWaittime(0);
			}
			
		}else if (msg.getEntertype() == 2 && zonedata.getQ_type() > 1) {//单人报名
			if (player.getZoneteamenterid() == 0) {
				if (player.getTeamid() > 0 && checkzoneteammatch(msg.getZoneid(),player.getTeamid())) {
					MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("您已参加组队报名，不能再进行单人报名"));
					return;
				}
				
				applymathematical(player,msg.getZoneid(),zonedata);
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("正在进行副本传送，暂时不能报名"));
			}
			
		}else if (msg.getEntertype() == 3 && zonedata.getQ_type() > 1) {//组队报名
			if (player.getTeamid() == 0) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("您没有组队，不能使用组队进入功能"));
				return;
			}
			TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
			if (team==null) {
				return;
			}
			for (TeamMemberInfo info : team.getMemberinfo()) {
				Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
				if (member != null) {
					if(checkzoneapplymap(msg.getZoneid(),info.getMemberid())){
						MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("队员{1}已经在单人报名中，不能组队报名"),member.getName());
						return;
					}
					Map mmap = ManagerPool.mapManager.getMap(member);
					if (mmap.isCopy()) {
						MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("队员{1}处于副本中，不能组队报名。"),member.getName());
						return;
					}
				}
			}
			
			if (team.getMemberinfo().size() < 2) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("需要{1}人组队才能进入本活动，队伍人数不足"),"4");
				return;
			}
						
			//先期设定为队长可发起，，以后要去掉这个检测
			if (team.getMemberinfo().get(0).getMemberid()!= player.getId()) {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("只有队长才能发起组队进入"));
				return;
			}
			
			if (!ckTeamRequirement(player ,team,zonedata)) {
				return;
			}
			
			if(checkzoneteammatch(zonedata.getQ_id(),player.getTeamid()) == false){
				int ms = (int) (System.currentTimeMillis()/1000);
				ZoneTeamData zoneTeamData = new ZoneTeamData();
				zoneTeamData.setInitiateplayerid(player.getId());
				zoneTeamData.setInitiatetime(ms);
				zoneTeamData.setZonemodelid(zonedata.getQ_id());
				zoneTeamData.setSelectnum(1);
				zoneTeamData.getMemberidmap().put(player.getId(), 2);
				zoneTeamData.setTeamid(player.getTeamid());
				zoneTeamData.setTeamsum(team.getMemberinfo().size());
				for (TeamMemberInfo info : team.getMemberinfo()) {
					Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
					if (member != null ) {
						ResZoneTeamNoticeToClientMessage cmsg= new ResZoneTeamNoticeToClientMessage();
						cmsg.setLeaderid(player.getId());
						cmsg.setLeadername(player.getName());
						cmsg.setZoneid(msg.getZoneid());
						cmsg.setEntertime(ENTERTIME);
						cmsg.setWaittime(WAITTIME);
						MessageUtil.tell_player_message(member, cmsg);		//通知队员
					}
				}
				addzoneteammatch(zonedata.getQ_id(),zoneTeamData);
			}
		}
	}
	
	
	/**检测是否在同个服务器,以及其他条件
	 * 
	 * @param map
	 * @return 
	 */
	public boolean ckTeamRequirement(Player player ,TeamInfo team ,Q_clone_activityBean zonedata){
		//Map map=ManagerPool.mapManager.getMap(player);
		Player tmpplayer = null;
		if(!zoneteammap.containsKey(player.getTeamid())){
			tmpplayer = player;
		}
		
		for (TeamMemberInfo info : team.getMemberinfo()) {	//检测地图线路
			Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
			if (member != null) {
//				if ( map.getLineId() != info.getMemberline() ||  map.getId() != info.getMembermaponlyid()) {
//					notify_team_player(tmpplayer,team,Notifys.ERROR, "{1}和您不在同个地图内，无法开启副本组队传送",info.getMembername());
//					return false;
//				}
				
				if (member.getCountry() != player.getCountry() ) {
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】不在同个服务器或者国家内，无法进入副本"),info.getMembername(),zonedata.getQ_min_lv()+"");
					return false;
				}
				
				Map membermap=ManagerPool.mapManager.getMap(member);
				if (membermap == null) {
					if (info.getMemberid() == player.getId()) {
						MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您不在地图中，请稍后操作"));
					}else {
						notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】不在地图中，因此无法组队进入副本"),info.getMembername());
					}
					return false;
				}
				
				if ( membermap.isCopy()) {
					if (info.getMemberid() == player.getId()) {
						MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您正处于副本中，请先退出本副本中再进行本操作"));
					}else {
						notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】正处于某个副本中，因此无法组队进入副本"),info.getMembername());
					}
					return false;
				}
				
				if (zonedata.getQ_min_lv() > member.getLevel()) {
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】的等级低于{2}级，不能进入此副本"),info.getMembername(),zonedata.getQ_min_lv()+"");
					return false;
				}
			
				if (zonedata.getQ_max_lv() < member.getLevel()){
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】的等级高于{2}级，不能进入此副本"),info.getMembername(),zonedata.getQ_max_lv()+"");
					return false;
				}
				
				if (!ManagerPool.mapManager.ischangMap(member)) {
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】没有脱离战斗状态或者其他原因导致不能进入副本。"),info.getMembername(),zonedata.getQ_max_lv()+"");
					return false;
				}
				
				if (member.isDie()) {
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】处于死亡状态，无法进入副本。"),info.getMembername());
					return false;
				}
				
	 			int num = 0;
//				int vipid = VipManager.getInstance().getPlayerVipId(member);
//				if(vipid>0){
//					Q_vipBean vipdb = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
//					if (vipdb != null) {
//						num = vipdb.getQ_zone_time();
//					}
//				}
				long manual = ManagerPool.countManager.getCount(member, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id());
				if (manual >= zonedata.getQ_manual_num() + num) {
					notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】今日参与次数已用完，因此无法组队进入副本"),info.getMembername());
					return false;
				}
			}else {
				notify_team_player(tmpplayer,team,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队友【{1}】可能不在本国，或者已经下线，因此无法组队进入副本"),info.getMembername());
				return false;
			}
		}

		return true;
	}
	
	
	
	/**对报名表进行处理
	 * 
	 * @param map
	 * @return 
	 */
	public void applymathematical(Player player ,int zonemodelid ,Q_clone_activityBean zonedata){
		long pid=0;
		if (player != null) {
			pid=player.getId();
		}
		
		if (checkzoneapplymap(zonemodelid ,pid ) == false) {	//检测是否已经报名
			if (player !=null) {
				ZoneApplyData playerzoneApplyData = new ZoneApplyData();
				playerzoneApplyData.setPlayerid(player.getId());	//添加报名
				playerzoneApplyData.setZonemodelid(zonemodelid);
				playerzoneApplyData.setTime((int) (System.currentTimeMillis()/1000));
				addzoneapplymap(zonemodelid,playerzoneApplyData);//加入名单
			}
		}
//		ArrayList<ZoneApplyData> arrayList = new ArrayList<ZoneApplyData>();
//		Vector<ZoneApplyData> vector = zoneapplymap.get(zonemodelid);
//		//对列表里的人实时检测，筛选
//		for (ZoneApplyData zoneApplyData : vector) {
//			Player onLinePlayer = ManagerPool.playerManager.getOnLinePlayer(zoneApplyData.getPlayerid());
//			if (onLinePlayer != null) {
//				Map membermap=ManagerPool.mapManager.getMap(onLinePlayer);
//				if (membermap == null || membermap.isCopy()) {
//					continue;
//				}
//				
//				if (zonedata.getQ_min_lv() > onLinePlayer.getLevel() && zonedata.getQ_max_lv() < onLinePlayer.getLevel()) {
//					continue;
//				}
//				
//				if (!ManagerPool.mapManager.ischangMap(onLinePlayer)) {
//					continue;
//				}
//				
//				if (onLinePlayer.isDie()) {
//					continue;
//				}
//				long manual = ManagerPool.countManager.getCount(onLinePlayer, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id());
//				if (manual >= zonedata.getQ_manual_num()) {
//					continue;
//				}
//				arrayList.add(zoneApplyData);
//			}
//		}
//		List<Player> playerlist = new ArrayList<Player>();
//		ZoneApplyDataInfo zoneApplyDataInfo = new ZoneApplyDataInfo();	//通知前端用
//		zoneApplyDataInfo.setZoneid(zonemodelid);
//		ResZoneApplyDataInfoToClientMessage cmsg = new ResZoneApplyDataInfoToClientMessage();
//		if (arrayList.size() >= zonedata.getQ_min_num()) {//筛选到合适的玩家数量，设置传送标记
//			List<ZoneApplyData> zoneApplyDatalist = new ArrayList<ZoneApplyData>();
//			for (int i = 0; i < zonedata.getQ_min_num(); i++) {
//				Player olPlayer = ManagerPool.playerManager.getOnLinePlayer(arrayList.get(i).getPlayerid());
//				zoneApplyDataInfo.getPlayerlvlist().add(olPlayer.getLevel());
//				zoneApplyDataInfo.getPlayernamelist().add(olPlayer.getName());
//				zoneApplyDatalist.add(arrayList.get(i));
//				playerlist.add(olPlayer);
//				arrayList.get(i).setTime((int) (System.currentTimeMillis()/1000));	//重设时间，这里表示传送倒计时等待时间
//				arrayList.get(i).setApplystatus(1);
//				arrayList.get(i).setZonemodelid(zonemodelid);
//			}
//			cmsg.setType(2);
//			addzonereadylist(zoneApplyDatalist);
//			zoneGrouping(playerlist,zonedata.getQ_map_group());//进行随机分组
//		}else {	//人数不足，只展示
//			for (int i = 0; i < arrayList.size(); i++) {	
//				Player olPlayer = ManagerPool.playerManager.getOnLinePlayer(arrayList.get(i).getPlayerid());
//				zoneApplyDataInfo.getPlayerlvlist().add(olPlayer.getLevel());
//				zoneApplyDataInfo.getPlayernamelist().add(olPlayer.getName());
//				arrayList.get(i).setZonemodelid(zonemodelid);
//				playerlist.add(olPlayer);
//			}
//			if (player !=null) {
//				ResZoneApplyDataInfoToClientMessage pmsg = new ResZoneApplyDataInfoToClientMessage();
//				pmsg.setZoneapplydatainfo(zoneApplyDataInfo);
//				MessageUtil.tell_player_message(player, pmsg);
//			}
//			cmsg.setType(1);
//		}
//		
//		if (playerlist.size()> 0) {
//			cmsg.setZoneapplydatainfo(zoneApplyDataInfo);
//			MessageUtil.tell_playerlist_message(playerlist,cmsg);//通知報名的玩家
//		}
		
	}
	
	
	
	/**单人报名，自动分组
	 * 
	 */
	public void autogroup(){
		try {
			synchronized (zoneapplymap) {
				List<Q_clone_activityBean> cloneactlist = ManagerPool.dataManager.q_clone_activityContainer.getList();
				for (Q_clone_activityBean q_clone_activityBean : cloneactlist) {
					if (q_clone_activityBean.getQ_map_group() > 1) { //报名副本
						int zonemodelid=q_clone_activityBean.getQ_id();
						if (zoneapplymap.containsKey(zonemodelid) ) {
							ArrayList<ZoneApplyData> arrayList = new ArrayList<ZoneApplyData>();
							Vector<ZoneApplyData> vector = zoneapplymap.get(zonemodelid);
							if (vector != null && !vector.isEmpty() ) {
								//对列表里的人实时检测，筛选
								for (ZoneApplyData zoneApplyData : vector) {
									if (zoneApplyData.getApplystatus() == 1) {//如果已经匹配成功
										continue;
									}
									Player onLinePlayer = ManagerPool.playerManager.getOnLinePlayer(zoneApplyData.getPlayerid());
									if (onLinePlayer != null) {
										Map membermap=ManagerPool.mapManager.getMap(onLinePlayer);
										if (membermap == null || membermap.isCopy()) {
											continue;
										}
										
										if (q_clone_activityBean.getQ_min_lv() > onLinePlayer.getLevel() && q_clone_activityBean.getQ_max_lv() < onLinePlayer.getLevel()) {
											continue;
										}
										
										if (!ManagerPool.mapManager.ischangMap(onLinePlayer)) {
											continue;
										}
										
										if (onLinePlayer.isDie()) {
											continue;
										}
										
										long manual = ManagerPool.countManager.getCount(onLinePlayer, CountTypes.ZONE_MANUAL, ""+q_clone_activityBean.getQ_id());
										if (manual >= q_clone_activityBean.getQ_manual_num()) {
											continue;
										}
										
										arrayList.add(zoneApplyData);
									}
								}
		
								List<Player> playerlist = new ArrayList<Player>();//玩家列表
								ZoneApplyDataInfo zoneApplyDataInfo = new ZoneApplyDataInfo();	//通知前端用
								zoneApplyDataInfo.setZoneid(zonemodelid);
								ResZoneApplyDataInfoToClientMessage cmsg = new ResZoneApplyDataInfoToClientMessage();
								if (arrayList.size() >= q_clone_activityBean.getQ_min_num()) {//筛选到合适的玩家数量，设置传送标记
									List<ZoneApplyData> zoneApplyDatalist = new ArrayList<ZoneApplyData>();
									for (int i = 0; i < q_clone_activityBean.getQ_min_num(); i++) {
										Player olPlayer = ManagerPool.playerManager.getOnLinePlayer(arrayList.get(i).getPlayerid());
										zoneApplyDataInfo.getPlayerlvlist().add(olPlayer.getLevel());
										zoneApplyDataInfo.getPlayernamelist().add(olPlayer.getName());
										zoneApplyDatalist.add(arrayList.get(i));
										playerlist.add(olPlayer);
										arrayList.get(i).setTime((int) (System.currentTimeMillis()/1000));	//重设时间，这里表示传送倒计时等待时间
										arrayList.get(i).setApplystatus(1);
										arrayList.get(i).setZonemodelid(zonemodelid);
									}
									cmsg.setType(2);
									addzonereadylist(zoneApplyDatalist);	//匹配完成后，进入传送等待列表
									zoneGrouping(playerlist,q_clone_activityBean.getQ_map_group());//进行随机分组
								}else {	//人数不足，只展示
									for (int i = 0; i < arrayList.size(); i++) {	
										Player olPlayer = ManagerPool.playerManager.getOnLinePlayer(arrayList.get(i).getPlayerid());
										zoneApplyDataInfo.getPlayerlvlist().add(olPlayer.getLevel());
										zoneApplyDataInfo.getPlayernamelist().add(olPlayer.getName());
										arrayList.get(i).setZonemodelid(zonemodelid);
										playerlist.add(olPlayer);
									}
									cmsg.setType(1);
								}
								
								if (playerlist.size()> 0) {
									cmsg.setZoneapplydatainfo(zoneApplyDataInfo);
									MessageUtil.tell_playerlist_message(playerlist,cmsg);//通知報名的玩家
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {
			log.error("单人匹配错误："+e,e);
		}
	}

	
	
	/**进副本前进行分组
	 * 
	 */
	public void zoneGrouping(List<Player> list,int group){
		if (list.size() > 0) {
			for (int i = 0; i < 10; i++) {	//打乱顺序
				int rnd = RandomUtils.random(1,list.size()) - 1;
				Player player = list.remove(rnd);
				list.add(player);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			Player player = list.get(i);
			player.setGroupmark((i % group) +1);
		}
	}
	
	
	
	
	
	
	
	
	public void notify_team_player(Player tmpplayer,TeamInfo team, Notifys type, String message, String... values) {
		if (tmpplayer == null) {
			for (TeamMemberInfo info : team.getMemberinfo()) {	
				Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
				if (member != null) {
					MessageUtil.notify_player(member,type, message,values);
				}	
			}
		}else {
			MessageUtil.notify_player(tmpplayer,type, message,values);
		}
	}
	
	
	
	
	
	
	

	/**组队多人队员选择消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqZoneTeamSelectToGameMessage(Player player,ReqZoneTeamSelectToGameMessage msg) {
		long teamid=player.getTeamid();
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(msg.getZoneid());
		if (teamid > 0) {
			if (zonedata == null ) {
				return;
			}
			if (zonedata.getQ_map_group() > 1) {
				ZoneTeamData zoneteamdata = getmatchZoneTeamData(msg.getZoneid(),teamid);
				if (zoneteamdata != null) {
					zoneteamdata.getMemberidmap().put(player.getId(), (int)msg.getSelect());
					TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
					ResZoneTeamNoticeSelectToClientMessage cmsg =new ResZoneTeamNoticeSelectToClientMessage();
					cmsg.setZoneid(msg.getZoneid());
					cmsg.setSelect(msg.getSelect());
					cmsg.setMemberid(player.getId());
					tell_team_message(team,cmsg);
					zoneteamdata.setSelectnum(zoneteamdata.getSelectnum()+1);
					int size = zoneteamdata.getMemberidmap().size();
					if (team.getMemberinfo().size() == size && size == zoneteamdata.getSelectnum()) {
						boolean is =true;
						for (int select : zoneteamdata.getMemberidmap().values()) {
							if (select != 2) {
								is = false;
								break;
							}
						}
						
						if (is == false) { 	//条件未达到，停止传送,删除传送信息
							zoneteamdata.setInitiatetime(0);
						}else {
							checkzoneteammatch(msg.getZoneid(), teamid);
							//teamzoneGroup(player.getTeamid() ,msg.getZoneid());
						}
					}
				}

			}else {
				if(zoneteammap.containsKey(teamid)){
					ZoneTeamData zoneteamdata = zoneteammap.get(teamid);
					zoneteamdata.getMemberidmap().put(player.getId(), (int)msg.getSelect());
					TeamInfo team = ManagerPool.teamManager.getTeam(player.getTeamid());
					ResZoneTeamNoticeSelectToClientMessage cmsg =new ResZoneTeamNoticeSelectToClientMessage();
					cmsg.setSelect(msg.getSelect());
					cmsg.setMemberid(player.getId());
					cmsg.setZoneid(msg.getZoneid());
					tell_team_message(team,cmsg);
					zoneteamdata.setSelectnum(zoneteamdata.getSelectnum()+1);
					int size = zoneteamdata.getMemberidmap().size();
					if (team.getMemberinfo().size() == size && size == zoneteamdata.getSelectnum()) {
						boolean is =true;
						for (int select : zoneteamdata.getMemberidmap().values()) {
							if (select != 2) {
								is = false;
								break;
							}
						}
						
						if (is == false) { 	//条件未达到，停止传送,删除传送信息
							zoneteamdata.setInitiatetime(0);
						}else {
							int ms = (int) (System.currentTimeMillis()/1000);
							zoneteamdata.setEntertime(ms+ENTERTIME);
							zoneteamdata.setWaittime(0);
						}
					}
				}
			}
		}
	}
	
	
	//-------------------------------------------多组队进副本
	/**自动多个队伍匹配
	 * 
	 */
	public void autoteamzoneGroup(){
		try {
			synchronized (zoneteammatch) {
				List<Q_clone_activityBean> cloneactlist = ManagerPool.dataManager.q_clone_activityContainer.getList();
				for (Q_clone_activityBean zonedata : cloneactlist) {
					if (zonedata.getQ_map_group() > 1) { //报名副本
						int zonemodelid=zonedata.getQ_id();
						Vector<ZoneTeamData> vector = zoneteammatch.get(zonemodelid);
						if (vector != null && !vector.isEmpty()) {
							ArrayList<ZoneTeamData> arrayList = new ArrayList<ZoneTeamData>();
							for (ZoneTeamData zoneTeamData : vector) {//对列表里的队伍实时检测，筛选
								boolean is = true;
								TeamInfo teamInfo = ManagerPool.teamManager.getTeam(zoneTeamData.getTeamid());
								if (teamInfo != null ) {
									if (zoneTeamData.getSelectnum() != zoneTeamData.getTeamsum() || zoneTeamData.getMemberidmap().size() != teamInfo.getMemberinfo().size()) {
										is = false;
									}else {
										for (long pid : zoneTeamData.getMemberidmap().keySet()) {
											Player onLinePlayer = ManagerPool.playerManager.getOnLinePlayer(pid);
											if (onLinePlayer != null) {
												Map membermap=ManagerPool.mapManager.getMap(onLinePlayer);
												if (membermap == null || membermap.isCopy()) {
													is = false;
												}
											
												if (zonedata.getQ_min_lv() > onLinePlayer.getLevel() && zonedata.getQ_max_lv() < onLinePlayer.getLevel()) {
													is = false;
												}
											
												if (!ManagerPool.mapManager.ischangMap(onLinePlayer)) {
													is = false;
												}
											
												if (onLinePlayer.isDie()) {
													is = false;
												}
											
												long manual = ManagerPool.countManager.getCount(onLinePlayer, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id());
												if (manual >= zonedata.getQ_manual_num()) {
													is = false;
												}
											}
										}
									}
								}else {
									is = false;
								}
							
								if (is) {
									arrayList.add(zoneTeamData);
								}
							}
						
							if (arrayList.size() >= zonedata.getQ_map_group()) {	//筛选到合适的玩家数量，设置传送标记
								List<Player> players = new ArrayList<Player>();		
								ZoneApplyDataInfo zoneApplyDataInfo = new ZoneApplyDataInfo();	//通知前端用
								zoneApplyDataInfo.setZoneid(zonemodelid);
								ResZoneApplyDataInfoToClientMessage cmsg = new ResZoneApplyDataInfoToClientMessage();
								List<ZoneApplyData> zoneApplyDatas = new ArrayList<ZoneApplyData>();
								for (int i = 0; i < zonedata.getQ_map_group(); i++) {
									for (Long id : arrayList.get(i).getMemberidmap().keySet()) {
										Player onLinePlayer = ManagerPool.playerManager.getOnLinePlayer(id);
										if (onLinePlayer != null) {
											zoneApplyDataInfo.getPlayerlvlist().add(onLinePlayer.getLevel());
											zoneApplyDataInfo.getPlayernamelist().add(onLinePlayer.getName());
											if (arrayList.get(i).getTeamid() == onLinePlayer.getTeamid() ) {
												onLinePlayer.setGroupmark(i+1);	//设置分组
											}
											players.add(onLinePlayer);
											ZoneApplyData appdata = new ZoneApplyData();
											appdata.setApplystatus(1);
											appdata.setPlayerid(id);
											appdata.setTime((int) (System.currentTimeMillis()/1000));
											appdata.setZonemodelid(zonemodelid);
											appdata.setTeamid(arrayList.get(i).getTeamid());
											zoneApplyDatas.add(appdata);
										}
									}
									arrayList.get(i).setTeamid(0);//标记队伍ID为0，删除用
								}
								cmsg.setType(2);
								addzonereadylist(zoneApplyDatas);	//匹配完成后，进入传送等待列表
								cmsg.setZoneapplydatainfo(zoneApplyDataInfo);
								MessageUtil.tell_playerlist_message(players,cmsg);//通知報名的玩家
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("多组队匹配错误："+e,e);
		}
	}
	
	
	
	
	/**队伍群发消息
	 * 
	 * @param team
	 * @param message
	 */
	public void tell_team_message(TeamInfo team,Message message) {
		for (TeamMemberInfo info : team.getMemberinfo()) {
			message.getRoleId().clear();
			Player member = ManagerPool.playerManager.getOnLinePlayer(info.getMemberid());
			MessageUtil.tell_player_message(member, message);
		}
	}


	/**组队多人副本(队伍ID，队伍信息)
	 * 
	 * @return
	 */
	public static ConcurrentHashMap<Long, ZoneTeamData> getZoneteammap() {
		return zoneteammap;
	}

	public static void setZoneteammap(ConcurrentHashMap<Long, ZoneTeamData> zoneteammap) {
		ZonesTeamManager.zoneteammap = zoneteammap;
	}



	public static Vector<List<ZoneApplyData>> getZonereadylist() {
		return zonereadylist;
	}

	public static void setZonereadylist(Vector<List<ZoneApplyData>> zonereadylist) {
		ZonesTeamManager.zonereadylist = zonereadylist;
	}

	
	
	/**取消报名
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqZoneCancelSignupToGame(Player player,ReqZoneCancelSignupToGameMessage msg) {
		if (msg.getType() == 1) {//取消单人报名
			synchronized (zoneapplymap) {
				Vector<ZoneApplyData> data = zoneapplymap.get(msg.getZoneid());
				if (data == null) {
					return;
				}
				Iterator<ZoneApplyData> it = data.iterator();
				while (it.hasNext()) {
					ZoneApplyData zoneApplyData = (ZoneApplyData) it.next();
					if (zoneApplyData.getApplystatus() == 0 && zoneApplyData.getPlayerid() == player.getId() ) {
						zoneApplyData.setApplystatus(1);	//设置为已经匹配完成，将会被删除
					}
				}
				
				Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(msg.getZoneid());
				checkzoneapplymap(msg.getZoneid() , player.getId());//检测并删除不符合的对象
				MessageUtil.notify_player(player,Notifys.CHAT_ROLE, ResManager.getInstance().getString("您取消了进入【{1}】的报名"), zonedata.getQ_duplicate_name());
			}
		}else {//取消队伍报名
			synchronized (zoneteammatch) {
				if (zoneteammatch.containsKey(msg.getZoneid())) {
					Iterator<ZoneTeamData> it = zoneteammatch.get(msg.getZoneid()).iterator();
					while (it.hasNext()) {
						ZoneTeamData zoneTeamData = (ZoneTeamData) it.next();
						if (player.getTeamid() > 0 && zoneTeamData.getTeamid() == player.getTeamid()) {
							zoneTeamData.setTeamid(0);//设置队伍ID为0，将会被删除
							break;
						}
					}
					
					//通知所有队员
					TeamInfo teamInfo = ManagerPool.teamManager.getTeam(player.getTeamid());
					if (teamInfo != null) {
						Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(msg.getZoneid());
						for (TeamMemberInfo teamMemberInfo : teamInfo.getMemberinfo()) {
							Player mp = ManagerPool.playerManager.getOnLinePlayer(teamMemberInfo.getMemberid());
							if (mp != null) {
								MessageUtil.notify_player(mp,Notifys.CHAT_ROLE, ResManager.getInstance().getString("队员{1}取消了进入【{2}】的报名"), player.getName(),zonedata.getQ_duplicate_name());
								ResZoneApplyDataInfoToClientMessage pmsg = new ResZoneApplyDataInfoToClientMessage();
								pmsg.setZoneapplydatainfo(new ZoneApplyDataInfo());
								MessageUtil.tell_player_message(mp, pmsg);
							}
						}
					}
				}
			}
		}
	}

	public static ConcurrentHashMap<Integer, Vector<ZoneTeamData>> getZoneteammatch() {
		return zoneteammatch;
	}

	public static void setZoneteammatch(
			ConcurrentHashMap<Integer, Vector<ZoneTeamData>> zoneteammatch) {
		ZonesTeamManager.zoneteammatch = zoneteammatch;
	}

	
	/**队伍变更后移除队伍报名
	 * 
	 * @param teamid
	 */
	public void removeteamapply(long teamid){
		synchronized (zoneteammatch) {
			if(!zoneteammatch.isEmpty()){
				Iterator<Entry<Integer, Vector<ZoneTeamData>>> it = getZoneteammatch().entrySet().iterator();
				while (it.hasNext()) {
					 Entry<Integer, Vector<ZoneTeamData>> vector = it.next();
					 Iterator<ZoneTeamData> zit = vector.getValue().iterator();
					 while (zit.hasNext()) {
						 ZoneTeamData zoneTeamData = zit.next();
						 if (zoneTeamData.getTeamid() == teamid) {
							 zoneTeamData.setTeamid(0);
							 break;
						}
					}
				}
			}
		}
	}
	
	

	
}
