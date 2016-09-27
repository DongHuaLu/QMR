package com.game.guild.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.config.Config;
import com.game.country.manager.CountryManager;
import com.game.db.bean.GuildeventBean;
import com.game.db.bean.GuildmemberBean;
import com.game.guild.bean.EventInfo;
import com.game.guild.bean.GuildInfo;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.message.ResGuildAddDiplomaticToClientMessage;
import com.game.guild.message.ResGuildDeleteDiplomaticToClientMessage;
import com.game.guild.message.ResGuildGetGuildListToClientMessage;
import com.game.guild.message.ResInnerGuildAloneGuildInfoToServerMessage;
import com.game.json.JSONserializable;
import com.game.message.Message;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;

/**
 *
 * @author 杨洪岚 公会类
 */
public class Guild {

	private Logger log = Logger.getLogger(Guild.class);
	private int Event_MaxNum = 50;		//事件保存最大数
	private int country;			//帮会所属国家
	private GuildInfo guildInfo = new GuildInfo();
	private HashMap<Long, MemberInfo> memberinfoHashMap = new HashMap<Long, MemberInfo>();
	private List<EventInfo> eventInfoList = new ArrayList<EventInfo>();
	private List<ApplyAndInvite> guildapplyAndInviteList = new ArrayList<ApplyAndInvite>();
	private List<ApplyAndInvite> saveSendMsgList = new ArrayList<ApplyAndInvite>();	//保存要给帮主下次上线发送的消息
	private List<Long> ActiveValueList = new ArrayList<Long>();	//活跃值记录列表 记录上线的玩家ID
	private long calActiveValueTime;	//计算活跃值时间

	public long getCalActiveValueTime() {
		return calActiveValueTime;
	}

