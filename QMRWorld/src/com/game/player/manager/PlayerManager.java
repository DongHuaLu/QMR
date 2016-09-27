package com.game.player.manager;

import com.game.arrow.structs.ArrowData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Equip;
import com.game.backpack.structs.HorseEquip;
import com.game.config.Config;
import com.game.db.bean.GameUser;
import com.game.db.bean.Role;
import com.game.db.dao.BlackListDao;
import com.game.db.dao.RoleDao;
import com.game.db.dao.UserDao;
import com.game.db.dao.UserRegisterDao;
import com.game.db.dao.WorldDao;
import com.game.dblog.LogService;
import com.game.friend.manager.FriendManager;
import com.game.gem.struts.Gem;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.Horse;
import com.game.horse.struts.HorseSkill;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.login.message.ReqKickPlayerMessage;
import com.game.login.message.ResPlayerNonageToGameMessage;
import com.game.login.message.ResPlayerWorldInfoMessage;
import com.game.manager.ManagerPool;
import com.game.pet.struts.Pet;
import com.game.player.bean.PlayerAppearanceInfo;
import com.game.player.bean.PlayerAttributeItem;
import com.game.player.log.DelRoleLog;
import com.game.player.message.ReqChangePlayerNameToWorldMessage;
import com.game.player.message.ReqDelPlayerToWorldMessage;
import com.game.player.message.ReqGetPlayerAppearanceInfoToWorldMessage;
import com.game.player.message.ReqOtherPlayerInfoToGameMessage;
import com.game.player.message.ReqPlayerCheckOnlineToWorldMessage;
import com.game.player.message.ReqScriptCommonServerToWorldMessage;
import com.game.player.message.ReqSyncPlayerAppearanceInfoMessage;
import com.game.player.message.ReqSyncPlayerAttributeMessage;
import com.game.player.message.ReqSyncPlayerHpMessage;
import com.game.player.message.ReqSyncPlayerInfoMessage;
import com.game.player.message.ReqSyncPlayerLevelMessage;
import com.game.player.message.ReqSyncPlayerMapMessage;
import com.game.player.message.ReqSyncPlayerOrderInfoMessage;
import com.game.player.message.ReqSyncPlayerPositionMessage;
import com.game.player.message.ResChangePlayerNameToGameMessage;
import com.game.player.message.ResDelPlayerStatusMessage;
import com.game.player.message.ResGetPlayerAppearanceInfMessage;
import com.game.player.message.ResPlayerCheckOnlineMessage;
import com.game.player.message.ResPlayerNameInfoToGameMessage;
import com.game.player.message.ResPlayerNonageRegisterMessage;
import com.game.player.message.ResPlayerNonageTimeMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.player.structs.User;
import com.game.player.structs.UserRegister;
import com.game.prompt.structs.Notifys;
import com.game.registrar.manager.RegistrarManager;
import com.game.server.WorldServer;
import com.game.skill.structs.Skill;
import com.game.structs.Attributes;
import com.game.structs.IDCardInfo;
import com.game.structs.Position;
import com.game.toplist.manager.TopListManager;
import com.game.toplist.structs.ArrowTop;
import com.game.toplist.structs.FightPowerTop;
import com.game.toplist.structs.LevelTop;
import com.game.toplist.structs.TopPlayer;
import com.game.utils.MessageUtil;

public class PlayerManager {

	protected static Logger log = Logger.getLogger(PlayerManager.class);
	private static Logger playerlog = Logger.getLogger("WORLDPLAYER");
	private static Object obj = new Object();
	//玩家管理类实例
	private static PlayerManager manager;
	//在线玩家列表
	private static ConcurrentHashMap<Long, Player> players = new ConcurrentHashMap<Long, Player>();
	//全部玩家列表
	private static ConcurrentHashMap<Long, PlayerWorldInfo> allPlayers = new ConcurrentHashMap<Long, PlayerWorldInfo>();
	//账号对应玩家列表
	private static ConcurrentHashMap<String, List<Player>> userPlayers = new ConcurrentHashMap<String, List<Player>>();
	//防沉迷
	private static ConcurrentHashMap<String, User> userTimes = new ConcurrentHashMap<String, User>();
	//玩家身份证注册
	private static ConcurrentHashMap<String, UserRegister> userRegisters = new ConcurrentHashMap<String, UserRegister>();
	//聊天黑名单  账号,IP
	private Set<String> chatBlackUsernames = new HashSet<String>();
	private Set<String> chatBlackIPs = new HashSet<String>();
	private Set<String> testGmIPs = new HashSet<String>();
	//是否检查防沉迷
//	private static boolean CHECK_NONAGE = false;
	private static int AGE = 18;
	private WorldDao dao;
	private UserDao userdao;
	private UserRegisterDao userregdao;
	private BlackListDao blacklistdao; 
	
	private PlayerManager() {
		dao = new WorldDao();
		userdao = new UserDao();
		userregdao = new UserRegisterDao();
		blacklistdao = new BlackListDao();
		try {
			List<PlayerWorldInfo> list = dao.select();
			for (PlayerWorldInfo playerWorldInfo : list) {
				allPlayers.put(playerWorldInfo.getId(), playerWorldInfo);
			}
			List<User> list2 = userdao.select();
			for (User user : list2) {
				userTimes.put(user.getUserId(), user);
			}
			List<UserRegister> list3 = userregdao.select();
			for (UserRegister user : list3) {
				userRegisters.put(user.getUserId(), user);
			}
			chatBlackUsernames = blacklistdao.selectUserSetByTypeState(1, 0);
			chatBlackIPs = blacklistdao.selectUserSetByTypeState(2, 0);
			testGmIPs = blacklistdao.selectUserSetByTypeState(3, 0);
		} catch (SQLException e) {
			log.error(e, e);
		}
	}

