package com.game.db.bean;

public class Role {

	private Long roleid;
	private String userid;
	private Integer createServer;
	private Integer locate;
	private Integer level;
	private String name;
	private Integer sex;
	private String data;
	private Long logintime;
	//版本号
	private int version;
	//删除状态
	private boolean isDelete;
	//封停状态
	private boolean isForbid;
	//国家
	private int country;
	//登录ip
	private String loginIp;
	//屏幕宽度
	private int width;
	//屏幕高度
	private int height;
	// 游戏币
	private int money;
	//绑定元宝
	private int bindGold;
	// 包裹已开格子数
	private int bagCellsNum;
	// 仓库已开格子数
	private int storeCellsNum;
	//禁言时间 毫秒
	private long prohibitChatTime;
	//禁言开始时间
	private long startProhibitChatTime;
	//自动禁言检查起始时间点
	private long addBlackTime;
	//被加入黑名单的次数
	private int addBlackCount;
	//经验值
	private long exp;
	//真气
	private int zhenqi;
	//战场声望
	private int prestige;
	//死亡时间
	private long dieTime;
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
	//坐骑等级
	private int horseLevel;
	//在线时间
	private long onlineTime;
	//地图
	private int map;
	//地图模板id
	private int mapModelId;
	//坐标
	private int x;
	//坐标
	private int y;
	//当前生命
	private int hp;
	//当前魔法
	private int mp;
	//当前体力
	private int sp;
	private String agentPlusdata;
	private String agentColdatas;

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getCreateServer() {
		return createServer;
	}

	public void setCreateServer(Integer createServer) {
		this.createServer = createServer;
	}

	public Integer getLocate() {
		return locate;
	}

