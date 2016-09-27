package scripts.zone.bowboss;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.game.count.structs.CountTypes;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.util.TimerUtil;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;
import com.game.zones.log.ZoneLog;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneLoopEventTimer;

public class BowBossRoomFiveScript  implements ICreateZoneScript, IEnterMapScript, IZoneEventTimerScript, IMonsterDieScript{
	//副本结束
	private static String FINISH = "finish";
	
	//副本boss id
	private static String BOSSID = "bossid";
	
	//副本boss狂暴
	private static String KUANGBAO = "kuangbao";
	
	//副本已经开始刷新buff计时
	private static String REFRESHBUFF = "refreshbuff";
	
	//刷新buff时间
	private static String BUFFTIME = "bufftime";
	
	//弓箭boss副本模版ID
	private int ZONEID = 4005;	
	
	//弓箭boss地图ID
	private int[] ZONEMAPIDLIST = {
			30011};
	
	//中心点坐标
	private int[] center = {65, 61};
	
	//boss
	private int BOSS = 14005;

	//buff
	private int BUFF = 24005;
	
	//buff刷新间隔
	private int BTIME = 10 * 1000;
	
	private short enter_x = 33;			//进入副本gridX
	private short enter_y = 53;			//进入副本gridY
	
	//狂暴buff
	private int KB_BUFF = 24001;
	
	@Override
	public int getId() {
		return 4105;
	}
	
	
	
	
	/**
	 * 七曜战将
	 */
	public BowBossRoomFiveScript(){

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
			ZoneContext zone = ManagerPool.zonesManager.setZone("弓箭BOSS副本1", others, maplist, ZONEID);	//创建副本，返回副本消息
			MapConfig config = zone.getConfigs().get(0);
			Map zoneMap = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
			
			zone.getOthers().put("time", (int)(System.currentTimeMillis() / 1000));
			//刷新BOSS
			Grid grid = MapUtils.getGrid(center[0], center[1], config.getMapModelId());
			Monster monster = ManagerPool.monsterManager.createMonster(BOSS, config.getServerId(), config.getLineId(), config.getMapId(), grid.getCenter());
			ManagerPool.mapManager.enterMap(monster);
			
			zone.getOthers().put(BOSSID, monster.getId());
			
			zoneMap.setRound(Math.max(zoneMap.getWidth(), zoneMap.getHeight()) * 2 + 1);
			
			//拉人进副本
			ManagerPool.mapManager.changeMap(player, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, getPosition(enter_x, enter_y, zone.getConfigs().get(0).getMapModelId()), this.getClass().getName() + ".onCreate");
			
			return zone;
		}
		return null;
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if(map.getMapModelid()==ZONEMAPIDLIST[0]){
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if(zone!=null && !zone.getOthers().containsKey(REFRESHBUFF)){
				

				zone.getOthers().put(REFRESHBUFF, 1);
//				
//				MessageUtil.notify_player(player, Notifys.CUTOUT, "守住金剑不要让怪物碰触他，否则他会减血");
//				MessageUtil.notify_player(player, Notifys.CUTOUT, "击杀全部20波怪物为胜，金剑血量为0则败");
//				
				//开始定时算buff
				ScriptsUtils.call(this.getId(), "refreshBuff", map.getZoneId(), map.getZoneModelId());
				ZoneLog zlog = new ZoneLog();
				zlog.setPlayerid(player.getId());
				zlog.setZonemodelid(ZONEID);
				zlog.setType(-1);
				zlog.setSid(player.getCreateServerId());
				LogService.getInstance().execute(zlog);

			}
		}
	}
	
	/**
	 * 定时刷buff
	 * @param parameters
	 */
	public void refreshBuff(List<Object> parameters) {
		//开始刷新怪
		long zoneId = (Long)parameters.get(0);
		int zoneModelId = (Integer)parameters.get(1);
		
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
		
		//是否在冷却中
		if(zone.getOthers().containsKey(BUFFTIME)){
			long time = (Long)zone.getOthers().get(BUFFTIME);
			if(System.currentTimeMillis() - time < BTIME){
				return;
			}
		}
		
		//找到地图
		MapConfig mapconfig = zone.getConfigs().get(0);
		Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());

		//找到地图中boss
		Monster monster = ManagerPool.monsterManager.getMonster(map, (Long)zone.getOthers().get(BOSSID));
		if(monster==null) return;
		//计算boss血量
		int hpPercent = (int)(((double)monster.getHp() * 100)/monster.getMaxHp());
		
		if(hpPercent < 50){
			if(!zone.getOthers().containsKey(KUANGBAO)){
				zone.getOthers().put(KUANGBAO, System.currentTimeMillis());
				//增加狂暴
				ManagerPool.buffManager.addBuff(monster, monster, KB_BUFF, 0, 0, 0);
				//提示
				MessageUtil.notify_map(map, Notifys.CHAT_BULL, ResManager.getInstance().getString("BOSS进入狂暴状态，其攻击速度大幅提升！"));
			}
		}
				
		if(hpPercent < 30){			
			//技能B
			ManagerPool.buffManager.addBuff(monster, monster, BUFF, 0, 0, 0);
			zone.getOthers().put(BUFFTIME, System.currentTimeMillis());
			
			//提示
			MessageUtil.notify_map(map, Notifys.CHAT_BULL, ResManager.getInstance().getString("BOSS施放战王附体，其攻击力大幅提升！"));
		}
		

	}

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		Map map = ManagerPool.mapManager.getMap(monster);
		
		if(map==null || map.getMapModelid()!=ZONEMAPIDLIST[0]){
			return;
		}
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if(monster.getModelId()==BOSS){
			finish(map, zone, true);
		}
		
	}
	
	private void finish(Map map, ZoneContext context, boolean success){
		//副本设置结束
		context.getOthers().put(FINISH, 1);
		
		int zoneModelid = context.getZonemodelid();
		
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			
			
			long manual = ManagerPool.countManager.getCount(player, CountTypes.ZONE_MANUAL, ""+zoneModelid);
			if (manual== 0) {//现在改成手动和自动共用一个变量
				ManagerPool.countManager.addCount(player, CountTypes.ZONE_MANUAL, ""+zoneModelid,1, 0,0);
				ManagerPool.zonesManager.qiyaoReward(player, ZONEID, 4);	//增加奖励次数
			}
			ManagerPool.countManager.addCount(player, CountTypes.ZONE_MANUAL, zoneModelid+"", 1);//增加进入次数
			ManagerPool.zonesManager.stReqZoneOpenPanelMessage(player,null);//刷新副本面板消息
			ManagerPool.zonesManager.stResZoneSurplusSum(player);
			
			
		}
	}
	
	public void quit(List<Object> objs){
		ZoneContext context = (ZoneContext)objs.get(0);
		ManagerPool.zonesManager.deleteZone(context.getId());
	}
	
	private Position getPosition(int x, int y, int mapModelId) {
		Grid grid = MapUtils.getGrid(x, y, mapModelId);
		if (grid != null) {
			return grid.getCenter();
		}
		return new Position((short) 500, (short) 500);
	}

