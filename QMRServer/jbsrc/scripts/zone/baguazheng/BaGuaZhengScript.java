package scripts.zone.baguazheng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import scripts.zone.CreateZoneScript;

import com.alibaba.fastjson.JSON;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_monsterBean;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IEnterTeleporterScript;
import com.game.map.structs.Map;
import com.game.message.Message;
import com.game.monster.structs.Monster;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.server.script.IServerEventTimerScript;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.manager.ZonesTeamManager;
import com.game.zones.message.ResZoneTeamOpenBullToClientMessage;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ZoneContext;

/**八卦阵
 * 
 * @author zhangrong
 *
 */

public class BaGuaZhengScript  implements IServerEventTimerScript,ICreateZoneScript ,IEnterTeleporterScript , IEnterMapScript, IZoneEventTimerScript{
	private Logger log = Logger.getLogger(BaGuaZhengScript.class);
	@Override
	public int getId() {
		return 4008;
	}
	
	public BaGuaZhengScript(){
		
		setentrance();
	}
	
	public static int zonemodelid = 3002;
	//传送到达坐标，对应8个传送点
	private int[][] entrxy = {{54, 105}, {5, 90}, {9, 62}, {20,36},{59, 29}, {100, 37}, {111, 64}, {105, 93}};
	
	//走到错误入口时，传送 几率，事件
	private int[][] rndmove = {{50, -1}, {15, -2}, {15, -3}, {10, 0},{10, 3}};
	
	//刷新盒子几率，非互斥   几率 ，id，数量+2随机
	private int[][] refreshBox = {{10, 11068,5}, {10, 11069,4}, {10, 11070,3}};
	
	
	private  int[][] monxy = {{56,50},{66,50},{49,51},{42,55},{38,58},{38,63},{42,67},{49,71},{56,72},
			{66,72},{74,71},{81,67},{84,63},{84,58},{80,55},
			{73,51},{69,57},{61,55},{51,56},{47,60},{70,64},{74,60},{51,65},{61,66},{55,58},
			{65,58},{55,63},{65,63},{58,61},{62,61},};
	
	//资源ID
	private int[] resmodelid={140,160,170,180,190,200,220,230,240,241,250,270,280,290,310,320,330,350,360,370,410,420,430,450,460,470,480,490,500,510,520,530,550,560,570,590,600,610,620,640,650,660,670};
	
	/**获取特定等级怪物
	 * 
	 * @param level
	 * @param type == 2 取BOSS 
	 * @return
	 */

