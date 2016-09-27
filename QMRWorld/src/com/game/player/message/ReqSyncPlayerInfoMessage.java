package com.game.player.message;

import com.game.player.bean.PlayerAppearanceInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家全部信息变动消息
 */
public class ReqSyncPlayerInfoMessage extends Message{

	//角色id
	private long playerId;
	
	//账号
	private String userId;
	
	//服务器编号
	private int serverId;
	
	//角色所在线
	private int line;
	
	//角色名字
	private String name;
	
	//角色国家
	private int country;
	
	//角色等级
	private int level;
	
	//角色所在地图
	private int mapId;
	
	//角色所在地图唯一ID
	private long maponlyId;
	
	//角色所在X
	private short x;
	
	//角色所在Y
	private short y;
	
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
	
	//自动邀请组队
	private byte autoTeamApply;
	
	//自动邀请组队
	private byte autoTeamInvited;
	
	//心情
	private String mood;
	
	//是否公开我的地图位置
	private byte openMapLocation;
	
	//自动同意加入帮会
	private byte autoArgeeAddGuild;
	
	//离开帮会时间
	private long lastAfkGuildTime;
	
	//帮贡点
	private int contributionPoint;
	
	//外观信息
	private PlayerAppearanceInfo appearanceInfo;
	
	//玩家系统设置面板信息
	private int menustatus;
	
	//登陆类型
	private byte type;
	
	//是否可以改名
	private byte changeName;
	
	//是否可以改账号
	private byte changeUser;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	//军衔等级
	private byte ranklevel;
	
	//平台VIP
	private int webvip;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//账号
		writeString(buf, this.userId);
		//服务器编号
		writeInt(buf, this.serverId);
		//角色所在线
		writeInt(buf, this.line);
		//角色名字
		writeString(buf, this.name);
		//角色国家
		writeInt(buf, this.country);
		//角色等级
		writeInt(buf, this.level);
		//角色所在地图
		writeInt(buf, this.mapId);
		//角色所在地图唯一ID
		writeLong(buf, this.maponlyId);
		//角色所在X
		writeShort(buf, this.x);
		//角色所在Y
		writeShort(buf, this.y);
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
		//自动邀请组队
		writeByte(buf, this.autoTeamApply);
		//自动邀请组队
		writeByte(buf, this.autoTeamInvited);
		//心情
		writeString(buf, this.mood);
		//是否公开我的地图位置
		writeByte(buf, this.openMapLocation);
		//自动同意加入帮会
		writeByte(buf, this.autoArgeeAddGuild);
		//离开帮会时间
		writeLong(buf, this.lastAfkGuildTime);
		//帮贡点
		writeInt(buf, this.contributionPoint);
		//外观信息
		writeBean(buf, this.appearanceInfo);
		//玩家系统设置面板信息
		writeInt(buf, this.menustatus);
		//登陆类型
		writeByte(buf, this.type);
		//是否可以改名
		writeByte(buf, this.changeName);
		//是否可以改账号
		writeByte(buf, this.changeUser);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		//军衔等级
		writeByte(buf, this.ranklevel);
		//平台VIP
		writeInt(buf, this.webvip);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//账号
		this.userId = readString(buf);
		//服务器编号
		this.serverId = readInt(buf);
		//角色所在线
		this.line = readInt(buf);
		//角色名字
		this.name = readString(buf);
		//角色国家
		this.country = readInt(buf);
		//角色等级
		this.level = readInt(buf);
		//角色所在地图
		this.mapId = readInt(buf);
		//角色所在地图唯一ID
		this.maponlyId = readLong(buf);
		//角色所在X
		this.x = readShort(buf);
		//角色所在Y
		this.y = readShort(buf);
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
		//自动邀请组队
		this.autoTeamApply = readByte(buf);
		//自动邀请组队
		this.autoTeamInvited = readByte(buf);
		//心情
		this.mood = readString(buf);
		//是否公开我的地图位置
		this.openMapLocation = readByte(buf);
		//自动同意加入帮会
		this.autoArgeeAddGuild = readByte(buf);
		//离开帮会时间
		this.lastAfkGuildTime = readLong(buf);
		//帮贡点
		this.contributionPoint = readInt(buf);
		//外观信息
		this.appearanceInfo = (PlayerAppearanceInfo)readBean(buf, PlayerAppearanceInfo.class);
		//玩家系统设置面板信息
		this.menustatus = readInt(buf);
		//登陆类型
		this.type = readByte(buf);
		//是否可以改名
		this.changeName = readByte(buf);
		//是否可以改账号
		this.changeUser = readByte(buf);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		//军衔等级
		this.ranklevel = readByte(buf);
		//平台VIP
		this.webvip = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 角色所在线
	 * @return 
	 */
	public int getLine(){
		return line;
	}
	
