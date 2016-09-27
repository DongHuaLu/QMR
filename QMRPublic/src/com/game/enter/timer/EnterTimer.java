package com.game.enter.timer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.util.ConcurrentHashSet;

import com.game.config.Config;
import com.game.dataserver.message.ReqCheckPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ReqCheckTeamEnterToGameServerMessage;
import com.game.dataserver.message.ReqStartMatchToGameServerMessage;
import com.game.dataserver.message.ResPlayerResetToGameServerMessage;
import com.game.dataserver.message.ResTeamResetToGameServerMessage;
import com.game.enter.manager.EnterManager;
import com.game.enter.structs.EnterInfo;
import com.game.enter.structs.Team;
import com.game.enter.structs.TeamMember;
import com.game.enter.structs.TemporaryEnterInfo;
import com.game.server.config.PublicGameServerInfo;
import com.game.server.impl.PublicServer;
import com.game.timer.TimerEvent;
import com.game.util.MessageUtil;

public class EnterTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(EnterTimer.class);
	//检查准备时间
	private static long PREPARE_TIME = 30 * 1000;
	//队伍人数
	private static int TEAM_MEMBER_SIZE = 4;
	
	private static long nextMatchTime = 0;
	
	private static int COUNT = 0;
	
	public EnterTimer() {
		super(-1, 1 * 1000);
	}

	@Override
	public void action() {
		//检查校验过时比赛和开始可以开始的比赛
		checkTemporaryEnterInfo();
		
		long now = System.currentTimeMillis();
		
		if(now < nextMatchTime){
			return;
		}else{
			nextMatchTime = calNextMatchTime(now);
		}
		
		//安排比赛
		arrangeMatch();
	}
	
	/**
	 * 计算比赛匹配时间
	 * @param now
	 * @return
	 */
	private long calNextMatchTime(long now){
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.SECOND) >=55){
			cal.add(Calendar.MINUTE, 1);
		}
		cal.set(Calendar.SECOND, 55);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
	
	/**
	 * 检查校验过时比赛和开始可以开始的比赛
	 */
	private void checkTemporaryEnterInfo(){
		long now = System.currentTimeMillis();
		
		Iterator<TemporaryEnterInfo> iterator = EnterManager.getTemporaryEnterInfo().values().iterator();
		while (iterator.hasNext()) {
			TemporaryEnterInfo temporaryEnterInfo = (TemporaryEnterInfo) iterator
					.next();
			//是否检查完毕
			boolean prepare = true;
			//没有检查好的玩家
			HashSet<String> notPerpareMember = new HashSet<String>();
			//查看是否准备完毕
			for (int i = 0; i < temporaryEnterInfo.getTeam1().getPlayers().size(); i++) {
				if(temporaryEnterInfo.getPrepare1()[i]==0){
					prepare = false;
					TeamMember member = temporaryEnterInfo.getTeam1().getPlayers().get(i);
					notPerpareMember.add(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
				}
			}
			
			for (int i = 0; i < temporaryEnterInfo.getTeam2().getPlayers().size(); i++) {
				if(temporaryEnterInfo.getPrepare2()[i]==0){
					prepare = false;
					TeamMember member = temporaryEnterInfo.getTeam2().getPlayers().get(i);
					notPerpareMember.add(EnterManager.getPlayerKey(member.getWeb(), member.getServerId(), member.getPlayerId()));
				}
			}
			//没有检查完毕且超时
			if(!prepare && temporaryEnterInfo.getTime() + PREPARE_TIME < now){
				log.error("比赛准备过时:" + temporaryEnterInfo.toString());
				EnterManager.cancelPrepareEnter(temporaryEnterInfo.getTeam1(), 1, notPerpareMember);
				EnterManager.cancelPrepareEnter(temporaryEnterInfo.getTeam2(), 1, notPerpareMember);
				iterator.remove();
			}else if(prepare){
				//开始比赛
				startMatch(temporaryEnterInfo);
				iterator.remove();
			}
		}
	}
	
	/**
	 * 开始比赛
	 * @param temporaryEnterInfo
	 */
	private void startMatch(TemporaryEnterInfo temporaryEnterInfo){
		//构建比赛信息
		EnterInfo info = new EnterInfo();
		info.setEnterId(temporaryEnterInfo.getEnterId());
		info.setTeam1(temporaryEnterInfo.getTeam1());
		info.setTeam2(temporaryEnterInfo.getTeam2());
		info.setMatchId(temporaryEnterInfo.getMatchId());
		info.setVersion(temporaryEnterInfo.getVersion());
		
		if(PublicServer.getPublicGameServers()!=null){
			ConcurrentHashSet<Integer> sessions = PublicServer.getPublicSessions().get(info.getVersion());
			if(sessions!=null){
				Integer[] servers = sessions.toArray(new Integer[0]);
				if(servers.length > 0){
					int index = COUNT % servers.length;
					COUNT++;
					//选择比赛的公共服务器
					PublicGameServerInfo publicGameServerInfo = PublicServer.getPublicGameServers().get(servers[index]);
					if(publicGameServerInfo!=null) info.setCrossServer(publicGameServerInfo.getServerId());
				}
			}
		}
		
		if(info.getCrossServer()==0){
			log.error("比赛准备中发现没有合适版本的跨服服务器, 这是不正常的:" + temporaryEnterInfo.toString());
			//没有检查好的玩家
			HashSet<String> notPerpareMember = new HashSet<String>();
			EnterManager.cancelPrepareEnter(temporaryEnterInfo.getTeam1(), 1, notPerpareMember);
			EnterManager.cancelPrepareEnter(temporaryEnterInfo.getTeam2(), 1, notPerpareMember);
			return;
		}
		
		for (TeamMember member : temporaryEnterInfo.getTeam1().getPlayers()) {
			member.setDataServerTeamId(temporaryEnterInfo.getTeam1().getDataServerTeamId());
			member.setEnterId(temporaryEnterInfo.getEnterId());
		}
		
		for (TeamMember member : temporaryEnterInfo.getTeam2().getPlayers()) {
			member.setDataServerTeamId(temporaryEnterInfo.getTeam2().getDataServerTeamId());
			member.setEnterId(temporaryEnterInfo.getEnterId());
		}
		
		//加入到比赛列表
		EnterManager.getEnterInfo().put(info.getEnterId(), info);
		
		log.error("比赛开始:" + info.toString());
		
		//发送开始跨服信息
		for (TeamMember member : temporaryEnterInfo.getTeam1().getPlayers()) {
			ReqStartMatchToGameServerMessage smsg = new ReqStartMatchToGameServerMessage();
			smsg.setPlayerId(member.getPlayerId());
			smsg.setWeb(member.getWeb());
			smsg.setServerId(member.getServerId());
			MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), smsg);
		}
		
		for (TeamMember member : temporaryEnterInfo.getTeam2().getPlayers()) {
			ReqStartMatchToGameServerMessage smsg = new ReqStartMatchToGameServerMessage();
			smsg.setPlayerId(member.getPlayerId());
			smsg.setWeb(member.getWeb());
			smsg.setServerId(member.getServerId());
			MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), smsg);
		}
	}
	
	/**
	 * 安排比赛
	 */
	private void arrangeMatch(){
		long now = System.currentTimeMillis();
		
		HashSet<String> keys = new HashSet<String>();
		keys.addAll(EnterManager.getEnterteams().keySet());
		keys.addAll(EnterManager.getEnterplayers().keySet());
		
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String matchKey = (String) iter.next();
			
			List<Team> teams = new ArrayList<Team>();
			HashMap<String, Team> enterteams = EnterManager.getEnterteams().get(matchKey);
			if(enterteams!=null){
				for (Team team : enterteams.values()) {
					if(team.getMatchTime() <= now){
						log.error("队伍进入匹配队列:" + team.toString());
						teams.add(team);
					}
				}
			}
			//个人玩家，按先后组队
			List<TeamMember> enterplayers = EnterManager.getEnterplayers().get(matchKey);
			if(enterplayers!=null){
				for (int i = 0; i < enterplayers.size(); i=i+TEAM_MEMBER_SIZE) {
					//初始化队伍信息
					Team team = new Team();
					team.setDataServerTeamId(Config.getId());
					int matchId = 0;
					int version = 0;
					int power = 0;
					int victory = 0;
					boolean enough= true;
					for (int j = 0; j < TEAM_MEMBER_SIZE; j++) {
						if(i+j >= enterplayers.size()){
							enough=false;
							break;
						}
						TeamMember member = enterplayers.get(i + j);
						if(member.getMatchTime()>now){
							enough=false;
							break;
						}
						log.error("个人进入匹配队列:" + member.toString());
						team.getPlayers().add(member);
						power+=member.getPower();
						victory+=member.getVictory();
						matchId=member.getMatchId();
						version=member.getVersion();
					}
					if(!enough){
						for (TeamMember member : team.getPlayers()) {
							member.setMatchTime(calNextMatchTime(member.getEnterTime()));
							
							log.error("个人重新开始匹配:" + member.toString());
							
							//匹配失败，没有合适的队伍 ， 提示重新匹配
							ResPlayerResetToGameServerMessage msg = new ResPlayerResetToGameServerMessage();
							msg.setPlayerId(member.getPlayerId());
							msg.setServerId(member.getServerId());
							msg.setWeb(member.getWeb());
							msg.setTime((int)((member.getMatchTime() - System.currentTimeMillis()) / 1000));
							MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), msg);
						}
						break;
					}
					team.setPower(power);
					team.setVictory(victory);
					team.setMatchId(matchId);
					team.setVersion(version);
					teams.add(team);
					log.error("自动组队成功:" + team.toString());
				}
			}
			//按战斗力排序
			Collections.sort(teams, new Comparator<Team>() {

				@Override
				public int compare(Team team1, Team team2) {
					if(team1.getVictory() > team2.getVictory()){
						return -1;
					}else if(team1.getVictory() < team2.getVictory()){
						return 1;
					}else{
						return team1.getPower() > team2.getPower()?-1:1;
					}
				}
			});
			
			//开始匹配
			List<Team> sortTeams = new ArrayList<Team>();
			sortTeams.addAll(teams);
			for (int i = 0; i < sortTeams.size(); i=i+2) {
				if(i+1 >= sortTeams.size()){
					//匹配失败，没有合适的队伍 ， 提示重新匹配
					Team team = sortTeams.get(i);
					//队伍报名
					if(team.getTeamId()!=0){
						//重新匹配
						team.setMatchTime(calNextMatchTime(team.getEnterTime()));
						
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
						for (TeamMember member : team.getPlayers()) {
							member.setMatchTime(calNextMatchTime(member.getEnterTime()));
							
							log.error("个人重新匹配:" + member.toString());
							//匹配失败，没有合适的队伍 ， 提示重新匹配
							ResPlayerResetToGameServerMessage msg = new ResPlayerResetToGameServerMessage();
							msg.setPlayerId(member.getPlayerId());
							msg.setServerId(member.getServerId());
							msg.setWeb(member.getWeb());
							msg.setTime((int)((member.getMatchTime() - System.currentTimeMillis()) / 1000));
							MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), msg);
						}
					}
					break;
				}
				Team team1 = sortTeams.get(i);
				Team team2 = sortTeams.get(i + 1);
				
				//构建临时比赛信息
				TemporaryEnterInfo info = new TemporaryEnterInfo();
				info.setEnterId(Config.getId());
				info.setTeam1(team1);
				info.setTeam2(team2);
				info.setTime(System.currentTimeMillis());
				info.setVersion(team1.getVersion());
				EnterManager.getTemporaryEnterInfo().put(info.getEnterId(), info);
				
				log.error("准备比赛成功:" + info.toString());
				//取消队伍1排名
				prepareEnter(team1);
				//取消队伍2排名
				prepareEnter(team2);
				
				//发送队伍1验证请求
				sendCheckMessage(team1, info.getEnterId());
				//发送队伍2验证请求
				sendCheckMessage(team2, info.getEnterId());
			}
		}
	}

	/**
	 * 准备进入比赛
	 * @param team
	 */
	private void prepareEnter(Team team){
		log.error("准备进入:" + team.toString());
		//队伍报名
		if(team.getTeamId()!=0){
			log.error("队伍移除报名队列:" + team.toString());
			HashMap<String, Team> teams = EnterManager.getEnterteams().get(EnterManager.getMatchKey(team.getMatchId(), team.getVersion()));
			if(teams!=null){
				teams.remove(EnterManager.getTeamKey(team.getWeb(), team.getServerId(), team.getTeamId()));
			}else{
				log.error("队伍版本报名队列不存在:" + team.toString());
			}
		}
		//个人报名组建的队伍
		else{
			List<TeamMember> members = EnterManager.getEnterplayers().get(EnterManager.getMatchKey(team.getMatchId(), team.getVersion()));
			if(members!=null){
				for (TeamMember member : team.getPlayers()) {
					log.error("个人移除报名队列:" + member.toString());
					members.remove(member);
				}
			}else{
				log.error("个人版本报名队列不存在:" + team.toString());
			}
		}
		EnterManager.getTeams().put(team.getDataServerTeamId(), team);
	}
	
	/**
	 * 发送验证消息
	 * @param team
	 */
	private void sendCheckMessage(Team team, long enterId){
		//队伍报名
		if(team.getTeamId()!=0){
			ReqCheckTeamEnterToGameServerMessage msg = new ReqCheckTeamEnterToGameServerMessage();
			msg.setPlayerId(team.getPlayers().get(0).getPlayerId());
			msg.setServerId(team.getServerId());
			msg.setWeb(team.getWeb());
			msg.setTeamId(team.getTeamId());
			msg.setDataServerTeamId(team.getDataServerTeamId());
			msg.setEntryId(enterId);
			for (TeamMember member : team.getPlayers()) {
				msg.getTeamPlayerIds().add(member.getPlayerId());
			}
			MessageUtil.send_to_server_message(team.getWeb(), team.getServerId(), msg);
		}
		//个人报名组建的队伍
		else{
			for (TeamMember member : team.getPlayers()) {
				ReqCheckPlayerEnterToGameServerMessage msg = new ReqCheckPlayerEnterToGameServerMessage();
				msg.setPlayerId(member.getPlayerId());
				msg.setServerId(member.getServerId());
				msg.setWeb(member.getWeb());
				msg.setDataServerTeamId(team.getDataServerTeamId());
				msg.setEntryId(enterId);
				
				MessageUtil.send_to_server_message(member.getWeb(), member.getServerId(), msg);
			}
		}
	}
	
	
}