	public Q_monsterBean  getMobsBean(int level,int type ){
		int kaishi = 11001;
		int jieshu = 11066;
		if (type == 1) {
			if (level > 105) {
				level = 105;
			}else if (level < 40) {
				level = 40;
			}
		}else if (type == 2) {
			if (level > 120) {
				level = 120;
			}else if (level < 60) {
				level = 60;
			}
		}
		
		if (type ==2) {	//BOSS
			kaishi=11071;	
			jieshu=11151;
			
		}
		
		for (int i = kaishi; i <= jieshu; i++) {
			Q_monsterBean mondb = ManagerPool.dataManager.q_monsterContainer.getMap().get(i);
			if (mondb != null) {
				if(mondb.getQ_grade() >= level ){
					return mondb;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	
	/**创建副本
	 * 
	 */
	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		if (zonemodelid != zoneId) {
			return null;
		}
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneId);
		HashMap<String, Object> others = new HashMap<String, Object>();
		others.put("time", (int)(System.currentTimeMillis()/1000));	//开始时间
		others.put("zonetype",zonedata.getQ_zone_type());			//副本类型 3
		others.put("teamtype",1);		//队伍类型，0，单人，1组队
		List<Integer> mapidlist = JSON.parseArray(zonedata.getQ_mapid(),Integer.class);
		ZoneContext zone =ManagerPool.zonesManager.setZone("八卦阵",others,mapidlist,zonedata.getQ_id());	//创建副本，返回副本消息

		return zone;
	}

	

	

	
	

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}


	/**进入地图
	 * 
	 */
	@Override
	public void onEnterMap(Player player, Map map) {
		if(map.getZoneModelId() == zonemodelid && map.getZoneId() > 0 ){
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone == null) {
				return;
			}
			int layers = ManagerPool.zonesManager.getFloor(player);
			if ( player.getTransType() == 1) {
				notify_zone_player(map.getZoneId(),Notifys.CUTOUT,ResManager.getInstance().getString("恭喜【{1}】前进至第{2}层"),player.getName(),(layers+1)+"");
				int monid = refreshBox(player,map) ;
				if (monid > 0) {
					notify_zone_player(map.getZoneId(),Notifys.CUTOUT,ResManager.getInstance().getString("【{1}】幸运的踩中了机关，刷新出：【{2}】"),player.getName(),ManagerPool.monsterManager.getName(monid));	
				}
	
				if (zone.getConfigs().get(7).getMapModelId() == map.getMapModelid()) {
					if ( zone.getOthers().containsKey("boss_isdie")) {
						triggerReward( player,map.getMapModelid() ,zone.getId());
						setClearance(player,map.getZoneModelId());
					}else {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您来到最终层，击败守将即可破阵，BOSS难度较高，您不妨在此等待其他玩家一起"));
					}
				}else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您进入正确入口，来到了第{1}层"), (layers+1)+"");
				}
			}
			//-----------------副本追踪面板
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("zonemodelid", BaGuaZhengScript.zonemodelid);
			paramMap.put("layers", layers+1);
			int ms = (int)(System.currentTimeMillis()/1000);
			int yms = (Integer)zone.getOthers().get("time");
			paramMap.put("time", ms - yms);
			if (zone.getOthers().containsKey("boss_isdie")) {
				paramMap.put("bossdie", 1);
			}
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(getId());
			sendMessage.setType(1);
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_player_message(player, sendMessage);	
			
			
			//----------------------------------初始化刷怪
			if( !zone.getOthers().containsKey("initialize")){
				zone.getOthers().put("initialize", 1);
				@SuppressWarnings("unchecked")
				List<Integer> levellist = (List<Integer>) zone.getOthers().get("levellist");
				if (levellist.size() > 0) {
					
					Object[] tab = levellist.toArray();
					//从高到低排序
					for (int i = tab.length - 1; i > 0; i--) {
						for (int j = 0; j < i; j++) {
							if ((Integer)tab[j] < (Integer)tab[j + 1]) {
								int tmp1 = (Integer)tab[j];
								tab[j] = tab[j + 1];
								tab[j + 1] = tmp1;
							}
						}
					}
					
					int lowest =  (Integer) tab[tab.length-1];	//最低等级
					int highest = (Integer) tab[0];//最高等级
					int average = 0;			//平均等级
					for (int i : levellist) {
						average = average + i;
					}
					average  = average/levellist.size();
					
					for (int i = 0; i < zone.getConfigs().size(); i++) {
						MapConfig mapConfig = zone.getConfigs().get(i);
						Map	gmap=ManagerPool.mapManager.getMap(mapConfig.getServerId(), mapConfig.getLineId(), mapConfig.getMapId());
						int level = 0;
						if (i <= 1) {
							level = lowest;
						}else if (i > 1 && i<= 3) {
							level = average;
						}else if (i > 3 && i<= 5) {
							level = highest;
						}else if (i==6) {
							level = highest+5;
						}else if (i==7) {	//刷BOSS
							Q_monsterBean bossbean = getMobsBean(highest + 20 , 2);
							if (bossbean != null) {
								Position position = new Position();
								position.setX((short) (60*MapUtils.GRID_BORDER));
								position.setY((short) (60*MapUtils.GRID_BORDER));
								Monster bossmonster = ManagerPool.monsterManager.createMonsterAndEnterMap(bossbean.getQ_id(), gmap.getServerId(),  gmap.getLineId(),  (int)gmap.getId(), position);
								bossmonster.getParameters().put("bgzbossdie", gmap.getZoneId());
								try {
									log.error(player.getName()+",八卦阵"+bossmonster.getModelId() + ",地图唯一ID："+gmap.getId()+",地图模版ID："+gmap.getMapModelid());
								} catch (Exception e) {
									log.error(e,e);
								}
							}
						}
						
						//策划说如果里面小怪不能复活，就不刷怪
//						if (level > 0) {//刷小怪
//							if (gmap != null) {
//								Q_monsterBean mondb = getMobsBean(level,1);
//								if (mondb != null) {
//									int idx = RandomUtils.random(resmodelid.length);	//随机造型
//									Q_monsterBean rnddb = ManagerPool.dataManager.q_monsterContainer.getMap().get(resmodelid[idx]);
//									if (rnddb != null) {
//										for (int j = 0; j < monxy.length ; j++) {
//											Position position = setPosition(monxy[j][0],monxy[j][1]);
//											Monster monster = ManagerPool.monsterManager.createMonster(mondb.getQ_id(), gmap.getServerId(),  gmap.getLineId(),  (int)gmap.getId(), position);
//											monster.setRes(rnddb.getQ_sculpt_resid());
//											monster.setIcon(rnddb.getQ_head_resid());
//											//monster.setDistributeId(1); 设置为自动复活
//											ManagerPool.mapManager.enterMap(monster);
//										}
//									}
//								}
//							}
//						}
					}
				}
			}
		}
	}
	




	/**踩到传送点
	 * 
	 */
	@Override
	public void onTeleporter(Player player, int line, int tranId, int scriptid) {
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null && map.getZoneModelId() == zonemodelid) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone != null) {
				List<MapConfig> mapConfigs  =zone.getConfigs();
				int layers = ManagerPool.zonesManager.getFloor(player);
				if (layers == -1) {
					return;
				}
				
				int path = ZonesTeamManager.entrance.get(map.getMapModelid());	//正确路径
				Position pos = setPosition(entrxy[path-10][0],entrxy[path-10][1]);
				if (layers == 0) {	//第1层
					if (path == scriptid) {
						player.setTransType(1);
						ManagerPool.mapManager.changeMap(player,mapConfigs.get(layers+1).getMapId(), mapConfigs.get(layers+1).getMapModelId(), 1, pos, this.getClass().getName() + ".onTeleporter 1");	
					}else {//第1层，走错了回到中心
						player.setTransType(0);
						MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("此路不通，请换个入口"));
						ManagerPool.mapManager.changePosition(player, setPosition(58,63));
					}
				}else if (layers == 7) {	//第8层
					//BOSS被杀后到达第8层就算通关

				}else {	//其他层数
					if (path == scriptid) {
						player.setTransType(1);
						ManagerPool.mapManager.changeMap(player,mapConfigs.get(layers+1).getMapId(), mapConfigs.get(layers+1).getMapModelId(), 1, pos, this.getClass().getName() + ".onTeleporter 2");	
					}else {
						player.setTransType(2);
						getmoveEvent(player,zone,layers,pos);
					}
				}
			}
		}
	}

	
	
	
	/**生成坐标点
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Position setPosition(int x ,int y){
		Position position = new Position();
		position.setX((short) (x*MapUtils.GRID_BORDER));
		position.setY((short) (y*MapUtils.GRID_BORDER));
		return position;
	}
	
	
	
	/**刷新盒子
	 * 
	 */
	public int refreshBox(Player player, Map map) {
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if (zone != null) {
			for (int[] mons : refreshBox) {
				if (!zone.getOthers().containsKey("RefreshBox"+mons[1])) {
					if( RandomUtils.random(1, 100) <= mons[0]){
						zone.getOthers().put("RefreshBox"+mons[1], mons[1]);	//标记已刷
						int num = RandomUtils.random(mons[2],mons[2]+2);
						for (int i = 0; i < num; i++) {
							List<Grid> gridlista = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(55, 61, map.getMapModelid()),25*MapUtils.GRID_BORDER , map.getMapModelid());
							int idx=RandomUtils.random(gridlista.size());
							Monster monster = ManagerPool.monsterManager.createMonsterAndEnterMap(mons[1], map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlista.get(idx).getCenter());
							monster.getParameters().put("bgzmonsterdrop",""+map.getZoneId());
						}
						return mons[1];		
					}
				}
			}
		}
		return 0;
	}
	
	
	/**传送事件
	 * 
	 */
	public void getmoveEvent(Player player,ZoneContext zone,int layers , Position pos){
		List<MapConfig> mapConfigs  =zone.getConfigs();
		List<Integer> rndlist = new ArrayList<Integer>();
		for (int[] moves : rndmove) {
			rndlist.add(moves[0]);
		}
		int idx = RandomUtils.randomIndexByProb(rndlist);
		if (rndmove[idx][1] == 0) {	//倒退至第一层
			ManagerPool.mapManager.changeMap(player,mapConfigs.get(0).getMapId(), mapConfigs.get(0).getMapModelId(), 1, pos, this.getClass().getName() + ".getmoveEvent 1");	
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("很不幸，您踩入了错误入口，倒退至第1层"));
		}else if (rndmove[idx][1] == 3) {//传送至第四层
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("奇异的入口，您进入第四层"));
			ManagerPool.mapManager.changeMap(player,mapConfigs.get(3).getMapId(), mapConfigs.get(3).getMapModelId(), 1, pos, this.getClass().getName() + ".getmoveEvent 2");
		}else  if (rndmove[idx][1] < 0){
			int daotui = Math.abs(rndmove[idx][1]);
			int num =layers - daotui;
			
			if (num < 0) {
				num = 0;
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("很不幸，您踩入了错误入口，倒退至第1层"));
				notify_zone_player(zone.getId(),Notifys.CUTOUT,ResManager.getInstance().getString("【{1}】很不幸踩入了错误入口，倒退至第1层"),player.getName(),Math.abs(daotui)+"",(num+1)+"");
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("很不幸，您踩入了错误入口，倒退了{1}层"),Math.abs(daotui)+"");
				notify_zone_player(zone.getId(),Notifys.CUTOUT,ResManager.getInstance().getString("【{1}】不幸倒退了{2}层，到达了第{3}层"),player.getName(),Math.abs(daotui)+"",(num+1)+"");
			}

			ManagerPool.mapManager.changeMap(player,mapConfigs.get(num).getMapId(), mapConfigs.get(num).getMapModelId(), 1, pos, this.getClass().getName() + ".getmoveEvent 3");
		}
	}
	
	
	
	/**设置八卦阵随机入口
	 * 
	 */
	public void setentrance(){
		ZonesTeamManager.entrance.clear();
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zonemodelid);
		List<Integer> mapidlist = JSON.parseArray(zonedata.getQ_mapid(),Integer.class);
		List<Integer> rndList = new ArrayList<Integer>();
		for (int i = 10; i <= 17; i++) {
			rndList.add(i);
		}
		
		for (int mapid : mapidlist) {
			int rnd = RandomUtils.random(rndList.size());
			int ent = rndList.remove(rnd);
			ZonesTeamManager.entrance.put(mapid, ent);
		}
	}
	
	
	

	@Override
	public void action(int serverId, String serverWeb) {
		long timems = System.currentTimeMillis();
		int hour = TimeUtil.getDayOfHour(timems);
		int min = TimeUtil.getDayOfMin(timems);
		//台湾版暂不开放
//		if (WServer.getInstance().getServerWeb().equals("twgmei")) {	
//			return;
//		}
		if (hour == 0 && (min >= 0 || min <= 2)) {			//0点必然清理一次
			ZonesTeamManager.entrance.clear();	
		}
		
		if(ZonesTeamManager.entrance.size()==0){	//其他时间，或者重启后会自动生成
			setentrance();
		}
		
		
		
		if (hour == 13 && min == 0 ) {			//发布副本开启国家公告
			ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
			cmsg.setZonemodelid(zonemodelid);
			MessageUtil.tell_world_message(cmsg);
		}
		
		
		 
	}
	
	
	/**对副本内玩家广播
	 * 
	 * @param zoneid
	 * @param type
	 * @param message
	 * @param values
	 */
	public void notify_zone_player(long zoneid , Notifys type, String message, String... values) {
		if (zoneid > 0) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneid);
			if (zone == null) {
				return;
			}
			@SuppressWarnings("unchecked")
			List<Long> pidlist = (List<Long>)zone.getOthers().get("teamlist");	//读出副本人物
			for (long pid : pidlist) {	
				Player member = ManagerPool.playerManager.getOnLinePlayer(pid);
				if (member != null) {
					Map map = ManagerPool.mapManager.getMap(member);
					if (map != null) {
						if (ZonesTeamManager.entrance.containsKey(map.getMapModelid())) {
							MessageUtil.notify_player(member,type, message,values);
						}
					}
				}	
			}
		}
	}
	
	
	
	/**队伍群发消息
	 * 
	 * @param team
	 * @param message
	 */
	public void tell_team_message(long zoneid,Message message) {
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneid);
		if (zone == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		List<Long> pidlist = (List<Long>)zone.getOthers().get("teamlist");	//读出副本人物
		for (long pid : pidlist) {	
			Player member = ManagerPool.playerManager.getOnLinePlayer(pid);
			if (member != null) {
				Map map = ManagerPool.mapManager.getMap(member);
				if (ZonesTeamManager.entrance.containsKey(map.getMapModelid())) {
					message.getRoleId().clear();
					MessageUtil.tell_player_message(member, message);
				}
			}	
		}
	}
	
	
	
	
	/**击杀木桶怪物
	 * 
	 * @param list
	 */
	public void bgzmonsterdrop(List<Object> list){
		Player player = (Player) list.get(0);
		int monmodelid = (Integer) list.get(1);
		if (monmodelid == 11068) {//经验木桶怪物给与玩家经验数=玩家等级*玩家等级*玩家等级
			int exp = player.getLevel()*player.getLevel()*player.getLevel();
			ManagerPool.playerManager.addExp(player,exp ,AttributeChangeReason.EXP_CASK);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("击破{1}，获得经验（{2}）"), ManagerPool.monsterManager.getName(monmodelid),exp+"");
		}else if (monmodelid == 11069) {//真气木桶怪物给与玩家真气数=玩家等级*玩家等级
			int zhenqi = player.getLevel()*player.getLevel();
			ManagerPool.playerManager.addZhenqi(player, zhenqi,AttributeChangeReason.ZHENQI_CASK);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("击破{1}，获得真气（{2}）"), ManagerPool.monsterManager.getName(monmodelid),zhenqi+"");
		}
	}
	
	
	/**BOSS死亡触发
	 * 
	 * @param list
	 */
	public void bgzbossdie(List<Object> list){
		Player player = (Player) list.get(0);
		if (player == null) {
			log.error("八卦阵：BOSS死亡触发，玩家空");
		}else {
			log.error("八卦阵：BOSS死亡触发，玩家存在："+player.getName());
		}
		//Monster monster = (Monster) list.get(1);
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null) {
			if (map.getZoneId() > 0 && map.getZoneModelId() == zonemodelid) {
				ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (zone == null) {
					return;
				}
				if ( !zone.getOthers().containsKey("boss_isdie")) {
					zone.getOthers().put("boss_isdie", map.getZoneId());
					triggerReward(player,map.getMapModelid(),map.getZoneId());
					notify_zone_player(map.getZoneId(),Notifys.CUTOUT,ResManager.getInstance().getString("八层BOSS已被{1}成功击杀,其他玩家只需尽快抵达第八层即可通关。"),player.getName());
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("zonemodelid", BaGuaZhengScript.zonemodelid);
					paramMap.put("bossdie", 1);
					ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
					sendMessage.setScriptid(getId());
					sendMessage.setType(1);
					sendMessage.setMessageData(JSON.toJSONString(paramMap));
					
					tell_team_message(map.getZoneId(),sendMessage);
				}
			}
		}	
	}
	
	
	/**触发奖励
	 * 
	 * @param player
	 * @param mapmodelid
	 * @param zoneid
	 */
	public void triggerReward(Player player,int mapmodelid,long zoneid){
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null) {
			int zoneModelId = map.getZoneModelId();
			if (map.getZoneId() > 0) {
				ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (zone == null) {
					return;
				}
				@SuppressWarnings("unchecked")
				List<Long> teamlist = (List<Long>)zone.getOthers().get("teamlist");
				for (long id : teamlist) {
					Player mPlayer = ManagerPool.playerManager.getOnLinePlayer(id);
					if (mPlayer != null) {
						if ( mapmodelid == mPlayer.getMapModelId() && !zone.getOthers().containsKey("rew"+id)) {
							ZoneLog zlog = new ZoneLog();
							zlog.setPlayerid(mPlayer.getId());
							zlog.setType(3);
							zlog.setZonemodelid(zoneModelId);
							zlog.setSid(player.getCreateServerId());
							LogService.getInstance().execute(zlog);
							
							zone.getOthers().put("rew"+id,zoneModelId);
							mPlayer.getZoneinfo().put(ZonesManager.ManualDeathnum+"_"+zoneModelId,0);
							mPlayer.getZoneinfo().put(ZonesManager.killmonsternum+"_"+zoneModelId,0);
							int time = (Integer)zone.getOthers().get("time");
							int newtime = (int)(System.currentTimeMillis()/1000 - time);
							mPlayer.getZoneinfo().put(ZonesManager.Manualendtime+"_"+zoneModelId, newtime);
							
							ManagerPool.zonesFlopManager.addZoneReward(mPlayer, zoneModelId, false);
							ManagerPool.zonesFlopManager.stZonePassShow(mPlayer, 3, zoneModelId);
							setClearance(mPlayer,zoneModelId);
							

						}
					}
				}
			}
		}	
	}
	
	
	/**设定参与通关
	 * 
	 * @param player
	 * @param zoneid
	 */
	public void setClearance(Player player ,int zoneModelId){
		long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId);
		if (status < 2) {//（记录是否参与）（通关后设置为2）
			ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId, 1);
		}
	}

	
	
}
