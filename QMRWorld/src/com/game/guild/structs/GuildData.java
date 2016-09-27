package com.game.guild.structs;

import com.game.guild.bean.DiplomaticInfo;
import com.game.guild.bean.GuildInfo;
import com.game.object.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 帮会保存数据
 * @author 杨洪岚
 */
public class GuildData extends GameObject {
	
	private static final long serialVersionUID = 8103227099663344872L;
	
	//帮会id
	private long guildId;
	
	//帮会名
	private String guildName;
	
	//帮会旗帜
	private String guildBanner;
	
	//帮主id
	private long bangZhuid;
	
	//帮主名字
	private String bangZhuName;
	
	//帮主等级
	private short bangZhuLevel;
	
	//帮主是否在线
	private byte bangZhuOnline;
	
	//副帮主id
	private long viceBangZhuid;
	
	//副帮主名字
	private String viceBangZhuName;
	
	//副帮主等级
	private short viceBangZhuLevel;
	
	//副帮主是否在线
	private byte viceBangZhuOnline;
	
	//旗帜造型
	private int bannerIcon;
	
	//旗帜等级
	private byte bannerLevel;
	
	//帮会公告
	private String guildBulletin;
	
	//青龙令牌数
	private int dragon;
	
	//白虎令牌数
	private int whiteTiger;
	
	//朱雀令牌数
	private int suzaku;
	
	//玄武令牌数
	private int basaltic;
	
	//库存元宝
	private long stockGold;
	
	//友好帮会列表
	private List<DiplomaticData> friendGuildList = new ArrayList<DiplomaticData>();
	//敌对帮会列表
	private List<DiplomaticData> enemyGuildList = new ArrayList<DiplomaticData>();
	//今日活跃值
	private byte activeValue;
	
	//解散警告值
	private byte warningValue;
	
	//自动同意加入帮会的申请
	private byte autoGuildAgreeAdd;
	
	//成员数量
	private byte memberNum;
	
	//成员战斗力之和
	private int memberFightPower;
	
	//占领地图列表
	private List<Integer> ownMapList = new ArrayList<Integer>();
	//拥有王城
	private byte ownKingCity;
	
	//拥有皇城
	private byte ownEmperorCity;
	
	//帮会创建时间
	private int createTime;
	
	//帮会创建IP
	private String createIp;
	
	//帮会公告最后更新时间
	private int lastGuildBulletinTime;

	/**
	 * @return the guildId
	 */
	public long getGuildId() {
		return guildId;
	}

	/**
	 * @param guildId the guildId to set
	 */
	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	/**
	 * @return the guildName
	 */
	public String getGuildName() {
		return guildName;
	}

	/**
	 * @param guildName the guildName to set
	 */
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	/**
	 * @return the guildBanner
	 */
	public String getGuildBanner() {
		return guildBanner;
	}

	/**
	 * @param guildBanner the guildBanner to set
	 */
	public void setGuildBanner(String guildBanner) {
		this.guildBanner = guildBanner;
	}

	/**
	 * @return the bangZhuid
	 */
	public long getBangZhuid() {
		return bangZhuid;
	}

	/**
	 * @param bangZhuid the bangZhuid to set
	 */
	public void setBangZhuid(long bangZhuid) {
		this.bangZhuid = bangZhuid;
	}

	/**
	 * @return the bangZhuName
	 */
	public String getBangZhuName() {
		return bangZhuName;
	}

	/**
	 * @param bangZhuName the bangZhuName to set
	 */
	public void setBangZhuName(String bangZhuName) {
		this.bangZhuName = bangZhuName;
	}

	/**
	 * @return the bangZhuLevel
	 */
	public short getBangZhuLevel() {
		return bangZhuLevel;
	}

	/**
	 * @param bangZhuLevel the bangZhuLevel to set
	 */
	public void setBangZhuLevel(short bangZhuLevel) {
		this.bangZhuLevel = bangZhuLevel;
	}

	/**
	 * @return the bangZhuOnline
	 */
	public byte getBangZhuOnline() {
		return bangZhuOnline;
	}

	/**
	 * @param bangZhuOnline the bangZhuOnline to set
	 */
	public void setBangZhuOnline(byte bangZhuOnline) {
		this.bangZhuOnline = bangZhuOnline;
	}

