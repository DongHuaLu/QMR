package com.game.guild.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 成员信息
 */
public class MemberInfo extends Bean {

	//玩家id
	private long userId;
	
	//玩家名
	private String userName;
	
	//帮会id
	private long guildId;
	
	//帮会名
	private String guildName;
	
	//所在地图Id
	private int mapId;
	
	//玩家等级
	private short level;
	
	//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
	private byte guildPowerLevel;
	
	//昵称
	private String nickName;
	
	//分组名
	private String groupName;
	
	//贡献点
	private int contributionPoint;
	
	//历史贡献点
	private int contributionPointHistory;
	
	//入会时间
	private int addTime;
	
	//上次在线时间
	private int lastOnlineTime;
	
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
	
	//历史青龙令牌数
	private int dragonHistory;
	
	//历史白虎令牌数
	private int whiteTigerHistory;
	
	//历史朱雀令牌数
	private int suzakuHistory;
	
	//历史玄武令牌数
	private int basalticHistory;
	
	//历史库存元宝
	private long stockGoldHistory;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.userId);
		//玩家名
		writeString(buf, this.userName);
		//帮会id
		writeLong(buf, this.guildId);
		//帮会名
		writeString(buf, this.guildName);
		//所在地图Id
		writeInt(buf, this.mapId);
		//玩家等级
		writeShort(buf, this.level);
		//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		writeByte(buf, this.guildPowerLevel);
		//昵称
		writeString(buf, this.nickName);
		//分组名
		writeString(buf, this.groupName);
		//贡献点
		writeInt(buf, this.contributionPoint);
		//历史贡献点
		writeInt(buf, this.contributionPointHistory);
		//入会时间
		writeInt(buf, this.addTime);
		//上次在线时间
		writeInt(buf, this.lastOnlineTime);
		//坐骑阶数
		writeByte(buf, this.mountLevel);
		//弓箭阶数
		writeByte(buf, this.arrowLevel);
		//天元阶数
		writeByte(buf, this.tianyuanLevel);
		//声望点
		writeInt(buf, this.prestigePoint);
		//成就点
		writeInt(buf, this.achievementPoint);
		//战斗力
		writeInt(buf, this.fightPower);
		//历史青龙令牌数
		writeInt(buf, this.dragonHistory);
		//历史白虎令牌数
		writeInt(buf, this.whiteTigerHistory);
		//历史朱雀令牌数
		writeInt(buf, this.suzakuHistory);
		//历史玄武令牌数
		writeInt(buf, this.basalticHistory);
		//历史库存元宝
		writeLong(buf, this.stockGoldHistory);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.userId = readLong(buf);
		//玩家名
		this.userName = readString(buf);
		//帮会id
		this.guildId = readLong(buf);
		//帮会名
		this.guildName = readString(buf);
		//所在地图Id
		this.mapId = readInt(buf);
		//玩家等级
		this.level = readShort(buf);
		//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		this.guildPowerLevel = readByte(buf);
		//昵称
		this.nickName = readString(buf);
		//分组名
		this.groupName = readString(buf);
		//贡献点
		this.contributionPoint = readInt(buf);
		//历史贡献点
		this.contributionPointHistory = readInt(buf);
		//入会时间
		this.addTime = readInt(buf);
		//上次在线时间
		this.lastOnlineTime = readInt(buf);
		//坐骑阶数
		this.mountLevel = readByte(buf);
		//弓箭阶数
		this.arrowLevel = readByte(buf);
		//天元阶数
		this.tianyuanLevel = readByte(buf);
		//声望点
		this.prestigePoint = readInt(buf);
		//成就点
		this.achievementPoint = readInt(buf);
		//战斗力
		this.fightPower = readInt(buf);
		//历史青龙令牌数
		this.dragonHistory = readInt(buf);
		//历史白虎令牌数
		this.whiteTigerHistory = readInt(buf);
		//历史朱雀令牌数
		this.suzakuHistory = readInt(buf);
		//历史玄武令牌数
		this.basalticHistory = readInt(buf);
		//历史库存元宝
		this.stockGoldHistory = readLong(buf);
		return true;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getUserId(){
		return userId;
	}
	
	/**
	 * set 玩家id
	 */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * get 玩家名
	 * @return 
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * set 玩家名
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * get 帮会id
	 * @return 
	 */
	public long getGuildId(){
		return guildId;
	}
	
	/**
	 * set 帮会id
	 */
	public void setGuildId(long guildId){
		this.guildId = guildId;
	}
	
	/**
	 * get 帮会名
	 * @return 
	 */
	public String getGuildName(){
		return guildName;
	}
	
	/**
	 * set 帮会名
	 */
	public void setGuildName(String guildName){
		this.guildName = guildName;
	}
	
	/**
	 * get 所在地图Id
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 所在地图Id
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 玩家等级
	 * @return 
	 */
	public short getLevel(){
		return level;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setLevel(short level){
		this.level = level;
	}
	
	/**
	 * get 帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
	 * @return 
	 */
	public byte getGuildPowerLevel(){
		return guildPowerLevel;
	}
	
	/**
	 * set 帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
	 */
	public void setGuildPowerLevel(byte guildPowerLevel){
		this.guildPowerLevel = guildPowerLevel;
	}
	
	/**
	 * get 昵称
	 * @return 
	 */
	public String getNickName(){
		return nickName;
	}
	
	/**
	 * set 昵称
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	/**
	 * get 分组名
	 * @return 
	 */
	public String getGroupName(){
		return groupName;
	}
	
	/**
	 * set 分组名
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	/**
	 * get 贡献点
	 * @return 
	 */
	public int getContributionPoint(){
		return contributionPoint;
	}
	
	/**
	 * set 贡献点
	 */
	public void setContributionPoint(int contributionPoint){
		this.contributionPoint = contributionPoint;
	}
	
	/**
	 * get 历史贡献点
	 * @return 
	 */
	public int getContributionPointHistory(){
		return contributionPointHistory;
	}
	
	/**
	 * set 历史贡献点
	 */
	public void setContributionPointHistory(int contributionPointHistory){
		this.contributionPointHistory = contributionPointHistory;
	}
	
	/**
	 * get 入会时间
	 * @return 
	 */
	public int getAddTime(){
		return addTime;
	}
	
	/**
	 * set 入会时间
	 */
	public void setAddTime(int addTime){
		this.addTime = addTime;
	}
	
	/**
	 * get 上次在线时间
	 * @return 
	 */
	public int getLastOnlineTime(){
		return lastOnlineTime;
	}
	
	/**
	 * set 上次在线时间
	 */
	public void setLastOnlineTime(int lastOnlineTime){
		this.lastOnlineTime = lastOnlineTime;
	}
	
	/**
	 * get 坐骑阶数
	 * @return 
	 */
	public byte getMountLevel(){
		return mountLevel;
	}
	
	/**
	 * set 坐骑阶数
	 */
	public void setMountLevel(byte mountLevel){
		this.mountLevel = mountLevel;
	}
	
	/**
	 * get 弓箭阶数
	 * @return 
	 */
	public byte getArrowLevel(){
		return arrowLevel;
	}
	
	/**
	 * set 弓箭阶数
	 */
	public void setArrowLevel(byte arrowLevel){
		this.arrowLevel = arrowLevel;
	}
	
	/**
	 * get 天元阶数
	 * @return 
	 */
	public byte getTianyuanLevel(){
		return tianyuanLevel;
	}
	
	/**
	 * set 天元阶数
	 */
	public void setTianyuanLevel(byte tianyuanLevel){
		this.tianyuanLevel = tianyuanLevel;
	}
	
	/**
	 * get 声望点
	 * @return 
	 */
	public int getPrestigePoint(){
		return prestigePoint;
	}
	
	/**
	 * set 声望点
	 */
	public void setPrestigePoint(int prestigePoint){
		this.prestigePoint = prestigePoint;
	}
	
	/**
	 * get 成就点
	 * @return 
	 */
	public int getAchievementPoint(){
		return achievementPoint;
	}
	
	/**
	 * set 成就点
	 */
	public void setAchievementPoint(int achievementPoint){
		this.achievementPoint = achievementPoint;
	}
	
	/**
	 * get 战斗力
	 * @return 
	 */
	public int getFightPower(){
		return fightPower;
	}
	
	/**
	 * set 战斗力
	 */
	public void setFightPower(int fightPower){
		this.fightPower = fightPower;
	}
	
	/**
	 * get 历史青龙令牌数
	 * @return 
	 */
	public int getDragonHistory(){
		return dragonHistory;
	}
	
	/**
	 * set 历史青龙令牌数
	 */
	public void setDragonHistory(int dragonHistory){
		this.dragonHistory = dragonHistory;
	}
	
	/**
	 * get 历史白虎令牌数
	 * @return 
	 */
	public int getWhiteTigerHistory(){
		return whiteTigerHistory;
	}
	
	/**
	 * set 历史白虎令牌数
	 */
	public void setWhiteTigerHistory(int whiteTigerHistory){
		this.whiteTigerHistory = whiteTigerHistory;
	}
	
	/**
	 * get 历史朱雀令牌数
	 * @return 
	 */
	public int getSuzakuHistory(){
		return suzakuHistory;
	}
	
	/**
	 * set 历史朱雀令牌数
	 */
	public void setSuzakuHistory(int suzakuHistory){
		this.suzakuHistory = suzakuHistory;
	}
	
	/**
	 * get 历史玄武令牌数
	 * @return 
	 */
	public int getBasalticHistory(){
		return basalticHistory;
	}
	
	/**
	 * set 历史玄武令牌数
	 */
	public void setBasalticHistory(int basalticHistory){
		this.basalticHistory = basalticHistory;
	}
	
	/**
	 * get 历史库存元宝
	 * @return 
	 */
	public long getStockGoldHistory(){
		return stockGoldHistory;
	}
	
	/**
	 * set 历史库存元宝
	 */
	public void setStockGoldHistory(long stockGoldHistory){
		this.stockGoldHistory = stockGoldHistory;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家id
		buf.append("userId:" + userId +",");
		//玩家名
		if(this.userName!=null) buf.append("userName:" + userName.toString() +",");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//帮会名
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//所在地图Id
		buf.append("mapId:" + mapId +",");
		//玩家等级
		buf.append("level:" + level +",");
		//帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
		buf.append("guildPowerLevel:" + guildPowerLevel +",");
		//昵称
		if(this.nickName!=null) buf.append("nickName:" + nickName.toString() +",");
		//分组名
		if(this.groupName!=null) buf.append("groupName:" + groupName.toString() +",");
		//贡献点
		buf.append("contributionPoint:" + contributionPoint +",");
		//历史贡献点
		buf.append("contributionPointHistory:" + contributionPointHistory +",");
		//入会时间
		buf.append("addTime:" + addTime +",");
		//上次在线时间
		buf.append("lastOnlineTime:" + lastOnlineTime +",");
		//坐骑阶数
		buf.append("mountLevel:" + mountLevel +",");
		//弓箭阶数
		buf.append("arrowLevel:" + arrowLevel +",");
		//天元阶数
		buf.append("tianyuanLevel:" + tianyuanLevel +",");
		//声望点
		buf.append("prestigePoint:" + prestigePoint +",");
		//成就点
		buf.append("achievementPoint:" + achievementPoint +",");
		//战斗力
		buf.append("fightPower:" + fightPower +",");
		//历史青龙令牌数
		buf.append("dragonHistory:" + dragonHistory +",");
		//历史白虎令牌数
		buf.append("whiteTigerHistory:" + whiteTigerHistory +",");
		//历史朱雀令牌数
		buf.append("suzakuHistory:" + suzakuHistory +",");
		//历史玄武令牌数
		buf.append("basalticHistory:" + basalticHistory +",");
		//历史库存元宝
		buf.append("stockGoldHistory:" + stockGoldHistory +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}