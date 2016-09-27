package scripts.zone.simen;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_fourjinsuoBean;
import com.game.data.bean.Q_monsterBean;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.server.script.IServerEventTimerScript;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.util.TimerUtil;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.message.ResZoneTeamOpenBullToClientMessage;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneEventTimer;
import com.game.zones.timer.ZoneLoopEventTimer;
/**
 * 四象剑阵
 */
public class SimenScript  implements ICreateZoneScript, IEnterMapScript, IZoneEventTimerScript, IMonsterDieScript ,IServerEventTimerScript{
	private static Logger log = Logger.getLogger(SimenScript.class);
	
	//副本结束
	private static String FINISH = "finish";
	//副本刷怪
	private static String REFRESH = "refresh";
	//副本正在刷怪中
	private static String REFRESHING = "refreshing";
	//副本已经杀怪数量
	private static String KILL = "kill";
	//波次
	private static String LOOP = "loop";
	//下轮刷怪时间
	private static String NEXTLOOP = "nextloop";
	
	//四门金锁副本模版ID
	private int ZONEID = 3003;	
	
	//四门金锁地图ID
	private int[] ZONEMAPIDLIST = {
			42111};
	
	//小怪的modelId
	private int[] monsters = new int[71];
	
	//BOSS的modelId
	private int[] bosses = new int[71];
	
	//对应4个刷怪点
//	西南：２４,１２８
//	东南：１３８,１２８
//	西北：６,４６
//	东北：１５４,４６
	private int[][] entrxy = {{24, 128}, {138, 128}, {6, 46}, {154, 46}};
	
	//中心点坐标
	private int[] center = {81, 89};
	
	//阿斗
	private int ADOU = 13501;
	
	private Vector<List<Position>> roads = new Vector<List<Position>>();

	private static int MAX = 0;
	
	private static int MAXLOOP = 0;

	
	@Override
	public int getId() {
		return 4010;
	}
	
	
	
	

	public SimenScript(){
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(ZONEMAPIDLIST[0]);
		Position end = MapUtils.getGrid(center[0], center[1], grids).getCenter();
		for (int i = 0; i < entrxy.length; i++) {
			Position start = MapUtils.getGrid(entrxy[i][0], entrxy[i][1], grids).getCenter();
			roads.add(MapUtils.findRoads(grids, start, end, -1, false));
		}
		List<Q_monsterBean> beans = ManagerPool.dataManager.q_monsterContainer.getList();
		int mnum = 0;
		int bnum = 0;
		for (Q_monsterBean q_monsterBean : beans) {
			if(q_monsterBean.getQ_id()>=12001 && q_monsterBean.getQ_id()<=12071){
				monsters[mnum] = q_monsterBean.getQ_id();
				mnum++;
			}else if(q_monsterBean.getQ_id()>=13001 && q_monsterBean.getQ_id()<=13071){
				bosses[bnum] = q_monsterBean.getQ_id();
				bnum++;
			}
		}
		
		List<Q_fourjinsuoBean> fbeans = ManagerPool.dataManager.q_fourjinsuoContainer.getList();
		for (int i = 0; i < fbeans.size(); i++) {
			MAX += (fbeans.get(i).getQ_num() * 4 + 1);
			MAXLOOP++;
		}
	}
	

