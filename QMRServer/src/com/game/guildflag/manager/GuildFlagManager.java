package com.game.guildflag.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_guildbannerBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.guild.bean.GuildInfo;
import com.game.guild.manager.GuildServerManager;
import com.game.guild.structs.GuildTmpInfo;
import com.game.guildflag.bean.GuildFlagInfo;
import com.game.guildflag.log.GuildFlagLog;
import com.game.guildflag.message.ResGuildFlagStatusToClientMessage;
import com.game.guildflag.message.ResOpenGuildFlagToClientMessage;
import com.game.guildflag.structs.GuildFiagJF;
import com.game.guildflag.structs.GuildTerritoryFlag;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.structs.Position;
import com.game.utils.CommonConfig;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ServerParamUtil;
import com.game.utils.TimeUtil;

/**帮会领地争夺战
 * 
 * @author zhangrong
 *
 */


public class GuildFlagManager {
	
	private Logger log = Logger.getLogger(GuildFlagManager.class);
	private static Object obj = new Object();

	private static GuildFlagManager manager;

	private GuildFlagManager() {
		setFlagmaplist(getmapidlist());	//加载可插旗地图列表
		setFlagidlist(getflagmonidlist());	//加载旗帜ID
	}

	public static GuildFlagManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new GuildFlagManager();
			}
		}
		return manager;
	}
	
	

	
	//储存已插旗地图
	private static ConcurrentHashMap<Integer,GuildTerritoryFlag> territorymap = new ConcurrentHashMap<Integer,GuildTerritoryFlag>();
	
	//从配置库读入 可插旗地图列表
	private static List<Integer> flagmaplist = new ArrayList<Integer>();
	
	//从配置库读入  旗帜怪物ID列表
	private static List<Integer> flagidlist = new ArrayList<Integer>();
	
	//攻击帮旗积分
	public static ConcurrentHashMap<Integer,GuildFiagJF> attackjfmap = new ConcurrentHashMap<Integer,GuildFiagJF>();
	
	//临时保存参战帮会名字
	public static ConcurrentHashMap<Long,String> guildnamemap = new ConcurrentHashMap<Long,String>();
	
	//帮会争夺战状态 0 关闭，1开启，2结束（2只是用来区分下一次争夺战开始时间）
	private int flagwarstatus;
	
	//帮会争夺战结束时间
	private int flagwarendtime;
	//开区多少天后开启领地争夺战
	private int OpenArea = 14;
	/**
	 * 帮会争夺战存在时间（秒）
	 */
	public static int flagwarmaxtime = 60*60;
	
	/**载入帮会领地争夺战内容
	 * 
	 */
	public void loadguildfiag(int sid){
		int countryid = WServer.getGameConfig().getCountryByServer(sid);
		if (ServerParamUtil.getImportantParamMap().containsKey(ServerParamUtil.GUILDFLAG+countryid)) {
			String dataString=ServerParamUtil.getImportantParamMap().get(ServerParamUtil.GUILDFLAG+countryid);
			@SuppressWarnings("unchecked")
			ConcurrentHashMap<Integer,GuildTerritoryFlag> map = JSON.parseObject(dataString, ConcurrentHashMap.class);
			territorymap.putAll(map);
		}
	}

	/**保存帮会领地争夺战内容
	 * 
	 */
	public void saveguildfiag(){
		try {
			int countryid = WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId());
			ServerParamUtil.threadSaveImportant(ServerParamUtil.GUILDFLAG+countryid, JSON.toJSONString(territorymap, SerializerFeature.WriteClassName));
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	/**获取数据库内可插旗的地图ID
	 * 
	 * @return
	 */
	public List<Integer> getmapidlist(){
		Q_globalBean db = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.GUILD_FLAG_MAP.getValue());
		List<Integer> monsteridList = new ArrayList<Integer>();
		if (db != null) {
			try {
				monsteridList = JSON.parseArray(db.getQ_string_value(), Integer.class);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return monsteridList;
	}
	
	
	/**领地争夺战，旗帜怪物ID
	 * 
	 * @return
	 */
	public List<Integer> getflagmonidlist(){
		Q_globalBean db = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.GUILD_FLAG_MONID.getValue());
		List<Integer> flagmonidList = new ArrayList<Integer>();
		if (db != null) {
			try {
				flagmonidList = JSON.parseArray(db.getQ_string_value(), Integer.class);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return flagmonidList;
	}
	
	
	
	/**获取数据库内插旗需要的帮会金币
	 * 
	 * @return
	 */
	public int getguildgold(){
		Q_globalBean db = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.GUILD_FLAG_GOLD.getValue());
		return db.getQ_int_value();
	}
	

	/**帮会争夺战状态 0 关闭，1开启,2结束
	 * 
	 * @param flagstatus
	 */
	public int getFlagwarstatus() {
		return flagwarstatus;
	}
	/**帮会争夺战状态 0 关闭，1开启,2结束
	 * 
	 * @param flagstatus
	 */
	public void setFlagwarstatus(int flagwarstatus) {
		this.flagwarstatus = flagwarstatus;
	}
	
	
	

	
	/**加砍旗积分
	 * 
	 * @param player
	 */
	public void addFlagWarJF(Player player,int jf){
		if (player != null && player.getGuildId() > 0) {
			if (attackjfmap.containsKey(player.getMapModelId())) {
				GuildFiagJF guildFiagJF = attackjfmap.get(player.getMapModelId());
				if (guildFiagJF.getJifenmap().containsKey(player.getGuildId())) {
					int num = guildFiagJF.getJifenmap().get(player.getGuildId()) + jf;
					guildFiagJF.getJifenmap().put(player.getGuildId(), num);
				}else {
					guildFiagJF.getJifenmap().put(player.getGuildId(), jf);
				}
			}else {
				GuildFiagJF guildFiagJF = new GuildFiagJF();
				guildFiagJF.getJifenmap().put(player.getGuildId(), jf);
				attackjfmap.put(player.getMapModelId(), guildFiagJF);
			}
		}	
	}
	

	
	
	
	
	
	
	
//	---------------移动到脚本GuildFlagScript 里面去了--------------------
//	/**领地争夺战开启时间为：每周2、5晚8.30-9.30
//	 * 
//	 */
//	public void loopcall() {
//		long millis = System.currentTimeMillis();
//		long week = TimeUtil.getDayOfWeek(millis);
//		long min = TimeUtil.getDayOfMin(millis);
//		long hour = TimeUtil.getDayOfHour(millis);
//		int kday = TimeUtil.getOpenAreaDay();
//		
//		if (week == 2 || week == 5) {
//			if (hour == 20 &&  min == 30) {  	//开始
//				setFlagwarstatus(1);
//				setFlagwarendtime((int)(System.currentTimeMillis()/1000) + flagwarmaxtime);
//				guildnamemap.clear();
//				attackjfmap.clear();
//				MessageUtil.notify_All_player(Notifys.SROLL, "帮会领地争夺战开始了!");
//				ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
//				cmsg.setTime(flagwarmaxtime);
//				MessageUtil.tell_world_message(cmsg);
//			}else if (hour == 21 && min == 30){	//结束
//				setFlagwarstatus(2);
//				setFlagwarendtime(0);
//				MessageUtil.notify_All_player(Notifys.SROLL, "今日的帮会领地争夺战结束了!");
//				ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
//				MessageUtil.tell_world_message(cmsg);
//			}else if (hour == 23 && min >= 58){	
//				setFlagwarstatus(0);
//				
//			}else if (hour == 0 && min == 1) {
//				setFlagmaplist(getmapidlist());	//重新加载可插旗地图列表
//			}
//		}
//	}
//	
	
	
	/**GM命令，开启领地争夺战，帮旗
	 * 
	 */
	public void testflag(Player player,int type){
		if (type == 1) {
			try {
				GuildFlagLog glog = new GuildFlagLog();
				glog.setType(0);
				glog.setData(JSON.toJSONString(territorymap, SerializerFeature.WriteClassName));
				LogService.getInstance().execute(glog);
			} catch (Exception e) {
				log.error(e);
			}

			
			setFlagwarstatus(1);
			setFlagwarendtime((int)(System.currentTimeMillis()/1000) + flagwarmaxtime);
			guildnamemap.clear();
			attackjfmap.clear();
			MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("帮会领地争夺战开始了!"));
			ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
			cmsg.setTime(flagwarmaxtime);
			MessageUtil.tell_world_message(cmsg);
		}else if (type== 2) {
			setFlagwarstatus(0);
			setFlagwarendtime((int)(System.currentTimeMillis()/1000)+5);
			MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日的帮会领地争夺战结束了!"));
			ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
			MessageUtil.tell_world_message(cmsg);
		}else if (type==3) {
			stReqInsertGuildFlagToGameMessage(player);
			
		}else if (type==4){
			territorymap.remove(player.getMapModelId());
		}
		
	}
	
	
	
	
	
	/**发送争夺战信息
	 * 
	 * @param parameter
	 */
	public void stReqOpenGuildFlagToGameMessage(Player player) {
		ResOpenGuildFlagToClientMessage cmsg = new ResOpenGuildFlagToClientMessage();
		List<Integer> mapidlist = getmapidlist();
		GuildFlagInfo guildFlagInfo = new GuildFlagInfo();
		for (int mapid : mapidlist) {
			if (territorymap.containsKey(mapid)) {
				cmsg.getChallengeInfo().add(territorymap.get(mapid).makeGuildFlagInfo(mapid));
			}else {
				cmsg.getChallengeInfo().add(guildFlagInfo);
			}
		}
		cmsg.setStatus(getFlagWarstrTime());
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	/**显示活动时间
	 * 
	 * @return
	 */
	public String getFlagWarstrTime(){
		long week = TimeUtil.getDayOfWeek(System.currentTimeMillis());
		long time =0;
		int day = TimeUtil.getOpenAreaDay();
		if (day >= getOpenArea()) {
			if (week > 2 && week <= 5) {
				if (getFlagwarstatus() == 0) {
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),5);
				}else if (getFlagwarstatus() == 2){
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),2);
				}else if (getFlagwarstatus() == 1){
					return ResManager.getInstance().getString("今日领地争夺战活动正在进行中");
				}
			}else {
				if (getFlagwarstatus() == 0) {
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),2);
				}else if (getFlagwarstatus() == 2){
					time = TimeUtil.getSoonWeek(System.currentTimeMillis(),5);
				}else if (getFlagwarstatus() == 1){
					return ResManager.getInstance().getString("今日领地争夺战活动正在进行中");
				}
			}
			long curday = TimeUtil.getDayOfMonth(System.currentTimeMillis());
			int mday = (int)TimeUtil.getDayOfMonth(time);
			if (curday == mday) {
				if (getFlagwarstatus() == 0) {
					return String.format(ResManager.getInstance().getString("今日领地争夺战时间：今晚20时30分"));
				}
			}
			
		}else {
			int sday = getOpenArea() - day;
			long time_a = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),2);
			long time_b = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),5);
			if (time_b < time_a) {
				time = time_b;
			}else{
				time = time_a;
			}
		}
		
		long mday = TimeUtil.getDayOfMonth(time);
		long month = TimeUtil.getMonth(time)+1;
		return String.format(ResManager.getInstance().getString("下次领地争夺战时间：%d月%d日20时30分"), month,mday);
	}
	
	

	
	
	
	/**插旗
	 * 
	 * @param parameter
	 */
	public void stReqInsertGuildFlagToGameMessage(Player player) {
		try {
			
			if(player.getGuildId() == 0){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只能帮主或者副帮主才能安插帮旗。"));
				return;
			}
			
			if ( player.getMemberInfo().getGuildPowerLevel() != 1 && player.getMemberInfo().getGuildPowerLevel() != 2) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只能帮主或者副帮主才能安插本帮帮旗。"));
				return;
			}
			
			
			if (getFlagwarstatus() != 1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有在领地争夺战期间才可以插旗，")+getFlagWarstrTime());
				return;
			}
			
			if (player.getLine() != 1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有在1线才可以进行领地争夺战。"));
				return;
			}
			
			Map map=ManagerPool.mapManager.getMap(player);
			if (map == null) {
				return;
			}
	
			List<Integer> mapidlist= getmapidlist();
			if (mapidlist.contains(map.getMapModelid()) == false) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有在【赵苑及以上地图】中才可安插帮旗。"));
				return;
			}
			
			if (territorymap.containsKey(map.getMapModelid())) {
				GuildTerritoryFlag guildTerritoryFlag = territorymap.get(map.getMapModelid());
				if (guildTerritoryFlag.getGuildid() == player.getGuildId()) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前地图已安插本帮旗帜。"));
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前地图已存在帮旗，将其破坏后才能安插新旗帜。"));
				}
				return;
			}
			
			if (attackjfmap.containsKey(map.getMapModelid())) {
				GuildFiagJF guildFiagJF = attackjfmap.get(map.getMapModelid());
				if (guildFiagJF.getPriorityguildid() != player.getGuildId()  && guildFiagJF.getPriorityguildid() > 0) {
					if ( (int)(System.currentTimeMillis()/1000)- guildFiagJF.getPrioritytime() < 60* 3) {
						MessageUtil.notify_player(player, Notifys.ERROR, "【{1}】帮派在刚才的领地争夺战中砍旗最勇猛，拥有插旗优先权3分钟.",guildFiagJF.getPriorityguildname());
						return;
					}
				}
			}
	
			
			
			int money = getguildgold();
			if (player.getGuildInfo().getStockGold()  >= money) {
				Q_mapBean mapdb = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
				Position position = new Position();
				String xyString = "";
				if(mapdb.getQ_flag_pos() != null){
					Integer[] pos = JSON.parseObject(mapdb.getQ_flag_pos(),Integer[].class);
					position.setX((short) (pos[0]*MapUtils.GRID_BORDER));
					position.setY((short) (pos[1]*MapUtils.GRID_BORDER));
					xyString = pos[0] +","+ pos[1];
				}else {
					position = player.getPosition();
					xyString = (position.getX()/MapUtils.GRID_BORDER) +","+ (position.getY()/MapUtils.GRID_BORDER);
				}
				double distance = MapUtils.countDistance(player.getPosition(), position);	//得到距离
				if (distance > 20*MapUtils.GRID_BORDER) {//20格距离内
					MessageUtil.notify_player(player, Notifys.ERROR, "您需要到当前地图{1}（帮旗安插的坐标）坐标附近才能安插帮旗。",xyString);
					return;
				}
				
				
				String gname = player.getGuildInfo().getGuildName();
				GuildServerManager.getInstance().reqInnerKingCityEventToWorld(player,1,money+"");	//扣钱
				GuildTerritoryFlag guildTerritoryFlag = new GuildTerritoryFlag();
				guildTerritoryFlag.setFlagid(player.getGuildInfo().getBannerIcon());
				guildTerritoryFlag.setFlaglevel(player.getGuildInfo().getBannerLevel());
				guildTerritoryFlag.setGuildid(player.getGuildInfo().getGuildId());
				guildTerritoryFlag.setGuildname(gname);
				guildTerritoryFlag.setPosition(position);
				
				GuildTmpInfo guildTmpInfo = GuildServerManager.getInstance().getGuildTmpInfo(player.getGuildId());	//读取帮会临时数据
				if (guildTmpInfo != null) {
					guildTerritoryFlag.setGuildheadid(guildTmpInfo.getbangzhu(1).getMemberid());
					guildTerritoryFlag.setGuildheadname(guildTmpInfo.getbangzhu(1).getMembername());
				}

				territorymap.put(map.getMapModelid(), guildTerritoryFlag);
				MessageUtil.notify_player(player, Notifys.SUCCESS, "恭喜您成功的安插了帮旗。。");
	
				MessageUtil.notify_guild_all(player.getGuildId() , String.format("我帮在【%s】%s成功安插了一面新的帮旗",mapdb.getQ_map_name(),xyString));
				saveguildfiag();
				MessageUtil.notify_All_player(Notifys.CUTOUT, "【{1}】帮派在【{2}】成功安插了一面新的帮旗",gname , mapdb.getQ_map_name());
				log.error(gname+" 在 "+mapdb.getQ_map_name()+" 安插旗帜");
	
				if (attackjfmap.containsKey(map.getMapModelid())) {//移除积分记录
					attackjfmap.remove(map.getMapModelid());
				}
				
				GuildFlagLog glog = new GuildFlagLog();
				glog.setType(2);
				glog.setData(JSON.toJSONString(territorymap, SerializerFeature.WriteClassName));
				LogService.getInstance().execute(glog);
				
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, "帮贡仓库铜币不足{1}，不能插旗。",money+"");
			}
		} catch (Exception e) {
			log.error("领地争夺战插旗出错:"+e,e);
		}
	}

	
	
	/**积分排序，获得最高分数的帮会
	 * 
	 * @param mapmodelid
	 * @return 
	 */
	public long getjifensequence(int mapmodelid){
		if (attackjfmap.containsKey(mapmodelid)) {
			GuildFiagJF guildFiagJF = attackjfmap.get(mapmodelid);
			long gid=0;
			int jf = 0;
			Iterator<Entry<Long, Integer>> it = guildFiagJF.getJifenmap().entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, Integer> entry = it.next();
				if (entry.getValue() >= jf) {
					gid = entry.getKey();
					jf = entry.getValue();
				}
			}
			guildFiagJF.getJifenmap().clear();//清除本地图进攻帮会记录积分
			guildFiagJF.setPriorityguildid(gid);//记录最高分数的帮会
			guildFiagJF.setPriorityguildname(getGuildName(gid));
			guildFiagJF.setPrioritytime((int )(System.currentTimeMillis()/1000));//记录插旗优先权开始时间
			return gid;
		}
		return 0;
	}
	
	
	/**移除帮旗
	 * 
	 * @param gid
	 */
	public void removeguildflag(long gid ){
		Iterator<Entry<Integer, GuildTerritoryFlag>> it = territorymap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, GuildTerritoryFlag> entry = it.next();
			if(entry.getValue().getGuildid()== gid){
				it.remove();
			}
		}
		saveguildfiag();
	} 
	
	
	
	
	public ConcurrentHashMap<Integer, GuildTerritoryFlag> getTerritorymap() {
		return territorymap;
	}

	public void setTerritorymap(
			ConcurrentHashMap<Integer, GuildTerritoryFlag> territorymap) {
		GuildFlagManager.territorymap = territorymap;
	}

	public List<Integer> getFlagmaplist() {
		return flagmaplist;
	}

	public void setFlagmaplist(List<Integer> flagmaplist) {
		GuildFlagManager.flagmaplist = flagmaplist;
	}

	public static List<Integer> getFlagidlist() {
		return flagidlist;
	}

	public static void setFlagidlist(List<Integer> flagidlist) {
		GuildFlagManager.flagidlist = flagidlist;
	}

	public static ConcurrentHashMap<Integer, GuildFiagJF> getAttackjfmap() {
		return attackjfmap;
	}

	public static void setAttackjfmap(ConcurrentHashMap<Integer, GuildFiagJF> attackjfmap) {
		GuildFlagManager.attackjfmap = attackjfmap;
	}

	public int getFlagwarendtime() {
		return flagwarendtime;
	}

	public void setFlagwarendtime(int flagwarendtime) {
		this.flagwarendtime = flagwarendtime;
	}


	/**领地争夺战加特定BUFF
	 * 
	 */
	public void addGuildFlagBuff(Fighter fighter, Player player){
		if (fighter instanceof Monster) {
			return;
		}
		Player attkplayer = null;	//攻击者
		if (fighter instanceof Player) {
			attkplayer = (Player) fighter;
		}else if (fighter instanceof Pet) {
			attkplayer = ManagerPool.petInfoManager.getPetHost((Pet)fighter);
		}
		
		if (attkplayer != null) {
			List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.FLAG_BUFF);
			List<Buff> buffs2 = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.FLAG_DEF_BUFF);
			if (buffs.size() == 0 && buffs2.size() == 0) {	//被攻击者身上没有BUFF
				return;
			}

			if (buffs.size() > 0 ) {
				ManagerPool.buffManager.removeByBuffId(attkplayer, BuffConst.FLAG_BUFF);
				ManagerPool.buffManager.addBuff(attkplayer, attkplayer, BuffConst.FLAG_DEF_BUFF , 0, 0, 0);
			}else {
				if (buffs2.size() > 0) {
					ManagerPool.buffManager.removeByBuffId(attkplayer, BuffConst.FLAG_DEF_BUFF);
					ManagerPool.buffManager.addBuff(attkplayer, attkplayer, BuffConst.FLAG_BUFF , 0, 0, 0);
				}
			}
			//由于系统得不到帮会名字，，只好在这里做个缓存，所有参与的帮会名字都会被记录下来
			if(attkplayer.getGuildId() > 0 && !guildnamemap.containsKey(player.getGuildId())){
				guildnamemap.put(attkplayer.getGuildId(), attkplayer.getGuildInfo().getGuildName());
			}
		}
	}
	
	
	/**检测是否有帮派领地战BUFF
	 * 
	 * @param player
	 * @return
	 */
	public boolean ckFlagBuff(Player player){
		List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.FLAG_BUFF);
		List<Buff> buffs2 = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.FLAG_DEF_BUFF);
		if (buffs.size() > 0 || buffs2.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	/**得到帮会名字，
	 * 
	 * @param gid
	 * @return
	 */
	public String getGuildName(long gid){
		if (guildnamemap.containsKey(gid)) {
			return guildnamemap.get(gid);
		}
		return ResManager.getInstance().getString("未知帮会");
	}
	
		
	/**上线通知帮派领地争夺战，剩余时间
	 * 
	 * @param player
	 */
	public void loginGuildFlagStatus(Player player){
		if (getFlagwarstatus() == 1) {
			ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
			int ms = (int)(System.currentTimeMillis()/1000);
			cmsg.setTime(getFlagwarendtime() - ms );
			MessageUtil.tell_player_message(player, cmsg);
		}
	}

	
	/**帮旗更新
	 * 
	 * @param gid
	 * @param flagid
	 * @param flaglv
	 */
	public void updateFlag(long gid ,int flagid ,int flaglv){
		for (GuildTerritoryFlag guildTerritoryFlag : territorymap.values()) {
			if (guildTerritoryFlag.getGuildid() == gid && (guildTerritoryFlag.getFlagid() != flagid || guildTerritoryFlag.getFlaglevel() != flaglv)) {
				guildTerritoryFlag.setFlagid(flagid);
				guildTerritoryFlag.setFlaglevel(flaglv);
			}
		}
		saveguildfiag();
	}
	
	/**帮旗结构帮主更新
	 * 
	 * @param gid
	 * @param flagid
	 * @param flaglv
	 */
	public void updateFlaghead(long gid ,String name ,long pid){
		for (GuildTerritoryFlag guildTerritoryFlag : territorymap.values()) {
			if (guildTerritoryFlag.getGuildid() == gid && guildTerritoryFlag.getGuildheadid() != pid ) {
				guildTerritoryFlag.setGuildheadid(pid);
				guildTerritoryFlag.setGuildheadname(name);
			}
		}
		saveguildfiag();
	}
	
	
	
	/**检测地图是否有本帮旗帜 true=有，false=无
	 * @return 
	 * 
	 */
	public boolean ckmapflag(Player player ,Map map){
		if (player.getGuildId() > 0) {
			if (territorymap.containsKey(map.getMapModelid())) {
				if(territorymap.get(map.getMapModelid()).getGuildid() == player.getGuildId()){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 帮旗变更或者切换地图对应的帮旗加成BUFF变化
	 * 
	 * @param player
	 */
	public void checkAndAddGuildFlagBUFF(Player player,Map map){
		try {
			for (int i = 1; i <= 5; i++) {
				Q_guildbannerBean model = DataManager.getInstance().q_guildbannerContainer.getMap().get(i);
				BuffManager.getInstance().removeByBuffId(player, model.getBuffid());
			}
			if (ckmapflag( player , map) && getFlagwarstatus() != 1) {
				Q_guildbannerBean model = DataManager.getInstance().q_guildbannerContainer.getMap().get((int)player.getGuildInfo().getBannerLevel());
				if(model==null){
					return;
				}else{
					BuffManager.getInstance().addBuff(player, player, model.getBuffid(), 0, 0, 0, 0);	
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	
	
	/**同步帮派临时数据缓存 
	 * isCover =false 如果不存在，则写入  ，true = 覆盖更新
	 * 
	 */
	public void synGuildTmpInfo(GuildInfo guildInfo,boolean isCover){
		if(isCover){
			//覆盖更新
			if(!GuildServerManager.GuildTmpInfoMap.containsKey(guildInfo.getGuildId())){
				GuildTmpInfo guildTmpInfo = new GuildTmpInfo();
				GuildServerManager.GuildTmpInfoMap.put(guildInfo.getGuildId(), guildTmpInfo.saveGuildTmpInfo(guildInfo));
			}else {
				GuildTmpInfo guildTmpInfo = GuildServerManager.GuildTmpInfoMap.get(guildInfo.getGuildId());
				guildTmpInfo.saveGuildTmpInfo(guildInfo);
			}
		}else {
			if(!GuildServerManager.GuildTmpInfoMap.containsKey(guildInfo.getGuildId())){
				GuildTmpInfo guildTmpInfo = new GuildTmpInfo();
				GuildServerManager.GuildTmpInfoMap.put(guildInfo.getGuildId(), guildTmpInfo.saveGuildTmpInfo(guildInfo));
			}
		}
	}

	public int getOpenArea() {
		return OpenArea;
	}

	public void setOpenArea(int openArea) {
		OpenArea = openArea;
	}

	
	
	
	
	
	
	
	
}