	/**
	 * @return the viceBangZhuid
	 */
	public long getViceBangZhuid() {
		return viceBangZhuid;
	}

	/**
	 * @param viceBangZhuid the viceBangZhuid to set
	 */
	public void setViceBangZhuid(long viceBangZhuid) {
		this.viceBangZhuid = viceBangZhuid;
	}

	/**
	 * @return the viceBangZhuName
	 */
	public String getViceBangZhuName() {
		return viceBangZhuName;
	}

	/**
	 * @param viceBangZhuName the viceBangZhuName to set
	 */
	public void setViceBangZhuName(String viceBangZhuName) {
		this.viceBangZhuName = viceBangZhuName;
	}

	/**
	 * @return the viceBangZhuLevel
	 */
	public short getViceBangZhuLevel() {
		return viceBangZhuLevel;
	}

	/**
	 * @param viceBangZhuLevel the viceBangZhuLevel to set
	 */
	public void setViceBangZhuLevel(short viceBangZhuLevel) {
		this.viceBangZhuLevel = viceBangZhuLevel;
	}

	/**
	 * @return the viceBangZhuOnline
	 */
	public byte getViceBangZhuOnline() {
		return viceBangZhuOnline;
	}

	/**
	 * @param viceBangZhuOnline the viceBangZhuOnline to set
	 */
	public void setViceBangZhuOnline(byte viceBangZhuOnline) {
		this.viceBangZhuOnline = viceBangZhuOnline;
	}

	/**
	 * @return the bannerIcon
	 */
	public int getBannerIcon() {
		return bannerIcon;
	}

	/**
	 * @param bannerIcon the bannerIcon to set
	 */
	public void setBannerIcon(int bannerIcon) {
		this.bannerIcon = bannerIcon;
	}

	/**
	 * @return the bannerLevel
	 */
	public byte getBannerLevel() {
		return bannerLevel;
	}

	/**
	 * @param bannerLevel the bannerLevel to set
	 */
	public void setBannerLevel(byte bannerLevel) {
		this.bannerLevel = bannerLevel;
	}

	/**
	 * @return the guildBulletin
	 */
	public String getGuildBulletin() {
		return guildBulletin;
	}

	/**
	 * @param guildBulletin the guildBulletin to set
	 */
	public void setGuildBulletin(String guildBulletin) {
		this.guildBulletin = guildBulletin;
	}

	/**
	 * @return the dragon
	 */
	public int getDragon() {
		return dragon;
	}

	/**
	 * @param dragon the dragon to set
	 */
	public void setDragon(int dragon) {
		this.dragon = dragon;
	}

	/**
	 * @return the whiteTiger
	 */
	public int getWhiteTiger() {
		return whiteTiger;
	}

	/**
	 * @param whiteTiger the whiteTiger to set
	 */
	public void setWhiteTiger(int whiteTiger) {
		this.whiteTiger = whiteTiger;
	}

	/**
	 * @return the suzaku
	 */
	public int getSuzaku() {
		return suzaku;
	}

	/**
	 * @param suzaku the suzaku to set
	 */
	public void setSuzaku(int suzaku) {
		this.suzaku = suzaku;
	}

	/**
	 * @return the basaltic
	 */
	public int getBasaltic() {
		return basaltic;
	}

	/**
	 * @param basaltic the basaltic to set
	 */
	public void setBasaltic(int basaltic) {
		this.basaltic = basaltic;
	}

	/**
	 * @return the stockGold
	 */
	public long getStockGold() {
		return stockGold;
	}

	/**
	 * @param stockGold the stockGold to set
	 */
	public void setStockGold(long stockGold) {
		this.stockGold = stockGold;
	}

	/**
	 * @return the friendGuildList
	 */
	public List<DiplomaticData> getFriendGuildList() {
		return friendGuildList;
	}

	/**
	 * @param friendGuildList the friendGuildList to set
	 */
	public void setFriendGuildList(List<DiplomaticData> friendGuildList) {
		this.friendGuildList = friendGuildList;
	}

	/**
	 * @return the enemyGuildList
	 */
	public List<DiplomaticData> getEnemyGuildList() {
		return enemyGuildList;
	}

