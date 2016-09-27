package com.game.country.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.country.bean.CountryTopInfo;
import com.game.country.bean.CountryWarInfo;
import com.game.country.bean.JobAwardInfo;
import com.game.country.log.CountryLog;
import com.game.country.message.ReqCountrySiegeSelectToClientMessage;
import com.game.country.message.ReqCountryStructureInfoToWoridMessage;
import com.game.country.message.ReqCountrySyncKingCityToWoridMessage;
import com.game.country.message.ReqCountryWarCarInAdvanceToGameMessage;
import com.game.country.message.ReqCountryWarCarToGameMessage;
import com.game.country.message.ResCountryArtilleryLocusToClientMessage;
import com.game.country.message.ResCountryJobAwardInfoToClientMessage;
import com.game.country.message.ResCountrySiegeHomingYuxiToClientMessage;
import com.game.country.message.ResCountrySiegeSelectToGameMessage;
import com.game.country.message.ResCountrySiegeWarStateToClientMessage;
import com.game.country.message.ResCountrySiegeYuXiImmediateToClientMessage;
import com.game.country.message.ResCountrySyncKingCityToGameMessage;
import com.game.country.message.ResCountryTopInfoToClientMessage;
import com.game.country.message.ResCountryWarCarDamageToClientMessage;
import com.game.country.structs.KingCity;
import com.game.country.structs.KingData;
import com.game.country.structs.SiegeSMS;
import com.game.country.timer.TransferMirrorBackEvent;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_npcBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.drop.structs.MapDropInfo;
import com.game.guild.manager.GuildServerManager;
import com.game.guild.structs.GuildTmpInfo;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.manager.MapManager;
import com.game.map.message.ResRoundObjectsMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Effect;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.npc.struts.NPC;
import com.game.pet.struts.Pet;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.util.TimerUtil;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ServerParamUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;

public class CountryManager {
	
	/**王城争霸战
	 * 
	 */
	protected Logger log = Logger.getLogger(CountryManager.class);
	//玩家管理类实例
	private static CountryManager manager;
	private static Object obj = new Object();
	
