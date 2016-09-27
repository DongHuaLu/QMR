package com.game.player.structs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.game.country.manager.CountryManager;
import com.game.db.bean.GoldExpend;
import com.game.friend.bean.FriendModeInfo;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.manager.ManagerPool;
import com.game.player.bean.PlayerAppearanceInfo;
import com.game.player.manager.PlayerManager;
import com.game.recharge.RechargeHistory;
import com.game.structs.Position;

public class Player implements Comparable<Player> {
	//玩家id

	private long id;
	//玩家账号id
	private String userId;
	//玩家名字
	private String name;
	//玩家所在网关
	private int gateId;
	//玩家登陆类型
	private int loginType;
	//未成年标识
	private int isAdult;
	//玩家所在服务器
	private int server;
	//玩家创建服
	private int createServer;
	//玩家等级
	private int level;
	//玩家所在线
	private int line;
	//玩家所在地图
	private int map;
	//玩家所在地图唯一ID
	private long maponlyid;
	//玩家所在坐标
	private Position position;
	//是否同步数据成功
	private boolean syncdata;
	//----------组队-------------
	//队伍ID
	private long teamid;
	//自动邀请组队
	private byte autoteaminvited;
	//队长设置是否允许自动加入队伍
	private byte autoIntoteamapply;
	//心情
	private String mood;
	//是否公开我的地图位置
	private byte openmaplocation;
	//国家
	private int country;
	//自动同意加入帮会
	private byte autoArgeeAddGuild;
	//帮会
	private long guildid;
	//玩家所在IP
	private String ipString;
	//坐骑阶数
	private byte mountLevel;
	//弓箭阶数
	private byte arrowLevel;
	//天元阶数
	private byte tianyuanLevel;
	//声望点
	private int prestigePoint;
	//成就点
	private int achievementPoint;
	//战斗力
	private int fightPower;
	//离开帮会时间
	private long lastAfkGuildTime;
	//帮贡点
	private int contributionPoint;
	//------外观展示-----
	//性别
	private byte sex;
	//衣服模版ID
	private int clothingmodid;
	//武器模版ID
	private int weaponmodid;
	//武器强化等级
	private byte weaponStreng;
	//当前坐骑模版ID
	private int horsemodid;
	//骑战兵器ID
	private int horseweaponid;
	//暗器ID
	private int hiddenweaponid;
	//头像模板ID
	private int avatarid;
	//系统设置
	private int menustatus;
	//VIP等级
	private int vipid;
	//充值历史
	private List<RechargeHistory> rechargeHistorys;
	//领奖参数
	private Map<String, String> awardParamMap;
	//结婚ID
	private long marriageid;
	//军衔等级
	private int ranklevel ;
	//重复次数
	private transient int repeatNum;		
	//摊位物品上次验证时间
	private transient long stallverifytime;		
	//元宝消耗记录
	private List<GoldExpend> goldExpendHistorys;
	
