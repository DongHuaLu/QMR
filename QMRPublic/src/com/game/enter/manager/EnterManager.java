package com.game.enter.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.config.Config;
import com.game.dataserver.manager.DataServerManager;
import com.game.dataserver.message.ReqCancelPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ReqCancelTeamEnterToDataServerMessage;
import com.game.dataserver.message.ReqFinishMatchToDataServerMessage;
import com.game.dataserver.message.ReqPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ReqTeamEnterToDataServerMessage;
import com.game.dataserver.message.ResCancelPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ResCancelTeamEnterToGameServerMessage;
import com.game.dataserver.message.ResCheckPlayerEnterToDataServerMessage;
import com.game.dataserver.message.ResCheckTeamEnterToDataServerMessage;
import com.game.dataserver.message.ResEnterErrorToGameServerMessage;
import com.game.dataserver.message.ResPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ResPlayerResetToGameServerMessage;
import com.game.dataserver.message.ResTeamEnterToGameServerMessage;
import com.game.dataserver.message.ResTeamResetToGameServerMessage;
import com.game.enter.structs.EnterInfo;
import com.game.enter.structs.Team;
import com.game.enter.structs.TeamMember;
import com.game.enter.structs.TemporaryEnterInfo;
import com.game.memorycache.structs.PlayerInfo;
import com.game.server.impl.PublicServer;
import com.game.util.MessageUtil;

/**
 * 报名跨服副本
 * @author heyang
 *
 */
public class EnterManager {

	protected static Logger log = Logger.getLogger(EnterManager.class);
	
	//队伍报名列表
	private static HashMap<String, HashMap<String, Team>> enterteams = new HashMap<String, HashMap<String, Team>>();
	
	//玩家列表
	private static HashMap<String, TeamMember> players = new HashMap<String, TeamMember>();
	
	//单人报名列表
	private static HashMap<String, List<TeamMember>> enterplayers = new HashMap<String, List<TeamMember>>();
	
	//待验证比赛列表
	private static HashMap<Long, TemporaryEnterInfo> temporaryEnterInfo = new HashMap<Long, TemporaryEnterInfo>();
	
	//比赛列表
	private static HashMap<Long, EnterInfo> enterInfo = new HashMap<Long, EnterInfo>();
	
	//比赛队伍列表
	private static HashMap<Long, Team> teams = new HashMap<Long, Team>();
		
	private static Object obj = new Object();
	
	//实例
	private static EnterManager manager;
	
	private EnterManager() {
	}