	public static CountryManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new CountryManager();
			}
		}
		return manager;
	}
	
	public CountryManager(){

	}
	
	public static int KILL = 1;		//杀敌数量
	public static int DEATH = 2;	//死亡次数
	public static int HURT = 3;		//总伤害输出
	public static int BEENHURT = 4;	//被伤害总数
	
	
	
	/**王城争霸战，排行榜
	 * 
	 */
	HashMap<Long,SiegeSMS> SiegeSMSTopMap = new HashMap<Long,SiegeSMS>();
	
	
	/**王城雕像是否展示 KEY
	 * 
	 */
	public static String WANGCHENGDIAOXIANG = "WANGCHENGDIAOXIANG";
	
	//王城简要信息，储存所有国家王城帮派
	public static HashMap<Integer, Long> kingcitymap = new HashMap<Integer, Long>();
	
	//攻城状态： 0没有攻城，1攻城进行中,2结束
	private int siegestate;

	//王城信息
	private KingCity kingcity= new KingCity();
	
	//攻城战时间记录 用来做倒计时(秒)
	private long siegecountdown;
	
	//进入战场随机传送点
	private int[][] ALONE_XY = {{20,107},{56,98},{92,133},{121,142},{76,104}};
	
	//王城防守方复活点
	private int[] GUARD_XY = {208,105};
	
	//进攻者复活点
	private int[] ATTACK_XY = {31,89};
	
	//玉玺NPC
	private int YuXiNpc = 12300;
	
	//开区多少天后攻城
	private int OpenArea = 14;
	
	//点击Npc玉玺开始夺取的时间,脚本用来控制公告出现的频繁度
	private long yuxitime;
	
	//刷怪标记
	private int monstatus;
	
	//到点全部传送回城
	private long movetime;
	
	
	/**攻城战地图id
	 * 
	 */
	public  int SIEGE_MAPID = 20017;
	
	/**拔起玉玺需要的帮贡仓库铜币数量
	 * 
	 * @return
	 */
	public int getYuXiGuildGold(){
		return ManagerPool.dataManager.q_globalContainer.getMap().get(110).getQ_int_value();
	}
	
	
	
	public long getYuxitime() {
		return yuxitime;
	}

	public void setYuxitime(long yuxitime) {
		this.yuxitime = yuxitime;
	}
	
	
	
	public KingCity getKingcity() {
		return kingcity;
	}

	public void setKingcity(KingCity kingcity) {
		this.kingcity = kingcity;
	}
	
	/**攻城战状态
	 * 
	 * @return
	 */
	public int getSiegestate() {
		return siegestate;
	}

	public void setSiegestate(int siegestate) {
		this.siegestate = siegestate;
	}
	/**攻城开始时间
	 * 
	 * @return
	 */
	public long getSiegecountdown() {
		return siegecountdown;
	}

	public void setSiegecountdown(long siegecountdown) {
		this.siegecountdown = siegecountdown;
	}
	
	public int getYuXiNpc() {
		return YuXiNpc;
	}

	public void setYuXiNpc(int yuXiNpc) {
		YuXiNpc = yuXiNpc;
	}
	
	public int getMonstatus() {
		return monstatus;
	}

	public void setMonstatus(int monstatus) {
		this.monstatus = monstatus;
	}

	/**载入攻城战王城内容
	 * 
	 */
	public void loadkingcity(int sid){
		int countryid = WServer.getGameConfig().getCountryByServer(sid);
		for (int i = 0; i < 10; i++) {
			if (ServerParamUtil.getImportantParamMap().containsKey(ServerParamUtil.KINGCITYWAR+i)) {
				if (countryid == i) {
					String dataString=ServerParamUtil.getImportantParamMap().get(ServerParamUtil.KINGCITYWAR+countryid);
					KingCity jskingcity = JSON.parseObject(dataString, KingCity.class);
					setKingcity(jskingcity);
					kingcitymap.put(i,jskingcity.getGuildid() );
					setdiaoxiang(true,sid);
				}else {
					String otherdata =ServerParamUtil.getImportantParamMap().get(ServerParamUtil.KINGCITYWAR+countryid);
					if (otherdata != null) {
						KingCity otherking = JSON.parseObject(otherdata, KingCity.class);
						kingcitymap.put(i,otherking.getGuildid() );
					}

				}
			}
		}
	}

	
	
	
	

	/**同步所其他国家王城
	 * 
	 * @param msg
	 */
	public void stResCountrySyncKingCityToGameMessage(ResCountrySyncKingCityToGameMessage msg) {
		kingcitymap.put(msg.getCountryid(), msg.getGuildid());
	}
	
	
	/**得到攻城战地图
	 * 
	 * @return
	 */
	public Map getSiegeMap(){
		return ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),  1,SIEGE_MAPID );
	}
	
	
	
	
	/**攻城战循环调用
	 * 
	 */
	public void loopcall() {
		long millis = System.currentTimeMillis();
		long week = TimeUtil.getDayOfWeek(millis);
		long min = TimeUtil.getDayOfMin(millis);
		long hour = TimeUtil.getDayOfHour(millis);
		int kday = TimeUtil.getOpenAreaDay();
		if (kday >= getOpenArea()) {
			if (week == 6 ) {
				if (hour == 19 &&  min == 30) {
					setMonstatus(0);
					setSiegestate(0);
					MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日王城攻城战将在半小时后开启，请参战各方做好战前准备"));
				}else if (hour == 19 &&  min == 50) {
					MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日王城攻城战将在10分钟后开启，请参战各方做好战前准备"));
				}else if (hour == 20 && (min == 0 || min == 1)) {	//开始战斗
					if (getSiegestate() == 0) {
						backtocitymove();
						startSiegeWar();
					}
				}else if (hour == 20 && min == 30) { //刷怪
					if (getMonstatus() == 0) {
						appearMonster();
					}
					
				}else if (hour == 21 &&  (min == 0 || min == 1) ){//战斗结束
					if (getSiegestate() == 1) {
						SiegeEnd(0);
					}
				}else if (hour == 21 && min == 2){//传送
					backtocitymove();
				}else if (hour == 23 && min >= 58) {
					setSiegestate(0);
				}
				
				if (hour == 20 && min >= 10 && min <=50 && min % 10 == 0) {
					RefreshMapItemDrop();
				}
				
			}
		}
	}

	/**攻城战开始初始化
	 * 
	 */
	public void startSiegeWar(){
		GuildServerManager.getInstance().reqInnerKingCityEventToWorld(null,2,getKingcity().getGuildid()+"");//删除BUFF
		log.error("攻城战开始前拥有王帮的帮会ID是："+getKingcity().getGuildid());
		
		try {
			CountryLog clog = new CountryLog();
			clog.setCountrydata(JSON.toJSONString(getKingcity(), SerializerFeature.WriteClassName));
			clog.setType(0);
			LogService.getInstance().execute(clog);
		} catch (Exception e) {
			log.error(e);
		}

		
		getKingcity().setGuildid(0);
		getKingcity().setGuildname("");
		getKingcity().setHoldplayerid(0);
		getKingcity().setHoldplayername("");
		getKingcity().setHoldplayersex(0);
		getKingcity().setHoldtime(0);
		setSiegestate(1);	//设置为战斗状态
		setMonstatus(0);	//清除刷怪标记
		setSiegecountdown(System.currentTimeMillis()/1000);	//记录开始时间

		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("王城攻城战已经开启，秦王玉玺已回收，请大家从国家面板-王城争霸处进入攻城战地图"));
		
		Map map = getSiegeMap();

		ReqCountrySiegeSelectToClientMessage cmsg = new ReqCountrySiegeSelectToClientMessage();
		MessageUtil.tell_world_message(cmsg);	//弹出面板询问是否参与//TODO 这里到攻城战时候要改成国家通知
		
		List<NPC> npclist = ManagerPool.npcManager.findNpc(map, getYuXiNpc());
		if (npclist.size() > 0) {
			ManagerPool.npcManager.showNpc(npclist.get(0));	//显示玉玺NPC
		}
		setdiaoxiang(false);
		SiegeSMSTopMap.clear();
	}
	
	
	/**GM测试开始攻城
	 * 
	 */
	public void testSiege(Player player){
		startSiegeWar();
		
		player.setKingcityrewtime(0);
		player.setKingcityexp(0);
		player.setKingcityzq(0);
		
		stcountryWarInfo(null,true);
	}
	
	
	/**GM测试结束攻城
	 * 
	 */
	public void testSiegeend(Player player){
		SiegeEnd(0);
	}
	
	
	
	/**
	 * 获取攻城战时间信息
	 * @param type = 0 国家面板显示内容。1=挑战面板显示
	 * @return
	 */
	public String getstrtimeinfo(int type){
		int day = TimeUtil.getOpenAreaDay();
		long curday = TimeUtil.getDayOfMonth(System.currentTimeMillis());
		if (day >= getOpenArea()) {
			long time = TimeUtil.getSoonWeek(6);
			long mday = TimeUtil.getDayOfMonth(time);
			long month = TimeUtil.getMonth(time)+1;
			if (curday == mday) {
				if (getSiegestate()==2) {
					if (type == 0) {
						return ResManager.getInstance().getString("今日攻城战已结束");
					}
				}else {
					if (type == 0) {
						return ResManager.getInstance().getString("今日晚20:00将开启攻城战");
					}
				}

			}else {
				if (type == 0) {
					return ResManager.getInstance().getString("攻城战时间：")+"\n"+month+ResManager.getInstance().getString("月")+mday+ResManager.getInstance().getString("日20:00");
				}
			}
		}else {
			int sday = getOpenArea() - day;
			long time = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),6);
			long mday = TimeUtil.getDayOfMonth(time);
			long month = TimeUtil.getMonth(time)+1;
			if (curday == mday) {
				if (getSiegestate()==2) {
					if (type == 0) {
						return ResManager.getInstance().getString("今日攻城战已结束");
					}
					
				}else {
					if (type == 0) {
						return ResManager.getInstance().getString("今日晚20:00将开启攻城战");
					}
				}
			}else {
				if (type == 0) {
					return ResManager.getInstance().getString("攻城战时间：")+"\n"+month+ResManager.getInstance().getString("月")+mday+ResManager.getInstance().getString("日20:00");
				}
			}
		}
		return "";
	}
	
	
	
	
	
	
	/**查看王城面板
	 * 
	 * @param parameter
	 */
	public void stReqCountryStructureInfoToGameMessage(Player player) {
		ReqCountryStructureInfoToWoridMessage wmsg = new ReqCountryStructureInfoToWoridMessage();
		wmsg.setPlayerid(player.getId());
//		int day=TimeUtil.getOpenAreaDay(player);	
//		long curday = TimeUtil.getDayOfMonth(System.currentTimeMillis());
		if(getSiegestate() == 0 || getSiegestate()==2){
//			if (day >= getOpenArea()) {
//				long time = TimeUtil.getSoonWeek(6);
//				long mday = TimeUtil.getDayOfMonth(time);
//				long month = TimeUtil.getMonth(time)+1;
//				if (curday == mday) {
//					if (getSiegestate()==2) {
//						wmsg.setSiegetime("今日的攻城战结束了");
//					}else {
//						wmsg.setSiegetime("今日晚20:00将开启攻城战");
//					}
//					
//				}else {
//					wmsg.setSiegetime("攻城战时间："+month+"月"+mday+"日20:00");
//				}
//			}else {
//				int sday = getOpenArea() - day;
//				long time = TimeUtil.getSoonWeek(System.currentTimeMillis()+(sday*24*60*60*1000),6);
//				long mday = TimeUtil.getDayOfMonth(time);
//				long month = TimeUtil.getMonth(time)+1;
//				if (curday == mday) {
//					if (getSiegestate()==2) {
//						wmsg.setSiegetime("今日的攻城战结束了");
//					}else {
//						wmsg.setSiegetime("今日晚20:00将开启攻城战");
//					}
//				}else {
//					wmsg.setSiegetime("攻城战时间："+month+"月"+mday+"日20:00");
//				}
//			}
			
			wmsg.setSiegetime(getstrtimeinfo(0));
			ResCountryJobAwardInfoToClientMessage jobmsg = new ResCountryJobAwardInfoToClientMessage();
			//TODO 帮主老婆现在未定
			for (int i = 1; i <= 6; i++) {
				JobAwardInfo jobinfo = new JobAwardInfo();
				jobinfo.setLevel(i);
				if (getKingcity().checkSalary(i)) {
					jobinfo.setStatus(1);
				}
				jobmsg.getDamageinfo().add(jobinfo);
			}
			MessageUtil.tell_player_message(player, jobmsg);
			
		}else if(getSiegestate() == 1){
			if(getKingcity().getHoldplayerid() > 0){
				Player holdplayer = ManagerPool.playerManager.getOnLinePlayer(getKingcity().getHoldplayerid());
				if (holdplayer != null) {
					String timestr = TimeUtil.GetTransformTime((System.currentTimeMillis()/1000)-getKingcity().getHoldtime());
					wmsg.setSiegetime(ResManager.getInstance().getString("秦王玉玺已被【")+holdplayer.getName()+ResManager.getInstance().getString("】持有")+timestr+"");
				}
			}else {
				String timestr = TimeUtil.GetTransformTime((getSiegecountdown()+60*60) - (System.currentTimeMillis()/1000)  );
				wmsg.setSiegetime(ResManager.getInstance().getString("攻城战结束剩余：")+timestr+"");
			}
		}
		wmsg.setGuildid(getKingcity().getGuildid());
		MessageUtil.send_to_world(wmsg);
	//	log.error(wmsg);
	}

	
	
	
	/**传送攻城战地图
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stResCountrySiegeSelectToGameMessage(Player player,ResCountrySiegeSelectToGameMessage msg) {
		Map map = ManagerPool.mapManager.getMap(player);
		if (msg.getType() == 1) {
			
			if (getSiegestate() != 1) {	//是否攻城期间
				return;
			}
			
			long day=TimeUtil.GetCurTimeInMin(4);	//清理奖励数据
			if(player.getKingcityrewday() != day ){
				player.setKingcityrewtime(0);
				player.setKingcityrewday((int) day);
				player.setKingcityexp(0);
				player.setKingcityzq(0);
			}
			
			if(map==null){
				log.error("server " + player.getServerId() + " line " + player.getLine() + " map " + player.getMap() + "  is null!");
			}
			//删除原来的死亡复活保护BUFF
			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
			//和平保护BUFF
			List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, Global.PROTECT_IN_SIEGE);
			if (buffs.size() == 0) {
				ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_IN_SIEGE, 0, 0, 0);
			}
			
			Position position = new Position();
			int x = 0;
			int y = 0;
			if (player.getGuildId() >0 && getKingcity().checkKingCity(player)) {	//防守方
				x=GUARD_XY[0];
				y=GUARD_XY[1];
//			}else if (player.getGuildId() > 0) {	//进攻者帮派
//				x=ATTACK_XY[0];
//				y=ATTACK_XY[1];
			}else {	//散人
				int rnd = RandomUtils.random(1, ALONE_XY.length)-1;
				x=ALONE_XY[rnd][0];
				y=ALONE_XY[rnd][1];
			}
			position.setX((short) (x*MapUtils.GRID_BORDER));
			position.setY((short) (y*MapUtils.GRID_BORDER));
			List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(position,2*MapUtils.GRID_BORDER ,SIEGE_MAPID);
			int rnd = RandomUtils.random(1, gridlist.size())-1;
			ManagerPool.mapManager.changeMap(player,SIEGE_MAPID,SIEGE_MAPID, 1, gridlist.get(rnd).getCenter(), this.getClass().getName() + ".stResCountrySiegeSelectToGameMessage 1");
			//PK模式 0-和平 1-组队 2-帮会 3-全体
			if (player.getGuildId() > 0 && player.getPkState() != 2 ) {
				ManagerPool.playerManager.changePkState(player, 2, 0);
			}else if (player.getGuildId() == 0 && player.getPkState() != 3 ) {
				ManagerPool.playerManager.changePkState(player, 3, 0);
			}
		}else if (msg.getType() == 2) {	
			//离开攻城战地图（回城）
			if (map.getMapModelid() == SIEGE_MAPID) {
				Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(SIEGE_MAPID);
				Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
				ManagerPool.playerManager.autoRevive(player);
				List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(position,15*MapUtils.GRID_BORDER , mapBean.getQ_map_quit());
				int rnd = RandomUtils.random(1, gridlist.size())-1;
				ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, gridlist.get(rnd).getCenter(), this.getClass().getName() + ".stResCountrySiegeSelectToGameMessage 2");
			}
		}
	}

	

	/**地图内传送到指定复活点
	 *守城方使用守方复活点，攻城方和中立方使用攻方复活点；
	 *在承玺台地图中其他地方被杀会在复活点重生，在复活点范围内被杀死则只能返回咸阳城重生；
	 */
	public void SiegeMoveMap(Player player){
		Position position = new Position();
		int x = 0;
		int y = 0;
		if (player.getGuildId() >0 && getKingcity().checkKingCity(player)) {	//防守方
			x=GUARD_XY[0];
			y=GUARD_XY[1];
		}else  {	//进攻者帮派,散人中立方复活点
			x=ATTACK_XY[0];
			y=ATTACK_XY[1];
		}
		position.setX((short) (x*MapUtils.GRID_BORDER));
		position.setY((short) (y*MapUtils.GRID_BORDER));
		double distance = MapUtils.countDistance(player.getPosition(), position);	//得到距离
		//和平保护BUFF
		ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_IN_SIEGE, 0, 0, 0);
		
		if (distance <= MapUtils.GRID_BORDER * 20 ) {	//复活点范围格子(复活后回城)
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("在攻城战复活点死亡，回到咸阳王城。"));
			//player.setAutohorse((byte) 1);
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(SIEGE_MAPID);
			Position cposition = ManagerPool.mapManager.RandomDieBackCity(mapBean);
			ManagerPool.playerManager.autoRevive(player);
			ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, cposition, this.getClass().getName() + ".SiegeMoveMap");
		}else {
			ManagerPool.mapManager.changePosition(player, position);
			
		}
	}
	
	
	/**攻城战结束处理
	 * type= 0正常结束，1持有20分钟结束
	 */
	public void SiegeEnd(int type) {
		setMonstatus(0);
		setSiegecountdown(0);
		
		KingCity kingCity = getKingcity();
		ResCountrySiegeWarStateToClientMessage cmsg =new ResCountrySiegeWarStateToClientMessage();
		cmsg.setState((byte) 0);
		MessageUtil.tell_world_message(cmsg);//TODO 这里到攻城战时候要改成国家通知
		
		if(kingCity.getGuildid() > 0){
			GuildTmpInfo guildTmpInfo = ManagerPool.guildServerManager.getGuildTmpInfo(kingCity.getGuildid());
			GuildServerManager.getInstance().reqInnerKingCityEventToWorld(null,2,getKingcity().getGuildid()+"");//给帮主王城BUFF
			
			String kingname = guildTmpInfo.getbangzhu(1).getMembername();
			long kingpid = guildTmpInfo.getbangzhu(1).getMemberid();

			if (type == 1) {
				MessageUtil.notify_All_player(Notifys.SROLL,ResManager.getInstance().getString("{1}帮会持有秦王玉玺时间超过20分钟，帮主【{2}】成为了新的王城之主。让我们一起来见证这个伟大的时刻！") , kingCity.getGuildname(), kingname);
			}else {
				MessageUtil.notify_All_player(Notifys.SROLL,ResManager.getInstance().getString("攻城结束时间已到，新的王城之主已经产生，他就是【{1}】帮会的帮主【{2}】，让我们一起来见证这个伟大的时刻！") , kingCity.getGuildname(), kingname);
			}
			
			addking(kingpid,kingname);
			kingCity.setHoldtime(0);
			kingCity.setHoldplayerid(kingpid);
			kingCity.setHoldplayername(kingname);
			
			Player player = ManagerPool.playerManager.getOnLinePlayer(guildTmpInfo.getbangzhu(1).getMemberid());
			if (player !=null) {
				ManagerPool.buffManager.removeByBuffId(player,  Global.PROTECT_IN_BANGZHU);
				kingCity.setHoldplayersex(player.getSex());
			}
			
			Player fuplayer = ManagerPool.playerManager.getOnLinePlayer(guildTmpInfo.getbangzhu(2).getMemberid());
			if (fuplayer != null) {
				ManagerPool.buffManager.removeByBuffId(fuplayer,  Global.PROTECT_IN_BANGZHU);
			}
			
			MessageUtil.notify_All_player(Notifys.SROLL,ResManager.getInstance().getString("今晚的攻城战已经结束，感谢大家的参与，让我们期待下一次的精彩战役") );
			log.error(String.format("%s帮会的帮主【%s】持有秦王玉玺时间超过20分钟，成为了新的王城之主,GID=%s", kingCity.getGuildname(), kingname,kingCity.getGuildid()+""));
			

		}else {
			MessageUtil.notify_All_player(Notifys.SROLL,ResManager.getInstance().getString("攻城结束时间已到，很遗憾秦王玉玺未被人持有，未来一段时间内王城之主位置将空悬。请大家积极准备下周的王城战，未来的秦王也许就是你！") );
			log.error("攻城结束时间已到，很遗憾秦王玉玺未被人持有");
		}
		
		setSiegestate(2);		//设置为战斗结束状态(发奖励需要)
		
		try {//储存最后攻城记录
			CountryLog clog = new CountryLog();
			clog.setCountrydata(JSON.toJSONString(kingCity, SerializerFeature.WriteClassName));
			clog.setType(2);
			LogService.getInstance().execute(clog);
		} catch (Exception e) {
			log.error(e);
		}
		savekingcity(kingCity);
		MessageUtil.notify_All_player(Notifys.SROLL,ResManager.getInstance().getString("参与攻城战所奖励的经验，将在10秒内自动结算，请务必保持在线。") );
		//清理怪物
		ManagerPool.monsterManager.removeMonster(getSiegeMap());
		setMovetime((System.currentTimeMillis()/1000) +60);	//60秒后人物全部回城
		setdiaoxiang(true);
	}
	
	
	/**储存王城信息
	 * 
	 * @param kingCity
	 */
	public void savekingcity(KingCity kingCity){
		try {
			int countryid = WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId());
			ServerParamUtil.threadSaveImportant(ServerParamUtil.KINGCITYWAR+countryid, JSON.toJSONString(kingCity, SerializerFeature.WriteClassName));
			ReqCountrySyncKingCityToWoridMessage wmsg = new ReqCountrySyncKingCityToWoridMessage();
			wmsg.setCountryid(countryid);
			wmsg.setGuildid(kingCity.getGuildid());
			MessageUtil.send_to_world(wmsg);
			log.error("发送王城储存消息:");
			log.error(wmsg);
		} catch (Exception e) {
			log.error("储存王城消息出错"+e);
		}
	}
	
	

	/**加下一任城主
	 * 
	 * @param playerid
	 * @param playername
	 */
	public synchronized void addking(long playerid, String playername){
		KingCity kingCity = getKingcity();
		KingData old = kingCity.gCurKingData();
		if (old ==null) {
			int renqi = kingCity.gKingDataKey();
			KingData kingdata = new KingData();
			kingdata.setPlayerid(playerid);
			kingdata.setPlayername(playername);
			kingdata.setTerm(renqi);
			kingdata.setReigntime(System.currentTimeMillis()/1000);
			kingCity.putKingData(kingdata);
		}else {
			if(old.getPlayerid() != playerid){
				int renqi = kingCity.gKingDataKey();
				KingData kingdata = new KingData();
				kingdata.setPlayerid(playerid);
				kingdata.setPlayername(playername);
				kingdata.setTerm(renqi);
				kingdata.setReigntime(System.currentTimeMillis()/1000);
				kingCity.putKingData(kingdata);
				old.setAbdicatetime(System.currentTimeMillis()/1000);	//上一任退位时间
			}
		}
	}
	

	
	/**点NPC获得玉玺
	 * 
	 */
	
	public void SiegeGrabYuXi(Player player){
		GuildTmpInfo guildTmpInfo = ManagerPool.guildServerManager.getGuildTmpInfo(player.getGuildId());
		String guildname = guildTmpInfo.getGuildname();
		getKingcity().setGuildid(player.getGuildId());
		getKingcity().setGuildname(guildname);
		getKingcity().setHoldplayerid(player.getId());
		getKingcity().setHoldtime((int) (System.currentTimeMillis()/1000));
		getKingcity().setHoldplayername(player.getName());
		stcountryWarInfo(null,true);
		if (player.getMemberInfo().getGuildPowerLevel() == 1) {
			MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("恭喜【{1}】帮会帮主【{2}】成功取得秦王玉玺"),guildname,player.getName());
			log.error(String.format("恭喜【%s】帮会帮主【%s】成功取得秦王玉玺,GID=%s", guildname,player.getName(),player.getGuildId()+""));
		}else if (player.getMemberInfo().getGuildPowerLevel() == 2) {
			MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("恭喜【{1}】帮会副帮主【{2}】成功取得秦王玉玺"),guildname,player.getName());
			log.error(String.format("恭喜【%s】帮会副帮主【%s】成功取得秦王玉玺,GID=%s", guildname,player.getName(),player.getGuildId()+""));
		}
		//CountryAwardManager.getInstance().setOtherKingCityBuff(player);
		ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_IN_BANGZHU, 0, 0, 0);
		CountryLog clog = new CountryLog();
		clog.setCountrydata(JSON.toJSONString(getKingcity(), SerializerFeature.WriteClassName));
		clog.setType(1);
		LogService.getInstance().execute(clog);
	}
	
	/**全服广播玉玺归位
	 * 
	 */  
	public void SiegeHomingYuXi(Player player ){
		KingCity kingCity = getKingcity();
		if (player.getId() == kingCity.getHoldplayerid() && getSiegestate() == 1) {
			Map map =getSiegeMap();
			List<NPC> npclist = ManagerPool.npcManager.findNpc(map, getYuXiNpc());
			if (npclist.size() > 0) {
				ManagerPool.npcManager.showNpc(npclist.get(0));
			}
			kingCity.setGuildid(0);
			kingCity.setGuildname("");
			kingCity.setHoldplayerid(0);
			kingCity.setHoldplayername("");
			kingCity.setHoldtime(0);
			ResCountrySiegeHomingYuxiToClientMessage cmsg = new ResCountrySiegeHomingYuxiToClientMessage();
			MessageUtil.tell_world_message(cmsg);
			stcountryWarInfo(player,true);
			//CountryAwardManager.getInstance().removeKingCityBuff(player);
			ManagerPool.buffManager.removeByBuffId(player,  Global.PROTECT_IN_BANGZHU);
		}
	}


	/**发送即时攻城消息
	 * 
	 * @param player boolean
	 * false 只对个人发送   ，true = 当前地图发送
	 */
	public void stcountryWarInfo(Player player ,boolean ismap){
		try {
			long systime = System.currentTimeMillis()/1000;
			ResCountrySiegeYuXiImmediateToClientMessage cmsg = new ResCountrySiegeYuXiImmediateToClientMessage();
			KingCity kingCity = getKingcity();
			CountryWarInfo countryWarInfo=new CountryWarInfo();
			
			long ms = ManagerPool.countryManager.getSiegecountdown();
			long downtime =  (ms+60*60) - systime;
			countryWarInfo.setWarendtime((int) downtime);//剩余时间
			
			if(kingCity.getHoldplayerid() > 0){
				Player holderplayer = ManagerPool.playerManager.getOnLinePlayer(kingCity.getHoldplayerid());
				countryWarInfo.setHolderid(kingCity.getHoldplayerid());
				countryWarInfo.setHoldertime((int) ( systime - kingCity.getHoldtime()));
				countryWarInfo.setHolderguildid(kingCity.getGuildid());
				countryWarInfo.setMx(holderplayer.getPosition().getX());
				countryWarInfo.setMy(holderplayer.getPosition().getY());
				countryWarInfo.setHoldername(holderplayer.getName());
			}
			cmsg.setCountrywarinfo(countryWarInfo);
			
			if (ismap) {
				Map map =getSiegeMap();
				MessageUtil.tell_map_message(map, cmsg);
			}else {
				if (player != null) {
					MessageUtil.tell_player_message(player, cmsg);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}
	}


	
	/**当前是否多倍经验。
	 * 
	 */
	public int getposexpmultiple(Player player){
		//攻城战获取当前格子多倍经验数量
		double x1 = 0;
		double y1 = 26;
		double x2 = 232;
		double y2 = 159;
		int x = player.getPosition().getX()/MapUtils.GRID_BORDER;
		int y = player.getPosition().getY()/MapUtils.GRID_BORDER;
		double value = (x2-x1) * (y-y1) - (y2-y1) * (x-x1);
		if (value > 0) {
			return 75;
		}else {
			return 150;
		}
	}
	
	
	/**在攻城战地图，并且是攻城时间,是否可打坐
	 * 
	 * @return true 可打坐，false不可打坐
	 */
	public boolean isSiegeWarandMap(Player player) {
		if	(getSiegestate() == 1 ){
			Map map = ManagerPool.mapManager.getMap(player);
			if (map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID) {
				return false;
			}
		}
		return true;
	}
	
	
	/**攻城战时间奖励
	 * 
	 */
	public void giveSiegeTimeReward(Player player){
		Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
		if (player.getKingcityrewtime() > 20*60 && player.getLevel() >= 40) {
			int exp = model.getQ_basis_exp()*player.getKingcityrewtime() /6;
			int zhenqi =  model.getQ_basis_zhenqi()*player.getKingcityrewtime()/6;
			ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.COUNTRY);
			ManagerPool.playerManager.addZhenqi(player,zhenqi,AttributeChangeReason.COUNTRY);
			player.setKingcityrewtime(0);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜获得攻城战时间奖励：经验{1},真气{2}"), exp+"",zhenqi+"");
		}
	}
	
	
	
	/**攻城战登录触发
	 * 
	 */
	public void siegeLoginHandle(Player player ){
		if(getSiegestate()==1){
			ResCountrySiegeWarStateToClientMessage cmsg = new  ResCountrySiegeWarStateToClientMessage();
			cmsg.setState((byte) 1);
			MessageUtil.tell_player_message(player, cmsg);
		}else {

			
		}
	}

	
	/**攻城车消息
	 * 1,弩箭攻城车：小范围高伤害的攻击利器，误差范围小；
	 * 2,巨型投石车：大范围低伤害的散射钝器，误差范围大；
	 * 3 超级弩箭攻城车 %10 体力清零
	 * 4 超级巨型投石车 %10内力清零
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqCountryWarCarToGameMessage(Player player,ReqCountryWarCarToGameMessage msg) {
		int money = 120000;
		if (msg.getType() == 1 || msg.getType() == 2) {
			 money =ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_MONEY.getValue()).getQ_int_value();
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.SIEGE_COOL,null) ) {
				return;
			}
		}else {
			 money =ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_SUPRE_MONEY.getValue()).getQ_int_value();
			if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.SIEGE_SUPER_COOL,null) ) {
				return;
			}
		}

		Q_npcBean npcdata = ManagerPool.dataManager.q_npcContainer.getMap().get(msg.getNpcid());
		Grid npcgrid = MapUtils.getGrid(npcdata.getQ_x(),npcdata.getQ_y(), SIEGE_MAPID);
		double dis = MapUtils.countDistance(npcgrid.getCenter(), player.getPosition());	//得到距离
		if (dis > 25 * MapUtils.GRID_BORDER) {
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("您离攻城车太远了，无法呼叫火力支援"));
			return;
		} 
		
		if (ManagerPool.backpackManager.changeMoney(player, -money, Reasons.KINGCITY_ZHANCHE, Config.getId()) == false) {
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("您没有{1}铜币，无法发射。"),money+"");
			return; 
		}
		
		Map map = getSiegeMap();
		if (msg.getType() == 1 || msg.getType() == 2) {
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.SIEGE_COOL,null,5*1000);	//冷却时间5秒
		}else {
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.SIEGE_SUPER_COOL,null,3*1000);	//冷却时间3秒
		}

		ResCountryArtilleryLocusToClientMessage gmsg = new ResCountryArtilleryLocusToClientMessage();
		gmsg.setEndx(msg.getX());
		gmsg.setEndy(msg.getY());
		gmsg.setPlayerid(player.getId());
		gmsg.setType(msg.getType());
		MessageUtil.tell_map_message(map, gmsg);
		String cname= ResManager.getInstance().getString("弩箭攻城车");
		int atk = 1000;		//攻击力  (默认值)
		int radius = 10;	//攻击范围
		if (msg.getType() == 1) {
			Q_globalBean data =ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_CRISSBOW.getValue());
			if (data != null) {
				String[] datstr = data.getQ_string_value().split(Symbol.SHUXIAN_REG);
				radius = Integer.parseInt(datstr[1]);//弩箭攻击范围
				atk = Integer.parseInt(datstr[0]);	//弩箭攻击力
			}
		}else if(msg.getType() == 2){
			Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_CATAPULTS.getValue());
			if (data != null) {
				String[] datstr = data.getQ_string_value().split(Symbol.SHUXIAN_REG);
				radius = Integer.parseInt(datstr[1]);//投石车攻击范围
				atk = Integer.parseInt(datstr[0]);	//投石车攻击力
				cname = ResManager.getInstance().getString("投石车");
			}
		}else if (msg.getType() == 3) {//超级弩箭（体力清零）
			Q_globalBean data =ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_SUPRE_CRISSBOW.getValue());
			if (data != null) {
				String[] datstr = data.getQ_string_value().split(Symbol.SHUXIAN_REG);
				radius = Integer.parseInt(datstr[1]);//超级弩箭攻击范围
				atk = Integer.parseInt(datstr[0]);	//超级弩箭攻击力
				cname = ResManager.getInstance().getString("超级弩箭攻城车");
			}
		}else if (msg.getType() == 4) {//超级投石车 （内力清零）
			Q_globalBean data =ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_SUPRE_CATAPULTS.getValue());
			if (data != null) {
				String[] datstr = data.getQ_string_value().split(Symbol.SHUXIAN_REG);
				radius = Integer.parseInt(datstr[1]);//超级投石车攻击范围
				atk = Integer.parseInt(datstr[0]);	////超级投石车攻击力
				cname = ResManager.getInstance().getString("超级投石车");
			}
		}else {
			return;
		}
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("花费{1}铜币，使用{2}进行攻击。"),money+"",cname);
		Grid grid = MapUtils.getGrid(msg.getX(),msg.getY(), map.getMapModelid());
		List<Player> players = getWarCharAround(grid.getCenter() ,radius);
		String injured = "";
		String death = "";
//			Player mirror = transferMirror(player, grid.getCenter());
//			if(mirror!=null){
//				TransferMirrorBackEvent event = new TransferMirrorBackEvent(mirror, player);
//				TimerUtil.addTimerEvent(event);
//			}
		
		ResCountryWarCarDamageToClientMessage dmsg = new ResCountryWarCarDamageToClientMessage();
		for (Player beattplayer : players) {
			if (beattplayer.getName() != null && beattplayer.getId() != player.getId() && ( beattplayer.getGuildId() == 0 || (beattplayer.getGuildId() > 0 && beattplayer.getGuildId() != player.getGuildId()))) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(beattplayer, Global.PROTECT_IN_SIEGE);
				if (buffs.size() == 0) {
					double distance = MapUtils.countDistance(grid.getCenter(), beattplayer.getPosition());	//得到距离
					int att = (int) Math.ceil(distance /MapUtils.GRID_BORDER);//距离/25像素=格子距离
					double s = ((double)(radius-att)/(double)radius);
					if (s > 0) {
						int sum = (int)(atk*s);
						if (beattplayer.getHp() > sum) {
							injured = beattplayer.getName()+","+injured;
							beattplayer.setHp(beattplayer.getHp() - sum);
							ManagerPool.playerManager.onHpChange(beattplayer);
							beattplayer.setState(PlayerState.FIGHT);	//进入战斗状态
							beattplayer.setLastFightTime(System.currentTimeMillis());
							MessageUtil.notify_player(beattplayer, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您被【{1}】发射的{2}命中，损失生命：{3}"), player.getName(),cname,sum+"");
							if (msg.getType() == 3) {// 超级弩箭（体力清零）
								ManagerPool.buffManager.addBuff(beattplayer, beattplayer, 24016, 0, 0, 0);
							}else if (msg.getType() == 4) {// 超级投石车 （内力清零）
								ManagerPool.buffManager.addBuff(beattplayer, beattplayer, 24017, 0, 0, 0);
							}
						}else {
							death = beattplayer.getName()+","+death;
							beattplayer.setHp(0);
							ManagerPool.playerManager.onHpChange(beattplayer);
							ManagerPool.playerManager.die(beattplayer,player);
							MessageUtil.notify_player(beattplayer, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您被【{1}】发射的{2}命中后死亡。"), player.getName(),cname);
						}
						MessageUtil.tell_round_message(beattplayer, ManagerPool.fightManager.getAttackResultMessage(0, player, beattplayer, new Skill(), 0, sum, 0, 0));
					}
				}
			}
		}
		MessageUtil.tell_player_message(player, dmsg);
		
		if  (!injured.equals("") && injured.length() > 4 && !death.equals("") && death.length() > 4) {
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：{3},杀死玩家：{4}"),msg.getX()+"",msg.getY()+"",injured,death);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：{3},杀死玩家：{4}"),msg.getX()+"",msg.getY()+"",injured,death);
		}else {
			if (!injured.equals("")) {
				MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：{3}，杀死玩家：无"),msg.getX()+"",msg.getY()+"",injured);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：{3}，杀死玩家：无"),msg.getX()+"",msg.getY()+"",injured);
			}else if (!death.equals("")) {
				MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：无，杀死玩家：{3}"),msg.getX()+"",msg.getY()+"",death);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，重伤玩家：无，杀死玩家：{3}"),msg.getX()+"",msg.getY()+"",death);
			}else {
				MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("攻城车成功发射，落至{1},{2}点，但是什么也没打中。"),msg.getX()+"",msg.getY()+"");
			}
		}
	}
	
	
	/**提前把玩家镜像放到目标地点
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqCountryWarCarInAdvanceToGameMessage(Player player,ReqCountryWarCarInAdvanceToGameMessage msg) {
		Map map = getSiegeMap();
		Grid grid = MapUtils.getGrid(msg.getX(),msg.getY(), map.getMapModelid());
		Player mirror = transferMirror(player, grid.getCenter());
		if(mirror!=null){
			TransferMirrorBackEvent event = new TransferMirrorBackEvent(mirror, player);
			TimerUtil.addTimerEvent(event);
		}
	}

	
	/**设置王城帮主雕像信息
	 * 
	 * @param player
	 */
	public void setKingdiaoxoang(Player player){
		KingCity kdata = getKingcity();
		if(getSiegestate() != 1){
			if(kdata.getGuildid() > 0 && kdata.getGuildid() == player.getGuildId() ){
				if (player.getId() != kdata.getHoldplayerid()) {
					kdata.setHoldplayersex(player.getSex());
					kdata.setHoldplayerid(player.getId());
					kdata.setHoldplayername(player.getName());
					savekingcity(kdata);
					setdiaoxiang(true);
				}
			}
		}
	}
	
	
	
	
	
	
	
	
//	/**攻击目标详细情况
//	 * 
//	 * @param player
//	 * @return
//	 */
//	private PlayerDamageInfo getPlayerDamageInfo(Player player){
//		PlayerDamageInfo info = new PlayerDamageInfo();
//		info.setPersonId(player.getId());
//		info.setName(player.getName());
//		info.setSex(player.getSex());
//		info.setAvatar(player.getAvatarid());
//		info.setX(player.getPosition().getX());
//		info.setY(player.getPosition().getY());
//		info.setState(player.getState());
//		info.setHp(player.getHp());
//		info.setMaxHp(player.getMaxHp());
//		info.setDir(player.getDirection());
//		info.setGuild(player.getGuildId());
//		if (player.getEquips()[0] != null) {
//			info.setWeapon(player.getEquips()[0].getItemModelId());
//		}
//		if (player.getEquips()[1] != null) {
//			info.setArmor(player.getEquips()[1].getItemModelId());
//		}
//		Horse horse = ManagerPool.horseManager.getHorse(player);
//		if (horse != null && horse.getStatus() == 1) {	//骑马状态
//			info.setHorseid(horse.getCurlayer());
//		}
//		return info;
//	}
//	
	
	
	/**获取坐标范围内玩家
	 * 
	 * @param position
	 * @param radius
	 * @return
	 */
	public List<Player> getWarCharAround(Position position, int radius) {
		Map map =getSiegeMap();
		if (map != null) {
			List<Area> round = MapManager.getInstance().getRound(map, position);
			if (round != null) {
				List<Player> playertab = new ArrayList<Player>();
				for (Area area : round) {
					HashSet<Player> players = area.getPlayers();
					if (players != null && players.size() > 0) {
						Iterator<Player> it = players.iterator();
						while (it.hasNext()) {
							Player splayer = (Player) it.next();
							if (!splayer.isDie() ) {
								double distance = MapUtils.countDistance(position, splayer.getPosition());	//得到距离
								if (distance <= MapUtils.GRID_BORDER * radius) {
									playertab.add(splayer);
								}
							}
						}
					}
				}
				return playertab;
			}
		}
		return null;
	}
	
	
	
	
	/**攻城中刷怪
	 * 
	 */
	
	public void appearMonster(){
		setMonstatus(1);
		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("神级BOSS【吕不韦】携带残余叛军进攻承玺台，请豪侠们速速前往击杀，可获得道具及装备奖励"));
		Map map = getSiegeMap();
		String xystr = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_BRUSH_XY.getValue()).getQ_string_value();
		String monstr = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_MON.getValue()).getQ_string_value();
		List<Integer[]> xyList = JSON.parseArray(xystr, Integer[].class);
		List<Integer[]> monList = JSON.parseArray(monstr, Integer[].class);
		
		int rnd = RandomUtils.random(1, xyList.size())-1;
		Integer[] xydata= xyList.remove(rnd);
		Position position = new Position();
		position.setX((short) (xydata[0]*MapUtils.GRID_BORDER));
		position.setY((short) (xydata[1]*MapUtils.GRID_BORDER));
		Integer[] monboss = monList.remove(0);
		ManagerPool.monsterManager.createMonsterAndEnterMap(monboss[0], map.getServerId(), map.getLineId(), (int)map.getId(),position);
		
		List<Integer> mons = new ArrayList<Integer>();
		for (Integer[] mon : monList) {
			for (int i = 0; i < mon[1]; i++) {
				mons.add(mon[0]);
			}
		}
		
		for (int i = 0; i <25; i++) {//打乱顺序
			int idx = RandomUtils.random(1,mons.size()) - 1;
			int s = mons.remove(idx);
			mons.add(s);
		}

		int num = 0;
		for (int i = 0; i < mons.size(); i++) {
			if (xyList.size() <= num ) {
				num = 0;
			}
			Integer[] xydb = xyList.get(num);
			num=num+1;
			Position xposition = new Position();
			xposition.setX((short) (xydb[0]*MapUtils.GRID_BORDER));
			xposition.setY((short) (xydb[1]*MapUtils.GRID_BORDER));
			ManagerPool.monsterManager.createMonsterAndEnterMap(mons.get(i), map.getServerId(), map.getLineId(), (int)map.getId(),xposition);
		}
	}
	
	
	/**传送回城
	 * 
	 */
	public void backtocitymove(){
		try {
			Map map = getSiegeMap();
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(SIEGE_MAPID);
			if(map.getPlayerNumber() > 0){
				HashMap<Long, Player> players = map.getPlayers();
				List<Player> playerslist=new ArrayList<Player>();
				playerslist.addAll(players.values());
				for (Player player :playerslist) {
					Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
					ManagerPool.playerManager.autoRevive(player);
					ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, position, this.getClass().getName() + ".backtocitymove");
				}
				playerslist = null;
			}
		} catch (Exception e) {
			log.error(e);
		}

	}
	


	public long getMovetime() {
		return movetime;
	}

	public void setMovetime(long movetime) {
		this.movetime = movetime;
	}
	
