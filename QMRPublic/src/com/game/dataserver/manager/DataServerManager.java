package com.game.dataserver.manager;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.game.config.Config;
import com.game.dataserver.message.ReqKickPlayerToCrossServerMessage;
import com.game.dataserver.message.ReqPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ReqPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ReqReceivePlayerRewardToDataServerMessage;
import com.game.dataserver.message.ReqStartMatchToGameServerMessage;
import com.game.dataserver.message.ReqSyncPlayerInfoFromDataServerMessage;
import com.game.dataserver.message.ReqSyncPlayerToDataServerMessage;
import com.game.dataserver.message.ResPlayerEnterToGameServerMessage;
import com.game.dataserver.message.ResPlayerQuitToDataServerMessage;
import com.game.dataserver.message.ResPlayerRewardFromDataServerMessage;
import com.game.dataserver.message.ResPlayerStateFromDataServerMessage;
import com.game.dataserver.message.ResReceivePlayerRewardToDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerInfoFromDataServerErrorMessage;
import com.game.dataserver.message.ResSyncPlayerInfoFromDataServerMessage;
import com.game.dataserver.message.ResSyncPlayerToDataServerMessage;
import com.game.db.dao.PlayerInfoDao;
import com.game.db.dao.RewardDataDao;
import com.game.enter.manager.EnterManager;
import com.game.enter.structs.EnterInfo;
import com.game.enter.structs.TeamMember;
import com.game.memorycache.PlayerCache;
import com.game.memorycache.UserCache;
import com.game.memorycache.structs.PlayerInfo;
import com.game.memorycache.structs.RewardData;
import com.game.memorycache.structs.UserInfo;
import com.game.memorycache.structs.UserState;
import com.game.server.config.PublicGameServerInfo;
import com.game.server.impl.PublicServer;
import com.game.util.MessageUtil;

public class DataServerManager {
	
	protected Logger log = Logger.getLogger(DataServerManager.class);
	
	private static Object obj = new Object();
	
	//玩家账号集合
	public static UserCache userCache = new UserCache();
	
	//玩家角色数据集合
	public static PlayerCache playerCache = new PlayerCache();
	
	//实例
	private static DataServerManager manager;
	
	private PlayerInfoDao playerdao = new PlayerInfoDao();
	
	private RewardDataDao rewarddao = new RewardDataDao();

	private DataServerManager() {
	}