	public int getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}
	
	public Map<String, String> getAwardParamMap() {
		return awardParamMap;
	}

	public void setAwardParamMap(Map<String, String> awardParamMap) {
		this.awardParamMap = awardParamMap;
	}

	public List<RechargeHistory> getRechargeHistorys() {
		return rechargeHistorys;
	}

	public void setRechargeHistorys(List<RechargeHistory> rechargeHistorys) {
		this.rechargeHistorys = rechargeHistorys;
	}

	public int getVipid() {
		return vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public int getContributionPoint() {
		return contributionPoint;
	}

	public void setContributionPoint(int contributionPoint) {
		this.contributionPoint = contributionPoint;
	}

	public long getLastAfkGuildTime() {
		return lastAfkGuildTime;
	}

	public void setLastAfkGuildTime(long lastAfkGuildTime) {
		this.lastAfkGuildTime = lastAfkGuildTime;
	}

	public int getAchievementPoint() {
		return achievementPoint;
	}

	public void setAchievementPoint(int achievementPoint) {
		this.achievementPoint = achievementPoint;
	}

	public byte getArrowLevel() {
		return arrowLevel;
	}

	public void setArrowLevel(byte arrowLevel) {
		this.arrowLevel = arrowLevel;
	}

	public int getFightPower() {
		return fightPower;
	}

	public void setFightPower(int fightPower) {
		this.fightPower = fightPower;
	}

	public byte getMountLevel() {
		return mountLevel;
	}

	public void setMountLevel(byte mountLevel) {
		this.mountLevel = mountLevel;
	}

	public int getPrestigePoint() {
		return prestigePoint;
	}

	public void setPrestigePoint(int prestigePoint) {
		this.prestigePoint = prestigePoint;
	}

	public byte getTianyuanLevel() {
		return tianyuanLevel;
	}

	public void setTianyuanLevel(byte tianyuanLevel) {
		this.tianyuanLevel = tianyuanLevel;
	}

	public String getIpString() {
		return ipString;
	}

	public void setIpString(String ipString) {
		this.ipString = ipString;
	}

	public long getGuildid() {
		return guildid;
	}

	public void setGuildid(long guildid) {
		this.guildid = guildid;
	}

	public byte getAutoArgeeAddGuild() {
		return autoArgeeAddGuild;
	}

	public void setAutoArgeeAddGuild(byte autoArgeeAddGuild) {
		this.autoArgeeAddGuild = autoArgeeAddGuild;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public byte getOpenmaplocation() {
		return openmaplocation;
	}

	public void setOpenmaplocation(byte openmaplocation) {
		this.openmaplocation = openmaplocation;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServer() {
		return server;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isSyncdata() {
		return syncdata;
	}

	public void setSyncdata(boolean syncdata) {
		this.syncdata = syncdata;
	}

	public byte getSelling() {
		return ManagerPool.stallsManager.getToStalls(this.getId());
	}

	public long getTeamid() {
		return teamid;
	}

	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}

	public int getGateId() {
		return gateId;
	}

	public void setGateId(int gateId) {
		this.gateId = gateId;
	}

	public long getAutoteaminvited() {
		return autoteaminvited;
	}

	public void setAutoteaminvited(byte autoteaminvited) {
		this.autoteaminvited = autoteaminvited;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public int getClothingmodid() {
		return clothingmodid;
	}

	public void setClothingmodid(int clothingmodid) {
		this.clothingmodid = clothingmodid;
	}

	public int getWeaponmodid() {
		return weaponmodid;
	}

	public void setWeaponmodid(int weaponmodid) {
		this.weaponmodid = weaponmodid;
	}

	public byte getWeaponStreng() {
		return weaponStreng;
	}

	public void setWeaponStreng(byte weaponStreng) {
		this.weaponStreng = weaponStreng;
	}

	public int getHorsemodid() {
		return horsemodid;
	}

	public void setHorsemodid(int horsemodid) {
		this.horsemodid = horsemodid;
	}

	public byte getAutoIntoteamapply() {
		return autoIntoteamapply;
	}

	public void setAutoIntoteamapply(byte autoIntoteamapply) {
		this.autoIntoteamapply = autoIntoteamapply;
	}

	public byte getCurState() {//状态 2 摆摊 1 在线 0 离线
		return (getSelling() == 1) ? (byte) 2 : (PlayerManager.getInstance().isOnline(getId()) ? (byte) 1 : (byte) 0);
	}

	public int getIsAdult() {
		return isAdult;
	}

	public void setIsAdult(int isAdult) {
		this.isAdult = isAdult;
	}

	public int getHorseweaponid() {
		return horseweaponid;
	}

	public void setHorseweaponid(int horseweaponid) {
		this.horseweaponid = horseweaponid;
	}

	public FriendModeInfo getFriendModeInfo() {
		FriendModeInfo friendModeInfo = new FriendModeInfo();
		friendModeInfo.setPlayerid(this.getId());
		friendModeInfo.setPlayerlv(this.getLevel());
		friendModeInfo.setPlayername(this.getName());
		PlayerAppearanceInfo playerAppearanceInfo = new PlayerAppearanceInfo();
		playerAppearanceInfo.setSex(this.getSex());
		playerAppearanceInfo.setWeaponmodid(this.getWeaponmodid());
		playerAppearanceInfo.setWeaponStreng(this.getWeaponStreng());
		playerAppearanceInfo.setClothingmodid(this.getClothingmodid());
		playerAppearanceInfo.setHorsemodid(this.getHorsemodid());
		playerAppearanceInfo.setHorseweaponmodid(this.getHorseweaponid());
		playerAppearanceInfo.setAvatarid(this.getAvatarid());
		playerAppearanceInfo.setArrowid(this.getArrowLevel());
		playerAppearanceInfo.setHiddenweaponmodid(this.getHiddenweaponid());
		friendModeInfo.setAppearanceInfo(playerAppearanceInfo);
		return friendModeInfo;
	}

	public int getMenustatus() {
		return menustatus;
	}

	public void setMenustatus(int menustatus) {
		this.menustatus = menustatus;
	}

	public void setWorldMenustatus(int menustatus) {
		PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(getId());
		if (playerWorldInfo != null) {
			playerWorldInfo.setMenustatus(menustatus);
			PlayerManager.getInstance().savePlayerWorldInfo(playerWorldInfo);
		}
	}

	public int compareTo(Player o) {
		return o.getLevel() - getLevel();
	}

	/**
	 * 是否临时玩家
	 *
	 * @return
	 */
	public boolean checkTempPlayer() {
		PlayerWorldInfo playerWorldInfo = PlayerManager.getInstance().getPlayerWorldInfo(getId());
		if (playerWorldInfo != null) {
			return playerWorldInfo.checkTempPlayer();
		}
		return true;
	}

	public long getMaponlyid() {
		return maponlyid;
	}

	public void setMaponlyid(long maponlyid) {
		this.maponlyid = maponlyid;
	}

	public int gKingLevel() {
		if (CountryManager.kingcitymap.containsKey(this.getCountry())) {
			long kingguildid = CountryManager.kingcitymap.get(this.getCountry());
			if (kingguildid != 0) {
				Guild guild = GuildWorldManager.getInstance().getGuild(kingguildid);
				if (guild != null) {
					MemberInfo memberInfo = guild.findMemberInfo(this);
					if (memberInfo != null) {
						return memberInfo.getGuildPowerLevel();
					}
				}
			}
		}
		return 0;
	}

	public int getRanklevel() {
		return ranklevel;
	}

	public void setRanklevel(int ranklevel) {
		this.ranklevel = ranklevel;
	}

	public int getCreateServer() {
		return createServer;
	}

	public void setCreateServer(int createServer) {
		this.createServer = createServer;
	}


	public long getStallverifytime() {
		return stallverifytime;
	}

	public void setStallverifytime(long stallverifytime) {
		this.stallverifytime = stallverifytime;
	}

	public int getHiddenweaponid() {
		return hiddenweaponid;
	}

	public void setHiddenweaponid(int hiddenweaponid) {
		this.hiddenweaponid = hiddenweaponid;
	}

	public long getMarriageid() {
		return marriageid;
	}

	public void setMarriageid(long marriageid) {
		this.marriageid = marriageid;
	}

	public List<GoldExpend> getGoldExpendHistorys() {
		return goldExpendHistorys;
	}

	public void setGoldExpendHistorys(List<GoldExpend> goldExpendHistorys) {
		this.goldExpendHistorys = goldExpendHistorys;
	}
	
}
