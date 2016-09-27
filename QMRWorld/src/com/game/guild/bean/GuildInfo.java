package com.game.guild.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮会信息
 */
public class GuildInfo extends Bean {

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
	private List<DiplomaticInfo> friendGuildList = new ArrayList<DiplomaticInfo>();
	//敌对帮会列表
	private List<DiplomaticInfo> enemyGuildList = new ArrayList<DiplomaticInfo>();
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
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会id
		writeLong(buf, this.guildId);
		//帮会名
		writeString(buf, this.guildName);
		//帮会旗帜
		writeString(buf, this.guildBanner);
		//帮主id
		writeLong(buf, this.bangZhuid);
		//帮主名字
		writeString(buf, this.bangZhuName);
		//帮主等级
		writeShort(buf, this.bangZhuLevel);
		//帮主是否在线
		writeByte(buf, this.bangZhuOnline);
		//副帮主id
		writeLong(buf, this.viceBangZhuid);
		//副帮主名字
		writeString(buf, this.viceBangZhuName);
		//副帮主等级
		writeShort(buf, this.viceBangZhuLevel);
		//副帮主是否在线
		writeByte(buf, this.viceBangZhuOnline);
		//旗帜造型
		writeInt(buf, this.bannerIcon);
		//旗帜等级
		writeByte(buf, this.bannerLevel);
		//帮会公告
		writeString(buf, this.guildBulletin);
		//青龙令牌数
		writeInt(buf, this.dragon);
		//白虎令牌数
		writeInt(buf, this.whiteTiger);
		//朱雀令牌数
		writeInt(buf, this.suzaku);
		//玄武令牌数
		writeInt(buf, this.basaltic);
		//库存元宝
		writeLong(buf, this.stockGold);
		//友好帮会列表
		writeShort(buf, friendGuildList.size());
		for (int i = 0; i < friendGuildList.size(); i++) {
			writeBean(buf, friendGuildList.get(i));
		}
		//敌对帮会列表
		writeShort(buf, enemyGuildList.size());
		for (int i = 0; i < enemyGuildList.size(); i++) {
			writeBean(buf, enemyGuildList.get(i));
		}
		//今日活跃值
		writeByte(buf, this.activeValue);
		//解散警告值
		writeByte(buf, this.warningValue);
		//自动同意加入帮会的申请
		writeByte(buf, this.autoGuildAgreeAdd);
		//成员数量
		writeByte(buf, this.memberNum);
		//成员战斗力之和
		writeInt(buf, this.memberFightPower);
		//占领地图列表
		writeShort(buf, ownMapList.size());
		for (int i = 0; i < ownMapList.size(); i++) {
			writeInt(buf, ownMapList.get(i));
		}
		//拥有王城
		writeByte(buf, this.ownKingCity);
		//拥有皇城
		writeByte(buf, this.ownEmperorCity);
		//帮会创建时间
		writeInt(buf, this.createTime);
		//帮会创建IP
		writeString(buf, this.createIp);
		//帮会公告最后更新时间
		writeInt(buf, this.lastGuildBulletinTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会id
		this.guildId = readLong(buf);
		//帮会名
		this.guildName = readString(buf);
		//帮会旗帜
		this.guildBanner = readString(buf);
		//帮主id
		this.bangZhuid = readLong(buf);
		//帮主名字
		this.bangZhuName = readString(buf);
		//帮主等级
		this.bangZhuLevel = readShort(buf);
		//帮主是否在线
		this.bangZhuOnline = readByte(buf);
		//副帮主id
		this.viceBangZhuid = readLong(buf);
		//副帮主名字
		this.viceBangZhuName = readString(buf);
		//副帮主等级
		this.viceBangZhuLevel = readShort(buf);
		//副帮主是否在线
		this.viceBangZhuOnline = readByte(buf);
		//旗帜造型
		this.bannerIcon = readInt(buf);
		//旗帜等级
		this.bannerLevel = readByte(buf);
		//帮会公告
		this.guildBulletin = readString(buf);
		//青龙令牌数
		this.dragon = readInt(buf);
		//白虎令牌数
		this.whiteTiger = readInt(buf);
		//朱雀令牌数
		this.suzaku = readInt(buf);
		//玄武令牌数
		this.basaltic = readInt(buf);
		//库存元宝
		this.stockGold = readLong(buf);
		//友好帮会列表
		int friendGuildList_length = readShort(buf);
		for (int i = 0; i < friendGuildList_length; i++) {
			friendGuildList.add((DiplomaticInfo)readBean(buf, DiplomaticInfo.class));
		}
		//敌对帮会列表
		int enemyGuildList_length = readShort(buf);
		for (int i = 0; i < enemyGuildList_length; i++) {
			enemyGuildList.add((DiplomaticInfo)readBean(buf, DiplomaticInfo.class));
		}
		//今日活跃值
		this.activeValue = readByte(buf);
		//解散警告值
		this.warningValue = readByte(buf);
		//自动同意加入帮会的申请
		this.autoGuildAgreeAdd = readByte(buf);
		//成员数量
		this.memberNum = readByte(buf);
		//成员战斗力之和
		this.memberFightPower = readInt(buf);
		//占领地图列表
		int ownMapList_length = readShort(buf);
		for (int i = 0; i < ownMapList_length; i++) {
			ownMapList.add(readInt(buf));
		}
		//拥有王城
		this.ownKingCity = readByte(buf);
		//拥有皇城
		this.ownEmperorCity = readByte(buf);
		//帮会创建时间
		this.createTime = readInt(buf);
		//帮会创建IP
		this.createIp = readString(buf);
		//帮会公告最后更新时间
		this.lastGuildBulletinTime = readInt(buf);
		return true;
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
	 * get 帮会旗帜
	 * @return 
	 */
	public String getGuildBanner(){
		return guildBanner;
	}
	
	/**
	 * set 帮会旗帜
	 */
	public void setGuildBanner(String guildBanner){
		this.guildBanner = guildBanner;
	}
	
	/**
	 * get 帮主id
	 * @return 
	 */
	public long getBangZhuid(){
		return bangZhuid;
	}
	
	/**
	 * set 帮主id
	 */
	public void setBangZhuid(long bangZhuid){
		this.bangZhuid = bangZhuid;
	}
	
	/**
	 * get 帮主名字
	 * @return 
	 */
	public String getBangZhuName(){
		return bangZhuName;
	}
	
	/**
	 * set 帮主名字
	 */
	public void setBangZhuName(String bangZhuName){
		this.bangZhuName = bangZhuName;
	}
	
	/**
	 * get 帮主等级
	 * @return 
	 */
	public short getBangZhuLevel(){
		return bangZhuLevel;
	}
	
	/**
	 * set 帮主等级
	 */
	public void setBangZhuLevel(short bangZhuLevel){
		this.bangZhuLevel = bangZhuLevel;
	}
	
	/**
	 * get 帮主是否在线
	 * @return 
	 */
	public byte getBangZhuOnline(){
		return bangZhuOnline;
	}
	
	/**
	 * set 帮主是否在线
	 */
	public void setBangZhuOnline(byte bangZhuOnline){
		this.bangZhuOnline = bangZhuOnline;
	}
	
	/**
	 * get 副帮主id
	 * @return 
	 */
	public long getViceBangZhuid(){
		return viceBangZhuid;
	}
	
	/**
	 * set 副帮主id
	 */
	public void setViceBangZhuid(long viceBangZhuid){
		this.viceBangZhuid = viceBangZhuid;
	}
	
	/**
	 * get 副帮主名字
	 * @return 
	 */
	public String getViceBangZhuName(){
		return viceBangZhuName;
	}
	
	/**
	 * set 副帮主名字
	 */
	public void setViceBangZhuName(String viceBangZhuName){
		this.viceBangZhuName = viceBangZhuName;
	}
	
	/**
	 * get 副帮主等级
	 * @return 
	 */
	public short getViceBangZhuLevel(){
		return viceBangZhuLevel;
	}
	
	/**
	 * set 副帮主等级
	 */
	public void setViceBangZhuLevel(short viceBangZhuLevel){
		this.viceBangZhuLevel = viceBangZhuLevel;
	}
	
	/**
	 * get 副帮主是否在线
	 * @return 
	 */
	public byte getViceBangZhuOnline(){
		return viceBangZhuOnline;
	}
	
	/**
	 * set 副帮主是否在线
	 */
	public void setViceBangZhuOnline(byte viceBangZhuOnline){
		this.viceBangZhuOnline = viceBangZhuOnline;
	}
	
	/**
	 * get 旗帜造型
	 * @return 
	 */
	public int getBannerIcon(){
		return bannerIcon;
	}
	
	/**
	 * set 旗帜造型
	 */
	public void setBannerIcon(int bannerIcon){
		this.bannerIcon = bannerIcon;
	}
	
	/**
	 * get 旗帜等级
	 * @return 
	 */
	public byte getBannerLevel(){
		return bannerLevel;
	}
	
	/**
	 * set 旗帜等级
	 */
	public void setBannerLevel(byte bannerLevel){
		this.bannerLevel = bannerLevel;
	}
	
	/**
	 * get 帮会公告
	 * @return 
	 */
	public String getGuildBulletin(){
		return guildBulletin;
	}
	
	/**
	 * set 帮会公告
	 */
	public void setGuildBulletin(String guildBulletin){
		this.guildBulletin = guildBulletin;
	}
	
	/**
	 * get 青龙令牌数
	 * @return 
	 */
	public int getDragon(){
		return dragon;
	}
	
	/**
	 * set 青龙令牌数
	 */
	public void setDragon(int dragon){
		this.dragon = dragon;
	}
	
	/**
	 * get 白虎令牌数
	 * @return 
	 */
	public int getWhiteTiger(){
		return whiteTiger;
	}
	
	/**
	 * set 白虎令牌数
	 */
	public void setWhiteTiger(int whiteTiger){
		this.whiteTiger = whiteTiger;
	}
	
	/**
	 * get 朱雀令牌数
	 * @return 
	 */
	public int getSuzaku(){
		return suzaku;
	}
	
	/**
	 * set 朱雀令牌数
	 */
	public void setSuzaku(int suzaku){
		this.suzaku = suzaku;
	}
	
	/**
	 * get 玄武令牌数
	 * @return 
	 */
	public int getBasaltic(){
		return basaltic;
	}
	
	/**
	 * set 玄武令牌数
	 */
	public void setBasaltic(int basaltic){
		this.basaltic = basaltic;
	}
	
	/**
	 * get 库存元宝
	 * @return 
	 */
	public long getStockGold(){
		return stockGold;
	}
	
	/**
	 * set 库存元宝
	 */
	public void setStockGold(long stockGold){
		this.stockGold = stockGold;
	}
	
	/**
	 * get 友好帮会列表
	 * @return 
	 */
	public List<DiplomaticInfo> getFriendGuildList(){
		return friendGuildList;
	}
	
	/**
	 * set 友好帮会列表
	 */
	public void setFriendGuildList(List<DiplomaticInfo> friendGuildList){
		this.friendGuildList = friendGuildList;
	}
	
	/**
	 * get 敌对帮会列表
	 * @return 
	 */
	public List<DiplomaticInfo> getEnemyGuildList(){
		return enemyGuildList;
	}
	
	/**
	 * set 敌对帮会列表
	 */
	public void setEnemyGuildList(List<DiplomaticInfo> enemyGuildList){
		this.enemyGuildList = enemyGuildList;
	}
	
	/**
	 * get 今日活跃值
	 * @return 
	 */
	public byte getActiveValue(){
		return activeValue;
	}
	
	/**
	 * set 今日活跃值
	 */
	public void setActiveValue(byte activeValue){
		this.activeValue = activeValue;
	}
	
	/**
	 * get 解散警告值
	 * @return 
	 */
	public byte getWarningValue(){
		return warningValue;
	}
	
	/**
	 * set 解散警告值
	 */
	public void setWarningValue(byte warningValue){
		this.warningValue = warningValue;
	}
	
	/**
	 * get 自动同意加入帮会的申请
	 * @return 
	 */
	public byte getAutoGuildAgreeAdd(){
		return autoGuildAgreeAdd;
	}
	
	/**
	 * set 自动同意加入帮会的申请
	 */
	public void setAutoGuildAgreeAdd(byte autoGuildAgreeAdd){
		this.autoGuildAgreeAdd = autoGuildAgreeAdd;
	}
	
	/**
	 * get 成员数量
	 * @return 
	 */
	public byte getMemberNum(){
		return memberNum;
	}
	
	/**
	 * set 成员数量
	 */
	public void setMemberNum(byte memberNum){
		this.memberNum = memberNum;
	}
	
	/**
	 * get 成员战斗力之和
	 * @return 
	 */
	public int getMemberFightPower(){
		return memberFightPower;
	}
	
	/**
	 * set 成员战斗力之和
	 */
	public void setMemberFightPower(int memberFightPower){
		this.memberFightPower = memberFightPower;
	}
	
	/**
	 * get 占领地图列表
	 * @return 
	 */
	public List<Integer> getOwnMapList(){
		return ownMapList;
	}
	
	/**
	 * set 占领地图列表
	 */
	public void setOwnMapList(List<Integer> ownMapList){
		this.ownMapList = ownMapList;
	}
	
	/**
	 * get 拥有王城
	 * @return 
	 */
	public byte getOwnKingCity(){
		return ownKingCity;
	}
	
	/**
	 * set 拥有王城
	 */
	public void setOwnKingCity(byte ownKingCity){
		this.ownKingCity = ownKingCity;
	}
	
	/**
	 * get 拥有皇城
	 * @return 
	 */
	public byte getOwnEmperorCity(){
		return ownEmperorCity;
	}
	
	/**
	 * set 拥有皇城
	 */
	public void setOwnEmperorCity(byte ownEmperorCity){
		this.ownEmperorCity = ownEmperorCity;
	}
	
	/**
	 * get 帮会创建时间
	 * @return 
	 */
	public int getCreateTime(){
		return createTime;
	}
	
	/**
	 * set 帮会创建时间
	 */
	public void setCreateTime(int createTime){
		this.createTime = createTime;
	}
	
	/**
	 * get 帮会创建IP
	 * @return 
	 */
	public String getCreateIp(){
		return createIp;
	}
	
	/**
	 * set 帮会创建IP
	 */
	public void setCreateIp(String createIp){
		this.createIp = createIp;
	}
	
	/**
	 * get 帮会公告最后更新时间
	 * @return 
	 */
	public int getLastGuildBulletinTime(){
		return lastGuildBulletinTime;
	}
	
	/**
	 * set 帮会公告最后更新时间
	 */
	public void setLastGuildBulletinTime(int lastGuildBulletinTime){
		this.lastGuildBulletinTime = lastGuildBulletinTime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//帮会id
		buf.append("guildId:" + guildId +",");
		//帮会名
		if(this.guildName!=null) buf.append("guildName:" + guildName.toString() +",");
		//帮会旗帜
		if(this.guildBanner!=null) buf.append("guildBanner:" + guildBanner.toString() +",");
		//帮主id
		buf.append("bangZhuid:" + bangZhuid +",");
		//帮主名字
		if(this.bangZhuName!=null) buf.append("bangZhuName:" + bangZhuName.toString() +",");
		//帮主等级
		buf.append("bangZhuLevel:" + bangZhuLevel +",");
		//帮主是否在线
		buf.append("bangZhuOnline:" + bangZhuOnline +",");
		//副帮主id
		buf.append("viceBangZhuid:" + viceBangZhuid +",");
		//副帮主名字
		if(this.viceBangZhuName!=null) buf.append("viceBangZhuName:" + viceBangZhuName.toString() +",");
		//副帮主等级
		buf.append("viceBangZhuLevel:" + viceBangZhuLevel +",");
		//副帮主是否在线
		buf.append("viceBangZhuOnline:" + viceBangZhuOnline +",");
		//旗帜造型
		buf.append("bannerIcon:" + bannerIcon +",");
		//旗帜等级
		buf.append("bannerLevel:" + bannerLevel +",");
		//帮会公告
		if(this.guildBulletin!=null) buf.append("guildBulletin:" + guildBulletin.toString() +",");
		//青龙令牌数
		buf.append("dragon:" + dragon +",");
		//白虎令牌数
		buf.append("whiteTiger:" + whiteTiger +",");
		//朱雀令牌数
		buf.append("suzaku:" + suzaku +",");
		//玄武令牌数
		buf.append("basaltic:" + basaltic +",");
		//库存元宝
		buf.append("stockGold:" + stockGold +",");
		//友好帮会列表
		buf.append("friendGuildList:{");
		for (int i = 0; i < friendGuildList.size(); i++) {
			buf.append(friendGuildList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//敌对帮会列表
		buf.append("enemyGuildList:{");
		for (int i = 0; i < enemyGuildList.size(); i++) {
			buf.append(enemyGuildList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//今日活跃值
		buf.append("activeValue:" + activeValue +",");
		//解散警告值
		buf.append("warningValue:" + warningValue +",");
		//自动同意加入帮会的申请
		buf.append("autoGuildAgreeAdd:" + autoGuildAgreeAdd +",");
		//成员数量
		buf.append("memberNum:" + memberNum +",");
		//成员战斗力之和
		buf.append("memberFightPower:" + memberFightPower +",");
		//占领地图列表
		buf.append("ownMapList:{");
		for (int i = 0; i < ownMapList.size(); i++) {
			buf.append(ownMapList.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//拥有王城
		buf.append("ownKingCity:" + ownKingCity +",");
		//拥有皇城
		buf.append("ownEmperorCity:" + ownEmperorCity +",");
		//帮会创建时间
		buf.append("createTime:" + createTime +",");
		//帮会创建IP
		if(this.createIp!=null) buf.append("createIp:" + createIp.toString() +",");
		//帮会公告最后更新时间
		buf.append("lastGuildBulletinTime:" + lastGuildBulletinTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}