	public static EnterManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new EnterManager();
			}
		}
		return manager;
	}
	/**
	 * 团队报名跨服
	 */
	public void reqTeamEnterToDataServer(ReqTeamEnterToDataServerMessage msg){
		long playerId = msg.getPlayerId();
		long teamId = msg.getTeamId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		int power = msg.getPower();
		//比赛ID
		int matchId = msg.getMatchId();
		//版本
		int version = msg.getVersion();
		
		//没有此版本跨服游戏服务器
		if(!PublicServer.getPublicSessions().containsKey(version)){
			//返回错误消息
			ResTeamEnterToGameServerMessage errormsg = new ResTeamEnterToGameServerMessage();
			errormsg.setPlayerId(playerId);
			errormsg.setServerId(serverId);
			errormsg.setWeb(web);
			errormsg.setTeamId(teamId);
			errormsg.setResult(2);
			
			msg.getSession().write(errormsg);
			return;
		}
		
		List<Long> members = new ArrayList<Long>();
		members.addAll(msg.getTeamPlayerIds());
		
		String matchKey = getMatchKey(matchId, version);
		//队伍是否已经报名
		String teamKey = getTeamKey(web, serverId, teamId);
		
		HashMap<String, Team> teams = null;
		//是否拥有比赛组队报名列表
		if(enterteams.containsKey(matchKey)){
			teams = enterteams.get(matchKey);
		}else{
			teams = new HashMap<String, Team>();
			enterteams.put(matchKey, teams);
		}
		
		if(teams.containsKey(teamKey)){
			Team team = teams.get(teamKey);
			//玩家报名成功
			ResTeamEnterToGameServerMessage errormsg = new ResTeamEnterToGameServerMessage();
			errormsg.setPlayerId(playerId);
			errormsg.setServerId(serverId);
			errormsg.setWeb(web);
			errormsg.setTeamId(teamId);
			errormsg.setParas(String.valueOf((int)((team.getMatchTime() - System.currentTimeMillis()) / 1000)));
			
			msg.getSession().write(errormsg);
			return;
		}
		
		//检查玩家是否有报名
		for (Long id : members) {
			String playerkey = getPlayerKey(web, serverId, id);
			if(players.containsKey(playerkey)){
				log.error("玩家已经报过名了！");
				//玩家已经报名或者已经比赛， 返回错误消息
				ResTeamEnterToGameServerMessage errormsg = new ResTeamEnterToGameServerMessage();
				errormsg.setPlayerId(playerId);
				errormsg.setServerId(serverId);
				errormsg.setWeb(web);
				errormsg.setTeamId(teamId);
				errormsg.setResult(1);
				errormsg.setParas(String.valueOf(id));
				
				msg.getSession().write(errormsg);
				return;
			}
		}
		
		//初始化队伍信息
		Team team = new Team();
		team.setTeamId(teamId);
		team.setDataServerTeamId(Config.getId());
		team.setPower(power);
		team.setWeb(web);
		team.setServerId(serverId);
		team.setEnterTime(System.currentTimeMillis());
		team.setMatchTime(calNextMatchTime(team.getEnterTime()));
		team.setMatchId(matchId);
		team.setVersion(version);
		
		int victory = 0;
		for (Long id : members) {
			TeamMember member = new TeamMember();
			member.setPlayerId(id);
			member.setTeamId(teamId);
			member.setDataServerTeamId(team.getDataServerTeamId());
			member.setWeb(web);
			member.setServerId(serverId);
			member.setMatchId(matchId);
			member.setVersion(version);
			
			PlayerInfo playerInfo = DataServerManager.getInstance().getPlayer(web, id);
			if(playerInfo!=null){
				member.setVictory(playerInfo.getSeries());
				victory += member.getVictory();
			}
			
			team.getPlayers().add(member);
			//放入玩家列表
			String playerkey = getPlayerKey(web, serverId, id);
			players.put(playerkey, member);
		}
		team.setVictory(victory);
		teams.put(getTeamKey(web, serverId, teamId), team);
		
		log.error("队伍报名成功(" + teams.size() + "):" + team.toString());
		
		//玩家报名成功
		ResTeamEnterToGameServerMessage errormsg = new ResTeamEnterToGameServerMessage();
		errormsg.setPlayerId(playerId);
		errormsg.setServerId(serverId);
		errormsg.setWeb(web);
		errormsg.setTeamId(teamId);
		errormsg.setParas(String.valueOf((int)((team.getMatchTime() - System.currentTimeMillis()) / 1000)));
		
		msg.getSession().write(errormsg);
		
	}
	
	/**
	 * 团队取消跨服报名
	 */
	public void reqCancelTeamEnterToDataServer(ReqCancelTeamEnterToDataServerMessage msg){
		long playerId = msg.getPlayerId();
		long teamId = msg.getTeamId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		
		//队伍是否已经报名
		String key = getTeamKey(web, serverId, teamId);
		
		//发送队伍解除报名消息
		ResCancelTeamEnterToGameServerMessage returnmsg = new ResCancelTeamEnterToGameServerMessage();
		returnmsg.setPlayerId(playerId);
		boolean finded = false;
		for (HashMap<String, Team> teams : enterteams.values()) {
			if(!teams.containsKey(key)){
				continue;
			}else {
				//获得队伍
				Team team = teams.get(key);
				for (TeamMember member : team.getPlayers()) {
					//放入玩家列表
					String playerkey = getPlayerKey(web, serverId, member.getPlayerId());
					players.remove(playerkey);
					returnmsg.getMembers().add(member.getPlayerId());
				}
				log.error("队伍解除报名:" + team.toString());
				teams.remove(key);
				returnmsg.setServerId(team.getServerId());
				returnmsg.setWeb(team.getWeb());
				returnmsg.setTeamId(team.getTeamId());
				finded = true;
				break;
			}
		}
		if(!finded){
			return;
		}
		
		returnmsg.setParas(msg.getReason()+"");
		msg.getSession().write(returnmsg);
	}
	
	/**
	 * 个人报名
	 */
	public void reqPlayerEnterToDataServer(ReqPlayerEnterToDataServerMessage msg){
		long playerId = msg.getPlayerId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		int power = msg.getPower();
		//比赛ID
		int matchId = msg.getMatchId();
		//版本
		int version = msg.getVersion();
		
		//没有此版本跨服游戏服务器
		if(!PublicServer.getPublicSessions().containsKey(version)){
			//返回错误消息
			ResPlayerEnterToGameServerMessage errormsg = new ResPlayerEnterToGameServerMessage();
			errormsg.setPlayerId(playerId);
			errormsg.setServerId(serverId);
			errormsg.setWeb(web);
			errormsg.setResult(2);
			msg.getSession().write(errormsg);
			return;
		}
		
		String matchKey = getMatchKey(matchId, version);
		
		String playerkey = getPlayerKey(web, serverId, playerId);
		if(players.containsKey(playerkey)){
			//玩家已经报名或者已经比赛， 返回错误消息
			ResPlayerEnterToGameServerMessage errormsg = new ResPlayerEnterToGameServerMessage();
			errormsg.setPlayerId(playerId);
			errormsg.setServerId(serverId);
			errormsg.setWeb(web);
			errormsg.setResult(1);
			msg.getSession().write(errormsg);
			return;
		}
		
		TeamMember member = new TeamMember();
		member.setPlayerId(playerId);
		member.setWeb(web);
		member.setServerId(serverId);
		member.setPower(power);
		member.setEnterTime(System.currentTimeMillis());
		member.setMatchTime(calNextMatchTime(member.getEnterTime()));
		member.setMatchId(matchId);
		member.setVersion(version);
		
		PlayerInfo playerInfo = DataServerManager.getInstance().getPlayer(web, playerId);
		if(playerInfo!=null){
			member.setVictory(playerInfo.getSeries());
		}
		
		//放入玩家列表
		players.put(playerkey, member);
		
		List<TeamMember> teamMembers = null; 
		if(enterplayers.containsKey(matchKey)){
			teamMembers = enterplayers.get(matchKey);
		}else{
			teamMembers = new ArrayList<TeamMember>();
			enterplayers.put(matchKey, teamMembers);
		}
		teamMembers.add(member);
		
		log.error("个人报名成功(" + teamMembers.size() + "):" + member.toString());
		
		//个人报名成功
		ResPlayerEnterToGameServerMessage errormsg = new ResPlayerEnterToGameServerMessage();
		errormsg.setPlayerId(playerId);
		errormsg.setServerId(serverId);
		errormsg.setWeb(web);
		errormsg.setParas(String.valueOf((int)((member.getMatchTime() - System.currentTimeMillis()) / 1000)));
		
		msg.getSession().write(errormsg);
	}
	
	/**
	 * 个人取消报名
	 */
	public void reqCancelPlayerEnterToDataServerMessage(ReqCancelPlayerEnterToDataServerMessage msg){
		long playerId = msg.getPlayerId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		
		String playerkey = getPlayerKey(web, serverId, playerId);
		TeamMember member = players.get(playerkey);
		
		if(member!=null){
			int matchId = member.getMatchId();
			int version = member.getVersion();
			
			String matchKey = getMatchKey(matchId, version);
			
			if(enterplayers.containsKey(matchKey)){
				List<TeamMember> teamMembers = enterplayers.get(matchKey);
				if(teamMembers.remove(member)){
					log.error("个人解除报名:" + member.toString());
					players.remove(playerkey);
				}else{
					return;
				}
			}else{
				return;
			}
		}
		//发送个人解除报名消息
		ResCancelPlayerEnterToGameServerMessage returnmsg = new ResCancelPlayerEnterToGameServerMessage();
		returnmsg.setPlayerId(playerId);
		returnmsg.setServerId(serverId);
		returnmsg.setWeb(web);
		msg.getSession().write(returnmsg);
	}
	
	/**
	 * 游戏服务器返回队伍检查结果
	 * @param msg
	 */
	public void resCheckTeamEnterToDataServer(ResCheckTeamEnterToDataServerMessage msg){
//		long playerId = msg.getPlayerId();
		long teamId = msg.getTeamId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		long entryId = msg.getEntryId();
		int result = msg.getResult();
		
		log.error("队伍检查结果：" + msg.toString());
		
		if(web==null){
			return;
		}
		
		TemporaryEnterInfo info = temporaryEnterInfo.get(entryId);
		if(info==null){
			return;
		}
		
		//没有检查好的玩家
		HashSet<String> notPerpareMember = new HashSet<String>();
		
		Team team = null;
		if(info.getTeam1().getTeamId() == teamId && web.equals(info.getTeam1().getWeb()) && info.getTeam1().getServerId()==serverId){
			team = info.getTeam1();
			for (int i = 0; i < info.getTeam1().getPlayers().size(); i++) {
				info.getPrepare1()[i] = msg.getMembers().get(i);
				if(info.getPrepare1()[i]==0){
					TeamMember member = info.getTeam1().getPlayers().get(i);
					notPerpareMember.add(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
				}
			}
		}else if(info.getTeam2().getTeamId() == teamId && web.equals(info.getTeam2().getWeb()) && info.getTeam2().getServerId()==serverId){
			team = info.getTeam2();
			for (int i = 0; i < info.getTeam2().getPlayers().size(); i++) {
				info.getPrepare2()[i] = msg.getMembers().get(i);
				if(info.getPrepare2()[i]==0){
					TeamMember member = info.getTeam2().getPlayers().get(i);
					notPerpareMember.add(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
				}
			}
		}
		
		if(result > 0){
			cancelPrepareEnter(info.getTeam1(), result, notPerpareMember);
			cancelPrepareEnter(info.getTeam2(), result, notPerpareMember);
			temporaryEnterInfo.remove(info.getEnterId());
		}else{
			log.error("队伍检查通过:" + team.toString());
		}
	}
	
	/**
	 * 游戏服务器返回个人检查结果
	 * @param msg
	 */
	public void resCheckPlayerEnterToDataServer(ResCheckPlayerEnterToDataServerMessage msg){
		long playerId = msg.getPlayerId();
		String web = msg.getWeb();
		int serverId = msg.getServerId();
		long entryId = msg.getEntryId();
		int result = msg.getResult();
		
		if(web==null){
			return;
		}
		
		TemporaryEnterInfo info = temporaryEnterInfo.get(entryId);
		if(info==null){
			return;
		}
		
		String playerKey = getPlayerKey(web, serverId, playerId);
		//没有检查好的玩家
		HashSet<String> notPerpareMember = new HashSet<String>();
		
		boolean finded = false;
		TeamMember tmember = null;
		if(info.getTeam1().getTeamId() == 0){
			for (int i = 0; i < info.getTeam1().getPlayers().size(); i++) {
				TeamMember member = info.getTeam1().getPlayers().get(i);
				if(playerKey.equals(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()))){
					tmember = member;
					if(result > 0){
						notPerpareMember.add(playerKey);
						log.error("个人检查失败:" + tmember.toString());
					}else{
						info.getPrepare1()[i] = 1;
					}
					finded = true;
					break;
				}
			}
		}
		
		if(info.getTeam2().getTeamId() == 0 && !finded){
			for (int i = 0; i < info.getTeam2().getPlayers().size(); i++) {
				TeamMember member = info.getTeam2().getPlayers().get(i);
				if(playerKey.equals(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()))){
					tmember = member;
					if(result > 0){
						notPerpareMember.add(playerKey);
						log.error("个人检查失败:" + tmember.toString());
					}else{
						info.getPrepare2()[i] = 1;
					}
					finded = true;
					break;
				}
			}
		}
		
		if(result > 0){
			cancelPrepareEnter(info.getTeam1(), result, notPerpareMember);
			cancelPrepareEnter(info.getTeam2(), result, notPerpareMember);
			temporaryEnterInfo.remove(info.getEnterId());
		}else{
			log.error("个人检查通过:" + tmember.toString());
		}
	}
	
	/**
	 * 请求完成比赛
	 * @param msg
	 */
	public void reqFinishMatchToDataServer(ReqFinishMatchToDataServerMessage msg){
		long matchId = msg.getMatchId();
		
		EnterInfo info = enterInfo.get(matchId);
		if(info==null){
			return;
		}
		
		finishMatch(info);
	}
	
	/**
	 * 服务器断开连接
	 * @param serverId
	 */
	public void serverQuit(String web, int serverId, int version){
		Iterator<String> keyIter = enterteams.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = (String) keyIter.next();
			if(key.endsWith("_" + version)){
				HashMap<String, Team> teams = enterteams.get(key);
				//排名中队伍移除
				Iterator<Team> iter1 = teams.values().iterator();
				while (iter1.hasNext()) {
					Team team = (Team) iter1.next();
					if(web!=null && web.equals(team.getWeb()) && team.getServerId()==serverId){
						for (TeamMember member : team.getPlayers()) {
							players.remove(getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
						}
						//移除队伍信息
						iter1.remove();
					}
				}
			}
		} 
		
		Iterator<String> keyIter2 = enterplayers.keySet().iterator();
		while (keyIter2.hasNext()) {
			String key = (String) keyIter2.next();
			if(key.endsWith("_" + version)){
				List<TeamMember> teamMembers = enterplayers.get(key);
				
				//排名中玩家移除
				Iterator<TeamMember> iter2 = teamMembers.iterator();
				while (iter2.hasNext()) {
					TeamMember member = (TeamMember) iter2.next();
					if(web!=null && web.equals(member.getWeb()) && member.getServerId()==serverId){
						players.remove(getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
						//移除队伍信息
						iter2.remove();
					}
				}
			}
		}
		
		//比赛中队伍移除
		Iterator<EnterInfo> iter3 = enterInfo.values().iterator();
		while (iter3.hasNext()) {
			EnterInfo info = (EnterInfo) iter3.next();
			if(info.getCrossServer()==serverId){
				finishMatch(info);
				//移除队伍信息
				iter3.remove();
			}
		}
	}
	
	/**
	 * 结束比赛，移除参赛信息
	 * @param info
	 */
	public void finishMatch(EnterInfo info){
		teams.remove(info.getTeam1().getDataServerTeamId());
		teams.remove(info.getTeam2().getDataServerTeamId());
		for (TeamMember member : info.getTeam1().getPlayers()) {
			players.remove(getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
		}
		for (TeamMember member : info.getTeam2().getPlayers()) {
			players.remove(getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
		}
	}
	
	/**
	 * 取消准备进入比赛
	 * @param team
	 * @param notPerpareMember
	 */
	public static void cancelPrepareEnter(Team team, int result, HashSet<String> notPerpareMember){
		//队伍报名
		if(team.getTeamId()!=0){
			//如果是队伍报名，队伍中成员有不能比赛的情况，解除队伍报名
			long id = 0;
			boolean prepare = true;
			for (TeamMember member : team.getPlayers()) {
				//包含在没有准备完毕的玩家列表中
				if(notPerpareMember.contains(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()))){
					id = member.getPlayerId();
					prepare = false;
				}
			}
			if(prepare){
				team.setMatchTime(calNextMatchTime(team.getEnterTime()));
				log.error("队伍进入报名队列:" + team.toString());
				
				String matchkey = getMatchKey(team.getMatchId(), team.getVersion());
				String teamKey = getTeamKey(team.getWeb(), team.getServerId(), team.getTeamId());
				
				HashMap<String, Team> teams = null;
				if(enterteams.containsKey(matchkey)){
					teams = enterteams.get(matchkey);
				}else{
					teams = new HashMap<String, Team>();
					enterteams.put(matchkey, teams);
				}
				
				teams.put(teamKey, team);
				
				log.error("队伍重新匹配:" + team.toString());
				
				//匹配失败，没有合适的队伍 ， 提示重新匹配
				ResTeamResetToGameServerMessage msg = new ResTeamResetToGameServerMessage();
				msg.setPlayerId(team.getPlayers().get(0).getPlayerId());
				msg.setServerId(team.getServerId());
				msg.setWeb(team.getWeb());
				msg.setTeamId(team.getTeamId());
				msg.setTime((int)((team.getMatchTime() - System.currentTimeMillis()) / 1000));
				MessageUtil.send_to_server_message(team.getWeb(), team.getServerId(), msg);
			}else{
				//匹配失败，没有准备好的队伍 ， 提示退出报名队列
				ResEnterErrorToGameServerMessage msg = new ResEnterErrorToGameServerMessage();
				
				//解除队伍报名
				for (TeamMember member : team.getPlayers()) {
					EnterManager.getPlayers().remove(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
					msg.getMembers().add(member.getPlayerId());
				}
				
				log.error("队伍解除报名:" + team.toString());
				
				msg.setPlayerId(team.getPlayers().get(0).getPlayerId());
				msg.setServerId(team.getServerId());
				msg.setWeb(team.getWeb());
				msg.setTeamId(team.getTeamId());
				msg.setError(result);
				msg.setParas(String.valueOf(id));
				MessageUtil.send_to_server_message(team.getWeb(), team.getServerId(), msg);
			}
		}
		//个人报名组建的队伍
		else{
			//个人报名，解除有不能比赛的情况玩家报名，其余玩家回到报名队列
			for (TeamMember member : team.getPlayers()) {
				//包含在没有准备完毕的玩家列表中
				if(!notPerpareMember.contains(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()))){
					member.setMatchTime(calNextMatchTime(member.getEnterTime()));
					log.error("个人进入报名队列:" + member.toString());
					
					String matchkey = getMatchKey(member.getMatchId(), member.getVersion());
					List<TeamMember> teamMembers = null;
					if(enterplayers.containsKey(matchkey)){
						teamMembers = enterplayers.get(matchkey);
					}else{
						teamMembers = new ArrayList<TeamMember>();
						enterplayers.put(matchkey, teamMembers);
					}
					
					teamMembers.add(0, member);
					
					log.error("个人重新匹配:" + member.toString());
					//匹配失败，没有合适的队伍 ， 提示重新匹配
					ResPlayerResetToGameServerMessage msg = new ResPlayerResetToGameServerMessage();
					msg.setPlayerId(member.getPlayerId());
					msg.setServerId(member.getServerId());
					msg.setWeb(member.getWeb());
					msg.setTime((int)((member.getMatchTime() - System.currentTimeMillis()) / 1000));
					MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), msg);
				}else{
					//解除个人报名
					EnterManager.getPlayers().remove(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));

					log.error("个人解除报名:" + member.toString());
					
					//匹配失败，没有准备好的队伍 ， 提示退出报名队列
					ResEnterErrorToGameServerMessage msg = new ResEnterErrorToGameServerMessage();
					msg.setPlayerId(team.getPlayers().get(0).getPlayerId());
					msg.setServerId(team.getServerId());
					msg.setWeb(team.getWeb());
					msg.setTeamId(team.getTeamId());
					msg.setError(result);
					MessageUtil.send_to_server_message(team.getWeb(), team.getServerId(), msg);
				}
			}
		}
		EnterManager.getTeams().remove(team.getDataServerTeamId());
	}
	
	/**
	 * 计算比赛匹配时间
	 * @param enterTime
	 * @return
	 */
	public static long calNextMatchTime(long enterTime){
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.SECOND) >=55){
			cal.add(Calendar.MINUTE, 1);
		}
		cal.set(Calendar.SECOND, 55);
		cal.set(Calendar.MILLISECOND, 0);
		//报名时间小于1
		if(cal.getTimeInMillis() - enterTime < 60 * 1000){
			cal.add(Calendar.MINUTE, 1);
		}
		return cal.getTimeInMillis();
	}
	
	public static String getPlayerKey(String web, int serverId, long playerId){
		return web + "_" + playerId;
	}
	
	public static String getTeamKey(String web, int serverId, long teamId){
		return web + "_" + teamId;
	}
	
	public static String getMatchKey(int matchId, int version){
		return matchId + "_" + version;
	}

	public static HashMap<Long, Team> getTeams() {
		return teams;
	}

	public static HashMap<String, TeamMember> getPlayers() {
		return players;
	}

	public static HashMap<Long, TemporaryEnterInfo> getTemporaryEnterInfo() {
		return temporaryEnterInfo;
	}

	public static HashMap<Long, EnterInfo> getEnterInfo() {
		return enterInfo;
	}

	public static HashMap<String, HashMap<String, Team>> getEnterteams() {
		return enterteams;
	}

	public static HashMap<String, List<TeamMember>> getEnterplayers() {
		return enterplayers;
	}
	
}