//	/**内测王帮奖励标记
//	 * 
//	 */
//	public void setkingguildReward(){
//		long gid = getKingcity().getGuildid();
//		if (gid > 0) {
//			Map map = getSiegeMap();
//			HashMap<Long, Player> players = map.getPlayers();
//			List<Player> playerslist=new ArrayList<Player>();
//			playerslist.addAll(players.values());
//			for (Player player :playerslist) {
//				if ( player.getGuildId() == gid) {
//					player.getActivitiesReward().put("REWARDLORDGUILD", ""+TimeUtil.GetCurTimeInMin(4));
//				}
//			}
//		}
//	}

	
	
	

	/**随机刷道具
	 * 
	 * @return
	 */
	public void RefreshMapItemDrop() {
		Map map = getSiegeMap();
		String xystr = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_ITEM_XY.getValue()).getQ_string_value();
		String itemstr = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SIEGE_ITEM.getValue()).getQ_string_value();
		List<Integer[]> xyList = JSON.parseArray(xystr, Integer[].class);
		List<Integer[]> itemList = JSON.parseArray(itemstr, Integer[].class);
		
		for (int i = 0; i < 10; i++) {
			int idx = RandomUtils.random(1,xyList.size()) - 1;
			Integer[] xy = xyList.remove(idx);
			int itemidx = RandomUtils.random(1,itemList.size()) - 1;
			List<Item> items = Item.createItems(itemList.get(itemidx)[0], itemList.get(itemidx)[1], true, 0);
			if (!items.isEmpty()) {
				Item item = items.get(0);
				DropGoodsInfo info = new DropGoodsInfo();
				info.setDropGoodsId(item.getId());
				info.setItemModelId(item.getItemModelId());
				info.setNum(item.getNum());
				Grid grid = MapUtils.getGrid(xy[0], xy[1], map.getMapModelid());
				info.setX(grid.getCenter().getX());
				info.setY(grid.getCenter().getY());
				info.setDropTime(System.currentTimeMillis());
				item.setGridId(0);

				MapDropInfo mapDropInfo = new MapDropInfo(info, item, map, System.currentTimeMillis() + 60*5 * 1000);
				ManagerPool.mapManager.enterMap(mapDropInfo);
			}
		}
	}
	
	/**
	 * 传送镜像到指定坐标
	 * @param player
	 * @param pos
	 * @return
	 */
	public Player transferMirror(Player player, Position pos){
		Player mirror = new Player();
		mirror.setId(player.getId());
		mirror.setShow(false);
		mirror.setPosition(pos);
		mirror.setLocate(player.getLocate());
		mirror.setLine(player.getLine());
		mirror.setMap(player.getMap());
		mirror.setMapModelId(player.getMapModelId());
		mirror.setGateId(player.getGateId());
		
		Map map = ManagerPool.mapManager.getMap(player);
		if (map == null) {
			log.error("mirror entermap player " + player.getId() + " server "
				+ player.getServerId() + " line " + player.getLine()
				+ " map " + player.getMap() + " not found!");
			return null;
		}

		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());

		int oldAreaId = ManagerPool.mapManager.getAreaId(player.getPosition());
		int newAreaId = ManagerPool.mapManager.getAreaId(pos);
		
		Area newarea = map.getAreas().get(newAreaId);
		if (newarea == null) {
			log.error("mirror player " + player.getId() + " server "
				+ player.getServerId() + " line " + player.getLine()
				+ " map " + player.getMap() + " area " + newAreaId + " not found, position" + player.getPosition());
			return null;
		}

		newarea.getPlayers().add(mirror);
		
		List<Area> oldRound = ManagerPool.mapManager.getRoundAreas(map, oldAreaId);
		List<Area> newRound = ManagerPool.mapManager.getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