	public static PlayerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new PlayerManager();
			}
		}
		return manager;
	}
	//玩家角色数据接口
	private RoleDao roleDao = new RoleDao();

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void registerPlayer(int gate, int server, String userId, long playerId, byte isAdult, int loginType, int createServer) {


		//注册角色线路信息
		Player player = null;
		if (players.containsKey(playerId)) {
			player = players.get(playerId);
			playerlog.error("玩家" + userId + "在服务器" + server + "上线注册角色" + playerId + ",在线:" + players.size());
		} else {
			player = new Player();
			player.setId(playerId);
			Guild guild = ManagerPool.guildWorldManager.getGuildByUserId(playerId);
			if (guild != null) {
				player.setGuildid(guild.getGuildInfo().getGuildId());
			} else {
				player.setGuildid(0);
			}
			players.put(playerId, player);
			playerlog.error("玩家" + userId + "在服务器" + server + "上线注册角色" + playerId + ",在线:" + players.size());
		}

		player.setUserId(userId);
		player.setIsAdult(isAdult);
		player.setLoginType(loginType);

		List<Player> players = null;
		if (userPlayers.containsKey(userId)) {
			players = userPlayers.get(userId);
		} else {
			players = new ArrayList<Player>();
			userPlayers.put(userId, players);
		}

		if (!players.contains(player)) {
			players.add(player);
		}

		player.setGateId(gate);
		player.setServer(server);
		player.setSyncdata(false);
		player.setCreateServer(createServer);
		ResPlayerWorldInfoMessage msg = new ResPlayerWorldInfoMessage();
		msg.setPlayerId(playerId);
		msg.setGuildId(player.getGuildid());
		msg.setTeamId(player.getTeamid());
		MessageUtil.send_to_game(player, msg);

		//开启防沉迷检查
		if (WorldServer.getNonageConfig().getNonage()==1) {
			boolean nonage = (player.getIsAdult() == 0);
			if (nonage) {
				if (userRegisters.containsKey(userId)) {
					UserRegister reg = userRegisters.get(userId);
					if (reg.getNonage() == 1) {
						nonage = false;
					}
				}
			}
			User user = null;
			if (nonage) {
				if (userTimes.containsKey(userId)) {
					user = userTimes.get(userId);
				} else {
					user = new User();
					user.setUserId(userId);
					user.setState(1);//待插入
					synchronized (userTimes) {
						userTimes.put(user.getUserId(), user);
					}
				}
				//下线5小时后清零

				if (System.currentTimeMillis() - user.getLasttime() > 5 * 60 * 60 * 1000) {
					user.setOnline(0);
				}

				//同步到游戏服务器
				ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
				nonagemsg.setPlayerId(playerId);
				nonagemsg.setNonage(getUserNonage(user));
				MessageUtil.send_to_game(player, nonagemsg);
			} else {
				if (userTimes.containsKey(userId)) {
					user = userTimes.get(userId);
					user.setState(2);//待删除
				}

				//同步到游戏服务器
				ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
				nonagemsg.setPlayerId(playerId);
				nonagemsg.setNonage(0);
				MessageUtil.send_to_game(player, nonagemsg);
			}
		} else {
			//同步到游戏服务器
			ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
			nonagemsg.setPlayerId(playerId);
			nonagemsg.setNonage(0);
			MessageUtil.send_to_game(player, nonagemsg);
		}
	}

	public int getUserNonage(User user) {
		//3小时之内
		if (user.getOnline() < 3 * 60 * 60 * 1000) {
			return 1;
		} //3-5小时
		else if (user.getOnline() < 5 * 60 * 60 * 1000) {
			return 2;
		} //5小时之外
		else {
			return 3;
		}
	}

	public void getUserNonageTime(String userId, long playerId) {
		Player player = null;
		if (players.containsKey(playerId)) {
			player = players.get(playerId);
		} else {
			return;
		}

		int time = 0;
		if (userTimes.containsKey(userId)) {
			User user = userTimes.get(userId);
			time = (int) (user.getOnline() / 1000);
		}
		ResPlayerNonageTimeMessage msg = new ResPlayerNonageTimeMessage();
		msg.setTime(time);
		MessageUtil.tell_player_message(player, msg);
	}

	public void registerUser(String userId, long playerId, String name, String idCard) {
		ResPlayerNonageRegisterMessage result = new ResPlayerNonageRegisterMessage();
		Player player = null;
		if (players.containsKey(playerId)) {
			player = players.get(playerId);
		} else {
			return;
		}
		if (userRegisters.containsKey(userId)) {
			result.setResult((byte) 4);
			MessageUtil.tell_player_message(player, result);
			return;
		} else {
			IDCardInfo info = new IDCardInfo(idCard);
			//未验证成功
			if (info.getBirthday() == null) {
				result.setResult((byte) 3);
				MessageUtil.tell_player_message(player, result);
				return;
			}

			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, date.get(Calendar.YEAR) - AGE);

			UserRegister reg = new UserRegister();
			reg.setUserId(userId);
			reg.setName(name);
			reg.setIdCard(idCard);
			if (info.getBirthday().before(date.getTime())) {
				reg.setNonage(1);
			}

			userRegisters.put(reg.getUserId(), reg);
			try {
				userregdao.insert(reg);
			} catch (SQLException e) {
				log.error(e, e);
			}

			if (reg.getNonage() == 1) {
				result.setResult((byte) 1);
				List<Player> players = ManagerPool.playerManager.getPlayersByUser(userId);

				if (userTimes.containsKey(userId)) {
					User user = userTimes.get(userId);
					user.setState(2);//待删除
				}

				if (players == null) {
					return;
				}
				for (int i = 0; i < players.size(); i++) {
					ResPlayerNonageToGameMessage nonagemsg = new ResPlayerNonageToGameMessage();
					nonagemsg.setPlayerId(players.get(i).getId());
					nonagemsg.setNonage(0);
					MessageUtil.send_to_game(players.get(i), nonagemsg);
				}
			} else {
				result.setResult((byte) 2);
			}
			MessageUtil.tell_player_message(player, result);
		}
	}

	public void removePlayer(long playerId) {
		//移除角色信息
		Player player = players.remove(playerId);
		if (player == null) {
			playerlog.error("移除玩家为空，玩家" + playerId + "在服务器" + WorldServer.getInstance().getServerId() + "下线注销角色" + playerId + ",在线:" + players.size());
			return;
		}

		playerlog.error("玩家" + player.getUserId() + "在服务器" + player.getServer() + "下线注销角色" + playerId + ",在线:" + players.size());

		PlayerWorldInfo info = allPlayers.get(playerId);
		if (info != null) {
			info.setLastOnlineTime(System.currentTimeMillis());
			info.setLevel(player.getLevel());
			savePlayerWorldInfo(info);
		}

		if (player != null) {
			List<Player> players = userPlayers.get(player.getUserId());
			if (players != null) {
				players.remove(player);
			} else {
				log.error("remove player " + player.getId() + " error");
			}
			//移除一些公会信息
			GuildWorldManager.getInstance().quitClearGuildOtherData(player);
		}
		//移除摆摊信息
		ManagerPool.stallsManager.stOfflineRemoveStallslist(playerId);
		//移除队伍
		ManagerPool.teamManager.stOfflineLeavetheTeam(player, (byte) 2);
	}

	public void getOtherPlayerInfo(long playerId, long otherPlayerId, byte type) {
		Player player = null;
		if (players.containsKey(otherPlayerId)) {
			player = players.get(otherPlayerId);
			ReqOtherPlayerInfoToGameMessage msg = new ReqOtherPlayerInfoToGameMessage();
			msg.setPlayerId(playerId);
			msg.setOtherPlayerId(otherPlayerId);
			msg.setType(type);
			MessageUtil.send_to_game(player, msg);
		} else {
			player = players.get(playerId);
			//提示玩家不在线
			if (player != null) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家不在线"));
			}
		}
	}

	public void syncPlayerInfo(ReqSyncPlayerInfoMessage message) {
		//注册角色线路信息
		Player player = null;
		if (players.containsKey(message.getPlayerId())) {
			player = players.get(message.getPlayerId());
			player.setSyncdata(true);
			player.setServer(message.getServerId());
			player.setLine(message.getLine());
			player.setName(message.getName());
			player.setLevel(message.getLevel());
			player.setCountry(message.getCountry());
			player.setMap(message.getMapId());
			player.setPosition(new Position(message.getX(), message.getY()));
			player.setMountLevel(message.getMountLevel());
			player.setTianyuanLevel(message.getTianyuanLevel());
			player.setArrowLevel(message.getArrowLevel());
			player.setFightPower(message.getFightPower());
			player.setPrestigePoint(message.getPrestigePoint());
			player.setAchievementPoint(message.getAchievementPoint());
			player.setAutoIntoteamapply(message.getAutoTeamApply());
			player.setAutoteaminvited(message.getAutoTeamInvited());
			player.setMood(message.getMood());
			player.setOpenmaplocation(message.getOpenMapLocation());
			player.setAutoArgeeAddGuild(message.getAutoArgeeAddGuild());
			player.setLastAfkGuildTime(message.getLastAfkGuildTime());
			player.setContributionPoint(message.getContributionPoint());
			player.setMaponlyid(message.getMaponlyId());
			player.setSex(message.getAppearanceInfo().getSex());
			player.setClothingmodid(message.getAppearanceInfo().getClothingmodid());
			player.setHorsemodid(message.getAppearanceInfo().getHorsemodid());
			player.setHorseweaponid(message.getAppearanceInfo().getHorseweaponmodid());
			player.setHiddenweaponid(message.getAppearanceInfo().getHiddenweaponmodid());
			player.setWeaponmodid(message.getAppearanceInfo().getWeaponmodid());
			player.setWeaponStreng(message.getAppearanceInfo().getWeaponStreng());
			player.setAvatarid(message.getAppearanceInfo().getAvatarid());
			player.setMenustatus(message.getMenustatus());
			player.setVipid(message.getVipid());
			player.setRanklevel(message.getRanklevel());
			GuildWorldManager.getInstance().loginSendGuildAndMemberInfo(player);
			TopListManager.getInstance().getPlayerTopTitleList(player);
			TopListManager.getInstance().testZoneSendMailTop5(player);
			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(player.getId());
			if (topPlayer != null) {
				topPlayer.setKingcitybuffid(message.getKingcitybuffid());
				topPlayer.setVipid(message.getVipid());
			}
			FriendManager.getInstance().loginAllOnlineAttentionHashMap(player);
			if (message.getType() == 0) {
				//读取摆摊信息
				ManagerPool.stallsManager.loadStalls(message.getPlayerId());
				ManagerPool.stallsManager.loginAddStallslist(player);
				//通知玩家双倍开启
				ManagerPool.monsterManager.LoginMonsterDoubleNotice(player);
			}
		} else {
			log.error("注册玩家信息，玩家不存在：" + message.getPlayerId());
		}

		PlayerWorldInfo info = null;
		if (!allPlayers.containsKey(message.getPlayerId())) {
			info = new PlayerWorldInfo();
			info.setId(message.getPlayerId());
			info.setName(message.getName());
			info.setAccount(message.getUserId());
			info.setCountry(message.getCountry());
			info.setLevel(message.getLevel());
			info.setChangeName(message.getChangeName());
			info.setChangaccount(message.getChangeUser());
			info.setMenustatus(message.getMenustatus());
			info.setSex(message.getAppearanceInfo().getSex());
			info.setVip(message.getVipid());
			info.setWebvip(message.getWebvip());
			insertPlayerWorldInfo(info);
			allPlayers.put(message.getPlayerId(), info);
		}else{
			info = allPlayers.get(message.getPlayerId());
			info.setVip(message.getVipid());
			info.setWebvip(message.getWebvip());
			savePlayerWorldInfo(info);
		}
		
		
		ResPlayerNameInfoToGameMessage msg = new ResPlayerNameInfoToGameMessage();
		msg.setPlayerId(message.getPlayerId());
		msg.setChangeName((byte) info.getChangeName());
		msg.setChangeUser((byte) info.getChangaccount());
		if (player != null) {
			MessageUtil.send_to_game(player, msg);
		} else {
			MessageUtil.send_to_game(msg);
		}

		//登录器任务奖励信息
		RegistrarManager.getInstance().sendRegistrarReward(player);
		//通知前端玩家是否领取首冲奖励
		//RegistrarManager.getInstance().sendHasRegistrared(player);
		
		//单独加载神树数据
		if (player.getLevel() >= 30) {
			ManagerPool.spiritTreeManager.loadSpirittree(player.getId());
		}
		
	}

	/**
	 * 玩家切换地图 同步
	 *
	 * @param message
	 */
	public void syncPlayerMap(ReqSyncPlayerMapMessage message) {
		Player player = null;
		if (players.containsKey(message.getPlayerId())) {
			player = players.get(message.getPlayerId());
			player.setLine(message.getLine());
			player.setMap(message.getMapId());
			player.setMaponlyid(message.getMaponlyId());
			player.setPosition(new Position(message.getX(), message.getY()));
			if (player.getTeamid() > 0) {
				ManagerPool.teamManager.changeMemberMoveMap(player.getTeamid());
			}
		}
	}

	public void syncPlayerPosition(ReqSyncPlayerPositionMessage message) {
		Player player = null;
		if (players.containsKey(message.getPlayerId())) {
			player = players.get(message.getPlayerId());
			player.setPosition(new Position(message.getX(), message.getY()));
		}
	}

	public void syncPlayerLevel(ReqSyncPlayerLevelMessage message) {
		Player player = null;
		if (players.containsKey(message.getPlayerId())) {
			player = players.get(message.getPlayerId());
			player.setLevel(message.getLevel());
			ManagerPool.stallsManager.refreshStallslist(player);//更新摊位简要信息里的等级
		}

		TopPlayer topPlayer = TopListManager.getTopplayerMap().get(message.getPlayerId());
		if (topPlayer != null) {
			FightPowerTop fightPowerTop = new FightPowerTop(topPlayer.getId(), topPlayer.getFightPower(), message.getLevel());
			TopListManager.getInstance().updateTopData(fightPowerTop);

			ArrowTop arrowTop = new ArrowTop(topPlayer.getId(), topPlayer.getArrowLevel(), topPlayer.getStarlv(), topPlayer.getBowlv(), message.getLevel(), topPlayer.getCostGold());
			TopListManager.getInstance().updateTopData(arrowTop);
		}
		
		LevelTop levelTop = new LevelTop(message.getPlayerId(), message.getLevel(), message.getLevelUpTime());
		TopListManager.getInstance().updateTopData(levelTop);
	}

	/**
	 * 玩家外观同步
	 *
	 * @param message
	 */
	public void syncPlayerAppearanceInfo(ReqSyncPlayerAppearanceInfoMessage message) {
		Player player = null;
		if (players.containsKey(message.getPlayerId())) {
			player = players.get(message.getPlayerId());

			player.setSex(message.getAppearanceInfo().getSex());
			player.setClothingmodid(message.getAppearanceInfo().getClothingmodid());
			player.setHorsemodid(message.getAppearanceInfo().getHorsemodid());
			player.setHorseweaponid(message.getAppearanceInfo().getHorseweaponmodid());
			player.setHiddenweaponid(message.getAppearanceInfo().getHiddenweaponmodid());
			player.setWeaponmodid(message.getAppearanceInfo().getWeaponmodid());
			player.setWeaponStreng(message.getAppearanceInfo().getWeaponStreng());
			player.setAvatarid(message.getAppearanceInfo().getAvatarid());
			player.setArrowLevel((byte) message.getAppearanceInfo().getArrowid());
			player.setVipid(message.getVipid());

			TopPlayer topPlayer = TopListManager.getTopplayerMap().get(message.getPlayerId());
			if (topPlayer != null) {
				topPlayer.setKingcitybuffid(message.getKingcitybuffid());
				topPlayer.setVipid(message.getVipid());
			}

			PlayerWorldInfo info = allPlayers.get(message.getPlayerId());
			if (info != null) {
				if (info.getVip() != message.getVipid()) {
					info.setVip(message.getVipid());
					savePlayerWorldInfo(info);
				}
			}
		}
	}

	public void syncPlayerHp(ReqSyncPlayerHpMessage message) {
	}

	public void syncPlayerOrderInfo(ReqSyncPlayerOrderInfoMessage message) {
		//排行1000名添加的
		TopPlayer player = TopListManager.getTopplayerMap().get(message.getPlayerId());
		if (player != null) {
			ReqSyncPlayerAttributeMessage msg = new ReqSyncPlayerAttributeMessage();
			msg.setPlayerId(message.getPlayerId());

			msg.setExp(message.getExp());
			msg.setZhenqi(message.getZhenqi());
			msg.setAvatar(message.getAvatar());
			msg.setChapter(message.getChapter());
			msg.setPrestige(message.getPrestige());

			msg.setEquip(message.getEquip());
			msg.setGem(message.getGem());
			msg.setSkills(message.getSkills());

			msg.getAttributes().addAll(message.getAttributes());

			msg.setFightPower(message.getFightPower());

			msg.setHorseEquip(message.getHorseEquip());
			msg.setHorseSkill(message.getHorseSkill());

			msg.setPets(message.getPets());

			msg.setKingcitybuffid(message.getKingcitybuffid());
			msg.setVipid(message.getVipid());

			msg.setArrowinfo(message.getArrowinfo());

			msg.setCostgold(message.getCostgold());//TODO 消耗元宝
			msg.setHorseWeaponSkill(message.getHorseWeaponSkill());
			syncPlayerAttribute(msg);
			return;
		}
		
		//注册排行角色
		player = new TopPlayer();

		player.setId(message.getPlayerId());
		player.setUserId(message.getUserId());
		player.setName(message.getName());
		player.setSex(message.getSex());
		player.setCountry(message.getCountry());
		player.setMoney(message.getMoney());
		player.setExp(message.getExp());
		player.setZhenqi(message.getZhenqi());
		player.setAvatar(message.getAvatar());
		player.setChapter(message.getChapter());
		player.setPrestige(message.getPrestige());
		player.setKingcitybuffid(message.getKingcitybuffid());
		player.setVipid(message.getVipid());
		player.setLevel(message.getLevel());
		player.setLevelUpTime(message.getLevelUpTime());
		player.setMaxEventcut(message.getEventcut());
		player.setMaxEventcutTime(message.getEventcutTime());
		player.setTotalSkillLevel(message.getSkillLevel());
		player.setSkillUpTime(message.getSkillTime());
		player.setMapModelId(message.getMapId());
		player.getLongyuan().setLysection((byte) message.getLongyuanSection());
		player.getLongyuan().setLylevel((byte) message.getLongyuanLevel());
		player.setLongyuanUpTime(message.getLongyuanTime());

		player.setFightPower(message.getFightPower());

		@SuppressWarnings("unchecked")
		List<Equip> equips = (List<Equip>) JSONserializable.toList(message.getEquip(), Equip.class);
		for (int i = 0; i < player.getEquips().length; i++) {
			player.getEquips()[i] = equips.get(i);
		}

		@SuppressWarnings("unchecked")
		List<List<Gem>> gems = (List<List<Gem>>) JSONserializable.toList(message.getGem(), Gem.class);
		for (int i = 0; i < player.getGems().length; i++) {
			for (int j = 0; j < player.getGems()[i].length; j++) {
				player.getGems()[i][j] = gems.get(i).get(j);
			}
		}

		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) JSONserializable.toList(message.getSkills(), Skill.class);
		player.setSkills(skills);

		setPlayerAttr(player, message.getAttributes());

		Horse horse = new Horse();
		horse.setLayer((short) message.getHorseStage());
		horse.setHorselevel(message.getHorseLevel());
		horse.setSkilllevelsum(message.getHorseSkillLevel());
		horse.setHorseUpTime(message.getHorseTime());

		@SuppressWarnings("unchecked")
		List<HorseEquip> horseequips = (List<HorseEquip>) JSONserializable.toList(message.getHorseEquip(), HorseEquip.class);
		for (int i = 0; i < horse.getHorseequips().length; i++) {
			horse.getHorseequips()[i] = horseequips.get(i);
		}

		@SuppressWarnings("unchecked")
		List<HorseSkill> horseskills = (List<HorseSkill>) JSONserializable.toList(message.getHorseSkill(), HorseSkill.class);
		horse.setSkills(horseskills);

		player.getHorselist().add(horse);

		@SuppressWarnings("unchecked")
		List<Pet> pets = (List<Pet>) JSONserializable.toList(message.getPets(), Pet.class);
		player.setPetList(pets);
		
		ArrowData arrowData = (ArrowData) JSONserializable.toObject(message.getArrowinfo(), ArrowData.class);
		player.setArrowData(arrowData);
		player.setArrowLevel((byte) player.getArrowData().getArrowlv());
		player.setStarlv(player.getArrowData().getStarData().getStarsublv());
		player.setBowlv(player.getArrowData().getBowData().getBowmainlv());
		player.setCostGold(message.getCostgold());

		player.calSkillLinked();
		player.calEquipLinked();
		player.calAttrLinked();

		HorseWeapon horseWeapon = new HorseWeapon();
		horseWeapon.setLevel(message.getHorseweaponlevel());
		horseWeapon.setLayer(message.getHorseweaponlayer());
		try {
			if (message.getHorseWeaponSkill() !=null) {
				@SuppressWarnings("unchecked")
				List<Integer> horsesWeaponkills = (List<Integer>) JSONserializable.toList(message.getHorseWeaponSkill(), Integer.class);
				for (int i = 0; i < horsesWeaponkills.size(); i++) {
					horseWeapon.getSkills()[i]=horsesWeaponkills.get(i);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}

		player.getHorseWeaponlist().add(horseWeapon);
		
		HiddenWeapon hiddenWeapon = new HiddenWeapon();
		hiddenWeapon.setLevel(message.getHiddenweaponlevel());
		hiddenWeapon.setLayer(message.getHiddenweaponlayer());
//		try {
//			if (message.getHiddenWeaponSkill() !=null) {
//				@SuppressWarnings("unchecked")
//				List<Integer> hiddenWeaponkills = (List<Integer>) JSONserializable.toList(message.getHiddenWeaponSkill(), Integer.class);
//				for (int i = 0; i < hiddenWeaponkills.size(); i++) {
//					hiddenWeapon.getSkills()[i]=hiddenWeaponkills.get(i);
//				}
//			}
//		} catch (Exception e) {
//			log.error(e,e);
//		}
		player.getHiddenWeaponlist().add(hiddenWeapon);
		player.setRealmintensifylevel(message.getRealmintensifylevel());
		player.setRealmlevel(message.getRealmlevel());
		
		TopListManager.getInstance().updateTopPlayer(player, true);
	}

	public void syncPlayerAttribute(ReqSyncPlayerAttributeMessage message) {
		TopPlayer player = TopListManager.getTopplayerMap().get(message.getPlayerId());
		if (player == null) {
			return;
		}
		
		FightPowerTop fightPowerTop = new FightPowerTop(player.getId(), message.getFightPower(), player.getLevel());
		TopListManager.getInstance().updateTopData(fightPowerTop);
		
		player.setExp(message.getExp());
		player.setZhenqi(message.getZhenqi());
		player.setAvatar(message.getAvatar());
		player.setChapter(message.getChapter());
		player.setPrestige(message.getPrestige());
		player.setFightPower(message.getFightPower());
		player.setKingcitybuffid(message.getKingcitybuffid());
		player.setVipid(message.getVipid());
		player.setRealmintensifylevel(message.getRealmintensifylevel());
		player.setRealmlevel(message.getRealmlevel());

		@SuppressWarnings("unchecked")
		List<Equip> equips = (List<Equip>) JSONserializable.toList(message.getEquip(), Equip.class);
		for (int i = 0; i < player.getEquips().length; i++) {
			player.getEquips()[i] = equips.get(i);
		}

		@SuppressWarnings("unchecked")
		List<List<Gem>> gems = (List<List<Gem>>) JSONserializable.toList(message.getGem(), Gem.class);
		for (int i = 0; i < player.getGems().length; i++) {
			for (int j = 0; j < player.getGems()[i].length; j++) {
				player.getGems()[i][j] = gems.get(i).get(j);
			}
		}

		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) JSONserializable.toList(message.getSkills(), Skill.class);
		player.setSkills(skills);

		Horse horse = null;
		if (player.getHorselist() != null && player.getHorselist().size() > 0) {
			horse = player.getHorselist().get(0);
		} else {
			horse = new Horse();
			player.getHorselist().add(horse);
		}

		@SuppressWarnings("unchecked")
		List<HorseEquip> horseequips = (List<HorseEquip>) JSONserializable.toList(message.getHorseEquip(), HorseEquip.class);
		for (int i = 0; i < horse.getHorseequips().length; i++) {
			horse.getHorseequips()[i] = horseequips.get(i);
		}

		@SuppressWarnings("unchecked")
		List<HorseSkill> horseskills = (List<HorseSkill>) JSONserializable.toList(message.getHorseSkill(), HorseSkill.class);
		horse.setSkills(horseskills);

		@SuppressWarnings("unchecked")
		List<Pet> pets = (List<Pet>) JSONserializable.toList(message.getPets(), Pet.class);
		player.setPetList(pets);
		
		ArrowData arrowData = (ArrowData) JSONserializable.toObject(message.getArrowinfo(), ArrowData.class);
		player.setArrowData(arrowData);
		ArrowTop arrowTop = new ArrowTop(player.getId(), player.getArrowData().getArrowlv(), player.getArrowData().getStarData().getStarsublv(), player.getArrowData().getBowData().getBowmainlv(), player.getLevel(), message.getCostgold());
		TopListManager.getInstance().updateTopData(arrowTop);

		setPlayerAttr(player, message.getAttributes());

		HorseWeapon horseWeapon = null;
		if (player.getHorseWeaponlist() != null && player.getHorseWeaponlist().size() > 0) {
			horseWeapon = player.getHorseWeaponlist().get(0);
		} else {
			horseWeapon = new HorseWeapon();
			player.getHorseWeaponlist().add(horseWeapon);
		}
		
		if (message.getHorseWeaponSkill() != null ) {
			try {
				@SuppressWarnings("unchecked")//新加骑兵
				List<Integer> horsewHorseskills = (List<Integer>) JSONserializable.toList(message.getHorseWeaponSkill(), Integer.class);
				for (int i = 0; i < horsewHorseskills.size(); i++) {
					horseWeapon.getSkills()[i]=horsewHorseskills.get(i);
				}
			} catch (Exception e) {
				log.error(e,e);
			}

		}
		
		HiddenWeapon hiddenWeapon = null;
		if (player.getHiddenWeaponlist() != null && player.getHiddenWeaponlist().size() > 0) {
			hiddenWeapon = player.getHiddenWeaponlist().get(0);
		} else {
			hiddenWeapon = new HiddenWeapon();
			player.getHiddenWeaponlist().add(hiddenWeapon);
		}
		
		if (message.getHiddenWeaponSkill() != null ) {
			try {
				@SuppressWarnings("unchecked")//新加骑兵
				List<Integer> hiddenHorseskills = (List<Integer>) JSONserializable.toList(message.getHiddenWeaponSkill(), Integer.class);
				for (int i = 0; i < hiddenHorseskills.size(); i++) {
					horseWeapon.getSkills()[i]=hiddenHorseskills.get(i);
				}
			} catch (Exception e) {
				log.error(e,e);
			}

		}
		
		player.calSkillLinked();
		player.calEquipLinked();
		player.calAttrLinked();
	}

	private void setPlayerAttr(TopPlayer player, List<PlayerAttributeItem> attrs) {
		for (int i = 0; i < attrs.size(); i++) {
			PlayerAttributeItem item = attrs.get(i);
			if (item.getType() == Attributes.MAXHP.getValue()) {
				player.setMaxHp(item.getValue());
			} else if (item.getType() == Attributes.MAXMP.getValue()) {
				player.setMaxMp(item.getValue());
			} else if (item.getType() == Attributes.MAXSP.getValue()) {
				player.setMaxSp(item.getValue());
			} else if (item.getType() == Attributes.ATTACK.getValue()) {
				player.setAttack(item.getValue());
			} else if (item.getType() == Attributes.DEFENSE.getValue()) {
				player.setDefense(item.getValue());
			} else if (item.getType() == Attributes.DODGE.getValue()) {
				player.setDodge(item.getValue());
			} else if (item.getType() == Attributes.CRIT.getValue()) {
				player.setCrit(item.getValue());
			} else if (item.getType() == Attributes.LUCK.getValue()) {
				player.setLuck(item.getValue());
			} else if (item.getType() == Attributes.ATTACKSPEED.getValue()) {
				player.setAttackSpeed(item.getValue());
			} else if (item.getType() == Attributes.SPEED.getValue()) {
				player.setSpeed(item.getValue());
			}
		}
	}

	public void insertPlayerWorldInfo(PlayerWorldInfo info) {
		//保存
		try {
			dao.insert(info);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public void savePlayerWorldInfo(PlayerWorldInfo info) {
		//保存
		try {
			dao.update(info);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public void insertUserOnline(User user) {
		//保存
		try {
			userdao.insert(user);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public void updateUserOnline(User user) {
		//保存
		try {
			userdao.update(user);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public void deleteUserOnline(User user) {
		//保存
		try {
			userdao.delete(user);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public Player getPlayer(long roleId) {
		return players.get(roleId);
	}

	/**
	 * 按名字查找在线的玩家
	 *
	 * @param name
	 * @return
	 */
	public Player getOnlinePlayerByName(String name) {
		Set<Long> keySet = players.keySet();
		for (Long roleid : keySet) {
			Player player = players.get(roleid);
			if (player != null && player.getName() != null && player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	public boolean isOnline(long roleId) {
		return players.containsKey(roleId);
	}

	public static ConcurrentHashMap<Long, Player> getPlayers() {
		return players;
	}

	public PlayerWorldInfo getPlayerWorldInfo(long roleId) {
		return allPlayers.get(roleId);
	}

	public PlayerWorldInfo getPlayerWorldInfo(String roleName) {
		for (Map.Entry<Long, PlayerWorldInfo> entry : allPlayers.entrySet()) {
			PlayerWorldInfo playerWorldInfo = entry.getValue();
			if (playerWorldInfo.getName().equalsIgnoreCase(roleName)) {
				return playerWorldInfo;
			}
		}
		return null;
	}

	public static ConcurrentHashMap<String, User> getUserTimes() {
		return userTimes;
	}

	public static void setUserTimes(ConcurrentHashMap<String, User> userTimes) {
		PlayerManager.userTimes = userTimes;
	}

	public List<Player> getPlayersByUser(String userId) {
		return userPlayers.get(userId);
	}

	/**
	 *
	 * @param userId 账号ID
	 * @param serverId 创建服ID
	 * @return
	 */
	public List<Player> getPlayerByUserServerId(String userId, int serverId) {
		List<Player> playersByUser = getPlayersByUser(userId);
		List<Player> result = new ArrayList<Player>();
		if (playersByUser != null) {
			for (Player player : playersByUser) {
				if (player.getServer() == serverId) {
					result.add(player);
				}
			}
		}
		return result;
	}

	/**
	 * 检查指定玩家是否在线
	 *
	 * @param msg
	 */
	public void stReqPlayerCheckOnlineToWorldMessage(ReqPlayerCheckOnlineToWorldMessage msg) {
		Player player = getPlayer(msg.getPlayerId());
		if (player != null) {
			ResPlayerCheckOnlineMessage wmsg = new ResPlayerCheckOnlineMessage();
			Player Other = getPlayer(msg.getOthersid());
			if (Other != null) {
				wmsg.setIsonline((byte) 1);
				wmsg.setOthersname(Other.getName());
			}
			wmsg.setOthersid(msg.getOthersid());
			wmsg.setType(msg.getType());
			wmsg.setX(msg.getX());
			wmsg.setY(msg.getY());
			MessageUtil.tell_player_message(player, wmsg);
		}
	}

	/**
	 * 发送玩家外观消息
	 *
	 * @param msg
	 */
	public void stReqGetPlayerAppearanceInfoToWorldMessage(ReqGetPlayerAppearanceInfoToWorldMessage msg) {
		Player player = getPlayer(msg.getPlayerId());
		if (player != null) {
			Player Other = getPlayer(msg.getOthersid());
			if (Other != null) {
				ResGetPlayerAppearanceInfMessage wmsg = new ResGetPlayerAppearanceInfMessage();
				wmsg.setAppearanceInfo(getPlayerAppearanceInfo(Other));
				wmsg.setOthersid(Other.getId());
				wmsg.setType(msg.getType());
				MessageUtil.tell_player_message(player, wmsg);
			}
		}
	}

	/**
	 * 生成玩家外观造型 结构
	 *
	 * @param player
	 * @return
	 */
	public PlayerAppearanceInfo getPlayerAppearanceInfo(Player player) {
		PlayerAppearanceInfo appearanceInfo = new PlayerAppearanceInfo();
		appearanceInfo.setClothingmodid(player.getClothingmodid());
		appearanceInfo.setHorsemodid(player.getHorsemodid());
		appearanceInfo.setHorseweaponmodid(player.getHorseweaponid());
		appearanceInfo.setWeaponmodid(player.getWeaponmodid());
		appearanceInfo.setWeaponStreng(player.getWeaponStreng());
		appearanceInfo.setSex(player.getSex());
		appearanceInfo.setAvatarid(player.getAvatarid());
		appearanceInfo.setArrowid(player.getArrowLevel());
		appearanceInfo.setHiddenweaponmodid(player.getHiddenweaponid());
		return appearanceInfo;
	}

	/**
	 * 删除角色
	 *
	 * @param msg
	 */
	public void stReqDelPlayerToWorldMessage(ReqDelPlayerToWorldMessage msg) {
		int createServer = msg.getCreateServer();
		String userId = msg.getUserId();
		long rid = msg.getPlayerId();
		List<Role> rolelest = null;
		try {
			rolelest = getRoleDao().selectDelByUser(userId, createServer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResDelPlayerStatusMessage cmsg = new ResDelPlayerStatusMessage();
		cmsg.setPlayerId(rid);
		cmsg.getRoleId().add(rid);
		cmsg.setUserId(userId);
		cmsg.setCreateServer(createServer);
		//最大删除数量4个
		if (rolelest != null && rolelest.size() >= 4) {
			cmsg.setType((byte) 2);
			MessageUtil.send_to_gate(msg.getGateId(), cmsg);
			return;
		}

		Role role = null;
		try {
			role = getRoleDao().selectById(rid);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (!userId.equals(role.getUserid())) {
			cmsg.setType((byte) 1);
			MessageUtil.send_to_gate(msg.getGateId(), cmsg);
			return;
		}
		if (role != null) {
			//帮主不能删除
			Guild guild = GuildWorldManager.getInstance().getGuildByUserId(role.getRoleid());
			if (guild != null) {
				MemberInfo memberInfo = guild.findMemberInfoById(role.getRoleid());
				if (memberInfo != null) {
					if (memberInfo.getGuildPowerLevel() == 1) {
						cmsg.setType((byte) 3);
						MessageUtil.send_to_gate(msg.getGateId(), cmsg);
						return;
					} else {
						GuildWorldManager.getInstance().deleteGuildData(2, guild, memberInfo);
						guild.removeMemberById(role.getRoleid());
						guild.Update();
						GuildWorldManager.getInstance().saveGuildData(7, guild, null);
						guild.boardcastInfo(GuildWorldManager.getInstance().Notify_AddOrUpdate);
						GuildWorldManager.getInstance().sendAllMemberInfo(GuildWorldManager.getInstance().Notify_AddOrUpdate, guild);
						GuildWorldManager.getInstance().boardcastGuildInfo(null, guild);
					}
				}
			}
//			//好友
//			PlayerRelation playerRelation = FriendManager.getInstance().getPlayerRelation(role.getRoleid());
//			if (playerRelation != null) {
//				//删除所有关注他的人的关系
//				for (Map.Entry<Long, RelationInfo> entry : playerRelation.getAttentionHashMap().entrySet()) {
//					RelationInfo relationInfo = entry.getValue();
//					if (relationInfo != null) {
//						PlayerRelation destPlayerRelation = FriendManager.getInstance().getPlayerRelation(relationInfo.getLgUserId());
//						if (destPlayerRelation != null) {
//							switch (relationInfo.getBtListType()) {
//								case 1: {
//									if (destPlayerRelation.getFriendHashMap().containsKey(playerRelation.getOwnPlayerId())) {
//										destPlayerRelation.getFriendHashMap().remove(playerRelation.getOwnPlayerId());
//										FriendManager.getInstance().sortRelationList(destPlayerRelation.getFriendHashMap());
//										FriendManager.getInstance().savePlayerRelationData(destPlayerRelation);
//									}
//								}
//								break;
//								case 2: {
//									if (destPlayerRelation.getEnemyHashMap().containsKey(playerRelation.getOwnPlayerId())) {
//										destPlayerRelation.getEnemyHashMap().remove(playerRelation.getOwnPlayerId());
//										FriendManager.getInstance().sortRelationList(destPlayerRelation.getEnemyHashMap());
//										FriendManager.getInstance().savePlayerRelationData(destPlayerRelation);
//									}
//								}
//								break;
//							}
//							Player destplayer = PlayerManager.getInstance().getPlayer(relationInfo.getLgUserId());
//							if (destplayer != null) {
//								ResRelationGetListToClientMessage sendMessage = new ResRelationGetListToClientMessage();
//								sendMessage.setRelationMyInfo(new RelationInfo());
//								FriendManager.getInstance().getPlayerInfo(sendMessage.getRelationMyInfo(), destplayer, null);
//								switch (relationInfo.getBtListType()) {
//									case 0: {
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getFriendHashMap());
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getEnemyHashMap());
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getRecentcontactpersonHashMap());
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getBlackHashMap());
//									}
//									break;
//									case 1: {
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getFriendHashMap());
//									}
//									break;
//									case 2: {
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getEnemyHashMap());
//									}
//									break;
//									case 3: {
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getRecentcontactpersonHashMap());
//									}
//									break;
//									case 4: {
//										FriendManager.getInstance().getRelationMapInfoToList(sendMessage.getRelationList(), destPlayerRelation.getBlackHashMap());
//									}
//									break;
//								}
//								MessageUtil.tell_player_message(destplayer, sendMessage);
//							}
//						}
//					}
//				}
//				playerRelation.getAttentionHashMap().clear();
//				//删除它所有的关注人
//				
//			}
		}


		try {
			int status = getRoleDao().update(role);
			cmsg.setType((byte) status);
			MessageUtil.send_to_gate(msg.getGateId(), cmsg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			GameUser selectGameUser = userdao.selectGameUser(Long.parseLong(role.getUserid()), createServer);
			DelRoleLog log = new DelRoleLog();
			log.setActionId(Config.getId());
			log.setIp(msg.getOptIp());
			log.setRoleId(role.getRoleid());
			log.setRolename(role.getName());
			log.setUserId(role.getUserid());
			log.setYyuserid(selectGameUser.getUsername());
			log.setSid(role.getCreateServer());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	/**
	 * 检查玩家名字是否重名
	 *
	 * @param name 玩家名字
	 */
	public boolean checkPlayer(String name) {
		try {
			return roleDao.selectByName(name) > 0;
		} catch (SQLException e) {
			log.error(e);
		}

		return true;
	}

	/**
	 * 修改玩家角色名
	 *
	 * @param msg
	 */
	public void stReqChangePlayerNameToWorldMessage(ReqChangePlayerNameToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerId());
		if (player != null) {
			String newname = msg.getNewname();
			if (checkPlayer(newname)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("已有相同名字存在"));
				return;
			}
			if (newname != null && newname.length() > 0) {
				PlayerWorldInfo info = allPlayers.get(msg.getPlayerId());
				if (info == null) {
					log.error("ChangePlayerName " + msg.getPlayerId() + " is null");
				} else {
					//if (info.getChangeName() == 1) { //原来游客模式改名需要这个检测
						Role role = null;
						try {
							role = roleDao.selectById(msg.getPlayerId());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (role != null) {
							role.setName(newname);
							try {
								int result = roleDao.updateName(role);
								if (result == 1) {
									player.setName(newname);
									info.setLevel(player.getLevel());
									info.setChangeName(0);		//修改内存里的标记
									info.setName(newname);
									savePlayerWorldInfo(info);
									ResChangePlayerNameToGameMessage smsg = new ResChangePlayerNameToGameMessage();
									smsg.setNewname(newname);
									smsg.setPlayerId(msg.getPlayerId());
									smsg.setResult((byte) result);
									MessageUtil.send_to_game(player, smsg);	//修改成功通知
									ResPlayerNameInfoToGameMessage infomsg = new ResPlayerNameInfoToGameMessage();
									infomsg.setChangeName((byte) info.getChangeName());
									infomsg.setChangeUser((byte) info.getChangaccount());
									infomsg.setPlayerId(player.getId());
									MessageUtil.send_to_game(player, infomsg);		//通知GAME标记改变
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} else {
							log.error("role " + msg.getPlayerId() + " is null");
						}
					}
				//}
			}
		}
	}

	public void reqScriptCommonServerToWorldMessage(ReqScriptCommonServerToWorldMessage message) {
		if (message.getScriptid() == 4003) {
			TopListManager.getInstance().reqScriptCommonServerToWorldMessage(message);
		} else if (message.getScriptid() == 1200) {
			RegistrarManager.getInstance().SWDispatch(message);
		} else if (message.getScriptid() == 1201) {
			RegistrarManager.getInstance().SWDispatchRecharge(message);
		}
	}

	public void kickPlayer(String name) {
		Player player = getOnlinePlayerByName(name);
		if (player != null) {
			ReqKickPlayerMessage msg = new ReqKickPlayerMessage();
			msg.setPlayerId(player.getId());
			msg.setUserId(player.getUserId());
			MessageUtil.send_to_gate(player.getGateId(), msg);
		}
	}

	/**
	 * 得到玩家名字
	 *
	 * @return
	 */
	public String getPlayerName(long pid) {
		PlayerWorldInfo worldInfo = ManagerPool.playerManager.getPlayerWorldInfo(pid);
		if (worldInfo != null) {
			return worldInfo.getName();
		}
		return ResManager.getInstance().getString("未知");
	}

	public void clearPlayerWorldInfo() {
		Iterator<PlayerWorldInfo> iterator = allPlayers.values().iterator();
		while (iterator.hasNext()) {
			PlayerWorldInfo playerWorldInfo = iterator.next();
			if (playerWorldInfo != null) {
				//TopListManager.getInstance().clearWorshipNum(playerWorldInfo);
			}
		}
	}
	
	//得到账号禁言set
	public Set<String> getChatBlackUsernames() {
		return chatBlackUsernames;
	}
	//得到IP禁言set
	public Set<String> getChatBlackIPs() {
		return chatBlackIPs;
	}
	//得到测试GM IP set
	public Set<String> getTestGmIPs() {
		return testGmIPs;
	}
	
}