	public void setLocate(Integer locate) {
		this.locate = locate;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getLogintime() {
		return logintime;
	}

	public void setLogintime(Long logintime) {
		this.logintime = logintime;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the isDelete
	 */
	public boolean isIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the isForbid
	 */
	public boolean isIsForbid() {
		return isForbid;
	}

	/**
	 * @param isForbid the isForbid to set
	 */
	public void setIsForbid(boolean isForbid) {
		this.isForbid = isForbid;
	}

	/**
	 * @return the country
	 */
	public int getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(int country) {
		this.country = country;
	}

	/**
	 * @return the loginIp
	 */
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the bindGold
	 */
	public int getBindGold() {
		return bindGold;
	}

	/**
	 * @param bindGold the bindGold to set
	 */
	public void setBindGold(int bindGold) {
		this.bindGold = bindGold;
	}

	/**
	 * @return the bagCellsNum
	 */
	public int getBagCellsNum() {
		return bagCellsNum;
	}

	/**
	 * @param bagCellsNum the bagCellsNum to set
	 */
	public void setBagCellsNum(int bagCellsNum) {
		this.bagCellsNum = bagCellsNum;
	}

	/**
	 * @return the storeCellsNum
	 */
	public int getStoreCellsNum() {
		return storeCellsNum;
	}

	/**
	 * @param storeCellsNum the storeCellsNum to set
	 */
	public void setStoreCellsNum(int storeCellsNum) {
		this.storeCellsNum = storeCellsNum;
	}

	/**
	 * @return the prohibitChatTime
	 */
	public long getProhibitChatTime() {
		return prohibitChatTime;
	}

	/**
	 * @param prohibitChatTime the prohibitChatTime to set
	 */
	public void setProhibitChatTime(long prohibitChatTime) {
		this.prohibitChatTime = prohibitChatTime;
	}

	/**
	 * @return the startProhibitChatTime
	 */
	public long getStartProhibitChatTime() {
		return startProhibitChatTime;
	}

	/**
	 * @param startProhibitChatTime the startProhibitChatTime to set
	 */
	public void setStartProhibitChatTime(long startProhibitChatTime) {
		this.startProhibitChatTime = startProhibitChatTime;
	}

	/**
	 * @return the addBlackTime
	 */
	public long getAddBlackTime() {
		return addBlackTime;
	}

	/**
	 * @param addBlackTime the addBlackTime to set
	 */
	public void setAddBlackTime(long addBlackTime) {
		this.addBlackTime = addBlackTime;
	}

	/**
	 * @return the addBlackCount
	 */
	public int getAddBlackCount() {
		return addBlackCount;
	}

	/**
	 * @param addBlackCount the addBlackCount to set
	 */
	public void setAddBlackCount(int addBlackCount) {
		this.addBlackCount = addBlackCount;
	}

	/**
	 * @return the exp
	 */
	public long getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(long exp) {
		this.exp = exp;
	}

	/**
	 * @return the zhenqi
	 */
	public int getZhenqi() {
		return zhenqi;
	}

	/**
	 * @param zhenqi the zhenqi to set
	 */
	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * @return the prestige
	 */
	public int getPrestige() {
		return prestige;
	}

	/**
	 * @param prestige the prestige to set
	 */
	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	/**
	 * @return the dieTime
	 */
	public long getDieTime() {
		return dieTime;
	}

	/**
	 * @param dieTime the dieTime to set
	 */
	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	/**
	 * @return the arrowLevel
	 */
	public byte getArrowLevel() {
		return arrowLevel;
	}

	/**
	 * @param arrowLevel the arrowLevel to set
	 */
	public void setArrowLevel(byte arrowLevel) {
		this.arrowLevel = arrowLevel;
	}

	/**
	 * @return the tianyuanLevel
	 */
	public byte getTianyuanLevel() {
		return tianyuanLevel;
	}

	/**
	 * @param tianyuanLevel the tianyuanLevel to set
	 */
	public void setTianyuanLevel(byte tianyuanLevel) {
		this.tianyuanLevel = tianyuanLevel;
	}

	/**
	 * @return the prestigePoint
	 */
	public int getPrestigePoint() {
		return prestigePoint;
	}

	/**
	 * @param prestigePoint the prestigePoint to set
	 */
	public void setPrestigePoint(int prestigePoint) {
		this.prestigePoint = prestigePoint;
	}

	/**
	 * @return the achievementPoint
	 */
	public int getAchievementPoint() {
		return achievementPoint;
	}

	/**
	 * @param achievementPoint the achievementPoint to set
	 */
	public void setAchievementPoint(int achievementPoint) {
		this.achievementPoint = achievementPoint;
	}

	/**
	 * @return the fightPower
	 */
	public int getFightPower() {
		return fightPower;
	}

	/**
	 * @param fightPower the fightPower to set
	 */
	public void setFightPower(int fightPower) {
		this.fightPower = fightPower;
	}

	/**
	 * @return the horseLevel
	 */
	public int getHorseLevel() {
		return horseLevel;
	}

	/**
	 * @param horseLevel the horseLevel to set
	 */
	public void setHorseLevel(int horseLevel) {
		this.horseLevel = horseLevel;
	}

	/**
	 * @return the onlineTime
	 */
	public long getOnlineTime() {
		return onlineTime;
	}

	/**
	 * @param onlineTime the onlineTime to set
	 */
	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}

	/**
	 * @return the map
	 */
	public int getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(int map) {
		this.map = map;
	}

	/**
	 * @return the mapModelId
	 */
	public int getMapModelId() {
		return mapModelId;
	}

	/**
	 * @param mapModelId the mapModelId to set
	 */
	public void setMapModelId(int mapModelId) {
		this.mapModelId = mapModelId;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the mp
	 */
	public int getMp() {
		return mp;
	}

	/**
	 * @param mp the mp to set
	 */
	public void setMp(int mp) {
		this.mp = mp;
	}

	/**
	 * @return the sp
	 */
	public int getSp() {
		return sp;
	}

	/**
	 * @param sp the sp to set
	 */
	public void setSp(int sp) {
		this.sp = sp;
	}

	public String getAgentPlusdata() {
		return agentPlusdata;
	}

	public void setAgentPlusdata(String agentPlusdata) {
		this.agentPlusdata = agentPlusdata;
	}

	public String getAgentColdatas() {
		return agentColdatas;
	}

	public void setAgentColdatas(String agentColdatas) {
		this.agentColdatas = agentColdatas;
	}
	
}