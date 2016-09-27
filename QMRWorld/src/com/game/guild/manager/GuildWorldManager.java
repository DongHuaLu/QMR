package com.game.guild.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.config.Config;
import com.game.country.manager.CountryManager;
import com.game.data.bean.Q_guildbannerBean;
import com.game.data.dao.Q_guildbannerDao;
import com.game.db.bean.GuildBean;
import com.game.db.dao.GuildDao;
import com.game.db.dao.GuildeventDao;
import com.game.db.dao.GuildmemberDao;
import com.game.dblog.LogService;
import com.game.guild.bean.DiplomaticInfo;
import com.game.guild.bean.EventInfo;
import com.game.guild.bean.FriendGuild;
import com.game.guild.bean.GuildInfo;
import com.game.guild.bean.GuildShortInfo;
import com.game.guild.bean.MemberInfo;
import com.game.guild.log.GuildLog;
import com.game.guild.message.ReqInnerGuildAddDiplomaticToWorldMessage;
import com.game.guild.message.ReqInnerGuildAddMemberToWorldMessage;
import com.game.guild.message.ReqInnerGuildApplyAddToWorldMessage;
import com.game.guild.message.ReqInnerGuildAutoArgeeAddGuildToWorldMessage;
import com.game.guild.message.ReqInnerGuildAutoGuildArgeeAddToWorldMessage;
import com.game.guild.message.ReqInnerGuildBannerLevelUpToWorldMessage;
import com.game.guild.message.ReqInnerGuildChangeBannerIconToWorldMessage;
import com.game.guild.message.ReqInnerGuildChangeBannerNameToWorldMessage;
import com.game.guild.message.ReqInnerGuildChangeBulletinToWorldMessage;
import com.game.guild.message.ReqInnerGuildChangeNickNameToWorldMessage;
import com.game.guild.message.ReqInnerGuildChangePowerLevelToWorldMessage;
import com.game.guild.message.ReqInnerGuildCreateToWorldMessage;
import com.game.guild.message.ReqInnerGuildDeleteDiplomaticToWorldMessage;
import com.game.guild.message.ReqInnerGuildDeleteGuildToWorldMessage;
import com.game.guild.message.ReqInnerGuildDeleteMemberToWorldMessage;
import com.game.guild.message.ReqInnerGuildGetEventListToWorldMessage;
import com.game.guild.message.ReqInnerGuildGetGuildListToWorldMessage;
import com.game.guild.message.ReqInnerGuildGetMemberListToWorldMessage;
import com.game.guild.message.ReqInnerGuildInviteAddToWorldMessage;
import com.game.guild.message.ReqInnerGuildNotifyToWorldMessage;
import com.game.guild.message.ReqInnerGuildQuitToWorldMessage;
import com.game.guild.message.ReqInnerGuildSubmitItemToWorldMessage;
import com.game.guild.message.ReqInnerKingCityEventToWorldMessage;
import com.game.guild.message.ResFriendGuildListToGameMessage;
import com.game.guild.message.ResFriendGuildToGameMessage;
import com.game.guild.message.ResGuildAddDiplomaticToClientMessage;
import com.game.guild.message.ResGuildAddMemberToClientMessage;
import com.game.guild.message.ResGuildApplyAddDoingToClientMessage;
import com.game.guild.message.ResGuildAutoArgeeAddGuildToClientMessage;
import com.game.guild.message.ResGuildAutoGuildArgeeAddToClientMessage;
import com.game.guild.message.ResGuildBannerLevelUpToClientMessage;
import com.game.guild.message.ResGuildChangeBannerIconToClientMessage;
import com.game.guild.message.ResGuildChangeBannerNameToClientMessage;
import com.game.guild.message.ResGuildChangeBulletinToClientMessage;
import com.game.guild.message.ResGuildChangeNickNameToClientMessage;
import com.game.guild.message.ResGuildChangePowerLevelToClientMessage;
import com.game.guild.message.ResGuildCreateToClientMessage;
import com.game.guild.message.ResGuildDeleteDiplomaticToClientMessage;
import com.game.guild.message.ResGuildDeleteGuildToClientMessage;
import com.game.guild.message.ResGuildDeleteMemberToClientMessage;
import com.game.guild.message.ResGuildGetEventListToClientMessage;
import com.game.guild.message.ResGuildGetGuildListToClientMessage;
import com.game.guild.message.ResGuildGetGuildShortInfoListToClientMessage;
import com.game.guild.message.ResGuildGetMemberListToClientMessage;
import com.game.guild.message.ResGuildInviteAddDoingToClientMessage;
import com.game.guild.message.ResGuildQuitToClientMessage;
import com.game.guild.message.ResGuildSubmitItemToClientMessage;
import com.game.guild.message.ResInnerGuildAloneGuildInfoToServerMessage;
import com.game.guild.message.ResInnerGuildAloneMemberInfoToServerMessage;
import com.game.guild.structs.ApplyAndInvite;
import com.game.guild.structs.Guild;
import com.game.guild.structs.GuildBanner;
import com.game.guild.structs.GuildData;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.server.WorldServer;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;

/**
 * @author 杨洪岚 帮会管理类(世界服务器)
 */
public class GuildWorldManager {

	private Logger log = Logger.getLogger(GuildWorldManager.class);
	private static Object obj = new Object();
	//帮会管理类实例
	private static GuildWorldManager manager;

	private GuildWorldManager() {
	}