	/**
	 * @param enemyGuildList the enemyGuildList to set
	 */
	public void setEnemyGuildList(List<DiplomaticData> enemyGuildList) {
		this.enemyGuildList = enemyGuildList;
	}

	/**
	 * @return the activeValue
	 */
	public byte getActiveValue() {
		return activeValue;
	}

	/**
	 * @param activeValue the activeValue to set
	 */
	public void setActiveValue(byte activeValue) {
		this.activeValue = activeValue;
	}

	/**
	 * @return the warningValue
	 */
	public byte getWarningValue() {
		return warningValue;
	}

	/**
	 * @param warningValue the warningValue to set
	 */
	public void setWarningValue(byte warningValue) {
		this.warningValue = warningValue;
	}

	/**
	 * @return the autoGuildAgreeAdd
	 */
	public byte getAutoGuildAgreeAdd() {
		return autoGuildAgreeAdd;
	}

	/**
	 * @param autoGuildAgreeAdd the autoGuildAgreeAdd to set
	 */
	public void setAutoGuildAgreeAdd(byte autoGuildAgreeAdd) {
		this.autoGuildAgreeAdd = autoGuildAgreeAdd;
	}

	/**
	 * @return the memberNum
	 */
	public byte getMemberNum() {
		return memberNum;
	}

	/**
	 * @param memberNum the memberNum to set
	 */
	public void setMemberNum(byte memberNum) {
		this.memberNum = memberNum;
	}

	/**
	 * @return the memberFightPower
	 */
	public int getMemberFightPower() {
		return memberFightPower;
	}

	/**
	 * @param memberFightPower the memberFightPower to set
	 */
	public void setMemberFightPower(int memberFightPower) {
		this.memberFightPower = memberFightPower;
	}

	/**
	 * @return the ownMapList
	 */
	public List<Integer> getOwnMapList() {
		return ownMapList;
	}

	/**
	 * @param ownMapList the ownMapList to set
	 */
	public void setOwnMapList(List<Integer> ownMapList) {
		this.ownMapList = ownMapList;
	}

	/**
	 * @return the ownKingCity
	 */
	public byte getOwnKingCity() {
		return ownKingCity;
	}

	/**
	 * @param ownKingCity the ownKingCity to set
	 */
	public void setOwnKingCity(byte ownKingCity) {
		this.ownKingCity = ownKingCity;
	}

	/**
	 * @return the ownEmperorCity
	 */
	public byte getOwnEmperorCity() {
		return ownEmperorCity;
	}

	/**
	 * @param ownEmperorCity the ownEmperorCity to set
	 */
	public void setOwnEmperorCity(byte ownEmperorCity) {
		this.ownEmperorCity = ownEmperorCity;
	}

	/**
	 * @return the createTime
	 */
	public int getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createIp
	 */
	public String getCreateIp() {
		return createIp;
	}

	/**
	 * @param createIp the createIp to set
	 */
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	/**
	 * @return the lastGuildBulletinTime
	 */
	public int getLastGuildBulletinTime() {
		return lastGuildBulletinTime;
	}

	/**
	 * @param lastGuildBulletinTime the lastGuildBulletinTime to set
	 */
	public void setLastGuildBulletinTime(int lastGuildBulletinTime) {
		this.lastGuildBulletinTime = lastGuildBulletinTime;
	}
	