	/**
	 * set 角色所在线
	 */
	public void setLine(int line){
		this.line = line;
	}
	
	/**
	 * get 角色名字
	 * @return 
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * set 角色名字
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * get 角色国家
	 * @return 
	 */
	public int getCountry(){
		return country;
	}
	
	/**
	 * set 角色国家
	 */
	public void setCountry(int country){
		this.country = country;
	}
	
	/**
	 * get 角色等级
	 * @return 
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * set 角色等级
	 */
	public void setLevel(int level){
		this.level = level;
	}
	
	/**
	 * get 角色所在地图
	 * @return 
	 */
	public int getMapId(){
		return mapId;
	}
	
	/**
	 * set 角色所在地图
	 */
	public void setMapId(int mapId){
		this.mapId = mapId;
	}
	
	/**
	 * get 角色所在地图唯一ID
	 * @return 
	 */
	public long getMaponlyId(){
		return maponlyId;
	}
	
	/**
	 * set 角色所在地图唯一ID
	 */
	public void setMaponlyId(long maponlyId){
		this.maponlyId = maponlyId;
	}
	
	/**
	 * get 角色所在X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 角色所在X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 角色所在Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 角色所在Y
	 */
	public void setY(short y){
		this.y = y;
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
	 * get 自动邀请组队
	 * @return 
	 */
	public byte getAutoTeamApply(){
		return autoTeamApply;
	}
	
	/**
	 * set 自动邀请组队
	 */
	public void setAutoTeamApply(byte autoTeamApply){
		this.autoTeamApply = autoTeamApply;
	}
	
	/**
	 * get 自动邀请组队
	 * @return 
	 */
	public byte getAutoTeamInvited(){
		return autoTeamInvited;
	}
	
	/**
	 * set 自动邀请组队
	 */
	public void setAutoTeamInvited(byte autoTeamInvited){
		this.autoTeamInvited = autoTeamInvited;
	}
	
	/**
	 * get 心情
	 * @return 
	 */
	public String getMood(){
		return mood;
	}
	
	/**
	 * set 心情
	 */
	public void setMood(String mood){
		this.mood = mood;
	}
	
	/**
	 * get 是否公开我的地图位置
	 * @return 
	 */
	public byte getOpenMapLocation(){
		return openMapLocation;
	}
	
	/**
	 * set 是否公开我的地图位置
	 */
	public void setOpenMapLocation(byte openMapLocation){
		this.openMapLocation = openMapLocation;
	}
	
	/**
	 * get 自动同意加入帮会
	 * @return 
	 */
	public byte getAutoArgeeAddGuild(){
		return autoArgeeAddGuild;
	}
	
	/**
	 * set 自动同意加入帮会
	 */
	public void setAutoArgeeAddGuild(byte autoArgeeAddGuild){
		this.autoArgeeAddGuild = autoArgeeAddGuild;
	}
	
	/**
	 * get 离开帮会时间
	 * @return 
	 */
	public long getLastAfkGuildTime(){
		return lastAfkGuildTime;
	}
	
	/**
	 * set 离开帮会时间
	 */
	public void setLastAfkGuildTime(long lastAfkGuildTime){
		this.lastAfkGuildTime = lastAfkGuildTime;
	}
	
	/**
	 * get 帮贡点
	 * @return 
	 */
	public int getContributionPoint(){
		return contributionPoint;
	}
	
	/**
	 * set 帮贡点
	 */
	public void setContributionPoint(int contributionPoint){
		this.contributionPoint = contributionPoint;
	}
	
	/**
	 * get 外观信息
	 * @return 
	 */
	public PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 外观信息
	 */
	public void setAppearanceInfo(PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	/**
	 * get 玩家系统设置面板信息
	 * @return 
	 */
	public int getMenustatus(){
		return menustatus;
	}
	
	/**
	 * set 玩家系统设置面板信息
	 */
	public void setMenustatus(int menustatus){
		this.menustatus = menustatus;
	}
	
	/**
	 * get 登陆类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 登陆类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 是否可以改名
	 * @return 
	 */
	public byte getChangeName(){
		return changeName;
	}
	
	/**
	 * set 是否可以改名
	 */
	public void setChangeName(byte changeName){
		this.changeName = changeName;
	}
	
	/**
	 * get 是否可以改账号
	 * @return 
	 */
	public byte getChangeUser(){
		return changeUser;
	}
	
	/**
	 * set 是否可以改账号
	 */
	public void setChangeUser(byte changeUser){
		this.changeUser = changeUser;
	}
	
	/**
	 * get 王城BUFFid
	 * @return 
	 */
	public int getKingcitybuffid(){
		return kingcitybuffid;
	}
	
	/**
	 * set 王城BUFFid
	 */
	public void setKingcitybuffid(int kingcitybuffid){
		this.kingcitybuffid = kingcitybuffid;
	}
	
	/**
	 * get VIPid
	 * @return 
	 */
	public int getVipid(){
		return vipid;
	}
	
	/**
	 * set VIPid
	 */
	public void setVipid(int vipid){
		this.vipid = vipid;
	}
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public byte getRanklevel(){
		return ranklevel;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setRanklevel(byte ranklevel){
		this.ranklevel = ranklevel;
	}
	
	/**
	 * get 平台VIP
	 * @return 
	 */
	public int getWebvip(){
		return webvip;
	}
	
	/**
	 * set 平台VIP
	 */
	public void setWebvip(int webvip){
		this.webvip = webvip;
	}
	
	
	@Override
	public int getId() {
		return 103301;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色id
		buf.append("playerId:" + playerId +",");
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//角色所在线
		buf.append("line:" + line +",");
		//角色名字
		if(this.name!=null) buf.append("name:" + name.toString() +",");
		//角色国家
		buf.append("country:" + country +",");
		//角色等级
		buf.append("level:" + level +",");
		//角色所在地图
		buf.append("mapId:" + mapId +",");
		//角色所在地图唯一ID
		buf.append("maponlyId:" + maponlyId +",");
		//角色所在X
		buf.append("x:" + x +",");
		//角色所在Y
		buf.append("y:" + y +",");
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
		//自动邀请组队
		buf.append("autoTeamApply:" + autoTeamApply +",");
		//自动邀请组队
		buf.append("autoTeamInvited:" + autoTeamInvited +",");
		//心情
		if(this.mood!=null) buf.append("mood:" + mood.toString() +",");
		//是否公开我的地图位置
		buf.append("openMapLocation:" + openMapLocation +",");
		//自动同意加入帮会
		buf.append("autoArgeeAddGuild:" + autoArgeeAddGuild +",");
		//离开帮会时间
		buf.append("lastAfkGuildTime:" + lastAfkGuildTime +",");
		//帮贡点
		buf.append("contributionPoint:" + contributionPoint +",");
		//外观信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		//玩家系统设置面板信息
		buf.append("menustatus:" + menustatus +",");
		//登陆类型
		buf.append("type:" + type +",");
		//是否可以改名
		buf.append("changeName:" + changeName +",");
		//是否可以改账号
		buf.append("changeUser:" + changeUser +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		//军衔等级
		buf.append("ranklevel:" + ranklevel +",");
		//平台VIP
		buf.append("webvip:" + webvip +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}