	public void setCalActiveValueTime(long calActiveValueTime) {
		this.calActiveValueTime = calActiveValueTime;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public List<Long> getActiveValueList() {
		return ActiveValueList;
	}

	public void setActiveValueList(List<Long> ActiveValueList) {
		this.ActiveValueList = ActiveValueList;
	}

	public List<ApplyAndInvite> getSaveSendMsgList() {
		return saveSendMsgList;
	}

	public void setSaveSendMsgList(List<ApplyAndInvite> saveSendMsgList) {
		this.saveSendMsgList = saveSendMsgList;
	}

	public void sendIcoMsgList(Player player) {
		if (player.getGuildid() == getGuildInfo().getGuildId()) {
			MemberInfo memberInfo = findMemberInfo(player);
			if (memberInfo != null && memberInfo.getGuildPowerLevel() == 1) {
				ResGuildGetGuildListToClientMessage updateMessage = new ResGuildGetGuildListToClientMessage();
				updateMessage.setBtErrorCode((byte) 1);
				for (int i = 0; i < getSaveSendMsgList().size(); i++) {
					ApplyAndInvite applyAndInvite = getSaveSendMsgList().get(i);
					if (applyAndInvite != null) {
						Guild guild = GuildWorldManager.getInstance().getGuild(applyAndInvite.getGuildid());
						if (guild != null && !updateMessage.getGuildList().contains(guild.getGuildInfo())) {
							updateMessage.getGuildList().add(guild.getGuildInfo());
						}
					}
				}
				if (!updateMessage.getGuildList().isEmpty()) {
					MessageUtil.tell_player_message(player, updateMessage);
				}
				for (int i = 0; i < getSaveSendMsgList().size(); i++) {
					ApplyAndInvite applyAndInvite = getSaveSendMsgList().get(i);
					if (applyAndInvite != null) {
						if (applyAndInvite.getSrcid() == 1) {
							ResGuildAddDiplomaticToClientMessage sendMessage = new ResGuildAddDiplomaticToClientMessage();
							sendMessage.setBtErrorCode((byte) 0);
							sendMessage.setDiplomaticType(applyAndInvite.getType());
							sendMessage.setApplyGuildId(applyAndInvite.getGuildid());
							MessageUtil.tell_player_message(player, sendMessage);
						} else {
							ResGuildDeleteDiplomaticToClientMessage sendMessage = new ResGuildDeleteDiplomaticToClientMessage();
							sendMessage.setBtErrorCode((byte) 0);
							sendMessage.setDiplomaticType(applyAndInvite.getType());
							sendMessage.setApplyGuildId(applyAndInvite.getGuildid());
							MessageUtil.tell_player_message(player, sendMessage);
						}
					}
				}
				getSaveSendMsgList().clear();
			}
		}
	}

	public List<ApplyAndInvite> getGuildapplyAndInviteList() {
		return guildapplyAndInviteList;
	}

	public void setGuildapplyAndInviteList(List<ApplyAndInvite> guildapplyAndInviteList) {
		this.guildapplyAndInviteList = guildapplyAndInviteList;
	}

	public ApplyAndInvite findApplyAndInvite(long guildid) {
		for (int i = 0; i < getGuildapplyAndInviteList().size(); i++) {
			ApplyAndInvite applyAndInvite = getGuildapplyAndInviteList().get(i);
			if (applyAndInvite != null && applyAndInvite.getGuildid() == guildid) {
				return applyAndInvite;
			}
		}
		return null;
	}

	public boolean deleteApplyAndInvite(long guildid) {
		for (int i = 0; i < getGuildapplyAndInviteList().size(); i++) {
			ApplyAndInvite applyAndInvite = getGuildapplyAndInviteList().get(i);
			if (applyAndInvite != null && applyAndInvite.getGuildid() == guildid) {
				getGuildapplyAndInviteList().remove(applyAndInvite);
				return true;
			}
		}
		return false;
	}

	public List<EventInfo> getEventInfoList() {
		return eventInfoList;
	}

	public GuildInfo getGuildInfo() {
		return guildInfo;
	}

	public void setGuildInfo(GuildInfo guildInfo) {
		this.guildInfo = guildInfo;
	}

	public HashMap<Long, MemberInfo> getMemberinfoHashMap() {
		return memberinfoHashMap;
	}

	public void setMemberinfoHashMap(HashMap<Long, MemberInfo> memberinfoHashMap) {
		this.memberinfoHashMap = memberinfoHashMap;
	}

	public boolean addMember(Player player, byte powerLevel) {
		if (player != null && findMemberInfo(player) == null) {
			MemberInfo memberInfo = new MemberInfo();
			memberInfo.setUserId(player.getId());
//			memberInfo.setBasicInfo(player, getGuildInfo());
			setBasicInfo(memberInfo, player, getGuildInfo());
			memberInfo.setAddTime((int) (System.currentTimeMillis() / 1000));
			memberInfo.setGuildPowerLevel(powerLevel);
			memberinfoHashMap.put(memberInfo.getUserId(), memberInfo);
			player.setGuildid(getGuildInfo().getGuildId());
			player.setLastAfkGuildTime(0);
			return true;
		}
		return false;
	}

	public void removeMember(Player player) {
		if (player != null) {
			memberinfoHashMap.remove(player.getId());
			player.setGuildid(0);
		}
	}

	public void removeMemberById(long playerId) {
		memberinfoHashMap.remove(playerId);
		Player player = PlayerManager.getInstance().getPlayer(playerId);
		if (player != null) {
			player.setGuildid(0);
		}
	}

	public MemberInfo findMemberInfo(Player player) {
		if (player != null) {
			return memberinfoHashMap.get(player.getId());
		}
		return null;
	}

	public MemberInfo findMemberInfoById(Long playerid) {
		return memberinfoHashMap.get(playerid);
	}

	public EventInfo addEvent(String eventType, String eventMessage) {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setEventId(Config.getId());
		eventInfo.setGuildId(getGuildInfo().getGuildId());
		eventInfo.setGuildName(getGuildInfo().getGuildName());
		eventInfo.setMessageType(eventType);
		eventInfo.setMessageTime((int) (System.currentTimeMillis() / 1000));
		eventInfo.setMessage(eventMessage);
		if (saveEvent(eventInfo)) {
			getEventInfoList().add(eventInfo);
			if (getEventInfoList().size() > Event_MaxNum) {
				eventInfo = getEventInfoList().remove(0);
				deleteEvent(eventInfo);
			}
			return eventInfo;
		} else {
			return null;
		}
	}

	public void addParseEvent(String eventType, String parseString, ParseUtil.Parm... parmObjects) {
		ParseUtil parseUtil = new ParseUtil();
		parseUtil.setValue(parseString, parmObjects);
		this.addEvent(eventType, parseUtil.toString());
	}

	/**
	 * 计算战斗力之和和成员数目
	 *
	 * @param
	 * @return
	 */
	public void calFightPowerAndMemberNum() {
		getGuildInfo().setMemberNum((byte) memberinfoHashMap.size());
		getGuildInfo().setMemberFightPower(0);
		for (Map.Entry<Long, MemberInfo> entry : memberinfoHashMap.entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				getGuildInfo().setMemberFightPower(getGuildInfo().getMemberFightPower() + memberInfo.getFightPower());
			}
		}
	}