//	/**设定参与通关
//	 * 
//	 * @param player
//	 * @param zoneid
//	 */
//	public void setClearance(Player player ,int zoneModelId){
//		long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId);
//		if (status < 2) {//（记录是否参与）（通关后设置为2）
//			ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId, 1);
//		}
//	}
	
//	//-----------------副本追踪面板
//	public void notify(ZoneContext zone, Player player){
//		HashMap<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("zonemodelid", ZONEID);
//		paramMap.put("max", MAX);
//		int kill = (Integer)zone.getOthers().get(KILL);
//		paramMap.put("kill", kill);
//		
//		int loop = (Integer)zone.getOthers().get(LOOP);
//		paramMap.put("loop", MAXLOOP - loop);
//		
//		long nextloop = (Long)zone.getOthers().get(NEXTLOOP);
//		int time = (int)((System.currentTimeMillis() - nextloop)/1000);
//		if(time < 0) time = 0;
//		paramMap.put("nextloop", time);
//
//		
//		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
//		sendMessage.setScriptid(getId());
//		sendMessage.setType(1);
//		sendMessage.setMessageData(JSON.toJSONString(paramMap));
//		MessageUtil.tell_player_message(player, sendMessage);
//	}
//	
//	//-----------------副本追踪面板
//		public void notify(ZoneContext zone, Map map){
//			HashMap<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("zonemodelid", ZONEID);
//			paramMap.put("max", MAX);
//			int kill = (Integer)zone.getOthers().get(KILL);
//			paramMap.put("kill", kill);
//			
//			int loop = (Integer)zone.getOthers().get(LOOP);
//			paramMap.put("loop", MAXLOOP - loop);
//			
//			long nextloop = (Long)zone.getOthers().get(NEXTLOOP);
//			int time = (int)((System.currentTimeMillis() - nextloop)/1000);
//			if(time < 0) time = 0;
//			paramMap.put("nextloop", time);
//
//			
//			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
//			sendMessage.setScriptid(getId());
//			sendMessage.setType(1);
//			sendMessage.setMessageData(JSON.toJSONString(paramMap));
//			MessageUtil.tell_map_message(map, sendMessage);
//		}
//
//		
//		
//		@Override
//		public void action(int serverId, String serverWeb) {
//			int timems = (int)(System.currentTimeMillis()/1000);
//			int hour = TimeUtil.getDayOfHour(timems);
//			int min = TimeUtil.getDayOfMin(timems);
//
//			if (hour == 19 && min == 30 ) {			//发布副本开启国家公告(不去解析字符串了)
//				ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
//				cmsg.setZonemodelid(ZONEID);
//				MessageUtil.tell_world_message(cmsg);
//			}
//		}
}
