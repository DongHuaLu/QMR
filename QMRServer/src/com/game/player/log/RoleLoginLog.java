package com.game.player.log;

import org.apache.log4j.Logger;

import com.game.dblog.TableCheckStepEnum;
import com.game.dblog.base.Log;
import com.game.dblog.bean.BaseLogBean;
import com.game.json.JSONserializable;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.vip.manager.VipManager;

public class RoleLoginLog extends BaseLogBean {
	private long roleId;
	private int nowcount;//当前在线人数
	private String ipaddress;
	private int level;//人物等级
	private int line;//登录所在线
	private int serverid;//登录所在服务器
	private int mapid;//登录地图ID
	private String name;//角色名
	private String gold;//元宝信息
	private int accuonlinetime;//累计在线时间
	private int bindgold;//绑定元宝信息
	private int money;//铜币信息
	private int bagcellsnum;
	private int dailytaskcount;//己接日常任务数
	private int daydevourcount;//当日吞噬数
	private int conquertaskcount;//己接讨伐任务数
	private String userId;
	private int logintype;
	private int sid;
	private int vipid;
	private String username;
	
	@Log(logField="vipid",fieldType="int")
	public int getVipid() {
		return vipid;
	}

	public void setVipid(int vipid) {
		this.vipid = vipid;
	}

	@Log(logField="username",fieldType="varchar(255)")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	@Override
	public void logToFile() {
		logger.error(buildSql());
	}
	private static final Logger logger=Logger.getLogger("RoleLoginLog");
	
	public RoleLoginLog(){
		
	}
	public RoleLoginLog(Player player) {
		setRoleId(player.getId());
		setNowcount(PlayerManager.getInstance().getOnlineCount());
		setLevel(player.getLevel());
		setMapid(player.getMapModelId());
		setLine(player.getLine());
		setName(player.getName());
		setAccuonlinetime(player.getAccunonlinetime());
		setBindgold(player.getBindGold());
		setGold(player.getGold()==null?"":JSONserializable.toString(player.getGold()));
		setMoney(player.getMoney());
		setBagcellsnum(player.getBagCellsNum());
		setDailytaskcount(player.getDailyTaskCount());
		setDaydevourcount(player.getDaydevourcount());
		setConquertaskcount(player.getConquerTaskCount());
		setServerid(player.getServerId());
		setIpaddress(player.getLoginIp());
		setUserId(player.getUserId());
		setLogintype(player.getLoginType());
		setSid(player.getCreateServerId());
		setUsername(player.getUserName());
		setVipid(VipManager.getInstance().getPlayerVipId(player));
	}

	@Log(logField="logintype",fieldType="int")
	public int getLogintype() {
		return logintype;
	}
	public void setLogintype(int logintype) {
		this.logintype = logintype;
	}
	@Log(logField="userid",fieldType="varchar(512)")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Log(logField="roleid",fieldType="bigint")
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Log(logField="ipaddress",fieldType="varchar(50)")
	public String getIpaddress() {
		return ipaddress;
	}
	
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	
	@Override
	public TableCheckStepEnum getRollingStep() {
		return TableCheckStepEnum.DAY;
	}
	
	@Log(logField="nowcount",fieldType="int")
	public int getNowcount() {
		return nowcount;
	}
	public void setNowcount(int nowcount) {
		this.nowcount = nowcount;
	}
	
	@Log(logField="level",fieldType="int")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Log(logField="line",fieldType="int")
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	@Log(logField="mapid",fieldType="int")
	public int getMapid() {
		return mapid;
	}
	public void setMapid(int mapid) {
		this.mapid = mapid;
	}
	@Log(logField="rolename",fieldType="varchar(50)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Log(logField="goldinfo",fieldType="varchar(512)")
	public String getGold() {
		return gold;
	}
	public void setGold(String gold) {
		this.gold = gold;
	}
	@Log(logField="accuonlinetime",fieldType="int")
	public int getAccuonlinetime() {
		return accuonlinetime;
	}
	public void setAccuonlinetime(int accuonlinetime) {
		this.accuonlinetime = accuonlinetime;
	}
	@Log(logField="bindgold",fieldType="int")
	public int getBindgold() {
		return bindgold;
	}
	public void setBindgold(int bindgold) {
		this.bindgold = bindgold;
	}
	@Log(logField="money",fieldType="int")
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	@Log(logField="bagcellsnum",fieldType="int")
	public int getBagcellsnum() {
		return bagcellsnum;
	}
	public void setBagcellsnum(int bagcellsnum) {
		this.bagcellsnum = bagcellsnum;
	}
	@Log(logField="dailytaskcount",fieldType="int")
	public int getDailytaskcount() {
		return dailytaskcount;
	}
	public void setDailytaskcount(int dailytaskcount) {
		this.dailytaskcount = dailytaskcount;
	}
	@Log(logField="conquertaskcount",fieldType="int")
	public int getConquertaskcount() {
		return conquertaskcount;
	}
	public void setConquertaskcount(int conquertaskcount) {
		this.conquertaskcount = conquertaskcount;
	}
	@Log(logField="daydevourcount",fieldType="int")
	public int getDaydevourcount() {
		return daydevourcount;
	}
	public void setDaydevourcount(int daydevourcount) {
		this.daydevourcount = daydevourcount;
	}
	public static void main(String args[]){
	}
	@Log(logField="serverid",fieldType="int")
	public int getServerid() {
		return serverid;
	}
	public void setServerid(int serverid) {
		this.serverid = serverid;
	}
}