	/**
	 * 计算活跃值
	 *
	 * @param player 计算活跃值的玩家
	 * @return
	 */
	public void calActiveValue(Player player) {
		if (player == null) {
			getGuildInfo().setActiveValue((byte) 0);
			getActiveValueList().clear();
			for (Map.Entry<Long, MemberInfo> entry : memberinfoHashMap.entrySet()) {
				MemberInfo memberInfo = entry.getValue();
				if (memberInfo != null) {
					if (PlayerManager.getInstance().isOnline(memberInfo.getUserId())) {
						getActiveValueList().add(memberInfo.getUserId());
						getGuildInfo().setActiveValue((byte) getActiveValueList().size());
					}
				}
			}
			if (getGuildInfo().getActiveValue() > 100) {
				getGuildInfo().setActiveValue((byte) 100);
			}
		} else {
			if (!getActiveValueList().contains(player.getId())) {
				getActiveValueList().add(player.getId());
				getGuildInfo().setActiveValue((byte) getActiveValueList().size());
				if (getGuildInfo().getActiveValue() > 100) {
					getGuildInfo().setActiveValue((byte) 100);
				}
			}
		}
	}

	/**
	 * 得到加人权限的玩家
	 *
	 * @param
	 * @return
	 */
	public Player getAddPermissionsPlayer() {
		Player player = null;
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				if (memberInfo.getGuildPowerLevel() == 1) {
					player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				} else if (memberInfo.getGuildPowerLevel() == 2 && player == null) {
					player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				}
			}
		}
		return player;
	}

	public Player getPowerLevelPlayer(int powerLevel) {
		if (powerLevel == 6) {
			return null;
		}
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null && memberInfo.getGuildPowerLevel() == powerLevel) {
				return PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
			}
		}
		return null;
	}

	public MemberInfo getPowerLevelMemberInfo(int powerLevel) {
		if (powerLevel == 6) {
			return null;
		}
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null && memberInfo.getGuildPowerLevel() == powerLevel) {
				return memberInfo;
			}
		}
		return null;
	}

	public void Update() {
		boolean boFind = false;
		Iterator<MemberInfo> iterator = getMemberinfoHashMap().values().iterator();
		while(iterator.hasNext()) {
			MemberInfo memberInfo =  iterator.next();
			if (memberInfo != null) {
				if (memberInfo.getUserId() == 0) {
					iterator.remove();
					continue;
				}
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
//					memberInfo.setBasicInfo(player, getGuildInfo());
					setBasicInfo(memberInfo, player, getGuildInfo());
					if (memberInfo.getGuildPowerLevel() == 1) {
						setBangZhuInfo(getGuildInfo(), player);
					} else if (memberInfo.getGuildPowerLevel() == 2) {
						setViceBangZhuInfo(getGuildInfo(), player);
						boFind = true;
					}
				} else {
					setBasicInfo(memberInfo, null, getGuildInfo());
					if (memberInfo.getGuildPowerLevel() == 1) {
						//getGuildInfo().setBangZhuOnline((byte) 0);
						setBangZhuInfoWithMemberInfo(getGuildInfo(), memberInfo);
					} else if (memberInfo.getGuildPowerLevel() == 2) {
						//getGuildInfo().setViceBangZhuOnline((byte) 0);
						setViceBangZhuInfoWithMemberInfo(getGuildInfo(), memberInfo);
						boFind = true;
					}
				}
			}
		}
		if (!boFind) {
			getGuildInfo().setViceBangZhuid(0);
			getGuildInfo().setViceBangZhuName("");
			getGuildInfo().setViceBangZhuLevel((short) 0);
			getGuildInfo().setViceBangZhuOnline((byte) 0);
		}
		calFightPowerAndMemberNum();
		if (CountryManager.kingcitymap.containsValue(getGuildInfo().getGuildId())){
			getGuildInfo().setOwnKingCity((byte) 1);
		} else {
			getGuildInfo().setOwnKingCity((byte) 0);
		}
	}

	public void sendAllMemberNotifyNoMe(Player noplayer, Notifys type, String message, String... values) {
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null && player != noplayer) {
					MessageUtil.notify_player(player, type, message, values);
				}
			}
		}
	}

	public void sendAllMemberNotify(Notifys type, String message, String... values) {
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
					MessageUtil.notify_player(player, type, message, values);
				}
			}
		}
	}

	public void sendAllMemberMessage(Message message) {
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
					MessageUtil.tell_player_message(player, message);
				}
			}
		}
	}

	public void boardcastInfo(byte notify) {
		ResInnerGuildAloneGuildInfoToServerMessage guildInfoToServerMessage = new ResInnerGuildAloneGuildInfoToServerMessage();
		guildInfoToServerMessage.setNotifyType(notify);
		guildInfoToServerMessage.setGuildInfo(getGuildInfo());
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				Player player = PlayerManager.getInstance().getPlayer(memberInfo.getUserId());
				if (player != null) {
					guildInfoToServerMessage.getRoleId().add(player.getId());
				}
			}
		}
		MessageUtil.send_to_game(guildInfoToServerMessage);
	}

	public boolean deleteMember(MemberInfo memberInfo) {
		try {
			if (GuildWorldManager.getInstance().getGuildmemberDao().delete(memberInfo.getUserId()) == 0) {
				log.error(String.format("帮会成员数据删除错误，玩家ID[%s]", String.valueOf(memberInfo.getUserId())));
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void saveMember(MemberInfo memberInfo) {
		try {
			GuildmemberBean guildmemberBean = new GuildmemberBean();
			guildmemberBean.setGuildid(getGuildInfo().getGuildId());
			guildmemberBean.setGuildname(getGuildInfo().getGuildName());
			guildmemberBean.setUserid(memberInfo.getUserId());
			guildmemberBean.setUsername(memberInfo.getUserName());
			guildmemberBean.setGuildmemberdata(JSONserializable.toString(memberInfo));
			if (GuildWorldManager.getInstance().getGuildmemberDao().update(guildmemberBean) == 0) {
				if (GuildWorldManager.getInstance().getGuildmemberDao().insert(guildmemberBean) == 0) {
					log.error(String.format("帮会成员数据保存错误，玩家ID[%s]", String.valueOf(guildmemberBean.getUserid())));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveAllMember() {
		for (Map.Entry<Long, MemberInfo> entry : getMemberinfoHashMap().entrySet()) {
			MemberInfo memberInfo = entry.getValue();
			if (memberInfo != null) {
				saveMember(memberInfo);
			}
		}
	}

	public boolean deleteEvent(EventInfo eventInfo) {
		try {
			if (GuildWorldManager.getInstance().getGuildeventDao().delete(eventInfo.getEventId()) == 0) {
				log.error(String.format("帮会事件数据删除错误，事件消息[%s]", eventInfo.getMessage()));
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveEvent(EventInfo eventInfo) {
		try {
			GuildeventBean guildeventBean = new GuildeventBean();
			guildeventBean.setEventid(eventInfo.getEventId());
			guildeventBean.setGuildid(getGuildInfo().getGuildId());
			guildeventBean.setEventtype(eventInfo.getMessageType());
			guildeventBean.setEventtime(eventInfo.getMessageTime());
			guildeventBean.setEventdata(JSONserializable.toString(eventInfo));
			if (GuildWorldManager.getInstance().getGuildeventDao().update(guildeventBean) == 0) {
				if (GuildWorldManager.getInstance().getGuildeventDao().insert(guildeventBean) == 0) {
					log.error(String.format("帮会事件数据保存错误，事件消息[%s]", eventInfo.getMessage()));
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void saveAllEvent() {
		for (int i = 0; i < getEventInfoList().size(); i++) {
			EventInfo eventInfo = getEventInfoList().get(i);
			if (eventInfo != null) {
				saveEvent(eventInfo);
			}
		}
	}

	public void saveAll() {
		saveAllMember();
		saveAllEvent();
	}

	public void loadAllMember() {
		try {
			List<GuildmemberBean> list = GuildWorldManager.getInstance().getGuildmemberDao().selectbyid(getGuildInfo().getGuildId());
			Iterator<GuildmemberBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				GuildmemberBean guildmemberBean = (GuildmemberBean) iterator.next();
				if (guildmemberBean != null) {
					MemberInfo memberInfo = (MemberInfo) JSONserializable.toObject(guildmemberBean.getGuildmemberdata(), MemberInfo.class);
					if (memberInfo != null) {
						if (memberInfo.getGuildId() == getGuildInfo().getGuildId()) {
							getMemberinfoHashMap().put(memberInfo.getUserId(), memberInfo);
							if (memberInfo.getGuildPowerLevel() == 1) {
								PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(memberInfo.getUserId());
								if (playerWorldInfo != null) {
									setCountry(playerWorldInfo.getCountry());
								}
							}
						}
					} else {
						log.error(String.format("帮会成员数据读取错误，成员id[%s]", String.valueOf(guildmemberBean.getUserid())));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAllEvent() {
		try {
			List<GuildeventBean> list = GuildWorldManager.getInstance().getGuildeventDao().selectbyid(getGuildInfo().getGuildId());
			Iterator<GuildeventBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				GuildeventBean guildeventBean = (GuildeventBean) iterator.next();
				if (guildeventBean != null) {
					EventInfo eventInfo = (EventInfo) JSONserializable.toObject(guildeventBean.getEventdata(), EventInfo.class);
					if (eventInfo != null) {
						if (eventInfo.getGuildId() == getGuildInfo().getGuildId()) {
							getEventInfoList().add(eventInfo);
						}
					} else {
						log.error(String.format("帮会事件数据读取错误，数据内容[%s]", guildeventBean.getEventdata()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadAll() {
		loadAllMember();
		loadAllEvent();
	}

	public void setBasicInfo(MemberInfo memberInfo, Player player, GuildInfo guildInfo) {
		if (player != null && player.isSyncdata()) {
			memberInfo.setGuildId(guildInfo.getGuildId());
			memberInfo.setGuildName(guildInfo.getGuildName());
			memberInfo.setUserId(player.getId());
			memberInfo.setUserName(player.getName());
			memberInfo.setArrowLevel(player.getArrowLevel());
			memberInfo.setFightPower(player.getFightPower());
			//memberInfo.setLastOnlineTime(player.getLastOnlineTime());
			memberInfo.setLevel((short) player.getLevel());
			memberInfo.setMapId(player.getMap());
			//memberInfo.setMapName(player.getMapname());
			memberInfo.setMountLevel(player.getMountLevel());
			memberInfo.setPrestigePoint(player.getPrestigePoint());
			memberInfo.setTianyuanLevel(player.getTianyuanLevel());
			memberInfo.setAchievementPoint(player.getAchievementPoint());
			memberInfo.setContributionPoint(player.getContributionPoint());
		}
		if (memberInfo.getUserId() != 0) {
			if (PlayerManager.getInstance().isOnline(memberInfo.getUserId())) {
				memberInfo.setLastOnlineTime(0);
			} else {
				PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(memberInfo.getUserId());
				if (playerWorldInfo != null) {
					memberInfo.setUserName(playerWorldInfo.getName());
					memberInfo.setLastOnlineTime((int) (playerWorldInfo.getLastOnlineTime() / 1000));
				}
			}
		}
	}

	public void setBangZhuInfo(GuildInfo guildInfo, Player player) {
		guildInfo.setBangZhuid(player.getId());
		guildInfo.setBangZhuName(player.getName());
		guildInfo.setBangZhuLevel((short) player.getLevel());
		guildInfo.setBangZhuOnline((byte) (PlayerManager.getInstance().isOnline(player.getId()) ? 1 : 0));
	}

	public void setBangZhuInfoWithMemberInfo(GuildInfo guildInfo, MemberInfo memberInfo) {
		guildInfo.setBangZhuid(memberInfo.getUserId());
		guildInfo.setBangZhuName(memberInfo.getUserName());
		guildInfo.setBangZhuLevel((short) memberInfo.getLevel());
		guildInfo.setBangZhuOnline((byte) (PlayerManager.getInstance().isOnline(memberInfo.getUserId()) ? 1 : 0));
	}

	public void setViceBangZhuInfo(GuildInfo guildInfo, Player player) {
		guildInfo.setViceBangZhuid(player.getId());
		guildInfo.setViceBangZhuName(player.getName());
		guildInfo.setViceBangZhuLevel((short) player.getLevel());
		guildInfo.setViceBangZhuOnline((byte) (PlayerManager.getInstance().isOnline(player.getId()) ? 1 : 0));
	}

	public void setViceBangZhuInfoWithMemberInfo(GuildInfo guildInfo, MemberInfo memberInfo) {
		guildInfo.setViceBangZhuid(memberInfo.getUserId());
		guildInfo.setViceBangZhuName(memberInfo.getUserName());
		guildInfo.setViceBangZhuLevel((short) memberInfo.getLevel());
		guildInfo.setViceBangZhuOnline((byte) (PlayerManager.getInstance().isOnline(memberInfo.getUserId()) ? 1 : 0));
	}
}