	public static GuildWorldManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new GuildWorldManager();
			}
		}
		return manager;
	}
	//帮会数据接口
	private GuildDao guildDao = new GuildDao();
	private GuildmemberDao guildmemberDao = new GuildmemberDao();
	private GuildeventDao guildeventDao = new GuildeventDao();

	public GuildDao getGuildDao() {
		return guildDao;
	}

	public GuildmemberDao getGuildmemberDao() {
		return guildmemberDao;
	}

	public GuildeventDao getGuildeventDao() {
		return guildeventDao;
	}

	public boolean deleteGuild(Guild guild) {
		try {
			if (getGuildDao().delete(guild.getGuildInfo().getGuildId()) == 0) {
				log.error(String.format("帮会数据删除错误，帮会id[%s]", Long.toString(guild.getGuildInfo().getGuildId())));
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void saveGuild(Guild guild) {
		try {
			if (!guild.getMemberinfoHashMap().isEmpty()) {
				GuildBean guildBean = new GuildBean();
				guildBean.setGuildid(guild.getGuildInfo().getGuildId());
				guildBean.setGuildName(guild.getGuildInfo().getGuildName());
				GuildData guildData = new GuildData();
				guildData.toData(guild.getGuildInfo());
				guildBean.setGuilddata(JSONserializable.toString(guildData));
				guildBean.setGuildmsgdata(JSONserializable.toString(guild.getSaveSendMsgList()));
				guildBean.setGuildactivevalue(JSON.toJSONString(guild.getActiveValueList()));
				guildBean.setGuildcalactivevaluetime(guild.getCalActiveValueTime());
				guildBean.setGuildfightpower(guild.getGuildInfo().getMemberFightPower());
				if (getGuildDao().update(guildBean) == 0) {
					if (getGuildDao().insert(guildBean) == 0) {
						log.error(String.format("帮会数据保存错误，帮会id[%d](%s)", guild.getGuildInfo().getGuildId(), JSON.toJSONString(guildBean)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveAllGuild() {
		for (Map.Entry<Long, Guild> entry : getGuildMapManager().entrySet()) {
			Guild guild = entry.getValue();
			if (guild != null) {
				saveGuild(guild);
				guild.saveAll();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loadallGuild() {
		try {
			List<GuildBean> list = getGuildDao().select();
			Iterator<GuildBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				GuildBean guildBean = (GuildBean) iterator.next();
				if (guildBean != null) {
					Guild guild = new Guild();
					GuildData guildData = (GuildData) JSONserializable.toObject(guildBean.getGuilddata(), GuildData.class);
					GuildInfo guildInfo = guildData.toInfo();
					List<ApplyAndInvite> applyList = new ArrayList<ApplyAndInvite>();
					applyList = (List<ApplyAndInvite>) JSONserializable.toList(guildBean.getGuildmsgdata(), ApplyAndInvite.class);
					List<Long> activeValueList = new ArrayList<Long>();
					activeValueList = JSON.parseArray(guildBean.getGuildactivevalue(), Long.class);
					if (guildInfo != null && applyList != null && activeValueList != null) {
						guild.setGuildInfo(guildInfo);
						if (guild.getGuildInfo().getBannerIcon() == 1) {
							guild.getGuildInfo().setBannerIcon(3000);
						}
						guild.setSaveSendMsgList(applyList);
						guild.setActiveValueList(activeValueList);
						guild.setCalActiveValueTime(guildBean.getGuildcalactivevaluetime());
						guild.loadAll();
						if (!guild.getMemberinfoHashMap().isEmpty()) {
							getGuildMapManager().put(guild);
						} else {
							log.error(String.format("帮会成员为零，帮会id[%s]名字[%s]", Long.toString(guildBean.getGuildid()), guildBean.getGuildName()));
						}
					} else {
						log.error(String.format("帮会数据读取错误，帮会id[%s]名字[%s]", Long.toString(guildBean.getGuildid()), guildBean.getGuildName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//帮会旗帜配置数据
	private Q_guildbannerDao guildbannerDao = new Q_guildbannerDao();

	public Q_guildbannerDao getGuildbannerDao() {
		return guildbannerDao;
	}
	private HashMap<Integer, GuildBanner> guildBannerMap = new HashMap<Integer, GuildBanner>();

	public HashMap<Integer, GuildBanner> getGuildBannerMap() {
		return guildBannerMap;
	}

	public void loadBannerData() {
		try {
			List<Q_guildbannerBean> list = getGuildbannerDao().select();
			Iterator<Q_guildbannerBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				Q_guildbannerBean guildbannerBean = (Q_guildbannerBean) iterator.next();
				if (guildbannerBean != null) {
					GuildBanner guildBanner = new GuildBanner();
					guildBanner.getBannerInfo(guildbannerBean);
					getGuildBannerMap().put(guildBanner.getGuildbannerlv(), guildBanner);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//帮会HashMapManager
	private class GuildMapManager {

		private HashMap<Long, Guild> guildIdMap = new HashMap<Long, Guild>();			//帮会ID索引
		private HashMap<String, Guild> guildNameMap = new HashMap<String, Guild>();		//帮会名字索引
		private HashMap<String, Guild> guildBannerNameMap = new HashMap<String, Guild>();	//帮会旗帜名字索引

		public HashMap<Long, Guild> getGuildIdMap() {
			return guildIdMap;
		}

		public boolean put(Guild guild) {
			if (!guildIdMap.containsKey(guild.getGuildInfo().getGuildId())) {
				if (!guildNameMap.containsKey(guild.getGuildInfo().getGuildName())) {
					if (!guildBannerNameMap.containsKey(guild.getGuildInfo().getGuildBanner())) {
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
						guildBannerNameMap.put(guild.getGuildInfo().getGuildBanner(), guild);
						return true;
					} else {
						guild.getGuildInfo().setGuildBanner(guild.getGuildInfo().getGuildName());
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
						guildBannerNameMap.put(guild.getGuildInfo().getGuildBanner(), guild);
						return true;
					}
				}
			}
			return false;
		}

		public boolean remove(Guild guild) {
			if (guildIdMap.remove(guild.getGuildInfo().getGuildId()) != null) {
				if (guildNameMap.remove(guild.getGuildInfo().getGuildName()) != null) {
					if (guildBannerNameMap.remove(guild.getGuildInfo().getGuildBanner()) != null) {
						return true;
					} else {
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
					}
				} else {
					guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
				}
			}
			return false;
		}

		@SuppressWarnings("unused")
		public boolean removeId(Long guildid) {
			Guild guild = guildIdMap.remove(guildid);
			if (guild != null) {
				if (guildNameMap.remove(guild.getGuildInfo().getGuildName()) != null) {
					if (guildBannerNameMap.remove(guild.getGuildInfo().getGuildBanner()) != null) {
						return true;
					} else {
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
					}
				} else {
					guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
				}
			}
			return false;
		}

		@SuppressWarnings("unused")
		public boolean removeName(String guildname) {
			Guild guild = guildNameMap.remove(guildname);
			if (guild != null) {
				if (guildIdMap.remove(guild.getGuildInfo().getGuildId()) != null) {
					if (guildBannerNameMap.remove(guild.getGuildInfo().getGuildBanner()) != null) {
						return true;
					} else {
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
					}
				} else {
					guildNameMap.put(guild.getGuildInfo().getGuildName(), guild);
				}
			}
			return false;
		}

		@SuppressWarnings("unused")
		public boolean removeBannerName(String guildBannerName) {
			Guild guild = guildBannerNameMap.remove(guildBannerName);
			if (guild != null) {
				if (guildIdMap.remove(guild.getGuildInfo().getGuildId()) != null) {
					if (guildNameMap.remove(guild.getGuildInfo().getGuildName()) != null) {
						return true;
					} else {
						guildIdMap.put(guild.getGuildInfo().getGuildId(), guild);
						guildBannerNameMap.put(guild.getGuildInfo().getGuildBanner(), guild);
					}
				} else {
					guildBannerNameMap.put(guild.getGuildInfo().getGuildBanner(), guild);
				}
			}
			return false;
		}

		public Guild FindById(Long guildid) {
			return guildIdMap.get(guildid);
		}

		public Guild FindByName(String guildname) {
			return guildNameMap.get(guildname);
		}

		public Guild FindByBannerName(String guildBannerName) {
			return guildBannerNameMap.get(guildBannerName);
		}

		public Set<Map.Entry<Long, Guild>> entrySet() {
			return guildIdMap.entrySet();
		}
	}
	private GuildMapManager guildMapManager = new GuildMapManager();

	private GuildMapManager getGuildMapManager() {
		return guildMapManager;
	}

	public Guild getGuild(long guildid) {
		return getGuildMapManager().FindById(guildid);
	}

	public Set<Map.Entry<Long, Guild>> entrySet() {
		return getGuildMapManager().entrySet();
	}
	//帮会限制条件
	@SuppressWarnings("unused")
	private int Create_Need_Gold = 30000000;	//创建帮会所需铜币
	@SuppressWarnings("unused")
	private short Create_Need_Level = 30;		//创建帮会所需等级
	private byte Guild_MaxNum = 100;		//帮会最大人数
//	private String Default_Bulletin = "帮主很懒，什么话都没写，大家一起鄙视他。\n最多100个汉字。";	//默认公告
	private String[] Default_Power = {ResManager.getInstance().getString("帮主"), ResManager.getInstance().getString("副帮主"), ResManager.getInstance().getString("堂主"), ResManager.getInstance().getString("会主"), ResManager.getInstance().getString("门主"), ResManager.getInstance().getString("普通成员")};			//权限名字
	//错误代码
	private byte Error_Fail = -1;		//失败
	private byte Error_Success = 0;		//成功
	private byte Error_AddGuild = 1;	//广播添加帮会信息
	//通知类型
	public byte Notify_Create = 0;		//创建
	public byte Notify_AddOrUpdate = 1;	//添加或更新
	public byte Notify_Delete = 2;		//删除
	public byte Notity_DiplomaticChange = 3;//外交关系改变
	public byte Notity_AddMember = 4;	//添加帮会成员
	public byte Notity_KingCity = 5;	//王城帮派(领地争夺)
	public byte Notity_DeleteGuild = 6;	//解散帮派
	public byte Notity_ChangeBanner = 7;	//改变帮旗
	public byte Notity_BannerBuff = 8;	//帮旗BUFF
	private List<ApplyAndInvite> applyAndInvitesList = new ArrayList<ApplyAndInvite>();

	public List<ApplyAndInvite> getApplyAndInvitesList() {
		return applyAndInvitesList;
	}

	public void deleteApplyAndInvite(long userid) {
		Iterator<ApplyAndInvite> iterator = getApplyAndInvitesList().iterator();
		while (iterator.hasNext()) {
			ApplyAndInvite applyAndInvite = iterator.next();
			if (applyAndInvite != null) {
				if (applyAndInvite.getType() == ApplyAndInvite.Apply_Type && applyAndInvite.getSrcid() == userid) {
					iterator.remove();
				} else if (applyAndInvite.getType() == ApplyAndInvite.Invite_Type && applyAndInvite.getDestid() == userid) {
					iterator.remove();
				}
			}
		}
	}
	//--------------------------外部接口------------------------//

	/**
	 * 判断是否王城帮派
	 *
	 * @param guild 帮派
	 * @return true 是 false 不是
	 */
	public boolean checkKingCityGuild(Guild guild) {
		if (!guild.getMemberinfoHashMap().isEmpty()) {
			MemberInfo memberInfo = (MemberInfo) guild.getMemberinfoHashMap().values().toArray()[0];
			if (memberInfo != null) {
				PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(memberInfo.getUserId());
				if (playerWorldInfo != null) {
					if (CountryManager.kingcitymap.containsKey(playerWorldInfo.getCountry())) {
						long kingguildid = CountryManager.kingcitymap.get(playerWorldInfo.getCountry());
						return guild.getGuildInfo().getGuildId() == kingguildid;
					}
				}
			}
		}
		return false;
	}

	public Guild getGuildByUserId(long userid) {
		for (Map.Entry<Long, Guild> entry : this.entrySet()) {
			Guild guild = entry.getValue();
			if (guild != null && guild.getMemberinfoHashMap().containsKey(userid)) {
				return guild;
			}
		}
		return null;
	}

	public void loginSendGuildAndMemberInfo(Player player) {
		Guild guild = getGuild(player.getGuildid());

		if (guild != null) {
			MemberInfo memberInfo = guild.findMemberInfo(player);
			if (memberInfo != null) {
				guild.calActiveValue(player);
				sendAllGuildShortInfoToSelf(player);
				sendAllMemberInfoToSelf(player, guild);
				sendGuildAndMemberInfo(null, player, Notify_AddOrUpdate, guild);
				sendGuildInfo(player, Notity_KingCity, guild);
				sendGuildInfo(player, Notity_BannerBuff, guild);
				if (memberInfo.getGuildPowerLevel() == 1) {
					guild.sendIcoMsgList(player);
				}
				log.error(String.format("发送登陆公会信息，是成员，全部发送==%s", JSON.toJSONString(memberInfo)));
			} else {
				player.setGuildid(0);
				if (player.getLastAfkGuildTime() == 0) {
					player.setLastAfkGuildTime((int) (System.currentTimeMillis() / 1000));
				}
				sendAllGuildShortInfoToSelf(player);
				log.error("发送登陆公会信息，不是成员");
			}
		} else {
			player.setGuildid(0);
			if (player.getLastAfkGuildTime() == 0) {
				player.setLastAfkGuildTime((int) (System.currentTimeMillis() / 1000));
			}
			sendAllGuildShortInfoToSelf(player);
			log.error("发送登陆公会信息，没有公会");
		}
	}

	public void quitClearGuildOtherData(Player player) {
		Guild guild = getGuild(player.getGuildid());
		if (guild != null) {
			guild.Update();
			deleteApplyAndInvite(player.getId());
			MemberInfo memberInfo = guild.findMemberInfo(player);
			if (memberInfo != null) {
				guild.setBasicInfo(memberInfo, player, guild.getGuildInfo());
				if (memberInfo.getGuildPowerLevel() == 1) {
					guild.getGuildapplyAndInviteList().clear();
					boardcastGuildInfo(player, guild);
				}
				if (memberInfo.getGuildPowerLevel() == 2) {
					boardcastGuildInfo(player, guild);
				}
				saveGuildData(3, guild, memberInfo);
			}
		}
	}
	//--------------------------成员函数------------------------//

	public void boardcastGuildInfo(Player player, Guild guild) {
		if (guild != null) {
			ResGuildGetGuildListToClientMessage sendMessage = new ResGuildGetGuildListToClientMessage();
			sendMessage.setBtErrorCode(Error_AddGuild);
			sendMessage.getGuildList().add(guild.getGuildInfo());
			if (player == null) {
				MessageUtil.tell_world_message(sendMessage);
			} else {
				List<Player> players = new ArrayList<Player>();
				for (Map.Entry<Long, Player> entry : PlayerManager.getPlayers().entrySet()) {
					Player allplayer = entry.getValue();
					if (allplayer != null) {
						if (allplayer == player) {
							continue;
						} else {
							players.add(allplayer);
						}
					}
				}
				MessageUtil.tell_player_message(players, sendMessage);
			}
		}
	}

	public void sendGuildInfo(Player player, byte notify, Guild guild) {
		if (player != null) {
			ResInnerGuildAloneGuildInfoToServerMessage guildInfoToServerMessage = new ResInnerGuildAloneGuildInfoToServerMessage();
			guildInfoToServerMessage.setNotifyType(notify);
			guildInfoToServerMessage.setGuildInfo(new GuildInfo());
			if (player.getGuildid() == guild.getGuildInfo().getGuildId()) {
				guildInfoToServerMessage.setGuildInfo(guild.getGuildInfo());
				if (notify == Notity_KingCity) {
					log.error(String.format("发送公会信息=playerid=%s=notify=%d=guildid=%s", String.valueOf(player.getId()), notify, String.valueOf(player.getGuildid())));
				}
			}
			MessageUtil.send_to_game(player, guildInfoToServerMessage);
		}
	}

	public void sendMemberInfo(Player toPlayer, Player player, byte notify, Guild guild) {
		if (player != null) {
			ResInnerGuildAloneMemberInfoToServerMessage memberInfoToServerMessage = new ResInnerGuildAloneMemberInfoToServerMessage();
			memberInfoToServerMessage.setNotifyType(notify);
			memberInfoToServerMessage.setMemberInfo(new MemberInfo());
			MemberInfo memberInfo = guild.findMemberInfo(player);
			if (memberInfo != null) {
				memberInfoToServerMessage.setMemberInfo(memberInfo);
			}
			if (toPlayer != null) {
				MessageUtil.send_to_game(toPlayer, memberInfoToServerMessage);
			} else {
				MessageUtil.send_to_game(player, memberInfoToServerMessage);
			}
		}
	}

	public void sendMemberInfoById(Player toPlayer, long playerId, byte notify, Guild guild) {
		if (toPlayer != null) {
			ResInnerGuildAloneMemberInfoToServerMessage memberInfoToServerMessage = new ResInnerGuildAloneMemberInfoToServerMessage();
			memberInfoToServerMessage.setNotifyType(notify);
			memberInfoToServerMessage.setMemberInfo(new MemberInfo());
			MemberInfo memberInfo = guild.findMemberInfoById(playerId);
			if (memberInfo != null) {
				memberInfoToServerMessage.setMemberInfo(memberInfo);
			}
			MessageUtil.send_to_game(toPlayer, memberInfoToServerMessage);
		}
	}

	public void sendGuildAndMemberInfo(Player toPlayer, Player player, byte notify, Guild guild) {
		if (toPlayer != null) {
			sendGuildInfo(toPlayer, notify, guild);
		} else {
			sendGuildInfo(player, notify, guild);
		}
		sendMemberInfo(toPlayer, player, notify, guild);
	}

	public void sendGuildAndMemberInfoById(Player toPlayer, long playerId, byte notify, Guild guild) {
		if (toPlayer != null) {
			sendGuildInfo(toPlayer, notify, guild);
			sendMemberInfoById(toPlayer, playerId, notify, guild);
		}
	}

	public void sendAllGuildInfo(byte notify, Guild guild) {
		for (Map.Entry<Long, MemberInfo> entry : guild.getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
					sendGuildInfo(player, notify, guild);
				}
			}
		}
	}

	public void sendAllMemberInfo(byte notify, Guild guild) {
		List<Player> playerList = new ArrayList<Player>();
		ResGuildGetMemberListToClientMessage sendMessage = new ResGuildGetMemberListToClientMessage();
		sendMessage.setBtErrorCode(Error_Success);
		for (Map.Entry<Long, MemberInfo> entry : guild.getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
					playerList.add(player);
				}
				sendMessage.getMemberList().add(memberInfo);
			}
		}
		MessageUtil.tell_player_message(playerList, sendMessage);
	}

	public void sendAllGuildAndMemberInfo(byte notify, Guild guild) {
		sendAllGuildInfo(notify, guild);
		sendAllMemberInfo(notify, guild);
	}

	public void sendAllGuildInfoToSelf(Player player) {
		ResGuildGetGuildListToClientMessage sendMessage = new ResGuildGetGuildListToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		for (Map.Entry<Long, Guild> entry : getGuildMapManager().getGuildIdMap().entrySet()) {
			Guild guild = entry.getValue();
			if (guild != null) {
				guild.Update();
				sendMessage.getGuildList().add(guild.getGuildInfo());
			}
		}
		sendMessage.setBtErrorCode(Error_Success);
		MessageUtil.tell_player_message(player, sendMessage);
	}

	public void sendAllMemberInfoToSelf(Player player, Guild guild) {
		if (guild != null) {
			ResGuildGetMemberListToClientMessage sendMessage = new ResGuildGetMemberListToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			guild.Update();
			for (Map.Entry<Long, MemberInfo> entry : guild.getMemberinfoHashMap().entrySet()) {
				MemberInfo memberInfo = entry.getValue();
				if (memberInfo != null) {
					sendMessage.getMemberList().add(memberInfo);
				}
			}
			sendMessage.setBtErrorCode(Error_Success);
			MessageUtil.tell_player_message(player, sendMessage);
		}
	}

	public void sendAllGuildAndMemberInfoToSelf(Player player, Guild guild) {
		sendAllGuildInfoToSelf(player);
		sendAllMemberInfoToSelf(player, guild);
	}

	public void sendAllGuildShortInfoToSelf(Player player) {
		ResGuildGetGuildShortInfoListToClientMessage sendMessage = new ResGuildGetGuildShortInfoListToClientMessage();
		sendMessage.setBtErrorCode(Error_Fail);
		for (Map.Entry<Long, Guild> entry : getGuildMapManager().getGuildIdMap().entrySet()) {
			Guild guild = entry.getValue();
			if (guild != null) {
				GuildShortInfo guildShortInfo = new GuildShortInfo();
				guildShortInfo.setGuildId(guild.getGuildInfo().getGuildId());
				guildShortInfo.setGuildName(guild.getGuildInfo().getGuildName());
				sendMessage.getGuildShortInfoList().add(guildShortInfo);
			}
		}
		sendMessage.setBtErrorCode(Error_Success);
		MessageUtil.tell_player_message(player, sendMessage);
		log.error(String.format("%s 发送 公会短信息列表 (WORLD)", player.getName()));
	}

	public void saveGuildData(int saveType, Guild guild, MemberInfo memberInfo) {
		if (guild != null) {
			if ((saveType & 1) != 0) {
				saveGuild(guild);
			}
			if ((saveType & 2) != 0) {
				if (memberInfo != null) {
					guild.saveMember(memberInfo);
				}
			}
		}
	}

	public void deleteGuildData(int deleteType, Guild guild, MemberInfo memberInfo) {
		if (guild != null) {
			if ((deleteType & 1) != 0) {
				deleteGuild(guild);
			}
			if ((deleteType & 2) != 0) {
				if (memberInfo != null) {
					guild.deleteMember(memberInfo);
				}
			}
		}
	}

	public String getEventString(String eventType, Object... values) {
		try {
			if (eventType.equals(ResManager.getInstance().getString("创建帮会"))) {
				return String.format(ResManager.getInstance().getString("[%s]帮会被{@}创建。"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("添加成员"))) {
				return String.format(ResManager.getInstance().getString("欢迎{@}玩家加入本帮，我帮变得更强大了"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("逐出成员"))) {
				return String.format(ResManager.getInstance().getString("{@}玩家被[%s]逐出了帮会"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("退出帮会"))) {
				return String.format(ResManager.getInstance().getString("{@}玩家离开了我们的帮会"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("调整职务"))) {
				return String.format(ResManager.getInstance().getString("本帮[%s]变更为：{@}"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("调整昵称"))) {
				return String.format(ResManager.getInstance().getString("{@}玩家的昵称被“%s”修改为：%s"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("调整分组头衔"))) {
				return String.format(ResManager.getInstance().getString("{@}玩家的分组头衔被“%s”修改为：%s"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("行会结盟"))) {
				return String.format(ResManager.getInstance().getString("恭喜恭喜，您的帮会与%s帮会结成联盟，并肩战斗"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("本帮添加其他行会敌对"))) {
				return String.format(ResManager.getInstance().getString("本帮会已将%s帮会列为敌对帮会"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("本帮被其他行会加为敌对"))) {
				return String.format(ResManager.getInstance().getString("%s帮会已将本帮列为敌对帮会"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("成员被其他玩家击败"))) {
				return String.format(ResManager.getInstance().getString("本帮成员{@}玩家名在地图【{@}】被玩家【{@}】击败"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("成员击败其他玩家"))) {
				return String.format(ResManager.getInstance().getString("本帮成员{@}玩家名在地图【{@}】击败了玩家【{@}】"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("成员击败BOSS"))) {
				return String.format(ResManager.getInstance().getString("本帮成员{@}玩家名在地图【{@}】击败BOSS【%s】"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("成员被BOSS击败"))) {
				return String.format(ResManager.getInstance().getString("本帮成员{@}玩家名在地图【{@}】被BOSS【%s】击败"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("帮贡捐献"))) {
				return String.format(ResManager.getInstance().getString("感谢{@}玩家向帮贡仓库提交了：%s*%d，获得帮贡点：%d"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("帮旗改名"))) {
				return String.format(ResManager.getInstance().getString("本帮帮主启用了新的帮旗名称：%s，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("帮旗更换造型"))) {
				return String.format(ResManager.getInstance().getString("本帮帮主启用了新的帮旗造型，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("帮旗升级"))) {
				return String.format(ResManager.getInstance().getString("本帮帮旗已升级到[%d]级，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"), values);
			} else if (eventType.equals(ResManager.getInstance().getString("占领旗帜消耗帮贡"))) {
				return String.format(ResManager.getInstance().getString("成功占领[%s]的旗帜，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"), values);
			}
		} catch (Exception e) {
			log.error(String.format("得到事件字符串出错 类型[%s]", eventType));
		}
		return "";
	}

	/**
	 * 创建帮会
	 *
	 * @param message 创建帮会消息
	 * @return
	 */
	public void reqInnerGuildCreateToWorld(ReqInnerGuildCreateToWorldMessage message) {
		if(message.getGuildBannerIcon()==1){
			message.setGuildBannerIcon(3000);
		}
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildCreateToClientMessage sendMessage = new ResGuildCreateToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			String guildName = String.format(ResManager.getInstance().getString("[%d区]%s"), WorldServer.getGameConfig().getServerByCountry(player.getCountry()), message.getGuildName());
			if (getGuildMapManager().FindByName(guildName) == null) {
				if (getGuildMapManager().FindByBannerName(message.getGuildBanner()) == null) {
					Guild guild = new Guild();
					guild.setCountry(player.getCountry());
					guild.setCalActiveValueTime(System.currentTimeMillis());
					guild.getGuildInfo().setGuildId(Config.getId());
					guild.getGuildInfo().setGuildName(guildName);
					guild.getGuildInfo().setGuildBanner(message.getGuildBanner());
					guild.getGuildInfo().setBannerIcon(message.getGuildBannerIcon());
					guild.getGuildInfo().setBannerLevel((byte) 1);
					guild.getGuildInfo().setGuildBulletin("");
					guild.setBangZhuInfo(guild.getGuildInfo(), player);
					guild.getGuildInfo().setAutoGuildAgreeAdd((byte) 1);
					guild.getGuildInfo().setCreateTime((int) (System.currentTimeMillis() / 1000));
					guild.getGuildInfo().setCreateIp(player.getIpString());
					if (guild.addMember(player, (byte) 1)) {
						if (getGuildMapManager().FindByBannerName(guild.getGuildInfo().getGuildBanner()) == null && getGuildMapManager().put(guild)) {
							guild.calFightPowerAndMemberNum();
							guild.calActiveValue(player);
							guild.addParseEvent(ResManager.getInstance().getString("创建帮会"), getEventString(ResManager.getInstance().getString("创建帮会"), guild.getGuildInfo().getGuildName()), new ParseUtil.PlayerParm(player.getId(), player.getName()));
							saveGuildData(7, guild, guild.findMemberInfo(player));

							sendMessage.setBtErrorCode(Error_Success);
							sendGuildAndMemberInfo(null, player, Notify_Create, guild);
							MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，帮会创建成功，赶快去招募成员吧"));
							MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("江湖风起云涌，一个全新的帮会：【{1}】成立了"), guild.getGuildInfo().getGuildName());

							boardcastGuildInfo(null, guild);
						} else {
							guild.removeMember(player);
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，创建帮会失败"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，添加帮会成员失败"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮旗名字已被其他帮会占用"));
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮会名字已被其他帮会占用"));
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 玩家自动同意加入帮会
	 *
	 * @param message 玩家自动同意加入帮会消息
	 * @return
	 */
	public void reqInnerGuildAutoArgeeAddGuildToWorld(ReqInnerGuildAutoArgeeAddGuildToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildAutoArgeeAddGuildToClientMessage sendMessage = new ResGuildAutoArgeeAddGuildToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (message.getAutoArgeeAddGuild() == 0) {
				player.setAutoArgeeAddGuild((byte) 0);//不同意
			} else {
				player.setAutoArgeeAddGuild((byte) 1);//同意
			}
			sendMessage.setBtErrorCode(Error_Success);
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 获取公会列表
	 *
	 * @param message 获取公会列表消息
	 * @return
	 */
	public void reqInnerGuildGetGuildListToWorld(ReqInnerGuildGetGuildListToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildGetGuildListToClientMessage sendMessage = new ResGuildGetGuildListToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			for (Map.Entry<Long, Guild> entry : getGuildMapManager().getGuildIdMap().entrySet()) {
				Guild guild = entry.getValue();
				if (guild != null && !guild.getMemberinfoHashMap().isEmpty()) {
					guild.Update();
					sendMessage.getGuildList().add(guild.getGuildInfo());
				}
			}
			sendMessage.setBtErrorCode(Error_Success);
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 玩家申请加入帮会
	 *
	 * @param message 通知世界服务器申请加入帮会消息
	 * @return
	 */
	public void reqInnerGuildApplyAddToWorld(ReqInnerGuildApplyAddToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			if (player.getGuildid() == 0) {
				Guild guild = getGuildMapManager().FindById(message.getGuildId());
				if (guild != null) {
					MemberInfo memberInfo = guild.getPowerLevelMemberInfo(1);
					if (memberInfo != null) {
						PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(memberInfo.getUserId());
						if (playerWorldInfo != null) {
							guild.setCountry(playerWorldInfo.getCountry());
						}
					}
					if (guild.getCountry() == player.getCountry()) {
						if (guild.getMemberinfoHashMap().size() < Guild_MaxNum) {
							if (guild.getGuildInfo().getAutoGuildAgreeAdd() == 1) {
								if (guild.addMember(player, (byte) 6)) {
									guild.calFightPowerAndMemberNum();
									guild.calActiveValue(player);
									guild.addParseEvent(ResManager.getInstance().getString("添加成员"), getEventString(ResManager.getInstance().getString("添加成员")), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, guild.findMemberInfo(player));

									sendGuildAndMemberInfo(null, player, Notity_AddMember, guild);
									sendGuildInfo(player, Notity_KingCity, guild);
									sendGuildInfo(player, Notity_BannerBuff, guild);
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，对方帮派同意了您的入帮请求"));
									guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), player.getName());
									guild.sendAllMemberNotify(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), player.getName());
								}
							} else {
								Player addPermissionsPlayer = guild.getAddPermissionsPlayer();
								if (addPermissionsPlayer != null) {
									ResGuildApplyAddDoingToClientMessage sendMessage = new ResGuildApplyAddDoingToClientMessage();
									sendMessage.setBtErrorCode(Error_Success);
									sendMessage.setUserId(player.getId());
									sendMessage.setApplyLevel((short) player.getLevel());
									sendMessage.setApplyName(player.getName());
									sendMessage.setApplyModeInfo(player.getFriendModeInfo());
									MessageUtil.tell_player_message(addPermissionsPlayer, sendMessage);
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您的入帮申请已发送，请等待对方帮主或帮副回应"));

									ApplyAndInvite applyAndInvite = new ApplyAndInvite();
									applyAndInvite.setGuildid(guild.getGuildInfo().getGuildId());
									applyAndInvite.setType(ApplyAndInvite.Apply_Type);
									applyAndInvite.setSrcid(player.getId());
									applyAndInvite.setDestid(addPermissionsPlayer.getId());
									getApplyAndInvitesList().add(applyAndInvite);
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方帮主/副帮主均不在线"));
								}
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方帮会人数已满，无法招收更多成员了"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，不同国家不能加入同一个帮会"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法找到该帮会"));
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已加入帮会，如需换帮则需先退出帮会"));
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 帮会邀请玩家加入
	 *
	 * @param message 通知世界服务器邀请加入帮会消息
	 * @return
	 */
	public void reqInnerGuildInviteAddToWorld(ReqInnerGuildInviteAddToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			if (player.getId() != message.getUserId()) {
				if (player.getGuildid() != 0) {
					Guild guild = getGuildMapManager().FindById(player.getGuildid());
					if (guild != null) {
						MemberInfo memberInfo = guild.findMemberInfo(player);
						if (memberInfo != null) {
							if (memberInfo.getGuildPowerLevel() == 1 || memberInfo.getGuildPowerLevel() == 2) {
								if (guild.getMemberinfoHashMap().size() < Guild_MaxNum) {
									Player invitePlayer = PlayerManager.getInstance().getPlayer(message.getUserId());
									if (invitePlayer != null) {
										if (SetupMenuManager.getInstance().isBanGuild(invitePlayer)) {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该玩家已经设置禁止帮派邀请。"));
											return;
										}
										if (player.getCountry() == invitePlayer.getCountry()) {
											if (invitePlayer.getGuildid() == 0) {
												if (invitePlayer.getAutoArgeeAddGuild() == 1) {
													if (guild.addMember(invitePlayer, (byte) 6)) {
														guild.calFightPowerAndMemberNum();
														guild.calActiveValue(invitePlayer);
														guild.addParseEvent(ResManager.getInstance().getString("添加成员"), getEventString(ResManager.getInstance().getString("添加成员")), new ParseUtil.PlayerParm(invitePlayer.getId(), invitePlayer.getName()));
														saveGuildData(7, guild, guild.findMemberInfo(invitePlayer));

														sendGuildAndMemberInfo(null, invitePlayer, Notity_AddMember, guild);
														sendGuildAndMemberInfo(player, invitePlayer, Notify_AddOrUpdate, guild);
														sendGuildInfo(invitePlayer, Notity_KingCity, guild);
														sendGuildInfo(invitePlayer, Notity_BannerBuff, guild);
														MessageUtil.notify_player(invitePlayer, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，您成功加入了{1}帮会"), guild.getGuildInfo().getGuildName());
														MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，{1}玩家同意了您的帮会邀请"), invitePlayer.getName());
														guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), invitePlayer.getName());
														guild.sendAllMemberNotify(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), invitePlayer.getName());
													} else {
														MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，加入帮会失败，该玩家可能已经加入了本帮会"));
													}
												} else {
													ResGuildInviteAddDoingToClientMessage sendMessage = new ResGuildInviteAddDoingToClientMessage();
													sendMessage.setBtErrorCode(Error_Success);
													sendMessage.setGuildId(guild.getGuildInfo().getGuildId());
													sendMessage.setGuildName(guild.getGuildInfo().getGuildName());
													sendMessage.setInviteLevel((short) player.getLevel());
													sendMessage.setInviteName(player.getName());
													sendMessage.setInviteModeInfo(player.getFriendModeInfo());
													MessageUtil.tell_player_message(invitePlayer, sendMessage);
													MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您的入帮邀请已发送，请等待对方玩家回应"));

													ApplyAndInvite applyAndInvite = new ApplyAndInvite();
													applyAndInvite.setGuildid(guild.getGuildInfo().getGuildId());
													applyAndInvite.setType(ApplyAndInvite.Invite_Type);
													applyAndInvite.setSrcid(player.getId());
													applyAndInvite.setDestid(invitePlayer.getId());
													getApplyAndInvitesList().add(applyAndInvite);
												}
											} else {
												if (invitePlayer.getGuildid() == guild.getGuildInfo().getGuildId()) {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家已经在您的帮会中，无需重复邀请"));
												} else {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该玩家已加入了其他帮会，可先通知其退帮"));
												}
											}
										} else {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，不同国家不能加入同一个帮会"));
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到被邀请的玩家"));
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮会最大成员上限为100人，目前已达上限"));
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有权限进行此项操作"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法找到该成员"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法找到该帮会"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有加入帮会"));
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法邀请自己加入帮会"));
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 获得帮会成员列表
	 *
	 * @param message 通知世界服务器获取成员列表
	 * @return
	 */
	public void reqInnerGuildGetMemberListToWorld(ReqInnerGuildGetMemberListToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildGetMemberListToClientMessage sendMessage = new ResGuildGetMemberListToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					guild.Update();
					for (Map.Entry<Long, MemberInfo> entry : guild.getMemberinfoHashMap().entrySet()) {
						MemberInfo memberInfo = entry.getValue();
						if (memberInfo != null) {
							sendMessage.getMemberList().add(memberInfo);
						}
					}
					sendMessage.setBtErrorCode(Error_Success);
					sendGuildInfo(player, Notify_AddOrUpdate, guild);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			} else {
				if (message.getGuildId() == -1) {
					Guild guild = getGuildMapManager().FindById(player.getGuildid());
					if (guild != null) {
						sendGuildInfo(player, Notify_AddOrUpdate, guild);
						return;
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，不能获得其他帮会的成员列表"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 添加帮会成员
	 *
	 * @param message 通知世界服务器添加帮会成员
	 * @return
	 */
	public void reqInnerGuildAddMemberToWorld(ReqInnerGuildAddMemberToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		Player destPlayer = PlayerManager.getInstance().getPlayer(message.getUserId());
		if (player != null && destPlayer != null) {
			ResGuildAddMemberToClientMessage sendMessage = new ResGuildAddMemberToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getCountry() == destPlayer.getCountry()) {
				for (int i = 0; i < getApplyAndInvitesList().size(); i++) {
					ApplyAndInvite applyAndInvite = getApplyAndInvitesList().get(i);
					if (applyAndInvite != null && applyAndInvite.getDestid() == player.getId() && applyAndInvite.getSrcid() == destPlayer.getId()) {
						if (applyAndInvite.getType() == ApplyAndInvite.Apply_Type) {//player 帮会管理人员 destplayer 申请加入玩家
							if (message.getArgee() == 0) {
								Guild guild = getGuildMapManager().FindById(player.getGuildid());
								if (guild != null) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您拒绝了来自{1}玩家的入帮申请"), destPlayer.getName());
									MessageUtil.notify_player(destPlayer, Notifys.ERROR, ResManager.getInstance().getString("很遗憾，{1}帮会拒绝了您的帮会申请"), guild.getGuildInfo().getGuildName());
								}
								getApplyAndInvitesList().remove(applyAndInvite);
							} else {
								if (applyAndInvite.getGuildid() == message.getGuildId()) {
									Guild guild = getGuildMapManager().FindById(applyAndInvite.getGuildid());
									if (guild != null && guild.getGuildInfo().getGuildId() == player.getGuildid()) {
										MemberInfo memberInfo = guild.findMemberInfo(player);
										if (memberInfo != null && (memberInfo.getGuildPowerLevel() == 1 || memberInfo.getGuildPowerLevel() == 2)) {
											if (destPlayer.getGuildid() == 0) {
												if (guild.getMemberinfoHashMap().size() < Guild_MaxNum) {
													if (guild.addMember(destPlayer, (byte) 6)) {
														guild.calFightPowerAndMemberNum();
														guild.calActiveValue(destPlayer);
														guild.addParseEvent(ResManager.getInstance().getString("添加成员"), getEventString(ResManager.getInstance().getString("添加成员")), new ParseUtil.PlayerParm(destPlayer.getId(), destPlayer.getName()));
														saveGuildData(7, guild, guild.findMemberInfo(destPlayer));

														sendGuildAndMemberInfo(null, destPlayer, Notity_AddMember, guild);
														sendGuildAndMemberInfo(player, destPlayer, Notify_AddOrUpdate, guild);
														sendGuildInfo(destPlayer, Notity_KingCity, guild);
														sendGuildInfo(destPlayer, Notity_BannerBuff, guild);
														MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，对方帮派同意了您的入帮请求"));
														guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), destPlayer.getName());
														guild.sendAllMemberNotify(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), destPlayer.getName());
														sendMessage.setBtErrorCode(Error_Success);

														deleteApplyAndInvite(destPlayer.getId());
													}
												} else {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮会最大成员上限为100人，目前已达上限"));
													MessageUtil.notify_player(destPlayer, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方帮会人数已满，无法招收更多成员了"));
												}
											} else {
												if (destPlayer.getGuildid() == guild.getGuildInfo().getGuildId()) {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该玩家已经在您的帮会中"));
												} else {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方已加入了其他帮会"));
												}
											}
										} else {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的权限不够"));
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法找到该帮会"));
									}
								}
							}
						} else if (applyAndInvite.getType() == ApplyAndInvite.Invite_Type) {//player 邀请加入玩家 destplayer 帮会管理人员
							if (message.getArgee() == 0) {
								Guild guild = getGuildMapManager().FindById(destPlayer.getGuildid());
								if (guild != null) {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您拒绝了来自{1}帮会的入帮邀请"), guild.getGuildInfo().getGuildName());
									MessageUtil.notify_player(destPlayer, Notifys.ERROR, ResManager.getInstance().getString("很遗憾，{1}玩家拒绝了您的帮会邀请"), player.getName());
								}
								getApplyAndInvitesList().remove(applyAndInvite);
							} else {
								if (applyAndInvite.getGuildid() == message.getGuildId()) {
									Guild guild = getGuildMapManager().FindById(applyAndInvite.getGuildid());
									if (guild != null && guild.getGuildInfo().getGuildId() == destPlayer.getGuildid()) {
										MemberInfo memberInfo = guild.findMemberInfo(destPlayer);
										if (memberInfo != null && (memberInfo.getGuildPowerLevel() == 1 || memberInfo.getGuildPowerLevel() == 2)) {
											if (player.getGuildid() == 0) {
												if (guild.getMemberinfoHashMap().size() < Guild_MaxNum) {
													if (guild.addMember(player, (byte) 6)) {
														guild.calFightPowerAndMemberNum();
														guild.calActiveValue(player);
														guild.addParseEvent(ResManager.getInstance().getString("添加成员"), getEventString(ResManager.getInstance().getString("添加成员")), new ParseUtil.PlayerParm(player.getId(), player.getName()));
														saveGuildData(7, guild, guild.findMemberInfo(player));

														sendGuildAndMemberInfo(null, player, Notity_AddMember, guild);
														sendGuildAndMemberInfo(destPlayer, player, Notify_AddOrUpdate, guild);
														sendGuildInfo(player, Notity_KingCity, guild);
														sendGuildInfo(player, Notity_BannerBuff, guild);
														MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，您成功加入了{1}帮会"), guild.getGuildInfo().getGuildName());
														MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜，{1}玩家同意了您的帮会邀请"), player.getName());
														guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), player.getName());
														guild.sendAllMemberNotify(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("欢迎玩家[{1}]加入本帮，我帮变得更强大了"), player.getName());
														sendMessage.setBtErrorCode(Error_Success);

														deleteApplyAndInvite(player.getId());
													}
												} else {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方帮会人数已满，无法招收更多成员了"));
												}
											} else {
												if (player.getGuildid() == guild.getGuildInfo().getGuildId()) {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经在这个帮会中"));
												} else {
													MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您已加入了其他帮会"));
												}
											}
										} else {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，权限不够"));
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，无法找到该帮会"));
									}
								}
							}
						}
						break;
					}
				}
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，不同国家不能加入同一个帮会"));
				MessageUtil.notify_player(destPlayer, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，不同国家不能加入同一个帮会"));
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 退出帮会
	 *
	 * @param message 通知世界服务器退出帮会
	 * @return
	 */
	public void reqInnerGuildQuitToWorld(ReqInnerGuildQuitToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildQuitToClientMessage sendMessage = new ResGuildQuitToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() != 1) {
						sendGuildAndMemberInfo(null, player, Notify_Delete, guild);
						deleteGuildData(2, guild, memberInfo);
						guild.removeMember(player);
						guild.Update();
						guild.addParseEvent(ResManager.getInstance().getString("退出帮会"), getEventString(ResManager.getInstance().getString("退出帮会")), new ParseUtil.PlayerParm(player.getId(), player.getName()));
						saveGuildData(7, guild, null);

						player.setLastAfkGuildTime((int) (System.currentTimeMillis() / 1000));
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您离开了帮会：{1}"), guild.getGuildInfo().getGuildName());
						guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("{1}玩家离开了我们的帮会"), player.getName());
						sendMessage.setBtErrorCode(Error_Success);
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您是帮主，如需退帮，请先任命其他成员为帮主"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 修改昵称
	 *
	 * @param message 通知世界服务器修改昵称
	 * @return
	 */
	public void reqInnerGuildChangeNickNameToWorld(ReqInnerGuildChangeNickNameToWorldMessage message) {
		if (message.getNickName() == null) {
			message.setNickName("");
		}
		if (message.getGroupName() == null) {
			message.setGroupName("");
		}
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildChangeNickNameToClientMessage sendMessage = new ResGuildChangeNickNameToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() != 6) {
						Player destPlayer = PlayerManager.getInstance().getPlayer(message.getUserId());
						PlayerWorldInfo destPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(message.getUserId());
						MemberInfo destMemberInfo = guild.findMemberInfoById(message.getUserId());
						if (destMemberInfo != null && destPlayerWorldInfo != null) {
							destMemberInfo.setNickName(message.getNickName());
							destMemberInfo.setGroupName(message.getGroupName());
							if (destPlayer != null) {
								sendGuildAndMemberInfo(null, destPlayer, Notify_AddOrUpdate, guild);
								sendGuildAndMemberInfo(player, destPlayer, Notify_AddOrUpdate, guild);
								if ("".equals(message.getNickName())) {
									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了{1}玩家的帮中昵称"), destPlayer.getName());
										MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您的帮中昵称被“{1}”清除了"), Default_Power[memberInfo.getGuildPowerLevel() - 1]);
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了帮中昵称"));
									}
								} else {
									guild.addParseEvent(ResManager.getInstance().getString("调整昵称"), getEventString(ResManager.getInstance().getString("调整昵称"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getNickName()), new ParseUtil.PlayerParm(destPlayer.getId(), destPlayer.getName()));
									saveGuildData(6, guild, destMemberInfo);

									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改{1}玩家的帮中昵称为：{2}"), destPlayer.getName(), message.getNickName());
										MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您的帮中昵称被“{1}”修改为：{2}"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getNickName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改了帮中昵称"));
									}
								}
								if ("".equals(message.getGroupName())) {
									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了{1}玩家的分组与头衔"), destPlayer.getName());
										MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您的分组与头衔被“{1}”清除了"), Default_Power[memberInfo.getGuildPowerLevel() - 1]);
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了分组与头衔"));
									}
								} else {
									guild.addParseEvent(ResManager.getInstance().getString("调整分组头衔"), getEventString(ResManager.getInstance().getString("调整分组头衔"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getGroupName()), new ParseUtil.PlayerParm(destPlayer.getId(), destPlayer.getName()));
									saveGuildData(6, guild, destMemberInfo);

									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改{1}玩家的分组与头衔为：{2}"), destPlayer.getName(), message.getGroupName());
										MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您的分组与头衔被“{1}”修改为：{2}"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getGroupName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改了分组与头衔"));
									}
								}
							} else {
								sendGuildAndMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_AddOrUpdate, guild);
								if ("".equals(message.getNickName())) {
									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了{1}玩家的帮中昵称"), destPlayerWorldInfo.getName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了帮中昵称"));
									}
								} else {
									guild.addParseEvent(ResManager.getInstance().getString("调整昵称"), getEventString(ResManager.getInstance().getString("调整昵称"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getNickName()), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
									saveGuildData(6, guild, destMemberInfo);

									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改{1}玩家的帮中昵称为：{2}"), destPlayerWorldInfo.getName(), message.getNickName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改了帮中昵称"));
									}
								}
								if ("".equals(message.getGroupName())) {
									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了{1}玩家的分组与头衔"), destPlayerWorldInfo.getName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功取消了分组与头衔"));
									}
								} else {
									guild.addParseEvent(ResManager.getInstance().getString("调整分组头衔"), getEventString(ResManager.getInstance().getString("调整分组头衔"), Default_Power[memberInfo.getGuildPowerLevel() - 1], message.getGroupName()), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
									saveGuildData(6, guild, destMemberInfo);

									if (player != destPlayer) {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改{1}玩家的分组与头衔为：{2}"), destPlayerWorldInfo.getName(), message.getGroupName());
									} else {
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功修改了分组与头衔"));
									}
								}
							}
							sendMessage.setBtErrorCode(Error_Success);
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到这个帮会成员"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有修改帮中昵称的权限"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 修改职权
	 *
	 * @param message 通知世界服务器修改职权
	 * @return
	 */
	public void reqInnerGuildChangePowerLevelToWorld(ReqInnerGuildChangePowerLevelToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildChangePowerLevelToClientMessage sendMessage = new ResGuildChangePowerLevelToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						Player destPlayer = PlayerManager.getInstance().getPlayer(message.getUserId());
						PlayerWorldInfo destPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(message.getUserId());
						if (destPlayerWorldInfo != null) {
							MemberInfo destMemberInfo = guild.findMemberInfoById(message.getUserId());
							if (destMemberInfo != null && destMemberInfo.getGuildPowerLevel() != 1) {
								if (message.getNewPowerLevel() == 1) {//变更帮主
									memberInfo.setGuildPowerLevel((byte) 6);
									destMemberInfo.setGuildPowerLevel((byte) 1);
									guild.Update();
									guild.addParseEvent(ResManager.getInstance().getString("调整职务"), getEventString(ResManager.getInstance().getString("调整职务"), Default_Power[0]), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
									saveGuildData(7, guild, destMemberInfo);
									saveGuildData(2, guild, memberInfo);

									sendGuildInfo(player, Notify_AddOrUpdate, guild);
									sendGuildInfo(destPlayer, Notity_ChangeBanner, guild);
									sendGuildInfo(player, Notity_KingCity, guild);
									sendGuildInfo(destPlayer, Notity_KingCity, guild);
									sendMemberInfo(null, player, Notify_AddOrUpdate, guild);
									if (destPlayer != null) {
										sendMemberInfo(null, destPlayer, Notify_AddOrUpdate, guild);
										sendMemberInfo(destPlayer, player, Notify_AddOrUpdate, guild);
									}
									sendMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_AddOrUpdate, guild);
									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您将帮主职位禅让给了：{1}"), destPlayerWorldInfo.getName());
									if (destPlayer != null) {
										MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您被提升为：帮主"));
									}
									guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮【帮主】变更，欢迎新帮主：{1}"), destPlayerWorldInfo.getName());
									sendMessage.setBtErrorCode(Error_Success);
								} else {//变更其他职位
									if (destMemberInfo.getGuildPowerLevel() != message.getNewPowerLevel()) {
										Player otherPlayer = guild.getPowerLevelPlayer(message.getNewPowerLevel());
										MemberInfo otherMemberInfo = guild.getPowerLevelMemberInfo(message.getNewPowerLevel());
										if (otherMemberInfo != null) {
											if (otherMemberInfo.getGuildPowerLevel() == message.getNewPowerLevel()) {
												otherMemberInfo.setGuildPowerLevel((byte) 6);
												destMemberInfo.setGuildPowerLevel(message.getNewPowerLevel());
												guild.Update();
												guild.addParseEvent(ResManager.getInstance().getString("调整职务"), getEventString(ResManager.getInstance().getString("调整职务"), Default_Power[message.getNewPowerLevel() - 1]), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
												saveGuildData(7, guild, destMemberInfo);
												saveGuildData(2, guild, otherMemberInfo);

												sendGuildInfo(player, Notify_AddOrUpdate, guild);
												sendGuildInfo(destPlayer, Notity_ChangeBanner, guild);
												sendGuildInfo(otherPlayer, Notify_AddOrUpdate, guild);
												if (destPlayer != null) {
													sendMemberInfo(null, destPlayer, Notify_AddOrUpdate, guild);
													sendMemberInfoById(destPlayer, otherMemberInfo.getUserId(), Notify_AddOrUpdate, guild);
												}
												if (otherPlayer != null) {
													sendMemberInfo(null, otherPlayer, Notify_AddOrUpdate, guild);
													sendMemberInfoById(otherPlayer, destPlayerWorldInfo.getId(), Notify_AddOrUpdate, guild);
												}
												sendMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_AddOrUpdate, guild);
												sendMemberInfoById(player, otherMemberInfo.getUserId(), Notify_AddOrUpdate, guild);
												if (destPlayer != null) {
													MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您在帮中的职务变更为：{1}"), Default_Power[message.getNewPowerLevel() - 1]);
												}
												guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮[{1}]变更为：{2}"), Default_Power[message.getNewPowerLevel() - 1], destPlayerWorldInfo.getName());
												sendMessage.setBtErrorCode(Error_Success);
											} else {
												MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，职位调整错误，变更无效"));
											}
										} else {
											destMemberInfo.setGuildPowerLevel(message.getNewPowerLevel());
											guild.Update();
											guild.addParseEvent(ResManager.getInstance().getString("调整职务"), getEventString(ResManager.getInstance().getString("调整职务"), Default_Power[message.getNewPowerLevel() - 1]), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
											saveGuildData(7, guild, destMemberInfo);

											sendGuildInfo(player, Notify_AddOrUpdate, guild);
											sendGuildInfo(destPlayer, Notity_ChangeBanner, guild);
											if (destPlayer != null) {
												sendMemberInfo(null, destPlayer, Notify_AddOrUpdate, guild);
											}
											sendMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_AddOrUpdate, guild);
											if (destPlayer != null) {
												MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("您在帮中的职务变更为：{1}"), Default_Power[message.getNewPowerLevel() - 1]);
											}
											if (message.getNewPowerLevel() != 6) {
												guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮[{1}]变更为：{2}"), Default_Power[message.getNewPowerLevel() - 1], destPlayerWorldInfo.getName());
											}
											sendMessage.setBtErrorCode(Error_Success);
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，职位相同，变更无效"));
									}
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮主不能修改帮主自己的职位"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到被修改者数据"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才可以修改其他玩家的职位"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 删除帮会成员
	 *
	 * @param message 通知世界服务器删除帮会成员
	 * @return
	 */
	public void reqInnerGuildDeleteMemberToWorld(ReqInnerGuildDeleteMemberToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildDeleteMemberToClientMessage sendMessage = new ResGuildDeleteMemberToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				if (player.getId() != message.getUserId()) {
					Guild guild = getGuildMapManager().FindById(player.getGuildid());
					if (guild != null) {
						MemberInfo memberInfo = guild.findMemberInfo(player);
						if (memberInfo != null) {
							if (memberInfo.getGuildPowerLevel() == 1 || memberInfo.getGuildPowerLevel() == 2) {
								Player destPlayer = PlayerManager.getInstance().getPlayer(message.getUserId());
								PlayerWorldInfo destPlayerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(message.getUserId());
								MemberInfo destMemberInfo = guild.findMemberInfoById(message.getUserId());
								if (destMemberInfo != null && destPlayerWorldInfo != null) {
									switch (destMemberInfo.getGuildPowerLevel()) {
										case 1: {
											if (memberInfo.getGuildPowerLevel() == 1) {
												MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您不能开除自己"));
											} else {
												MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的权限不足"));
											}
										}
										break;
										case 2:
										case 3:
										case 4:
										case 5: {
											if (memberInfo.getGuildPowerLevel() == 1) {
												deleteGuildData(2, guild, destMemberInfo);
												if (destPlayer != null) {
													sendGuildAndMemberInfo(null, destPlayer, Notify_Delete, guild);
												}
												sendMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_Delete, guild);
												guild.removeMemberById(destPlayerWorldInfo.getId());
												guild.Update();
												guild.addParseEvent(ResManager.getInstance().getString("逐出成员"), getEventString(ResManager.getInstance().getString("逐出成员"), Default_Power[0]), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
												saveGuildData(7, guild, null);

												if (destPlayer != null) {
													destPlayer.setLastAfkGuildTime((int) (System.currentTimeMillis() / 1000));
												}
												sendGuildInfo(player, Notify_AddOrUpdate, guild);
												if (destPlayer != null) {
													MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("[{1}][{2}]将您移出了帮会"), Default_Power[memberInfo.getGuildPowerLevel() - 1], player.getName());
												}
												guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("{1}被[{2}]逐出了帮会"), destPlayerWorldInfo.getName(), Default_Power[memberInfo.getGuildPowerLevel() - 1]);
												sendMessage.setBtErrorCode(Error_Success);
											} else {
												MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有权限开除职权帮众"));
											}
										}
										break;
										case 6: {
											deleteGuildData(2, guild, destMemberInfo);
											if (destPlayer != null) {
												sendGuildAndMemberInfo(null, destPlayer, Notify_Delete, guild);
											}
											sendMemberInfoById(player, destPlayerWorldInfo.getId(), Notify_Delete, guild);
											guild.removeMemberById(destPlayerWorldInfo.getId());
											guild.Update();
											guild.addParseEvent(ResManager.getInstance().getString("逐出成员"), getEventString(ResManager.getInstance().getString("逐出成员"), Default_Power[memberInfo.getGuildPowerLevel() - 1]), new ParseUtil.PlayerParm(destPlayerWorldInfo.getId(), destPlayerWorldInfo.getName()));
											saveGuildData(7, guild, null);

											if (destPlayer != null) {
												destPlayer.setLastAfkGuildTime((int) (System.currentTimeMillis() / 1000));
											}
											sendGuildInfo(player, Notify_AddOrUpdate, guild);
											if (destPlayer != null) {
												MessageUtil.notify_player(destPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("[{1}][{2}]将您移出了帮会"), Default_Power[memberInfo.getGuildPowerLevel() - 1], player.getName());
											}
											guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("{1}被[{2}]逐出了帮会"), destPlayerWorldInfo.getName(), Default_Power[memberInfo.getGuildPowerLevel() - 1]);
											sendMessage.setBtErrorCode(Error_Success);
										}
										break;
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会成员"));
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的权限不足"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您不是该帮会成员"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您不能开除自己"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 帮主修改自动同意申请加入设置
	 *
	 * @param message 通知世界服务器帮主修改自动同意申请加入设置
	 * @return
	 */
	public void reqInnerGuildAutoGuildArgeeAddToWorld(ReqInnerGuildAutoGuildArgeeAddToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildAutoGuildArgeeAddToClientMessage sendMessage = new ResGuildAutoGuildArgeeAddToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (message.getAutoGuildAgreeAdd() == 0) {
							guild.getGuildInfo().setAutoGuildAgreeAdd((byte) 0);//不同意
						} else {
							guild.getGuildInfo().setAutoGuildAgreeAdd((byte) 1);//同意
						}
						saveGuildData(7, guild, null);
						sendMessage.setBtErrorCode(Error_Success);
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才能修改此项设置"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 修改公告
	 *
	 * @param message 通知世界服务器修改公告
	 * @return
	 */
	public void reqInnerGuildChangeBulletinToWorld(ReqInnerGuildChangeBulletinToWorldMessage message) {
		if (message.getGuildBulletin() == null) {
			message.setGuildBulletin("");
		}
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildChangeBulletinToClientMessage sendMessage = new ResGuildChangeBulletinToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						guild.getGuildInfo().setGuildBulletin(message.getGuildBulletin());
						guild.getGuildInfo().setLastGuildBulletinTime((int) (System.currentTimeMillis() / 1000));
						saveGuildData(7, guild, null);

						sendGuildInfo(player, Notify_AddOrUpdate, guild);
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，帮会公告修改成功"));
						guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("帮主更新了帮会公告，欢迎查阅"));
						sendMessage.setBtErrorCode(Error_Success);
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才可以修改公告"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 提交帮会贡献物品
	 *
	 * @param message 通知世界服务器提交帮会贡献物品
	 * @return
	 */
	public void reqInnerGuildSubmitItemToWorld(ReqInnerGuildSubmitItemToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildSubmitItemToClientMessage sendMessage = new ResGuildSubmitItemToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null) {
						int addnum = 0;
						if (message.getItemType() == 5) {//铜币
							addnum = message.getItemNum() / 300000;
						} else {
							addnum = message.getItemNum();
						}
						if (addnum != 0) {
							player.setContributionPoint(player.getContributionPoint() + addnum);
							memberInfo.setContributionPoint(player.getContributionPoint());
							memberInfo.setContributionPointHistory(memberInfo.getContributionPointHistory() + addnum);
							switch (message.getItemType()) {
								case 1: {//青龙令
									guild.getGuildInfo().setDragon(guild.getGuildInfo().getDragon() + message.getItemNum());
									memberInfo.setDragonHistory(memberInfo.getDragonHistory() + message.getItemNum());
									guild.addParseEvent(ResManager.getInstance().getString("帮贡捐献"), getEventString(ResManager.getInstance().getString("帮贡捐献"), ResManager.getInstance().getString("青龙令"), message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, memberInfo);

									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢您，增加帮贡库存[青龙令]：{1}，您获得帮贡点：{2}"), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢{1}向帮贡仓库提交了：青龙令*{2}，获得帮贡点：{3}"), player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
								}
								break;
								case 2: {//白虎令
									guild.getGuildInfo().setWhiteTiger(guild.getGuildInfo().getWhiteTiger() + message.getItemNum());
									memberInfo.setWhiteTigerHistory(memberInfo.getWhiteTigerHistory() + message.getItemNum());
									guild.addParseEvent(ResManager.getInstance().getString("帮贡捐献"), getEventString(ResManager.getInstance().getString("帮贡捐献"), ResManager.getInstance().getString("白虎令"), message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, memberInfo);

									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢您，增加帮贡库存[白虎令]：{1}，您获得帮贡点：{2}"), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢{1}向帮贡仓库提交了：白虎令*{2}，获得帮贡点：{3}"), player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
								}
								break;
								case 3: {//朱雀令
									guild.getGuildInfo().setSuzaku(guild.getGuildInfo().getSuzaku() + message.getItemNum());
									memberInfo.setSuzakuHistory(memberInfo.getSuzakuHistory() + message.getItemNum());
									guild.addParseEvent(ResManager.getInstance().getString("帮贡捐献"), getEventString(ResManager.getInstance().getString("帮贡捐献"), ResManager.getInstance().getString("朱雀令"), message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, memberInfo);

									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢您，增加帮贡库存[朱雀令]：{1}，您获得帮贡点：{2}"), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢{1}向帮贡仓库提交了：朱雀令*{2}，获得帮贡点：{3}"), player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
								}
								break;
								case 4: {//玄武令
									guild.getGuildInfo().setBasaltic(guild.getGuildInfo().getBasaltic() + message.getItemNum());
									memberInfo.setBasalticHistory(memberInfo.getBasalticHistory() + message.getItemNum());
									guild.addParseEvent(ResManager.getInstance().getString("帮贡捐献"), getEventString(ResManager.getInstance().getString("帮贡捐献"), ResManager.getInstance().getString("玄武令"), message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, memberInfo);

									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢您，增加帮贡库存[玄武令]：{1}，您获得帮贡点：{2}"), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢{1}向帮贡仓库提交了：玄武令*{2}，获得帮贡点：{3}"), player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
								}
								break;
								case 5: {//铜币
									guild.getGuildInfo().setStockGold(guild.getGuildInfo().getStockGold() + message.getItemNum());
									memberInfo.setStockGoldHistory(memberInfo.getStockGoldHistory() + message.getItemNum());
									guild.addParseEvent(ResManager.getInstance().getString("帮贡捐献"), getEventString(ResManager.getInstance().getString("帮贡捐献"), ResManager.getInstance().getString("铜币"), message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, memberInfo);

									MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢您，增加帮贡库存[铜币]：{1}，您获得帮贡点：{2}"), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, ResManager.getInstance().getString("感谢{1}向帮贡仓库提交了：铜币*{2}，获得帮贡点：{3}"), player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
								}
								break;
							}
							logState("捐献成功", guild);
							sendGuildAndMemberInfo(null, player, Notify_AddOrUpdate, guild);
							guild.boardcastInfo(Notify_AddOrUpdate);
							sendMessage.setBtErrorCode(Error_Success);
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您不是该帮会成员"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	private void logState(String becase, Guild guild) {
		try {
			log.error("帮会当前状态[青龙:" + guild.getGuildInfo().getDragon()
					+ ",白虎:" + guild.getGuildInfo().getWhiteTiger()
					+ ",玄武:" + guild.getGuildInfo().getBasaltic()
					+ ",朱雀:" + guild.getGuildInfo().getSuzaku()
					+ ",铜币:" + guild.getGuildInfo().getStockGold() + "](" + becase + ")");
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public void reqInnerKingCityEventToWorld(ReqInnerKingCityEventToWorldMessage message) {
		switch (message.getEventtype()) {
			case 1: {
				Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
				if (player != null) {
					Guild guild = getGuildMapManager().FindById(player.getGuildid());
					if (guild != null) {
						MemberInfo memberInfo = guild.findMemberInfo(player);
						if (memberInfo != null) {
							if (memberInfo.getGuildPowerLevel() == 1) {
								int money = Integer.valueOf(message.getGuildevent());
								if (money != 0 && guild.getGuildInfo().getStockGold() >= money) {
									guild.getGuildInfo().setStockGold(guild.getGuildInfo().getStockGold() - money);
									//guild.addParseEvent("帮贡捐献", getEventString("帮贡捐献", "铜币", message.getItemNum(), addnum), new ParseUtil.PlayerParm(player.getId(), player.getName()));
									saveGuildData(7, guild, null);
									//MessageUtil.notify_player(player, Notifys.SUCCESS, "感谢您，增加帮贡库存[铜币]：{1}，您获得帮贡点：{2}", String.valueOf(message.getItemNum()), String.valueOf(addnum));
									//guild.sendAllMemberNotifyNoMe(player, Notifys.SUCCESS, "感谢{1}向帮贡仓库提交了：铜币*{2}，获得帮贡点：{3}", player.getName(), String.valueOf(message.getItemNum()), String.valueOf(addnum));
									//sendGuildAndMemberInfo(null, player, Notify_AddOrUpdate, guild);
									guild.boardcastInfo(Notify_AddOrUpdate);
								}
							}
						}
					}
				} else {
					log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
				}
			}
			break;
			case 2: {
				long guildid = Long.valueOf(message.getGuildevent());
				if (guildid != 0) {
					Guild guild = getGuildMapManager().FindById(guildid);
					if (guild != null) {
						guild.boardcastInfo(Notity_KingCity);
					}
				}
			}
			break;
		}
	}

	/**
	 * 更换帮旗造型
	 *
	 * @param message 通知世界服务器更换帮旗造型
	 * @return
	 */
	public void reqInnerGuildChangeBannerIconToWorld(ReqInnerGuildChangeBannerIconToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildChangeBannerIconToClientMessage sendMessage = new ResGuildChangeBannerIconToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (message.getBannerIcon() != guild.getGuildInfo().getBannerIcon()) {
							GuildBanner guildBanner = getGuildBannerMap().get((int) guild.getGuildInfo().getBannerLevel());
							if (guildBanner != null) {
								if (guildBanner.getMonsterList().contains(message.getBannerIcon())) {
									if (guildBanner.getChangicondragon() <= guild.getGuildInfo().getDragon()
										&& guildBanner.getChangiconwhitetiger() <= guild.getGuildInfo().getWhiteTiger()
										&& guildBanner.getChangiconsuzaku() <= guild.getGuildInfo().getSuzaku()
										&& guildBanner.getChangiconbasaltic() <= guild.getGuildInfo().getBasaltic()
										&& guildBanner.getChangicongold() <= guild.getGuildInfo().getStockGold()) {
										guild.getGuildInfo().setBannerIcon(message.getBannerIcon());
										guild.getGuildInfo().setDragon(guild.getGuildInfo().getDragon() - guildBanner.getChangicondragon());
										guild.getGuildInfo().setWhiteTiger(guild.getGuildInfo().getWhiteTiger() - guildBanner.getChangiconwhitetiger());
										guild.getGuildInfo().setSuzaku(guild.getGuildInfo().getSuzaku() - guildBanner.getChangiconsuzaku());
										guild.getGuildInfo().setBasaltic(guild.getGuildInfo().getBasaltic() - guildBanner.getChangiconbasaltic());
										guild.getGuildInfo().setStockGold(guild.getGuildInfo().getStockGold() - guildBanner.getChangicongold());
										guild.addParseEvent(ResManager.getInstance().getString("帮旗更换造型"), getEventString(ResManager.getInstance().getString("帮旗更换造型"), ResManager.getInstance().getString("青龙令"), guildBanner.getChangicondragon(), ResManager.getInstance().getString("白虎令"), guildBanner.getChangiconwhitetiger(),
											ResManager.getInstance().getString("朱雀令"), guildBanner.getChangiconsuzaku(), ResManager.getInstance().getString("玄武令"), guildBanner.getChangiconbasaltic(), ResManager.getInstance().getString("铜币"), guildBanner.getChangicongold()));
										saveGuildData(3, guild, null);

										sendGuildInfo(player, Notity_ChangeBanner, guild);
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，帮旗造型修改成功"));
										guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮帮主已启用了新的帮旗造型"));
										guild.boardcastInfo(Notity_BannerBuff);
										sendMessage.setBtErrorCode(Error_Success);
										logState("修改帮旗造型成功", guild);
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，更换造型所需消耗的帮贡仓库资源不足"));
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的帮旗造型选择错误"));
								}
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请选择一个新的帮旗造型"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才可以修改帮旗"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 更换帮旗名字
	 *
	 * @param message 通知世界服务器更换帮旗名字
	 * @return
	 */
	public void reqInnerGuildChangeBannerNameToWorld(ReqInnerGuildChangeBannerNameToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildChangeBannerNameToClientMessage sendMessage = new ResGuildChangeBannerNameToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (getGuildMapManager().FindByBannerName(message.getBannerName()) == null) {
							if (!guild.getGuildInfo().getGuildBanner().equals(message.getBannerName())) {
								GuildBanner guildBanner = getGuildBannerMap().get((int) guild.getGuildInfo().getBannerLevel());
								if (guildBanner != null) {
									if (guildBanner.getChangenamedragon() <= guild.getGuildInfo().getDragon()
										&& guildBanner.getChangenamewhitetiger() <= guild.getGuildInfo().getWhiteTiger()
										&& guildBanner.getChangenamesuzaku() <= guild.getGuildInfo().getSuzaku()
										&& guildBanner.getChangenamebasaltic() <= guild.getGuildInfo().getBasaltic()
										&& guildBanner.getChangenamegold() <= guild.getGuildInfo().getStockGold()) {
										getGuildMapManager().remove(guild);
										guild.getGuildInfo().setGuildBanner(message.getBannerName());
										getGuildMapManager().put(guild);
										guild.getGuildInfo().setDragon(guild.getGuildInfo().getDragon() - guildBanner.getChangenamedragon());
										guild.getGuildInfo().setWhiteTiger(guild.getGuildInfo().getWhiteTiger() - guildBanner.getChangenamewhitetiger());
										guild.getGuildInfo().setSuzaku(guild.getGuildInfo().getSuzaku() - guildBanner.getChangenamesuzaku());
										guild.getGuildInfo().setBasaltic(guild.getGuildInfo().getBasaltic() - guildBanner.getChangenamebasaltic());
										guild.getGuildInfo().setStockGold(guild.getGuildInfo().getStockGold() - guildBanner.getChangenamegold());
										guild.addParseEvent(ResManager.getInstance().getString("帮旗改名"), getEventString(ResManager.getInstance().getString("帮旗改名"), guild.getGuildInfo().getGuildBanner(), ResManager.getInstance().getString("青龙令"), guildBanner.getChangenamedragon(),
											ResManager.getInstance().getString("白虎令"), guildBanner.getChangenamewhitetiger(), ResManager.getInstance().getString("朱雀令"), guildBanner.getChangenamesuzaku(), ResManager.getInstance().getString("玄武令"), guildBanner.getChangenamebasaltic(), ResManager.getInstance().getString("铜币"), guildBanner.getChangenamegold()));
										saveGuildData(3, guild, null);

										sendGuildInfo(player, Notity_ChangeBanner, guild);
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，帮旗改名成功"));
										guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮帮主启用了新的帮旗名称：{1}"), message.getBannerName());
										guild.boardcastInfo(Notity_BannerBuff);
										sendMessage.setBtErrorCode(Error_Success);
										logState("修改帮旗名称成功", guild);
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，改名所需消耗的帮贡仓库资源不足"));
									}
								}
							} else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请输入一个新的帮旗名字"));
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮旗名字已被其他帮会占用"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才可以修改帮旗"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 帮旗升级
	 *
	 * @param message 通知世界服务器帮旗升级
	 * @return
	 */
	public void reqInnerGuildBannerLevelUpToWorld(ReqInnerGuildBannerLevelUpToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildBannerLevelUpToClientMessage sendMessage = new ResGuildBannerLevelUpToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (guild.getGuildInfo().getBannerLevel() < getGuildBannerMap().size()) {
							GuildBanner guildBanner = getGuildBannerMap().get((int) guild.getGuildInfo().getBannerLevel());
							if (guildBanner != null) {
								if (guildBanner.getLevelupdragon() <= guild.getGuildInfo().getDragon()
									&& guildBanner.getLevelupwhitetiger() <= guild.getGuildInfo().getWhiteTiger()
									&& guildBanner.getLevelupsuzaku() <= guild.getGuildInfo().getSuzaku()
									&& guildBanner.getLevelupbasaltic() <= guild.getGuildInfo().getBasaltic()
									&& guildBanner.getLevelupgold() <= guild.getGuildInfo().getStockGold()) {

									guild.getGuildInfo().setDragon(guild.getGuildInfo().getDragon() - guildBanner.getLevelupdragon());
									guild.getGuildInfo().setWhiteTiger(guild.getGuildInfo().getWhiteTiger() - guildBanner.getLevelupwhitetiger());
									guild.getGuildInfo().setSuzaku(guild.getGuildInfo().getSuzaku() - guildBanner.getLevelupsuzaku());
									guild.getGuildInfo().setBasaltic(guild.getGuildInfo().getBasaltic() - guildBanner.getLevelupbasaltic());
									guild.getGuildInfo().setStockGold(guild.getGuildInfo().getStockGold() - guildBanner.getLevelupgold());

									if (RandomUtils.nextInt(10000) <= guildBanner.getSuccesscof()) {
										guild.getGuildInfo().setBannerLevel((byte) (guild.getGuildInfo().getBannerLevel() + 1));
										guild.getGuildInfo().setBannerIcon(guild.getGuildInfo().getBannerIcon() + 100);
										guild.addParseEvent(ResManager.getInstance().getString("帮旗升级"), getEventString(ResManager.getInstance().getString("帮旗升级"), guild.getGuildInfo().getBannerLevel(), ResManager.getInstance().getString("青龙令"), guildBanner.getLevelupdragon(),
											ResManager.getInstance().getString("白虎令"), guildBanner.getLevelupwhitetiger(), ResManager.getInstance().getString("朱雀令"), guildBanner.getLevelupsuzaku(), ResManager.getInstance().getString("玄武令"), guildBanner.getLevelupbasaltic(), ResManager.getInstance().getString("铜币"), guildBanner.getLevelupgold()));
										saveGuildData(3, guild, null);//"本帮帮旗已升级到[%d]级，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"

										sendGuildInfo(player, Notity_ChangeBanner, guild);
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，帮旗升级成功"));
										guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮帮旗已升级到[{1}]级，她带来了更强大的属性与收益加成"), String.valueOf(guild.getGuildInfo().getBannerLevel()));
										guild.boardcastInfo(Notity_BannerBuff);
										sendMessage.setBtErrorCode(Error_Success);
										MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("[{1}]帮会的帮旗升级至[{2}]级，他们变得更强大了"), guild.getGuildInfo().getGuildName(), String.valueOf(guild.getGuildInfo().getBannerLevel()));
										
										logState("升级帮旗成功", guild);
									} else {
										guild.addParseEvent(ResManager.getInstance().getString("帮旗升级"), getEventString(ResManager.getInstance().getString("帮旗升级"), guild.getGuildInfo().getBannerLevel(), ResManager.getInstance().getString("青龙令"), guildBanner.getLevelupdragon(),
											ResManager.getInstance().getString("白虎令"), guildBanner.getLevelupwhitetiger(), ResManager.getInstance().getString("朱雀令"), guildBanner.getLevelupsuzaku(), ResManager.getInstance().getString("玄武令"), guildBanner.getLevelupbasaltic(), ResManager.getInstance().getString("铜币"), guildBanner.getLevelupgold()));
										saveGuildData(3, guild, null);//"本帮帮旗已升级到[%d]级，消耗帮贡仓库：%s*%d；%s*%d；%s*%d；%s*%d；%s*%d"

										sendGuildInfo(player, Notify_AddOrUpdate, guild);
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，帮旗升级失败"));
										sendMessage.setBtErrorCode(Error_Fail);
										logState("升级帮旗失败", guild);
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，升级所需消耗的帮贡仓库资源不足"));
								}
							}
						} else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的帮旗等级已达顶级"));
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才可以修改帮旗"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 添加外交关系
	 *
	 * @param message 通知世界服务器添加外交关系
	 * @return
	 */
	public void reqInnerGuildAddDiplomaticToWorld(ReqInnerGuildAddDiplomaticToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildAddDiplomaticToClientMessage sendMessage = new ResGuildAddDiplomaticToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				Guild otherGuild = getGuildMapManager().FindById(message.getOtherGuildId());
				if (guild != null && otherGuild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (guild.getGuildInfo().getGuildId() != otherGuild.getGuildInfo().getGuildId()) {
							if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Friend_Type) {
								if (!findfriendDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId())) {
									if (!findenemyDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId())) {
										if (!findenemyDiplomaticInfo(otherGuild.getGuildInfo(), guild.getGuildInfo().getGuildId())) {
											Player otherPlayer = otherGuild.getPowerLevelPlayer(1);
											if (otherPlayer != null) {
												ApplyAndInvite applyAndInvite = otherGuild.findApplyAndInvite(guild.getGuildInfo().getGuildId());
												if (applyAndInvite == null && message.getArgee() == 1) {
													sendAllGuildInfoToSelf(otherPlayer);

													sendMessage.setDiplomaticType(message.getDiplomaticType());
													sendMessage.setApplyGuildId(guild.getGuildInfo().getGuildId());
													sendMessage.setBtErrorCode(Error_Success);
													MessageUtil.tell_player_message(otherPlayer, sendMessage);
													MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您的结盟邀请已发送，请等待对方帮主：{1} 的同意"), otherPlayer.getName());

													if (guild.findApplyAndInvite(otherGuild.getGuildInfo().getGuildId()) == null) {
														applyAndInvite = new ApplyAndInvite();
														applyAndInvite.setGuildid(otherGuild.getGuildInfo().getGuildId());
														applyAndInvite.setType(ApplyAndInvite.Diplomatic_Friend_Type);
														applyAndInvite.setSrcid(guild.getGuildInfo().getGuildId());
														applyAndInvite.setDestid(otherGuild.getGuildInfo().getGuildId());
														guild.getGuildapplyAndInviteList().add(applyAndInvite);
													}
												} else {
													if (applyAndInvite != null && applyAndInvite.getDestid() == guild.getGuildInfo().getGuildId() && applyAndInvite.getSrcid() == otherGuild.getGuildInfo().getGuildId()) {
														if (message.getArgee() == 1) {
															DiplomaticInfo diplomaticInfo = new DiplomaticInfo();
															diplomaticInfo.setDiplomaticTime((int) (System.currentTimeMillis() / 1000));
															diplomaticInfo.setGuildId(guild.getGuildInfo().getGuildId());
															otherGuild.getGuildInfo().getFriendGuildList().add(diplomaticInfo);
															sendGuildInfo(otherPlayer, Notify_AddOrUpdate, otherGuild);
															otherGuild.addParseEvent(ResManager.getInstance().getString("行会结盟"), getEventString(ResManager.getInstance().getString("行会结盟"), guild.getGuildInfo().getGuildName()));
															saveGuildData(3, otherGuild, null);
															MessageUtil.notify_player(otherPlayer, Notifys.SUCCESS, ResManager.getInstance().getString("对方帮主：{1}已经同意了您的结盟邀请"), player.getName());
															otherGuild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("恭喜恭喜，您的帮会与{1}帮会结成联盟，并肩战斗"), guild.getGuildInfo().getGuildName());
															otherGuild.boardcastInfo(Notity_DiplomaticChange);
															sendFriendGuildToGame(otherGuild);

															DiplomaticInfo otherDiplomaticInfo = new DiplomaticInfo();
															otherDiplomaticInfo.setDiplomaticTime((int) (System.currentTimeMillis() / 1000));
															otherDiplomaticInfo.setGuildId(otherGuild.getGuildInfo().getGuildId());
															guild.getGuildInfo().getFriendGuildList().add(otherDiplomaticInfo);
															sendGuildInfo(player, Notify_AddOrUpdate, guild);
															guild.addParseEvent(ResManager.getInstance().getString("行会结盟"), getEventString(ResManager.getInstance().getString("行会结盟"), otherGuild.getGuildInfo().getGuildName()));
															saveGuildData(3, guild, null);
															guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("恭喜恭喜，您的帮会与{1}帮会结成联盟，并肩战斗"), otherGuild.getGuildInfo().getGuildName());
															guild.boardcastInfo(Notity_DiplomaticChange);
															sendFriendGuildToGame(guild);

															MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("[帮会]{1}与[帮会]{2}结成联盟，江湖风起云涌"), guild.getGuildInfo().getGuildName(), otherGuild.getGuildInfo().getGuildName());
														} else {
															MessageUtil.notify_player(otherPlayer, Notifys.ERROR, ResManager.getInstance().getString("很遗憾，对方帮主：{1}拒绝了您的结盟邀请"), player.getName());
															MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您拒绝了{1}帮会的结盟请求"), otherGuild.getGuildInfo().getGuildName());
														}
														otherGuild.deleteApplyAndInvite(guild.getGuildInfo().getGuildId());
													}
												}
											} else {
												MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("帮会结盟需对方帮主同意，但他目前不在线"));
											}
										} else {
											MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您是该帮会的敌对帮会，如需结盟请先联系其帮主：{1} 解除敌对关系"), otherGuild.getGuildInfo().getBangZhuName());
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该帮会是您的敌对帮会，如需结盟请先解除外交关系"));
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该帮会已经是您的联盟帮会了"));
								}
							} else if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Enemy_Type) {
								if (!findfriendDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId())) {
									if (!findenemyDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId())) {
										DiplomaticInfo diplomaticInfo = new DiplomaticInfo();
										diplomaticInfo.setGuildId(otherGuild.getGuildInfo().getGuildId());
										diplomaticInfo.setDiplomaticTime((int) (System.currentTimeMillis() / 1000));
										guild.getGuildInfo().getEnemyGuildList().add(diplomaticInfo);
										sendGuildInfo(player, Notify_AddOrUpdate, guild);
										guild.addParseEvent(ResManager.getInstance().getString("本帮添加其他行会敌对"), getEventString(ResManager.getInstance().getString("本帮添加其他行会敌对"), otherGuild.getGuildInfo().getGuildName()));
										saveGuildData(3, guild, null);
										MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功添加帮会：{1}为敌对帮会"), otherGuild.getGuildInfo().getGuildName());
										guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮会已将{1}帮会列为敌对帮会"), otherGuild.getGuildInfo().getGuildName());
										guild.boardcastInfo(Notity_DiplomaticChange);
										otherGuild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("{1}帮会已将本帮列为敌对帮会"), guild.getGuildInfo().getGuildName());
										otherGuild.addParseEvent(ResManager.getInstance().getString("本帮被其他行会加为敌对"), getEventString(ResManager.getInstance().getString("本帮被其他行会加为敌对"), guild.getGuildInfo().getGuildName()));

										MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("[帮会]{1}将[帮会]{2}列入了敌对帮会名单"), guild.getGuildInfo().getGuildName(), otherGuild.getGuildInfo().getGuildName());

										Player otherPlayer = otherGuild.getPowerLevelPlayer(1);
										if (otherPlayer != null) {
											sendAllGuildInfoToSelf(otherPlayer);

											sendMessage.setDiplomaticType(message.getDiplomaticType());
											sendMessage.setApplyGuildId(guild.getGuildInfo().getGuildId());
											sendMessage.setBtErrorCode(Error_Success);
											MessageUtil.tell_player_message(otherPlayer, sendMessage);
										} else {
											//弹出ICO提示
											ApplyAndInvite applyAndInvite = new ApplyAndInvite();
											applyAndInvite.setGuildid(guild.getGuildInfo().getGuildId());
											applyAndInvite.setType(message.getDiplomaticType());
											applyAndInvite.setSrcid(1);
											otherGuild.getSaveSendMsgList().add(applyAndInvite);
										}
									} else {
										MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该帮会已经是您的敌对帮会"));
									}
								} else {
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该帮会是您的联盟帮会，如需敌对请先解除外交关系"));
								}
							}
						} else {
							if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Friend_Type) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法将自己的帮会列为联盟"));
							} else if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Enemy_Type) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法将自己的帮会列为敌对"));
							}
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才能设立外交关系"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 删除外交关系
	 *
	 * @param message 通知世界服务器删除外交关系
	 * @return
	 */
	public void reqInnerGuildDeleteDiplomaticToWorld(ReqInnerGuildDeleteDiplomaticToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildDeleteDiplomaticToClientMessage sendMessage = new ResGuildDeleteDiplomaticToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				Guild otherGuild = getGuildMapManager().FindById(message.getOtherGuildId());
				if (guild != null && otherGuild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (guild.getGuildInfo().getGuildId() != otherGuild.getGuildInfo().getGuildId()) {
							if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Friend_Type) {
								deletefriendDiplomaticInfo(otherGuild.getGuildInfo(), guild.getGuildInfo().getGuildId());
								saveGuildData(3, otherGuild, null);
								deletefriendDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId());
								sendGuildInfo(player, Notify_AddOrUpdate, guild);
								saveGuildData(3, guild, null);
								MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("与[帮会]{1}的联盟关系解除成功"), otherGuild.getGuildInfo().getGuildName());
								guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮与帮会：[{1}]之间的联盟关系解除了"), otherGuild.getGuildInfo().getGuildName());
								guild.boardcastInfo(Notity_DiplomaticChange);
								otherGuild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("帮会：[{1}]解除了与本帮之间的联盟关系"), guild.getGuildInfo().getGuildName());
								otherGuild.boardcastInfo(Notity_DiplomaticChange);

								MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("[帮会]{1}与[帮会]{2}解除了联盟关系，江湖风起云涌"), guild.getGuildInfo().getGuildName(), otherGuild.getGuildInfo().getGuildName());

								Player otherPlayer = otherGuild.getPowerLevelPlayer(1);
								if (otherPlayer != null) {
									sendMessage.setDiplomaticType(message.getDiplomaticType());
									sendMessage.setApplyGuildId(guild.getGuildInfo().getGuildId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(otherPlayer, sendMessage);
								} else {
									//弹出ICO提示
									ApplyAndInvite applyAndInvite = new ApplyAndInvite();
									applyAndInvite.setGuildid(guild.getGuildInfo().getGuildId());
									applyAndInvite.setType(message.getDiplomaticType());
									applyAndInvite.setSrcid(2);
									otherGuild.getSaveSendMsgList().add(applyAndInvite);
								}
							} else if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Enemy_Type) {
								deleteenemyDiplomaticInfo(guild.getGuildInfo(), otherGuild.getGuildInfo().getGuildId());
								sendGuildInfo(player, Notify_AddOrUpdate, guild);
								saveGuildData(3, guild, null);
								MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您解除了对[帮会]{1}的敌对状态"), otherGuild.getGuildInfo().getGuildName());
								guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("本帮解除了对帮会：[{1}]的敌对状态"), otherGuild.getGuildInfo().getGuildName());
								guild.boardcastInfo(Notity_DiplomaticChange);
								otherGuild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("帮会：[{1}]解除了对本帮的敌对状态"), guild.getGuildInfo().getGuildName());

								MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("[帮会]{1}解除了对[帮会]{2}的敌对状态，江湖风起云涌"), guild.getGuildInfo().getGuildName(), otherGuild.getGuildInfo().getGuildName());

								Player otherPlayer = otherGuild.getPowerLevelPlayer(1);
								if (otherPlayer != null) {
									sendMessage.setDiplomaticType(message.getDiplomaticType());
									sendMessage.setApplyGuildId(guild.getGuildInfo().getGuildId());
									sendMessage.setBtErrorCode(Error_Success);
									MessageUtil.tell_player_message(otherPlayer, sendMessage);
								} else {
									//弹出ICO提示
									ApplyAndInvite applyAndInvite = new ApplyAndInvite();
									applyAndInvite.setGuildid(guild.getGuildInfo().getGuildId());
									applyAndInvite.setType(message.getDiplomaticType());
									applyAndInvite.setSrcid(2);
									otherGuild.getSaveSendMsgList().add(applyAndInvite);
								}
							}
						} else {
							if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Friend_Type) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法将自己的帮会进行此操作"));
							} else if (message.getDiplomaticType() == ApplyAndInvite.Diplomatic_Enemy_Type) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您无法将自己的帮会进行此操作"));
							}
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只有帮主才能设立外交关系"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 执行解散帮会
	 *
	 * @param guild 帮会
	 * @param player 玩家
	 * @param boNotify 是否发送消息
	 * @return boolean
	 */
	public boolean deleteGuildFromAll(Guild guild, Player player, boolean boNotify) {
		if (getGuildMapManager().remove(guild)) {//数据库
			if (deleteGuild(guild)) {
				List<Player> players = new ArrayList<Player>();
				for (Map.Entry<Long, MemberInfo> entry : guild.getMemberinfoHashMap().entrySet()) {
					MemberInfo deletememberInfo = entry.getValue();
					if (deletememberInfo != null) {
						Player deletePlayer = PlayerManager.getInstance().getPlayer(deletememberInfo.getUserId());
						if (deletePlayer != null) {
							players.add(deletePlayer);
						}
						guild.deleteMember(deletememberInfo);
					}
				}
				for (int i = 0; i < guild.getEventInfoList().size(); i++) {
					EventInfo eventInfo = guild.getEventInfoList().get(i);
					if (eventInfo != null) {
						guild.deleteEvent(eventInfo);
					}
				}
				for (Map.Entry<Long, Guild> entry : getGuildMapManager().entrySet()) {
					Guild allguild = entry.getValue();
					if (allguild != null) {
						boolean bofind = false;
						if (deletefriendDiplomaticInfo(allguild.getGuildInfo(), guild.getGuildInfo().getGuildId())) {
							bofind = true;
						}
						if (deleteenemyDiplomaticInfo(allguild.getGuildInfo(), guild.getGuildInfo().getGuildId())) {
							bofind = true;
						}
						if (bofind) {
							saveGuildData(3, allguild, null);
							allguild.boardcastInfo(Notity_DiplomaticChange);
						}
					}
				}
				//消息
				if (boNotify && player != null) {
					guild.sendAllMemberNotify(Notifys.SUCCESS, ResManager.getInstance().getString("您所处的帮会：{1}，被帮主：{2}，解散了。"), guild.getGuildInfo().getGuildName(), player.getName());
				}
				for (int i = 0; i < players.size(); i++) {
					Player deletePlayer = players.get(i);
					if (deletePlayer != null) {
						if (i == players.size() - 1) {
							sendGuildAndMemberInfo(null, deletePlayer, Notity_DeleteGuild, guild);
						} else {
							sendGuildAndMemberInfo(null, deletePlayer, Notify_Delete, guild);
						}
						guild.removeMember(deletePlayer);
					}
				}
				setGuildLog(guild, player == null ? "系统删除" : player.getName() + "玩家删除", "成员数量=" + guild.getMemberinfoHashMap().size() + "=公会信息=" + JSON.toJSONString(guild.getGuildInfo()));
				return true;
			}
		}
		return false;
	}

	/**
	 * 解散帮会
	 *
	 * @param message 通知世界服务器解散帮会
	 * @return
	 */
	public void reqInnerGuildDeleteGuildToWorld(ReqInnerGuildDeleteGuildToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildDeleteGuildToClientMessage sendMessage = new ResGuildDeleteGuildToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(player);
					if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
						if (deleteGuildFromAll(guild, player, true)) {
							sendMessage.setBtErrorCode(Error_Success);
						}
					} else {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉、只有帮主才有权限解散帮会"));
					}
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 获取帮会事件列表
	 *
	 * @param message 通知世界服务器获取帮会事件列表
	 * @return
	 */
	public void reqInnerGuildGetEventListToWorld(ReqInnerGuildGetEventListToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			ResGuildGetEventListToClientMessage sendMessage = new ResGuildGetEventListToClientMessage();
			sendMessage.setBtErrorCode(Error_Fail);
			if (player.getGuildid() == message.getGuildId()) {
				Guild guild = getGuildMapManager().FindById(player.getGuildid());
				if (guild != null) {
					for (int i = 0; i < guild.getEventInfoList().size(); i++) {
						EventInfo eventInfo = guild.getEventInfoList().get(i);
						if (eventInfo != null) {
							sendMessage.getEventList().add(eventInfo);
						}
					}
					sendMessage.setBtErrorCode(Error_Success);
				} else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，没有找到该帮会"));
				}
			}
			MessageUtil.tell_player_message(player, sendMessage);
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	/**
	 * 内部帮会通知消息
	 *
	 * @param message 消息
	 * @return
	 */
	public void ReqInnerGuildNotifyToWorld(ReqInnerGuildNotifyToWorldMessage message) {
		Player player = PlayerManager.getInstance().getPlayer(message.getPlayerId());
		if (player != null) {
			if (player.getGuildid() != 0) {
				Guild guild = getGuild(player.getGuildid());
				if (guild != null) {
					switch (message.getNotifytype()) {
						case 0: {
							guild.sendAllMemberNotifyNoMe(player, Notifys.CUTOUT, message.getGuildNotify());
							guild.addEvent(ResManager.getInstance().getString("成员被其他玩家击败"), message.getGuildNotify());
						}
						break;
						case 1: {
							//guild.sendAllMemberNotifyNoMe(player, Notifys.CUTOUT, message.getGuildNotify());
							guild.addEvent(ResManager.getInstance().getString("成员击败其他玩家"), message.getGuildNotify());
						}
						break;
						case 2: {
							guild.addEvent(ResManager.getInstance().getString("成员击败BOSS"), message.getGuildNotify());
						}
						break;
						case 3: {
							guild.addEvent(ResManager.getInstance().getString("成员被BOSS击败"), message.getGuildNotify());
						}
						break;
					}
				}
			}
		} else {
			log.error(String.format("没有找到玩家 玩家ID[%s]", String.valueOf(message.getPlayerId())));
		}
	}

	public boolean findfriendDiplomaticInfo(GuildInfo guildInfo, long findguildid) {
		for (int i = 0; i < guildInfo.getFriendGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getFriendGuildList().get(i);
			if (diplomaticInfo != null && diplomaticInfo.getGuildId() == findguildid) {
				return true;
			}
		}
		return false;
	}

	public boolean findenemyDiplomaticInfo(GuildInfo guildInfo, long findguildid) {
		for (int i = 0; i < guildInfo.getEnemyGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getEnemyGuildList().get(i);
			if (diplomaticInfo != null && diplomaticInfo.getGuildId() == findguildid) {
				return true;
			}
		}
		return false;
	}

	public boolean deletefriendDiplomaticInfo(GuildInfo guildInfo, long deleteguildid) {
		for (int i = 0; i < guildInfo.getFriendGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getFriendGuildList().get(i);
			if (diplomaticInfo != null && diplomaticInfo.getGuildId() == deleteguildid) {
				guildInfo.getFriendGuildList().remove(diplomaticInfo);
				sendFriendGuildToGame(guildInfo);
				return true;
			}
		}
		return false;
	}

	public boolean deleteenemyDiplomaticInfo(GuildInfo guildInfo, long deleteguildid) {
		for (int i = 0; i < guildInfo.getEnemyGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getEnemyGuildList().get(i);
			if (diplomaticInfo != null && diplomaticInfo.getGuildId() == deleteguildid) {
				guildInfo.getEnemyGuildList().remove(diplomaticInfo);
				return true;
			}
		}
		return false;
	}

	public void setGuildLog(Guild guild, String runName, String logString) {
		GuildLog guildLog = new GuildLog();
		guildLog.setGuildId(guild.getGuildInfo().getGuildId());
		guildLog.setGuildName(guild.getGuildInfo().getGuildName());
		guildLog.setRunName(runName);
		guildLog.setLogString(logString);
		LogService.getInstance().execute(guildLog);
	}

	/**
	 * 发给所有成员的文字消息
	 *
	 * @param tid
	 * @param type
	 * @param message
	 * @param values
	 */
	public void notify_guild_all_player(long tid, String message) {
		Guild guild = getGuild(tid);
		if (guild != null) {
			ArrayList<Long> list = new ArrayList<Long>();
			Iterator<MemberInfo> it = guild.getMemberinfoHashMap().values().iterator();
			while (it.hasNext()) {
				list.add(it.next().getUserId());
			}
			MessageUtil.notify_player(list, Notifys.CHAT_GROUP, message);
		}
	}
	
	/**
	 * 发送同盟帮会信息
	 * @param info
	 */
	public void sendFriendGuildToGame(GuildInfo info){
		if(info.getFriendGuildList()!=null){
			int size = info.getFriendGuildList().size();
			
			ResFriendGuildToGameMessage msg = new ResFriendGuildToGameMessage();
			FriendGuild friend = new FriendGuild();
			friend.setGuildId(info.getGuildId());
			for (int i = 0; i < size; i++) {
				friend.getFriendGuilds().add(info.getFriendGuildList().get(i).getGuildId());
			}
			msg.setFriend(friend);
			MessageUtil.send_to_game(msg);
		}
	}
	
	/**
	 * 发送同盟帮会信息
	 * @param guild
	 */
	public void sendFriendGuildToGame(Guild guild){
		if(guild.getGuildInfo()!=null && guild.getGuildInfo().getFriendGuildList()!=null){
			int size = guild.getGuildInfo().getFriendGuildList().size();
			ResFriendGuildToGameMessage msg = new ResFriendGuildToGameMessage();
			FriendGuild friend = new FriendGuild();
			friend.setGuildId(guild.getGuildInfo().getGuildId());
			for (int i = 0; i < size; i++) {
				friend.getFriendGuilds().add(guild.getGuildInfo().getFriendGuildList().get(i).getGuildId());
			}
			msg.setFriend(friend);
			MessageUtil.send_to_game(msg);
		}
	}
	
	/**
	 * 发送同盟帮会信息
	 */
	public void syncFriendGuildToGame() {
		ResFriendGuildListToGameMessage msg = new ResFriendGuildListToGameMessage();
		Iterator<Entry<Long, Guild>> iterator = getGuildMapManager().entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Long, Guild> entry = (Entry<Long, Guild>) iterator
					.next();
			Guild guild = entry.getValue();
			if(guild.getGuildInfo()!=null && guild.getGuildInfo().getFriendGuildList()!=null){
				int size = guild.getGuildInfo().getFriendGuildList().size();
				if(size > 0){
					FriendGuild friend = new FriendGuild();
					friend.setGuildId(guild.getGuildInfo().getGuildId());
					for (int i = 0; i < size; i++) {
						friend.getFriendGuilds().add(guild.getGuildInfo().getFriendGuildList().get(i).getGuildId());
					}
					msg.getFriend().add(friend);
				}
			}
		}
		MessageUtil.send_to_game(msg);
	}
}
