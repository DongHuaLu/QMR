package com.game.team.manager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.game.cooldown.structs.CooldownTypes;
import com.game.guild.bean.GuildInfo;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.server.config.GameConfig;
import com.game.server.impl.WServer;
import com.game.team.bean.TeamInfo;
import com.game.team.bean.TeamMemberInfo;
import com.game.team.message.ReqApplyWorldMessage;
import com.game.team.message.ReqApplyWorldSelectMessage;
import com.game.team.message.ReqAppointWorldMessage;
import com.game.team.message.ReqAppointWorldSelectMessage;
import com.game.team.message.ReqAutoIntoTeamApplyWorldMessage;
import com.game.team.message.ReqAutoTeaminvitedWorldMessage;
import com.game.team.message.ReqCreateateamWorldMessage;
import com.game.team.message.ReqGenericSearchToGameMessage;
import com.game.team.message.ReqGenericSearchToWorldMessage;
import com.game.team.message.ReqIntoTeamToGameMessage;
import com.game.team.message.ReqIntoTeamToWorldMessage;
import com.game.team.message.ReqInviteWorldMessage;
import com.game.team.message.ReqInviteWorldSelectMessage;
import com.game.team.message.ReqMapSearchMemberNameGameMessage;
import com.game.team.message.ReqMapSearchMemberNameWorldMessage;
import com.game.team.message.ReqMapSearchPlayerInfoGameMessage;
import com.game.team.message.ReqMapSearchPlayerInfoWorldMessage;
import com.game.team.message.ReqMapSearchTeamInfoGameMessage;
import com.game.team.message.ReqMapSearchTeamInfoWorldMessage;
import com.game.team.message.ReqTeamMessageWorldMessage;
import com.game.team.message.ReqToleaveWorldMessage;
import com.game.team.message.ReqUpdateTeaminfoWorldMessage;
import com.game.team.message.ResTeamExteriorClientMessage;
import com.game.team.message.ResTeamSetToClientMessage;
import com.game.team.message.ResTeamchangeToGameMessage;
import com.game.team.message.ResTeammateMoveToClientMessage;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.zones.manager.ZonesTeamManager;

public class TeamManager {
	private static Object obj = new Object();
	// 管理类实例
	private static TeamManager manager;

	private TeamManager() {
	}