//			System.out.println("oldArea:" + oldRound.get(i).getId());
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
//			System.out.println("newArea" + newRound.get(i).getId());
			newSet.add(newRound.get(i));
		}

		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();

		for (int i = 0; i < newRound.size(); i++) {
			Area area = newRound.get(i);
			if (oldSet.contains(area)) {
				continue;
			}

			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (other.getId() == player.getId()) {
					continue;
				}
				if (other.canSee(player)) {
					msg.getPlayer().add(ManagerPool.mapManager.getPlayerInfo(other, grids));
				}
			}

			Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
			while (monster_iter.hasNext()) {
				Monster monster = (Monster) monster_iter.next();

				if (monster.canSee(player)) {
					msg.getMonster().add(ManagerPool.mapManager.getMonsterInfo(monster, grids));
				}
			}

			Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
			while (iterator.hasNext()) {
				MapDropInfo next = iterator.next();

				if (next.canSee(player)) {
					msg.getGoods().add(next.getDropinfo());
				}
			}

			HashSet<Pet> petset = area.getPets();
			for (Pet pet : petset) {
				if (pet.canSee(player)) {
					msg.getPets().add(ManagerPool.mapManager.getPetInfo(pet, grids));
				}
			}

			Iterator<NPC> npciterator = area.getNpcs().values().iterator();
			while (npciterator.hasNext()) {
				NPC npc = (NPC) npciterator.next();
				if (npc.canSee(player)) {
					msg.getNpcs().add(ManagerPool.mapManager.getNpcInfo(npc));
				}
			}

			Iterator<Effect> effectiterator = area.getEffects().values().iterator();
			while (effectiterator.hasNext()) {
				Effect effect = (Effect) effectiterator.next();
				if (effect.canSee(player)) {
					msg.getEffect().add(ManagerPool.mapManager.getEffectInfo(effect));
				}
			}
		}

		MessageUtil.tell_player_message(player, msg);

		return mirror;
	}
	
	/**
	 * 传送镜像到指定坐标
	 * @param player
	 * @param pos
	 * @return
	 */
	public void transferMirrorBack(Player mirror, Position pos){

		Map map = ManagerPool.mapManager.getMap(mirror);
		if (map == null) {
			log.error("mirror quitmap player " + mirror.getId() + " server "
				+ mirror.getServerId() + " line " + mirror.getLine()
				+ " map " + mirror.getMap() + " not found!");
			return;
		}

		int oldAreaId = ManagerPool.mapManager.getAreaId(mirror.getPosition());
		int newAreaId = ManagerPool.mapManager.getAreaId(pos);
		
		Area oldarea = map.getAreas().get(oldAreaId);
		if (oldarea == null) {
			log.error("mirror player " + mirror.getId() + " server "
				+ mirror.getServerId() + " line " + mirror.getLine()
				+ " map " + mirror.getMap() + " area " + oldAreaId + " not found, position" + mirror.getPosition());
			return;
		}

		boolean result = oldarea.getPlayers().remove(mirror);
		if(!result){
			log.error("no contain mirror！");
		}
		
		List<Area> oldRound = ManagerPool.mapManager.getRoundAreas(map, oldAreaId);
		List<Area> newRound = ManagerPool.mapManager.getRoundAreas(map, newAreaId);

		HashSet<Area> oldSet = new HashSet<Area>();
		for (int i = 0; i < oldRound.size(); i++) {
//			System.out.println("oldArea:" + oldRound.get(i).getId());
			oldSet.add(oldRound.get(i));
		}

		HashSet<Area> newSet = new HashSet<Area>();
		for (int i = 0; i < newRound.size(); i++) {
//			System.out.println("newArea" + newRound.get(i).getId());
			newSet.add(newRound.get(i));
		}

		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();

		for (int i = 0; i < oldRound.size(); i++) {
			Area area = oldRound.get(i);
			if (newSet.contains(area)) {
				continue;
			}

			Iterator<Player> iter = area.getPlayers().iterator();
			while (iter.hasNext()) {
				Player other = (Player) iter.next();
				if (other.getId() == mirror.getId()) {
					continue;
				}
				if (other.canSee(mirror)) {
					msg.getPlayerIds().add(other.getId());
				}
			}

			Iterator<Monster> monster_iter = area.getMonsters().values().iterator();
			while (monster_iter.hasNext()) {
				Monster monster = (Monster) monster_iter.next();

				if (monster.canSee(mirror)) {
					msg.getMonstersIds().add(monster.getId());
				}
			}

			Iterator<MapDropInfo> iterator = area.getDropGoods().values().iterator();
			while (iterator.hasNext()) {
				MapDropInfo next = iterator.next();

				if (next.canSee(mirror)) {
					msg.getGoodsIds().add(next.getId());
				}
			}

			HashSet<Pet> petset = area.getPets();
			for (Pet pet : petset) {
				if (pet.canSee(mirror)) {
					msg.getPetIds().add(pet.getId());
				}
			}

			Iterator<NPC> npciterator = area.getNpcs().values().iterator();
			while (npciterator.hasNext()) {
				NPC npc = (NPC) npciterator.next();
				if (npc.canSee(mirror)) {
					msg.getNpcid().add(npc.getId());
				}
			}

			Iterator<Effect> effectiterator = area.getEffects().values().iterator();
			while (effectiterator.hasNext()) {
				Effect effect = (Effect) effectiterator.next();
				if (effect.canSee(mirror)) {
					msg.getEffectid().add(effect.getId());
				}
			}
		}

		MessageUtil.tell_player_message(mirror, msg);

	}



	public int getOpenArea() {
		return OpenArea;
	}

	public void setOpenArea(int openArea) {
		OpenArea = openArea;
	}


	
	/**10条线全部 展示或者隐藏雕像
	 * 
	 * @param isshow 
	 * true展示
	 */
	public void setdiaoxiang(boolean isshow ) {
		int sid = WServer.getInstance().getServerId();
		setdiaoxiang(isshow,sid);
	}
	
	/**10条线全部 展示或者隐藏雕像
	 * 
	 * @param isshow 
	 * true展示
	 */
	public void setdiaoxiang(boolean isshow,int sid) {
		try {
			for (int i = 1; i <= 10; i++) {
				Map map = ManagerPool.mapManager.getMap(sid , i, 20002);
				if (map != null) {
					if (isshow) {
						map.getParameters().put(WANGCHENGDIAOXIANG, 1);
					}else {
						map.getParameters().put(WANGCHENGDIAOXIANG, 2);
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		
	}

	
	
	/**是否启用新的攻城战和领地战时间
	 * 开区时间在20121110之前使用原来的时间false，
	 * 之后使用新时间true
	 * @return
	 */
	public boolean stSiegeIntervalDay(int sid){
		try {
			Date date = WServer.getGameConfig().getServerTimeByServer(sid);
			if (date != null) {	
				long time = date.getTime();
				int year = TimeUtil.getYear(time);
				int month= TimeUtil.getMonth(time)+1;
				int day= TimeUtil.getDayOfMonth(time);
				int sday = year * 10000 + month * 100 + day;
				if (sday >= 20121110) {
					return true;
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		setOpenArea(4);//攻城战距离开区间隔天数
		ManagerPool.guildFlagManager.setOpenArea(1);//领地战战距离开区间隔天数
		return false;
	}

	
	//--------------------------------------------------------------------------
	
	/**改变攻城战 排行榜数据
	 * 
	 */
	public void changeSiegeSMSTopData(Player player ,int type,int num ){
		SiegeSMS siegeSMS = null;
		if (SiegeSMSTopMap.containsKey(player.getId())) {
			 siegeSMS = SiegeSMSTopMap.get(player.getId());
		}else {
			 siegeSMS = new SiegeSMS(player);
			 SiegeSMSTopMap.put(player.getId(), siegeSMS);
		}
		
		if (type == KILL) {	//杀敌
			siegeSMS.setKill(siegeSMS.getKill() + num );
		}else if (type == DEATH) {//死亡
			siegeSMS.setDeath(siegeSMS.getDeath() + num );
		}else if (type == HURT) {//伤害输出
			siegeSMS.setHurt(siegeSMS.getHurt() + num );
		}else if (type == BEENHURT) {//被伤害
			siegeSMS.setBeenhurt(siegeSMS.getBeenhurt() + num );
		}
	}
	
	
	
	/**获取攻城战排行榜
	 * @return 
	 * 
	 */
	public List<CountryTopInfo> getSiegeSMStopinfo(Player player ,int num  ){
		List<CountryTopInfo> infos = new ArrayList<CountryTopInfo>();
		try {
			List<SiegeSMS> toplist=new ArrayList<SiegeSMS>();
			if(SiegeSMSTopMap.size() > 0 ){
				toplist.addAll(SiegeSMSTopMap.values());
				Collections.sort(toplist, new SiegeSMSSort());
				for (int i = 0; i < toplist.size(); i++) {
					SiegeSMS top = toplist.get(i);
					if (i >= num){
						if (player == null ) {
							break;
						}else if(top.getPlayerid() == player.getId()) {
							CountryTopInfo info = top.getinfo();
							info.setRanking(i+1);
							infos.add(info);
							break;
						}
					}else {
						CountryTopInfo info = top.getinfo();
						info.setRanking(i+1);
						infos.add(info);
					}
				}
			}
		} catch (Exception e) {
			log.error("攻城战排行榜："+e,e);
		}
		return infos;
	}

	
	
	/**给玩家发送排行榜消息
	 * 
	 * @param player
	 */
	public void stReqCountryOpenTopToGameMessage(Player player) {
		ResCountryTopInfoToClientMessage cmsg = new ResCountryTopInfoToClientMessage();
		List<CountryTopInfo> list=getSiegeSMStopinfo(player,10);
		cmsg.setCountryTopInfolist(list);
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	/**地图广播排行榜
	 * 
	 */
	public void mapbroadcastTop(){
		ResCountryTopInfoToClientMessage cmsg = new ResCountryTopInfoToClientMessage();
		List<CountryTopInfo> list = getSiegeSMStopinfo(null,10);
		cmsg.setCountryTopInfolist(list);
		MessageUtil.tell_map_message(getSiegeMap(), cmsg);
	}
	

	
	
}


//1.优先排净胜数，净胜数越大排名越靠前（净胜数=击杀人数-被击杀数）
//2.净胜数相同情况下按等级排序，等级高的在前面
//3.净胜数相同等级相同的情况下，按用户ID排序，用户ID较小的排在前面

class SiegeSMSSort implements Comparator<SiegeSMS> {
	public int compare(SiegeSMS arg0, SiegeSMS arg1) {
		if (arg0.getKill() - arg0.getDeath() < arg1.getKill() - arg1.getDeath()) {
			return 1;
		}
		if (arg0.getKill() - arg0.getDeath() == arg1.getKill() - arg1.getDeath()) {
			if (arg0.getPlayerlevel() < arg1.getPlayerlevel()) {
				return 1;
			}
			
			if (arg0.getPlayerlevel() == arg1.getPlayerlevel()) {
				if (arg0.getPlayerid() > arg1.getPlayerid()) {
					return 1;
				}
			}
		}
		return 0 ;	  
	 }
}