	public void toData(GuildInfo guildInfo){
		setActiveValue(guildInfo.getActiveValue());
		setAutoGuildAgreeAdd(guildInfo.getAutoGuildAgreeAdd());
		setBangZhuLevel(guildInfo.getBangZhuLevel());
		setBangZhuName(guildInfo.getBangZhuName());
		setBangZhuOnline(guildInfo.getBangZhuOnline());
		setBangZhuid(guildInfo.getBangZhuid());
		setBannerIcon(guildInfo.getBannerIcon());
		setBannerLevel(guildInfo.getBannerLevel());
		setBasaltic(guildInfo.getBasaltic());
		setCreateIp(guildInfo.getCreateIp());
		setCreateTime(guildInfo.getCreateTime());
		setDragon(guildInfo.getDragon());
		for (int i = 0; i < guildInfo.getEnemyGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getEnemyGuildList().get(i);
			if (diplomaticInfo != null) {
				DiplomaticData diplomaticData = new DiplomaticData();
				diplomaticData.toData(diplomaticInfo);
				getEnemyGuildList().add(diplomaticData);
			}
		}
		for (int i = 0; i < guildInfo.getFriendGuildList().size(); i++) {
			DiplomaticInfo diplomaticInfo = guildInfo.getFriendGuildList().get(i);
			if (diplomaticInfo != null) {
				DiplomaticData diplomaticData = new DiplomaticData();
				diplomaticData.toData(diplomaticInfo);
				getFriendGuildList().add(diplomaticData);
			}
		}
		setGuildBanner(guildInfo.getGuildBanner());
		setGuildBulletin(guildInfo.getGuildBulletin());
		setGuildId(guildInfo.getGuildId());
		setGuildName(guildInfo.getGuildName());
		setLastGuildBulletinTime(guildInfo.getLastGuildBulletinTime());
		setMemberFightPower(guildInfo.getMemberFightPower());
		setMemberNum(guildInfo.getMemberNum());
		setOwnEmperorCity(guildInfo.getOwnEmperorCity());
		setOwnKingCity(guildInfo.getOwnKingCity());
		setOwnMapList(guildInfo.getOwnMapList());
		setStockGold(guildInfo.getStockGold());
		setSuzaku(guildInfo.getSuzaku());
		setViceBangZhuLevel(guildInfo.getViceBangZhuLevel());
		setViceBangZhuName(guildInfo.getViceBangZhuName());
		setViceBangZhuOnline(guildInfo.getViceBangZhuOnline());
		setViceBangZhuid(guildInfo.getViceBangZhuid());
		setWarningValue(guildInfo.getWarningValue());
		setWhiteTiger(guildInfo.getWhiteTiger());
	}
	
	public GuildInfo toInfo(){
		GuildInfo guildInfo = new GuildInfo();
		guildInfo.setActiveValue(getActiveValue());
		guildInfo.setAutoGuildAgreeAdd(getAutoGuildAgreeAdd());
		guildInfo.setBangZhuLevel(getBangZhuLevel());
		guildInfo.setBangZhuName(getBangZhuName());
		guildInfo.setBangZhuOnline(getBangZhuOnline());
		guildInfo.setBangZhuid(getBangZhuid());
		guildInfo.setBannerIcon(getBannerIcon());
		guildInfo.setBannerLevel(getBannerLevel());
		guildInfo.setBasaltic(getBasaltic());
		guildInfo.setCreateIp(getCreateIp());
		guildInfo.setCreateTime(getCreateTime());
		guildInfo.setDragon(getDragon());
		for (int i = 0; i < getEnemyGuildList().size(); i++) {
			DiplomaticData diplomaticData = getEnemyGuildList().get(i);
			if (diplomaticData != null) {
				guildInfo.getEnemyGuildList().add(diplomaticData.toInfo());
			}
		}
		for (int i = 0; i < getFriendGuildList().size(); i++) {
			DiplomaticData diplomaticData = getFriendGuildList().get(i);
			if (diplomaticData != null) {
				guildInfo.getFriendGuildList().add(diplomaticData.toInfo());
			}
		}
		guildInfo.setGuildBanner(getGuildBanner());
		guildInfo.setGuildBulletin(getGuildBulletin());
		guildInfo.setGuildId(getGuildId());
		guildInfo.setGuildName(getGuildName());
		guildInfo.setLastGuildBulletinTime(getLastGuildBulletinTime());
		guildInfo.setMemberFightPower(getMemberFightPower());
		guildInfo.setMemberNum(getMemberNum());
		guildInfo.setOwnEmperorCity(getOwnEmperorCity());
		guildInfo.setOwnKingCity(getOwnKingCity());
		guildInfo.setOwnMapList(getOwnMapList());
		guildInfo.setStockGold(getStockGold());
		guildInfo.setSuzaku(getSuzaku());
		guildInfo.setViceBangZhuLevel(getViceBangZhuLevel());
		guildInfo.setViceBangZhuName(getViceBangZhuName());
		guildInfo.setViceBangZhuOnline(getViceBangZhuOnline());
		guildInfo.setViceBangZhuid(getViceBangZhuid());
		guildInfo.setWarningValue(getWarningValue());
		guildInfo.setWhiteTiger(getWhiteTiger());
		return guildInfo;
	}
}