	public static TeamManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new TeamManager();
			}
		}
		return manager;
	}
	
	/**
	 * 队伍列表 (队伍ID做索引)
	 */
	private static  ConcurrentHashMap<Long, TeamInfo> teamlist = new ConcurrentHashMap<Long, TeamInfo>();
	
	
	/**传入队伍ID，得到队伍详细列表
	 * 
	 * @param tid
	 * @return
	 */
	public TeamInfo getTeam(long tid) {
		if(tid > 0) {
			TeamInfo teaminfo = teamlist.get(tid);
			if(teaminfo != null) {
				return teaminfo;
			}
		}
		return null;
	}
	
	
	/**玩家是否死亡
	 * 
	 * @param pid
	 * @return
	 */
	public boolean playerIsDie(long pid) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(pid);
		if(player!= null && PlayerState.DIE.compare(player.getState())){
			return true;
		}
		return false;
	}
	
	
	
	
	/**
	 * 创建队伍，转发到世界服务器
	 * 
	 * @param player
	 */
	public void stCreateateam(Player player) {
		long tid = player.getTeamid();
		if (playerIsDie(player.getId()) ){
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您处于死亡状态，不能创建队伍。"));
			return;
		} 
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，组队功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		if (tid == 0) {
			ReqCreateateamWorldMessage msg = new ReqCreateateamWorldMessage();
			msg.setPlayerid(player.getId());
			MessageUtil.send_to_world( msg);
		}
	}

	
	//---------------------申请组队----------------------
	
	
	/**
	 * 玩家申请入队,转发到世界服务器
	 * 
	 */
	public void stReqApplyGame(Player player, long tid) {
		if (playerIsDie(player.getId()) ){
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您处于死亡状态，不能申请入队。"));
			return;
		}
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，组队功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		if (ZonesTeamManager.getZoneteammap().containsKey(tid)) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该队伍正在进入副本倒计时，您当前无法加入该队伍，请稍后再试。"));
			return;
		}
		
		if (player.getTeamid() == 0) {
			ReqApplyWorldMessage msg = new ReqApplyWorldMessage();
			msg.setTeamid(tid);
			msg.setPlayerid(player.getId());
			MessageUtil.send_to_world(msg);
		}

	}
	/**
	 * 队长选择是否允许加入,转发到世界服务器
	 * 
	 * @param tid  队伍ID
	 * @param newmemberid新加入队员ID
	 * @param select 0同意，1拒绝
	 */
	public void stReqApplyGameSelect(long tid, long newmemberid, byte select) {
		ReqApplyWorldSelectMessage msg = new ReqApplyWorldSelectMessage();
		msg.setPlayerid(newmemberid);
		msg.setSelect(select);
		msg.setTeamid(tid);
		MessageUtil.send_to_world(msg);
	}
	
	
	
	//---------------------邀请组队------------------------
	
	
	/**发起 邀请组队,转发到世界服务器
	 * 
	 * @param player
	 * @param tid
	 * @param pid
	 */
	public void stReqInviteGame(Player player ,long tid,long pid) {
		if (playerIsDie(pid) ) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方处于死亡状态，无法对其发起组队邀请。"));
			return;
		}
		
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，组队功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}

		if (ZonesTeamManager.getZoneteammap().containsKey(tid)) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队伍正在进入副本倒计时，您无法邀请入队，请稍后再试。"));
			return;
		}
		
		ReqInviteWorldMessage msg = new ReqInviteWorldMessage();
		msg.setTeamid(tid);
		msg.setPlayerid(pid);
		msg.setCaptainid(player.getId());	//邀请人
		MessageUtil.send_to_world(msg);
	}
	
	
	
	/**
	 * 邀请组队--玩家选择,转发到世界服务器
	 * @param player
	 * @param tid
	 * @param cid   邀请人
	 * @param select
	 */

	public void stReqInviteGameSelect(Player player ,long tid, long cid,byte select) {
		ReqInviteWorldSelectMessage msg = new ReqInviteWorldSelectMessage();
		msg.setTeamid(tid);
		msg.setSelect(select);
		msg.setPlayerid(player.getId());
		msg.setCaptainid(cid);
		MessageUtil.send_to_world(msg);
	}
	
	
	//----------------玩家离队----------------
	//0开除 ，1 自己离开 ，2下线
	/**玩家发起离队请求
	 * 
	 * @param player
	 * @param pid （离队的玩家ID）
	 * @param type （这里是1和0）
	 */
	public void stLeavetheteam(Player player , long pid, byte type){
		if(player.getTeamid() > 0) {
			if (type == 0 || type == 1) {
				if (ZonesTeamManager.getZoneteammap().containsKey(player.getTeamid())) {
					MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队伍正在进入副本倒计时，无法离队，请稍后再试。"));
					return;
				}
			}
			
			ReqToleaveWorldMessage msg = new ReqToleaveWorldMessage();
			msg.setType(type);
			msg.setPlayerid(pid);
			msg.setTeamid(player.getTeamid());
			MessageUtil.send_to_world(msg);
			if (type == 2) {		//离线前改全体攻击
				if (player.getPkState() == 1) {	
					ManagerPool.playerManager.changePkState(player, 3, 0);
				}
			}
		}
	}
	
	/**世界服务器通知GAME服务器玩家离队成功
	 * 
	 * @param tid
	 * @param pid
	 */
	public void stResToleave(long tid,long pid) {
		Player player =  ManagerPool.playerManager.getOnLinePlayer(pid);
		if(player != null) {
			player.setTeamid(0);
			ResTeamExteriorClientMessage msg = new ResTeamExteriorClientMessage();
			msg.setTeamid(0);
			msg.setPlayerid(pid);
			MessageUtil.tell_round_message(player ,msg);// 外观消息给周围同组玩家（通过这个消息，前端清除组队消息）
			//重新计算组队增加的属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.TEAM);
			if (player.getPkState() == 1) {
				ManagerPool.playerManager.changePkState(player, 3, 0);
			}
		}
		//移除组队报名
		ManagerPool.zonesTeamManager.removeteamapply(tid);
	}
	
	
	
	//-------------------任命新队长--------------------------
	
	/**发起任命新队长，转发给世界服务器
	 * 
	 * @param tid
	 * @param pid
	 */
	public void stReqAppoint(Player player, long tid , long pid) {
		
		if (ZonesTeamManager.getZoneteammap().containsKey(tid)) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队伍正在进入副本倒计时，您无法更换队长，请稍后再试。"));
			return;
		}
		
		ReqAppointWorldMessage msg = new ReqAppointWorldMessage();
		msg.setPlayerid(pid);
		msg.setTeamid(tid);
		MessageUtil.send_to_world(msg);	
	}
		
	
	/**任命新队长，玩家选择后-转发到世界服务器
	 * 
	 * @param player
	 * @param tid
	 * @param select
	 */
	public void stReqAppointSelect(Player player ,long tid,byte select) {
		if (ZonesTeamManager.getZoneteammap().containsKey(tid)) {
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，队伍正在进入副本倒计时，无法更换队长，请稍后再试。"));
			return;
		}
		
		ReqAppointWorldSelectMessage msg = new ReqAppointWorldSelectMessage();
		msg.setPlayerid(player.getId());
		msg.setTeamid(tid);
		msg.setSelect(select);
		MessageUtil.send_to_world(msg);	
	}
	
	//--------------------------------------------------------
	
	/**，前端请求队伍信息，转发给世界服务器
	 * 
	 * @param tid
	 */
	public void  clientreqteaminfo(Player player,long tid){
		if ( tid == 0) {
			if (player.getTeamid() > 0) {
				tid = player.getTeamid();
			}
		}

		ReqUpdateTeaminfoWorldMessage msg = new ReqUpdateTeaminfoWorldMessage();
		msg.setPlayerid(player.getId());
		msg.setTeamid(tid);
		msg.setType((byte) 2);//标记，更新队伍信息到前端
		MessageUtil.send_to_world(msg);	
		
		ResTeamSetToClientMessage cmsg = new ResTeamSetToClientMessage();
		cmsg.setAutoIntoteamapply(player.getAutoIntoteamapply());
		cmsg.setAutoTeaminvited(player.getAutoteaminvited());
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	

	/**获取队伍信息到每个队员,并设置玩家队伍ID（群发）
	 * 
	 * @param tid
	 * @param memberinfos
	 * @param type  0更新前端外观并设置玩家队伍ID ， 1 更新组队信息到GAME（暂时无用）
	 */
	public void getupdateteaminfo(long tid ,List<TeamMemberInfo> memberinfos,byte type) {
		if(type == 0) {
			if (memberinfos != null) {
				//---------------更新组队缓存---------------------
				if (teamlist.containsKey(tid)) {
					if (memberinfos.size() == 0) {
						teamlist.remove(tid);
					}else {
						teamlist.get(tid).setMemberinfo(memberinfos);
					}
				}else {
					TeamInfo teamInfo = new TeamInfo();
					teamInfo.setTeamid(tid);
					teamInfo.setMemberinfo(memberinfos);
					teamlist.put(tid, teamInfo);
				}

			}else {
				if (teamlist.containsKey(tid)) {
					teamlist.remove(tid);
				}
			}
		}else if (type == 1) {//这里可以让某个玩家保存某个组队信息（暂时无用）
			
			
			
			
		}
	}
	

	
	/**自动接受入队申请
	 * 
	 */
	public void AutoIntoTeamApply(Player player,byte select) {
		ReqAutoIntoTeamApplyWorldMessage msg = new ReqAutoIntoTeamApplyWorldMessage();
		msg.setAutointoteamapply(select);
		msg.setPlayerid(player.getId());
		MessageUtil.send_to_world(msg);	
		player.setAutoIntoteamapply(select);
	}
	
	/**自动组队邀请消息
	 * 
	 */
	public void AutoTeaminvited(Player player,byte select) {
		ReqAutoTeaminvitedWorldMessage msg = new ReqAutoTeaminvitedWorldMessage();
		msg.setAutoTeaminvited(select);
		msg.setPlayerid(player.getId());
		MessageUtil.send_to_world(msg);	
		player.setAutoteaminvited(select);
	}
	
	
	
	
	/**
	 * 获取玩家所在地图组队成员
	 * @param player
	 * @return
	 */
	public List<Player> getMapSameTeam(Player player){
		Map map = ManagerPool.mapManager.getMap(player);
		List<Player> sameteam = new ArrayList<Player>();
		if (map != null) {
			Iterator<Entry<Long, Player>> it = map.getPlayers().entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Player> players =  it.next();
				Player member = players.getValue();
				if(member.getTeamid() > 0 && member.getTeamid() == player.getTeamid()) {
					sameteam.add(member);
				}
			}
			Collections.sort(sameteam);//进行排序
		}
		return sameteam;
	}
	
	/**
	 * 获取玩家所在副本地图组队成员
	 * @param player
	 * @return
	 */
	public List<Player> getZoneMapSameTeam(Player player){
		List<Player> sameteam = new ArrayList<Player>();
		TeamInfo team = TeamManager.getInstance().getTeam(player.getTeamid());
		Map map = MapManager.getInstance().getMap(player);
		if (team != null && map != null && map.isCopy()) {
			Iterator<TeamMemberInfo> iterator = team.getMemberinfo().iterator();
			while(iterator.hasNext()) {
				TeamMemberInfo teamMemberInfo =  iterator.next();
				if (teamMemberInfo != null) {
					Player curPlayer = PlayerManager.getInstance().getOnLinePlayer(teamMemberInfo.getMemberid());
					if (curPlayer != null) {
						Map curMap = MapManager.getInstance().getMap(curPlayer);
						if (curMap != null && curMap.isCopy() && curMap.getZoneId() == map.getZoneId()) {
							sameteam.add(curPlayer);
						}
					}
				}
			}
			Collections.sort(sameteam);//进行排序
		}
		return sameteam;
	}

	
	/**
	 * 得到队伍平均等级
	 * @param List<Player>
	 * @return
	 */
	public int getTeamAVG(List<Player> members) {
		int num = 0;
		for ( Player member : members) {
			num = num + member.getLevel();
		}
		return num / members.size();
	}

	/**
	 * 搜索本地图玩家信息
	 * @param msg
	 */
	public void stMapSearchPlayerInfo(Player player, ReqMapSearchPlayerInfoGameMessage msg) {
		if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.TEAM_SHARCH,null) == false) {
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.TEAM_SHARCH,null,Global.TEAM_SEARCH_TIME);
		}else {
			return;
		}
		
		ReqMapSearchPlayerInfoWorldMessage wmsg = new ReqMapSearchPlayerInfoWorldMessage();
		wmsg.setSearchcontent(msg.getSearchcontent());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);	
		
	}

	
	/**
	 * 搜索本地图队伍信息
	 * @param msg
	 */
	public void stMapSearchTeamInfo(Player player,ReqMapSearchTeamInfoGameMessage msg) {
		if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.TEAM_SHARCH,null) == false) {
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.TEAM_SHARCH,null,Global.TEAM_SEARCH_TIME);
		}else {
			return;
		}
		ReqMapSearchTeamInfoWorldMessage wmsg = new ReqMapSearchTeamInfoWorldMessage();
		wmsg.setSearchcontent(msg.getSearchcontent());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);	
	}

	
	/**获取队伍全部成员名字
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqMapSearchMemberNameGameMessage(Player player,ReqMapSearchMemberNameGameMessage msg) {
		ReqMapSearchMemberNameWorldMessage wmsg = new ReqMapSearchMemberNameWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setTeamid(msg.getTeamid());
		MessageUtil.send_to_world(wmsg);	
	}
	
	
	/**给队伍全部成员的文字通知信息
	 * 
	 */
	public void notify_team_all(long tid ,String cont){
		ReqTeamMessageWorldMessage wmsg = new ReqTeamMessageWorldMessage();
		wmsg.setTeamid(tid);
		wmsg.setContent(cont);
		MessageUtil.send_to_world(wmsg);
	}
	
	/**队友死亡全队提示
	 * 
	 * @param player
	 * @param attacker
	 */
	public void TeammateDieNotify(Player player,Player attacker){
		//本队成员:[XXXXX],在[XXX地图名]X线坐标:XX,XX被【X国】[XXX帮会][XXXX]玩家击败"
		int mapid = player.getMapModelId();
		int line = player.getLine();
		short x = (short) (player.getPosition().getX()/MapUtils.GRID_BORDER);
		short y = (short) (player.getPosition().getY()/MapUtils.GRID_BORDER);
		String gname = "";
		GuildInfo guild = attacker.getGuildInfo();
		if (guild != null && attacker.getGuildId() != 0 ) {
			gname = "『"+guild.getGuildName()+ResManager.getInstance().getString("帮会』");
		}
		
		GameConfig config = new GameConfig();
		String cname = config.getCountryNameByConutry(attacker.getCountry());
		ParseUtil par = new ParseUtil();
		par.setValue(ResManager.getInstance().getString("本队成员:『{@}』在『{@}』被『")+cname+"』"+gname+ResManager.getInstance().getString("『{@}』击败。"), new ParseUtil.PlayerParm(player.getId(), player.getName()),new ParseUtil.MapParm(mapid,line,x,y),new ParseUtil.PlayerParm(attacker.getId(), attacker.getName()));
		notify_team_all(player.getTeamid() , par.toString());
	}
	

	
	/**通用搜索玩家
	 * 
	 * @param player
	 * @param msg
	 */

	public void stReqGenericSearchToGameMessage(Player player,ReqGenericSearchToGameMessage msg) {
		if (msg.getSearchcontent() != null && msg.getSearchcontent().length() >= 1) {
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.TEAM_SHARCH,null) == false) {
				ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.TEAM_SHARCH,null,Global.TEAM_SEARCH_TIME);
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("您的搜索速度太快了，请稍后再试。"));
				return;
			}
		}else {
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.TEAM_OPEN_SHARCH,null) == false) {
				ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.TEAM_OPEN_SHARCH,null,Global.TEAM_SEARCH_TIME);
			}else {
				return;
			}
		}	
		
		ReqGenericSearchToWorldMessage wmsg = new ReqGenericSearchToWorldMessage();
		wmsg.setSearchcontent(msg.getSearchcontent());
		wmsg.setPlayerid(player.getId());
		wmsg.setType(msg.getType());
		wmsg.setPaneltype(msg.getPaneltype());
		wmsg.setIndexend(msg.getIndexend());
		wmsg.setIndexstart(msg.getIndexstart());
		wmsg.setSort(msg.getSort());
		MessageUtil.send_to_world(wmsg);		
		
	}
	
	
	/**在小地图实时显示队友位置，（移动触发)
	 * 
	 */
	public void showMapTeamMember(Player player){
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null) {
			long pid = player.getId();
			long teamid = player.getTeamid();
			ResTeammateMoveToClientMessage msg = new ResTeammateMoveToClientMessage();
			msg.setMemberid(pid);
			msg.setMx(player.getPosition().getX());
			msg.setMy(player.getPosition().getY());
			msg.setType((byte) 0);
			List<Player> players = getMapSameTeam(player);	//获取所在地图队员
			for (Player xplayer : players) {
				if (xplayer.getTeamid() > 0 && xplayer.getId() != pid &&xplayer.getTeamid() == teamid) {
					MessageUtil.tell_player_message(xplayer, msg);	
				}
			}
		}
	}
	
	
	/**组队时显示队员初始坐标
	 * @param player
	 * @param memberinfos
	 */
	public void showMapTeamMemberToteaminfo(Player player,List<TeamMemberInfo> memberinfos) {
		ResTeammateMoveToClientMessage msg = new ResTeammateMoveToClientMessage();
		msg.setMemberid(player.getId());
		msg.setMx(player.getPosition().getX());
		msg.setMy(player.getPosition().getY());
		msg.setType((byte) 1);
		for (TeamMemberInfo teamMemberInfo : memberinfos) {
			Player member =  ManagerPool.playerManager.getOnLinePlayer(teamMemberInfo.getMemberid());
			if (member != null) {
				if (member.getId() != player.getId()  && member.getLine() == player.getLine() && member.getMap() == player.getMap()) {
					MessageUtil.tell_player_message(member, msg);	
				}
			}
		}
	}
	
	
	/**检测player和另外一个玩家ID是否在同个队伍
	 * 
	 * @param player
	 * @param pid
	 * @return
	 */
	public boolean  isSameTeam(Player player , long pid){
		long tid = player.getTeamid();
		if (tid  > 0) {
			TeamInfo team = getTeam(tid);
			if (team != null ) {
				TeamMemberInfo[] teamMember = team.getMemberinfo().toArray(new TeamMemberInfo[0]);
				for (TeamMemberInfo teaminfo : teamMember) {
					if (teaminfo.getMemberid() == pid) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void stReqIntoTeamToGameMessage(Player player,ReqIntoTeamToGameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，组队功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqIntoTeamToWorldMessage wmsg = new ReqIntoTeamToWorldMessage();
		wmsg.setOthersid(msg.getOthersid());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world(wmsg);		
	}
	
	
	
	
	
	/**改变队员属性
	 * 
	 * @param player
	 */
	public void stTeamChangeProperties(Player player){
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null) {
			ArrayList<Player> playerlist =new ArrayList<Player>();
			long teamid = player.getTeamid();
			HashMap<Long, Player> mapplayers = map.getPlayers();
			if (mapplayers != null && mapplayers.size() > 0) {
				Iterator<Entry<Long, Player>> it = mapplayers.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, Player> players =  it.next();
					Player xplayer = players.getValue();
					if (xplayer.getTeamid() > 0  &&xplayer.getTeamid() == teamid) {
						playerlist.add(xplayer);
					}
				}
			}
			for (Player player2 : playerlist) {
				ManagerPool.playerAttributeManager.countPlayerAttribute(player2, PlayerAttributeType.TEAM);
			}
		}
	}
	
	
	
	/**世界服务器通知玩家改变组队
	 * 
	 * @param msg
	 */
	public void stResTeamchangeToGameMessage(ResTeamchangeToGameMessage msg) {
		Player player =  ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			boolean is =false;
			if (player.getTeamid() == 0 && player.getPkState() == 3) {
				is=true;
			}
			List<TeamMemberInfo> memberinfo = msg.getMemberinfo();
			player.setTeamid(msg.getTeamid());
			stTeamChangeProperties(player);
			
			ResTeamExteriorClientMessage cmsg = new ResTeamExteriorClientMessage();
			cmsg.setTeamid(msg.getTeamid());
			cmsg.setPlayerid(player.getId());
			int mapid = player.getMap();
			if (ManagerPool.dataManager.q_mapContainer.getMap().get(mapid) == null) {
				return;
			}
			if(!PlayerState.CHANGEMAP.compare(player.getState())){
				MessageUtil.tell_round_message(player ,cmsg);	//发送队伍ID给周围的人
			}
			
			if (is) {
				ManagerPool.playerManager.changePkState(player, 1, 0);
			}
			showMapTeamMemberToteaminfo(player,memberinfo);	
		}
	}


	
}



