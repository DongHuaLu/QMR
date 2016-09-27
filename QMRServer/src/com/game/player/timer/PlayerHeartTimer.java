package com.game.player.timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.cooldown.structs.CooldownTypes;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.manager.DataManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dblog.LogService;
import com.game.fight.structs.FighterState;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Enemy;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.structs.Position;
import com.game.timer.TimerEvent;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.TimeUtil;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.structs.ZoneContext;

public class PlayerHeartTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(PlayerHeartTimer.class);
	private int serverId;
	private int lineId;
	private int mapId;

	public PlayerHeartTimer(int serverId, int lineId, int mapId) {
		super(-1, 1000);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		boolean protect = (mapBean.getQ_map_hangprotection() == 1);
		int remainTime = 0;
		if (protect) {
			protect = ManagerPool.playerManager.inProtectTime();
			if(protect){
				//计算到保护开始时间过去的时间
				remainTime = (int)(ManagerPool.playerManager.getProtectCostTime());
			}
		}
		//遍历玩家列表
//		Player[] players = map.getPlayers().values().toArray(new Player[0]);
//		for (Player player : players) {
		List<Player> waitquit = new ArrayList<Player>();
		List<Player> waitrevive = new ArrayList<Player>();
		List<Player> revivelist = new ArrayList<Player>();
		List<Player> zoneteamlist = new ArrayList<Player>();
		List<Player> gatherlist = new ArrayList<Player>();
		int day = TimeUtil.getDayOfMonth(System.currentTimeMillis());
		//遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			if (player == null) {
				continue;
			}
			//不是本线玩家
			if (player.getServerId() != this.serverId || player.getLine() != this.lineId || player.getMap() != this.mapId) {
				continue;
			}

			if (player.isSave()) {
				ManagerPool.playerManager.updatePlayer(player);
				player.setSave(false);
			}
			//累计在线总时间(秒)
			player.setAccunonlinetime(player.getAccunonlinetime() + 1);
			
			//今日累计在线时间(秒)
			if (player.getCurday() == day ) {
				player.setDayonlinetime(player.getDayonlinetime() + 1);
			}else {
				player.setCurday(day);
				player.setDayonlinetime(1);
			}
			
			if(!player.isDie()&& !ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.RECOVER, null)&&!PlayerDaZuoManager.getInstacne().isSit(player)){
				//非打坐状态下的血量恢复
				int maxHp = player.getMaxHp();
				int maxMp = player.getMaxMp();
				int maxSp = player.getMaxSp();
				
				Q_characterBean levelModel = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
				//生命恢复
				if(player.getHp() < maxHp){
					int revocer = levelModel.getQ_auto_recover_hp();
					player.setHp(player.getHp() + revocer);
					if(player.getHp() > maxHp) player.setHp(maxHp);
					ManagerPool.playerManager.onHpChange(player);
					
					int min = maxHp * 2000/ Global.MAX_PROBABILITY;
					if(revocer >= min){
						log.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命" + revocer + ",因为自动回复");
					}
				}
				
				//内力恢复
				if(player.getMp() < maxMp && !FighterState.JINZHINEILIHUIFU.compare(player.getFightState())){
					int revocer = levelModel.getQ_auto_recover_mp();
					player.setMp(player.getMp() + revocer);
					if(player.getMp() > maxMp) player.setMp(maxMp);
					ManagerPool.playerManager.onMpChange(player);
				}
				
				//体力恢复
				if(player.getSp() < maxSp && !FighterState.JINZHITILIHUIFU.compare(player.getFightState())){
					int revocer = levelModel.getQ_auto_recover_sp();
					player.setSp(player.getSp() + revocer);
					if(player.getSp() > maxSp) player.setSp(maxSp);
					ManagerPool.playerManager.onSpChange(player);
				}
				
				//添加冷却
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.RECOVER, null, Global.RECOVER_TIME);
			}

			//判断自动复活
			if (player.isDie() && PlayerState.AUTOREVIVE.compare(player.getState()) && player.getDieTime() <= System.currentTimeMillis() - Global.RECOVER_TIME) {
				//设状态为非自动复活
				player.setState(PlayerState.NOREVIVE);
				//自动复活
				ManagerPool.playerManager.revive(player, 3);
			}

			
			if(player.getPlacerevivetime() > 0){
				//检测原地复活	5秒
				if(player.isDie() && System.currentTimeMillis() - player.getPlacerevivetime()>= 5*1000){
					revivelist.add(player);
				}
			}else {
				//检测回城复活  30秒
				if(player.isDie() && System.currentTimeMillis() -player.getDieTime() >= 30*1000){
					waitrevive.add(player);
				}	
			}

			//采集
			if(PlayerState.GATHER.compare(player.getState())){
				if(System.currentTimeMillis() > player.getGatherStarttime() + player.getGatherCosttime()){
					gatherlist.add(player);
				}
			}
			//骑乘
			if (player.getRidingtime() > 0 &&  player.getAccunonlinetime() - player.getRidingtime() > 5 ) {
				player.setRidingtime(0);
				ManagerPool.horseManager.rideHorse(player);
			}
			
			//龙元心法
			if (player.getLongyuantime() > 0 &&  System.currentTimeMillis()/1000 - player.getLongyuantime() > 10 ) {
				player.setLongyuantime(0);
				ManagerPool.longyuanManager.stRunResLongYuanActivation(player);
			}
			
			//骑战兵器
			{
				HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
				if(weapon!=null && weapon.getOvertime()!=-1 && System.currentTimeMillis()>weapon.getOvertime()){
					if(ManagerPool.horseWeaponManager.isWearHorseWeapon(player)) ManagerPool.horseWeaponManager.unwearHorseWeapon(player);
				}
			}
			
			//暗器
			{
			HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
			if(weapon!=null && weapon.getOvertime()!=-1 && System.currentTimeMillis()>weapon.getOvertime()){
				if(ManagerPool.hiddenWeaponManager.isWearHiddenWeapon(player)) ManagerPool.hiddenWeaponManager.unwearHiddenWeapon(player);
			}
			}
			
			//是否开启挂机保护BUFF
			if (protect && remainTime > 0) {
				if (PlayerState.AUTOFIGHT.compare(player.getState()) && !FighterState.PKBAOHUFORNIGHT.compare(player.getFightState()) && System.currentTimeMillis() - Global.PROTECT_IN_NIGHT_DEALY >= player.getAutoStartTime()) {
					//添加保护buff
					ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_IN_NIGHT, -remainTime, 0, 0);
					log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")增加夜晚和平保护buff");	
				}
			}

			//是否进行副本传送
			if (player.getZoneteamenterid() > 0) {
				zoneteamlist.add(player);
			}
			
			//清除敌对目标
			if (player.getEnemys().size() > 0) {
				long overdue = System.currentTimeMillis() - Global.ENEMY_TIME;
				Iterator<Enemy> enemyIter = player.getEnemys().values().iterator();
				while (enemyIter.hasNext()) {
					Enemy enemy = (Enemy) enemyIter.next();
					if (enemy.getLastTime() < overdue) {
						enemyIter.remove();
					}
				}
			}

			//判断是否可以退出战斗状态
			if (PlayerState.FIGHT.compare(player.getState()) && player.getLastFightTime() + Global.FIGHT_OVERDUE < System.currentTimeMillis()) {
				log.debug("out fight state:" + System.currentTimeMillis());
				//设置状态为普通
				player.setState(PlayerState.NOTHING);
				//清楚攻击和被攻击列表
				player.getHits().clear();
			}

			//判断是否可以退出游戏
			if (!PlayerState.FIGHT.compare(player.getState()) && !PlayerState.JUMP.compare(player.getState()) && !PlayerState.DOUBLEJUMP.compare(player.getState()) && PlayerState.QUITING.compare(player.getState())) {
				waitquit.add(player);
			}
		}

		for (int i = 0; i < waitquit.size(); i++) {
			ManagerPool.playerManager.quit(waitquit.get(i));
		}
		
		for (int i = 0; i < waitrevive.size(); i++) {
			ManagerPool.playerManager.revive(waitrevive.get(i), 2);
		}
		
		for (int i = 0; i < revivelist.size(); i++) {
			ManagerPool.playerManager.revive(revivelist.get(i), 1);
		}
		
		for (int i = 0; i < gatherlist.size(); i++) {
			ManagerPool.npcManager.playerFinishGather(gatherlist.get(i));
		}
		//传送进入多人副本
		for (Player tmpmember : zoneteamlist) {
			long zid = tmpmember.getZoneteamenterid();
			if (zid > 0) {
				tmpmember.setZoneteamenterid(0);
				ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zid);
				if (zone != null) {
					Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zone.getZonemodelid());
					
					long manual = ManagerPool.countManager.getCount(tmpmember, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id());
					if (manual == 0) {	//初始化计数器
						ManagerPool.countManager.addCount(tmpmember, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id(),1, 0, 0);
					}
					ManagerPool.countManager.addCount(tmpmember, CountTypes.ZONE_MANUAL, ""+zonedata.getQ_id(), 1);//进入次数+1
					
					long status = ManagerPool.countManager.getCount(tmpmember, CountTypes.ZONE_TEAM_ST, ""+zonedata.getQ_id());
					if (status == 0) {//初始化计数器（记录是否参与）（通关后设置为2）
						ManagerPool.countManager.addCount(tmpmember, CountTypes.ZONE_TEAM_ST, ""+zonedata.getQ_id(),1, 1, 0);
					}
					tmpmember.getZoneinfo().put(ZonesManager.ManualDeathnum+"_"+zonedata.getQ_id(), 0);
					tmpmember.getZoneinfo().put(ZonesManager.killmonsternum+"_"+zonedata.getQ_id(), 0);
					tmpmember.getZoneinfo().put(ZonesManager.Manualendtime+"_"+zonedata.getQ_id(), 0);
					if(zonedata.getQ_map_group() > 1 ){	//分组
						try {
							//"coordinate" 自定义坐标，配置在地图创建的时候
							List<Integer[]> xy = JSON.parseArray((String)zone.getOthers().get("coordinate"), Integer[].class);
							int griup = tmpmember.getGroupmark();
							int x= xy.get(griup-1)[0];
							int y= xy.get(griup-1)[1];
							Position position = new Position();
							position.setX((short) (x*MapUtils.GRID_BORDER));
							position.setY((short) (y*MapUtils.GRID_BORDER));
							ManagerPool.mapManager.changeMap(tmpmember, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, position, this.getClass().getName() + ".action 1");
						} catch (Exception e) {
							log.error("副本分组传送坐标未设定coordinate"+e);
						}
					}else {
						Position position = new Position();
						position.setX((short) (zonedata.getQ_x()*MapUtils.GRID_BORDER));
						position.setY((short) (zonedata.getQ_y()*MapUtils.GRID_BORDER));
						ManagerPool.mapManager.changeMap(tmpmember, zone.getConfigs().get(0).getMapId(), zone.getConfigs().get(0).getMapModelId(), 1, position, this.getClass().getName() + ".action 2");	
					}
					
					ZoneLog zlog = new ZoneLog();
					zlog.setPlayerid(tmpmember.getId());
					zlog.setZonemodelid(zone.getZonemodelid());
					zlog.setType(-1);
					zlog.setSid(tmpmember.getCreateServerId());
					LogService.getInstance().execute(zlog);
				}
			}
		}
		
	}
}