	public static DataServerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new DataServerManager();
			}
		}
		return manager;
	}
	

	/**
	 * 接收同步信息(普通游戏服务器向公共数据服务器请求，跨服前数据同步，准备跨服)
	 * @param msg
	 */
	public void recSyncPlayerData(ReqSyncPlayerToDataServerMessage msg){
		PlayerInfo player = null;
		UserInfo user = userCache.getUser(msg.getWeb(), msg.getUserId());
		
		//内存中寻找玩家数据
		player = playerCache.getPlayer(msg.getWeb(), msg.getPlayerId());
		if(player==null){
			//数据库中查找
			player = loadPlayer(msg.getWeb(), msg.getPlayerId());
			if(player!=null) playerCache.putPlayer(player);
		}
		
		if(player==null){
			//初始化
			player = new PlayerInfo();
			player.setUserId(msg.getUserId());
			player.setName(msg.getUserName());
			player.setServerId(msg.getServerId());
			player.setWeb(msg.getWeb());
			player.setPlayerId(msg.getPlayerId());
			player.setDataServerPlayerId(Config.getId());
			player.setData(msg.getData());
			player.setTime(System.currentTimeMillis());
			playerCache.putPlayer(player);
			
			insertPlayer(player);
		}
		
		//设置user
		if(user==null){
			//加入账号信息
			user = new UserInfo();
			user.setUserId(msg.getUserId());
			user.setName(msg.getUserName());
			user.setServerId(msg.getServerId());
			user.setWeb(msg.getWeb());
			user.setState(UserState.WAIT_CROSS);
			user.setTime(System.currentTimeMillis());
			user.setPlayerId(msg.getPlayerId());
			user.setDataServerPlayerId(player.getDataServerPlayerId());

			userCache.putUser(user);
		}
		
		TeamMember teamMember = EnterManager.getPlayers().get(EnterManager.getPlayerKey(player.getWeb(), player.getServerId(), player.getPlayerId()));
		if(teamMember==null){
			log.error("组队信息找不到，这是不正常的， 同步数据:" + player.toString());
			return;
		}
		EnterInfo enterInfo = EnterManager.getEnterInfo().get(teamMember.getEnterId());
		if(enterInfo==null){
			log.error("比赛信息找不到，这是不正常的， 同步数据:" + teamMember.toString());
			return;
		}
		PublicGameServerInfo info = PublicServer.getInstance().getPublicGameServerInfo(enterInfo.getCrossServer());
		if(info==null){
			log.error("比赛游戏服信息找不到，这是不正常的， 同步数据:" + enterInfo.toString());
			return;
		}

		//跨服中
		if(user.getState()==UserState.CROSS_SERVER){
			sendSyncPlayerDataResult(msg.getSession(), msg.getUserId(), msg.getPlayerId(), user.getDataServerPlayerId(), user.getState(), info.getServerId(), info.getIp(), info.getPort(), info.getSslport());
			kickPlayer(user);
			log.error("玩家" + player.getName() + "当前状态为跨服中！跨服过程失败");
			return;
		}
		//准备跨服中
		else if(user.getState()==UserState.WAIT_CROSS){
			//与上次准备跨服的是同一人
			if(user.getPlayerId()==msg.getPlayerId()){
				player.setData(msg.getData());
				player.setTime(System.currentTimeMillis());
				
				user.setState(UserState.WAIT_CROSS);
				user.setTime(System.currentTimeMillis());
				//返回成功
				sendSyncPlayerDataResult(msg.getSession(), msg.getUserId(), msg.getPlayerId(), user.getDataServerPlayerId(), user.getState(), info.getServerId(), info.getIp(), info.getPort(), info.getSslport());
				
				log.error("玩家" + player.getName() + "当前状态为跨服准备中！跨服过程成功");
				return;
			}
			//与上次准备跨服的不是同一人
			else{
				player.setData(msg.getData());
				player.setTime(System.currentTimeMillis());
				
				user.setPlayerId(msg.getPlayerId());
				user.setDataServerPlayerId(player.getDataServerPlayerId());
				user.setState(UserState.WAIT_CROSS);
				user.setTime(System.currentTimeMillis());
				
				sendSyncPlayerDataResult(msg.getSession(), msg.getUserId(), user.getPlayerId(), user.getDataServerPlayerId(), user.getState(), info.getServerId(), info.getIp(), info.getPort(), info.getSslport());
				log.error("玩家" + player.getName() + "当前状态为跨服准备中！跨服过程成功");
				return;
			}
		}
		
		//跨服结束中
		else if(user.getState()==UserState.QUIT_CROSS || user.getState()==UserState.NO_CROSS){
			if(user.getPlayerId()==msg.getPlayerId()){
				player.setData(msg.getData());
				player.setTime(System.currentTimeMillis());
				
				//更新账号信息
				user.setState(UserState.WAIT_CROSS);
				user.setTime(System.currentTimeMillis());
				//返回成功
				sendSyncPlayerDataResult(msg.getSession(), msg.getUserId(), msg.getPlayerId(), user.getDataServerPlayerId(), user.getState(), info.getServerId(), info.getIp(), info.getPort(), info.getSslport());
				
				log.error("玩家" + player.getName() + "当前状态为未跨服！跨服过程成功");
				return;
			}else{
				player.setData(msg.getData());
				player.setTime(System.currentTimeMillis());
				
				//更新账号信息
				user.setPlayerId(player.getPlayerId());
				user.setDataServerPlayerId(player.getDataServerPlayerId());
				user.setState(UserState.WAIT_CROSS);
				user.setTime(System.currentTimeMillis());
				//返回成功
				sendSyncPlayerDataResult(msg.getSession(), msg.getUserId(), msg.getPlayerId(), user.getDataServerPlayerId(), user.getState(), info.getServerId(), info.getIp(), info.getPort(), info.getSslport());
				
				log.error("玩家" + player.getName() + "当前状态为未跨服！跨服过程成功");
				return;
			}
		}
	}
	
	/**
	 * 请求玩家数据(跨服游戏服务器向公共数据服务器请求，跨服游戏服务器登录请求玩家数据，跨服中)
	 */
	public void reqPlayerData(ReqSyncPlayerInfoFromDataServerMessage msg){
		PlayerInfo player = null;
		//内存中寻找玩家数据
		player = playerCache.getPlayer(msg.getDataServerPlayerId());
		if(player==null){
			log.error("玩家数据不存在：" + msg.getDataServerPlayerId());
			//返回玩家信息不存在错误
			sendSyncPlayerInfoError(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getDataServerPlayerId(), msg.getServerId(), msg.getGateId(), SyncPlayerError.PLAYER_NOT_EXITS);
			return;
		}
		
		UserInfo user = userCache.getUser(player.getWeb(), player.getUserId());
		if(user==null){
			log.error("玩家跨服帐号不存在：" + player.toString());
			//返回玩家账号信息不存在
			sendSyncPlayerInfoError(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getDataServerPlayerId(), msg.getServerId(), msg.getGateId(), SyncPlayerError.USER_NOT_EXITS);
			return;
		}

		//跨服中
		if(user.getState()==UserState.CROSS_SERVER){
			log.error("玩家正在跨服中，请求数据失败：" + player.toString());
			//返回错误
			sendSyncPlayerInfoError(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getDataServerPlayerId(), msg.getServerId(), msg.getGateId(), SyncPlayerError.ALREADY_CROSS_SERVER);
			return;
		}
		//准备跨服中
		else if(user.getState()==UserState.WAIT_CROSS){
			//与上次准备跨服的是同一人
			if(user.getDataServerPlayerId() == msg.getDataServerPlayerId()){
				TeamMember teamMember = EnterManager.getPlayers().get(EnterManager.getPlayerKey(player.getWeb(), player.getServerId(), player.getPlayerId()));
				if(teamMember==null){
					log.error("组队信息找不到，这是不正常的，请求跨服数据，玩家" + player.getName());
					return;
				}
				user.setState(UserState.CROSS_SERVER);
				user.setCrossServer(msg.getServerId());
				user.setCrossServerWeb(msg.getServerWeb());
				user.setTime(System.currentTimeMillis());
				
				sendPlayerData(msg.getSession(), player.getWeb(), player.getUserId(), player.getServerId(), msg.getGateId(), msg.getDataServerPlayerId(), player.getData(), teamMember.getDataServerTeamId(), teamMember.getEnterId());
				
				log.error("玩家数据跨服请求成功：" + player.toString());
				return;
			}
			//与上次准备跨服的不是同一人
			else{
				log.error("玩家此帐号其他角色正在跨服中，请求数据失败：" + player.toString());
				//返回错误
				sendSyncPlayerInfoError(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getDataServerPlayerId(), msg.getServerId(), msg.getGateId(), SyncPlayerError.CROSS_SERVER_PLAYER_ERROR);
				return;
			}
		}
		
		//跨服结束中
		else if(user.getState()==UserState.QUIT_CROSS || user.getState()==UserState.NO_CROSS){
			log.error("玩家未跨服或退出中，请求数据失败：" + player.toString());
			//返回错误
			sendSyncPlayerInfoError(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getDataServerPlayerId(), msg.getServerId(), msg.getGateId(), SyncPlayerError.NOT_READY_FOR_CROSS);
			return;
		}
	}
	
	/**
	 * 请求玩家状态(普通游戏服务器向公共数据服务器请求，返回当前玩家状态)
	 */
	public void reqPlayerState(ReqPlayerStateFromDataServerMessage msg){
		UserInfo user = userCache.getUser(msg.getWeb(), msg.getUserId());
		boolean needCross = false;
		if(user==null || user.getState()==UserState.NO_CROSS){
			//返回状态未跨服
			sendPlayerState(msg.getSession(), msg.getWeb(), msg.getPlayerId(), msg.getUserId(), UserState.NO_CROSS);
		}
		//跨服中 
		else if(user.getState()==UserState.CROSS_SERVER){
			
			//返回错误
			sendPlayerState(msg.getSession(), msg.getWeb(), msg.getPlayerId(), msg.getUserId(), UserState.CROSS_SERVER);
			kickPlayer(user);
			
			user.setState(UserState.WAIT_CROSS);
			user.setTime(System.currentTimeMillis());

			log.error("玩家" + msg.getPlayerId() + "当前状态为跨服中！");
			
			TeamMember teamMember = EnterManager.getPlayers().get(EnterManager.getPlayerKey(msg.getWeb(), msg.getServerId(), msg.getPlayerId()));
			if(teamMember==null){
				log.error("组队信息找不到，这是不正常的，玩家状态：" + UserState.CROSS_SERVER + "，数据：" + msg);
				return;
			}
			
			ReqStartMatchToGameServerMessage smsg = new ReqStartMatchToGameServerMessage();
			smsg.setPlayerId(teamMember.getPlayerId());
			smsg.setWeb(teamMember.getWeb());
			smsg.setServerId(teamMember.getServerId());
			MessageUtil.send_to_server_message(teamMember.getWeb(), teamMember.getServerId(), smsg);
			
			needCross = true;
		}
		//准备跨服中
		else if(user.getState()==UserState.WAIT_CROSS){
			user.setState(UserState.NO_CROSS);
			user.setTime(System.currentTimeMillis());
			//返回状态未跨服
			sendPlayerState(msg.getSession(), msg.getWeb(), msg.getPlayerId(), msg.getUserId(), UserState.NO_CROSS);

			log.error("玩家" + msg.getPlayerId() + "当前状态为跨服中！");
			
			TeamMember teamMember = EnterManager.getPlayers().get(EnterManager.getPlayerKey(msg.getWeb(), msg.getServerId(), msg.getPlayerId()));
			if(teamMember==null){
				log.error("组队信息找不到，这是不正常的，玩家状态：" + UserState.CROSS_SERVER + "，数据：" + msg);
				return;
			}
			
			ReqStartMatchToGameServerMessage smsg = new ReqStartMatchToGameServerMessage();
			smsg.setPlayerId(teamMember.getPlayerId());
			smsg.setWeb(teamMember.getWeb());
			smsg.setServerId(teamMember.getServerId());
			MessageUtil.send_to_server_message(teamMember.getWeb(), teamMember.getServerId(), smsg);
			
			needCross = true;
		}
		//跨服结束中
		else if(user.getState()==UserState.QUIT_CROSS){
			user.setState(UserState.NO_CROSS);
			user.setTime(System.currentTimeMillis());
			//返回状态未跨服
			sendPlayerState(msg.getSession(), msg.getWeb(), msg.getPlayerId(), msg.getUserId(), UserState.NO_CROSS);
		}
		
		if(!needCross){
			String playerkey = EnterManager.getPlayerKey(msg.getWeb(), msg.getServerId(), msg.getPlayerId());
			//找玩家报名信息
			TeamMember teamMember = EnterManager.getPlayers().get(playerkey);
			if(teamMember!=null){
				ResPlayerEnterToGameServerMessage entermsg = new ResPlayerEnterToGameServerMessage();
				entermsg.setPlayerId(teamMember.getPlayerId());
				entermsg.setServerId(teamMember.getServerId());
				entermsg.setWeb(teamMember.getWeb());
				entermsg.setParas(String.valueOf((int)((teamMember.getMatchTime() - System.currentTimeMillis()) / 1000)));
				
				msg.getSession().write(entermsg);
			}
			
			reqPlayerRewardFromDataServer(msg.getSession(), msg.getWeb(), msg.getUserId(), msg.getPlayerId(),msg.getMs());
		}
	}
	
	/**
	 * 请求玩家退出(跨服游戏服务器向公共数据服务器请求，玩家退出跨服状态)
	 */
	public void reqPlayerQuit(ReqPlayerQuitToDataServerMessage msg){
		PlayerInfo player = null;
		//内存中寻找玩家数据
		player = playerCache.getPlayer(msg.getDataServerPlayerId());
		if(player==null){
			//数据库中查找
			player = loadPlayer(msg.getDataServerPlayerId());
			if(player==null){
				//返回玩家信息不存在错误
				sendPlayerQuit(msg.getSession(), msg.getDataServerPlayerId(), msg.getServerId(), PlayerQuitError.PLAYER_NOT_EXITS);
				return;
			}
		}
		
		UserInfo user = userCache.getUser(player.getWeb(), player.getUserId());
		if(user==null){
			//返回玩家账号信息不存在
			sendPlayerQuit(msg.getSession(), msg.getDataServerPlayerId(), msg.getServerId(), PlayerQuitError.USER_NOT_EXITS);
			return;
		}
		
		//更新账号信息
		user.setState(UserState.QUIT_CROSS);
		user.setTime(System.currentTimeMillis());
		
		player.setPower(msg.getPower());
		player.setMatchtimes(player.getMatchtimes() + 1);
		//连胜次数等
		if(msg.getVictory()==1){
			player.setVictory(player.getVictory() + 1);
			player.setSeries(player.getSeries() + 1);
			if(player.getSeries()>player.getMaxseries()){
				player.setMaxseries(player.getSeries());
			}
			player.setMatchinfo((player.getMatchinfo() << 2) | 1);
			player.setPrevictory(1);
			resetPlayerTodaySeries(player,System.currentTimeMillis());
			player.setTodaySeries(player.getTodaySeries() + 1);
			if(player.getTodaySeries()>player.getTodayMaxseries()){
				player.setTodayMaxseries(player.getTodaySeries());
			}
			player.setVictoryTime(System.currentTimeMillis());
			log.error("玩家" + player.getName() + "胜利，连胜场数" + player.getMaxseries() + "，今天连胜场数" + player.getTodayMaxseries());
		}else if(msg.getVictory()==100){
			player.setSeries(0);
			player.setPrevictory(0);
			player.setMatchinfo((player.getMatchinfo() << 2) | 3);
			player.setTodaySeries(0);
			log.error("玩家" + player.getName() + "战平，连胜场数" + player.getMaxseries() + "，今天连胜场数" + player.getTodayMaxseries());
		}else{
			player.setSeries(0);
			player.setPrevictory(0);
			player.setMatchinfo((player.getMatchinfo() << 2) | 2);
			player.setTodaySeries(0);
			log.error("玩家" + player.getName() + "失败，连胜场数" + player.getMaxseries() + "，今天连胜场数" + player.getTodayMaxseries());
		}
		updatePlayer(player);
		
		//奖励信息
		RewardData data = new RewardData();
		data.setRewardId(Config.getId());
		data.setPlayerId(player.getPlayerId());
		data.setDataServerPlayerId(player.getDataServerPlayerId());
		data.setUserId(player.getUserId());
		data.setWeb(player.getWeb());
		data.setServerId(player.getServerId());
		data.setReward(msg.getReward());
		data.setTime(System.currentTimeMillis());
		data.setReceive(0);
		
		insertRewardData(data);
		
		//返回成功
		sendPlayerQuit(msg.getSession(), msg.getDataServerPlayerId(), msg.getServerId(), 0);
		
	}
	
	/**
	 * 请求玩家跨服奖励(这里只是查看)(游戏服务器向公共数据服务器)
	 */
	public void reqPlayerRewardFromDataServer(IoSession session, String web, String userId, long playerId,long ms){

		PlayerInfo player = null;
		//内存中寻找玩家数据
		player = playerCache.getPlayer(web, playerId);
		if(player==null){
			//数据库中查找
			player = loadPlayer(web, playerId);
			if(player==null){
				return;
			}
		}
		
		//查找玩家可领取奖励列表
		List<RewardData> rewardDatas = loadRewards(web, playerId);
		
		ResPlayerRewardFromDataServerMessage returnmsg = new ResPlayerRewardFromDataServerMessage();
		returnmsg.setPlayerId(playerId);
		returnmsg.setUserId(userId);
		returnmsg.setWeb(web);
		for (RewardData rewardData : rewardDatas) {
			returnmsg.getReward().add(rewardData.getReward());
		}
		resetPlayerTodaySeries(player,ms);
		returnmsg.setDayconvictory(player.getTodaySeries());
		returnmsg.setDayconvictorymax(player.getTodayMaxseries());
		returnmsg.setWinningstreak(player.getMaxseries());
		returnmsg.setRecvord(player.getMatchinfo());
		returnmsg.setCurrwinningstreak(player.getSeries());
		returnmsg.setResult(1);
		
		log.error("玩家数据：" + player.toString());
		
		session.write(returnmsg);
	}
	
	/**
	 * 请求获取玩家奖励(普通游戏服务器向公共数据服务器请求)
	 */
	public void reqReceivePlayerRewardToDataServer(ReqReceivePlayerRewardToDataServerMessage msg){
		//查找玩家可领取奖励列表
		List<RewardData> rewardDatas = loadRewards(msg.getWeb(), msg.getPlayerId());
		
		//更新状态已经领取
		updateRewardData(msg.getWeb(), msg.getPlayerId());
		
		ResReceivePlayerRewardToDataServerMessage returnmsg = new ResReceivePlayerRewardToDataServerMessage();
		returnmsg.setPlayerId(msg.getPlayerId());
		returnmsg.setUserId(msg.getUserId());
		returnmsg.setWeb(msg.getWeb());
		for (RewardData rewardData : rewardDatas) {
			returnmsg.getReward().add(rewardData.getReward());
		}
		
		msg.getRoleId().add(msg.getPlayerId());
		
		msg.getSession().write(returnmsg);
	}
	
	/**
	 * 跨服服务器断开连接，需要重置所有已经跨服账号状态
	 * @param serverId
	 */
	public void serverQuit(int serverId){
		ConcurrentHashMap<String, UserInfo> users = userCache.getUsers();
		Iterator<UserInfo> iterator = users.values().iterator();
		while (iterator.hasNext()) {
			UserInfo userInfo = (UserInfo) iterator.next();
			if(userInfo.getState()==UserState.CROSS_SERVER && userInfo.getCrossServer()==serverId){
				userInfo.setState(UserState.QUIT_CROSS);
				userInfo.setTime(System.currentTimeMillis());
			}
		}
	}
	
	/**
	 * 获取玩家信息
	 * @param web
	 * @param playerId
	 * @return
	 */
	public PlayerInfo getPlayer(String web, long playerId){
		PlayerInfo player = null;
		//内存中寻找玩家数据
		player = playerCache.getPlayer(web, playerId);
		if(player==null){
			//数据库中查找
			player = loadPlayer(web, playerId);
			if(player!=null) playerCache.putPlayer(player);
		}
		
		return player;
	}
	
	private PlayerInfo loadPlayer(long dataServerPlayerId){
		return playerdao.selectByDataServerPlayerId(dataServerPlayerId);
	}
	
	private PlayerInfo loadPlayer(String web, long playerId){
		return playerdao.selectByWebAndPlayerId(web, playerId);
	}
	
	private void insertPlayer(PlayerInfo player){
		playerdao.insert(player);
	}
	
	private void updatePlayer(PlayerInfo player){
		playerdao.update(player);
	}
	
	private void insertRewardData(RewardData rewardData){
		rewarddao.insert(rewardData);
	}
	
	private List<RewardData> loadRewards(String web, long playerId){
		return rewarddao.selectByWebAndPlayerId(web, playerId);
	}
	
	private void updateRewardData(String web, long playerId){
		rewarddao.update(web, playerId);
	}
	
	/**
	 * 从跨服服务器踢出
	 * @param user
	 */
	private void kickPlayer(UserInfo user){
		ReqKickPlayerToCrossServerMessage msg = new ReqKickPlayerToCrossServerMessage();
		msg.setWeb(user.getWeb());
		msg.setUserId(user.getUserId());
		msg.setPlayerId(user.getPlayerId());
		msg.setDataServerPlayerId(user.getDataServerPlayerId());
		
		msg.getRoleId().add(user.getDataServerPlayerId());
		
		IoSession session = PublicServer.getInstance().getGame(user.getCrossServer(), user.getCrossServerWeb());
		if(session!=null && session.isConnected()){
			session.write(msg);
		}else{
			user.setState(UserState.NO_CROSS);
			user.setTime(System.currentTimeMillis());
		}
	}
	
	private void sendPlayerData(IoSession session, String web, String userId, int serverId, int gateId, long dataServerPlayerId, String data, long teamId, long enterId){
		//返回当前跨服信息
		ResSyncPlayerInfoFromDataServerMessage returnmsg = new ResSyncPlayerInfoFromDataServerMessage();
		returnmsg.setDataServerPlayerId(dataServerPlayerId);
		returnmsg.setUserId(userId);
		returnmsg.setServerId(serverId);
		returnmsg.setWeb(web);
		returnmsg.setData(data);
		returnmsg.setGateId(gateId);
		returnmsg.setTeamId(teamId);
		returnmsg.setEnterId(enterId);

		session.write(returnmsg);
	}
	
	private void sendSyncPlayerDataResult(IoSession session, String userId, long playerId, long dataServerPlayerId, int state, int serverId, String ip, int port, int sslport){
		ResSyncPlayerToDataServerMessage returnmsg = new ResSyncPlayerToDataServerMessage();
		returnmsg.setUserId(userId);
		returnmsg.setPlayerId(playerId);
		returnmsg.setDataServerPlayerId(dataServerPlayerId);
		returnmsg.setResult(state);
		returnmsg.setServerId(serverId);
		returnmsg.setServerIp(ip);
		returnmsg.setServerPort(port);
		returnmsg.setServerSSLPort(sslport);
		
		session.write(returnmsg);
	}
	
	private void sendSyncPlayerInfoError(IoSession session, String web, String userId, long dataServerPlayerId, int serverId, int gateId, int error){
		ResSyncPlayerInfoFromDataServerErrorMessage returnmsg = new ResSyncPlayerInfoFromDataServerErrorMessage();
		returnmsg.setDataServerPlayerId(dataServerPlayerId);
		returnmsg.setUserId(userId);
		returnmsg.setWeb(web);
		returnmsg.setServerId(serverId);
		returnmsg.setGateId(gateId);
		returnmsg.setError(error);
		session.write(returnmsg);
	}
	
	private void sendPlayerState(IoSession session, String web, long playerId, String userId, int state){
		ResPlayerStateFromDataServerMessage returnmsg = new ResPlayerStateFromDataServerMessage();
		returnmsg.setWeb(web);
		returnmsg.setPlayerId(playerId);
		returnmsg.setUserId(userId);
		returnmsg.setState(state);
		
		session.write(returnmsg);
	}
	
	private void sendPlayerQuit(IoSession session, long dataServerPlayerId, int serverId, int state){
		ResPlayerQuitToDataServerMessage returnmsg = new ResPlayerQuitToDataServerMessage();
		returnmsg.setDataServerPlayerId(dataServerPlayerId);
		returnmsg.setServerId(serverId);
		returnmsg.setState(state);
		
		session.write(returnmsg);
	}

	private boolean isSameDay(long time1, long time2){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(time1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(time2);
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)){
			return true;
		}else{
			return false;
		}
	}
	
	private void resetPlayerTodaySeries(PlayerInfo info,long ms){
		if(!isSameDay(ms, info.getVictoryTime())){
			info.setTodaySeries(0);
			info.setTodayMaxseries(0);
		}
	}
}