	/**
	 * 副本创建
	 */
	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		if (zoneId == ZONEID) {
			//创建副本
			HashMap<String, Object> others = new HashMap<String, Object>();
			ArrayList<Integer> maplist = new ArrayList<Integer>();
			for (int i = 0; i < 1; i++) {
				maplist.add(ZONEMAPIDLIST[i]);
			}
			ZoneContext zone = ManagerPool.zonesManager.setZone("四门金锁副本", others, maplist, ZONEID);	//创建副本，返回副本消息
			MapConfig config = zone.getConfigs().get(0);
			Map zoneMap = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
			
			zone.getOthers().put("time", (int)(System.currentTimeMillis() / 1000));
			zone.getOthers().put(KILL, 0);
			//刷新阿斗
			Grid grid = MapUtils.getGrid(center[0], center[1], config.getMapModelId());
			Monster monster = ManagerPool.monsterManager.createMonster(ADOU, config.getServerId(), config.getLineId(), config.getMapId(), grid.getCenter());
			monster.setFriend(1);
			monster.setDirection((byte)0);
			ManagerPool.mapManager.enterMap(monster);
			
			zoneMap.setRound(Math.max(zoneMap.getWidth(), zoneMap.getHeight()) * 2 + 1);
			
			return zone;
		}
		return null;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if(map.getMapModelid()==ZONEMAPIDLIST[0]){
			
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("守住金剑不要让怪物碰触他，否则他会减血"));
			MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("击杀全部10波怪物为胜，金剑血量为0则败"));
			
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			//副本是否已经开始刷新
			if(zone.getOthers().containsKey(REFRESH)){
				notify(zone, player);
				return;
			}
			zone.getOthers().put(REFRESH, 1);
			
			zone.getOthers().put(REFRESHING, false);
			
			zone.getOthers().put(LOOP, 0);
			
			zone.getOthers().put(NEXTLOOP, System.currentTimeMillis() + 10000);
			//3秒后刷新怪
			ScriptsUtils.call(this.getId(), "refreshMonster", map.getZoneId(), map.getZoneModelId());
			
			notify(zone, player);
		}
	}
	
	public void refreshMonster(List<Object> parameters) {
		//开始刷新怪
		long zoneId = (Long)parameters.get(0);
		int zoneModelId = (Integer)parameters.get(1);
		
		int loop = 0;
		//下波怪刷新时间点
		long nextloop = System.currentTimeMillis() + 10000;
		
		parameters.clear();
		parameters.add(1);
		parameters.add(loop);
		parameters.add(nextloop);
		
		ZoneLoopEventTimer timer = new ZoneLoopEventTimer(this.getId(), zoneId, zoneModelId, parameters, 1000);
		TimerUtil.addTimerEvent(timer);
	}

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneId);
		//副本是否结束
		if(zone.getOthers().containsKey(FINISH)){
			return;
		}
		
		MapConfig mapconfig = zone.getConfigs().get(0);
		Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());

		//类型
		int type = (Integer)parameters.get(0);
		//轮数
		int loop = (Integer)parameters.get(1);
		
		if(type==1){
			long nextloop = (Long)parameters.get(2);
			
			//地图中只剩下一只怪物（阿斗）
			if(loop>1 && map.getMonsters().size()==1 && nextloop - System.currentTimeMillis() > 4000){
				boolean refreshing = (Boolean)zone.getOthers().get(REFRESHING);
				if(!refreshing){
					nextloop = System.currentTimeMillis() + 3000;
					zone.getOthers().put(NEXTLOOP, System.currentTimeMillis() + 3000);
					
					notify(zone, map);
				}
			}
			
			long time = nextloop - System.currentTimeMillis();
//			System.out.println(System.currentTimeMillis() + " remain:" + time);
			if(time>=3000 && time<4000 && loop < 20){
				MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("第【%d】波怪物3秒后来袭，请做好准备"), loop + 1));
			}
			
			//到达刷新时间
			if(System.currentTimeMillis() >= nextloop){
				
				boolean canRefresh = false;
				if(map.getMonsters().size()==1){
					Iterator<Monster> iter = map.getMonsters().values().iterator();
					Monster monster = (Monster) iter.next();
					if(monster.getModelId() == ADOU){
						canRefresh = true;
					}
				}
				
				loop++;
//				System.out.println("开始刷新第" + loop + "轮怪物");
				
				Q_fourjinsuoBean bean = ManagerPool.dataManager.q_fourjinsuoContainer.getMap().get(loop);
				if(bean==null){
					if(canRefresh){
						finish(map, zone, true);
					}
					return;
				}else{
					if(!canRefresh){
						return;
					}
				}
				
				int num = 0;
				
				List<Object> objs = new ArrayList<Object>();
				objs.add(2);
				objs.add(loop);
				objs.add(num);
				objs.add(0);
				
				zone.getOthers().put(LOOP, loop);
				zone.getOthers().put(REFRESHING, true);
				
				//准备刷新怪物
				ZoneEventTimer timer = new ZoneEventTimer(bean.getQ_num() + 1, this.getId(), zoneId, zoneModelId, objs, bean.getQ_mobs_interval_num());
				TimerUtil.addTimerEvent(timer);

				//刷新下次时间
				nextloop = System.currentTimeMillis() + bean.getQ_interval_num() * 1000;
				zone.getOthers().put(NEXTLOOP, nextloop);
				
				notify(zone, map);
			}
			
			parameters.remove(2);
			parameters.remove(1);
			parameters.add(loop);
			parameters.add(nextloop);
		}else if(type==2){
			Q_fourjinsuoBean bean = ManagerPool.dataManager.q_fourjinsuoContainer.getMap().get(loop);
			if(bean==null){
				return;
			}
			//次数
			int num = (Integer)parameters.get(2);
			int showmodel = (Integer)parameters.get(3);
			int createmodel = 0;
			
			int level = getLevel(map);
			if(level < 50) level = 50;
			int speed = 0;
			boolean isboss = false;
			
			int createnum = 0;
			if(num < bean.getQ_num()){
				if(showmodel==0){
					//刷新小怪
					showmodel = monsters[RandomUtils.random(monsters.length)];
				}
				level += bean.getQ_mon_level_add();
				if(level > 120) level = 120;
				createmodel = monsters[level - 50];
				speed = bean.getQ_move_speed();
				createnum = 4;
			}else{
				//刷新boss
				showmodel = bosses[RandomUtils.random(bosses.length)];
				level += bean.getQ_morimasa_level_add();
				if(level > 120) level = 120;
				createmodel = bosses[level - 50];
				zone.getOthers().put(REFRESHING, false);
				isboss = true;
				createnum = 1;
			}
			num++;
			
			Q_monsterBean show = ManagerPool.dataManager.q_monsterContainer.getMap().get(showmodel);
			
			
			for (int i = 0; i < createnum; i++) {
				//选择怪物出生点
				Position pos = null;
				//选择怪物路径
				List<Position> road = new ArrayList<Position>();
				List<Position> road2 = new ArrayList<Position>();
				
				if(isboss){
					//出生点
					int index = RandomUtils.random(4);
					Grid grid = MapUtils.getGrid(entrxy[index][0], entrxy[index][1], map.getMapModelid());
					pos = grid.getCenter();
					
					//移动路径
					road.addAll(roads.get(index));
					road2.addAll(roads.get(index));
				}else{
					//出生点
					Grid grid = MapUtils.getGrid(entrxy[i][0], entrxy[i][1], map.getMapModelid());
					pos = grid.getCenter();
					
					//移动路径
					road.addAll(roads.get(i));
					road2.addAll(roads.get(i));
				}
				
				//创建怪物
				Monster monster = ManagerPool.monsterManager.createMonster(createmodel, map.getServerId(), map.getLineId(), (int)map.getId(), pos);
				monster.setLevel(level);
				monster.setSpeed(speed);
				monster.setRoads(road);
				
				if(isboss){
					monster.setName(java.text.MessageFormat.format(ResManager.getInstance().getString("金锁守将[第{0}波]"), new Object[] {loop}));
				}else{
					monster.setName(show.getQ_name());
				}
				monster.setRes(show.getQ_sculpt_resid());
				monster.setIcon(show.getQ_head_resid());
				
				ManagerPool.mapManager.enterMap(monster);
				//设置怪物路径
				ManagerPool.mapManager.monsterRunning(monster, road2);
				
//				System.out.println("第" + loop + "轮->第" + num + "怪->monster " + monster.getId() + " start " + monster.getPosition() + " run " + MessageUtil.castListToString(road2));
			}
			
			
			
			parameters.remove(3);
			parameters.remove(2);
			parameters.add(num);
			parameters.add(showmodel);
		}
	}

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		Map map = ManagerPool.mapManager.getMap(monster);
		
		if(map==null || map.getMapModelid()!=ZONEMAPIDLIST[0]){
			return;
		}
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if(monster.getModelId()!=ADOU){
			int kill = (Integer)zone.getOthers().get(KILL);
			kill++;
			zone.getOthers().put(KILL, kill);
			
			notify(zone, map);
			return;
		}else{
			finish(map, zone, false);
		}
		
	}
	
	private int getLevel(Map map){
		int size = map.getPlayers().size();
		int total = 0;
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			total += player.getLevel();
		}
		if(size==0) return 0;
		else return total / size;
	}
	
	private void finish(Map map, ZoneContext context, boolean success){
		//副本设置结束
		context.getOthers().put(FINISH, 1);
		
		if(!success){
			try {
				Iterator<Player> iter = map.getPlayers().values().iterator();
				while (iter.hasNext()) {
					Player player = (Player) iter.next();
					log.error("四象剑阵失败:" + "player " + player.getId() + " " + player.getName());
				}
			} catch (Exception e) {
				log.error(e, e);
			}
			
			MessageUtil.notify_map(map, Notifys.CUTOUT, String.format(ResManager.getInstance().getString("金剑败亡，四门金锁阵破阵失败， 5秒后您将传出此副本")));
			
			ScriptsUtils.delayCall(this.getId(), "quit", 5000, context);
		}else{
			Iterator<Player> iter = map.getPlayers().values().iterator();
			while (iter.hasNext()) {
				Player player = (Player) iter.next();
				if (player != null) {
					if (!context.getOthers().containsKey("rew"+player.getId())) {
						ZoneLog zlog = new ZoneLog();
						zlog.setPlayerid(player.getId());
						zlog.setType(3);
						zlog.setZonemodelid(ZONEID);
						zlog.setSid(player.getCreateServerId());
						LogService.getInstance().execute(zlog);
						
						context.getOthers().put("rew"+player.getId(), ZONEID);
						player.getZoneinfo().put(ZonesManager.ManualDeathnum+"_"+ZONEID, 0);
						player.getZoneinfo().put(ZonesManager.killmonsternum+"_"+ZONEID, 0);
						int time = (Integer)context.getOthers().get("time");
						int newtime = (int)(System.currentTimeMillis()/1000 - time);
						player.getZoneinfo().put(ZonesManager.Manualendtime+"_"+ZONEID, newtime);
						
						ManagerPool.zonesFlopManager.addZoneReward(player, ZONEID, false);
						ManagerPool.zonesFlopManager.stZonePassShow(player, 3, ZONEID);
						
						setClearance(player, ZONEID);
					}
				}
			}
		}
	}
	
	public void quit(List<Object> objs){
		ZoneContext context = (ZoneContext)objs.get(0);
		ManagerPool.zonesManager.deleteZone(context.getId());
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
	
	//-----------------副本追踪面板
	public void notify(ZoneContext zone, Player player){
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("zonemodelid", ZONEID);
		paramMap.put("max", MAX);
		int kill = (Integer)zone.getOthers().get(KILL);
		paramMap.put("kill", kill);
		
		int loop = (Integer)zone.getOthers().get(LOOP);
		paramMap.put("loop", MAXLOOP - loop);
		
		long nextloop = (Long)zone.getOthers().get(NEXTLOOP);
		int time = (int)((System.currentTimeMillis() - nextloop)/1000);
		if(time < 0) time = 0;
		paramMap.put("nextloop", time);

		
		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
		sendMessage.setScriptid(getId());
		sendMessage.setType(1);
		sendMessage.setMessageData(JSON.toJSONString(paramMap));
		MessageUtil.tell_player_message(player, sendMessage);
	}
	
	//-----------------副本追踪面板
		public void notify(ZoneContext zone, Map map){
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("zonemodelid", ZONEID);
			paramMap.put("max", MAX);
			int kill = (Integer)zone.getOthers().get(KILL);
			paramMap.put("kill", kill);
			
			int loop = (Integer)zone.getOthers().get(LOOP);
			paramMap.put("loop", MAXLOOP - loop);
			
			long nextloop = (Long)zone.getOthers().get(NEXTLOOP);
			int time = (int)((System.currentTimeMillis() - nextloop)/1000);
			if(time < 0) time = 0;
			paramMap.put("nextloop", time);

			
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(getId());
			sendMessage.setType(1);
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_map_message(map, sendMessage);
		}

		
		
		@Override
		public void action(int serverId, String serverWeb) {
			//韩国版暂不开放
			if (WServer.getInstance().getServerWeb().equals("hgpupugame")) {	
				return;
			}
			long timems = System.currentTimeMillis();
			int hour = TimeUtil.getDayOfHour(timems);
			int min = TimeUtil.getDayOfMin(timems);

			if (hour == 19 && min == 30 ) {			//发布副本开启国家公告(不去解析字符串了)
				ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
				cmsg.setZonemodelid(ZONEID);
				MessageUtil.tell_world_message(cmsg);
			}
		}
}
